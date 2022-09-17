package ar.NovilloSaravia.banco_utn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SimularPlazoFijo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular_plazo_fijo);

        EditText textoCapital = findViewById(R.id.textoCapital);
        textoCapital.addTextChangedListener(new TextWatcher() { //TODO ask about this implementation
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });

        EditText textoTasaNominal = findViewById(R.id.textoTasaNominal);
        textoTasaNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });

        EditText textoTasaEfectiva = findViewById(R.id.textoTasaEfectiva);
        textoTasaEfectiva.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });


        //Captura de cambio de valor en SeekBar
        TextView textoSeekBar = findViewById(R.id.textoSeekBar);
        SeekBar seekBar = findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textoSeekBar.setText((progress*360)/100+" dias");
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Button botonConfirmar = findViewById(R.id.botonConfirmar);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textoCapital = findViewById(R.id.textoCapital);
                SeekBar seekBar = findViewById(R.id.seekBar1);
                Intent intentResultado = new Intent();
                intentResultado.putExtra("capital",
                        Double.valueOf(
                                textoCapital.getText().toString())
                );
                intentResultado.putExtra("dias",(seekBar.getProgress()*360)/100);
                setResult(Activity.RESULT_OK,intentResultado);
                finish();

            }
        });
    }

    protected void calcular(){
        //Getting Detalles
        SeekBar seekBar = findViewById(R.id.seekBar1);
        TextView plazo = findViewById(R.id.plazo);
        plazo.setText("Plazo: "+(seekBar.getProgress()*360)/100+" dias");

        EditText textoCapital = findViewById(R.id.textoCapital);
        TextView capital = findViewById(R.id.capital);
        capital.setText("Capital: "+textoCapital.getText());

        EditText textoTasaNominal = findViewById(R.id.textoTasaNominal);
        EditText textoTasaEfectiva = findViewById(R.id.textoTasaEfectiva);
        //TODO calculate intereses
        TextView interesesGanados = findViewById(R.id.interesesGanados);
        interesesGanados.setText("Intereses ganados: $ intereses");

        TextView montoTotal = findViewById(R.id.montoTotal);
        montoTotal.setText("Montot total: $ Intereses + capital");

        TextView montoTotalAnual = findViewById(R.id.montoTotalAnual);
        montoTotalAnual.setText("Monto total anual: $"+textoTasaNominal.getText());

        Button botonConfirmar = findViewById(R.id.botonConfirmar);

        //TODO check that all the values are valid if something is invalid disable button
        botonConfirmar.setEnabled(true);
    }


}