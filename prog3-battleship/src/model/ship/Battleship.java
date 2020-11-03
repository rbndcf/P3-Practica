package model.ship;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar el Ship Battleship
 */
public class Battleship extends Ship{
	/**
	 * @param o Orientación del Battleship
	 * Constructor por parámetros
	 */
	public Battleship(Orientation o) {
		super(o, 'O', "Battleship");
		
		shape = new int[][] {
	      	  { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
			   	0, 0, 1, 0, 0,
			   	0, 0, 1, 0, 0},
		   	  { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0},
		   	  { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		   	  { 0, 0, 0, 0, 0,
			   	0, 0, 0, 0, 0,	
			   	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0}
		};
	}
}