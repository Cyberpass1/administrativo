/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


public class JCAsignarProcedimientos {
    
       int idShowProcess;
       String Process;
       String Estado;
       
       
           public JCAsignarProcedimientos() {}

    public JCAsignarProcedimientos(int idShowProcess, String Process, String Estado) {
        this.idShowProcess = idShowProcess;
        this.Process = Process;
        this.Estado = Estado;
    }

    public int getIdShowProcess() {
        return idShowProcess;
    }

    public void setIdShowProcess(int idShowProcess) {
        this.idShowProcess = idShowProcess;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String Process) {
        this.Process = Process;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
           
           

    
}
