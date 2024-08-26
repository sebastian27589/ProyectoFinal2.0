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
import logico.PanelSimulacionAnim;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	private JLabel label;
	private JPanel panel_2;
	private JLabel lblRegistro;
	private PanelSimulacionAnim panelCita;
	private JLabel lblCita;
	private PanelSimulacionAnim panelReporte;
	private JLabel lblReporte;
	private PanelSimulacionAnim panelGerencia;
	private JLabel lblGerencia;
	private PanelSimulacionAnim panelCerrarSesion;
	private JLabel lblCerrarSesion;
	private PanelSimulacionAnim panelRegistro;
	private RoundedPanel roundedPanelPersona;
	private RoundedPanel roundedPanelEnfermedad;
	private RoundedPanel roundedPanelVacuna;
	private RoundedPanel roundedPanelMedico;
	private JLabel lblMedico;
	private JLabel lblPersona;
	private JLabel lblEnfermedad;
	private JLabel lblVacuna;
	private PanelSimulacionAnim panelFondo;
	private PanelSimulacionAnim panelFondo2;
	private PanelSimulacionAnim panelFondo3;
	private PanelSimulacionAnim panelFondo4;
	private int ind = 0;
	private int tiempoAnim = 20;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Connection conexion) {
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
//				FileOutputStream fclinica2;
//				ObjectOutputStream fclinicaWrite;
//				try {
//					
//					fclinica2 = new FileOutputStream("clinica.dat");
//					fclinicaWrite = new ObjectOutputStream(fclinica2);
//					fclinicaWrite.writeObject(Clinica.getInstance());
//					
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
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
		
		if (Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Secretario") ||
			Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Médico")) {
				
//			menuItemRegMedico.setEnabled(false);
//			menuItemRegVacunas.setEnabled(false);
//			menuItemRegEnfermedad.setEnabled(false);
//			menuItemRegVivienda.setEnabled(false);
		}
		if (Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Secretario") ||
		    Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Médico")) {
			
			//menuRecursosHumanos.setEnabled(false);
		}
		if (Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Secretario")) {
				
			//menuDatosMedicos.setEnabled(false);
		}
		if (!Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Administrador")) {
			
			//menuReportes.setEnabled(false);
		}
		if (!Clinica.getInstance().getUsuarioLogueado().getCargoUsuario().equalsIgnoreCase("Administrador")) {
			
			//menuGerencia.setEnabled(false);
		}
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		VisualPaciente mostrarPersona = new VisualPaciente(conexion);
		mostrarPersona.setSize((int)(1381*widthRatio), (int)(900*heightRatio));
		mostrarPersona.setLocation((int)(492*widthRatio), (int)(10*heightRatio));
		mostrarPersona.setVisible(false);
        
		VisualMedico mostrarMedico = new VisualMedico();
		mostrarMedico.setSize((int)(1381*widthRatio), (int)(900*heightRatio));
		mostrarMedico.setLocation((int)(492*widthRatio), (int)(10*heightRatio));
        mostrarMedico.setVisible(false);
        
		VisualEnfermedad mostrarEnfermedad = new VisualEnfermedad();
		mostrarEnfermedad.setSize((int)(1381*widthRatio), (int)(900*heightRatio));
		mostrarEnfermedad.setLocation((int)(492*widthRatio), (int)(10*heightRatio));
		mostrarEnfermedad.setVisible(false);
		
		VisualVacuna mostrarVacuna = new VisualVacuna();
		mostrarVacuna.setSize((int)(1381*widthRatio), (int)(900*heightRatio));
		mostrarVacuna.setLocation((int)(492*widthRatio), (int)(10*heightRatio));
		mostrarVacuna.setVisible(false);
		
		VisualCita mostrarCita = new VisualCita();
		mostrarCita.setSize((int)(1381*widthRatio), (int)(900*heightRatio));
		mostrarCita.setLocation((int)(492*widthRatio), (int)(10*heightRatio));
		mostrarCita.setVisible(false);
		
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
        contentPane.add(mostrarPersona, BorderLayout.CENTER);
        contentPane.add(mostrarMedico, BorderLayout.CENTER);
        contentPane.add(mostrarEnfermedad, BorderLayout.CENTER);
        contentPane.add(mostrarVacuna, BorderLayout.CENTER);
        contentPane.add(mostrarCita, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        
        lblRegistro = new JLabel("REGISTRO");
        lblRegistro.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		switch (ind){
        			case 1:
        				panelCita.Desaparecer(tiempoAnim);
        				panelFondo2.Desaparecer(tiempoAnim);
        				mostrarCita.Desaparecer(tiempoAnim);
        				break;
        				
        			case 2:
        				panelReporte.Desaparecer(tiempoAnim);
        				panelFondo3.Desaparecer(tiempoAnim);
        				break;
        				
        			case 3:
        				panelGerencia.Desaparecer(tiempoAnim);
        				panelFondo4.Desaparecer(tiempoAnim);
        				break;
        				
        			case 5:
        				mostrarPersona.Desaparecer(tiempoAnim);
        				break;
        				
        			case 6:
        				mostrarMedico.Desaparecer(tiempoAnim);
        				break;
        				
        			case 7:
        				mostrarEnfermedad.Desaparecer(tiempoAnim);
        				break;
        				
        			case 8:
        				mostrarVacuna.Desaparecer(tiempoAnim);
        				break;
        		}
        		
        		ind = 0;
				panelRegistro.setBackground(new Color(81, 137, 252));
				panelRegistro.Aparecer(tiempoAnim);
				panelFondo.Aparecer(tiempoAnim);
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
        
        panelRegistro = new PanelSimulacionAnim();
        panelRegistro.setBorder(new CompoundBorder());
        panelRegistro.setBounds(0, 0, (int)(459*widthRatio), (int)(196*heightRatio));
        contentPane.add(panelRegistro);
        panelRegistro.setBackground(new Color(255, 255, 255, 0));
        panelRegistro.setLayout(null);

        
        lblCita = new JLabel("CITAS");
        lblCita.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		switch (ind){
        			case 0:
        				panelRegistro.Desaparecer(tiempoAnim);
        				panelFondo.Desaparecer(tiempoAnim);
        				break;
        			case 2:
        				panelReporte.Desaparecer(tiempoAnim);
        				panelFondo3.Desaparecer(tiempoAnim);
        				break;
        			case 3:
        				panelGerencia.Desaparecer(tiempoAnim);
        				panelFondo4.Desaparecer(tiempoAnim);
        				break;
        			case 5:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarPersona.Desaparecer(tiempoAnim);
        				break;
        			case 6:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarMedico.Desaparecer(tiempoAnim);
        				break;
        			case 7:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarEnfermedad.Desaparecer(tiempoAnim);
        				break;
        			case 8:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarVacuna.Desaparecer(tiempoAnim);
        				break;
        		}
        		
        		ind = 1;
				panelCita.setBackground(new Color(81, 137, 252));
				panelCita.Aparecer(tiempoAnim);
				panelFondo2.Aparecer(tiempoAnim);
				mostrarCita.Aparecer(tiempoAnim);
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
        
        panelCita = new PanelSimulacionAnim();
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
        		switch (ind){
        			case 0:
        				panelRegistro.Desaparecer(tiempoAnim);
        				panelFondo.Desaparecer(tiempoAnim);
        				break;
        			case 1:
        				panelCita.Desaparecer(tiempoAnim);
        				panelFondo2.Desaparecer(tiempoAnim);
        				mostrarCita.Desaparecer(tiempoAnim);
        				break;
        			case 3:
        				panelGerencia.Desaparecer(tiempoAnim);
        				panelFondo4.Desaparecer(tiempoAnim);
        				break;
        			case 5:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarPersona.Desaparecer(tiempoAnim);
        				break;
        			case 6:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarMedico.Desaparecer(tiempoAnim);
        				break;
        			case 7:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarEnfermedad.Desaparecer(tiempoAnim);
        				break;
        			case 8:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarVacuna.Desaparecer(tiempoAnim);
        				break;
        		}
        		
        		ind = 2;
				panelReporte.setBackground(new Color(81, 137, 252));
				panelReporte.Aparecer(tiempoAnim);
				panelFondo3.Aparecer(tiempoAnim);
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
        
        panelReporte = new PanelSimulacionAnim();
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
        		switch (ind){
        			case 0:
        				panelRegistro.Desaparecer(tiempoAnim);
        				panelFondo.Desaparecer(tiempoAnim);
        				break;
        			case 1:
        				panelCita.Desaparecer(tiempoAnim);
        				panelFondo2.Desaparecer(tiempoAnim);
        				mostrarCita.Desaparecer(tiempoAnim);
        				break;
        			case 2:
        				panelReporte.Desaparecer(tiempoAnim);
        				panelFondo3.Desaparecer(tiempoAnim);
        				break;
        			case 5:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarPersona.Desaparecer(tiempoAnim);
        				break;
        			case 6:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarMedico.Desaparecer(tiempoAnim);
        				break;
        			case 7:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarEnfermedad.Desaparecer(tiempoAnim);
        				break;
        			case 8:
        				panelRegistro.Desaparecer(tiempoAnim);
        				mostrarVacuna.Desaparecer(tiempoAnim);
        				break;
        		}
        		
        		ind = 3;
				panelGerencia.setBackground(new Color(81, 137, 252));
				panelGerencia.Aparecer(tiempoAnim);
				panelFondo4.Aparecer(tiempoAnim);
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
        
        panelGerencia = new PanelSimulacionAnim();
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
        		switch (ind){
    			case 0:
    				panelRegistro.Desaparecer(tiempoAnim);
    				panelFondo.Desaparecer(tiempoAnim);
    				break;
    			case 1:
    				panelCita.Desaparecer(tiempoAnim);
    				panelFondo2.Desaparecer(tiempoAnim);
    				mostrarCita.Desaparecer(tiempoAnim);
    				break;
    			case 2:
    				panelReporte.Desaparecer(tiempoAnim);
    				panelFondo3.Desaparecer(tiempoAnim);
    				break;
    				
    			case 3:
    				panelGerencia.Desaparecer(tiempoAnim);
    				panelFondo4.Desaparecer(tiempoAnim);
    				break;
    			case 5:
    				mostrarPersona.Desaparecer(tiempoAnim);
    				break;
    			case 6:
    				mostrarMedico.Desaparecer(tiempoAnim);
    				break;
    			case 7:
    				mostrarEnfermedad.Desaparecer(tiempoAnim);
    				break;
    			case 8:
    				mostrarVacuna.Desaparecer(tiempoAnim);
    				break;
        		}
    		
				panelCerrarSesion.setBackground(new Color(81, 137, 252));
				panelCerrarSesion.Aparecer(tiempoAnim);
        		
        		int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cerrar su sesión?", "Cerrar Sesión", JOptionPane.OK_CANCEL_OPTION);
				
        		if (Option == JOptionPane.OK_OPTION) {
					VentanaLogin ventanaLogin = new VentanaLogin();
					dispose();
					ventanaLogin.setVisible(true);
				}
        		
        		panelCerrarSesion.Desaparecer(tiempoAnim);
        		ind = 4;
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
        
        panelCerrarSesion = new PanelSimulacionAnim();
        panelCerrarSesion.setBorder(new CompoundBorder());

        panelCerrarSesion.setBounds(0, (int)(797*heightRatio), (int)(459*widthRatio), (int)(196*heightRatio));
        panelCerrarSesion.setBackground(new Color(255, 255, 255, 0));
        contentPane.add(panelCerrarSesion);
        panelCerrarSesion.setLayout(null);
        
        KGradientPanel gradientPanel = new KGradientPanel();
        gradientPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        gradientPanel.setOpaque(false);
        gradientPanel.setkEndColor(new Color(255, 255, 255));
        gradientPanel.kEndColor = new Color(51, 255, 153);
        gradientPanel.kStartColor = new Color(19, 58, 113);
        gradientPanel.setkStartColor(new Color(102, 153, 255));
        gradientPanel.setBounds(0, 0, (int)(459*widthRatio), (int)(993*heightRatio));
        contentPane.add(gradientPanel);
        gradientPanel.setLayout(null);
        
        panelFondo = new PanelSimulacionAnim();
        panelFondo.setBackground(new Color(255, 255, 255));
        panelFondo.setBounds((int)(458*widthRatio), 0, (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo);
        panelFondo.setLayout(null);
        
        roundedPanelPersona = new RoundedPanel();
        roundedPanelPersona.setBorder(new CompoundBorder());
        roundedPanelPersona.setLayout(null);
        roundedPanelPersona.setRoundTopRight(35);
        roundedPanelPersona.setRoundTopLeft(35);
        roundedPanelPersona.setRoundBottomRight(35);
        roundedPanelPersona.setRoundBottomLeft(35);
        roundedPanelPersona.setBackground(new Color(81, 137, 252));
        roundedPanelPersona.setBounds((int)(286*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelPersona);
        
        lblPersona = new JLabel("PERSONAS");
        lblPersona.setForeground(new Color(255, 255, 255));
        lblPersona.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(25*widthRatio)));
        lblPersona.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		ind = 5;      		
        		panelFondo.Desaparecer(tiempoAnim);
				mostrarPersona.Aparecer(tiempoAnim);
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
        roundedPanelMedico.setBounds((int)(800*widthRatio), (int)(72*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelMedico);
        
        lblMedico = new JLabel("M\u00C9DICOS");
        lblMedico.setForeground(new Color(255, 255, 255));
        lblMedico.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(25*widthRatio)));
        lblMedico.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ind = 6;      		
        		panelFondo.Desaparecer(tiempoAnim);
				mostrarMedico.Aparecer(tiempoAnim);
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
        roundedPanelEnfermedad.setBounds((int)(286*widthRatio), (int)(457*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelEnfermedad);
        
        lblEnfermedad = new JLabel("ENFERMEDADES");
        lblEnfermedad.setForeground(new Color(255, 255, 255));
        lblEnfermedad.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(25*widthRatio)));
        lblEnfermedad.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ind = 7;      		
        		panelFondo.Desaparecer(tiempoAnim);
				mostrarEnfermedad.Aparecer(tiempoAnim);
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
        roundedPanelVacuna.setBounds((int)(800*widthRatio), (int)(457*heightRatio), (int)(345*widthRatio), (int)(353*heightRatio));
        panelFondo.add(roundedPanelVacuna);
        
        lblVacuna = new JLabel("VACUNAS");
        lblVacuna.setForeground(new Color(255, 255, 255));
        lblVacuna.setFont(new Font("Yu Gothic UI", Font.BOLD, (int)(25*widthRatio)));
        lblVacuna.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ind = 8;      		
        		panelFondo.Desaparecer(tiempoAnim);
				mostrarVacuna.Aparecer(tiempoAnim);
        	}
        });
        lblVacuna.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/icons8-vaccine-64 (1).png")));
        lblVacuna.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblVacuna.setHorizontalTextPosition(SwingConstants.CENTER);
        lblVacuna.setHorizontalAlignment(SwingConstants.CENTER);
        lblVacuna.setBounds(0, 0, (int)(345*widthRatio), (int)(353*heightRatio));
        roundedPanelVacuna.add(lblVacuna);
        
        
        panelFondo2 = new PanelSimulacionAnim();
        panelFondo2.setBackground(new Color(255, 255, 255));
        panelFondo2.setBounds((int)(458*widthRatio), (int)(0*heightRatio), (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo2);
        panelFondo2.setLayout(null);
        
        panelFondo3 = new PanelSimulacionAnim();
        panelFondo3.setLayout(null);
        panelFondo3.setBackground(new Color(255, 255, 255));
        panelFondo3.setBounds((int)(458*widthRatio), (int)(0*heightRatio), (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo3);
        
        panelFondo4 = new PanelSimulacionAnim();
        panelFondo4.setLayout(null);
        panelFondo4.setBackground(new Color(255, 255, 255));
        panelFondo4.setBounds((int)(458*widthRatio), (int)(0*heightRatio), (int)(1444*widthRatio), (int)(993*heightRatio));
        contentPane.add(panelFondo4);
        
        panelFondo2.setVisible(false);
        panelFondo3.setVisible(false);
        panelFondo4.setVisible(false);
		panelRegistro.setBackground(new Color(81, 137, 252));
		panelRegistro.Aparecer(tiempoAnim);
		panelFondo.Aparecer(tiempoAnim);
        

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