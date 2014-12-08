package com.izv.basededatos;

/**
 * Created by Ivan on 07/12/2014.
 */
public class JoinJugadoPartido {


    private String nombre;
    private String contrincante;
    private String valoracion;


    public JoinJugadoPartido() {
    }

    public JoinJugadoPartido(String nombre, String contrincante, String valoracion) {
        this.nombre = nombre;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
