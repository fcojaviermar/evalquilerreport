package com.evalquiler.excepciones.provincia;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.comun.excepcion.ExcepcionComun;

public final class NoExisteProvinciaExcepcion extends ExcepcionComun {

	public NoExisteProvinciaExcepcion(final String id) {
		super(ConstantesCodigosExcepciones.ERROR.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_PROVINCIA.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "", 
			  "NoExisteProvinciaExcepcion: No existe la provincia con id: " + id);
	}
}
