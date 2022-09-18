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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the instance of Spinner and setting the list from resources
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Monedas,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        //Getting button
        Button boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =
                        new Intent(MainActivity.this,
                                SimularPlazoFijo.class);
//                i.putExtra("primerLetra"
//                        ,edtLetra.getText().toString());
                startActivityForResult(i, 1); //TODO do we still use it like this?
//                startActivity(new Intent(MainActivity.this, SimularPlazoFijo.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Button botonConstruir = findViewById(R.id.botonConstituir);
                botonConstruir.setEnabled(true);
                Double capital = data.getExtras().getDouble("capital");
                Integer dias = data.getExtras().getInt("dias");

                botonConstruir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
                        EditText editTextTextApellido = findViewById(R.id.editTextTextApellido);

                        builder.setTitle("Felicitaciones " + editTextTextPersonName.getText().toString()+" "+editTextTextApellido.getText().toString());
                        builder.setMessage("Tu plazo fijo de "+capital.toString()+" pesos por "+dias.toString()+" dias ha sido construido!");
                        builder.setPositiveButton("piola!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // START THE GAME!
                            }
                        });
//                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        }
//                        );
                        builder.show();
//        // Create the AlertDialog object and return it
//        return builder.create();
                    }
                });
//                datos.setText("SELECCIONO <" + producto + "> Cantidad:"+cantidad+" unidades ");
            }
        }
    }

}




