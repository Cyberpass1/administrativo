/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class JCEspecialidades {
    
    int idespecialidad;
    String especialidad;
    String estado;
    
    
    
    
    int idAntcP;
    String antcP;
    String estadoantcP;
    
    int idAntcF;
    String antcF;
    String estadoantcF;
    
    int idDianostico;
    String Diagnostico;
    String estadoDiagnostico;
    
    
    public JCEspecialidades(){}

    public JCEspecialidades(int idespecialidad, String especialidad, String estado, int idAntcP, String antcP, String estadoantcP, int idAntcF, String antcF, String estadoantcF, int idDianostico, String Diagnostico, String estadoDiagnostico) {
        this.idespecialidad = idespecialidad;
        this.especialidad = especialidad;
        this.estado = estado;
        this.idAntcP = idAntcP;
        this.antcP = antcP;
        this.estadoantcP = estadoantcP;
        this.idAntcF = idAntcF;
        this.antcF = antcF;
        this.estadoantcF = estadoantcF;
        this.idDianostico = idDianostico;
        this.Diagnostico = Diagnostico;
        this.estadoDiagnostico = estadoDiagnostico;
    }

    public int getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(int idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdAntcP() {
        return idAntcP;
    }

    public void setIdAntcP(int idAntcP) {
        this.idAntcP = idAntcP;
    }

    public String getAntcP() {
        return antcP;
    }

    public void setAntcP(String antcP) {
        this.antcP = antcP;
    }

    public String getEstadoantcP() {
        return estadoantcP;
    }

    public void setEstadoantcP(String estadoantcP) {
        this.estadoantcP = estadoantcP;
    }

    public int getIdAntcF() {
        return idAntcF;
    }

    public void setIdAntcF(int idAntcF) {
        this.idAntcF = idAntcF;
    }

    public String getAntcF() {
        return antcF;
    }

    public void setAntcF(String antcF) {
        this.antcF = antcF;
    }

    public String getEstadoantcF() {
        return estadoantcF;
    }

    public void setEstadoantcF(String estadoantcF) {
        this.estadoantcF = estadoantcF;
    }

    public int getIdDianostico() {
        return idDianostico;
    }

    public void setIdDianostico(int idDianostico) {
        this.idDianostico = idDianostico;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public String getEstadoDiagnostico() {
        return estadoDiagnostico;
    }

    public void setEstadoDiagnostico(String estadoDiagnostico) {
        this.estadoDiagnostico = estadoDiagnostico;
    }

   
    
    
}
