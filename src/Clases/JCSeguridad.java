/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class JCSeguridad {
    
    int idUser;  
    String usuario;
    String clave;
    
    String pregunta1;
    String pregunta2;
    String pregunta3;
    
    String respuesta1;
    String respuesta2;
    String respuesta3;
      
    
     public JCSeguridad() {}

    public JCSeguridad(int idUser, String usuario, String clave, String pregunta1, String pregunta2, String pregunta3, String respuesta1, String respuesta2, String respuesta3) {
        this.idUser = idUser;
        this.usuario = usuario;
        this.clave = clave;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

   
}
