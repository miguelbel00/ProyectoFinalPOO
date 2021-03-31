import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AjedrezJuego {
	

  // DECLARAR ATRIBUTOS
  private int estadoJuego = estadoBlanco;
  public static final int estadoBlanco = 0;
  public static final int estadoNegro = 1;
  public static final int estadoAcabado = 2;

    boolean torreBlancaI = false;
    boolean torreNegraI = false;
    boolean torreBlancaD = false;
    boolean torreNegraD = false;
    boolean reyInicialNegro = false;
    boolean reyInicialBlanco = false;

    AjedrezGui ajedrezGui;

  // DECLARAR LAS PIEZAS
  private List<Pieza> piezas = new ArrayList<Pieza>();

  public boolean[] peones = {false, false, false, false, false, false, false, false};

  private ValidarMovimiento validadorMovimiento;

  public AjedrezJuego(AjedrezGui ajedrezGui) {


    this.ajedrezGui = ajedrezGui;
    // ATRIBUTO QUE PERMITE MOVER DEPENDIENDO DE LA CLASE VALIDAR MOVIMIENTO
    this.validadorMovimiento = new ValidarMovimiento(this);

    // INVOCAR MÃ‰TODO PARA CREAR Y ADICIONAR CADA PIEZA BLANCA, EL TIPO DE PIEZA, EL COLOR, LA FILA Y LA COLUMNA

    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoTorre, Pieza.fila_1, Pieza.columna_A);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoCaballo, Pieza.fila_1, Pieza.columna_B);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoAlfil, Pieza.fila_1, Pieza.columna_C);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoReina, Pieza.fila_1, Pieza.columna_D);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoRey, Pieza.fila_1, Pieza.columna_E);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoAlfil, Pieza.fila_1, Pieza.columna_F);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoCaballo, Pieza.fila_1, Pieza.columna_G);
    crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoTorre, Pieza.fila_1, Pieza.columna_H);

    // CICLO FOR NORMAL PARA CREAR CADA UNO DE LOS PEONES BLANCOS
    int columnaIteradora = Pieza.columna_A;
    for (int i = 0; i < 8; i++) {
      crearYAdicionarPieza(Pieza.colorBlanco, Pieza.tipoPeon, Pieza.fila_2, columnaIteradora);
      columnaIteradora++;
    }

    // INVOCAR MÃ‰TODO PARA CREAR Y ADICIONAR CADA PIEZA NEGRA, EL TIPO DE PIEZA, EL COLOR, LA FILA Y LA COLUMNA
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoTorre, Pieza.fila_8, Pieza.columna_A);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoCaballo, Pieza.fila_8, Pieza.columna_B);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoAlfil, Pieza.fila_8, Pieza.columna_C);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoReina, Pieza.fila_8, Pieza.columna_D);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoRey, Pieza.fila_8, Pieza.columna_E);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoAlfil, Pieza.fila_8, Pieza.columna_F);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoCaballo, Pieza.fila_8, Pieza.columna_G);
    crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoTorre, Pieza.fila_8, Pieza.columna_H);

    // CICLO FOR NORMAL PARA CREAR CADA UNO DE LOS PEONES BLANCOS
    columnaIteradora = Pieza.columna_A;
    for (int i = 0; i < 8; i++) {
      crearYAdicionarPieza(Pieza.colorNegro, Pieza.tipoPeon, Pieza.fila_7, columnaIteradora);
      columnaIteradora++;
    }
  }

  // MÃ‰TODO PARA CREAR CADA UNA DE LAS PIEZAS Y AGREGARLAS A LIST INTERNA DE LA CLASE
  private void crearYAdicionarPieza(int color, int tipo, int fila, int columna) {
    Pieza pieza = new Pieza(color, tipo, fila, columna);
    // AGREGANDO PIEZAS AL LIST
    this.piezas.add(pieza);
  }






  // METODO PARA MOVER UNA PIEZA A UNA UBICACION ESPECIFICA, IS LA UBICACION OBJETIVO ESTA OCUPADA POR UNA PIEZA OPONENTE, ENTONCES DICHE PIEZA SERA MARCADA COMO PIEZA "estaCapturada"
  public boolean moverPieza(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
	  
    // INVOCAR MÃ‰TODO PARA VER CÃšAL ES LA PIEZA QUE SE DESEA MOVER
    Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaReferente);
    Pieza torre = PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino);
    
    if ((pieza.getTipo() == Pieza.tipoRey) 
    && ((PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino) != null) && (PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino).getTipo() == Pieza.tipoTorre) && (PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino).getColor() == pieza.getColor()))){


        if(torre.getFila() == 0 && torre.getColumna()== 0 && torreBlancaI == false && reyInicialBlanco == false){

          // CAMBIANDO LAS COORDENADAS DONDE AHORA ESTARÃ� LA PIEZA
           pieza.setFila(filaDestino);
           pieza.setColumna(columnaDestino+2);

           torre.setColumna(3);

        }else
        if(torre.getFila() == 0 && torre.getColumna()== 7 && torreBlancaD == false && reyInicialBlanco == false){

          // CAMBIANDO LAS COORDENADAS DONDE AHORA ESTARÃ� LA PIEZA
           pieza.setFila(filaDestino);
           pieza.setColumna(columnaDestino-1);

           torre.setColumna(5);

        }else        
        if(torre.getFila() == 7 && torre.getColumna()== 0 && torreNegraI == false && reyInicialNegro == false){

          // CAMBIANDO LAS COORDENADAS DONDE AHORA ESTARÃ� LA PIEZA
           pieza.setFila(filaDestino);
           pieza.setColumna(columnaDestino+2);
           
           torre.setColumna(3);

        }else
        if(torre.getFila() == 7 && torre.getColumna()== 7 && torreNegraD == false && reyInicialNegro == false){

          // CAMBIANDO LAS COORDENADAS DONDE AHORA ESTARÃ� LA PIEZA
           pieza.setFila(filaDestino);
           pieza.setColumna(columnaDestino-1);

           torre.setColumna(5);
           
        }
        
       this.ajedrezGui.rellenar(this);       

        this.cambiarEstadoDelJuego();       
        return true;

    // INVOCAR MÃ‰TODO PARA VALIDAR SI EL MOVIMIENTO DESEADO ES VÃ�LIDO
    }else 
    if (!this.validadorMovimiento.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino)) {
      // IMPRIMIR POR CONSOLA SI EL MOVIMIENTO ES INVALIDO
      System.out.println("movimiento invalido");
      return false;
    }
    

     // DECLARAR UNA VARIABLE DE TIPO PIEZA, QUE SERA UNA PIEZA QUE PUEDE LLEGARSE A CAPTURAR SI COINCIDE CON LAS COORDENADAS A LAS QUE SE QUIERE MOVER LA PRIMERA PIEZA Y SI EL COLOR ES DISTINTO
    Pieza piezaOponente = null;

    // VERIFICAR SI EL MOVIMIENTO DESEADO CAPTURARA UNA PIEZA OPONENTE, EN CUYO CASO LA VARIABLE "PIEZAOPONENTE" SERA ESTA PIEZA A CAPTURAR
    // DECLARANDO COLOR OPONENTE Y ASIGNANDO POR MEDIO DEL CONDICIONAL
    int colorOponente = (pieza.getColor() == Pieza.colorNegro ? Pieza.colorBlanco : Pieza.colorNegro);
    if (hayPiezaOponenteNoCapturadaEnDeterminadaPosicion(colorOponente, filaDestino, columnaDestino)) {
      piezaOponente = PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino);
      // CAMBIAR EL ESTADO DE CAPTURA A TRUE DE LA PIEZA OPONENTE CAPTURADA
      piezaOponente.definirEstadoDeCaptura(true);
    };

    // CAMBIANDO LAS COORDENADAS DONDE AHORA ESTARA LA PIEZAÃ§

    int a=filaDestino;
    int b=columnaDestino;
    pieza.setFila(filaDestino);
    pieza.setColumna(columnaDestino);




