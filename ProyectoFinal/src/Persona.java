import java.io.BufferedReader;
import java.io.PrintWriter;

public class Persona {

	// declaracion de variables
	private String nombre;
	private String colorFicha;
	private int edad;

	// metodo constructor
	public Persona(String nombre, String colorFicha, int edad) {
		// constructor
		this.colorFicha = colorFicha;
		this.edad = edad;
		this.nombre = nombre;

	}

	// constructor
	public Persona() {

	}

	// getters and setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColorFicha() {
		return colorFicha;
	}

	public void setColorFicha(String colorFicha) {
		this.colorFicha = colorFicha;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	// metodo guardar
	public void guardar(PrintWriter escribe) {

		// se utliza el metodo printerwriter para escribir
		// en el txt las variables
		escribe.println(nombre);
		escribe.println(colorFicha);
		escribe.println(edad);
	}

	// metodo para cargar la base de datos(txt)
	public Persona cargar(BufferedReader almacen) {
		// declaracion de variables
		String nombre;
		int edad;
		String colorFicha;
		// manejo de errores
		try {
			// se lee las linea del txt y se guarda en variables
			nombre = almacen.readLine();
			colorFicha = almacen.readLine();
			edad = Integer.parseInt(almacen.readLine());
			// devuelve una persona con las variables obtenidas como parametros
			return new Persona(nombre, colorFicha, edad);
		} catch (Exception e1) {

		}
		return null;

	}

}
