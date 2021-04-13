package com.client;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import vo.client.Protocol;

public class ChattingModel {
	ServerConnecter connect = null;
	Map<String,Vector<String>> chatroom = null;
	ChattingForm cf = null;
	//생성자
	public ChattingModel() {
		
	}
	public ChattingModel(ServerConnecter con) {
		this.connect = con;
	}
	
	//200번 1대1 대화방 생성
	public void createroom1(String name, Vector<String> withRoomIDs) {
		String roomName = JOptionPane.showInputDialog(null,"채팅방 이름을 설정해주세요.");
		String msg = Protocol.createRoom1 
					+ Protocol.seperator+name
					+Protocol.seperator+withRoomIDs
					+Protocol.seperator+roomName;
		try {
			connect.oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//210번 1대n 대화방 생성
	
	
	//220번 대화방 퇴장
	public void closeRoom() {
		
		ChattingForm.chat.jf.dispose(); //기존 화면 닫음
	}
	
	//300번 메시지 전송
	public void sendMessage(String p_id, String roomName, String chat_msg) {
		String msg = Protocol.sendMessage //이거 다른곳에서 가져와서 oos앞에 주소번지
					+Protocol.seperator+p_id
					+Protocol.seperator+roomName
					+Protocol.seperator+chat_msg;
		try {//300#유저 아이디#룸네임#msg
			connect.oos.writeObject(msg);
			System.out.println("sendMessage 실행  : "+msg);
		} catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	
	
}
