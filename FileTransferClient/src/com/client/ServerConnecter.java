package com.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

public class ServerConnecter {//얘가 연결해서 보내는거 담당
	Socket soc = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	ClientThread clientth = null;
	Map<String, ChattingForm> chatRoom = null;
	public ServerConnecter() {
		try {
			//내 로컬아이피 자동으로 가져오기
			InetAddress ipadd = InetAddress.getLocalHost();
			String ip = ipadd.getHostAddress();
			//소켓생성하기
			soc = new Socket(ip, 3000); 
			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());
			clientth = new ClientThread(this);
			chatRoom = new Hashtable<String, ChattingForm>();
			clientth.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
