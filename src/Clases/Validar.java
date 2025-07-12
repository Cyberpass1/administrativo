/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Validar {
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

    public Validar(){}
    
    public void longitud(String cadena, int tamaño, KeyEvent ev)
    {
    if(cadena.trim().length()==tamaño){ev.consume();}
    
    }
    
    public void Validarnumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                //SI EL CARACTER INGRESADO NO ES UN NUMERO, NO LO CONSIDERA.
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
    
    
     public void Validarletras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                    e.consume();
                }
            }
        });
    }
    
    
    public boolean ExisteEnTabla (JTable tabla, String Dto, int col)
    {
    boolean existe=false;
    for(int i=0; i<tabla.getRowCount(); i++)
    {
    if(tabla.getValueAt(i, col).equals(Dto))
    {
    
    existe= true;
    }
    
    }
    
    return existe;
    }
    
    public void RegistrarTabla(JTable tabla, String Dto, int col, DefaultTableModel mdt)
    {
  if(ExisteEnTabla(tabla, Dto, col))
  {
  Object struct[]={Dto}; mdt.addRow(struct);
  
  }
  else{JOptionPane.showMessageDialog(null,"Esta materia prima ya ha sido agregada"," Pedido ",JOptionPane.ERROR_MESSAGE);}
    
    }
    
    
    
    
    public static String fecha_actual()
    {
    Date fecha = new Date();
     SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy");
          return formato_fecha.format(fecha);
          
    
    }
    
    
        
    public static String fecha_mes()
    {
    Date fecha = new Date();
     SimpleDateFormat formato_fecha = new SimpleDateFormat("MM");
          return formato_fecha.format(fecha);
          
    
    }
    
            
    public static String fecha_dia()
    {
    Date fecha = new Date();
     SimpleDateFormat formato_fecha = new SimpleDateFormat("dd");
          return formato_fecha.format(fecha);
          
    
    }
 
    
    
       
    public void ValidarnumerosSignos(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                //SI EL CARACTER INGRESADO NO ES UN NUMERO, NO LO CONSIDERA.
                char c = e.getKeyChar();
                if (c=='.' ||c==',' || c>='0' && c<='9') {
                    e.consume();
                }
            }
        });
    }

   
    
    
    
    
    
    
    
}
