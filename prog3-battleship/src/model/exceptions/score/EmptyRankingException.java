package model.exceptions.score;

import model.exceptions.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaermos para controlar la excepción cuando el Ranking al que queremos acceder está vacio
 */
@SuppressWarnings("serial")
public class EmptyRankingException extends BattleshipException{
	/**
	 * Constructor
	 */
	public EmptyRankingException(){}
	
	/**
	 * @return mensaje de error
	 * getter del message
	 */
	public String getMessage() {
		return "El ranking está vacío";
	}
}
