package com.evalquiler.excepciones.tipovia;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;

public final class NoExisteTipoViaExcepcion extends ExcepcionComun {

	public NoExisteTipoViaExcepcion(final String id) {
		super(ConstantesCodigosExcepciones.ERROR.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_TIPO_VIA.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "", 
			  "NoExisteTipoViaExcepcion: No existen el tipo de vía: " + id);
	}
}
