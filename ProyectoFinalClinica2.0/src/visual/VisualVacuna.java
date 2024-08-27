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
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

public class VisualVacuna extends PanelSimulacionAnim {

	private static DefaultTableModel model;
	private Dimension dim;
	private JTable tableEnfermedad;
	private static Object[] row;
	private Paciente selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private JTextField txtCodeVacuna;
	private JTextField txtNombreVacuna;
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	public static String codePacienteRegistrado = null;
	private JPanel panelTablaVacuna;
	private JLabel lblEliminarVacuna;
	private JLabel lblModificarVacuna;
	private JLabel lblRegistrar;
	private JTextField txtBuscarVacuna;
	private JLabel lblRegistrarVacuna;
	private JLabel lblRegistroDeVacuna;
	private JCheckBox chbxVigilancia;
	private JComboBox cbxEnfermedad;
	private JTextField txtLaboratorio;
	private RoundedGlowPanel roundedGlowPanelRegistrarVacuna;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualVacuna dialog = new VisualVacuna();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualVacuna() 
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
		
		panelTablaVacuna = new JPanel();
		panelTablaVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaVacuna.setBackground(new Color(255, 255, 255));
		panelTablaVacuna.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(550*widthRatio),(int)(515*heightRatio));
		add(panelTablaVacuna);
		panelTablaVacuna.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tableEnfermedad);
		panelTablaVacuna.add(scrollPane, BorderLayout.CENTER);
		
		tableEnfermedad = new JTable(model);
		tableEnfermedad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEnfermedad.getTableHeader().setResizingAllowed(false);
		tableEnfermedad.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < tableEnfermedad.getColumnCount(); index++) {
			
			if (index != 5) {
				
				tableEnfermedad.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		
		tableEnfermedad.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableEnfermedad.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableEnfermedad.getColumnModel().getColumn(3).setPreferredWidth(5);
		tableEnfermedad.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableEnfermedad.getColumnModel().getColumn(5).setPreferredWidth(5);
		tableEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowIndex = tableEnfermedad.getSelectedRow(), colIndex = tableEnfermedad.getSelectedColumn();
				
				if (rowIndex >= 0) {
					
					selected = Clinica.getInstance().buscarPacienteByCode(tableEnfermedad.getValueAt(rowIndex, 0).toString());
					//lblHistorial.setEnabled(true);
					
					if (pacientesEspecificosAMostrar == null) {
						
						lblModificarVacuna.setEnabled(true);
						lblEliminarVacuna.setEnabled(true);
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
		tableEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableEnfermedad.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableEnfermedad);
		{
			RoundedPanel panelDatosVacuna = new RoundedPanel();
			panelDatosVacuna.setRoundTopRight(35);
			panelDatosVacuna.setRoundTopLeft(35);
			panelDatosVacuna.setRoundBottomRight(35);
			panelDatosVacuna.setRoundBottomLeft(35);
			panelDatosVacuna.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(790*widthRatio),(int)(721*heightRatio));
			add(panelDatosVacuna);
			panelDatosVacuna.setBackground(new Color(240, 240, 240));
			panelDatosVacuna.setLayout(null);
			
			RoundedPanel roundedPanelNombreVacuna = new RoundedPanel();
			roundedPanelNombreVacuna.setLayout(null);
			roundedPanelNombreVacuna.setRoundTopRight(18);
			roundedPanelNombreVacuna.setRoundTopLeft(18);
			roundedPanelNombreVacuna.setRoundBottomRight(18);
			roundedPanelNombreVacuna.setRoundBottomLeft(18);
			roundedPanelNombreVacuna.setBackground(SystemColor.window);
			roundedPanelNombreVacuna.setBounds((int)(91*widthRatio),(int)(167*heightRatio),  (int)(524*widthRatio),(int)(46*heightRatio));
			panelDatosVacuna.add(roundedPanelNombreVacuna);
			
			txtNombreVacuna = new JTextField();
			txtNombreVacuna.setBorder(null);
			txtNombreVacuna.setBounds((int)(84*widthRatio),(int)(0*heightRatio),  (int)(440*widthRatio),(int)(46*heightRatio));
			roundedPanelNombreVacuna.add(txtNombreVacuna);
			txtNombreVacuna.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtNombreVacuna.setColumns(10);
			{
				JLabel lblNombreVacuna = new JLabel("Nombre:");
				lblNombreVacuna.setBounds((int)(4*widthRatio), (int)(11*heightRatio), (int)(74*widthRatio), (int)(22*heightRatio));
				roundedPanelNombreVacuna.add(lblNombreVacuna);
				lblNombreVacuna.setOpaque(true);
				lblNombreVacuna.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombreVacuna.setForeground(new Color(65, 105, 225));
				lblNombreVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
				lblNombreVacuna.setBackground(Color.WHITE);
			}
			
			RoundedPanel roundedPanelEnfermedad = new RoundedPanel();
			roundedPanelEnfermedad.setLayout(null);
			roundedPanelEnfermedad.setRoundTopRight(18);
			roundedPanelEnfermedad.setRoundTopLeft(18);
			roundedPanelEnfermedad.setRoundBottomRight(18);
			roundedPanelEnfermedad.setRoundBottomLeft(18);
			roundedPanelEnfermedad.setBackground(Color.WHITE);
			roundedPanelEnfermedad.setBounds((int)(93*widthRatio), (int)(330*heightRatio),  (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosVacuna.add(roundedPanelEnfermedad);
			
			JLabel lblEnfermedad = new JLabel("Enfermedad");
			lblEnfermedad.setOpaque(true);
			lblEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnfermedad.setForeground(new Color(65, 105, 225));
			lblEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblEnfermedad.setBackground(Color.WHITE);
			lblEnfermedad.setBounds((int)(4*widthRatio), (int)(11*heightRatio), (int)(89*widthRatio), (int)(22*heightRatio));
			roundedPanelEnfermedad.add(lblEnfermedad);
			
			RoundedPanel roundedPanelCodeVacuna = new RoundedPanel();
			roundedPanelCodeVacuna.setLayout(null);
			roundedPanelCodeVacuna.setRoundTopRight(18);
			roundedPanelCodeVacuna.setRoundTopLeft(18);
			roundedPanelCodeVacuna.setRoundBottomRight(18);
			roundedPanelCodeVacuna.setRoundBottomLeft(18);
			roundedPanelCodeVacuna.setBackground(Color.WHITE);
			roundedPanelCodeVacuna.setBounds((int)(93*widthRatio),(int)(87*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
			panelDatosVacuna.add(roundedPanelCodeVacuna);
			
			JLabel lblCodigoVacuna = new JLabel("C\u00F3digo:");
			lblCodigoVacuna.setOpaque(true);
			lblCodigoVacuna.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodigoVacuna.setForeground(new Color(65, 105, 225));
			lblCodigoVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCodigoVacuna.setBackground(Color.WHITE);
			lblCodigoVacuna.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeVacuna.add(lblCodigoVacuna);
			
			txtCodeVacuna = new JTextField();
			txtCodeVacuna.setBackground(new Color(255, 255, 255));
			txtCodeVacuna.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(48*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeVacuna.add(txtCodeVacuna);
			txtCodeVacuna.setBorder(null);
			txtCodeVacuna.setEditable(false);
			txtCodeVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeVacuna.setColumns(10);
			txtCodeVacuna.setText("V-"+Clinica.getInstance().getGeneradorCodeVacuna());
			
			RoundedGlowPanel roundedGlowPanelCodeVacuna = new RoundedGlowPanel();
			roundedGlowPanelCodeVacuna.setBounds((int)(77*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(61*heightRatio));
			panelDatosVacuna.add(roundedGlowPanelCodeVacuna);
			roundedGlowPanelCodeVacuna.setGlowAlpha(170);
			roundedGlowPanelCodeVacuna.setForeground(new Color(255, 255, 255));
			roundedGlowPanelCodeVacuna.setBorder(null);
			roundedGlowPanelCodeVacuna.setGlowColor(new Color(0, 255, 255));
			roundedGlowPanelCodeVacuna.setRoundTopRight(60);
			roundedGlowPanelCodeVacuna.setRoundTopLeft(60);
			roundedGlowPanelCodeVacuna.setRoundBottomRight(60);
			roundedGlowPanelCodeVacuna.setRoundBottomLeft(60);
			roundedGlowPanelCodeVacuna.setBackground(new Color(255, 255, 255));
			roundedGlowPanelCodeVacuna.setLayout(null);
			
			lblRegistroDeVacuna = new JLabel("Registro de Vacuna");
			lblRegistroDeVacuna.setOpaque(true);
			lblRegistroDeVacuna.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeVacuna.setForeground(new Color(0, 0, 0));
			lblRegistroDeVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDeVacuna.setBackground(new Color(240, 240, 240));
			lblRegistroDeVacuna.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosVacuna.add(lblRegistroDeVacuna);
			
			RoundedPanel roundedPanelTablaEnfermedad = new RoundedPanel();
			roundedPanelTablaEnfermedad.setLayout(null);
			roundedPanelTablaEnfermedad.setBackground(Color.WHITE);
			roundedPanelTablaEnfermedad.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosVacuna.add(roundedPanelTablaEnfermedad);
			
			JLabel lblImagen = new JLabel("AQUI VA LA TABLA DE ENFERMEDADES");
			lblImagen.setOpaque(true);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagen.setForeground(new Color(65, 105, 225));
			lblImagen.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblImagen.setBackground(Color.WHITE);
			lblImagen.setBounds((int)(203*widthRatio),(int)(101*heightRatio), (int)(348*widthRatio),(int)(22*heightRatio));
			roundedPanelTablaEnfermedad.add(lblImagen);
			
			RoundedGlowPanel roundedGlowPanelNombreVacuna = new RoundedGlowPanel();
			roundedGlowPanelNombreVacuna.setLayout(null);
			roundedGlowPanelNombreVacuna.setRoundTopRight(60);
			roundedGlowPanelNombreVacuna.setRoundTopLeft(60);
			roundedGlowPanelNombreVacuna.setRoundBottomRight(60);
			roundedGlowPanelNombreVacuna.setRoundBottomLeft(60);
			roundedGlowPanelNombreVacuna.setGlowColor(Color.CYAN);
			roundedGlowPanelNombreVacuna.setGlowAlpha(170);
			roundedGlowPanelNombreVacuna.setForeground(Color.WHITE);
			roundedGlowPanelNombreVacuna.setBorder(null);
			roundedGlowPanelNombreVacuna.setBackground(Color.WHITE);
			roundedGlowPanelNombreVacuna.setBounds((int)(77*widthRatio),(int)(161*heightRatio),  (int)(562*widthRatio),(int)(59*heightRatio));
			panelDatosVacuna.add(roundedGlowPanelNombreVacuna);
			
			RoundedGlowPanel roundedGlowPanelEnfermedad = new RoundedGlowPanel();
			roundedGlowPanelEnfermedad.setLayout(null);
			roundedGlowPanelEnfermedad.setRoundTopRight(60);
			roundedGlowPanelEnfermedad.setRoundTopLeft(60);
			roundedGlowPanelEnfermedad.setRoundBottomRight(60);
			roundedGlowPanelEnfermedad.setRoundBottomLeft(60);
			roundedGlowPanelEnfermedad.setGlowColor(Color.CYAN);
			roundedGlowPanelEnfermedad.setGlowAlpha(170);
			roundedGlowPanelEnfermedad.setForeground(Color.WHITE);
			roundedGlowPanelEnfermedad.setBorder(null);
			roundedGlowPanelEnfermedad.setBackground(Color.WHITE);
			roundedGlowPanelEnfermedad.setBounds((int)(77*widthRatio), (int)(323*heightRatio), (int)(140*widthRatio),(int)(59*heightRatio));
			panelDatosVacuna.add(roundedGlowPanelEnfermedad);
			
			chbxVigilancia = new JCheckBox("Bajo Vigilancia");
			chbxVigilancia.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			chbxVigilancia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			chbxVigilancia.setBackground(Color.WHITE);
			chbxVigilancia.setBounds((int)(275*widthRatio),(int)(2662*heightRatio), (int)(163*widthRatio),(int)(22*heightRatio));
			panelDatosVacuna.add(chbxVigilancia);
			
			cbxEnfermedad = new JComboBox();
			cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "Vacuna1"}));
			//cbxEnfermedad.setSelectedIndex(0);
			cbxEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			cbxEnfermedad.setBorder(null);
			cbxEnfermedad.setBounds((int)(229*widthRatio), (int)(330*heightRatio), (int)(216*widthRatio), (int)(46*heightRatio));
			panelDatosVacuna.add(cbxEnfermedad);
			
			RoundedGlowPanel roundedGlowPanelLaboratorio = new RoundedGlowPanel();
			roundedGlowPanelLaboratorio.setLayout(null);
			roundedGlowPanelLaboratorio.setRoundTopRight(60);
			roundedGlowPanelLaboratorio.setRoundTopLeft(60);
			roundedGlowPanelLaboratorio.setRoundBottomRight(60);
			roundedGlowPanelLaboratorio.setRoundBottomLeft(60);
			roundedGlowPanelLaboratorio.setGlowColor(Color.CYAN);
			roundedGlowPanelLaboratorio.setGlowAlpha(170);
			roundedGlowPanelLaboratorio.setForeground(Color.WHITE);
			roundedGlowPanelLaboratorio.setBorder(null);
			roundedGlowPanelLaboratorio.setBackground(Color.WHITE);
			roundedGlowPanelLaboratorio.setBounds((int)(77*widthRatio), (int)(242*heightRatio), (int)(562*widthRatio), (int)(59*heightRatio));
			panelDatosVacuna.add(roundedGlowPanelLaboratorio);
			
			RoundedPanel roundedPanelLaboratorio = new RoundedPanel();
			roundedPanelLaboratorio.setLayout(null);
			roundedPanelLaboratorio.setRoundTopRight(18);
			roundedPanelLaboratorio.setRoundTopLeft(18);
			roundedPanelLaboratorio.setRoundBottomRight(18);
			roundedPanelLaboratorio.setRoundBottomLeft(18);
			roundedPanelLaboratorio.setBackground(Color.WHITE);
			roundedPanelLaboratorio.setBounds((int)(14*widthRatio), (int)(6*heightRatio), (int)(524*widthRatio), (int)(46*heightRatio));
			roundedGlowPanelLaboratorio.add(roundedPanelLaboratorio);
			
			txtLaboratorio = new JTextField();
			txtLaboratorio.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtLaboratorio.setColumns(10);
			txtLaboratorio.setBorder(null);
			txtLaboratorio.setBounds((int)(100*widthRatio), (int)(0*heightRatio), (int)(424*widthRatio), (int)(46*heightRatio));
			roundedPanelLaboratorio.add(txtLaboratorio);
			
			JLabel lblLaboratorio = new JLabel("Laboratorio:");
			lblLaboratorio.setOpaque(true);
			lblLaboratorio.setHorizontalAlignment(SwingConstants.CENTER);
			lblLaboratorio.setForeground(new Color(65, 105, 225));
			lblLaboratorio.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblLaboratorio.setBackground(Color.WHITE);
			lblLaboratorio.setBounds((int)(4*widthRatio), (int)(11*heightRatio), (int)(84*widthRatio), (int)(22*heightRatio));
			roundedPanelLaboratorio.add(lblLaboratorio);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds((int)(0*widthRatio),(int)(780*heightRatio), (int)(1381*widthRatio),(int)(120*heightRatio));
		add(gradientPanel);
		gradientPanel.setLayout(null);
		
		RoundedGlowPanel roundedGlowPanelModificarVacuna = new RoundedGlowPanel();
		roundedGlowPanelModificarVacuna.setLayout(null);
		roundedGlowPanelModificarVacuna.setRoundTopRight(60);
		roundedGlowPanelModificarVacuna.setRoundTopLeft(60);
		roundedGlowPanelModificarVacuna.setRoundBottomRight(60);
		roundedGlowPanelModificarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelModificarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelModificarVacuna.setGlowAlpha(170);
		roundedGlowPanelModificarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelModificarVacuna.setBorder(null);
		roundedGlowPanelModificarVacuna.setBackground(Color.WHITE);
		roundedGlowPanelModificarVacuna.setBounds((int)(1105*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelModificarVacuna);
		
		lblModificarVacuna = new JLabel("Modificar");
		lblModificarVacuna.setEnabled(false);
		lblModificarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarVacuna.setForeground(new Color(60, 179, 113));
		lblModificarVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblModificarVacuna.setBackground(Color.WHITE);
		lblModificarVacuna.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelModificarVacuna.add(lblModificarVacuna);
		
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
		
		RoundedGlowPanel roundedGlowPanelEliminarVacuna = new RoundedGlowPanel();
		roundedGlowPanelEliminarVacuna.setLayout(null);
		roundedGlowPanelEliminarVacuna.setRoundTopRight(60);
		roundedGlowPanelEliminarVacuna.setRoundTopLeft(60);
		roundedGlowPanelEliminarVacuna.setRoundBottomRight(60);
		roundedGlowPanelEliminarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelEliminarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelEliminarVacuna.setGlowAlpha(170);
		roundedGlowPanelEliminarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelEliminarVacuna.setBorder(null);
		roundedGlowPanelEliminarVacuna.setBackground(Color.WHITE);
		roundedGlowPanelEliminarVacuna.setBounds((int)(1249*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelEliminarVacuna);
		
		lblEliminarVacuna = new JLabel("Eliminar");
		lblEliminarVacuna.setEnabled(false);
		lblEliminarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarVacuna.setForeground(new Color(220, 20, 60));
		lblEliminarVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblEliminarVacuna.setBackground(Color.WHITE);
		lblEliminarVacuna.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelEliminarVacuna.add(lblEliminarVacuna);
		
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
		
		roundedGlowPanelRegistrarVacuna = new RoundedGlowPanel();
		roundedGlowPanelRegistrarVacuna.setBounds((int)(961*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelRegistrarVacuna);
		roundedGlowPanelRegistrarVacuna.setEnabled(false);
		roundedGlowPanelRegistrarVacuna.setLayout(null);
		roundedGlowPanelRegistrarVacuna.setRoundTopRight(60);
		roundedGlowPanelRegistrarVacuna.setRoundTopLeft(60);
		roundedGlowPanelRegistrarVacuna.setRoundBottomRight(60);
		roundedGlowPanelRegistrarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelRegistrarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelRegistrarVacuna.setGlowAlpha(170);
		roundedGlowPanelRegistrarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelRegistrarVacuna.setBorder(null);
		roundedGlowPanelRegistrarVacuna.setBackground(new Color(240,240,240));
		
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
		
		lblRegistrarVacuna = new JLabel("Registrar");
		lblRegistrarVacuna.setEnabled(false);
		lblRegistrarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarVacuna.setForeground(new Color(100, 149, 237));
		lblRegistrarVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrarVacuna.setBackground(Color.WHITE);
		lblRegistrarVacuna.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrarVacuna.add(lblRegistrarVacuna);
		
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
		
		RoundedGlowPanel roundedGlowPanelBuscarVacuna = new RoundedGlowPanel();
		roundedGlowPanelBuscarVacuna.setLayout(null);
		roundedGlowPanelBuscarVacuna.setRoundTopRight(60);
		roundedGlowPanelBuscarVacuna.setRoundTopLeft(60);
		roundedGlowPanelBuscarVacuna.setRoundBottomRight(60);
		roundedGlowPanelBuscarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelBuscarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelBuscarVacuna.setGlowAlpha(170);
		roundedGlowPanelBuscarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelBuscarVacuna.setBorder(null);
		roundedGlowPanelBuscarVacuna.setBackground(Color.WHITE);
		roundedGlowPanelBuscarVacuna.setBounds((int)(817*widthRatio),(int)(541*heightRatio), (int)(550*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelBuscarVacuna);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(100, 149, 237));
		lblBuscar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblBuscar.setBackground(Color.WHITE);
		lblBuscar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelBuscarVacuna.add(lblBuscar);
		
		txtBuscarVacuna = new JTextField();
		txtBuscarVacuna.setOpaque(false);
		txtBuscarVacuna.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		txtBuscarVacuna.setColumns(10);
		txtBuscarVacuna.setBorder(null);
		txtBuscarVacuna.setBounds((int)(101*widthRatio), 0, (int)(428*widthRatio),(int)(49*heightRatio));
		txtBuscarVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableEnfermedad.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableEnfermedad.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarVacuna.getText()));
			}
		});
		roundedGlowPanelBuscarVacuna.add(txtBuscarVacuna);
		txtBuscarVacuna.setColumns(10);
		
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
	    
	    txtNombreVacuna.addKeyListener(campoListener);
	    txtLaboratorio.addKeyListener(campoListener);
	    cbxEnfermedad.addActionListener(cbxListener);
	}
	
	private void validarCampos() {
		
		if(!txtNombreVacuna.getText().isEmpty() && !txtLaboratorio.getText().isEmpty() && (cbxEnfermedad.getSelectedIndex() != 0)) {
												
		   roundedGlowPanelRegistrarVacuna.setEnabled(true);
		   roundedGlowPanelRegistrarVacuna.setBackground(Color.WHITE);
		   lblRegistrarVacuna.setEnabled(true);
		   
		} else {
			
		   roundedGlowPanelRegistrarVacuna.setEnabled(false);
		   roundedGlowPanelRegistrarVacuna.setBackground(new Color(240, 240, 240));
		   lblRegistrarVacuna.setEnabled(false);
		   
		}
		
	}
}