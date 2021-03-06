package com.job.nutriplayapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.job.nutriplayapp.utilidades.MyViewPagerAdapter;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ModuloDetalleActivity extends AppCompatActivity {

    private TextView texto1, texto2;
    private ImageView imagen1, imagen2;
    private List<Contenido> contenidoList;
    private String uid;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnVolver, btnSiguiente;
    private Modulo modulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_modulo_detalle);


        //Inicialización de la librería de fuentes de texto
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Overlock-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnVolver = (Button) findViewById(R.id.btn_volver);
        btnSiguiente = (Button) findViewById(R.id.btn_siguiente);


        /*texto1 = findViewById(R.id.text_cont_1);
        texto2 = findViewById(R.id.text_cont_2);*/

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();
        String titulo = getIntent().getExtras().getString("titulo");
        final String cont_texto1 = getIntent().getExtras().getString("texto1");
/*
        texto1.setText(titulo);
        texto2.setText(cont_texto1);*/

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.modulo_slide1,
                R.layout.modulo_slide2,
                R.layout.modulo_slide3};

        // adición de puntos inferiores
        addPuntosInferiores(0);

        // making notification bar transparent
        changeStatusBarColor();


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(0);
                Log.d("current",""+current);
                if (current == 0) {
                    irAlPrincipal();
                } else {
                    // move to last screen
                    viewPager.setCurrentItem(current-1);
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    //Iniciar preguntas
                    irAlCuestionario();
                }
            }
        });

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
                                    modulo = ds.getValue(Modulo.class);
                                    Log.d("modulo", modulo.toString());
                                    Log.d("contenido1", modulo.getContenido().getTexto1());

                                    /****************************/
                                    myViewPagerAdapter = new MyViewPagerAdapter(layouts,ModuloDetalleActivity.this,modulo);
                                    viewPager.setAdapter(myViewPagerAdapter);
                                    viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

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
    private void addPuntosInferiores(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addPuntosInferiores(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnSiguiente.setText("¡GANA MONEDAS!");
                btnVolver.setText("ATRÁS");
            } else {
                // still pages are left
                btnSiguiente.setText("SIGUIENTE");
                btnVolver.setText("ATRÁS");
            }
            if (position == 0){
                btnVolver.setText("SALIR");
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void irAlPrincipal() {
        startActivity(new Intent(ModuloDetalleActivity.this, HomeActivity.class));
        finish();
    }

    private void irAlCuestionario() {
        Intent intent = new Intent(ModuloDetalleActivity.this, ModuloCuestionarioActivity.class);
        Log.d("Modulo1111",modulo.toString());
        intent.putExtra("Modulo",modulo);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
