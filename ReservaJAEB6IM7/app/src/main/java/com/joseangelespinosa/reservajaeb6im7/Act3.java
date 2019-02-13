package com.joseangelespinosa.reservajaeb6im7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Act3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra);
    }

    public void veInicio(View us) {
        Intent envia = new Intent(this, MainActivity.class);
        finish();
        startActivity(envia);
    }
}
