
package Clases;


public class JCProcedimientos {
    
    
    int idprocedimiento;
    String Procedimiento;
    String Categoria;
    String observacion;
    String Estado; 
   
    
    
    
    int idhorario;
    String especialidad;
    String doctor;
    String entrada;
    String salida;
    String estadoHora;
    String observacionHora;
    String diasHorario;
    String am_pm;
    
    int idDiferencia;
    String entradaReal;
    String entradaDif;
    String salidaReal;
    String salidaDif;
    String motivoReal;
    String observacionReal;
    String fechaReal;
    
    
    
    
    
    
    public JCProcedimientos(){}

    public JCProcedimientos(int idprocedimiento, String Procedimiento, String Categoria, String observacion, String Estado, int idhorario, String especialidad, String doctor, String entrada, String salida, String estadoHora, String observacionHora, String diasHorario, String am_pm, int idDiferencia, String entradaReal, String entradaDif, String salidaReal, String salidaDif, String motivoReal, String observacionReal, String fechaReal) {
        this.idprocedimiento = idprocedimiento;
        this.Procedimiento = Procedimiento;
        this.Categoria = Categoria;
        this.observacion = observacion;
        this.Estado = Estado;
        this.idhorario = idhorario;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.entrada = entrada;
        this.salida = salida;
        this.estadoHora = estadoHora;
        this.observacionHora = observacionHora;
        this.diasHorario = diasHorario;
        this.am_pm = am_pm;
        this.idDiferencia = idDiferencia;
        this.entradaReal = entradaReal;
        this.entradaDif = entradaDif;
        this.salidaReal = salidaReal;
        this.salidaDif = salidaDif;
        this.motivoReal = motivoReal;
        this.observacionReal = observacionReal;
        this.fechaReal = fechaReal;
    }

    public int getIdprocedimiento() {
        return idprocedimiento;
    }

    public void setIdprocedimiento(int idprocedimiento) {
        this.idprocedimiento = idprocedimiento;
    }

    public String getProcedimiento() {
        return Procedimiento;
    }

    public void setProcedimiento(String Procedimiento) {
        this.Procedimiento = Procedimiento;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getEstadoHora() {
        return estadoHora;
    }

    public void setEstadoHora(String estadoHora) {
        this.estadoHora = estadoHora;
    }

    public String getObservacionHora() {
        return observacionHora;
    }

    public void setObservacionHora(String observacionHora) {
        this.observacionHora = observacionHora;
    }

    public String getDiasHorario() {
        return diasHorario;
    }

    public void setDiasHorario(String diasHorario) {
        this.diasHorario = diasHorario;
    }

    public String getAm_pm() {
        return am_pm;
    }

    public void setAm_pm(String am_pm) {
        this.am_pm = am_pm;
    }

    public int getIdDiferencia() {
        return idDiferencia;
    }

    public void setIdDiferencia(int idDiferencia) {
        this.idDiferencia = idDiferencia;
    }

    public String getEntradaReal() {
        return entradaReal;
    }

    public void setEntradaReal(String entradaReal) {
        this.entradaReal = entradaReal;
    }

    public String getEntradaDif() {
        return entradaDif;
    }

    public void setEntradaDif(String entradaDif) {
        this.entradaDif = entradaDif;
    }

    public String getSalidaReal() {
        return salidaReal;
    }

    public void setSalidaReal(String salidaReal) {
        this.salidaReal = salidaReal;
    }

    public String getSalidaDif() {
        return salidaDif;
    }

    public void setSalidaDif(String salidaDif) {
        this.salidaDif = salidaDif;
    }

    public String getMotivoReal() {
        return motivoReal;
    }

    public void setMotivoReal(String motivoReal) {
        this.motivoReal = motivoReal;
    }

    public String getObservacionReal() {
        return observacionReal;
    }

    public void setObservacionReal(String observacionReal) {
        this.observacionReal = observacionReal;
    }

    public String getFechaReal() {
        return fechaReal;
    }

    public void setFechaReal(String fechaReal) {
        this.fechaReal = fechaReal;
    }

   
}
