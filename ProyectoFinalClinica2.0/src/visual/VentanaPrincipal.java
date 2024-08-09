package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import keeptoo.KGradientPanel;
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
import java.awt.Panel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import logico.RoundedPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem menuItemVacunas;
	private JMenuItem menuItemAgendarCita;
	private JMenuItem menuItemMostrarCitas;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu menuItemUsuarios;
	private JMenu menuCrearUsuario;
	private JMenuItem menuItemMedico;
	private JMenuItem menuItemSecretario;
	private JMenuItem menuItemListaUsuarios;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenu menuReportes;
	private JMenu menuGerencia;
	private JMenu menuRecursosHumanos;
	private JMenu menuCitas;
	private JMenu menuDatosMedicos;
	private JLabel label;
	private JPanel panel_2;
	private JLabel lblRegistro;
	private JPanel panelCita;
	private JLabel lblCita;
	private JPanel panelReporte;
	private JLabel lblReporte;
	private JPanel panelGerencia;
	private JLabel lblGerencia;
	private JPanel panelCerrarSesion;
	private JLabel lblCerrarSesion;
	private JPanel panelRegistro;
	private RoundedPanel roundedPanelPersona;
	private RoundedPanel roundedPanelEnfermedad;
	private RoundedPanel roundedPanelVacuna;
	private RoundedPanel roundedPanelMedico;
	private JLabel lblMedico;
	private RoundedPanel roundedPanelSintoma;
	private JLabel lblPersona;
	private JLabel lblSintoma;
	private JLabel lblEnfermedad;
	private JLabel lblVacuna;
	private JPanel panelFondo2;
	private JPanel panelFondo3;
	private JPanel panelFondo4;
	private JPanel panelFondo;
	private RoundedPanel roundedAgendarCita;
	private JLabel lblAgendarCita;
	private RoundedPanel roundedListadoCita;
	private JLabel lblListadoCita;

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
		
		//KGradientPanel gradientPanel = new KGradientPanel();
		//contentPane.add(gradientPanel, BorderLayout.CENTER);
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/iconLogoHoms.png")));
		dim = getToolkit().getScreenSize();
		int screenWidthOriginal = 1920;
		int screenHeightOriginal = 1080;
		double widthRatio = (double) dim.width / screenWidthOriginal;
		double heightRatio = (double) dim.height / screenHeightOriginal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int)(450*widthRatio), (int)(300*heightRatio));
		setSize((int)(1920*widthRatio),(int)(1040*heightRatio));
		setLocationRelativeTo(null);
		
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario") ||
			Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Médico")) {
				
