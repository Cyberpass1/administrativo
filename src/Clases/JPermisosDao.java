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

/**
 *
 * @author FCGI-ADMIN
 */
public class JPermisosDao {
        
  Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 
    
    
    public List listarUsuarios() {
        List<JCPermisos> lista = new ArrayList<>();
        String sql = "select IdPersonal, Usuario,nivelu, date_format(Fecha, '%d/%m/%Y') AS Fecha2, Estado\n" +
                     "from table_usuario as u \n" +
                     "INNER JOIN table_estado AS n ON u.Pestado=n.IdEstado\n" +
                     "INNER JOIN table_nivel AS d ON u.Nivel = d.IdNivel";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCPermisos p = new JCPermisos();
               
                p.setIdpermisos(rs.getInt(1));
                p.setPcedula(rs.getString(2));
                p.setPnombre(rs.getString(3));
                p.setPconexion(rs.getString(4));
                p.setEstado(rs.getString(5));
       
          
             
     
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
