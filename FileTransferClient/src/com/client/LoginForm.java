package com.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vo.client.Protocol;

public class LoginForm implements ActionListener{
	//선언부
	ServerConnecter connect = null;
	public static JoinForm join = null;
	public static LoginForm login = null;
	public static MainForm main = null;
	JFrame 				jf = new JFrame();
	JLabel 			jlb_id = new JLabel("ID");
	JLabel 			jlb_pw = new JLabel("PW");
	JTextField 		jtf_id = new JTextField();
	JPasswordField 	jtf_pw = new JPasswordField();
	JButton 		jbtn_login = new JButton("로그인");
	JButton 		jbtn_join  = new JButton("회원가입");
	Font 			font 	   = new Font("고딕체",Font.BOLD, 17);
	
	//생성자
	public LoginForm() {
		connect = new ServerConnecter();
		//서버와 연결된 주소번지, 소켓이 두개 그 이상이 만들어짐 관리 안됨. 누가누군지 모름
		//로그인창 그대로두고 회원가입창이 떳다가 사라지는게 있고
		//로그인창 - 회원가입창 - 로그인창 이렇게 dispose setvisible 프로세서 종료되는게아니라 그대로 실행중이면서 눈에만 안보이게
	}
	
	

	//화면처리부 
	public void initDisplay() {
		
		//Enter 키 눌렀을시 로그인 실행가능하도록 설정.
		//jbtn_login.addKeyListener(ftckeypress); 키프레스아니여도 가능합니다. 
		
		jf.setLayout(null);
		jf.add(jlb_id);
		jf.add(jlb_pw);
		jlb_id.setFont(font);
		jlb_pw.setFont(font);
		jlb_id.setBounds(55, 200, 80, 40);
		jlb_pw.setBounds(55, 250, 80, 40);
		
		jf.add(jtf_id);
		jtf_pw.addActionListener(login);
		jf.add(jtf_pw);
		jtf_id.setBounds(120, 200, 185, 40);
		jtf_pw.setBounds(120, 250, 185, 40);
		
		jbtn_login.addActionListener(login);
		jf.add(jbtn_login);
		jbtn_login.setBounds(160, 300, 100, 40);
		jbtn_join.addActionListener(login);
		jf.add(jbtn_join);
		jbtn_join.setBounds(160, 350, 100, 40);
		
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setTitle("Login");
		jf.setBounds(700, 200, 400, 600);
		//jf.setSize(400, 600);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		login = new LoginForm();
		login.initDisplay();

	}
	//클라이언트에선 오라클에 직접 접속할 권한이 없습니다. 
	//무조건 서버에 요청만 하는거고 오라클 접속하고 결과값을 뱉어주는건 서버만 할 수 있습니다
	//클라이언트는 서버에서 뱉은 값을 가지고 화면에 보여주는 역할만 합니다.
	//그 작업은 일단 클라이언트 쓰레드에서 작업할겁니다.
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_login || obj == jtf_pw) {
			System.out.println("로그인 버튼 누름");
			LoginModel model = new LoginModel(connect);
			model.checkLogin(jtf_id.getText(), jtf_pw.getText());
		}
		else if(obj == jbtn_join) {
			System.out.println("Clicked join!!");
			join  = new JoinForm(connect);
		}
	}

}
