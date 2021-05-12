package AutomatedWorkspace;
/* Screen for changing amount of goods (GUI of Automated Workspace)
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ChangeAmountOfGoods {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeAmountOfGoods window = new ChangeAmountOfGoods();
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
	public ChangeAmountOfGoods() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 230);
		frame.getContentPane().setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(176, 86, 44, 29);
		spinner.setModel(new SpinnerNumberModel(1, 1, null, 1));
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel = new JLabel("Зміна кількості товару");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(101, 10, 195, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Додати");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnNewButton.setBounds(59, 137, 87, 34);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GoodsScreen.good.setAmount(GoodsScreen.good.getAmount() + (int)spinner.getValue());
				JOptionPane.showConfirmDialog(frame, "Зарахували товару \"" + GoodsScreen.good.getName() + 
						"\" " + (int)spinner.getValue() + "шт.", "Зараховано", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				try {
					try (PrintWriter printOut = new PrintWriter(GroupOrGoodsScreen.group.getName() + ".txt")) {
						printOut.println(GroupOrGoodsScreen.group.toString());
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				ChangeAmountOfGoods.this.frame.dispose();
			}
		});
		
		
		
		JButton btnNewButton_1 = new JButton("Видалити");
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnNewButton_1.setBounds(247, 137, 96, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Введіть кількість товару та оберіть, що з ним зробити ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(16, 58, 364, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GoodsScreen.good.setAmount(GoodsScreen.good.getAmount() - (int)spinner.getValue());
				if (GoodsScreen.good.getAmount() < 0)
					GoodsScreen.good.setAmount(0);
				JOptionPane.showConfirmDialog(frame, "Продали товару \"" + GoodsScreen.good.getName() + 
						"\" " + (int)spinner.getValue() + "шт.", "Продано", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				try {
					try (PrintWriter printOut = new PrintWriter(GroupOrGoodsScreen.group.getName() + ".txt")) {
						printOut.println(GroupOrGoodsScreen.group.toString());
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				ChangeAmountOfGoods.this.frame.dispose();
			}
		});
	}
}
