/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;


import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.dao.DaoVivienda;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.vivienda.NoExisteViviendaExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpVivienda {
	
	public static final DatosViviendaActionForm consultarVivienda(final DatosViviendaActionForm viviendaIn) 
		throws NoExisteViviendaExcepcion, ExcepcionEjecutarSentancia {
		Collection<DatosViviendaActionForm> listaViviendas = DaoVivienda.consultarPorPk( ((DatosViviendaActionForm)viviendaIn).getIdVivienda());
		
		if ( (null == listaViviendas) || (listaViviendas.isEmpty()) ) {
			throw new NoExisteViviendaExcepcion(String.valueOf(((DatosViviendaActionForm)viviendaIn).getIdVivienda()));
		}
		
		return listaViviendas.iterator().next(); 
	}

}
