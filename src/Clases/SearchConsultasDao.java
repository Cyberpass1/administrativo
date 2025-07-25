

package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/*ING JUAN CALDERON*/
public class SearchConsultasDao {
    
    
    
    
    
    
    
    
    
     
  public ArrayList<PdfVO> ListarByCedPdfVO(String text, String fecha1, String fecha2) {
    ArrayList<PdfVO> list = new ArrayList<>();
    EnlaceBd cn = new EnlaceBd();
    String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
                 "FROM table_laboratorio u " +
                 "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
                 "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
                 "WHERE Cedula LIKE ? " +
                 "AND FechaReporte BETWEEN ? AND ? " +
                 "ORDER BY Codigopdf ASC";

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, "%" + text + "%");
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt("Codigopdf"));
                vo.setExamen(rs.getString("Estudio"));
                vo.setNombreApellido(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                vo.setCedulapaciente(rs.getString("Cedula"));
                vo.setUsuario(rs.getString("Usuario"));
                vo.setFecha(rs.getString("FechaReporte"));
               // vo.setArchivopdf(rs.getBytes("Reportepdf"));
                vo.setState(rs.getString("Estado"));
                vo.setCorreo(rs.getString("Correo"));
                vo.setNumeropac(rs.getInt("Idpaciente"));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    
    

  public ArrayList<PdfVO> Listar_PdfVO(String fecha, String fecha2) {
    ArrayList<PdfVO> list = new ArrayList<>();
    EnlaceBd cn = new EnlaceBd();
    String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
                 "FROM table_laboratorio u " +
                 "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
                 "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
                 "WHERE FechaReporte BETWEEN ? AND ? " +
                 "ORDER BY Codigopdf ASC";

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, fecha);
        ps.setString(2, fecha2);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt("Codigopdf"));
                vo.setExamen(rs.getString("Estudio"));
                vo.setNombreApellido(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                vo.setCedulapaciente(rs.getString("Cedula"));
                vo.setUsuario(rs.getString("Usuario"));
                vo.setFecha(rs.getString("FechaReporte"));
                vo.setState(rs.getString("Estado"));
                vo.setCorreo(rs.getString("Correo"));
                vo.setNumeropac(rs.getInt("Idpaciente"));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    
  
  
  
  public ArrayList<PdfVO> listarbyEstudio_PdfVO(String fecha, String fecha2, String estudio) {
    // Usar 'List' en lugar de 'ArrayList' para una mejor flexibilidad
   ArrayList<PdfVO> list = new ArrayList<>();

    String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
                 "FROM table_laboratorio u " +
                 "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
                 "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
                 "WHERE Estudio LIKE ? " +
                 "AND FechaReporte BETWEEN ? AND ? " +
                 "ORDER BY Codigopdf ASC";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Set parameters for the query
        ps.setString(1, "%" + estudio + "%");
        ps.setString(2, fecha);
        ps.setString(3, fecha2);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt("Codigopdf"));
                vo.setExamen(rs.getString("Estudio"));
                vo.setNombreApellido(rs.getString("Nombre") + " " + rs.getString("Apellido"));
                vo.setCedulapaciente(rs.getString("Cedula"));
                vo.setUsuario(rs.getString("Usuario"));
                vo.setFecha(rs.getString("FechaReporte"));
                vo.setState(rs.getString("Estado"));
                vo.setCorreo(rs.getString("Correo"));
                vo.setNumeropac(rs.getInt("Idpaciente"));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Usar el log para registrar excepciones
        System.err.println("Error al obtener datos: " + ex.getMessage());
        ex.printStackTrace(); // Mejor para depuración
    }

    return list;
}

  
 
  
  public ArrayList<PdfVO> listarbyEstado_PdfVO(String fecha, String fecha2, String estado) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
                 "FROM table_laboratorio u " +
                 "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
                 "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
                 "WHERE Estado LIKE ? " +
                 "AND FechaReporte BETWEEN ? AND ? " +
                 "ORDER BY Codigopdf ASC";

    // Usamos try-with-resources para asegurar que se cierren los recursos correctamente
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + estado + "%");
        ps.setString(2, fecha);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setNombreApellido(rs.getString(3) + " " + rs.getString(4));
                vo.setCedulapaciente(rs.getString(5));
                vo.setUsuario(rs.getString(6));
                vo.setFecha(rs.getString(7));
                vo.setState(rs.getString(8));
                vo.setCorreo(rs.getString(9));
                vo.setNumeropac(rs.getInt(10));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores en caso de fallo con la base de datos
        System.out.println(ex.getMessage());
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex.getMessage());
    }

    return list;
}

    
    
    
    
    
   public ArrayList<PdfVO> listarbyEstudioyEstado_PdfVO(String fecha, String fecha2, String estudio, String estado) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
                 "FROM table_laboratorio u " +
                 "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
                 "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
                 "WHERE Estudio LIKE ? " +
                 "AND Estado LIKE ? " +
                 "AND FechaReporte BETWEEN ? AND ? " +
                 "ORDER BY Codigopdf ASC";

    // Usamos try-with-resources para asegurar que se cierren los recursos correctamente
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + estudio + "%");
        ps.setString(2, "%" + estado + "%");
        ps.setString(3, fecha);
        ps.setString(4, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setNombreApellido(rs.getString(3) + " " + rs.getString(4));
                vo.setCedulapaciente(rs.getString(5));
                vo.setUsuario(rs.getString(6));
                vo.setFecha(rs.getString(7));
                vo.setState(rs.getString(8));
                vo.setCorreo(rs.getString(9));
                vo.setNumeropac(rs.getInt(10));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores con la base de datos
        System.out.println(ex.getMessage());
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex.getMessage());
    }

    return list;
}

    
    
  
    
    
    
  //---------------- CONSULTAS MEDICAS -----------------
    
    
    
    public ArrayList<PdfVO> Listar_Historias(String fecha, String fecha2) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT Id_historias, especialidad, Usuario, Nombre, Apellido, Cedula, Fecha_Historia, Estado, Correo " +
                 "FROM table_historias u " +
                 "INNER JOIN table_especialidad n ON u.Id_Hespecialidad=n.id_especialidad " +
                 "INNER JOIN table_paciente c ON u.id_pacienteh=c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estadoh=l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh=x.IdPersonal " +
                 "WHERE Fecha_Historia BETWEEN ? AND ? " +
                 "ORDER BY Id_historias ASC";

    // Usamos try-with-resources para manejar los recursos de forma segura y automática
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, fecha);
        ps.setString(2, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodHistoria(rs.getInt(1));
                vo.setEspecialidadHistoria(rs.getString(2));
                vo.setUsuarioHistoria(rs.getString(3));
                vo.setPacienteHistoria(rs.getString(4) + " " + rs.getString(5));
                vo.setCedHistoria(rs.getString(6));
                vo.setFechaHistoria(rs.getString(7));
                vo.setEstadoHistoria(rs.getString(8));
                vo.setCorreoHistoria(rs.getString(9));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}

    

    
   public ArrayList<PdfVO> Listar_CedH(String text, String fecha1, String fecha2) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT Id_historias, especialidad, Usuario, Nombre, Apellido, Cedula, Fecha_Historia, Estado, Correo " +
                 "FROM table_historias u " +
                 "INNER JOIN table_especialidad n ON u.Id_Hespecialidad = n.id_especialidad " +
                 "INNER JOIN table_paciente c ON u.id_pacienteh = c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estadoh = l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
                 "WHERE Cedula LIKE ? AND Fecha_Historia BETWEEN ? AND ? " +
                 "ORDER BY Id_historias ASC";

    // Usamos try-with-resources para manejar los recursos de forma segura y automática
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%"); // Agregamos los comodines % para buscar coincidencias parciales
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodHistoria(rs.getInt(1));
                vo.setEspecialidadHistoria(rs.getString(2));
                vo.setUsuarioHistoria(rs.getString(3));
                vo.setPacienteHistoria(rs.getString(4) + " " + rs.getString(5));
                vo.setCedHistoria(rs.getString(6));
                vo.setFechaHistoria(rs.getString(7));
                vo.setEstadoHistoria(rs.getString(8));
                vo.setCorreoHistoria(rs.getString(9));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}

    
    
    public ArrayList<PdfVO> listar_User(String text, String fecha1, String fecha2) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT Id_historias, especialidad, Usuario, Nombre, Apellido, Cedula, Fecha_Historia, Estado, Correo " +
                 "FROM table_historias u " +
                 "INNER JOIN table_especialidad n ON u.Id_Hespecialidad = n.id_especialidad " +
                 "INNER JOIN table_paciente c ON u.id_pacienteh = c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estadoh = l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
                 "WHERE Usuario LIKE ? AND Fecha_Historia BETWEEN ? AND ? " +
                 "ORDER BY Id_historias ASC";

    // Usamos try-with-resources para manejar los recursos de forma segura y automática
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%"); // Agregamos los comodines % para buscar coincidencias parciales
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodHistoria(rs.getInt(1));
                vo.setEspecialidadHistoria(rs.getString(2));
                vo.setUsuarioHistoria(rs.getString(3));
                vo.setPacienteHistoria(rs.getString(4) + " " + rs.getString(5));
                vo.setCedHistoria(rs.getString(6));
                vo.setFechaHistoria(rs.getString(7));
                vo.setEstadoHistoria(rs.getString(8));
                vo.setCorreoHistoria(rs.getString(9));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}
    
    
    public ArrayList<PdfVO> listar_Code(String text, String fecha1, String fecha2) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT Id_historias, especialidad, Usuario, Nombre, Apellido, Cedula, Fecha_Historia, Estado, Correo " +
                 "FROM table_historias u " +
                 "INNER JOIN table_especialidad n ON u.Id_Hespecialidad = n.id_especialidad " +
                 "INNER JOIN table_paciente c ON u.id_pacienteh = c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estadoh = l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
                 "WHERE Id_historias LIKE ? AND Fecha_Historia BETWEEN ? AND ? " +
                 "ORDER BY Id_historias ASC";

    // Usamos try-with-resources para manejar los recursos de forma segura y automática
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%"); // Agregamos los comodines % para buscar coincidencias parciales
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodHistoria(rs.getInt(1));
                vo.setEspecialidadHistoria(rs.getString(2));
                vo.setUsuarioHistoria(rs.getString(3));
                vo.setPacienteHistoria(rs.getString(4) + " " + rs.getString(5));
                vo.setCedHistoria(rs.getString(6));
                vo.setFechaHistoria(rs.getString(7));
                vo.setEstadoHistoria(rs.getString(8));
                vo.setCorreoHistoria(rs.getString(9));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}
  
    /*
   public ArrayList<PdfVO> listar_Especialidad(String text, String fecha1, String fecha2) {
    // Lista para almacenar los resultados
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT Id_historias, especialidad, Usuario, Nombre, Apellido, Cedula, Fecha_Historia, Estado, Correo " +
                 "FROM table_historias u " +
                 "INNER JOIN table_especialidad n ON u.Id_Hespecialidad = n.id_especialidad " +
                 "INNER JOIN table_paciente c ON u.id_pacienteh = c.Idpaciente " +
                 "INNER JOIN table_estado l ON u.Id_Estadoh = l.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
                 "WHERE especialidad LIKE ? AND Fecha_Historia BETWEEN ? AND ? " +
                 "ORDER BY Id_historias ASC";

    // Usamos try-with-resources para manejar los recursos de forma segura y automática
    try (Connection con = new EnlaceBd().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%"); // Agregamos los comodines % para buscar coincidencias parciales
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodHistoria(rs.getInt(1));
                vo.setEspecialidadHistoria(rs.getString(2));
                vo.setUsuarioHistoria(rs.getString(3));
                vo.setPacienteHistoria(rs.getString(4) + " " + rs.getString(5));
                vo.setCedHistoria(rs.getString(6));
                vo.setFechaHistoria(rs.getString(7));
                vo.setEstadoHistoria(rs.getString(8));
                vo.setCorreoHistoria(rs.getString(9));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}

*/


