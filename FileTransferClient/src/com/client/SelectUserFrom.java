package com.client;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class SelectUserFrom implements ActionListener,ItemListener{
	//선언부
	ServerConnecter connect 	= null;
	String onlines[] = null;
	Vector<String> withRoomIDs = new Vector<>();;
	
	JFrame jf = new JFrame();
	JPanel jp_north = new JPanel();
	JPanel jp_center = new JPanel();
	JPanel jp_south = new JPanel();
	JLabel jlb_selectUser = new JLabel("접속중인 유저");
	JCheckBox[] jcb_online = null;
	JCheckBox jcb_apple = null;
	JButton jbtn_create = new JButton("방 만들기");
	MainForm main = null;
	
	//생성자
	public SelectUserFrom() {}
	
	public SelectUserFrom(ServerConnecter con, MainForm main) {
		this.connect = con;
		this.main = main;
		
		checkbox();
		initDisplay();
	}

	//체크박스 생성 메소드
	void checkbox() {
		jp_center = new JPanel(new GridLayout(main.dtm_online.getRowCount(),1,2,2)); //접속중 유저만큼 그리드레이아웃 만들기
		onlines = new String[main.dtm_online.getRowCount()]; 	  //dtm값 넣을 배열 크기 초기화
		jcb_online = new JCheckBox[main.dtm_online.getRowCount()]; //체크 박스 크기 초기화
		
		for(int i=0; i<main.dtm_online.getRowCount(); i++) {    
			System.out.println("포문실행?");
			if(!main.p_id.equals(main.dtm_online.getValueAt(i, 0))) {//equals써보자
				System.out.println("이프문 실행?");
				onlines[i]=main.dtm_online.getValueAt(i, 0).toString(); //dtm값을 배열에 넣기
				System.out.println("onlines["+i+"]  :  "+onlines[i]);
				jcb_online[i] = new JCheckBox(onlines[i]); //배열의 값을 담은 체크박스 생성
				jp_center.add(jcb_online[i]); //체크박스 패널에 추가
				jcb_online[i].addItemListener(this); //이벤트 처리
			}
		}
		
	}
	
	//화면처리부
	public void initDisplay() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		jp_center.setBackground(Color.WHITE);
		jbtn_create.addActionListener(this);
		jlb_selectUser.setFont(new Font("고딕체", Font.BOLD, 15));
		jp_north.add(jlb_selectUser);
		jp_south.add(jbtn_create);
		jp_north.setBackground(Color.WHITE);
		jf.add("North",jp_north);
		jf.add("Center",jp_center);
		jf.add("South",jp_south);
		jf.setTitle("초대 유저 선택");
		jf.setBounds(1000, 200, 300, 400);
		jf.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object c = ie.getSource();
		System.out.println("itemStateChanged 호출");
		if(ie.getStateChange() == ie.SELECTED) {
			withRoomIDs.add(((JCheckBox) ie.getSource()).getText()); //체크박스의 값 들어가야함.
			System.out.println("체크 성공   :   "+withRoomIDs);
		}
		
		else if(ie.getStateChange() == ie.DESELECTED) {
			withRoomIDs.remove(((JCheckBox) ie.getSource()).getText()); //체크박스의 값 들어가야함.
			System.out.println("체크 해제   :   "+withRoomIDs);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==jbtn_create) {
			System.out.println("방 만들기 버튼 누름");
			ChattingModel cm = new ChattingModel(connect); 
			cm.createroom1(main.p_id,withRoomIDs); 
			//이거 클라이언트스레드에서 해결할거니까 나중에 삭제하기 이거 테스트용임
			jf.dispose();
			
		}
	}
}
