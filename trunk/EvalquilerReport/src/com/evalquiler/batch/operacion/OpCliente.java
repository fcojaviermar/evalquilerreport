/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.dao.DaoCliente;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.cliente.ClienteNoExisteExcepcion;
import com.evalquiler.excepciones.cliente.ClienteRepetidoExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpCliente {
	
	public static final DatosClienteActionForm consultarPorPk(DatosClienteActionForm clienteIn) 
		throws ClienteNoExisteExcepcion, ClienteRepetidoExcepcion, ExcepcionEjecutarSentancia {
		
		Collection<DatosClienteActionForm> listaClientes = DaoCliente.consultarPorPk(clienteIn.getUser());
		
		if ( (null != listaClientes) && (listaClientes.isEmpty()) ) {
			throw new ClienteNoExisteExcepcion(clienteIn.getUser());
		} 
		
		if (listaClientes.size() > 1) {
			throw new ClienteRepetidoExcepcion(clienteIn.getUser());
		}
		
		return listaClientes.iterator().next(); 
	}
	
	
}
