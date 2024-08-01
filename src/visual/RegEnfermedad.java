package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import exception.ValidarCampo;
import logico.Clinica;
import logico.Enfermedad;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class RegEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String nombre, sintomas;
	private Enfermedad enfermedad = null;
	private JTextField txtNombre;
	private JTextField txtSintoma;
	private JComboBox cbxTipo;
	private JSpinner spnMortalidad;
	private JLabel lblSiemprePreocupndonos;
	private JCheckBox chbxVigilancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Prueba 1 de 2

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad(Enfermedad enfermedadAModificar, boolean mod) {
		
		setTitle("Gesti\u00F3n de enfermedades");
		
		enfermedad = enfermedadAModificar;
		
		if(enfermedad != null) {
			if (mod) {
				setTitle("Modificar Enfermedad");
				
			} else {
				setTitle("Visualizar Enfermedad");
			}
		}
		
		
		setResizable(false);
		setBounds(100, 100, 763, 438);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0xAA6B6B);
		Color rayita2 = new Color(0x69747C);
		Color rayita3 = new Color(0x69747C);
		int alpha = 100;
		int alpha2 = 120;
		int alpha3 = 200;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		rayita3 = new Color(rayita3.getRed(), rayita3.getGreen(), rayita3.getBlue(), alpha3);
		contentPanel.setBackground(new Color(255, 250, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(rayita1);
		panel.setBounds(0, 13, 757, 70);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Nombre:");
		lblNewLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 23, 70, 25);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtNombre.setBounds(114, 23, 233, 25);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("  Tipo:");
		lblTipo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblTipo.setBackground(new Color(255, 255, 255));
		lblTipo.setOpaque(true);
		lblTipo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblTipo.setBounds(398, 23, 50, 25);
		panel.add(lblTipo);
		
		cbxTipo = new JComboBox();
		cbxTipo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cbxTipo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Alergia", "Enf. Autoinmune", "Enf. Cardiovascular", "Enf. de la Mujer", "Enf. de la Sangre", "Enf. Mentales", "Enf. infecciosa"}));
		cbxTipo.setBounds(493, 23, 205, 25);
		panel.add(cbxTipo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(rayita2);
		panel_1.setBounds(0, 96, 757, 70);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSntomas = new JLabel(" S\u00EDntomas:");
		lblSntomas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblSntomas.setOpaque(true);
		lblSntomas.setBackground(new Color(255, 255, 255));
		lblSntomas.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblSntomas.setBounds(38, 23, 70, 22);
		panel_1.add(lblSntomas);
		
		txtSintoma = new JTextField();
		txtSintoma.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtSintoma.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtSintoma.setColumns(10);
		txtSintoma.setBounds(114, 10, 233, 50);
		panel_1.add(txtSintoma);
		
		JLabel lblMortalidad = new JLabel(" Mortalidad:");
		lblMortalidad.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		lblMortalidad.setBackground(new Color(255, 255, 255));
		lblMortalidad.setOpaque(true);
		lblMortalidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblMortalidad.setBounds(398, 10, 80, 22);
		panel_1.add(lblMortalidad);
		
		spnMortalidad = new JSpinner();
		spnMortalidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		spnMortalidad.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spnMortalidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		spnMortalidad.setBounds(493, 10, 41, 22);
		panel_1.add(spnMortalidad);
		
		chbxVigilancia = new JCheckBox("Bajo Vigilancia");
		chbxVigilancia.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		chbxVigilancia.setBackground(new Color(255, 255, 255));
		chbxVigilancia.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		chbxVigilancia.setBounds(398, 38, 136, 22);
		panel_1.add(chbxVigilancia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(rayita1);
		panel_2.setBounds(0, 214, 757, 126);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.setBackground(rayita3);
		panel_6.setBounds(287, 13, 432, 100);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		panel_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		panel_7.setBounds(40, 13, 261, 74);
		panel_7.setBackground(new Color(255, 255, 255));
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("National Covert Research Center");
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		lblNewLabel_1.setBounds(8, 8, 249, 16);
		panel_7.add(lblNewLabel_1);
		
		lblSiemprePreocupndonos = new JLabel("<html><div style='text-align: center; vertical-align: middle;'>Siempre preocupándonos<br>por tu salud :)</div></html>");
		lblSiemprePreocupndonos.setToolTipText("");
		lblSiemprePreocupndonos.setLabelFor(panel_7);
		lblSiemprePreocupndonos.setHorizontalAlignment(SwingConstants.LEFT);
		lblSiemprePreocupndonos.setBounds(40, 20, 210, 53);
		panel_7.add(lblSiemprePreocupndonos);
		lblSiemprePreocupndonos.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(322, 0, 100, 100);
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(RegEnfermedad.class.getResource("/Imagenes/enf_registrar_logo.png")));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(RegEnfermedad.class.getResource("/Imagenes/enf_registrar_img.png")));
		lblNewLabel_3.setBounds(76, 13, 100, 100);
		panel_2.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 128, 128));
		panel_3.setBounds(0, 172, 757, 5);
		contentPanel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(220, 20, 60));
		panel_4.setBounds(0, 187, 757, 5);
		contentPanel.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(178, 34, 34));
		panel_5.setBounds(0, 202, 757, 5);
		contentPanel.add(panel_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if (enfermedad != null) {
					btnRegistrar.setText("Modificar");
					if(mod == false) {
						btnRegistrar.setEnabled(false);
					}
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
								// Si es una nueva enfermedad, se hace el proceso normal.
								if (enfermedad == null) {
								boolean vigilancia;
								if(chbxVigilancia.isSelected()) {
									vigilancia = true;
								}else {
									vigilancia = false;
								}
								nombre = txtNombre.getText();
								sintomas = txtSintoma.getText();
								
								// Validar que la nueva enfermedad no esté vacía
								if (nombre.isEmpty() || sintomas.isEmpty()) {
									throw new ValidarCampo("Debe llenar los campos obligatorios.");
								}
									Enfermedad nuevaEnfermedad = new Enfermedad(txtNombre.getText(), cbxTipo.getSelectedItem().toString(), txtSintoma.getText(),new Integer(spnMortalidad.getValue().toString()), vigilancia);
									Clinica.getInstance().insertarEnfermedad(nuevaEnfermedad);
									JOptionPane.showMessageDialog(null, "Enfermedad registrada con éxito.", "Registro de enfermedad", JOptionPane.INFORMATION_MESSAGE);
									clean();
								//} else
									//JOptionPane.showMessageDialog(null, "Debe llenar los campos.", "Error", JOptionPane.ERROR_MESSAGE);
							
							// Si ya es una enfermedad seleccionada, se modifica.
							} else if (enfermedad != null && mod == true){
								enfermedad.setNombre(txtNombre.getText());
								enfermedad.setSintomas(txtSintoma.getText());
								enfermedad.setTipo(cbxTipo.getSelectedItem().toString());
								enfermedad.setIndPeligro(new Integer(spnMortalidad.getValue().toString()));
								if(chbxVigilancia.isSelected()) {
									enfermedad.setVigilada(true);
								}else {
									enfermedad.setVigilada(false);
								}
								Clinica.getInstance().actualizarEnfermedad(enfermedad);
								JOptionPane.showMessageDialog(null, "Enfermedad modificada con éxito.", "Modificación de Enfermedad", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
								
						// Para el validador de campo
						} catch (ValidarCampo ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
							txtNombre.grabFocus();
						} 
						
						// Para que no se muestren todos esos errores en la consola 
//						catch (Exception e2) {
//							e2.printStackTrace();
//							txtNombre.grabFocus();
//						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			
			if(enfermedad != null) {
				cargarEnfermedad(enfermedad);
				txtNombre.setEditable(false);
				if(mod == false) {
					cbxTipo.setEnabled(false);
					txtSintoma.setEditable(false);
					spnMortalidad.setEnabled(false);
					chbxVigilancia.setEnabled(false);
				}
			}
		}
	}

	public void clean() {
		
		txtNombre.setText("");
		txtSintoma.setText("");
		cbxTipo.setSelectedIndex(0);
		spnMortalidad.setValue(new Integer(1));
	}
	
	public void cargarEnfermedad(Enfermedad enfermedad) {
		txtNombre.setText(enfermedad.getNombre());
		txtSintoma.setText(enfermedad.getSintomas());
		boolean encontrado = false;
		int ind = 0;
		while(ind <= cbxTipo.getItemCount() && encontrado == false) {
			if(cbxTipo.getItemAt(ind).toString().equalsIgnoreCase(enfermedad.getTipo())) {
				cbxTipo.setSelectedIndex(ind);
				encontrado = true;
			}
			ind++;
		}
		spnMortalidad.setValue(enfermedad.getIndPeligro());
		if(enfermedad.isVigilada() == true) {
			chbxVigilancia.setSelected(true);
		}else {
			chbxVigilancia.setSelected(false);
		}
	}
}
