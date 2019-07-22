package com.example.celulares;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.celulares.R;

import java.util.ArrayList;

public class AdaptadorCelular extends RecyclerView.Adapter<AdaptadorCelular.CelularViewHolder> {
    private ArrayList<Celular> celulares;
    private OnCelularClickListener clickListener;

    public AdaptadorCelular(ArrayList<Celular> celulares, OnCelularClickListener clickListener){
        this.celulares = celulares;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public CelularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_celular,viewGroup,false);
        return new CelularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CelularViewHolder celularViewHolder, int i) {
        final Celular cel = celulares.get(i);
        celularViewHolder.foto.setImageResource(cel.getFoto());
        celularViewHolder.codigo.setText(cel.getCodigo());
        celularViewHolder.marca.setText(cel.getMarca());
        celularViewHolder.modelo.setText(cel.getModelo());
        celularViewHolder.ram.setText(cel.getRam());
        celularViewHolder.almacenamiento.setText(cel.getAlmacenamiento());

        celularViewHolder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCelularClick(cel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return celulares.size();
    }

    public static class CelularViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView codigo;
        private TextView marca;
        private TextView modelo;
        private TextView ram;
        private TextView almacenamiento;
        private View v;

        public CelularViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.foto);
            codigo = v.findViewById(R.id.lblCodigo);
            marca = v.findViewById(R.id.lblMarca);
            modelo = v.findViewById(R.id.lblModelo);
            ram = v.findViewById(R.id.lblRam);
            almacenamiento = v.findViewById(R.id.lblAlmacenamiento);

        }
    }

    public interface OnCelularClickListener{
        void onCelularClick(Celular cel);
    }
}
