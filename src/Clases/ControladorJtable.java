/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorJtable {
    
   public ControladorJtable() {}

    
    
    
  
    public boolean ExisteDato(JTable tabla, String Dato, int pos)
    {
    
    boolean bandera=false;
    
    for(int i=0;i<tabla.getRowCount();i++)
    
    
    { 
    
    if(tabla.getValueAt(i,pos).equals(Dato)) 
    { 
    bandera=true;
    } 
    }
     return bandera;
    }
    
    
    
    
    public void AsignarPaciente(JTable tabla, String Dato, int col, DefaultTableModel mdt, int pos)
    {
    if(!ExisteDato(tabla,Dato,col))
    {
    Object struct[]={Dato};
    mdt.addRow(struct);
    
    }
    else
    {
    JOptionPane.showMessageDialog(null,""+Dato+"","",JOptionPane.ERROR_MESSAGE);
    }
    
    }
    
    
    
    public void Actualizacion(JTable tabla, String Dato) 
    {
   for(int i=0;i<tabla.getColumnCount();i++)  
   {
   tabla.setValueAt(Dato,tabla.getSelectedRow(),0);
   
   }
    
    }
}
