/**
 * 
 */
package com.evalquiler.combo;

import java.io.Serializable;
import java.util.ArrayList;

import com.evalquiler.entidad.ElementoComboTipoInforme;

/**
 * @author cachorro
 *
 */
public final class ComboTipoInforme extends ArrayList<ElementoComboTipoInforme> implements Serializable {
	
	public ComboTipoInforme() {
		add(new ElementoComboTipoInforme(String.valueOf("0"), "--- Seleccione un elemento... ---" ));
		add(new ElementoComboTipoInforme("1", "Sobre inquilinos"));
		add(new ElementoComboTipoInforme("2", "Sobre propietarios"));
	}
	
}
