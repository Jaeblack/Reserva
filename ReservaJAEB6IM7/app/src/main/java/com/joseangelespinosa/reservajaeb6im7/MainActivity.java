package com.joseangelespinosa.reservajaeb6im7;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText editTextNombre;
    TextView textViewAcom, textViewHabs;
    Button LlegadaDia, LlegadaHora;
    Button PartidaDia, PartidaHora;
    SeekBar seekBarAcom, seekBarHabs;
    Switch switchTodo;

    SimpleDateFormat LlegadaHoraFormato, LlegadaDiaFormato;
    SimpleDateFormat PartidaHoraFormato, PartidaDiaFormato;

    String nombreVato = "";
    String acompas = "";
    String habs = "";
    String LlegadaDiaSel = "", LlegadaHoraSel = "",PartidaDiaSel = "", PartidaHoraSel = "";
    Date LlegadaDiaConv,PartidaDiaConv;
    String textViewAcomFormat = "";
    String habsFormat = "";
    String valSwitchTodo = "";
    int compas = 0;
    int minhabs = 1;

    Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchTodo = findViewById(R.id.switchTodo);

        textViewAcom = findViewById(R.id.textViewAcom);
        seekBarAcom = findViewById(R.id.seekBarAcom);

        textViewHabs = findViewById(R.id.textViewHabs);
        seekBarHabs = findViewById(R.id.seekBarHabs);

        LlegadaDia = findViewById(R.id.btnLlegadaDia);
        LlegadaHora = findViewById(R.id.btnLlegadaHora);
        PartidaDia = findViewById(R.id.btnPartidaDia);
        PartidaHora = findViewById(R.id.btnPartidaHora);

        seekBarAcom.setOnSeekBarChangeListener(this);
        seekBarHabs.setOnSeekBarChangeListener(this);

        editTextNombre = findViewById(R.id.editTextNombre);

        textViewAcomFormat = textViewAcom.getText().toString();
        habsFormat = textViewHabs.getText().toString();
        // textViewAcomFormat = "compas: %d";
        textViewAcom.setText("Acompaniantes: 0"); // condicion inicial
        textViewHabs.setText("Habitaciones: 1");

        // Para seleccionar la LlegadaDia y la LlegadaHora
        Calendar LlegadaDiaSeleccionada = Calendar.getInstance();
        LlegadaDiaSeleccionada.set(Calendar.HOUR_OF_DAY, 12); // LlegadaHora inicial
        LlegadaDiaSeleccionada.clear(Calendar.MINUTE); // 0
        LlegadaDiaSeleccionada.clear(Calendar.SECOND); // 0

        Calendar PartidaDiaSeleccionada = Calendar.getInstance();
        PartidaDiaSeleccionada.set(Calendar.HOUR_OF_DAY, 12); // LlegadaHora inicial
        PartidaDiaSeleccionada.clear(Calendar.MINUTE); // 0
        PartidaDiaSeleccionada.clear(Calendar.SECOND); // 0

        // formatos de la LlegadaDia y LlegadaHora
        LlegadaDiaFormato = new SimpleDateFormat(LlegadaDia.getText().toString());
        LlegadaHoraFormato = new SimpleDateFormat("HH:mm");
        PartidaDiaFormato = new SimpleDateFormat(LlegadaDia.getText().toString());
        PartidaHoraFormato = new SimpleDateFormat("HH:mm");
        // LlegadaHoraFormato = new SimpleDateFormat(LlegadaHora.getText().toString());

        // La primera vez mostramos la LlegadaDia actual
        Date LlegadaDiaReservacion = LlegadaDiaSeleccionada.getTime();
        LlegadaDiaSel = LlegadaDiaFormato.format(LlegadaDiaReservacion);
        LlegadaDia.setText(LlegadaDiaSel); // LlegadaDia en el

        Date PartidaDiaReservacion = PartidaDiaSeleccionada.getTime();
        PartidaDiaSel = PartidaDiaFormato.format(PartidaDiaReservacion);
        PartidaDia.setText(PartidaDiaSel); // LlegadaDia en el

        LlegadaHoraSel = LlegadaHoraFormato.format(LlegadaDiaReservacion);
        // boton
        LlegadaHora.setText(LlegadaHoraSel); // LlegadaHora en el boton
        PartidaHoraSel = PartidaHoraFormato.format(PartidaDiaReservacion);
        // boton
        PartidaHora.setText(PartidaHoraSel); // LlegadaHora en el boton

        // Otra forma de ocupar los botones
        LlegadaDia.setOnClickListener(this);
        LlegadaHora.setOnClickListener(this);
        PartidaDia.setOnClickListener(this);
        PartidaHora.setOnClickListener(this);



    }

    @Override
    public void onProgressChanged(SeekBar barra, int progreso,
                                  boolean delUsuario) {

        acompas = String.format(textViewAcomFormat, seekBarAcom.getProgress());
        compas = seekBarAcom.getProgress();
        textViewAcom.setText(acompas);

        habs = String.format(habsFormat, seekBarHabs.getProgress());
        minhabs = seekBarHabs.getProgress();
        textViewHabs.setText(habs);
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0) {
    }

    @Override
    public void onClick(View v) {
        if (v == LlegadaDia) {
            Calendar calendario = parseCalendar(LlegadaDia.getText(), LlegadaDiaFormato);
            new DatePickerDialog(this, this, calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
        } else if (v == LlegadaHora) {
            Calendar calendario = parseCalendar(LlegadaHora.getText(), LlegadaHoraFormato);
            new TimePickerDialog(this, this,
                    calendario.get(Calendar.HOUR_OF_DAY),
                    calendario.get(Calendar.MINUTE), false) // /true = 24 LlegadaHoras
                    .show();
        } else if (v == PartidaDia) {
            Calendar calendario = parseCalendar2(PartidaDia.getText(), PartidaDiaFormato);
            new DatePickerDialog(this, this, calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)).show();
        } else if (v == PartidaHora) {
            Calendar calendario = parseCalendar2(PartidaHora.getText(), PartidaHoraFormato);
            new TimePickerDialog(this, this,
                    calendario.get(Calendar.HOUR_OF_DAY),
                    calendario.get(Calendar.MINUTE), false) // /true = 24 LlegadaHoras
                    .show();
        }
    }

    private Calendar parseCalendar(CharSequence text,
                                   SimpleDateFormat LlegadaDiaFormat2) {
        try {
            LlegadaDiaConv = LlegadaDiaFormat2.parse(text.toString());
        } catch (ParseException e) { // import java.text.ParsedExc
            throw new RuntimeException(e);
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(LlegadaDiaConv);
        return calendario;
    }
    private Calendar parseCalendar2(CharSequence text,
                                   SimpleDateFormat PartidaDiaFormat2) {
        try {
            PartidaDiaConv = PartidaDiaFormat2.parse(text.toString());
        } catch (ParseException e) { // import java.text.ParsedExc
            throw new RuntimeException(e);
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(PartidaDiaConv);
        return calendario;
    }

    @Override
    public void onDateSet(DatePicker picker, int anio, int mes, int dia) {
        calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, anio);
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, dia);

        LlegadaDiaSel = LlegadaDiaFormato.format(calendario.getTime());
        LlegadaDia.setText(LlegadaDiaSel);
        PartidaDiaSel = PartidaDiaFormato.format(calendario.getTime());
        PartidaDia.setText(PartidaDiaSel);

    }

    public void onTimeSet(TimePicker picker, int LlegadaHoras, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, LlegadaHoras);
        calendar.set(Calendar.MINUTE, minutos);

        LlegadaHoraSel = LlegadaHoraFormato.format(calendar.getTime());
        LlegadaHora.setText(LlegadaHoraSel);
        PartidaHoraSel = PartidaHoraFormato.format(calendar.getTime());
        PartidaHora.setText(PartidaHoraSel);
    }

    public void reserva(View v) {
        Intent envia = new Intent(this, Act2.class);
        Bundle datos = new Bundle();
        datos.putString("nombre", editTextNombre.getText().toString().trim());
        datos.putInt("compas", compas);
        datos.putString("LlegadaDia", LlegadaDiaSel);
        datos.putString("LlegadaHora", LlegadaHoraSel);
        datos.putString("PartidaDia", PartidaDiaSel);
        datos.putString("PartidaHora", PartidaHoraSel);
        datos.putInt("habs", minhabs);
        datos.putBoolean("Todo", switchTodo.isChecked());
        envia.putExtras(datos);
        finish();
        startActivity(envia);
    }

}