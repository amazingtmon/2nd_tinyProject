package com.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vo.client.Protocol;

public class ChattingForm implements ActionListener{
	public static ChattingForm chat = null;
	//선언부
	ServerConnecter connect 	= null;
	String roomName = null; //방 이름
	String p_id = null; //현재 유저 아이디
	JFrame 	jf = new JFrame();
	JPanel	jp_first = new JPanel();
	JPanel	jp_first_south = new JPanel();
	
	JTextField jtf_msg = new JTextField(); 
	JTextArea jta_display = new JTextArea();
	JScrollPane jsp_display = new JScrollPane(jta_display);
	
	JButton jbtn_send  = new JButton("전송");
	
	//생성자
	public ChattingForm() {}

	public ChattingForm(ServerConnecter connect,String p_id, String roomName) {
		this.connect = connect;
		this.p_id = p_id;
		this.roomName = roomName;
		initDisplay();
	}
	
	
	//화면처리
	public void initDisplay() {
		
		jtf_msg.addActionListener(this);
		jbtn_send.addActionListener(this);
		jta_display.setFont(new Font("고딕체",Font.BOLD,20));
		
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center", jsp_display);
		jp_first.add("South", jp_first_south);
		
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		
		jf.setTitle("방 이름 : "+roomName + "  /  내 아이디 : "+p_id); //앞에 방 이름 나중에 삭제할 것
		jf.add(jp_first);
		jf.setBounds(1000, 200, 500, 600);
		//jf.setSize(500, 600);
		jf.setVisible(true);
	}
	
	
	/*
	public static void main(String[] args) {
		//new ChattingForm();


	//1.메세지 채팅폼에 보이게하기
	//2. 이 메세지들을 채팅방 초대된 사람들에게 뿌리기 -각각의 스레드를 가진 초대유저들에게 어떻게 뿌릴지 고민
	// 2-1 폼은 열리게 ㄴㄴ
	
	}
	*/


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String chat_msg = jtf_msg.getText();
		//닫기 버튼도 구현해주세욥 프로토콜 220
		
		if(obj == jtf_msg ||obj ==jbtn_send) {
			System.out.println("button action!! 메세지 전송");
			ChattingModel cm = new ChattingModel(connect); 
			cm.sendMessage(p_id, roomName, chat_msg);
			jtf_msg.setText("");
		}
	}

}
