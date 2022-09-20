package ar.NovilloSaravia.banco_utn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import ar.NovilloSaravia.banco_utn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting the instance of Spinner and setting the list from resources
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Monedas,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter2);

        //Getting button
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =
                        new Intent(MainActivity.this,
                                SimularPlazoFijo.class);
                startActivityForResult(i, 1); //TODO do we still use it like this?
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                binding.botonConstituir.setEnabled(true);
                Double capital = data.getExtras().getDouble("capital");
                Integer dias = data.getExtras().getInt("dias");

                binding.botonConstituir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                        builder.setTitle("Felicitaciones " + binding.editTextTextPersonName.getText().toString()+" "+binding.editTextTextApellido.getText().toString());
                        builder.setMessage("Tu plazo fijo de "+capital.toString()+" pesos por "+dias.toString()+" dias ha sido construido!");
                        builder.setPositiveButton("piola!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                            }
                        });
                        builder.show();
                    }
                });
            }
        }
    }

}




