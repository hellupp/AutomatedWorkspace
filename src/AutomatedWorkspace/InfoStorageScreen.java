package AutomatedWorkspace;
/* Screen for showing all goods of warehouse (GUI of Automated Workspace)
 */

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InfoStorageScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoStorageScreen window = new InfoStorageScreen();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoStorageScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		int size = Storage.getNumberOfGoods()*6;
		panel.setLayout(new GridLayout(size, 1, 4, 4));
		
		for (Group gr : Storage.getGroupsOfGoods()) {
			for (Goods g : gr.getGoods()) {
				JLabel line = new JLabel("Назва товару: " + g.getName());
				panel.add(line);
				line = new JLabel("Опис: " + g.getDescription());
				panel.add(line);
				line = new JLabel("Виробник: " + g.getManufacturer());
				panel.add(line);
				line = new JLabel("Ціна за одиницю: " + g.getPriceForOne());
				panel.add(line);
				line = new JLabel("Кількість одиниць:" + g.getAmount());
				panel.add(line);
				line = new JLabel();
				panel.add(line);
			}
		}
	}
}
