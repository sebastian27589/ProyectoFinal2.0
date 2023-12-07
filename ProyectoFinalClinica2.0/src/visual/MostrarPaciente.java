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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MostrarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablePacientes;
	private JTextField txtBuscarPaciente;
	private static DefaultTableModel model;
	private static Object[] row;
	private Paciente selected = null;
	private ArrayList<Paciente> pacientesEspecificosAMostrar = new ArrayList<Paciente>();
	private JButton btnVerHistMed;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarPaciente dialog = new MostrarPaciente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarPaciente(ArrayList<Paciente> pacientesAMostrar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarPaciente.class.getResource("/Imagenes/paciente.png")));
		
		pacientesEspecificosAMostrar = pacientesAMostrar;
		
		/*
		Vacuna vac1 = new Vacuna("000", "neumococo", "LabSpain");
		Clinica.getInstance().insertarVacuna(vac1);
		Paciente pac = new Paciente("123", "Julito", new Date(), 'M', "908", null, "PAC", "A+", 189.9f, 167.123f, null, null);
		pac.getMisVacunas().add(vac1);
		Clinica.getInstance().insertarPaciente(pac);
		*/
		
		setResizable(false);
		setTitle("Pacientes");
		setBounds(100, 100, 990, 466);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0x6BAA75);
		Color rayita2 = new Color(0x69747C);
		int alpha = 60;
		int alpha2 = 120;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		
		Object[] header = {"Código", "Cédula", "Nombre", "Sexo", "Telefono", "Ver más"};
		
		model = new DefaultTableModel() {
			
			public Class getColumnClass(int column) {
				
				if (column == 5) {
					return Boolean.class;
				}
				else {
					return String.class;
				}
			}
			
			public boolean isCellEditable(int row, int column) {       
			       
			       if (row >= 0 && column == 5) {
			    	   return true;
			       }
			       else {
			    	   return false;
			       }
			}
		};
		model.setColumnIdentifiers(header);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(53, 81, 744, 248);
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
				
				if (index != 5) {
					
					tablePacientes.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tablePacientes.getColumnModel().getColumn(0).setPreferredWidth(5);
			tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(25);
			tablePacientes.getColumnModel().getColumn(2).setPreferredWidth(100);
			tablePacientes.getColumnModel().getColumn(3).setPreferredWidth(5);
			tablePacientes.getColumnModel().getColumn(4).setPreferredWidth(25);
			tablePacientes.getColumnModel().getColumn(5).setPreferredWidth(5);
			tablePacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tablePacientes.getSelectedRow(), colIndex = tablePacientes.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarPacienteByCode(tablePacientes.getValueAt(rowIndex, 0).toString());
						btnVerHistMed.setEnabled(true);
						
						if (pacientesEspecificosAMostrar == null) {
							
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
						
						if (colIndex == 5) {
							
							RegPaciente visualizarPaciente = new RegPaciente(selected, false, true);
							visualizarPaciente.setModal(true);
							visualizarPaciente.setVisible(true);
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
		panel_1.setBackground(new Color(240, 255, 240));
		panel_1.setBounds(0, 128, 984, 91);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 13, 984, 91);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarPaciente = new JLabel("Buscar:");
		lblBuscarPaciente.setBackground(new Color(255, 255, 255));
		lblBuscarPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPaciente.setOpaque(true);
		lblBuscarPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarPaciente.setBounds(53, 36, 55, 22);
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
		txtBuscarPaciente.setBounds(118, 36, 307, 22);
		panel.add(txtBuscarPaciente);
		txtBuscarPaciente.setColumns(10);
		
		btnVerHistMed = new JButton("Historial M\u00E9dico");
		btnVerHistMed.setBackground(new Color(255, 255, 255));
		btnVerHistMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VerHistorialMedico verHistorialPaciente = new VerHistorialMedico(selected.getCodePaciente());
				verHistorialPaciente.setModal(true);
				verHistorialPaciente.setVisible(true);
				
			}
		});
		btnVerHistMed.setEnabled(false);
		btnVerHistMed.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnVerHistMed.setBounds(661, 37, 136, 22);
		panel.add(btnVerHistMed);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setOpaque(true);
		lblIcon.setBackground(new Color(255, 255, 255, 20));
		lblIcon.setIcon(new ImageIcon(MostrarPaciente.class.getResource("/Imagenes/xd.png")));
		lblIcon.setBounds(825, 230, 128, 140);
		contentPanel.add(lblIcon);
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
				
				btnModificar = new JButton("Modificar");
				btnModificar.setBackground(new Color(255, 255, 255));
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						RegPaciente mod_verPaciente = new RegPaciente(selected, false, false);
						mod_verPaciente.setModal(true);
						mod_verPaciente.setVisible(true);
						loadPacientes();
						JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Paciente", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				btnModificar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnModificar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setBackground(new Color(255, 255, 255));
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al Paciente con código: <" + selected.getCodePaciente() + ">?", "Eliminar Paciente", JOptionPane.OK_CANCEL_OPTION);
						
						if (Option == JOptionPane.OK_OPTION) {
							
							Clinica.getInstance().eliminarPaciente(selected);
							loadPacientes();
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
							btnVerHistMed.setEnabled(false);
						}
						
					}
				});
				btnEliminar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnEliminar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (pacientesEspecificosAMostrar == null) {
		
			loadPacientes();
		}
		else {
			
			loadPacientesEspecificos();
			lblBuscarPaciente.setVisible(false);
			txtBuscarPaciente.setVisible(false);
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
		}
		
	}

	public static void loadPacientes() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : Clinica.getInstance().getMisPersonas()) {
			
			if (persona instanceof Paciente) {
				
				row[0] = ((Paciente) persona).getCodePaciente();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
	
	private void loadPacientesEspecificos() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : pacientesEspecificosAMostrar) {
			
			if (persona instanceof Paciente) {
				
				row[0] = ((Paciente) persona).getCodePaciente();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
}
