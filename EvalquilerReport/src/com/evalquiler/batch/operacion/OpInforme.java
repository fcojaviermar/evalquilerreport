/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;

import com.evalquiler.actionforms.encuesta.DatosEncuestaActionForm;
import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.dao.DaoInforme;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.encuesta.NoRecuperadaEncuestaExcepcion;
import com.evalquiler.excepciones.encuesta.NoRecuperadasPreguntasParaEncuestaExcepcion;
import com.evalquiler.excepciones.informe.ErrorObtenerDatosInformeExcepcion;
import com.evalquiler.excepciones.informe.SolicitudesConUnaFechaException;

/**
 * @author cachorro
 *
 */
public final class OpInforme {
	
	public static final DatosEncuestaActionForm consultarDatosInforme(DatosSolicitudInformeActionForm objetoIn) 
		throws ExcepcionEjecutarSentancia, ErrorObtenerDatosInformeExcepcion, SolicitudesConUnaFechaException { 

		Collection<DatosEncuestaActionForm> listaEncuesta = null;
		try {
			listaEncuesta = DaoInforme.consultar(objetoIn, DaoInforme.SENT_CONSULTAR_RESPUESTAS_ENC_VIV);
		} catch (NoRecuperadaEncuestaExcepcion e) {
			throw new ErrorObtenerDatosInformeExcepcion();
		} catch (NoRecuperadasPreguntasParaEncuestaExcepcion e) {
			throw new ErrorObtenerDatosInformeExcepcion();
		}
		
		if ( (null == listaEncuesta) || (listaEncuesta.isEmpty()) ) {
			throw new ErrorObtenerDatosInformeExcepcion();
		}
	
		return listaEncuesta.iterator().next(); 
	}
}
