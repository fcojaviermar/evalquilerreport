/**
 * 
 */
package com.evalquiler.batch.operacion;

import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.dao.DaoTipoVia;
import com.evalquiler.entidad.ElementoComboTipoVia;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.tipovia.NoExisteTipoViaExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpTipoVia {
	
	public static final ElementoComboTipoVia consultarTipoVia(final DatosViviendaActionForm viviendaIn) 
		throws NoExisteTipoViaExcepcion, ExcepcionEjecutarSentancia {
		ElementoComboTipoVia tipoVia = DaoTipoVia.consultarPorPk( ((DatosViviendaActionForm)viviendaIn).getTipoVia().getIdTipoVia());
		
		if (null == tipoVia) {
			throw new NoExisteTipoViaExcepcion(((DatosViviendaActionForm)viviendaIn).getTipoVia().getIdTipoVia());
		}
		
		return tipoVia; 
	}

}
