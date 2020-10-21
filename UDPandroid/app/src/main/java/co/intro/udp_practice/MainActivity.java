package co.intro.udp_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int cont = 0;
    UDPConnection udp;
    private ImageView salad, pizza, juice, burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        udp = new UDPConnection();
        udp.start();
        udp.setObserver((OnMessageList) this);

        burger = findViewById(R.id.burger);
        juice = findViewById(R.id.juice);
        pizza = findViewById(R.id.pizza);
        salad = findViewById(R.id.salad);


        burger.setOnClickListener(
                (view)->{

                    cont ++;

                    Date now = new Date();
                    Pedido p = new Pedido("burger", cont,now);
                    Gson gson = new Gson();
                    String json = gson.toJson(p);

                    udp.enviarMsg(json);
                }
        );

        salad.setOnClickListener(
                (view)->{

                    cont ++;

                    Date now = new Date();
                    Pedido p = new Pedido("salad", cont,now);
                    Gson gson = new Gson();
                    String json = gson.toJson(p);

                    udp.enviarMsg(json);
                }
        );

        juice.setOnClickListener(
                (view)->{

                    cont ++;

                    Date now = new Date();
                    Pedido p = new Pedido("juice", cont,now);
                    Gson gson = new Gson();
                    String json = gson.toJson(p);

                    udp.enviarMsg(json);
                }
        );

        pizza.setOnClickListener(
                (view)->{

                    cont ++;

                    Date now = new Date();
                    Pedido p = new Pedido("pizza", cont,now);
                    Gson gson = new Gson();
                    String json = gson.toJson(p);

                    udp.enviarMsg(json);
                }
        );

    }// oncreate

    public void pedidoR (Pedido r) {
        runOnUiThread(
                ()->{
                    Log.e("AAAA","R"+ r);
                    Toast.makeText(this, r.getName()+"funciona", Toast.LENGTH_SHORT).show();
                }
        );

    }
}
