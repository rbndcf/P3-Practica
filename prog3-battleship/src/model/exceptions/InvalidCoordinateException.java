package model.exceptions;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos como Excepción cuando se intente colocar una coordenada en el tablero en
 * una posición que no esté dentro de este.
 */
@SuppressWarnings("serial")
public class InvalidCoordinateException extends CoordinateException{
	/**
	 * @param c Coordenada que causa el problema
	 * Recibe la coordenada que causa el problema y la guarda en BattleshipException
	 */
	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}

	/**
	 * @return mensaje de la excepción
	 * Devuelve el mensaje de la excepción que llama a getMessage() 
	 */
	public String getMessage() {
		return "Problem with coordinate " + this.coord.toString() + "; it is not valid";
	}
}