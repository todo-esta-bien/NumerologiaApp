package mx.com.bit01.numerologiaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        String tipoNum = getIntent().getExtras().getString("numText");
        int valorNum = getIntent().getExtras().getInt("numValue");

        setTitle(getResources().getString(R.string.title_activity_resultados)+" "+tipoNum);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Aquí se planea compartir el número y la interpretación en las redes sociales", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView numeroNon = (TextView)findViewById(R.id.numeroNon);
        numeroNon.setText(valorNum+"");

    }
}
