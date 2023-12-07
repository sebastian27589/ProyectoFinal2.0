package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import exception.ValidarCampo;
import logico.Clinica;
import logico.Medico;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String nombre, cedula, telefono;
	private JTextField txtCodeMedico;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JDateChooser dateChooserNacim;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JTextArea txtareaDireccion;
	private String selected = null;
	private Date fechaNacimiento;
	private static Medico medico = null;
	private char sexoMedico;
	private JTable tableEspecialidades;
	private static DefaultTableModel model;
	private static Object[] row;
	private static ArrayList<String> especialidadesElegidas = new ArrayList<String>();
	private JButton btnEliminar;
	private JButton btnRegistrar;
	private JButton cancelButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegMedico dialog = new RegMedico(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegMedico(Medico medicoAModificar, boolean visualizar) {
		
		medico = medicoAModificar;
		
		if (medico != null) {
			
			especialidadesElegidas.clear();
			especialidadesElegidas.addAll(medico.getEspecialidades());
		}
		
		setTitle("Registrar Médico");
		
		if (medico != null) {
			
			setTitle("Modificar Médico");
		}
		
		setResizable(false);
		setBounds(100, 100, 800, 395);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(218, 221, 216));
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelContenedor1 = new JPanel();
			panelContenedor1.setOpaque(false);
			panelContenedor1.setBackground(new Color(218, 221, 216));
			panelContenedor1.setBounds(0, 11, 794, 116);
			contentPanel.add(panelContenedor1);
			panelContenedor1.setLayout(null);
			
			JLabel lblCodemedico = new JLabel("C\u00F3digo:");
			lblCodemedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCodemedico.setOpaque(true);
			lblCodemedico.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodemedico.setForeground(new Color(0, 0, 0));
			lblCodemedico.setBackground(new Color(255, 255, 255));
			lblCodemedico.setBounds(23, 21, 72, 22);
			panelContenedor1.add(lblCodemedico);
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setOpaque(true);
				lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombre.setForeground(Color.BLACK);
				lblNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				lblNombre.setBackground(Color.WHITE);
				lblNombre.setBounds(23, 54, 72, 22);
				panelContenedor1.add(lblNombre);
			}
			
			txtCodeMedico = new JTextField();
			txtCodeMedico.setEditable(false);
			txtCodeMedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCodeMedico.setBounds(105, 21, 62, 22);
			panelContenedor1.add(txtCodeMedico);
			txtCodeMedico.setColumns(10);
			txtCodeMedico.setText("M-"+Clinica.getInstance().getGeneradorCodeMedico());
			
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtNombre.setColumns(10);
			txtNombre.setBounds(105, 54, 176, 22);
			panelContenedor1.add(txtNombre);
			
			JLabel lblCédula = new JLabel("C\u00E9dula:");
			lblCédula.setOpaque(true);
			lblCédula.setHorizontalAlignment(SwingConstants.CENTER);
			lblCédula.setForeground(Color.BLACK);
			lblCédula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblCédula.setBackground(Color.WHITE);
			lblCédula.setBounds(309, 21, 72, 22);
			panelContenedor1.add(lblCédula);
			
			txtCedula = new JTextField();
			txtCedula.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCedula.setColumns(10);
			txtCedula.setBounds(391, 21, 176, 22);
			panelContenedor1.add(txtCedula);
			
			JLabel lblFechaNacim = new JLabel("F. de nac:");
			lblFechaNacim.setOpaque(true);
			lblFechaNacim.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaNacim.setForeground(Color.BLACK);
			lblFechaNacim.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblFechaNacim.setBackground(Color.WHITE);
			lblFechaNacim.setBounds(309, 54, 72, 22);
			panelContenedor1.add(lblFechaNacim);
			
			dateChooserNacim = new JDateChooser();
			dateChooserNacim.setBounds(391, 54, 176, 22);
			panelContenedor1.add(dateChooserNacim);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(23, 87, 72, 22);
			panelContenedor1.add(lblSexo);
			lblSexo.setOpaque(true);
			lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSexo.setForeground(Color.BLACK);
			lblSexo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblSexo.setBackground(Color.WHITE);
			
			rdbtnMasculino = new JRadioButton("M");
			rdbtnMasculino.setSelected(true);
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnFemenino.setSelected(false);
				}
			});
			rdbtnMasculino.setBounds(105, 87, 38, 22);
			panelContenedor1.add(rdbtnMasculino);
			rdbtnMasculino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			rdbtnFemenino = new JRadioButton("F");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rdbtnMasculino.setSelected(false);
				}
			});
			rdbtnFemenino.setBounds(160, 87, 33, 22);
			panelContenedor1.add(rdbtnFemenino);
			rdbtnFemenino.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(309, 87, 72, 22);
			panelContenedor1.add(lblTelefono);
			lblTelefono.setOpaque(true);
			lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			lblTelefono.setForeground(Color.BLACK);
			lblTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTelefono.setBackground(Color.WHITE);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(391, 87, 176, 22);
			panelContenedor1.add(txtTelefono);
			txtTelefono.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtTelefono.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Imagen de relleno");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(610, 59, 145, 14);
			panelContenedor1.add(lblNewLabel);
		}
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAzul.setBackground(new Color(173, 216, 230));
		panelAzul.setBounds(0, 21, 794, 112);
		contentPanel.add(panelAzul);
		
		JPanel panelGris = new JPanel();
		panelGris.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGris.setBackground(new Color(105, 116, 124, 120));
		panelGris.setBounds(0, 145, 415, 121);
		contentPanel.add(panelGris);
		panelGris.setLayout(null);
		
		txtareaDireccion = new JTextArea();
		txtareaDireccion.setBounds(105, 34, 285, 54);
		panelGris.add(txtareaDireccion);
		txtareaDireccion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaDireccion.setLineWrap(true);
		txtareaDireccion.setWrapStyleWord(true);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(23, 49, 72, 22);
		panelGris.add(lblDireccion);
		lblDireccion.setOpaque(true);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblDireccion.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(425, 145, 369, 121);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarEsp = new JLabel("Especialidad:");
		lblBuscarEsp.setOpaque(true);
		lblBuscarEsp.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarEsp.setForeground(Color.BLACK);
		lblBuscarEsp.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblBuscarEsp.setBackground(Color.WHITE);
		lblBuscarEsp.setBounds(10, 11, 82, 22);
		panel.add(lblBuscarEsp);
		
		JComboBox cbxElegirEspecialidad = new JComboBox();
		cbxElegirEspecialidad.setModel(new DefaultComboBoxModel(new String[] {"<Elegir>", "Medicina Interna", "Cardiolog\u00EDa", "Dermatolog\u00EDa", "Gastroenterolog\u00EDa", "Neurolog\u00EDa", "Oftalmolog\u00EDa", "Otorrinolaringolog\u00EDa", "Endocrinolog\u00EDa", "Hematolog\u00EDa", "Nefrolog\u00EDa", "Urolog\u00EDa", "Ginecolog\u00EDa", "Pediatr\u00EDa", "Psiquiatr\u00EDa", "Ortopedia", "Traumatolog\u00EDa", "Cirug\u00EDa General", "Radiolog\u00EDa", "Oncolog\u00EDa", "Reumatolog\u00EDa"}));
		cbxElegirEspecialidad.setSelectedIndex(0);
		cbxElegirEspecialidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		cbxElegirEspecialidad.setBounds(102, 11, 146, 22);
		AutoCompleteDecorator.decorate(cbxElegirEspecialidad);
		panel.add(cbxElegirEspecialidad);
		
		JButton btnAnadirEspecialidad = new JButton("A\u00F1adir");
		btnAnadirEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (verificarAdicionEspecialidad(cbxElegirEspecialidad.getSelectedItem().toString())) {
			
					especialidadesElegidas.add(cbxElegirEspecialidad.getSelectedItem().toString());
					loadEspecialidades();
				}
				
			}
		});
		btnAnadirEspecialidad.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		btnAnadirEspecialidad.setBounds(254, 11, 78, 22);
		panel.add(btnAnadirEspecialidad);
		
		JPanel panelTablaEsps = new JPanel();
		panelTablaEsps.setBounds(10, 44, 238, 66);
		panel.add(panelTablaEsps);
		panelTablaEsps.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTablaEsps.add(scrollPane, BorderLayout.CENTER);
		
		Object[] header = {"Especialidades del Médico"};
		
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int column) {       
			       return false;
			}
		};
	
		model.setColumnIdentifiers(header);
		tableEspecialidades = new JTable(model);
		tableEspecialidades.getTableHeader().setReorderingAllowed(false);
		tableEspecialidades.getTableHeader().setResizingAllowed(false);
		tableEspecialidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowIndex = tableEspecialidades.getSelectedRow();
				
				if (rowIndex >= 0) {
					
					selected = buscarEspecialidadTabla(tableEspecialidades.getValueAt(rowIndex, 0).toString());
					System.out.println(selected);
					btnEliminar.setEnabled(true);
				}
				
			}
		});
		tableEspecialidades.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		scrollPane.setViewportView(tableEspecialidades);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				especialidadesElegidas.remove(selected);
				loadEspecialidades();
				btnEliminar.setEnabled(false);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		btnEliminar.setBounds(254, 88, 78, 22);
		panel.add(btnEliminar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (medico == null) {
								
								if (rdbtnMasculino.isSelected()) {
									
									sexoMedico = 'M';
								}
								else {
									
									sexoMedico = 'F';
								}
								
								nombre = txtNombre.getText();
								cedula = txtCedula.getText();
								telefono = txtTelefono.getText();
								fechaNacimiento = dateChooserNacim.getDate();
								
								if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()) {
									throw new ValidarCampo("Debe llenar los campos obligatorios.");
								}
								
								if (fechaNacimiento == null) {
									throw new ValidarCampo("No ha seleccionado una fecha de nacimiento.");
								}
								
								if (especialidadesElegidas.isEmpty()) {
									throw new ValidarCampo("El médico debe tener al menos 1 especialidad.");
								}
								
								if (!rdbtnMasculino.isSelected() && !rdbtnFemenino.isSelected()) {
									throw new ValidarCampo("Debe seleccionar un sexo.");
								}
								
								Medico medicoNuevo = new Medico(txtCedula.getText(), txtNombre.getText(), dateChooserNacim.getDate(), sexoMedico,
									                            txtTelefono.getText(), txtareaDireccion.getText(), txtCodeMedico.getText());
								medicoNuevo.getEspecialidades().addAll(especialidadesElegidas);
								
								Clinica.getInstance().insertarMedico(medicoNuevo);
								JOptionPane.showMessageDialog(null, "Registrado con éxito", "Registrar Médico", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
							else {
						
								if (rdbtnMasculino.isSelected()) {
									
									sexoMedico = 'M';
								}
								else {
									
									sexoMedico = 'F';
								}
								
								medico.setTelefono(txtTelefono.getText());
								medico.setDireccion(txtareaDireccion.getText());
								medico.getEspecialidades().clear();
								medico.getEspecialidades().addAll(especialidadesElegidas);
								
								Clinica.getInstance().actualizarMedico(medico);
								dispose();
							}
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtNombre.grabFocus();
						}
						
					}
				});
				btnRegistrar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (medico != null) {
			
			txtNombre.setEditable(false);
			rdbtnMasculino.setEnabled(false);
			rdbtnFemenino.setEnabled(false);
			txtCedula.setEditable(false);
			dateChooserNacim.setEnabled(false);
			
			if (visualizar) {
				
				setTitle("Datos del Médico");
				
				txtTelefono.setEditable(false);
				txtareaDireccion.setEditable(false);
				cbxElegirEspecialidad.setEnabled(false);
				btnAnadirEspecialidad.setEnabled(false);
				tableEspecialidades.setEnabled(false);;
				scrollPane.setEnabled(true);
			}
			
		}
		
		loadMedicoAModificar();
		loadEspecialidades();
		
		if (visualizar) {
			
			btnRegistrar.setVisible(false);
			cancelButton.setText("Cerrar");
		}
		
	}
	
	private void loadMedicoAModificar() {
		
		if (medico != null) {
			
			txtCodeMedico.setText(medico.getCodeMedico());
			txtNombre.setText(medico.getNombre());
			if (medico.getSexo() == 'M') {
				
				rdbtnMasculino.setSelected(true);
			}
			else {
				
				rdbtnFemenino.setSelected(true);
			}
			
			txtCedula.setText(medico.getCedula());
			dateChooserNacim.setDate(medico.getFechaDeNacimiento());
			txtTelefono.setText(medico.getTelefono());
			txtareaDireccion.setText(medico.getDireccion());
			loadEspecialidades();
		}
		
	}
	
	private void clean() {
		
		txtCodeMedico.setText("M-"+Clinica.getInstance().getGeneradorCodeMedico());
		txtNombre.setText("");
		rdbtnMasculino.setSelected(false);
		rdbtnFemenino.setSelected(false);
		txtCedula.setText("");
		dateChooserNacim.setCalendar(null);
		txtTelefono.setText("");
		txtareaDireccion.setText("");
		especialidadesElegidas.clear();
		loadEspecialidades();
	}
	
	public static void loadEspecialidades() {

		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (String especialidad : especialidadesElegidas) {
			row[0] = especialidad; 
			model.addRow(row);
		}
	
	}
	
	public boolean verificarAdicionEspecialidad(String especialidadAVerificar) {
		
		boolean permitirElegir = true;
		
		if (especialidadAVerificar.equalsIgnoreCase("<Elegir>")) {
			
			permitirElegir = false;
		}
		else {
			
			for (String especialidad : especialidadesElegidas) {
				
				if (especialidad.equalsIgnoreCase(especialidadAVerificar)) {
					
					permitirElegir = false;
				}
			}
		}

		
		return permitirElegir;
	}
	
	public String buscarEspecialidadTabla(String especialidadABuscar) {
		
		String especialidad = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < especialidadesElegidas.size()) {
			
			if (especialidadesElegidas.get(index).equalsIgnoreCase(especialidadABuscar)) {
				
				encontrado = true;
				especialidad = especialidadesElegidas.get(index);
			}
			
			
			index++;
		}
	
		return especialidad;
	}
}
