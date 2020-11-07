package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import dao.tableDao;
import javax.swing.JTextPane;

public class PopupFrame1 extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static PopupFrame1 popupFrame1;
	private JButton btnNewButton_2;
	public String content = "";
	public static JComboBox<String> comboBox;
	private JTextPane textPane;
	private JTextPane textPane_1;

	/**
	 * Launch the application.
	 */
	public static PopupFrame1 getFrame1() {
		if (popupFrame1 == null) {
			popupFrame1 = new PopupFrame1();
		}
		return popupFrame1;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupFrame1 frame = new PopupFrame1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PopupFrame1() {

		setDefaultCloseOperation(0);
		setBounds(100, 100, 533, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("添加表格");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textPane.getText()==null||textPane.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "不能为空");
				}else {
					String text = textPane.getText();
					String[] strings= text.split("\n");
					
					for (String string : strings) {
						string.replace("\n", "");
						tableDao.addTable(string);
					}
					JOptionPane.showMessageDialog(null, "添加完成");
					TestFrame.iniList();
				}
			
			}
		});
		btnNewButton.setBounds(20, 21, 175, 37);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("添加字段");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textPane_1.getText()==null||textPane_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "不能为空");
				}else {
					Long id = tableDao.queryTableId((String)comboBox.getSelectedItem());
					String name = textPane_1.getText();
					
					String[] strings= name.split("\n");
					for (String string : strings) {
						string.replace("\n", "");
						tableDao.addColum(id, string);
					}
					 
					 JOptionPane.showMessageDialog(null, "添加成功");
				}
			
			}
		});
		btnNewButton_1.setBounds(204, 21, 175, 37);
		contentPane.add(btnNewButton_1);

		comboBox = new JComboBox<>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		// List<TableInfo> tables = tableDao.getTables();

		// for (TableInfo tableInfo : tables) {
		// 	model.addElement(tableInfo.getTableName());
		// 	content += tableInfo.getTableName() + "\n";
		// }
		comboBox.setModel(model);
		comboBox.setBounds(205, 56, 173, 35);
		contentPane.add(comboBox);

		btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupFrame1.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(387, 21, 111, 37);
		contentPane.add(btnNewButton_2);
		
		textPane = new JTextPane();
		textPane.setBounds(20, 56, 174, 370);
		contentPane.add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(205, 91, 173, 335);
		contentPane.add(textPane_1);
	}

	public JTextPane getTextPane() {
		return textPane;
	}
	public JTextPane getTextPane_1() {
		return textPane_1;
	}
}
