package com.job.nutriplayapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private String pregunta_id;
    private ArrayList<String> content = new ArrayList<>();
    private int n = 0;
    ProgressDialog progress;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        listView = findViewById(R.id.listView);
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


                                if (estado == true) {
                                    String id_juego = dsp.getKey();
                                    if (ds.getKey().equals(id_juego)) {
                                        content.add(ds.child("titulo").getValue(String.class));
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(JuegoActivity.this, android.R.layout.simple_list_item_1, content);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            }
                            progress.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //  Log.d("id_re", ds.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(JuegoActivity.this, DetalleJuegoActivity.class);
                intent.putExtra("titulo", content.get(i));
                startActivity(intent);
                finish();

            }
        });

    }
}
