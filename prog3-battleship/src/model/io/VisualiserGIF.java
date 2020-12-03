package model.io;

import java.util.Objects;

import java.awt.Color;
import model.io.gif.*;
import java.io.*;
import model.*;
import model.exceptions.io.*;

public class VisualiserGIF implements IVisualiser{
	private Game game;
	private AnimatedGIF agif;
	private int w;
	private int h;
	
	public VisualiserGIF(Game g) {
		Objects.requireNonNull(g);
		
		game = g;
		agif = new AnimatedGIF();
	}
	
	//TODO
	public void show() {
		String s1 = game.getBoard1().show(false);
		String s2 = game.getBoard2().show(false);
		
		h = s1.length() / game.getBoard1().getSize();
		w = s1.length() % game.getBoard1().getSize();
		
		FrameGIF frame = new FrameGIF(w, h*2+1);
		
		for(int row = 0 ; row < h*2+1 ; row++) {
			for(int column = 0 ; column < w ; column++) {
				try {
					if(row < h) {
						switch(s1.charAt(row+column)){
							case Board.NOTSEEN_SYMBOL: 	frame.printSquare(column, row, Color.LIGHT_GRAY);
														break;
							case Board.WATER_SYMBOL:	frame.printSquare(column, row, Color.BLUE);
														break;
							case Board.HIT_SYMBOL:	frame.printSquare(column, row, Color.RED);
													break;
							case Board.Board_SEPARATOR:	frame.printSquare(column,  row, Color.YELLOW);
														break;
						}
					}
					
					else if(row == h)
						frame.printSquare(column, row, Color.DARK_GRAY);
					
					else
						switch(s2.charAt((row+column)-(h+1))){
							case Board.NOTSEEN_SYMBOL: 	frame.printSquare(column, row, Color.LIGHT_GRAY);
														break;
							case Board.WATER_SYMBOL:	frame.printSquare(column, row, Color.BLUE);
														break;
							case Board.HIT_SYMBOL:	frame.printSquare(column, row, Color.RED);
													break;
							case Board.Board_SEPARATOR:	frame.printSquare(column,  row, Color.YELLOW);
														break;
						}
					agif.addFrame(frame);
				}
				catch(BattleshipIOException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
	}
}
