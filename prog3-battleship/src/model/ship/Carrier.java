package model.ship;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar el Ship Carrier
 */
public class Carrier extends Ship{
	/**
	 * @param o Orientacion del Carrier
	 * Constructor por parámetros
	 */
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
		
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
			    0, 0, 1, 0, 0,	
			    0, 0, 1, 0, 0,	
			    0, 0, 1, 0, 0,
			   	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}
		};
	}
	
	/**
	 * @return valor del Craft
	 * getter de Value
	 */
	public int getValue() {
		return 8;
	}
}