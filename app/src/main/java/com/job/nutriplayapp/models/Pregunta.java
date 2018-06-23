package com.job.nutriplayapp.models;

import java.util.ArrayList;

public class Pregunta {

    private String id, pregunta;
    private Alternativa alternativa1, alternativa2, alternativa3;

    public Pregunta() {
    }

    public Pregunta(String id, String pregunta, Alternativa alternativa1, Alternativa alternativa2, Alternativa alternativa3) {
        this.id = id;
        this.pregunta = pregunta;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.alternativa3 = alternativa3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Alternativa getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(Alternativa alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public Alternativa getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(Alternativa alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public Alternativa getAlternativa3() {
        return alternativa3;
    }

    public void setAlternativa3(Alternativa alternativa3) {
        this.alternativa3 = alternativa3;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id='" + id + '\'' +
                ", pregunta='" + pregunta + '\'' +
                ", alternativa1=" + alternativa1 +
                ", alternativa2=" + alternativa2 +
                ", alternativa3=" + alternativa3 +
                '}';
    }
}