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
		String[] s1 = game.getBoard1().show(false).split("\n");
		String[] s2 = game.getBoard2().show(false).split("\\s+");
		
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
						case Board.HIT_SYMBOL:		frame.printSquare(width, heigh, Color.RED);
													break;
						case Board.Board_SEPARATOR:	frame.printSquare(width, heigh, Color.ORANGE);
													break;
					}
					
					switch(s2[heigh].charAt(width)) {
						case Board.NOTSEEN_SYMBOL: 	frame.printSquare(width, heigh + h + 1, Color.LIGHT_GRAY);
													break;
						case Board.WATER_SYMBOL:	frame.printSquare(width, heigh + h + 1, Color.BLUE);
													break;
						case Board.HIT_SYMBOL:		frame.printSquare(width, heigh + h + 1, Color.RED);
													break;
						case Board.Board_SEPARATOR:	frame.printSquare(width, heigh + h + 1, Color.ORANGE);
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
	
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
	}
}