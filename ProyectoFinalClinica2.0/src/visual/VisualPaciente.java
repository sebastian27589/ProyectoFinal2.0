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

public class VisualPaciente extends JPanel {

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
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JTextArea txtareaDireccion;
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	private JComboBox cbxTipoSangre;
	private JTextArea txtareaAlergias;
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
	private JLabel lblAltura;
	private RoundedPanel roundedPanelAltura;
	private RoundedPanel roundedPanelPeso;
	private JLabel lblFDeNac;
	private RoundedPanel roundedPanelFNac;
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
	private RoundedPanel roundedPanelDireccion;
	private JLabel lblAlergia;
	private RoundedPanel roundedPanelAlergia;
	private RoundedGlowPanel roundedGlowPanelDireccion;
	private JPanel panelTablaPersona;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private RoundedGlowPanel roundedGlowHistorial;
	private JLabel lblHistorial;
	private RoundedGlowPanel roundedGlowPanelBuscarPaciente;
	private JLabel lblBuscar;
	private JTextField txtBuscarPaciente;
	private JLabel lblRegistrar_1;
	private JSpinner spnAltura;
	private JSpinner spnPeso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegPaciente dialog = new RegPaciente(null, false, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualPaciente() 
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
		contentPanel.setSize(new Dimension((int)(1400*widthRatio),(int)(900*heightRatio)));
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VisualPaciente.class.getResource("/Imagenes/isometric-mri-room-in-hospital (1).png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(65, 105, 225));
		label.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		label.setBackground(Color.WHITE);
		label.setBounds((int)(923*widthRatio), (int)(655*heightRatio), (int)(477*widthRatio), (int)(248*heightRatio));
		contentPanel.add(label);
		
		panelTablaPersona = new JPanel();
		panelTablaPersona.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaPersona.setBackground(new Color(255, 255, 255));
		panelTablaPersona.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(574*widthRatio),(int)(515*heightRatio));
		contentPanel.add(panelTablaPersona);
		panelTablaPersona.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tablePacientes);
		panelTablaPersona.add(scrollPane, BorderLayout.CENTER);
		
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
					lblHistorial.setEnabled(true);
					
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
			contentPanel.add(panelDatosPersona);
			panelDatosPersona.setBackground(new Color(240, 240, 240));
			panelDatosPersona.setLayout(null);
			
