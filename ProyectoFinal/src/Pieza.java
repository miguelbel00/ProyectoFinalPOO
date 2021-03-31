import java.awt.Image;

public class Pieza {
 


  /* DECLARAR ATRIBUTOS DE LAS PIEZAS
    ATRIBUTOS POSBILES DE LAS PIEZAS*/
	  public static final int colorBlanco = 0;
		public static final int colorNegro = 1;

		public static final int tipoTorre = 1;
		public static final int tipoCaballo = 2;
		public static final int tipoAlfil = 3;
		public static final int tipoReina = 4;
		public static final int tipoRey = 5;
	  public static final int tipoPeon = 6;

  // ATRIBUTOS OBLIGATORIOS DE LAS PIEZAS
		private Image img;
		private int x;
		private int y;
	  private int color;
	  private int tipo;

   // ATRIBUTOS PARA LA NOMENCLATURA
	  private int fila;

    public static final int fila_1 = 0;
    public static final int fila_2 = 1;
    public static final int fila_3 = 2;
    public static final int fila_4 = 3;
    public static final int fila_5 = 4;
    public static final int fila_6 = 5;
    public static final int fila_7 = 6;
    public static final int fila_8 = 7;

	  private int columna;

    public static final int columna_A = 0;
    public static final int columna_B = 1;
    public static final int columna_C = 2;
    public static final int columna_D = 3;
    public static final int columna_E = 4;
    public static final int columna_F = 5;
    public static final int columna_G = 6;
    public static final int columna_H = 7;

  // ATRIBUTO QUE DEFINE SI LA PIEZA ESTA VIVA O CAPTURADA
	private boolean estadoCaptura = false;

  // METODO CONSTRUCTOR
	public Pieza(int color, int tipo, int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.color = color;
		this.tipo = tipo;
	}

  // METODOS GETERS Y SETTERS


  public void setTipo(int tipo){
    this.tipo = tipo;
  }

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getColor() {
		return this.color;
	}

     public String toString() {
		String strcolor = (this.color==colorBlanco?"b":"n");
		String strTipo = "desconocido";
		switch (this.tipo) {
			case tipoAlfil: strTipo = "a";break;
			case tipoRey: strTipo = "r";break;
			case tipoCaballo: strTipo = "c";break;
			case tipoPeon: strTipo = "p";break;
			case tipoReina: strTipo = "d";break;
			case tipoTorre: strTipo = "t";break;
		}

    String strfila = getfilaString(this.fila);
		String strcolumna = getcolumnaString(this.columna);
		
		return strcolor+" "+strTipo+" "+strfila+"/"+strcolumna;
	}
  public int getTipo() {
		return this.tipo;
	}

  public static String getfilaString(int fila){
		String strfila = "desconocido";
		switch (fila) {
			case fila_1: strfila = "1";break;
			case fila_2: strfila = "2";break;
			case fila_3: strfila = "3";break;
			case fila_4: strfila = "4";break;
			case fila_5: strfila = "5";break;
			case fila_6: strfila = "6";break;
			case fila_7: strfila = "7";break;
			case fila_8: strfila = "8";break;
		}
		return strfila;
	}
  public static String getcolumnaString(int columna){
		String strcolumna = "desconocido";
		switch (columna) {
			case columna_A: strcolumna = "A";break;
			case columna_B: strcolumna = "B";break;
			case columna_C: strcolumna = "C";break;
			case columna_D: strcolumna = "D";break;
			case columna_E: strcolumna = "E";break;
			case columna_F: strcolumna = "F";break;
			case columna_G: strcolumna = "G";break;
			case columna_H: strcolumna = "H";break;
		}
		return strcolumna;
	}

  // METODO PARA CAMBIAR EL ESTADO DE UNA PIEZA (VIVA O CAPTURADA)
  public void definirEstadoDeCaptura(boolean isCaptured) {
		this.estadoCaptura = isCaptured;
	}

  // METODO QUE DEFINE SI LA PIEZA ESTA O NO CAPTURADA
	public boolean estaCapturada() {
		return this.estadoCaptura;
	}

}
