package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import logico.Medico;
import logico.Paciente;

import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class GenerarReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<Paciente> pacientesEspecificasMostrar = new ArrayList<Paciente>();
	private ArrayList<Medico> medicosEspecificosMostrar = new ArrayList<Medico>();
	private static DefaultTableModel model;
	private static Object[] row;
	private JTable table;
	private JRadioButton rdbtnCiudad;
	private JRadioButton rdbtnTipoSangre;
	private JRadioButton rdbtnVigia;
	private JPanel panelCiudad;
	private JPanel panelTipoSangre;
	private JPanel panelVigilado1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenerarReporte dialog = new GenerarReporte(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Create the dialog.
	 */
	public GenerarReporte(ArrayList<Paciente> personasAMostrar, ArrayList<Medico> medicosAMostrar) {
		
		Object[] titulo1 = {"Nombre", "Cedula", "Enfermedad"};
		Object[] titulo2 = {"Nombre", "Cedula", "Tipo de Sangre"};
		Object[] titulo3 = {"Nombre", "Cedula", "Ciudad"};
		
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 2) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarReporte.class.getResource("/Imagenes/report (1).png")));
		setTitle("Reportes de Salud");
		setResizable(false);
		setBounds(100, 100, 965, 662);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0x6BAA75);
		Color rayita2 = new Color(0x69747C);
		int alpha = 60;
		int alpha2 = 120;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Documento exitosamente impreso.", "Impresora", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		panelVigilado1 = new JPanel();
		panelVigilado1.setVisible(false);
		
		panelTipoSangre = new JPanel();
		panelTipoSangre.setBackground(Color.WHITE);
		panelTipoSangre.setVisible(false);
		
		panelCiudad = new JPanel();
		panelCiudad.setVisible(false);
		panelCiudad.setBackground(Color.WHITE);
		panelCiudad.setBounds(48, 219, 348, 103);
		contentPanel.add(panelCiudad);
		panelCiudad.setLayout(null);
		
		JTextPane txtpnCreaUnReporte = new JTextPane();
		txtpnCreaUnReporte.setText("Crea un reporte con una lista de personas de una ciudad en espec\u00EDfico.");
		txtpnCreaUnReporte.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtpnCreaUnReporte.setBounds(12, 13, 324, 95);
		panelCiudad.add(txtpnCreaUnReporte);
		panelTipoSangre.setBounds(48, 219, 348, 104);
		contentPanel.add(panelTipoSangre);
		panelTipoSangre.setLayout(null);
		
		JTextPane txtpnMuestraUnReporte = new JTextPane();
		txtpnMuestraUnReporte.setText("Muestra un reporte de los pacientes con el tipo de sangre que se seleccione.");
		txtpnMuestraUnReporte.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtpnMuestraUnReporte.setBounds(12, 13, 324, 95);
		panelTipoSangre.add(txtpnMuestraUnReporte);
		panelVigilado1.setBackground(new Color(255, 255, 255));
		panelVigilado1.setBounds(48, 219, 348, 104);
		contentPanel.add(panelVigilado1);
		panelVigilado1.setLayout(null);
		
		JTextPane txtpnDsdadadadsa = new JTextPane();
		txtpnDsdadadadsa.setEditable(false);
		txtpnDsdadadadsa.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		txtpnDsdadadadsa.setText("Muestra una recopilaci\u00F3n de los pacientes que padezcan de una enfermedad actualmente en vigilancia.");
		txtpnDsdadadadsa.setBounds(12, 13, 324, 95);
		panelVigilado1.add(txtpnDsdadadadsa);
		btnImprimir.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		btnImprimir.setEnabled(false);
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setBounds(722, 538, 97, 25);
		contentPanel.add(btnImprimir);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setEnabled(false);
		btnCargar.setBackground(new Color(255, 255, 255));
		btnCargar.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		btnCargar.setBounds(831, 538, 97, 25);
		contentPanel.add(btnCargar);
		
		rdbtnCiudad = new JRadioButton("Ciudad");
		rdbtnCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnVigia.setSelected(false);
				rdbtnTipoSangre.setSelected(false);
				panelVigilado1.setVisible(false);
				panelTipoSangre.setVisible(false);
				panelCiudad.setVisible(true);
			}
		});
		rdbtnCiudad.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		rdbtnCiudad.setBackground(new Color(255, 255, 255));
		rdbtnCiudad.setBounds(333, 163, 127, 25);
		contentPanel.add(rdbtnCiudad);
		
		rdbtnTipoSangre = new JRadioButton("Tipo de sangre");
		rdbtnTipoSangre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnVigia.setSelected(false);
				rdbtnCiudad.setSelected(false);
				panelVigilado1.setVisible(false);
				panelTipoSangre.setVisible(true);
				panelCiudad.setVisible(false);
			}
		});
		rdbtnTipoSangre.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		rdbtnTipoSangre.setBackground(new Color(255, 255, 255));
		rdbtnTipoSangre.setBounds(179, 163, 127, 25);
		contentPanel.add(rdbtnTipoSangre);
		
		rdbtnVigia = new JRadioButton("P. Vigilados");
		rdbtnVigia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCiudad.setSelected(false);
				rdbtnTipoSangre.setSelected(false);
				btnCargar.setEnabled(true);
				btnImprimir.setEnabled(true);
				panelVigilado1.setVisible(true);
				panelTipoSangre.setVisible(false);
				panelCiudad.setVisible(false);
			}
		});
		rdbtnVigia.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		rdbtnVigia.setBackground(new Color(255, 255, 255));
		rdbtnVigia.setBounds(48, 163, 127, 25);
		contentPanel.add(rdbtnVigia);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(849, 163, 110, 110);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(GenerarReporte.class.getResource("/Imagenes/vecteezy2.png")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(new Color(220, 220, 220));
		panel_2.setBounds(453, 219, 475, 312);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBounds(12, 13, 451, 284);
		panel_2.add(panel_3);
		panel_3.setBackground(new Color(255, 250, 250));
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 239, 213, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 98));
		lblNewLabel.setIcon(new ImageIcon(GenerarReporte.class.getResource("/Imagenes/PngItem_2486361.png")));
		lblNewLabel.setBounds(183, 0, 776, 563);
		contentPanel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(0, 36, 959, 10);
		contentPanel.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 13, 959, 10);
		contentPanel.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Reportes  -  HOMS");
		lblNewLabel_2.setFont(new Font("STSong", Font.ITALIC, 41));
		lblNewLabel_2.setBounds(76, 13, 320, 121);
		contentPanel.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(176, 196, 222));
		panel_4.setBounds(113, 109, 959, 10);
		contentPanel.add(panel_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
