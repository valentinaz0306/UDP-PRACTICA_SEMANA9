package modelo;

import java.util.Date;

public class Pedido {

	String nombre;
	int num;
	Date time;

	public Pedido(String nombre, int num, Date time) {
		super();
		this.nombre = nombre;
		this.num = num;
		this.time = time;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
