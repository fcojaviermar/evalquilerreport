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

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.comun.bbdd.ConexionBD;
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.comun.utilidades.UtilidadesFechas;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;


/**
 * @author cachorro
 *
 */
public class DaoSolicitud {
	
	private final static String CONSULTAR_SOLICITUD_PENDIENTES = "SELECT IDSOLICITUD, IDCLIENTE, IDVIVIENDA, IDTIPOINFORME, FECHAINICIO, " +
	 															 "FECHAFIN, FECHAALTA " +
	 															 "FROM SOLICITUD WHERE PROCESADO = 0";
	private final static String ACTUALIZAR_PROCESADO = "UPDATE SOLICITUD SET PROCESADO = 1 WHERE IDSOLICITUD = ?";
	
	public final static int SENT_SOLICITUD_PENDIENTES = 1;
	public final static int SENT_ACTUALIZAR_PROCESADO = 2;
	
	
	public static final Collection<DatosSolicitudInformeActionForm> consultar(DatosSolicitudInformeActionForm objetoIn, final int tipoConsulta) 
		throws ExcepcionEjecutarSentancia {
		
		Collection<DatosSolicitudInformeActionForm> datosSolicitudes = null;
		DatosSolicitudInformeActionForm 			datosSolicitud   = null;
		DatosClienteActionForm  datosCliente  = null;
		DatosViviendaActionForm datosVivienda = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection 		  conn  = null;
		
		try {
			if (tipoConsulta == SENT_SOLICITUD_PENDIENTES) {
				conn = ConexionBD.getConnection();
    			
				if (null != conn) {
    				pstmt = conn.prepareStatement(CONSULTAR_SOLICITUD_PENDIENTES);
    				
    				if (null != pstmt) {
    					rs = pstmt.executeQuery() ; 
    					
    					datosSolicitudes = new ArrayList<DatosSolicitudInformeActionForm>();
    					while(rs.next()) {
    						datosSolicitud = new DatosSolicitudInformeActionForm();
    						datosSolicitud.setIdSolicitudInforme(rs.getLong("IDSOLICITUD"));
    						datosSolicitud.setIdTipoInforme(rs.getInt("IDTIPOINFORME"));
    						datosSolicitud.setFechaInicio(UtilidadesFechas.getStringFromDate(rs.getDate("FECHAINICIO")));
    						datosSolicitud.setFechaFin(UtilidadesFechas.getStringFromDate(rs.getDate("FECHAFIN")));
    						datosSolicitud.setFechaAlta(UtilidadesFechas.getStringFromDate(rs.getDate("FECHAALTA")));
    						datosCliente = new DatosClienteActionForm();
    						datosCliente.setUser(rs.getString("IDCLIENTE"));
    						datosSolicitud.setDatosCliente(datosCliente);
    						datosVivienda = new DatosViviendaActionForm();
    						datosVivienda.setIdVivienda(rs.getLong("IDVIVIENDA"));
    						datosSolicitud.setDatosVivienda(datosVivienda);
    						datosSolicitudes.add(datosSolicitud);
    					}
    					
    				} else {
    					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
    						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    						 		"error.global.mesage", 
    						 		"No se ha obtenido un preparedStatement en DaoSolicitud.consultar. TipoConsulta: " + SENT_SOLICITUD_PENDIENTES);
    				}
    			} else {
    				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
    					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    					 		"error.global.mesage", 
    					 		"No se ha obtenido una conexi�n en DaoSolicitud.consultar. TipoConsulta: " + SENT_SOLICITUD_PENDIENTES);
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
												 "DaoSolicitud.consultar\n" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
				 		ConstantesCodigosExcepciones.CODIGO_EXCEPTION)), 
				 "error.global.mesage", 
				 "Exception: DaoSolicitud.consultar\n" + e.getMessage() + "\n");
			
		} 

		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoInforme.consultar");
		return datosSolicitudes;

	}

	
	public static final int actualizar(DatosSolicitudInformeActionForm datosSolicitudInforme, final int tipoConsulta) 
			throws ExcepcionEjecutarSentancia {
		
		PreparedStatement pstmt 	 = null;
		int 			  iResultado = 1;
		Connection conn = ConexionBD.getConnection();

		try {
			if (tipoConsulta == SENT_SOLICITUD_PENDIENTES) {
    			if (null != conn) {
    				pstmt = conn.prepareStatement(ACTUALIZAR_PROCESADO);
    
    				if (null != pstmt) {
    					pstmt.setLong(1, datosSolicitudInforme.getIdSolicitudInforme());
    
    					iResultado = pstmt.executeUpdate();
    					if (0 != iResultado ) { //Si se ha insertado el registro en la bbdd
    						conn.commit();
    					} else {
    						conn.rollback();
    					}
    				} else {
    					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
    						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    						 		"error.global.mesage", 
    						 		"No se ha obtenido un preparedStatement en DaoSolicitudInformes.insertar.");
    				}
    			} else {
    				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
    					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
    					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
    					 		"error.global.mesage", 
    					 		"No se ha obtenido una conexi�n en DaoSolicitudInformes.insertar.");
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
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "DaoSolicitudInformes.insertar\n" + e.getMessage());
		} 
		
		ConexionBD.cerrarConexiones(conn, pstmt, "DaoSolicitudInformes.insertar");
		return iResultado;
	}

	
}
