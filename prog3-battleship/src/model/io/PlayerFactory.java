package model.io;

import java.io.*;
import model.exceptions.io.*;

public class PlayerFactory {
	public static IPlayer createPlayer(String name, String s) throws BattleshipIOException {
		if(s.contains(".") || s.contains("\\") || s.contains("/")){
			try {
				BufferedReader br = new BufferedReader(new StringReader(s));
				return new PlayerFile(name, br);
			}
			catch(Exception e) {
				throw new BattleshipIOException("No se ha podido leer el fichero " + s);
			}
		}
		
		else if(isLong(s)) {
			return new PlayerRandom(name, Long.parseLong(s));
		}
		
		else return null;
	}
	
	private static boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
