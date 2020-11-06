package model.aircraft;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para crear Aircrafts
 */
public abstract class Aircraft extends Craft{
	/**
	 * @param o Orientación
	 * @param s Símbolo
	 * @param n Nombre
	 * Constructor por parámetros
	 */
	public Aircraft(Orientation o, char s, String n) {
		super(o, s, n);
	}
}
