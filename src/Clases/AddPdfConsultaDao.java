/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author FCGI-ADMIN
 */
public class AddPdfConsultaDao {


    /*Metodo listar*/
    
    
    String cedulapaciente;
    String usuario;
    String examen;


    
    
    public ArrayList<PdfVO> Listar_PdfVO() {
        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
        ArrayList<PdfVO> list = new ArrayList<PdfVO>();
   
        String sql = "SELECT Codigopdf, Estudio, Cedula, Usuario, FechaReporte, Reportepdf \n" +
"FROM table_laboratorio u\n" +
"\n" +
"INNER JOIN table_estudios n\n" +
"ON u.Id_examen=n.IdEstudio\n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.id_paciente=c.Idpaciente\n" +
"\n" +
"INNER JOIN table_usuario x\n" +
"ON u.Id_personal=x.IdPersonal";
        

        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setCedulapaciente(rs.getString(3));
                vo.setUsuario(rs.getString(4));
                vo.setFecha(rs.getString(5));
                vo.setArchivopdf(rs.getBytes(6));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        closeResources(rs, ps, con);
    }
        return list;
    }


    
    
    
    /*Metodo agregar*/
    public void Agregar_PdfVO(AddPdfConsulta vo) {

        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
 
            String sql = "UPDATE table_historias  set   Archivo_Historia=? WHERE Id_historias=?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBytes(1, vo.getArchivo());
            ps.setInt(2, vo.getIdConsulta());
            ps.executeUpdate();
            //   int res = ps.executeUpdate();
/*
            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ALMACENAR PDF", "ERROR", JOptionPane.ERROR_MESSAGE);
            }*/

        } catch (Exception e) {
            System.out.println("error en sql "+e);
       //     JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }finally {
        closeResources(rs, ps, con);
    }
    }


    
       public void ejecutar_archivoPDF(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con=null;
        byte[] b = null;

        try {
            String sql="SELECT ReporteConsulta FROM table_asignar WHERE IdAsignar = ?";
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("consulta.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();


        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }finally {
        closeResources(rs, ps, con);
    }
    }

    
    
    
       public void correo_archivoPDF(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        byte[] b = null;

        try {
            String sql="SELECT Reportepdf FROM table_laboratorio WHERE codigopdf = ?";
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("correo.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();


        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }finally {
        closeResources(rs, ps, con);
    }
    }

       
       
       
       
        
       
       
       
       
          /*Metodo agregar*/
    public void Agregar_PdfVOHistorias(AddPdfConsulta vo) {
       
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        ResultSet rs=null;
        String sql = "INSERT INTO table_historias (Id_usuarioh, Id_pacienteh, Fecha_Historia, Archivo_Historia) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
  
            
            ps.setInt(1, vo.getId_usuarioh());
            ps.setInt(2, vo.getId_pacienteh());
            ps.setString(3, vo.getFecha_Historia());
            ps.setBytes(4, vo.getArchivoHistoria());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        closeResources(rs, ps, con);
    }
    }
       
       
       
       
       
       
       
       
         
       public void ejecutar_archivoPDFHistorias(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con=null;
        byte[] b = null;

        try {
        
            String sql="SELECT Archivo_Historia FROM table_historias WHERE Id_historias = ?";
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("ConsultaHistoria.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();

        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }finally {
        closeResources(rs, ps, con);
    }
    }

    
       
       
       
    /*Metodo agregar*/
    public void AgregarHistoriaPdfVO(AddPdfConsulta vo) {

        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
 
            String sql = "UPDATE table_historias  set   Archivo_Historia=?, Id_Estadoh=? WHERE Id_historias=? AND Id_pacienteh=?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBytes(1, vo.getArchivoHistoria());
            ps.setInt(2, vo.getId_Estadoh());
            ps.setInt(3, vo.getIdHistoria());
            ps.setInt(4, vo.getId_pacienteh());
            ps.executeUpdate();
            //   int res = ps.executeUpdate();
/*
            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ALMACENAR PDF", "ERROR", JOptionPane.ERROR_MESSAGE);
            }*/

        } catch (Exception e) {
            System.out.println(e);
       //     JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }finally {
        closeResources(rs, ps, con);
    }
    }
       
       
       
   
    
         

    public void agregarInformeS(AddPdfConsulta vo) {

        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
 
            String sql = "INSERT INTO `ocp_informe`(ocp_paciente,ocp_doctor, ocp_titulo ,  ocp_descrip, ocp_pdf, ocp_fecha, ocp_estado) VALUES (?,?,?,?,?,?,?)";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getIdPacienteS());
            ps.setInt(2, vo.getOcp_doctorInt());
            ps.setString(3, vo.getTituloSimple());
            ps.setString(4, vo.getOcpdescrip());
            ps.setBytes(5, vo.getArchivoSimple());
            ps.setString(6, vo.getOcpFecha());
            ps.setInt(7, vo.getEstadoSimple());
    
            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("error en agregarInformeS "+e);
       //     JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }finally {
        closeResources(rs, ps, con);
    }
    }
       
    
    
        
       public void selectPdfSimple(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con=null;
        byte[] b = null;

        try {
            String sql="SELECT ocp_pdf FROM ocp_informe WHERE Id_ocpI  = ?";
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("InformeSimple.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();


        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }finally {
        closeResources(rs, ps, con);
    }
    }
    
    
    
    
     public void updateInformeS(AddPdfConsulta vo) {

        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
 
            String sql = "UPDATE ocp_informe SET ocp_doctor=?,ocp_titulo=?,ocp_descrip=?,ocp_pdf=?,ocp_fecha=? WHERE Id_ocpI=?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getOcp_doctorInt());
            ps.setString(2, vo.getTituloSimple());
            ps.setString(3, vo.getOcpdescrip());
            ps.setBytes(4, vo.getArchivoSimple());
            ps.setString(5, vo.getOcpFecha());
            ps.setInt(6, vo.getIdSimple());
           
    
            ps.executeUpdate();
            //   int res = ps.executeUpdate();
/*
            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ALMACENAR PDF", "ERROR", JOptionPane.ERROR_MESSAGE);
            }*/

        } catch (Exception e) {
            System.out.println("error en updateInformeS "+e);
       //     JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }finally {
        closeResources(rs, ps, con);
    }
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
    } catch (SQLException ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
}
       
}
