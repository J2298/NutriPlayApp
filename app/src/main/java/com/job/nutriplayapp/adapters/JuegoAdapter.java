package com.job.nutriplayapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.job.nutriplayapp.R;
import com.job.nutriplayapp.activities.DetalleJuegoActivity;
import com.job.nutriplayapp.models.Juego;
import com.job.nutriplayapp.models.Modulo;

import java.util.ArrayList;
import java.util.List;

public class JuegoAdapter extends RecyclerView.Adapter<JuegoAdapter.ViewHolder> {

    private List<Juego> juegos;

    private Activity activity;

    public JuegoAdapter(Activity activity) {
        this.juegos = new ArrayList<>();
        this.activity = activity;
    }


    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public FloatingActionButton pregunta;

        public ViewHolder(View itemView) {
            super(itemView);
            pregunta = (FloatingActionButton) itemView.findViewById(R.id.float_pregunta);
        }
    }

    @Override
    public JuegoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juego, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Juego juego = this.juegos.get(position);
        Log.d("as:", juego.getTitulo()+"    aaaaaaaaaaaaaa");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("as:", juego.getId()+"    aaaaaaaaaaaaaa");
                Intent intent = new Intent(activity, DetalleJuegoActivity.class);
                intent.putExtra("ID", "" + juego.getId());

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.juegos.size();
    }

}