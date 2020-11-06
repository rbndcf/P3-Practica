package model.ship;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para crear Ships
 */
public abstract class Ship extends Craft {
	/**
	 * @param o Orientación
	 * @param s Símbolo
	 * @param n Nombre
	 * Constructor por parámetros
	 */
	public Ship(Orientation o, char s, String n) {
		super(o, s, n);
	}
}