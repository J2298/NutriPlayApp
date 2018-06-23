package com.job.nutriplayapp.models;

import java.util.ArrayList;

public class Pregunta {

    private String id, pregunta;
    private ArrayList<Alternativa> alternativa;

    public Pregunta() {
    }

    public Pregunta(String id, String pregunta, ArrayList<Alternativa> alternativa) {
        this.id = id;
        this.pregunta = pregunta;
        this.alternativa = alternativa;
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

    public ArrayList<Alternativa> getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(ArrayList<Alternativa> alternativa) {
        this.alternativa = alternativa;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id='" + id + '\'' +
                ", pregunta='" + pregunta + '\'' +
                ", alternativa=" + alternativa +
                '}';
    }
}