package com.example.celulares;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.celulares.R;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCelular extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText codigo, marca, modelo, ram, almacenamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_celular);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.foto1);
        fotos.add(R.drawable.foto2);
        fotos.add(R.drawable.foto3);
        codigo = findViewById(R.id.txtCodigo);
        marca = findViewById(R.id.txtMarca);
        modelo = findViewById(R.id.txtModelo);
        ram = findViewById(R.id.txtRam);
        almacenamiento = findViewById(R.id.txtAlmacenamiento);
    }

    public void guardar(View v){
        String cod, marc, mod, ra, alm, id;
        int foto;
        if(validar()){
            id = Datos.getId();
            cod = codigo.getText().toString();
            marc = marca.getText().toString();
            mod = modelo.getText().toString();
            ra = ram.getText().toString();
            alm = almacenamiento.getText().toString();

            foto = this.fotoAleatoria();
            Celular cel = new Celular(id,foto,cod,marc,mod,ra,alm);
            cel.guardar();
            limpiar();

            Snackbar.make(v,getResources().getString(R.string.mensaje),Snackbar.LENGTH_SHORT).show();
        }

    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        codigo.setText("");
        marca.setText("");
        modelo.setText("");
        ram.setText("");
        almacenamiento.setText("");
        codigo.setError(null);
        codigo.requestFocus();
    }
    public boolean validar(){
        ArrayList<Celular> celulares = Datos.obtener();
        for (Celular cel: celulares
             ) {
            if(cel.getCodigo().equals(codigo.getText().toString())){
                codigo.setError(getResources().getString(R.string.error_duplicado));
                codigo.requestFocus();
                return false;
            }
        }
        return true;
    }
    public int fotoAleatoria(){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(this.fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCelular.this,MainActivity.class);
        startActivity(i);
    }
}
