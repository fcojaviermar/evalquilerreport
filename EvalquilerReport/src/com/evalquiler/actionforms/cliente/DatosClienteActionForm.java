package com.evalquiler.actionforms.cliente;

import com.evalquiler.actionforms.comun.DatosInicioSesionActionForm;



public class DatosClienteActionForm extends DatosInicioSesionActionForm {

	/*
     * Validamamos los datos introducidos por el usuario
     */
//    public actionerrors validate(actionmapping mapping, httpservletrequest request) {
//    	system.out.println("datosclienteactionform.validate()");
//    	actionerrors errors = null;
//    	
//    	string botonpulsado = request.getparameter(constantesbotones.boton_pulsado);
//    	if ( (!constantesbotones.cancelar.equals(botonpulsado)) &&
//             (!constantesbotones.guardar.equals(botonpulsado)) ) {
//        	super.validate(mapping, request);
//        	
//        	errors = new actionerrors();
//            if ( (null == this.getemail()) ||  ("".equals(this.getemail()))) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.email"));
//            } else if (!validaciones.emailvalido(this.getemail())) {
//            	errors.add("errorvalidacion", new actionerror("error.email.novalido"));
//            }
//
//            if ( (null == this.getemail2()) ||  ("".equals(this.getemail2()))) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.email2"));
//            } else if (!validaciones.emailvalido(this.getemail2())) {
//            	errors.add("errorvalidacion", new actionerror("error.email2.novalido"));
//            }               
//            
//            if (constantes.elemento_no_seleccionado == this.getidtipodocumento()) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.tipodocumento"));
//            } else if (!combotipodocumento.elementovalido(this.getidtipodocumento())) {
//            	errors.add("errorvalidacion", new actionerror("error.tipodocumento.novalido"));            	
//            } else {
//            	combotipodocumento combo = new combotipodocumento();
//            	elementocombotipodocumento elcombo = combo.get(this.getidtipodocumento());
//            	this.setdesctipodocumento(elcombo.getdescripcion());
//            }
//
//            if ( (null == this.getnifcif()) ||  ("".equals(this.getnifcif()))) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.nifcif"));
//            } else if (!documentovalido(this.getidtipodocumento(), this.getnifcif())) {
//           		errors.add("errorvalidacion", new actionerror("error.documento.novalido"));
//            }
//    
//            if ( (null == this.getpassword2()) ||  ("".equals(this.getpassword2()))) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.password2"));
//            } else if (!this.getpassword().equals(this.getpassword2())) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.password2"));
//            }
//            
//        	if (!this.getpassword().equals(this.getpassword2())) {
//        		errors.add("errorvalidacion", new actionerror("error.passwords.distintas"));
//        	}
//            	
//            if (!this.getemail().equals(this.getemail2())) {
//            	errors.add("errorvalidacion", new actionerror("error.emails.distintos"));
//            }
//
//            if (!errors.isempty()) {
//        		this.setpassword(null);
//        		this.setpassword2(null);
//        	}
//    	} else {
//    		
//    	}
//        
//        return errors;
//    }

//    private final static boolean documentoValido(final int tipoDocumento, final String documento) {
//    	boolean esValido = false;
//    	switch (tipoDocumento) {
//        	case 1: esValido = Validaciones.nifValido(documento);
//        			  break;
//        	case 2: esValido = Validaciones.nieValido(documento);
//        			  break;
//        	case 3: esValido = Validaciones.cifValido(documento);
//        			  break;
//        	default: break;
//    	}
//    	return esValido;
//    }
}