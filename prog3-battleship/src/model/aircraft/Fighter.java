package model.aircraft;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar el Aircraft Fighter
 */
public class Fighter extends Aircraft{
	/**
	 * @param o Orientación del Fighter
	 * Constructor por parámetros
	 */
	public Fighter(Orientation o) {
		super(o, '⇄', "Fighter");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0}
		};
	}
}