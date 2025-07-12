/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;




public class vGraficasHono {
    
    String servicio;
    int cantidad;
    double brutoTotal;
    double netoTotal;
    
    
    String especialidad;
    int res_cant;
    double res_subtotal;
    double res_deducciones;
    double res_totalneto;
    
    
        
   public vGraficasHono(){}

   
   public vGraficasHono(String servicio, int cantidad, double brutoTotal, double netoTotal, String especialidad, int res_cant, double res_subtotal, double res_deducciones, double res_totalneto) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.brutoTotal = brutoTotal;
        this.netoTotal = netoTotal;
        this.especialidad = especialidad;
        this.res_cant = res_cant;
        this.res_subtotal = res_subtotal;
        this.res_deducciones = res_deducciones;
        this.res_totalneto = res_totalneto;
    }

   
   
    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getBrutoTotal() {
        return brutoTotal;
    }

    public void setBrutoTotal(double brutoTotal) {
        this.brutoTotal = brutoTotal;
    }

    public double getNetoTotal() {
        return netoTotal;
    }

    public void setNetoTotal(double netoTotal) {
        this.netoTotal = netoTotal;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getRes_cant() {
        return res_cant;
    }

    public void setRes_cant(int res_cant) {
        this.res_cant = res_cant;
    }

    public double getRes_subtotal() {
        return res_subtotal;
    }

    public void setRes_subtotal(double res_subtotal) {
        this.res_subtotal = res_subtotal;
    }

    public double getRes_deducciones() {
        return res_deducciones;
    }

    public void setRes_deducciones(double res_deducciones) {
        this.res_deducciones = res_deducciones;
    }

    public double getRes_totalneto() {
        return res_totalneto;
    }

    public void setRes_totalneto(double res_totalneto) {
        this.res_totalneto = res_totalneto;
    }

    
   
   
}
