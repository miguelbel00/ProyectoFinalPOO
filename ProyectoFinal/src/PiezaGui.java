import java.awt.Image;

public class PiezaGui {
	
  // DECLARAR ATRIBUTOS: LA IMAGEN, LA PIEZA Y LAS COORDENADAS
	private Image img;
	private int x;
	private int y;
	private Pieza piece;

  // METODO CONSTRUCTOR
	public PiezaGui(Image img, Pieza piece) {
		this.img = img;
		this.piece = piece;

		this.resetarPosicionDePiezaReferente();
    
	}
  
  // METODOS GETTERS Y SETTERS
	public Image getImagen() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return img.getHeight(null);
	}

	public int getAlto() {
		return img.getHeight(null);
	}

	public int getColor() {
		return this.piece.getColor();
	}
	
	@Override
	public String toString() {
		return this.piece+" "+x+"/"+y;
	}

	// MOVER LA PIEZA A LAS COORDENADAS QUE CORRESPONDAN CON LA COLUMNA Y FILA, ES DECIR, PASAR PRIMERO LA COLUMNA Y FILA A COORDENADAS, PARA LUEGO ESTAS ASIGNARLAS A LA PIEZA
	public void resetarPosicionDePiezaReferente() {
		this.x = AjedrezGui.convertirColumnaA_X(piece.getColumna());
		this.y = AjedrezGui.convertirFilaA_Y(piece.getFila());
	}

  // OBTENER PIEZA
	public Pieza getPieza() {
		return piece;
	}

  // METODO PARA SABER EL ESTADO DE LA PIEZA (CAPTURADA O NO)
	public boolean estaCapturada() {
		return this.piece.estaCapturada();
	}

}
