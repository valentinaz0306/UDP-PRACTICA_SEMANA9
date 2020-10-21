package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import events.OnMessageList;
import modelo.Pedido;

public class UdpConnection extends Thread {
	
	private DatagramSocket socket; 
	private OnMessageList observer; 
	
	public void setObserver(OnMessageList observer) {
		this.observer = observer; 
		
	}
	public void run() {
		
		try {
		
			socket = new DatagramSocket(5000);
			while(true) {
				
				byte [] buffer = new byte[100]; 
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length ); 
		
				System.out.println("esperando");
				socket.receive(packet);
				String msg = new String(packet.getData()).trim(); 				
				System.out.println("recibido"+ msg);
				
				Gson gson = new Gson(); 
				Pedido pedido = gson.fromJson(msg, Pedido.class); 
				observer.pedidoR(pedido);
				System.out.println(pedido.getTime()+"."+pedido.getNombre());
				
			}
			
		}catch(SocketException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void enviarMsg(String message) {
		new Thread(
			
				()->{
					
					try {
						InetAddress ip = InetAddress.getByName("192.168.1.187");
						DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, 6000); 
						socket.send(packet);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}	
				
				
				).start();
	
		
	}
	

}
