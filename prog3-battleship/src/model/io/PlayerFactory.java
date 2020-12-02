package model.io;

import java.io.*;

public class PlayerFactory {
	public IPlayer createPlayer(String name, String s) {
		if(s.contains(".") || s.contains("\\") || s.contains("/")){
			return new PlayerFile(name, new BufferedReader(new StringReader(s)));
		}
		
		else if(isLong(s)) {
			return new PlayerRandom(name, Long.parseLong(s));
		}
		
		else return null;
	}
	
	private boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
