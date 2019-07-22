package com.example.celulares;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celulares.R;

public class DetalleCelular extends AppCompatActivity {
    private TextView codigo;
    private TextView marca;
    private TextView modelo;
    private TextView ram;
    private TextView almacenamiento;
    private ImageView foto;
    private Bundle bundle;
    private Intent i;
    private int fot;
    private String cod,marc,mod,ra,alm,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_celular);
        codigo = findViewById(R.id.lblCodigoD);
        marca = findViewById(R.id.lblMarcaD);
        modelo = findViewById(R.id.lblModeloD);
        ram = findViewById(R.id.lblRamD);
        almacenamiento = findViewById(R.id.lblAlmacenamientoD);
        foto = findViewById(R.id.fotoD);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        cod = bundle.getString("codigo");
        marc = bundle.getString("marca");
        mod = bundle.getString("modelo");
        ra = bundle.getString("ram");
        alm = bundle.getString("almacenamiento");
        fot = bundle.getInt("foto");
        id = bundle.getString("id");

        codigo.setText(cod);
        marca.setText(marc);
        modelo.setText(mod);
        ram.setText(ra);
        almacenamiento.setText(alm);
        foto.setImageResource(fot);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetalleCelular.this, MainActivity.class);
        startActivity(i);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.titulo_eliminar));
        builder.setMessage(getResources().getString(R.string.mensaje_eliminar));
        positivo = getResources().getString(R.string.positivo);
        negativo = getResources().getString(R.string.negativo);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Celular cel = new Celular();
                cel.setId(id);
                cel.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
