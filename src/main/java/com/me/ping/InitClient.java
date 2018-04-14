package com.me.ping;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOServer;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@Service
public class InitClient {

	private Socket socket;
	final int PORT = 881;
	SocketIOServer server;
	
	public InitClient() {
		try {
			client();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public void client() throws URISyntaxException {
		
		socket = IO.socket("http://192.168.1.7:" + PORT);
		
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			public void call(Object... objects) {
				System.err.println("COONECTED...");
				socket.emit("on-connect", "rhaynel1");
				socket.emit("rooms", "rhaynel1");
			}
		});
		
		socket.on("message", new Emitter.Listener(){
			public void call(Object... args) {
				System.out.println("Client recievd : " + args[0]);

			}
		});
		
		
		socket.on("room-check", new Emitter.Listener(){
			public void call(Object... rooms) {
				System.err.println(rooms[0]);
			}
		});


		socket.on("join-room", new Emitter.Listener(){
			public void call(Object... args) {
				System.out.println("client joint the group 2 ");
			}
		});
		
		socket.connect();
		
		System.err.println(socket.id());
	}
}
