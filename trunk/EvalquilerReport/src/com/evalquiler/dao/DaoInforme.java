/**
 * 
 */
package com.evalquiler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.evalquiler.actionforms.encuesta.DatosEncuestaActionForm;
import com.evalquiler.actionforms.encuesta.PreguntasEncuestaActionForm;
import com.evalquiler.actionforms.encuesta.RespuestasPreguntaActionForm;
import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.comun.bbdd.ConexionBD;
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.comun.utilidades.UtilidadesFechas;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.encuesta.NoRecuperadaEncuestaExcepcion;
import com.evalquiler.excepciones.encuesta.NoRecuperadasPreguntasParaSolicitudExcepcion;
import com.evalquiler.excepciones.informe.SolicitudesConUnaFechaException;


/**
 * @author cachorro
 *
 */
public class DaoInforme {
	
		private final static String CONSULTAR_RESPUESTAS_ENC_VIV_1 = "SELECT A.IDENCUESTA, A.TITULO, B.IDPREGUNTA, B.DESCRIPCION AS DESC_PREG, " +
																     "C.IDRESPUESTA, C.DESCRIPCION AS DESC_RESP, COUNT(*) AS CONTADOR_RESPUESTAS " +
																     "FROM ENCUESTA A, PREGUNTA B, RESPUESTA C, RESPUESTAS_ENCUESTA E " +
																     "WHERE E.IDVIVIENDA = ? AND E.IDTIPOUSUARIO = ? ";
		private final static String AND_FECHAS					   = "AND E.FECHA_INICIO >= ? AND E.FECHA_FIN <= ? ";	
		private final static String CONSULTAR_RESPUESTAS_ENC_VIV_2 = "AND A.IDENCUESTA = E.IDENCUESTA " +
																   	 "AND B.IDPREGUNTA = E.IDPREGUNTA AND C.IDRESPUESTA = E.IDRESPUESTADADA " +
																   	 "GROUP BY E.IDENCUESTA, E.IDPREGUNTA, E.IDRESPUESTADADA " +
																   	 "ORDER BY e.IDENCUESTA, E.IDPREGUNTA, E.IDRESPUESTADADA";

		public final static int SENT_CONSULTAR_RESPUESTAS_ENC_VIV = 1;
	
