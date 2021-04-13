package com.client;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import vo.client.Protocol;

public class LoginModel {
	ServerConnecter connect = null;
	//생성자
	public LoginModel() {}
	public LoginModel(ServerConnecter con) {
		this.connect = con;

	}
	//100번 로그인체크
	public void checkLogin(String p_id, String p_pw) {
		//100#haeri1127#tiger
		String msg = Protocol.checkLogin
					+Protocol.seperator+p_id
					+Protocol.seperator+p_pw;
		System.out.println("checkLogin실행, msg : "+msg);////
		try {
			connect.oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//100번 로그인체크 결과
	public void resultLogin(String result) {
		if("difid".equals(result)) {
			JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다");
		}
		else if("difpw".equals(result)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
		}
		else if("overlap".equals(result)) {
			JOptionPane.showMessageDialog(null, "이미 로그인된 아이디입니다.");
		}
		else if(LoginForm.login.jtf_id.getText().equals(result)) {
			//온라인 리스트 벡터 가져오기
			LoginForm.main = new MainForm(connect, result); //메인화면 띄움
			Protocol.p_id = result;
			LoginForm.login.jf.dispose(); //기존 화면 닫음
			LoginForm.login = null; // null로 다시 해주는 이유가 뭐지?메모리때문인가 질문
		}
	}

	//110번 회원가입
	public void addUser(String id, String pw, String name) {
		String msg = Protocol.addUser
					+Protocol.seperator+id
					+Protocol.seperator+pw
					+Protocol.seperator+name; 
		System.out.println(msg);
		try {
			connect.oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//110번 회원가입 결과
	public void addResult(String result) {
		if("성공".equals(result)) {
			System.out.println(LoginForm.join);
			JOptionPane.showMessageDialog(LoginForm.join.jd, LoginForm.join.jtf_id.getText()+"님 가입을 환영합니다.");
			LoginForm.join.jd.dispose();
			LoginForm.join = null;
		}
	}
}