/**************************SEGEMENTO PIPE************************************/
int movimientosReyBlanco = 0;
	int movimientosTorreBlancaIzquierda = 0;
	int movimientosTorreBlancaDerecha = 0;	
	int movimientosReyNegro = 0;
	int movimientosTorreNegraIzquierda = 0;
	int movimientosTorreNegraDerecha = 0;
	

  if(  (filaDestino==0 && columnaDestino==7 && pieza.getTipo()==Pieza.tipoRey)
  ||
    (filaDestino==0 && columnaDestino==0 && pieza.getTipo()==Pieza.tipoRey)||
  (filaDestino==7 && columnaDestino==0 && pieza.getTipo()==Pieza.tipoRey)||
(filaDestino==7 && columnaDestino==7 && pieza.getTipo()==Pieza.tipoRey)  )

  {pieza.setFila(filaReferente);
  pieza.setColumna(columnaReferente);}
  
	 /*pieza = PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaReferente);*/
    Pieza piezaDestino = PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino);
    
    //inicia if
    if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorBlanco)
    		&&(pieza.getFila()== 0)&&(pieza.getColumna()== 7)) {

		movimientosTorreBlancaDerecha++;}else

    if((pieza.getTipo()==Pieza.tipoRey)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorBlanco)
    		&&(pieza.getFila()== 0)&&(pieza.getColumna()== 0)) {
	
	movimientosTorreBlancaIzquierda++;}else
	
	if((pieza.getTipo()==Pieza.tipoRey)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorBlanco)) {
		
		movimientosReyBlanco++;
    
		
		if(piezaDestino!=null) {



			if((piezaDestino.getTipo()==Pieza.tipoTorre)&&(piezaDestino.estaCapturada() == false)&&
					(piezaDestino.getColor()== Pieza.colorBlanco)&&(piezaDestino.getFila()==0)&&(piezaDestino.getColumna()==7)){
				System.out.println("ENTRE AQUI TORRE BLANCA DERECHA");

        
				if(movimientosTorreBlancaDerecha==0) {
          
         
          pieza.setColumna(6);piezaDestino.setColumna(5);
          this.ajedrezGui.rellenar(this);}
				
			}

      
			if((piezaDestino.getTipo()==Pieza.tipoTorre)&&(piezaDestino.estaCapturada() == false)&&
					(piezaDestino.getColor()== Pieza.colorBlanco)&&(piezaDestino.getFila()==0)&&(piezaDestino.getColumna()==0)){
				System.out.println("ENTRE AQUI TORRE BLANCA IZQUIERDA");
        
				if(movimientosTorreBlancaIzquierda==0) {
          
          
          pieza.setColumna(1);piezaDestino.setColumna(3);
           this.ajedrezGui.rellenar(this);}
				
			}
			
			
		}
		

	}else

	 if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorNegro)
		&&(pieza.getFila()== 7)&&(pieza.getColumna()== 7)) {
		movimientosTorreNegraDerecha++;}else