	public static final Collection<DatosEncuestaActionForm> consultar(final DatosSolicitudInformeActionForm objetoIn, final int tipoConsulta) 
		throws ExcepcionEjecutarSentancia, NoRecuperadaEncuestaExcepcion, NoRecuperadasPreguntasParaSolicitudExcepcion, 
			   SolicitudesConUnaFechaException {
		
		Collection<DatosEncuestaActionForm> datosEncuesta	= null;
		DatosEncuestaActionForm 			encuesta 		= null;
		PreguntasEncuestaActionForm  		pregunta  		= null;
		RespuestasPreguntaActionForm 		respuesta 		= null;		
		
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection 		  conn  = null;
		int idPregunta 	  = 0;
		int idRespuesta	  = 0;
		int idEncuestaAux = 0;
		int idEncuesta    = 0;
		
		try {
			if (tipoConsulta == SENT_CONSULTAR_RESPUESTAS_ENC_VIV) {
				conn = ConexionBD.getConnection();
    			
				if (null != conn) {
    				pstmt = conn.prepareStatement(obtenerSentencia(objetoIn));
    				
    				if (null != pstmt) {
    					pstmt.setLong(1, objetoIn.getDatosVivienda().getIdVivienda());
    					pstmt.setInt(2, objetoIn.getIdTipoInforme());
    					if (objetoIn.tieneFechas()) {
    						pstmt.setDate(3, UtilidadesFechas.getDateForSql(objetoIn.getFechaInicio()));
    						pstmt.setDate(4, UtilidadesFechas.getDateForSql(objetoIn.getFechaFin()));
    					}
    					rs = pstmt.executeQuery() ; 
    					datosEncuesta = new ArrayList<DatosEncuestaActionForm>();
    					
    					while(rs.next()) {
    						idEncuesta = rs.getInt("IDENCUESTA");
    						if (idEncuesta != idEncuestaAux) {
    							encuesta = new DatosEncuestaActionForm();
    							encuesta.setIdEncuesta(rs.getInt("IDENCUESTA"));
    							encuesta.setTitulo(rs.getString("TITULO"));
    							idEncuestaAux = encuesta.getIdEncuesta();
    							encuesta.setPreguntas(new ArrayList<PreguntasEncuestaActionForm>());    							
    						}

    						int idPreguntaAux = rs.getInt("IDPREGUNTA");
    						if (idPregunta != idPreguntaAux) {
    							//Cada vez que se cambia de pregunta, excepto la primera vez que se entra 
    							//hay que anadir la pregunta a la encuesta.    							
    							if (null != pregunta) {
    								encuesta.getPreguntas().add(pregunta);
    							}
    							pregunta = new PreguntasEncuestaActionForm();
    							pregunta.setIdPregunta(idPreguntaAux);
    							pregunta.setDescripcion(rs.getString("DESC_PREG"));
    							pregunta.setRespuestas(new ArrayList<RespuestasPreguntaActionForm>());
    							idRespuesta = 0;
    						} 

    						idPregunta = idPreguntaAux;
    						
    						int idRespuestaAux = rs.getInt("IDRESPUESTA");
    						if (idRespuesta != idRespuestaAux) {
    							respuesta = new RespuestasPreguntaActionForm();
        						respuesta.setIdRespuesta(idRespuestaAux);
        						respuesta.setDescripcion(rs.getString("DESC_RESP"));
        						respuesta.setContadorRespuestas(rs.getInt("CONTADOR_RESPUESTAS"));
        						pregunta.getRespuestas().add(respuesta);        						
        						idRespuesta = idRespuestaAux;
    						}  
    					}
    					
    					if (null != pregunta) {
    						encuesta.getPreguntas().add(pregunta);
        					if (null != encuesta) {
        						datosEncuesta.add(encuesta);
        					} 
    					} else {
    						throw new NoRecuperadasPreguntasParaSolicitudExcepcion(String.valueOf(objetoIn.getDatosVivienda().getIdVivienda()), 
    																			  String.valueOf(objetoIn.getIdTipoInforme()), 
    																			  objetoIn.getFechaInicio(), objetoIn.getFechaFin());
    					}
    				} else {
    					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
    						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    						 		"error.global.mesage", 
    						 		"No se ha obtenido un preparedStatement en DaoInforme.consultar. TipoConsulta: " + SENT_CONSULTAR_RESPUESTAS_ENC_VIV);
    				}
    			} else {
    				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
    					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    					 		"error.global.mesage", 
    					 		"No se ha obtenido una conexi�n en DaoInforme.consultar. TipoConsulta: " + SENT_CONSULTAR_RESPUESTAS_ENC_VIV);
    			}
			} else {
				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
					 		"error.global.mesage", 
					 		"El tipo de sentencia que se quiere ejecutar no existe: " + tipoConsulta);
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
												 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
												 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
												 "error.global.mesage", 
												 "DaoInforme.consultar\n" + e.getMessage());
		} 

		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoInforme.consultar");
		return datosEncuesta;

	}

	
	private final static String obtenerSentencia(final DatosSolicitudInformeActionForm objetoIn) 
			throws SolicitudesConUnaFechaException {
		
		String sentencia = null;
		if (objetoIn.tieneFechas()) {
			sentencia = CONSULTAR_RESPUESTAS_ENC_VIV_1.concat(AND_FECHAS.concat(CONSULTAR_RESPUESTAS_ENC_VIV_2));
		} else if (objetoIn.noTieneFechas()) {
			sentencia = CONSULTAR_RESPUESTAS_ENC_VIV_1.concat(CONSULTAR_RESPUESTAS_ENC_VIV_2);
		} else {
			throw new SolicitudesConUnaFechaException(objetoIn.getIdSolicitudInforme());
		}
		
		return sentencia;
	}

	
}
