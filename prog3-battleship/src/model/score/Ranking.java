package model.score;

import java.util.*;
import model.exceptions.score.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * @param <ScoreType> tipo de score que se utilizará en el Ranking
 * 
 * Esta clase la utilizaremos para tener un sistema de clasificacion basado en puntuaciones obtenidas por los diferentes jugadores.
 */
public class Ranking<ScoreType extends Score<?>> {
	/**
	 * @param scoreSet tipo de score
	 */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Constructor
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	
	/**
	 * @param st score a añadir
	 * Añade el score recibido al scoreSet
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	/**
	 * @return scoreSet guardado
	 * Getter de scoreSet
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	/**
	 * @return el primer valor de scoreSet
	 * @throws EmptyRankingException cuando scoreSet está vacío
	 * 
	 * Lanza una excepcion si el scoreSet está vacio, en caso de que no esté vacio devuelve el primer valor de scoreSet
	 */
	public ScoreType getWinner() throws EmptyRankingException {
		if(scoreSet.isEmpty())
			throw new EmptyRankingException();
		else
			return scoreSet.first();
	}
}
