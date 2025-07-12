/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;






public class JCAuditoria {
    
    int idAudit;
    String idUser;
    String idPersonal;
    String Accion;
    String Fecha;

   public JCAuditoria() {
    
    }

    public JCAuditoria(int idAudit, String idUser, String idPersonal, String Accion, String Fecha) {
        this.idAudit = idAudit;
        this.idUser = idUser;
        this.idPersonal = idPersonal;
        this.Accion = Accion;
        this.Fecha = Fecha;
    }

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String Accion) {
        this.Accion = Accion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
  

 
    
    
    
}
