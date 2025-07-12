/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Jpnew
 */
public class reporteFact {
    
    
    String idFact;
    String cliente;
    String producto;
    String categoria;
    int cantidad;
    String moneda;
    double precio_unit;
    double precio_total;
    double precio_unitBs;
    double precio_totalBs;
    String fecha;
    String state;
    
    

    
    
   public  reporteFact(){}    

    public reporteFact(String idFact, String cliente, String producto, String categoria, int cantidad, String moneda, double precio_unit, double precio_total, double precio_unitBs, double precio_totalBs, String fecha, String state) {
        this.idFact = idFact;
        this.cliente = cliente;
        this.producto = producto;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.moneda = moneda;
        this.precio_unit = precio_unit;
        this.precio_total = precio_total;
        this.precio_unitBs = precio_unitBs;
        this.precio_totalBs = precio_totalBs;
        this.fecha = fecha;
        this.state = state;
    }

    public String getIdFact() {
        return idFact;
    }

    public void setIdFact(String idFact) {
        this.idFact = idFact;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public double getPrecio_unitBs() {
        return precio_unitBs;
    }

    public void setPrecio_unitBs(double precio_unitBs) {
        this.precio_unitBs = precio_unitBs;
    }

    public double getPrecio_totalBs() {
        return precio_totalBs;
    }

    public void setPrecio_totalBs(double precio_totalBs) {
        this.precio_totalBs = precio_totalBs;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

   
    
    
    
}
