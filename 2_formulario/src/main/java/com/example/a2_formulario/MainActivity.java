package com.example.a2_formulario;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText eNombre, ePassword, eRePassword, eCorreo;
    private TextView tResult, tdatos;
    Button bfecha;
    EditText eFecha;
    private Spinner sCiudades;
    private String sexo= "Masculino", ciudad= "Medellin";
    private int dia, mes, ano;
    CheckBox bici, lee, baile, pelis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = findViewById(R.id.eNombre);
        ePassword = findViewById(R.id.ePassword);
        eRePassword = findViewById(R.id.eRePassword);
        eCorreo = findViewById(R.id.eCorreo);
        tResult = findViewById(R.id.tResultado);
        sCiudades = findViewById(R.id.sCiudades);
        bfecha= (Button) findViewById(R.id.bfecha);
        eFecha= (EditText) findViewById(R.id.eFecha);
        bfecha.setOnClickListener(this);
        bici= (CheckBox) findViewById(R.id.bici);
        lee= (CheckBox) findViewById(R.id.lee);
        baile= (CheckBox) findViewById(R.id.baile);
        pelis= (CheckBox) findViewById(R.id.pelis);




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ciudades,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(this);

        final ArrayList<String> eNombre = new ArrayList<String>();
        final ArrayList<String> eCorreo = new ArrayList<String>();
        final ArrayList<String> sCiudades = new ArrayList<String>();
        final ArrayList<String> eFecha = new ArrayList<String>();
        final ArrayList<String>  sexo = new ArrayList<String>();



    }

    public void guardarClicked(View view) {
        String name, pass, repass, email, dateBirth, bike, read, dance, movies;


        name= eNombre.getText().toString();
        pass= ePassword.getText().toString();
        repass= eRePassword.getText().toString();
        email= eCorreo.getText().toString();
        dateBirth= eFecha.getText().toString();
        bike= bici.getText().toString();
        read= lee.getText().toString();
        dance= baile.getText().toString();
        movies=pelis.getText().toString();





        if (name.equals ("") || pass.equals("") || repass.equals("") || email.equals("") || dateBirth.equals (""))
            Toast.makeText(getApplicationContext(), "Debe digitar todos los campos", Toast.LENGTH_SHORT).show();

        else
            if (pass.equals(repass))
                tResult.setText(name+" - "+email+" - "+sexo+" - "+ciudad+" - "+dateBirth+" - "+bike+" - "+read+" - "+dance+" - "+movies);
            /*else if(bici.isChecked()){
                tResult.setText(bici.getText());
            }
            else if(lee.isChecked()){
                tResult.setText(lee.getText());
            }
            else if(baile.isChecked()){
                tResult.setText(baile.getText());
            }
            else if(pelis.isChecked()){
                tResult.setText(pelis.getText());
            } */

            else
                Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();

        if (id == R.id.rMasculino)
            sexo = "Masculino";
        else
            sexo = "Femenino";
        

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ciudad= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v==bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
            ,dia, mes,ano);
            datePickerDialog.show();
        }
    }

    public void buscarClicked(View view) {
    }

    public void eliminarClicked(View view) {
    }

    public void verClicked(View view) {
    }
}
