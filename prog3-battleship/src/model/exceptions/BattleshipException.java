package model.exceptions;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos como Excepción general para los problemas que ocurran relacionado con las coordenadas
 */
@SuppressWarnings("serial")
public abstract class BattleshipException extends Exception{
	/**
	 * @param coord Coordennada donde se guardará la coordenada que causa la excepción
	 */
	protected Coordinate coord;
	
	/**
	 * @param c Coordenada que causa la excepción
	 * Recibe la coordenada que causa la excepción y la almacena para utilizarla luego en el mensaje
	 */
	public BattleshipException(Coordinate c){
		coord = c.copy();
	}
	
	/**
	 * @return mensaje de la excepción
	 * Devuelve el mensaje de la excepción que llama a getMessage() 
	 */
	public String getMessage() {
		return "The coordinate " + coord.toString() + " is causing an issue";
	}
}