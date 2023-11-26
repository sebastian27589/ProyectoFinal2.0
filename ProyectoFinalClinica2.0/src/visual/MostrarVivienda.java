package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logico.Clinica;
import logico.Vacuna;
import logico.Vivienda;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MostrarVivienda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] row;
	private Vivienda selected = null;
	private JTable table;
	private JTextField txtBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarVivienda dialog = new MostrarVivienda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarVivienda() {
		
		Vivienda v1 = new Vivienda("#5", "01", "Las Rosas", "Santiago", "809");
		Vivienda v2 = new Vivienda("#2", "02", "Cerro Alto", "Santiago", "809");
		Clinica.getInstance().insertarVivienda(v1);
		Clinica.getInstance().insertarVivienda(v2);
		
		//jajajajajjajajajajajaaj
		
		setResizable(false);
		setTitle("Mostrar Viviendas");
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
		Object[] columnNames = {"Núm. Vivienda", "Calle", "Sector", "Ciudad", "Teléfono", "Residentes"};
		
//		Object[][] data = {
//				{"0001", "Calle del Sol", "Santiago", "Santiago", "809-554-1124", false},
//		};
		model = new DefaultTableModel() {
			
			public Class getColumnClass(int columnIndex){
				
				if(columnIndex == 5) {
					return Boolean.class;
					
				}else{
					return String.class;
				}
				
				//return super.getColumnClass(columnIndex);
			}
			
			public boolean isCellEditable(int row, int columnIndex) {
				return false;
			}
		};
		model.setColumnIdentifiers(columnNames);
		contentPanel.setBackground(backgroundColor);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(35, 42, 744, 248);
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(table);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable(model);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int rowIndex = table.getSelectedRow(), colIndex = table.getSelectedColumn();
					if (rowIndex >= 0) {
						selected = Clinica.getInstance().buscarViviendaByNum(table.getValueAt(rowIndex, 0).toString());
						if (colIndex == 5) {
							selected = Clinica.getInstance().buscarViviendaByNum(table.getValueAt(rowIndex, 0).toString());
							System.out.println("Abrir la lista de Residentes");
							table.setValueAt(Boolean.FALSE, rowIndex, colIndex);
						}
					}
				}
			});
			table.setFillsViewportHeight(true);
			scrollPane.setViewportView(table);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(rayita2);
		panel_1.setBounds(0, 128, 851, 91);
		contentPanel.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(rayita1);
		panel.setBounds(0, 13, 851, 91);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Buscar:");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		label.setBounds(35, 302, 55, 22);
		contentPanel.add(label);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel searchModel1 = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				table.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText()));
			}
		});
		txtBuscar.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(102, 302, 307, 22);
		contentPanel.add(txtBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					JButton okButton = new JButton("Modificar");
					buttonPane.add(okButton);
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadViviendas();
	}

	public static void loadViviendas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Vivienda vivienda : Clinica.getInstance().getMisViviendas()) {
			row[0] = vivienda.getNumero();
			row[1] = vivienda.getCalle();
			row[2] = vivienda.getSector();
			row[3] = vivienda.getCiudad();
			row[4] = vivienda.getTelefonoResi();
			//row[5] = vivienda.getResidentes();
			model.addRow(row);
		}
	}
}
