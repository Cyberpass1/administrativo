
package Clases;


public class JCPermisos {
    
    int idpermisos;
    String pcedula;
    String pnombre;
    String pconexion;
    String estado;
            
    
    public JCPermisos (){}

    public JCPermisos(int idpermisos, String pcedula, String pnombre, String pconexion, String estado) {
        this.idpermisos = idpermisos;
        this.pcedula = pcedula;
        this.pnombre = pnombre;
        this.pconexion = pconexion;
        this.estado = estado;
    }

    public int getIdpermisos() {
        return idpermisos;
    }

    public void setIdpermisos(int idpermisos) {
        this.idpermisos = idpermisos;
    }

    public String getPcedula() {
        return pcedula;
    }

    public void setPcedula(String pcedula) {
        this.pcedula = pcedula;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getPconexion() {
        return pconexion;
    }

    public void setPconexion(String pconexion) {
        this.pconexion = pconexion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