if((pieza.getTipo()==Pieza.tipoRey)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorNegro)
		&&(pieza.getFila()== 7)&&(pieza.getColumna()== 0)) {
	
	movimientosTorreNegraIzquierda++;}else
	
if((pieza.getTipo()==Pieza.tipoRey)&&(pieza.estaCapturada() == false)&&(pieza.getColor()== Pieza.colorNegro)) {
		
		movimientosReyNegro++;
		
            System.out.println("ENTRE AQUI TORRE NEGRA DERECHA");
		if(piezaDestino!=null) {
			if((piezaDestino.getTipo()==Pieza.tipoTorre)&&(piezaDestino.estaCapturada() == false)&&
					(piezaDestino.getColor()== Pieza.colorNegro)&&(piezaDestino.getFila()==0)&&(piezaDestino.getColumna()==7)){
				


				if(movimientosTorreNegraDerecha==0) 
        
        
        
        {pieza.setColumna(6);piezaDestino.setColumna(5);
         this.ajedrezGui.rellenar(this);}
				
			}
			
              System.out.println("ENTRE AQUI TORRE NEGRA IZQUIERDA");
			if((piezaDestino.getTipo()==Pieza.tipoTorre)&&(piezaDestino.estaCapturada() == false)&&
					(piezaDestino.getColor()== Pieza.colorNegro)&&(piezaDestino.getFila()==0)&&(piezaDestino.getColumna()==0)){
				


				if(movimientosTorreNegraIzquierda==0) {
          
          
          pieza.setColumna(1);piezaDestino.setColumna(3);
           this.ajedrezGui.rellenar(this);}
				
			}
			
			
		}
		
	

	}else

/**************************SEGMENTO PIPE*************************************/





      // VALIDAR SI AL MOVER LA PIEZA EL REY QUEDA EN JAQUE, EN CUYO CASO SE RETROCEDERA EL MOVIMIENTO
      if (condicionJaque(pieza.getColor())) {

        // DEVOLVER LA PIEZA A SU POSICIÃ“N
        pieza.setFila(filaReferente);
        pieza.setColumna(columnaReferente);
        // DEVOLVER EL ESTADO DE CAPTURA DE LA PIEZA OPONENTE A FALSE, YA QUE EL MOVIMIENTO NO HA SIDO VALIDO
        if (piezaOponente != null) {
          piezaOponente.definirEstadoDeCaptura(false);
        }
        // IMPRIMIR EN CONSOLA EN CASO DE NO SER VÃ�LIDO EL MOVIMIENTO
        System.out.println("No se puede mover esta pieza, el rey queda en jaque");
      }       
      

      else if (condicionJaque(colorOponente)){
        if(!hayEscape(colorOponente))
        {
          if(estadoJuego==estadoNegro){
           JOptionPane.showMessageDialog(null, "JAQUE MATE \nGANAN BLANCAS");
          } 
          else{
           JOptionPane.showMessageDialog(null, "JAQUE MATE \nGANAN NEGRAS");
          }
          
        estadoJuego=estadoAcabado;       
        }
      } 


      else if(estaAhogado(colorOponente))
        {System.out.print("Ahogado papuh");
        estadoJuego=estadoAcabado;
      } 


      else{
        this.cambiarEstadoDelJuego();
      }

      


      if(peonCoronado( pieza, filaDestino,columnaDestino )){

        int colorPieza = pieza.getColor();


        switch(Marco.promocion()){

          case 0: pieza.setTipo(Pieza.tipoReina);break;

          case 1:  pieza.setTipo(Pieza.tipoTorre);break;

          case 2: pieza.setTipo(Pieza.tipoAlfil);break;

          case 3: pieza.setTipo(Pieza.tipoCaballo); break;

       }

       this.ajedrezGui.rellenar(this);       
       
       
    }

    return true;
}
  
