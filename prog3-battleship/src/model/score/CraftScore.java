package model.score;

import model.*;
import model.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para representar un score basado en Craft y aumenta el score con el valor del Craft
 */
public class CraftScore extends Score<Craft>{
	/**
	 * @param player Jugador a guardar
	 * Constructor por parámetros
	 */
	public CraftScore(IPlayer player) {
		super(player);
	}
	
	/**
	 * @param sc Craft del que se saca el valor
	 * Aumenta el valor del parámetro score con el valor del Craft recibido
	 */
	public void score(Craft sc) {
		score = score + sc.getValue();
	}
}
