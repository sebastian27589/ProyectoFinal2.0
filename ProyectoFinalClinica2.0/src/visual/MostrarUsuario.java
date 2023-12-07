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
import logico.Usuario;
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
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MostrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableUsuarios;
	private JTextField txtBuscarUsuario;
	private static DefaultTableModel model;
	private static Object[] row;
	private Usuario selected = null;
	private ArrayList<Usuario> usuariosEspecificosAMostrar = new ArrayList<Usuario>();
	private JButton btnModificar;
	private JButton btnEliminar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarUsuario dialog = new MostrarUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarUsuario(ArrayList<Usuario> usuariosAMostrar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarUsuario.class.getResource("/Imagenes/iconVentanaLogin.png")));
		
		usuariosEspecificosAMostrar = usuariosAMostrar;
		
		/*
		Vacuna vac1 = new Vacuna("000", "neumococo", "LabSpain");
		Clinica.getInstance().insertarVacuna(vac1);
		Paciente pac = new Paciente("123", "Julito", new Date(), 'M', "908", null, "PAC", "A+", 189.9f, 167.123f, null, null);
		pac.getMisVacunas().add(vac1);
		Clinica.getInstance().insertarPaciente(pac);
		*/
		
		setResizable(false);
		setTitle("Usuarios");
		setBounds(100, 100, 962, 495);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		Color backgroundColor = new Color(0xDADDD8);
		Color rayita1 = new Color(0x6BAA75);
		Color rayita2 = new Color(0x69747C);
		int alpha = 60;
		int alpha2 = 120;
		rayita1 = new Color(rayita1.getRed(), rayita1.getGreen(), rayita1.getBlue(), alpha);
		rayita2 = new Color(rayita2.getRed(), rayita2.getGreen(), rayita2.getBlue(), alpha2);
		
		Object[] header = {"Nombre de Usuario", "Nombre", "Rol", "Ver más"};
		
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(794, 232, 203, 222);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(MostrarUsuario.class.getResource("/Imagenes/pngegg (11).png")));
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(53, 81, 777, 248);
			contentPanel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane(tableUsuarios);
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			tableUsuarios = new JTable(model);
			tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableUsuarios.getTableHeader().setResizingAllowed(false);
			tableUsuarios.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setHorizontalAlignment(JLabel.CENTER);
			
			for (int index = 0; index < tableUsuarios.getColumnCount(); index++) {
				
				if (index != 3) {
					
					tableUsuarios.getColumnModel().getColumn(index).setCellRenderer(cellRenderer);
				}
			}
			
			tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(60);
			tableUsuarios.getColumnModel().getColumn(1).setPreferredWidth(120);
			tableUsuarios.getColumnModel().getColumn(2).setPreferredWidth(10);
			tableUsuarios.getColumnModel().getColumn(3).setPreferredWidth(5);

			tableUsuarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int rowIndex = tableUsuarios.getSelectedRow(), colIndex = tableUsuarios.getSelectedColumn();
					
					if (rowIndex >= 0) {
						
						selected = Clinica.getInstance().buscarUsuario(tableUsuarios.getValueAt(rowIndex, 0).toString());
						
						if (usuariosEspecificosAMostrar == null) {
							
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
						}
						
						if (colIndex == 3) {
							
							RegUsuario visualizarUsuario = new RegUsuario(selected, null, true);
							visualizarUsuario.setModal(true);
							visualizarUsuario.setVisible(true);
						}

					}
					
				}
			});
			tableUsuarios.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
			tableUsuarios.setFillsViewportHeight(true);
			scrollPane.setViewportView(tableUsuarios);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 128, 961, 91);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 13, 961, 91);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarUsuario = new JLabel("Buscar:");
		lblBuscarUsuario.setBackground(new Color(255, 255, 255));
		lblBuscarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarUsuario.setOpaque(true);
		lblBuscarUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblBuscarUsuario.setBounds(53, 36, 55, 22);
		panel.add(lblBuscarUsuario);
		
		txtBuscarUsuario = new JTextField();
		txtBuscarUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel searchModel1 = (DefaultTableModel) tableUsuarios.getModel();
				TableRowSorter<DefaultTableModel> searchModel2 = new TableRowSorter<DefaultTableModel>(searchModel1);
				tableUsuarios.setRowSorter(searchModel2);
				searchModel2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarUsuario.getText()));
			}
		});
		txtBuscarUsuario.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtBuscarUsuario.setBounds(118, 36, 307, 22);
		panel.add(txtBuscarUsuario);
		txtBuscarUsuario.setColumns(10);
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
						
						RegUsuario modUsuario = new RegUsuario(selected, null, false);
						modUsuario.setModal(true);
						modUsuario.setVisible(true);
						loadUsuarios();
						JOptionPane.showMessageDialog(null, "Modificado con éxito", "Modificar Usuario", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				btnModificar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnModificar);
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setBackground(new Color(255, 255, 255));
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int Option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al Usuario: <" + selected.getNombreUsuario() + ">?", "Eliminar Usuario", JOptionPane.OK_CANCEL_OPTION);
						
						if (Option == JOptionPane.OK_OPTION) {
							
							Clinica.getInstance().eliminarUsuario(selected);
							loadUsuarios();
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
						}
						
					}
				});
				btnEliminar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				buttonPane.add(btnEliminar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (usuariosEspecificosAMostrar == null) {
		
			loadUsuarios();
		}
		else {
			
			loadUsuariosEspecificos();
			lblBuscarUsuario.setVisible(false);
			txtBuscarUsuario.setVisible(false);
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
		}
		
	}

	public static void loadUsuarios() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Usuario usuario : Clinica.getInstance().getMisUsuarios()) {
			
			row[0] = usuario.getNombreUsuario();
			row[1] = usuario.getNombre();
			row[2] = usuario.getRolUsuario();
			model.addRow(row);

		}
		
	}
	
	private void loadUsuariosEspecificos() {
		
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Usuario usuario : usuariosEspecificosAMostrar) {
			
			row[0] = usuario.getNombreUsuario();
			row[1] = usuario.getNombre();
			row[2] = usuario.getRolUsuario();
			model.addRow(row);

		}
		
	}
	
}
