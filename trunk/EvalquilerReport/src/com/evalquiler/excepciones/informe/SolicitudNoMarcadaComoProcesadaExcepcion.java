package com.evalquiler.excepciones.informe;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;


public class SolicitudNoMarcadaComoProcesadaExcepcion extends ExcepcionComun {

	public SolicitudNoMarcadaComoProcesadaExcepcion(final long idSolicitud) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_ACTUALIZADO)), 
			  "", //Esta excepci�n es para el proceso que env�a la informaci�n de las encuestas. 
			  "SolicitudesNoMarcadaComoProcesadaExcepcion: No se ha marcado la solicitud " + idSolicitud + " como procesada, pero el informe se ha enviado.");
	}
}
