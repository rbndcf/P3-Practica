package model.io;

import model.*;

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
		if(n == "Console")
			return new VisualiserConsole(g);
		else if(n == "GIF")
			return new VisualiserGIF(g);
		else
			return null;
	}
}
