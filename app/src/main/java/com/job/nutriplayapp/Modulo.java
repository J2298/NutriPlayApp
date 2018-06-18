package com.job.nutriplayapp;

public class Modulo {

    private String id;
    private String titulo;
    private String descripcion;
    private String picture;

    public Modulo(){}

    public Modulo(String id, String titulo, String descripcion, String picture) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.picture = picture;
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

    @Override
    public String toString() {
        return "Modulo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
