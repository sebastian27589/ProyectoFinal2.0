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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				FileOutputStream fclinica2;
				ObjectOutputStream fclinicaWrite;
				try {
					
					fclinica2 = new FileOutputStream("datos_clinica.dat");
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
		setSize(dim.width,dim.height-40);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuRegistro = new JMenu("Registro");
		menuBar.add(menuRegistro);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar M\u00E9dico");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegMedico registrarMedico = new RegMedico(null, false);
				registrarMedico.setModal(true);
				registrarMedico.setVisible(true);
			}
		});
		menuRegistro.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Paciente");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegPaciente registrarPaciente = new RegPaciente(null, false, false);
				registrarPaciente.setModal(true);
				registrarPaciente.setVisible(true);
			}
		});
		menuRegistro.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registrar Enfermedad");
		menuRegistro.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar Vacunas");
		menuRegistro.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Vivienda");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegVivienda registrarVivienda = new RegVivienda(null, false);
				registrarVivienda.setModal(true);
				registrarVivienda.setVisible(true);
			}
		});
		menuRegistro.add(mntmNewMenuItem_4);
		
		JMenu menuRecursosHumanos = new JMenu("Recursos Humanos");
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
		menuBar.add(menuDatosMedicos);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Enfermedades");
		menuDatosMedicos.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Vacunas");
		menuDatosMedicos.add(mntmNewMenuItem_8);
		
		JMenu menuCitas = new JMenu("Citas");
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
		if (!Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Administrador")) {
			
			menuReportes.setEnabled(false);
		}
		menuBar.add(menuReportes);
		
		JMenu menuGerencia = new JMenu("Gerencia");
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
		
		JMenuItem menuItemConsMeds = new JMenuItem("Consultas M\u00E9dicas");
		menuGerencia.add(menuItemConsMeds);
		
		JMenuItem menuItemHistMeds = new JMenuItem("Historiales M\u00E9dicos");
		menuGerencia.add(menuItemHistMeds);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/principal_fondo.png")));
		lblNewLabel.setBounds(434, 0, 562, 600);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 611, 1319, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
        JLabel lbl_Tiempo = new JLabel();
        lbl_Tiempo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        lbl_Tiempo.setBounds(1179, 15, 130, 14);

        panel.add(lbl_Tiempo);
        
        JLabel lblNewLabel_1 = new JLabel(Clinica.getInstance().getUsuarioLogueado().getNombreUsuario());
        lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(33, 15, 130, 14);
        panel.add(lblNewLabel_1);

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
