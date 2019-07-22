package com.example.celulares;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorCelular.OnCelularClickListener {
    private RecyclerView lstOpciones;
    private Intent i;
    private ArrayList<Celular> celulares;
    private LinearLayoutManager llm;
    private String db = "Celulares";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lstOpciones = findViewById(R.id.lstCelulares);
        celulares = new ArrayList<>();
        final AdaptadorCelular adapter = new AdaptadorCelular(celulares,this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        lstOpciones.setLayoutManager(llm);
        lstOpciones.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                celulares.clear();
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Celular cel = snapshot.getValue(Celular.class);
                        celulares.add(cel);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPersonas(celulares);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void agregarCelular(View v){
        i = new Intent(MainActivity.this,AgregarCelular.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onCelularClick(Celular p) {
        Intent i = new Intent(MainActivity.this, DetalleCelular.class);
        Bundle b = new Bundle();
        b.putString("id",p.getId());
        b.putString("codigo",p.getCodigo());
        b.putString("marca",p.getMarca());
        b.putString("modelo",p.getModelo());
        b.putString("ram",p.getRam());
        b.putString("almacenamiento",p.getAlmacenamiento());
        b.putInt("foto",p.getFoto());
        i.putExtra("datos",b);
        startActivity(i);
        finish();
    }
}
