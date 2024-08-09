package visual;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import logico.Clinica;
import logico.RoundedPanel;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField passwordFieldLogin;
	private JLabel lblTextBotonLogin;
	private JTextField txtContrasena;
	private JLabel lblIconOcultarContra;
	private JLabel lblIconVerContra;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				FileInputStream fclinica;
				FileOutputStream fclinica2;
				ObjectInputStream fclinicaRead;
				ObjectOutputStream fclinicaWrite;
				
				try {
					
					fclinica = new FileInputStream ("clinica.dat");
					fclinicaRead = new ObjectInputStream(fclinica);
					Clinica temp = (Clinica)fclinicaRead.readObject();
					Clinica.setClinica(temp);
					fclinica.close();
					fclinicaRead.close();
					
				} catch (FileNotFoundException e) {
					
					try {
						
						fclinica2 = new  FileOutputStream("clinica.dat");
						fclinicaWrite = new ObjectOutputStream(fclinica2);
						Usuario primerUsuario = new Usuario("", "", new Date(), 'M', "", "", "Administrador", "admin", "admin");
						Clinica.getInstance().registrarUsuario(primerUsuario);;
						fclinicaWrite.writeObject(Clinica.getInstance());
						fclinica2.close();
						fclinicaWrite.close();
						
						
					} catch (FileNotFoundException e1) {
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
					
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				try {
					
					VentanaLogin ventanaLogin = new VentanaLogin();
					ventanaLogin.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	public static void main(String[] args) {
		try {
			
			Usuario admin = new Usuario("123", "", new Date(), 'M', "", "", "Admin", "admin", "admin");
			Clinica.getInstance().insertarUsuario(admin);
			VentanaLogin dialog = new VentanaLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * 
	 * Create the dialog.
	 */
	public VentanaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/Imagenes/iconLogoHoms.png")));
		setTitle("Sistema de gesti\u00F3n de informaci\u00F3n - HOMS");
		setResizable(false);
		setBounds(100, 100, 820, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		JPanel panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 0, 814, 431);
		contentPanel.add(panelContenedor);
		panelContenedor.setLayout(null);
		
		JPanel barraVertical = new JPanel();
		barraVertical.setBackground(new Color(81, 137, 252, 60));
		barraVertical.setBounds(0, 0, 10, 431);
		panelContenedor.add(barraVertical);
		
		JPanel contenedorCampos = new JPanel();
		contenedorCampos.setBorder(null);
		contenedorCampos.setBounds(10, 0, 289, 431);
		panelContenedor.add(contenedorCampos);
		contenedorCampos.setLayout(null);
		
		lblIconOcultarContra = new JLabel("");
		lblIconOcultarContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblIconOcultarContra.setVisible(false);
				lblIconVerContra.setVisible(true);
				txtContrasena.setVisible(false);
				passwordFieldLogin.setText(txtContrasena.getText());
				passwordFieldLogin.setVisible(true);
			}
		});
		
		lblIconOcultarContra.setVisible(false);
		
		lblIconVerContra = new JLabel("");
		lblIconVerContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblIconVerContra.setVisible(false);
				lblIconOcultarContra.setVisible(true);
				passwordFieldLogin.setVisible(false);
				txtContrasena.setText(String.valueOf(passwordFieldLogin.getPassword()));
				txtContrasena.setVisible(true);
			}
		});
		
		lblIconVerContra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIconVerContra.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/iconVerContrasena.png")));
		lblIconVerContra.setBounds(204, 238, 16, 16);
		contenedorCampos.add(lblIconVerContra);
		lblIconOcultarContra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIconOcultarContra.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/iconOcultarContrasena.png")));
		lblIconOcultarContra.setBounds(204, 238, 16, 16);
		contenedorCampos.add(lblIconOcultarContra);
		
		JLabel lblEncabezado = new JLabel("\u00A1Bienvenido!");
		lblEncabezado.setForeground(new Color(81, 137, 252));
		lblEncabezado.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		lblEncabezado.setBounds(48, 89, 194, 35);
		contenedorCampos.add(lblEncabezado);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblUsuario.setBounds(48, 150, 79, 14);
		contenedorCampos.add(lblUsuario);
		
		RoundedPanel roundedPanelUsuario = new RoundedPanel();
		roundedPanelUsuario.setBackground(new Color(204, 204, 204));
		roundedPanelUsuario.setBounds(48, 175, 146, 22);
		roundedPanelUsuario.setRoundTopLeft(18);
		roundedPanelUsuario.setRoundTopRight(18);
		roundedPanelUsuario.setRoundBottomLeft(18);
		roundedPanelUsuario.setRoundBottomRight(18);
		contenedorCampos.add(roundedPanelUsuario);
		roundedPanelUsuario.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(null);
		txtUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtUsuario.setOpaque(false);
		txtUsuario.setBounds(10, 0, 126, 22);
		roundedPanelUsuario.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblContrasena.setBounds(48, 211, 79, 14);
		contenedorCampos.add(lblContrasena);
		
		RoundedPanel roundedPanelContrasena = new RoundedPanel();
		roundedPanelContrasena.setLayout(null);
		roundedPanelContrasena.setRoundTopRight(18);
		roundedPanelContrasena.setRoundTopLeft(18);
		roundedPanelContrasena.setRoundBottomRight(18);
		roundedPanelContrasena.setRoundBottomLeft(18);
		roundedPanelContrasena.setBackground(new Color(204, 204, 204));
		roundedPanelContrasena.setBounds(48, 236, 146, 22);
		contenedorCampos.add(roundedPanelContrasena);
		
		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		passwordFieldLogin.setOpaque(false);
		passwordFieldLogin.setBorder(null);
		passwordFieldLogin.setBounds(10, 0, 126, 22);
		roundedPanelContrasena.add(passwordFieldLogin);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(10, 0, 126, 22);
		roundedPanelContrasena.add(txtContrasena);
		txtContrasena.setOpaque(false);
		txtContrasena.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtContrasena.setColumns(10);
		txtContrasena.setBorder(null);
		
		JLabel lblIconUsuario = new JLabel("");
		lblIconUsuario.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/iconUsuario.png")));
		lblIconUsuario.setBounds(178, 150, 16, 16);
		contenedorCampos.add(lblIconUsuario);
		
		JLabel lblIconContrasena = new JLabel("");
		lblIconContrasena.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/iconContrasena.png")));
		lblIconContrasena.setBounds(178, 211, 16, 16);
		contenedorCampos.add(lblIconContrasena);
		
		RoundedPanel panelBotonLogin = new RoundedPanel();
		txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
				String password;
				
				if (lblIconVerContra.isVisible()) {
					
					password = String.valueOf(passwordFieldLogin.getPassword());
				}
				else {
					
					password = txtContrasena.getText();
				}
				
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    				if (Clinica.getInstance().permitirInicioSesion(txtUsuario.getText(), password)) {
    					
    					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    					dispose();
    					ventanaPrincipal.setVisible(true);
    				}
    				else {
    					JOptionPane.showMessageDialog(null,"¡Usuario o Contraseña no válidos!", "Error", JOptionPane.ERROR_MESSAGE);
    				}
                }
            }
        });
		
		passwordFieldLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	
				String password;
				
				if (lblIconVerContra.isVisible()) {
					
					password = String.valueOf(passwordFieldLogin.getPassword());
				}
				else {
					
					password = txtContrasena.getText();
				}
            	
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    				if (Clinica.getInstance().permitirInicioSesion(txtUsuario.getText(), password)) {
    					
    					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    					dispose();
    					ventanaPrincipal.setVisible(true);
    				}
    				else {
    					JOptionPane.showMessageDialog(null,"¡Usuario o Contraseña no válidos!", "Error", JOptionPane.ERROR_MESSAGE);
    				}
                }
            }
        });
		
		txtContrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	
				String password;
				
				if (lblIconVerContra.isVisible()) {
					
					password = String.valueOf(passwordFieldLogin.getPassword());
				}
				else {
					
					password = txtContrasena.getText();
				}
            	
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    				if (Clinica.getInstance().permitirInicioSesion(txtUsuario.getText(), password)) {
    					
    					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    					dispose();
    					ventanaPrincipal.setVisible(true);
    				}
    				else {
    					JOptionPane.showMessageDialog(null,"¡Usuario o Contraseña no válidos!", "Error", JOptionPane.ERROR_MESSAGE);
    				}
                }
            }
        });
		panelBotonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				panelBotonLogin.setBackground(new Color(56, 115, 252));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				panelBotonLogin.setBackground(new Color(81, 137, 252));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String password;
				
				if (lblIconVerContra.isVisible()) {
					
					password = String.valueOf(passwordFieldLogin.getPassword());
				}
				else {
					
					password = txtContrasena.getText();
				}
				
				if (Clinica.getInstance().permitirInicioSesion(txtUsuario.getText(), password)) {
					
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
					dispose();
					ventanaPrincipal.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"¡Usuario o Contraseña no válidos!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelBotonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBotonLogin.setBackground(new Color(81, 137, 252));
		panelBotonLogin.setBounds(48, 276, 146, 35);
		panelBotonLogin.setRoundTopLeft(35);
		panelBotonLogin.setRoundTopRight(35);
		panelBotonLogin.setRoundBottomLeft(35);
		panelBotonLogin.setRoundBottomRight(35);
		contenedorCampos.add(panelBotonLogin);
		panelBotonLogin.setLayout(null);
		
		lblTextBotonLogin = new JLabel("INICIAR SESI\u00D3N");
		lblTextBotonLogin.setForeground(Color.WHITE);
		lblTextBotonLogin.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
		lblTextBotonLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextBotonLogin.setBounds(10, 10, 126, 14);
		panelBotonLogin.add(lblTextBotonLogin);
		
		JPanel contenedorGraficos = new JPanel();
		contenedorGraficos.setBounds(299, 0, 515, 431);
		panelContenedor.add(contenedorGraficos);
		contenedorGraficos.setLayout(null);
		
		JLabel lblImagenDoctores = new JLabel("");
		lblImagenDoctores.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/doctoresLogin.png")));
		lblImagenDoctores.setBounds(-300, -8, 804, 431);
		contenedorGraficos.add(lblImagenDoctores);
		
		JLabel lblFigura = new JLabel("");
		lblFigura.setIcon(new ImageIcon(VentanaLogin.class.getResource("/Imagenes/figuraLogin.png")));
		lblFigura.setBounds(-289, 0, 804, 431);
		contenedorGraficos.add(lblFigura);
	}
}
