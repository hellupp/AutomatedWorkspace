package AutomatedWorkspace;
/* Screen for editing goods (GUI of Automated Workspace)
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditingGoods {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditingGoods window = new EditingGoods();
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
	public EditingGoods() {
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
		
		JLabel lblNewLabel = new JLabel("Редагувати товар");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 10, 165, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Нова назва товару*");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(43, 39, 133, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Опис*");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(43, 75, 85, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Виробник*");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(43, 104, 75, 26);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ціна за одиницю (грн)*");
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
		
		btnNewButton = new JButton("Змінити");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(152, 207, 85, 26);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					if (Storage.checkGoodsName(textField.getText())) {
						GoodsScreen.good.setName(textField.getText());
					} else {
						JOptionPane.showConfirmDialog(frame, "Товар з таким ім'ям вже існує!\nСпробуйте ще раз", "Помилка", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
				if (!textField_1.getText().equals(""))
					GoodsScreen.good.setDescription(textField_1.getText());
				if (!textField_2.getText().equals(""))
					GoodsScreen.good.setManufacturer(textField_2.getText());
				if (!textField_3.getText().equals(""))
					GoodsScreen.good.setPriceForOne(textField_3.getText());
				
				GoodsScreen.comboBox.removeAllItems();
				for (int i = 0; i < GroupOrGoodsScreen.group.getGoods().size(); i++)
					GoodsScreen.comboBox.addItem(GroupOrGoodsScreen.group.getGoods().get(i).getName());
				EditingGoods.this.frame.dispose();
				
				try {
					try (PrintWriter printOut = new PrintWriter(GroupOrGoodsScreen.group.getName() + ".txt")) {
						printOut.println(GroupOrGoodsScreen.group.toString());
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_1_3_1 = new JLabel("*якщо поле не потрібно змінювати - залишити його пустим");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_1_3_1.setBounds(25, 176, 339, 26);
		frame.getContentPane().add(lblNewLabel_1_3_1);
	}
}
