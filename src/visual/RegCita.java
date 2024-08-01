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
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.components.JSpinField;

import exception.ValidarCampo;

import javax.swing.JProgressBar;
import com.jgoodies.common.format.EmptyNumberFormat;
import java.text.NumberFormat;
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.ImageIcon;

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private Date fechaNacimiento, fechaCita;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JDateChooser dateChooserNacim;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private Paciente paciente = null;
	private char sexoPersona;
	public static String codePacienteRegistrado = null;
	private JButton btnAgendar;
	private JButton cancelButton;
	private ArrayList<String> horasParaCitas = new ArrayList<String>();
	private ArrayList<String> horasDisponibles = new ArrayList<String>();
	private Medico medico = null;
	private JComboBox cbxHoraCita;
	private JTextField txtNumCita;
	private JDateChooser dateChooserCita;
	private JButton btnAsignarMedico;
	
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
			RegCita dialog = new RegCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCita(Paciente pacienteAModificar) {
	
		horasParaCitas.add("7:00 AM");
		horasParaCitas.add("8:00 AM");
		horasParaCitas.add("9:00 AM");
		horasParaCitas.add("10:00 AM");
		horasParaCitas.add("11:00 AM");
		horasParaCitas.add("12:00 PM");
		horasParaCitas.add("1:00 PM");
		horasParaCitas.add("2:00 PM");
		horasParaCitas.add("3:00 PM");
		horasParaCitas.add("4:00 PM");
		horasParaCitas.add("5:00 PM");
		
		paciente = pacienteAModificar;
		
		setTitle("Agendar Cita");
		
		if (paciente != null) {
			
			setTitle("Modificar Paciente");
		}
		
		Date fecha1 = new Date();
		Date fecha2 = new Date();
		
		
		
		setResizable(false);
		setBounds(100, 100, 604, 353);
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
			panelContenedor1.setBounds(0, 11, 598, 116);
			contentPanel.add(panelContenedor1);
			panelContenedor1.setLayout(null);
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
			
			txtNombre = new JTextField();
			txtNombre.setBorder(null);
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
			lblCédula.setBounds(23, 21, 72, 22);
			panelContenedor1.add(lblCédula);
			
			txtCedula = new JTextField();
			txtCedula.setBorder(null);
			txtCedula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCedula.setColumns(10);
			txtCedula.setBounds(105, 21, 176, 22);
			panelContenedor1.add(txtCedula);
			
			JLabel lblFechaNacim = new JLabel("F. de nac:");
			lblFechaNacim.setOpaque(true);
			lblFechaNacim.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaNacim.setForeground(Color.BLACK);
			lblFechaNacim.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblFechaNacim.setBackground(Color.WHITE);
			lblFechaNacim.setBounds(309, 21, 72, 22);
			panelContenedor1.add(lblFechaNacim);
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds(391, 21, 176, 22);
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
			lblTelefono.setBounds(309, 54, 72, 22);
			panelContenedor1.add(lblTelefono);
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(Color.BLACK);
			lblTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTelefono.setBackground(Color.WHITE);
			
			txtTelefono = new JTextField();
			txtTelefono.setBorder(null);
			txtTelefono.setBounds(391, 54, 176, 22);
			panelContenedor1.add(txtTelefono);
			txtTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtTelefono.setColumns(10);
		}
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVerde.setBackground(new Color(173, 216, 230));
		panelVerde.setBounds(0, 21, 598, 112);
		contentPanel.add(panelVerde);
		
		JPanel panelGris = new JPanel();
		panelGris.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGris.setBackground(new Color(105, 116, 124, 120));
		panelGris.setBounds(0, 145, 341, 112);
		contentPanel.add(panelGris);
		panelGris.setLayout(null);
		
		JLabel lblNumCita = new JLabel("No. Cita:");
		lblNumCita.setOpaque(true);
		lblNumCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCita.setForeground(Color.BLACK);
		lblNumCita.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblNumCita.setBackground(Color.WHITE);
		lblNumCita.setBounds(23, 11, 72, 22);
		panelGris.add(lblNumCita);
		
		txtNumCita = new JTextField();
		txtNumCita.setText(String.valueOf(Clinica.getInstance().getGeneradorNumCita()));
		txtNumCita.setBorder(null);
		txtNumCita.setEditable(false);
		txtNumCita.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtNumCita.setColumns(10);
		txtNumCita.setBounds(105, 11, 90, 22);
		panelGris.add(txtNumCita);
		
		dateChooserCita = new JDateChooser();
		dateChooserCita.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				medico = null;
				cbxHoraCita.setModel(new DefaultComboBoxModel<>());
			}
		});
		dateChooserCita.setBorder(null);
		dateChooserCita.setBounds(105, 44, 90, 22);
		panelGris.add(dateChooserCita);
		
		JLabel lblFechaCita = new JLabel("Fecha:");
		lblFechaCita.setOpaque(true);
		lblFechaCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCita.setForeground(Color.BLACK);
		lblFechaCita.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblFechaCita.setBackground(Color.WHITE);
		lblFechaCita.setBounds(23, 44, 72, 22);
		panelGris.add(lblFechaCita);
		
		JLabel lblHoraCita = new JLabel("Hora:");
		lblHoraCita.setOpaque(true);
		lblHoraCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraCita.setForeground(Color.BLACK);
		lblHoraCita.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblHoraCita.setBackground(Color.WHITE);
		lblHoraCita.setBounds(23, 75, 72, 22);
		panelGris.add(lblHoraCita);
		
		cbxHoraCita = new JComboBox();
		cbxHoraCita.setEnabled(false);
		cbxHoraCita.setBorder(null);
		cbxHoraCita.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		cbxHoraCita.setBounds(105, 75, 90, 22);
		panelGris.add(cbxHoraCita);
		
		btnAsignarMedico = new JButton("Elegir M\u00E9dico");
		btnAsignarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (dateChooserCita.getDate() != null) {
					
					MostrarMedico elegirMedicoCita = new MostrarMedico(null, false, true);
					elegirMedicoCita.setModal(true);
					elegirMedicoCita.setVisible(true);
					medico = elegirMedicoCita.getMedicoParaCita();
					
					if (medico != null) {
						
						cargarEnCbxFromArrayList();
						cbxHoraCita.setEnabled(true);
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Favor elegir la fecha de la cita", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnAsignarMedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnAsignarMedico.setBounds(205, 75, 115, 23);
		panelGris.add(btnAsignarMedico);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegCita.class.getResource("/Imagenes/registrar_cita_img.png")));
		lblNewLabel.setBounds(422, 157, 100, 100);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgendar = new JButton("Agendar");
				btnAgendar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if (rdbtnMasculino.isSelected()) {
								
								sexoPersona = 'M';
							}
							else {
								
								sexoPersona = 'F';
							}
							
							nombre = txtNombre.getText();
							cedula = txtCedula.getText();
							telefono = txtTelefono.getText();
							fechaNacimiento = dateChooserNacim.getDate();
							fechaCita = dateChooserCita.getDate();
							
							
							if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()) {
								throw new ValidarCampo("Debe llenar los campos obligatorios.");
							}
							
							if (fechaNacimiento == null) {
								throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
							}
							
							if (fechaCita == null) {
								throw new ValidarCampo("Debe asignar la fecha de la cita.");
							}
							
							if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
								throw new ValidarCampo("Debe seleccionar un sexo.");
							}
							
							if (medico == null) {
								throw new ValidarCampo("No ha seleccionado el médico.");
							}
							
							Persona personaCita = new Persona(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
									                          sexoPersona, txtTelefono.getText(), null);
							
							Cita nuevaCita = new Cita(txtNumCita.getText(), personaCita, medico.getCodeMedico(), dateChooserCita.getDate(),
									                  cbxHoraCita.getSelectedItem().toString());
							
							Clinica.getInstance().insertarCita(nuevaCita);
							cbxHoraCita.setEnabled(false);
							cbxHoraCita.setModel(new DefaultComboBoxModel<>());
							JOptionPane.showMessageDialog(null, "Cita agendada con éxito", "Agendar Cita", JOptionPane.INFORMATION_MESSAGE);
							clean();
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtNombre.grabFocus();
						}
					}
				});
				btnAgendar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnAgendar.setActionCommand("OK");
				buttonPane.add(btnAgendar);
				getRootPane().setDefaultButton(btnAgendar);
			}
			{
				cancelButton = new JButton("Cancelar");
				
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
		
	}
	
	
	private void clean() {
		
		txtNombre.setText("");
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
		txtCedula.setText("");
		dateChooserNacim.setCalendar(null);
		txtTelefono.setText("");
		txtNumCita.setText(String.valueOf(Clinica.getInstance().getGeneradorNumCita()));
		dateChooserCita.setDate(null);
	}
	
	public void horariosDisponiblesMedico() {
		
		if (horasDisponibles.size() > 0) {
			
			horasDisponibles.clear();
		}
		
		horasDisponibles.addAll(horasParaCitas);
		
		for (Cita cita : Clinica.getInstance().citasPendientesByCodeMedico(medico.getCodeMedico())) {
			
			if (fechasIguales(cita.getFechaDeCita(), dateChooserCita.getDate())) {
				
				if (cita.isPendiente()) {
					
					if (horasDisponibles.contains(cita.getHoraCita())) {
						
						horasDisponibles.remove(cita.getHoraCita());
					}
				}
			}
		}
	}
	
	public void cargarEnCbxFromArrayList() {
		
		horariosDisponiblesMedico();
		int sizeHorasDisponibles = horasDisponibles.size();
		String[] itemsHorasDisponibles = new String[sizeHorasDisponibles];
		
		for (int index = 0; index < sizeHorasDisponibles; index++) {
			
			itemsHorasDisponibles[index] = horasDisponibles.get(index);
		}
				
		cbxHoraCita.setModel(new DefaultComboBoxModel(itemsHorasDisponibles));
	}
	
	public boolean fechasIguales(Date fecha1, Date fecha2) {
		
		Calendar calendarFecha1 = Calendar.getInstance();
		Calendar calendarFecha2 = Calendar.getInstance();
		calendarFecha1.setTime(fecha1);
		calendarFecha2.setTime(fecha2);
		boolean sonIguales = false;
		
		if (calendarFecha1.get(Calendar.YEAR) == calendarFecha2.get(Calendar.YEAR) &&
			calendarFecha1.get(Calendar.MONTH) == calendarFecha2.get(Calendar.MONTH) &&
			calendarFecha1.get(Calendar.DAY_OF_MONTH) == calendarFecha2.get(Calendar.DAY_OF_MONTH) ) {
			
			sonIguales = true;
		}
		
		return sonIguales;
	}
	
}
