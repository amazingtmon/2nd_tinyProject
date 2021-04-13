package com.client;

import java.util.List;
import java.util.Vector;

import vo.client.Protocol;

public class MainModel {
	ServerConnecter connect = null;
	//생성자
	public MainModel() {
		
	}
	public MainModel(ServerConnecter sercon) {
		this.connect = sercon;
	}
	
	//120번 유저리스트
	public void showUser (Vector<String> onlineUser, Vector<String> offlineUser) {
		while(LoginForm.main.dtm_online.getRowCount() > 0) {
			LoginForm.main.dtm_online.removeRow(0);
		}
		for(int i=0; i< onlineUser.size(); i++) {
			Vector<Object> onRow = new Vector<Object>();
			onRow.add(onlineUser.get(i)); 
			LoginForm.main.dtm_online.addRow(onRow);
			//System.out.println("onRow.get(i): "+onRow.get(i));
			//System.out.println("--------------");
			//System.out.println("dtm값 :  "+LoginForm.main.dtm_online.getValueAt(i, 0));
			//System.out.println("dtm값 :  "+LoginForm.main.dtm_online.getValueAt(, 0));
		}
		while(LoginForm.main.dtm_offline.getRowCount() > 0) {
			LoginForm.main.dtm_offline.removeRow(0);
		}
		for(int i=0; i< offlineUser.size(); i++) {
			Vector<Object> onRow = new Vector<Object>();
			onRow.add(offlineUser.get(i));
			LoginForm.main.dtm_offline.addRow(onRow);
		}
	}
	//200번 방만들기
	public void createRoom() {
		
	}
}
