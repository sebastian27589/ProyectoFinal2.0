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
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import java.awt.Component;

public class VisualHistorial extends JPanel {

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
	// Posiblemente haya que cambiar esto a VisualPaciente
	private Paciente paciente = null;
	private char sexoPaciente;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JPanel panelDatosPersona;
	private JPanel panel_1;
	private JLabel lblRegistrar;
	private JLabel lbVacuna;
	private JPanel panelTablaConsulta;
	private JPanel panelTablaEnfermedad;
	private JPanel panelTablaVacuna;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualHistorial dialog = new VisualHistorial();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualHistorial() 
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
		contentPanel.setSize(new Dimension((int)(1381*widthRatio),(int)(900*heightRatio)));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(null);
		contentPanel.setLayout(null);
		
		panelTablaVacuna = new JPanel();
		panelTablaVacuna.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaVacuna.setBackground(new Color(255, 255, 255));
		panelTablaVacuna.setBounds((int)(927*widthRatio),(int)(13*heightRatio), (int)(438*widthRatio),(int)(515*heightRatio));
		contentPanel.add(panelTablaVacuna);
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
						
						//lblModificarVacuna.setEnabled(true);
						//lblEliminarVacuna.setEnabled(true);
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
			
			RoundedGlowPanel panel11 = new RoundedGlowPanel();
			panel_1.setBounds(108, 462, 145, 59);
			

		}
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kGradientFocus = -100;
		gradientPanel.setkGradientFocus(-10);
		gradientPanel.kEndColor = new Color(102, 204, 255);
		gradientPanel.setkStartColor(new Color(51, 255, 204));
		gradientPanel.setBounds((int)(0*widthRatio),(int)(780*heightRatio), (int)(1381*widthRatio),(int)(120*heightRatio));
		contentPanel.add(gradientPanel);
		gradientPanel.setLayout(null);
		
		lbVacuna = new JLabel(" Vacunas");
		lbVacuna.setBounds((int)(1067*widthRatio),(int)(541*heightRatio), (int)(188*widthRatio),(int)(46*heightRatio));
		contentPanel.add(lbVacuna);
		lbVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lbVacuna.setForeground(new Color(0, 0, 0));
		lbVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
		lbVacuna.setBackground(new Color(240, 240, 240));
		
		panelTablaConsulta = new JPanel();
		panelTablaConsulta.setLayout(null);
		panelTablaConsulta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaConsulta.setBackground(Color.WHITE);
		panelTablaConsulta.setBounds((int)(472*widthRatio),(int)(13*heightRatio), (int)(438*widthRatio),(int)(515*heightRatio));
		contentPanel.add(panelTablaConsulta);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(0, 0, 0, 0);
		panelTablaConsulta.add(scrollPane_1);
		
		panelTablaEnfermedad = new JPanel();
		panelTablaEnfermedad.setLayout(null);
		panelTablaEnfermedad.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTablaEnfermedad.setBackground(Color.WHITE);
		panelTablaEnfermedad.setBounds((int)(16*widthRatio),(int)(13*heightRatio), (int)(438*widthRatio),(int)(515*heightRatio));
		contentPanel.add(panelTablaEnfermedad);
		
		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setBounds(0, 0, 0, 0);
		panelTablaEnfermedad.add(scrollPane_2);
		
		JLabel lblConsulta = new JLabel("Consultas");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setForeground(Color.BLACK);
		lblConsulta.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
		lblConsulta.setBackground(SystemColor.menu);
		lblConsulta.setBounds((int)(592*widthRatio),(int)(541*heightRatio), (int)(188*widthRatio),(int)(46*heightRatio));
		contentPanel.add(lblConsulta);
		
		JLabel lblEnfermedad = new JLabel("Enfermedades");
		lblEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnfermedad.setForeground(Color.BLACK);
		lblEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(30*widthRatio)));
		lblEnfermedad.setBackground(SystemColor.menu);
		lblEnfermedad.setBounds((int)(125*widthRatio),(int)(541*heightRatio), (int)(207*widthRatio),(int)(46*heightRatio));
		contentPanel.add(lblEnfermedad);
		
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
		
	}
}