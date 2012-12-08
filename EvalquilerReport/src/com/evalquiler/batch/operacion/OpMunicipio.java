/**
 * 
 */
package com.evalquiler.batch.operacion;

import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.dao.DaoMunicipio;
import com.evalquiler.entidad.ElementoComboMunicipio;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.municipio.NoExisteMunicipioExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpMunicipio {
	
	public static final ElementoComboMunicipio consultarMunicipio(final DatosViviendaActionForm viviendaIn) 
		throws NoExisteMunicipioExcepcion, ExcepcionEjecutarSentancia {
		ElementoComboMunicipio municipio = DaoMunicipio.consultarPorPk( ((DatosViviendaActionForm)viviendaIn).getProvincia().getIdProvincia(), 
																	  ((DatosViviendaActionForm)viviendaIn).getMunicipio().getIdMunicipio());
		
		if (null == municipio) {
			throw new NoExisteMunicipioExcepcion(((DatosViviendaActionForm)viviendaIn).getProvincia().getIdProvincia(), 
			  									 ((DatosViviendaActionForm)viviendaIn).getMunicipio().getIdMunicipio());
		}
		
		return municipio; 
	}

}
