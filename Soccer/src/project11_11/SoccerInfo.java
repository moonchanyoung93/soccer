package project11_11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SoccerInfo extends JFrame {

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
	private JPanel panel;
	private JLabel label;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SoccerInfo frame = new SoccerInfo();
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
	public SoccerInfo(SoccerList sl, String name) {
		this.sl=sl;
		this.name=name;
		
		
		
		setTitle("선수 정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dao=new SoccerDAO();
		dto=dao.viewSoccer(name);
		
		JPanel panel = new JPanel();
		this.panel=panel;
		panel.setBounds(0, 0, 306, 403);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(46, 74, 32, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2 = new JLabel("포지션");
		lblNewLabel_2.setBounds(46, 126, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("소속팀");
		lblNewLabel_3.setBounds(46, 170, 54, 15);
		panel.add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel("나이");
		label_1.setBounds(46, 214, 54, 15);
		panel.add(label_1);
		
		JLabel lblNewLabel_4 = new JLabel("골");
		lblNewLabel_4.setBounds(46, 252, 54, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("경고/퇴장");
		lblNewLabel_5.setBounds(46, 301, 78, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setBounds(46, 29, 32, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		
		tfNo = new JTextField();
		tfNo.setBounds(119, 26, 136, 26);
		panel.add(tfNo);
		tfNo.setFont(new Font("굴림", Font.PLAIN, 17));
		tfNo.setColumns(10);
		tfNo.setText(dto.getNo()+"");
		
		tfName = new JTextField();
		tfName.setBounds(119, 71, 136, 26);
		panel.add(tfName);
		tfName.setFont(new Font("굴림", Font.PLAIN, 17));
		tfName.setColumns(10);
		tfName.setText(dto.getName());
		
		tfPosition = new JTextField();
		tfPosition.setBounds(119, 123, 116, 21);
		panel.add(tfPosition);
		tfPosition.setColumns(10);
		tfPosition.setText(dto.getPosition());
		
		tfTeam = new JTextField();
		tfTeam.setBounds(119, 167, 116, 21);
		panel.add(tfTeam);
		tfTeam.setColumns(10);
		tfTeam.setText(dto.getTeam());
		
		tfAge = new JTextField();
		tfAge.setBounds(119, 211, 116, 21);
		panel.add(tfAge);
		tfAge.setColumns(10);
		tfAge.setText(dto.getAge()+"");
		
		tfGoal = new JTextField();
		tfGoal.setBounds(119, 249, 116, 21);
		panel.add(tfGoal);
		tfGoal.setColumns(10);
		tfGoal.setText(dto.getGoal()+"");
		
		
		
		JButton btnYellowCard = new JButton("경고");
		btnYellowCard.setBounds(126, 297, 71, 23);
		panel.add(btnYellowCard);
		
		JButton btnRedCard = new JButton("퇴장");
		btnRedCard.setBounds(209, 297, 71, 23);
		panel.add(btnRedCard);
		
		JButton btnNewButton = new JButton("나가기");
		btnNewButton.setBounds(88, 350, 127, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRedCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnRedCard) {
					panel.setBackground(Color.red);
				}
			}
		});
		btnYellowCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnYellowCard ) {
					panel.setBackground(Color.yellow);
				}
			}
		});
	}
}
