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
public class JCAntecedentesFamiliares {
    
    int idAntecentes1;
    int idAntecentes2;
    int idAntecentes3;
    String Enfermedad1;
    String Enfermedad2;
    String medicamento;
    String Estado1;
    String Estado2;
    String Estado3;
    
    
    
    int CallAtcd1;
    int CallAtcd2;
    String CallE1;
    String CallE2;
    String CallD1;
    String CallD2;
    int idHistoria;
    
    
    
    int showAntcid1;
    int showAntcidh1;
    String showNameantc1;
    String showDescription1;
    
    int showAntcid2;
    int showAntcidh2;
    String showNameantc2;
    String showDescription2;
    
    
    int showEadc;
    int showEadcH;
    String showNameAdc;
    String showDescriptionAdc;
    
    
    
    
    int idlaboral;
    int historialaboral;
    String empresalaboral;
    String cargo;
    String actividad;
    String tiempo;
    String riesgos;
    
    
    int idOdontologia;
    int idHistoriaOdontologica;
    String Dientes;
    String informeDiente;
    String dolorDientes;
    String signosDientes;
    String radiologiaDientes;
    byte[] odontograma;
    
    JCAntecedentesFamiliares(){}

    public JCAntecedentesFamiliares(int idAntecentes1, int idAntecentes2, int idAntecentes3, String Enfermedad1, String Enfermedad2, String medicamento, String Estado1, String Estado2, String Estado3, int CallAtcd1, int CallAtcd2, String CallE1, String CallE2, String CallD1, String CallD2, int idHistoria, int showAntcid1, int showAntcidh1, String showNameantc1, String showDescription1, int showAntcid2, int showAntcidh2, String showNameantc2, String showDescription2, int showEadc, int showEadcH, String showNameAdc, String showDescriptionAdc, int idlaboral, int historialaboral, String empresalaboral, String cargo, String actividad, String tiempo, String riesgos, int idOdontologia, int idHistoriaOdontologica, String Dientes, String informeDiente, String dolorDientes, String signosDientes, String radiologiaDientes, byte[] odontograma) {
        this.idAntecentes1 = idAntecentes1;
        this.idAntecentes2 = idAntecentes2;
        this.idAntecentes3 = idAntecentes3;
        this.Enfermedad1 = Enfermedad1;
        this.Enfermedad2 = Enfermedad2;
        this.medicamento = medicamento;
        this.Estado1 = Estado1;
        this.Estado2 = Estado2;
        this.Estado3 = Estado3;
        this.CallAtcd1 = CallAtcd1;
        this.CallAtcd2 = CallAtcd2;
        this.CallE1 = CallE1;
        this.CallE2 = CallE2;
        this.CallD1 = CallD1;
        this.CallD2 = CallD2;
        this.idHistoria = idHistoria;
        this.showAntcid1 = showAntcid1;
        this.showAntcidh1 = showAntcidh1;
        this.showNameantc1 = showNameantc1;
        this.showDescription1 = showDescription1;
        this.showAntcid2 = showAntcid2;
        this.showAntcidh2 = showAntcidh2;
        this.showNameantc2 = showNameantc2;
        this.showDescription2 = showDescription2;
        this.showEadc = showEadc;
        this.showEadcH = showEadcH;
        this.showNameAdc = showNameAdc;
        this.showDescriptionAdc = showDescriptionAdc;
        this.idlaboral = idlaboral;
        this.historialaboral = historialaboral;
        this.empresalaboral = empresalaboral;
        this.cargo = cargo;
        this.actividad = actividad;
        this.tiempo = tiempo;
        this.riesgos = riesgos;
        this.idOdontologia = idOdontologia;
        this.idHistoriaOdontologica = idHistoriaOdontologica;
        this.Dientes = Dientes;
        this.informeDiente = informeDiente;
        this.dolorDientes = dolorDientes;
        this.signosDientes = signosDientes;
        this.radiologiaDientes = radiologiaDientes;
        this.odontograma = odontograma;
    }

    public int getIdAntecentes1() {
        return idAntecentes1;
    }

    public void setIdAntecentes1(int idAntecentes1) {
        this.idAntecentes1 = idAntecentes1;
    }

    public int getIdAntecentes2() {
        return idAntecentes2;
    }

    public void setIdAntecentes2(int idAntecentes2) {
        this.idAntecentes2 = idAntecentes2;
    }

    public int getIdAntecentes3() {
        return idAntecentes3;
    }

    public void setIdAntecentes3(int idAntecentes3) {
        this.idAntecentes3 = idAntecentes3;
    }

    public String getEnfermedad1() {
        return Enfermedad1;
    }

    public void setEnfermedad1(String Enfermedad1) {
        this.Enfermedad1 = Enfermedad1;
    }

    public String getEnfermedad2() {
        return Enfermedad2;
    }

