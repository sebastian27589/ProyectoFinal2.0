package visual;

import java.awt.BorderLayout;
import javax.swing.JPanel;
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
import logico.Clinica;
import logico.Enfermedad;
import logico.Paciente;
import logico.PanelSimulacionAnim;
import logico.RoundedGlowPanel;
import logico.RoundedPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import keeptoo.KGradientPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JCheckBox;

public class VisualEnfermedad extends PanelSimulacionAnim {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<Integer> selectedSin = new ArrayList<Integer>();
	private static int indexSin = 0;
	private int lugar = 0;
	
	private static DefaultTableModel model;
	private static DefaultTableModel modelSin;
	private Dimension dim;
	private JTable tableEnfermedad;
	private static Object[] row;
	private Enfermedad selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private static JTextField txtCodeEnfermedad;
	private JTextField txtNombreEnfermedad;
	private char sexoPaciente;
	private JComboBox cbxTipoEnfermedad;
	private JPanel panelTablaEnfermedad;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private JTextField txtBuscarEnfermedad;
	private JLabel lblRegistrar_1;
	private JSpinner spnMortalidad;
	private JLabel lblRegistroDeEnfermedad;
	private RoundedGlowPanel roundedGlowPanelRegistrar;
	private JCheckBox chbxVigilancia;
	private RoundedGlowPanel roundedGlowPanelModificar;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private static JTable tableSintoma;
	
