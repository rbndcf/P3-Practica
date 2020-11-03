package model.aircraft;

import model.*;

public class Transport extends Aircraft{
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
		    	0, 0, 0, 1, 0}};
	}
}
