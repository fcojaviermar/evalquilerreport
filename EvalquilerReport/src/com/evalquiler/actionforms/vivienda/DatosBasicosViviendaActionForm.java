package com.evalquiler.actionforms.vivienda;



public class DatosBasicosViviendaActionForm {

	private long idVivienda = 0;
	
	
	public long getIdVivienda() {
		return idVivienda;
	}


	public void setIdVivienda(long idVivienda) {
		this.idVivienda = idVivienda;
	}




	/*
     * Validamamos los datos introducidos por el usuario
     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//    	System.out.println("DatosBasicosViviendaActionForm.validate()");
//    	ActionErrors errors = new ActionErrors();
//    	
//        if (this.getIdVivienda() <= Constantes.SIN_NUMERICO_EN_VIA){
//        	errors.add("errorValidacion", new ActionError("error.obligatorio.idvivienda"));
//        } 
//        
//        return errors;
//    }
    

}