package model.aircraft;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar el Aircraft Bomber
 */
public class Bomber extends Aircraft{
	/**
	 * @param o Orientación del Aircraft
	 * Constructor por parámetros
	 */
	public Bomber(Orientation o) {
		super(o, '⇶', "Bomber");
		
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 1, 1, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	1, 0, 1, 0, 1,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 1, 1, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 1, 0}
		};
	}
}