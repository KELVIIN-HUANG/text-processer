package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import bean.TableColum;
import bean.TableInfo;
import dao.tableDao;
import util.ConverterUtil;

public class TestFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	public static String content = "";
	public static JList<String> listColumnName;
	public static JList<TableInfo> listTableName;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private static TestFrame frame;
	private JButton btnNewButton_5;
	private JButton btnAdd;
	private JButton btnNewButton_2;
	private JButton btnToSequence;

	public static TestFrame getFrame() {
		if (frame == null) {
			frame = new TestFrame();
		}
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitGlobalFont(new Font("", Font.PLAIN, 17));
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					final TestFrame frame = TestFrame.getFrame();
					frame.setVisible(true);

				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void setSysClipboardText(final String writeMe) {
		final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		final Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	/**
	 * Create the frame.
	 */
	private static void InitGlobalFont(final Font font) {
		final FontUIResource fontRes = new FontUIResource(font);
		for (final Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			final Object key = keys.nextElement();
			final Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1243, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnToForm = new JButton("生成");
		btnToForm.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				List<String> listValues = listColumnName.getSelectedValuesList();
				String x = "";
				String y = "";

				for (int j = 0; j < listValues.size(); j++) {
					String text = listValues.get(j);
					text = ConverterUtil.toGetParameter(text);
					y += text + "\n";
				}

				textPane_1.setText(x);
				textPane_2.setText(y);
			}
		});
		btnToForm.setBounds(4, 100, 107, 37);
		contentPane.add(btnToForm);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(589, 56, 607, 215);
		contentPane.add(scrollPane);

		textPane_1 = new JTextPane();
		scrollPane.setViewportView(textPane_1);
		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(407, 21, 183, 501);
		contentPane.add(scrollPane_1);
		
				listColumnName = new JList<>();
				scrollPane_1.setViewportView(listColumnName);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				PopupFrame1.getFrame1().setVisible(true);
			}
		});
		btnAdd.setBounds(4, 60, 107, 37);
		contentPane.add(btnAdd);

		btnNewButton_2 = new JButton("刷新");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				iniList();
			}
		});
		btnNewButton_2.setBounds(4, 20, 107, 37);
		contentPane.add(btnNewButton_2);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(589, 270, 607, 252);
		contentPane.add(scrollPane_2);

		textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);

		final JButton btnNewButton_3 = new JButton("复制文本1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final String string = textPane_1.getText();
				setSysClipboardText(string);
			}
		});
		btnNewButton_3.setBounds(881, 528, 153, 37);
		contentPane.add(btnNewButton_3);

		final JButton btnNewButton_4 = new JButton("复制文本2");
		btnNewButton_4.setBounds(1043, 528, 153, 37);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final String string = textPane_2.getText();
				setSysClipboardText(string);
			}
		});

		btnNewButton_5 = new JButton("转换驼峰");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ListModel<String> listModel = listColumnName.getModel();
				DefaultListModel<String> model = new DefaultListModel<>();
				for (int i = 0; i < listModel.getSize(); i++) {
					model.add(i, lineToHump(listModel.getElementAt(i)));
				}
				listColumnName.setModel(model);
			}
		});
		btnNewButton_5.setBounds(114, 60, 107, 37);
		contentPane.add(btnNewButton_5);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(223, 21, 183, 501);
		contentPane.add(scrollPane_3);

		listTableName = new JList<>();
		listTableName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
				final List<TableColum> colums = tableDao.gTablecolums(listTableName.getSelectedValue().getTableName());
				for (int i = 0; i < colums.size(); i++) {
					defaultListModel.add(i, colums.get(i).getColumnName());
				}
				listColumnName.setModel(defaultListModel);
			}
		});
		scrollPane_3.setViewportView(listTableName);

		btnToSequence = new JButton("生成序列");
		btnToSequence.setBounds(114, 100, 107, 37);
		contentPane.add(btnToSequence);
		btnToSequence.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tables = textPane_1.getText();
				ConverterUtil.splitByLineBreak(tables);
				List<String> sequences = ConverterUtil.toSequencesWith100(ConverterUtil.splitByLineBreak(tables));
				tables = "";
				for (String string : sequences) {
					tables = tables + string + "\n";
				}
				textPane_2.setText(tables);
			}
		});


		btnNewButton_9 = new JButton("连接");
		btnNewButton_9.setBounds(114, 20, 107, 37);
		contentPane.add(btnNewButton_9);

		JButton btnPreFix = new JButton("前缀合成");
		btnPreFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prefix = txtPre.getText();
				String text1 = textPane_1.getText();
				String text2 = textPane_2.getText();
				String newString = "";
				List<String> strings = ConverterUtil.splitByLineBreak(text1);
				List<String> strings2 = ConverterUtil.splitByLineBreak(text2);
				if (strings.size() == strings2.size()) {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += prefix + strings2.get(i) + "\n";
					}
				} else {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += prefix + strings.get(i) + "\n";
					}
				}

				textPane_2.setText(newString);
			}
		});
		btnPreFix.setBounds(4, 140, 107, 37);
		contentPane.add(btnPreFix);

		txtPre = new JTextField();
		txtPre.setText("prefix");
		txtPre.setBounds(591, 21, 309, 35);
		contentPane.add(txtPre);
		txtPre.setColumns(10);

		txtSufix = new JTextField();
		txtSufix.setText("suffix");
		txtSufix.setColumns(10);
		txtSufix.setBounds(901, 21, 295, 35);
		contentPane.add(txtSufix);

		btnSurfix = new JButton("后缀合成");
		btnSurfix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1 = textPane_1.getText();
				String suffix = txtSufix.getText();
				String text2 = textPane_2.getText();
				String newString = "";
				List<String> strings = ConverterUtil.splitByLineBreak(text1);
				List<String> strings2 = ConverterUtil.splitByLineBreak(text2);
				if (strings.size() == strings2.size()) {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += strings2.get(i) + suffix + "\n";
					}
				} else {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += strings.get(i) + suffix + "\n";
					}
				}

				textPane_2.setText(newString);

			}
		});
		btnSurfix.setBounds(114, 140, 107, 37);
		contentPane.add(btnSurfix);

		btnPrefix_3 = new JButton("清空");
		btnPrefix_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane_1.setText("");
				textPane_2.setText("");
			}
		});
		btnPrefix_3.setBounds(792, 528, 82, 37);
		contentPane.add(btnPrefix_3);

		btnPrefix_4 = new JButton("自增加");
		btnPrefix_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1 = textPane_1.getText();
				String text2 = textPane_2.getText();
				String newString = "";
				List<String> strings = ConverterUtil.splitByLineBreak(text1);
				List<String> strings2 = ConverterUtil.splitByLineBreak(text2);
				if (strings.size() == strings2.size()) {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += strings2.get(i) + strings.get(i) + "\n";
					}
				} else {
					for (int i = 0; i < strings.size(); i++) {
						strings.set(i, strings.get(i).replaceAll("\r", ""));
						newString += strings.get(i) + strings.get(i) + "\n";
					}
				}

				textPane_2.setText(newString);

			}
		});
		btnPrefix_4.setBounds(682, 528, 107, 37);
		contentPane.add(btnPrefix_4);
		iniList();
	}

	public static void iniList() {
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		final List<TableInfo> tables = tableDao.gettablenams();
		final DefaultListModel<TableInfo> listModel = new DefaultListModel<>();

		for (final TableInfo tableInfo : tables) {
			listModel.addElement(tableInfo);
			model.addElement(tableInfo.getTableName());
			content += tableInfo.getTableName() + "\n";
		}
		listTableName.setModel(listModel);
		PopupFrame1.getFrame1();
		PopupFrame1.comboBox.setModel(model);
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private JButton btnPrefix;
	private JButton btnNewButton_9;
	private JTextField txtPre;
	private JTextField txtSufix;
	private JButton btnSurfix;
	private JButton btnPrefix_3;
	private JButton btnPrefix_4;

	public static String lineToHump(String str) {
		str = str.toLowerCase();
		final Matcher matcher = linePattern.matcher(str);
		final StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public JTextField getTxtPre() {
		return txtPre;
	}

	public JTextField getTxtSufix() {
		return txtSufix;
	}
}
