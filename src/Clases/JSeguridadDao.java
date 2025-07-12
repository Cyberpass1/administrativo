/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class JSeguridadDao {
    
      Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 

            
    
        public List listarSeguridad() {
        List<JCSeguridad> lista = new ArrayList<>();
        String sql = "select IdPersonal, Usuario, Clave, Pregunta1, Respuesta1, Pregunta2, Respuesta2, Pregunta3, Respuesta3, Pestado from table_usuario where Pestado <> 101 ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCSeguridad p = new JCSeguridad();
                p.setIdUser(rs.getInt(1));
                p.setUsuario(rs.getString(2));
                p.setClave(rs.getString(3));
                
                p.setPregunta1(rs.getString(4));
                p.setRespuesta1(rs.getString(5));
                
                p.setPregunta2(rs.getString(6));
                p.setRespuesta2(rs.getString(7));
               
                p.setPregunta3(rs.getString(8));         
                p.setRespuesta3(rs.getString(9));
          
             
     
                lista.add(p);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
        finally {
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
        return lista;
    }
    
    
    
    
    
    
    
    
    
}
