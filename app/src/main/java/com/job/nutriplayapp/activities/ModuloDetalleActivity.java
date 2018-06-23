package com.job.nutriplayapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.job.nutriplayapp.models.Contenido;
import com.job.nutriplayapp.models.Modulo;
import com.job.nutriplayapp.R;

import java.util.List;

public class ModuloDetalleActivity extends AppCompatActivity {

    private TextView texto1, texto2;
    private ImageView imagen1, imagen2;
    private List<Contenido> contenidoList;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_detalle);

        texto1 = findViewById(R.id.text_cont_1);
        texto2 = findViewById(R.id.text_cont_2);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();
        String titulo = getIntent().getExtras().getString("titulo");
        final String cont_texto1 = getIntent().getExtras().getString("texto1");

        texto1.setText(titulo);
        texto2.setText(cont_texto1);

        final DatabaseReference userData = FirebaseDatabase.getInstance().getReference();
        userData.child("modulo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()){
                    userData.child("coleccion_modulo").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot dsp : dataSnapshot.getChildren()){
                                if(ds.getKey().equals(dsp.getKey())){
                                    Modulo modulo = ds.getValue(Modulo.class);
                                    Log.d("modulo", modulo.toString());
                                    Log.d("contenido1", modulo.getContenido().getTexto1());
                                    texto1.setText(modulo.getContenido().getTexto1());
                                  /*  ds.child("contenido").getRef().addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for(DataSnapshot dsps : dataSnapshot.getChildren()){
                                                    Contenido contenido = dsps.getValue(Contenido.class);
                                                    Log.d("cont", contenido.toString());
                                                    contenidoList.add(contenido);

                                            }
                                            Log.d("contenidos", contenidoList.toString());
                                            texto1.setText(contenidoList.get(0).getTexto());
                                            texto2.setText(contenidoList.get(1).getTexto());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });*/
                                  //  String co = String.valueOf(dsp.child("contenido").getChildren());
                                  //  Log.d("dddd", co);
                                   // texto1.setText(dsp.child("contenido").child());
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
