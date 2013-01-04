package com.evalquiler.excepciones.encuesta;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;

public class NoRecuperadasPreguntasParaSolicitudExcepcion extends ExcepcionComun {

	public NoRecuperadasPreguntasParaSolicitudExcepcion(final String idVivienda, final String idTipoInforme,
														final String fechaInicio, final String fechaFin) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "error.no.existen.preguntas", 
			  "NoRecuperadasPreguntasParaSolicitudExcepcion: No se han recuperado respuestas para la solicitud sobre la vivienda " + idVivienda + 
			  " hechas por un usuario de tipo " + idTipoInforme + " con fecha de inicio " + fechaInicio + " y fecha de fin " + fechaFin);
	}
}
