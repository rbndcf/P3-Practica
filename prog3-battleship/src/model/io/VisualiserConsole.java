package model.io;

import java.util.Objects;
import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para mostrar el visualiser por consola
 */
public class VisualiserConsole implements IVisualiser{
	/**
	 * @param game Juego que se utiliza
	 */
	private Game game;
	
	/**
	 * @param g game recibido
	 * Constructor por parámetros
	 */
	public VisualiserConsole(Game g){
		Objects.requireNonNull(g);
		game = g;
	}
	
	/**
	 * El metodo show() muestra por pantalla la String recibe al llamar a game.toString()
	 */
	public void show() {
		System.out.println(game.toString());
	}
	
	/**
	 * close() no hace nada en VisualiserConsole
	 */
	public void close() {}
}
