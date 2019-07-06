/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Nacho
 */
public class Producto {
    
    private ArrayList<String> listaImagenes;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String categoria;
    private int precio;
    private int numeroConsecutivo;
    private int estadoTrueque;
    
    public Producto(ArrayList<String> listaImagenes, String descripcionCorta, String descripcionDetallada, String categoria, int precio, int numeroConsecutivo) {
        this.listaImagenes = listaImagenes;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.categoria = categoria;
        this.precio = precio;
        this.numeroConsecutivo = numeroConsecutivo;
        this.estadoTrueque=0;
    }

    
    
    public Producto() {
        listaImagenes=new ArrayList<>(5);
        this.estadoTrueque=0;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    public void setNumeroConsecutivo(int numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

    public int getEstadoTrueque() {
        return estadoTrueque;
    }

    public void setEstadoTrueque(int estadoTrueque) {
        this.estadoTrueque = estadoTrueque;
    }

    @Override
    public String toString() {
        return "Producto{" + "listaImagenes=" + listaImagenes + ", descripcionCorta=" + descripcionCorta + ", descripcionDetallada=" + descripcionDetallada + ", categoria=" + categoria + ", precio=" + precio + ", numeroConsecutivo=" + numeroConsecutivo + ", estadoTrueque=" + estadoTrueque + '}';
    }
}
