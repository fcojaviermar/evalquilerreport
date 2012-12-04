package com.evalquiler.actionforms.vivienda;




public class DatosViviendaActionForm extends DatosBasicosViviendaActionForm  {

	private int idTipoVia		   = 0;
	private String descTipoVia		   = null;
	private String nombreVia	   = null;
	private int numeroVia	  	   = 0;
	private String bloque		   = null;
	private int portal			   = 0;
	private String escalera		   = null;
	private String planta		   = null;
	private String puerta		   = null;
	private int codigoPostal	   = 0;
	private int municipio		   = 0;
	private int provincia		   = 0;
	private int pais			   = 0;
	private String nifPropietario  = null;

	
	public String getDatosViviendaParaInforme() {
		String datosVivienda = new String();
		
		datosVivienda = descTipoVia.concat(": ".concat(nombreVia.concat(" Número vía: ".concat(String.valueOf(numeroVia).concat("\n")))));
		datosVivienda = datosVivienda.concat("Bloque: ".concat(bloque).concat(" Portal: ".concat(" Escalera: ".concat(" Planta: ".concat(planta)))));
		datosVivienda = datosVivienda.concat(" Puerta: ".concat(puerta).concat("\n"));
		datosVivienda = datosVivienda.concat("Código postal: ".concat(String.valueOf(codigoPostal).concat( "Municipio: ".concat(String.valueOf(municipio)))));
		datosVivienda = datosVivienda.concat(" Provincia: ".concat(String.valueOf(provincia).concat(" País: ".concat(String.valueOf(pais)))));
				
		return datosVivienda;
	}
	
	
	public int getIdTipoVia() {
		return idTipoVia;
	}


	public void setIdTipoVia(int idTipoVia) {
		this.idTipoVia = idTipoVia;
	}

	
	public String getDescTipoVia() {
		return descTipoVia;
	}


	public void setDescTipoVia(String descTipoVia) {
		this.descTipoVia = descTipoVia;
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


	public int getMunicipio() {
		return municipio;
	}


	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}


	public int getProvincia() {
		return provincia;
	}


	public void setProvincia(int provincia) {
		this.provincia = provincia;
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