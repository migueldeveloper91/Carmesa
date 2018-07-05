package com.apps.carmesa;

public class datos {
    private int id;
    private String titulo;
    private String valor;

    public datos(int id, String titulo, String valor) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }




}
