package day08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Ex01_Server {

	//1. port 지정
	public final static int PORT = 7000;
	
	public static void main(String[] args) {
		
		
		//2. port를 이용하여 서버소켓 객체 생성
		try{
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			System.out.println("[연결 대기 중...]");
			
			//3. 연결 대기 후, 연결 요청이 오면 수락 후 소켓 객체 생성
			Socket socket = serverSocket.accept();
			
			System.out.println("[연결 성공!]");
			
			Client01 client = new Client01(socket);
			client.send();
			client.recieve();
			
			//클라이언트가 전송하면 받아서 콘솔에 출력 하는 쓰레드
//			Thread t1 = new Thread(()->{
//				System.out.println("[클라이언트가 전송한 메시지]");
//				try {
//					//클라이언트가 보낸 값을 받기 위한 입력 스트림객체 생성
//					ObjectInputStream ois 
//						= new ObjectInputStream(socket.getInputStream());
//					//메세지를 여러번 입력할수 있게 무한 루프
//					while(true) {
//						String msg = ois.readUTF();
//						System.out.println("클라이언트 : " + msg);
//						if(msg.equals("EXIT")) {
//							System.out.println("[클라이언트가 전송을 종료했습니다.]");
//							break;
//						}
//					}
//				} catch (IOException e) {
//					System.err.println("예외가 발생했습니다.");
//				}
//				System.out.println("[수신 스레드 종료]");
//			});
//			t1.start();
//			//클라이언티에게 메세지를 전송할 스레드
//			Thread t2 = new Thread(()->{
//				System.out.println("[클라이언티로 전송할 메세지]");	
//				Scanner scan = new Scanner(System.in);
//				try {
//					ObjectOutputStream oos
//						= new ObjectOutputStream(socket.getOutputStream());
//					//메세지를 여러번 전송하기 위한 무한루프
//					while(true) {
//						//메세지 콘솔에서 입력
//						System.out.println("입력 : ");
//						String msg = scan.nextLine();
//						//서버로 전송
//						oos.writeUTF(msg);
//						oos.flush();
//						if(msg.equals("EXIT")) {
//							System.out.println("[클라이언트가 전송을 종료했습니다.]");
//							break;
//						}
//					}			
//				} catch (IOException e) {
//					System.err.println("예외 발생");
//				}
//			});
//			t2.start();
		}catch(Exception e) {
			System.err.println("예외가 발생했습니다.");
		}
		System.out.println("[서버 종료(메인 스레드 종료)]");
	}

}