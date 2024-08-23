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

public class VisualCita extends PanelSimulacionAnim {

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
	private JTextField txtCodePaciente;
	private JTextField txtPNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JDateChooser dateChooserNacim;
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	private JComboBox cbxElegirMedico;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JPanel panelDatosPersona;
	private RoundedPanel roundedPanelPNombre;
	private JTextField txtSnombre;
	private JTextField txtPApellido;
	private JLabel lblPApellido;
	private RoundedPanel roundedPanelPApellido;
	private JTextField txtSApellido;
	private JLabel lblSApellido;
	private RoundedPanel roundedPanelSApellido;
	private RoundedPanel roundedPanelCodePaciente;
	private JLabel lblCdigo;
	private JLabel lblCedula;
	private RoundedPanel roundedPanelCedula;
	private JLabel lblTelefono;
	private RoundedPanel roundedPanelTelefono;
	private JLabel lblFDeNac;
	private RoundedPanel roundedPanelFNac;
	private RoundedGlowPanel roundedGlowPanelCodePaciente;
	private RoundedGlowPanel roundedGlowPanelPApellido;
	private RoundedGlowPanel roundedGlowPanelSApellido;
	private RoundedGlowPanel roundedGlowPanelSNombre;
	private RoundedGlowPanel roundedGlowPanelPNombre;
	private RoundedGlowPanel roundedGlowPanelCedula;
	private RoundedGlowPanel roundedGlowPanelTelefono;
	private RoundedGlowPanel roundedGlowPanelFNacimiento;
	private RoundedGlowPanel roundedGlowPanelFechaCita;
	private JPanel panelTablaCita;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private RoundedGlowPanel roundedGlowConsultar;
	private JLabel lblConsultar;
	private RoundedGlowPanel roundedGlowPanelBuscarPaciente;
	private JLabel lblBuscar;
	private JTextField txtBuscarPaciente;
	private JLabel lblAgendar;
	private RoundedGlowPanel roundedGlowPanelCodeCita;
	private RoundedGlowPanel roundedGlowHora;
	private JTextField txtCodeCita;
	private JComboBox cbxHora;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualCita dialog = new VisualCita();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualCita() 
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
		