// H O N O R A R I O SSSSSSSSSSSSSSSSSSSSSSS
    
    public ArrayList<PdfVO> Listar_Honorarios(String fecha, String fecha2) {
    ArrayList<PdfVO> list = new ArrayList<PdfVO>();

    // Sentencia SQL para la consulta
    String sql = "SELECT " +
                 "id_horonario, " +
                 "n.especialidad, " +
                 "CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, " +
                 "x.Usuario, " +
                 "Ho_fechaOne, " +
                 "Ho_fechaTwo, " +
                 "l.Estado, " +
                 "p.Telefono, " +
                 "p.Correo, " +
                 "Ho_estado, " +
                 "res_totalneto " +
                 "FROM " +
                 "honorarios u " +
                 "INNER JOIN " +
                 "table_especialidad n ON u.id_especialidad = n.id_especialidad " +
                 "INNER JOIN " +
                 "table_usuario x ON u.Ho_encargado = x.IdPersonal " +
                 "INNER JOIN " +
                 "table_personal p ON u.id_especialista = p.IdPersonal " +
                 "INNER JOIN " +
                 "table_estado l ON u.Ho_estado = l.IdEstado " +
                 "INNER JOIN " +
                 "honorario_resumen r ON u.id_horonario = r.id_honorario " +
                 "WHERE Ho_fechaOne BETWEEN ? AND ? " +
                 "ORDER BY Ho_fechaOne ASC, id_horonario ASC";

    // Usamos try-with-resources para gestionar recursos de manera automática
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, fecha);
        ps.setString(2, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setId_hon(rs.getInt(1));
                vo.setHon_especialista(rs.getString(2));
                vo.setHon_especialidad(rs.getString(3));
                vo.setHon_encargado(rs.getString(4));
                vo.setHon_fecha1(rs.getString(5));
                vo.setHon_fecha2(rs.getString(6));
                vo.setHo_estado(rs.getString(7));
                vo.setHo_telefono(rs.getString(8));
                vo.setHo_correo(rs.getString(9));
                vo.setNetoPagar(rs.getString("res_totalneto"));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores específicos de SQL
        System.out.println(ex + " error consulta clase");
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex + " error consulta clase");
    }

    // Retornar la lista de resultados
    return list;
}

    
    
    
    
    
    
   public ArrayList<PdfVO> listarHonoBoth(String fecha, String fecha2, String estudio, String estado) {
    ArrayList<PdfVO> list = new ArrayList<>();

    // Sentencia SQL para la consulta
    String sql = "SELECT id_horonario, n.especialidad, CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, " +
                 "x.Usuario, Ho_fechaOne, Ho_fechaTwo, l.Estado, p.Telefono, p.Correo, Ho_estado, res_totalneto " +
                 "FROM honorarios u " +
                 "INNER JOIN table_especialidad n ON u.id_especialidad = n.id_especialidad " +
                 "INNER JOIN table_usuario x ON u.Ho_encargado = x.IdPersonal " +
                 "INNER JOIN table_personal p ON u.id_especialista = p.IdPersonal " +
                 "INNER JOIN table_estado l ON u.Ho_estado = l.IdEstado " +
                 "INNER JOIN honorario_resumen r ON u.id_horonario = r.id_honorario " +
                 "WHERE n.especialidad LIKE ? AND u.Ho_estado LIKE ? AND Ho_fechaOne BETWEEN ? AND ? " +
                 "ORDER BY id_horonario ASC;";

    // Usamos try-with-resources para gestionar la conexión y demás recursos automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + estudio + "%");
        ps.setString(2, "%" + estado + "%");
        ps.setString(3, fecha);
        ps.setString(4, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setId_hon(rs.getInt("id_horonario"));
                vo.setHon_especialidad(rs.getString("n.especialidad"));
                vo.setHon_especialista(rs.getString("especialista"));
                vo.setHon_encargado(rs.getString("x.Usuario"));
                vo.setNetoPagar(rs.getString("res_totalneto"));
                vo.setHon_fecha1(rs.getString("Ho_fechaOne"));
                vo.setHon_fecha2(rs.getString("Ho_fechaTwo"));
                vo.setHo_estado(rs.getString("l.Estado"));
                vo.setHo_telefono(rs.getString("p.Telefono"));
                vo.setHo_correo(rs.getString("p.Correo"));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores SQL
        System.out.println(ex.getMessage());
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex.getMessage());
    }

    // Retornar la lista con los resultados obtenidos
    return list;
}


    public ArrayList<PdfVO> listarHonoState(String fecha, String fecha2, String estudio, String estado) {
    ArrayList<PdfVO> list = new ArrayList<>();

    // Sentencia SQL para la consulta
    String sql = "SELECT id_horonario, n.especialidad, CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, " +
                 "x.Usuario, Ho_fechaOne, Ho_fechaTwo, l.Estado, p.Telefono, p.Correo, Ho_estado, res_totalneto " +
                 "FROM honorarios u " +
                 "INNER JOIN table_especialidad n ON u.id_especialidad = n.id_especialidad " +
                 "INNER JOIN table_usuario x ON u.Ho_encargado = x.IdPersonal " +
                 "INNER JOIN table_personal p ON u.id_especialista = p.IdPersonal " +
                 "INNER JOIN table_estado l ON u.Ho_estado = l.IdEstado " +
                 "INNER JOIN honorario_resumen r ON u.id_horonario = r.id_honorario " +
                 "WHERE l.Estado = ? AND Ho_fechaOne BETWEEN ? AND ? " +
                 "ORDER BY id_horonario ASC;";

    // Usamos try-with-resources para gestionar la conexión y demás recursos automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, estado);
        ps.setString(2, fecha);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y obtener los resultados
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setId_hon(rs.getInt("id_horonario"));
                vo.setHon_especialidad(rs.getString("n.especialidad"));
                vo.setHon_especialista(rs.getString("especialista"));
                vo.setHon_encargado(rs.getString("x.Usuario"));
                vo.setNetoPagar(rs.getString("res_totalneto"));
                vo.setHon_fecha1(rs.getString("Ho_fechaOne"));
                vo.setHon_fecha2(rs.getString("Ho_fechaTwo"));
                vo.setHo_estado(rs.getString("l.Estado"));
                vo.setHo_telefono(rs.getString("p.Telefono"));
                vo.setHo_correo(rs.getString("p.Correo"));

                // Agregar el objeto PdfVO a la lista
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Manejo de errores SQL
        System.out.println(ex.getMessage());
    } catch (Exception ex) {
        // Manejo de errores generales
        System.out.println(ex.getMessage());
    }

    // Retornar la lista con los resultados obtenidos
    return list;
}
    
    
    
    
    public ArrayList<PdfVO> listarHonoSpeciallty(String fecha, String fecha2, String estudio, String estado) {
    String sql = "SELECT id_horonario, \n" +
"       n.especialidad, \n" +
"       CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, \n" +
"       x.Usuario, \n" +
"       Ho_fechaOne, \n" +
"       Ho_fechaTwo, \n" +
"       l.Estado, \n" +
"       p.Telefono, \n" +
"       p.Correo, \n" +
"       Ho_estado,\n" +
"       res_totalneto\n" +
            
"FROM honorarios u\n" +
"INNER JOIN table_especialidad n ON u.id_especialidad = n.id_especialidad\n" +
"INNER JOIN table_usuario x ON u.Ho_encargado = x.IdPersonal\n" +
"INNER JOIN table_personal p ON u.id_especialista = p.IdPersonal\n" +
"INNER JOIN table_estado l ON u.Ho_estado = l.IdEstado\n" +
"INNER JOIN honorario_resumen r ON u.id_horonario = r.id_honorario \n" +
"WHERE n.especialidad LIKE ? \n" +
"  AND Ho_fechaOne BETWEEN ? AND ? \n" +
"ORDER BY id_horonario ASC;";

    // Usamos try-with-resources para gestionar la conexión y los recursos
    ArrayList<PdfVO> list = new ArrayList<>();
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer parámetros de la consulta
        ps.setString(1, "%" + estudio + "%");  // Incluimos % en la búsqueda
        ps.setString(2, fecha);
        ps.setString(3, fecha2);

        // Ejecutar la consulta y procesar el ResultSet
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            PdfVO vo = new PdfVO();
            vo.setId_hon(rs.getInt("id_horonario"));
            vo.setHon_especialidad(rs.getString("n.especialidad"));
            vo.setHon_especialista(rs.getString("especialista"));
            vo.setHon_encargado(rs.getString("x.Usuario"));
            vo.setNetoPagar(rs.getString("res_totalneto"));
            vo.setHon_fecha1(rs.getString("Ho_fechaOne"));
            vo.setHon_fecha2(rs.getString("Ho_fechaTwo"));
            vo.setHo_estado(rs.getString("l.Estado"));
            vo.setHo_telefono(rs.getString("p.Telefono"));
            vo.setHo_correo(rs.getString("p.Correo"));
                
                list.add(vo);
            }
        }
    } catch (SQLException ex) {
        // Utilizamos un logger o una forma más apropiada de manejo de excepciones
        System.err.println("Error en la consulta: " + ex.getMessage());
    } catch (Exception ex) {
        // Manejo general de excepciones
        System.err.println("Error inesperado: " + ex.getMessage());
    }

    return list;
}
    
    
    
    
    
    
   public ArrayList<PdfVO> listarNameHon(String text, String fecha1, String fecha2) {
    String sql = "SELECT id_horonario, n.especialidad, CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, " +
                 "x.Usuario, Ho_fechaOne, Ho_fechaTwo, l.Estado, p.Telefono, p.Correo, Ho_estado, res_totalneto " +
                 "FROM honorarios u " +
                 "INNER JOIN table_especialidad n ON u.id_especialidad = n.id_especialidad " +
                 "INNER JOIN table_usuario x ON u.Ho_encargado = x.IdPersonal " +
                 "INNER JOIN table_personal p ON u.id_especialista = p.IdPersonal " +
                 "INNER JOIN table_estado l ON u.Ho_estado = l.IdEstado " +
                 "INNER JOIN honorario_resumen r ON u.id_horonario = r.id_honorario \n" +
                 "WHERE CONCAT(p.Nombre, ' ', p.Apellido) LIKE ? AND Ho_fechaOne BETWEEN ? AND ? " +
                 "ORDER BY id_horonario ASC";




    ArrayList<PdfVO> list = new ArrayList<>();

    // Usar try-with-resources para manejar la conexión y los recursos automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%");
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);

        // Ejecutar la consulta
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear y llenar el objeto PdfVO
                PdfVO vo = new PdfVO();
                vo.setId_hon(rs.getInt("id_horonario"));
                vo.setHon_especialista(rs.getString("especialista"));
                vo.setHon_especialidad(rs.getString("especialidad"));
                vo.setHon_encargado(rs.getString("Usuario"));
                vo.setNetoPagar(rs.getString("res_totalneto"));
                vo.setHon_fecha1(rs.getString("Ho_fechaOne"));
                vo.setHon_fecha2(rs.getString("Ho_fechaTwo"));
                vo.setHo_estado(rs.getString("Estado"));
                vo.setHo_telefono(rs.getString("Telefono"));
                vo.setHo_correo(rs.getString("Correo"));

                // Añadir el objeto a la lista
                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
} 
   
   
   
   
    
    
       public ArrayList<PdfVO> listarIdHon(String text, String fecha1, String fecha2) {
     String sql = "SELECT id_horonario, n.especialidad, CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, " +
                 "x.Usuario, Ho_fechaOne, Ho_fechaTwo, l.Estado, p.Telefono, p.Correo, Ho_estado, res_totalneto " +
                 "FROM honorarios u " +
                 "INNER JOIN table_especialidad n ON u.id_especialidad = n.id_especialidad " +
                 "INNER JOIN table_usuario x ON u.Ho_encargado = x.IdPersonal " +
                 "INNER JOIN table_personal p ON u.id_especialista = p.IdPersonal " +
                 "INNER JOIN table_estado l ON u.Ho_estado = l.IdEstado " +
                 "INNER JOIN honorario_resumen r ON u.id_horonario = r.id_honorario \n" +
                 "WHERE id_horonario LIKE ? AND Ho_fechaOne BETWEEN ? AND ? " +
                 "ORDER BY id_horonario ASC";


    ArrayList<PdfVO> list = new ArrayList<>();

    // Usar try-with-resources para manejar la conexión y los recursos automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, "%" + text + "%");
        ps.setString(2, fecha1);
        ps.setString(3, fecha2);
	
        // Ejecutar la consulta
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear y llenar el objeto PdfVO
                PdfVO vo = new PdfVO();
                vo.setId_hon(rs.getInt("id_horonario"));
                vo.setHon_especialista(rs.getString("especialista"));
                vo.setHon_especialidad(rs.getString("especialidad"));
                vo.setHon_encargado(rs.getString("Usuario"));
                
                vo.setNetoPagar(rs.getString("res_totalneto"));
                vo.setHon_fecha1(rs.getString("Ho_fechaOne"));
                vo.setHon_fecha2(rs.getString("Ho_fechaTwo"));
                vo.setHo_estado(rs.getString("Estado"));
                vo.setHo_telefono(rs.getString("Telefono"));
                vo.setHo_correo(rs.getString("Correo"));

                // Añadir el objeto a la lista
                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
} 
    
    
    
       
public ArrayList<PdfVO> listarFacturas(String fecha1, String fecha2) {
    String sql = "  SELECT \n" +
"    id_factura, \n" +
"    cod_fact, \n" +
"    x.Usuario, \n" +
"    c.Nombre, \n" +
"    c.Apellido, \n" +
"    c.Cedula, \n" +
"    c.Correo, \n" +
"    u.fecha_fact, \n" +
"    l.Estado,\n" +
"    p.moneda\n" +

"FROM \n" +
"table_facturacion u\n" +
"INNER JOIN \n" +
"table_paciente c ON u.id_paciente = c.Idpaciente\n" +
"INNER JOIN \n" +
"table_estado l ON u.estado_fact = l.IdEstado\n" +
"INNER JOIN \n" +
"table_usuario x ON u.id_usuario = x.IdPersonal\n" +
"INNER JOIN \n" +
"moneda_pago p ON u.metodo_pago = p.id_moneda\n" +

"WHERE u.fecha_fact BETWEEN ? AND ?\n" +

"ORDER BY \n" +
"    u.id_factura ASC;";

    ArrayList<PdfVO> list = new ArrayList<>();

    // Usar try-with-resources para manejar la conexión y los recursos automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer los parámetros de la consulta
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);

        // Ejecutar la consulta
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear y llenar el objeto PdfVO
                PdfVO vo = new PdfVO();
                
                vo.setId_factura(rs.getInt("id_factura"));
                vo.setFac_code(rs.getString("cod_fact"));
                vo.setFac_usuario(rs.getString("Usuario")); 
                vo.setFac_nombre(rs.getString("Nombre") + " " + rs.getString("Apellido"));  
                vo.setFac_ced(rs.getString("Cedula"));
                vo.setFac_correo(rs.getString("Correo"));
                vo.setFac_fecha(rs.getString("fecha_fact"));  
                vo.setFac_estado(rs.getString("Estado"));
                vo.setMoneda(rs.getString("moneda"));

                // Añadir el objeto a la lista
                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
}

       
       
       
public ArrayList<PdfVO> listarFacBy(String busqueda, String fecha1, String fecha2) {
    String sql = "SELECT \n" +
                 "  id_factura, \n" +
                 "  cod_fact, \n" +
                 "  x.Usuario, \n" +
                 "  c.Nombre, \n" +
                 "  c.Apellido, \n" +
                 "  c.Cedula, \n" +
                 "  c.Correo, \n" +
                 "  u.fecha_fact, \n" +
                 "  u.arch_fact, \n" +
                 "  l.Estado,\n" +
                 "  p.moneda\n" +
                 "FROM table_facturacion u\n" +
                 "INNER JOIN table_paciente c ON u.id_paciente = c.Idpaciente\n" +
                 "INNER JOIN table_estado l ON u.estado_fact = l.IdEstado\n" +
                 "INNER JOIN table_usuario x ON u.id_usuario = x.IdPersonal\n" +
                 "INNER JOIN moneda_pago p ON u.metodo_pago = p.id_moneda\n" +
                 "WHERE u.fecha_fact BETWEEN ? AND ?\n" +
                 "AND (CONCAT(c.Nombre, ' ', c.Apellido) LIKE ? OR c.Cedula LIKE ?)\n" +
                 "ORDER BY u.id_factura ASC";

    ArrayList<PdfVO> list = new ArrayList<>();

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        ps.setString(3,"%" + busqueda + "%");
        ps.setString(4,"%" + busqueda + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();

                vo.setId_factura(rs.getInt("id_factura"));
                vo.setFac_code(rs.getString("cod_fact"));
                vo.setFac_usuario(rs.getString("Usuario")); 
                vo.setFac_nombre(rs.getString("Nombre") + " " + rs.getString("Apellido"));  
                vo.setFac_ced(rs.getString("Cedula"));
                vo.setFac_correo(rs.getString("Correo"));
                vo.setFac_reporte(rs.getBytes("arch_fact"));
                vo.setFac_fecha(rs.getString("fecha_fact"));  
                vo.setFac_estado(rs.getString("Estado"));
                vo.setMoneda(rs.getString("moneda"));

                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
}
       
       
       public ArrayList<PdfVO> listarFacByCod(String busqueda, String fecha1, String fecha2) {
    String sql = "SELECT \n" +
                 "  id_factura, \n" +
                 "  cod_fact, \n" +
                 "  x.Usuario, \n" +
                 "  c.Nombre, \n" +
                 "  c.Apellido, \n" +
                 "  c.Cedula, \n" +
                 "  c.Correo, \n" +
                 "  u.fecha_fact, \n" +
                 "  u.arch_fact, \n" +
                 "  l.Estado,\n" +
                 "  p.moneda\n" +
                 "FROM table_facturacion u\n" +
                 "INNER JOIN table_paciente c ON u.id_paciente = c.Idpaciente\n" +
                 "INNER JOIN table_estado l ON u.estado_fact = l.IdEstado\n" +
                 "INNER JOIN table_usuario x ON u.id_usuario = x.IdPersonal\n" +
                 "INNER JOIN moneda_pago p ON u.metodo_pago = p.id_moneda\n" +
                 "WHERE u.fecha_fact BETWEEN ? AND ?\n" +
                 "AND cod_fact like ? \n" +
                 "ORDER BY u.id_factura ASC";

    ArrayList<PdfVO> list = new ArrayList<>();

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        ps.setString(3,"%" + busqueda + "%");


        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();

                vo.setId_factura(rs.getInt("id_factura"));
                vo.setFac_code(rs.getString("cod_fact"));
                vo.setFac_usuario(rs.getString("Usuario")); 
                vo.setFac_nombre(rs.getString("Nombre") + " " + rs.getString("Apellido"));  
                vo.setFac_ced(rs.getString("Cedula"));
                vo.setFac_correo(rs.getString("Correo"));
                vo.setFac_reporte(rs.getBytes("arch_fact"));
                vo.setFac_fecha(rs.getString("fecha_fact"));  
                vo.setFac_estado(rs.getString("Estado"));
                vo.setMoneda(rs.getString("moneda"));

                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
}
       
       
       
       
       
       
              public ArrayList<PdfVO> listarFacByState(String busqueda, String fecha1, String fecha2) {
    String sql = "SELECT \n" +
                 "  id_factura, \n" +
                 "  cod_fact, \n" +
                 "  x.Usuario, \n" +
                 "  c.Nombre, \n" +
                 "  c.Apellido, \n" +
                 "  c.Cedula, \n" +
                 "  c.Correo, \n" +
                 "  u.fecha_fact, \n" +
                 "  u.arch_fact, \n" +
                 "  l.Estado,\n" +
                 "  p.moneda\n" +
                 "FROM table_facturacion u\n" +
                 "INNER JOIN table_paciente c ON u.id_paciente = c.Idpaciente\n" +
                 "INNER JOIN table_estado l ON u.estado_fact = l.IdEstado\n" +
                 "INNER JOIN table_usuario x ON u.id_usuario = x.IdPersonal\n" +
                 "INNER JOIN moneda_pago p ON u.metodo_pago = p.id_moneda\n" +
                 "WHERE u.fecha_fact BETWEEN ? AND ?\n" +
                 "AND l.Estado = ? \n" +
                 "ORDER BY u.id_factura ASC";

    ArrayList<PdfVO> list = new ArrayList<>();

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        ps.setString(3, busqueda);


        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PdfVO vo = new PdfVO();

                vo.setId_factura(rs.getInt("id_factura"));
                vo.setFac_code(rs.getString("cod_fact"));
                vo.setFac_usuario(rs.getString("Usuario")); 
                vo.setFac_nombre(rs.getString("Nombre") + " " + rs.getString("Apellido"));  
                vo.setFac_ced(rs.getString("Cedula"));
                vo.setFac_correo(rs.getString("Correo"));
                vo.setFac_reporte(rs.getBytes("arch_fact"));
                vo.setFac_fecha(rs.getString("fecha_fact"));  
                vo.setFac_estado(rs.getString("Estado"));
                vo.setMoneda(rs.getString("moneda"));

                list.add(vo);
            }
        }

    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return list;
}
       
       
       
       
       
       
       
       
       
       
       
       
}

