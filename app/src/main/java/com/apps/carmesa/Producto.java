package com.apps.carmesa;

public class Producto {
    private String titulo;
    private String descripcion;
    private int id_Imagen;
    private String valor;
    private boolean selected;

    public Producto (String titulo, String descripcion, int id_Imagen, String valor){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.id_Imagen = id_Imagen;
        this.valor= valor;
        this.selected = false;
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

    public int getId_Imagen() {
        return id_Imagen;
    }

    public void setId_Imagen(int id_Imagen) {
        this.id_Imagen = id_Imagen;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
