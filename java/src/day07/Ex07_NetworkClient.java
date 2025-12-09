package day07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Ex07_NetworkClient {

	public static void main(String[] args) {
		/*- 클라이언트 
		 * 1. 서버의 IP주소와 포트를 이용하여 클라이언트 객체를 생성
		 * 2. 연결 요청
		 * 3. 연결이 되면 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
		 * 4. 통신을 종료
		 * */
		//1.ip주소, 포트설정
		//127.0.0.1 - localhost내부 아이피 주소
		String ip = "192.168.0.39";
		int port = 5000;
		//1.2. 클라이언트가 연결을 요청하고 요청이 되면 객체 생성
		try(Socket socket = new Socket(ip, port)){
			//3. IO스트림을 이용한 작업
			//입력
			Scanner scan = new Scanner(System.in);
			System.out.println("클라이언트 : ");
			String msg = scan.nextLine();
			//전송
			ObjectOutputStream oos
			= new ObjectOutputStream(socket.getOutputStream());
		oos.writeUTF(msg);
		oos.flush();//버퍼가 다 안차도 밀어내는 역할
			//받음
			ObjectInputStream ois 
				= new ObjectInputStream(socket.getInputStream());
			String str = ois.readUTF();
			
			//출력
			System.out.println("서버 : "+ str);
		}catch(Exception e) {
			System.err.println("클라이언트에서 예외 발생");
		}
	}

}
