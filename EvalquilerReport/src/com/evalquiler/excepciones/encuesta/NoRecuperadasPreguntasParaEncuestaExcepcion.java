package com.evalquiler.excepciones.encuesta;

import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.excepciones.comun.ExcepcionComun;

public class NoRecuperadasPreguntasParaEncuestaExcepcion extends ExcepcionComun {

	public NoRecuperadasPreguntasParaEncuestaExcepcion(final String idVivienda, final String idTipoInforme) {
		super(ConstantesCodigosExcepciones.INFORMACION.concat(
						ConstantesCodigosExcepciones.FUNCIONALIDAD_ENCUESTA.concat(
								  ConstantesCodigosExcepciones.CODIGO_REGISTRO_NO_EXISTE)), 
			  "error.no.existen.preguntas", 
			  "NoRecuperadasPreguntasParaEncuestaExcepcion: NoRecuperadasPreguntasParaEncuestaExcepcion: No se han recuperado respuestas para la vivienda " + idVivienda + 
			  " hechas por un usuario de tipo " + idTipoInforme);
	}
}
