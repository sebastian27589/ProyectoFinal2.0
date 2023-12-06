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
import javax.swing.table.TableRowSorter;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Vacuna;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
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

public class MostrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableVacunas;
	private JTextField txtBuscarVacuna;
	private static DefaultTableModel model;
	private static Object[] row;
	private Cita selected = null;
	private ArrayList<Cita> citasEspecificasAMostrar = new ArrayList<Cita>();
	private JButton btnConsultar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarCita dialog = new MostrarCita(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarCita(ArrayList<Cita> citasAMostrar) {
		
		citasEspecificasAMostrar = citasAMostrar;
		
		setResizable(false);
		setTitle("Citas");
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
		
		Object[] header = {"No. Cita", "Nombre del Médico", "Nombre de la Persona", "Fecha", "Hora"};
		
		model = new DefaultTableModel();
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
			
			JScrollPane scrollPane = new JScrollPane(tableVacunas);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			tableVacunas = new JTable(model);
			tableVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableVacunas.getTableHeader().setResizingAllowed(false);
			tableVacunas.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			for (int index = 0; index < tableVacunas.getColumnCount(); index++) {
				
				tableVacunas.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
			
			tableVacunas.getColumnModel().getColumn(0).setPreferredWidth(5);
			tableVacunas.getColumnModel().getColumn(1).setPreferredWidth(100);
			tableVacunas.getColumnModel().getColumn(2).setPreferredWidth(100);
			tableVacunas.getColumnModel().getColumn(3).setPreferredWidth(5);
			tableVacunas.getColumnModel().getColumn(4).setPreferredWidth(5);
			
			tableVacunas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableVacunas.getSelectedRow(), colIndex = tableVacunas.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarCitaByNum(tableVacunas.getValueAt(rowIndex, 0).toString());
						btnConsultar.setEnabled(true);
					}
					
				}
			});
			tableVacunas.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			tableVacunas.setFillsViewportHeight(true);
			scrollPane.setViewportView(tableVacunas);
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
		
		JLabel lblBuscarVacuna = new JLabel("Buscar:");
		lblBuscarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarVacuna.setOpaque(true);
		lblBuscarVacuna.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarVacuna.setBounds(53, 36, 55, 22);
		panel.add(lblBuscarVacuna);
		
		txtBuscarVacuna = new JTextField();
		txtBuscarVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableVacunas.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableVacunas.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarVacuna.getText()));
			}
		});
		txtBuscarVacuna.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscarVacuna.setBounds(118, 36, 307, 22);
		panel.add(txtBuscarVacuna);
		txtBuscarVacuna.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setEnabled(false);
		btnConsultar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnConsultar.setBounds(708, 36, 89, 23);
		panel.add(btnConsultar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
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
		
		if (citasEspecificasAMostrar == null) {
			
			loadCitas(Clinica.getInstance().getMisCitas());
			btnConsultar.setVisible(false);
		}
		else {
			
			loadCitas(citasEspecificasAMostrar);
		}
		
	}

	public static void loadCitas(ArrayList<Cita> mostrarCitas) {
		
		Medico medicoDeCita = null;
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Cita cita : mostrarCitas) {
			row[0] = cita.getNumCita();
			
			medicoDeCita = Clinica.getInstance().buscarMedicoByCode(cita.getCodeMedico());
			
			row[1] = medicoDeCita.getNombre();
			row[2] = cita.getCliente().getNombre();
			row[3] = cita.getFechaDeCita();
			row[4] = cita.getHoraCita(); 
			model.addRow(row);
		}
		
	}
}
