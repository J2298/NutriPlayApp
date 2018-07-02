package com.job.nutriplayapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.job.nutriplayapp.R;
import com.job.nutriplayapp.models.Modulo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Modifier;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ModuloCuestionarioActivity extends AppCompatActivity {

    private Modulo modulo;
    private TextView pregunta1,pregunta2,pregunta3,ganaste_cantidad;
    private RadioGroup primera_pregunta,segunda_pregunta,tercera_pregunta;
    private RadioButton primera_opcion_p1,segunda_opcion_p1,tercera_opcion_p1,primera_opcion_p2,segunda_opcion_p2,tercera_opcion_p2,primera_opcion_p3,segunda_opcion_p3,tercera_opcion_p3;
    private Dialog popupModuloCulminado;
    private Button botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_cuestionario);

        //Inicialización de la librería de fuentes de texto
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Overlock-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        modulo = getIntent().getExtras().getParcelable("Modulo");

        pregunta1 = (TextView) findViewById(R.id.pregunta_1);
        pregunta2 = (TextView) findViewById(R.id.pregunta_2);
        pregunta3 = (TextView) findViewById(R.id.pregunta_3);

        primera_pregunta = (RadioGroup)findViewById(R.id.primera_pregunta);
        segunda_pregunta = (RadioGroup)findViewById(R.id.segunda_pregunta);
        tercera_pregunta = (RadioGroup)findViewById(R.id.tercera_pregunta);

        primera_opcion_p1 = (RadioButton)findViewById(R.id.primera_opcion_p1);
        segunda_opcion_p1 = (RadioButton)findViewById(R.id.segunda_opcion_p1);
        tercera_opcion_p1 = (RadioButton)findViewById(R.id.tercera_opcion_p1);
        primera_opcion_p2 = (RadioButton)findViewById(R.id.primera_opcion_p2);
        segunda_opcion_p2 = (RadioButton)findViewById(R.id.segunda_opcion_p2);
        tercera_opcion_p2 = (RadioButton)findViewById(R.id.tercera_opcion_p2);
        primera_opcion_p3 = (RadioButton)findViewById(R.id.primera_opcion_p3);
        segunda_opcion_p3 = (RadioButton)findViewById(R.id.segunda_opcion_p3);
        tercera_opcion_p3 = (RadioButton)findViewById(R.id.tercera_opcion_p3);

        pregunta1.setText(modulo.getPregunta1().getPregunta());
        pregunta2.setText(modulo.getPregunta2().getPregunta());
        pregunta3.setText(modulo.getPregunta3().getPregunta());

        primera_opcion_p1.setText(modulo.getPregunta1().getAlternativa1().getNombre());
        segunda_opcion_p1.setText(modulo.getPregunta1().getAlternativa2().getNombre());
        tercera_opcion_p1.setText(modulo.getPregunta1().getAlternativa3().getNombre());
        primera_opcion_p2.setText(modulo.getPregunta2().getAlternativa1().getNombre());
        segunda_opcion_p2.setText(modulo.getPregunta2().getAlternativa2().getNombre());
        tercera_opcion_p2.setText(modulo.getPregunta2().getAlternativa3().getNombre());
        primera_opcion_p3.setText(modulo.getPregunta3().getAlternativa1().getNombre());
        segunda_opcion_p3.setText(modulo.getPregunta3().getAlternativa2().getNombre());
        tercera_opcion_p3.setText(modulo.getPregunta3().getAlternativa3().getNombre());

        //Inicialización del PopUp
        popupModuloCulminado = new Dialog(this);
        popupModuloCulminado.setCanceledOnTouchOutside(false);

    }

    public void moduloTerminado(View view) {
        if (primera_pregunta.getCheckedRadioButtonId() == -1 || segunda_pregunta.getCheckedRadioButtonId() == -1 || tercera_pregunta.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(this,"Por favor marque todos las alternativas, no se preocupe, no perderá puntos :) ",Toast.LENGTH_LONG).show();
        }
        else
        {
            int puntos_acumulados=0;
            //Evaluación
            switch(primera_pregunta.getCheckedRadioButtonId()) {
                case R.id.primera_opcion_p1:
                    if (modulo.getPregunta1().getAlternativa1().getEstado())
                        puntos_acumulados+=modulo.getPregunta1().getPuntos();
                        break;
                case R.id.segunda_opcion_p1:
                    if (modulo.getPregunta1().getAlternativa2().getEstado())
                        puntos_acumulados+=modulo.getPregunta1().getPuntos();
                        break;
                case R.id.tercera_opcion_p1:
                    if (modulo.getPregunta1().getAlternativa3().getEstado())
                        puntos_acumulados+=modulo.getPregunta1().getPuntos();
                        break;
            }

            switch(segunda_pregunta.getCheckedRadioButtonId()) {
                case R.id.primera_opcion_p2:
                    if (modulo.getPregunta2().getAlternativa1().getEstado())
                        puntos_acumulados+=modulo.getPregunta2().getPuntos();
                    break;
                case R.id.segunda_opcion_p2:
                    if (modulo.getPregunta2().getAlternativa2().getEstado())
                        puntos_acumulados+=modulo.getPregunta2().getPuntos();
                    break;
                case R.id.tercera_opcion_p2:
                    if (modulo.getPregunta2().getAlternativa3().getEstado())
                        puntos_acumulados+=modulo.getPregunta2().getPuntos();
                    break;
            }

            switch(tercera_pregunta.getCheckedRadioButtonId()) {
                case R.id.primera_opcion_p3:
                    if (modulo.getPregunta3().getAlternativa1().getEstado())
                        puntos_acumulados+=modulo.getPregunta3().getPuntos();
                    break;
                case R.id.segunda_opcion_p3:
                    if (modulo.getPregunta3().getAlternativa2().getEstado())
                        puntos_acumulados+=modulo.getPregunta3().getPuntos();
                    break;
                case R.id.tercera_opcion_p3:
                    if (modulo.getPregunta3().getAlternativa3().getEstado())
                        puntos_acumulados+=modulo.getPregunta3().getPuntos();
                    break;
            }
            //Toast.makeText(this,"Sacaste un total de: " + puntos_acumulados,Toast.LENGTH_LONG).show();
            MostrarPopUpDescubierto(puntos_acumulados);
        }
    }

    public void MostrarPopUpDescubierto(int cantidad){
        popupModuloCulminado.setContentView(R.layout.modulo_culminado_popup);
        botonAceptar = (Button)popupModuloCulminado.findViewById(R.id.botonAceptar);
        ganaste_cantidad = (TextView)popupModuloCulminado.findViewById(R.id.ganaste_cantidad);

        ganaste_cantidad.setText("¡Felicidades!, ganaste: "+String.valueOf(cantidad));
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupModuloCulminado.dismiss();
                finish();
            }
        });
        popupModuloCulminado.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupModuloCulminado.show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
