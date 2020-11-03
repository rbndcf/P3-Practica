package model.ship;

import model.*;

public class Carrier extends Ship{
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
}
