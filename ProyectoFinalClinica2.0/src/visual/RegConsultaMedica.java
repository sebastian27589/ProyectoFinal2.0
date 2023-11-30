package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class RegConsultaMedica extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsultaMedica dialog = new RegConsultaMedica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsultaMedica() {
		setTitle("Realizar Consulta M\u00E9dica");
		setBounds(100, 100, 630, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(0, 0, 614, 32);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelDivisorVertical = new JPanel();
		panelDivisorVertical.setBackground(new Color(240, 248, 255));
		FlowLayout fl_panelDivisorVertical = (FlowLayout) panelDivisorVertical.getLayout();
		panelDivisorVertical.setBounds(69, 0, 3, 32);
		panel.add(panelDivisorVertical);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		menuBar.setBackground(new Color(173, 216, 230));
		menuBar.setBounds(0, 0, 72, 32);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Paciente");
		mnNewMenu.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItemRegPaciente = new JMenuItem("Registrar Paciente");
		menuItemRegPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		mnNewMenu.add(menuItemRegPaciente);
		
		JMenuItem menuItemModViPaciente = new JMenuItem("Modificar/Visualizar Paciente");
		menuItemModViPaciente.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		mnNewMenu.add(menuItemModViPaciente);
		
		JMenuItem menuItemVacunas = new JMenuItem("Vacunas");
		menuItemVacunas.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		mnNewMenu.add(menuItemVacunas);
		
		JMenuItem menuItemHistMed = new JMenuItem("Historial M\u00E9dico");
		menuItemHistMed.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		mnNewMenu.add(menuItemHistMed);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRealizar = new JButton("Realizar");
				btnRealizar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnRealizar.setActionCommand("OK");
				buttonPane.add(btnRealizar);
				getRootPane().setDefaultButton(btnRealizar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
