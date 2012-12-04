package com.evalquiler.excepciones.informe;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;


public class ErrorObtenerDatosInformeExcepcion extends ExcepcionComun {

	public ErrorObtenerDatosInformeExcepcion() {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "", //Esta excepci�n es para el proceso que env�a la informaci�n de las encuestas. 
			  "ErrorObtenerDatosInformeExcepcion: Se ha producido un error al obtener los datos del informe. Ver excepciones anteriores.");
	}
}