			rdbtnMasculino = new JRadioButton("Hombre");
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculino.setBounds((int)(260*widthRatio),(int)(464*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			
			rdbtnFemenino = new JRadioButton("Mujer");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
				}
			});
			rdbtnFemenino.setBounds((int)(379*widthRatio),(int)(464*heightRatio), (int)(93*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			
			roundedPanelPNombre = new RoundedPanel();
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
			
			txtSnombre = new JTextField();
			txtSnombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSnombre.setColumns(10);
			txtSnombre.setBorder(null);
			txtSnombre.setBounds((int)(136*widthRatio),0, (int)(101*widthRatio),(int)(46*heightRatio));
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
			roundedPanelPApellido.setBounds((int)(91*widthRatio),(int)(270*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPApellido);
			
			txtPApellido = new JTextField();
			txtPApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtPApellido.setColumns(10);
			txtPApellido.setBorder(null);
			txtPApellido.setBounds((int)(126*widthRatio),0, (int)(111*widthRatio),(int)(46*heightRatio));
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
			roundedPanelSApellido.setBounds((int)(91*widthRatio),(int)(329*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelSApellido);
			
			txtSApellido = new JTextField();
			txtSApellido.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSApellido.setColumns(10);
			txtSApellido.setBorder(null);
			txtSApellido.setBounds((int)(136*widthRatio),0, (int)(101*widthRatio),(int)(46*heightRatio));
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
			roundedPanelCodePaciente.setBounds((int)(200*widthRatio),(int)(87*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
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
			roundedGlowPanelCodePaciente.setBounds((int)(184*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(61*heightRatio));
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
			
			JLabel lblRegistroDePersonas = new JLabel("Registro de Personas");
			lblRegistroDePersonas.setOpaque(true);
			lblRegistroDePersonas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDePersonas.setForeground(new Color(0, 0, 0));
			lblRegistroDePersonas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDePersonas.setBackground(new Color(240, 240, 240));
			lblRegistroDePersonas.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDePersonas);
			
			roundedPanelCedula = new RoundedPanel();
			roundedPanelCedula.setLayout(null);
			roundedPanelCedula.setRoundTopRight(18);
			roundedPanelCedula.setRoundTopLeft(18);
			roundedPanelCedula.setRoundBottomRight(18);
			roundedPanelCedula.setRoundBottomLeft(18);
			roundedPanelCedula.setBackground(Color.WHITE);
			roundedPanelCedula.setBounds((int)(379*widthRatio),(int)(87*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
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
			txtCedula.setBorder(null);
			txtCedula.setBounds((int)(64*widthRatio),0, (int)(173*widthRatio),(int)(46*heightRatio));
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
			roundedPanelTelefono.setBounds((int)(379*widthRatio),(int)(150*heightRatio), (int)(249*widthRatio),(int)(46*heightRatio));
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
			txtTelefono.setBorder(null);
			txtTelefono.setBounds((int)(77*widthRatio),0, (int)(160*widthRatio),(int)(46*heightRatio));
			roundedPanelTelefono.add(txtTelefono);
			txtTelefono.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			txtTelefono.setColumns(10);
			
			roundedPanelAltura = new RoundedPanel();
			roundedPanelAltura.setLayout(null);
			roundedPanelAltura.setRoundTopRight(18);
			roundedPanelAltura.setRoundTopLeft(18);
			roundedPanelAltura.setRoundBottomRight(18);
			roundedPanelAltura.setRoundBottomLeft(18);
			roundedPanelAltura.setBackground(Color.WHITE);
			roundedPanelAltura.setBounds((int)(379*widthRatio),(int)(273*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelAltura);
			
			lblAltura = new JLabel("Altura:");
			lblAltura.setOpaque(true);
			lblAltura.setHorizontalAlignment(SwingConstants.CENTER);
			lblAltura.setForeground(new Color(65, 105, 225));
			lblAltura.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblAltura.setBackground(Color.WHITE);
			lblAltura.setBounds((int)(2*widthRatio),(int)(9*heightRatio), (int)(58*widthRatio),(int)(22*heightRatio));
			roundedPanelAltura.add(lblAltura);
			
			spnAltura = new JSpinner();
			spnAltura.setBorder(null);
			spnAltura.setBounds((int)(65*widthRatio),0, (int)(40*widthRatio),(int)(46*heightRatio));
			roundedPanelAltura.add(spnAltura);
			
			roundedPanelPeso = new RoundedPanel();
			roundedPanelPeso.setLayout(null);
			roundedPanelPeso.setRoundTopRight(18);
			roundedPanelPeso.setRoundTopLeft(18);
			roundedPanelPeso.setRoundBottomRight(18);
			roundedPanelPeso.setRoundBottomLeft(18);
			roundedPanelPeso.setBackground(Color.WHITE);
			roundedPanelPeso.setBounds((int)(521*widthRatio),(int)(273*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelPeso);
			
			JLabel lblPeso = new JLabel("Peso:");
			lblPeso.setOpaque(true);
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setForeground(new Color(65, 105, 225));
			lblPeso.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblPeso.setBackground(Color.WHITE);
			lblPeso.setBounds((int)(2*widthRatio),(int)(9*heightRatio), (int)(58*widthRatio),(int)(22*heightRatio));
			roundedPanelPeso.add(lblPeso);
			
			spnPeso = new JSpinner();
			spnPeso.setBorder(null);
			spnPeso.setBounds((int)(65*widthRatio),0, (int)(40*widthRatio),(int)(46*heightRatio));
			roundedPanelPeso.add(spnPeso);
			
			roundedPanelFNac = new RoundedPanel();
			roundedPanelFNac.setLayout(null);
			roundedPanelFNac.setRoundTopRight(18);
			roundedPanelFNac.setRoundTopLeft(18);
			roundedPanelFNac.setRoundBottomRight(18);
			roundedPanelFNac.setRoundBottomLeft(18);
			roundedPanelFNac.setBackground(Color.WHITE);
			roundedPanelFNac.setBounds((int)(379*widthRatio),(int)(211*heightRatio), (int)(111*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelFNac);
			
			lblFDeNac = new JLabel("F. Nacimiento");
			lblFDeNac.setOpaque(true);
			lblFDeNac.setHorizontalAlignment(SwingConstants.CENTER);
			lblFDeNac.setForeground(new Color(65, 105, 225));
			lblFDeNac.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblFDeNac.setBackground(Color.WHITE);
			lblFDeNac.setBounds((int)(0*widthRatio),(int)(11*heightRatio), (int)(112*widthRatio),(int)(22*heightRatio));
			roundedPanelFNac.add(lblFDeNac);
			
			RoundedPanel roundedPanelTSangre = new RoundedPanel();
			roundedPanelTSangre.setLayout(null);
			roundedPanelTSangre.setRoundTopRight(18);
			roundedPanelTSangre.setRoundTopLeft(18);
			roundedPanelTSangre.setRoundBottomRight(18);
			roundedPanelTSangre.setRoundBottomLeft(18);
			roundedPanelTSangre.setBackground(Color.WHITE);
			roundedPanelTSangre.setBounds((int)(379*widthRatio),(int)(329*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(roundedPanelTSangre);
			
			JLabel lblTSangre = new JLabel("T. Sangre");
			lblTSangre.setOpaque(true);
			lblTSangre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTSangre.setForeground(new Color(65, 105, 225));
			lblTSangre.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTSangre.setBackground(Color.WHITE);
			lblTSangre.setBounds((int)(3*widthRatio),(int)(13*heightRatio), (int)(73*widthRatio),(int)(22*heightRatio));
			roundedPanelTSangre.add(lblTSangre);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setBounds((int)(521*widthRatio),(int)(331*heightRatio), (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(cbxTipoSangre);
			cbxTipoSangre.setBorder(null);
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "A+", "A", "B+", "B", "AB+", "AB", "O+", "O"}));
			cbxTipoSangre.setSelectedIndex(0);
			cbxTipoSangre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds((int)(521*widthRatio),(int)(211*heightRatio), (int)(118*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(dateChooserNacim);
			BorderLayout borderLayout = (BorderLayout) dateChooserNacim.getLayout();
			dateChooserNacim.setBackground(new Color(255, 255, 255));
			dateChooserNacim.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			dateChooserNacim.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserNacim.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			
			RoundedGlowPanel panel11 = new RoundedGlowPanel();
			panel_1.setBounds(108, 462, 145, 59);
			panelDatosPersona.add(panel_1);
			
			RoundedPanel roundedPanel_1 = new RoundedPanel();
			roundedPanel_1.setLayout(null);
			roundedPanel_1.setRoundTopRight(18);
			roundedPanel_1.setRoundTopLeft(18);
			roundedPanel_1.setRoundBottomRight(18);
			roundedPanel_1.setRoundBottomLeft(18);
			roundedPanel_1.setBackground(Color.WHITE);
			roundedPanel_1.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosPersona.add(roundedPanel_1);
			
			JLabel lblImagen = new JLabel("AQUI VA LA TABLA DE ASIGNAR VACUNAS");
			lblImagen.setOpaque(true);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagen.setForeground(new Color(65, 105, 225));
			lblImagen.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblImagen.setBackground(Color.WHITE);
			lblImagen.setBounds((int)(203*widthRatio),(int)(101*heightRatio), (int)(348*widthRatio),(int)(22*heightRatio));
			roundedPanel_1.add(lblImagen);
			
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
			roundedGlowPanelPNombre.setBounds((int)(77*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
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
			roundedGlowPanelSNombre.setBounds((int)(77*widthRatio),(int)(205*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
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
			roundedGlowPanelPApellido.setBounds((int)(77*widthRatio),(int)(266*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
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
			roundedGlowPanelSApellido.setBounds((int)(77*widthRatio),(int)(327*heightRatio), (int)(277*widthRatio),(int)(53*heightRatio));
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
			roundedGlowPanelCedula.setBounds((int)(365*widthRatio),(int)(79*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
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
			roundedGlowPanelTelefono.setBounds((int)(365*widthRatio),(int)(144*heightRatio), (int)(277*widthRatio),(int)(59*heightRatio));
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
			roundedGlowPanelFNacimiento.setBounds((int)(365*widthRatio),(int)(205*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelFNacimiento);
			
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
			roundedGlowPanelAltura.setBounds((int)(365*widthRatio),(int)(266*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelAltura);
			
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
			roundedGlowPanelPeso.setBounds((int)(507*widthRatio),(int)(266*heightRatio), (int)(135*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedGlowPanelPeso);
			
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
			roundedGlowPanelTSangre.setBounds((int)(365*widthRatio),(int)(327*heightRatio), (int)(135*widthRatio),(int)(53*heightRatio));
			panelDatosPersona.add(roundedGlowPanelTSangre);
			
			roundedPanelDireccion = new RoundedPanel();
			roundedPanelDireccion.setLayout(null);
			roundedPanelDireccion.setRoundTopRight(18);
			roundedPanelDireccion.setRoundTopLeft(18);
			roundedPanelDireccion.setRoundBottomRight(18);
			roundedPanelDireccion.setRoundBottomLeft(18);
			roundedPanelDireccion.setBackground(Color.WHITE);
			roundedPanelDireccion.setBounds((int)(91*widthRatio),(int)(388*heightRatio), (int)(249*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedPanelDireccion);
			
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setOpaque(true);
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setForeground(new Color(65, 105, 225));
			lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblDireccion.setBackground(Color.WHITE);
			lblDireccion.setBounds(0,(int)(18*heightRatio), (int)(79*widthRatio),(int)(22*heightRatio));
			roundedPanelDireccion.add(lblDireccion);
			
			txtareaDireccion = new JTextArea();
			txtareaDireccion.setBounds((int)(86*widthRatio),0, (int)(151*widthRatio),(int)(59*heightRatio));
			roundedPanelDireccion.add(txtareaDireccion);
			txtareaDireccion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			txtareaDireccion.setLineWrap(true);
			txtareaDireccion.setWrapStyleWord(true);
			
			roundedPanelAlergia = new RoundedPanel();
			roundedPanelAlergia.setLayout(null);
			roundedPanelAlergia.setRoundTopRight(18);
			roundedPanelAlergia.setRoundTopLeft(18);
			roundedPanelAlergia.setRoundBottomRight(18);
			roundedPanelAlergia.setRoundBottomLeft(18);
			roundedPanelAlergia.setBackground(Color.WHITE);
			roundedPanelAlergia.setBounds((int)(379*widthRatio),(int)(388*heightRatio), (int)(249*widthRatio),(int)(59*heightRatio));
			panelDatosPersona.add(roundedPanelAlergia);
			
			lblAlergia = new JLabel("Alergia:");
			lblAlergia.setOpaque(true);
			lblAlergia.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlergia.setForeground(new Color(65, 105, 225));
			lblAlergia.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblAlergia.setBackground(Color.WHITE);
			lblAlergia.setBounds(0,(int)(18*heightRatio), (int)(67*widthRatio),(int)(22*heightRatio));
			roundedPanelAlergia.add(lblAlergia);
			
			txtareaAlergias = new JTextArea();
			txtareaAlergias.setBounds((int)(67*widthRatio),0, (int)(170*widthRatio),(int)(59*heightRatio));
			roundedPanelAlergia.add(txtareaAlergias);
			txtareaAlergias.setWrapStyleWord(true);
			txtareaAlergias.setLineWrap(true);
			txtareaAlergias.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
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
			roundedGlowPanelDireccion.setBounds((int)(77*widthRatio),(int)(383*heightRatio), (int)(277*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanelDireccion);
			
			RoundedGlowPanel roundedGlowPanel = new RoundedGlowPanel();
			roundedGlowPanel.setLayout(null);
			roundedGlowPanel.setRoundTopRight(60);
			roundedGlowPanel.setRoundTopLeft(60);
			roundedGlowPanel.setRoundBottomRight(60);
			roundedGlowPanel.setRoundBottomLeft(60);
			roundedGlowPanel.setGlowColor(Color.CYAN);
			roundedGlowPanel.setGlowAlpha(170);
			roundedGlowPanel.setForeground(Color.WHITE);
			roundedGlowPanel.setBorder(null);
			roundedGlowPanel.setBackground(Color.WHITE);
			roundedGlowPanel.setBounds((int)(363*widthRatio),(int)(383*heightRatio), (int)(277*widthRatio),(int)(70*heightRatio));
			panelDatosPersona.add(roundedGlowPanel);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds(0, (int)(780*heightRatio), (int)(1400*widthRatio), (int)(170*heightRatio));
		contentPanel.add(gradientPanel);
		gradientPanel.setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelRegistro = new RoundedGlowPanel();
		roundedGlowPanelRegistro.setLayout(null);
		roundedGlowPanelRegistro.setRoundTopRight(60);
		roundedGlowPanelRegistro.setRoundTopLeft(60);
		roundedGlowPanelRegistro.setRoundBottomRight(60);
		roundedGlowPanelRegistro.setRoundBottomLeft(60);
		roundedGlowPanelRegistro.setGlowColor(Color.CYAN);
		roundedGlowPanelRegistro.setGlowAlpha(170);
		roundedGlowPanelRegistro.setForeground(Color.WHITE);
		roundedGlowPanelRegistro.setBorder(null);
		roundedGlowPanelRegistro.setBackground(Color.WHITE);
		roundedGlowPanelRegistro.setBounds((int)(1114*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowPanelRegistro);
		
		lblModificar = new JLabel("Modificar");
		lblModificar.setEnabled(false);
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setForeground(new Color(60, 179, 113));
		lblModificar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblModificar.setBackground(Color.WHITE);
		lblModificar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistro.add(lblModificar);
		
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
		roundedGlowPanelEliminar.setBounds((int)(1256*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowPanelEliminar);
		
		lblEliminar = new JLabel("Eliminar");
		lblEliminar.setEnabled(false);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(new Color(220, 20, 60));
		lblEliminar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblEliminar.setBackground(Color.WHITE);
		lblEliminar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
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
		
		RoundedGlowPanel roundedGlowPanelRegistrar = new RoundedGlowPanel();
		roundedGlowPanelRegistrar.setBounds((int)(824*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowPanelRegistrar);
		roundedGlowPanelRegistrar.setLayout(null);
		roundedGlowPanelRegistrar.setRoundTopRight(60);
		roundedGlowPanelRegistrar.setRoundTopLeft(60);
		roundedGlowPanelRegistrar.setRoundBottomRight(60);
		roundedGlowPanelRegistrar.setRoundBottomLeft(60);
		roundedGlowPanelRegistrar.setGlowColor(Color.CYAN);
		roundedGlowPanelRegistrar.setGlowAlpha(170);
		roundedGlowPanelRegistrar.setForeground(Color.WHITE);
		roundedGlowPanelRegistrar.setBorder(null);
		roundedGlowPanelRegistrar.setBackground(Color.WHITE);
		
//		lblRegistrar = new JLabel("Registrar");
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

		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setForeground(new Color(100, 149, 237));
		lblRegistrar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar.setBackground(Color.WHITE);
		lblRegistrar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar);
		
		lblRegistrar_1 = new JLabel("Registrar");
		lblRegistrar_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar_1.setForeground(new Color(100, 149, 237));
		lblRegistrar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar_1.setEnabled(false);
		lblRegistrar_1.setBackground(Color.WHITE);
		lblRegistrar_1.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar_1);
		
		roundedGlowHistorial = new RoundedGlowPanel();
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
		roundedGlowHistorial.setBounds((int)(970*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowHistorial);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setEnabled(false);
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setForeground(new Color(100, 149, 237));
		lblHistorial.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblHistorial.setBackground(Color.WHITE);
		lblHistorial.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowHistorial.add(lblHistorial);
		
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
		roundedGlowPanelBuscarPaciente.setBounds((int)(824*widthRatio),(int)(541*heightRatio), (int)(564*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowPanelBuscarPaciente);
		
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
		txtBuscarPaciente.setBounds((int)(101*widthRatio), 0, (int)(438*widthRatio),(int)(49*heightRatio));
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
		
		if (pacientesEspecificosAMostrar == null) {
			
			loadPacientes();
		}
		else {
			
			loadPacientesEspecificos();
			lblBuscar.setVisible(false);
			txtBuscarPaciente.setVisible(false);
			lblModificar.setVisible(false);
			lblEliminar.setVisible(false);
		}
		
	
//	private void loadPaciente() {
//		
//		if (paciente != null) {
//			
//			txtCodePaciente.setText(paciente.getCodePaciente());
//			txtNombre.setText(paciente.getNombre());
//			if (paciente.getSexo() == 'M') {
//				
//				rdbtnMasculino.setSelected(true);
//			}
//			else {
//				
//				rdbtnFemenino.setSelected(true);
//			}
//			txtCedula.setText(paciente.getCedula());
//			dateChooserNacim.setDate(paciente.getFechaDeNacimiento());
//			txtTelefono.setText(paciente.getTelefono());
//			cbxTipoSangre.setSelectedItem(paciente.getTipoDeSangre());
//			txtAltura.setText(String.valueOf(paciente.getAltura()));
//			txtPeso.setText(String.valueOf(paciente.getPeso()));
//			txtareaDireccion.setText(paciente.getDireccion());
//			txtareaAlergias.setText(paciente.getAlergias());
//			txtareaInfoRelevante.setText(paciente.getInfoImportante());
//		}
//		
	}
	
// AQUI TERMINA REGPACIENTE
	
	public static void loadPacientes() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : Clinica.getInstance().getMisPersonas()) {
			
			if (persona instanceof Paciente) {
				
				row[0] = ((Paciente) persona).getCodePaciente();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
	
	private void loadPacientesEspecificos() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : pacientesEspecificosAMostrar) {
			
			if (persona instanceof Paciente) {
				
				row[0] = ((Paciente) persona).getCodePaciente();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
	
	private void clean() {
		
		txtCodePaciente.setText("P-"+Clinica.getInstance().getGeneradorCodePaciente());
		txtPNombre.setText("");
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
		txtCedula.setText("");
		dateChooserNacim.setCalendar(null);
		cbxTipoSangre.setSelectedIndex(0);
		txtTelefono.setText("");
		spnAltura.setValue(0);
		spnPeso.setValue(0);
		txtareaAlergias.setText("");
		txtareaDireccion.setText("");
	}
	
	public String getCodePacienteRegistrado() {
		
		return codePacienteRegistrado;
	}
}
