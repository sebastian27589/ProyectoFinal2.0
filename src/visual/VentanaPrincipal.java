package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Medico;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 * @param conexion 
	 */
	public VentanaPrincipal(Connection conexion) {
		
		// Comentario de prueba
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				FileOutputStream fclinica2;
				ObjectOutputStream fclinicaWrite;
				try {
					
					fclinica2 = new FileOutputStream("clinica.dat");
					fclinicaWrite = new ObjectOutputStream(fclinica2);
					fclinicaWrite.writeObject(Clinica.getInstance());
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/iconLogoHoms.png")));
		dim = getToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setSize(dim.width,dim.height-40);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuRegistro = new JMenu("Registro");
		menuRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/8933286741678773690-48.png")));
		menuBar.add(menuRegistro);
		
		JMenuItem menuItemRegPaciente = new JMenuItem("Registrar Paciente");
		menuItemRegPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegPaciente registrarPaciente = new RegPaciente(null, false, false);
				registrarPaciente.setModal(true);
				registrarPaciente.setVisible(true);
			}
		});
		menuRegistro.add(menuItemRegPaciente);
		
		JMenuItem menuItemRegEnfermedad = new JMenuItem("Registrar Enfermedad");
		menuItemRegEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad regNuevaEnfermedad = new RegEnfermedad(null,false);
				regNuevaEnfermedad.setModal(true);
				regNuevaEnfermedad.setVisible(true);
			}
		});
		
		JMenuItem menuItemRegMedico = new JMenuItem("Registrar M\u00E9dico");
		
		menuItemRegMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegMedico registrarMedico = new RegMedico(null, false);
				registrarMedico.setModal(true);
				registrarMedico.setVisible(true);
			}
		});
		menuRegistro.add(menuItemRegMedico);
		menuRegistro.add(menuItemRegEnfermedad);
		
		JMenuItem menuItemRegVacunas = new JMenuItem("Registrar Vacunas");
		menuItemRegVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegVacuna nuevaVacuna = new RegVacuna(null);
				nuevaVacuna.setModal(true);
				nuevaVacuna.setVisible(true);
			}
		});
		menuRegistro.add(menuItemRegVacunas);
		
		JMenuItem menuItemRegVivienda = new JMenuItem("Registrar Vivienda");
		menuItemRegVivienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegVivienda registrarVivienda = new RegVivienda(null, false);
				registrarVivienda.setModal(true);
				registrarVivienda.setVisible(true);
			}
		});
		menuRegistro.add(menuItemRegVivienda);
		
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario") ||
			Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Médico")) {
				
			menuItemRegMedico.setEnabled(false);
			menuItemRegVacunas.setEnabled(false);
			menuItemRegEnfermedad.setEnabled(false);
			menuItemRegVivienda.setEnabled(false);
		}
		JMenu menuRecursosHumanos = new JMenu("Recursos Humanos");
		menuRecursosHumanos.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuRecursosHumanos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/14102962671595341166-48.png")));
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario") ||
		    Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Médico")) {
			
			menuRecursosHumanos.setEnabled(false);
		}
		
		menuBar.add(menuRecursosHumanos);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("M\u00E9dicos");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarMedico mostrarMedicos = new MostrarMedico(null, false, false);
				mostrarMedicos.setModal(true);
				mostrarMedicos.setVisible(true);
			}
		});
		menuRecursosHumanos.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Pacientes");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarPaciente mostrarPacientes = new MostrarPaciente(null);
				mostrarPacientes.setModal(true);
				mostrarPacientes.setVisible(true);
			}
		});
		menuRecursosHumanos.add(mntmNewMenuItem_6);
		
		JMenu menuDatosMedicos = new JMenu("Datos M\u00E9dicos");
		menuDatosMedicos.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuDatosMedicos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/10295499861529659195-48.png")));
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario")) {
				
			menuDatosMedicos.setEnabled(false);
		}
		
		menuBar.add(menuDatosMedicos);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Enfermedades");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarEnfermedad mostEnfermedad = new MostrarEnfermedad(null);
				mostEnfermedad.setModal(true);
				mostEnfermedad.setVisible(true);
			}
		});
		menuDatosMedicos.add(mntmNewMenuItem_7);
		
		JMenuItem menuItemVacunas = new JMenuItem("Vacunas");
		menuItemVacunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarVacuna mostrarVacunas = new MostrarVacuna(null);
				mostrarVacunas.setModal(true);
				mostrarVacunas.setVisible(true);
			}
		});
		menuDatosMedicos.add(menuItemVacunas);
		
		JMenu menuCitas = new JMenu("Citas");
		menuCitas.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuCitas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/9766375281691835297-48.png")));
		menuBar.add(menuCitas);
		
		JMenuItem menuItemAgendarCita = new JMenuItem("Agendar Cita");
		menuItemAgendarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegCita registrarCita = new RegCita(null);
				registrarCita.setModal(true);
				registrarCita.setVisible(true);
			}
		});
		menuCitas.add(menuItemAgendarCita);
		
		JMenuItem menuItemMostrarCitas = new JMenuItem("Listado de Citas");
		menuItemMostrarCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarCita mostrarCitasClinica = null;
				
				if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Médico")) {
					
					Medico medicoLogueado = Clinica.getInstance().buscarMedicoByCedula(Clinica.getInstance().getUsuarioLogueado().getCedula());
					
					mostrarCitasClinica = new MostrarCita(Clinica.getInstance().citasPendientesByCodeMedico(medicoLogueado.getCodeMedico()));
				}
				else {
					
					mostrarCitasClinica = new MostrarCita(null);
				}

				mostrarCitasClinica.setModal(true);
				mostrarCitasClinica.setVisible(true);
			}
		});
		menuCitas.add(menuItemMostrarCitas);
		
		JMenu menuReportes = new JMenu("Reportes");
		menuReportes.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuReportes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/12663137361594941313-48.png")));
		if (!Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Administrador")) {
			
			menuReportes.setEnabled(false);
		}
		menuBar.add(menuReportes);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Abrir Reportes");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarReporte nuevoReporte = new GenerarReporte();
				nuevoReporte.setModal(true);
				nuevoReporte.setVisible(true);
			}
		});
		menuReportes.add(mntmNewMenuItem_9);
		
		JMenu menuGerencia = new JMenu("Gerencia");
		menuGerencia.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuGerencia.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/8648519161659819449-48.png")));
		if (!Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Administrador")) {
			
			menuGerencia.setEnabled(false);
		}
		menuBar.add(menuGerencia);
		
		JMenu menuItemUsuarios = new JMenu("Usuarios");
		menuGerencia.add(menuItemUsuarios);
		
		JMenu menuCrearUsuario = new JMenu("Crear Usuario");
		menuItemUsuarios.add(menuCrearUsuario);
		
		JMenuItem menuItemMedico = new JMenuItem("M\u00E9dico");
		menuItemMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarMedico mostrarMedicoParaCrearUser = new MostrarMedico(null, true, false);
				mostrarMedicoParaCrearUser.setModal(true);
				mostrarMedicoParaCrearUser.setVisible(true);
				
			}
		});
		menuCrearUsuario.add(menuItemMedico);
		
		JMenuItem menuItemSecretario = new JMenuItem("Secretario");
		menuItemSecretario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegUsuario registrarUsuario = new RegUsuario(null, null, false);
				registrarUsuario.setModal(true);
				registrarUsuario.setVisible(true);
				
			}
		});
		menuCrearUsuario.add(menuItemSecretario);
		
		JMenuItem menuItemListaUsuarios = new JMenuItem("Listado de Usuarios");
		menuItemListaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarUsuario mostrarUsuarios = new MostrarUsuario(null);
				mostrarUsuarios.setModal(true);
				mostrarUsuarios.setVisible(true);
			}
		});
		menuItemUsuarios.add(menuItemListaUsuarios);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 877, 1824, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
        JLabel lbl_Tiempo = new JLabel();
        lbl_Tiempo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        lbl_Tiempo.setBounds(1650, 7, 130, 33);
        
                panel.add(lbl_Tiempo);
                
                JLabel lblNewLabel_1 = new JLabel(Clinica.getInstance().getUsuarioLogueado().getNombreUsuario());
                lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
                lblNewLabel_1.setBounds(33, 7, 130, 33);
                panel.add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2222).png")));
		label_4.setBounds(934, 0, 766, 1000);
		contentPane.add(label_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2).png")));
		label_3.setBounds(664, -57, 766, 1000);
		contentPane.add(label_3);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2222).png")));
		label_1.setBounds(1230, 0, 766, 1000);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2222).png")));
		label.setBounds(1532, 0, 766, 1000);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (5)6.png")));
		label_2.setBounds(944, 131, 958, 569);
		contentPane.add(label_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (5).png")));
		lblNewLabel_3.setBounds(0, 176, 670, 569);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2).png")));
		lblNewLabel_2.setBounds(310, -43, 766, 1000);
		contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/logo-y-slogan-a-color-1024x429.png")));
        lblNewLabel_4.setBounds(621, 264, 708, 356);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/pngegg (2).png")));
        lblNewLabel.setBounds(0, -48, 375, 991);
        contentPane.add(lblNewLabel);

        Thread hilo = new Thread(() -> {
        	LocalDateTime fechaHora = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
            while (true) {
                try {
                	
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    lbl_Tiempo.setText(fechaHora.format(formatter));
                    Thread.sleep(1000);
                    fechaHora = fechaHora.plusSeconds(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        hilo.start();
	}
}