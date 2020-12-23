package model.score;

import model.*;
import model.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para representar un score basado en CellStatus y lleva la cuenta de las posiciones alcanzadas por el jugador
 */
public class HitScore extends Score<CellStatus> {
	/**
	 * @param player Jugador a guardar
	 * Constructor por parametros
	 */
	public HitScore(IPlayer player) {
		super(player);
	}
	
	/**
	 * @param sc Estado de la celda recibida
	 * Aumenta en 1 la variable score si el estado recibido es igual a DESTROYED o HIT
	 */
	public void score(CellStatus sc) {
		if(sc == CellStatus.DESTROYED || sc == CellStatus.HIT)
			super.score++;
	}
}
