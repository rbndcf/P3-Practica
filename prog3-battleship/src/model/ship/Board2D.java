package model.ship;

import model.*;

/**
 * @author Rubén Del Castillo Fuentes 48786827D
 *
 * Esta clase la utilizaremos para controlar Boards de 2 dimensiones, además de su checkcoordinate y su show de 2 dimensiones
 */
public class Board2D extends Board{
	/**
	 * @param size del board
	 * Constructor por parametros
	 */
	public Board2D(int size) {
		super(size);
	}
	
	/**
	 * @param c Coordenada a checkear
	 * @throws IllegalArgumentException cuando la coordenada c no es una coordenada 2D
	 * @return true si esta dentro, false si no
	 * Recibe una coordenada y devuelve true o false según esté dentro del board o no
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) {
		if(!(c instanceof Coordinate2D))
			throw new IllegalArgumentException();
		
		if(c.get(0) < 0 || c.get(0) >= this.getSize() || c.get(1) < 0 || c.get(1) >= this.getSize()) return false;
		else return true;
	}
	
	/**
	 * @param unveil
	 * @return estado de la coordenada
	 * Recibe si se quieren mostrar todas las casillas o no, en caso de que si se quieran ver se mostrarán todas las coordenadas del board, en caso
	 * de que no se quieran ver todas solo se mostraran las casillas que hayan sido vistas anteriormente
	 */
	@Override
	public String show(boolean unveil) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < this.getSize() ; i++) {
			for(int j = 0 ; j < this.getSize() ; j++) {
				Coordinate c = CoordinateFactory.createCoordinate(j, i);
				
				if(unveil) {
					if(this.getCraft(c) != null) {
						if(this.getCraft(c).isHit(c))
							sb.append(HIT_SYMBOL);
							
						else
							sb.append(this.getCraft(c).getSymbol());
					}
					
					else
						sb.append(WATER_SYMBOL);
				}
				
				else {
					if(this.isSeen(c)) {
						if(this.getCraft(c) != null && this.getCraft(c).isShotDown())
							sb.append(this.getCraft(c).getSymbol());
						
						else if(this.getCraft(c) != null)
							sb.append(HIT_SYMBOL);
						
						else
							sb.append(WATER_SYMBOL);
					}
					
					else
						sb.append(NOTSEEN_SYMBOL);
				}
			}
			
			if(i < (this.getSize() - 1))
				sb.append("\n");
		}
		
		return sb.toString();
	}
}