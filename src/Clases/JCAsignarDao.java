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
 * @author FCGinebraI
 */
public class JCAsignarDao {
    
    
            
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    
    
    
    
    
    
    
  public ArrayList<JCAsignar> ListarAsignacion(){
       
  ArrayList<JCAsignar> al=null;
                al=new ArrayList<JCAsignar>();
 
String sql = "SELECT IdAsignar, Nombre, Apellido, Cedula, Usuario,date_format(Fecha, '%d/%m/%Y') AS Fecha, EstadoA\n" +
"FROM table_asignar u \n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.IdPacientes= c.Idpaciente\n" +
"\n" +
"INNER JOIN table_estado p\n" +
"ON u.EstadoA= p.IdEstado \n" +
"\n" +
"INNER JOIN table_usuario x\n" +
"ON u.IdUsuario= x.IdPersonal\n" +
"\n" +
"where Id_Especialidad=6001";
        






        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int estado =rs.getInt("EstadoA"); 
                if( estado==102){
                JCAsignar prd = new JCAsignar();
                prd.setIdAsignar(rs.getInt(1));
                prd.setNombre(rs.getString(2));
                prd.setApellido(rs.getString(3));
                prd.setCedula(rs.getString(4));
                prd.setCorreo(rs.getString(5));
                prd.setSexo(rs.getString(6));
                prd.setEdad(rs.getString(7));
                prd.setFecha(rs.getString(8));
                prd.setEspecialidad(rs.getString(9));
                prd.setEstado(rs.getString(10));
                al.add(prd);
                  }
            }
        } catch (Exception e) {System.err.println("Error al listar"+ e);
        }
         finally {
            closeResources(rs, ps, con);
        }
        return al;
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
