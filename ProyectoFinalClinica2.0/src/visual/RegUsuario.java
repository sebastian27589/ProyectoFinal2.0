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
import logico.Medico;
import logico.Paciente;
import logico.Usuario;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodePaciente;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JDateChooser dateChooserNacim;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private Usuario usuario = null;
	private Medico medico = null;
	private char sexoUsuario;
	public static String codePacienteRegistrado = null;
	private JButton btnSiguiente;
	private JButton cancelButton;
	private JTextField txtUsuario;
	
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
			RegUsuario dialog = new RegUsuario(null, null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUsuario(Usuario usuarioAModificar, Medico medicoACrearUsuario, boolean visualizar) {
		
		usuario = usuarioAModificar;
		medico = medicoACrearUsuario;
		
		setTitle("Registrar Usuario");
		
		if (usuario != null) {
			
			if (visualizar) {
				
				setTitle("Datos del Usuario");
			}
			else {
				
				setTitle("Modificar Usuario");
			}
			
		}
		
		setResizable(false);
		setBounds(100, 100, 606, 395);
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
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(31, 11, 72, 22);
		panelGris.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setOpaque(true);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblContrasena.setBackground(Color.WHITE);
		lblContrasena.setBounds(105, 49, 89, 22);
		panelGris.add(lblContrasena);
		
		JLabel lblConfirmContra = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmContra.setOpaque(true);
		lblConfirmContra.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmContra.setForeground(Color.BLACK);
		lblConfirmContra.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblConfirmContra.setBackground(Color.WHITE);
		lblConfirmContra.setBounds(105, 82, 153, 22);
		panelGris.add(lblConfirmContra);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(31, 29, 176, 22);
		panelGris.add(txtUsuario);
		
		JLabel lblNewLabel = new JLabel("Hacer espacio para \u00EDcono o imagen de fondo");
		lblNewLabel.setBounds(153, 301, 323, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSiguiente = new JButton("Registrar");
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (usuario == null) {
							
							if (medico == null) {
								
								if (rdbtnMasculino.isSelected()) {
									
									sexoUsuario = 'M';
								}
								else {
									
									sexoUsuario = 'F';
								}
								
								//Usuario nuevoUsuario = new Usuario(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
										// sexoUsuario, txtTelefono.getText(), "Secretario", );
								
							}
							
							
							
							
							
						}
						else {
					
							if (rdbtnMasculino.isSelected()) {
								
								sexoUsuario = 'M';
							}
							else {
								
								sexoUsuario = 'F';
							}
							
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
	

	

	
	public String getCodePacienteRegistrado() {
		
		return codePacienteRegistrado;
	}
}
