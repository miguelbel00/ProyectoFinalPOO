import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * all x and y coordinates point to the upper left position of a component all
 * lists are treated as 0 being the bottom and size-1 being the top pieza
 * 
 */
public class AjedrezGui extends JPanel {

	private static final int tableroInicialX = 101;
	private static final int tableroInicialY = 51;

	private static final int recuadroX = 50;
	private static final int recuadroY = 50;


  private static final int piezaInicialX = tableroInicialX + (int)(recuadroX/2.0 - recuadroX/2.0);

	private static final int piezaInicialY = tableroInicialY + (int)(recuadroY/2.0 - recuadroY/2.0);
	
	private static final int arrastrarAlRecuadroInicialX = tableroInicialX - (int)(recuadroX/2.0);
	private static final int arrastrarAlRecuadroInicialY = tableroInicialY - (int)(recuadroY/2.0);

  // IMAGEN DE FONDO DE TIPO
	private Image imgFondo;
  // JLABEL PARA DEFINIR EL TURNO
	private JLabel lblEstadoDelJuego;
  // CREAR CADA PIEZA Y SU RESPECTIVA IMAGEN
	private List<PiezaGui> piezasGui = new ArrayList<PiezaGui>();
  // CREAR UN NUEVO JUEGO
	private AjedrezJuego ajedrezJuego;




