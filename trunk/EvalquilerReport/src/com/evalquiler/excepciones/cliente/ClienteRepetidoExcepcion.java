package com.evalquiler.excepciones.cliente;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;

public class ClienteRepetidoExcepcion extends com.evalquiler.excepciones.comun.ExcepcionComun {

	public ClienteRepetidoExcepcion(final String idCliente) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_CLIENTE.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_REPETIDO)), 
			  "error.mas.de.un.cliente", 
			  "ClienteRepetidoExcepcion: Existen dos clientes con el mismo identificador: " + idCliente );
	}
}
