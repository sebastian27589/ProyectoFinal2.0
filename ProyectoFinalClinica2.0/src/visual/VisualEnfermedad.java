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
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

public class VisualEnfermedad extends PanelSimulacionAnim {

	private static DefaultTableModel model;
	private Dimension dim;
	private JTable tableEnfermedad;
	private static Object[] row;
	private Paciente selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private JTextField txtCodeEnfermedad;
	private JTextField txtNombreEnfermedad;
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	private JComboBox cbxTipoEnfermedad;
	public static String codePacienteRegistrado = null;
	private JPanel panelTablaEnfermedad;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private JTextField txtBuscarEnfermedad;
	private JLabel lblRegistrar_1;
	private JSpinner spnMortalidad;
	private JLabel lblRegistroDeEnfermedad;
	private JCheckBox chbxVigilancia;
	private JComboBox cbxSintomas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualEnfermedad dialog = new VisualEnfermedad();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualEnfermedad() 
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
		
		panelTablaEnfermedad = new JPanel();
		panelTablaEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaEnfermedad.setBackground(new Color(255, 255, 255));
		panelTablaEnfermedad.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(550*widthRatio),(int)(515*heightRatio));
		add(panelTablaEnfermedad);
		panelTablaEnfermedad.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(tableEnfermedad);
		panelTablaEnfermedad.add(scrollPane, BorderLayout.CENTER);
		
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
		tableEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tableEnfermedad.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableEnfermedad);
		{
			RoundedPanel panelDatosEnfermedad = new RoundedPanel();
			panelDatosEnfermedad.setRoundTopRight(35);
			panelDatosEnfermedad.setRoundTopLeft(35);
			panelDatosEnfermedad.setRoundBottomRight(35);
			panelDatosEnfermedad.setRoundBottomLeft(35);
			panelDatosEnfermedad.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(790*widthRatio),(int)(721*heightRatio));
			add(panelDatosEnfermedad);
			panelDatosEnfermedad.setBackground(new Color(240, 240, 240));
			panelDatosEnfermedad.setLayout(null);
			
			RoundedPanel roundedPanelPNombreEnfermedad = new RoundedPanel();
			roundedPanelPNombreEnfermedad.setLayout(null);
			roundedPanelPNombreEnfermedad.setRoundTopRight(18);
			roundedPanelPNombreEnfermedad.setRoundTopLeft(18);
			roundedPanelPNombreEnfermedad.setRoundBottomRight(18);
			roundedPanelPNombreEnfermedad.setRoundBottomLeft(18);
			roundedPanelPNombreEnfermedad.setBackground(SystemColor.window);
			roundedPanelPNombreEnfermedad.setBounds((int)(91*widthRatio),(int)(167*heightRatio),  (int)(524*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(roundedPanelPNombreEnfermedad);
			
			txtNombreEnfermedad = new JTextField();
			txtNombreEnfermedad.setBorder(null);
			txtNombreEnfermedad.setBounds((int)(84*widthRatio),(int)(0*heightRatio),  (int)(440*widthRatio),(int)(46*heightRatio));
			roundedPanelPNombreEnfermedad.add(txtNombreEnfermedad);
			txtNombreEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			txtNombreEnfermedad.setColumns(10);
			{
				JLabel lblPNombreEnfermedad = new JLabel("Nombre:");
				lblPNombreEnfermedad.setBounds(4,11, 74,22);
				roundedPanelPNombreEnfermedad.add(lblPNombreEnfermedad);
				lblPNombreEnfermedad.setOpaque(true);
				lblPNombreEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
				lblPNombreEnfermedad.setForeground(new Color(65, 105, 225));
				lblPNombreEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
				lblPNombreEnfermedad.setBackground(Color.WHITE);
			}
			
			RoundedPanel roundedPanelSintoma = new RoundedPanel();
			roundedPanelSintoma.setLayout(null);
			roundedPanelSintoma.setRoundTopRight(18);
			roundedPanelSintoma.setRoundTopLeft(18);
			roundedPanelSintoma.setRoundBottomRight(18);
			roundedPanelSintoma.setRoundBottomLeft(18);
			roundedPanelSintoma.setBackground(Color.WHITE);
			roundedPanelSintoma.setBounds((int)(93*widthRatio),(int)(412*heightRatio),  (int)(105*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(roundedPanelSintoma);
			
			JLabel lblSintomas = new JLabel("S\u00EDntomas");
			lblSintomas.setOpaque(true);
			lblSintomas.setHorizontalAlignment(SwingConstants.CENTER);
			lblSintomas.setForeground(new Color(65, 105, 225));
			lblSintomas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblSintomas.setBackground(Color.WHITE);
			lblSintomas.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(77*widthRatio),(int)(22*heightRatio));
			roundedPanelSintoma.add(lblSintomas);
			
			RoundedPanel roundedPanelCodeEnfermedad = new RoundedPanel();
			roundedPanelCodeEnfermedad.setLayout(null);
			roundedPanelCodeEnfermedad.setRoundTopRight(18);
			roundedPanelCodeEnfermedad.setRoundTopLeft(18);
			roundedPanelCodeEnfermedad.setRoundBottomRight(18);
			roundedPanelCodeEnfermedad.setRoundBottomLeft(18);
			roundedPanelCodeEnfermedad.setBackground(Color.WHITE);
			roundedPanelCodeEnfermedad.setBounds((int)(93*widthRatio),(int)(87*heightRatio), (int)(140*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(roundedPanelCodeEnfermedad);
			
			JLabel lblCodigoEnfermedad = new JLabel("C\u00F3digo:");
			lblCodigoEnfermedad.setOpaque(true);
			lblCodigoEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodigoEnfermedad.setForeground(new Color(65, 105, 225));
			lblCodigoEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblCodigoEnfermedad.setBackground(Color.WHITE);
			lblCodigoEnfermedad.setBounds((int)(4*widthRatio),(int)(11*heightRatio), (int)(76*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeEnfermedad.add(lblCodigoEnfermedad);
			
			txtCodeEnfermedad = new JTextField();
			txtCodeEnfermedad.setBackground(new Color(255, 255, 255));
			txtCodeEnfermedad.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(62*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeEnfermedad.add(txtCodeEnfermedad);
			txtCodeEnfermedad.setBorder(null);
			txtCodeEnfermedad.setEditable(false);
			txtCodeEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeEnfermedad.setColumns(10);
			txtCodeEnfermedad.setText("E-"+Clinica.getInstance().getGeneradorCodePaciente());
			
			RoundedGlowPanel roundedGlowPanelCodeEnfermedad = new RoundedGlowPanel();
			roundedGlowPanelCodeEnfermedad.setBounds((int)(77*widthRatio),(int)(79*heightRatio), (int)(170*widthRatio),(int)(61*heightRatio));
			panelDatosEnfermedad.add(roundedGlowPanelCodeEnfermedad);
			roundedGlowPanelCodeEnfermedad.setGlowAlpha(170);
			roundedGlowPanelCodeEnfermedad.setForeground(new Color(255, 255, 255));
			roundedGlowPanelCodeEnfermedad.setBorder(null);
			roundedGlowPanelCodeEnfermedad.setGlowColor(new Color(0, 255, 255));
			roundedGlowPanelCodeEnfermedad.setRoundTopRight(60);
			roundedGlowPanelCodeEnfermedad.setRoundTopLeft(60);
			roundedGlowPanelCodeEnfermedad.setRoundBottomRight(60);
			roundedGlowPanelCodeEnfermedad.setRoundBottomLeft(60);
			roundedGlowPanelCodeEnfermedad.setBackground(new Color(255, 255, 255));
			roundedGlowPanelCodeEnfermedad.setLayout(null);
			
			lblRegistroDeEnfermedad = new JLabel("Registro de Enfermedad");
			lblRegistroDeEnfermedad.setOpaque(true);
			lblRegistroDeEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeEnfermedad.setForeground(new Color(0, 0, 0));
			lblRegistroDeEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDeEnfermedad.setBackground(new Color(240, 240, 240));
			lblRegistroDeEnfermedad.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(lblRegistroDeEnfermedad);
			
			RoundedPanel roundedPanelMortalidad = new RoundedPanel();
			roundedPanelMortalidad.setLayout(null);
			roundedPanelMortalidad.setRoundTopRight(18);
			roundedPanelMortalidad.setRoundTopLeft(18);
			roundedPanelMortalidad.setRoundBottomRight(18);
			roundedPanelMortalidad.setRoundBottomLeft(18);
			roundedPanelMortalidad.setBackground(Color.WHITE);
			roundedPanelMortalidad.setBounds((int)(91*widthRatio),(int)(250*heightRatio),  (int)(151*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(roundedPanelMortalidad);
			
			JLabel lblMortalidad = new JLabel("Mortalidad:");
			lblMortalidad.setOpaque(true);
			lblMortalidad.setHorizontalAlignment(SwingConstants.CENTER);
			lblMortalidad.setForeground(new Color(65, 105, 225));
			lblMortalidad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblMortalidad.setBackground(Color.WHITE);
			lblMortalidad.setBounds((int)(2*widthRatio),(int)(9*heightRatio),  (int)(96*widthRatio),(int)(22*heightRatio));
			roundedPanelMortalidad.add(lblMortalidad);
			
			spnMortalidad = new JSpinner();
			spnMortalidad.setModel(new SpinnerNumberModel(0, 0, 10, 1));
			spnMortalidad.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			spnMortalidad.setBorder(null);
			spnMortalidad.setBounds((int)(110*widthRatio), 0, (int)(40*widthRatio),(int)(46*heightRatio));
			roundedPanelMortalidad.add(spnMortalidad);
			
			RoundedPanel roundedPanelTEnfermedad = new RoundedPanel();
			roundedPanelTEnfermedad.setLayout(null);
			roundedPanelTEnfermedad.setRoundTopRight(18);
			roundedPanelTEnfermedad.setRoundTopLeft(18);
			roundedPanelTEnfermedad.setRoundBottomRight(18);
			roundedPanelTEnfermedad.setRoundBottomLeft(18);
			roundedPanelTEnfermedad.setBackground(Color.WHITE);
			roundedPanelTEnfermedad.setBounds((int)(91*widthRatio),(int)(328*heightRatio), (int)(113*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(roundedPanelTEnfermedad);
			
			JLabel lblTEnfermedad = new JLabel("T. Enfermedad");
			lblTEnfermedad.setOpaque(true);
			lblTEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
			lblTEnfermedad.setForeground(new Color(65, 105, 225));
			lblTEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblTEnfermedad.setBackground(Color.WHITE);
			lblTEnfermedad.setBounds((int)(3*widthRatio),(int)(13*heightRatio), (int)(116*widthRatio),(int)(22*heightRatio));
			roundedPanelTEnfermedad.add(lblTEnfermedad);
			
			cbxTipoEnfermedad = new JComboBox();
			cbxTipoEnfermedad.setBounds((int)(229*widthRatio),(int)(328*heightRatio), (int)(216*widthRatio),(int)(46*heightRatio));
			panelDatosEnfermedad.add(cbxTipoEnfermedad);
			cbxTipoEnfermedad.setBorder(null);
			cbxTipoEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"Alergia", "Enf. Autoinmune", "Enf. Cardiovascular", "Enf. de la Mujer", "Enf. de la Sangre", "Enf. Mentales", "Enf. infecciosa"}));
			cbxTipoEnfermedad.setSelectedIndex(0);
			cbxTipoEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			RoundedPanel roundedPanelTablaSintoma = new RoundedPanel();
			roundedPanelTablaSintoma.setLayout(null);
			roundedPanelTablaSintoma.setBackground(Color.WHITE);
			roundedPanelTablaSintoma.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosEnfermedad.add(roundedPanelTablaSintoma);
			
			JLabel lblImagen = new JLabel("AQUI VA LA TABLA DE SINTOMAS");
			lblImagen.setOpaque(true);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagen.setForeground(new Color(65, 105, 225));
			lblImagen.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			lblImagen.setBackground(Color.WHITE);
			lblImagen.setBounds((int)(203*widthRatio),(int)(101*heightRatio), (int)(348*widthRatio),(int)(22*heightRatio));
			roundedPanelTablaSintoma.add(lblImagen);
			
			RoundedGlowPanel roundedGlowPanelNombreEnfermedad = new RoundedGlowPanel();
			roundedGlowPanelNombreEnfermedad.setLayout(null);
			roundedGlowPanelNombreEnfermedad.setRoundTopRight(60);
			roundedGlowPanelNombreEnfermedad.setRoundTopLeft(60);
			roundedGlowPanelNombreEnfermedad.setRoundBottomRight(60);
			roundedGlowPanelNombreEnfermedad.setRoundBottomLeft(60);
			roundedGlowPanelNombreEnfermedad.setGlowColor(Color.CYAN);
			roundedGlowPanelNombreEnfermedad.setGlowAlpha(170);
			roundedGlowPanelNombreEnfermedad.setForeground(Color.WHITE);
			roundedGlowPanelNombreEnfermedad.setBorder(null);
			roundedGlowPanelNombreEnfermedad.setBackground(Color.WHITE);
			roundedGlowPanelNombreEnfermedad.setBounds((int)(77*widthRatio),(int)(161*heightRatio),  (int)(562*widthRatio),(int)(59*heightRatio));
			panelDatosEnfermedad.add(roundedGlowPanelNombreEnfermedad);
			
			RoundedGlowPanel roundedGlowPanelSintoma = new RoundedGlowPanel();
			roundedGlowPanelSintoma.setLayout(null);
			roundedGlowPanelSintoma.setRoundTopRight(60);
			roundedGlowPanelSintoma.setRoundTopLeft(60);
			roundedGlowPanelSintoma.setRoundBottomRight(60);
			roundedGlowPanelSintoma.setRoundBottomLeft(60);
			roundedGlowPanelSintoma.setGlowColor(Color.CYAN);
			roundedGlowPanelSintoma.setGlowAlpha(170);
			roundedGlowPanelSintoma.setForeground(Color.WHITE);
			roundedGlowPanelSintoma.setBorder(null);
			roundedGlowPanelSintoma.setBackground(Color.WHITE);
			roundedGlowPanelSintoma.setBounds((int)(77*widthRatio),(int)(405*heightRatio), (int)(140*widthRatio),(int)(59*heightRatio));
			panelDatosEnfermedad.add(roundedGlowPanelSintoma);
			
			RoundedGlowPanel roundedGlowPanelMortalidad = new RoundedGlowPanel();
			roundedGlowPanelMortalidad.setLayout(null);
			roundedGlowPanelMortalidad.setRoundTopRight(60);
			roundedGlowPanelMortalidad.setRoundTopLeft(60);
			roundedGlowPanelMortalidad.setRoundBottomRight(60);
			roundedGlowPanelMortalidad.setRoundBottomLeft(60);
			roundedGlowPanelMortalidad.setGlowColor(Color.CYAN);
			roundedGlowPanelMortalidad.setGlowAlpha(170);
			roundedGlowPanelMortalidad.setForeground(Color.WHITE);
			roundedGlowPanelMortalidad.setBorder(null);
			roundedGlowPanelMortalidad.setBackground(Color.WHITE);
			roundedGlowPanelMortalidad.setBounds((int)(77*widthRatio),(int)(243*heightRatio),  (int)(181*widthRatio),(int)(59*heightRatio));
			panelDatosEnfermedad.add(roundedGlowPanelMortalidad);
			
			RoundedGlowPanel roundedGlowPanelTEnfermedad = new RoundedGlowPanel();
			roundedGlowPanelTEnfermedad.setLayout(null);
			roundedGlowPanelTEnfermedad.setRoundTopRight(60);
			roundedGlowPanelTEnfermedad.setRoundTopLeft(60);
			roundedGlowPanelTEnfermedad.setRoundBottomRight(60);
			roundedGlowPanelTEnfermedad.setRoundBottomLeft(60);
			roundedGlowPanelTEnfermedad.setGlowColor(Color.CYAN);
			roundedGlowPanelTEnfermedad.setGlowAlpha(170);
			roundedGlowPanelTEnfermedad.setForeground(Color.WHITE);
			roundedGlowPanelTEnfermedad.setBorder(null);
			roundedGlowPanelTEnfermedad.setBackground(Color.WHITE);
			roundedGlowPanelTEnfermedad.setBounds((int)(77*widthRatio),(int)(324*heightRatio),  (int)(140*widthRatio),(int)(53*heightRatio));
			panelDatosEnfermedad.add(roundedGlowPanelTEnfermedad);
			
			chbxVigilancia = new JCheckBox("Bajo Vigilancia");
			chbxVigilancia.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			chbxVigilancia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			chbxVigilancia.setBackground(Color.WHITE);
			chbxVigilancia.setBounds((int)(275*widthRatio),(int)(2662*heightRatio), (int)(163*widthRatio),(int)(22*heightRatio));
			panelDatosEnfermedad.add(chbxVigilancia);
			
			cbxSintomas = new JComboBox();
			//cbxSintomas.setSelectedIndex(0);
			cbxSintomas.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			cbxSintomas.setBorder(null);
			cbxSintomas.setBounds((int)(229*widthRatio),(int)(412*heightRatio), (int)(216*widthRatio), (int)(46*heightRatio));
			panelDatosEnfermedad.add(cbxSintomas);
			

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
		roundedGlowPanelModificar.setBounds((int)(1105*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelModificar);
		
		lblModificar = new JLabel("Modificar");
		lblModificar.setEnabled(false);
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setForeground(new Color(60, 179, 113));
		lblModificar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblModificar.setBackground(Color.WHITE);
		lblModificar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
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
		
		RoundedGlowPanel roundedGlowPanelEliminar = new RoundedGlowPanel();
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
		roundedGlowPanelRegistrar.setBounds((int)(961*widthRatio),(int)(599*heightRatio), (int)(132*widthRatio),(int)(49*heightRatio));
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
		roundedGlowPanelRegistrar.setBackground(Color.WHITE);

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
		
		lblRegistrar_1 = new JLabel("Registrar");
		lblRegistrar_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar_1.setForeground(new Color(100, 149, 237));
		lblRegistrar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar_1.setEnabled(false);
		lblRegistrar_1.setBackground(Color.WHITE);
		lblRegistrar_1.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar_1);
		
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
		
		RoundedGlowPanel roundedGlowPanelBuscarEnfermedad = new RoundedGlowPanel();
		roundedGlowPanelBuscarEnfermedad.setLayout(null);
		roundedGlowPanelBuscarEnfermedad.setRoundTopRight(60);
		roundedGlowPanelBuscarEnfermedad.setRoundTopLeft(60);
		roundedGlowPanelBuscarEnfermedad.setRoundBottomRight(60);
		roundedGlowPanelBuscarEnfermedad.setRoundBottomLeft(60);
		roundedGlowPanelBuscarEnfermedad.setGlowColor(Color.CYAN);
		roundedGlowPanelBuscarEnfermedad.setGlowAlpha(170);
		roundedGlowPanelBuscarEnfermedad.setForeground(Color.WHITE);
		roundedGlowPanelBuscarEnfermedad.setBorder(null);
		roundedGlowPanelBuscarEnfermedad.setBackground(Color.WHITE);
		roundedGlowPanelBuscarEnfermedad.setBounds((int)(817*widthRatio),(int)(541*heightRatio), (int)(550*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelBuscarEnfermedad);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(new Color(100, 149, 237));
		lblBuscar.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblBuscar.setBackground(Color.WHITE);
		lblBuscar.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelBuscarEnfermedad.add(lblBuscar);
		
		txtBuscarEnfermedad = new JTextField();
		txtBuscarEnfermedad.setOpaque(false);
		txtBuscarEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		txtBuscarEnfermedad.setColumns(10);
		txtBuscarEnfermedad.setBorder(null);
		txtBuscarEnfermedad.setBounds((int)(101*widthRatio), 0, (int)(428*widthRatio),(int)(49*heightRatio));
		txtBuscarEnfermedad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableEnfermedad.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableEnfermedad.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEnfermedad.getText()));
			}
		});
		roundedGlowPanelBuscarEnfermedad.add(txtBuscarEnfermedad);
		txtBuscarEnfermedad.setColumns(10);
		
	}
}