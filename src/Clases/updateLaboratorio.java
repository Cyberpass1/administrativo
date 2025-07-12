/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class updateLaboratorio {
    
    int idlabUpdate;
    byte[] archivoUpdate;
    
    
    public  updateLaboratorio (){}

    public updateLaboratorio(int idlabUpdate, byte[] archivoUpdate) {
        this.idlabUpdate = idlabUpdate;
        this.archivoUpdate = archivoUpdate;
    }

    public int getIdlabUpdate() {
        return idlabUpdate;
    }

    public void setIdlabUpdate(int idlabUpdate) {
        this.idlabUpdate = idlabUpdate;
    }

    public byte[] getArchivoUpdate() {
        return archivoUpdate;
    }

    public void setArchivoUpdate(byte[] archivoUpdate) {
        this.archivoUpdate = archivoUpdate;
    }
    
    
    
}
