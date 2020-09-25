package model;

import java.util.Arrays;

public class Coordinate {

	private int[] components;

	public Coordinate(int x, int y){
		components = new int[2];

		components[0] = x;
		components[1] = y;
	}

	public Coordinate(Coordinate c){
		components = new int[2];

		for(int i = 0 ; i < components.length ; i++)
			components[i] = c.components[i];
	}

	protected void set(int component,int value){
		if (component>=0 && component<components.length)
      		components[component] = value;

   		else
      		System.err.println("Error in Coordinate.set, component " + component + " is out of range");
	}

	public int get(int component){
		if(component >= 0 & component < components.length)
			return(components[component]);

		else
			System.err.println("Error in Coordinate.get, component " + component + " is out of range");

		return -1;
	}

	public Coordinate add(Coordinate c){
		Coordinate new_c = new Coordinate(this);

		for(int i = 0 ; i < new_c.components.length ; i++)
			new_c.set(i, new_c.get(i) + c.get(i));

		return new_c;
	}

	public Coordinate subtract(Coordinate c){
		Coordinate new_c = new Coordinate(this); 
        
   		for(int i = 0 ; i < new_c.components.length ; i++)
      		new_c.set(i, new_c.get(i) - c.get(i));
                
   		return new_c;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append("(");

		for(int i = 0 ; i < components.length ; i++){
			sb.append(components[i]);

			if(i < (components.length - 1))
				sb.append(",");
		}

		sb.append(")");

		return sb.toString();
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	public boolean equals(Coordinate c){
		for(int i = 0 ; i < c.components.length ; i++)
			if(components[i] == c.components[i])
				return false;

		return true;
	}
}
