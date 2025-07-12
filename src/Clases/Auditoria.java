/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class Auditoria {
    
       int codigopdf;
       String Usuario;
       String Personal;
       String Fecha;
       String Accion;
       
       public Auditoria(){}

    public Auditoria(int codigopdf, String Usuario, String Personal, String Fecha, String Accion) {
        this.codigopdf = codigopdf;
        this.Usuario = Usuario;
        this.Personal = Personal;
        this.Fecha = Fecha;
        this.Accion = Accion;
    }

    public int getCodigopdf() {
        return codigopdf;
    }

    public void setCodigopdf(int codigopdf) {
        this.codigopdf = codigopdf;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String Personal) {
        this.Personal = Personal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }
              
    
    
    
    
}
