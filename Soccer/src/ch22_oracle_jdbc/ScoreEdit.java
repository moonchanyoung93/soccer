package ch22_oracle_jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	//변수 추가
	private ScoreList parent;
	//화면 갱신용
	private ScoreDAO dao;
	private ScoreDTO dto;
	private String student_no;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScoreEdit frame = new ScoreEdit();
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
	public ScoreEdit(ScoreList parent, String student_no) {
		//ScoreList 프레임의 주소값을 저장
		this.parent = parent;
		this.student_no = student_no;
		System.out.println(parent);
		System.out.println(dto);
		
		setTitle("점수편집");
		//현재 창만 닫는 옵션으로 변경
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 383);
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
		lblNewLabel_2.setBounds(14, 106, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		tfKor = new JTextField();
		tfKor.setBounds(90, 103, 116, 24);
		contentPane.add(tfKor);
		tfKor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("영어");
		lblNewLabel_3.setBounds(14, 159, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		tfEng = new JTextField();
		tfEng.setBounds(90, 156, 116, 24);
		contentPane.add(tfEng);
		tfEng.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수학");
		lblNewLabel_4.setBounds(14, 211, 62, 18);
		contentPane.add(lblNewLabel_4);
		
		tfMat = new JTextField();
		tfMat.setBounds(90, 208, 116, 24);
		contentPane.add(tfMat);
		tfMat.setColumns(10);

		//dto값을 텍스트필드에 출력
		dao=new ScoreDAO();
		dto=dao.viewScore(student_no);
		tfStudentNo.setText(dto.getStudent_no());
		tfName.setText(dto.getName());
		tfKor.setText(dto.getKor()+"");//int 점수를 ""로 String화함
		tfEng.setText(dto.getEng()+"");
		tfMat.setText(dto.getMat()+"");
		
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudentNo.getText();
				String name=tfName.getText();
				int kor=Integer.parseInt(tfKor.getText());
				int eng=Integer.parseInt(tfEng.getText());
				int mat=Integer.parseInt(tfMat.getText());
				ScoreDTO dto= new ScoreDTO(student_no, name, kor, eng, mat);
				int result=dao.updateScore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							ScoreEdit.this, "수정되었습니다.");
					parent.refreshTable();//ScoreList 갱신
					dispose();//현재 프레임을 닫는 Window 메소드
				}
			}
		});
		btnUpdate.setBounds(14, 269, 105, 27);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String student_no=tfStudentNo.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(
						ScoreEdit.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteScore(student_no);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(
							ScoreEdit.this, "삭제되었습니다.");
					parent.refreshTable();//ScoreList 갱신
					dispose();
				}
			}
		});
		btnDelete.setBounds(133, 269, 105, 27);
		contentPane.add(btnDelete);
	}

}
