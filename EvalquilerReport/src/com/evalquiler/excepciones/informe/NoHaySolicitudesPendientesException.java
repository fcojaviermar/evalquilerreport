package com.evalquiler.excepciones.informe;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;


public class NoHaySolicitudesPendientesException extends ExcepcionComun {

	public NoHaySolicitudesPendientesException() {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "", //Esta excepci�n es para el proceso que env�a la informaci�n de las encuestas. 
			  "NoHaySolicitudesPendientesException: No hay solicitudes no procesadas.");
	}
}
