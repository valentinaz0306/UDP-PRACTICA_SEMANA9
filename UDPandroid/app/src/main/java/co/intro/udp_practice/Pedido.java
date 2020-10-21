package co.intro.udp_practice;

import java.util.Date;

public class Pedido {

    Date time;
    String name;
    int number;

    public Pedido(String name, int number, Date time) {
        super();
        this.time = time;
        this.name = name;
        this.number = number;

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
