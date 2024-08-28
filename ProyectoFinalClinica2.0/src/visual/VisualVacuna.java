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
import javax.swing.SwingUtilities;

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
import java.util.HashSet;
import java.util.Set;
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
	private static DefaultTableModel modelEnfermedad;
	private Dimension dim;
	private JTable tableVacuna;
	private static Object[] row;
	private Vacuna selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private static JTextField txtCodeVacuna;
	private JTextField txtNombreVacuna;
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
	private JTextField txtLaboratorio;
	private RoundedGlowPanel roundedGlowPanelRegistrarVacuna;
	private RoundedGlowPanel roundedGlowPanelEliminarVacuna;
	private RoundedGlowPanel roundedGlowPanelModificarVacuna;
	private static JTable tableEnfermedad;
	private JTextField txtCodeEnfermedad;

	/**
	 * Create the dialog.
	 */
	public VisualVacuna(Connection conexion) 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		
		Object[] header = {"ID_Vacuna", "Nombre_Vacuna", "Nombre_Laboratorio"};
		Object[] headerEnf = {"ID_Enfermedad", "Nombre_Enfermedad"};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		modelEnfermedad = new DefaultTableModel();
		
		modelEnfermedad.setColumnIdentifiers(headerEnf);
		
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
		panelTablaVacuna.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneVacuna = new JScrollPane(tableVacuna);
		panelTablaVacuna.add(scrollPaneVacuna);
		
		tableVacuna = new JTable(model);
		tableVacuna.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVacuna.getTableHeader().setResizingAllowed(false);
		tableVacuna.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		tableVacuna.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableVacuna.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableVacuna.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadEnfermedades(conexion);
				tableEnfermedad.clearSelection();
				
				int ind = tableVacuna.rowAtPoint(e.getPoint());
		        if (ind == -1) 
		        { 
		            limpiarDatos(); 
		            tableVacuna.clearSelection();
		            
		        } else {
					selected = Clinica.getInstance().buscarVacunaByCodeSQL(conexion, (int) tableVacuna.getValueAt(tableVacuna.getSelectedRow(), 0));
					
					txtCodeVacuna.setText("V-"+ selected.getID_vacuna());
					txtNombreVacuna.setText(selected.getNombre_vacuna());
					txtLaboratorio.setText(selected.getNombre_laboratorio());
					txtCodeEnfermedad.setText(new Integer(selected.getID_enfermedad()).toString());

					roundedGlowPanelEliminarVacuna.setEnabled(true);
					roundedGlowPanelEliminarVacuna.setBackground(Color.WHITE);
					roundedGlowPanelModificarVacuna.setEnabled(true);
					roundedGlowPanelModificarVacuna.setBackground(Color.WHITE);
					roundedGlowPanelRegistrarVacuna.setEnabled(false);
					roundedGlowPanelRegistrarVacuna.setBackground(new Color(240,240,240));
					lblModificarVacuna.setEnabled(true);
					lblEliminarVacuna.setEnabled(true);
					lblRegistrarVacuna.setEnabled(false);
		        }
			}
		});
		tableVacuna.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableVacuna.setFillsViewportHeight(true);
		scrollPaneVacuna.setViewportView(tableVacuna);
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
			//txtCodeVacuna.setText("V-"+Clinica.getInstance().getGeneradorCodeVacuna());
			
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
			roundedPanelTablaEnfermedad.setBackground(Color.WHITE);
			roundedPanelTablaEnfermedad.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosVacuna.add(roundedPanelTablaEnfermedad);
			roundedPanelTablaEnfermedad.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPaneEnfermedad = new JScrollPane();
			roundedPanelTablaEnfermedad.add(scrollPaneEnfermedad, BorderLayout.CENTER);
			
			tableEnfermedad = new JTable(modelEnfermedad);
			tableEnfermedad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txtCodeEnfermedad.setText(tableEnfermedad.getValueAt(tableEnfermedad.getSelectedRow(), 0).toString());
				}
			});
			tableEnfermedad.getTableHeader().setResizingAllowed(false);
			tableEnfermedad.getTableHeader().setReorderingAllowed(false);
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);	
		
			
			tableEnfermedad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableEnfermedad.getColumnModel().getColumn(0).setPreferredWidth(5);
			tableEnfermedad.getColumnModel().getColumn(1).setPreferredWidth(20);
			tableEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*heightRatio)));
			tableEnfermedad.setFillsViewportHeight(true);
			scrollPaneEnfermedad.setViewportView(tableEnfermedad);
			
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
			
			chbxVigilancia = new JCheckBox("Bajo Vigilancia");
			chbxVigilancia.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			chbxVigilancia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			chbxVigilancia.setBackground(Color.WHITE);
			chbxVigilancia.setBounds((int)(275*widthRatio),(int)(2662*heightRatio), (int)(163*widthRatio),(int)(22*heightRatio));
			panelDatosVacuna.add(chbxVigilancia);
			
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
			
			RoundedGlowPanel roundedGlowPanelIdEnfermedad = new RoundedGlowPanel();
			roundedGlowPanelIdEnfermedad.setLayout(null);
			roundedGlowPanelIdEnfermedad.setRoundTopRight(60);
			roundedGlowPanelIdEnfermedad.setRoundTopLeft(60);
			roundedGlowPanelIdEnfermedad.setRoundBottomRight(60);
			roundedGlowPanelIdEnfermedad.setRoundBottomLeft(60);
			roundedGlowPanelIdEnfermedad.setGlowColor(Color.CYAN);
			roundedGlowPanelIdEnfermedad.setGlowAlpha(170);
			roundedGlowPanelIdEnfermedad.setForeground(Color.WHITE);
			roundedGlowPanelIdEnfermedad.setBorder(null);
			roundedGlowPanelIdEnfermedad.setBackground(Color.WHITE);
			roundedGlowPanelIdEnfermedad.setBounds((int)(77*widthRatio), (int)(325*heightRatio), (int)(170*widthRatio), (int)(61*heightRatio));
			panelDatosVacuna.add(roundedGlowPanelIdEnfermedad);
			
			JLabel lblCodeEnfermedad = new JLabel("C\u00F3digo Enf:");
			lblCodeEnfermedad.setOpaque(true);
			lblCodeEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodeEnfermedad.setForeground(new Color(65, 105, 225));
			lblCodeEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCodeEnfermedad.setBackground(Color.WHITE);
			lblCodeEnfermedad.setBounds((int)(20*widthRatio), (int)(18*heightRatio), (int)(89*widthRatio), (int)(22*heightRatio));
			roundedGlowPanelIdEnfermedad.add(lblCodeEnfermedad);
			
			txtCodeEnfermedad = new JTextField();
			txtCodeEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeEnfermedad.setColumns(10);
			txtCodeEnfermedad.setBorder(null);
			txtCodeEnfermedad.setBackground(Color.WHITE);
			txtCodeEnfermedad.setBounds((int)(116*widthRatio), (int)(20*heightRatio), (int)(42*widthRatio), (int)(22*heightRatio));
			roundedGlowPanelIdEnfermedad.add(txtCodeEnfermedad);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds((int)(0*widthRatio),(int)(780*heightRatio), (int)(1381*widthRatio),(int)(120*heightRatio));
		add(gradientPanel);
		gradientPanel.setLayout(null);
		
		roundedGlowPanelModificarVacuna = new RoundedGlowPanel();
		roundedGlowPanelModificarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelModificarVacuna.isEnabled() == true) 
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar los datos de la enfermedad con ID <" + selected.getID_vacuna() + "> y cuyo nombre es: <" + selected.getNombre_vacuna() + ">?", "Modificar Vacuna", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) {
					
						try {
								int idEnfermedad = Integer.parseInt(txtCodeEnfermedad.getText());
				                Set<Integer> idsEnfermedades = obtenerIDsEnfermedades();
				                
				                if(idsEnfermedades.contains(idEnfermedad)) 
				                {
									boolean mod = Clinica.getInstance().modificarVacuna(conexion, new Integer(txtCodeEnfermedad.getText()), 
											  txtNombreVacuna.getText(), txtLaboratorio.getText(), (int) tableVacuna.getValueAt(tableVacuna.getSelectedRow(), 0));
								
									if(mod == true) 
									{
										JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Vacuna", JOptionPane.INFORMATION_MESSAGE);
										loadVacunas(conexion);
										loadEnfermedades(conexion);
										limpiarDatos();
										
										roundedGlowPanelEliminarVacuna.setEnabled(false);
										roundedGlowPanelEliminarVacuna.setBackground(new Color(240,240,240));
										lblEliminarVacuna.setEnabled(false);
										roundedGlowPanelModificarVacuna.setEnabled(false);
										roundedGlowPanelModificarVacuna.setBackground(new Color(240,240,240));
										lblModificarVacuna.setEnabled(false);
										
									} else {
										JOptionPane.showMessageDialog(null,"¡No Se Pudo Modificar la Vacuna!", "Error", JOptionPane.ERROR_MESSAGE);
									  }
				                } else {
				                	JOptionPane.showMessageDialog(null, "ID de enfermedad no válido. Verifique que el ID exista en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
				                }
							} catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(null, "ID de enfermedad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
					}
				}
			}
		});
		roundedGlowPanelModificarVacuna.setEnabled(false);
		roundedGlowPanelModificarVacuna.setLayout(null);
		roundedGlowPanelModificarVacuna.setRoundTopRight(60);
		roundedGlowPanelModificarVacuna.setRoundTopLeft(60);
		roundedGlowPanelModificarVacuna.setRoundBottomRight(60);
		roundedGlowPanelModificarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelModificarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelModificarVacuna.setGlowAlpha(170);
		roundedGlowPanelModificarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelModificarVacuna.setBorder(null);
		roundedGlowPanelModificarVacuna.setBackground(new Color(240,240,240));
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

		roundedGlowPanelEliminarVacuna = new RoundedGlowPanel();
		roundedGlowPanelEliminarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelEliminarVacuna.isEnabled() == true) 
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la Vacuna con ID <" + selected.getID_vacuna() + "> y cuyo nombre es: <" + selected.getNombre_vacuna() + ">?", "Eliminar Vacuna", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) 
					{
						
						boolean elim = Clinica.getInstance().eliminarVacuna(conexion, (int) tableVacuna.getValueAt(tableVacuna.getSelectedRow(), 0));
						
						if(elim == true) 
						{
							JOptionPane.showMessageDialog(null, "Eliminado con éxito", "Eliminar Vacuna", JOptionPane.INFORMATION_MESSAGE);

							loadEnfermedades(conexion);
							loadVacunas(conexion);
							limpiarDatos();
							
						} else {
							JOptionPane.showMessageDialog(null,"¡No Se Pudo Eliminar la Vacuna!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		roundedGlowPanelEliminarVacuna.setEnabled(false);
		roundedGlowPanelEliminarVacuna.setLayout(null);
		roundedGlowPanelEliminarVacuna.setRoundTopRight(60);
		roundedGlowPanelEliminarVacuna.setRoundTopLeft(60);
		roundedGlowPanelEliminarVacuna.setRoundBottomRight(60);
		roundedGlowPanelEliminarVacuna.setRoundBottomLeft(60);
		roundedGlowPanelEliminarVacuna.setGlowColor(Color.CYAN);
		roundedGlowPanelEliminarVacuna.setGlowAlpha(170);
		roundedGlowPanelEliminarVacuna.setForeground(Color.WHITE);
		roundedGlowPanelEliminarVacuna.setBorder(null);
		roundedGlowPanelEliminarVacuna.setBackground(new Color(240,240,240));
		roundedGlowPanelEliminarVacuna.setBounds((int)(1249*widthRatio),(int)(599*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelEliminarVacuna);
		
		lblEliminarVacuna = new JLabel("Eliminar");
		lblEliminarVacuna.setEnabled(false);
		lblEliminarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarVacuna.setForeground(new Color(220, 20, 60));
		lblEliminarVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblEliminarVacuna.setBackground(Color.WHITE);
		lblEliminarVacuna.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelEliminarVacuna.add(lblEliminarVacuna);
		
		roundedGlowPanelRegistrarVacuna = new RoundedGlowPanel();
		roundedGlowPanelRegistrarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(roundedGlowPanelRegistrarVacuna.isEnabled() == true) 
				{
					try {
						
						int idEnfermedad = Integer.parseInt(txtCodeEnfermedad.getText());
		                Set<Integer> idsEnfermedades = obtenerIDsEnfermedades();
		                
		                if(idsEnfermedades.contains(idEnfermedad)) 
		                {
							boolean res = Clinica.getInstance().insertarVacuna(conexion, new Integer(txtCodeEnfermedad.getText()), txtNombreVacuna.getText(), txtLaboratorio.getText());
							
							if(res) {
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Enfermedad", JOptionPane.INFORMATION_MESSAGE);
								loadVacunas(conexion);
								limpiarDatos();
							}
							else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
							}
		                } else {
		                	JOptionPane.showMessageDialog(null, "ID de enfermedad no válido. Verifique que el ID exista en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
					} catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "ID de enfermedad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
				}
			}
		});
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
		
		lblRegistrarVacuna = new JLabel("Registrar");
		lblRegistrarVacuna.setEnabled(false);
		lblRegistrarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarVacuna.setForeground(new Color(100, 149, 237));
		lblRegistrarVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrarVacuna.setBackground(Color.WHITE);
		lblRegistrarVacuna.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrarVacuna.add(lblRegistrarVacuna);
		
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
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableVacuna.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableVacuna.setRowSorter(searchModel2);
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
	    
	    txtNombreVacuna.addKeyListener(campoListener);
	    txtLaboratorio.addKeyListener(campoListener);
	    
	    loadVacunas(conexion);
	    loadEnfermedades(conexion);
	    limpiarDatos();
	}

	protected Set<Integer> obtenerIDsEnfermedades() 
	{
	    Set<Integer> idsEnfermedades = new HashSet<>();
	    for (int i = 0; i < tableEnfermedad.getRowCount(); i++) {
	        Integer id = (Integer) tableEnfermedad.getValueAt(i, 0);
	        idsEnfermedades.add(id);
	    }
	    return idsEnfermedades;
	}

	public static void loadVacunas(Connection conexion) {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Vacuna, Nombre_Vacuna, Laboratorio FROM Vacuna;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Vacuna");
            	row[1] = resultSet.getString("Nombre_Vacuna");
            	row[2] = resultSet.getString("Laboratorio");
            	model.addRow(row);
            	
				Clinica.setGeneradorCodeVacuna(resultSet.getInt("ID_Vacuna"));
				txtCodeVacuna.setText("V-"+(Clinica.getGeneradorCodeVacuna()+1));
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadEnfermedades(Connection conexion) {
		
		modelEnfermedad.setRowCount(0);
		row = new Object[modelEnfermedad.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Enfermedad, Nombre_Enfermedad FROM Enfermedad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Enfermedad");
            	row[1] = resultSet.getString("Nombre_Enfermedad");
            	modelEnfermedad.addRow(row);

            	//Clinica.setGeneradorCodeEnfermedad(resultSet.getInt("ID_Enfermedad"));
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	protected void limpiarDatos() {

		txtNombreVacuna.setText("");
		txtLaboratorio.setText("");
		txtCodeEnfermedad.setText("");
		roundedGlowPanelRegistrarVacuna.setEnabled(false);
		roundedGlowPanelRegistrarVacuna.setBackground(new Color(240, 240, 240));
        roundedGlowPanelEliminarVacuna.setEnabled(false);
		roundedGlowPanelEliminarVacuna.setBackground(new Color(240,240,240));
		roundedGlowPanelModificarVacuna.setEnabled(false);
		roundedGlowPanelModificarVacuna.setBackground(new Color(240,240,240));
		lblModificarVacuna.setEnabled(false);
		lblEliminarVacuna.setEnabled(false);
		lblRegistrarVacuna.setEnabled(false);
		txtCodeVacuna.setText("V-" + (Clinica.getGeneradorCodeVacuna() + 1));
		selected = null;
	}
	
	private void validarCampos() 
	{
		if (selected == null) 
		{
			if(!txtNombreVacuna.getText().isEmpty() && !txtLaboratorio.getText().isEmpty()) 
			{
													
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
}