		panelTablaCita = new JPanel();
		panelTablaCita.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaCita.setBackground(new Color(255, 255, 255));
		panelTablaCita.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(555*widthRatio),(int)(515*heightRatio));
		add(panelTablaCita);
		panelTablaCita.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tablePacientes);
		panelTablaCita.add(scrollPane, BorderLayout.CENTER);
		
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
					lblConsultar.setEnabled(true);
					
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
			
			roundedPanelPNombre = new RoundedPanel();
			roundedPanelPNombre.setLayout(null);
			roundedPanelPNombre.setRoundTopRight(18);
			roundedPanelPNombre.setRoundTopLeft(18);
			roundedPanelPNombre.setRoundBottomRight(18);
			roundedPanelPNombre.setRoundBottomLeft(18);
			roundedPanelPNombre.setBackground(SystemColor.window);
			roundedPanelPNombre.setBounds((int)(91*widthRatio),(int)(83*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPNombre);
			
			txtPNombre = new JTextField();
			txtPNombre.setOpaque(false);
			txtPNombre.setEditable(false);
			txtPNombre.setBorder(null);
			txtPNombre.setBounds((int)(135*widthRatio),(int)(0*heightRatio), (int)(102*widthRatio),(int)(46*heightRatio));
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
			roundedPanelSNombre.setBounds((int)(91*widthRatio),(int)(148*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelSNombre);
			
			txtSnombre = new JTextField();
			txtSnombre.setOpaque(false);
			txtSnombre.setEditable(false);
			txtSnombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSnombre.setColumns(10);
			txtSnombre.setBorder(null);
			txtSnombre.setBounds((int)(135*widthRatio),(int)(0*heightRatio), (int)(102*widthRatio),(int)(46*heightRatio));
			roundedPanelSNombre.add(txtSnombre);
			
			JLabel lblSNombre = new JLabel("Segundo Nombre:");
			lblSNombre.setOpaque(true);
			lblSNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblSNombre.setForeground(new Color(65, 105, 225));
			lblSNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSNombre.setBackground(Color.WHITE);
			lblSNombre.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedPanelSNombre.add(lblSNombre);
			
			roundedPanelPApellido = new RoundedPanel();
			roundedPanelPApellido.setLayout(null);
			roundedPanelPApellido.setRoundTopRight(18);
			roundedPanelPApellido.setRoundTopLeft(18);
			roundedPanelPApellido.setRoundBottomRight(18);
			roundedPanelPApellido.setRoundBottomLeft(18);
			roundedPanelPApellido.setBackground(Color.WHITE);
			roundedPanelPApellido.setBounds((int)(91*widthRatio),(int)(213*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPApellido);
			
			txtPApellido = new JTextField();
			txtPApellido.setOpaque(false);
			txtPApellido.setEditable(false);
			txtPApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPApellido.setColumns(10);
			txtPApellido.setBorder(null);
			txtPApellido.setBounds((int)(135*widthRatio),(int)(0*heightRatio), (int)(102*widthRatio),(int)(46*heightRatio));
			roundedPanelPApellido.add(txtPApellido);
			
			lblPApellido = new JLabel("Primer Apellido:");
			lblPApellido.setOpaque(true);
			lblPApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblPApellido.setForeground(new Color(65, 105, 225));
			lblPApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPApellido.setBackground(Color.WHITE);
			lblPApellido.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(119*widthRatio),(int)(22*heightRatio));
			roundedPanelPApellido.add(lblPApellido);
			
			roundedPanelSApellido = new RoundedPanel();
			roundedPanelSApellido.setLayout(null);
			roundedPanelSApellido.setRoundTopRight(18);
			roundedPanelSApellido.setRoundTopLeft(18);
			roundedPanelSApellido.setRoundBottomRight(18);
			roundedPanelSApellido.setRoundBottomLeft(18);
			roundedPanelSApellido.setBackground(Color.WHITE);
			roundedPanelSApellido.setBounds((int)(91*widthRatio),(int)(278*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelSApellido);
			
			txtSApellido = new JTextField();
			txtSApellido.setOpaque(false);
			txtSApellido.setEditable(false);
			txtSApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSApellido.setColumns(10);
			txtSApellido.setBorder(null);
			txtSApellido.setBounds((int)(135*widthRatio),(int)(0*heightRatio), (int)(102*widthRatio),(int)(46*heightRatio));
			roundedPanelSApellido.add(txtSApellido);
			
			lblSApellido = new JLabel("Segundo Apellido:");
			lblSApellido.setOpaque(true);
			lblSApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblSApellido.setForeground(new Color(65, 105, 225));
			lblSApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSApellido.setBackground(Color.WHITE);
			lblSApellido.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(133*widthRatio),(int)(22*heightRatio));
			roundedPanelSApellido.add(lblSApellido);
			
			roundedPanelCodePaciente = new RoundedPanel();
			roundedPanelCodePaciente.setLayout(null);
			roundedPanelCodePaciente.setRoundTopRight(18);
			roundedPanelCodePaciente.setRoundTopLeft(18);
			roundedPanelCodePaciente.setRoundBottomRight(18);
			roundedPanelCodePaciente.setRoundBottomLeft(18);
			roundedPanelCodePaciente.setBackground(Color.WHITE);
			roundedPanelCodePaciente.setBounds((int)(382*widthRatio),(int)(83*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelCodePaciente);
			
			lblCdigo = new JLabel("C\u00F3digo:");
			lblCdigo.setOpaque(true);
			lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCdigo.setForeground(new Color(65, 105, 225));
			lblCdigo.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCdigo.setBackground(Color.WHITE);
			lblCdigo.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedPanelCodePaciente.add(lblCdigo);
			
			txtCodePaciente = new JTextField();
			txtCodePaciente.setBackground(new Color(255, 255, 255));
			txtCodePaciente.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(62*widthRatio),(int)(22*heightRatio));
			roundedPanelCodePaciente.add(txtCodePaciente);
			txtCodePaciente.setBorder(null);
			txtCodePaciente.setEditable(false);
			txtCodePaciente.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodePaciente.setColumns(10);
			txtCodePaciente.setText("P-"+Clinica.getInstance().getGeneradorCodePaciente());
			
			roundedGlowPanelCodePaciente = new RoundedGlowPanel();
			roundedGlowPanelCodePaciente.setBounds((int)(366*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(53*heightRatio));
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
			
			JLabel lblRegistroDeCitas = new JLabel("Registro de Citas");
			lblRegistroDeCitas.setOpaque(true);
			lblRegistroDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeCitas.setForeground(new Color(0, 0, 0));
			lblRegistroDeCitas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDeCitas.setBackground(new Color(240, 240, 240));
			lblRegistroDeCitas.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDeCitas);
			
			roundedPanelCedula = new RoundedPanel();
			roundedPanelCedula.setLayout(null);
			roundedPanelCedula.setRoundTopRight(18);
			roundedPanelCedula.setRoundTopLeft(18);
			roundedPanelCedula.setRoundBottomRight(18);
			roundedPanelCedula.setRoundBottomLeft(18);
			roundedPanelCedula.setBackground(Color.WHITE);
			roundedPanelCedula.setBounds((int)(380*widthRatio),(int)(148*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelCedula);
			
			lblCedula = new JLabel("C\u00E9dula:");
			lblCedula.setOpaque(true);
			lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCedula.setForeground(new Color(65, 105, 225));
			lblCedula.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCedula.setBackground(Color.WHITE);
			lblCedula.setBounds(0,(int)(11*heightRatio), (int)(64*widthRatio),(int)(22*heightRatio));
			roundedPanelCedula.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setOpaque(false);
			txtCedula.setEditable(false);
			txtCedula.setBorder(null);
			txtCedula.setBounds((int)(77*widthRatio),(int)(0*heightRatio), (int)(160*widthRatio),(int)(46*heightRatio));
			roundedPanelCedula.add(txtCedula);
			txtCedula.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtCedula.setColumns(10);
			
			roundedPanelTelefono = new RoundedPanel();
			roundedPanelTelefono.setLayout(null);
			roundedPanelTelefono.setRoundTopRight(18);
			roundedPanelTelefono.setRoundTopLeft(18);
			roundedPanelTelefono.setRoundBottomRight(18);
			roundedPanelTelefono.setRoundBottomLeft(18);
			roundedPanelTelefono.setBackground(Color.WHITE);
			roundedPanelTelefono.setBounds((int)(380*widthRatio),(int)(213*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelTelefono);
			
			lblTelefono = new JLabel("Tel\u00E9fono:");
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(new Color(65, 105, 225));
			lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTelefono.setBackground(Color.WHITE);
			lblTelefono.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedPanelTelefono.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setOpaque(false);
			txtTelefono.setEditable(false);
			txtTelefono.setBorder(null);
			txtTelefono.setBounds((int)(77*widthRatio),0, (int)(160*widthRatio),(int)(46*heightRatio));
			roundedPanelTelefono.add(txtTelefono);
			txtTelefono.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtTelefono.setColumns(10);
			
			roundedPanelFNac = new RoundedPanel();
			roundedPanelFNac.setLayout(null);
			roundedPanelFNac.setRoundTopRight(18);
			roundedPanelFNac.setRoundTopLeft(18);
			roundedPanelFNac.setRoundBottomRight(18);
			roundedPanelFNac.setRoundBottomLeft(18);
			roundedPanelFNac.setBackground(Color.WHITE);
			roundedPanelFNac.setBounds((int)(380*widthRatio),(int)(278*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelFNac);
			
			lblFDeNac = new JLabel("F. Nacimiento");
			lblFDeNac.setOpaque(true);
			lblFDeNac.setHorizontalAlignment(SwingConstants.CENTER);
			lblFDeNac.setForeground(new Color(65, 105, 225));
			lblFDeNac.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblFDeNac.setBackground(Color.WHITE);
			lblFDeNac.setBounds((int)(0*widthRatio),(int)(11*heightRatio), (int)(112*widthRatio),(int)(22*heightRatio));
			roundedPanelFNac.add(lblFDeNac);
			
			RoundedPanel roundedPanelFechaCita = new RoundedPanel();
			roundedPanelFechaCita.setLayout(null);
			roundedPanelFechaCita.setRoundTopRight(18);
			roundedPanelFechaCita.setRoundTopLeft(18);
			roundedPanelFechaCita.setRoundBottomRight(18);
			roundedPanelFechaCita.setRoundBottomLeft(18);
			roundedPanelFechaCita.setBackground(Color.WHITE);
			roundedPanelFechaCita.setBounds((int)(103*widthRatio),(int)(437*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelFechaCita);
			
			JLabel lblFechaCita = new JLabel("Fecha");
			lblFechaCita.setOpaque(true);
			lblFechaCita.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaCita.setForeground(new Color(65, 105, 225));
			lblFechaCita.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblFechaCita.setBackground(Color.WHITE);
			lblFechaCita.setBounds((int)(3*widthRatio),(int)(13*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedPanelFechaCita.add(lblFechaCita);
			
			cbxElegirMedico = new JComboBox();
			cbxElegirMedico.setBounds((int)(366*widthRatio),(int)(437*heightRatio), (int)(277*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(cbxElegirMedico);
			cbxElegirMedico.setBorder(null);
			cbxElegirMedico.setModel(new DefaultComboBoxModel(new String[] {"Elegir M\u00E9dico"}));
			cbxElegirMedico.setSelectedIndex(0);
			cbxElegirMedico.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setOpaque(false);
			dateChooserNacim.setEnabled(false);
			dateChooserNacim.setBounds((int)(522*widthRatio),(int)(278*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(dateChooserNacim);
			BorderLayout borderLayout = (BorderLayout) dateChooserNacim.getLayout();
			dateChooserNacim.setBackground(new Color(255, 255, 255));
			dateChooserNacim.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserNacim.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserNacim.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
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
			roundedGlowPanelPNombre.setBounds((int)(77*widthRatio),(int)(79*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPNombre);
			
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
			roundedGlowPanelSNombre.setBounds((int)(77*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSNombre);
			
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
			roundedGlowPanelPApellido.setBounds((int)(77*widthRatio),(int)(209*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPApellido);
			
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
			roundedGlowPanelSApellido.setBounds((int)(77*widthRatio),(int)(274*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelSApellido);
			
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
			roundedGlowPanelCedula.setBounds((int)(366*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCedula);
			
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
			roundedGlowPanelTelefono.setBounds((int)(366*widthRatio),(int)(209*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelTelefono);
			
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
			roundedGlowPanelFNacimiento.setBounds((int)(366*widthRatio),(int)(274*heightRatio), (int)(135*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelFNacimiento);
			
			roundedGlowPanelFechaCita = new RoundedGlowPanel();
			roundedGlowPanelFechaCita.setLayout(null);
			roundedGlowPanelFechaCita.setRoundTopRight(60);
			roundedGlowPanelFechaCita.setRoundTopLeft(60);
			roundedGlowPanelFechaCita.setRoundBottomRight(60);
			roundedGlowPanelFechaCita.setRoundBottomLeft(60);
			roundedGlowPanelFechaCita.setGlowColor(Color.CYAN);
			roundedGlowPanelFechaCita.setGlowAlpha(170);
			roundedGlowPanelFechaCita.setForeground(Color.WHITE);
			roundedGlowPanelFechaCita.setBorder(null);
			roundedGlowPanelFechaCita.setBackground(Color.WHITE);
			roundedGlowPanelFechaCita.setBounds((int)(89*widthRatio),(int)(433*heightRatio), (int)(135*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelFechaCita);
			
			roundedGlowPanelCodeCita = new RoundedGlowPanel();
			roundedGlowPanelCodeCita.setLayout(null);
			roundedGlowPanelCodeCita.setRoundTopRight(60);
			roundedGlowPanelCodeCita.setRoundTopLeft(60);
			roundedGlowPanelCodeCita.setRoundBottomRight(60);
			roundedGlowPanelCodeCita.setRoundBottomLeft(60);
			roundedGlowPanelCodeCita.setGlowColor(Color.CYAN);
			roundedGlowPanelCodeCita.setGlowAlpha(170);
			roundedGlowPanelCodeCita.setForeground(Color.WHITE);
			roundedGlowPanelCodeCita.setBorder(null);
			roundedGlowPanelCodeCita.setBackground(Color.WHITE);
			roundedGlowPanelCodeCita.setBounds((int)(183*widthRatio),(int)(368*heightRatio), (int)(170*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelCodeCita);
			
			RoundedPanel roundedPanelCodeCita = new RoundedPanel();
			roundedPanelCodeCita.setBounds((int)(18*widthRatio),(int)(3*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelCodeCita.add(roundedPanelCodeCita);
			roundedPanelCodeCita.setLayout(null);
			roundedPanelCodeCita.setRoundTopRight(18);
			roundedPanelCodeCita.setRoundTopLeft(18);
			roundedPanelCodeCita.setRoundBottomRight(18);
			roundedPanelCodeCita.setRoundBottomLeft(18);
			roundedPanelCodeCita.setBackground(Color.WHITE);
			
			JLabel lblCodeCita = new JLabel("C\u00F3digo:");
			lblCodeCita.setOpaque(true);
			lblCodeCita.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodeCita.setForeground(new Color(65, 105, 225));
			lblCodeCita.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCodeCita.setBackground(Color.WHITE);
			lblCodeCita.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeCita.add(lblCodeCita);
			
			txtCodeCita = new JTextField();
			txtCodeCita.setText("N-"+String.valueOf(Clinica.getInstance().getGeneradorNumCita()));
			txtCodeCita.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeCita.setEditable(false);
			txtCodeCita.setColumns(10);
			txtCodeCita.setBorder(null);
			txtCodeCita.setBackground(Color.WHITE);
			txtCodeCita.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(48*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeCita.add(txtCodeCita);
			
			roundedGlowHora = new RoundedGlowPanel();
			roundedGlowHora.setBounds((int)(366*widthRatio),(int)(368*heightRatio), (int)(135*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowHora);
			roundedGlowHora.setLayout(null);
			roundedGlowHora.setRoundTopRight(60);
			roundedGlowHora.setRoundTopLeft(60);
			roundedGlowHora.setRoundBottomRight(60);
			roundedGlowHora.setRoundBottomLeft(60);
			roundedGlowHora.setGlowColor(Color.CYAN);
			roundedGlowHora.setGlowAlpha(170);
			roundedGlowHora.setForeground(Color.WHITE);
			roundedGlowHora.setBorder(null);
			roundedGlowHora.setBackground(Color.WHITE);
			
			RoundedPanel roundedPanelHora = new RoundedPanel();
			roundedPanelHora.setLayout(null);
			roundedPanelHora.setRoundTopRight(18);
			roundedPanelHora.setRoundTopLeft(18);
			roundedPanelHora.setRoundBottomRight(18);
			roundedPanelHora.setRoundBottomLeft(18);
			roundedPanelHora.setBackground(Color.WHITE);
			roundedPanelHora.setBounds((int)(12*widthRatio),(int)(3*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			roundedGlowHora.add(roundedPanelHora);
			
			JLabel lblHora = new JLabel("Hora");
			lblHora.setOpaque(true);
			lblHora.setHorizontalAlignment(SwingConstants.CENTER);
			lblHora.setForeground(new Color(65, 105, 225));
			lblHora.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblHora.setBackground(Color.WHITE);
			lblHora.setBounds((int)(0*widthRatio),(int)(11*heightRatio), (int)(112*widthRatio),(int)(22*heightRatio));
			roundedPanelHora.add(lblHora);
			
			JDateChooser dateChooserFechaCita = new JDateChooser();
			dateChooserFechaCita.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserFechaCita.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserFechaCita.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserFechaCita.setBackground(Color.WHITE);
			dateChooserFechaCita.setBounds((int)(235*widthRatio),(int)(437*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(dateChooserFechaCita);
			
			cbxHora = new JComboBox();
			//cbxHora.setSelectedIndex(0);
			cbxHora.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			cbxHora.setBorder(null);
			cbxHora.setBounds((int)(522*widthRatio),(int)(368*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(cbxHora);
			

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
		
		/* RECORDAR CAMBIAR ESTO POR EL LABEL DE ELIMINAR, CREO QUE NO HAY QUE HACER CAMBIOS EN LA ESTRUCTURA PERO
		 * VERIFICAR.
		  
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al Paciente con código: <" + selected.getCodePaciente() + ">?", "Eliminar Paciente", JOptionPane.OK_CANCEL_OPTION);
				
				if (Option == JOptionPane.OK_OPTION) {
					
					Clinica.getInstance().eliminarPaciente(selected);
					loadPacientes();
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					btnVerHistMed.setEnabled(false);
				}
				
			}
		});
		
		*/
		
		RoundedGlowPanel roundedGlowPanelAgendar = new RoundedGlowPanel();
		roundedGlowPanelAgendar.setBounds((int)(817*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelAgendar);
		roundedGlowPanelAgendar.setLayout(null);
		roundedGlowPanelAgendar.setRoundTopRight(60);
		roundedGlowPanelAgendar.setRoundTopLeft(60);
		roundedGlowPanelAgendar.setRoundBottomRight(60);
		roundedGlowPanelAgendar.setRoundBottomLeft(60);
		roundedGlowPanelAgendar.setGlowColor(Color.CYAN);
		roundedGlowPanelAgendar.setGlowAlpha(170);
		roundedGlowPanelAgendar.setForeground(Color.WHITE);
		roundedGlowPanelAgendar.setBorder(null);
		roundedGlowPanelAgendar.setBackground(Color.WHITE);
		
//		lblRegistrar.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				try {
//					if (paciente == null) {
//						
//						if (rdbtnMasculino.isSelected()) {
//							
//							sexoPaciente = 'M';
//						}
//						else {
//							
//							sexoPaciente = 'F';
//						}
//						
//						nombre = txtNombre.getText();
//						cedula = txtCedula.getText();
//						telefono = txtTelefono.getText();
//						fechaNacimiento = dateChooserNacim.getDate();
//						peso = Float.parseFloat(txtPeso.getText());
//						altura = Float.parseFloat(txtAltura.getText());
//						
//						if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()) {
//							throw new ValidarCampo("Debe llenar los campos obligatorios.");
//						}
//						
//						if (fechaNacimiento == null) {
//							throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
//						}
//						
//						if (cbxTipoSangre.getSelectedIndex() == 0) {
//							throw new ValidarCampo("No ha seleccionado un tipo de sangre.");
//						}
//						
//						if (peso <= 0 || altura <= 0) {
//							throw new ValidarCampo("Las entradas del peso o la altura no pueden ser negativas.");
//						} 
//						
//						if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
//							throw new ValidarCampo("Debe seleccionar un sexo.");
//						}
//						
//						Paciente nuevoPaciente = new Paciente(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
//								 sexoPaciente, txtTelefono.getText(), txtareaDireccion.getText(), txtCodePaciente.getText(),
//								 cbxTipoSangre.getSelectedItem().toString(), new Float(txtAltura.getText()), new Float(txtPeso.getText()),
//								 txtareaAlergias.getText(), txtareaInfoRelevante.getText());
//						
//						codePacienteRegistrado = nuevoPaciente.getCodePaciente();
//						ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(null);
//						elegirVacunas.setModal(true);
//						elegirVacunas.setVisible(true);
//						nuevoPaciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());
//						Clinica.getInstance().insertarPaciente(nuevoPaciente);
//						JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Paciente", JOptionPane.INFORMATION_MESSAGE);
//						
//						if (regUnSoloPaciente) {
//							
//							dispose();
//						}
//						else {
//							clean();
//						}
//						
//					}
//					else {
//				
//						if (rdbtnMasculino.isSelected()) {
//							
//							sexoPaciente = 'M';
//						}
//						else {
//							
//							sexoPaciente = 'F';
//						}
//						
//						paciente.setTipoDeSangre(cbxTipoSangre.getSelectedItem().toString());
//						paciente.setAltura(new Float(txtAltura.getText()));
//						paciente.setPeso(new Float(txtPeso.getText()));
//						paciente.setTelefono(txtTelefono.getText());
//						paciente.setDireccion(txtareaDireccion.getText());
//						paciente.setAlergias(txtareaAlergias.getText());
//						
//						ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(paciente);
//						elegirVacunas.setModal(true);
//						elegirVacunas.setVisible(true);
//						paciente.getMisVacunas().clear();
//						paciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());							
//						Clinica.getInstance().actualizarPaciente(paciente);
//						dispose();
//					}
//				} catch (ValidarCampo e2) {
//					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//					e2.printStackTrace();
//					txtNombre.grabFocus();
//				}
//				catch (NumberFormatException e3) {
//					JOptionPane.showMessageDialog(null, "Ingrese datos válidos para la altura y el peso.", "Error", JOptionPane.ERROR_MESSAGE);
//					txtPeso.grabFocus();
//				}
//				
//			}
//		});
		
		lblAgendar = new JLabel("Agendar");
		lblAgendar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendar.setForeground(new Color(100, 149, 237));
		lblAgendar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblAgendar.setEnabled(false);
		lblAgendar.setBackground(Color.WHITE);
		lblAgendar.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelAgendar.add(lblAgendar);
		
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
		
		lblConsultar = new JLabel("Consultar");
		lblConsultar.setEnabled(false);
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setForeground(new Color(100, 149, 237));
		lblConsultar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblConsultar.setBackground(Color.WHITE);
		lblConsultar.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowConsultar.add(lblConsultar);
		
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