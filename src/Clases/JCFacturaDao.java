/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static Clases.LlenarCombobox.rs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class JCFacturaDao {
    
    
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
 

    
    
 
    public List<JCFactura> listarServices() {
        List<JCFactura> lista = new ArrayList<>();
String sql = "SELECT \n" +
"    id_servicio, \n" +
"    serv_codigo, \n" +
"    servicio, \n" +
"    serv_precio, \n" +
"    c.Catg_Servicio, \n" +
"    serv_estado,\n" +
"    e.Estado\n" +
"FROM \n" +
"    table_servicios s\n" +
"INNER JOIN \n" +
"    categorias_servicios c \n" +
"    ON s.serv_categoria = c.Id_CatgServ\n" +
"  INNER JOIN \n" +
"    table_estado e \n" +
"    ON s.serv_estado = e.IdEstado  \n" +
"    \n" +
"ORDER BY \n" +
"    id_servicio ASC;";

        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int estado = rs.getInt("serv_estado");

                if (estado == 100 || estado == 101) {
                JCFactura f = new JCFactura();
                
                    f.setServ_id(rs.getInt("id_servicio"));
                    f.setServ_code(rs.getString("serv_codigo"));
                    f.setServicio(rs.getString("servicio"));
                    f.setServ_precio(rs.getString("serv_precio"));
                    f.setServ_catg(rs.getString("Catg_Servicio"));
                    f.setServ_estado(rs.getString("e.Estado"));
                        lista.add(f);
                    
                }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return lista;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
 
public List<JCFactura> listarServicios() {
    List<JCFactura> lista = new ArrayList<>();
    String sql = "SELECT id_producto AS idPrd, code_prd AS codigoPrd, producto AS Prd, precio AS precio, " +
                 "c.Catg_Prd AS categoriaPrd, existencia AS existencia, stockmin AS stockmin, " +
                 "stockmax AS stockmax, estadoPrd AS estado " +
                 "FROM table_productos u " +
                 "INNER JOIN categorias_productos c ON u.id_catgPrd = c.id_ctgPrd " +
                 "UNION ALL " +
                 "SELECT id_servicio AS idPrd, serv_codigo AS codigoPrd, servicio AS Prd, serv_precio AS precio, " +
                 "c.Catg_Servicio AS categoriaPrd, NULL AS existencia, NULL AS stockmin, NULL AS stockmax, " +
                 "serv_estado AS estado " +
                 "FROM table_servicios s " +
                 "INNER JOIN categorias_servicios c ON s.serv_categoria = c.Id_CatgServ " +
                 "ORDER BY codigoPrd ASC;";

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        // Procesar los resultados dentro del bloque try
        while (rs.next()) {
            int estado = rs.getInt("estado");

            if (estado == 100) {
                JCFactura f = createFacture(rs); // Suponiendo que 'createFacture' se encarga de crear un objeto JCFactura
                if (f != null) {
                    lista.add(f);
                }
            }
        }

    } catch (Exception e) {
        System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
    }

    return lista;
}
    
    
    
    private JCFactura createFacture(ResultSet rs) {
        try {
            JCFactura f = new JCFactura();
            // Identifica si es un servicio o un producto basado en la presencia de datos.
            if (rs.getObject("precio") != null) {
                // Asumiendo que si 'precio' no es null, entonces es un servicio.
                f.setServ_id(rs.getInt("idPrd"));
                f.setServ_code(rs.getString("codigoPrd"));
                f.setServicio(rs.getString("Prd"));
                f.setServ_precio(rs.getString("precio"));
                f.setServ_catg(rs.getString("categoriaPrd"));
           
                // Asumiendo que si 'precio' es null, entonces es un producto.
                f.setIdproducto(rs.getInt("idPrd"));
                f.setCodeproducto(rs.getString("codigoPrd"));
                f.setProducto(rs.getString("Prd"));
                f.setPrecioPrd(rs.getString("precio"));
                f.setCtgProducto(rs.getString("categoriaPrd"));
                f.setExistencia(rs.getInt("existencia"));
                f.setStockmin(rs.getInt("stockmin"));
                f.setStockmax(rs.getInt("stockmax"));
             } 
            return f;
        } catch (Exception e) {
            System.err.println("Error al procesar datos: " + e.getMessage());
            return null;
        } 
    }
       
        public List listarCatgServ() {
        List<JCFactura> lista = new ArrayList<>();
        String sql = "SELECT u.Id_CatgServ, u.Catg_Servicio, e.Estado\n" +
        "FROM categorias_servicios u\n" +
        "INNER JOIN table_estado e\n" +
        "ON u.Catg_estado = e.IdEstado\n" +
        "ORDER BY u.Id_CatgServ;" ;
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCFactura f = new JCFactura();
                f.setIdCatgServ(rs.getInt(1));
                f.setNameCatgServ(rs.getString(2));
                f.setStateCatServ(rs.getString(3));
         
          
          
          
             
     
                lista.add(f);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
    
    
    
        
      
        
        
        public List listarProductos() {
        List<JCFactura> lista = new ArrayList<>();
        String sql = "SELECT id_producto, code_prd, producto, precio, existencia,stockmin,stockmax,c.Catg_Prd, e.estado, estadoPrd, serial_barra FROM table_productos u \n" +
"        INNER JOIN table_estado e \n" +
"        ON u.estadoPrd=e.IdEstado\n" +
"  \n" +
"        INNER JOIN categorias_productos c \n" +
"        ON u.id_catgPrd = c.id_ctgPrd ORDER BY u.id_producto ASC;" ;
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCFactura f =  new JCFactura();
                f.setIdproducto(rs.getInt(1));
                f.setCodeproducto(rs.getString(2));
                f.setProducto(rs.getString(3));
                f.setPrecioPrd(rs.getString(4));
                f.setExistencia(rs.getInt(5));
                f.setStockmin(rs.getInt(6));
                f.setStockmax(rs.getInt(7));
                f.setCtgProducto(rs.getString(8));
                f.setStatePrd(rs.getString(9));
                f.setCode_barra(rs.getString(11));
     
                lista.add(f);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
        
        
        
        
        
        

  public List searchProductos(String producto) {
     Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
        List<JCFactura> lista = new ArrayList<>();
        String sql = "SELECT id_producto, code_prd, producto, precio, existencia, stockmin, stockmax, c.Catg_Prd, e.estado, estadoPrd, serial_barra " +
             "FROM table_productos u " +
             "INNER JOIN table_estado e ON u.estadoPrd = e.IdEstado " +
             "INNER JOIN categorias_productos c ON u.id_catgPrd = c.id_ctgPrd " +
             "WHERE code_prd LIKE ? OR producto LIKE ? " +
             "ORDER BY u.id_producto ASC";
        
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + producto + "%");
            ps.setString(2, "%" + producto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCFactura f =  new JCFactura();
                f.setIdproducto(rs.getInt(1));
                f.setCodeproducto(rs.getString(2));
                f.setProducto(rs.getString(3));
                f.setPrecioPrd(rs.getString(4));
                f.setExistencia(rs.getInt(5));
                f.setStockmin(rs.getInt(6));
                f.setStockmax(rs.getInt(7));
                f.setCtgProducto(rs.getString(8));
                f.setStatePrd(rs.getString(9));
                f.setCode_barra(rs.getString(11));
     
                lista.add(f);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
        
        

        
          
        public List listarCatgPrd() {
        List<JCFactura> lista = new ArrayList<>();
        String sql = "SELECT u.id_ctgPrd, u.Catg_Prd, e.Estado\n" +
"        FROM categorias_productos u\n" +
"        INNER JOIN table_estado e\n" +
"        ON u.Estado_Prd = e.IdEstado\n" +
"        ORDER BY u.id_ctgPrd;" ;
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                JCFactura f = new JCFactura();
                f.setIdCatgServ(rs.getInt(1));
                f.setNameCatgServ(rs.getString(2));
                f.setStateCatServ(rs.getString(3));
         
          
          
          
             
     
                lista.add(f);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
        
        
        
        
     
        
              
 public void facturarOrden(JCFactura vo) {
    Connection con = null;
    PreparedStatement ps = null;
    EnlaceBd cn = new EnlaceBd();
    ResultSet rs = null; 
    try {
        String sql = "INSERT INTO table_facturacion (cod_fact, id_paciente, id_usuario, metodo_pago, id_pago, fecha_fact, arch_fact, estado_fact) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        
        // Set parameters
        ps.setString(1, vo.getCod_fact()); // Ensure `cod_fact` is properly set
        ps.setInt(2, vo.getId_paciente());
        ps.setInt(3, vo.getId_usuario());
        ps.setInt(4, vo.getMetodo_pago());
        ps.setInt(5, vo.getId_pago());
        ps.setString(6, vo.getFecha_fact());
        ps.setBytes(7, vo.getArch_fact());
        ps.setInt(8, vo.getEstado_factI());

        // Execute the insert
        ps.executeUpdate();
     
    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for debugging
        JOptionPane.showMessageDialog(null, "ERROR ", "FACTURACION", JOptionPane.ERROR_MESSAGE);
    }  finally {
            closeResources(rs, ps, con);
        }
}
        
        
  
              
 public void guardarHonorario(JCFactura vo) {
    Connection con = null;
    PreparedStatement ps = null;
    EnlaceBd cn = new EnlaceBd();

    try {
        String sql = "INSERT INTO `honorarios`( `id_especialista`, `id_especialidad`, `Ho_encargado`, `Ho_fechaOne`, `Ho_fechaTwo`, `Ho_informe`, `Ho_estado`) VALUES (?,?,?,?,?,?,?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        
        // Set parameters
        ps.setInt(1, vo.getHon_especialista()); // Ensure `cod_fact` is properly set
        ps.setInt(2, vo.getHon_especialidad());
        ps.setInt(3, vo.getHon_encargado());
        ps.setString(4, vo.getHon_fecha1());
        ps.setString(5, vo.getHon_fecha2());
        ps.setBytes(6, vo.getHo_reporte());
        ps.setInt(7, vo.getHo_estado());

        // Execute the insert
        int res = ps.executeUpdate();
        if (res >= 1) {
          //  JOptionPane.showMessageDialog(null, "HONORARIO GENERADO...", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ALMACENAR EL HONORARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for debugging
        JOptionPane.showMessageDialog(null, "ERROR ", "HONORARIO", JOptionPane.ERROR_MESSAGE);
    }  finally {
            closeResources(rs, ps, con);
        }
    
    
    
    
}
           
        
        
        
  public void updateHonorario(JCFactura vo, int idHonorario) {
    Connection con = null;
    PreparedStatement ps = null;
    EnlaceBd cn = new EnlaceBd();

    try {
        String sql = "UPDATE `honorarios` SET `id_especialista`=?,`id_especialidad`=?,`Ho_encargado`=?,`Ho_fechaOne`=?,`Ho_fechaTwo`=?,`Ho_informe`=?,`Ho_estado`=? WHERE `id_horonario`=?";
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        
        ps.setInt(1, vo.getHon_especialista()); // Ensure `cod_fact` is properly set
        ps.setInt(2, vo.getHon_especialidad());
        ps.setInt(3, vo.getHon_encargado());
        ps.setString(4, vo.getHon_fecha1());
        ps.setString(5, vo.getHon_fecha2());
        ps.setBytes(6, vo.getHo_reporte());
        ps.setInt(7, vo.getHo_estado());
        ps.setInt(8, idHonorario);

        ps.executeUpdate();
        
    } catch (Exception e) {
      System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
        }
    
    
    
    
}
     
 //-------------- corregir honorarios
  
  
  
public List<JCFactura> listarHonorarioC(int id) {
    List<JCFactura> lista = new ArrayList<>();
    String sql = "SELECT servicio, cantidad, bruto_unit, bruto_total, porcentaje, neto " +
                 "FROM honorario_servicios WHERE id_honorario=?";

    // Usamos try-with-resources para manejar los recursos automáticamente.
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Usar setInt para pasar el valor del id correctamente.
        ps.setInt(1, id);
        
        
        // Ejecutamos la consulta y obtenemos los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                JCFactura f = new JCFactura();
                f.setServicioH(rs.getString("servicio"));
                f.setCantidadH(rs.getInt("cantidad"));
                f.setBrutoInitH(rs.getString("bruto_unit"));
                f.setBrutoTotalH(rs.getString("bruto_total"));
                f.setPorcentajeH(rs.getString("porcentaje"));
                f.setNetoH(rs.getString("neto"));
                
                lista.add(f);
            }
        }

    } catch (Exception e) {
        System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        // Aquí podrías registrar el error con un logger.
    } finally {
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