//			menuItemRegMedico.setEnabled(false);
//			menuItemRegVacunas.setEnabled(false);
//			menuItemRegEnfermedad.setEnabled(false);
//			menuItemRegVivienda.setEnabled(false);
		}
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario") ||
		    Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Médico")) {
			
			menuRecursosHumanos.setEnabled(false);
		}
		if (Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Secretario")) {
				
			menuDatosMedicos.setEnabled(false);
		}
		if (!Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Administrador")) {
			
			menuReportes.setEnabled(false);
		}
		if (!Clinica.getInstance().getUsuarioLogueado().getRolUsuario().equalsIgnoreCase("Administrador")) {
			
			menuGerencia.setEnabled(false);
		}
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panelTiempoSesion = new JPanel();
		panelTiempoSesion.setBorder(new CompoundBorder());
		panelTiempoSesion.setBounds((int)(492*widthRatio), (int)(920*heightRatio), (int)(1381*widthRatio), (int)(46*heightRatio));
		contentPane.add(panelTiempoSesion);
		panelTiempoSesion.setLayout(null);
		
        JLabel lbl_Tiempo = new JLabel();
        lbl_Tiempo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        lbl_Tiempo.setBounds((int)(1230*widthRatio), (int)(7*heightRatio), (int)(130*widthRatio), (int)(33*heightRatio));
        
        
                panelTiempoSesion.add(lbl_Tiempo);
                
                JLabel lblNewLabel_1 = new JLabel(Clinica.getInstance().getUsuarioLogueado().getNombreUsuario());
                lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, (int)(25*widthRatio)));
                lblNewLabel_1.setBounds((int)(33*widthRatio), (int)(7*heightRatio), (int)(130*widthRatio), (int)(33*heightRatio));
                panelTiempoSesion.add(lblNewLabel_1);
        
        lblRegistro = new JLabel("REGISTRO");
        lblRegistro.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		panelRegistro.setVisible(true);
        		panelCita.setVisible(false);
        		panelReporte.setVisible(false);
        		panelGerencia.setVisible(false);
        		panelCerrarSesion.setVisible(false);
        		panelRegistro.setBackground(new Color(81, 137, 252));
        		panelFondo.setVisible(true);
        		panelFondo2.setVisible(false);
        		panelFondo3.setVisible(false);
        		panelFondo4.setVisible(false);
        	}
        });
        lblRegistro.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-register-64.png")));
        lblRegistro.setSize((int)(459*widthRatio), (int)(196*heightRatio));
        lblRegistro.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblRegistro.setPreferredSize(new Dimension(65, 25));
        lblRegistro.setLocation(new Point(0, 0));
        lblRegistro.setIconTextGap(7);
        lblRegistro.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setForeground(Color.WHITE);
        lblRegistro.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        contentPane.add(lblRegistro);
        
        panelRegistro = new JPanel();
        panelRegistro.setBorder(new CompoundBorder());
        panelRegistro.setBounds(0, 0, (int)(459*widthRatio), (int)(196*heightRatio));
        contentPane.add(panelRegistro);
        panelRegistro.setBackground(new Color(255, 255, 255, 0));
        panelRegistro.setLayout(null);

        
        lblCita = new JLabel("CITAS");
        lblCita.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		panelRegistro.setVisible(false);
        		panelCita.setVisible(true);
        		panelReporte.setVisible(false);
        		panelGerencia.setVisible(false);
        		panelCerrarSesion.setVisible(false);
        		panelCita.setBackground(new Color(81, 137, 252));
        		panelFondo.setVisible(false);
        		panelFondo2.setVisible(true);
        		panelFondo3.setVisible(false);
        		panelFondo4.setVisible(false);
        	}
        });
        lblCita.setBounds(0, (int)(198*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        contentPane.add(lblCita);
        lblCita.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-appointment-64.png")));
        lblCita.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblCita.setPreferredSize(new Dimension(65, 25));
        lblCita.setIconTextGap(7);
        lblCita.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCita.setHorizontalAlignment(SwingConstants.CENTER);
        lblCita.setForeground(Color.WHITE);
        lblCita.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        
        panelCita = new JPanel();
        panelCita.setBorder(new CompoundBorder());
        panelCita.setBounds(0, (int)(198*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        panelCita.setBackground(new Color(255, 255, 255, 0));
        contentPane.add(panelCita);
        contentPane.setLayout(null);
        
        lblReporte = new JLabel("REPORTES");
        lblReporte.setBounds(0, (int)(397*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        contentPane.add(lblReporte);
        lblReporte.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		panelRegistro.setVisible(false);
        		panelCita.setVisible(false);
        		panelReporte.setVisible(true);
        		panelGerencia.setVisible(false);
        		panelCerrarSesion.setVisible(false);
        		panelReporte.setBackground(new Color(81, 137, 252));
        		panelFondo.setVisible(false);
        		panelFondo2.setVisible(false);
        		panelFondo3.setVisible(true);
        		panelFondo4.setVisible(false);
        	}
        });
        lblReporte.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-report-70.png")));
        lblReporte.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblReporte.setPreferredSize(new Dimension(65, 25));
        lblReporte.setIconTextGap(7);
        lblReporte.setHorizontalTextPosition(SwingConstants.CENTER);
        lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
        lblReporte.setForeground(Color.WHITE);
        lblReporte.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        
        panelReporte = new JPanel();
        panelReporte.setBorder(new CompoundBorder());
        panelReporte.setForeground(new Color(0, 0, 0));
        panelReporte.setBounds(0, (int)(397*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        panelReporte.setBackground(new Color(255, 255, 255, 0));
        contentPane.add(panelReporte);
        panelReporte.setLayout(null);
        
	                lblGerencia = new JLabel("GERENCIA");
	                lblGerencia.setBounds(0, (int)(596*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
	                contentPane.add(lblGerencia);
	                lblGerencia.addMouseListener(new MouseAdapter() {
	                	@Override
	                	public void mouseClicked(MouseEvent e) {
	                		panelRegistro.setVisible(false);
	                		panelCita.setVisible(false);
	                		panelReporte.setVisible(false);
	                		panelGerencia.setVisible(true);
	                		panelCerrarSesion.setVisible(false);
	                		panelGerencia.setBackground(new Color(81, 137, 252));
	                		panelFondo.setVisible(false);
	                		panelFondo2.setVisible(false);
	                		panelFondo3.setVisible(false);
	                		panelFondo4.setVisible(true);
	                	}
	                });
	                lblGerencia.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-manager-70.png")));
	                lblGerencia.setVerticalTextPosition(SwingConstants.BOTTOM);
	                lblGerencia.setPreferredSize(new Dimension(65, 25));
	                lblGerencia.setIconTextGap(7);
	                lblGerencia.setHorizontalTextPosition(SwingConstants.CENTER);
	                lblGerencia.setHorizontalAlignment(SwingConstants.CENTER);
	                lblGerencia.setForeground(Color.WHITE);
	                lblGerencia.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        
        panelGerencia = new JPanel();
        panelGerencia.setBorder(new CompoundBorder());
        panelGerencia.setBounds(0, (int)(596*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        panelGerencia.setBackground(new Color(255, 255, 255, 0));
        contentPane.add(panelGerencia);
        panelGerencia.setLayout(null);
        
        lblCerrarSesion = new JLabel("CERRAR SESI\u00D3N");
        lblCerrarSesion.setBounds(0, (int)(797*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        contentPane.add(lblCerrarSesion);
        lblCerrarSesion.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		panelRegistro.setVisible(false);
        		panelCita.setVisible(false);
        		panelReporte.setVisible(false);
        		panelGerencia.setVisible(false);
        		panelCerrarSesion.setVisible(true);
        		panelCerrarSesion.setBackground(new Color(81, 137, 252));
        	}
        });
        lblCerrarSesion.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-log-out-70.png")));
        lblCerrarSesion.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblCerrarSesion.setPreferredSize(new Dimension(65, 25));
        lblCerrarSesion.setIconTextGap(7);
        lblCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
        lblCerrarSesion.setForeground(Color.WHITE);
        lblCerrarSesion.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        
        panelCerrarSesion = new JPanel();
        panelCerrarSesion.setBorder(new CompoundBorder());

        panelCerrarSesion.setBounds(0, (int)(797*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        panelCerrarSesion.setBackground(new Color(255, 255, 255, 0));
        contentPane.add(panelCerrarSesion);
        panelCerrarSesion.setLayout(null);
        
        KGradientPanel gradientPanel = new KGradientPanel();
        gradientPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        gradientPanel.setOpaque(false);
        gradientPanel.setkEndColor(new Color(0, 0, 255));
        gradientPanel.kEndColor = new Color(0, 0, 255);
        gradientPanel.kStartColor = new Color(255, 0, 255);
        gradientPanel.setkStartColor(new Color(255, 0, 255));
        gradientPanel.setBounds(0, 0, (int)(459*widthRatio), (int)(993*heightRatio));
        contentPane.add(gradientPanel);
        gradientPanel.setLayout(null);
        
        panelFondo = new JPanel();
        panelFondo.setBackground(new Color(255, 255, 255));
        panelFondo.setBounds((int)(458*widthRatio), 0, (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo);
        panelFondo.setLayout(null);
        
        Panel panel_1 = new Panel();
        panel_1.setBounds((int)(943*widthRatio), (int)(457*heightRatio), (int)(345*widthRatio), (int)(417*heightRatio));
        panelFondo.add(panel_1);
        
        menuDatosMedicos = new JMenu("Datos M\u00E9dicos");
        panel_1.add(menuDatosMedicos);
        menuDatosMedicos.setFont(new Font("Segoe UI", Font.PLAIN, (int)(30*heightRatio)));
        menuDatosMedicos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/10295499861529659195-48.png")));
        
        mntmNewMenuItem = new JMenuItem("Enfermedades");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarEnfermedad mostEnfermedad = new MostrarEnfermedad(null);
        		mostEnfermedad.setModal(true);
        		mostEnfermedad.setVisible(true);
        	}
        });
        menuDatosMedicos.add(mntmNewMenuItem);
        
        menuItemVacunas = new JMenuItem("Vacunas");
        menuItemVacunas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarVacuna mostrarVacunas = new MostrarVacuna(null);
        		mostrarVacunas.setModal(true);
        		mostrarVacunas.setVisible(true);
        	}
        });
        menuDatosMedicos.add(menuItemVacunas);
        
        menuCitas = new JMenu("Citas");
        panel_1.add(menuCitas);
        menuCitas.setFont(new Font("Segoe UI", Font.PLAIN, (int)(30*heightRatio)));
        menuCitas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/9766375281691835297-48.png")));
        
        menuItemAgendarCita = new JMenuItem("Agendar Cita");
        menuItemAgendarCita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		RegCita registrarCita = new RegCita(null);
        		registrarCita.setModal(true);
        		registrarCita.setVisible(true);
        	}
        });
        menuCitas.add(menuItemAgendarCita);
        
        menuItemMostrarCitas = new JMenuItem("Listado de Citas");
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
        
        menuReportes = new JMenu("Reportes");
        panel_1.add(menuReportes);
        menuReportes.setFont(new Font("Segoe UI", Font.PLAIN, (int)(30*heightRatio)));
        menuReportes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/12663137361594941313-48.png")));
        
        mntmNewMenuItem_1 = new JMenuItem("Abrir Reportes");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GenerarReporte nuevoReporte = new GenerarReporte();
        		nuevoReporte.setModal(true);
        		nuevoReporte.setVisible(true);
        	}
        });
        menuReportes.add(mntmNewMenuItem_1);
        
        menuGerencia = new JMenu("Gerencia");
        panel_1.add(menuGerencia);
        menuGerencia.setFont(new Font("Segoe UI", Font.PLAIN, (int)(30*heightRatio)));
        menuGerencia.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/8648519161659819449-48.png")));
        
        menuItemUsuarios = new JMenu("Usuarios");
        menuGerencia.add(menuItemUsuarios);
        
        menuCrearUsuario = new JMenu("Crear Usuario");
        menuItemUsuarios.add(menuCrearUsuario);
        
        menuItemMedico = new JMenuItem("M\u00E9dico");
        menuItemMedico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarMedico mostrarMedicoParaCrearUser = new MostrarMedico(null, true, false);
        		mostrarMedicoParaCrearUser.setModal(true);
        		mostrarMedicoParaCrearUser.setVisible(true);
        		
        	}
        });
        menuCrearUsuario.add(menuItemMedico);
        
        menuItemSecretario = new JMenuItem("Secretario");
        menuItemSecretario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		RegUsuario registrarUsuario = new RegUsuario(null, null, false);
        		registrarUsuario.setModal(true);
        		registrarUsuario.setVisible(true);
        		
        	}
        });
        menuCrearUsuario.add(menuItemSecretario);
        
        menuItemListaUsuarios = new JMenuItem("Listado de Usuarios");
        menuItemListaUsuarios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarUsuario mostrarUsuarios = new MostrarUsuario(null);
        		mostrarUsuarios.setModal(true);
        		mostrarUsuarios.setVisible(true);
        	}
        });
        menuItemUsuarios.add(menuItemListaUsuarios);
        menuRecursosHumanos = new JMenu("Recursos Humanos");
        panel_1.add(menuRecursosHumanos);
        menuRecursosHumanos.setFont(new Font("Segoe UI", Font.PLAIN, (int)(30*heightRatio)));
        menuRecursosHumanos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/14102962671595341166-48.png")));
        
        mntmNewMenuItem_2 = new JMenuItem("M\u00E9dicos");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarMedico mostrarMedicos = new MostrarMedico(null, false, false);
        		mostrarMedicos.setModal(true);
        		mostrarMedicos.setVisible(true);
        	}
        });
        menuRecursosHumanos.add(mntmNewMenuItem_2);
        
        mntmNewMenuItem_3 = new JMenuItem("Pacientes");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		MostrarPaciente mostrarPacientes = new MostrarPaciente(null);
        		mostrarPacientes.setModal(true);
        		mostrarPacientes.setVisible(true);
        	}
        });
        menuRecursosHumanos.add(mntmNewMenuItem_3);
        
        roundedPanelPersona = new RoundedPanel();
        roundedPanelPersona.setBorder(new CompoundBorder());
        roundedPanelPersona.setLayout(null);
        roundedPanelPersona.setRoundTopRight(35);
        roundedPanelPersona.setRoundTopLeft(35);
        roundedPanelPersona.setRoundBottomRight(35);
        roundedPanelPersona.setRoundBottomLeft(35);
        roundedPanelPersona.setBackground(new Color(81, 137, 252));
        roundedPanelPersona.setBounds((int)(167*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelPersona);
        
        lblPersona = new JLabel("PERSONA");
        lblPersona.setForeground(new Color(255, 255, 255));
        lblPersona.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
        lblPersona.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegPaciente registrarPaciente = new RegPaciente(null, false, false);
        		registrarPaciente.setModal(true);
        		registrarPaciente.setVisible(true);
        	}
        });
        lblPersona.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-person-100.png")));
        lblPersona.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblPersona.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPersona.setHorizontalAlignment(SwingConstants.CENTER);
        lblPersona.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelPersona.add(lblPersona);
        
        roundedPanelMedico = new RoundedPanel();
        roundedPanelMedico.setBorder(new CompoundBorder());
        roundedPanelMedico.setLayout(null);
        roundedPanelMedico.setRoundTopRight(35);
        roundedPanelMedico.setRoundTopLeft(35);
        roundedPanelMedico.setRoundBottomRight(35);
        roundedPanelMedico.setRoundBottomLeft(35);
        roundedPanelMedico.setBackground(new Color(81, 137, 252));
        roundedPanelMedico.setBounds((int)(553*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelMedico);
        
        lblMedico = new JLabel("");
        lblMedico.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegMedico registrarMedico = new RegMedico(null, false);
        		registrarMedico.setModal(true);
        		registrarMedico.setVisible(true);
        	}
        });
        lblMedico.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-medical-doctor-100.png")));
        lblMedico.setHorizontalAlignment(SwingConstants.CENTER);
        lblMedico.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblMedico.setHorizontalTextPosition(SwingConstants.CENTER);
        lblMedico.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelMedico.add(lblMedico);
        
        roundedPanelEnfermedad = new RoundedPanel();
        roundedPanelEnfermedad.setBorder(new CompoundBorder());
        roundedPanelEnfermedad.setLayout(null);
        roundedPanelEnfermedad.setRoundTopRight(35);
        roundedPanelEnfermedad.setRoundTopLeft(35);
        roundedPanelEnfermedad.setRoundBottomRight(35);
        roundedPanelEnfermedad.setRoundBottomLeft(35);
        roundedPanelEnfermedad.setBackground(new Color(81, 137, 252));
        roundedPanelEnfermedad.setBounds((int)(167*widthRatio), (int)(457*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelEnfermedad);
        
        lblEnfermedad = new JLabel("");
        lblEnfermedad.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegEnfermedad regNuevaEnfermedad = new RegEnfermedad(null,false);
        		regNuevaEnfermedad.setModal(true);
        		regNuevaEnfermedad.setVisible(true);
        	}
        });
        lblEnfermedad.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-disease-64.png")));
        lblEnfermedad.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblEnfermedad.setHorizontalTextPosition(SwingConstants.CENTER);
        lblEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnfermedad.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelEnfermedad.add(lblEnfermedad);
        
        roundedPanelVacuna = new RoundedPanel();
        roundedPanelVacuna.setBorder(new CompoundBorder());
        roundedPanelVacuna.setLayout(null);
        roundedPanelVacuna.setRoundTopRight(35);
        roundedPanelVacuna.setRoundTopLeft(35);
        roundedPanelVacuna.setRoundBottomRight(35);
        roundedPanelVacuna.setRoundBottomLeft(35);
        roundedPanelVacuna.setBackground(new Color(81, 137, 252));
        roundedPanelVacuna.setBounds((int)(553*widthRatio), (int)(457*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelVacuna);
        
        lblVacuna = new JLabel("");
        lblVacuna.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegVacuna nuevaVacuna = new RegVacuna(null);
        		nuevaVacuna.setModal(true);
        		nuevaVacuna.setVisible(true);
        	}
        });
        lblVacuna.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-vaccine-64 (1).png")));
        lblVacuna.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblVacuna.setHorizontalTextPosition(SwingConstants.CENTER);
        lblVacuna.setHorizontalAlignment(SwingConstants.CENTER);
        lblVacuna.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelVacuna.add(lblVacuna);
        
        roundedPanelSintoma = new RoundedPanel();
        roundedPanelSintoma.setBorder(new CompoundBorder());
        roundedPanelSintoma.setLayout(null);
        roundedPanelSintoma.setRoundTopRight(35);
        roundedPanelSintoma.setRoundTopLeft(35);
        roundedPanelSintoma.setRoundBottomRight(35);
        roundedPanelSintoma.setRoundBottomLeft(35);
        roundedPanelSintoma.setBackground(new Color(81, 137, 252));
        roundedPanelSintoma.setBounds((int)(943*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelSintoma);
        
        lblSintoma = new JLabel("");
        lblSintoma.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-fever-64_1_optimized.png")));
        lblSintoma.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblSintoma.setHorizontalTextPosition(SwingConstants.CENTER);
        lblSintoma.setHorizontalAlignment(SwingConstants.CENTER);
        lblSintoma.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelSintoma.add(lblSintoma);
        
        panelFondo2 = new JPanel();
        panelFondo2.setBackground(new Color(255, 255, 255));
        panelFondo2.setBounds((int)(458*widthRatio), (int)(0*heightRatio), (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo2);
        panelFondo2.setLayout(null);
        
        roundedAgendarCita = new RoundedPanel();
        roundedAgendarCita.setLayout(null);
        roundedAgendarCita.setRoundTopRight(35);
        roundedAgendarCita.setRoundTopLeft(35);
        roundedAgendarCita.setRoundBottomRight(35);
        roundedAgendarCita.setRoundBottomLeft(35);
        roundedAgendarCita.setBorder(new CompoundBorder());
        roundedAgendarCita.setBackground(new Color(255, 222, 173));
        roundedAgendarCita.setBounds((int)(167*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo2.add(roundedAgendarCita);
        
        lblAgendarCita = new JLabel("");
        lblAgendarCita.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RegCita registrarCita = new RegCita(null);
        		registrarCita.setModal(true);
        		registrarCita.setVisible(true);
        	}
        });
        lblAgendarCita.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-appointment-100.png")));
        lblAgendarCita.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblAgendarCita.setHorizontalTextPosition(SwingConstants.CENTER);
        lblAgendarCita.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgendarCita.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedAgendarCita.add(lblAgendarCita);
        
        roundedListadoCita = new RoundedPanel();
        roundedListadoCita.setLayout(null);
        roundedListadoCita.setRoundTopRight(35);
        roundedListadoCita.setRoundTopLeft(35);
        roundedListadoCita.setRoundBottomRight(35);
        roundedListadoCita.setRoundBottomLeft(35);
        roundedListadoCita.setBorder(new CompoundBorder());
        roundedListadoCita.setBackground(new Color(81, 137, 252));
        roundedListadoCita.setBounds((int)(553*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo2.add(roundedListadoCita);
        
        lblListadoCita = new JLabel("");
        lblListadoCita.setBackground(new Color(255, 140, 0));
        lblListadoCita.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
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
        lblListadoCita.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-list-80.png")));
        lblListadoCita.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblListadoCita.setHorizontalTextPosition(SwingConstants.CENTER);
        lblListadoCita.setHorizontalAlignment(SwingConstants.CENTER);
        lblListadoCita.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedListadoCita.add(lblListadoCita);
        
        panelFondo3 = new JPanel();
        panelFondo3.setLayout(null);
        panelFondo3.setBackground(Color.WHITE);
        panelFondo3.setBounds(458, 0, 1444, 993);
        contentPane.add(panelFondo3);
        
        panelFondo4 = new JPanel();
        panelFondo4.setLayout(null);
        panelFondo4.setBackground(Color.WHITE);
        panelFondo4.setBounds(458, 0, 1444, 993);
        contentPane.add(panelFondo4);
        

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