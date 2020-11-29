package model.exceptions.io;

import model.exceptions.*;

@SuppressWarnings("serial")
public class BattleshipIOException extends BattleshipException{
	private String message;
	
	public BattleshipIOException(String m) {
		message = m;
	}
	
	public String getMessage() {
		return message;
	}
}
