package ch21_jdbc;

import java.awt.EventQueue;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfStudentNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMat;
	private JTextField tfSearch;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearch;
	//추가
	private ScoreDAO dao;
	private Vector<String> col;
	private DefaultTableModel model;
	private ScoreDTO dto;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreList frame = new ScoreList();
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
	public ScoreList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 232, 454, 251);
		contentPane.add(scrollPane);
		
		//추가
		dao=new ScoreDAO();//dao 인스턴스 생성
		//제목열을 위한 벡터 생성
		col=new Vector<String>();
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		//JTable에 데이터를 출력시키기 위한 테이블 모델 생성
		list();
		//Jtable에 데이터 모델을 연결
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//현재 클릭한 행의 인덱스 값
				int idx=table.getSelectedRow();
				//수정모드에서는 학번 입력 제한되도록 
				//텍스트필드입력기능을 비활성화(false)처리
				tfStudentNo.setEditable(false);
				tfStudentNo.setText(table.getValueAt(idx, 0)+"");
				//0행0열 학번, getValueAt은 Object 타입이다.
				//setText는 String타입이기 때문에 타입을 처리를 위해
				//+""처리하면 간단히 스트링 타입으로 맞춰줄 수 있다.
				tfName.setText(table.getValueAt(idx, 1)+"");//0행1열 이름
				tfKor.setText(table.getValueAt(idx, 2)+"");//0행2열 국어
				tfEng.setText(table.getValueAt(idx, 3)+"");//0행3열 영어
				tfMat.setText(table.getValueAt(idx, 4)+"");//0행4열 수학
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBounds(14, 12, 62, 18);
		contentPane.add(lblNewLabel);
		
		tfStudentNo = new JTextField();
		tfStudentNo.setBounds(90, 9, 116, 24);
		contentPane.add(tfStudentNo);
		tfStudentNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(14, 42, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(90, 39, 116, 24);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("국어");
		lblNewLabel_2.setBounds(14, 72, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		tfKor = new JTextField();
		tfKor.setBounds(90, 69, 116, 24);
		contentPane.add(tfKor);
		tfKor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("영어");
		lblNewLabel_3.setBounds(14, 102, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		tfEng = new JTextField();
		tfEng.setBounds(90, 99, 116, 24);
		contentPane.add(tfEng);
		tfEng.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수학");
		lblNewLabel_4.setBounds(14, 137, 62, 18);
		contentPane.add(lblNewLabel_4);
		
		tfMat = new JTextField();
		tfMat.setBounds(90, 134, 116, 24);
		contentPane.add(tfMat);
		tfMat.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("이름을 입력하세요");
		lblNewLabel_5.setBounds(14, 184, 132, 18);
		contentPane.add(lblNewLabel_5);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSearch.setBounds(160, 181, 116, 24);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한 값을 dto에 저장
				input();
				//레코드 저장
				int result=dao.insertScore(dto);
				if(result==1) {//insert 성공
					JOptionPane.showMessageDialog(
							ScoreList.this, "저장되었습니다.");
					//테이블 목록 갱신
					list();
					table.setModel(model);
					clear();//입력값 초기화
				}
			}
		});
		btnSave.setBounds(239, 8, 105, 27);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//수정한 값
				input();
				//update method 호출
				int result=dao.updateScore(dto);
				//화면 갱신
				if(result==1) {
					JOptionPane.showMessageDialog(
							ScoreList.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
				
			}
		});
		btnUpdate.setBounds(239, 38, 105, 27);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//현재 선택한 행의 학번
				String student_no=tfStudentNo.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(
						ScoreList.this, "삭제하시겠습니까?");
				//yes 버튼을 눌렀을 때
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteScore(student_no);//삭제처리
				}
				if(result==1) {//삭제된 레코드의 갯수가 1이면
					JOptionPane.showMessageDialog(
							ScoreList.this, "삭제되었습니다.");
					list();//테이블 갱신
					table.setModel(model);
					clear();//텍스트필드 초기화
				}
			}
		});
		btnDelete.setBounds(239, 68, 105, 27);
		contentPane.add(btnDelete);
		
		btnSearch = new JButton("찾기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(307, 180, 105, 27);
		contentPane.add(btnSearch);
	}//end ScoreList()

	//메소드 추가
	public void search() {
		String name=tfSearch.getText();
		model=new DefaultTableModel(dao.searchScore(name),col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}//search()
	
	public void input() {
		String student_no=tfStudentNo.getText();
		String name=tfName.getText();
		int kor= Integer.parseInt(tfKor.getText());
		int eng= Integer.parseInt(tfEng.getText());
		int mat= Integer.parseInt(tfMat.getText());
		dto=new ScoreDTO(student_no, name, kor, eng, mat);
	}
	
	public void clear() {
		tfStudentNo.setText("");
		tfName.setText("");
		tfKor.setText("");
		tfEng.setText("");
		tfMat.setText("");
		tfStudentNo.requestFocus();//커서를 옮김
		tfStudentNo.setEditable(true);
		//수정이 끝나면 학번 입력을 활성화시킴
	}
	
	public void list() {
		model=new DefaultTableModel(dao.listScore(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}//end class
