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
import com.evalquiler.entidad.ElementoComboTipoVia;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;


/**
 * @author cachorro
 *
 */
public class DaoTipoVia {
	
	private final static String CONSULTAR_TIPOVIA_POR_PK = "SELECT IDTIPOVIA, DESCRIPCION " +
															"FROM TIPOVIA " +
															"WHERE IDTIPOVIA = ?";

	public static final ElementoComboTipoVia consultarPorPk(final String idTipoVia) throws ExcepcionEjecutarSentancia {
		ElementoComboTipoVia tipoVia = null;
		
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection conn = ConexionBD.getConnection();
		try {
			if (null != conn) {
				pstmt = conn.prepareStatement(CONSULTAR_TIPOVIA_POR_PK);
				if (null != pstmt) {
					pstmt.setLong(1, Long.valueOf(idTipoVia));
					rs = pstmt.executeQuery() ; 
					while(rs.next()) {
						tipoVia = new ElementoComboTipoVia();
						tipoVia.setIdTipoVia(rs.getString("IDTIPOVIA"));
						tipoVia.setDescripcion(rs.getString("DESCRIPCION"));
					}
				} else {
					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
						 		"error.global.mesage", 
						 		"No se ha obtenido un preparedStatement en DaoTipoVia.consultarPorPk.");
				}
			} else {
				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
					 		"error.global.mesage", 
					 		"No se ha obtenido una conexi�n en DaoTipoVia.consultarPorPk.");				
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 "error.global.mesage", 
				 "DaoTipoVia.consultarPorPk\n" + e.getMessage());
		} 
		
		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoTipoVia.consultarPorPk");
		return tipoVia;
	}
	
	
}
