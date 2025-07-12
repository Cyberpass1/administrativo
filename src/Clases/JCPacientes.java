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
public class JCPacientes {
    
    int idpacientes;
    String nombre;
    String apellido;
    String cedula;
    String telefono;
    String correo;
    String direccion;
    String sexo;
    String fechaN;
    
    int idOrden;
    int orden;
    int idpacienteOrden;
    String pacienteOrden;
    String fechaOrden;
    String cedulaorden;
    String factura;
    
    
    
    public JCPacientes() {}

    public JCPacientes(int idpacientes, String nombre, String apellido, String cedula, String telefono, String correo, String direccion, String sexo, String fechaN, int idOrden, int orden, int idpacienteOrden, String pacienteOrden, String fechaOrden, String cedulaorden, String factura) {
        this.idpacientes = idpacientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fechaN = fechaN;
        this.idOrden = idOrden;
        this.orden = orden;
        this.idpacienteOrden = idpacienteOrden;
        this.pacienteOrden = pacienteOrden;
        this.fechaOrden = fechaOrden;
        this.cedulaorden = cedulaorden;
        this.factura = factura;
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

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getIdpacienteOrden() {
        return idpacienteOrden;
    }

    public void setIdpacienteOrden(int idpacienteOrden) {
        this.idpacienteOrden = idpacienteOrden;
    }

    public String getPacienteOrden() {
        return pacienteOrden;
    }

    public void setPacienteOrden(String pacienteOrden) {
        this.pacienteOrden = pacienteOrden;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getCedulaorden() {
        return cedulaorden;
    }

    public void setCedulaorden(String cedulaorden) {
        this.cedulaorden = cedulaorden;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

   
  
    
}
