package com.example.duvan.conversionmoneda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final String[] datos= new String[] {"Peso", "Dolar", "Euro"};

    private Spinner sMonorig;
    private Spinner sMondest;
    private TextView eIngreseValor, tResultado;


    final private double factorPesoDolar = 0.00033;
    final private double factorPesoEuro = 0.00028;
    final private double factorDolarPeso = 3050;
    final private double factorDolarEuro = 0.86;
    final private double factorEuroPeso = 3542;
    final private double factorEuroDolar = 1.16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, datos);

        sMonorig = (Spinner) findViewById(R.id.sMonorig);
        sMonorig.setAdapter(adapter);
    }


    public void clickConvertir(View view) {

        sMonorig = (Spinner)findViewById(R.id.sMonorig);
        sMondest = (Spinner)findViewById(R.id.sMondest);
        eIngreseValor = (TextView) findViewById(R.id.eIngreseValor);
        tResultado = (TextView) findViewById(R.id.tResultado);

        String origen = sMonorig.getSelectedItem().toString();
        String destino = sMondest.getSelectedItem().toString();

        double ingreseValor = Double.parseDouble(eIngreseValor.getText().toString());
        double resultado = procesarConversion(origen, destino, ingreseValor);

        if(resultado>0){
            tResultado.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s ", ingreseValor, origen, resultado, destino ));
            eIngreseValor.setText("");
        }else
        {
            tResultado.setText(String.format("Usted recibirá"));
            Toast.makeText(MainActivity.this, "Las opciones elegidas no tienen un factor de conversion ", Toast.LENGTH_SHORT).show();
        }
    }


    private double procesarConversion(String origen, String destino, double ingreseValor) {
        double resultadoConversion = 0;

        switch (origen){
            case "Peso":
                if(destino.equals("Dolar"))
                    resultadoConversion = ingreseValor * factorPesoDolar;

                if(destino.equals("Euro"))
                    resultadoConversion = ingreseValor * factorPesoEuro;

                break;
            case "Dolar":
                if(destino.equals("Peso"))
                    resultadoConversion = ingreseValor * factorDolarPeso;

                if(destino.equals("Euro"))
                    resultadoConversion = ingreseValor * factorDolarEuro;

                break;
            case "Euro":
                if(destino.equals("Peso"))
                    resultadoConversion = ingreseValor * factorEuroPeso;

                if(destino.equals("Dolar"))
                    resultadoConversion = ingreseValor * factorEuroDolar;

                break;
        }
        return resultadoConversion;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}