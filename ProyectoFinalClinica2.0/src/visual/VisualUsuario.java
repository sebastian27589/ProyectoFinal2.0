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
import logico.Clinica;
import logico.Medico;
import logico.Paciente;
import logico.PanelSimulacionAnim;
import logico.Persona;
import logico.RoundedGlowPanel;
import logico.RoundedPanel;
import logico.Usuario;
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

public class VisualUsuario extends PanelSimulacionAnim {

	private static DefaultTableModel modelPersona;
	private static DefaultTableModel modelMedico;
	private static DefaultTableModel modelUsuario;
	private Dimension dim;
	private JTable tablePersona;
	private static Object[] row;
	private Persona selectedPersona;
	private Medico selectedMedico;
	private Usuario selectedUsuario;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	
	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private JTextField txtCodeUsuario;
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
	private JPanel panelTablaPersona;
	private RoundedGlowPanel roundedGlowPanelEliminar;
	private JLabel lblEliminar;
	private JLabel lblModificar;
	private JLabel lblRegistrar;
	private RoundedGlowPanel roundedGlowPanelRegistrar;
	private JLabel lblRegistrar1;
	private RoundedGlowPanel roundedGlowPanelBuscarPaciente;
	private JLabel lblBuscar;
	private JTextField txtBuscarPaciente;
	private RoundedGlowPanel roundedGlowCargo;
	private JComboBox cbxCargo;
	private JTable tableMedico;
	private JTable tableUsuario;
	private RoundedGlowPanel roundedGlowPanelModificar;
	

