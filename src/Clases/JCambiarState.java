
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class JCambiarState {
    
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
   
public boolean Revisar(int estado, int id) {
    String sql = "UPDATE table_laboratorio SET Id_Estado=? WHERE Idlab=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}



    
    public boolean CancelarAsignacion(int estado, int id) {
    String sql = "UPDATE table_historias SET Id_Estadoh=? WHERE Id_historias=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean CancelarProceso(int estado, int id) {
    String sql = "UPDATE asignar_procedimiento SET Id_Estado=? WHERE Id_AProcedimientos=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        ps.execute();
        JOptionPane.showMessageDialog(null, "EL ESTADO DEL PROCEDIMIENTO HA ACTUALIZADO A : Cancelado", "Estado", 1);
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean InactivarProcedimiento(int estado, int id) {
    String sql = "UPDATE table_procedimientos SET Id_Pestado=? WHERE idProcedimiento =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean InactivarEspecialidad(int estado, int id) {
    String sql = "UPDATE table_especialidad SET Id_esta=? WHERE id_especialidad =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean CompletarPaciente(int estado, int id) {
    String sql = "UPDATE table_historias SET Id_Estadoh=? WHERE Id_historias=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean CompletarHistoria(int estado, int id) {
    String sql = "UPDATE table_historias SET Id_Estadoh=? WHERE Id_historias=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

           
           
           
public boolean activarHorario(int estado, int id) {
    String sql = "UPDATE horario_doctores SET EstadoHorario=? WHERE id_horario =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
           
           
        



public boolean inactivarAntecedenteF(int estado, int id) {
    String sql = "UPDATE table_afamiliares SET Estado=? WHERE Id_AFamiliares=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}



public boolean inactivarAntecedenteP(int estado, int id) {
    String sql = "UPDATE table_afamiliarespers SET EstadoPer=? WHERE Id_afamiliarespers =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}

public boolean cancelarOcpSimple(int estado, int id) {
    String sql = "UPDATE ocp_informe SET ocp_estado=? WHERE Id_ocpI=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}




public boolean inactivarCtgPrd(int estado, int id) {
    String sql = "UPDATE categorias_productos SET Estado_Prd=? WHERE id_ctgPrd =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}





public boolean inactivarServicio(int estado, int id) {
    String sql = "UPDATE table_servicios SET serv_estado=? WHERE id_servicio =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}



public boolean inactivarProductos(int estado, int id) {
    String sql = "UPDATE table_productos SET estadoPrd=? WHERE 	id_producto =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}





           
           
public boolean activarHabilitar(int estado, int id, String observacion) {
    String sql = "UPDATE horario_doctores SET Libre_today=?, Observaciones=? WHERE id_horario =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setString(2, observacion);
        ps.setInt(3, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
           
           
                
public boolean inasistenciaPac(int estado, int id) {
    String sql = " UPDATE `table_historias` SET `Id_Estadoh`=? WHERE Id_historias=?";
   
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
        
   
public boolean autorizarHonorario(int estado, int id) {
    String sql = "UPDATE honorarios SET `Ho_estado`=? WHERE id_horonario=?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}





   
public boolean stateFactura(int estado, int id) {
    String sql = "UPDATE table_facturacion SET `estado_fact`=? WHERE 	id_factura =?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, estado);
        ps.setInt(2, id);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}



        
}
