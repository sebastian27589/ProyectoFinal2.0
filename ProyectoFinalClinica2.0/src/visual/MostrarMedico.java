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
import java.util.ArrayList;
import java.util.Date;

public class MostrarMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableMedicos;
	private JTextField txtBuscarMedico;
	private static DefaultTableModel model;
	private static Object[] row;
	private Medico selected = null;
	private ArrayList<Medico> medicosEspecificosAMostrar = new ArrayList<Medico>();
	private JButton btnCitasPendientesMed;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarMedico dialog = new MostrarMedico(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarMedico(ArrayList<Medico> medicosAMostrar, boolean elegirParaCrearUsuario) {
		
		medicosEspecificosAMostrar = medicosAMostrar;
		
		/*
		Medico med = new Medico("123", "Hubieres Pe�a Rubieramero", new Date(), 'M', "8096691645", "Benito juare", "med-123");
		Medico med1 = new Medico("567", "Rubieramero", new Date(), 'M', "8096691645", "Benito juare", "med-123");
		med.getEspecialidades().add("Medicina Interna");
		med.getEspecialidades().add("Radiolog�a");
		Clinica.getInstance().insertarMedico(med);
		Clinica.getInstance().insertarMedico(med1);
		*/ 
		
		setResizable(false);
		setTitle("M�dicos");
		setBounds(100, 100, 857, 443);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0x6BAA75);
		Color rayita2 = new Color(0x69747C);
		int alpha = 60;
		int alpha2 = 120;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		
		Object[] header = {"C�digo", "C�dula", "Nombre", "Sexo", "Telefono", "Ver m�s"};
		
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
		contentPanel.setBackground(backgroundColor);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(53, 81, 744, 248);
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(tableMedicos);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			tableMedicos = new JTable(model);
			tableMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableMedicos.getTableHeader().setResizingAllowed(false);
			tableMedicos.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			for (int index = 0; index < tableMedicos.getColumnCount(); index++) {
				
				if (index != 5) {
					
					tableMedicos.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tableMedicos.getColumnModel().getColumn(0).setPreferredWidth(5);
			tableMedicos.getColumnModel().getColumn(1).setPreferredWidth(25);
			tableMedicos.getColumnModel().getColumn(2).setPreferredWidth(100);
			tableMedicos.getColumnModel().getColumn(3).setPreferredWidth(5);
			tableMedicos.getColumnModel().getColumn(4).setPreferredWidth(25);
			tableMedicos.getColumnModel().getColumn(5).setPreferredWidth(5);
			tableMedicos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableMedicos.getSelectedRow(), colIndex = tableMedicos.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarMedicoByCode(tableMedicos.getValueAt(rowIndex, 0).toString());
						btnCitasPendientesMed.setEnabled(true);
						
						if (medicosEspecificosAMostrar == null) {
							
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
						
						if (colIndex == 5) {
							
							RegMedico visualizarMedico =  new RegMedico(selected, true);
							visualizarMedico.setModal(true);
							visualizarMedico.setVisible(true);
							tableMedicos.setValueAt(Boolean.FALSE, rowIndex, colIndex);
						}

						
					}
					
				}
			});
			tableMedicos.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			tableMedicos.setFillsViewportHeight(true);
			scrollPane.setViewportView(tableMedicos);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(rayita2);
		panel_1.setBounds(0, 128, 851, 91);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(rayita1);
		panel.setBounds(0, 13, 851, 91);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarDoctor = new JLabel("Buscar:");
		lblBuscarDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarDoctor.setOpaque(true);
		lblBuscarDoctor.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarDoctor.setBounds(53, 36, 55, 22);
		panel.add(lblBuscarDoctor);
		
		txtBuscarMedico = new JTextField();
		txtBuscarMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableMedicos.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableMedicos.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarMedico.getText()));
			}
		});
		txtBuscarMedico.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscarMedico.setBounds(118, 36, 307, 22);
		panel.add(txtBuscarMedico);
		txtBuscarMedico.setColumns(10);
		
		btnCitasPendientesMed = new JButton("Citas pendientes");
		btnCitasPendientesMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* SE DEBE DISCUTIR...
				 * Listar todas las consultas m�dicas o el historial m�dico. Pienso que ser�a m�s l�gico simplemente listar el
				 * historial m�dico, y que no todos los usuarios tengan acceso a ver el historial m�dico de un paciente*\
				 */
			}
		});
		btnCitasPendientesMed.setEnabled(false);
		btnCitasPendientesMed.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnCitasPendientesMed.setBounds(654, 37, 143, 22);
		panel.add(btnCitasPendientesMed);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (!elegirParaCrearUsuario) {
							
							RegMedico modificarMedico = new RegMedico(selected, false);
							modificarMedico.setModal(true);
							modificarMedico.setVisible(true);
							loadMedicos();
							JOptionPane.showMessageDialog(null, "Modificado con �xito", "Modificar M�dico", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							
							
						}

					}
				});
				btnModificar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnModificar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int Option = JOptionPane.showConfirmDialog(null, "�Est� seguro de eliminar al M�dico con c�digo: <" + selected.getCodeMedico() + ">?", "Eliminar Paciente", JOptionPane.OK_CANCEL_OPTION);
						
						if (Option == JOptionPane.OK_OPTION) {
							
							Clinica.getInstance().eliminarMedico(selected);
							loadMedicos();
							btnModificar.setEnabled(false);
							btnEliminar.setEnabled(false);
							btnCitasPendientesMed.setEnabled(false);
						}
						
					}
				});
				btnEliminar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnEliminar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (medicosEspecificosAMostrar == null) {
			
			if (elegirParaCrearUsuario) {
				
				btnCitasPendientesMed.setVisible(false);
				btnEliminar.setVisible(false);
				btnModificar.setText("Siguiente");
				
			}
			
			loadMedicos();
		}
		else {
			
			loadMedicosEspecificos();
			lblBuscarDoctor.setVisible(false);
			txtBuscarMedico.setVisible(false);
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
		}
		
	}

	public static void loadMedicos() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : Clinica.getInstance().getMisPersonas()) {
			
			if (persona instanceof Medico) {
				
				row[0] = ((Medico) persona).getCodeMedico();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
	
	private void loadMedicosEspecificos() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Persona persona : medicosEspecificosAMostrar) {
			
			if (persona instanceof Medico) {
				
				row[0] = ((Medico) persona).getCodeMedico();
				row[1] = persona.getCedula();
				row[2] = persona.getNombre();
				row[3] = persona.getSexo();
				row[4] = persona.getTelefono();
				model.addRow(row);
			}
			
		}
		
	}
}
