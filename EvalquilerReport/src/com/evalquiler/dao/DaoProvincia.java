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
import com.evalquiler.entidad.ElementoComboProvincia;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;


/**
 * @author cachorro
 *
 */
public class DaoProvincia {
	
	private final static String CONSULTAR_PROVINCIA_POR_PK = "SELECT IDPROVINCIA, DESCRIPCION " +
															 "FROM PROVINCIA " +
															 "WHERE IDPROVINCIA = ?";

	public static final ElementoComboProvincia consultarPorPk(final String idProvincia) throws ExcepcionEjecutarSentancia {
		ElementoComboProvincia provincia = null;
		
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection conn = ConexionBD.getConnection();
		try {
			if (null != conn) {
				pstmt = conn.prepareStatement(CONSULTAR_PROVINCIA_POR_PK);
				if (null != pstmt) {
					pstmt.setString(1, idProvincia);
					rs = pstmt.executeQuery() ; 
					while(rs.next()) {
						provincia = new ElementoComboProvincia();
						provincia.setIdProvincia(rs.getString("IDPROVINCIA"));
						provincia.setDescripcion(rs.getString("DESCRIPCION"));
					}
				} else {
					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_PROVINCIA.concat(
						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
						 		"", 
						 		"No se ha obtenido un preparedStatement en DaoProvincia.consultarPorPk.");
				}
			} else {
				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_PROVINCIA.concat(
					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
					 		"", 
					 		"No se ha obtenido una conexi�n en DaoProvincia.consultarPorPk.");				
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_PROVINCIA.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 "", 
				 "DaoProvincia.consultarPorPk\n" + e.getMessage());
		} 
		
		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoProvincia.consultarPorPk");
		return provincia;
	}
	
	
}
