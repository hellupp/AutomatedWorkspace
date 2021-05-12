package AutomatedWorkspace;
/* Screen with choosing for editing, deleting or adding goods to the group (GUI of Automated Workspace)
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class GoodsScreen {

	private JFrame frame;
	protected static Goods good;
	private int indexOfGood;
	private JButton btnNewButton_2;
	private JButton btnNewButton_2_1;
	private JButton btnNewButton_2_2;
	protected static JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsScreen window = new GoodsScreen();
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
	public GoodsScreen() {
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
				
				GoodsScreen.this.frame.dispose();
				new GroupOrGoodsScreen().start();
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Група товарів: \"" + GroupOrGoodsScreen.group.getName() + "\"");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 188, 839, 62);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Оберіть товар");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel_2_1.setBounds(295, 260, 268, 68);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(319, 338, 221, 38);
//		System.out.println(GroupOrGoodsScreen.group.toString());
		for (int i = 0; i < GroupOrGoodsScreen.group.getGoods().size(); i++)
			comboBox.addItem(GroupOrGoodsScreen.group.getGoods().get(i).getName());
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnNewButton_2.setEnabled(true);
				btnNewButton_2_1.setEnabled(true);
				btnNewButton_2_2.setEnabled(true);
				
				for (Goods g: GroupOrGoodsScreen.group.getGoods()) {
					if (g.getName().equals((String)comboBox.getSelectedItem())) {
						GoodsScreen.good = g;
						break;
					}
				}
				indexOfGood = comboBox.getSelectedIndex();
			}
		});
		
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Або додайте новий товар");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel_3.setBounds(302, 445, 254, 62);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Додати");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.setBounds(350, 517, 159, 62);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddingNewGoods().start();
			}
		});
		
		btnNewButton_2 = new JButton("Редагувати");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_2.setBounds(218, 408, 118, 32);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditingGoods().start();	
			}
		});
		
		btnNewButton_2_1 = new JButton("Видалити");
		btnNewButton_2_1.setEnabled(false);
		btnNewButton_2_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_2_1.setBounds(525, 408, 111, 32);
		frame.getContentPane().add(btnNewButton_2_1);
		
		btnNewButton_2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GroupOrGoodsScreen.group.getGoods().remove(indexOfGood);
				JOptionPane.showConfirmDialog(btnNewButton_2_1, "Товар успішно видалено", "Видалено", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				GoodsScreen.comboBox.removeAllItems();
				for (int i = 0; i < GroupOrGoodsScreen.group.getGoods().size(); i++)
					GoodsScreen.comboBox.addItem(GroupOrGoodsScreen.group.getGoods().get(i).getName());
				btnNewButton_2.setEnabled(false);
				btnNewButton_2_1.setEnabled(false);
				btnNewButton_2_2.setEnabled(false);
				
				try {
					try (PrintWriter printOut = new PrintWriter(GroupOrGoodsScreen.group.getName() + ".txt")) {
						printOut.println(GroupOrGoodsScreen.group.toString());
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_2_2 = new JButton("Змінити кількість");
		btnNewButton_2_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_2_2.setEnabled(false);
		btnNewButton_2_2.setBounds(350, 408, 159, 32);
		frame.getContentPane().add(btnNewButton_2_2);
		
		btnNewButton_2_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ChangeAmountOfGoods().start();
				
			}
		});
	}
	
	public void reload() {
		GoodsScreen.this.frame.dispose();
		GoodsScreen.this.start();
	}

}
