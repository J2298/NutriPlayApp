package com.job.nutriplayapp.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.job.nutriplayapp.R;
import com.job.nutriplayapp.additional.ViewDialog;

public class DetalleJuegoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private boolean estado;
    private TextView pregunta;
    private String descripcion;
    private String juego_id;
    private CardView cardVerdad, cardFalso;
    private String acierto = "¡Acertaste!";
    private String fallo = "¡Fallaste!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        final String titulo =  getIntent().getExtras().getString("titulo");

        cardVerdad = findViewById(R.id.cardVerdad);
        cardFalso = findViewById(R.id.cardFalso);
        pregunta = findViewById(R.id.preguntaText);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("juego").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (titulo.equals(ds.child("titulo").getValue(String.class))) {
                        juego_id = ds.getKey();
                        descripcion = ds.child("respuesta").getValue(String.class);
                        estado = ds.child("estado").getValue(Boolean.class);
                        pregunta.setText(ds.child("pregunta").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void verdadTapped(View view) {
        ViewDialog alert = new ViewDialog();
        if(estado){
            Log.d("Respuesta:", "Respuesta correcta");
            cardVerdad.setCardBackgroundColor(Color.GREEN);
            alert.showDialog(DetalleJuegoActivity.this, descripcion, acierto);
        }else{
            Log.d("Respuesta:", "Respuesta incorrecta");
            cardVerdad.setCardBackgroundColor(Color.RED);
            alert.showDialog(DetalleJuegoActivity.this, descripcion, fallo);
        }
        borrarJuego();
    }

    public void falsoTapped(View view) {
        ViewDialog alert = new ViewDialog();
        if(!estado){
            Log.d("Respuesta:", "Respuesta correcta");
            cardFalso.setCardBackgroundColor(Color.GREEN);
            alert.showDialog(DetalleJuegoActivity.this, descripcion, acierto);
        }else{
            Log.d("Respuesta:", "Respuesta incorrecta");
            cardFalso.setCardBackgroundColor(Color.RED);
            alert.showDialog(DetalleJuegoActivity.this, descripcion, fallo);
        }
        borrarJuego();
    }

    public void borrarJuego(){
        final String id_usu = "1";
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("coleccion_juego").child(id_usu).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (dsp.getKey().equals(juego_id)){
                        Log.d("Eliminar", "Eliminando juego " + dsp.getKey() + "de los mitos");
                        dsp.getRef().setValue(false);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Eliminar", "Hay errores");
            }
        });
    }
}
