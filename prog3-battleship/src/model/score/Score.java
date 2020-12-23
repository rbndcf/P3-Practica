package model.score;

import java.util.Objects;
import model.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * @param <T> tipo de puntuación
 * 
 * Esta clase la utilizaremos para controlar las puntuaciones que tiene cada jugador y poder comparar ese score
 * con el score de cualquier otro jugador
 */
public abstract class Score<T> implements Comparable<Score<T>> {
	/**
	 * @param player Jugador
	 */
	private IPlayer player;
	/**
	 * @param score puntuación del jugador
	 */
	protected int score;
	
	/**
	 * @param player jugador a guardar
	 * Constructor por parámetros
	 */
	public Score(IPlayer player) {
		Objects.requireNonNull(player);
		
		this.player = player;
		score = 0;
	}
	
	/**
	 * @return score puntuación
	 * Getter del Score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param other El score del otro jugador
	 * @return la diferencia
	 * Compara dos scores de jugadores y devuelve la diferencia que hay entre ellos, negativo si other es mayor, 0 si other es igual o
	 * positivo si other es menor (Con respecto al score que llama a compareTo();)
	 */
	public int compareTo(Score<T> other) {
		return this.score - other.score;
	}
	
	/**
	 * @return score and name
	 * Deuvelve el nombre del jugador, junto al tipo y el score que lleva
	 */
	public String toString() {
		return player.getName() + ": " + score;
	}
	
	public abstract void score(T sc);
}
