/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author FCGI-ADMIN
 */
public class JCMostrarAsignados {
    
    int IdAsginado;
    String PacAsignado;
    String CedAsignado;
    String Estado;
    
    public  JCMostrarAsignados(){}

    public JCMostrarAsignados(int IdAsginado, String PacAsignado, String CedAsignado, String Estado) {
        this.IdAsginado = IdAsginado;
        this.PacAsignado = PacAsignado;
        this.CedAsignado = CedAsignado;
        this.Estado = Estado;
    }

    public int getIdAsginado() {
        return IdAsginado;
    }

    public void setIdAsginado(int IdAsginado) {
        this.IdAsginado = IdAsginado;
    }

    public String getPacAsignado() {
        return PacAsignado;
    }

    public void setPacAsignado(String PacAsignado) {
        this.PacAsignado = PacAsignado;
    }

    public String getCedAsignado() {
        return CedAsignado;
    }

    public void setCedAsignado(String CedAsignado) {
        this.CedAsignado = CedAsignado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
     
     
     
    
}
