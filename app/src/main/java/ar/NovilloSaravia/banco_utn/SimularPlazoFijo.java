package ar.NovilloSaravia.banco_utn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;

import java.text.DecimalFormat;

import ar.NovilloSaravia.banco_utn.databinding.ActivitySimularPlazoFijoBinding;

public class SimularPlazoFijo extends AppCompatActivity {

    private ActivitySimularPlazoFijoBinding binding;
    private boolean btnConfirmar = false;
    private static final DecimalFormat df = new DecimalFormat("0.00");


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
        binding.seekBar1.setMax(360);
        binding.seekBar1.incrementProgressBy(1);
        binding.seekBar1.setProgress(0);
        binding.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textoSeekBar.setText(progress+" dias");
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
                intentResultado.putExtra("dias",binding.seekBar1.getProgress());
                setResult(Activity.RESULT_OK,intentResultado);
                finish();

            }
        });
    }

    protected void calcular(){

        int dias = binding.seekBar1.getProgress();


        String textoCapital=binding.textoCapital.getText().toString();


        String textoTasaNominal = binding.textoTasaNominal.getText().toString();
        Double tasaNominal;
        if (!textoTasaNominal.isEmpty()){
            tasaNominal=Double.valueOf(textoTasaNominal);
        }
        else{
            tasaNominal=Double.valueOf(0);
        }
        Double capital;
        if (!textoCapital.isEmpty()){
            capital=Double.valueOf(textoCapital);
        }else{
            capital=Double.valueOf(0);
        }
        Double meses = Double.valueOf(dias)/12/30;
        Double intereses= (tasaNominal/100)*capital*meses;

        binding.plazo.setText("Plazo: "+dias+" dias");
        binding.capital.setText("Capital: "+df.format(capital));
        binding.interesesGanados.setText("Intereses ganados: $"+df.format(intereses));
        binding.montoTotal.setText("Montot total: $"+df.format(intereses+capital));
        binding.montoTotalAnual.setText("Monto total anual: $"+df.format(capital+intereses*12));

        //check that all the values are valid if something is invalid disable button
        if (capital.equals(Double.valueOf(0))){
            binding.botonConfirmar.setEnabled(false);
            btnConfirmar = false;
        }
        else{
            binding.botonConfirmar.setEnabled(true);
            btnConfirmar = true;
        }

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