package AutomatedWorkspace;
/* Screen with editing or deleting a group (GUI of Automated Workspace)
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class GroupOrGoodsScreen {

	protected static Group group;
	protected static int indexOfGroup;
	protected static File groupFile;
	
	private JFrame frame;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupOrGoodsScreen window = new GroupOrGoodsScreen();
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
	public GroupOrGoodsScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
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
		
		JButton btnNewButton_1 = new JButton("Назад");
		btnNewButton_1.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 10, 85, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GroupOrGoodsScreen.this.frame.dispose();
				new EditScreen().start();
				
			}
		});
		
		lblNewLabel_2 = new JLabel("Група товарів: \"" + group.getName() + "\"");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 188, 839, 62);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Редагувати групу");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(79, 346, 202, 76);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = JOptionPane.showInputDialog(btnNewButton, "Введіть нову назву для групи товарів:", 
						"Редагування групи", JOptionPane.QUESTION_MESSAGE);
				if (result == null)
					System.out.println("");
				else {
					if (result.equals("")) {
						JOptionPane.showConfirmDialog(btnNewButton, "Поле пусте.\nНазву групи не було змінено", 
								"Редагування групи", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						int res1 = JOptionPane.showOptionDialog(btnNewButton, "Потрібно змінити опис групи товарів?", 
								"Редагування групи", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
								new String[] {"Так", "Ні"}, null);
						if (res1 == JOptionPane.OK_OPTION) {
							String result2 = JOptionPane.showInputDialog(btnNewButton, "Введіть новий опис для групи товарів:", 
									"Додавання групи", JOptionPane.QUESTION_MESSAGE);
							if (result2 != null) {
								group.setDescription(result2);
							} else {
								group.setDescription("");
							}
							try (FileWriter writer = new FileWriter("_Storage.txt", false)){
								writer.write(Storage.toStringGroups());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						if (Storage.checkGroupName(result)) {
							group.setName(result);
							lblNewLabel_2.setText("Група товарів: \"" + group.getName() + "\"");
							int res = JOptionPane.showOptionDialog(btnNewButton, "Потрібно змінити опис групи товарів?", 
									"Редагування групи", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
									new String[] {"Так", "Ні"}, null);
							if (res == JOptionPane.OK_OPTION) {
								String result1 = JOptionPane.showInputDialog(btnNewButton, "Введіть новий опис для групи товарів:", 
										"Додавання групи", JOptionPane.QUESTION_MESSAGE);
								if (result1 != null) {
									group.setDescription(result1);
								} else {
									group.setDescription("");
								}
							}
							try (FileWriter writer = new FileWriter("_Storage.txt", false)){
								writer.write(Storage.toStringGroups());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							groupFile.delete();
							groupFile = new File(group.getName() + ".txt");
							try {
								try (PrintWriter printOut = new PrintWriter(groupFile.getAbsoluteFile())) {
									printOut.println(group.toString());
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
		
		JButton btnNewButton_2 = new JButton("Редагувати товар");
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_2.setBounds(304, 498, 251, 76);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(group.toString());
				GroupOrGoodsScreen.this.frame.dispose();
				new GoodsScreen().start();
			}
		});
		
		JButton btnNewButton_2_1 = new JButton("Видалити групу");
		btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton_2_1.setBounds(583, 345, 202, 79);
		frame.getContentPane().add(btnNewButton_2_1);
		
		btnNewButton_2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Storage.groups.remove(indexOfGroup);
//				System.out.println(EditScreen.groups.toString());
				JOptionPane.showConfirmDialog(btnNewButton_2_1, "Групу успішно видалено", "Видалено", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				try (FileWriter writer = new FileWriter("_Storage.txt", false)){
					writer.write(Storage.toStringGroups());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				groupFile.delete();
				
				GroupOrGoodsScreen.this.frame.dispose();
				new EditScreen().start();
			}
		});
	}
}
