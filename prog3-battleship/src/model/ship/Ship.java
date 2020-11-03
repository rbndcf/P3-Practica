package model.ship;

import java.util.*;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para controlar los valores de cada barco, crearlos, posicionarlos, saber su posición
 * y su estado (No hit, hit o shot down), saber su orientación y su forma (shape), además de poder obtener cada 
 * uno de sus elementos como symbol, name, posición, etc.
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