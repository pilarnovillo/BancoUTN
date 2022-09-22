package ar.NovilloSaravia.banco_utn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;

import ar.NovilloSaravia.banco_utn.databinding.ActivitySimularPlazoFijoBinding;

public class SimularPlazoFijo extends AppCompatActivity {

    private ActivitySimularPlazoFijoBinding binding;
    private boolean btnConfirmar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySimularPlazoFijoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textoCapital.addTextChangedListener(new TextWatcher() { //TODO ask about this implementation
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

        binding.textoTasaNominal.addTextChangedListener(new TextWatcher() {
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

        binding.textoTasaEfectiva.addTextChangedListener(new TextWatcher() {
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
        binding.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textoSeekBar.setText((progress*360)/100+" dias");
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        binding.botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResultado = new Intent();
                intentResultado.putExtra("capital",
                        Double.valueOf(
                                binding.textoCapital.getText().toString())
                );
                intentResultado.putExtra("dias",(binding.seekBar1.getProgress()*360)/100);
                setResult(Activity.RESULT_OK,intentResultado);
                finish();

            }
        });
    }

    protected void calcular(){
        //Getting Detalles
        binding.plazo.setText("Plazo: "+(binding.seekBar1.getProgress()*360)/100+" dias");

        binding.capital.setText("Capital: "+binding.textoCapital.getText());

//        EditText textoTasaNominal = findViewById(R.id.textoTasaNominal);
//        EditText textoTasaEfectiva = findViewById(R.id.textoTasaEfectiva);
        //TODO calculate intereses
        binding.interesesGanados.setText("Intereses ganados: $ intereses");

        binding.montoTotal.setText("Montot total: $ Intereses + capital");

        binding.montoTotalAnual.setText("Monto total anual: $"+binding.textoTasaNominal.getText());

        //TODO check that all the values are valid if something is invalid disable button
        binding.botonConfirmar.setEnabled(true);
        btnConfirmar = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("btnConfirmar", btnConfirmar);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        btnConfirmar = savedInstanceState.getBoolean("btnConfirmar");
        binding.botonConfirmar.setEnabled(btnConfirmar);
    }

}