package com.evalquiler.actionforms.vivienda;

import com.evalquiler.entidad.ElementoComboMunicipio;
import com.evalquiler.entidad.ElementoComboProvincia;
import com.evalquiler.entidad.ElementoComboTipoVia;




public class DatosViviendaActionForm extends DatosBasicosViviendaActionForm  {

	
	private ElementoComboTipoVia tipoVia 	 = null;
	private ElementoComboMunicipio municipio = null;
	private ElementoComboProvincia provincia = null;
	private String nombreVia	   = null;
	private int numeroVia	  	   = 0;
	private String bloque		   = null;
	private int portal			   = 0;
	private String escalera		   = null;
	private String planta		   = null;
	private String puerta		   = null;
	private int codigoPostal	   = 0;
	private int pais			   = 0;
	private String nifPropietario  = null;

	
	public String getDatosParaInforme() {
		String datosVivienda = new String();
		
		datosVivienda = this.getTipoVia().getDescripcion().concat(": ".concat(nombreVia.concat(" Número vía: ".concat(String.valueOf(numeroVia).concat("\n")))));
		datosVivienda = datosVivienda.concat("Bloque: ".concat(bloque).concat(" Portal: ".concat(" Escalera: ".concat(" Planta: ".concat(planta)))));
		datosVivienda = datosVivienda.concat(" Puerta: ".concat(puerta).concat("\n"));
		datosVivienda = datosVivienda.concat("Código postal: ".concat(String.valueOf(codigoPostal).
															   concat( " Municipio: ".concat(this.getMunicipio().getDescripcion()))));
		datosVivienda = datosVivienda.concat(" Provincia: ".concat(this.getProvincia().getDescripcion()));
				
		return datosVivienda;
	}
	
	
	public ElementoComboMunicipio getMunicipio() {
		return municipio;
	}


	public void setMunicipio(ElementoComboMunicipio municipio) {
		this.municipio = municipio;
	}


	public ElementoComboProvincia getProvincia() {
		return provincia;
	}


	public void setProvincia(ElementoComboProvincia provincia) {
		this.provincia = provincia;
	}


	public ElementoComboTipoVia getTipoVia() {
		return tipoVia;
	}


	public void setTipoVia(ElementoComboTipoVia tipoVia) {
		this.tipoVia = tipoVia;
	}

	
	public String getNombreVia() {
		return nombreVia;
	}


	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}


	public int getNumeroVia() {
		return numeroVia;
	}


	public void setNumeroVia(int numeroVia) {
		this.numeroVia = numeroVia;
	}


	public String getBloque() {
		return bloque;
	}


	public void setBloque(String bloque) {
		this.bloque = bloque;
	}


	public int getPortal() {
		return portal;
	}


	public void setPortal(int portal) {
		this.portal = portal;
	}


	public String getEscalera() {
		return escalera;
	}


	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}


	public String getPlanta() {
		return planta;
	}


	public void setPlanta(String planta) {
		this.planta = planta;
	}


	public String getPuerta() {
		return puerta;
	}


	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}


	public int getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public int getPais() {
		return pais;
	}


	public void setPais(int pais) {
		this.pais = pais;
	}


	public String getNifPropietario() {
		return nifPropietario;
	}


	public void setNifPropietario(String nifPropietario) {
		this.nifPropietario = nifPropietario;
	}

    
}