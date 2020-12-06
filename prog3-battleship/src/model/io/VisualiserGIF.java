package model.io;

import java.util.Objects;

import java.awt.Color;
import model.io.gif.*;
import java.io.*;
import model.*;
import model.exceptions.io.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 * 
 * Esta clase la utilizaremos para crear GIFs con el estado de la partida que estemos utilizando, ya sea con el constructor, creando nuevos frames
 * con la funcion show() o cerrando y guardando el gif con la funcion close()
 */
public class VisualiserGIF implements IVisualiser{
	/**
	 * @param game Juego que utiliza el Visualiser
	 */
	private Game game;
	/**
	 * @param agif fichero GIF que guardará el Visualiser
	 */
	private AnimatedGIF agif;
	/**
	 * @param w ancho del fichero
	 */
	private int w;
	/**
	 * @param h altura de cada board
	 */
	private int h;
	
	/**
	 * @param g juego que recibe
	 * Constructor por parámetros
	 */
	public VisualiserGIF(Game g) {
		Objects.requireNonNull(g);
		
		game = g;
		agif = new AnimatedGIF();
	}
	
	/**
	 * Esta funcion coge el estado actual de game, y añade un nuevo frame con ese estado a agif, pintando un cuadro de diferente color segun si
	 * hay un NOTSEEN_SYMBOL (Light Gray), WATER_SYMBOL (Blue), Board_SEPARATOR (Orange) y el resto (Craft symbol y hit symbol) de color rojo
	 */
	public void show() {
		String[] s1 = game.getBoard1().show(false).split("\n");
		String[] s2 = game.getBoard2().show(false).split("\n");
		
		h = s1.length;
		w =	s1[0].length();
		
		FrameGIF frame = new FrameGIF(w, h*2+1);
		
		try {
			for(int heigh = 0 ; heigh < h ; heigh ++) {
				for(int width = 0 ; width < w ; width++) {
					switch(s1[heigh].charAt(width)) {
						case Board.NOTSEEN_SYMBOL: 	frame.printSquare(width, heigh, Color.LIGHT_GRAY);
													break;
						case Board.WATER_SYMBOL:	frame.printSquare(width, heigh, Color.BLUE);
													break;
						case Board.Board_SEPARATOR:	frame.printSquare(width, heigh, Color.ORANGE);
													break;
						default:					frame.printSquare(width, heigh, Color.RED);
													break;
					}		
					switch(s2[heigh].charAt(width)) {
						case Board.NOTSEEN_SYMBOL: 	frame.printSquare(width, heigh + h + 1, Color.LIGHT_GRAY);
													break;
						case Board.WATER_SYMBOL:	frame.printSquare(width, heigh + h + 1, Color.BLUE);
													break;
						case Board.Board_SEPARATOR:	frame.printSquare(width, heigh + h + 1, Color.ORANGE);
													break;
						default:					frame.printSquare(width, heigh + h + 1, Color.RED);
													break;
					}
				}
			}
			
			for(int width = 0 ; width < w ; width++)
				frame.printSquare(width, h, Color.DARK_GRAY);
			
			agif.addFrame(frame);
		}
		catch(BattleshipIOException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Esta funcion cierra y guarda el fichero generado con agif
	 */
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
	}
}