package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class JoinForm implements ActionListener , FocusListener{
	//선언부
	JDialog 		jd = new JDialog();
	JLabel 			jlb_id = new JLabel("아이디");
	JLabel 			jlb_pw = new JLabel("비밀번호");
	JLabel 			jlb_name = new JLabel("이름");
	JTextField 		jtf_id = new JTextField("아이디를 설정해주세요.");
	JTextField 		jtf_pw = new JTextField("비밀번호를 설정해주세요.");
	JTextField 		jtf_name = new JTextField("이름을 입력해주세요.");
	JButton 		jbtn_join = new JButton("가입신청");
	
	ServerConnecter connect = null;
	
	//생성자
	public JoinForm (ServerConnecter con) {
		this.connect = con;
		initDisplay();
		System.out.println("JoinForm() 호출 성공");
	}
	//화면
	public void initDisplay() {
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//이벤트
		jbtn_join.addActionListener(this);
		jtf_id.addFocusListener(this);
		jtf_pw.addFocusListener(this);
		jtf_name.addFocusListener(this);
		
		//라벨 추가.
		jd.add(jlb_id);
		jd.add(jlb_pw);
		jd.add(jlb_name);
		
		//라벨 위치 세팅.
		jlb_id.setBounds(55, 50, 80, 40);
		jlb_pw.setBounds(55, 100, 80, 40);
		jlb_name.setBounds(55, 150, 80, 40);
		
		//텍스트 필드 추가.
		jd.add(jtf_id);
		jd.add(jtf_pw);
		jd.add(jtf_name);
		
		//텍스트 필드 위치 세팅.
		jtf_id.setBounds(120, 50, 180, 40);
		jtf_pw.setBounds(120, 100, 180, 40);
		jtf_name.setBounds(120, 150, 180, 40);
		
		//버튼추가.
		jd.add(jbtn_join);
		
		//버튼 위치 세팅.
		jbtn_join.setBounds(160, 270, 100, 40);
		
		//dialog 세팅.
		jd.setLayout(null);
		jd.setSize(400, 400);
		jd.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtn_join || obj == jtf_name) {
			LoginModel login = new LoginModel(connect);
			System.out.println(this);
			login.addUser(jtf_id.getText(), jtf_pw.getText(), jtf_name.getText());
		}
	
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		
		if(obj == jtf_id ) {
			jtf_id.setText("");//UI관련해서 디테일한건 천천히 잡져 도움 흐름 로직 그런걸 우선 잡아야됨
		}
		else if(obj == jtf_pw) {
			jtf_pw.setText("");
		}
		else if(obj == jtf_name) {
			jtf_name.setText("");
		}
			
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object obj = e.getSource();
		
	}

}
