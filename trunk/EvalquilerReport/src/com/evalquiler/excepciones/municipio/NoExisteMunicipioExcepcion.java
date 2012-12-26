package com.evalquiler.excepciones.municipio;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;

public final class NoExisteMunicipioExcepcion extends ExcepcionComun {

	public NoExisteMunicipioExcepcion(final String idProvincia, final String idMunicipio) {
		super(ConstantesCodigosExcepciones.ERROR.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_MUNICIPIO.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "", 
			  "NoExisteMunicipioExcepcion: No existen el municipio con id: " + idMunicipio + " en la provincia con id: " + idProvincia);
	}
}
