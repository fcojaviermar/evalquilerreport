package com.evalquiler.actionforms.encuesta;

import java.util.Collection;
import java.util.Iterator;

import com.evalquiler.comun.utilidades.UtilidadesCanvas;


public class DatosEncuestaActionForm {

	private int    idEncuesta 	 				 = 0;
	private String titulo	 					 = null;
	private int    idTipoUsuario 				 = 0;  // O va aquí o va en la clase RespuestasEncuestaActionForm

	private Collection<PreguntasEncuestaActionForm> preguntas = null;
	
	public String getDatosParaInforme() {
		Iterator<PreguntasEncuestaActionForm> preguntasEncuesta = null;
		String datosEncuesta = "<strong>Título de la encuesta: </strong>".concat(titulo.concat(".<br><br>"));
		PreguntasEncuestaActionForm  pregunta  = null;
		RespuestasPreguntaActionForm respuesta = null;
		
		
		int indicePregunta = 1;
		if (!preguntas.isEmpty()) {
			preguntasEncuesta = preguntas.iterator();
    		while (preguntasEncuesta.hasNext()) {
    			pregunta = preguntasEncuesta.next();
    			datosEncuesta = datosEncuesta.concat("<strong>Pregunta: </strong>".concat(pregunta.getDescripcion().concat("<br>".
    															  concat(pregunta.getDatosParaInforme().concat("<br>")))));
    			Iterator<RespuestasPreguntaActionForm> respuestas = pregunta.getRespuestas().iterator();
    			int i=0;
    			int[] numeroRespuestasDadas = {0, 0, 0, 0 ,0};    			
    			while (respuestas.hasNext()) {
    				respuesta = respuestas.next();
    				numeroRespuestasDadas[i] = respuesta.getContadorRespuestas();
    				i = i + 1;
    			}
    			datosEncuesta = datosEncuesta.concat(UtilidadesCanvas.construirCanva(numeroRespuestasDadas, indicePregunta));
    			indicePregunta = indicePregunta + 1;
    		}
    		
		}
				
		return datosEncuesta;
	}
	
	
	
//	public String getDatosParaInforme() {
//		Iterator<PreguntasEncuestaActionForm> preguntasEncuesta = null;
//		String datosEncuesta = "<strong>Título de la encuesta: </strong>".concat(titulo.concat(".<br><br>"));
//		
//		Iterator<RespuestasPreguntaActionForm> respuestasPregunta = null;
//		PreguntasEncuestaActionForm  pregunta  = null;
//		RespuestasPreguntaActionForm respuesta = null;
//		
//		int indicePregunta = 1;
//		if (!preguntas.isEmpty()) {
//			preguntasEncuesta = preguntas.iterator();
//    		while (preguntasEncuesta.hasNext()) {
//    			pregunta = preguntasEncuesta.next();
//    			
//    			respuestasPregunta = pregunta.getRespuestas().iterator();
//    			int acumulador = 0;
//    			while (respuestasPregunta.hasNext()) {
//    				respuesta = respuestasPregunta.next();
//    				acumulador = acumulador + respuesta.getContadorRespuestas();
//    				
//    			}
//    			
//    			datosEncuesta = datosEncuesta.concat("<strong>Pregunta: </strong>".concat(pregunta.getDescripcion().concat("<br>".
//    															  concat(pregunta.getDatosParaInforme(indicePregunta, acumulador).concat("<br>")))));
//    			
//    			indicePregunta = indicePregunta + 1;
//    		}
//    		
//		}
//				
//		return datosEncuesta;
//	}
	
	
	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getIdEncuesta() {
		return idEncuesta;
	}


	public void setIdEncuesta(int idEncuesta) {
		this.idEncuesta = idEncuesta;
	}


	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}


	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}


	public Collection<PreguntasEncuestaActionForm> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(Collection<PreguntasEncuestaActionForm> preguntas) {
		this.preguntas = preguntas;
	}


	/*
     * Validamamos los datos introducidos por el usuario
     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//    	System.out.println("DatosEncuestaActionForm.validate()");
//    	ActionErrors errors = null;
//        
//        return errors;
//    }
    

}