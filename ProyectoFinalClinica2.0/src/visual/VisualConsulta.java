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
import java.awt.Component;

public class VisualConsulta extends JPanel {

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
	private JTextField txtSnombre;
	private JTextField txtPApellido;
	private JLabel lblPApellido;
	private JTextField txtSApellido;
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
	private JLabel lblRegistrar;
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
	private JLabel lblSintoma;
	private JLabel lblEnfermedad;
	private JLabel lblAnalisis;
	private JLabel lblVacuna;
	
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
	public VisualConsulta() 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		//paciente = pacienteAModificar;
		//pacientesEspecificosAMostrar = pacientesAMostrar;
		
		Object[] header = {"C�digo", "C�dula", "Nombre", "Sexo", "Tel�fono", "Ver m�s"};
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
		contentPanel.setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelVolver = new RoundedGlowPanel();
		roundedGlowPanelVolver.setBounds((int)(64*widthRatio),(int)(11*heightRatio), (int)(57*widthRatio),(int)(49*heightRatio));
		contentPanel.add(roundedGlowPanelVolver);
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
		contentPanel.add(panelTablaSintoma);
		panelTablaSintoma.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tablePacientes);
		panelTablaSintoma.add(scrollPane, BorderLayout.CENTER);
		
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
						
						//lblModificar.setEnabled(true);
						//lblEliminar.setEnabled(true);
					}
					
					if (colIndex == 5) {
						
						/* RECORDAR QUE AQUI EN VEZ DE ABRIR LA PESTA�A DE REGISTRAR PACIENTE, DEBEMOS PONER LOS DATOS DE LA PERSONA EN 
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
		
		lblSintoma = new JLabel("Sintoma");
		lblSintoma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSintoma.setForeground(new Color(65, 105, 225));
		lblSintoma.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblSintoma.setBackground(Color.WHITE);
		lblSintoma.setBounds((int)(223*widthRatio),(int)(72*heightRatio), (int)(118*widthRatio),(int)(22*heightRatio));
		panelTablaSintoma.add(lblSintoma);
		{
			RoundedPanel panelDatosPersona = new RoundedPanel();
			panelDatosPersona.setRoundTopRight(35);
			panelDatosPersona.setRoundTopLeft(35);
			panelDatosPersona.setRoundBottomRight(35);
			panelDatosPersona.setRoundBottomLeft(35);
			panelDatosPersona.setBounds((int)(66*widthRatio),(int)(13*heightRatio), (int)(693*widthRatio),(int)(839*heightRatio));
			contentPanel.add(panelDatosPersona);
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
			lblRegistroDePersonas.setBounds(248,20, 201,46);
			panelDatosPersona.add(lblRegistroDePersonas);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setEnabled(false);
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
			BorderLayout borderLayout = (BorderLayout) dateChooserNacim.getLayout();
			dateChooserNacim.setBackground(new Color(255, 255, 255));
			dateChooserNacim.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserNacim.setBorder(new EmptyBorder(0, 0, 0, 0));
			dateChooserNacim.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			RoundedGlowPanel panel11 = new RoundedGlowPanel();
			panel_1.setBounds(108, 462, 145, 59);
			panelDatosPersona.add(panel_1);
			
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
			
			txtSnombre = new JTextField();
			txtSnombre.setEnabled(false);
			txtSnombre.setEditable(false);
			txtSnombre.setOpaque(false);
			txtSnombre.setBounds((int)(144*widthRatio),(int)(5*heightRatio), (int)(101*widthRatio),(int)(46*heightRatio));
			roundedGlowPanelSNombre.add(txtSnombre);
			txtSnombre.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtSnombre.setColumns(10);
			txtSnombre.setBorder(null);
			
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
			spnAltura.setBackground(Color.WHITE);
			spnAltura.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			spnAltura.setForeground(Color.WHITE);
			spnAltura.setEnabled(false);
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
			spnPeso.setBackground(Color.WHITE);
			spnPeso.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			spnPeso.setForeground(Color.WHITE);
			spnPeso.setOpaque(false);
			spnPeso.setEnabled(false);
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
			
			txtareaAlergias = new JTextArea();
			txtareaAlergias.setEnabled(false);
			txtareaAlergias.setEditable(false);
			txtareaAlergias.setOpaque(false);
			txtareaAlergias.setBounds((int)(79*widthRatio),(int)(6*heightRatio), (int)(460*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelAlergia.add(txtareaAlergias);
			txtareaAlergias.setWrapStyleWord(true);
			txtareaAlergias.setLineWrap(true);
			txtareaAlergias.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
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
			
			JTextArea textArea = new JTextArea();
			textArea.setWrapStyleWord(true);
			textArea.setOpaque(false);
			textArea.setLineWrap(true);
			textArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			textArea.setBounds((int)(107*widthRatio),(int)(6*heightRatio), (int)(426*widthRatio),(int)(59*heightRatio));
			roundedGlowPanelDiagnostico.add(textArea);
			
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
					
					DefaultTableModel searchModel1 = (DefaultTableModel) tablePacientes.getModel();
					TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
					tablePacientes.setRowSorter(searchModel2);
					searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				}
			});
			roundedGlowPanelBuscarPaciente.add(txtBuscarPaciente);
			txtBuscarPaciente.setColumns(10);	
			
			RoundedGlowPanel roundedGlowPanelRealizar = new RoundedGlowPanel();
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
			roundedGlowPanelRealizar.setBackground(Color.WHITE);
			roundedGlowPanelRealizar.add(lblRegistrar);
			
			lblRealizar = new JLabel("Realizar");
			lblRealizar.setHorizontalAlignment(SwingConstants.CENTER);
			lblRealizar.setForeground(new Color(100, 149, 237));
			lblRealizar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblRealizar.setEnabled(false);
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
			
			JDateChooser dateChooserConsulta = new JDateChooser();
			dateChooserConsulta.getCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			dateChooserConsulta.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(12*widthRatio)));
			dateChooserConsulta.setBounds((int)(536*widthRatio),(int)(36*heightRatio), (int)(94*widthRatio),(int)(22*heightRatio));
			panelDatosPersona.add(dateChooserConsulta);
			

		}
		
		/* RECORDAR CAMBIAR ESTO POR EL LABEL DE ELIMINAR, CREO QUE NO HAY QUE HACER CAMBIOS EN LA ESTRUCTURA PERO
		 * VERIFICAR.
		  
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Option = JOptionPane.showConfirmDialog(null, "�Est� seguro de eliminar al Paciente con c�digo: <" + selected.getCodePaciente() + ">?", "Eliminar Paciente", JOptionPane.OK_CANCEL_OPTION);
				
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
//						JOptionPane.showMessageDialog(null, "Registrado con �xito", "Registrar Paciente", JOptionPane.INFORMATION_MESSAGE);
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
//					JOptionPane.showMessageDialog(null, "Ingrese datos v�lidos para la altura y el peso.", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		panelTablaEnfermedad = new JPanel();
		panelTablaEnfermedad.setLayout(null);
		panelTablaEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaEnfermedad.setBackground(Color.WHITE);
		panelTablaEnfermedad.setBounds((int)(780*widthRatio),(int)(226*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		contentPanel.add(panelTablaEnfermedad);
		
		scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(0, 0, 0, 0);
		panelTablaEnfermedad.add(scrollPane_1);
		
		lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnfermedad.setForeground(new Color(65, 105, 225));
		lblEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblEnfermedad.setBackground(Color.WHITE);
		lblEnfermedad.setBounds((int)(226*widthRatio),(int)(91*heightRatio), (int)(118*widthRatio),(int)(22*heightRatio));
		panelTablaEnfermedad.add(lblEnfermedad);
		
		panelTablaAnalisis = new JPanel();
		panelTablaAnalisis.setLayout(null);
		panelTablaAnalisis.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaAnalisis.setBackground(Color.WHITE);
		panelTablaAnalisis.setBounds((int)(780*widthRatio),(int)(439*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		contentPanel.add(panelTablaAnalisis);
		
		scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setBounds(0, 0, 0, 0);
		panelTablaAnalisis.add(scrollPane_2);
		
		lblAnalisis = new JLabel("Analisis");
		lblAnalisis.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalisis.setForeground(new Color(65, 105, 225));
		lblAnalisis.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblAnalisis.setBackground(Color.WHITE);
		lblAnalisis.setBounds((int)(231*widthRatio),(int)(91*heightRatio), (int)(118*widthRatio),(int)(22*heightRatio));
		panelTablaAnalisis.add(lblAnalisis);
		
		panelTablaVacuna = new JPanel();
		panelTablaVacuna.setLayout(null);
		panelTablaVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaVacuna.setBackground(Color.WHITE);
		panelTablaVacuna.setBounds((int)(780*widthRatio),(int)(652*heightRatio), (int)(574*widthRatio),(int)(200*heightRatio));
		contentPanel.add(panelTablaVacuna);
		
		scrollPane_3 = new JScrollPane((Component) null);
		scrollPane_3.setBounds(0, 0, 0, 0);
		panelTablaVacuna.add(scrollPane_3);
		
		lblVacuna = new JLabel("Vacuna");
		lblVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacuna.setForeground(new Color(65, 105, 225));
		lblVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblVacuna.setBackground(Color.WHITE);
		lblVacuna.setBounds((int)(235*widthRatio),(int)(101*heightRatio), (int)(118*widthRatio),(int)(22*heightRatio));
		panelTablaVacuna.add(lblVacuna);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VisualConsulta.class.getResource("/Imagenes/6876480.jpg")));
		lblNewLabel.setBounds(0, 0, (int)(1381*widthRatio),(int)(900*heightRatio));
		contentPanel.add(lblNewLabel);
		
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
	
	}
}