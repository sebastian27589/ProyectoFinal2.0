package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/iconLogoHoms.png")));
		dim = getToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(dim.width,dim.height-40);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registros");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar M\u00E9dico");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Paciente");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registrar Enfermedad");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar Vacunas");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar Vivienda");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("Recursos Humanos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Mostrar Doctores");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Mostrar Pacientes");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Datos M\u00E9dicos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Mostrar Enfermedades");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Mostrar Vacunas");
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
	}
}
