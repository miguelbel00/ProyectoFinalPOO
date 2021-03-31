

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;



public class ArrastrarYSoltarPieza implements MouseListener, MouseMotionListener {

  // DECLARAR ATRIBUTOS
	private List<PiezaGui> piezasGui;
	private AjedrezGui ajedrezGui;
	
	private PiezaGui piezaArrastrante;
	private int dragOffsetX;
	private int dragOffsetY;
	
  // METODO CONSTRUCTOR
	public ArrastrarYSoltarPieza(List<PiezaGui> piezasGui, AjedrezGui ajedrezGui) {
		this.piezasGui = piezasGui;
		this.ajedrezGui = ajedrezGui;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

  // METODO PARA IDENTIFICAR LAS COORDENADAS DEL MOUSE AL DAR UN CLICK
	@Override
	public void mousePressed(MouseEvent evt) {
		int x = evt.getPoint().x;
		int y = evt.getPoint().y;
		
		// ENCONTRAR LA PIEZA QUE SE DESEA MOVER ANALIZANDO CON UN FOR NORMAL DE ATRAS HACIA ADELANTE
		for (int i = this.piezasGui.size()-1; i >= 0; i--) {
			PiezaGui piezaGui = this.piezasGui.get(i);
      // REVISAR SI LA PIEZA ESTA CAPTURADA
			if (piezaGui.estaCapturada()) continue;
    
			if(mouseOverPiece(piezaGui,x,y)){
				
				if( (	this.ajedrezGui.obtenerEstadoDelJuego() == AjedrezJuego.estadoBlanco
						&& piezaGui.getColor() == Pieza.colorBlanco
					) ||
					(	this.ajedrezGui.obtenerEstadoDelJuego() == AjedrezJuego.estadoNegro
							&& piezaGui.getColor() == Pieza.colorNegro
						)
					){
					// calculate offset, because we do not want the drag piece
					// to jump with it's upper left corner to the current mouse
					// position
					//
					this.dragOffsetX = x - piezaGui.getX();
					this.dragOffsetY = y - piezaGui.getY();
					this.piezaArrastrante = piezaGui;
					break;
				}
			}
		}
		
		// move drag piece to the top of the list
		if(this.piezaArrastrante != null){
			this.piezasGui.remove( this.piezaArrastrante );
			this.piezasGui.add(this.piezaArrastrante);
		}
	}

	/**
	 * check whether the mouse is currently over this piece
	 * @param piece the playing piece
	 * @param x x coordinate of mouse
	 * @param y y coordinate of mouse
	 * @return true if mouse is over the piece
	 */
	private boolean mouseOverPiece(PiezaGui piezaGui, int x, int y) {

		return piezaGui.getX() <= x 
			&& piezaGui.getX()+piezaGui.getAncho() >= x
			&& piezaGui.getY() <= y
			&& piezaGui.getY()+piezaGui.getAlto() >= y;
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		if( this.piezaArrastrante != null){
			int x = evt.getPoint().x - this.dragOffsetX;
			int y = evt.getPoint().y - this.dragOffsetY;
			
			// set game piece to the new location if possible
			//
			ajedrezGui.establecerUbicacionNuevaAUnaPieza(this.piezaArrastrante, x, y);
			this.ajedrezGui.repaint();
			this.piezaArrastrante = null;
		}
	}

	@Override
	public void mouseDragged(MouseEvent evt) {
		if(this.piezaArrastrante != null){
			
			int x = evt.getPoint().x - this.dragOffsetX;
			int y = evt.getPoint().y - this.dragOffsetY;
			
			System.out.println(
					"fila:"+AjedrezGui.convertirY_A_Fila(y)
					+" columna:"+AjedrezGui.convertirX_A_Columna(x));
			
			this.piezaArrastrante.setX(x);
			this.piezaArrastrante.setY(y);
			
			this.ajedrezGui.repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