	/**
	 * Create the dialog.
	 */
	public VisualEnfermedad(Connection conexion) 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		Object[] header = {"ID_Enfermedad", "ID_T_Enfermedad", "Nombre_Enfermedad", "Niv_Mortalidad"};
		Object[] headerSin = {"ID_Síntoma", "Nombre_Síntoma", "Elegir"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		modelSin = new DefaultTableModel() {
			
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
		
		modelSin.setColumnIdentifiers(headerSin);
		
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
		
		panelTablaEnfermedad = new JPanel();
		panelTablaEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaEnfermedad.setBackground(new Color(255, 255, 255));
		panelTablaEnfermedad.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(550*widthRatio),(int)(515*heightRatio));
		add(panelTablaEnfermedad);
		panelTablaEnfermedad.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(tableEnfermedad);
		panelTablaEnfermedad.add(scrollPane, BorderLayout.CENTER);
		
		tableEnfermedad = new JTable(model);
		tableEnfermedad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEnfermedad.getTableHeader().setResizingAllowed(false);
		tableEnfermedad.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		tableEnfermedad.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableEnfermedad.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableEnfermedad.getColumnModel().getColumn(2).setPreferredWidth(10);
		tableEnfermedad.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				loadSintomas(conexion);
				selectedSin.clear();
				indexSin = 0;
				lugar = 0;
				//tableSintoma.clearSelection();
				
				int ind = tableEnfermedad.rowAtPoint(e.getPoint());
		        if (ind == -1) 
		        { 
		            limpiarDatos(); 
		            tableEnfermedad.clearSelection();
		            
		        } else {
					selected = Clinica.getInstance().buscarEnfermedadByCode(conexion, (int) tableEnfermedad.getValueAt(tableEnfermedad.getSelectedRow(), 0));
					
					chbxVigilancia.setSelected(false);
					
					txtCodeEnfermedad.setText("E-"+selected.getID_Enfermedad());
					txtNombreEnfermedad.setText(selected.getNombre_Enfermedad());
					spnMortalidad.setValue(selected.getIndPeligro());
					
					if(selected.isVigilada() == true) {
						chbxVigilancia.setSelected(true);
					} else {
						chbxVigilancia.setSelected(false);
					}
					
					cbxTipoEnfermedad.setSelectedIndex(selected.getID_Tipo_Enfermedad());
					loadSintomasEnf(conexion, selected.getID_Enfermedad());
					
					roundedGlowPanelEliminar.setEnabled(true);
					roundedGlowPanelEliminar.setBackground(Color.WHITE);
					roundedGlowPanelModificar.setEnabled(true);
					roundedGlowPanelModificar.setBackground(Color.WHITE);
					roundedGlowPanelRegistrar.setEnabled(false);
					roundedGlowPanelRegistrar.setBackground(new Color(240,240,240));
					lblModificar.setEnabled(true);
					lblEliminar.setEnabled(true);
					lblRegistrar_1.setEnabled(false);
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
			txtCodeEnfermedad.setBounds((int)(92*widthRatio),(int)(14*heightRatio), (int)(48*widthRatio),(int)(22*heightRatio));
			roundedPanelCodeEnfermedad.add(txtCodeEnfermedad);
			txtCodeEnfermedad.setBorder(null);
			txtCodeEnfermedad.setEditable(false);
			txtCodeEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
			txtCodeEnfermedad.setColumns(10);
			//txtCodeEnfermedad.setText("E-"+Clinica.getInstance().getGeneradorCodeEnfermedad()+1);
			
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
			cbxTipoEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"Elegir"}));
			cbxTipoEnfermedad.setSelectedIndex(0);
			cbxTipoEnfermedad.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			
			RoundedPanel roundedPanelTablaSintoma = new RoundedPanel();
			roundedPanelTablaSintoma.setBackground(Color.WHITE);
			roundedPanelTablaSintoma.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosEnfermedad.add(roundedPanelTablaSintoma);
			roundedPanelTablaSintoma.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_Sintoma = new JScrollPane();
			roundedPanelTablaSintoma.add(scrollPane_Sintoma, BorderLayout.CENTER);
			
			tableSintoma = new JTable(modelSin);
			tableSintoma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableSintoma.getSelectedRow(), colIndex = tableSintoma.getSelectedColumn();
					
					if (rowIndex >= 0) 
					{
						
						if (colIndex == 2) 
						{
							if(tableSintoma.getValueAt(rowIndex, colIndex) == Boolean.TRUE) {
								selectedSin.add(Integer.valueOf((tableSintoma.getValueAt(rowIndex, colIndex-2).toString())));
								indexSin++;
							} else {
								selectedSin.remove(Integer.valueOf((tableSintoma.getValueAt(rowIndex, colIndex-2).toString())));
								indexSin--;
							}
						}
					}
					
					validarCampos();
				}
			});
			
			tableSintoma.getTableHeader().setResizingAllowed(false);
			tableSintoma.getTableHeader().setReorderingAllowed(false);
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);	
			
			for (int index = 0; index < tableSintoma.getColumnCount(); index++) {
				
				if (index != 2) {
					
					tableSintoma.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tableSintoma.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableSintoma.getColumnModel().getColumn(0).setPreferredWidth(10);
			tableSintoma.getColumnModel().getColumn(1).setPreferredWidth(10);
			tableSintoma.getColumnModel().getColumn(2).setPreferredWidth(10);
			tableSintoma.setFillsViewportHeight(true);
			tableSintoma.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			scrollPane_Sintoma.setViewportView(tableSintoma);
			
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
			
			chbxVigilancia = new JCheckBox("Vigilancia");
			chbxVigilancia.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
			chbxVigilancia.setBounds((int)(91*widthRatio),(int)(401*heightRatio), (int)(129*widthRatio),(int)(25*heightRatio));
			panelDatosEnfermedad.add(chbxVigilancia);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds((int)(0*widthRatio),(int)(780*heightRatio), (int)(1381*widthRatio),(int)(120*heightRatio));
		add(gradientPanel);
		gradientPanel.setLayout(null);
		
		roundedGlowPanelModificar = new RoundedGlowPanel();
		roundedGlowPanelModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelModificar.isEnabled() == true) 
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar los datos de la enfermedad con ID <" + selected.getID_Enfermedad() + "> y cuyo nombre es: <" + selected.getNombre_Enfermedad() + ">?", "Modificar Enfermedad", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) 
					{
						boolean modSintoma = Clinica.getInstance().modificarSintomas(conexion, selected.getID_Enfermedad(), selectedSin, indexSin);
						
						if(modSintoma == true) 
						{
							boolean vigilancia;
							if(chbxVigilancia.isSelected())
								vigilancia = true;
							else
								vigilancia = false;
							
							boolean mod = Clinica.getInstance().modificarEnfermedad(conexion, cbxTipoEnfermedad.getSelectedItem().toString(), 
									txtNombreEnfermedad.getText(), vigilancia, new Integer(spnMortalidad.getValue().toString()), (int) tableEnfermedad.getValueAt(tableEnfermedad.getSelectedRow(), 0));
							
							if(mod == true) 
							{
								JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Enfermedad", JOptionPane.INFORMATION_MESSAGE);
								
								selectedSin.clear();
								indexSin = 0;
								loadEnfermedades(conexion);
								loadSintomas(conexion);
								limpiarDatos();
								
							} else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Modificar la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,"¡Hubo un Error al Modificar Los Síntomas de la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
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
		roundedGlowPanelModificar.setBackground(new Color(240,240,240));
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
		
		roundedGlowPanelEliminar = new RoundedGlowPanel();
		roundedGlowPanelEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				if(roundedGlowPanelEliminar.isEnabled() == true) 
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la enfermedad con ID <" + selected.getID_Enfermedad() + "> y cuyo nombre es: <" + selected.getNombre_Enfermedad() + ">?", "Eliminar Enfermedad", JOptionPane.OK_CANCEL_OPTION);
					
					if (Option == JOptionPane.OK_OPTION) 
					{
						boolean elimSin = Clinica.getInstance().eliminarSintomas(conexion, selected.getID_Enfermedad());
						
						if(elimSin == true) 
						{
							boolean elim = Clinica.getInstance().eliminarEnfermedad(conexion, (int) tableEnfermedad.getValueAt(tableEnfermedad.getSelectedRow(), 0));
							
							if(elim == true) 
							{
								JOptionPane.showMessageDialog(null, "Eliminado con éxito", "Eliminar Enfermedad", JOptionPane.INFORMATION_MESSAGE);
								selectedSin.clear();
								indexSin = 0;
								loadEnfermedades(conexion);
								loadSintomas(conexion);
								limpiarDatos();
								
							} else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Eliminar la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,"¡Ocurrió un Error Al Eliminar los Síntomas de la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
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
		roundedGlowPanelEliminar.setBackground(new Color(240,240,240));
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
				
				if(roundedGlowPanelRegistrar.isEnabled() == true) 
				{
					boolean vigilancia;
					if(chbxVigilancia.isSelected())
						vigilancia = true;
					else
						vigilancia = false;
					
					boolean res = Clinica.getInstance().insertarEnfermedad(conexion, cbxTipoEnfermedad.getSelectedItem().toString(), 
									txtNombreEnfermedad.getText(), vigilancia, new Integer(spnMortalidad.getValue().toString()));
					if(res) 
					{
						boolean res2 = Clinica.getInstance().insertarSintomas(conexion, (Clinica.getInstance().getGeneradorCodeEnfermedad()+1), selectedSin, indexSin);
						
						if(res2) 
						{
							JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Enfermedad", JOptionPane.INFORMATION_MESSAGE);
							loadEnfermedades(conexion);
							loadSintomas(conexion);
							limpiarDatos();
							indexSin = 0;
							selectedSin.clear();
						}
						else {
							JOptionPane.showMessageDialog(null,"¡No Se Pudieron insertar los Síntomas!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar la Enfermedad!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		roundedGlowPanelRegistrar.setEnabled(false);
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
		roundedGlowPanelRegistrar.setBackground(new Color(240,240,240));
		
		lblRegistrar_1 = new JLabel("Registrar");
		lblRegistrar_1.setEnabled(false);
		lblRegistrar_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar_1.setForeground(new Color(100, 149, 237));
		lblRegistrar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar_1.setBackground(Color.WHITE);
		lblRegistrar_1.setBounds(0, 0, (int)(132*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar_1);
		
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
				
				DefaultTableModel searchModel5 = (DefaultTableModel) tableSintoma.getModel();
				TableRowSorter<DefaultTableModel> searchModel6 = new TableRowSorter<DefaultTableModel>(searchModel5);
				tableSintoma.setRowSorter(searchModel6);
				searchModel6.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEnfermedad.getText()));
			}
		});
		roundedGlowPanelBuscarEnfermedad.add(txtBuscarEnfermedad);
		txtBuscarEnfermedad.setColumns(10);
		
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
	    
	    txtNombreEnfermedad.addKeyListener(campoListener);
	    cbxTipoEnfermedad.addActionListener(cbxListener);
	    
	    llenarComboBoxBD(conexion, cbxTipoEnfermedad);
	    loadEnfermedades(conexion);
	    loadSintomas(conexion);
	    limpiarDatos();
	}

	public static void loadEnfermedades(Connection conexion) {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Enfermedad, ID_Tipo_Enfermedad, Nombre_Enfermedad, Peligrosidad FROM Enfermedad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Enfermedad");
            	row[1] = resultSet.getInt("ID_Tipo_Enfermedad");
            	row[2] = resultSet.getString("Nombre_Enfermedad");
            	row[3] = resultSet.getInt("Peligrosidad");
            	model.addRow(row);
            	
				Clinica.setGeneradorCodeEnfermedad(resultSet.getInt("ID_Enfermedad"));
				txtCodeEnfermedad.setText("E-"+(Clinica.getGeneradorCodeEnfermedad()+1));
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadSintomas(Connection conexion) {
	
		modelSin.setRowCount(0);
		row = new Object[modelSin.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Sintoma, Nombre_Sintoma FROM Sintoma;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("ID_Sintoma");
            	row[1] = resultSet.getString("Nombre_Sintoma");
            	modelSin.addRow(row);
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadSintomasEnf(Connection conexion, int idEnf) {
		
		int filasTot = tableSintoma.getRowCount();
		indexSin = 0;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Enfermedad.ID_Enfermedad, Sintoma.ID_Sintoma, Sintoma.Nombre_Sintoma FROM Enfermedad "
            				 + "INNER JOIN Enfermedad_Sintoma ON Enfermedad.ID_Enfermedad = Enfermedad_Sintoma.ID_Enfermedad "
            				 + "INNER JOIN Sintoma ON Enfermedad_Sintoma.ID_Sintoma = Sintoma.ID_Sintoma WHERE Enfermedad.ID_Enfermedad = "+idEnf+";";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getString("ID_Sintoma");
            	row[1] = resultSet.getString("Nombre_Sintoma");
            	for(int ind = 0; ind < filasTot; ind++) {
            		if(tableSintoma.getValueAt(ind, 0).toString().equals(resultSet.getString("ID_Sintoma"))) {
            			tableSintoma.setValueAt(Boolean.TRUE, ind, 2);
            			selectedSin.add(Integer.valueOf(tableSintoma.getValueAt(ind, 0).toString()));
            			indexSin++;
            		}
            	}
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	

	private void llenarComboBoxBD(Connection conexion, JComboBox cbxTipoEnfermedad) {
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			String selectSql = "SELECT Nombre_Tipo_Enfermedad FROM Tipo_Enfermedad";
			ResultSet resulSet = statement.executeQuery(selectSql);
			
			while(resulSet.next()) {
				cbxTipoEnfermedad.addItem(resulSet.getString("Nombre_Tipo_Enfermedad"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	protected void limpiarDatos() {
	
		chbxVigilancia.setSelected(false);
		cbxTipoEnfermedad.setSelectedIndex(0);
		spnMortalidad.setValue(0);
		txtNombreEnfermedad.setText("");
		roundedGlowPanelRegistrar.setEnabled(false);
		roundedGlowPanelRegistrar.setBackground(new Color(240, 240, 240));
        roundedGlowPanelEliminar.setEnabled(false);
		roundedGlowPanelEliminar.setBackground(new Color(240,240,240));
		roundedGlowPanelModificar.setEnabled(false);
		roundedGlowPanelModificar.setBackground(new Color(240,240,240));
		lblModificar.setEnabled(false);
		lblEliminar.setEnabled(false);
		lblRegistrar_1.setEnabled(false);
		txtCodeEnfermedad.setText("E-" + (Clinica.getGeneradorCodeEnfermedad() + 1));
		selected = null;
	}

	private void validarCampos() {
		
		if(selected == null) 
		{
			
			if(!txtNombreEnfermedad.getText().isEmpty() && (cbxTipoEnfermedad.getSelectedIndex() != 0)) 
			{
				
			   roundedGlowPanelRegistrar.setEnabled(true);
			   roundedGlowPanelRegistrar.setBackground(Color.WHITE);
			   lblRegistrar_1.setEnabled(true);
				   
			} else {
					
			   roundedGlowPanelRegistrar.setEnabled(false);
			   roundedGlowPanelRegistrar.setBackground(new Color(240, 240, 240));
			   lblRegistrar_1.setEnabled(false);
				   
			}
		}
		
	}
}