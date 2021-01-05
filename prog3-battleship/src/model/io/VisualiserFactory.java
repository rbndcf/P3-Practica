package model.io;

import model.*;
import java.lang.reflect.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos como factoria para crear Visualisers tanto de Consola como de GIF
 */
public class VisualiserFactory {
	/**
	 * @param n tipo de Visualiser a crear
	 * @param g game que se va a utilizar
	 * @return tipo de visualiser especificado
	 * Recibe una cadena donde esta el tipo de visualiser que se quiere crear, en caso de ser Console o GIF se devuelve uno de esos dos, en caso de que
	 * no sea ninguno de esos dos tipos de visualiser devuelve null.
	 */
	public static IVisualiser createVisualiser(String n, Game g) {
		try {
			Class<?> clazz = Class.forName("model.io.Visualiser" + n);
			Class<?>[] paramTypes = new Class[] {Game.class};
			Constructor<?> cons = clazz.getConstructor(paramTypes);	
			
			return (IVisualiser)cons.newInstance(g);
		}
		catch(ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			return null;
		}
	}
}