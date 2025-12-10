package day08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Ex04_Server {

	public static final int PORT = 6000;
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocker = new ServerSocket(PORT);
			//클라이언트들에게 메세지를 보낼 수 있는 출력 스트림 리스트
			List<ObjectOutputStream> list
				= new ArrayList<ObjectOutputStream>();
			
			while(true) {
				//3. 연결 대기 후, 연결 요청이 오면 수락 후 소켓 객체 생성
				Socket socket = serverSocker.accept();
				
				ObjectInputStream ois
					= new ObjectInputStream(socket.getInputStream());
				String id = ois.readUTF();
				
				System.out.println("["+id+"가 접속했습니다]");
				
				System.out.println("[클라인 접속했습니다]");
				
				//클러이언트가 보낸 아이디 및 메세지를 다른 클라이언트들에게 전송
				//(본인 클러이언트포함)
				Server02 server = new Server02(socket, list);
				server.receive();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
