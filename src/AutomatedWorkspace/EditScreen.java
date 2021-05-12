package AutomatedWorkspace;
/* Second screen for editing warehouse with choosing or adding a group (GUI of Automated Workspace)
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EditScreen {

	private JFrame frame;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditScreen window = new EditScreen();
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
	public EditScreen() {
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
		
		JLabel lblNewLabel_2 = new JLabel("Оберіть групу товарів");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel_2.setBounds(295, 212, 268, 68);
		frame.getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		for (int i = 0; i < Storage.groups.size(); i++)
			comboBox.addItem(Storage.groups.get(i).getName());			
		
		comboBox.setBounds(319, 290, 221, 38);
		frame.getContentPane().add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Group gr: Storage.groups) {
					if (gr.getName().equals((String) comboBox.getSelectedItem())) {
						GroupOrGoodsScreen.group = gr;
						GroupOrGoodsScreen.groupFile = new File(gr.getName() + ".txt");
						break;
					}
				}
				GroupOrGoodsScreen.indexOfGroup = comboBox.getSelectedIndex();
				EditScreen.this.frame.dispose();
				new GroupOrGoodsScreen().start();
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Або додайте нову групу");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel_3.setBounds(309, 397, 240, 62);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Додати");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.setBounds(350, 469, 159, 62);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String result = JOptionPane.showInputDialog(btnNewButton, "Введіть назву нової групи товарів:", 
						"Додавання групи", JOptionPane.QUESTION_MESSAGE);
				if (result == null)
					System.out.println("");
				else {
					if (result.equals("")) {
						JOptionPane.showConfirmDialog(btnNewButton, "Поле з назвою групи пусте!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						if (Storage.checkGroupName(result)) {
							String result1 = JOptionPane.showInputDialog(btnNewButton, "Введіть опис нової групи товарів:", 
									"Додавання групи", JOptionPane.QUESTION_MESSAGE);
							Storage.groups.add(new Group(result, result1));
							comboBox.addItem(Storage.groups.get(Storage.groups.size() - 1).getName());
							try (FileWriter writer = new FileWriter("_Storage.txt", false)){
								writer.write(Storage.toStringGroups());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							try {
								try (PrintWriter printOut = new PrintWriter(Storage.groups.get(Storage.groups.size() - 1).getName() + ".txt")) {
									printOut.println(Storage.groups.get(Storage.groups.size() - 1).toString());
									GroupOrGoodsScreen.groupFile = new File(Storage.groups.get(Storage.groups.size() - 1).getName() + ".txt");
								}
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showConfirmDialog(btnNewButton, "Група з таким ім'ям вже існує!\nСпробуйте ще раз", 
									"Помилка", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}		
					}
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Назад");
		btnNewButton_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 10, 85, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EditScreen.this.frame.dispose();
				new WelcomeScreen().start();
				
			}
		});
	}
}