private boolean hayEscape(int color) {

cambiarEstadoDelJuego();
for(Pieza pieza: this.piezas){
  if(pieza.getColor()==color && pieza.estaCapturada() == false){

int filaReferente = pieza.getFila();
int columnaReferente = pieza.getColumna();
      
        for(int filaIteradora =0;filaIteradora<8;filaIteradora++){
          for(int columnaIteradora = 0;columnaIteradora<8;columnaIteradora++){
                     
              if(
                this.validadorMovimiento.esMovimientoValido(pieza.getFila(), pieza.getColumna(), filaIteradora, columnaIteradora))
              { 
                

                pieza.setFila(filaIteradora);
                pieza.setColumna(columnaIteradora);

                if (!condicionJaque(color)){

                  pieza.setFila(filaReferente);
                  pieza.setColumna(columnaReferente);
                  System.out.println("Escape");
                 
                return true;}
                else{

                  pieza.setFila(filaReferente);
                  pieza.setColumna(columnaReferente);
                  System.out.println("NO hay escape");
                }
  
                }

          }
        }
    }
  }
  
  return false;
}

private boolean estaAhogado(int color) {
	
  cambiarEstadoDelJuego();

	for(Pieza pieza: this.piezas) {

		if((pieza.getColor()==color) && (pieza.estaCapturada() == false)) {
	
			for(int filaIteradora=0;filaIteradora<8;filaIteradora++) {
				for(int columnaIteradora=0;columnaIteradora<8;columnaIteradora++) {
							
					if(this.validadorMovimiento.esMovimientoValido(pieza.getFila(), pieza.getColumna(), filaIteradora, columnaIteradora))
					{	
						int filaReferente = pieza.getFila();
						int columnaReferente = pieza.getColumna();
						
						pieza.setFila(filaIteradora);
						pieza.setColumna(columnaIteradora);
						
						if (condicionJaque(color)){    
							
							pieza.setFila(filaReferente);
			                pieza.setColumna(columnaReferente);

                  	

						}else {
							  pieza.setFila(filaReferente);
			                  pieza.setColumna(columnaReferente);
			                  System.out.println("NO HAY AHOGADO, MUEVA LA FICHA: "+(pieza.getTipo())+"  (fila: " + pieza.getFila()+"   columna: " + pieza.getColumna()+")" + " (FILA ITERADORA: " + filaIteradora + "COLUMNA ITERADORA: " + columnaIteradora);
                        cambiarEstadoDelJuego();
			                  return false;
						}
						
						}          	
								
				}
			}		
			
		}
		
		
	}


JOptionPane.showMessageDialog(null, "AHOGADO\nTABLAS");
//	System.out.println("AHOGADO PAPUH!!!")
    return true;

}



  //VALIDAR SI EL JUEGO TERMINO, ES DECIR SI UN REY HA SIDO CAPTURADO
  private boolean condicionDeFinalAlcanzada() {
    for (Pieza pieza : this.piezas) {
      if (pieza.getTipo() == Pieza.tipoRey && pieza.estaCapturada()) {
       
        return true;
      } else {
      }
    }

    return false;
  }

  // VALIDAR SI EL REY ESTA EN JAQUE O ESTARA EN JAQUE
  private boolean condicionJaque(int color) {

    if (color == Pieza.colorBlanco) {

    // VALIDAR SI EL REY BLANCO ESTA EN JAQUE
      return jaqueBlanco(color);

    } else {

    // VALIDAR SI EL REY NEGRO ESTA EN JAQUE
      return jaqueNegro(color);

    }
  }

  // MÃ‰TODO PARA VERIFICAR SI EL REY BLANCO SE ENCUENTRA EN JAQUE
  private boolean jaqueBlanco(int color) {

    // INVOCAR CADA METODO DE CADA PIEZA PARA VERIFICAR SI ALGUNA PIEZA TIENE AL REY EN JAQUE
    if (jaquePeonNegro(color) || jaqueRey(color) || jaqueCaballo(color) || jaqueTorre(color) || jaqueAlfil(color) || jaqueDama(color)) {

      return true;

    } else {

      return false;
    }

  }

  // MÃ‰TODO PARA VERIFICAR SI EL REY NEGRO SE ENCUENTRA EN JAQUE
  private boolean jaqueNegro(int color) {

    // INVOCAR CADA MÃ‰TODO DE CADA PIEZA PARA VERIFICAR SI ALGUNA PIEZA TIENE AL REY EN JAQUE
    if (jaquePeonBlanco(color) || jaqueRey(color) || jaqueCaballo(color) || jaqueTorre(color)  || jaqueAlfil(color) || jaqueDama(color)){

      return true;

    } else {

      return false;
    }

  }

  private Pieza obtenerRey(int color){

    Pieza rey = null;

    for (Pieza pieza : this.piezas) {
      if ((pieza.getTipo() == Pieza.tipoRey) && (pieza.getColor() == color)) {
        rey = new Pieza(color, Pieza.tipoRey, pieza.getFila(), pieza.getColumna());
      }
    }

    return rey;
  }

  // HAY DOS METODOS DE PEONES, UNO PARA NEGRAS Y OTRO PARA BLANCAS, YA QUE LA DIRECCIÃ“N ES DISTINTA

  // METODO PARA VALIDAR SI EL PEÃ“N NEGRO TIENE AL REY BLANCO EN JAQUE O SI LO TENDRÃ� EN JAQUE
  private boolean jaquePeonNegro(int color) {

    Pieza reyBlanco = obtenerRey(color);

    int cordenadaPIF = (reyBlanco.getFila()) + 1;
    int cordenadaPIC = (reyBlanco.getColumna()) - 1;

    int cordenadaPDF = (reyBlanco.getFila()) + 1;
    int cordenadaPDC = (reyBlanco.getColumna()) + 1;

    for (Pieza pieza : this.piezas) {if
    
     (((pieza.getTipo() == Pieza.tipoPeon) && (pieza.getColor() == Pieza.colorNegro)
          && (pieza.getFila() == cordenadaPIF) && (pieza.getColumna() == cordenadaPIC)
          && (pieza.estaCapturada() == false))
          || ((pieza.getTipo() == Pieza.tipoPeon) && (pieza.getColor() == Pieza.colorNegro)
              && (pieza.getFila() == cordenadaPDF) && (pieza.getColumna() == cordenadaPDC)
              && (pieza.estaCapturada() == false))) {
                
        return true;
      }
    }
    return false;
  }

  // METODO PARA VALIDAR SI EL PEÃ“N BLANCO TIENE AL REY BLANCO EN JAQUE O SI LO TENDRA EN JAQUE
  private boolean jaquePeonBlanco(int color) {

    Pieza reyNegro = obtenerRey(color);
    

    int cordenadaPIF = (reyNegro.getFila()) - 1;
    int cordenadaPIC = (reyNegro.getColumna()) - 1;

    int cordenadaPDF = (reyNegro.getFila()) - 1;
    int cordenadaPDC = (reyNegro.getColumna()) + 1;

    for (Pieza pieza : this.piezas) {
      if (((pieza.getTipo() == Pieza.tipoPeon) && (pieza.getColor() == Pieza.colorBlanco)
          && (pieza.getFila() == cordenadaPIF) && (pieza.getColumna() == cordenadaPIC)
          && (pieza.estaCapturada() == false))
          || ((pieza.getTipo() == Pieza.tipoPeon) && (pieza.getColor() == Pieza.colorBlanco)
              && (pieza.getFila() == cordenadaPDF) && (pieza.getColumna() == cordenadaPDC)
              && (pieza.estaCapturada() == false))) {
        return true;
      }
    }
    return false;
  }

