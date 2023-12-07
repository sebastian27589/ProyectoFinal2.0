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

import logico.Clinica;
import logico.Enfermedad;
import logico.HistorialMedico;
import logico.Paciente;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VerHistorialMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAltura;
	private JTextField txtPeso;
	private JComboBox cbxTipoSangre;
	private JTextArea txtareaAlergias;
	private JTextArea txtareaInfoRelevante;
	private JButton cancelButton;
	private JTextField txtEdad;
	private JButton btnEnfermedades;
	private JButton btnConsultas;
	private JButton btnVacunas;
	private JTextField txtNombrePaciente;
	private HistorialMedico histMed = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerHistorialMedico dialog = new VerHistorialMedico(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerHistorialMedico(String codePaciente) {
		
		boolean existePaciente = false;
		
		if (Clinica.getInstance().buscarPacienteByCode(codePaciente) != null) {
			
			existePaciente = true;
			histMed = Clinica.getInstance().buscarHistMedByCodePaciente(codePaciente);
		}
		
		setTitle("Historial M\u00E9dico");
		setResizable(false);
		setBounds(100, 100, 843, 442);
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
			panelContenedor1.setBounds(0, 62, 411, 116);
			contentPanel.add(panelContenedor1);
			panelContenedor1.setLayout(null);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setOpaque(true);
			lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
			lblEdad.setForeground(Color.BLACK);
			lblEdad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblEdad.setBackground(Color.WHITE);
			lblEdad.setBounds(23, 35, 72, 22);
			panelContenedor1.add(lblEdad);
			
			JLabel lblTipoSangre = new JLabel("T. Sangre:");
			lblTipoSangre.setOpaque(true);
			lblTipoSangre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTipoSangre.setForeground(Color.BLACK);
			lblTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTipoSangre.setBackground(Color.WHITE);
			lblTipoSangre.setBounds(23, 73, 72, 22);
			panelContenedor1.add(lblTipoSangre);
			
			cbxTipoSangre = new JComboBox();
			cbxTipoSangre.setEnabled(false);
			cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"<Elegir>", "A+", "A", "B+", "B", "AB+", "AB", "O+", "O"}));
			cbxTipoSangre.setSelectedIndex(0);
			cbxTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			cbxTipoSangre.setBounds(105, 73, 77, 22);
			panelContenedor1.add(cbxTipoSangre);
			
			JLabel lblAltura = new JLabel("Altura:");
			lblAltura.setOpaque(true);
			lblAltura.setHorizontalAlignment(SwingConstants.CENTER);
			lblAltura.setForeground(Color.BLACK);
			lblAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblAltura.setBackground(Color.WHITE);
			lblAltura.setBounds(218, 35, 72, 22);
			panelContenedor1.add(lblAltura);
			
			JLabel lblPeso = new JLabel("Peso:");
			lblPeso.setOpaque(true);
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setForeground(Color.BLACK);
			lblPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblPeso.setBackground(Color.WHITE);
			lblPeso.setBounds(218, 73, 72, 22);
			panelContenedor1.add(lblPeso);
			
			txtAltura = new JTextField();
			txtAltura.setEditable(false);
			txtAltura.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtAltura.setColumns(10);
			txtAltura.setBounds(305, 35, 77, 22);
			panelContenedor1.add(txtAltura);
			
			txtPeso = new JTextField();
			txtPeso.setEditable(false);
			txtPeso.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtPeso.setColumns(10);
			txtPeso.setBounds(305, 73, 77, 22);
			panelContenedor1.add(txtPeso);
			
			txtEdad = new JTextField();
			txtEdad.setEditable(false);
			txtEdad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtEdad.setColumns(10);
			txtEdad.setBounds(105, 35, 77, 22);
			panelContenedor1.add(txtEdad);
		}
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVerde.setBackground(new Color(173, 216, 230));
		panelVerde.setBounds(0, 72, 411, 112);
		contentPanel.add(panelVerde);
		
		JPanel panelGris = new JPanel();
		panelGris.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGris.setBackground(new Color(105, 116, 124, 120));
		panelGris.setBounds(0, 205, 411, 121);
		contentPanel.add(panelGris);
		panelGris.setLayout(null);
		
		JLabel lblAlergias = new JLabel("Alergias:");
		lblAlergias.setOpaque(true);
		lblAlergias.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlergias.setForeground(Color.BLACK);
		lblAlergias.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblAlergias.setBackground(Color.WHITE);
		lblAlergias.setBounds(23, 25, 73, 22);
		panelGris.add(lblAlergias);
		
		txtareaAlergias = new JTextArea();
		txtareaAlergias.setEditable(false);
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
		txtareaInfoRelevante.setEditable(false);
		txtareaInfoRelevante.setWrapStyleWord(true);
		txtareaInfoRelevante.setLineWrap(true);
		txtareaInfoRelevante.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaInfoRelevante.setBounds(105, 66, 276, 38);
		panelGris.add(txtareaInfoRelevante);
		
		JPanel panelDeBotones = new JPanel();
		panelDeBotones.setBackground(new Color(173, 216, 230));
		panelDeBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeBotones.setBounds(438, 21, 399, 37);
		contentPanel.add(panelDeBotones);
		panelDeBotones.setLayout(null);
		
		btnEnfermedades = new JButton("Enfermedades");
		btnEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarEnfermedad mostrarEnfermedadesPaciente = new MostrarEnfermedad(histMed.getMisEnfermedades());
				mostrarEnfermedadesPaciente.setModal(true);
				mostrarEnfermedadesPaciente.setVisible(true);
			}
		});
		btnEnfermedades.setBounds(9, 7, 117, 23);
		panelDeBotones.add(btnEnfermedades);
		btnEnfermedades.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		
		btnConsultas = new JButton("Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarConsultaPaciente verConsultasPaciente = new MostrarConsultaPaciente(histMed.getPaciente());
				verConsultasPaciente.setModal(true);
				verConsultasPaciente.setVisible(true);
			}
		});
		btnConsultas.setBounds(136, 7, 117, 23);
		panelDeBotones.add(btnConsultas);
		btnConsultas.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		
		btnVacunas = new JButton("Vacunas");
		btnVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarVacuna verVacunasPaciente = new MostrarVacuna(histMed.getPaciente().getMisVacunas());
				verVacunasPaciente.setModal(true);
				verVacunasPaciente.setVisible(true);
			}
		});
		btnVacunas.setBounds(263, 7, 117, 23);
		panelDeBotones.add(btnVacunas);
		btnVacunas.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("IMAGEN PNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(438, 69, 378, 257);
		contentPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 21, 411, 30);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtNombrePaciente = new JTextField();
		txtNombrePaciente.setBorder(null);
		txtNombrePaciente.setEditable(false);
		txtNombrePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtNombrePaciente.setOpaque(false);
		txtNombrePaciente.setBounds(182, 4, 200, 22);
		panel.add(txtNombrePaciente);
		txtNombrePaciente.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del Paciente:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 8, 149, 14);
		panel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cerrar");
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
		
		ArrayList<Enfermedad> enfermedadesAInsertar = new ArrayList<Enfermedad>();
		
		if (existePaciente && histMed == null) {
			
			HistorialMedico nuevoHistorialMedico = new HistorialMedico("HM-" + Clinica.getInstance().getGeneradorCodeHistMed(),
																	   Clinica.getInstance().buscarPacienteByCode(codePaciente));
			
			enfermedadesAInsertar = Clinica.getInstance().enfermedadesDelPaciente(codePaciente);
			
			if (enfermedadesAInsertar.size() > 0) {
				
				nuevoHistorialMedico.getMisEnfermedades().addAll(enfermedadesAInsertar);
			}
			
			histMed = nuevoHistorialMedico;
			Clinica.getInstance().insertarHistorialMedico(nuevoHistorialMedico);

		}
		else if (existePaciente && histMed != null) {
			
			enfermedadesAInsertar = Clinica.getInstance().enfermedadesDelPaciente(histMed.getPaciente().getCodePaciente());
			
			if (enfermedadesAInsertar.size() > 0) {
				
				histMed.getMisEnfermedades().clear();
				histMed.getMisEnfermedades().addAll(enfermedadesAInsertar);
			}
			
		}	
		
		if (existePaciente) {
			
			loadHistorialMedico();
		}
		
	}
	
	private void loadHistorialMedico() {
		
		txtNombrePaciente.setText(histMed.getPaciente().getNombre());
		txtEdad.setText(String.valueOf(Clinica.getInstance().edadByFechaDeNacim(histMed.getPaciente().getFechaDeNacimiento(), new Date())));
		cbxTipoSangre.setSelectedItem(histMed.getPaciente().getTipoDeSangre());
		txtAltura.setText(String.valueOf(histMed.getPaciente().getAltura()));
		txtPeso.setText(String.valueOf(histMed.getPaciente().getPeso()));
		txtareaAlergias.setText(histMed.getPaciente().getAlergias());
		txtareaInfoRelevante.setText(histMed.getPaciente().getInfoImportante());
	}
}
