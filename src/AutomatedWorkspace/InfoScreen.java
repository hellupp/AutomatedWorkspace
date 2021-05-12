package AutomatedWorkspace;
/* Second screen for get information about warehouse (GUI of Automated Workspace)
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class InfoScreen {

	private JFrame frame;
	protected static Group group;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoScreen window = new InfoScreen();
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
	public InfoScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Підприємство \"МАКДОНАЛЬДС ЮКРЕЙН ЛТД\"");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 36, 839, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Склад №" + WelcomeScreen.storage.getName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblNewLabel_1.setBounds(328, 98, 202, 51);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Інформація про всі товари складу");
		btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.setBounds(59, 344, 315, 61);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoStorageScreen().start();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Інформація про всі товари групи");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.TRAILING);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton_1.setBounds(506, 344, 295, 61);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				String result = JOptionPane.showInputDialog(btnNewButton_1, "Введіть назву групи товарів:", 
						"Інформація по групі", JOptionPane.QUESTION_MESSAGE);
				if (result == null)
					System.out.println("");
				else {
					if (result.equals("")) {
						JOptionPane.showConfirmDialog(btnNewButton_1, "Поле з назвою товару пусте!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						for (Group gr : Storage.getGroupsOfGoods()) {
							if (gr.getName().equals(result)) {
								group = gr;
								found = true;
								new InfoGroupScreen().start();
							}								
						}
						if (found == false) {
							JOptionPane.showConfirmDialog(btnNewButton_1, "Групу не знайдено", "Помилка", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					}
				}				
			}
		});
		
		JButton btnNewButton_3 = new JButton("Загальна вартість усього товару групи");
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton_3.setBounds(489, 478, 348, 61);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				String result1 = JOptionPane.showInputDialog(btnNewButton_3, "Введіть назву групи товарів:", 
						"Вартість товару групи", JOptionPane.QUESTION_MESSAGE);
				if (result1 == null)
					System.out.println("");
				else {
					if (result1.equals("")) {
						JOptionPane.showConfirmDialog(btnNewButton_3, "Поле з назвою товару пусте!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						for (Group gr : Storage.getGroupsOfGoods()) {
							if (gr.getName().equals(result1)) {
								group = gr;
								JOptionPane.showConfirmDialog(btnNewButton_3, "Вартість усього товару групи \"" + group.getName() + "\" "
										+ group.getCostOfGroup() + "грн", "Вартість товару групи", JOptionPane.DEFAULT_OPTION,
										JOptionPane.INFORMATION_MESSAGE);
								found = true;
							}	
						}
						if (found == false) {
							JOptionPane.showConfirmDialog(btnNewButton_3, "Групу не знайдено", "Помилка", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		JButton btnNewButton_3_1 = new JButton("Загальна вартість усіх товарів складу");
		btnNewButton_3_1.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton_3_1.setBounds(56, 478, 348, 61);
		frame.getContentPane().add(btnNewButton_3_1);
		
		btnNewButton_3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(btnNewButton_3_1, "Вартість усього товару складу " 
					+ Storage.getCostOfStorage() + "грн", "Вартість товару групи", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Назад");
		btnNewButton_1_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(10, 10, 85, 27);
		frame.getContentPane().add(btnNewButton_1_1);
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				InfoScreen.this.frame.dispose();
				new WelcomeScreen().start();
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Пошук товару по складу");
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton_2.setBounds(315, 219, 229, 61);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				String result2 = JOptionPane.showInputDialog(btnNewButton_2, "Введіть назву товару:", 
						"Пошук товару по складу", JOptionPane.QUESTION_MESSAGE);
				if (result2 == null)
					System.out.println("");
				else {
					if (result2.equals("")) {
						JOptionPane.showConfirmDialog(btnNewButton_2, "Поле з назвою товару пусте!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						for (Group gr : Storage.getGroupsOfGoods()) {
							for (Goods g : gr.getGoods()) {
								if (g.getName().equals(result2)) {
									JOptionPane.showMessageDialog(btnNewButton_2, g.toString(), "Товар знайдено", 
											JOptionPane.INFORMATION_MESSAGE);
									found = true;
								} 
							}
						}
						if (found == false) {
							JOptionPane.showConfirmDialog(btnNewButton_2, "Товар не знайдено на складі", "Помилка", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
}