private boolean jaqueRey(int color){

  Pieza reyPrincipal = obtenerRey(color);

   Pieza reyRival = null;

    for (Pieza pieza : this.piezas ){
      if((pieza.getTipo()== Pieza.tipoRey)&&(pieza.getColor()!=color)){

          reyRival = new Pieza ( Pieza.colorBlanco, Pieza.tipoRey, pieza.getFila(),pieza.getColumna());
      }
    }

    if(reyPrincipal.getFila()+1==reyRival.getFila()){
      if(
        (reyPrincipal.getColumna()==reyRival.getColumna())
        ||(reyPrincipal.getColumna()+1==reyRival.getColumna())
        ||(reyPrincipal.getColumna()-1==reyRival.getColumna())
        ){
          return true;
      }
    }

    if(reyPrincipal.getFila()==reyRival.getFila()){
      if(
        (reyPrincipal.getColumna()+1==reyRival.getColumna())
        ||(reyPrincipal.getColumna()-1==reyRival.getColumna())
        ){
          return true;
      }
    }

    if(reyPrincipal.getFila()-1==reyRival.getFila()){
      if(
        (reyPrincipal.getColumna()==reyRival.getColumna())
        ||(reyPrincipal.getColumna()+1==reyRival.getColumna())
        ||(reyPrincipal.getColumna()-1==reyRival.getColumna())
        ){
          return true;
      }
    }

    return false;
  }

