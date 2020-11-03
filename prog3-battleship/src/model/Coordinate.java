package model;

import java.util.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para poder controlar las coordenadas de la practica que llevaremos a cabo en la asignatura,
 * que es un Battleship o Hundir la flota en español, se pueden crear nuevas coordenadas, modificar uno de sus parametros
 * ('y' o 'x') comparar si dos coordenadas son las mimsmas, sumar y restar coordenadas y obtener el hashCode.
 */
public abstract class Coordinate {
	/**
	 * @param components variable en la que guardaremos las coordenadas
	 */
	private int[] components;

	/**
	 * @param dim Dimensión de la Coordenada
	 * @param coords Coordenadas
	 * Constructor por parámetros
	 */
	protected Coordinate(int dim){
		components = new int[dim];
	}

	/**
	 * @param c Coordinate que se va a copiar
	 * Constructor de copia
	 */
	protected Coordinate(Coordinate c){
		for(int i = 0 ; i < components.length ; i++)
			components[i] = c.components[i];
	}

	/**
	 * @param component posición del valor a cambiar
	 * @param value valor a cambiar
	 * Establecemos una de las coordenadas, la que indica el parámetro component, al valor recibido mediante
	 * el parámetro value
	 */
	protected void set(int component,int value){
		if (component>=0 && component<components.length)
      		components[component] = value;

   		else
      		throw new IllegalArgumentException();
	}

	/**
	 * @param component posicion del valor que queremos obtener
	 * @return el valor de la coordenada indicada (component)
	 * Recibimos la posicion en la que se encuentra el valor que queremos obtener, y en caso de estar en
	 * rango, nos lo devuelve con el return
	 */
	public int get(int component){
		if(component >= 0 & component < components.length)
			return(components[component]);

		else
			throw new IllegalArgumentException();
	}

	/**
	 * @param c objeto Coordinate a sumar
	 * @return la suma del objeto Coordinate que llama a la función más el que recibe
	 * Recibe un Coordinate, y lo suma al objeto Coordinate que llama a la función add();
	 */
	public Coordinate add(Coordinate c){
		Objects.requireNonNull(c);
		
		Coordinate new_c = this.copy();

		for(int i = 0 ; i < new_c.components.length && i < c.components.length ; i++)
			new_c.set(i, new_c.get(i) + c.get(i));
		
		return new_c;
	}

	/**
	 * @param c objeto Coordinate a restar
	 * @return la resta del objeto Coordinate que llama a la función add() menos el que recibe
	 * Recibe un Coordinate, y se lo resta al objeto Coordinate que llama a la función subtract();
	 */
	public Coordinate subtract(Coordinate c){
		Objects.requireNonNull(c);
		
		Coordinate new_c = this.copy();
        
   		for(int i = 0 ; i < new_c.components.length && i < c.components.length ; i++)
      		new_c.set(i, new_c.get(i) - c.get(i));
                
   		return new_c;
	}
	
	/**
	 * @return copia del Coordinate
	 * Devuelve la copia del coordinate que ha llamado a la función copy()
	 */
	public abstract Coordinate copy();

	/**
	 * @return Set de Coordinate
	 * Devuelve todas las Coordinate adyacentes de la Coordinate que llama a la función adjacentCoordinates()
	 */
	public abstract Set<Coordinate> adjacentCoordinates();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}
}