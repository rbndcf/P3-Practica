package model.io;

import java.io.*;
import model.exceptions.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos como factoria para crear jugadores tanto aleatorios como por fichero
 */
public class PlayerFactory {
	/**
	 * @param name Nombre del jugador
	 * @param s Nombre del fichero, o seed si es jugador random
	 * @return tipo de player deseado
	 * @throws BattleshipIOException cuando ocurre algun problema con el fichero
	 * Recibe el nombre del jugador, y si el string es la direccion de un fichero intenta abrirlo, si no puede abrirlo lanzara una
	 * BattleshipIOException.
	 * Si el string no es un fichero intentará crear un PlayerRandom, si tampoco puede crearlo devolvera null
	 */
	public static IPlayer createPlayer(String name, String s) throws BattleshipIOException {
		if(s.contains(".") || s.contains("\\") || s.contains("/")){
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(s));
				return new PlayerFile(name, br);	
			}
			catch(FileNotFoundException e) {
				throw new BattleshipIOException("El fichero " + s + " no existe");
			}
		}
		
		else if(isLong(s)) {
			return new PlayerRandom(name, Long.parseLong(s));
		}
		
		else return null;
	}
	
	/**
	 * @param s Cadena de la que se saca el Long
	 * @return true si hay un long, false si no
	 */
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
