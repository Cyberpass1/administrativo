/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class labEstudiosBd {
    
    int idhistoria;
    String igm;
    String igg;
    
   
     public labEstudiosBd(){
     
     }

    public labEstudiosBd(int idhistoria, String igm, String igg) {
        this.idhistoria = idhistoria;
        this.igm = igm;
        this.igg = igg;
    }

    public int getIdhistoria() {
        return idhistoria;
    }

    public void setIdhistoria(int idhistoria) {
        this.idhistoria = idhistoria;
    }

    public String getIgm() {
        return igm;
    }

    public void setIgm(String igm) {
        this.igm = igm;
    }

    public String getIgg() {
        return igg;
    }

    public void setIgg(String igg) {
        this.igg = igg;
    }
     
     
    
}