    public void setEnfermedad2(String Enfermedad2) {
        this.Enfermedad2 = Enfermedad2;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getEstado1() {
        return Estado1;
    }

    public void setEstado1(String Estado1) {
        this.Estado1 = Estado1;
    }

    public String getEstado2() {
        return Estado2;
    }

    public void setEstado2(String Estado2) {
        this.Estado2 = Estado2;
    }

    public String getEstado3() {
        return Estado3;
    }

    public void setEstado3(String Estado3) {
        this.Estado3 = Estado3;
    }

    public int getCallAtcd1() {
        return CallAtcd1;
    }

    public void setCallAtcd1(int CallAtcd1) {
        this.CallAtcd1 = CallAtcd1;
    }

    public int getCallAtcd2() {
        return CallAtcd2;
    }

    public void setCallAtcd2(int CallAtcd2) {
        this.CallAtcd2 = CallAtcd2;
    }

    public String getCallE1() {
        return CallE1;
    }

    public void setCallE1(String CallE1) {
        this.CallE1 = CallE1;
    }

    public String getCallE2() {
        return CallE2;
    }

    public void setCallE2(String CallE2) {
        this.CallE2 = CallE2;
    }

    public String getCallD1() {
        return CallD1;
    }

    public void setCallD1(String CallD1) {
        this.CallD1 = CallD1;
    }

    public String getCallD2() {
        return CallD2;
    }

    public void setCallD2(String CallD2) {
        this.CallD2 = CallD2;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public int getShowAntcid1() {
        return showAntcid1;
    }

    public void setShowAntcid1(int showAntcid1) {
        this.showAntcid1 = showAntcid1;
    }

    public int getShowAntcidh1() {
        return showAntcidh1;
    }

    public void setShowAntcidh1(int showAntcidh1) {
        this.showAntcidh1 = showAntcidh1;
    }

    public String getShowNameantc1() {
        return showNameantc1;
    }

    public void setShowNameantc1(String showNameantc1) {
        this.showNameantc1 = showNameantc1;
    }

    public String getShowDescription1() {
        return showDescription1;
    }

    public void setShowDescription1(String showDescription1) {
        this.showDescription1 = showDescription1;
    }

    public int getShowAntcid2() {
        return showAntcid2;
    }

    public void setShowAntcid2(int showAntcid2) {
        this.showAntcid2 = showAntcid2;
    }

    public int getShowAntcidh2() {
        return showAntcidh2;
    }

    public void setShowAntcidh2(int showAntcidh2) {
        this.showAntcidh2 = showAntcidh2;
    }

    public String getShowNameantc2() {
        return showNameantc2;
    }

    public void setShowNameantc2(String showNameantc2) {
        this.showNameantc2 = showNameantc2;
    }

    public String getShowDescription2() {
        return showDescription2;
    }

    public void setShowDescription2(String showDescription2) {
        this.showDescription2 = showDescription2;
    }

    public int getShowEadc() {
        return showEadc;
    }

    public void setShowEadc(int showEadc) {
        this.showEadc = showEadc;
    }

    public int getShowEadcH() {
        return showEadcH;
    }

    public void setShowEadcH(int showEadcH) {
        this.showEadcH = showEadcH;
    }

    public String getShowNameAdc() {
        return showNameAdc;
    }

    public void setShowNameAdc(String showNameAdc) {
        this.showNameAdc = showNameAdc;
    }

    public String getShowDescriptionAdc() {
        return showDescriptionAdc;
    }

    public void setShowDescriptionAdc(String showDescriptionAdc) {
        this.showDescriptionAdc = showDescriptionAdc;
    }

    public int getIdlaboral() {
        return idlaboral;
    }

    public void setIdlaboral(int idlaboral) {
        this.idlaboral = idlaboral;
    }

    public int getHistorialaboral() {
        return historialaboral;
    }

    public void setHistorialaboral(int historialaboral) {
        this.historialaboral = historialaboral;
    }

    public String getEmpresalaboral() {
        return empresalaboral;
    }

    public void setEmpresalaboral(String empresalaboral) {
        this.empresalaboral = empresalaboral;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(String riesgos) {
        this.riesgos = riesgos;
    }

    public int getIdOdontologia() {
        return idOdontologia;
    }

    public void setIdOdontologia(int idOdontologia) {
        this.idOdontologia = idOdontologia;
    }

    public int getIdHistoriaOdontologica() {
        return idHistoriaOdontologica;
    }

    public void setIdHistoriaOdontologica(int idHistoriaOdontologica) {
        this.idHistoriaOdontologica = idHistoriaOdontologica;
    }

    public String getDientes() {
        return Dientes;
    }

    public void setDientes(String Dientes) {
        this.Dientes = Dientes;
    }

    public String getInformeDiente() {
        return informeDiente;
    }

    public void setInformeDiente(String informeDiente) {
        this.informeDiente = informeDiente;
    }

    public String getDolorDientes() {
        return dolorDientes;
    }

    public void setDolorDientes(String dolorDientes) {
        this.dolorDientes = dolorDientes;
    }

    public String getSignosDientes() {
        return signosDientes;
    }

    public void setSignosDientes(String signosDientes) {
        this.signosDientes = signosDientes;
    }

    public String getRadiologiaDientes() {
        return radiologiaDientes;
    }

    public void setRadiologiaDientes(String radiologiaDientes) {
        this.radiologiaDientes = radiologiaDientes;
    }

    public byte[] getOdontograma() {
        return odontograma;
    }

    public void setOdontograma(byte[] odontograma) {
        this.odontograma = odontograma;
    }

    
    
}
