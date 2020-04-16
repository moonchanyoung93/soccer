package ch22_oracle_jdbc;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	//변수 추가
	private ScoreList frm;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScoreSave frame = new ScoreSave();
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
	
	public ScoreSave(ScoreList frm) {
		this();//ScoreSave의 화면을 보이기 위해 처리
		//ScoreSave() 기본생성자 호출
		this.frm = frm;
	}
	
	
	public ScoreSave() {
		//창닫기(현재창만 닫기)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(14, 12, 62, 18);
		contentPane.add(lblNewLabel);
		
		tfStudentNo = new JTextField();
		tfStudentNo.setBounds(90, 9, 116, 24);
		contentPane.add(tfStudentNo);
		tfStudentNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(14, 55, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(90, 52, 116, 24);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("국어");
		lblNewLabel_2.setBounds(14, 103, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		tfKor = new JTextField();
		tfKor.setBounds(90, 100, 116, 24);
		contentPane.add(tfKor);
		tfKor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("영어");
		lblNewLabel_3.setBounds(14, 154, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		tfEng = new JTextField();
		tfEng.setBounds(90, 151, 116, 24);
		contentPane.add(tfEng);
		tfEng.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수학");
		lblNewLabel_4.setBounds(14, 205, 62, 18);
		contentPane.add(lblNewLabel_4);
		
		tfMat = new JTextField();
		tfMat.setBounds(90, 202, 116, 24);
		contentPane.add(tfMat);
		tfMat.setColumns(10);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한 값
				String student_no=tfStudentNo.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText());
				int eng=Integer.parseInt(tfEng.getText());
				int mat=Integer.parseInt(tfMat.getText());
				ScoreDTO dto=new ScoreDTO(student_no, name, kor, eng, mat);
				ScoreDAO dao=new ScoreDAO();
				int result=dao.insertScore(dto);
				if(result==1) {//insert가 성공하면
					JOptionPane.showMessageDialog(
							ScoreSave.this, "저장되었습니다.");
					//현재 프레임을 닫기 전에 ScoreList의 refreshTable()호출
					frm.refreshTable();
					//현재 프레임을 닫음
					dispose();
				}
			}
		});
		btnSave.setBounds(90, 270, 105, 27);
		contentPane.add(btnSave);
	}
}
