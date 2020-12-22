package model;

import model.aircraft.*;
import model.ship.*;
import model.*;
import java.lang.reflect.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos como factoria para crear crafts de cualquier tipo, aviones o barcos
 */
public class CraftFactory {
	/**
	 * @param type Tipo del craft que vamos a crear
	 * @param orientation Orientación del craft que vamos a crear
	 * @return el craft creado
	 */
	public static Craft createCraft(String type, Orientation orientation) {
		try {
			Class<?> clazz = Class.forName("model." + type);
			Class<?>[] paramTypes = new Class[] {Orientation.class};
			Constructor<?> cons = clazz.getConstructor(paramTypes);	
			
			return (Craft)cons.newInstance(orientation);
		}
		
		catch(ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
			return null;
		}
		
		/*switch(type) {
			case "Bomber": 		return new Bomber(orientation);
			case "Fighter":		return new Fighter(orientation);
			case "Transport":	return new Transport(orientation);
			case "Battleship":	return new Battleship(orientation);
			case "Carrier":		return new Carrier(orientation);
			case "Cruiser": 	return new Cruiser(orientation);
			case "Destroyer":	return new Destroyer(orientation);
			default: 			return null;
		}*/
	}
}
