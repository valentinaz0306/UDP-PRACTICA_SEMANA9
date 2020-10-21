package main;

import java.util.ArrayList;
import java.util.Date;
//import gson
import com.google.gson.Gson;
import events.OnMessageList;
import processing.core.PApplet;
import modelo.Pedido;
import modelo.PintarPedido;

public class Main extends PApplet implements OnMessageList {

	UdpConnection udp;
	PintarPedido pruebaPedido;
	ArrayList<Pedido> pedidos;
	ArrayList<PintarPedido> menu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");

	}

	public void settings() {
		size(800, 800);
	}

	public void setup() {
		pedidos = new ArrayList<Pedido>();
		menu = new ArrayList<PintarPedido>();
		udp = new UdpConnection();
		udp.start();
		udp.setObserver(this);
		Date now = new Date();
		Pedido pedido = new Pedido("Jugo", 1, now);
		pruebaPedido = new PintarPedido(pedido, 0, 0, this);

	}

	public void draw() {
		background(0);
		fill(255);
		for (int i = 0; i < menu.size(); i++) {
			menu.get(i).pintar();
		}

	}

	public void pedidoR(Pedido pd) {
		pedidos.add(pd);
		menu.add(new PintarPedido(pd, pedidos.size() * 100, 0, this));
		System.out.println(pedidos.size());
	}

	public void mousePressed() {

		for (int i = 0; i < menu.size(); i++) {
			finalPedido(mouseX, mouseY, menu.get(i));
		}
	}

	public void finalPedido(int x, int y, PintarPedido p) {

		if (x >= p.getX() && x <= p.getX() + 100)

		{

			int list = pedidos.indexOf(p.getPedido());
			Pedido p1 = p.getPedido();
			Gson gson = new Gson();
			String actualP = gson.toJson(p1);
			udp.enviarMsg(actualP);

			pedidos.remove(p.getPedido());
			menu.remove(p);
			System.out.println(list + "pinta");

		}

	}

}
