/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;






public class JCAsignar {
    
    int idAsignar;
    int idPaciente;
    String nombre;
    String apellido;
    String cedula;
    String correo;
    String sexo;
    String edad;
    String paciente;
    String motivo;
    String Fecha;
    String Especialidad;
    String estado;
    String Doctor;
    String Observacion;
    
    public JCAsignar () {}

    public JCAsignar(int idAsignar, int idPaciente, String nombre, String apellido, String cedula, String correo, String sexo, String edad, String paciente, String motivo, String Fecha, String Especialidad, String estado, String Doctor, String Observacion) {
        this.idAsignar = idAsignar;
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.sexo = sexo;
        this.edad = edad;
        this.paciente = paciente;
        this.motivo = motivo;
        this.Fecha = Fecha;
        this.Especialidad = Especialidad;
        this.estado = estado;
        this.Doctor = Doctor;
        this.Observacion = Observacion;
    }

    public int getIdAsignar() {
        return idAsignar;
    }

    public void setIdAsignar(int idAsignar) {
        this.idAsignar = idAsignar;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String Doctor) {
        this.Doctor = Doctor;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

  
    
    
    
}
