/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author David
 */
public class sql {

 Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public int auto_increment(String sql) {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (Exception ex) {
            System.out.println("idmaximo" + ex.getMessage());
            id = 1;
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
        return id;
    }
    
    
    
    
       public int auto_increment2(String sql) {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (Exception ex) {
            System.out.println("idmaximo" + ex.getMessage());
            id = 1;
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
        return id;
    }
    
    
    

    public static void main(String[] args) {
    }

}
