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
public class JPacientesDao {
    

    
  Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 
   
  
     
    
    
   public List<JCPacientes> listarPacientes() {
    List<JCPacientes> lista = new ArrayList<>();
    String sql = "select Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo from table_paciente";
    try (Connection con = cn.getConnection(); // Usamos try-with-resources para que se cierre automáticamente
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {  // También aseguramos el cierre del ResultSet y PreparedStatement

        while (rs.next()) {
            JCPacientes p = new JCPacientes();
            p.setIdpacientes(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setApellido(rs.getString(3));
            p.setCedula(rs.getString(4));
            p.setTelefono(rs.getString(5));
            p.setCorreo(rs.getString(6));
            p.setDireccion(rs.getString(7));
            p.setSexo(rs.getString(8));


            lista.add(p);
        }
    } catch (Exception e) {
        System.err.println("Error al conectar: " + e);
    }
    return lista;
}

     
    
    
    
    
    
    
     
}
