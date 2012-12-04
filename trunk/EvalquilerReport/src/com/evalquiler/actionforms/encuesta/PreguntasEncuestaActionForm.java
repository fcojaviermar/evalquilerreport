package com.evalquiler.actionforms.encuesta;

import java.util.Collection;
import java.util.Iterator;


public class PreguntasEncuestaActionForm {

	private int idPregunta = 0;
	private String descripcion = null;
	private Collection<RespuestasPreguntaActionForm> respuestas = null;
	private int idRespuestaDada = 0;
	//private String tipoRespuesta = null;	

	public String getDatosParaInforme() {
		Iterator<RespuestasPreguntaActionForm> respuestasPregunta = null;
		RespuestasPreguntaActionForm respuesta = null;
		String datosEncuesta = "";
		
		if (!respuestas.isEmpty()) {
			respuestasPregunta = respuestas.iterator();
    		while (respuestasPregunta.hasNext()) {
    			respuesta = respuestasPregunta.next();
    			datosEncuesta = datosEncuesta.concat(respuesta.getDatosParaInforme().concat("<br>"));
    		}
		}
		
		return datosEncuesta;
	}	
	
	public int getIdRespuestaDada() {
		return idRespuestaDada;
	}

	public void setIdRespuestaDada(int idRespuestaDada) {
		this.idRespuestaDada = idRespuestaDada;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Collection<RespuestasPreguntaActionForm> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Collection<RespuestasPreguntaActionForm> respuestas) {
		this.respuestas = respuestas;
	}

//	/*
//     * Validamamos los datos introducidos por el usuario
//     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//    	System.out.println("RespuestasEncuestaActionForm.validate()");
//    	ActionErrors errors = null;
//        
//        return errors;
//    }

}