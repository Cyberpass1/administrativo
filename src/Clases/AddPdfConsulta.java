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
public class AddPdfConsulta {
    
    
    int IdConsulta;
    int IdPacientes;
    int IdUsuario;
    int IdEspecialidad;
    String nombredoctor;
    String FechaAsignar;
    byte[] archivo;
    String Estado;
   
    
    int IdHistoria;
    int Id_usuarioh;
    int Id_pacienteh;
    int Id_Fisico;
    String Fecha_Historia;
    int Id_Estadoh;     
    byte[] archivoHistoria;
    
    
    
    int idSimple;
    byte[] archivoSimple;
    int idPacienteS;
    int estadoSimple;
    String ocpdescrip;
    String ocpFecha;
    String tituloSimple;
    String ocp_doctorString;
    int ocp_doctorInt;
    
    
    
   public  AddPdfConsulta(){}

    public AddPdfConsulta(int IdConsulta, int IdPacientes, int IdUsuario, int IdEspecialidad, String nombredoctor, String FechaAsignar, byte[] archivo, String Estado, int IdHistoria, int Id_usuarioh, int Id_pacienteh, int Id_Fisico, String Fecha_Historia, int Id_Estadoh, byte[] archivoHistoria, int idSimple, byte[] archivoSimple, int idPacienteS, int estadoSimple, String ocpdescrip, String ocpFecha, String tituloSimple, String ocp_doctorString, int ocp_doctorInt) {
        this.IdConsulta = IdConsulta;
        this.IdPacientes = IdPacientes;
        this.IdUsuario = IdUsuario;
        this.IdEspecialidad = IdEspecialidad;
        this.nombredoctor = nombredoctor;
        this.FechaAsignar = FechaAsignar;
        this.archivo = archivo;
        this.Estado = Estado;
        this.IdHistoria = IdHistoria;
        this.Id_usuarioh = Id_usuarioh;
        this.Id_pacienteh = Id_pacienteh;
        this.Id_Fisico = Id_Fisico;
        this.Fecha_Historia = Fecha_Historia;
        this.Id_Estadoh = Id_Estadoh;
        this.archivoHistoria = archivoHistoria;
        this.idSimple = idSimple;
        this.archivoSimple = archivoSimple;
        this.idPacienteS = idPacienteS;
        this.estadoSimple = estadoSimple;
        this.ocpdescrip = ocpdescrip;
        this.ocpFecha = ocpFecha;
        this.tituloSimple = tituloSimple;
        this.ocp_doctorString = ocp_doctorString;
        this.ocp_doctorInt = ocp_doctorInt;
    }

    public int getIdConsulta() {
        return IdConsulta;
    }

    public void setIdConsulta(int IdConsulta) {
        this.IdConsulta = IdConsulta;
    }

    public int getIdPacientes() {
        return IdPacientes;
    }

    public void setIdPacientes(int IdPacientes) {
        this.IdPacientes = IdPacientes;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getIdEspecialidad() {
        return IdEspecialidad;
    }

    public void setIdEspecialidad(int IdEspecialidad) {
        this.IdEspecialidad = IdEspecialidad;
    }

    public String getNombredoctor() {
        return nombredoctor;
    }

    public void setNombredoctor(String nombredoctor) {
        this.nombredoctor = nombredoctor;
    }

    public String getFechaAsignar() {
        return FechaAsignar;
    }

    public void setFechaAsignar(String FechaAsignar) {
        this.FechaAsignar = FechaAsignar;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIdHistoria() {
        return IdHistoria;
    }

    public void setIdHistoria(int IdHistoria) {
        this.IdHistoria = IdHistoria;
    }

    public int getId_usuarioh() {
        return Id_usuarioh;
    }

    public void setId_usuarioh(int Id_usuarioh) {
        this.Id_usuarioh = Id_usuarioh;
    }

    public int getId_pacienteh() {
        return Id_pacienteh;
    }

    public void setId_pacienteh(int Id_pacienteh) {
        this.Id_pacienteh = Id_pacienteh;
    }

    public int getId_Fisico() {
        return Id_Fisico;
    }

    public void setId_Fisico(int Id_Fisico) {
        this.Id_Fisico = Id_Fisico;
    }

    public String getFecha_Historia() {
        return Fecha_Historia;
    }

    public void setFecha_Historia(String Fecha_Historia) {
        this.Fecha_Historia = Fecha_Historia;
    }

    public int getId_Estadoh() {
        return Id_Estadoh;
    }

    public void setId_Estadoh(int Id_Estadoh) {
        this.Id_Estadoh = Id_Estadoh;
    }

    public byte[] getArchivoHistoria() {
        return archivoHistoria;
    }

    public void setArchivoHistoria(byte[] archivoHistoria) {
        this.archivoHistoria = archivoHistoria;
    }

    public int getIdSimple() {
        return idSimple;
    }

    public void setIdSimple(int idSimple) {
        this.idSimple = idSimple;
    }

    public byte[] getArchivoSimple() {
        return archivoSimple;
    }

    public void setArchivoSimple(byte[] archivoSimple) {
        this.archivoSimple = archivoSimple;
    }

    public int getIdPacienteS() {
        return idPacienteS;
    }

    public void setIdPacienteS(int idPacienteS) {
        this.idPacienteS = idPacienteS;
    }

    public int getEstadoSimple() {
        return estadoSimple;
    }

    public void setEstadoSimple(int estadoSimple) {
        this.estadoSimple = estadoSimple;
    }

    public String getOcpdescrip() {
        return ocpdescrip;
    }

    public void setOcpdescrip(String ocpdescrip) {
        this.ocpdescrip = ocpdescrip;
    }

    public String getOcpFecha() {
        return ocpFecha;
    }

    public void setOcpFecha(String ocpFecha) {
        this.ocpFecha = ocpFecha;
    }

    public String getTituloSimple() {
        return tituloSimple;
    }

    public void setTituloSimple(String tituloSimple) {
        this.tituloSimple = tituloSimple;
    }

    public String getOcp_doctorString() {
        return ocp_doctorString;
    }

    public void setOcp_doctorString(String ocp_doctorString) {
        this.ocp_doctorString = ocp_doctorString;
    }

    public int getOcp_doctorInt() {
        return ocp_doctorInt;
    }

    public void setOcp_doctorInt(int ocp_doctorInt) {
        this.ocp_doctorInt = ocp_doctorInt;
    }

   
    
}
