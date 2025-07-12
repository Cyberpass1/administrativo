/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Jpnew
 */
public class factGlobal {
    
        
    
  String idFact;
  String cliente;
  String producto;
  String moneda;
  boolean pntoVenta;
  boolean pagomovil;
  boolean efectivo;
  boolean transferencia; 
  boolean zelle; 
  boolean otro;
  double TotalD;
  double TotalB;
  double Pago_Bsf;
  double Pago_Divisa;
  String observacion; 
  String fecha;
  String state;
    
  
    public factGlobal(){}

    public factGlobal(String idFact, String cliente, String producto, String moneda, boolean pntoVenta, boolean pagomovil, boolean efectivo, boolean transferencia, boolean zelle, boolean otro, double TotalD, double TotalB, double Pago_Bsf, double Pago_Divisa, String observacion, String fecha, String state) {
        this.idFact = idFact;
        this.cliente = cliente;
        this.producto = producto;
        this.moneda = moneda;
        this.pntoVenta = pntoVenta;
        this.pagomovil = pagomovil;
        this.efectivo = efectivo;
        this.transferencia = transferencia;
        this.zelle = zelle;
        this.otro = otro;
        this.TotalD = TotalD;
        this.TotalB = TotalB;
        this.Pago_Bsf = Pago_Bsf;
        this.Pago_Divisa = Pago_Divisa;
        this.observacion = observacion;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public boolean isPntoVenta() {
        return pntoVenta;
    }

    public void setPntoVenta(boolean pntoVenta) {
        this.pntoVenta = pntoVenta;
    }

    public boolean isPagomovil() {
        return pagomovil;
    }

    public void setPagomovil(boolean pagomovil) {
        this.pagomovil = pagomovil;
    }

    public boolean isEfectivo() {
        return efectivo;
    }

    public void setEfectivo(boolean efectivo) {
        this.efectivo = efectivo;
    }

    public boolean isTransferencia() {
        return transferencia;
    }

    public void setTransferencia(boolean transferencia) {
        this.transferencia = transferencia;
    }

    public boolean isZelle() {
        return zelle;
    }

    public void setZelle(boolean zelle) {
        this.zelle = zelle;
    }

    public boolean isOtro() {
        return otro;
    }

    public void setOtro(boolean otro) {
        this.otro = otro;
    }

    public double getTotalD() {
        return TotalD;
    }

    public void setTotalD(double TotalD) {
        this.TotalD = TotalD;
    }

    public double getTotalB() {
        return TotalB;
    }

    public void setTotalB(double TotalB) {
        this.TotalB = TotalB;
    }

    public double getPago_Bsf() {
        return Pago_Bsf;
    }

    public void setPago_Bsf(double Pago_Bsf) {
        this.Pago_Bsf = Pago_Bsf;
    }

    public double getPago_Divisa() {
        return Pago_Divisa;
    }

    public void setPago_Divisa(double Pago_Divisa) {
        this.Pago_Divisa = Pago_Divisa;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
