/**
 * 
 */
package com.evalquiler.batch.operacion;

import java.util.Collection;

import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.dao.DaoSolicitud;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.informe.NoHaySolicitudesPendientesExcepcion;

/**
 * @author cachorro
 *
 */
public final class OpSolicitud {
	
	public static final Collection<DatosSolicitudInformeActionForm> consultarSolicitudesPendientes() 
		throws ExcepcionEjecutarSentancia, NoHaySolicitudesPendientesExcepcion { 

		Collection<DatosSolicitudInformeActionForm> datosSolicitudes = null;

		datosSolicitudes = DaoSolicitud.consultar(null, DaoSolicitud.SENT_SOLICITUD_PENDIENTES);
		
		if ( (null == datosSolicitudes) || (datosSolicitudes.isEmpty()) ) {
			throw new NoHaySolicitudesPendientesExcepcion();
		}
	
		return datosSolicitudes; 
	}



	public static final int actualizarProcesado(final DatosSolicitudInformeActionForm datosSolicitud) 
			throws ExcepcionEjecutarSentancia, NoHaySolicitudesPendientesExcepcion { 

	int iResultado = DaoSolicitud.actualizar(datosSolicitud, DaoSolicitud.SENT_ACTUALIZAR_PROCESADO);
	
	if ( 0 == iResultado) {
		throw new NoHaySolicitudesPendientesExcepcion();
	}

	return iResultado; 
}
}