  // METODO CONSTRUCTOR
	public AjedrezGui() {
		// BUSCANDO RUTA DE LA IMAGEN DE FONDO
		URL rutaImagenDeFondo = getClass().getResource("board.png");
    // ASIGNANDO IMAGEN DE FONDO
		this.imgFondo = new ImageIcon(rutaImagenDeFondo).getImage();

  this.ajedrezJuego = new AjedrezJuego(this);
  // FOR MEJORADO PARA RECORRER CADA PIEZA Y AGREGARLAS A LA PARTE LOGICA
 
  rellenar(this.ajedrezJuego);



		// CREAR LISTENER DE TIPO ARRASTRAR Y SOLTAR PIEZA 
		ArrastrarYSoltarPieza listener = new ArrastrarYSoltarPieza(this.piezasGui,
				this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
//
  
		//boton declinar
		JButton btnDeclinar = new JButton("Declinar");
		btnDeclinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "EL ADVERSARIO DECLINO");
				
			}

		});
    btnDeclinar.setBounds(100,100,100,100);
		this.add(btnDeclinar);

		
		// BOTON PARA CERRAR EL PROGRAMA
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marco.guardar();
				System.exit(0);
			}

			

		});
	
		btnCerrar.setBounds(100,100,100,100);
		this.add(btnCerrar);
		
		

		// CREAR JFRAME Y MOSTRARLO
		Marco marcoVentana = new Marco(this);
		marcoVentana.setVisible(true);
		marcoVentana.setTitle("Ajedrez");
		marcoVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//marcoVentana.setResizable(false);
		marcoVentana.setSize(this.imgFondo.getWidth(null), this.imgFondo.getHeight(null));
		
		
		// MOSTRAR EL ESTADO DEL JUEGO (TURNO DE BLANCAS O NEGRAS) POR MEDIO DEL METODO OBTENERESTADODELJUEGO
			
				textoParaLaEtiquetaDeEstadoDeJuego = this.obtenerEstadoDelJuegoTexto();
				this.lblEstadoDelJuego = new JLabel(textoParaLaEtiquetaDeEstadoDeJuego);
				lblEstadoDelJuego.setBounds(0, 30, 80, 30);
			  lblEstadoDelJuego.setForeground(Color.WHITE);
				this.add(lblEstadoDelJuego);
	}
	String textoParaLaEtiquetaDeEstadoDeJuego;

  // METODO PARA OBTENER EL ESTADO DEL JUEGO, ESTE A SU VEZ INVOCA OTRO METODO QUE DEFINE EL ESTADO DEL JUEGO
	private String obtenerEstadoDelJuegoTexto() {
    // DECLARAR VARIABLE ESTADODEJUEGOSTRING
		String estadoJuegoString = "unknown";
    if(this.ajedrezJuego.obtenerEstadoDelJuego() == AjedrezJuego.estadoNegro){
      estadoJuegoString = "Negras";
    }else if (this.ajedrezJuego.obtenerEstadoDelJuego() == AjedrezJuego.estadoBlanco){
      estadoJuegoString = "Blancas";
    }else{
      estadoJuegoString = "Acabado";
    }
		return estadoJuegoString;
	}
	
	
	
	//metodo para actualizar la etiqueta del color del jugador
	public void actualizardosk() {
		
		lblEstadoDelJuego.setText(obtenerEstadoDelJuegoTexto());
	}

  // Metodo PARA AGREGAR UNA PIEZA, PARA MOSTRAR SU IMAGEN POR INTERFAZ
	public void crearYAdicionarPieza(Pieza pieza) {
    // INVOCAR METODO PARA PONER LA IMAGEN DEPENDIENDO DEL TIPO DE PIEZA Y COLOR
		Image img = this.ponerImgPieza(pieza.getColor(), pieza.getTipo());
    // CREAR LAS PIEZAS DE PIEZAGUIA PARA LUEGO PODER MANIPULARLAS POR MEDIO DE ESTA CLASE
		PiezaGui piezaGui = new PiezaGui(img, pieza);
		this.piezasGui.add(piezaGui);
	}

	// METODO PARA COLOCAR CADA IMAGEN DEPENDIENDO DEL TIPO DEL PIEZA Y COLOR
	public Image ponerImgPieza(int color, int tipo) {
    // VARIABLE AUXILIAR PARA DEFINIR EL NOMBRE DEL ARCHIVO DEPENDIENDO DE LA PIEZA QUE SE DESEE COLOCAR
		String nombreArchivo = "";
    // SE CONCATENA LA VARIABLE CON EL COLOR DE LA PIEZA, SEA BLANCA (b) O NEGRA (n)
		nombreArchivo += (color == Pieza.colorBlanco ? "b" : "n");
    // SE SIGUE CONCATENANDO DEPENDIENDO DEL TIPO DE PIEZA
		switch (tipo) {
      // CONCATENAR CON ALFIL (a)
			case Pieza.tipoAlfil:
				nombreArchivo += "a";
				break;
      // CONCATENAR CON REY (r)
			case Pieza.tipoRey:
				nombreArchivo += "r";
				break;
      // CONCATENAR CON CABALLO (c)
			case Pieza.tipoCaballo:
				nombreArchivo += "c";
				break;
      // CONCATENAR CON PEON (p)
			case Pieza.tipoPeon:
				nombreArchivo += "p";
				break;
      // CONCATENAR CON DAMA (d)
			case Pieza.tipoReina:
				nombreArchivo += "d";
				break;
      // CONCATENAR CON TORRE (t)
			case Pieza.tipoTorre:
				nombreArchivo += "t";
				break;
		}
    // SE CONCATENA CON LA EXTENCION DEL ARCHIVO (.png)
		nombreArchivo += ".png";

    // DEPENDIENDO DE COMO QUEDE LA VARIABLE "nombreArchivo" SE INVOCA DICHA IMAGEN
		URL urlPieceImg = getClass().getResource("" + nombreArchivo);
		return new ImageIcon(urlPieceImg).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.imgFondo, 0, 0, null);

		for (PiezaGui piezaGui : this.piezasGui) {
			if( !piezaGui.estaCapturada()){
				g.drawImage(piezaGui.getImagen(), piezaGui.getX(), piezaGui.getY(), null);
			}
		}
	}

  // METODO QUE DEVUELVE EL TURNO ACCEDIENDO A OTRO METODO
	public int obtenerEstadoDelJuego() {
		return this.ajedrezJuego.obtenerEstadoDelJuego();
	}

  // METODO PARA CONVERTIR DE COLUMNA A COORDENADA X
 	public static int convertirColumnaA_X(int column){
		return piezaInicialX + recuadroX * column;
	}
  // METODO PARA CONVERTIR DE FILA A COORDENADA Y
  public static int convertirFilaA_Y(int row){
		return piezaInicialY + recuadroY * (Pieza.fila_8 - row);
	}
  // METODO PARA CONVERTIR DE COORDENADA X A COLUMNA
  public static int convertirX_A_Columna(int x){
		return (x - arrastrarAlRecuadroInicialX)/recuadroX;
	}
  // METODO PARA CONVERTIR DE COORDENADA Y A FILA
  public static int convertirY_A_Fila(int y){
		return Pieza.fila_8 - (y - arrastrarAlRecuadroInicialY)/recuadroY;
	} 

  // METODO PARA CAMBIAR DE POSICION UNA PIEZA QUE SE DESEE MOVER
  public void establecerUbicacionNuevaAUnaPieza(PiezaGui piezaArrastrante, int x, int y) {
    // DECLARAR VARIABLES A DONDE SE DESEA COLOCAR LA PIEZA, LA FILA Y LA COLUMNA SE ASIGNAN CON LAS COORDENADAS DEL MOUSE Y SON CONVERTIDAS POR MEDIO DE LOS METODOS "Convertir a"
		int filaDestino = AjedrezGui.convertirY_A_Fila(y);
		int columnaDestino = AjedrezGui.convertirX_A_Columna(x);
		// VALIDAR QUE LA PIEZA NO SE DESEE MOVER AFUERA DEL TABLERO
		if( filaDestino < Pieza.fila_1 || filaDestino > Pieza.fila_8 || columnaDestino < Pieza.columna_A || columnaDestino > Pieza.columna_H){
			// REESTABLECER LA POSICION DE LA PIEZA SI EL MOVIMIENTO NO ES VALIDO
			piezaArrastrante.resetarPosicionDePiezaReferente();
		
		}else{
			// IMPRIMIR POR CONSOLA EL MOVIMIENTO
			System.out.println("moviendo pieza a "+filaDestino+"/"+columnaDestino);
      // INVOCAR METODO PARA MOVER LA PIEZA
			this.ajedrezJuego.moverPieza(
					piezaArrastrante.getPieza().getFila(), piezaArrastrante.getPieza().getColumna()
					, filaDestino, columnaDestino);
			piezaArrastrante.resetarPosicionDePiezaReferente();
		}
	}

public List<PiezaGui>  getPiezasGui() {
		return this.piezasGui;
	}

public void rellenar(AjedrezJuego ajedrezJuego){ 
 
 this.piezasGui.clear();

 for (Pieza pieza : ajedrezJuego.getPiezas()) {
		crearYAdicionarPieza(pieza);
	}
}

}