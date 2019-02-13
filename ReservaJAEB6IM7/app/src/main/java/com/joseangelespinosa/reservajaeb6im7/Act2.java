package com.joseangelespinosa.reservajaeb6im7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Act2 extends AppCompatActivity {

    String nombre = "",  fechaLlega = "", horaLlega = "",  fechaParte = "", horaParte = "";
    int compas = 0, habs = 0;
    boolean todo = false;
    String texto = "";
    TextView textViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

        textViewDatos = findViewById(R.id.textViewDatos);

        Bundle recibe = new Bundle();
        recibe = this.getIntent().getExtras();

        nombre = recibe.getString("nombre");
        compas = recibe.getInt("compas");
        fechaLlega = recibe.getString("LlegadaDia");
        horaLlega = recibe.getString("LlegadaHora");
        fechaParte = recibe.getString("ParteDia");
        horaParte = recibe.getString("ParteHora");
        habs = recibe.getInt("habs");
        todo = recibe.getBoolean("Todo");

        if (todo)
            texto = "Todo incluido";
        else
            texto = "Sin servicio de Todo incluido";


        textViewDatos.setText("Reservacion a nombre de:\n" +
                                nombre + "\n" +
                                (compas+1) + " Personas.\n" +
                                "Fecha de Llegada:\n" + fechaLlega + "\n" +
                                "Hora de llegada:\n" + horaLlega + "\n" +
                                "Fecha de Partida:\n" + fechaParte + "\n" +
                                "Hora de Partida:\n" + horaParte + "\n"
                                + habs + " Habitaciones." + "\n" +
                                texto);

    }

    public void veExtra(View v) {
        Intent envia = new Intent(this, Act3.class);
        Toast.makeText(Act2.this,"Reservacion realizada con exito",Toast.LENGTH_LONG).show();
        finish();
        startActivity(envia);
    }
}
