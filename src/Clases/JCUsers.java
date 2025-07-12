/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author FCGinebraI
 */
public class JCUsers {
    
    int idpacientes;
    String nombre;
    String apellido;
    String cedula;
    String telefono;
    String correo;
    String direccion;
    String sexo;
    String fechaN;
    
    
    
    int idonline;
    int online;
    String usuario;
    String nivel;
    String fechaEntrada;
    String horaEntrada;
    String ultentrada;
    String fechaOnline;
    String especialidadUser;
    
    
    public JCUsers() {}

    public JCUsers(int idpacientes, String nombre, String apellido, String cedula, String telefono, String correo, String direccion, String sexo, String fechaN, int idonline, int online, String usuario, String nivel, String fechaEntrada, String horaEntrada, String ultentrada, String fechaOnline, String especialidadUser) {
        this.idpacientes = idpacientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fechaN = fechaN;
        this.idonline = idonline;
        this.online = online;
        this.usuario = usuario;
        this.nivel = nivel;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.ultentrada = ultentrada;
        this.fechaOnline = fechaOnline;
        this.especialidadUser = especialidadUser;
    }

    public int getIdpacientes() {
        return idpacientes;
    }

    public void setIdpacientes(int idpacientes) {
        this.idpacientes = idpacientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public int getIdonline() {
        return idonline;
    }

    public void setIdonline(int idonline) {
        this.idonline = idonline;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getUltentrada() {
        return ultentrada;
    }

    public void setUltentrada(String ultentrada) {
        this.ultentrada = ultentrada;
    }

    public String getFechaOnline() {
        return fechaOnline;
    }

    public void setFechaOnline(String fechaOnline) {
        this.fechaOnline = fechaOnline;
    }

    public String getEspecialidadUser() {
        return especialidadUser;
    }

    public void setEspecialidadUser(String especialidadUser) {
        this.especialidadUser = especialidadUser;
    }

   
    
    
    
}
