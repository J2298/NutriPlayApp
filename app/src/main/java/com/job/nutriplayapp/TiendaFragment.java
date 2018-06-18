package com.job.nutriplayapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TiendaFragment extends Fragment {

    View view;
    private RecyclerView misrecetasList;
    private DatabaseReference mDatabase;
    private List<Receta> recetas = new ArrayList<>();
    private String uid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tienda, container, false);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();

        misrecetasList = (RecyclerView) view.findViewById(R.id.tiendaLista);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("receta").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                            mDatabase.child("coleccion_receta").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                                        boolean estado = dsp.getValue(Boolean.class);
                                        if (estado == false) {
                                            String id_receta = dsp.getKey();
                                            if (ds.getKey().equals(id_receta)) {
                                                Log.d("Accion", "Añadiendo receta a la tienda..");
                                                Receta receta = ds.getValue(Receta.class);
                                                receta.setId(ds.getKey());
                                                recetas.add(receta);
                                                TiendaAdapter adapter = new TiendaAdapter();
                                                adapter.setRecetas(recetas);
                                                misrecetasList.setAdapter(adapter);
                                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                                misrecetasList.setLayoutManager(layoutManager);
                                            }
                                        }
                                    }
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

        //Receta receta = new Receta("Jugo de fresa", "dec3");

        return view;
    }
    public void back() {
        getActivity().onBackPressed();
    }
}
