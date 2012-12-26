package com.evalquiler.excepciones.vivienda;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;

public final class NoExisteViviendaExcepcion extends ExcepcionComun {

	public NoExisteViviendaExcepcion(final String id) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "msg.vivienda.no.existe", 
			  "NoExisteViviendaExcepcion: No existen la vivienda con identificacador: " + id);
	}
}
