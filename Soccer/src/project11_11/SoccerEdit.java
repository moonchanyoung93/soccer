package project11_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ch17.MyColorAction;

public class SoccerEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfPosition;
	private JTextField tfTeam;
	private JTextField tfAge;
	private JTextField tfGoal;

	private SoccerList sl;

	private SoccerDAO dao;
	private SoccerDTO dto;
	private String name;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					SoccerEdit frame = new SoccerEdit();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public SoccerEdit(SoccerList sl, String name) {
		this.sl=sl;
		this.name=name;

		setTitle("선수 정보수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setBounds(52, 41, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(52, 78, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("포지션");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(52, 121, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("소속팀");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(52, 162, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("나이");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(52, 198, 57, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("골");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(52, 233, 57, 15);
		contentPane.add(lblNewLabel_5);

		tfNo = new JTextField();
		tfNo.setBounds(133, 39, 116, 21);
		contentPane.add(tfNo);
		tfNo.setColumns(10);

		tfName = new JTextField();
		tfName.setBounds(133, 76, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);

		tfPosition = new JTextField();
		tfPosition.setBounds(133, 119, 116, 21);
		contentPane.add(tfPosition);
		tfPosition.setColumns(10);

		tfTeam = new JTextField();
		tfTeam.setBounds(133, 160, 116, 21);
		contentPane.add(tfTeam);
		tfTeam.setColumns(10);

		tfAge = new JTextField();
		tfAge.setBounds(133, 196, 116, 21);
		contentPane.add(tfAge);
		tfAge.setColumns(10);

		tfGoal = new JTextField();
		tfGoal.setBounds(133, 231, 116, 21);
		contentPane.add(tfGoal);
		tfGoal.setColumns(10);



		JRadioButton radioButton = new JRadioButton("경고");
		radioButton.addActionListener(new MyColorAction(this, Color.yellow));
		radioButton.setBounds(121, 261, 57, 23);
		contentPane.add(radioButton);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("퇴장");
		rdbtnNewRadioButton.addActionListener(new MyColorAction(this, Color.red));
		rdbtnNewRadioButton.setBounds(196, 261, 73, 23);
		contentPane.add(rdbtnNewRadioButton);


		dao=new SoccerDAO();
		dto=dao.viewSoccer(name);
		tfNo.setText(dto.getNo()+"");
		tfName.setText(dto.getName());
		tfPosition.setText(dto.getPosition());
		tfTeam.setText(dto.getTeam());
		tfAge.setText(dto.getAge()+"");
		tfGoal.setText(dto.getGoal()+""); //int 를 ""로 String화 함


		JButton btnNewButton = new JButton("완료");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int no=Integer.parseInt(tfNo.getText());
				String name=tfName.getText();
				String position=tfPosition.getText();
				String team=tfTeam.getText();
				int age=Integer.parseInt(tfAge.getText());
				int goal=Integer.parseInt(tfGoal.getText());
				SoccerDTO dto=new SoccerDTO(no, age, goal, name, position, team);
				int result=dao.updateSoccer(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(SoccerEdit.this, "완료되었습니다.");
					sl.refreshTable();
					dispose();
				}
			}
		});
		btnNewButton.setBounds(52, 304, 97, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(172, 304, 97, 23);
		contentPane.add(btnNewButton_1);
	}



}


