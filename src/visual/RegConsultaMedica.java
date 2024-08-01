package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import logico.Clinica;
import logico.ConsultaMedica;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import exception.ValidarCampo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegConsultaMedica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String sintoma, diagnostico;
	private JTextField txtCodeConsulta;
	private JTextField txtCodeMedico;
	private JTextField txtCodePaciente;
	private JTable tableAnalisis;
	private static DefaultTableModel model;
	private static Object[] row;
	private Vacuna selected = null;
	private static ArrayList<String> analisis;
	private Paciente paciente = null;
	private Medico medico = null;
	private ConsultaMedica consMed = null;
	private JDateChooser dateChooserConsulta;
	private JButton btnRealizar;
	private JButton cancelButton;
	private JTextArea txtareaSintomas;
	private JTextArea txtareaDiagnostico;
	private JComboBox cbxEnfermedad;
	private JTextField txtBuscarAnalisis;
	private JMenuItem menuItemModViPaciente;
	private JMenuItem menuItemHistMed;
	private JMenuItem menuItemRegPaciente;
	public static boolean consultaRealizada = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//Paciente para probar
			//Paciente pac = new Paciente("123", "232", new Date(), 'M', "23342341", "sds", "PAC", "O", 180.1f, 200.15f, null, null);
			//Médico y Consulta para probar el visualizar y la colocación automática del código del médico al momento de registrar
			Medico med = new Medico("123", "Julito", new Date(), 'M', "88988987", null, "M-PRU");
			//ConsultaMedica cons = new ConsultaMedica("cons", "pac", "doc", null, "Pila de vainas", "muy malas", new Date());
			//cons.getAnalisis().add("Hemograma");
			//RegConsultaMedica dialog = new RegConsultaMedica(null, med, cons);
			
			RegConsultaMedica dialog = new RegConsultaMedica(null, med, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsultaMedica(Paciente pacienteAConsultar, Medico medicoConsultor, ConsultaMedica consMedAVisualizar) {
		
		paciente = pacienteAConsultar;
		medico = medicoConsultor;
		consMed = consMedAVisualizar;
		
		setTitle("Realizar Consulta Médica");
		
		if (consMedAVisualizar != null) {
			
			setTitle("Visualizar Consulta Médica");
		}
		
		analisis = new ArrayList<String>();

		analisis.add("Hemograma");
		analisis.add("Perfil Biofísico");
		analisis.add("Biopsia");
		analisis.add("Perfil renal");
		analisis.add("Panel hepático");
		analisis.add("Electrocardiograma");
		analisis.add("Radiografía de Tórax");
		analisis.add("Perfil Lipídico");
		analisis.add("TSH (Tiroides)");
		analisis.add("Glucosa en sangre");
		
		setBounds(100, 100, 667, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JPanel panelSuperiorAzul = new JPanel();
		panelSuperiorAzul.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperiorAzul.setBackground(new Color(173, 216, 230));
		panelSuperiorAzul.setBounds(0, 0, 651, 32);
		contentPanel.add(panelSuperiorAzul);
		panelSuperiorAzul.setLayout(null);
		
		JPanel panelDivisorVertical = new JPanel();
		panelDivisorVertical.setBackground(new Color(240, 248, 255));
		FlowLayout fl_panelDivisorVertical = (FlowLayout) panelDivisorVertical.getLayout();
		panelDivisorVertical.setBounds(88, 0, 3, 32);
		panelSuperiorAzul.add(panelDivisorVertical);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		menuBar.setBackground(new Color(173, 216, 230));
		menuBar.setBounds(5, 0, 84, 32);
		panelSuperiorAzul.add(menuBar);
		
		JMenu menuPaciente = new JMenu("Paciente");
		menuPaciente.setIcon(new ImageIcon(RegConsultaMedica.class.getResource("/Imagenes/paciente.png")));
		menuPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		menuBar.add(menuPaciente);
		
		menuItemRegPaciente = new JMenuItem("Registrar Paciente");
		
		if (paciente != null) {
			
			menuItemRegPaciente.setEnabled(false);
		}
		
		menuItemRegPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegPaciente registrarPaciente = new RegPaciente(null, true, false);
				registrarPaciente.setModal(true);
				registrarPaciente.setVisible(true);
					
				txtCodePaciente.setText(registrarPaciente.getCodePacienteRegistrado());
				paciente = Clinica.getInstance().buscarPacienteByCode(registrarPaciente.getCodePacienteRegistrado());
				activarDesactivarCampos();
				menuItemRegPaciente.setEnabled(false);
				menuItemModViPaciente.setEnabled(true);
				menuItemHistMed.setEnabled(true);
				btnRealizar.setEnabled(true);
			}
		});
		menuItemRegPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		menuPaciente.add(menuItemRegPaciente);
		
		menuItemModViPaciente = new JMenuItem("Modificar/Visualizar Paciente");
		
		if (menuItemRegPaciente.isEnabled()) {
			
			menuItemModViPaciente.setEnabled(false);
		}
		
		menuItemModViPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegPaciente mod_verPaciente = new RegPaciente(paciente, false, false);
				mod_verPaciente.setModal(true);
				mod_verPaciente.setVisible(true);
				
				paciente = Clinica.getInstance().buscarPacienteByCode(paciente.getCodePaciente());
				
			}
		});
		menuItemModViPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		menuPaciente.add(menuItemModViPaciente);
		
		if (menuItemRegPaciente.isEnabled()) {
			
		
		}
		
		menuItemHistMed = new JMenuItem("Historial M\u00E9dico");
		menuItemHistMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VerHistorialMedico visualizarHistMed = new VerHistorialMedico(paciente.getCodePaciente());
				visualizarHistMed.setModal(true);
				visualizarHistMed.setVisible(true);
			}
		});
		
		if (menuItemRegPaciente.isEnabled()) {
			
			menuItemHistMed.setEnabled(false);
		}
		
		menuItemHistMed.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		menuPaciente.add(menuItemHistMed);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(0, 0, 4, 32);
		panelSuperiorAzul.add(panel_1);
		
		JLabel lblCodePaciente = new JLabel("C\u00F3digo de Paciente:");
		lblCodePaciente.setOpaque(true);
		lblCodePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodePaciente.setForeground(Color.BLACK);
		lblCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblCodePaciente.setBackground(Color.WHITE);
		lblCodePaciente.setBounds(425, 5, 132, 22);
		panelSuperiorAzul.add(lblCodePaciente);
		
		txtCodePaciente = new JTextField();
		
		if (paciente != null) {
			
			txtCodePaciente.setText(paciente.getCodePaciente());
		}
		else {
			
			txtCodePaciente.setText("");
		}
		
		txtCodePaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtCodePaciente.setEditable(false);
		txtCodePaciente.setColumns(10);
		txtCodePaciente.setBounds(567, 5, 62, 22);
		panelSuperiorAzul.add(txtCodePaciente);
		
		JPanel panelDeCodigos = new JPanel();
		panelDeCodigos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeCodigos.setLayout(null);
		panelDeCodigos.setBackground(Color.WHITE);
		panelDeCodigos.setBounds(0, 32, 651, 32);
		contentPanel.add(panelDeCodigos);
		
		JLabel lblCodeConsulta = new JLabel("C\u00F3digo de Consulta:");
		lblCodeConsulta.setOpaque(true);
		lblCodeConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeConsulta.setForeground(Color.BLACK);
		lblCodeConsulta.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblCodeConsulta.setBackground(Color.WHITE);
		lblCodeConsulta.setBounds(21, 5, 132, 22);
		panelDeCodigos.add(lblCodeConsulta);
		
		txtCodeConsulta = new JTextField();
		txtCodeConsulta.setText("CONS-"+Clinica.getInstance().getGeneradorCodeConsMed());
		txtCodeConsulta.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtCodeConsulta.setEditable(false);
		txtCodeConsulta.setColumns(10);
		txtCodeConsulta.setBounds(163, 5, 62, 22);
		panelDeCodigos.add(txtCodeConsulta);
		
		JLabel lblCodeMedico = new JLabel("C\u00F3digo de M\u00E9dico:");
		lblCodeMedico.setOpaque(true);
		lblCodeMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeMedico.setForeground(Color.BLACK);
		lblCodeMedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblCodeMedico.setBackground(Color.WHITE);
		lblCodeMedico.setBounds(425, 5, 132, 22);
		panelDeCodigos.add(lblCodeMedico);
		
		txtCodeMedico = new JTextField();	
		txtCodeMedico.setText(medico.getCodeMedico());
		txtCodeMedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtCodeMedico.setEditable(false);
		txtCodeMedico.setColumns(10);
		txtCodeMedico.setBounds(567, 5, 62, 22);
		panelDeCodigos.add(txtCodeMedico);
		
		dateChooserConsulta = new JDateChooser();
		dateChooserConsulta.setBounds(238, 5, 94, 22);
		panelDeCodigos.add(dateChooserConsulta);
		dateChooserConsulta.getCalendarButton().setEnabled(false);
		dateChooserConsulta.setDate(new Date());
		dateChooserConsulta.setEnabled(false);
		
		JPanel panelInfoConsulta = new JPanel();
		panelInfoConsulta.setBackground(new Color(105, 116, 124));
		panelInfoConsulta.setBounds(0, 63, 651, 215);
		contentPanel.add(panelInfoConsulta);
		panelInfoConsulta.setLayout(null);
		
		JLabel lblSintomas = new JLabel("S\u00EDntomas:");
		lblSintomas.setOpaque(true);
		lblSintomas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSintomas.setForeground(Color.BLACK);
		lblSintomas.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblSintomas.setBackground(Color.WHITE);
		lblSintomas.setBounds(21, 25, 100, 22);
		panelInfoConsulta.add(lblSintomas);
		
		JLabel lblDiagnostico = new JLabel("Diagn\u00F3stico:");
		lblDiagnostico.setOpaque(true);
		lblDiagnostico.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiagnostico.setForeground(Color.BLACK);
		lblDiagnostico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblDiagnostico.setBackground(Color.WHITE);
		lblDiagnostico.setBounds(21, 94, 100, 22);
		panelInfoConsulta.add(lblDiagnostico);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setOpaque(true);
		lblEnfermedad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnfermedad.setForeground(Color.BLACK);
		lblEnfermedad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblEnfermedad.setBackground(Color.WHITE);
		lblEnfermedad.setBounds(21, 162, 100, 22);
		panelInfoConsulta.add(lblEnfermedad);
		
		txtareaSintomas = new JTextArea();
		txtareaSintomas.setWrapStyleWord(true);
		txtareaSintomas.setLineWrap(true);
		txtareaSintomas.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaSintomas.setBounds(131, 25, 175, 52);
		panelInfoConsulta.add(txtareaSintomas);
		
		txtareaDiagnostico = new JTextArea();
		txtareaDiagnostico.setWrapStyleWord(true);
		txtareaDiagnostico.setLineWrap(true);
		txtareaDiagnostico.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		txtareaDiagnostico.setBounds(131, 94, 175, 52);
		panelInfoConsulta.add(txtareaDiagnostico);
		
		cbxEnfermedad = new JComboBox();
		
		int sizeListaEnfermedades = Clinica.getInstance().getMisEnfermedades().size() + 1;
		String[] nombresEnfermedades = new String[sizeListaEnfermedades];
		
		nombresEnfermedades[0] = "Ninguna";
		
		for (int index = 1; index < sizeListaEnfermedades; index++) {
			
			nombresEnfermedades[index] = Clinica.getInstance().getMisEnfermedades().get(index - 1).getNombre();
		}
				
		cbxEnfermedad.setModel(new DefaultComboBoxModel(nombresEnfermedades));
		cbxEnfermedad.setSelectedIndex(0);
		cbxEnfermedad.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		cbxEnfermedad.setBounds(131, 163, 175, 22);
		AutoCompleteDecorator.decorate(cbxEnfermedad);
		panelInfoConsulta.add(cbxEnfermedad);
		
		JPanel panelAnalisis = new JPanel();
		panelAnalisis.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAnalisis.setBackground(new Color(173, 216, 230));
		panelAnalisis.setBounds(334, 25, 295, 159);
		panelInfoConsulta.add(panelAnalisis);
		panelAnalisis.setLayout(null);
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setBounds(10, 11, 275, 137);
		panelAnalisis.add(panelContenedor);
		panelContenedor.setLayout(null);
		
		JPanel panelTablaAnalisis = new JPanel();
		panelTablaAnalisis.setBounds(0, 53, 275, 84);
		panelContenedor.add(panelTablaAnalisis);
		panelTablaAnalisis.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTablaAnalisis.add(scrollPane, BorderLayout.CENTER);
		
		Object[] header = {"Nombre", "Elegir"};
		
		model = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 1) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       if (row >= 0 && column == 1) {
			    	   
			    	   return true;
			       }
			       else {
					
			    	   return false;
				}
			}
		};
		model.setColumnIdentifiers(header);
		tableAnalisis = new JTable(model);
		tableAnalisis.getTableHeader().setResizingAllowed(false);
		tableAnalisis.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		tableAnalisis.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableAnalisis);
		
		JLabel lblAnalisis = new JLabel("An\u00E1lisis a indicar:");
		lblAnalisis.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalisis.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblAnalisis.setBounds(76, 9, 118, 14);
		panelContenedor.add(lblAnalisis);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setOpaque(true);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.BLACK);
		lblBuscar.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		lblBuscar.setBackground(Color.WHITE);
		lblBuscar.setBounds(10, 31, 56, 16);
		panelContenedor.add(lblBuscar);
		
		txtBuscarAnalisis = new JTextField();
		txtBuscarAnalisis.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableAnalisis.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableAnalisis.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarAnalisis.getText()));
				
			}
		});
		txtBuscarAnalisis.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		txtBuscarAnalisis.setColumns(10);
		txtBuscarAnalisis.setBounds(76, 31, 189, 16);
		panelContenedor.add(txtBuscarAnalisis);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRealizar = new JButton("Realizar");
				btnRealizar.setEnabled(false);
				btnRealizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
							sintoma = txtareaSintomas.getText();
							diagnostico = txtareaDiagnostico.getText();
							
							if (sintoma.isEmpty() || diagnostico.isEmpty()) {
								throw new ValidarCampo("Debe llenar los campos obligatorios.");
							}
							if (consMed == null) {
								
								Enfermedad enfermedadElegida = null;
								
								if (!cbxEnfermedad.getSelectedItem().toString().equalsIgnoreCase("Ninguna")) {
									
									enfermedadElegida = Clinica.getInstance().buscarEnfermedadByNombre(cbxEnfermedad.getSelectedItem().toString());
								}
								
								ConsultaMedica consMedNueva = new ConsultaMedica(txtCodeConsulta.getText(), txtCodePaciente.getText(),
									           txtCodeMedico.getText(), enfermedadElegida, txtareaSintomas.getText(),
									           txtareaDiagnostico.getText(), dateChooserConsulta.getDate());
								
								consMedNueva.getAnalisis().addAll(extraerAnalisisElegidos());
								Clinica.getInstance().insertarConsultaMedica(consMedNueva);
								
								JOptionPane.showMessageDialog(null, "Realizada con éxito", "Realizar Consulta Médica", JOptionPane.INFORMATION_MESSAGE);
								
								consultaRealizada = true;
								dispose();
							}
						} catch (ValidarCampo e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							e2.printStackTrace();
							txtareaSintomas.grabFocus();
						}
					}
				});
				btnRealizar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnRealizar.setActionCommand("OK");
				buttonPane.add(btnRealizar);
				getRootPane().setDefaultButton(btnRealizar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						consultaRealizada = false;
						dispose();
					}
				});
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (consMed != null) {
			
			txtCodeConsulta.setText(consMed.getCodeConsMed());
			dateChooserConsulta.setDate(consMed.getFechaConsulta());
			txtCodePaciente.setText(consMed.getCodePaciente());
			txtCodeMedico.setText(consMed.getCodeMedico());
			txtareaSintomas.setText(consMed.getSintomas());
			txtareaDiagnostico.setText(consMed.getDiagnostico());
			cbxEnfermedad.setSelectedItem(consMed.getEnfermedad());
			
			tableAnalisis.setEnabled(false);
			txtCodeConsulta.setEditable(false);
			txtareaSintomas.setEditable(false);
			txtareaDiagnostico.setEditable(false);
			cbxEnfermedad.setEnabled(false);
			btnRealizar.setVisible(false);
			cancelButton.setText("Cerrar");
		}
		
		if (paciente != null) {
			
			btnRealizar.setEnabled(true);
		}
		
		activarDesactivarCampos();
		loadAnalisis();
		loadConsultaMedica();
		
		if (consMed != null) {
			
			menuPaciente.setEnabled(false);
		}
		
	}
	
	public ArrayList<String> extraerAnalisisElegidos() {
		
		ArrayList<String> analisisElegidos = new ArrayList<String>();
		
		for (int index = 0; index < tableAnalisis.getRowCount(); index++) {
			
			if (tableAnalisis.getValueAt(index, 1) == Boolean.TRUE) {
				
				analisisElegidos.add(tableAnalisis.getValueAt(index, 0).toString());
			}
			
		}
		
		return analisisElegidos;
	}
	
	private void loadAnalisis() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		if (consMed == null) {

			for (int index = 0; index < analisis.size(); index++) {
				

				row[0] = analisis.get(index);
				row[1] = false;
				model.addRow(row);
			}
			
		}
		else {
			
			for (int index = 0; index < analisis.size(); index++) {
	
				row[0] = analisis.get(index);
				
				if (consMed.getAnalisis().contains(analisis.get(index))) {
					row[1] = true;
				}
				else {
					row[1] = false;
				}
				model.addRow(row);
			}
			
		}
		
	}
	
	public void activarDesactivarCampos() {
		
		if (paciente != null && consMed == null) {
			
			txtareaSintomas.setEditable(true);
			txtareaDiagnostico.setEditable(true);
			cbxEnfermedad.setEnabled(true);
			tableAnalisis.setEnabled(true);
			txtBuscarAnalisis.setEditable(true);
		}
		else {
			
			txtareaSintomas.setEditable(false);
			txtareaDiagnostico.setEditable(false);
			cbxEnfermedad.setEnabled(false);
			tableAnalisis.setEnabled(false);
			txtBuscarAnalisis.setEditable(false);
		}
		
	}
	
	public void loadConsultaMedica() {
		
		if (consMed != null) {
			
			txtareaSintomas.setText(consMed.getSintomas());
			txtareaDiagnostico.setText(consMed.getDiagnostico());
			cbxEnfermedad.setSelectedItem(consMed.getEnfermedad().getNombre());
			
		}
		
	}

	public static boolean isConsultaRealizada() {
		return consultaRealizada;
	}
	
}
