/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jpnew
 */
public class jCGastosDao {
    
        
  Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
 
    
    public List<jCGastos> listarGastos() {
        List<jCGastos> lista = new ArrayList<>();
String sql = "SELECT `id_gasto`,code_gasto ,`gasto`, p.proveedor , e.Estado, id_estado FROM table_gastos u\n" +
"INNER JOIN table_proveedor p\n" +
"ON u.proveedor = p.id_proveedor\n" +
"\n" +
"INNER JOIN table_estado e\n" +
"ON u.id_estado= e.IdEstado";

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
              //  int estado = rs.getInt("id_estado");

               // if (estado == 100 || estado == 101) {
               jCGastos f = new  jCGastos();

                    f.setIdGasto(rs.getInt("id_gasto"));
                    f.setCodeGasto(rs.getString("code_gasto"));
                    f.setGasto(rs.getString("gasto"));
                    f.setProveedor(rs.getString("p.proveedor"));
                    f.setEstado(rs.getString("e.Estado"));
                    lista.add(f);
                    
               // }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        } finally {
            cn.closeResources(rs, ps, con);
        }

        return lista;
    }
    
    
    
    
    
    
    
}
