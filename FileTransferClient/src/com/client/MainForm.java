package com.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainForm implements ActionListener{
	//선언부
	ServerConnecter connect 	= null;/////
	String 			p_id 		= null;/////
	//프레임
	JFrame 		jf 		= new JFrame();
	//상단
	JPanel 		jp_north = new JPanel();
	JLabel 		jlb_p_id = new JLabel();  //사용자이름

	//중단1
	JPanel 		jp_online 		= new JPanel();
	JLabel 		jlb_online 		= new JLabel("온라인");
	String 		online[] 		= {"아이디"};
	DefaultTableModel dtm_online = new DefaultTableModel(online,0){
		@Override //셀 더블클릭 후 수정 안되도록 조정.
		public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
			return false;
		}
	};
	JTable 		jtb_online 		= new JTable(dtm_online);
	JScrollPane jsp_online 	= new JScrollPane(jtb_online);
	//중단2
	JPanel 		jp_offline 		= new JPanel();
	JLabel 		jlb_offline 		= new JLabel("오프라인");
	String 		offline[] 		= {"아이디"};
	DefaultTableModel dtm_offline = new DefaultTableModel(offline,0){
		@Override //셀 더블클릭 후 수정 안되도록 조정.
		public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
			return false;
		}
	}; 
	JTable 		jtb_offline 		= new JTable(dtm_offline);
	JScrollPane jsp_offline = new JScrollPane(jtb_offline);
	//하단
	JPanel	 	jp_south   		= new JPanel();
	JButton 	jbtn_chat =		 new JButton("채팅하기");

	// 생성자
	public MainForm() {
		//initDisplay();
	}
	public MainForm(ServerConnecter con, String p_id) {
		this.connect = con;
		this.p_id = p_id;
		jf.setTitle(p_id);
		jlb_p_id.setText(p_id+"님 환영합니다.");
		initDisplay();
	}
	
	//화면
	public void initDisplay() {
		//프레임
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		jf.setLayout(null);
		//jf.getContentPane().setLayout(new GridLayout(0,1));
		
		//상단
		//절대값으로 위치 선정
//		jp_north.setBackground(Color.green);
//		jp_online.setBackground(Color.blue);
//		jp_offline.setBackground(Color.yellow);
//		jp_south.setBackground(Color.white);
		
		jp_north.setBounds(0, 20, 500, 40);
		jp_online.setBounds(0, 60, 500, 200);
		jp_offline.setBounds(0, 280, 500, 200);
		jp_south.setBounds(0, 500, 500, 40);
		/////////////////
		jlb_p_id.setFont(new Font("맑은고딕",Font.BOLD,15));
		jp_north.add(jlb_p_id);

		//중단
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
		TableColumnModel tcm = jtb_online.getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
			tcm.getColumn(i).setCellRenderer(dtcr);
		} 
		tcm = null;
		tcm = jtb_offline.getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
		for(int i = 0 ; i < tcm.getColumnCount() ; i++){
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		jp_online.add(jlb_online);
		jp_online.add(jsp_online);
		jp_offline.add(jlb_offline);
		jp_offline.add(jsp_offline);
		//jtb_online.addMouseListener(this);
		jtb_online.setEnabled(false);
		jtb_offline.setEnabled(false);
		//jtb_offline.addMouseListener(this);
		
		//하단
		jbtn_chat.addActionListener(this);
		jp_south.add(jbtn_chat);

		jf.add(jp_north);
		jf.add(jp_online); //온라인 테이블 적용
		jf.add(jp_offline); //오프라인 테이블 적용
		jf.add(jp_south);
		jf.setBounds(500,200,500,600);
		jf.setVisible(true);
		
	}

	//public static void main(String[] args) {
	//	new MainForm();
	//}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		////채팅방 만들기
		if(obj == jbtn_chat) {
			SelectUserFrom suf = new SelectUserFrom(connect,this);
			System.out.println("방 만들기 버튼 누름");
		
		}
	}
	
}
