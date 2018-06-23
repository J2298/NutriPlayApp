package com.job.nutriplayapp.models;

public class Alternativa {

    private String id, nombre;
    private Boolean estado;


    public Alternativa() {
    }

    public Alternativa(String id, Boolean estado, String nombre) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Alternativa{" +
                "id='" + id + '\'' +
                ", estado='" + estado + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
