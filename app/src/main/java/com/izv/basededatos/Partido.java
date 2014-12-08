package com.izv.basededatos;

import java.io.Serializable;

/**
 * Created by Ivan on 06/12/2014.
 */
public class Partido implements Serializable, Comparable<Partido> {

    private long id;
    private long idjugador;
    private String contrincante;
    private String valoracion;

    public Partido() {
    }

    public Partido(long idjugador, String contrincante, String valoracion) {
        this.idjugador = idjugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public Partido(long id, long idjugador, String contrincante, String valoracion) {
        this.id = id;
        this.idjugador = idjugador;
        this.contrincante = contrincante;
        this.valoracion = valoracion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(long idjugador) {
        this.idjugador = idjugador;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partido partido = (Partido) o;

        if (id != partido.id) return false;

        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Partido partido) {
        if(this.contrincante.compareTo(partido.contrincante)!=0){
            return this.contrincante.compareTo(partido.contrincante);
        }else{
            return this.valoracion.compareTo(partido.valoracion);
        }
    }
}
