package com.evalquiler.excepciones.cliente;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;

public class ClienteNoExisteExcepcion extends com.evalquiler.excepciones.comun.ExcepcionComun {

	public ClienteNoExisteExcepcion(final String idCliente) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "error.no.existe.cliente", 
			  "ClienteNoExisteExcepcion: El cliente con identificador " + idCliente + " no existe");
	}
}
