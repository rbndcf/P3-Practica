package model.exceptions;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos como Excepción cuando se intente colocar una nave en una posición la cual esté
 * previamente ocupada por otra nave
 */
@SuppressWarnings("serial")
public class OccupiedCoordinateException extends CoordinateException{
	/**
	 * @param c Coordenada que causa el problema
	 * Recibe la coordenada que causa el problema y la guarda en CoordinateException
	 */
	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * @return mensaje de la excepción
	 * Devuelve el mensaje de la excepción que llama a getMessage() 
	 */
	public String getMessage() {
		return "Problem with coordinate " + this.coord.toString() + "; it is occupied";
	}
}