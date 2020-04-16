package project11_11;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SoccerSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfPosition;
	private JTextField tfTeam;
	private JTextField tfAge;
	private JTextField tfGoal;

	private SoccerList sl;
	private Vector<String> cols;
	private DefaultTableModel model;
	private SoccerDAO dao;
	private SoccerDTO dto;
	private JTable table;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SoccerSave frame = new SoccerSave();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public SoccerSave(SoccerList sl) {
		this();
		this.sl=sl;
	}

	/**
	 * Create the frame.
	 */
	public SoccerSave() {
		setTitle("선수등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 251, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setBounds(25, 33, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(25, 84, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("포지션");
		lblNewLabel_2.setBounds(25, 138, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("소속팀");
		lblNewLabel_3.setBounds(25, 190, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("나이");
		lblNewLabel_4.setBounds(25, 241, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("골");
		lblNewLabel_5.setBounds(25, 287, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfNo = new JTextField();
		tfNo.setBounds(94, 30, 116, 21);
		contentPane.add(tfNo);
		tfNo.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(94, 81, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPosition = new JTextField();
		tfPosition.setBounds(94, 135, 116, 21);
		contentPane.add(tfPosition);
		tfPosition.setColumns(10);
		
		tfTeam = new JTextField();
		tfTeam.setBounds(94, 187, 116, 21);
		contentPane.add(tfTeam);
		tfTeam.setColumns(10);
		
		tfAge = new JTextField();
		tfAge.setBounds(94, 238, 116, 21);
		contentPane.add(tfAge);
		tfAge.setColumns(10);
		
		tfGoal = new JTextField();
		tfGoal.setBounds(94, 284, 116, 21);
		contentPane.add(tfGoal);
		tfGoal.setColumns(10);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int no=Integer.parseInt(tfNo.getText());
				String name=tfName.getText();
				String position=tfPosition.getText();
				String team=tfTeam.getText();
				int age=Integer.parseInt(tfAge.getText());
				int goal=Integer.parseInt(tfGoal.getText());
				
				SoccerDTO dto=new SoccerDTO(no, age, goal, name, position, team);
				SoccerDAO dao=new SoccerDAO();
				int result=dao.insertSoccer(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(SoccerSave.this, "등록되었습니다.");
					model=new DefaultTableModel(dao.listSoccer(),cols);
					sl.refreshTable();
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(12, 326, 97, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("종료");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(126, 326, 97, 34);
		contentPane.add(btnNewButton_1);
	}

}
