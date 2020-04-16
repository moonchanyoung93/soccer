package project11_11;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SoccerList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private SoccerDAO dao;
	private Vector<String> cols;
	private JTextField tfSearch;
	private DefaultTableModel model;
	private SoccerDTO dto;

	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfPosition;
	private JTextField tfTeam;
	private JTextField tfAge;
	private JTextField tfGoal;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoccerList frame = new SoccerList();
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
	public SoccerList() {
		setTitle("선수명단");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 182, 560, 297);
		contentPane.add(scrollPane);

		dao=new SoccerDAO();
		cols=new Vector<String>();
		cols.add("번호");
		cols.add("이름");
		cols.add("포지션");
		cols.add("소속");
		cols.add("나이");
		cols.add("골");
		list();


		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfName.setEditable(false);
				tfNo.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
				tfPosition.setText(table.getValueAt(idx, 2)+"");
				tfTeam.setText(table.getValueAt(idx, 3)+"");
				tfAge.setText(table.getValueAt(idx, 4)+"");
				tfGoal.setText(table.getValueAt(idx, 5)+"");
			}
		});
		scrollPane.setViewportView(table);




		JLabel label = new JLabel("선수명");
		label.setFont(new Font("굴림", Font.PLAIN, 18));
		label.setBounds(22, 489, 72, 23);
		contentPane.add(label);

		tfSearch = new JTextField();
		tfSearch.setBounds(94, 492, 116, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnNewButton_2 = new JButton("찾기");
		btnNewButton_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnNewButton_2.setBounds(222, 491, 97, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("나가기");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(420, 489, 152, 23);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setBounds(29, 19, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(29, 44, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("포지션");
		lblNewLabel_2.setBounds(29, 69, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("소속팀");
		lblNewLabel_3.setBounds(29, 94, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("나이");
		lblNewLabel_4.setBounds(29, 119, 57, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("골");
		lblNewLabel_5.setBounds(29, 144, 57, 15);
		contentPane.add(lblNewLabel_5);

		tfNo = new JTextField();
		tfNo.setBounds(111, 16, 116, 21);
		contentPane.add(tfNo);
		tfNo.setColumns(10);

		tfName = new JTextField();
		tfName.setBounds(111, 41, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);

		tfPosition = new JTextField();
		tfPosition.setBounds(111, 66, 116, 21);
		contentPane.add(tfPosition);
		tfPosition.setColumns(10);

		tfTeam = new JTextField();
		tfTeam.setBounds(111, 91, 116, 21);
		contentPane.add(tfTeam);
		tfTeam.setColumns(10);

		tfAge = new JTextField();
		tfAge.setBounds(111, 116, 116, 21);
		contentPane.add(tfAge);
		tfAge.setColumns(10);

		tfGoal = new JTextField();
		tfGoal.setBounds(111, 141, 116, 21);
		contentPane.add(tfGoal);
		tfGoal.setColumns(10);

		JButton btnSave = new JButton("선수 등록");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertSoccer(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(SoccerList.this, "선수가 등록되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnSave.setBounds(284, 19, 97, 23);
		contentPane.add(btnSave);

		JButton btnUpdate = new JButton("선수 수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateSoccer(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(SoccerList.this, "선수 정보가 수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnUpdate.setBounds(284, 69, 97, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("선수 삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(SoccerList.this, "선수를 삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteSoccer(name);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(SoccerList.this, "삭제되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnDelete.setBounds(284, 119, 97, 23);
		contentPane.add(btnDelete);

		JButton btnNewButton = new JButton("선수 정보");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				if(idx==-1) {
					JOptionPane.showMessageDialog(SoccerList.this, "선수를 선택하세요.");
					return;
				}else {
					String name=String.valueOf(table.getValueAt(idx, 1));
					SoccerInfo si=new SoccerInfo(SoccerList.this, name);
					si.setVisible(true);
					si.setLocation(200, 200);
				}
			}
		});
		btnNewButton.setBounds(443, 19, 97, 140);
		contentPane.add(btnNewButton);

	}




	public void search() {
		String name=tfSearch.getText();
		model=new DefaultTableModel(dao.searchSoccer(name),cols);
		table.setModel(model);


	}

	public void list() {
		model=new DefaultTableModel(dao.listSoccer(),cols) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	public void input() {
		int no=Integer.parseInt(tfNo.getText());
		String name=tfName.getText();
		String position=tfPosition.getText();
		String team=tfTeam.getText();
		int age=Integer.parseInt(tfAge.getText());
		int goal=Integer.parseInt(tfGoal.getText());
		dto=new SoccerDTO(no, age, goal, name, position, team);
	}

	public void clear() {
		tfNo.setText("");
		tfName.setText("");
		tfPosition.setText("");
		tfTeam.setText("");
		tfAge.setText("");
		tfGoal.setText("");
		tfName.requestFocus();
		tfNo.setEditable(true);
	}
}
