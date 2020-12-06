package model.io.gif;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.exceptions.io.BattleshipIOException;

/**
 * It generates a GIF frame using GIF4J
 * @author drizo
 *
 */
public class FrameGIF {
	/**
	 * @param PIXELS_SQUARE pixels per square
	 */
	private static final int PIXELS_SQUARE = 50;
	/**
	 * @param width width
	 */
	private int width;
	/**
	 * @param heigh heigh
	 */
	private int heigh;

	/**
	 * @param ig2 Graphics of 2DGIF
	 */
	private Graphics2D ig2; // GIF4J
	/**
	 * @param bi Image buffered
	 */
	private BufferedImage bi; // GIF4J

	/**
	 * @param w width
	 * @param h heigh
	 * Parameters constructor
	 */
	public FrameGIF(int w, int h) {
		this.width = w;
		this.heigh = h;
		
		bi = new BufferedImage(w*PIXELS_SQUARE, h*PIXELS_SQUARE, BufferedImage.TYPE_3BYTE_BGR);
	    ig2 = bi.createGraphics();
	    ig2.fillRect(0, 0, w*PIXELS_SQUARE - 1, h*PIXELS_SQUARE - 1);
	}
	
	/**
	 * It prints a square in the given position
	 * @param x column
	 * @param y file
	 * @param colour colour to be used
	 * @throws BattleshipIOException if the positions is out of limits
	 */
	public void printSquare(int x, int y, Color colour) throws BattleshipIOException {
		if (x<0 || x>=width || y<0 || y>=heigh) {
			throw new BattleshipIOException("Error: wrong position to print a square: ("+x+","+y+")");
		}
		
		ig2.setPaint(colour);
		ig2.fill(new Rectangle(x*PIXELS_SQUARE, y*PIXELS_SQUARE, PIXELS_SQUARE, PIXELS_SQUARE));
	}

	/**
	 * @return bi image
	 * getter of BufferedImage
	 */
	BufferedImage getBufferedImage() {
		return bi;
	}
	
	/**
	 * @param file to save
	 * @throws BattleshipIOException if there is any I/O Exception
	 * saves the file
	 */
	public void saveFile(File file) throws BattleshipIOException {
	    try {
			ImageIO.write(bi, "GIF", file);
		} catch (IOException e) {
			throw new BattleshipIOException("Error: problem saving file");
		}
	}
	
	
}