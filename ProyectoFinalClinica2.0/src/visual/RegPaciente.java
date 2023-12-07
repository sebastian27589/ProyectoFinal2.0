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
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegPaciente extends JDialog {

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
	private Paciente paciente = null;
	private char sexoPaciente;
	private JTextField txtAltura;
	private JTextField txtPeso;
	private JComboBox cbxTipoSangre;
	private JTextArea txtareaAlergias;
	private JTextArea txtareaInfoRelevante;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	
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
	public RegPaciente(Paciente pacienteAModificar, boolean regUnSoloPaciente, boolean visualizar) {
		
		paciente = pacienteAModificar;
		
		setTitle("Registrar Paciente");
		
		if (paciente != null) {
			
			setTitle("Modificar Paciente");
		}
		
		setResizable(false);
		setBounds(100, 100, 793, 395);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(218, 221, 216));
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelContenedor1 = new JPanel();
			panelContenedor1.setOpaque(false);
			panelContenedor1.setBackground(new Color(218, 221, 216));
			panelContenedor1.setBounds(0, 11, 787, 116);
			contentPanel.add(panelContenedor1);
			panelContenedor1.setLayout(null);
			
			JLabel lblCodePaciente = new JLabel("C\u00F3digo:");
			lblCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCodePaciente.setOpaque(true);
			lblCodePaciente.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodePaciente.setForeground(new Color(0, 0, 0));
			lblCodePaciente.setBackground(new Color(255, 255, 255));
			lblCodePaciente.setBounds(23, 21, 72, 22);
			panelContenedor1.add(lblCodePaciente);
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setOpaque(true);
				lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombre.setForeground(Color.BLACK);
				lblNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				lblNombre.setBackground(Color.WHITE);
				lblNombre.setBounds(23, 54, 72, 22);
				panelContenedor1.add(lblNombre);
			}
			
			txtCodePaciente = new JTextField();
			txtCodePaciente.setEditable(false);
			txtCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCodePaciente.setBounds(105, 21, 62, 22);
			panelContenedor1.add(txtCodePaciente);
			txtCodePaciente.setColumns(10);
			txtCodePaciente.setText("P-"+Clinica.getInstance().getGeneradorCodePaciente());
			
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtNombre.setColumns(10);
			txtNombre.setBounds(105, 54, 176, 22);
			panelContenedor1.add(txtNombre);
			
			JLabel lblCédula = new JLabel("C\u00E9dula:");
			lblCédula.setOpaque(true);
			lblCédula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCédula.setForeground(Color.BLACK);
			lblCédula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCédula.setBackground(Color.WHITE);
			lblCédula.setBounds(309, 21, 72, 22);
			panelContenedor1.add(lblCédula);
			
			txtCedula = new JTextField();
			txtCedula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCedula.setColumns(10);
			txtCedula.setBounds(391, 21, 176, 22);
			panelContenedor1.add(txtCedula);
			
			JLabel lblFechaNacim = new JLabel("F. de nac:");
			lblFechaNacim.setOpaque(true);
			lblFechaNacim.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaNacim.setForeground(Color.BLACK);
			lblFechaNacim.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblFechaNacim.setBackground(Color.WHITE);
			lblFechaNacim.setBounds(309, 54, 72, 22);
			panelContenedor1.add(lblFechaNacim);
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds(391, 54, 176, 22);
			panelContenedor1.add(dateChooserNacim);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(23, 87, 72, 22);
			panelContenedor1.add(lblSexo);
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
			rdbtnMasculino.setBounds(105, 87, 38, 22);
			panelContenedor1.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			rdbtnFemenino = new JRadioButton("F");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
				}
			});
			rdbtnFemenino.setBounds(160, 87, 33, 22);
			panelContenedor1.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(309, 87, 72, 22);
			panelContenedor1.add(lblTelefono);
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(Color.BLACK);
			lblTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTelefono.setBackground(Color.WHITE);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(391, 87, 176, 22);
			panelContenedor1.add(txtTelefono);
			txtTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtTelefono.setColumns(10);
			
			JLabel lblTipoSangre = new JLabel("T. Sangre:");
			lblTipoSangre.setOpaque(true);
			lblTipoSangre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTipoSangre.setForeground(Color.BLACK);
			lblTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTipoSangre.setBackground(Color.WHITE);
			lblTipoSangre.setBounds(595, 21, 72, 22);
			panelContenedor1.add(lblTipoSangre);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"<Elegir>", "A+", "A", "B+", "B", "AB+", "AB", "O+", "O"}));
			cbxTipoSangre.setSelectedIndex(0);
			cbxTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			cbxTipoSangre.setBounds(682, 21, 77, 22);
			panelContenedor1.add(cbxTipoSangre);
			
			JLabel lblAltura = new JLabel("Altura:");
			lblAltura.setOpaque(true);
			lblAltura.setHorizontalAlignment(SwingConstants.CENTER);
			lblAltura.setForeground(Color.BLACK);
			lblAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblAltura.setBackground(Color.WHITE);
			lblAltura.setBounds(595, 54, 72, 22);
			panelContenedor1.add(lblAltura);
			
			JLabel lblPeso = new JLabel("Peso:");
			lblPeso.setOpaque(true);
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setForeground(Color.BLACK);
			lblPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblPeso.setBackground(Color.WHITE);
			lblPeso.setBounds(595, 87, 72, 22);
			panelContenedor1.add(lblPeso);
			
			txtAltura = new JTextField();
			txtAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtAltura.setColumns(10);
			txtAltura.setBounds(682, 54, 77, 22);
			panelContenedor1.add(txtAltura);
			
			txtPeso = new JTextField();
			txtPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtPeso.setColumns(10);
			txtPeso.setBounds(682, 87, 77, 22);
			panelContenedor1.add(txtPeso);
		}
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVerde.setBackground(new Color(107, 170, 117, 60));
		panelVerde.setBounds(0, 21, 787, 112);
		contentPanel.add(panelVerde);
		
		JPanel panelGris = new JPanel();
		panelGris.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGris.setBackground(new Color(105, 116, 124, 120));
		panelGris.setBounds(0, 145, 787, 121);
		contentPanel.add(panelGris);
		panelGris.setLayout(null);
		
		txtareaDireccion = new JTextArea();
		txtareaDireccion.setBounds(474, 34, 285, 54);
		panelGris.add(txtareaDireccion);
		txtareaDireccion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaDireccion.setLineWrap(true);
		txtareaDireccion.setWrapStyleWord(true);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(391, 49, 73, 22);
		panelGris.add(lblDireccion);
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
		panelGris.add(lblAlergias);
		
		txtareaAlergias = new JTextArea();
		txtareaAlergias.setWrapStyleWord(true);
		txtareaAlergias.setLineWrap(true);
		txtareaAlergias.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaAlergias.setBounds(105, 17, 276, 38);
		panelGris.add(txtareaAlergias);
		
		JLabel lblInfoRelevante = new JLabel("Importante:");
		lblInfoRelevante.setOpaque(true);
		lblInfoRelevante.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoRelevante.setForeground(Color.BLACK);
		lblInfoRelevante.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblInfoRelevante.setBackground(Color.WHITE);
		lblInfoRelevante.setBounds(23, 73, 73, 22);
		panelGris.add(lblInfoRelevante);
		
		txtareaInfoRelevante = new JTextArea();
		txtareaInfoRelevante.setWrapStyleWord(true);
		txtareaInfoRelevante.setLineWrap(true);
		txtareaInfoRelevante.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaInfoRelevante.setBounds(105, 66, 276, 38);
		panelGris.add(txtareaInfoRelevante);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSiguiente = new JButton("Siguiente");
				btnSiguiente.setBackground(Color.WHITE);
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if (paciente == null) {
								
								if (rdbtnMasculino.isSelected()) {
									
									sexoPaciente = 'M';
								}
								else {
									
									sexoPaciente = 'F';
								}
								
								nombre = txtNombre.getText();
								cedula = txtCedula.getText();
								telefono = txtTelefono.getText();
								fechaNacimiento = dateChooserNacim.getDate();
								peso = Float.parseFloat(txtPeso.getText());
								altura = Float.parseFloat(txtAltura.getText());
								
								if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()) {
									throw new ValidarCampo("Debe llenar los campos obligatorios.");
								}
								
								if (fechaNacimiento == null) {
									throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
								}
								
								if (cbxTipoSangre.getSelectedIndex() == 0) {
									throw new ValidarCampo("No ha seleccionado un tipo de sangre.");
								}
								
								if (peso <= 0 || altura <= 0) {
									throw new ValidarCampo("Las entradas del peso o la altura no pueden ser negativas.");
								} 
								
								if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
									throw new ValidarCampo("Debe seleccionar un sexo.");
								}
								
								Paciente nuevoPaciente = new Paciente(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
										 sexoPaciente, txtTelefono.getText(), txtareaDireccion.getText(), txtCodePaciente.getText(),
										 cbxTipoSangre.getSelectedItem().toString(), new Float(txtAltura.getText()), new Float(txtPeso.getText()),
										 txtareaAlergias.getText(), txtareaInfoRelevante.getText());
								
								codePacienteRegistrado = nuevoPaciente.getCodePaciente();
								ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(null);
								elegirVacunas.setModal(true);
								elegirVacunas.setVisible(true);
								nuevoPaciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());
								Clinica.getInstance().insertarPaciente(nuevoPaciente);
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Paciente", JOptionPane.INFORMATION_MESSAGE);
								
								if (regUnSoloPaciente) {
									
									dispose();
								}
								else {
									clean();
								}
								
							}
							else {
						
								if (rdbtnMasculino.isSelected()) {
									
									sexoPaciente = 'M';
								}
								else {
									
									sexoPaciente = 'F';
								}
								
								paciente.setTipoDeSangre(cbxTipoSangre.getSelectedItem().toString());
								paciente.setAltura(new Float(txtAltura.getText()));
								paciente.setPeso(new Float(txtPeso.getText()));
								paciente.setTelefono(txtTelefono.getText());
								paciente.setDireccion(txtareaDireccion.getText());
								paciente.setAlergias(txtareaAlergias.getText());
								paciente.setInfoImportante(txtareaInfoRelevante.getText());
								
								ElegirVacunaPaciente elegirVacunas = new ElegirVacunaPaciente(paciente);
								elegirVacunas.setModal(true);
								elegirVacunas.setVisible(true);
								paciente.getMisVacunas().clear();
								paciente.getMisVacunas().addAll(elegirVacunas.extraerVacunasElegidas());							
								Clinica.getInstance().actualizarPaciente(paciente);
								dispose();
							}
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtNombre.grabFocus();
						}
						catch (NumberFormatException e3) {
							JOptionPane.showMessageDialog(null, "Ingrese datos válidos para la altura y el peso.", "Error", JOptionPane.ERROR_MESSAGE);
							txtPeso.grabFocus();
						}
						
					}
				});
				btnSiguiente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnSiguiente.setActionCommand("OK");
				buttonPane.add(btnSiguiente);
				getRootPane().setDefaultButton(btnSiguiente);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(Color.WHITE);
				
				if (regUnSoloPaciente) {
					
					setDefaultCloseOperation(0);
					cancelButton.setEnabled(false);
				}
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (paciente != null) {
			
			txtNombre.setEditable(false);
			rdbtnMasculino.setEnabled(false);
			rdbtnFemenino.setEnabled(false);
			txtCedula.setEditable(false);
			dateChooserNacim.setEnabled(false);
			
			if (visualizar) {
				
				setTitle("Datos del Paciente");
				
				txtTelefono.setEditable(false);
				cbxTipoSangre.setEnabled(false);
				txtAltura.setEditable(false);
				txtPeso.setEditable(false);
				txtareaAlergias.setEditable(false);
				txtareaInfoRelevante.setEditable(false);
				txtareaDireccion.setEditable(false);
			}
			
		}
		
		loadPaciente();
		
		if (visualizar) {
			
			btnSiguiente.setVisible(false);
			cancelButton.setText("Cerrar");
		}
		
	}
	
	private void loadPaciente() {
		
		if (paciente != null) {
			
			txtCodePaciente.setText(paciente.getCodePaciente());
			txtNombre.setText(paciente.getNombre());
			if (paciente.getSexo() == 'M') {
				
				rdbtnMasculino.setSelected(true);
			}
			else {
				
				rdbtnFemenino.setSelected(true);
			}
			txtCedula.setText(paciente.getCedula());
			dateChooserNacim.setDate(paciente.getFechaDeNacimiento());
			txtTelefono.setText(paciente.getTelefono());
			cbxTipoSangre.setSelectedItem(paciente.getTipoDeSangre());
			txtAltura.setText(String.valueOf(paciente.getAltura()));
			txtPeso.setText(String.valueOf(paciente.getPeso()));
			txtareaDireccion.setText(paciente.getDireccion());
			txtareaAlergias.setText(paciente.getAlergias());
			txtareaInfoRelevante.setText(paciente.getInfoImportante());
		}
		
	}
	
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
