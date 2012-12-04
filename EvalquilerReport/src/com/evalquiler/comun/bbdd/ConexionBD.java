/**
 * 
 */
package com.evalquiler.comun.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;

/**
 * @author cachorro
 *
 */
public class ConexionBD {
	
	private static DataSource dataSource = null;

	public static final Connection getConnection() {

		Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evalquiler","root","root");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		return conn;

	}

	public static final void cerrarConexiones(Connection conn, PreparedStatement pstmt, ResultSet rs, final String lugarProcedencia) throws ExcepcionEjecutarSentancia {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "Cerrando ResultSet de " + lugarProcedencia + "\n" + e.getMessage());
		}
		try {
			if (null != pstmt) {
				pstmt.close() ;
			}
		} catch(final SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "Cerrando PreparedStatement " + lugarProcedencia + "\n" + e.getMessage());
		}
			
		try {
			if (null != conn) {
				conn.close() ;
			}
		} catch(final SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "Cerrando Connection " + lugarProcedencia + "\n" + e.getMessage());
		}			
	}

	public static final void cerrarConexiones(Connection conn, PreparedStatement pstmt, final String lugarProcedencia) throws ExcepcionEjecutarSentancia {
		try {
			if (null != pstmt) {
				pstmt.close() ;
			}
		} catch(final SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "Cerrando PreparedStatement " + lugarProcedencia + "\n" + e.getMessage());
		}
			
		try {
			if (null != conn) {
				conn.close() ;
			}
		} catch(final SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 		"error.global.mesage", "Cerrando Connection " + lugarProcedencia + "\n" + e.getMessage());
		}			
	}
	
	
}
