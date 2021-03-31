/**
 * reference
 *   a  b  c  d  e  f  g  h  
 *  +--+--+--+--+--+--+--+--+
 * 8|BR|BN|BB|BQ|BK|BB|BN|BR|8
 * +--+--+--+--+--+--+--+--+
 * 7|BP|BP|BP|BP|BP|BP|BP|BP|7
 *  +--+--+--+--+--+--+--+--+
 * ..
 * 2|WP|WP|WP|WP|WP|WP|WP|WP|2
 *  +--+--+--+--+--+--+--+--+
 * 1|WR|WN|WB|WQ|WK|WB|WN|WR|1
 *  +--+--+--+--+--+--+--+--+
 *   a  b  c  d  e  f  g  h  
 *
 */
public class ValidarMovimiento {

	private AjedrezJuego ajedrezJuego;
	private Pieza piezaReferente;
	private Pieza piezaDestino;

  public boolean[] peones = {false, false, false, false, false, false, false, false};

	public ValidarMovimiento(AjedrezJuego ajedrezJuego){
		this.ajedrezJuego = ajedrezJuego;
	}
	
	/**
	 * Checks if the specified move is valid
	 * @param filaReferente
	 * @param columnaReferente
	 * @param filaDestino
	 * @param columnaDestino
	 * @return true if move is valid, false if move is invalid
	 */
	public boolean esMovimientoValido(int filaReferente,
			int columnaReferente, int filaDestino, int columnaDestino) {
		
		piezaReferente = ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaReferente);
		piezaDestino = this.ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaDestino, columnaDestino);
			
		// source piece does not exist
		if( piezaReferente == null ){
			System.out.println("no hay pieza referente");
			return false;
		}
		
		// source piece has right color?
		if( piezaReferente.getColor() == Pieza.colorBlanco
				&& this.ajedrezJuego.obtenerEstadoDelJuego() == AjedrezJuego.estadoBlanco){
			// ok
		}else if( piezaReferente.getColor() == Pieza.colorNegro
				&& this.ajedrezJuego.obtenerEstadoDelJuego() == AjedrezJuego.estadoNegro){
			// ok
		}else{
			System.out.println("no es tu turno");
			return false;
		}
		
		// check if target location within boundaries
		if( filaDestino < Pieza.fila_1 || filaDestino > Pieza.fila_8
				|| columnaDestino < Pieza.columna_A || columnaDestino > Pieza.columna_H){
			System.out.println("fila o columna de destino estan fuera de posicion valida");
			return false;
		}
		
		// validate piece movement rules
		boolean movimientoValidoDePieza = false;
		actualizar3();
		switch (piezaReferente.getTipo()) {
			case Pieza.tipoAlfil:
				movimientoValidoDePieza = esValidoMovimientoAlfil(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			case Pieza.tipoRey:
				movimientoValidoDePieza = esValidoMovimientoRey(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			case Pieza.tipoCaballo:
				movimientoValidoDePieza = esValidoMovimientoCaballo(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			case Pieza.tipoPeon:
				movimientoValidoDePieza = esValidoMovimientoPeon(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			case Pieza.tipoReina:
				movimientoValidoDePieza = esValidoMovimientoReina(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			case Pieza.tipoTorre:
				movimientoValidoDePieza = esValidoMovimientoTorre(filaReferente,columnaReferente,filaDestino,columnaDestino);break;
			default: break;
		}
		if( !movimientoValidoDePieza){
			return false;
		}else{
			// ok
		}
		
		
		// handle stalemate and checkmate
		// ..
		
		return true;
	}
	//metodo para actualizar la etiqueta del color del jugador
	  public void actualizar3() {
		   this.ajedrezJuego.actualizar2();
	 }

	private boolean esCapturablePosicionDeDestino() {
		if( piezaDestino == null ){
			return false;
		}else if( piezaDestino.getColor() != piezaReferente.getColor()){
			return true;
		}else{
			return false;
		}
	}

	private boolean estaLibrePosicionDeDestino() {
		return piezaDestino == null;
	}

	private boolean esValidoMovimientoAlfil(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		//The bishop can move any number of squares diagonally, but may not leap
		//over other pieces.
		
		// target location possible?
		if( estaLibrePosicionDeDestino() || esCapturablePosicionDeDestino()){
			//ok
		}else{
			System.out.println("posicion de destino no capturable ni libre");
			return false;
		}
		
		boolean esValido = false;
		// first lets check if the path to the target is diagonally at all
		int diffFila = filaDestino - filaReferente;
		int diffColumna = columnaDestino - columnaReferente;
		
		if( diffFila==diffColumna && diffColumna > 0){
			// moving diagonally up-right
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,+1,+1);

		}else if( diffFila==-diffColumna && diffColumna > 0){
			// moving diagnoally down-right
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,-1,+1);
			
		}else if( diffFila==diffColumna && diffColumna < 0){
			// moving diagnoally down-left
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,-1,-1);

		}else if( diffFila==-diffColumna && diffColumna < 0){
			// moving diagnoally up-left
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,+1,-1);
			
		}else{
			// not moving diagonally
			System.out.println(diffFila);
			System.out.println(diffColumna);
			System.out.println("no movimiento diagonal");
			esValido = false;
		}
		return esValido;
	}

	private boolean esValidoMovimientoReina(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		// The queen combines the power of the rook and bishop and can move any number
		// of squares along rank, file, or diagonal, but it may not leap over other pieces.
		//
		boolean reultado = esValidoMovimientoAlfil(filaReferente, columnaReferente, filaDestino, columnaDestino);
		reultado |= esValidoMovimientoTorre(filaReferente, columnaReferente, filaDestino, columnaDestino);
		return reultado;
	}

	private boolean esValidoMovimientoPeon(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		
		boolean esValido = false;
		// The pawn may move forward to the unoccupied square immediately in front
		// of it on the same file, or on its first move it may advance two squares
		// along the same file provided both squares are unoccupied
		if( estaLibrePosicionDeDestino() ){
			if( columnaReferente == columnaDestino){
				// same column
				if(  piezaReferente.getColor() == Pieza.colorBlanco ){
					// white
					if((filaReferente+1 == filaDestino) || ((filaDestino == 3 && piezaReferente.getColor() == Pieza.colorBlanco) && (filaDestino > filaReferente) && (avanceDobleBlancas(filaReferente, columnaReferente)))){

            
						// move one up
						esValido = true;
					}else{
						System.out.println("not moving one up");
						esValido = false;
					}
				}else{
					// black
					if((filaReferente-1 == filaDestino) || ((filaDestino == 4 && piezaReferente.getColor() == Pieza.colorNegro) && (filaDestino < filaReferente) && (avanceDobleNegras(filaReferente, columnaReferente)))){
						// move one down
						esValido = true;
					}else{
						System.out.println("not moving one down");
						esValido =  false;
					}
				}
			}else if (((piezaReferente.getColor() == Pieza.colorBlanco) && (filaReferente == 4) && (comerAlPasoBlancas(filaReferente, columnaReferente, filaDestino, columnaDestino)) && (filaReferente < filaDestino))
      || ((piezaReferente.getColor() == Pieza.colorNegro) && (filaReferente == 3) && (comerAlPasoNegras(filaReferente, columnaReferente, filaDestino, columnaDestino)) && (filaReferente > filaDestino))){

						esValido = true;
      } else{

				// not the same column
				System.out.println("no esta en la misma columna");
				esValido = false;
			}
			
		// or it may move
		// to a square occupied by an opponent’s piece, which is diagonally in front
		// of it on an adjacent file, capturing that piece. 
		}else if( esCapturablePosicionDeDestino() ){
			
			if( columnaReferente+1 == columnaDestino || columnaReferente-1 == columnaDestino){
				// one column to the right or left
				if(  piezaReferente.getColor() == Pieza.colorBlanco ){
					// white
					if( filaReferente+1 == filaDestino ){
						// move one up
						esValido = true;
					}else{
						System.out.println("no movimiento encima");
						esValido = false;
					}
				}else{
					// black
					if( filaReferente-1 == filaDestino ){
						// move one down
						esValido = true;
					}else{
						System.out.println("no movimiento abajo");
						esValido = false;
					}
				}
			}else{
				// note one column to the left or right
				System.out.println("no movimiento de columna de derecha o de izquierda");
				esValido = false;
			}
		}
		
		return esValido;
	}

  private boolean avanceDobleBlancas(int filaReferente, int columnaReferente){

   if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente+1, columnaReferente) == null){

     return true;
   }

   return false;
  }

    private boolean avanceDobleNegras(int filaReferente, int columnaReferente){

   if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente-1, columnaReferente) == null){

     return true;
   }

   return false;
  }

  private boolean comerAlPasoBlancas(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino){

  Pieza peonOponente = null;
  if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino) != null && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getTipo() == Pieza.tipoPeon && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getColor() == Pieza.colorNegro){
    
    ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).definirEstadoDeCaptura(true);
    return true;
  } else if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino) != null && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getTipo() == Pieza.tipoPeon && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getColor() == Pieza.colorNegro){
    
    ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).definirEstadoDeCaptura(true);
    return true;
  }
      
    return false;
  }

  private boolean comerAlPasoNegras(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino){

  Pieza peonOponente = null;
  if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino) != null && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getTipo() == Pieza.tipoPeon && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getColor() == Pieza.colorBlanco){
    
    ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).definirEstadoDeCaptura(true);
    return true;
  } else if (ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino) != null && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getTipo() == Pieza.tipoPeon && ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).getColor() == Pieza.colorBlanco){
    
    ajedrezJuego.PiezaNoCapturadaEnDeterminadaPosicion(filaReferente, columnaDestino).definirEstadoDeCaptura(true);
    return true;
  }
      
    return false;
  }

	private boolean esValidoMovimientoCaballo(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		// The knight moves to any of the closest squares which are not on the same rank,
		// file or diagonal, thus the move forms an "L"-shape two squares long and one
		// square wide. The knight is the only piece which can leap over other pieces.
		
		// target location possible?
		if( estaLibrePosicionDeDestino() || esCapturablePosicionDeDestino()){
			//ok
		}else{
			System.out.println("posicion de destino no esta libre y no es capturable");
			return false;
		}
		
		if( filaReferente+2 == filaDestino && columnaReferente+1 == columnaDestino){
			// move up up right
			return true;
		}else if( filaReferente+1 == filaDestino && columnaReferente+2 == columnaDestino){
			// move up right right
			return true;
		}else if( filaReferente-1 == filaDestino && columnaReferente+2 == columnaDestino){
			// move down right right
			return true;
		}else if( filaReferente-2 == filaDestino && columnaReferente+1 == columnaDestino){
			// move down down right
			return true;
		}else if( filaReferente-2 == filaDestino && columnaReferente-1 == columnaDestino){
			// move down down left
			return true;
		}else if( filaReferente-1 == filaDestino && columnaReferente-2 == columnaDestino){
			// move down left left
			return true;
		}else if( filaReferente+1 == filaDestino && columnaReferente-2 == columnaDestino){
			// move up left left
			return true;
		}else if( filaReferente+2 == filaDestino && columnaReferente-1 == columnaDestino){
			// move up up left
			return true;
		}else{
			return false;
		}
	}

	private boolean esValidoMovimientoRey(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		
		
		// target location possible?
		if( estaLibrePosicionDeDestino() || esCapturablePosicionDeDestino()){
			//ok
		}else{
			System.out.println("ubicacion de destino no libre ni pieza capturable");
			return false;
		}
		
		// The king moves one square in any direction, the king has also a special move which is
		// called castling and also involves a rook.
		boolean esValido = true;
		if(( filaReferente+1 == filaDestino && columnaReferente == columnaDestino)
		|| ( filaReferente+1 == filaDestino && columnaReferente+1 == columnaDestino)
		||( filaReferente == filaDestino && columnaReferente+1 == columnaDestino)
		||( filaReferente-1 == filaDestino && columnaReferente+1 == columnaDestino)
		||( filaReferente-1 == filaDestino && columnaReferente == columnaDestino)
		||( filaReferente-1 == filaDestino && columnaReferente-1 == columnaDestino)
		||( filaReferente == filaDestino && columnaReferente-1 == columnaDestino)
		||( filaReferente+1 == filaDestino && columnaReferente-1 == columnaDestino)){
			esValido = true;
		}else{
			System.out.println("movimiento muy lejos");
			esValido = false;
		}
    
		
		return esValido;
	}

	private boolean esValidoMovimientoTorre(int filaReferente, int columnaReferente, int filaDestino, int columnaDestino) {
		// The rook can move any number of squares along any rank or file, but
		// may not leap over other pieces. Along with the king, the rook is also
		// involved during the king's castling move.
		if( estaLibrePosicionDeDestino() || esCapturablePosicionDeDestino()){
			//ok
		}else{
			System.out.println("ubicacion de destino no libre ni pieza capturable");
			return false;
		}
		
		boolean esValido = false;
		
		// first lets check if the path to the target is straight at all
		int diffFila = filaDestino - filaReferente;
		int diffColumna = columnaDestino - columnaReferente;
		
		if( diffFila == 0 && diffColumna > 0){
			// right
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,0,+1);

		}else if( diffFila == 0 && diffColumna < 0){
			// left
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,0,-1);
			
		}else if( diffFila > 0 && diffColumna == 0){
			// up
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,+1,0);

		}else if( diffFila < 0 && diffColumna == 0){
			// down
			esValido = !sonPiezasEntreLaReferenteYLaDestino(filaReferente,columnaReferente,filaDestino,columnaDestino,-1,0);
			
		}else{
			// not moving diagonally
			System.out.println("no movimiento justo o permitido");
			esValido = false;
		}
		
		return esValido;
	}


	private boolean sonPiezasEntreLaReferenteYLaDestino(int filaReferente, int columnaReferente,
			int filaDestino, int columnaDestino, int rowIncrementPerStep, int columnIncrementPerStep) {
		
		int filaActual = filaReferente + rowIncrementPerStep;
		int columnaActual = columnaReferente + columnIncrementPerStep;
		while(true){
			if(filaActual == filaDestino && columnaActual == columnaDestino){
				break;
			}
			if( filaActual < Pieza.fila_1 || filaActual > Pieza.fila_8
				|| columnaActual < Pieza.columna_A || columnaActual > Pieza.columna_H){
				break;
			}

			if( this.ajedrezJuego.hayPiezaNoCapturadaEnDeterminadaPosicion(filaActual, columnaActual)){
				System.out.println("piezas entre el destino y la referencia");
				return true;
			}

			filaActual += rowIncrementPerStep;
			columnaActual += columnIncrementPerStep;
		}
		return false;
	}
	
	public static void main(String[] args) {
		AjedrezJuego ch = new AjedrezJuego(new AjedrezGui());
		ValidarMovimiento mo = new ValidarMovimiento(ch);
		boolean esValido;
		
		int filaReferente; int columnaReferente; int filaDestino; int columnaDestino;
		int contadorTest = 1;

		// ok
		filaReferente = Pieza.fila_2; columnaReferente = Pieza.columna_D;
		filaDestino = Pieza.fila_3; columnaDestino = Pieza.columna_D;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado el test: "+(true==esValido));
		contadorTest++;
		
		// it's not white's turn
		filaReferente = Pieza.fila_2; columnaReferente = Pieza.columna_B;
		filaDestino = Pieza.fila_3; columnaDestino = Pieza.columna_B;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado del test: "+(false==esValido));
		contadorTest++;
		
		// ok
		filaReferente = Pieza.fila_7; columnaReferente = Pieza.columna_E;
		filaDestino = Pieza.fila_6; columnaDestino = Pieza.columna_E;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado de test: "+(true==esValido));
		contadorTest++;
		
		// pieces in the way
		filaReferente = Pieza.fila_1; columnaReferente = Pieza.columna_F;
		filaDestino = Pieza.fila_4; columnaDestino = Pieza.columna_C;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado del test: "+(false==esValido));
		contadorTest++;
		
		// ok
		filaReferente = Pieza.fila_1; columnaReferente = Pieza.columna_C;
		filaDestino = Pieza.fila_4; columnaDestino = Pieza.columna_F;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+".resultado del test "+(true==esValido));
		contadorTest++;
		
		// ok
		filaReferente = Pieza.fila_8; columnaReferente = Pieza.columna_B;
		filaDestino = Pieza.fila_6; columnaDestino = Pieza.columna_C;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+".resultado del test "+(true==esValido));
		contadorTest++;
		
		// invalid knight move
		filaReferente = Pieza.fila_1; columnaReferente = Pieza.columna_G;
		filaDestino = Pieza.fila_3; columnaDestino = Pieza.columna_G;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado del test "+(false==esValido));
		contadorTest++;
		
		// invalid knight move
		filaReferente = Pieza.fila_1; columnaReferente = Pieza.columna_G;
		filaDestino = Pieza.fila_2; columnaDestino = Pieza.columna_E;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado del test: "+(false==esValido));
		contadorTest++;
		
		// ok
		filaReferente = Pieza.fila_1; columnaReferente = Pieza.columna_G;
		filaDestino = Pieza.fila_3; columnaDestino = Pieza.columna_H;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+".resultado del test "+(true==esValido));
		contadorTest++;

		// pieces in between
		filaReferente = Pieza.fila_8; columnaReferente = Pieza.columna_A;
		filaDestino = Pieza.fila_5; columnaDestino = Pieza.columna_A;
		esValido = mo.esMovimientoValido(filaReferente, columnaReferente, filaDestino, columnaDestino);
		ch.moverPieza(filaReferente, columnaReferente, filaDestino, columnaDestino);
		System.out.println(contadorTest+". resultado del test: "+(false==esValido));
		contadorTest++;
		
		//ConsoleGui.printCurrentGameState(ch);

	}

}