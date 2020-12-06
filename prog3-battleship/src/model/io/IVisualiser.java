package model.io;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Interfaz que utilizaremos para crear los diferentes Visualisers
 */
public interface IVisualiser {
	/**
	 * Muetra como está la partida en ese momento
	 */
	void show();
	/**
	 * Cierra y guarda el fichero GIF
	 */
	void close();
}
