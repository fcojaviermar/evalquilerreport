package com.evalquiler.excepciones;

import com.evalquiler.excepciones.comun.ExcepcionComun;

public final class ExcepcionEjecutarSentancia extends ExcepcionComun {

	public ExcepcionEjecutarSentancia(final String codigo , final String mensaje , final String mensajeExtendido) {
		super(codigo, mensaje, "ExcepcionEjecutarSentancia: " + mensajeExtendido);
	}
}