	/**
	 * Create the dialog.
	 */
	public VisualUsuario(Connection conexion) 
	{
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		
		Object[] headerPersona = {"Doc_Identidad", "P_Nombre", "S_Nombre", "P_Apellido", "S_Apellido"};
		Object[] headerUsuario = {"ID_Adm", "Cargo", "Nombre_Usuario", "Pass"};
		
		modelPersona = new DefaultTableModel();
		modelPersona.setColumnIdentifiers(headerPersona);
		modelMedico = new DefaultTableModel();
		modelMedico.setColumnIdentifiers(headerPersona);
		modelUsuario = new DefaultTableModel();
		modelUsuario.setColumnIdentifiers(headerUsuario);
		
		setBounds(100, 100, 1444, 993);
		setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		setBackground(new Color(248, 248, 255));
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
	
		panelTablaPersona = new JPanel();
		panelTablaPersona.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaPersona.setBackground(new Color(255, 255, 255));
		panelTablaPersona.setBounds((int)(814*widthRatio),(int)(13*heightRatio), (int)(555*widthRatio),(int)(209*heightRatio));
		add(panelTablaPersona);
		panelTablaPersona.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPanePersona = new JScrollPane(tablePersona);
		panelTablaPersona.add(scrollPanePersona);
		
		tablePersona = new JTable(modelPersona);
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ind = tablePersona.rowAtPoint(e.getPoint());
		        if (ind == -1) 
		        {
		        	limpiarDatos();
					
		        } else {
		        	
		        	tableMedico.clearSelection();
					tableUsuario.clearSelection();
					selectedMedico = null;
					selectedUsuario = null;
					selectedPersona = Clinica.getInstance().buscarPersonaByCode(conexion, tablePersona.getValueAt(tablePersona.getSelectedRow(), 0).toString());
		        }

			}
		});
		tablePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePersona.getTableHeader().setResizingAllowed(false);
		tablePersona.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		tablePersona.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablePersona.getColumnModel().getColumn(1).setPreferredWidth(20);
		tablePersona.getColumnModel().getColumn(2).setPreferredWidth(20);
		tablePersona.getColumnModel().getColumn(3).setPreferredWidth(20);
		tablePersona.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablePersona.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*widthRatio)));
		tablePersona.setFillsViewportHeight(true);
		scrollPanePersona.setViewportView(tablePersona);
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
			//txtCodeUsuario.setText("U-"+Clinica.getInstance().getGeneradorCodePaciente()+1);
			
			JLabel lblRegistroDeCitas = new JLabel("Administraci\u00F3n");
			lblRegistroDeCitas.setOpaque(true);
			lblRegistroDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeCitas.setForeground(new Color(0, 0, 0));
			lblRegistroDeCitas.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
			lblRegistroDeCitas.setBackground(new Color(240, 240, 240));
			lblRegistroDeCitas.setBounds((int)(157*widthRatio),(int)(20*heightRatio), (int)(416*widthRatio),(int)(46*heightRatio));
			panelDatosPersona.add(lblRegistroDeCitas);
			
			RoundedPanel roundedPanelTablaUsuario = new RoundedPanel();
			roundedPanelTablaUsuario.setRoundTopRight(18);
			roundedPanelTablaUsuario.setRoundTopLeft(18);
			roundedPanelTablaUsuario.setRoundBottomRight(18);
			roundedPanelTablaUsuario.setRoundBottomLeft(18);
			roundedPanelTablaUsuario.setBackground(Color.WHITE);
			roundedPanelTablaUsuario.setBounds((int)(0*widthRatio),(int)(512*heightRatio), (int)(790*widthRatio),(int)(209*heightRatio));
			panelDatosPersona.add(roundedPanelTablaUsuario);
			roundedPanelTablaUsuario.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPaneUsuario = new JScrollPane();
			roundedPanelTablaUsuario.add(scrollPaneUsuario, BorderLayout.CENTER);
			
			tableUsuario = new JTable(modelUsuario);
			tableUsuario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int ind = tableUsuario.rowAtPoint(e.getPoint());
			        if (ind == -1) 
			        {
			        	limpiarDatos();
						
			        } else {
			        	
						tablePersona.clearSelection();
						tableMedico.clearSelection();
						selectedPersona = null;
						selectedMedico = null;
					
						selectedUsuario = Clinica.getInstance().buscarAdmByCode(conexion, (int) tableUsuario.getValueAt(tableUsuario.getSelectedRow(), 0));
			        
						txtUsuario.setText(selectedUsuario.getNombreUsuario());
						txtPassword.setText(selectedUsuario.getContrasena());
						cbxCargo.setSelectedItem(selectedUsuario.getCargoUsuario());
						
						txtCodeUsuario.setText("U-"+(Clinica.getGeneradorCodeUsuario()+1));
						validarCampos();
						
						roundedGlowPanelEliminar.setEnabled(true);
						roundedGlowPanelEliminar.setBackground(Color.WHITE);
						roundedGlowPanelModificar.setEnabled(true);
						roundedGlowPanelModificar.setBackground(Color.WHITE);
						roundedGlowPanelRegistrar.setEnabled(false);
						roundedGlowPanelRegistrar.setBackground(new Color(240,240,240));
						lblModificar.setEnabled(true);
						lblEliminar.setEnabled(true);
						lblRegistrar1.setEnabled(false);
			        }
			    }
			});
			tableUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableUsuario.getTableHeader().setResizingAllowed(false);
			tableUsuario.getTableHeader().setReorderingAllowed(false);
			tableUsuario.getColumnModel().getColumn(0).setPreferredWidth(20);
			tableUsuario.getColumnModel().getColumn(1).setPreferredWidth(20);
			tableUsuario.getColumnModel().getColumn(2).setPreferredWidth(20);
			tableUsuario.getColumnModel().getColumn(3).setPreferredWidth(20);
			tableUsuario.setFillsViewportHeight(true);
			tableUsuario.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*heightRatio)));
			scrollPaneUsuario.setViewportView(tableUsuario);
			
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
			lblUsuario.setBounds((int)(12*widthRatio),(int)(13*heightRatio), (int)(77*widthRatio),(int)(22*heightRatio));
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
			cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"Elegir", "Secretario", "M\u00E9dico", "Admin"}));
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
		
		roundedGlowPanelModificar = new RoundedGlowPanel();
		roundedGlowPanelModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(roundedGlowPanelModificar.isEnabled() == true) 
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de modificar los datos del Usuario con usuario <" + selectedUsuario.getNombreUsuario() + ">?", "Modificar Usuario", JOptionPane.OK_CANCEL_OPTION);
					
					if((selectedPersona == null && selectedMedico == null) && selectedUsuario != null) 
					{
						if (Option == JOptionPane.OK_OPTION) 
						{
							
							boolean mod = Clinica.getInstance().modificarUsuario(conexion, tableUsuario.getValueAt(tableUsuario.getSelectedRow(), 0).toString(),
									txtUsuario.getText(), txtPassword.getText(), cbxCargo.getSelectedItem().toString());
							
							if(mod == true) 
							{
								JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Usuario", JOptionPane.INFORMATION_MESSAGE);
								
								loadPersonas(conexion);
								loadMedicos(conexion);
								loadUsuarios(conexion);
								limpiarDatos();
								
								roundedGlowPanelEliminar.setEnabled(false);
								roundedGlowPanelEliminar.setBackground(new Color(240,240,240));
								lblEliminar.setEnabled(false);
								roundedGlowPanelModificar.setEnabled(false);
								roundedGlowPanelModificar.setBackground(new Color(240,240,240));
								lblModificar.setEnabled(false);
							}
							else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Modificar el Usuario!", "Error", JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null,"¡No se pudo realizar esta Acción!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,"¡No seleccione otras columnas además de la tabla de Usuarios!", "Error", JOptionPane.ERROR_MESSAGE);
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
		roundedGlowPanelModificar.setBounds((int)(1109*widthRatio),(int)(598*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
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
				
				if(roundedGlowPanelEliminar.isEnabled() == true)
				{
					int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el Usuario con nombre <" + selectedUsuario.getNombreUsuario() + ">?", "Eliminar Usuario", JOptionPane.OK_CANCEL_OPTION);
				
					if((selectedPersona == null && selectedMedico == null) && selectedUsuario != null) 
					{
						if (Option == JOptionPane.OK_OPTION) 
						{
							boolean elim = Clinica.getInstance().eliminarUsuarioSQL(conexion, (int) tableUsuario.getValueAt(tableUsuario.getSelectedRow(), 0));
							
							if(elim == true) 
							{
								JOptionPane.showMessageDialog(null, "Eliminado con éxito", "Eliminar Usuario", JOptionPane.INFORMATION_MESSAGE);
								
								loadPersonas(conexion);
								loadMedicos(conexion);
								loadUsuarios(conexion);
								limpiarDatos();
								
								roundedGlowPanelEliminar.setEnabled(false);
								roundedGlowPanelEliminar.setBackground(new Color(240,240,240));
								lblEliminar.setEnabled(false);
								roundedGlowPanelModificar.setEnabled(false);
								roundedGlowPanelModificar.setBackground(new Color(240,240,240));
								lblModificar.setEnabled(false);
								
							} else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Eliminar el Usuario!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null,"¡No seleccione otras columnas además de la tabla de Usuarios!", "Error", JOptionPane.ERROR_MESSAGE);
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
		roundedGlowPanelEliminar.setBounds((int)(1251*widthRatio),(int)(598*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
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
					if(selectedPersona != null && selectedMedico == null && selectedUsuario == null) 
					{
						if(cbxCargo.getSelectedItem().toString().equals("Secretario") || cbxCargo.getSelectedItem().toString().equals("Administrador")) 
						{
							boolean res = Clinica.getInstance().insertarUsuarioSQL(conexion, tablePersona.getValueAt(tablePersona.getSelectedRow(), 0).toString(),
									txtUsuario.getText(), txtPassword.getText(), cbxCargo.getSelectedItem().toString());
					
							if(res) 
							{
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
								
							    loadPersonas(conexion);
							    loadMedicos(conexion);
							    loadUsuarios(conexion);
							    limpiarDatos();
							    
							} else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar el Usuario!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					
					if(selectedMedico != null && selectedPersona == null && selectedUsuario == null) 
					{
						System.out.println("No vacio");
						if(cbxCargo.getSelectedItem().toString().equals("Médico")) 
						{
							System.out.println("Entra");

							boolean res2 = Clinica.getInstance().insertarUsuarioSQL2(conexion, tableMedico.getValueAt(tableMedico.getSelectedRow(), 0).toString(),
									txtUsuario.getText(), txtPassword.getText(), cbxCargo.getSelectedItem().toString());
							
							if(res2) 
							{
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
								
							    loadPersonas(conexion);
							    loadMedicos(conexion);
							    loadUsuarios(conexion);
							    limpiarDatos();
							    
							} else {
								JOptionPane.showMessageDialog(null,"¡No Se Pudo Insertar el Usuario!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		roundedGlowPanelRegistrar.setEnabled(false);
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
		roundedGlowPanelRegistrar.setBounds((int)(965*widthRatio),(int)(598*heightRatio), (int)(118*widthRatio),(int)(49*heightRatio));
		add(roundedGlowPanelRegistrar);
		
		lblRegistrar1 = new JLabel("Registrar");
		lblRegistrar1.setEnabled(false);
		lblRegistrar1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar1.setForeground(new Color(100, 149, 237));
		lblRegistrar1.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(15*widthRatio)));
		lblRegistrar1.setBackground(Color.WHITE);
		lblRegistrar1.setBounds(0, 0, (int)(118*widthRatio),(int)(49*heightRatio));
		roundedGlowPanelRegistrar.add(lblRegistrar1);
		
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
		roundedGlowPanelBuscarPaciente.setBounds((int)(819*widthRatio),(int)(540*heightRatio), (int)(550*widthRatio),(int)(49*heightRatio));
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
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tablePersona.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tablePersona.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				
				DefaultTableModel searchModel3 = (DefaultTableModel) tableMedico.getModel();
				TableRowSorter<DefaultTableModel> searchModel4 = new TableRowSorter<DefaultTableModel>(searchModel3);
				tableMedico.setRowSorter(searchModel4);
				searchModel4.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
				
				DefaultTableModel searchModel5 = (DefaultTableModel) tableMedico.getModel();
				TableRowSorter<DefaultTableModel> searchModel6 = new TableRowSorter<DefaultTableModel>(searchModel5);
				tableMedico.setRowSorter(searchModel6);
				searchModel6.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
			}
		});
		roundedGlowPanelBuscarPaciente.add(txtBuscarPaciente);
		txtBuscarPaciente.setColumns(10);
		
		JPanel panelTablaMedico = new JPanel();
		panelTablaMedico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaMedico.setBackground(Color.WHITE);
		panelTablaMedico.setBounds((int)(814*widthRatio),(int)(318*heightRatio), (int)(555*widthRatio),(int)(209*heightRatio));
		add(panelTablaMedico);
		panelTablaMedico.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneMedico = new JScrollPane((Component) null);
		panelTablaMedico.add(scrollPaneMedico);
		
		tableMedico = new JTable(modelMedico);
		tableMedico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ind = tableMedico.rowAtPoint(e.getPoint());
		        if (ind == -1) 
		        {
		        	limpiarDatos();
					
		        } else {
		        	
					tablePersona.clearSelection();
					tableUsuario.clearSelection();
					selectedPersona = null;
					selectedUsuario = null;
					selectedMedico = Clinica.getInstance().buscarMedicoByCode(conexion, tableMedico.getValueAt(tableMedico.getSelectedRow(), 0).toString());
		        }
		    }
		});
		tableMedico.getTableHeader().setResizingAllowed(false);
		tableMedico.getTableHeader().setReorderingAllowed(false);
		
		tableMedico.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableMedico.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableMedico.getColumnModel().getColumn(2).setPreferredWidth(20);
		tableMedico.getColumnModel().getColumn(3).setPreferredWidth(20);
		tableMedico.getColumnModel().getColumn(4).setPreferredWidth(20);
		tableMedico.setFillsViewportHeight(true);
		tableMedico.setFont(new Font("Yu Gothic UI", Font.PLAIN, (int)(15*heightRatio)));
		scrollPaneMedico.setViewportView(tableMedico);
		
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
	    
	    txtUsuario.addKeyListener(campoListener);
	    txtPassword.addKeyListener(campoListener);
	    cbxCargo.addActionListener(cbxListener);
	    
	    loadPersonas(conexion);
	    loadMedicos(conexion);
	    loadUsuarios(conexion);
	    limpiarDatos();
	
	}
	
	protected void limpiarDatos() {
	
		txtUsuario.setText("");
		txtPassword.setText("");
		cbxCargo.setSelectedIndex(0);
		roundedGlowPanelRegistrar.setEnabled(false);
		roundedGlowPanelRegistrar.setBackground(new Color(240, 240, 240));
        roundedGlowPanelEliminar.setEnabled(false);
		roundedGlowPanelEliminar.setBackground(new Color(240,240,240));
		roundedGlowPanelModificar.setEnabled(false);
		roundedGlowPanelModificar.setBackground(new Color(240,240,240));
		lblModificar.setEnabled(false);
		lblEliminar.setEnabled(false);
		lblRegistrar1.setEnabled(false);
		txtCodeUsuario.setText("E-" + (Clinica.getGeneradorCodeUsuario() + 1));
		selectedPersona = null;
		selectedMedico = null;
		selectedUsuario = null;
		
	}

	public static void loadPersonas(Connection conexion) {
		
		modelPersona.setRowCount(0);
		row = new Object[modelPersona.getColumnCount()];
		
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
            	modelPersona.addRow(row);
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

            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void loadUsuarios(Connection conexion) {
		
		modelUsuario.setRowCount(0);
		row = new Object[modelUsuario.getColumnCount()];
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Administrativo, Cargo, Nombre_Usuario, Pass FROM Administrativo";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            	row[0] = resultSet.getInt("ID_Administrativo");
            	row[1] = resultSet.getString("Cargo");
            	row[2] = resultSet.getString("Nombre_Usuario");
            	row[3] = resultSet.getString("Pass");
            	modelUsuario.addRow(row);
            	
            	Clinica.setGeneradorCodeUsuario(resultSet.getInt("ID_Administrativo"));
            }
            

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	private void validarCampos() 
	{
		
		if((selectedPersona != null || selectedMedico != null) && selectedUsuario == null) 
		{
			if(!txtUsuario.getText().isEmpty() && !txtPassword.getText().isEmpty() && (cbxCargo.getSelectedIndex() != 0)) {
			
			   roundedGlowPanelRegistrar.setEnabled(true);
			   roundedGlowPanelRegistrar.setBackground(Color.WHITE);
			   lblRegistrar1.setEnabled(true);
			   
			} else {
			   roundedGlowPanelRegistrar.setEnabled(false);
			   roundedGlowPanelRegistrar.setBackground(new Color(240, 240, 240));
			   lblRegistrar1.setEnabled(false);
			}
		}
	}
}
