package com.evalquiler.actionforms.informe;

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.actionforms.vivienda.DatosViviendaActionForm;
import com.evalquiler.combo.ComboTipoInforme;
import com.evalquiler.entidad.ElementoComboTipoInforme;



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
		ComboTipoInforme comboTipoInforme = new ComboTipoInforme();
		ElementoComboTipoInforme tipoInforme = comboTipoInforme.get(Integer.valueOf(this.getIdTipoInforme()));
		
		String solicitud = "<strong>Solicitud número: </strong>".concat(String.valueOf(idSolicitudInforme).
																 concat("  ".concat(tipoInforme.getDescripcion().concat("<br>"))));
		return solicitud;
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
}