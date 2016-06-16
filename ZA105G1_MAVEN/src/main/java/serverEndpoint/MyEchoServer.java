package serverEndpoint;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{Sender}/{Reciver}")
public class MyEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	

	@OnOpen
	public void onOpen(Session userSession ,@PathParam("Sender") int Sender,@PathParam("Reciver") int Reciver) throws IOException {
		
		userSession.getUserProperties().put("SenderSessionId", userSession.getId());
		userSession.getUserProperties().put("Sender", Sender);
		userSession.getUserProperties().put("Reciver", Reciver);
		
		
		if(allSessions.size()==0){
			allSessions.add(userSession);
			System.out.println(userSession.getId()+" userSession added!!");
			System.out.println("目前session數量:"+allSessions.size());
		}
		else{
			boolean judge=false;
			
			for(Session existedSession : allSessions){
				System.out.println("here!!");
				
				if((userSession.getUserProperties().get("Sender").equals(existedSession.getUserProperties().get("Sender")))
	&&(userSession.getUserProperties().get("Reciver").equals(existedSession.getUserProperties().get("Reciver")))){
					System.out.println("此session已存在!!");
					System.out.println("目前session數量:"+allSessions.size());
					break;
				}
				else{
					judge=true;
				}

			}
			
			if(judge==true){
				allSessions.add(userSession);
				System.out.println(userSession.getId()+" userSession added!!");
				System.out.println("目前session數量:"+allSessions.size());
			}
		}
		
	
		
//		userSession.getBasicRemote().sendText("WebSocket �s�u���\");
	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		int n=0;
		for (Session a : allSessions) {//所有的session
			//if (session.isOpen())
				//session.getAsyncRemote().sendText(message);

//			System.out.println("a Sender:"+a.getUserProperties().get("Sender"));
//			System.out.println("a Reciver:"+a.getUserProperties().get("Reciver"));
//			System.out.println();
			
			for (Session b : allSessions) {//所有的session
//				System.out.println("b Sender:"+b.getUserProperties().get("Sender"));
//				System.out.println("b Reciver:"+b.getUserProperties().get("Reciver"));
				
				
				System.out.println(a.getUserProperties().get("Sender").equals(b.getUserProperties().get("Reciver")));
				System.out.println(b.getUserProperties().get("Sender").equals(a.getUserProperties().get("Reciver")));
				System.out.println();
				if(n==0&&   //聊天雙方皆已開session
				  (a.getUserProperties().get("Sender").equals(b.getUserProperties().get("Reciver")))&&
				  (b.getUserProperties().get("Sender").equals(a.getUserProperties().get("Reciver")))&&
				  (userSession.getUserProperties().get("SenderSessionId")).equals(a.getUserProperties().get("SenderSessionId")))
				{

					a.getAsyncRemote().sendText(message);
					b.getAsyncRemote().sendText(message);
					n++;
					break;
				}
			}
			
		}
		if(n==0){
			userSession.getAsyncRemote().sendText(message);
		}
		
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		
		/*for(Session session: allSessions){
			System.out.println(session.getUserProperties().get("Sender"));
			System.out.println(Sender);
			System.out.println(session.getUserProperties().get("Reciver"));
			System.out.println(Reciver);
			
			
			if(((int)(session.getUserProperties().get("Sender"))==Sender)&&
			   ((int)(session.getUserProperties().get("Reciver"))==Reciver)){
				System.out.println(session.getId());
				System.out.println(userSession.getId());
				
			}
		}*/
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		System.out.println("目前session數量:"+allSessions.size());
	}

 
}
