import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Marco extends JFrame {
	public JTextField textNombre;
	public JTextField textEdad;
	public JTable table;
	public JTextField textColorFichas;
	public static ArrayList<Persona> list;

	// se crea Jpanel que contiene el juego
	Marco(JPanel panel) {

		// acccion para cuando salga presionando la x se active
		// el metodo de guardar el cual guarda los registros
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guardar();
			}
		});

		// se crea el arraylist "list"
		list = new ArrayList<Persona>();

		// se crean y aÃ±anden los paneles 2 y 3 como tambien
		// se agregan las pestaÃ±as
		JTabbedPane pestañas = new JTabbedPane();
		pestañas.add("AJEDREZ", panel);
		this.getContentPane().add(pestañas);
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(204, 153, 102));
		pestañas.add("PARTICIPANTES", panel2);
		panel2.setLayout(null);
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(204, 153, 102));
		pestañas.add("REGLAS", panel3);
		panel3.setLayout(null);

		// un label saber el nombre del jugador
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(60, 306, 73, 14);
		panel2.add(lblNombre);

		// un label para saber la edad del jugador
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEdad.setBounds(61, 362, 46, 14);
		panel2.add(lblEdad);

		// Jlabel para saber el
		JLabel lblColorFichas = new JLabel("ColorFichas");
		lblColorFichas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColorFichas.setBounds(60, 415, 82, 14);
		panel2.add(lblColorFichas);

		// textfield para tener el texto del nombre
		textNombre = new JTextField();
		textNombre.setBounds(189, 305, 144, 20);
		panel2.add(textNombre);
		textNombre.setColumns(10);
		// un textfiel para tener el texto de la edad
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(189, 361, 144, 20);
		panel2.add(textEdad);
		// un textfiel para tener el texto del color de las fichas
		textColorFichas = new JTextField();
		textColorFichas.setColumns(10);
		textColorFichas.setBounds(189, 414, 144, 20);
		panel2.add(textColorFichas);

		// creacion de Jtable para mostrar los registros de las personas
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombres", "Edad", "ColorFichas" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(0).setMinWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setMinWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setMinWidth(102);
		table.setBounds(44, 69, 438, 147);
		panel2.add(table);
		cargar(panel2);

		// un Jlabel dentro del panel

		JLabel lblNewLabel_1 = new JLabel("Participantes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(213, 11, 103, 24);
		panel2.add(lblNewLabel_1);

		// boton para guardar l
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int limpiar = 1;
				String nom, color;
				int ed;
				// manejo de errores
				try {
					// creacion de variables
					nom = textNombre.getText();
					color = textColorFichas.getText();
					ed = Integer.parseInt(textEdad.getText());

					// se agrega al arraylist "list" un nuevo objeto persona con las anteriores
					// variables como parametros
					list.add(new Persona(nom, color, ed));

				} catch (Exception e1) {
					limpiar = 0;
					// control de errores
					JOptionPane.showMessageDialog(null, "Verifica los parametros");
				}
				// if para limpiar todo dentro de los textfield
				if (limpiar == 1) {
					textColorFichas.setText("");
					textEdad.setText("");
					textNombre.setText("");
				}
				// metodo para refrescar el Jtable
				actualizar(panel2);
			}
		});
		btnGuardar.setBounds(44, 242, 89, 23);
		panel2.add(btnGuardar);

		// botn para modificar la fila
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion, edad, aux=0;
				String nombre,colorFichas, opciones[] = {"Nombre","ColorFichas","Edad"};
				Persona persona;
				// manejo de errores
				try {
					// se obtiene la fila seleccionada
					seleccion = table.getSelectedRow();
					// se obtiene la fila de la tabla
					persona = list.get(seleccion);
					//se muestra las opciones para modificar 
					aux = JOptionPane.showOptionDialog(null, "Seleccione el item a modificar", "Modificar", 0, 0, null, opciones, null);
					//ciclo para introducir los nuevos camb
					if(aux==0) {
						nombre = String.valueOf(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
						persona.setNombre(nombre);
						
					}else if(aux==1) {
						colorFichas = String.valueOf(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
						persona.setNombre(colorFichas);
						
					}else {
						edad = Integer.parseInt(JOptionPane.showInputDialog("Nueva Edad"));
						persona.setEdad(edad);
					}

				} catch (Exception e1) {
					// mensaje de error
					JOptionPane.showMessageDialog(null, "Escojer una fila");
				}
				// metodo para refrescar el jtable
				actualizar(panel2);

			}
		});
		btnModificar.setBounds(213, 242, 89, 23);
		panel2.add(btnModificar);

		// boton para eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion;
				// manejo de errores
				try {
					// obtencion de la fila seleccionada
					seleccion = table.getSelectedRow();
					// se remueve del arraylist "list" la fila seleccionada
					list.remove(seleccion);
				} catch (Exception e1) {
					// mensaje de error
					JOptionPane.showMessageDialog(null, "Escoje una casilla por favor");

				}
				// metodo para refrescar el jtable
				actualizar(panel2);

			}

		});
		btnEliminar.setBounds(393, 242, 89, 23);
		panel2.add(btnEliminar);
	}

	// metodo para refrescar el jtable
	private void actualizar(JPanel panel2) {
		// TODO Auto-generated method stub

		// se crea la matriz que estara dentro del jtable
		// esta vez con los nuevos registros aÃ±adidos eliminados o modificados
		String matriz[][] = new String[list.size()][3];
		Persona aux;
		for (int i = 0; i < list.size(); i++) {
			aux = list.get(i);
			matriz[i][0] = aux.getNombre();
			matriz[i][1] = aux.getColorFicha();
			matriz[i][2] = Integer.toString(aux.getEdad());
		}
		;
		table.setModel(new DefaultTableModel(matriz, new String[] { "Nombres", "Edad", "ColorFichas" }));
		table.setBounds(44, 69, 438, 147);
		panel2.add(table);
	}

	// metodo para guardar en un txt como base de datos
	public static void guardar() {
		// creacion de un objeto tipo file
		File archivo = new File("Personas.txt");
		PrintWriter Escribe;
		// if en caso de que el txt no exista
		if (!archivo.exists()) {
			// manejo de errores
			try {
				archivo.createNewFile();

			} catch (Exception e1) {

			}

		}
		// manejo de errores
		// y para poder escribir dentro del txt lo que este
		// en el arraylist "list"
		try {
			Persona aux;
			Escribe = new PrintWriter(archivo, "utf-8");
			for (int i = 0; i < list.size(); i++) {
				aux = list.get(i);
				aux.guardar(Escribe);
			}
			Escribe.close();
		} catch (Exception e1) {
		}
	};

	// metodo para cargar la base de datos(txt)
	private void cargar(JPanel panel2) {
		// se crea un objeto tipo File
		File archivo = new File("Personas.txt");
		FileReader leer;
		BufferedReader almacen;
		Persona persona = new Persona();
		Persona aux = new Persona();
		try {
			leer = new FileReader(archivo);
			almacen = new BufferedReader(leer);
			persona = aux.cargar(almacen);
			// si persona no existe se agrega un objeto tipo persona
			// con los parametros de almacem
			while (persona != null) {
				list.add(persona);
				persona = aux.cargar(almacen);
			}
			almacen.close();
			leer.close();

		} catch (Exception e1) {

		}
		// metodo para refrescar el jtable
		actualizar(panel2);
	}
  
  public static int promocion() {
		  
		  String botones[] = {"Reina","Torre","Alfin","Caballo"};
		  int variable = JOptionPane.showOptionDialog(null, "Peon Promovido", "Promocion",0 , 0, null, botones,null);

      return variable;
		  
	  }


}
/*
public static void jaquemate() {
		
		JOptionPane.showMessageDialog(null, "JAQUE MATE");
	}
public static void ahogado() {
		
		JOptionPane.showMessageDialog(null, "EL REY QUEDO AHOGADO");
	}
  */