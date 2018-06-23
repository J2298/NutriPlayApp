package com.job.nutriplayapp.models;

public class Contenido {

    private String id, imagen1, texto1, imagen2, texto2;

    public Contenido() {
    }

    public Contenido(String id, String imagen1, String texto1, String imagen2, String texto2) {
        this.id = id;
        this.imagen1 = imagen1;
        this.texto1 = texto1;
        this.imagen2 = imagen2;
        this.texto2 = texto2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getTexto1() {
        return texto1;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "id='" + id + '\'' +
                ", imagen1='" + imagen1 + '\'' +
                ", texto1='" + texto1 + '\'' +
                ", imagen2='" + imagen2 + '\'' +
                ", texto2='" + texto2 + '\'' +
                '}';
    }
}
