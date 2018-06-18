package com.job.nutriplayapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ModulosAdapter extends RecyclerView.Adapter<ModulosAdapter.ViewHolder> {

    private List<Modulo> modulos;

    public ModulosAdapter() {
        this.modulos = new ArrayList<>();
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;
        public TextView titulo;
        public TextView descripcion;
        public ImageView desplegable;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.picture_image);
            titulo = (TextView) itemView.findViewById(R.id.titulo_text);
            descripcion = (TextView) itemView.findViewById(R.id.desc_text);
            desplegable = (ImageView) itemView.findViewById(R.id.desplegableButton);
        }
    }

    @Override
    public ModulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modulo, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ModulosAdapter.ViewHolder viewHolder, int position) {
        Modulo modulo = this.modulos.get(position);
        viewHolder.titulo.setText(modulo.getTitulo());
        viewHolder.descripcion.setText(modulo.getDescripcion());

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/nutriplayapp.appspot.com/o/avatares%2Fexample.jpg?alt=media&token=1ac328d0-aa32-450d-a54b-492ca1b0ff52").into(viewHolder.picture);

        /*Context context = viewHolder.itemView.getContext();
        int idRes = context.getResources().getIdentifier(modulo.getPicture(), "drawable", context.getPackageName());
        viewHolder.picture.setImageResource(idRes);*/

    }

    @Override
    public int getItemCount() {
        return this.modulos.size();
    }

}