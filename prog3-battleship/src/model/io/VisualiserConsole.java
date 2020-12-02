package model.io;

import java.util.Objects;

public class VisualiserConsole {
	private Game game;
	
	public VisualiserConsole(Game g){
		Objects.requireNonNull(g);
		game = g;
	}
	
	public void show() {
		System.out.println(game.toString());
	}
	
	public void close() {
		
	}
}
