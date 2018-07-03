package com.job.nutriplayapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.job.nutriplayapp.R;
import com.job.nutriplayapp.adapters.JuegoAdapter;
import com.job.nutriplayapp.adapters.ModulosAdapter;
import com.job.nutriplayapp.models.Juego;
import com.job.nutriplayapp.models.Modulo;

import java.util.ArrayList;
import java.util.List;

public class JuegoActivity extends AppCompatActivity {

    ListView listView;
    private RecyclerView juegosList;
    private DatabaseReference mDatabase;
    private String pregunta_id;
    private List<Juego> juegos = new ArrayList<Juego>();
    private int n = 0;
    ProgressDialog progress;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        juegosList = (RecyclerView) findViewById(R.id.juegos_list);
        progress = new ProgressDialog(this);
        progress.setTitle("Cargando");
        progress.setMessage("Por favor espere...");
        progress.setCancelable(false);
        progress.show();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("juego").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    mDatabase.child("coleccion_juego").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                boolean estado = dsp.getValue(Boolean.class);


                                if (estado) {
                                    String id_juego = dsp.getKey();
                                    if (ds.getKey().equals(id_juego)) {
                                        Juego game = ds.getValue(Juego.class);
                                        game.setId(ds.getKey());
                                        juegos.add(game);
                                        JuegoAdapter adapter = new JuegoAdapter(JuegoActivity.this);
                                        adapter.setJuegos(juegos);
                                        juegosList.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
                                        juegosList.setAdapter(adapter);
                                    }
                                }
                            }
                            progress.dismiss();
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
