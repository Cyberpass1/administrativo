/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Admin
 */
public class vGraficaDc {
    
    String especialita;
    double Dec_flat;
    double Dec_ISRL;
    double Dec_personal;
    double Dec_almacen;
    double Dec_nomina;        
    double Dec_incineradora;        
    double Dec_anticipo;       
    double Dec_Bioseguridad;
    double Fundacion;        
    double Dec_otras;

            
            
       public vGraficaDc(){}

    public vGraficaDc(String especialita, double Dec_flat, double Dec_ISRL, double Dec_personal, double Dec_almacen, double Dec_nomina, double Dec_incineradora, double Dec_anticipo, double Dec_Bioseguridad, double Fundacion, double Dec_otras) {
        this.especialita = especialita;
        this.Dec_flat = Dec_flat;
        this.Dec_ISRL = Dec_ISRL;
        this.Dec_personal = Dec_personal;
        this.Dec_almacen = Dec_almacen;
        this.Dec_nomina = Dec_nomina;
        this.Dec_incineradora = Dec_incineradora;
        this.Dec_anticipo = Dec_anticipo;
        this.Dec_Bioseguridad = Dec_Bioseguridad;
        this.Fundacion = Fundacion;
        this.Dec_otras = Dec_otras;
    }

    public String getEspecialita() {
        return especialita;
    }

    public void setEspecialita(String especialita) {
        this.especialita = especialita;
    }

    public double getDec_flat() {
        return Dec_flat;
    }

    public void setDec_flat(double Dec_flat) {
        this.Dec_flat = Dec_flat;
    }

    public double getDec_ISRL() {
        return Dec_ISRL;
    }

    public void setDec_ISRL(double Dec_ISRL) {
        this.Dec_ISRL = Dec_ISRL;
    }

    public double getDec_personal() {
        return Dec_personal;
    }

    public void setDec_personal(double Dec_personal) {
        this.Dec_personal = Dec_personal;
    }

    public double getDec_almacen() {
        return Dec_almacen;
    }

    public void setDec_almacen(double Dec_almacen) {
        this.Dec_almacen = Dec_almacen;
    }

    public double getDec_nomina() {
        return Dec_nomina;
    }

    public void setDec_nomina(double Dec_nomina) {
        this.Dec_nomina = Dec_nomina;
    }

    public double getDec_incineradora() {
        return Dec_incineradora;
    }

    public void setDec_incineradora(double Dec_incineradora) {
        this.Dec_incineradora = Dec_incineradora;
    }

    public double getDec_anticipo() {
        return Dec_anticipo;
    }

    public void setDec_anticipo(double Dec_anticipo) {
        this.Dec_anticipo = Dec_anticipo;
    }

    public double getDec_Bioseguridad() {
        return Dec_Bioseguridad;
    }

    public void setDec_Bioseguridad(double Dec_Bioseguridad) {
        this.Dec_Bioseguridad = Dec_Bioseguridad;
    }

    public double getFundacion() {
        return Fundacion;
    }

    public void setFundacion(double Fundacion) {
        this.Fundacion = Fundacion;
    }

    public double getDec_otras() {
        return Dec_otras;
    }

    public void setDec_otras(double Dec_otras) {
        this.Dec_otras = Dec_otras;
    }

   
  
       
    
}
