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
import javax.swing.text.DateFormatter;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Paciente;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MostrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableCitas;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarCita.class.getResource("/Imagenes/5709036141642251933-128.png")));
		
		citasEspecificasAMostrar = citasAMostrar;
		
		setResizable(false);
		setTitle("Citas");
		setBounds(100, 100, 857, 474);
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
		
		model = new DefaultTableModel() {
			
			public boolean isCellEditable(int row, int column) {       
			       
			       return false;
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
			panel_2.setBounds(53, 81, 744, 248);
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(tableCitas);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			tableCitas = new JTable(model);
			tableCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableCitas.getTableHeader().setResizingAllowed(false);
			tableCitas.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			for (int index = 0; index < tableCitas.getColumnCount(); index++) {
				
				tableCitas.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
			}
			
			tableCitas.getColumnModel().getColumn(0).setPreferredWidth(5);
			tableCitas.getColumnModel().getColumn(1).setPreferredWidth(100);
			tableCitas.getColumnModel().getColumn(2).setPreferredWidth(100);
			tableCitas.getColumnModel().getColumn(3).setPreferredWidth(5);
			tableCitas.getColumnModel().getColumn(4).setPreferredWidth(5);
			
			tableCitas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableCitas.getSelectedRow();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarCitaByNum(tableCitas.getValueAt(rowIndex, 0).toString());
						btnConsultar.setEnabled(true);
					}
					
				}
			});
			tableCitas.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			tableCitas.setFillsViewportHeight(true);
			scrollPane.setViewportView(tableCitas);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 218, 185));
		panel_1.setBounds(0, 128, 851, 91);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(0, 13, 851, 91);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarVacuna = new JLabel("Buscar:");
		lblBuscarVacuna.setBackground(new Color(255, 255, 255));
		lblBuscarVacuna.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarVacuna.setOpaque(true);
		lblBuscarVacuna.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarVacuna.setBounds(53, 36, 55, 22);
		panel.add(lblBuscarVacuna);
		
		txtBuscarVacuna = new JTextField();
		txtBuscarVacuna.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableCitas.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableCitas.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarVacuna.getText()));
			}
		});
		txtBuscarVacuna.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscarVacuna.setBounds(118, 36, 307, 22);
		panel.add(txtBuscarVacuna);
		txtBuscarVacuna.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBackground(new Color(255, 255, 255));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Paciente paciente = Clinica.getInstance().buscarPacienteByCedula(selected.getCliente().getCedula());
				Medico medico = Clinica.getInstance().buscarMedicoByCedula(Clinica.getUsuarioLogueado().getCedula());
				RegConsultaMedica realizarConsulta = new RegConsultaMedica(paciente, medico, null);
				realizarConsulta.setModal(true);
				realizarConsulta.setVisible(true);
				
				if (realizarConsulta.isConsultaRealizada()) {
					
					citasEspecificasAMostrar.remove(selected);
					selected.setPendiente(false);
					Clinica.getInstance().actualizarCita(selected);
					loadCitas(citasEspecificasAMostrar);
					btnConsultar.setEnabled(false);
				}
			}
		});
		btnConsultar.setEnabled(false);
		btnConsultar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnConsultar.setBounds(693, 36, 104, 23);
		panel.add(btnConsultar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setBackground(Color.WHITE);
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
		
		String formato = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		Medico medicoDeCita = null;
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Cita cita : mostrarCitas) {
			
			if (cita.isPendiente()) {
				
				row[0] = cita.getNumCita();
				
				medicoDeCita = Clinica.getInstance().buscarMedicoByCode(cita.getCodeMedico());
				
				row[1] = medicoDeCita.getNombre();
				row[2] = cita.getCliente().getNombre();
				
				String fechaConFormato = simpleDateFormat.format(cita.getFechaDeCita());
				row[3] = fechaConFormato;
				
				row[4] = cita.getHoraCita(); 
				model.addRow(row);
			}
			
		}
		
	}
}
