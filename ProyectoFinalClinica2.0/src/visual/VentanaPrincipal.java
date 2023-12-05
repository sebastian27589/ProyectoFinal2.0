package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import logico.Clinica;

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
		
		JMenu mnNewMenu_1 = new JMenu("Recursos Humanos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("M\u00E9dicos");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarMedico mostrarMedicos = new MostrarMedico(null, false);
				mostrarMedicos.setModal(true);
				mostrarMedicos.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Pacientes");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MostrarPaciente mostrarPacientes = new MostrarPaciente(null);
				mostrarPacientes.setModal(true);
				mostrarPacientes.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Datos M\u00E9dicos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Enfermedades");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Vacunas");
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_3 = new JMenu("Citas");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Agendar Citas");
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Mostrar Citas");
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		JMenu mnNewMenu_4 = new JMenu("Consultas M\u00E9dicas");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Ver Citas");
		mnNewMenu_4.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_5 = new JMenu("Reportes");
		menuBar.add(mnNewMenu_5);
		
		JMenu menuHerramientas = new JMenu("Herramientas");
		menuBar.add(menuHerramientas);
		
		JMenu menuItemCrearUsuario = new JMenu("Crear Usuario");
		menuHerramientas.add(menuItemCrearUsuario);
		
		JMenuItem menuItemCrearUMed = new JMenuItem("M\u00E9dico");
		menuItemCrearUsuario.add(menuItemCrearUMed);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Secretario");
		menuItemCrearUsuario.add(mntmNewMenuItem_13);
		
		JMenu mnNewMenu_6 = new JMenu("Mi Perfil");
		menuBar.add(mnNewMenu_6);
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
