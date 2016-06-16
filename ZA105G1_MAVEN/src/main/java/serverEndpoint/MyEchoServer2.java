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

@ServerEndpoint("/MyEchoServer2/{Sender}/{Reciver}")
public class MyEchoServer2 {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	

	@OnOpen
	public void onOpen(Session userSession ,@PathParam("Sender") int Sender,@PathParam("Reciver") int Reciver) throws IOException {

		userSession.getUserProperties().put("SenderSessionId", userSession.getId());
		System.out.println("userSession.getId():"+userSession.getId()+" Sender:"+Sender+" Reciver"+Reciver);
		userSession.getUserProperties().put("Sender", Sender);
		userSession.getUserProperties().put("Reciver", Reciver);

			allSessions.add(userSession);
			System.out.println(userSession.getId()+":userSession.getId() userSession added!!");
			System.out.println("onOpen:allSessions.size():"+allSessions.size());

	}

	
	@OnMessage
	public void onMessage(Session userSession, String message) {

		System.out.println("--------------");

		for (Session a : allSessions) {//�����ession
				System.out.println("a.Reciver:"+a.getUserProperties().get("Reciver"));
				System.out.println("a.id:"+a.getUserProperties().get("SenderSessionId"));
				
				System.out.println("userSession.Reciver:"+userSession.getUserProperties().get("Reciver"));
				System.out.println("userSession.id:"+userSession.getUserProperties().get("SenderSessionId"));
				System.out.println(a.getUserProperties().get("Reciver").equals(userSession.getUserProperties().get("Reciver")));
			
				if(a.getUserProperties().get("Reciver").equals(userSession.getUserProperties().get("Reciver")))
				{	
					a.getAsyncRemote().sendText(message);
		}
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
		System.out.println("allSessions.size():"+allSessions.size());
	}

 
}
