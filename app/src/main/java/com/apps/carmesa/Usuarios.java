package com.apps.carmesa;

public class Usuarios {
    private String usuario;
    private String pass;
    private Integer documento;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer telFijo;
    private Integer telMovil;

    public Usuarios() {
        this.usuario = usuario;
        this.pass = pass;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telFijo = telFijo;
        this.telMovil = telMovil;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(Integer telFijo) {
        this.telFijo = telFijo;
    }

    public Integer getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(Integer telMovil) {
        this.telMovil = telMovil;
    }




}
