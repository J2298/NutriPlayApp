package com.job.nutriplayapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.ViewHolder> {

    private List<Receta> recetas;
    private DatabaseReference mDatabase;
    private TiendaAdapter adapter;
    public TiendaAdapter(){
        this.recetas = new ArrayList<>();
        this.adapter = this;
    }
    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView picture;
        public TextView titulo;
        public TextView descripcion;
        public Button btnComprar;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.picture_image);
            titulo = (TextView) itemView.findViewById(R.id.titulo_text);
            descripcion = (TextView) itemView.findViewById(R.id.desc_text);
            btnComprar = itemView.findViewById(R.id.btnComprar);
        }
    }
    @Override
    public TiendaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tienda, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TiendaAdapter.ViewHolder viewHolder, int position) {
        final Receta receta = this.recetas.get(position);
        viewHolder.titulo.setText(receta.getTitulo());
        viewHolder.descripcion.setText(receta.getDescripcion());

        Picasso.get().load(receta.getImagen()).into(viewHolder.picture);


        viewHolder.btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("Tienda", "Comprando..");

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String uid = currentUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("coleccion").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            String id_receta = receta.getId();
                            if (dsp.getKey().equals(id_receta)){
                                Log.d("Compra", "Agregando receta " + dsp.getKey() + " a Mis Recetas");
                                dsp.getRef().setValue(true);
                                ((Activity)view.getContext()).finish();
                                view.getContext().startActivity(((Activity)view.getContext()).getIntent());

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.recetas.size();
    }
}