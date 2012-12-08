/**
 * 
 */
package com.evalquiler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.evalquiler.comun.bbdd.ConexionBD;
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.entidad.ElementoComboMunicipio;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;


/**
 * @author cachorro
 *
 */
public class DaoMunicipio {
	
	private final static String CONSULTAR_MUNICIPIO_POR_PK = "SELECT IDMUNICIPIO, DESCRIPCION " +
															 "FROM MUNICIPIO " +
															 "WHERE IDPROVINCIA = ? AND IDMUNICIPIO = ?";

	public static final ElementoComboMunicipio consultarPorPk(final String idProvincia, final String idMunicipio) throws ExcepcionEjecutarSentancia {
		ElementoComboMunicipio municipio = null;
		
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection conn = ConexionBD.getConnection();
		try {
			if (null != conn) {
				pstmt = conn.prepareStatement(CONSULTAR_MUNICIPIO_POR_PK);
				if (null != pstmt) {
					pstmt.setString(1, idProvincia);
					pstmt.setString(2, idMunicipio);
					rs = pstmt.executeQuery() ; 
					while(rs.next()) {
						municipio = new ElementoComboMunicipio();
						municipio.setIdMunicipio(rs.getString("IDMUNICIPIO"));
						municipio.setDescripcion(rs.getString("DESCRIPCION"));
					}
				} else {
					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
						 		"", 
						 		"No se ha obtenido un preparedStatement en DaoMunicipio.consultarPorPk.");
				}
			} else {
				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
					 		"", 
					 		"No se ha obtenido una conexi�n en DaoMunicipio.consultarPorPk.");				
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 "", 
				 "DaoMunicipio.consultarPorPk\n" + e.getMessage());
		} 
		
		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoMunicipio.consultarPorPk");
		return municipio;
	}
	
	
}
