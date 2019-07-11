package modelo;

import java.util.ArrayList;

/**
 * Class Producto
 * @author Jose Ignacio Zamora
 */
public class Producto {
    
    private ArrayList<String> listaImagenes;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String categoria;
    private int precio;
    private int numeroConsecutivo;
    private int estadoTrueque;
    private boolean aceptadoPrimeraVez;
    private boolean rechazado;
    
    /**
     * Constructor
     * @param listaImagenes
     * @param descripcionCorta
     * @param descripcionDetallada
     * @param categoria
     * @param precio
     * @param numeroConsecutivo
     */
    public Producto(ArrayList<String> listaImagenes, String descripcionCorta, String descripcionDetallada, String categoria, int precio, int numeroConsecutivo) {
        this.listaImagenes = listaImagenes;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.categoria = categoria;
        this.precio = precio;
        this.numeroConsecutivo = numeroConsecutivo;
        this.estadoTrueque=0;
        aceptadoPrimeraVez = rechazado = false;
    }

    /**
     *Constructor
     */
    public Producto() {
        listaImagenes=new ArrayList<>(5);
        this.estadoTrueque=0;
    }

    /**
     *get
     * @return
     */
    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    /**
     *
     * @param listaImagenes
     */
    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    /**
     *
     * @return
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     *
     * @param descripcionCorta
     */
    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    /**
     *
     * @return
     */
    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    /**
     *
     * @param descripcionDetallada
     */
    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    /**
     *
     * @return
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public int getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    /**
     *
     * @param numeroConsecutivo
     */
    public void setNumeroConsecutivo(int numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

    /**
     *
     * @return
     */
    public int getEstadoTrueque() {
        return estadoTrueque;
    }

    /**
     *
     * @param estadoTrueque
     */
    public void setEstadoTrueque(int estadoTrueque) {
        this.estadoTrueque = estadoTrueque;
    }

    /**
     *
     * @return
     */
    public boolean isAceptadoPrimeraVez() {
        return aceptadoPrimeraVez;
    }

    /**
     *
     * @param aceptadoPrimeraVez
     */
    public void setAceptadoPrimeraVez(boolean aceptadoPrimeraVez) {
        this.aceptadoPrimeraVez = aceptadoPrimeraVez;
    }

    /**
     *
     * @return
     */
    public boolean isRechazado() {
        return rechazado;
    }

    /**
     *
     * @param rechazado
     */
    public void setRechazado(boolean rechazado) {
        this.rechazado = rechazado;
    }

    @Override
    public String toString() {
        return "Producto{" + "listaImagenes=" + listaImagenes + ", descripcionCorta=" + descripcionCorta + ", descripcionDetallada=" + descripcionDetallada + ", categoria=" + categoria + ", precio=" + precio + ", numeroConsecutivo=" + numeroConsecutivo + ", estadoTrueque=" + estadoTrueque + ", aceptadoPrimeraVez=" + aceptadoPrimeraVez + ", rechazado=" + rechazado + '}';
    }
}
