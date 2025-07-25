/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Registros.JProveedores;
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
    

    
  Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
 
   
  
     
    
    
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

     
    
    
        
    
   public List<jProveedores> listarProveedores() {
    List<jProveedores> lista = new ArrayList<>();
    String sql = "SELECT id_proveedor, `proveedor`, p.categoria, `rif`, `telefono`, `correo`, `direccion`, e.Estado FROM table_proveedor u\n" +
    "INNER join categorias_proveedores p \n" +
    "ON u.id_categoria=p.id_catprov\n" +
    "INNER join table_estado e \n" +
    "ON u.estado=e.IdEstado";
    
    try (Connection con = cn.getConnection(); // Usamos try-with-resources para que se cierre automáticamente
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {  // También aseguramos el cierre del ResultSet y PreparedStatement

        while (rs.next()) {
            jProveedores p = new jProveedores();
            p.setIdProveedor(rs.getInt(1));
            p.setProveedor(rs.getString(2));
            p.setCategoria(rs.getString(3));
            p.setRif(rs.getString(4));
            p.setTelefono(rs.getString(5));
            p.setCorreo(rs.getString(6));
            p.setDireccion(rs.getString(7));
            p.setEstado(rs.getString(8));


            lista.add(p);
        }
    } catch (Exception e) {
        System.err.println("Error al conectar: " + e);
    }
    return lista;
}
    
    
    
     
}
