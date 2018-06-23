package com.job.nutriplayapp.models;

public class Contenido {

    private String id, imagen, texto;

    public Contenido() {
    }

    public Contenido(String id, String imagen, String texto) {
        this.id = id;
        this.imagen = imagen;
        this.texto = texto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "id='" + id + '\'' +
                ", imagen='" + imagen + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
