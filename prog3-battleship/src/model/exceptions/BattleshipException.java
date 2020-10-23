package model.exceptions;

import model.*;

public abstract class BattleshipException extends Exception{
	public BattleshipException(Coordinate c){
		
	}
	
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}