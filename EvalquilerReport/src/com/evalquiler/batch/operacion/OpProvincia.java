/**
 * 
 */
package com.evalquiler.batch.operacion;

import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.dao.DaoProvincia;
import com.evalquiler.entidad.ElementoComboProvincia;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.provincia.NoExisteProvinciaExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpProvincia {
	
	public static final ElementoComboProvincia consultarProvincia(final DatosViviendaActionForm viviendaIn) 
		throws NoExisteProvinciaExcepcion, ExcepcionEjecutarSentancia {
		ElementoComboProvincia provincia = DaoProvincia.consultarPorPk( ((DatosViviendaActionForm)viviendaIn).getProvincia().getIdProvincia());
		
		if (null == provincia) {
			throw new NoExisteProvinciaExcepcion(((DatosViviendaActionForm)viviendaIn).getProvincia().getIdProvincia());
		}
		
		return provincia; 
	}

}
