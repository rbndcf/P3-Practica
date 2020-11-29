package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
public abstract class CoordinateException extends BattleshipException{
	/**
	 * @param coord Coordennada donde se guardará la coordenada que causa la excepción
	 */
	protected Coordinate coord;
	
	/**
	 * @param c Coordenada que causa la excepción
	 * Recibe la coordenada que causa la excepción y la almacena para utilizarla luego en el mensaje
	 */
	public CoordinateException(Coordinate c){
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
