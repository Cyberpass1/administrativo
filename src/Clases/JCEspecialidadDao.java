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
public class JCEspecialidadDao {
    
    
    
    
    
      
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 
   
  
     
    
   public List<JCEspecialidades> listarEspecialidades() {
    List<JCEspecialidades> lista = new ArrayList<>();
    String sql = "SELECT id_especialidad, especialidad, Estado FROM table_especialidad u " +
                 "INNER JOIN table_estado e ON u.Id_esta = e.IdEstado " +
                 "ORDER BY especialidad ASC";

    // Usar try-with-resources para gestionar los recursos automáticamente
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados y añadirlos a la lista
        while (rs.next()) {
            JCEspecialidades p = new JCEspecialidades();
            p.setIdespecialidad(rs.getInt(1));
            p.setEspecialidad(rs.getString(2));
            p.setEstado(rs.getString(3));

            lista.add(p);
        }

    } catch (Exception e) {
        System.err.println("Error al procesar los resultados Especialidades: " + e.getMessage());
    }

    return lista;
}

    
    
    
    
    public List<JCEspecialidades> listarAntcFam() {
    List<JCEspecialidades> lista = new ArrayList<>();
    String sql = "SELECT Id_AFamiliares, Enfermedad, e.Estado FROM table_afamiliares u " +
                 "INNER JOIN table_estado e ON u.Estado = e.IdEstado " +
                 "ORDER BY Enfermedad ASC";

    // Usar try-with-resources para gestionar los recursos automáticamente
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados y añadirlos a la lista
        while (rs.next()) {
            JCEspecialidades p = new JCEspecialidades();
            p.setIdAntcF(rs.getInt(1));
            p.setAntcF(rs.getString(2));
            p.setEstadoantcF(rs.getString(3));

            lista.add(p);
        }

    } catch (Exception e) {
        System.err.println("Error en la consulta SQL  Antecendentes: " + e.getMessage());
    }

    return lista;
}

    
    
    ///--------------------------------------------------------------------
    
public List<JCEspecialidades> listarAntcPers() {
    List<JCEspecialidades> lista = new ArrayList<>();
    String sql = "SELECT Id_afamiliarespers, EnfermedadPer, e.Estado " +
                 "FROM table_afamiliarespers u " +
                 "INNER JOIN table_estado e ON u.EstadoPer = e.IdEstado " +
                 "ORDER BY Id_afamiliarespers ASC";

    // Usar try-with-resources para gestionar los recursos automáticamente
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados y añadirlos a la lista
        while (rs.next()) {
            JCEspecialidades p = new JCEspecialidades();
            p.setIdAntcP(rs.getInt(1));
            p.setAntcP(rs.getString(2));
            p.setEstadoantcP(rs.getString(3));

            lista.add(p);
        }

    } catch (Exception e) {
        System.err.println("Error al procesar los resultados Antecedentes Personales: " + e.getMessage());
    }

    return lista;
}

    
public List<JCEspecialidades> listarDiagnostico() {
    List<JCEspecialidades> lista = new ArrayList<>();
    String sql = "SELECT u.id_resultado, u.diagnostico, e.Estado " +
                 "FROM table_resultado u " +
                 "INNER JOIN table_estado e ON u.estado = e.IdEstado " +
                 "ORDER BY u.diagnostico ASC;";

    // Usar try-with-resources para gestionar los recursos automáticamente
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados y añadirlos a la lista
        while (rs.next()) {
            JCEspecialidades p = new JCEspecialidades();
            p.setIdDianostico(rs.getInt(1));
            p.setDiagnostico(rs.getString(2));
            p.setEstadoDiagnostico(rs.getString(3));

            lista.add(p);
        }

    }  catch (Exception e) {
        System.err.println("Error al procesar los resultados: " + e.getMessage());
    }

    return lista;
}
         
  
    
    
    
}
