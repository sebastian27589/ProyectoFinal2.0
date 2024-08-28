package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import keeptoo.KGradientPanel;
import logico.Clinica;
import logico.Medico;
import logico.PanelSimulacionAnim;
import logico.Persona;
import logico.RoundedGlowPanel;
import logico.RoundedPanel;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.table.TableModel;

public class VisualMedico extends PanelSimulacionAnim {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Integer> selectedEsp = new ArrayList<Integer>();
	private static int indexEsp = 0;
	private int lugar = 0;
	private Persona selected;
	private Medico selectedMedico;
	private Dimension dim;
	private static Object[] row;
	private static DefaultTableModel model;
	private static DefaultTableModel modelMedico;
	private static DefaultTableModel modelEspecialidad;
	private PanelSimulacionAnim panelTablaPersona;
	private JTable tablePersona;
	private JTable tableMedico;
	private static JTable tableEspecialidad;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JTextField txtPNombre;
	private JTextField txtSNombre;
	private JTextField txtPApellido;
	private JTextField txtSApellido;
	private static JTextField txtCodeMedicos;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTextField txtBuscarPaciente;
	private JDateChooser dateChooserNacim;
	private JTextArea txtareaDireccion;
	private RoundedGlowPanel roundedGlowPanelRegistrar;
	private RoundedGlowPanel roundedGlowCitasPendientes;
	private RoundedGlowPanel roundedGlowPanelModificar;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private JLabel lblRegistrar;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblCitas;
	private Color colorDeshabilitado = new Color(240, 240, 240);
	
	
	public VisualMedico(Connection conexion) {
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		Object[] header = {"Doc_Identidad", "P_Nombre", "S_Nombre", "P_Apellido", "S_Apellido"};
		Object[] headerEspecialidad = {"Código", "Nombre_Especialidad", "Elegir"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		modelMedico = new DefaultTableModel();
		modelMedico.setColumnIdentifiers(header);
		modelEspecialidad = new DefaultTableModel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
		modelEspecialidad.setColumnIdentifiers(headerEspecialidad);
		
		setBounds(100, 100, 1444, 993);
		setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelVolver = new RoundedGlowPanel();
		roundedGlowPanelVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desaparecer(20);
				VentanaPrincipal.mostrarPanelFondo();
			}
		});
		roundedGlowPanelVolver.setBounds((int)(10*widthRatio),(int)(10*heightRatio), (int)(57*widthRatio),(int)(49*heightRatio));
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
		
		panelTablaPersona = new PanelSimulacionAnim();
		panelTablaPersona.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaPersona.setBackground(new Color(255, 255, 255));
		panelTablaPersona.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(555*widthRatio),(int)(250*heightRatio));
		add(panelTablaPersona);
		panelTablaPersona.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(tablePersona);
		panelTablaPersona.add(scrollPane);
		
		tablePersona = new JTable(model);
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadEspecialidad(conexion);
				selectedEsp.clear();
				indexEsp = 0;
				lugar = 0;
				tableMedico.clearSelection();
				selectedMedico = null;
				selected = Clinica.getInstance().buscarPersonaByCode(conexion, tablePersona.getValueAt(tablePersona.getSelectedRow(), 0).toString());
				
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(false);
				
				txtCedula.setText(selected.getCedula());
				txtPNombre.setText(selected.getPrimerNombre());
				txtSNombre.setText(selected.getSegundoNombre()); 
				txtPApellido.setText(selected.getPrimerApellido()); 
				txtSApellido.setText(selected.getSegundoApellido()); 
				txtTelefono.setText(selected.getTelefono()); 
				txtareaDireccion.setText(selected.getDireccion());
				
				if(selected.getSexo() == 'M') {
					rdbtnMasculino.setSelected(true);
				} else {
					rdbtnFemenino.setSelected(true);
				}
				
				dateChooserNacim.setDate(selected.getFechaDeNacimiento());
				txtCodeMedicos.setText("M-"+(Clinica.getGeneradorCodeMedico()+1));
				validarCampos();
				
				roundedGlowPanelEliminar.setEnabled(false);
				roundedGlowPanelEliminar.setBackground(colorDeshabilitado);
				lblEliminar.setEnabled(false);
				roundedGlowPanelModificar.setEnabled(false);
				roundedGlowPanelModificar.setBackground(colorDeshabilitado);
				lblModificar.setEnabled(false);
				roundedGlowCitasPendientes.setEnabled(false);
				roundedGlowCitasPendientes.setBackground(colorDeshabilitado);
				lblCitas.setEnabled(false);
			}
		});
		tablePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePersona.getTableHeader().setResizingAllowed(false);
		tablePersona.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);		
		tablePersona.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePersona.getColumnModel().getColumn(1).setPreferredWidth(25);
		tablePersona.getColumnModel().getColumn(2).setPreferredWidth(25);
		tablePersona.getColumnModel().getColumn(3).setPreferredWidth(25);
		tablePersona.getColumnModel().getColumn(4).setPreferredWidth(25);
		tablePersona.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tablePersona.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablePersona);
		{
			RoundedPanel panelDatosPersona = new RoundedPanel();
			panelDatosPersona.setRoundTopRight(35);
			panelDatosPersona.setRoundTopLeft(35);
			panelDatosPersona.setRoundBottomRight(35);
			panelDatosPersona.setRoundBottomLeft(35);
			panelDatosPersona.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(790*widthRatio),(int)(721*heightRatio));
			add(panelDatosPersona);
			panelDatosPersona.setBackground(colorDeshabilitado);
			panelDatosPersona.setLayout(null);
			
			rdbtnMasculino = new JRadioButton("Hombre");
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnFemenino.setSelected(false);
					validarCampos();
				}
			});
			rdbtnMasculino.setBounds((int)(401*widthRatio),(int)(347*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			rdbtnFemenino = new JRadioButton("Mujer");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
					validarCampos();
				}
			});
			rdbtnFemenino.setBounds((int)(501*widthRatio),(int)(347*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			
			RoundedPanel roundedPanelPNombre = new RoundedPanel();
			roundedPanelPNombre.setLayout(null);
			roundedPanelPNombre.setRoundTopRight(18);
			roundedPanelPNombre.setRoundTopLeft(18);
			roundedPanelPNombre.setRoundBottomRight(18);
			roundedPanelPNombre.setRoundBottomLeft(18);
			roundedPanelPNombre.setBackground(SystemColor.window);
			roundedPanelPNombre.setBounds((int)(91*widthRatio),(int)(150*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPNombre);
			
			txtPNombre = new JTextField();
			txtPNombre.setBorder(null);
			txtPNombre.setBounds((int)(126*widthRatio),0, (int)(111*widthRatio),(int)(46*heightRatio));
			roundedPanelPNombre.add(txtPNombre);
			txtPNombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));

			txtPNombre.setColumns(10);
			{
				JLabel lblPNombre = new JLabel("Primer Nombre:");
				lblPNombre.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(119*widthRatio),(int)(22*heightRatio));
				roundedPanelPNombre.add(lblPNombre);
				lblPNombre.setOpaque(true);
				lblPNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblPNombre.setForeground(new Color(65, 105, 225));
				lblPNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
				lblPNombre.setBackground(Color.WHITE);
			}
			
			RoundedPanel roundedPanelSNombre = new RoundedPanel();
			roundedPanelSNombre.setLayout(null);
			roundedPanelSNombre.setRoundTopRight(18);
			roundedPanelSNombre.setRoundTopLeft(18);
			roundedPanelSNombre.setRoundBottomRight(18);
			roundedPanelSNombre.setRoundBottomLeft(18);
			roundedPanelSNombre.setBackground(Color.WHITE);
			roundedPanelSNombre.setBounds((int)(91*widthRatio),(int)(209*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelSNombre);
			
			txtSNombre = new JTextField();
			txtSNombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSNombre.setColumns(10);
			txtSNombre.setBorder(null);
			txtSNombre.setBounds((int)(136*widthRatio),0, (int)(101*widthRatio),(int)(46*heightRatio));
			roundedPanelSNombre.add(txtSNombre);
			
			JLabel lblSNombre = new JLabel("Segundo Nombre:");
			lblSNombre.setOpaque(true);
			lblSNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblSNombre.setForeground(new Color(65, 105, 225));
			lblSNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSNombre.setBackground(Color.WHITE);
			lblSNombre.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedPanelSNombre.add(lblSNombre);
			
			RoundedPanel roundedPanelPApellido = new RoundedPanel();
			roundedPanelPApellido.setLayout(null);
			roundedPanelPApellido.setRoundTopRight(18);
			roundedPanelPApellido.setRoundTopLeft(18);
			roundedPanelPApellido.setRoundBottomRight(18);
			roundedPanelPApellido.setRoundBottomLeft(18);
			roundedPanelPApellido.setBackground(Color.WHITE);
			roundedPanelPApellido.setBounds((int)(91*widthRatio),(int)(270*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPApellido);
			
			txtPApellido = new JTextField();
			txtPApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPApellido.setColumns(10);
			txtPApellido.setBorder(null);
			txtPApellido.setBounds((int)(126*widthRatio),0, (int)(111*widthRatio),(int)(46*heightRatio));
			roundedPanelPApellido.add(txtPApellido);
			
			JLabel lblPApellido = new JLabel("Primer Apellido:");
			lblPApellido.setOpaque(true);
			lblPApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblPApellido.setForeground(new Color(65, 105, 225));
			lblPApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPApellido.setBackground(Color.WHITE);
			lblPApellido.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(119*widthRatio),(int)(22*heightRatio));
			roundedPanelPApellido.add(lblPApellido);
			
			RoundedPanel roundedPanelSApellido = new RoundedPanel();
			roundedPanelSApellido.setLayout(null);
			roundedPanelSApellido.setRoundTopRight(18);
			roundedPanelSApellido.setRoundTopLeft(18);
			roundedPanelSApellido.setRoundBottomRight(18);
			roundedPanelSApellido.setRoundBottomLeft(18);
			roundedPanelSApellido.setBackground(Color.WHITE);
			roundedPanelSApellido.setBounds((int)(91*widthRatio),(int)(329*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelSApellido);
			
			txtSApellido = new JTextField();
			txtSApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSApellido.setColumns(10);
			txtSApellido.setBorder(null);
			txtSApellido.setBounds((int)(136*widthRatio),0, (int)(101*widthRatio),(int)(46*heightRatio));
			roundedPanelSApellido.add(txtSApellido);
			
			JLabel lblSApellido = new JLabel("Segundo Apellido:");
			lblSApellido.setOpaque(true);
			lblSApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblSApellido.setForeground(new Color(65, 105, 225));
			lblSApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSApellido.setBackground(Color.WHITE);
			lblSApellido.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedPanelSApellido.add(lblSApellido);
			
			RoundedPanel roundedPanelCodePaciente = new RoundedPanel();
			roundedPanelCodePaciente.setLayout(null);
			roundedPanelCodePaciente.setRoundTopRight(18);
			roundedPanelCodePaciente.setRoundTopLeft(18);
			roundedPanelCodePaciente.setRoundBottomRight(18);
			roundedPanelCodePaciente.setRoundBottomLeft(18);
			roundedPanelCodePaciente.setBackground(Color.WHITE);
			roundedPanelCodePaciente.setBounds((int)(200*widthRatio),(int)(87*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelCodePaciente);
			
			JLabel lblCdigo = new JLabel("C\u00F3digo:");
			lblCdigo.setOpaque(true);
			lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCdigo.setForeground(new Color(65, 105, 225));
			lblCdigo.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCdigo.setBackground(Color.WHITE);
			lblCdigo.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedPanelCodePaciente.add(lblCdigo);
			
			txtCodeMedicos = new JTextField();
			txtCodeMedicos.setBackground(new Color(255, 255, 255));
			txtCodeMedicos.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(62*widthRatio),(int)(22*heightRatio));
			roundedPanelCodePaciente.add(txtCodeMedicos);
			txtCodeMedicos.setBorder(null);
			txtCodeMedicos.setEditable(false);
			txtCodeMedicos.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeMedicos.setColumns(10);
			txtCodeMedicos.setText("M-"+(Clinica.getGeneradorCodeMedico()+1));
			
			RoundedGlowPanel roundedGlowPanelCodePaciente = new RoundedGlowPanel();
			roundedGlowPanelCodePaciente.setBounds((int)(184*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(61*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCodePaciente);
			roundedGlowPanelCodePaciente.setGlowAlpha(170);
			roundedGlowPanelCodePaciente.setForeground(new Color(255, 255, 255));
			roundedGlowPanelCodePaciente.setBorder(null);
			roundedGlowPanelCodePaciente.setGlowColor(Color.CYAN);
			roundedGlowPanelCodePaciente.setRoundTopRight(60);
			roundedGlowPanelCodePaciente.setRoundTopLeft(60);
			roundedGlowPanelCodePaciente.setRoundBottomRight(60);
			roundedGlowPanelCodePaciente.setRoundBottomLeft(60);
			roundedGlowPanelCodePaciente.setBackground(Color.WHITE);
			roundedGlowPanelCodePaciente.setLayout(null);
			
			JLabel lblRegistroDePersonas = new JLabel("Registro de M\u00E9dicos");
			lblRegistroDePersonas.setOpaque(true);
			lblRegistroDePersonas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDePersonas.setForeground(new Color(0, 0, 0));
			lblRegistroDePersonas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDePersonas.setBackground(colorDeshabilitado);
			lblRegistroDePersonas.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDePersonas);
			
			RoundedPanel roundedPanelCedula = new RoundedPanel();
			roundedPanelCedula.setLayout(null);
			roundedPanelCedula.setRoundTopRight(18);
			roundedPanelCedula.setRoundTopLeft(18);
			roundedPanelCedula.setRoundBottomRight(18);
			roundedPanelCedula.setRoundBottomLeft(18);
			roundedPanelCedula.setBackground(Color.WHITE);
			roundedPanelCedula.setBounds((int)(379*widthRatio),(int)(87*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelCedula);
			
			JLabel lblCedula = new JLabel("C\u00E9dula:");
			lblCedula.setOpaque(true);
			lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCedula.setForeground(new Color(65, 105, 225));
			lblCedula.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCedula.setBackground(Color.WHITE);
			lblCedula.setBounds(0,(int)(11*heightRatio), (int)(64*widthRatio),(int)(22*heightRatio));
			roundedPanelCedula.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setBorder(null);
			txtCedula.setBounds((int)(64*widthRatio),0, (int)(173*widthRatio),(int)(46*heightRatio));
			roundedPanelCedula.add(txtCedula);
			txtCedula.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtCedula.setColumns(10);
			
			RoundedPanel roundedPanelTelefono = new RoundedPanel();
			roundedPanelTelefono.setLayout(null);
			roundedPanelTelefono.setRoundTopRight(18);
			roundedPanelTelefono.setRoundTopLeft(18);
			roundedPanelTelefono.setRoundBottomRight(18);
			roundedPanelTelefono.setRoundBottomLeft(18);
			roundedPanelTelefono.setBackground(Color.WHITE);
			roundedPanelTelefono.setBounds((int)(379*widthRatio),(int)(150*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelTelefono);
			
			JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(new Color(65, 105, 225));
			lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTelefono.setBackground(Color.WHITE);
			lblTelefono.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedPanelTelefono.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setBorder(null);
			txtTelefono.setBounds((int)(77*widthRatio),0, (int)(160*widthRatio),(int)(46*heightRatio));
			roundedPanelTelefono.add(txtTelefono);
			txtTelefono.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtTelefono.setColumns(10);
			
			RoundedPanel roundedPanelFNac = new RoundedPanel();
			roundedPanelFNac.setLayout(null);
			roundedPanelFNac.setRoundTopRight(18);
			roundedPanelFNac.setRoundTopLeft(18);
			roundedPanelFNac.setRoundBottomRight(18);
			roundedPanelFNac.setRoundBottomLeft(18);
			roundedPanelFNac.setBackground(Color.WHITE);
			roundedPanelFNac.setBounds((int)(379*widthRatio),(int)(211*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelFNac);
			
			JLabel lblFDeNac = new JLabel("F. Nacimiento");
			lblFDeNac.setOpaque(true);
			lblFDeNac.setHorizontalAlignment(SwingConstants.CENTER);
			lblFDeNac.setForeground(new Color(65, 105, 225));
			lblFDeNac.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblFDeNac.setBackground(Color.WHITE);
			lblFDeNac.setBounds((int)(0*widthRatio),(int)(11*heightRatio), (int)(112*widthRatio),(int)(22*heightRatio));
			roundedPanelFNac.add(lblFDeNac);
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds((int)(521*widthRatio),(int)(211*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(dateChooserNacim);
			BorderLayout borderLayout = (BorderLayout) dateChooserNacim.getLayout();
			dateChooserNacim.setBackground(new Color(255, 255, 255));
			dateChooserNacim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			dateChooserNacim.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserNacim.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			
			RoundedPanel panelTablaEspecialidad = new RoundedPanel();
			panelTablaEspecialidad.setBackground(Color.WHITE);
			panelTablaEspecialidad.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosPersona.add(panelTablaEspecialidad);
			panelTablaEspecialidad.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_Esp = new JScrollPane((Component) null);
			panelTablaEspecialidad.add(scrollPane_Esp);
			
			tableEspecialidad = new JTable(modelEspecialidad);
			tableEspecialidad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableEspecialidad.getSelectedRow(), colIndex = tableEspecialidad.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						if (colIndex == 2) {
							if(tableEspecialidad.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
								selectedEsp.add(Integer.valueOf((tableEspecialidad.getValueAt(rowIndex, colIndex-2).toString())));
								indexEsp++;
							} else {
								selectedEsp.remove(Integer.valueOf((tableEspecialidad.getValueAt(rowIndex, colIndex-2).toString())));
								indexEsp--;
							}
						}
					}
					
					validarCampos();
				}
			});
			tableEspecialidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableEspecialidad.getTableHeader().setResizingAllowed(false);
			tableEspecialidad.getTableHeader().setReorderingAllowed(false);
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);	
			
			for (int index = 0; index < tableEspecialidad.getColumnCount(); index++) {
				
				if (index != 2) {
					
					tableEspecialidad.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tableEspecialidad.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableEspecialidad.getColumnModel().getColumn(1).setPreferredWidth(25);
			tableEspecialidad.getColumnModel().getColumn(2).setPreferredWidth(25);
			tableEspecialidad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			tableEspecialidad.setFillsViewportHeight(true);
			scrollPane_Esp.setViewportView(tableEspecialidad);
			
			
			RoundedGlowPanel roundedGlowPanelPNombre = new RoundedGlowPanel();
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
			roundedGlowPanelPNombre.setBounds((int)(77*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPNombre);
			
			RoundedGlowPanel roundedGlowPanelSNombre = new RoundedGlowPanel();
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
			roundedGlowPanelSNombre.setBounds((int)(77*widthRatio),(int)(205*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSNombre);
			
			RoundedGlowPanel roundedGlowPanelPApellido = new RoundedGlowPanel();
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
			roundedGlowPanelPApellido.setBounds((int)(77*widthRatio),(int)(266*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPApellido);
			
			RoundedGlowPanel roundedGlowPanelSApellido = new RoundedGlowPanel();
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
			roundedGlowPanelSApellido.setBounds((int)(77*widthRatio),(int)(327*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSApellido);
			
			RoundedGlowPanel roundedGlowPanelCedula = new RoundedGlowPanel();
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
			roundedGlowPanelCedula.setBounds((int)(365*widthRatio),(int)(79*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCedula);
			
			RoundedGlowPanel roundedGlowPanelTelefono = new RoundedGlowPanel();
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
			roundedGlowPanelTelefono.setBounds((int)(365*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelTelefono);
			
			RoundedGlowPanel roundedGlowPanelFNacimiento = new RoundedGlowPanel();
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
			roundedGlowPanelFNacimiento.setBounds((int)(365*widthRatio),(int)(205*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelFNacimiento);
			
			RoundedGlowPanel roundedGlowPanelDireccion = new RoundedGlowPanel();
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
			roundedGlowPanelDireccion.setBounds((int)(362*widthRatio),(int)(268*heightRatio), (int)(277*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanelDireccion);
			
			JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setBounds((int)(12*widthRatio),(int)(24*heightRatio), (int)(86*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelDireccion.add(lblDireccion);
			lblDireccion.setOpaque(true);
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setForeground(new Color(65, 105, 225));
			lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblDireccion.setBackground(Color.WHITE);
			
			txtareaDireccion = new JTextArea();
			txtareaDireccion.setBounds((int)(97*widthRatio),(int)(6*heightRatio), (int)(164*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelDireccion.add(txtareaDireccion);
			txtareaDireccion.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtareaDireccion.setLineWrap(true);
			txtareaDireccion.setWrapStyleWord(true);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds(0, (int)(780*heightRatio), (int)(1400*widthRatio), (int)(170*heightRatio));
		add(gradientPanel);
		gradientPanel.setLayout(null);
		
		roundedGlowPanelModificar = new RoundedGlowPanel();
		roundedGlowPanelModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelModificar.isEnabled()) {
					
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar los datos del Medico con la Cédula <" + selectedMedico.getCedula() + "> y cuyo nombre es: <" + selectedMedico.getPrimerNombre() + ">?", "Modificar Medico", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) {
						
						boolean modEspecialidad = Clinica.getInstance().modificarEspecialidades(conexion, selectedMedico.getID_Medico(), selectedEsp, indexEsp);
						
						if(modEspecialidad == true) {
							
							char sexo;
							
							if(rdbtnMasculino.isSelected()) {
								sexo = 'M';
							}
							
							else {
								sexo = 'F';
							}
							
							boolean mod = Clinica.getInstance().modificarPersona(conexion, txtCedula.getText(), txtPNombre.getText(), txtSNombre.getText(), txtPApellido.getText(), txtSApellido.getText(), txtTelefono.getText(), txtareaDireccion.getText(), sexo, dateChooserNacim.getDate());
							
							if(mod == true) {
								JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Medico", JOptionPane.INFORMATION_MESSAGE);
								
								selectedEsp.clear();
								indexEsp = 0;
								limpiarDatos();
								loadPersonas(conexion);
								loadMedicos(conexion);
								loadEspecialidad(conexion);
								
								roundedGlowPanelEliminar.setEnabled(false);
								roundedGlowPanelEliminar.setBackground(colorDeshabilitado);
								lblEliminar.setEnabled(false);
								roundedGlowPanelModificar.setEnabled(false);
								roundedGlowPanelModificar.setBackground(colorDeshabilitado);
								lblModificar.setEnabled(false);
								roundedGlowCitasPendientes.setEnabled(false);
								roundedGlowCitasPendientes.setBackground(colorDeshabilitado);
								lblCitas.setEnabled(false);
							}
							else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Modificar el Médico!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} 
						else {
							JOptionPane.showMessageDialog(null,"¡Hubo un Error al Modificar Las Especialidades del Médico!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
		});
		roundedGlowPanelModificar.setEnabled(false);
		roundedGlowPanelModificar.setLayout(null);
		roundedGlowPanelModificar.setRoundTopRight(60);
		roundedGlowPanelModificar.setRoundTopLeft(60);
		roundedGlowPanelModificar.setRoundBottomRight(60);
		roundedGlowPanelModificar.setRoundBottomLeft(60);
		roundedGlowPanelModificar.setGlowColor(Color.CYAN);
		roundedGlowPanelModificar.setGlowAlpha(170);
		roundedGlowPanelModificar.setForeground(Color.WHITE);
		roundedGlowPanelModificar.setBorder(null);
		roundedGlowPanelModificar.setBackground(colorDeshabilitado);
		roundedGlowPanelModificar.setBounds((int)(1107*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelModificar);
		
		lblModificar = new JLabel("Modificar");
		lblModificar.setEnabled(false);
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setForeground(new Color(60, 179, 113));
		lblModificar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblModificar.setBackground(Color.WHITE);
		lblModificar.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelModificar.add(lblModificar);
		
		roundedGlowPanelEliminar = new RoundedGlowPanel();
		roundedGlowPanelEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelEliminar.isEnabled()) {
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al Medico con la Cédula <" + selectedMedico.getCedula() + "> y cuyo nombre es: <" + selectedMedico.getPrimerNombre() + ">?", "Eliminar Medico", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) {
						
						boolean elimEspecialidad = Clinica.getInstance().eliminarEspecialidades(conexion, selectedMedico.getID_Medico());
						
						if(elimEspecialidad == true) {
							
							boolean elim = Clinica.getInstance().eliminarMedico(conexion, selectedMedico.getID_Medico());
							
							if(elim == true) {
								
								JOptionPane.showMessageDialog(null, "Eliminado con éxito", "Eliminar Medico", JOptionPane.INFORMATION_MESSAGE);
								
								Option = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar También a la Persona con la Cédula: <" + selectedMedico.getCedula() + "> y cuyo nombre es: <" + selectedMedico.getPrimerNombre() + ">?", "Eliminar Antigüo Medico", JOptionPane.OK_CANCEL_OPTION);
								
								if (Option == JOptionPane.OK_OPTION) {
									
									boolean elimPersona = Clinica.getInstance().eliminarPersona(conexion, selectedMedico.getCedula());
									
									if(elimPersona == true) {
										JOptionPane.showMessageDialog(null, "Eliminado con éxito", "Eliminar Persona", JOptionPane.INFORMATION_MESSAGE);
									}
									else {
										JOptionPane.showMessageDialog(null,"¡No Se Pudo Eliminar la Persona!", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
								
								selectedEsp.clear();
								indexEsp = 0;
								limpiarDatos();
								loadPersonas(conexion);
								loadMedicos(conexion);
								loadEspecialidad(conexion);
								
								roundedGlowPanelEliminar.setEnabled(false);
								roundedGlowPanelEliminar.setBackground(colorDeshabilitado);
								lblEliminar.setEnabled(false);
								roundedGlowPanelModificar.setEnabled(false);
								roundedGlowPanelModificar.setBackground(colorDeshabilitado);
								lblModificar.setEnabled(false);
								roundedGlowCitasPendientes.setEnabled(false);
								roundedGlowCitasPendientes.setBackground(colorDeshabilitado);
								lblCitas.setEnabled(false);
							}
							else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Eliminar al Medico!", "Error", JOptionPane.ERROR_MESSAGE);
							}	
						}
						else {
							JOptionPane.showMessageDialog(null,"¡Ocurrió un Error Al Eliminar las Especialidades del Médico!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		roundedGlowPanelEliminar.setEnabled(false);
		roundedGlowPanelEliminar.setLayout(null);
		roundedGlowPanelEliminar.setRoundTopRight(60);
		roundedGlowPanelEliminar.setRoundTopLeft(60);
		roundedGlowPanelEliminar.setRoundBottomRight(60);
		roundedGlowPanelEliminar.setRoundBottomLeft(60);
		roundedGlowPanelEliminar.setGlowColor(Color.CYAN);
		roundedGlowPanelEliminar.setGlowAlpha(170);
		roundedGlowPanelEliminar.setForeground(Color.WHITE);
		roundedGlowPanelEliminar.setBorder(null);
		roundedGlowPanelEliminar.setBackground(colorDeshabilitado);
		roundedGlowPanelEliminar.setBounds((int)(1249*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelEliminar);
		
		lblEliminar = new JLabel("Eliminar");
		lblEliminar.setEnabled(false);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(new Color(220, 20, 60));
		lblEliminar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblEliminar.setBackground(Color.WHITE);
		lblEliminar.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelEliminar.add(lblEliminar);
		
		roundedGlowPanelRegistrar = new RoundedGlowPanel();
		roundedGlowPanelRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelRegistrar.isEnabled()) {
					
					boolean res = Clinica.getInstance().insertarMedico(conexion, txtCedula.getText(), null, null);
					
					if(res) {
						
						boolean res2 = Clinica.getInstance().insertarEspecialidades(conexion, (Clinica.getGeneradorCodeMedico()+1), selectedEsp, indexEsp);
						
						if(res2) {
							JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Medico", JOptionPane.INFORMATION_MESSAGE);
							
							limpiarDatos();
						    loadPersonas(conexion);
						    loadMedicos(conexion);
						    loadEspecialidad(conexion);
						    indexEsp = 0;
						    selectedEsp.clear();
						    
						} else {
							JOptionPane.showMessageDialog(null,"¡No Se Pudieron Insertar las Especialidades!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar al Medico!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		roundedGlowPanelRegistrar.setEnabled(false);
		roundedGlowPanelRegistrar.setBounds((int)(817*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelRegistrar);
		roundedGlowPanelRegistrar.setLayout(null);
		roundedGlowPanelRegistrar.setRoundTopRight(60);
		roundedGlowPanelRegistrar.setRoundTopLeft(60);
		roundedGlowPanelRegistrar.setRoundBottomRight(60);
		roundedGlowPanelRegistrar.setRoundBottomLeft(60);
		roundedGlowPanelRegistrar.setGlowColor(Color.CYAN);
		roundedGlowPanelRegistrar.setGlowAlpha(170);
		roundedGlowPanelRegistrar.setForeground(Color.WHITE);
		roundedGlowPanelRegistrar.setBorder(null);
		roundedGlowPanelRegistrar.setBackground(colorDeshabilitado);
	
		
		lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setEnabled(false);
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setForeground(new Color(100, 149, 237));
		lblRegistrar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar.setBackground(Color.WHITE);
		lblRegistrar.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar);
		
		roundedGlowCitasPendientes = new RoundedGlowPanel();
		roundedGlowCitasPendientes.setEnabled(false);
		roundedGlowCitasPendientes.setLayout(null);
		roundedGlowCitasPendientes.setRoundTopRight(60);
		roundedGlowCitasPendientes.setRoundTopLeft(60);
		roundedGlowCitasPendientes.setRoundBottomRight(60);
		roundedGlowCitasPendientes.setRoundBottomLeft(60);
		roundedGlowCitasPendientes.setGlowColor(Color.CYAN);
		roundedGlowCitasPendientes.setGlowAlpha(170);
		roundedGlowCitasPendientes.setForeground(Color.WHITE);
		roundedGlowCitasPendientes.setBorder(null);
		roundedGlowCitasPendientes.setBackground(colorDeshabilitado);
		roundedGlowCitasPendientes.setBounds((int)(963*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowCitasPendientes);
		
		lblCitas = new JLabel("Citas");
		lblCitas.setEnabled(false);
		lblCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitas.setForeground(new Color(100, 149, 237));
		lblCitas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblCitas.setBackground(Color.WHITE);
		lblCitas.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowCitasPendientes.add(lblCitas);
		
		RoundedGlowPanel roundedGlowPanelBuscarPaciente = new RoundedGlowPanel();
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
		roundedGlowPanelBuscarPaciente.setBounds((int)(817*widthRatio),(int)(541*heightRatio), (int)(550*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelBuscarPaciente);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(100, 149, 237));
		lblBuscar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblBuscar.setBackground(Color.WHITE);
		lblBuscar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelBuscarPaciente.add(lblBuscar);
		
		txtBuscarPaciente = new JTextField();
		txtBuscarPaciente.setOpaque(false);
		txtBuscarPaciente.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		txtBuscarPaciente.setColumns(10);
		txtBuscarPaciente.setBorder(null);
		txtBuscarPaciente.setBounds((int)(101*widthRatio), 0, (int)(428*widthRatio),(int)(49*heightRatio));
		txtBuscarPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tablePersona.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tablePersona.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				
				DefaultTableModel searchModel3 = (DefaultTableModel) tableMedico.getModel();
				TableRowSorter<DefaultTableModel> searchModel4 = new TableRowSorter<DefaultTableModel>(searchModel3);
				tableMedico.setRowSorter(searchModel4);
				searchModel4.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				
				DefaultTableModel searchModel5 = (DefaultTableModel) tableEspecialidad.getModel();
				TableRowSorter<DefaultTableModel> searchModel6 = new TableRowSorter<DefaultTableModel>(searchModel5);
				tableEspecialidad.setRowSorter(searchModel6);
				searchModel6.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
			}
		});
		roundedGlowPanelBuscarPaciente.add(txtBuscarPaciente);
		txtBuscarPaciente.setColumns(10);
		
		PanelSimulacionAnim panelTablaMedico = new PanelSimulacionAnim();
		panelTablaMedico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaMedico.setBackground(Color.WHITE);
		panelTablaMedico.setBounds((int)(814*widthRatio),(int)(276*heightRatio), (int)(555*widthRatio),(int)(250*heightRatio));
		add(panelTablaMedico);
		panelTablaMedico.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane(tableMedico);
		panelTablaMedico.add(scrollPane_1);
		
		tableMedico = new JTable(modelMedico);
		tableMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadEspecialidad(conexion);
				selectedEsp.clear();
				indexEsp = 0;
				lugar = 1;
				tablePersona.clearSelection();
				selected = null;
				selectedMedico = Clinica.getInstance().buscarMedicoByCode(conexion, tableMedico.getValueAt(tableMedico.getSelectedRow(), 0).toString());
				
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(false);
				
				txtCedula.setText(selectedMedico.getCedula());
				txtPNombre.setText(selectedMedico.getPrimerNombre());
				txtSNombre.setText(selectedMedico.getSegundoNombre()); 
				txtPApellido.setText(selectedMedico.getPrimerApellido()); 
				txtSApellido.setText(selectedMedico.getSegundoApellido()); 
				txtTelefono.setText(selectedMedico.getTelefono()); 
				txtareaDireccion.setText(selectedMedico.getDireccion());
				
				if(selectedMedico.getSexo() == 'M') {
					rdbtnMasculino.setSelected(true);
				} else {
					rdbtnFemenino.setSelected(true);
				}
				
				dateChooserNacim.setDate(selectedMedico.getFechaDeNacimiento());
				txtCodeMedicos.setText("M-"+ selectedMedico.getID_Medico());
				loadEspecialidadMed(conexion, selectedMedico.getID_Medico());
				
				roundedGlowPanelEliminar.setEnabled(true);
				roundedGlowPanelEliminar.setBackground(Color.WHITE);
				lblEliminar.setEnabled(true);
				roundedGlowPanelModificar.setEnabled(true);
				roundedGlowPanelModificar.setBackground(Color.WHITE);
				lblModificar.setEnabled(true);
				roundedGlowCitasPendientes.setEnabled(true);
				roundedGlowCitasPendientes.setBackground(Color.WHITE);
				lblCitas.setEnabled(true);
				roundedGlowPanelRegistrar.setEnabled(false);
				validarCampos();
			}
		});
		tableMedico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMedico.getTableHeader().setResizingAllowed(false);
		tableMedico.getTableHeader().setReorderingAllowed(false);
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);		
		tableMedico.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableMedico.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableMedico.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableMedico.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableMedico.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableMedico.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableMedico.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableMedico);
		
		
			
		KeyListener campoListener = new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	        	validarCampos();
	        }
	    };
	    
	    
	    txtPNombre.addKeyListener(campoListener);
	    txtSNombre.addKeyListener(campoListener);
	    txtPApellido.addKeyListener(campoListener);
	    txtSApellido.addKeyListener(campoListener);
	    txtCedula.addKeyListener(campoListener);
	    txtTelefono.addKeyListener(campoListener);
	    txtareaDireccion.addKeyListener(campoListener);
	    dateChooserNacim.addPropertyChangeListener("yyyy-MM-dd", e -> validarCampos());
	    
	    loadPersonas(conexion);
	    loadMedicos(conexion);
	    loadEspecialidad(conexion);
	}


	private void validarCampos() {
		
		if(!txtPNombre.getText().isEmpty() && !txtSNombre.getText().isEmpty() && !txtPApellido.getText().isEmpty() && !txtSApellido.getText().isEmpty()
		   && !txtCedula.getText().isEmpty() && !txtTelefono.getText().isEmpty() && lugar == 0 && (dateChooserNacim.getDate() != null) && !selectedEsp.isEmpty() && !txtareaDireccion.getText().isEmpty()
		   && (rdbtnMasculino.isSelected() || rdbtnFemenino.isSelected())) {
												
		   roundedGlowPanelRegistrar.setEnabled(true);
		   roundedGlowPanelRegistrar.setBackground(Color.WHITE);
		   lblRegistrar.setEnabled(true);
		   
		} else {
			
		   roundedGlowPanelRegistrar.setEnabled(false);
		   roundedGlowPanelRegistrar.setBackground(colorDeshabilitado);
		   lblRegistrar.setEnabled(false);
		   
		}
		
	}
	
	public static void loadPersonas(Connection conexion) {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Persona.Doc_Identidad, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido FROM Persona LEFT JOIN Medico ON Persona.Doc_Identidad = Medico.Doc_Identidad LEFT JOIN Paciente ON Persona.Doc_Identidad = Paciente.Doc_Identidad LEFT JOIN Administrativo ON Persona.Doc_Identidad = Administrativo.Doc_Identidad WHERE Medico.Doc_Identidad IS NULL AND Administrativo.Doc_Identidad IS NULL AND Paciente.Doc_Identidad IS NULL;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("Doc_Identidad");
            	row[1] = resultSet.getString("Primer_Nombre");
            	row[2] = resultSet.getString("Segundo_Nombre");
            	row[3] = resultSet.getString("Primer_Apellido");
            	row[4] = resultSet.getString("Segundo_Apellido");
            	model.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadMedicos(Connection conexion) {
		
		modelMedico.setRowCount(0);
		row = new Object[modelMedico.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Persona.Doc_Identidad, Medico.ID_Medico, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido FROM Persona INNER JOIN Medico ON Persona.Doc_Identidad = Medico.Doc_Identidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("Doc_Identidad");
            	row[1] = resultSet.getString("Primer_Nombre");
            	row[2] = resultSet.getString("Segundo_Nombre");
            	row[3] = resultSet.getString("Primer_Apellido");
            	row[4] = resultSet.getString("Segundo_Apellido");
            	modelMedico.addRow(row);
            	
				Clinica.setGeneradorCodeMedico(resultSet.getInt("ID_Medico"));
				txtCodeMedicos.setText("M-"+(Clinica.getGeneradorCodeMedico()+1));
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadEspecialidad(Connection conexion) {
		
		modelEspecialidad.setRowCount(0);
		row = new Object[modelEspecialidad.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Especialidad, Nombre_Especialidad FROM Especialidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("ID_Especialidad");
            	row[1] = resultSet.getString("Nombre_Especialidad");
            	modelEspecialidad.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadEspecialidadMed(Connection conexion, int idMedico) {
		
		int filasTot = tableEspecialidad.getRowCount();
		indexEsp = 0;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Medico.ID_Medico, Especialidad.ID_Especialidad, Especialidad.Nombre_Especialidad FROM Medico INNER JOIN Med_Especialidad ON Medico.ID_Medico = Med_Especialidad.ID_Medico INNER JOIN Especialidad ON Med_Especialidad.ID_Especialidad = Especialidad.ID_Especialidad WHERE Medico.ID_Medico = "+idMedico+";";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("ID_Especialidad");
            	row[1] = resultSet.getString("Nombre_Especialidad");
            	for(int ind = 0; ind < filasTot; ind++) {
            		if(tableEspecialidad.getValueAt(ind, 0).toString().equals(resultSet.getString("ID_Especialidad"))) {
            			tableEspecialidad.setValueAt(Boolean.TRUE, ind, 2);
            			selectedEsp.add(Integer.valueOf(tableEspecialidad.getValueAt(ind, 0).toString()));
            			indexEsp++;
            		}
            	}
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	protected void limpiarDatos() {
		txtCedula.setText("");
		txtPNombre.setText("");
		txtSNombre.setText(""); 
		txtPApellido.setText(""); 
		txtSApellido.setText(""); 
		txtTelefono.setText(""); 
		txtareaDireccion.setText(""); 
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
	}
	
}
