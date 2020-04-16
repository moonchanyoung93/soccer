package ch22_oracle_jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	//변수 추가
	private ScoreDAO dao;
	private Vector cols;

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
		dao=new ScoreDAO();//dao 인스턴스 생성
		//제목열 구성
		cols=new Vector();
		cols.add("학번");
		cols.add("이름");
		cols.add("국어");
		cols.add("영어");
		cols.add("수학");
		cols.add("총점");
		cols.add("평균");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ScoreSave에 참조변수를 넘겨주면서 생성
				ScoreSave frm=new ScoreSave(ScoreList.this);
				frm.setVisible(true);//프레임을 화면에 표시
				//프레임을 표시할 좌표 지정
				frm.setLocation(200, 200);
			}
		});
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("편집");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JTable에서 선택한 행의 인덱스 값
				int idx=table.getSelectedRow();//0번째 행~
				System.out.println(idx);
				if(idx == -1) {//선택한 행이 없으면
					JOptionPane.showMessageDialog(
							ScoreList.this, "편집할 자료를 선택하세요.");
					return; //메소드 종료
				}else {//선택한 행이 있으면
					String student_no=
							String.valueOf(table.getValueAt(idx, 0));
					ScoreEdit frm=
							new ScoreEdit(ScoreList.this,student_no);
					frm.setVisible(true);
					frm.setLocation(550, 100);
				}
			}
		});
		panel.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//테이블 갱신
		refreshTable();
	}//end ScoreList()
	
	//JTable에 출력할 데이터 모델 생성
	public void refreshTable() {
		DefaultTableModel model=
				new DefaultTableModel(dao.listScore(), cols);
		//테이블에 데이터 채워짐
		table.setModel(model);
	}
	
	

}//end class