//se utilizan ciclos para recorrer el tablero, dentro de ellos tenemos las condiciones para determinar si el rey esta en jaque o no 
private boolean jaqueAlfil(int color){

  Pieza rey = obtenerRey(color);

  int a = 1;
  int b = 1;

  while(a < 8){
    
    if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).getTipo())==Pieza.tipoAlfil)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).estaCapturada())==false)){
  
    return true;

    } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getTipo())!=Pieza.tipoAlfil)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getTipo())==Pieza.tipoAlfil && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).estaCapturada())==false)){

        break;
    }
    a++;
  }

a = 1;



  while((a<8) && (b<8)){

    if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).getTipo())==Pieza.tipoAlfil)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).estaCapturada())==false)){

return true;

  } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getTipo())!=Pieza.tipoAlfil)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getTipo())==Pieza.tipoAlfil && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).estaCapturada())==false)){

      break;
   }
    a++;
    b++;
  }

a = 1;
b = 1;

  while((a<8) && (b<8)){

    if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).getTipo())==Pieza.tipoAlfil)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).estaCapturada())==false)){

return true;

} else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getTipo())!=Pieza.tipoAlfil)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getTipo())==Pieza.tipoAlfil && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).estaCapturada())==false)){

      break;
  }
    a++;
    b++;
  }

a = 1;
b = 1;

  while((a<8) && (b<8)){

    if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).getTipo())==Pieza.tipoAlfil)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).estaCapturada())==false)){

return true;

  } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getTipo())!=Pieza.tipoAlfil)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getTipo())==Pieza.tipoAlfil && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).estaCapturada())==false)){

     break;
  }
    a++;
    b++;
  }

  return false;
}



boolean jaqueCaballo(int color){

  Pieza rey = obtenerRey(color);

   
  if(
      /*CABALLO 1 EN POSICION 1,2 CON RESPECTO AL REY)*/
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()+1)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()+1))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()+1))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()+1))).estaCapturada())==false)     )
||  /*CABALLO 2 EN POSICION 2,1 CON RESPECTO AL REY)*/
(    ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()+2)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()+2))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()+2))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()+2))).estaCapturada())==false)     )
||  /*CABALLO 3 EN POSICION 2,-1 CON RESPECTO AL REY)*/
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()+2)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()+2))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()+2))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()+2))).estaCapturada())==false)     )
||  /*CABALLO 4 EN POSICION 1,-2 CON RESPECTO AL REY)*/
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()+1)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()+1))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()+1))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()+1))).estaCapturada())==false)     )
||  /*CABALLO 5 EN POSICION -1,-2 CON RESPECTO AL REY)*/
(    ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()-1)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()-1))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()-1))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-2),(rey.getColumna()-1))).estaCapturada())==false)     )
||  /*CABALLO 6 EN POSICION -2,-1 CON RESPECTO AL REY)*/
(    ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()-2)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()-2))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()-2))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-1),(rey.getColumna()-2))).estaCapturada())==false)     )
||  /*CABALLO 7 EN POSICION -2,1 CON RESPECTO AL REY)*/
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()-2)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()-2))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()-2))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+1),(rey.getColumna()-2))).estaCapturada())==false)     )
||  /*CABALLO 8 EN POSICION -1,2 CON RESPECTO AL REY)*/
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()-1)))!=null)&&
(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()-1))).getTipo())==Pieza.tipoCaballo)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()-1))).getColor())!=color)
&&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+2),(rey.getColumna()-1))).estaCapturada())==false)     )

){


return true;

}

return false;

}

