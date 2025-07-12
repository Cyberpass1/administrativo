/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Menu.Mprincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FCGinebraI
 */
public class JCAuditoriaDao {
    
    
        
  Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 Mprincipal MP = new Mprincipal();

   
  

        
                
        
        
    public List<JCAuditoria> listarAuditoria() {
    List<JCAuditoria> lista = new ArrayList<>();
    String sql = "SELECT Idauditoria, Usuario, Cedula, date_format(FechaMov, '%d/%m/%Y') AS FechaAudit, Accion " +
                 "FROM table_auditoria u " +
                 "INNER JOIN table_personal w ON u.IdPersonal = w.IdPersonal " +
                 "INNER JOIN table_usuario p ON u.IdUsuario = p.IdPersonal " +
                 "WHERE Idauditoria != 0 " +
                 "ORDER BY Idauditoria";

    // Usar try-with-resources para gestionar recursos automáticamente
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados y añadirlos a la lista
        while (rs.next()) {
            JCAuditoria p = new JCAuditoria();
            p.setIdAudit(rs.getInt(1));
            p.setIdUser(rs.getString(2));
            p.setIdPersonal(rs.getString(3));
            p.setFecha(rs.getString(4));
            p.setAccion(rs.getString(5));

            lista.add(p);
        }

    } catch (Exception e) {
        System.err.println("Error en la consulta: " + e.getMessage());
    }

    return lista;
}

    

}
