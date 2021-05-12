package AutomatedWorkspace;
/* Screen for adding new goods (GUI of Automated Workspace)
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddingNewGoods {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddingNewGoods window = new AddingNewGoods();
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
	public AddingNewGoods() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 280);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додати новий товар");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 10, 165, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Назва нового товару:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(43, 39, 133, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Опис:");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(43, 75, 64, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Виробник:");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(43, 104, 75, 26);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ціна за одиницю (грн): ");
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(43, 140, 133, 26);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(225, 44, 118, 19);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(225, 76, 118, 19);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(225, 109, 118, 19);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(225, 145, 118, 19);
		frame.getContentPane().add(textField_3);
		textField_3.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			         e.consume();  // ignore event
			      }
			   }
			});
		
		JButton btnNewButton = new JButton("Додати");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(152, 207, 85, 26);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_4 = new JLabel("Кількість одиниць:");
		lblNewLabel_1_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_4.setBounds(43, 176, 133, 26);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(225, 178, 118, 19);
		frame.getContentPane().add(textField_4);
		textField_4.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			         e.consume();  // ignore event
			      }
			   }
			});
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showConfirmDialog(frame, "Поле з назвою товару пусте!\nСпробуйте ще раз", "Помилка", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				} else {
					if (Storage.checkGoodsName(textField.getText())) {
						GroupOrGoodsScreen.group.addGood(new Goods(textField.getText()));
						if (GroupOrGoodsScreen.group.getGoods().isEmpty()) {
							GroupOrGoodsScreen.group.getGoods().get(0).setDescription(textField_1.getText());
							GroupOrGoodsScreen.group.getGoods().get(0).setManufacturer(textField_2.getText());
							if (!textField_3.getText().equals(""))
								GroupOrGoodsScreen.group.getGoods().get(0).setPriceForOne(textField_3.getText());
							if (!textField_4.getText().equals(""))
								GroupOrGoodsScreen.group.getGoods().get(0).setAmount(textField_4.getText());
						} else {
							GroupOrGoodsScreen.group.getGoods().get(GroupOrGoodsScreen.group.getGoods().size()-1).setDescription(textField_1.getText());
							GroupOrGoodsScreen.group.getGoods().get(GroupOrGoodsScreen.group.getGoods().size()-1).setManufacturer(textField_2.getText());
							if (!textField_3.getText().equals(""))
								GroupOrGoodsScreen.group.getGoods().get(GroupOrGoodsScreen.group.getGoods().size()-1).setPriceForOne(textField_3.getText());
							if (!textField_4.getText().equals(""))
								GroupOrGoodsScreen.group.getGoods().get(GroupOrGoodsScreen.group.getGoods().size()-1).setAmount(textField_4.getText());
						}
						GoodsScreen.comboBox.addItem(GroupOrGoodsScreen.group.getGoods().get(GroupOrGoodsScreen.group.getGoods().size()-1)
								.getName());
						try {
							try (PrintWriter printOut = new PrintWriter(GroupOrGoodsScreen.group.getName() + ".txt")) {
								printOut.println(GroupOrGoodsScreen.group.toString());
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(frame, "Товар з таким ім'ям вже існує!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
//				System.out.println(Storage.toStringGoods());
				AddingNewGoods.this.frame.dispose();
			}
		});
	}
}
