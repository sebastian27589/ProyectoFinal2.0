package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import exception.ValidarCampo;
import logico.Clinica;
import logico.Enfermedad;
import logico.Vacuna;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private static Object[] row;
	private ArrayList<Enfermedad> enfermedadesAAgregar = new ArrayList<Enfermedad>();
	private String nombre, nombreEnfermedad, lab;
	private Vacuna vacuna = null;
	private int selected;
	private JTextField txtNombre;
	private JTextField txtLab;
	private JTextField txtEnfermedad;
	private JTextField txtCodigo;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JButton btnAnadir;
	private JPanel panel_2;
	private JLabel lblIcono;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblEnfermedades;
	private JPanel panel_6;
	private JTable table;
	private JButton btnQuitar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna(Vacuna vacunaAModificar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegVacuna.class.getResource("/Imagenes/2830290011667485045-16.png")));
		
		Color rayita1 = new Color(0x9BBFD8);
		Color rayita2 = new Color(0x69747C);
		Color rayita3 = new Color(0x9BBFD8);
		int alpha = 100;
		int alpha2 = 120;
		int alpha3 = 150;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		rayita3 = new Color(rayita3.getRed(), rayita3.getGreen(), rayita3.getBlue(), alpha3);
		
		setTitle("Gesti\u00F3n de Vacunas");
		
		vacuna = vacunaAModificar;
		
		if (vacuna != null) {
			setTitle("Modificar Vacuna");
		}
		
		
		setResizable(false);
		setBounds(100, 100, 954, 562);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(345, 100, 300, 562);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(RegVacuna.class.getResource("/Imagenes/pngegg (12).png")));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 139, 139));
		panel_3.setBounds(0, 149, 948, 24);
		contentPanel.add(panel_3);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 948, 117);
		contentPanel.add(panel);
		
		JLabel label = new JLabel(" Nombre:");
		label.setOpaque(true);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		label.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		label.setBackground(Color.WHITE);
		label.setBounds(70, 49, 70, 22);
		panel.add(label);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtNombre.setColumns(10);
		txtNombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombre.setBounds(152, 49, 233, 22);
		panel.add(txtNombre);
		
		JLabel lblLaboratorio = new JLabel(" Laboratorio:");
		lblLaboratorio.setOpaque(true);
		lblLaboratorio.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblLaboratorio.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblLaboratorio.setBackground(Color.WHITE);
		lblLaboratorio.setBounds(450, 49, 91, 22);
		panel.add(lblLaboratorio);
		
		txtLab = new JTextField();
		txtLab.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtLab.setColumns(10);
		txtLab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtLab.setBounds(548, 49, 233, 22);
		panel.add(txtLab);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(rayita1);
		panel_1.setBounds(0, 161, 948, 217);
		contentPanel.add(panel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setText("VC-"+Clinica.getGeneradorCodeVacuna());
		txtCodigo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtCodigo.setColumns(10);
		txtCodigo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtCodigo.setBounds(152, 108, 114, 22);
		panel_1.add(txtCodigo);
		
		panel_5 = new JPanel();
		panel_5.setBounds(619, 25, 250, 35);
		panel_1.add(panel_5);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_5.setBackground(new Color(255, 250, 240));
		panel_5.setLayout(null);
		
		lblEnfermedades = new JLabel("ENFERMEDADES");
		lblEnfermedades.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		lblEnfermedades.setBounds(44, 10, 161, 16);
		panel_5.add(lblEnfermedades);
		
		JLabel lblEnfermedad = new JLabel(" Enfermedad:");
		lblEnfermedad.setOpaque(true);
		lblEnfermedad.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblEnfermedad.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblEnfermedad.setBackground(Color.WHITE);
		lblEnfermedad.setBounds(49, 73, 91, 22);
		panel_1.add(lblEnfermedad);
		
		txtEnfermedad = new JTextField();
		txtEnfermedad.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtEnfermedad.setColumns(10);
		txtEnfermedad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtEnfermedad.setBounds(152, 73, 233, 22);
		panel_1.add(txtEnfermedad);
		
		JLabel lblCdigo = new JLabel(" C\u00F3digo:");
		lblCdigo.setOpaque(true);
		lblCdigo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblCdigo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblCdigo.setBackground(Color.WHITE);
		lblCdigo.setBounds(76, 108, 64, 22);
		panel_1.add(lblCdigo);
		
		btnAnadir = new JButton("A\u00D1ADIR");
		btnAnadir.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		btnAnadir.setBackground(new Color(255, 255, 255));
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nombreEnfermedad = txtEnfermedad.getText();
				Enfermedad enfermedad = Clinica.getInstance().buscarEnfermedadByNombre(nombreEnfermedad);
				
				if (enfermedad != null) {
					enfermedadesAAgregar.add(enfermedad);
					Object[] datosEnfermedad = {enfermedad.getNombre(), enfermedad.getTipo()};
					model.addRow(datosEnfermedad);
				} else {
					JOptionPane.showMessageDialog(null, "Esta enfermedad no está registrada", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAnadir.setActionCommand("OK");
		btnAnadir.setBounds(300, 107, 85, 25);
		panel_1.add(btnAnadir);
		
		panel_2 = new JPanel();
		panel_2.setBackground(rayita3);
		panel_2.setBounds(619, 28, 250, 164);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(12, 30, 226, 121);
		panel_2.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Tipo");
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnQuitar = new JButton("x");
		btnQuitar.setBounds(881, 167, 47, 25);
		panel_1.add(btnQuitar);
		btnQuitar.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
		btnQuitar.setBackground(new Color(255, 255, 255));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = table.getSelectedRow();
				if (selected != -1) {
					enfermedadesAAgregar.remove(selected);
					model.removeRow(selected);
				}
			}
		});
		btnQuitar.setActionCommand("OK");
		
		lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(RegVacuna.class.getResource("/Imagenes/11423721931667542571-48.png")));
		lblIcono.setBounds(23, 408, 54, 52);
		contentPanel.add(lblIcono);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 139, 139));
		panel_4.setBounds(0, 366, 948, 24);
		contentPanel.add(panel_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setBackground(new Color(255, 255, 255));
				btnRegistrar.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
				if (vacuna != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (vacuna == null) 
							{
								
								nombre = txtNombre.getText();
								lab = txtLab.getText();
								
								if (nombre.isEmpty() || lab.isEmpty()) {
									throw new ValidarCampo("Debe llenar los campos obligatorios.");
								}
									
								if (enfermedadesAAgregar.isEmpty()) {
									throw new ValidarCampo("La vacuna debe tratar al menos una enfermedad.");
								}
										
								Vacuna nuevaVacuna = new Vacuna(txtCodigo.getText(), txtNombre.getText(), txtLab.getText());
								nuevaVacuna.getEnfermedadesQueTrata().addAll(enfermedadesAAgregar);
								Clinica.getInstance().insertarVacuna(nuevaVacuna);
								JOptionPane.showMessageDialog(null, "Vacuna registrada.", "Registro de Vacunas", JOptionPane.INFORMATION_MESSAGE);
								clean();
							} else {
								vacuna.setNombre(txtNombre.getText());
								vacuna.setLaboratorio(txtLab.getText());
								//Clinica.getInstance().actualizarVacuna();
								dispose();
							}
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtNombre.grabFocus();
						}

					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(new Color(255, 255, 255));
				btnCancelar.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	public void clean() {
		txtCodigo.setText("VC-"+Clinica.getGeneradorCodeVacuna());
		txtLab.setText("");
		txtEnfermedad.setText("");
		txtNombre.setText("");
		model.setRowCount(0);
		enfermedadesAAgregar.clear();
		
	}
}
