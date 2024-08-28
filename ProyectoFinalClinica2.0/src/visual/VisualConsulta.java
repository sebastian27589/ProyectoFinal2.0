package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import exception.ValidarCampo;
import logico.Cita;
import logico.Clinica;
import logico.Paciente;
import logico.PanelSimulacionAnim;
import logico.Persona;
import logico.RoundedGlowPanel;
import logico.RoundedPanel;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import keeptoo.KGradientPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VisualConsulta extends PanelSimulacionAnim {

	private static DefaultTableModel modelEnfermedad;
	private static DefaultTableModel modelAnalisis;
	private static DefaultTableModel modelVacuna;
	private static DefaultTableModel modelSintoma;
	private static ArrayList<Integer> selectedSintomas = new ArrayList<Integer>();
	private static ArrayList<Integer> selectedAnalisis = new ArrayList<Integer>();
	private static ArrayList<Integer> selectedEnfermedades = new ArrayList<Integer>();
	private static ArrayList<Integer> selectedVacunas = new ArrayList<Integer>();
	private Dimension dim;
	private JTable tableSintoma;
	private JTable tableEnfermedad;
	private JTable tableAnalisis;
	private JTable tableVacuna;
	private static Object[] row;
	private Persona personaConsulta = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private static JDateChooser dateChooserConsulta;
	private float peso, altura;
	private Date fechaNacimiento;
	private static JTextField txtCodePaciente;
	private static JTextField txtPNombre;
	private static JTextField txtCedula;
	private static JTextField txtTelefono;
	private static JDateChooser dateChooserNacim;
	private static JRadioButton rdbtnMasculino;
	private static JRadioButton rdbtnFemenino;
	private static JTextArea txtareaDireccion;
	private Paciente paciente = null;
	private char sexoPaciente;
	private JComboBox cbxTipoSangre;
	private JTextArea txtareaAlergia;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JPanel panelDatosPersona;
	private static JTextField txtSNombre;
	private static JTextField txtPApellido;
	private JLabel lblPApellido;
	private static JTextField txtSApellido;
	private JLabel lblSApellido;
	private JLabel lblCdigo;
	private JLabel lblCedula;
	private JLabel lblTelefono;
	private JLabel lblAltura;
	private JLabel lblFDeNac;
	private JPanel panel_1;
	private RoundedGlowPanel roundedGlowPanelCodePaciente;
	private RoundedGlowPanel roundedGlowPanelPApellido;
	private RoundedGlowPanel roundedGlowPanelSApellido;
	private RoundedGlowPanel roundedGlowPanelSNombre;
	private RoundedGlowPanel roundedGlowPanelPNombre;
	private RoundedGlowPanel roundedGlowPanelCedula;
	private RoundedGlowPanel roundedGlowPanelTelefono;
	private RoundedGlowPanel roundedGlowPanelFNacimiento;
	private RoundedGlowPanel roundedGlowPanelAltura;
	private RoundedGlowPanel roundedGlowPanelTSangre;
	private RoundedGlowPanel roundedGlowPanelPeso;
	private JLabel lblDireccion;
	private JLabel lblAlergia;
	private RoundedGlowPanel roundedGlowPanelDireccion;
	private JPanel panelTablaSintoma;
	private RoundedGlowPanel roundedGlowHistorial;
	private JLabel lblHistorial;
	private RoundedGlowPanel roundedGlowPanelBuscarPaciente;
	private JLabel lblBuscar;
	private JTextField txtBuscarPaciente;
	private JLabel lblRealizar;
	private JSpinner spnAltura;
	private JSpinner spnPeso;
	private RoundedGlowPanel roundedGlowPanelDiagnostico;
	private JPanel panelTablaEnfermedad;
	private JScrollPane scrollPane_1;
	private JPanel panelTablaAnalisis;
	private JScrollPane scrollPane_2;
	private JPanel panelTablaVacuna;
	private JScrollPane scrollPane_3;
	private JLabel lblNewLabel;
	private JLabel lblDiagnstico;
	private RoundedGlowPanel roundedGlowPanelRealizar;
	private JTextArea textAreaDiagnostico;

	/**
	 * Create the dialog.
	 */
	public VisualConsulta(Connection conexion, Cita personaCita) 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		Object[] headerSintoma = {"ID_Sintoma", "Nombre_Sintoma", "Elegir"};
		Object[] headerAnalisis = {"ID_Analisis", "Nombre", "Elegir"};
		Object[] headerEnfermedad = {"ID_Enfermedad", "Nombre", "Vigilancia", "Peligrosidad", "Elegir"};
		Object[] headerVacuna = {"ID_Vacuna", "ID_Enfermedad", "Nombre_Vacuna", "Laboratorio", "Elegir"};
		
		modelEnfermedad = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 4) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 4) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		modelEnfermedad.setColumnIdentifiers(headerEnfermedad);
		
		modelAnalisis = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 2) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 2) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		modelAnalisis.setColumnIdentifiers(headerAnalisis);
		
		modelSintoma = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 2) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 2) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		modelSintoma.setColumnIdentifiers(headerSintoma);
		
		modelVacuna = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 4) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 4) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		modelVacuna.setColumnIdentifiers(headerVacuna);
		
		setBounds(100, 100, 1444, 993);
		setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		setBackground(new Color(248, 248, 255));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelVolver = new RoundedGlowPanel();
		roundedGlowPanelVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desaparecer(20);
				VentanaPrincipal.mostrarPanelFondo();
			}
		});
		roundedGlowPanelVolver.setBounds((int)(64*widthRatio),(int)(11*heightRatio), (int)(57*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelVolver);
		roundedGlowPanelVolver.setLayout(null);
		roundedGlowPanelVolver.setRoundTopLeft(60);
		roundedGlowPanelVolver.setGlowColor(Color.CYAN);
		roundedGlowPanelVolver.setGlowAlpha(170);
		roundedGlowPanelVolver.setForeground(Color.WHITE);
		roundedGlowPanelVolver.setBorder(null);
		roundedGlowPanelVolver.setBackground(Color.WHITE);
		
		JLabel lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(VisualConsulta.class.getResource("/Imagenes/icons8-left-arrow-25.png")));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(100, 149, 237));
		lblVolver.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblVolver.setEnabled(false);
		lblVolver.setBackground(Color.WHITE);
		lblVolver.setBounds(0, 0, (int)(57*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelVolver.add(lblVolver);
		
		panelTablaSintoma = new JPanel();
		panelTablaSintoma.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaSintoma.setBackground(new Color(255, 255, 255));
		panelTablaSintoma.setBounds((int)(780*widthRatio),(int)(13*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		add(panelTablaSintoma);
		panelTablaSintoma.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(tableSintoma);
		panelTablaSintoma.add(scrollPane);
		
		tableSintoma = new JTable(modelSintoma);
		tableSintoma.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSintoma.getTableHeader().setResizingAllowed(false);
		tableSintoma.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tableSintoma.getColumnCount(); index++) {
			
			if (index != 2) {
				
				tableSintoma.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tableSintoma.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableSintoma.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableSintoma.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableSintoma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableSintoma.getSelectedRow(), colIndex = tableSintoma.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					if (colIndex == 2) {
						if(tableSintoma.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
							selectedSintomas.add(Integer.valueOf((tableSintoma.getValueAt(rowIndex, colIndex-2).toString())));
						} else {
							selectedSintomas.remove(Integer.valueOf((tableSintoma.getValueAt(rowIndex, colIndex-2).toString())));
						}
					}
				}
				
			}
		});
		tableSintoma.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableSintoma.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableSintoma);
		{
			RoundedPanel panelDatosPersona = new RoundedPanel();
			panelDatosPersona.setRoundTopRight(35);
			panelDatosPersona.setRoundTopLeft(35);
			panelDatosPersona.setRoundBottomRight(35);
			panelDatosPersona.setRoundBottomLeft(35);
			panelDatosPersona.setBounds((int)(66*widthRatio),(int)(13*heightRatio), (int)(693*widthRatio),(int)(839*heightRatio));
			add(panelDatosPersona);
			panelDatosPersona.setBackground(new Color(240, 240, 240));
			panelDatosPersona.setLayout(null);
			
			rdbtnMasculino = new JRadioButton("Hombre");
			rdbtnMasculino.setEnabled(false);
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculino.setBounds((int)(251*widthRatio),(int)(664*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			
			rdbtnFemenino = new JRadioButton("Mujer");
			rdbtnFemenino.setEnabled(false);
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
				}
			});
			rdbtnFemenino.setBounds((int)(356*widthRatio),(int)(664*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			
			roundedGlowPanelCodePaciente = new RoundedGlowPanel();
			roundedGlowPanelCodePaciente.setBounds((int)(174*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(61*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCodePaciente);
			roundedGlowPanelCodePaciente.setGlowAlpha(170);
			roundedGlowPanelCodePaciente.setForeground(new Color(255, 255, 255));
			roundedGlowPanelCodePaciente.setBorder(null);
			roundedGlowPanelCodePaciente.setGlowColor(new Color(0, 255, 255));
			roundedGlowPanelCodePaciente.setRoundTopRight(60);
			roundedGlowPanelCodePaciente.setRoundTopLeft(60);
			roundedGlowPanelCodePaciente.setRoundBottomRight(60);
			roundedGlowPanelCodePaciente.setRoundBottomLeft(60);
			roundedGlowPanelCodePaciente.setBackground(new Color(255, 255, 255));
			roundedGlowPanelCodePaciente.setLayout(null);
			
			lblCdigo = new JLabel("C\u00F3digo:");
			lblCdigo.setBounds((int)(12*widthRatio),(int)(20*heightRatio), (int)(62*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelCodePaciente.add(lblCdigo);
			lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCdigo.setForeground(new Color(65, 105, 225));
			lblCdigo.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCdigo.setBackground(Color.WHITE);
			
			txtCodePaciente = new JTextField();
			txtCodePaciente.setOpaque(false);
			txtCodePaciente.setBounds((int)(78*widthRatio),(int)(20*heightRatio), (int)(62*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelCodePaciente.add(txtCodePaciente);
			txtCodePaciente.setBackground(new Color(255, 255, 255));
			txtCodePaciente.setBorder(null);
			txtCodePaciente.setEditable(false);
			txtCodePaciente.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodePaciente.setColumns(10);
			txtCodePaciente.setText("CONS-"+Clinica.getInstance().getGeneradorCodeConsMed());
			
			JLabel lblRegistroDePersonas = new JLabel("Consulta");
			lblRegistroDePersonas.setOpaque(true);
			lblRegistroDePersonas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDePersonas.setForeground(new Color(0, 0, 0));
			lblRegistroDePersonas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDePersonas.setBackground(new Color(240, 240, 240));
			lblRegistroDePersonas.setBounds((int)(248*widthRatio),(int)(20*heightRatio), (int)(201*widthRatio), (int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDePersonas);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setEditable(true);
			cbxTipoSangre.setBounds((int)(511*widthRatio),(int)(355*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(cbxTipoSangre);
			cbxTipoSangre.setBorder(null);
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "A+", "A", "B+", "B", "AB+", "AB", "O+", "O"}));
			cbxTipoSangre.setSelectedIndex(0);
			cbxTipoSangre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setEnabled(false);
			dateChooserNacim.setBounds((int)(511*widthRatio),(int)(219*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(dateChooserNacim);
			dateChooserNacim.setBackground(new Color(255, 255, 255));
			dateChooserNacim.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserNacim.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserNacim.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			roundedGlowPanelPNombre = new RoundedGlowPanel();
			roundedGlowPanelPNombre.setLayout(null);
			roundedGlowPanelPNombre.setRoundTopRight(60);
			roundedGlowPanelPNombre.setRoundTopLeft(60);
			roundedGlowPanelPNombre.setRoundBottomRight(60);
			roundedGlowPanelPNombre.setRoundBottomLeft(60);
			roundedGlowPanelPNombre.setGlowColor(Color.CYAN);
			roundedGlowPanelPNombre.setGlowAlpha(170);
			roundedGlowPanelPNombre.setForeground(Color.WHITE);
			roundedGlowPanelPNombre.setBorder(null);
			roundedGlowPanelPNombre.setBackground(Color.WHITE);
			roundedGlowPanelPNombre.setBounds((int)(67*widthRatio),(int)(147*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPNombre);
			{
				JLabel lblPNombre = new JLabel("Primer Nombre:");
				lblPNombre.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(119*widthRatio),(int)(22*heightRatio));
				roundedGlowPanelPNombre.add(lblPNombre);
				lblPNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblPNombre.setForeground(new Color(65, 105, 225));
				lblPNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
				lblPNombre.setBackground(Color.WHITE);
			}
			
			txtPNombre = new JTextField();
			txtPNombre.setEnabled(false);
			txtPNombre.setEditable(false);
			txtPNombre.setOpaque(false);
			txtPNombre.setBounds((int)(134*widthRatio),(int)(5*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelPNombre.add(txtPNombre);
			txtPNombre.setBorder(null);
			txtPNombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPNombre.setColumns(10);
			
			roundedGlowPanelSNombre = new RoundedGlowPanel();
			roundedGlowPanelSNombre.setLayout(null);
			roundedGlowPanelSNombre.setRoundTopRight(60);
			roundedGlowPanelSNombre.setRoundTopLeft(60);
			roundedGlowPanelSNombre.setRoundBottomRight(60);
			roundedGlowPanelSNombre.setRoundBottomLeft(60);
			roundedGlowPanelSNombre.setGlowColor(Color.CYAN);
			roundedGlowPanelSNombre.setGlowAlpha(170);
			roundedGlowPanelSNombre.setForeground(Color.WHITE);
			roundedGlowPanelSNombre.setBorder(null);
			roundedGlowPanelSNombre.setBackground(Color.WHITE);
			roundedGlowPanelSNombre.setBounds((int)(67*widthRatio),(int)(215*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSNombre);
			
			txtSNombre = new JTextField();
			txtSNombre.setEnabled(false);
			txtSNombre.setEditable(false);
			txtSNombre.setOpaque(false);
			txtSNombre.setBounds((int)(144*widthRatio),(int)(5*heightRatio), (int)(101*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelSNombre.add(txtSNombre);
			txtSNombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSNombre.setColumns(10);
			txtSNombre.setBorder(null);
			
			JLabel lblSNombre = new JLabel("Segundo Nombre:");
			lblSNombre.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelSNombre.add(lblSNombre);
			lblSNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblSNombre.setForeground(new Color(65, 105, 225));
			lblSNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSNombre.setBackground(Color.WHITE);
			
			roundedGlowPanelPApellido = new RoundedGlowPanel();
			roundedGlowPanelPApellido.setLayout(null);
			roundedGlowPanelPApellido.setRoundTopRight(60);
			roundedGlowPanelPApellido.setRoundTopLeft(60);
			roundedGlowPanelPApellido.setRoundBottomRight(60);
			roundedGlowPanelPApellido.setRoundBottomLeft(60);
			roundedGlowPanelPApellido.setGlowColor(Color.CYAN);
			roundedGlowPanelPApellido.setGlowAlpha(170);
			roundedGlowPanelPApellido.setForeground(Color.WHITE);
			roundedGlowPanelPApellido.setBorder(null);
			roundedGlowPanelPApellido.setBackground(Color.WHITE);
			roundedGlowPanelPApellido.setBounds((int)(67*widthRatio),(int)(283*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPApellido);
			
			lblPApellido = new JLabel("Primer Apellido:");
			lblPApellido.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(119*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelPApellido.add(lblPApellido);
			lblPApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblPApellido.setForeground(new Color(65, 105, 225));
			lblPApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPApellido.setBackground(Color.WHITE);
			
			txtPApellido = new JTextField();
			txtPApellido.setEnabled(false);
			txtPApellido.setEditable(false);
			txtPApellido.setOpaque(false);
			txtPApellido.setBounds((int)(134*widthRatio),(int)(5*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelPApellido.add(txtPApellido);
			txtPApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPApellido.setColumns(10);
			txtPApellido.setBorder(null);
			
			roundedGlowPanelSApellido = new RoundedGlowPanel();
			roundedGlowPanelSApellido.setLayout(null);
			roundedGlowPanelSApellido.setRoundTopRight(60);
			roundedGlowPanelSApellido.setRoundTopLeft(60);
			roundedGlowPanelSApellido.setRoundBottomRight(60);
			roundedGlowPanelSApellido.setRoundBottomLeft(60);
			roundedGlowPanelSApellido.setGlowColor(Color.CYAN);
			roundedGlowPanelSApellido.setGlowAlpha(170);
			roundedGlowPanelSApellido.setForeground(Color.WHITE);
			roundedGlowPanelSApellido.setBorder(null);
			roundedGlowPanelSApellido.setBackground(Color.WHITE);
			roundedGlowPanelSApellido.setBounds((int)(67*widthRatio),(int)(351*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSApellido);
			
			lblSApellido = new JLabel("Segundo Apellido:");
			lblSApellido.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelSApellido.add(lblSApellido);
			lblSApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblSApellido.setForeground(new Color(65, 105, 225));
			lblSApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSApellido.setBackground(Color.WHITE);
			
			txtSApellido = new JTextField();
			txtSApellido.setEditable(false);
			txtSApellido.setEnabled(false);
			txtSApellido.setOpaque(false);
			txtSApellido.setBounds((int)(144*widthRatio),(int)(5*heightRatio), (int)(101*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelSApellido.add(txtSApellido);
			txtSApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSApellido.setColumns(10);
			txtSApellido.setBorder(null);
			
			roundedGlowPanelCedula = new RoundedGlowPanel();
			roundedGlowPanelCedula.setLayout(null);
			roundedGlowPanelCedula.setRoundTopRight(60);
			roundedGlowPanelCedula.setRoundTopLeft(60);
			roundedGlowPanelCedula.setRoundBottomRight(60);
			roundedGlowPanelCedula.setRoundBottomLeft(60);
			roundedGlowPanelCedula.setGlowColor(Color.CYAN);
			roundedGlowPanelCedula.setGlowAlpha(170);
			roundedGlowPanelCedula.setForeground(Color.WHITE);
			roundedGlowPanelCedula.setBorder(null);
			roundedGlowPanelCedula.setBackground(Color.WHITE);
			roundedGlowPanelCedula.setBounds((int)(355*widthRatio),(int)(79*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCedula);
			
			lblCedula = new JLabel("C\u00E9dula:");
			lblCedula.setBounds((int)(12*widthRatio),(int)(20*heightRatio), (int)(64*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelCedula.add(lblCedula);
			lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCedula.setForeground(new Color(65, 105, 225));
			lblCedula.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCedula.setBackground(Color.WHITE);
			
			txtCedula = new JTextField();
			txtCedula.setEnabled(false);
			txtCedula.setOpaque(false);
			txtCedula.setBounds((int)(76*widthRatio),(int)(8*heightRatio), (int)(173*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelCedula.add(txtCedula);
			txtCedula.setBorder(null);
			txtCedula.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtCedula.setColumns(10);
			
			roundedGlowPanelTelefono = new RoundedGlowPanel();
			roundedGlowPanelTelefono.setLayout(null);
			roundedGlowPanelTelefono.setRoundTopRight(60);
			roundedGlowPanelTelefono.setRoundTopLeft(60);
			roundedGlowPanelTelefono.setRoundBottomRight(60);
			roundedGlowPanelTelefono.setRoundBottomLeft(60);
			roundedGlowPanelTelefono.setGlowColor(Color.CYAN);
			roundedGlowPanelTelefono.setGlowAlpha(170);
			roundedGlowPanelTelefono.setForeground(Color.WHITE);
			roundedGlowPanelTelefono.setBorder(null);
			roundedGlowPanelTelefono.setBackground(Color.WHITE);
			roundedGlowPanelTelefono.setBounds((int)(355*widthRatio),(int)(147*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelTelefono);
			
			lblTelefono = new JLabel("Tel\u00E9fono:");
			lblTelefono.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelTelefono.add(lblTelefono);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(new Color(65, 105, 225));
			lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTelefono.setBackground(Color.WHITE);
			
			txtTelefono = new JTextField();
			txtTelefono.setEnabled(false);
			txtTelefono.setOpaque(false);
			txtTelefono.setBounds((int)(85*widthRatio),(int)(5*heightRatio), (int)(160*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelTelefono.add(txtTelefono);
			txtTelefono.setBorder(null);
			txtTelefono.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtTelefono.setColumns(10);
			
			roundedGlowPanelFNacimiento = new RoundedGlowPanel();
			roundedGlowPanelFNacimiento.setLayout(null);
			roundedGlowPanelFNacimiento.setRoundTopRight(60);
			roundedGlowPanelFNacimiento.setRoundTopLeft(60);
			roundedGlowPanelFNacimiento.setRoundBottomRight(60);
			roundedGlowPanelFNacimiento.setRoundBottomLeft(60);
			roundedGlowPanelFNacimiento.setGlowColor(Color.CYAN);
			roundedGlowPanelFNacimiento.setGlowAlpha(170);
			roundedGlowPanelFNacimiento.setForeground(Color.WHITE);
			roundedGlowPanelFNacimiento.setBorder(null);
			roundedGlowPanelFNacimiento.setBackground(Color.WHITE);
			roundedGlowPanelFNacimiento.setBounds((int)(355*widthRatio),(int)(215*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelFNacimiento);
			
			lblFDeNac = new JLabel("F. Nacimiento");
			lblFDeNac.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(112*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelFNacimiento.add(lblFDeNac);
			lblFDeNac.setHorizontalAlignment(SwingConstants.CENTER);
			lblFDeNac.setForeground(new Color(65, 105, 225));
			lblFDeNac.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblFDeNac.setBackground(Color.WHITE);
			
			roundedGlowPanelAltura = new RoundedGlowPanel();
			roundedGlowPanelAltura.setLayout(null);
			roundedGlowPanelAltura.setRoundTopRight(60);
			roundedGlowPanelAltura.setRoundTopLeft(60);
			roundedGlowPanelAltura.setRoundBottomRight(60);
			roundedGlowPanelAltura.setRoundBottomLeft(60);
			roundedGlowPanelAltura.setGlowColor(Color.CYAN);
			roundedGlowPanelAltura.setGlowAlpha(170);
			roundedGlowPanelAltura.setForeground(Color.WHITE);
			roundedGlowPanelAltura.setBorder(null);
			roundedGlowPanelAltura.setBackground(Color.WHITE);
			roundedGlowPanelAltura.setBounds((int)(355*widthRatio),(int)(283*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelAltura);
			
			lblAltura = new JLabel("Altura:");
			lblAltura.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(58*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelAltura.add(lblAltura);
			lblAltura.setHorizontalAlignment(SwingConstants.CENTER);
			lblAltura.setForeground(new Color(65, 105, 225));
			lblAltura.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblAltura.setBackground(Color.WHITE);
			
			spnAltura = new JSpinner();
			spnAltura.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					validarCampos();
				}
			});
			spnAltura.setBackground(Color.WHITE);
			spnAltura.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			spnAltura.setForeground(Color.WHITE);
			spnAltura.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnAltura.setOpaque(false);
			spnAltura.setBounds((int)(75*widthRatio),(int)(5*heightRatio), (int)(40*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelAltura.add(spnAltura);
			spnAltura.setBorder(null);
			
			roundedGlowPanelPeso = new RoundedGlowPanel();
			roundedGlowPanelPeso.setLayout(null);
			roundedGlowPanelPeso.setRoundTopRight(60);
			roundedGlowPanelPeso.setRoundTopLeft(60);
			roundedGlowPanelPeso.setRoundBottomRight(60);
			roundedGlowPanelPeso.setRoundBottomLeft(60);
			roundedGlowPanelPeso.setGlowColor(Color.CYAN);
			roundedGlowPanelPeso.setGlowAlpha(170);
			roundedGlowPanelPeso.setForeground(Color.WHITE);
			roundedGlowPanelPeso.setBorder(null);
			roundedGlowPanelPeso.setBackground(Color.WHITE);
			roundedGlowPanelPeso.setBounds((int)(497*widthRatio),(int)(283*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPeso);
			
			JLabel lblPeso = new JLabel("Peso:");
			lblPeso.setBounds((int)(12*widthRatio),(int)(16*heightRatio), (int)(58*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelPeso.add(lblPeso);
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setForeground(new Color(65, 105, 225));
			lblPeso.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPeso.setBackground(Color.WHITE);
			
			spnPeso = new JSpinner();
			spnPeso.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					validarCampos();
				}
			});
			spnPeso.setBackground(Color.WHITE);
			spnPeso.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			spnPeso.setForeground(Color.WHITE);
			spnPeso.setOpaque(false);
			spnPeso.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnPeso.setBounds((int)(75*widthRatio),(int)(5*heightRatio), (int)(40*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelPeso.add(spnPeso);
			spnPeso.setBorder(null);
			
			roundedGlowPanelTSangre = new RoundedGlowPanel();
			roundedGlowPanelTSangre.setLayout(null);
			roundedGlowPanelTSangre.setRoundTopRight(60);
			roundedGlowPanelTSangre.setRoundTopLeft(60);
			roundedGlowPanelTSangre.setRoundBottomRight(60);
			roundedGlowPanelTSangre.setRoundBottomLeft(60);
			roundedGlowPanelTSangre.setGlowColor(Color.CYAN);
			roundedGlowPanelTSangre.setGlowAlpha(170);
			roundedGlowPanelTSangre.setForeground(Color.WHITE);
			roundedGlowPanelTSangre.setBorder(null);
			roundedGlowPanelTSangre.setBackground(Color.WHITE);
			roundedGlowPanelTSangre.setBounds((int)(355*widthRatio),(int)(351*heightRatio), (int)(135*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelTSangre);
			
			JLabel lblTSangre = new JLabel("T. Sangre");
			lblTSangre.setBounds((int)(23*widthRatio),(int)(16*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelTSangre.add(lblTSangre);
			lblTSangre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTSangre.setForeground(new Color(65, 105, 225));
			lblTSangre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTSangre.setBackground(Color.WHITE);
			
			roundedGlowPanelDireccion = new RoundedGlowPanel();
			roundedGlowPanelDireccion.setLayout(null);
			roundedGlowPanelDireccion.setRoundTopRight(60);
			roundedGlowPanelDireccion.setRoundTopLeft(60);
			roundedGlowPanelDireccion.setRoundBottomRight(60);
			roundedGlowPanelDireccion.setRoundBottomLeft(60);
			roundedGlowPanelDireccion.setGlowColor(Color.CYAN);
			roundedGlowPanelDireccion.setGlowAlpha(170);
			roundedGlowPanelDireccion.setForeground(Color.WHITE);
			roundedGlowPanelDireccion.setBorder(null);
			roundedGlowPanelDireccion.setBackground(Color.WHITE);
			roundedGlowPanelDireccion.setBounds((int)(67*widthRatio),(int)(419*heightRatio), (int)(562*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanelDireccion);
			
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setBounds((int)(12*widthRatio),(int)(22*heightRatio), (int)(79*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelDireccion.add(lblDireccion);
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setForeground(new Color(65, 105, 225));
			lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblDireccion.setBackground(Color.WHITE);
			
			txtareaDireccion = new JTextArea();
			txtareaDireccion.setEnabled(false);
			txtareaDireccion.setEditable(false);
			txtareaDireccion.setOpaque(false);
			txtareaDireccion.setBounds((int)(98*widthRatio),(int)(6*heightRatio), (int)(441*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelDireccion.add(txtareaDireccion);
			txtareaDireccion.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtareaDireccion.setLineWrap(true);
			txtareaDireccion.setWrapStyleWord(true);
			
			RoundedGlowPanel roundedGlowPanelAlergia = new RoundedGlowPanel();
			roundedGlowPanelAlergia.setLayout(null);
			roundedGlowPanelAlergia.setRoundTopRight(60);
			roundedGlowPanelAlergia.setRoundTopLeft(60);
			roundedGlowPanelAlergia.setRoundBottomRight(60);
			roundedGlowPanelAlergia.setRoundBottomLeft(60);
			roundedGlowPanelAlergia.setGlowColor(Color.CYAN);
			roundedGlowPanelAlergia.setGlowAlpha(170);
			roundedGlowPanelAlergia.setForeground(Color.WHITE);
			roundedGlowPanelAlergia.setBorder(null);
			roundedGlowPanelAlergia.setBackground(Color.WHITE);
			roundedGlowPanelAlergia.setBounds((int)(67*widthRatio),(int)(500*heightRatio), (int)(562*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanelAlergia);
			
			lblAlergia = new JLabel("Alergia:");
			lblAlergia.setBounds((int)(12*widthRatio),(int)(22*heightRatio), (int)(67*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelAlergia.add(lblAlergia);
			lblAlergia.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlergia.setForeground(new Color(65, 105, 225));
			lblAlergia.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblAlergia.setBackground(Color.WHITE);
			
			txtareaAlergia = new JTextArea();
			txtareaAlergia.setOpaque(false);
			txtareaAlergia.setBounds((int)(79*widthRatio),(int)(6*heightRatio), (int)(460*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelAlergia.add(txtareaAlergia);
			txtareaAlergia.setWrapStyleWord(true);
			txtareaAlergia.setLineWrap(true);
			txtareaAlergia.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			roundedGlowPanelDiagnostico = new RoundedGlowPanel();
			roundedGlowPanelDiagnostico.setLayout(null);
			roundedGlowPanelDiagnostico.setRoundTopRight(60);
			roundedGlowPanelDiagnostico.setRoundTopLeft(60);
			roundedGlowPanelDiagnostico.setRoundBottomRight(60);
			roundedGlowPanelDiagnostico.setRoundBottomLeft(60);
			roundedGlowPanelDiagnostico.setGlowColor(Color.CYAN);
			roundedGlowPanelDiagnostico.setGlowAlpha(170);
			roundedGlowPanelDiagnostico.setForeground(Color.WHITE);
			roundedGlowPanelDiagnostico.setBorder(null);
			roundedGlowPanelDiagnostico.setBackground(Color.WHITE);
			roundedGlowPanelDiagnostico.setBounds((int)(67*widthRatio),(int)(581*heightRatio), (int)(562*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanelDiagnostico);
			
			lblDiagnstico = new JLabel("Diagn\u00F3stico:");
			lblDiagnstico.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiagnstico.setForeground(new Color(65, 105, 225));
			lblDiagnstico.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblDiagnstico.setBackground(Color.WHITE);
			lblDiagnstico.setBounds((int)(14*widthRatio),(int)(22*heightRatio), (int)(85*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelDiagnostico.add(lblDiagnstico);
			
			textAreaDiagnostico = new JTextArea();
			textAreaDiagnostico.setWrapStyleWord(true);
			textAreaDiagnostico.setOpaque(false);
			textAreaDiagnostico.setLineWrap(true);
			textAreaDiagnostico.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			textAreaDiagnostico.setBounds((int)(107*widthRatio),(int)(6*heightRatio), (int)(426*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelDiagnostico.add(textAreaDiagnostico);
			
			roundedGlowPanelBuscarPaciente = new RoundedGlowPanel();
			roundedGlowPanelBuscarPaciente.setBounds((int)(67*widthRatio),(int)(761*heightRatio), (int)(277*widthRatio),(int)(49*heightRatio));
			panelDatosPersona.add(roundedGlowPanelBuscarPaciente);
			roundedGlowPanelBuscarPaciente.setLayout(null);
			roundedGlowPanelBuscarPaciente.setRoundTopRight(60);
			roundedGlowPanelBuscarPaciente.setRoundTopLeft(60);
			roundedGlowPanelBuscarPaciente.setRoundBottomRight(60);
			roundedGlowPanelBuscarPaciente.setRoundBottomLeft(60);
			roundedGlowPanelBuscarPaciente.setGlowColor(Color.CYAN);
			roundedGlowPanelBuscarPaciente.setGlowAlpha(170);
			roundedGlowPanelBuscarPaciente.setForeground(Color.WHITE);
			roundedGlowPanelBuscarPaciente.setBorder(null);
			roundedGlowPanelBuscarPaciente.setBackground(Color.WHITE);
			
			lblBuscar = new JLabel("Buscar:");
			lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
			lblBuscar.setForeground(new Color(100, 149, 237));
			lblBuscar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblBuscar.setBackground(Color.WHITE);
			lblBuscar.setBounds(0, 0, (int)(80*widthRatio),(int)(49*heightRatio));
			roundedGlowPanelBuscarPaciente.add(lblBuscar);
			
			txtBuscarPaciente = new JTextField();
			txtBuscarPaciente.setOpaque(false);
			txtBuscarPaciente.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtBuscarPaciente.setColumns(10);
			txtBuscarPaciente.setBorder(null);
			txtBuscarPaciente.setBounds((int)(72*widthRatio),(int)(0*heightRatio), (int)(175*widthRatio),(int)(49*heightRatio));
			txtBuscarPaciente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					
					DefaultTableModel searchModel1 = (DefaultTableModel) tableSintoma.getModel();
					TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
					tableSintoma.setRowSorter(searchModel2);
					searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
					
					DefaultTableModel searchModel3 = (DefaultTableModel) tableAnalisis.getModel();
					TableRowSorter<DefaultTableModel> searchModel4 = new TableRowSorter<DefaultTableModel>(searchModel3);
					tableAnalisis.setRowSorter(searchModel4);
					searchModel4.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
					
					DefaultTableModel searchModel5 = (DefaultTableModel) tableEnfermedad.getModel();
					TableRowSorter<DefaultTableModel> searchModel6 = new TableRowSorter<DefaultTableModel>(searchModel5);
					tableEnfermedad.setRowSorter(searchModel6);
					searchModel6.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
					
					DefaultTableModel searchModel7 = (DefaultTableModel) tableVacuna.getModel();
					TableRowSorter<DefaultTableModel> searchModel8 = new TableRowSorter<DefaultTableModel>(searchModel7);
					tableVacuna.setRowSorter(searchModel8);
					searchModel8.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				}
			});
			roundedGlowPanelBuscarPaciente.add(txtBuscarPaciente);
			txtBuscarPaciente.setColumns(10);	
			
			roundedGlowPanelRealizar = new RoundedGlowPanel();
			roundedGlowPanelRealizar.setBounds((int)(355*widthRatio),(int)(761*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
			panelDatosPersona.add(roundedGlowPanelRealizar);
			roundedGlowPanelRealizar.setLayout(null);
			roundedGlowPanelRealizar.setRoundTopRight(60);
			roundedGlowPanelRealizar.setRoundTopLeft(60);
			roundedGlowPanelRealizar.setRoundBottomRight(60);
			roundedGlowPanelRealizar.setRoundBottomLeft(60);
			roundedGlowPanelRealizar.setGlowColor(Color.CYAN);
			roundedGlowPanelRealizar.setGlowAlpha(170);
			roundedGlowPanelRealizar.setForeground(Color.WHITE);
			roundedGlowPanelRealizar.setBorder(null);
			roundedGlowPanelRealizar.setBackground(new Color(240,240,240));
			
			lblRealizar = new JLabel("Realizar");
			lblRealizar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						
					boolean res = Clinica.getInstance().insertaConsulta(conexion, txtCedula.getText(), Integer.valueOf((spnAltura.getValue().toString())), Integer.valueOf((spnPeso.getValue().toString())), txtareaAlergia.getText(), Clinica.getInstance().getIdUsuarioLogueado(), selectedSintomas, selectedAnalisis, selectedEnfermedades, selectedVacunas, cbxTipoSangre.getSelectedItem().toString(), textAreaDiagnostico.getText());
					
					if(res) {
						JOptionPane.showMessageDialog(null, "Registrada con éxito", "Registrar Consulta", JOptionPane.INFORMATION_MESSAGE);
						
						loadSintomas(conexion);
					    loadEnfermedad(conexion);
					    loadAnalisis(conexion);
					    loadVacunas(conexion);   
					}
					else {
						JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar la Consulta!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			lblRealizar.setEnabled(false);
			lblRealizar.setHorizontalAlignment(SwingConstants.CENTER);
			lblRealizar.setForeground(new Color(100, 149, 237));
			lblRealizar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblRealizar.setBackground(Color.WHITE);
			lblRealizar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
			roundedGlowPanelRealizar.add(lblRealizar);
			
			roundedGlowHistorial = new RoundedGlowPanel();
			roundedGlowHistorial.setBounds((int)(492*widthRatio),(int)(761*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
			panelDatosPersona.add(roundedGlowHistorial);
			roundedGlowHistorial.setLayout(null);
			roundedGlowHistorial.setRoundTopRight(60);
			roundedGlowHistorial.setRoundTopLeft(60);
			roundedGlowHistorial.setRoundBottomRight(60);
			roundedGlowHistorial.setRoundBottomLeft(60);
			roundedGlowHistorial.setGlowColor(Color.CYAN);
			roundedGlowHistorial.setGlowAlpha(170);
			roundedGlowHistorial.setForeground(Color.WHITE);
			roundedGlowHistorial.setBorder(null);
			roundedGlowHistorial.setBackground(Color.WHITE);
			
			lblHistorial = new JLabel("Historial");
			lblHistorial.setEnabled(false);
			lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
			lblHistorial.setForeground(new Color(100, 149, 237));
			lblHistorial.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblHistorial.setBackground(Color.WHITE);
			lblHistorial.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
			roundedGlowHistorial.add(lblHistorial);
			
			dateChooserConsulta = new JDateChooser();
			dateChooserConsulta.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserConsulta.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(12*widthRatio)));
			dateChooserConsulta.setBounds((int)(536*widthRatio),(int)(36*heightRatio), (int)(94*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(dateChooserConsulta);
		}
		
		panelTablaEnfermedad = new JPanel();
		panelTablaEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaEnfermedad.setBackground(Color.WHITE);
		panelTablaEnfermedad.setBounds((int)(780*widthRatio),(int)(226*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		add(panelTablaEnfermedad);
		panelTablaEnfermedad.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane((Component) null);
		panelTablaEnfermedad.add(scrollPane_1);
		tableEnfermedad = new JTable(modelEnfermedad);
		tableEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableEnfermedad.getSelectedRow(), colIndex = tableEnfermedad.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					if (colIndex == 4) {
						if(tableEnfermedad.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
							selectedEnfermedades.add(Integer.valueOf((tableEnfermedad.getValueAt(rowIndex, colIndex-4).toString())));
						} else {
							selectedEnfermedades.remove(Integer.valueOf((tableEnfermedad.getValueAt(rowIndex, colIndex-4).toString())));
						}
					}
				}
			}
		});
		tableEnfermedad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEnfermedad.getTableHeader().setResizingAllowed(false);
		tableEnfermedad.getTableHeader().setReorderingAllowed(false);
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tableEnfermedad.getColumnCount(); index++) {
			
			if (index != 4) {
				
				tableEnfermedad.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tableEnfermedad.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableEnfermedad.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableEnfermedad);
		
		panelTablaAnalisis = new JPanel();
		panelTablaAnalisis.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaAnalisis.setBackground(Color.WHITE);
		panelTablaAnalisis.setBounds((int)(780*widthRatio),(int)(439*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		add(panelTablaAnalisis);
		panelTablaAnalisis.setLayout(new BorderLayout(0, 0));
		
		scrollPane_2 = new JScrollPane((Component) null);
		panelTablaAnalisis.add(scrollPane_2);
		
		tableAnalisis = new JTable(modelAnalisis);
		tableAnalisis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableAnalisis.getSelectedRow(), colIndex = tableAnalisis.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					if (colIndex == 2) {
						if(tableAnalisis.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
							selectedAnalisis.add(Integer.valueOf((tableAnalisis.getValueAt(rowIndex, colIndex-2).toString())));
						} else {
							selectedAnalisis.remove(Integer.valueOf((tableAnalisis.getValueAt(rowIndex, colIndex-2).toString())));
						}
					}
				}
			}
		});
		tableAnalisis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAnalisis.getTableHeader().setResizingAllowed(false);
		tableAnalisis.getTableHeader().setReorderingAllowed(false);
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tableAnalisis.getColumnCount(); index++) {
			
			if (index != 2) {
				
				tableAnalisis.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tableAnalisis.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableAnalisis.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableAnalisis.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableAnalisis.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(tableAnalisis);
		
		panelTablaVacuna = new JPanel();
		panelTablaVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaVacuna.setBackground(Color.WHITE);
		panelTablaVacuna.setBounds((int)(780*widthRatio),(int)(652*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		add(panelTablaVacuna);
		panelTablaVacuna.setLayout(new BorderLayout(0, 0));
		
		scrollPane_3 = new JScrollPane((Component) null);
		panelTablaVacuna.add(scrollPane_3);
		
		tableVacuna = new JTable(modelVacuna);
		tableVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = tableVacuna.getSelectedRow(), colIndex = tableVacuna.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					if (colIndex == 4) {
						if(tableVacuna.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
							selectedVacunas.add(Integer.valueOf((tableVacuna.getValueAt(rowIndex, colIndex-4).toString())));
						} else {
							selectedVacunas.remove(Integer.valueOf((tableVacuna.getValueAt(rowIndex, colIndex-4).toString())));
						}
					}
				}
			}
		});
		tableVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVacuna.getTableHeader().setResizingAllowed(false);
		tableVacuna.getTableHeader().setReorderingAllowed(false);
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tableVacuna.getColumnCount(); index++) {
			
			if (index != 4) {
				
				tableVacuna.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tableVacuna.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableVacuna.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableVacuna.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableVacuna.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableVacuna.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableVacuna.setFillsViewportHeight(true);
		scrollPane_3.setViewportView(tableVacuna);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VisualConsulta.class.getResource("/Imagenes/6876480.jpg")));
		lblNewLabel.setBounds(0, 0, (int)(1381*widthRatio),(int)(900*heightRatio));
		add(lblNewLabel);
		KeyListener campoListener = new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	        	validarCampos();
	        }
	    };
	    
	    ActionListener cbxListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            validarCampos();
	        }
	    };
	    
	    txtareaAlergia.addKeyListener(campoListener);
	    textAreaDiagnostico.addKeyListener(campoListener);
	    cbxTipoSangre.addActionListener(cbxListener);
	    
	    loadSintomas(conexion);
	    loadEnfermedad(conexion);
	    loadAnalisis(conexion);
	    loadVacunas(conexion);
	}
	
	private void validarCampos() {
		
		if(!txtareaAlergia.getText().isEmpty() && !textAreaDiagnostico.getText().isEmpty() && (cbxTipoSangre.getSelectedIndex() != 0)
			&& (new Integer(spnPeso.getValue().toString()) != 0) && (new Integer(spnAltura.getValue().toString()) != 0) ) {
												
		   roundedGlowPanelRealizar.setEnabled(true);
		   roundedGlowPanelRealizar.setBackground(Color.WHITE);
		   lblRealizar.setEnabled(true);
		   
		} else {
			
		   roundedGlowPanelRealizar.setEnabled(false);
		   roundedGlowPanelRealizar.setBackground(new Color(240, 240, 240));
		   lblRealizar.setEnabled(false);
		   
		}
		
	}
	
	public static void loadSintomas(Connection conexion) {
		
		modelSintoma.setRowCount(0);
		row = new Object[modelSintoma.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Sintoma, Nombre_Sintoma FROM Sintoma;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Sintoma");
            	row[1] = resultSet.getString("Nombre_Sintoma");
            	modelSintoma.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadEnfermedad(Connection conexion) {
		
		modelEnfermedad.setRowCount(0);
		row = new Object[modelEnfermedad.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Enfermedad, ID_Tipo_Enfermedad, Nombre_Enfermedad, Vigilancia, Peligrosidad FROM Enfermedad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Enfermedad");
            	row[1] = resultSet.getString("Nombre_Enfermedad");
            	row[2] = resultSet.getInt("Vigilancia");
            	row[3] = resultSet.getInt("Peligrosidad");
            	modelEnfermedad.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadAnalisis(Connection conexion) {
		
		modelAnalisis.setRowCount(0);
		row = new Object[modelAnalisis.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Analisis, Nombre_Analisis FROM Analisis;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Analisis");
            	row[1] = resultSet.getString("Nombre_Analisis");
            	modelAnalisis.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadVacunas(Connection conexion) {
		
		modelVacuna.setRowCount(0);
		row = new Object[modelVacuna.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Vacuna, ID_Enfermedad, Nombre_Vacuna, Laboratorio FROM Vacuna;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Vacuna");
            	row[1] = resultSet.getInt("ID_Enfermedad");
            	row[2] = resultSet.getString("Nombre_Vacuna");
            	row[3] = resultSet.getString("Laboratorio");
            	modelVacuna.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadPersonaTxt(Persona personaConsulta) {
		txtCedula.setText(personaConsulta.getCedula());
		txtPNombre.setText(personaConsulta.getPrimerNombre());
		txtSNombre.setText(personaConsulta.getSegundoNombre()); 
		txtPApellido.setText(personaConsulta.getPrimerApellido()); 
		txtSApellido.setText(personaConsulta.getSegundoApellido()); 
		txtTelefono.setText(personaConsulta.getTelefono()); 
		txtareaDireccion.setText(personaConsulta.getDireccion());
		dateChooserNacim.setDate(personaConsulta.getFechaDeNacimiento());
		dateChooserConsulta.setDate(new Date());
		
		if(personaConsulta.getSexo() == 'M') {
			rdbtnMasculino.setSelected(true);
		}
		else {
			rdbtnFemenino.setSelected(false);
		}
	}

	public Persona getPersonaConsulta() {
		return personaConsulta;
	}

	public void setPersonaConsulta(Persona selected) {
		this.personaConsulta = selected;
	}
}