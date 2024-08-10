package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import exception.ValidarCampo;
import logico.Clinica;
import logico.Paciente;
import logico.RoundedPanel;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class VisualPaciente extends JPanel {

	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private float peso, altura;
	private Date fechaNacimiento;
	private JTextField txtCodePaciente;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JDateChooser dateChooserNacim;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JTextArea txtareaDireccion;
	private VisualPaciente paciente = null;
	private char sexoPaciente;
	private JTextField txtAltura;
	private JTextField txtPeso;
	private JComboBox cbxTipoSangre;
	private JTextArea txtareaAlergias;
	private JTextArea txtareaInfoRelevante;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JPanel panelDatosPersona;
	private RoundedPanel panelContenerdor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			/* Paciente para probar MODIFICAR
			Paciente pacienteDePrueba = new Paciente("00101009371", "Julito Pere", new Date(), 'M', "8099098989", "Juan Pablo Duarte", "P---");
			Vacuna vac2 = new Vacuna("001", "19-Vaccine", "Pfizer");
			pacienteDePrueba.getMisVacunas().add(vac2);
			*/
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
	public VisualPaciente(VisualPaciente pacienteAModificar, boolean regUnSoloPaciente, boolean visualizar) 
	{
		
		paciente = pacienteAModificar;
	
		setBounds(100, 100, 1444, 993);
		contentPanel.setBackground(new Color(218, 221, 216));
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.setLayout(null);
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVerde.setBackground(new Color(107, 170, 117, 60));
		panelVerde.setBounds(814, 13, 257, 414);
		contentPanel.add(panelVerde);
		
		panelContenerdor = new RoundedPanel();
		panelContenerdor.setRoundTopRight(100);
		panelContenerdor.setRoundTopLeft(100);
		panelContenerdor.setRoundBottomRight(100);
		panelContenerdor.setRoundBottomLeft(100);
		panelContenerdor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContenerdor.setBackground(new Color(240, 240, 240));
		panelContenerdor.setBounds(22, 764, 520, 159);
		contentPanel.add(panelContenerdor);
		panelContenerdor.setLayout(null);
		
		txtareaDireccion = new JTextArea();
		txtareaDireccion.setBounds(474, 34, 285, 54);
		panelContenerdor.add(txtareaDireccion);
		txtareaDireccion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaDireccion.setLineWrap(true);
		txtareaDireccion.setWrapStyleWord(true);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(391, 49, 73, 22);
		panelContenerdor.add(lblDireccion);
		lblDireccion.setOpaque(true);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblDireccion.setBackground(Color.WHITE);
		
		JLabel lblAlergias = new JLabel("Alergias:");
		lblAlergias.setOpaque(true);
		lblAlergias.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlergias.setForeground(Color.BLACK);
		lblAlergias.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblAlergias.setBackground(Color.WHITE);
		lblAlergias.setBounds(23, 25, 73, 22);
		panelContenerdor.add(lblAlergias);
		
		txtareaAlergias = new JTextArea();
		txtareaAlergias.setWrapStyleWord(true);
		txtareaAlergias.setLineWrap(true);
		txtareaAlergias.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaAlergias.setBounds(105, 17, 276, 38);
		panelContenerdor.add(txtareaAlergias);
		
		JLabel lblInfoRelevante = new JLabel("Importante:");
		lblInfoRelevante.setOpaque(true);
		lblInfoRelevante.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoRelevante.setForeground(Color.BLACK);
		lblInfoRelevante.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblInfoRelevante.setBackground(Color.WHITE);
		lblInfoRelevante.setBounds(23, 73, 73, 22);
		panelContenerdor.add(lblInfoRelevante);
		
		txtareaInfoRelevante = new JTextArea();
		txtareaInfoRelevante.setWrapStyleWord(true);
		txtareaInfoRelevante.setLineWrap(true);
		txtareaInfoRelevante.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaInfoRelevante.setBounds(105, 66, 276, 38);
		panelContenerdor.add(txtareaInfoRelevante);
		{
			RoundedPanel panelDatosPersona = new RoundedPanel();
			panelDatosPersona.setRoundTopRight(35);
			panelDatosPersona.setRoundTopLeft(35);
			panelDatosPersona.setRoundBottomRight(35);
			panelDatosPersona.setRoundBottomLeft(35);
			panelDatosPersona.setBounds(25, 23, 777, 711);
			contentPanel.add(panelDatosPersona);
			panelDatosPersona.setBackground(new Color(240, 240, 240));
			panelDatosPersona.setLayout(null);
			
			JLabel lblCodePaciente = new JLabel("C\u00F3digo:");
			lblCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCodePaciente.setOpaque(true);
			lblCodePaciente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodePaciente.setForeground(new Color(0, 0, 0));
			lblCodePaciente.setBackground(new Color(255, 255, 255));
			lblCodePaciente.setBounds(33, 32, 72, 22);
			panelDatosPersona.add(lblCodePaciente);
			
			txtCodePaciente = new JTextField();
			txtCodePaciente.setEditable(false);
			txtCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCodePaciente.setBounds(117, 32, 62, 22);
			panelDatosPersona.add(txtCodePaciente);
			txtCodePaciente.setColumns(10);
			txtCodePaciente.setText("P-"+Clinica.getInstance().getGeneradorCodePaciente());
			
			JLabel lblCédula = new JLabel("C\u00E9dula:");
			lblCédula.setOpaque(true);
			lblCédula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCédula.setForeground(Color.BLACK);
			lblCédula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCédula.setBackground(Color.WHITE);
			lblCédula.setBounds(307, 246, 72, 22);
			panelDatosPersona.add(lblCédula);
			
			txtCedula = new JTextField();
			txtCedula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCedula.setColumns(10);
			txtCedula.setBounds(389, 246, 176, 22);
			panelDatosPersona.add(txtCedula);
			
			JLabel lblFechaNacim = new JLabel("F. de nac:");
			lblFechaNacim.setOpaque(true);
			lblFechaNacim.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaNacim.setForeground(Color.BLACK);
			lblFechaNacim.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblFechaNacim.setBackground(Color.WHITE);
			lblFechaNacim.setBounds(307, 279, 72, 22);
			panelDatosPersona.add(lblFechaNacim);
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds(389, 279, 176, 22);
			panelDatosPersona.add(dateChooserNacim);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(23, 279, 72, 22);
			panelDatosPersona.add(lblSexo);
			lblSexo.setOpaque(true);
			lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSexo.setForeground(Color.BLACK);
			lblSexo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblSexo.setBackground(Color.WHITE);
			
			rdbtnMasculino = new JRadioButton("M");
			rdbtnMasculino.setSelected(true);
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculino.setBounds(105, 279, 38, 22);
			panelDatosPersona.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			rdbtnFemenino = new JRadioButton("F");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
				}
			});
			rdbtnFemenino.setBounds(160, 279, 33, 22);
			panelDatosPersona.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(307, 312, 72, 22);
			panelDatosPersona.add(lblTelefono);
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(Color.BLACK);
			lblTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTelefono.setBackground(Color.WHITE);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(389, 312, 176, 22);
			panelDatosPersona.add(txtTelefono);
			txtTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtTelefono.setColumns(10);
			
			JLabel lblTipoSangre = new JLabel("T. Sangre:");
			lblTipoSangre.setOpaque(true);
			lblTipoSangre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTipoSangre.setForeground(Color.BLACK);
			lblTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTipoSangre.setBackground(Color.WHITE);
			lblTipoSangre.setBounds(23, 409, 72, 22);
			panelDatosPersona.add(lblTipoSangre);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"<Elegir>", "A+", "A", "B+", "B", "AB+", "AB", "O+", "O"}));
			cbxTipoSangre.setSelectedIndex(0);
			cbxTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			cbxTipoSangre.setBounds(110, 409, 77, 22);
			panelDatosPersona.add(cbxTipoSangre);
			
			JLabel lblAltura = new JLabel("Altura:");
			lblAltura.setOpaque(true);
			lblAltura.setHorizontalAlignment(SwingConstants.CENTER);
			lblAltura.setForeground(Color.BLACK);
			lblAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblAltura.setBackground(Color.WHITE);
			lblAltura.setBounds(23, 442, 72, 22);
			panelDatosPersona.add(lblAltura);
			
			JLabel lblPeso = new JLabel("Peso:");
			lblPeso.setOpaque(true);
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setForeground(Color.BLACK);
			lblPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblPeso.setBackground(Color.WHITE);
			lblPeso.setBounds(23, 475, 72, 22);
			panelDatosPersona.add(lblPeso);
			
			txtAltura = new JTextField();
			txtAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtAltura.setColumns(10);
			txtAltura.setBounds(110, 442, 77, 22);
			panelDatosPersona.add(txtAltura);
			
			txtPeso = new JTextField();
			txtPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtPeso.setColumns(10);
			txtPeso.setBounds(110, 475, 77, 22);
			panelDatosPersona.add(txtPeso);
			
			RoundedPanel roundedPanel = new RoundedPanel();
			roundedPanel.setLayout(null);
			roundedPanel.setRoundTopRight(18);
			roundedPanel.setRoundTopLeft(18);
			roundedPanel.setRoundBottomRight(18);
			roundedPanel.setRoundBottomLeft(18);
			roundedPanel.setBackground(SystemColor.window);
			roundedPanel.setBounds(117, 86, 190, 46);
			panelDatosPersona.add(roundedPanel);
			
			txtNombre = new JTextField();
			txtNombre.setBorder(null);
			txtNombre.setBounds(12, 0, 166, 46);
			roundedPanel.add(txtNombre);
			txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtNombre.setColumns(10);
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(33, 192, 72, 22);
				panelDatosPersona.add(lblNombre);
				lblNombre.setOpaque(true);
				lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombre.setForeground(Color.BLACK);
				lblNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				lblNombre.setBackground(Color.WHITE);
			}
		}
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			{
//				btnSiguiente = new JButton("Siguiente");
//				btnSiguiente.setBackground(Color.WHITE);
//				btnSiguiente.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						
//						try {
//							if (paciente == null) {
//								
//								if (rdbtnMasculino.isSelected()) {
//									
//									sexoPaciente = 'M';
//								}
//								else {
//									
//									sexoPaciente = 'F';
//								}
//								
//								nombre = txtNombre.getText();
//								cedula = txtCedula.getText();
//								telefono = txtTelefono.getText();
//								fechaNacimiento = dateChooserNacim.getDate();
//								peso = Float.parseFloat(txtPeso.getText());
//								altura = Float.parseFloat(txtAltura.getText());
//								
//								if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()) {
//									throw new ValidarCampo("Debe llenar los campos obligatorios.");
//								}
//								
//								if (fechaNacimiento == null) {
//									throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
//								}
//								
//								if (cbxTipoSangre.getSelectedIndex() == 0) {
//									throw new ValidarCampo("No ha seleccionado un tipo de sangre.");
//								}
//								
//								if (peso <= 0 || altura <= 0) {
//									throw new ValidarCampo("Las entradas del peso o la altura no pueden ser negativas.");
//								} 
//								
//								if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
//									throw new ValidarCampo("Debe seleccionar un sexo.");
//								}
//								
//								VisualPaciente nuevoPaciente = new VisualPaciente(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
//										 sexoPaciente, txtTelefono.getText(), txtareaDireccion.getText(), txtCodePaciente.getText(),
//										 cbxTipoSangre.getSelectedItem().toString(), new Float(txtAltura.getText()), new Float(txtPeso.getText()),
//										 txtareaAlergias.getText(), txtareaInfoRelevante.getText());
//								
//								codePacienteRegistrado = nuevoPaciente.getCodePaciente();
//								ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(null);
//								elegirVacunas.setModal(true);
//								elegirVacunas.setVisible(true);
//								nuevoPaciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());
//								Clinica.getInstance().insertarPaciente(nuevoPaciente);
//								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Paciente", JOptionPane.INFORMATION_MESSAGE);
//								
//								if (regUnSoloPaciente) {
//									
//									dispose();
//								}
//								else {
//									clean();
//								}
//								
//							}
//							else {
//						
//								if (rdbtnMasculino.isSelected()) {
//									
//									sexoPaciente = 'M';
//								}
//								else {
//									
//									sexoPaciente = 'F';
//								}
//								
//								paciente.setTipoDeSangre(cbxTipoSangre.getSelectedItem().toString());
//								paciente.setAltura(new Float(txtAltura.getText()));
//								paciente.setPeso(new Float(txtPeso.getText()));
//								paciente.setTelefono(txtTelefono.getText());
//								paciente.setDireccion(txtareaDireccion.getText());
//								paciente.setAlergias(txtareaAlergias.getText());
//								paciente.setInfoImportante(txtareaInfoRelevante.getText());
//								
//								ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(paciente);
//								elegirVacunas.setModal(true);
//								elegirVacunas.setVisible(true);
//								paciente.getMisVacunas().clear();
//								paciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());							
//								Clinica.getInstance().actualizarPaciente(paciente);
//								dispose();
//							}
//						} catch (ValidarCampo e2) {
//							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//							e2.printStackTrace();
//							txtNombre.grabFocus();
//						}
//						catch (NumberFormatException e3) {
//							JOptionPane.showMessageDialog(null, "Ingrese datos válidos para la altura y el peso.", "Error", JOptionPane.ERROR_MESSAGE);
//							txtPeso.grabFocus();
//						}
//						
//					}
//				});
//				btnSiguiente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
//				btnSiguiente.setActionCommand("OK");
//				buttonPane.add(btnSiguiente);
//				getRootPane().setDefaultButton(btnSiguiente);
//			}
//			{
//				cancelButton = new JButton("Cancelar");
//				cancelButton.setBackground(Color.WHITE);
//				
//				if (regUnSoloPaciente) {
//					
//					setDefaultCloseOperation(0);
//					cancelButton.setEnabled(false);
//				}
//				
//				cancelButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						dispose();
//					}
//				});
//				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
		
//		if (paciente != null) {
//			
//			txtNombre.setEditable(false);
//			rdbtnMasculino.setEnabled(false);
//			rdbtnFemenino.setEnabled(false);
//			txtCedula.setEditable(false);
//			dateChooserNacim.setEnabled(false);
//			
//			if (visualizar) {
//				
//				setTitle("Datos del Paciente");
//				
//				txtTelefono.setEditable(false);
//				cbxTipoSangre.setEnabled(false);
//				txtAltura.setEditable(false);
//				txtPeso.setEditable(false);
//				txtareaAlergias.setEditable(false);
//				txtareaInfoRelevante.setEditable(false);
//				txtareaDireccion.setEditable(false);
//			}
//			
//		}
//		
//		loadPaciente();
//		
//		if (visualizar) {
//			
//			btnSiguiente.setVisible(false);
//			cancelButton.setText("Cerrar");
//		}
		
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
//	}
	
	private void clean() {
		
		txtCodePaciente.setText("P-"+Clinica.getInstance().getGeneradorCodePaciente());
		txtNombre.setText("");
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
		txtCedula.setText("");
		dateChooserNacim.setCalendar(null);
		cbxTipoSangre.setSelectedIndex(0);
		txtTelefono.setText("");
		txtAltura.setText("");
		txtPeso.setText("");
		txtareaAlergias.setText("");
		txtareaInfoRelevante.setText("");
		txtareaDireccion.setText("");
	}
	
	public String getCodePacienteRegistrado() {
		
		return codePacienteRegistrado;
	}
}
