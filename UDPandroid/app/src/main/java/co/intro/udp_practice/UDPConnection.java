package co.intro.udp_practice;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection  extends Thread {

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

                System.out.println("waiting");
                socket.receive(packet);
                String mensaje = new String(packet.getData()).trim();

                Gson gson = new Gson();
                Pedido pe = gson.fromJson(mensaje,Pedido.class);
                observer.pedidoR(pe);
            }
        }catch(SocketException e) {
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void enviarMsg(String msg) {
        new Thread(

                ()->{

                    try {
                        InetAddress ip = InetAddress.getByName("192.168.1.187");
                        DatagramPacket packet = new DatagramPacket( msg.getBytes(), msg.getBytes().length, ip, 6000);
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
