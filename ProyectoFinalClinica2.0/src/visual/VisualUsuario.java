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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import exception.ValidarCampo;
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

public class VisualUsuario extends PanelSimulacionAnim {

	private static DefaultTableModel model;
	private Dimension dim;
	private JTable tablePacientes;
	private static Object[] row;
	private Paciente selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private JTextField txtCodeUsuario;
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JPanel panelDatosPersona;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JLabel lblPassword;
	private JLabel lblCodeUsuario;
	private RoundedGlowPanel roundedGlowPanelCodeUsuario;
	private RoundedGlowPanel roundedGlowPanelPassword;
	private RoundedGlowPanel roundedGlowPanelUsuario;
	private JPanel panelTablaUsuario;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private RoundedGlowPanel roundedGlowConsultar;
	private JLabel lblRegistrar1;
	private RoundedGlowPanel roundedGlowPanelBuscarPaciente;
	private JLabel lblBuscar;
	private JTextField txtBuscarPaciente;
	private RoundedGlowPanel roundedGlowCargo;
	private JComboBox cbxCargo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualUsuario dialog = new VisualUsuario();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualUsuario() 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		//paciente = pacienteAModificar;
		//pacientesEspecificosAMostrar = pacientesAMostrar;
		
		Object[] header = {"Código", "Cédula", "Nombre", "Sexo", "Teléfono", "Ver más"};
		model = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 5) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 5) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		model.setColumnIdentifiers(header);
		
		setBounds(100, 100, 1444, 993);
		setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		setBackground(new Color(248, 248, 255));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
	
		
		panelTablaUsuario = new JPanel();
		panelTablaUsuario.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaUsuario.setBackground(new Color(255, 255, 255));
		panelTablaUsuario.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(555*widthRatio),(int)(515*heightRatio));
		add(panelTablaUsuario);
		panelTablaUsuario.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tablePacientes);
		panelTablaUsuario.add(scrollPane, BorderLayout.CENTER);
		
		tablePacientes = new JTable(model);
		tablePacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePacientes.getTableHeader().setResizingAllowed(false);
		tablePacientes.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tablePacientes.getColumnCount(); index++) {
			
			if (index != 5) {
				
				tablePacientes.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tablePacientes.getColumnModel().getColumn(0).setPreferredWidth(5);
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(25);
		tablePacientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablePacientes.getColumnModel().getColumn(3).setPreferredWidth(5);
		tablePacientes.getColumnModel().getColumn(4).setPreferredWidth(25);
		tablePacientes.getColumnModel().getColumn(5).setPreferredWidth(5);
		tablePacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowIndex = tablePacientes.getSelectedRow(), colIndex = tablePacientes.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					selected = Clinica.getInstance().buscarPacienteByCode(tablePacientes.getValueAt(rowIndex, 0).toString());
					lblRegistrar1.setEnabled(true);
					
					if (pacientesEspecificosAMostrar == null) {
						
						lblModificar.setEnabled(true);
						lblEliminar.setEnabled(true);
					}
					
					if (colIndex == 5) {
						
						/* RECORDAR QUE AQUI EN VEZ DE ABRIR LA PESTAÑA DE REGISTRAR PACIENTE, DEBEMOS PONER LOS DATOS DE LA PERSONA EN 
						 * LOS CAMPOS QUE SALEN DENTRO DEL MISMO PANEL.
						
						RegPaciente visualizarPaciente = new RegPaciente(selected, false, true);
						visualizarPaciente.setModal(true);
						visualizarPaciente.setVisible(true);
						tablePacientes.setValueAt(Boolean.FALSE, rowIndex, colIndex); */
					}

					
				}
				
			}
		});
		tablePacientes.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tablePacientes.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablePacientes);
		{
			RoundedPanel panelDatosPersona = new RoundedPanel();
			panelDatosPersona.setRoundTopRight(35);
			panelDatosPersona.setRoundTopLeft(35);
			panelDatosPersona.setRoundBottomRight(35);
			panelDatosPersona.setRoundBottomLeft(35);
			panelDatosPersona.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(790*widthRatio),(int)(721*heightRatio));
			add(panelDatosPersona);
			panelDatosPersona.setBackground(new Color(240, 240, 240));
			panelDatosPersona.setLayout(null);
			
			roundedGlowPanelCodeUsuario = new RoundedGlowPanel();
			roundedGlowPanelCodeUsuario.setBounds((int)(286*widthRatio),(int)(101*heightRatio), (int)(170*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCodeUsuario);
			roundedGlowPanelCodeUsuario.setGlowAlpha(170);
			roundedGlowPanelCodeUsuario.setForeground(new Color(255, 255, 255));
			roundedGlowPanelCodeUsuario.setBorder(null);
			roundedGlowPanelCodeUsuario.setGlowColor(new Color(0, 255, 255));
			roundedGlowPanelCodeUsuario.setRoundTopRight(60);
			roundedGlowPanelCodeUsuario.setRoundTopLeft(60);
			roundedGlowPanelCodeUsuario.setRoundBottomRight(60);
			roundedGlowPanelCodeUsuario.setRoundBottomLeft(60);
			roundedGlowPanelCodeUsuario.setBackground(new Color(255, 255, 255));
			roundedGlowPanelCodeUsuario.setLayout(null);
			
			lblCodeUsuario = new JLabel("C\u00F3digo:");
			lblCodeUsuario.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelCodeUsuario.add(lblCodeUsuario);
			lblCodeUsuario.setOpaque(true);
			lblCodeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodeUsuario.setForeground(new Color(65, 105, 225));
			lblCodeUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCodeUsuario.setBackground(Color.WHITE);
			
			txtCodeUsuario = new JTextField();
			txtCodeUsuario.setBounds((int)(94*widthRatio),(int)(16*heightRatio), (int)(68*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelCodeUsuario.add(txtCodeUsuario);
			txtCodeUsuario.setBackground(new Color(255, 255, 255));
			txtCodeUsuario.setBorder(null);
			txtCodeUsuario.setEditable(false);
			txtCodeUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeUsuario.setColumns(10);
			txtCodeUsuario.setText("U-"+Clinica.getInstance().getGeneradorCodePaciente());
			
			JLabel lblRegistroDeCitas = new JLabel("Administraci\u00F3n");
			lblRegistroDeCitas.setOpaque(true);
			lblRegistroDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeCitas.setForeground(new Color(0, 0, 0));
			lblRegistroDeCitas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDeCitas.setBackground(new Color(240, 240, 240));
			lblRegistroDeCitas.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDeCitas);
			
			RoundedPanel roundedPanelTablaPersonas = new RoundedPanel();
			roundedPanelTablaPersonas.setLayout(null);
			roundedPanelTablaPersonas.setRoundTopRight(18);
			roundedPanelTablaPersonas.setRoundTopLeft(18);
			roundedPanelTablaPersonas.setRoundBottomRight(18);
			roundedPanelTablaPersonas.setRoundBottomLeft(18);
			roundedPanelTablaPersonas.setBackground(Color.WHITE);
			roundedPanelTablaPersonas.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosPersona.add(roundedPanelTablaPersonas);
			
			JLabel lblImagen = new JLabel("AQUI SALDR\u00C1 LA LISTA DE GENTE QUE EST\u00C1 REGISTRADA");
			lblImagen.setOpaque(true);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagen.setForeground(new Color(65, 105, 225));
			lblImagen.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblImagen.setBackground(Color.WHITE);
			lblImagen.setBounds((int)(97*widthRatio),(int)(101*heightRatio), (int)(454*widthRatio),(int)(22*heightRatio));
			roundedPanelTablaPersonas.add(lblImagen);
			
			roundedGlowPanelUsuario = new RoundedGlowPanel();
			roundedGlowPanelUsuario.setLayout(null);
			roundedGlowPanelUsuario.setRoundTopRight(60);
			roundedGlowPanelUsuario.setRoundTopLeft(60);
			roundedGlowPanelUsuario.setRoundBottomRight(60);
			roundedGlowPanelUsuario.setRoundBottomLeft(60);
			roundedGlowPanelUsuario.setGlowColor(Color.CYAN);
			roundedGlowPanelUsuario.setGlowAlpha(170);
			roundedGlowPanelUsuario.setForeground(Color.WHITE);
			roundedGlowPanelUsuario.setBorder(null);
			roundedGlowPanelUsuario.setBackground(Color.WHITE);
			roundedGlowPanelUsuario.setBounds((int)(170*widthRatio),(int)(167*heightRatio), (int)(369*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelUsuario);
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(88*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelUsuario.add(lblUsuario);
			lblUsuario.setOpaque(true);
			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsuario.setForeground(new Color(65, 105, 225));
			lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblUsuario.setBackground(Color.WHITE);
			
			txtUsuario = new JTextField();
			txtUsuario.setBounds((int)(95*widthRatio),(int)(4*heightRatio), (int)(246*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelUsuario.add(txtUsuario);
			txtUsuario.setOpaque(false);
			txtUsuario.setEditable(false);
			txtUsuario.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtUsuario.setColumns(10);
			txtUsuario.setBorder(null);
			
			roundedGlowPanelPassword = new RoundedGlowPanel();
			roundedGlowPanelPassword.setLayout(null);
			roundedGlowPanelPassword.setRoundTopRight(60);
			roundedGlowPanelPassword.setRoundTopLeft(60);
			roundedGlowPanelPassword.setRoundBottomRight(60);
			roundedGlowPanelPassword.setRoundBottomLeft(60);
			roundedGlowPanelPassword.setGlowColor(Color.CYAN);
			roundedGlowPanelPassword.setGlowAlpha(170);
			roundedGlowPanelPassword.setForeground(Color.WHITE);
			roundedGlowPanelPassword.setBorder(null);
			roundedGlowPanelPassword.setBackground(Color.WHITE);
			roundedGlowPanelPassword.setBounds((int)(170*widthRatio),(int)(232*heightRatio), (int)(369*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPassword);
			
			txtPassword = new JTextField();
			txtPassword.setBounds((int)(102*widthRatio),(int)(2*heightRatio), (int)(246*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelPassword.add(txtPassword);
			txtPassword.setOpaque(false);
			txtPassword.setEditable(false);
			txtPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPassword.setColumns(10);
			txtPassword.setBorder(null);
			
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(78*widthRatio),(int)(22*heightRatio));
			roundedGlowPanelPassword.add(lblPassword);
			lblPassword.setOpaque(true);
			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
			lblPassword.setForeground(new Color(65, 105, 225));
			lblPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPassword.setBackground(Color.WHITE);
			
			roundedGlowCargo = new RoundedGlowPanel();
			roundedGlowCargo.setBounds((int)(170*widthRatio),(int)(298*heightRatio), (int)(106*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowCargo);
			roundedGlowCargo.setLayout(null);
			roundedGlowCargo.setRoundTopRight(60);
			roundedGlowCargo.setRoundTopLeft(60);
			roundedGlowCargo.setRoundBottomRight(60);
			roundedGlowCargo.setRoundBottomLeft(60);
			roundedGlowCargo.setGlowColor(Color.CYAN);
			roundedGlowCargo.setGlowAlpha(170);
			roundedGlowCargo.setForeground(Color.WHITE);
			roundedGlowCargo.setBorder(null);
			roundedGlowCargo.setBackground(Color.WHITE);
			
			JLabel lblCargo = new JLabel("Cargo");
			lblCargo.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(80*widthRatio),(int)(22*heightRatio));
			roundedGlowCargo.add(lblCargo);
			lblCargo.setOpaque(true);
			lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCargo.setForeground(new Color(65, 105, 225));
			lblCargo.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCargo.setBackground(Color.WHITE);
			
			cbxCargo = new JComboBox();
			cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"Secretario", "M\u00E9dico", "Admin"}));
			//cbxHora.setSelectedIndex(0);
			cbxCargo.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			cbxCargo.setBorder(null);
			cbxCargo.setBounds((int)(286*widthRatio),(int)(302*heightRatio), (int)(253*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(cbxCargo);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds((int)(0*widthRatio),(int)(780*heightRatio), (int)(1381*widthRatio),(int)(120*heightRatio));
		add(gradientPanel);
		gradientPanel.setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelModificar = new RoundedGlowPanel();
		roundedGlowPanelModificar.setLayout(null);
		roundedGlowPanelModificar.setRoundTopRight(60);
		roundedGlowPanelModificar.setRoundTopLeft(60);
		roundedGlowPanelModificar.setRoundBottomRight(60);
		roundedGlowPanelModificar.setRoundBottomLeft(60);
		roundedGlowPanelModificar.setGlowColor(Color.CYAN);
		roundedGlowPanelModificar.setGlowAlpha(170);
		roundedGlowPanelModificar.setForeground(Color.WHITE);
		roundedGlowPanelModificar.setBorder(null);
		roundedGlowPanelModificar.setBackground(Color.WHITE);
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
		
		/* RECORDAR CAMBIAR ESTO POR EL LABEL DE MODIFICAR, ADEMAS HAY QUE CAMBIAR LA ESTRUCTURA PARA QUE PONGA LOS DATOS DE LA
		 * PERSONA EN LOS CAMPOS DEL MISMO PANEL.
		 
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegPaciente mod_verPaciente = new RegPaciente(selected, false, false);
				mod_verPaciente.setModal(true);
				mod_verPaciente.setVisible(true);
				loadPacientes();
				JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Paciente", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		*/
		
		roundedGlowPanelEliminar = new RoundedGlowPanel();
		roundedGlowPanelEliminar.setLayout(null);
		roundedGlowPanelEliminar.setRoundTopRight(60);
		roundedGlowPanelEliminar.setRoundTopLeft(60);
		roundedGlowPanelEliminar.setRoundBottomRight(60);
		roundedGlowPanelEliminar.setRoundBottomLeft(60);
		roundedGlowPanelEliminar.setGlowColor(Color.CYAN);
		roundedGlowPanelEliminar.setGlowAlpha(170);
		roundedGlowPanelEliminar.setForeground(Color.WHITE);
		roundedGlowPanelEliminar.setBorder(null);
		roundedGlowPanelEliminar.setBackground(Color.WHITE);
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
		
		roundedGlowConsultar = new RoundedGlowPanel();
		roundedGlowConsultar.setLayout(null);
		roundedGlowConsultar.setRoundTopRight(60);
		roundedGlowConsultar.setRoundTopLeft(60);
		roundedGlowConsultar.setRoundBottomRight(60);
		roundedGlowConsultar.setRoundBottomLeft(60);
		roundedGlowConsultar.setGlowColor(Color.CYAN);
		roundedGlowConsultar.setGlowAlpha(170);
		roundedGlowConsultar.setForeground(Color.WHITE);
		roundedGlowConsultar.setBorder(null);
		roundedGlowConsultar.setBackground(Color.WHITE);
		roundedGlowConsultar.setBounds((int)(963*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowConsultar);
		
		lblRegistrar1 = new JLabel("Registrar");
		lblRegistrar1.setEnabled(false);
		lblRegistrar1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar1.setForeground(new Color(100, 149, 237));
		lblRegistrar1.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar1.setBackground(Color.WHITE);
		lblRegistrar1.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowConsultar.add(lblRegistrar1);
		
		/*RECORDAR CAMBIAR ESTO POR EL LABEL DE HISTORIAL, ADEMAS HAY QUE CREAR UN NUEVO PANEL QUE MUESTRE
		 *EL HISTORIAL DE LA PERSONA SELECCIONADA.
		 
		
		btnVerHistMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VerHistorialMedico verHistorialPaciente = new VerHistorialMedico(selected.getCodePaciente());
				verHistorialPaciente.setModal(true);
				verHistorialPaciente.setVisible(true);
				
			}
		});
		
		*/
		
		roundedGlowPanelBuscarPaciente = new RoundedGlowPanel();
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
		
		lblBuscar = new JLabel("Buscar:");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(100, 149, 237));
		lblBuscar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblBuscar.setBackground(Color.WHITE);
		lblBuscar.setBounds((int)(0*widthRatio),(int)(0*heightRatio), (int)(99*widthRatio),(int)(49*heightRatio));
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
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tablePacientes.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tablePacientes.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
			}
		});
		roundedGlowPanelBuscarPaciente.add(txtBuscarPaciente);
		txtBuscarPaciente.setColumns(10);
		
	
	}
}