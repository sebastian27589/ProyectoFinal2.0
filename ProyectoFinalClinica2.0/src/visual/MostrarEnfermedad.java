package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import logico.Clinica;
import logico.Enfermedad;
import logico.Paciente;
import logico.Persona;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
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
		
		Enfermedad prueb = new Enfermedad("Cancer", "Viral", "ESTOS SON SINTOMAS DE PRUEBA", 3, true);
		Clinica.getInstance().insertarEnfermedad(prueb);
		
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
		
		String[] header = {"Nombre","Tipo","Peligrosidad","Mostrar M�s"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ind = table.getSelectedRow();
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
				}
			}
		});
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
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model);
				filtro.setRowFilter(RowFilter.regexFilter("(?i)" + txt_nombre.getText()));
				table.setRowSorter(filtro);
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
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model); 
				RowFilter<DefaultTableModel, Integer> filtroNumero = RowFilter.numberFilter(ComparisonType.AFTER, numFiltPeligrosidad-1, 2);
				filtro.setRowFilter(filtroNumero);
				table.setRowSorter(filtro);
			}
		});
		spn_peligro.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spn_peligro.setBounds(122, 120, 188, 25);
		PanelButtons.add(spn_peligro);
		
		JComboBox cbx_tipo = new JComboBox();
		cbx_tipo.setModel(new DefaultComboBoxModel(new String[] {"", "Alergias", "Enf. Autoinmunes", "Enf. Cardiovasculares", "Enf. de la Mujer", "Enf. de la Sangre", "Enf. Mentales", "Enf. infecciosas"}));
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
