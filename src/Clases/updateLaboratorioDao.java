/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class updateLaboratorioDao {
    
        Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    
     
    public void updateLaboratorio(updateLaboratorio vo) {

        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
 
            String sql = "UPDATE table_laboratorio  set   Reportepdf=? WHERE idlab=?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBytes(1, vo.getArchivoUpdate());
            ps.setInt(2, vo.getIdlabUpdate());
            ps.executeUpdate();
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "SE HA CORREGIDO EL EXAMEN, VERIFIQUE EN CONSULTA", "CORRECCIÓN", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ALMACENAR PDF", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e);
       //     JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           } finally {
            try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
        }
    }
       
       
       
       
}
    

