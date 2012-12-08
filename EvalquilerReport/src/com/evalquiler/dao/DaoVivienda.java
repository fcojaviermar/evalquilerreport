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

import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.comun.bbdd.ConexionBD;
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.entidad.ElementoComboMunicipio;
import com.evalquiler.entidad.ElementoComboProvincia;
import com.evalquiler.entidad.ElementoComboTipoVia;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;


/**
 * @author cachorro
 *
 */
public class DaoVivienda {
	
	private final static String CONSULTAR_VIVIENDA_POR_PK = "SELECT IDVIVIENDA, IDTIPOVIA, NOMBREVIA, NUMEROVIA, BLOQUE, PORTAL, ESCALERA, PLANTA, PUERTA, " +
															"CODIGOPOSTAL, MUNICIPIO, PROVINCIA, PAIS, NIFPROPIETARIO " +
															"FROM VIVIENDA " +
															"WHERE IDVIVIENDA = ?";

	public static final Collection<DatosViviendaActionForm> consultarPorPk(final long idVivienda) throws ExcepcionEjecutarSentancia {
		Collection<DatosViviendaActionForm> listaVivienda = null;
		DatosViviendaActionForm vivienda = null;
		ElementoComboTipoVia tipoVia 	 = null;
		ElementoComboMunicipio municipio = null;
		ElementoComboProvincia provincia = null;
		
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		Connection conn = ConexionBD.getConnection();
		try {
			if (null != conn) {
				pstmt = conn.prepareStatement(CONSULTAR_VIVIENDA_POR_PK);
				if (null != pstmt) {
					pstmt.setLong(1, idVivienda);
					rs = pstmt.executeQuery() ; 
					listaVivienda = new ArrayList<DatosViviendaActionForm>();
					while(rs.next()) {
						vivienda = new DatosViviendaActionForm();
						vivienda.setIdVivienda(rs.getLong("IDVIVIENDA"));
						tipoVia = new ElementoComboTipoVia();
						tipoVia.setIdTipoVia(rs.getString("IDTIPOVIA"));
						vivienda.setTipoVia(tipoVia);
						vivienda.setNombreVia(rs.getString("NOMBREVIA"));
						vivienda.setNumeroVia(rs.getInt("NUMEROVIA"));
						vivienda.setBloque(rs.getString("BLOQUE"));
						vivienda.setPortal(rs.getInt("PORTAL"));
						vivienda.setEscalera(rs.getString("ESCALERA"));
						vivienda.setPlanta(rs.getString("PLANTA"));
						vivienda.setPuerta(rs.getString("PUERTA"));
						vivienda.setCodigoPostal(rs.getInt("CODIGOPOSTAL"));
						municipio = new ElementoComboMunicipio();
						municipio.setIdMunicipio(rs.getString("MUNICIPIO"));
						vivienda.setMunicipio(municipio);
						provincia = new ElementoComboProvincia();
						provincia.setIdProvincia(rs.getString("PROVINCIA"));
						vivienda.setProvincia(provincia);
						vivienda.setPais(rs.getInt("PAIS"));
						vivienda.setNifPropietario(rs.getString("NIFPROPIETARIO"));
						listaVivienda.add(vivienda);
					}
				} else {
					throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
						 	ConstantesCodigosExcepciones.FUNCIONALIDAD_VIVIENDA.concat(
						 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
						 		"error.global.mesage", 
						 		"No se ha obtenido un preparedStatement en DaoVivienda.consultarPorPk.");
				}
			} else {
				throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
					 	ConstantesCodigosExcepciones.FUNCIONALIDAD_VIVIENDA.concat(
					 		ConstantesCodigosExcepciones.CODIGO_ERROR_NO_EJECUCION_SENTENCIA)), 
					 		"error.global.mesage", 
					 		"No se ha obtenido una conexi�n en DaoVivienda.consultarPorPk.");				
			}
		} catch (SQLException e) {
			throw new ExcepcionEjecutarSentancia(ConstantesCodigosExcepciones.ERROR.concat(
				 	ConstantesCodigosExcepciones.FUNCIONALIDAD_VIVIENDA.concat(
				 		ConstantesCodigosExcepciones.CODIGO_SQL_EXCEPTION)), 
				 "error.global.mesage", 
				 "DaoVivienda.consultarPorPk\n" + e.getMessage());
		} 
		
		ConexionBD.cerrarConexiones(conn, pstmt, rs, "DaoVivienda.consultarPorPk");
		return listaVivienda;
	}
	
	
}
