package mx.com.bit01.numerologiaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MenuNumeros extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_numeros);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Button[] btns = {(Button) findViewById(R.id.btnMenu1),(Button) findViewById(R.id.btnMenu2),(Button) findViewById(R.id.btnMenu3),(Button) findViewById(R.id.btnMenu4),(Button) findViewById(R.id.btnMenu5)
                ,(Button) findViewById(R.id.btnMenu6),(Button) findViewById(R.id.btnMenu7),(Button) findViewById(R.id.btnMenu8),
                (Button) findViewById(R.id.btnMenu9),(Button) findViewById(R.id.btnMenu10),(Button) findViewById(R.id.btnMenu11)};

        String tipoNum = getIntent().getExtras().getString("tipoNum");

        btns[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[0].getText().toString());
                startActivity(intent);
            }
        });

        btns[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[1].getText().toString());
                startActivity(intent);
            }
        });

        btns[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[2].getText().toString());
                startActivity(intent);
            }
        });

        btns[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[3].getText().toString());
                startActivity(intent);
            }
        });

        btns[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[4].getText().toString());
                startActivity(intent);
            }
        });

        btns[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[5].getText().toString());
                startActivity(intent);
            }
        });

        btns[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[6].getText().toString());
                startActivity(intent);
            }
        });

        btns[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[7].getText().toString());
                startActivity(intent);
            }
        });

        btns[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[8].getText().toString());
                startActivity(intent);
            }
        });

        btns[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[9].getText().toString());
                startActivity(intent);
            }
        });

        btns[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PedirDatos.class);
                intent.putExtra("num", btns[10].getText().toString());
                startActivity(intent);
            }
        });

        if(tipoNum.equals("TANT")){
            //Es tantrico
            ponBotonesTant();
        }else{
            //Es Pitagorico
            ponBotonesPita();
        }


    }

    public void ponBotonesTant(){
        Button[] btns = {(Button) findViewById(R.id.btnMenu1),(Button) findViewById(R.id.btnMenu2),(Button) findViewById(R.id.btnMenu3),(Button) findViewById(R.id.btnMenu4),(Button) findViewById(R.id.btnMenu5)
                ,(Button) findViewById(R.id.btnMenu6),(Button) findViewById(R.id.btnMenu7),(Button) findViewById(R.id.btnMenu8),
                (Button) findViewById(R.id.btnMenu9),(Button) findViewById(R.id.btnMenu10),(Button) findViewById(R.id.btnMenu11)};

        for(int i=6;i<11;i++){
            btns[i].setVisibility(View.GONE);
        }
    }

    public void ponBotonesPita(){
        Button[] btns = {(Button) findViewById(R.id.btnMenu1),(Button) findViewById(R.id.btnMenu2),(Button) findViewById(R.id.btnMenu3),(Button) findViewById(R.id.btnMenu4),(Button) findViewById(R.id.btnMenu5)
                ,(Button) findViewById(R.id.btnMenu6),(Button) findViewById(R.id.btnMenu7),(Button) findViewById(R.id.btnMenu8),
                (Button) findViewById(R.id.btnMenu9),(Button) findViewById(R.id.btnMenu10),(Button) findViewById(R.id.btnMenu11)};

        int[] txtBotones = {R.string.MenuNum_Personalidad, R.string.MenuNum_Nombre, R.string.MenuNum_Activo, R.string.MenuNum_Hereditario, R.string.MenuNum_Actitud,
                R.string.MenuNum_Realidad, R.string.MenuNum_AnioPersonal, R.string.MenuNum_Pinaculo, R.string.MenuNum_Sombra, R.string.MenuNum_Madurez};

        for(int i=0;i<6;i++){
            btns[i].setText(txtBotones[i]);
        }
    }

}
