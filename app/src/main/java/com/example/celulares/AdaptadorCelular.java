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
        celularViewHolder.marcaModelo.setText(cel.getMarca()+" "+cel.getModelo());

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
        private View v;
        private TextView marcaModelo;

        public CelularViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.foto);
            marcaModelo = v.findViewById(R.id.lblMarcaModelo);

        }
    }

    public interface OnCelularClickListener{
        void onCelularClick(Celular cel);
    }
}
