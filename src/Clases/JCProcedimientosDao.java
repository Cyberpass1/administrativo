/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.LlenarCombobox.rs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FCGI-ADMIN
 */
public class JCProcedimientosDao {
    
    
    
    
      
  Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 
   
  
     
    
    
    public List listarProcedimientos() {
        List<JCProcedimientos> lista = new ArrayList<>();
        String sql = "select idProcedimiento, Procedimiento, Especialidad, Estado from table_procedimientos u\n" +
"INNER JOIN table_estado e\n" +
"ON u.Id_Pestado=e.IdEstado\n" +
"\n" +
"INNER JOIN table_especialidad x\n" +
"ON u.Id_Categoria=x.id_Especialidad \n ORDER BY Especialidad ASC";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               JCProcedimientos p = new JCProcedimientos();
                p.setIdprocedimiento(rs.getInt(1));
                p.setProcedimiento(rs.getString(2));
                p.setCategoria(rs.getString(3));
                p.setEstado(rs.getString(4));
         
          
          
          
             
     
                lista.add(p);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
   
    
     public List<JCProcedimientos> listarHorarios() {
    List<JCProcedimientos> lista = new ArrayList<>();
    String sql = "SELECT id_horario, Especialidad, s.Usuario, Horario, observaciones, Dias_disponible, s.Pestado, Estado\n" +
"    FROM horario_doctores u\n" +
"    INNER JOIN table_estado e ON u.EstadoHorario = e.IdEstado \n" +
"    INNER JOIN table_especialidad x ON u.id_especialidad = x.id_Especialidad \n" +
"    INNER JOIN table_usuario s ON u.Doctor = s.IdPersonal\n" +
"    ORDER BY Especialidad ASC";

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int estado =rs.getInt("s.Pestado");
            // if( estado==100){
            JCProcedimientos p = new JCProcedimientos();
            p.setIdhorario(rs.getInt("id_horario"));
            p.setEspecialidad(rs.getString("Especialidad"));
            p.setDoctor(rs.getString("Usuario"));
            p.setEntrada(rs.getString("Horario"));
            p.setEstadoHora(rs.getString("Estado"));
            p.setObservacionHora(rs.getString("observaciones"));
            p.setDiasHorario(rs.getString("Dias_disponible"));


            lista.add(p);
        }// }
    } catch (Exception e) {
        System.err.println("Error al conectar: " + e.getMessage());
    }  finally {
            closeResources(rs, ps, con);
        }

    return lista;
}
    
    

      
      
      public List listarDiferencia(String fecha1, String fecha2) {
        List<JCProcedimientos> lista = new ArrayList<>();
     String sql = "SELECT `id_diferencia`, e.Doctor, u.entrada, `dif_entrada`, u.salida, `dif_salida`, `motivo`, `observacion`, `Fecha` FROM diferencia_horario u\n" +
        "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario\n" + 
        "WHERE fecha BETWEEN ? AND ? ORDER BY id_diferencia ASC";

        try {
           
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               JCProcedimientos p = new JCProcedimientos();
                p.setIdDiferencia(rs.getInt(1));
                p.setDoctor(rs.getString(2));
                p.setEntradaReal(rs.getString(3));
                p.setEntradaDif(rs.getString(4));
                p.setSalidaReal(rs.getString(5));
                p.setSalidaDif(rs.getString(6));
                p.setMotivoReal(rs.getString(7));
                p.setObservacionReal(rs.getString(8));
                p.setFechaReal(rs.getString(9));
          
             
     
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




