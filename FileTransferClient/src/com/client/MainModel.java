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
	
	public void filter(String p_onlineUser, String p_offlineUser) {
		Vector<String> onlineUser = new Vector<>();
		Vector<String> offlineUser = new Vector<>();
		
		//p_onlineUser : "[online1, online2, online3...]"
		if(p_onlineUser.contains("[")||p_onlineUser.contains("]")) {
			String clean_onlineUser = p_onlineUser.replaceAll("\\p{Punct}", "");
			if(clean_onlineUser.contains(" ")) {
				String[] clean = clean_onlineUser.split(" ");
				for(int i=0;i<clean.length;i++) {
					String result = clean[i];
					onlineUser.add(result);//online Vector에 add.
				}
			}else {
				//최초 로그인시. -> "[online1]"
				onlineUser.add(clean_onlineUser);
			}
		}
		//p_offlineUser : "[offline1, offline2, offline3...]"
		if(p_offlineUser.contains("[")||p_offlineUser.contains("]")) {
			String clean_offlineUser = p_offlineUser.replaceAll("\\p{Punct}", "");
			if(clean_offlineUser.contains(" ")) {
				String[] clean = clean_offlineUser.split(" ");
				for(int i=0;i<clean.length;i++) {
					String result = clean[i];
					offlineUser.add(result);
				}
			}
		}
		
		showUser(onlineUser, offlineUser);
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
}
