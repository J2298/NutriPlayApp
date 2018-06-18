package com.job.nutriplayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ModuloActivity extends AppCompatActivity {

    private RecyclerView modulosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo);

        modulosList = (RecyclerView)findViewById(R.id.modulos_list);

        modulosList.setLayoutManager(new LinearLayoutManager(this));

        ModulosAdapter adapter = new ModulosAdapter();

        List<Modulo> modulos = ModuloRepository.getModulos();
        adapter.setModulos(modulos);

        modulosList.setAdapter(adapter);
    }
}
