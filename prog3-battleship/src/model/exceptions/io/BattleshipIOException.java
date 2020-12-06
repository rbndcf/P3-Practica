package model.exceptions.io;

import model.exceptions.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos como excepción general para todas las excepciones de tipo IO que ocurran
 */
@SuppressWarnings("serial")
public class BattleshipIOException extends BattleshipException{
	/**
	 * @param message mensaje de error
	 */
	private String message;
	
	/**
	 * @param m mensaje de error generado
	 * Guarda el mensaje de error generado por la excepcion en la variable message
	 */
	public BattleshipIOException(String m) {
		message = m;
	}
	
	/**
	 * @return message
	 * Devuelve el mensaje de error
	 */
	public String getMessage() {
		return message;
	}
}