private boolean jaqueTorre(int color){

		Pieza rey = this.obtenerRey(color);

		int columnaIteradora=0;
		int filaIteradora=0; 

		boolean torreF = false;
		boolean torreI = false;
		boolean torreD = false;
		boolean torreT = false;


		filaIteradora =0;

		while(filaIteradora<8){
		filaIteradora++;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila()+filaIteradora,rey.getColumna());
		
		
		if(pieza!=null) {
			
			if (((pieza.getTipo()!=Pieza.tipoTorre)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
			{
			
			break;
			
			
			}else if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				torreF = true;
				break;
		
			}
				
		}
				
	}		
		
		
		filaIteradora =0;

		while(filaIteradora>-8){
		filaIteradora=filaIteradora-1;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila()+filaIteradora,rey.getColumna());
		
		
		if(pieza!=null) {
			
			if (((pieza.getTipo()!=Pieza.tipoTorre)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
			{
			
			break;
			
			
			}else if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				torreT = true;
				break;
		
			}
				
		}
				
	}
		
		columnaIteradora = 0;
		
		while(columnaIteradora<8){
			columnaIteradora++;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),rey.getColumna()+columnaIteradora);
		
		
		if(pieza!=null) {
			
			if (((pieza.getTipo()!=Pieza.tipoTorre)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
			{
			
			break;
			
			
			}else if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				torreD = true;
				break;
		
			}
				
		}
				
	}
				
	
		columnaIteradora = 0;
		
		while(columnaIteradora>-8){
			columnaIteradora--;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),rey.getColumna()+columnaIteradora);
		
		
		if(pieza!=null) {
			
			if (((pieza.getTipo()!=Pieza.tipoTorre)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
			{
			
			break;
			
			
			}else if((pieza.getTipo()==Pieza.tipoTorre)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				torreI = true;
				break;
		
			}
				
		}
				
	}
		
		
		if((torreF)) {
			return true;
		}else if((torreT)) {
			return true;
		}else if((torreI)) {
			return true;
		}else if((torreD)) {
			return true;
		}else {return false;}
		
		
}

 
  
  private boolean jaqueDama(int color){
    
    Pieza rey = obtenerRey(color);

    int columnaIteradora = 0;
    int filaIteradora = 0;

		while(filaIteradora<8){
		filaIteradora++;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila()+filaIteradora,rey.getColumna());
		
		
		if(pieza!=null) {
			
			if ((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				return true;
		
			}else if (((pieza.getTipo()!=Pieza.tipoReina)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
        {
          break;
      }
				
    }
  }

  filaIteradora =0;

		while(filaIteradora>-8){
		filaIteradora=filaIteradora-1;
		
		Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila()+filaIteradora,rey.getColumna());
		
		
		if(pieza!=null) {
			
			if ((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				return true;
		
			}else if (((pieza.getTipo()!=Pieza.tipoReina)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
        {
          break;
      }
				
    }
  }
  
  columnaIteradora = 0;

  while(columnaIteradora<8){
		columnaIteradora++;
		
	Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),rey.getColumna()+columnaIteradora);
		
		
		if(pieza!=null) {
			
			if ((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				return true;
		
			}else if (((pieza.getTipo()!=Pieza.tipoReina)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
        {
          break;
      }
				
    }
  }

  columnaIteradora = 0;

  while(columnaIteradora>-8){
		columnaIteradora= columnaIteradora-1;
		
	Pieza pieza = PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),rey.getColumna()+columnaIteradora);
		
		
		if(pieza!=null) {
			
			if ((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()!=color)){
				
				return true;
		
			}else if (((pieza.getTipo()!=Pieza.tipoReina)&&(pieza.estaCapturada()==false))
					||((pieza.getTipo()==Pieza.tipoReina)&&(pieza.estaCapturada()==false)&&(pieza.getColor()==color))) 
        {
          break;
      }
				
    }
  }
  	
    
      int a = 1;
      int b = 1;
 
      while(a < 8){
        
        if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).getTipo())==Pieza.tipoReina)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).getColor())!=color)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).estaCapturada())==false)){
      
        return true;

        } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getTipo())!=Pieza.tipoReina)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getTipo())==Pieza.tipoReina && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()+a))).estaCapturada())==false)){
          break;
        }
        a++;
      }

    a = 1;



      while((a<8) && (b<8)){

        if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).getTipo())==Pieza.tipoReina)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).getColor())!=color)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).estaCapturada())==false)){

    return true;

        } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getTipo())!=Pieza.tipoReina)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getTipo())==Pieza.tipoReina && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()+a),(rey.getColumna()-b))).estaCapturada())==false)){
          break;
        }
        a++;
        b++;
      }

    a = 1;
    b = 1;

      while((a<8) && (b<8)){

        if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).getTipo())==Pieza.tipoReina)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).getColor())!=color)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).estaCapturada())==false)){

    return true;

        } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getTipo())!=Pieza.tipoReina)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getTipo())==Pieza.tipoReina && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()+b))).estaCapturada())==false)){
          break;
        }
        a++;
        b++;
      }

    a = 1;
    b = 1;

      while((a<8) && (b<8)){

        if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).getTipo())==Pieza.tipoReina)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).getColor())!=color)
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).estaCapturada())==false)){

    return true;

        } else if(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)))!=null)&&
    (((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getTipo())!=Pieza.tipoReina)
    || ((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getTipo())==Pieza.tipoReina && PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b)).getColor() == rey.getColor()))
    &&(((PiezaNoCapturadaEnDeterminadaPosicion((rey.getFila()-a),(rey.getColumna()-b))).estaCapturada())==false)){
          break;
        }
        a++;
        b++;
      }

      return false;
    }
  

  // METODO QUE DEVUELVE LA PIEZA QUE SE DESEA MOVER, ES DECIR LA QUE COINCIDE CON LA FILA Y COLUMNA DEL MOUSE Y A SU VEZ ESTA VIVA
  public Pieza PiezaNoCapturadaEnDeterminadaPosicion(int fila, int columna) {
    for (Pieza pieza : this.piezas) {
      if (pieza.getFila() == fila && pieza.getColumna() == columna && pieza.estaCapturada() == false) {
        return pieza;
      }
    }
    return null;
  }

 
  private boolean hayPiezaOponenteNoCapturadaEnDeterminadaPosicion(int color, int fila, int columna) {
    for (Pieza pieza : this.piezas) {
      if (pieza.getFila() == fila && pieza.getColumna() == columna && pieza.estaCapturada() == false
          && pieza.getColor() == color) {
        return true;
      }
    }
    return false;
  }


  public boolean hayPiezaNoCapturadaEnDeterminadaPosicion(int fila, int columna) {
    for (Pieza pieza : this.piezas) {
      if (pieza.getFila() == fila && pieza.getColumna() == columna && pieza.estaCapturada() == false) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return current game state (one of AjedrezJuego.GAME_STATE_..)
   */
  public int obtenerEstadoDelJuego() {
    return this.estadoJuego;
  }

  /**
   * @return the internal list of pieces
   */
  public List<Pieza> getPiezas() {
    return this.piezas;
  }

  /**
   * switches the game state depending on the current board situation.
   */
  public void cambiarEstadoDelJuego() {

    // check if game end condition has been reached
    //
    if (this.condicionDeFinalAlcanzada()) {

      if (this.estadoJuego == AjedrezJuego.estadoNegro) {
        System.out.println("Game over! Negras ganan!");
      } else {
        System.out.println("Game over! Blancas ganan!");
      }

      this.estadoJuego = AjedrezJuego.estadoAcabado;
      return;
    }

     if (this.estadoJuego == AjedrezJuego.estadoNegro) {
     this.estadoJuego = estadoBlanco;
      System.out.println("cambiando a blancas");
      
      } else {
      this.estadoJuego = estadoNegro;
      System.out.println("cambiando a negras");
      System.out.println(this.estadoJuego);
      }

  }

  private boolean peonCoronado( Pieza pieza, int filaDestino,int columnaDestino ){

      if((pieza.getTipo()==Pieza.tipoPeon)&&(pieza.getColor()==Pieza.colorBlanco)&&(filaDestino==7))
      {
        return true;
      }
      if((pieza.getTipo()==Pieza.tipoPeon)&&(pieza.getColor()==Pieza.colorNegro)&&(filaDestino==0))
      {
        return true;
      }


    
    return false;
  }
//metodo para actualizar la etiqueta del color del jugador
  public void actualizar2() {
	   this.ajedrezGui.actualizardosk();
}




boolean caminoLibre(int color,int columnaDestino){

    Pieza rey = obtenerRey(color);
    
       if(columnaDestino==7){
         System.out.println("Entre aqui 3");
        for (int columnaIteradora=rey.getColumna();columnaIteradora<7;columnaIteradora++){
          
          System.out.println("Entre aqui 8");
            PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),4).setColumna(columnaIteradora);

              if(condicionJaque(color)){

                
                PiezaNoCapturadaEnDeterminadaPosicion(rey.getFila(),columnaIteradora).setColumna(rey.getColumna());
                System.out.println("Entre aqui");
                return false;

              }else{rey.setColumna(4);System.out.println("Entre aqui 2");}


        }
      }

      if(columnaDestino==0){
        System.out.println("Entre aqui 4");
        for (int columnaIteradora=rey.getColumna();columnaIteradora>0;columnaIteradora--){
          
              System.out.println("Entre aqui 5");
              rey.setColumna(columnaIteradora);

              if(condicionJaque(color)){rey.setColumna(4);return false;}else{rey.setColumna(4);System.out.println("Entre aqui 6");}


        }
      }

System.out.println("Entre aqui 7");
return true;
}





    











}
