package com.evalquiler.actionforms.informe;

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;



public class DatosSolicitudInformeActionForm {

	private long idSolicitudInforme  = 0;
	private int  idTipoInforme 		 = 0;
	private String descTipoInforme   = null;
	private String fechaAlta		 = null;
	private String fechaInicio		 = null;
	private String fechaFin		 	 = null;
	private DatosClienteActionForm  datosCliente  = null;
	private DatosViviendaActionForm datosVivienda = null;

	
	public String getDatosParaInforme() {
		String informe = "Informe solicitado por: ";
		
		informe = informe.concat(datosVivienda.getDatosViviendaParaInforme());
		
		
		return informe;
	}
	
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	
	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public DatosViviendaActionForm getDatosVivienda() {
		return datosVivienda;
	}

	public void setDatosVivienda(DatosViviendaActionForm datosVivienda) {
		this.datosVivienda = datosVivienda;
	}

	public DatosClienteActionForm getDatosCliente() {
		return datosCliente;
	}

	public void setDatosCliente(DatosClienteActionForm datosCliente) {
		this.datosCliente = datosCliente;
	}

	public long getIdSolicitudInforme() {
		return idSolicitudInforme;
	}

	public void setIdSolicitudInforme(long idSolicitudInforme) {
		this.idSolicitudInforme = idSolicitudInforme;
	}


	public int getIdTipoInforme() {
		return idTipoInforme;
	}

	public void setIdTipoInforme(int idTipoInforme) {
		this.idTipoInforme = idTipoInforme;
	}

	public String getDescTipoInforme() {
		return descTipoInforme;
	}

	public void setDescTipoInforme(String descTipoInforme) {
		this.descTipoInforme = descTipoInforme;
	}

	/*
     * Validamamos los datos introducidos por el usuario
     */
//    public actionerrors validate(actionmapping mapping, httpservletrequest request) {
//    	system.out.println("datossolicitudinformeactionform.validate()");
//    	
//    	actionerrors errors = new actionerrors();
//
//    	string botonpulsado = request.getparameter(constantesbotones.boton_pulsado);
//        if (constantesbotones.solicitar_informe.equals(botonpulsado)) {
//            if (this.getidtipoinforme() <= constantes.elemento_no_seleccionado) {
//            	errors.add("errorvalidacion", new actionerror("error.obligatorio.tipo.informe"));
//            } else if (this.getidtipoinforme() > constantes.maximo_tipo_informe) {
//            	errors.add("errorvalidacion", new actionerror("error.tipo.informe.no.valido"));
//            } else {
//            	combotipoinforme combo = new combotipoinforme();
//            	elementocombotipoinforme elcombo = combo.get(this.getidtipoinforme());
//            	this.setdesctipoinforme(elcombo.getdescripcion());
//            }
//            
//            if (!utilidadesfechas.tieneformatocorrecto(this.getfechainicio(), utilidadesfechas.formato_fecha)) {
//    			errors.add("errorvalidacion", new actionerror("error.fecha.inicio.formato.incorrecto"));
//    		}
//            
//            if (!utilidadesfechas.tieneformatocorrecto(this.getfechafin(), utilidadesfechas.formato_fecha)) {
//        		errors.add("errorvalidacion", new actionerror("error.fecha.fin.formato.incorrecto"));
//        	}
//        }
//
//    	
//        return errors;
//    }
    

}