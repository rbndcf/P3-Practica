package model.aircraft;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar el Aircraft Transport
 */
public class Transport extends Aircraft{
	/**
	 * @param o Orientacion del Transport
	 * Constructor por parámetros
	 */
	public Transport(Orientation o) {
		super(o, '⇋', "Transport");
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	1, 0, 1, 0, 1,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 1, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 1, 0}
		};
	}
	
	/**
	 * @return valor del Craft
	 * getter de Value
	 */
	public int getValue() {
		return 18;
	}
}