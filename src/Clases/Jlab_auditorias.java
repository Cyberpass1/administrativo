/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author FCGI-ADMIN
 */
public class Jlab_auditorias {
    
    
    
    
    
    public void corregirEstudio(int idusuario, String accion, String fecha ) {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) VALUES (?, ?, ?, ?)";
    
    Connection con = null;
    PreparedStatement ps = null;
    
    try {
        con = new EnlaceBd().getConnection();
        ps = con.prepareStatement(sql);
        
    
        ps.setInt(1, idusuario);
        ps.setInt(2, idusuario); // Asegúrate de que esto sea correcto, podría ser diferente de idUsuario
        ps.setString(3, accion);
        ps.setString(4, fecha);
        
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al registrar auditoría: " + e.getMessage());
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
        }
        
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar Connection: " + e.getMessage());
        }
    }
}
    
}
