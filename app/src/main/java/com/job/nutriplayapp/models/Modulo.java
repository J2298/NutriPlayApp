package com.job.nutriplayapp.models;

import java.util.ArrayList;

public class Modulo {

    private String id, titulo, descripcion, picture;
    private ArrayList<Contenido> contenido;
    private ArrayList<Pregunta> pregunta;

    public Modulo(){}

    public Modulo(String id, String titulo, String descripcion, String picture, ArrayList<Contenido> contenido, ArrayList<Pregunta> pregunta) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.picture = picture;
        this.contenido = contenido;
        this.pregunta = pregunta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<Contenido> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<Contenido> contenido) {
        this.contenido = contenido;
    }

    public ArrayList<Pregunta> getPregunta() {
        return pregunta;
    }

    public void setPregunta(ArrayList<Pregunta> pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", picture='" + picture + '\'' +
                ", contenido=" + contenido +
                ", preguntas=" + pregunta +
                '}';
    }
}
