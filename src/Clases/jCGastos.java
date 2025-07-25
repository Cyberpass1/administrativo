/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Jpnew
 */
public class jCGastos {
    
    
    int idGasto;
    String CodeGasto;
    String Gasto;
    String proveedor;
    String estado;
    
    public jCGastos(){
    
    }

    public jCGastos(int idGasto, String CodeGasto, String Gasto, String proveedor, String estado) {
        this.idGasto = idGasto;
        this.CodeGasto = CodeGasto;
        this.Gasto = Gasto;
        this.proveedor = proveedor;
        this.estado = estado;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getCodeGasto() {
        return CodeGasto;
    }

    public void setCodeGasto(String CodeGasto) {
        this.CodeGasto = CodeGasto;
    }

    public String getGasto() {
        return Gasto;
    }

    public void setGasto(String Gasto) {
        this.Gasto = Gasto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

 
    
}
