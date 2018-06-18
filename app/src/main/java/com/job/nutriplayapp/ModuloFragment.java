package com.job.nutriplayapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModuloFragment extends Fragment {

    View view;
    private RecyclerView modulosList;
    private DatabaseReference mDatabase;
    private  List<Modulo> modulos = new ArrayList<>();
    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_modulo, container, false);
        modulosList = (RecyclerView)view.findViewById(R.id.modulos_list);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("modulo").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (final DataSnapshot dsp : dataSnapshot.getChildren()){
                            mDatabase.child("coleccion_modulo").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                                        boolean estado = ds.getValue(Boolean.class);
                                        if(estado == true){
                                            String id_modulo = ds.getKey();
                                            if(ds.getKey().equals(id_modulo)){
                                                Modulo modulo = dsp.getValue(Modulo.class);
                                                modulo.setId(dsp.getKey());
                                                modulos.add(modulo);
                                                ModulosAdapter adapter = new ModulosAdapter();
                                                adapter.setModulos(modulos);
                                                modulosList.setAdapter(adapter);
                                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                                modulosList.setLayoutManager(layoutManager);
                                            }
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
                }
        );




       /* ModulosAdapter adapter = new ModulosAdapter();

        List<Modulo> modulos = ModuloRepository.getModulos();
        adapter.setModulos(modulos);

        modulosList.setAdapter(adapter);*/

        return view;
    }
}
