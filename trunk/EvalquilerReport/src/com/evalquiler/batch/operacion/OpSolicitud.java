/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;

import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.dao.DaoSolicitud;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.informe.NoHaySolicitudesPendientesException;

/**
 * @author cachorro
 *
 */
public final class OpSolicitud {
	
	public static final Collection<DatosSolicitudInformeActionForm> consultarSolicitudesPendientes() 
		throws ExcepcionEjecutarSentancia, NoHaySolicitudesPendientesException { 

		Collection<DatosSolicitudInformeActionForm> datosSolicitudes = null;

		datosSolicitudes = DaoSolicitud.consultar(null, DaoSolicitud.SENT_SOLICITUD_PENDIENTES);
		
		if ( (null == datosSolicitudes) || (datosSolicitudes.isEmpty()) ) {
			throw new NoHaySolicitudesPendientesException();
		}
	
		return datosSolicitudes; 
	}



	public static final int actualizarProcesado(final DatosSolicitudInformeActionForm datosSolicitud) 
			throws ExcepcionEjecutarSentancia, NoHaySolicitudesPendientesException { 

	int iResultado = DaoSolicitud.actualizar(datosSolicitud, DaoSolicitud.SENT_ACTUALIZAR_PROCESADO);
	
	if ( 0 == iResultado) {
		throw new NoHaySolicitudesPendientesException();
	}

	return iResultado; 
}
}