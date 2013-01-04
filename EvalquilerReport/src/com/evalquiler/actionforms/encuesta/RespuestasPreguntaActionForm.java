package com.evalquiler.actionforms.encuesta;

import com.evalquiler.comun.utilidades.UtilidadesCanvas;




public class RespuestasPreguntaActionForm {

	private int 	idRespuesta 		= 0;
	private String 	descripcion 		= null;
	private int 	contadorRespuestas 	= 0;
	
	public String getDatosParaInforme() {
		String datosRespuesta = "";

		if (0 != contadorRespuestas) {
			datosRespuesta = datosRespuesta.concat("<strong>Respuesta: </strong>".concat("<i>".concat(descripcion.
											concat("</i>".concat(" ha sido respondida por ".
											concat(String.valueOf(contadorRespuestas).concat(" personas.")))))));
		}
		return datosRespuesta;
	}	

	
	public String getDatosParaInforme(final int indicePregunta, final int indiceRespuesta,  
		  							  final int totalRespuestas) {
        String datosRespuesta = "";
        
        String canva = UtilidadesCanvas.construirCanva(indicePregunta, indiceRespuesta, totalRespuestas, contadorRespuestas);
//        if (0 != contadorRespuestas) {
        	datosRespuesta = datosRespuesta.concat("<strong>Respuesta: </strong>".concat("<i>".concat(descripcion.
        									concat("</i>".concat(" ha sido respondida por ".
        									concat(String.valueOf(contadorRespuestas).concat(" personas.".concat(canva))))))));
//        }
     
        return datosRespuesta;
     }		
	
	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getContadorRespuestas() {
		return contadorRespuestas;
	}

	public void setContadorRespuestas(int contadorRespuestas) {
		this.contadorRespuestas = contadorRespuestas;
	}	

	
//	/*
//     * Validamamos los datos introducidos por el usuario
//     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//    	System.out.println("RespuestasPreguntaActionForm.validate()");
//    	ActionErrors errors = null;
//        
//        return errors;
//    }
	

}