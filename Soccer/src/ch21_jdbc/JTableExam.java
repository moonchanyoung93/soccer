package ch21_jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class JTableExam extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector data,col;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableExam frame = new JTableExam();
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
	public JTableExam() {
		col=new Vector();//벡터 생성
		 //타이틀 구성
		col.add("학번");
		col.add("이름");
		col.add("국어");
		col.add("영어");
		col.add("수학");
		col.add("총점");
		col.add("평균");
		
		ScoreDAO dao=new ScoreDAO();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//테이블에 입력될 데이터 모델 생성
		DefaultTableModel model=new DefaultTableModel(dao.listScore(),col) {
			public boolean isCellEditable(int row, int column) {//셀 편집이 안되도록 변경
			return false;
		}
	};	
	
		table = new JTable(model);
		scrollPane.setViewportView(table);
	}

}
