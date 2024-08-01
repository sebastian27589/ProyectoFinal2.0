package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import logico.Clinica;
import logico.ConsultaMedica;
import logico.Medico;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MostrarConsultaPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablePacientes;
	private JTextField txtBuscarPaciente;
	private static DefaultTableModel model;
	private static Object[] row;
	private ConsultaMedica selected = null;
	private static Paciente paciente = null;
	private ArrayList<ConsultaMedica> consultasEspecificasAMostrar = new ArrayList<ConsultaMedica>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Paciente pac = new Paciente("", "", new Date(), 'M', "", "", "PAC1", "B+", 178.12f, 123.2f, "", "");
			Clinica.getInstance().insertarPaciente(pac);
			MostrarConsultaPaciente dialog = new MostrarConsultaPaciente(pac);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarConsultaPaciente(Paciente pacienteAMostrarCons) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarConsultaPaciente.class.getResource("/Imagenes/11891847711639747155-128.png")));
		
		paciente = Clinica.getInstance().buscarPacienteByCode(pacienteAMostrarCons.getCodePaciente());
		
		/*
		Vacuna vac1 = new Vacuna("000", "neumococo", "LabSpain");
		Clinica.getInstance().insertarVacuna(vac1);
		Paciente pac = new Paciente("123", "Julito", new Date(), 'M', "908", null, "PAC", "A+", 189.9f, 167.123f, null, null);
		pac.getMisVacunas().add(vac1);
		Clinica.getInstance().insertarPaciente(pac);
		*/
		
		setResizable(false);
		setTitle("Consultas Médicas");
		setBounds(100, 100, 945, 443);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0x6BAA75);
		Color rayita2 = new Color(0x69747C);
		int alpha = 60;
		int alpha2 = 120;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		
		Object[] header = {"Código de Consulta", "Nombre del Médico", "Fecha", "Ver detalles"};
		
		model = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 3) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 3) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		model.setColumnIdentifiers(header);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(147, 81, 765, 248);
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(tablePacientes);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			tablePacientes = new JTable(model);
			tablePacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablePacientes.getTableHeader().setResizingAllowed(false);
			tablePacientes.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			for (int index = 0; index < tablePacientes.getColumnCount(); index++) {
				
				if (index != 3) {
					
					tablePacientes.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tablePacientes.getColumnModel().getColumn(0).setPreferredWidth(5);
			tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(120);
			tablePacientes.getColumnModel().getColumn(2).setPreferredWidth(5);
			tablePacientes.getColumnModel().getColumn(3).setPreferredWidth(5);

			tablePacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tablePacientes.getSelectedRow(), colIndex = tablePacientes.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarConsMedByCode(tablePacientes.getValueAt(rowIndex, 0).toString());
						
						if (colIndex == 3) {
							
							RegConsultaMedica visualizarConsulta = new RegConsultaMedica(null, null, selected);
							visualizarConsulta.setModal(true);
							visualizarConsulta.setVisible(true);
							tablePacientes.setValueAt(Boolean.FALSE, rowIndex, colIndex);
						}

						
					}
					
				}
			});
			tablePacientes.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			tablePacientes.setFillsViewportHeight(true);
			scrollPane.setViewportView(tablePacientes);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(0, 128, 939, 91);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(rayita1);
		panel.setBounds(0, 13, 939, 91);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarPaciente = new JLabel("Buscar:");
		lblBuscarPaciente.setBackground(new Color(255, 255, 255));
		lblBuscarPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPaciente.setOpaque(true);
		lblBuscarPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarPaciente.setBounds(147, 36, 55, 22);
		panel.add(lblBuscarPaciente);
		
		txtBuscarPaciente = new JTextField();
		txtBuscarPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tablePacientes.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tablePacientes.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPaciente.getText()));
			}
		});
		txtBuscarPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscarPaciente.setBounds(214, 36, 307, 22);
		panel.add(txtBuscarPaciente);
		txtBuscarPaciente.setColumns(10);
		
		JCheckBox checkboxFiltrarCons = new JCheckBox("M\u00E1s Relevantes");
		checkboxFiltrarCons.setBackground(new Color(255, 255, 255));
		checkboxFiltrarCons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (checkboxFiltrarCons.isSelected()) {
					
					checkboxFiltrarCons.setSelected(false);
					loadConsultasMedicas(Clinica.getInstance().getMisConsultasMedicas());
				}
				else {
					
					checkboxFiltrarCons.setSelected(true);
					loadConsultasMedicas(Clinica.getInstance().getConsultasImportantesPaciente(paciente.getCodePaciente()));
				}
			}
		});
		checkboxFiltrarCons.setHorizontalAlignment(SwingConstants.CENTER);
		checkboxFiltrarCons.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		checkboxFiltrarCons.setBounds(774, 36, 138, 22);
		panel.add(checkboxFiltrarCons);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MostrarConsultaPaciente.class.getResource("/Imagenes/pngegg (8).png")));
		lblNewLabel.setBounds(24, 216, 126, 124);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadConsultasMedicas(Clinica.getInstance().getMisConsultasMedicas());
		
	}

	public static void loadConsultasMedicas(ArrayList<ConsultaMedica> consultasAMostrar) {
		
		String formato = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		Medico medico = null;
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (ConsultaMedica consMed : consultasAMostrar) {
			
			if (consMed.getCodePaciente().equalsIgnoreCase(paciente.getCodePaciente())) {
				
				row[0] = consMed.getCodeConsMed();
				
				medico = Clinica.getInstance().buscarMedicoByCode(consMed.getCodeMedico());
				row[1] = medico.getNombre();
				
				String fechaConFormato = simpleDateFormat.format(consMed.getFechaConsulta());
				row[2] = fechaConFormato;
				model.addRow(row);
			}
			
		}
		
	}
}
