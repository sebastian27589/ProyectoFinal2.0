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
import logico.Medico;
import logico.Paciente;
import logico.Usuario;
import logico.Vacuna;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import logico.RoundedPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono, user;
	private Date fechaNacimiento;
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
	private JTextField txtContrasena;
	private JPasswordField passwordFieldUsuario;
	private JLabel lblIconVerContra;
	private JLabel lblIconOcultarContra;
	
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
		setBounds(100, 100, 606, 331);
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
			panelContenedor1.setBounds(0, 11, 600, 116);
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
		panelVerde.setBackground(new Color(105, 116, 124, 120));
		panelVerde.setBounds(0, 21, 600, 112);
		contentPanel.add(panelVerde);
		
		JPanel panelGris = new JPanel();
		panelGris.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGris.setBackground(new Color(173, 216, 230));
		panelGris.setBounds(0, 145, 380, 82);
		contentPanel.add(panelGris);
		panelGris.setLayout(null);
		
		lblIconOcultarContra = new JLabel("");
		lblIconOcultarContra.setBorder(null);
		lblIconOcultarContra.setIconTextGap(0);
		lblIconOcultarContra.setVisible(false);
		lblIconOcultarContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblIconOcultarContra.setVisible(false);
				lblIconVerContra.setVisible(true);
				txtContrasena.setVisible(false);
				passwordFieldUsuario.setText(txtContrasena.getText());
				passwordFieldUsuario.setVisible(true);
			}
		});
		lblIconOcultarContra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIconOcultarContra.setIcon(new ImageIcon(RegUsuario.class.getResource("/Imagenes/iconOcultarContrasena.png")));
		lblIconOcultarContra.setBounds(323, 47, 16, 16);
		panelGris.add(lblIconOcultarContra);
		
		lblIconVerContra = new JLabel("");
		lblIconVerContra.setBounds(323, 47, 16, 16);
		panelGris.add(lblIconVerContra);
		lblIconVerContra.setForeground(Color.WHITE);
		lblIconVerContra.setBorder(null);
		lblIconVerContra.setIconTextGap(0);
		lblIconVerContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblIconVerContra.setVisible(false);
				lblIconOcultarContra.setVisible(true);
				passwordFieldUsuario.setVisible(false);
				txtContrasena.setText(String.valueOf(passwordFieldUsuario.getPassword()));
				txtContrasena.setVisible(true);
			}
		});
		lblIconVerContra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIconVerContra.setIcon(new ImageIcon(RegUsuario.class.getResource("/Imagenes/iconVerContrasena.png")));
		
		passwordFieldUsuario = new JPasswordField();
		passwordFieldUsuario.setBorder(null);
		passwordFieldUsuario.setBounds(137, 45, 176, 22);
		panelGris.add(passwordFieldUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setOpaque(true);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(23, 11, 103, 22);
		panelGris.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(null);
		txtUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(137, 11, 176, 22);
		panelGris.add(txtUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setOpaque(true);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblContrasena.setBackground(Color.WHITE);
		lblContrasena.setBounds(23, 45, 103, 22);
		panelGris.add(lblContrasena);
		
		txtContrasena = new JTextField();
		txtContrasena.setVisible(false);
		txtContrasena.setBorder(null);
		txtContrasena.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(137, 45, 176, 22);
		panelGris.add(txtContrasena);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegUsuario.class.getResource("/Imagenes/usuario_registrar_img.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(456, 144, 100, 100);
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
						try {
							
							String contrasena;
							
							if (lblIconVerContra.isVisible()) {
								
								contrasena = String.valueOf(passwordFieldUsuario.getPassword());
							}
							else {
								
								contrasena = txtContrasena.getText();
							}
							
							if (usuario == null) {
								
								Usuario nuevoUsuario;
								
								if (rdbtnMasculino.isSelected()) {
									
									sexoUsuario = 'M';
								}
								else {
									
									sexoUsuario = 'F';
								}
								
								cedula = txtCedula.getText();
								nombre = txtNombre.getText();
								telefono = txtTelefono.getText();
								fechaNacimiento = dateChooserNacim.getDate();
								user = txtUsuario.getText();
								
								if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
									throw new ValidarCampo("Debe llenar los campos obligatorios.");
								}
								
								if (fechaNacimiento == null) {
									throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
								}
								
								if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
									throw new ValidarCampo("Debe seleccionar un sexo.");
								}
								
								if (Clinica.getInstance().validarUsuario(user)) {
									throw new ValidarCampo("Este nombre de usuario ya está en uso.");
								}
								
//								if (!Clinica.getInstance().cedulaValida(txtCedula.getText())) {
//									throw new ValidarCampo("Favor introducir una cédula válida.");
//								}
								
								if (txtUsuario.getText().length() < 8) {
									throw new ValidarCampo("Su nombre de usuario debe tener 8 o más caracteres.");
								}
								
								if (String.valueOf(passwordFieldUsuario.getPassword()).length() < 8) {
									throw new ValidarCampo("Su contraseña debe tener 8 o más caracteres.");
								}
								
								if (medico != null) {
									
									
									
									nuevoUsuario = new Usuario(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
											           sexoUsuario, txtTelefono.getText(), medico.getDireccion(), "Médico", txtUsuario.getText(),
											           contrasena);
								
								}
								else {
									
									nuevoUsuario = new Usuario(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(),
									           sexoUsuario, txtTelefono.getText(), null, "Secretario", txtUsuario.getText(),
									           contrasena);
									
								}
								
								
								Clinica.getInstance().insertarUsuario(nuevoUsuario);
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								
							}
							else {
								
								usuario.setNombreUsuario(txtUsuario.getText());
								usuario.setContrasena(contrasena);
								
								if (usuario.getRolUsuario().equalsIgnoreCase("Secretario")) {
									
									usuario.setTelefono(txtTelefono.getText());
								}
								
								Clinica.getInstance().actualizarUsuario(usuario);
								dispose();
							}
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtNombre.grabFocus();
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
		
		if (usuario != null || medico != null) {
			
			txtCedula.setEditable(false);
			txtNombre.setEditable(false);
			rdbtnMasculino.setEnabled(false);
			rdbtnFemenino.setEnabled(false);
			dateChooserNacim.setEnabled(false);
			txtTelefono.setEditable(false);
			
			if (usuario != null && usuario.getRolUsuario().equalsIgnoreCase("Secretario")) {
				
				txtTelefono.setEditable(true);
			}
			
			if (visualizar) {

				txtTelefono.setEditable(false);
				btnSiguiente.setVisible(false);
				cancelButton.setText("Cerrar");
			}
			
		}
		
		loadDatosMedico();
		loadUsuarioAModificar();

	}
	

	private void loadUsuarioAModificar() {
		
		if (usuario != null) {
			
			txtCedula.setText(usuario.getCedula());
			txtNombre.setText(usuario.getNombre());
			
			if (usuario.getSexo() == 'M') {
				
				rdbtnMasculino.setSelected(true);
			}
			else {
				
				rdbtnFemenino.setSelected(true);
			}
			
			dateChooserNacim.setDate(usuario.getFechaDeNacimiento());
			txtTelefono.setText(usuario.getTelefono());
			txtUsuario.setText(usuario.getNombreUsuario());
			passwordFieldUsuario.setText(usuario.getContrasena());
		}
		
	}
	
	private void loadDatosMedico() {
		
		if (medico != null) {
			
			txtCedula.setText(medico.getCedula());
			txtNombre.setText(medico.getNombre());
			
			if (medico.getSexo() == 'M') {
				
				rdbtnMasculino.setSelected(true);
			}
			else {
				
				rdbtnFemenino.setSelected(true);
			}
			
			dateChooserNacim.setDate(medico.getFechaDeNacimiento());
			txtTelefono.setText(medico.getTelefono());
		}
		
	}

	
	public String getCodePacienteRegistrado() {
		
		return codePacienteRegistrado;
	}
}
