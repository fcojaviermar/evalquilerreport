/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.actionforms.comun.DatosInicioSesionActionForm;
import com.evalquiler.dao.DaoCliente;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.cliente.ClienteNoExisteExcepcion;
import com.evalquiler.excepciones.cliente.ClienteRepetidoExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpCliente {
	
	public static final Collection<DatosClienteActionForm> consultarPorPk(DatosClienteActionForm ClienteIn) 
		throws ClienteNoExisteExcepcion, ClienteRepetidoExcepcion, ExcepcionEjecutarSentancia {
		
		Collection<DatosClienteActionForm> listaClientes = DaoCliente.consultarPorPk( ((DatosInicioSesionActionForm)ClienteIn).getUser());
		
		if ( (null != listaClientes) && (listaClientes.isEmpty()) ) {
			throw new ClienteNoExisteExcepcion(((DatosInicioSesionActionForm)ClienteIn).getUser());
		} 
		
		if (listaClientes.size() > 1) {
			throw new ClienteRepetidoExcepcion(((DatosInicioSesionActionForm)ClienteIn).getUser());
		}
		
		return listaClientes; 
	}
	
	
}
