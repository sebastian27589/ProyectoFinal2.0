package visual;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import logico.Clinica;
import logico.Enfermedad;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MostrarEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] row;
	private JTable table;
	private JTextField txt_nombre;
	private JButton btn_modificar;
	private JButton btn_vigilanciaoff;
	private JButton btn_vigilanciaon;
	private Enfermedad selected = null;
	private JSpinner spn_peligro;
	private ArrayList<Enfermedad> enfermedadEspecificasAMostrar = new ArrayList<Enfermedad>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarEnfermedad dialog = new MostrarEnfermedad(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarEnfermedad(ArrayList<Enfermedad> enfermedadMostrar) {
		enfermedadEspecificasAMostrar = enfermedadMostrar;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarEnfermedad.class.getResource("/Imagenes/icon_enfermedad.png")));
		setTitle("Enfermedades");
		setResizable(false);
		setBounds(100, 100, 750, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(218, 221, 216));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel PanelTabla = new JPanel();
		PanelTabla.setBounds(10, 11, 392, 294);
		contentPanel.add(PanelTabla);
		PanelTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		});
		PanelTabla.add(scrollPane, BorderLayout.CENTER);
		
		String[] header = {"Nombre","Tipo","Peligrosidad","Mostrar Más"};
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
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int index = 0; index < table.getColumnCount(); index++) {
			
			if (index != 3) {
				
				table.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ind = table.getSelectedRow();
				int ind2 = table.getSelectedColumn();
				
				if(ind >= 0) {
					selected = Clinica.getInstance().buscarEnfermedadByNombre(table.getValueAt(ind, 0).toString());
					
					if(enfermedadEspecificasAMostrar == null) {
						btn_modificar.setEnabled(true);
						if(selected.isVigilada() == true) {
							btn_vigilanciaoff.setEnabled(true);
							btn_vigilanciaon.setEnabled(false);
						}
						
						else {
							btn_vigilanciaon.setEnabled(true);
							btn_vigilanciaoff.setEnabled(false);
						}
					}
					
					if (ind2 == 3) {
						RegEnfermedad verEnfermedad = new RegEnfermedad(selected, false);
						verEnfermedad.setModal(true);
						verEnfermedad.setVisible(true);
						table.setValueAt(Boolean.FALSE, ind, ind2);
					}
					
				}
			}
		});
		table.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		table.setFillsViewportHeight(true);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JPanel PanelButtons = new JPanel();
		PanelButtons.setBackground(new Color(218, 221, 216));
		PanelButtons.setBounds(412, 11, 310, 294);
		contentPanel.add(PanelButtons);
		PanelButtons.setLayout(null);
		
		JLabel lbl_Nombre = new JLabel("Nombre", SwingConstants.CENTER);
		lbl_Nombre.setOpaque(true);
		lbl_Nombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lbl_Nombre.setBackground(Color.WHITE);
		lbl_Nombre.setBounds(10, 2, 75, 25);
		PanelButtons.add(lbl_Nombre);
		
		JLabel lbl_Tipo = new JLabel("Tipo", SwingConstants.CENTER);
		lbl_Tipo.setOpaque(true);
		lbl_Tipo.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lbl_Tipo.setBackground(Color.WHITE);
		lbl_Tipo.setBounds(10, 59, 75, 25);
		PanelButtons.add(lbl_Tipo);
		
		JLabel lbl_Peligro = new JLabel("Peligro",SwingConstants.CENTER);
		lbl_Peligro.setOpaque(true);
		lbl_Peligro.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lbl_Peligro.setBackground(Color.WHITE);
		lbl_Peligro.setBounds(10, 119, 75, 25);
		PanelButtons.add(lbl_Peligro);
		
		btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected != null) {
					RegEnfermedad mod_verEnfermedad = new RegEnfermedad(selected, true);
					mod_verEnfermedad.setModal(true);
					mod_verEnfermedad.setVisible(true);
					if (enfermedadEspecificasAMostrar == null) {
						
						cargarEnfermedades(Clinica.getInstance().getMisEnfermedades());
					}
					else {
						
						cargarEnfermedades(enfermedadEspecificasAMostrar);
					}
				}
			}
		});
		btn_modificar.setEnabled(false);
		btn_modificar.setBounds(122, 271, 90, 23);
		PanelButtons.add(btn_modificar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancelar.setBounds(222, 271, 88, 23);
		PanelButtons.add(btn_cancelar);
		
		btn_vigilanciaon = new JButton("Poner Bajo Vigilancia");
		btn_vigilanciaon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected.setVigilada(true);
				table.clearSelection();
				btn_modificar.setEnabled(false);
				btn_vigilanciaon.setEnabled(false);
			}
		});
		btn_vigilanciaon.setEnabled(false);
		btn_vigilanciaon.setBounds(122, 189, 188, 23);
		PanelButtons.add(btn_vigilanciaon);
		
		btn_vigilanciaoff = new JButton("Quitar Bajo Vigilancia");
		btn_vigilanciaoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected.setVigilada(false);
				table.clearSelection();
				btn_modificar.setEnabled(false);
				btn_vigilanciaoff.setEnabled(false);
			}
		});
		btn_vigilanciaoff.setEnabled(false);
		btn_vigilanciaoff.setBounds(122, 230, 188, 23);
		PanelButtons.add(btn_vigilanciaoff);
		
		txt_nombre = new JTextField();
		txt_nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> filtroTxt = new TableRowSorter<DefaultTableModel>(model);
				filtroTxt.setRowFilter(RowFilter.regexFilter("(?i)" + txt_nombre.getText()));
				table.setRowSorter(filtroTxt);
			}
		});
		txt_nombre.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txt_nombre.setBounds(122, 2, 188, 25);
		PanelButtons.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		spn_peligro = new JSpinner();
		spn_peligro.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int numFiltPeligrosidad = new Integer(spn_peligro.getValue().toString());
				TableRowSorter<DefaultTableModel> filtroNum = new TableRowSorter<DefaultTableModel>(model); 
				filtroNum.setRowFilter(RowFilter.numberFilter(ComparisonType.AFTER, numFiltPeligrosidad-1, 2));
				table.setRowSorter(filtroNum);
			}
		});
		spn_peligro.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spn_peligro.setBounds(122, 120, 188, 25);
		PanelButtons.add(spn_peligro);
		
		JComboBox cbx_tipo = new JComboBox();
		cbx_tipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				TableRowSorter<DefaultTableModel> filtroTipo = new TableRowSorter<DefaultTableModel>(model);
				filtroTipo.setRowFilter(RowFilter.regexFilter("(?i)" + cbx_tipo.getSelectedItem().toString()));
				table.setRowSorter(filtroTipo);
			}
		});
		cbx_tipo.setModel(new DefaultComboBoxModel(new String[] {"", "Alergia", "Enf. Autoinmune", "Enf. Cardiovascular", "Enf. de la Mujer", "Enf. de la Sangre", "Enf. Mentales", "Enf. infecciosa"}));
		cbx_tipo.setBounds(122, 60, 188, 25);
		PanelButtons.add(cbx_tipo);
		
		JLabel img_enfermedad = new JLabel("");
		img_enfermedad.setIcon(new ImageIcon(MostrarEnfermedad.class.getResource("/Imagenes/photo_enfermedad.png")));
		img_enfermedad.setBounds(10, 185, 102, 105);
		PanelButtons.add(img_enfermedad);
		
		if (enfermedadEspecificasAMostrar == null) {
			
			cargarEnfermedades(Clinica.getInstance().getMisEnfermedades());
		}
		else {
			
			cargarEnfermedades(enfermedadEspecificasAMostrar);
		}
	}
	
	public static void cargarEnfermedades(ArrayList<Enfermedad> mostrarEnfermedades) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Enfermedad enf : mostrarEnfermedades) {
			row[0] = enf.getNombre();
			row[1] = enf.getTipo();
			row[2] = enf.getIndPeligro();
			
			model.addRow(row);
		}
		
	}
	
}
