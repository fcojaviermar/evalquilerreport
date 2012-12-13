package com.evalquiler.excepciones.informe;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;


public class SolicitudesConUnaFechaException extends ExcepcionComun {

	public SolicitudesConUnaFechaException(final long idSolicitud) {
		super(ConstantesCodigosExcepciones.ERROR.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
								  ConstantesCodigosExcepciones.CODIGO_DATOS_INCORRECTOS)), 
			  "", 
			  "SolicitudesConUnaFechaException: La solicitud con id " + idSolicitud + " tiene informada una fecha y no es correcto.");
	}
}
