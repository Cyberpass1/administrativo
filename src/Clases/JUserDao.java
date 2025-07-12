/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Registros.JOnline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JUserDao {
    

    
  Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 
   
  
     
    
    
    public List listarUsuarios() {
        List<JCUsers> lista = new ArrayList<>();
      //  String sql = "select IdPersonal, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, date_format(Nacimiento, '%d/%m/%Y') AS Fecha from table_personal";
           String query = "SELECT \n" +
"    u.IdPersonal, \n" +
"    u.Usuario, \n" +
"    o.Nombre, \n" +
"    o.Apellido, \n" +
"    o.Cedula, \n" +
"    o.Telefono,\n" +
"    o.Correo,\n" +
"    o.Direccion, \n" +
"    o.Nacimiento,\n" +
"    n.Nivelu,\n" +
"     l.Categoria AS EspecialidadID\n" +
"FROM \n" +
"    table_usuario u \n" +
"    INNER JOIN table_nivel n ON u.Nivel = n.Idnivel\n" +
"    INNER JOIN table_personal o ON u.IdPersonal = o.IdPersonal \n" +
"    LEFT JOIN categorias_administrativas l ON u.categoriaAdmin = l.id_Administrativo" ;
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCUsers p = new  JCUsers();
                p.setIdpacientes(rs.getInt("IdPersonal"));
                p.setUsuario(rs.getString("Usuario"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                p.setCedula(rs.getString("Cedula"));
                p.setTelefono(rs.getString("Telefono"));
                p.setCorreo(rs.getString("Correo"));
                p.setDireccion(rs.getString("Direccion"));
                p.setFechaN(rs.getString("Nacimiento"));
                p.setNivel(rs.getString("Nivelu"));
                p.setEspecialidadUser(rs.getString("EspecialidadID"));
                
          
             
     
                lista.add(p);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
      finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
     
     
     int idonline;
    int online;
    String usuario;
    String nivel;
    String ultentrada;
    
    
  
     public List listarOnline() {
        List<JCUsers> lista = new ArrayList<>();
        String sql = "SELECT u.IdPersonal, u.Usuario, e.Nivelu, u.Fecha, u.Hora, u.online, u.Fecha_Entrada, u.Hora_Entrada, Pestado " +
                 "FROM table_usuario u " +
                 "INNER JOIN table_nivel e ON u.Nivel = e.IdNivel " +
                 "WHERE u.online = ? AND Pestado <> 101 \n" +
                 "ORDER BY u.Usuario ASC";
       
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               JCUsers p = new JCUsers();
                p.setIdonline(rs.getInt(1));
                p.setOnline(rs.getInt(6));
                p.setUsuario(rs.getString(2));
                p.setNivel(rs.getString(3));
                p.setFechaOnline(rs.getString(4));
                p.setUltentrada(rs.getString(5));
                p.setFechaEntrada(rs.getString(7));
                p.setHoraEntrada(rs.getString(8));
            
                lista.add(p);
                
                
                
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
    
  
    
     
     
       
     public List listarTodosOn() {
        List<JCUsers> lista = new ArrayList<>();
        String sql = "SELECT u.IdPersonal, u.Usuario, e.Nivelu, u.Fecha, u.Hora, u.online, u.Fecha_Entrada, u.Hora_Entrada\n" +
        "FROM table_usuario u\n" +
        "INNER JOIN table_nivel e ON u.Nivel = e.IdNivel\n" +
        "WHERE Pestado <> 101 \n"+
        "ORDER BY u.online DESC;";
       
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            //ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               JCUsers p = new JCUsers();
                p.setIdonline(rs.getInt(1));
                p.setOnline(rs.getInt(6));
                p.setUsuario(rs.getString(2));
                p.setNivel(rs.getString(3));
                p.setFechaOnline(rs.getString(4));
                p.setUltentrada(rs.getString(5));
                p.setFechaEntrada(rs.getString(7));
                p.setHoraEntrada(rs.getString(8));
            
                lista.add(p);
                
                
                
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
     
     
     
            private void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
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
