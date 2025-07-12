package Clases;
import Clases.EnlaceBd;
import Clases.PdfVO;
import java.awt.Color;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



public class PdfDAO1 {

 Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

    /*Metodo listar*/
    
    
       String cedulapaciente;
    String usuario;
    String examen;


    
    
    public ArrayList<PdfVO> Listar_PdfVO() {
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
        
        ResultSet rs = null;
        PreparedStatement ps = null;
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
        }  finally {
        closeResources(rs, ps, con);
        }

        return list;
    }


    /*Metodo agregar*/
    public void Agregar_PdfVO(PdfVO vo) {
        
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs=null;

        String sql = "INSERT INTO table_laboratorio (codigopdf, id_examen, id_paciente, id_personal, FechaReporte, Reportepdf) VALUES(?, ?, ?,?,?,?);";
      
        try {
           
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, vo.getCodigopdf());
            ps.setInt(2, vo.getIdexamen());
            ps.setInt(3, vo.getIdpaciente());
            ps.setInt(4, vo.getIdusuario());
            ps.setString(5, vo.getFecha());
            ps.setBytes(6, vo.getArchivopdf());
            ps.executeUpdate();
            
            
            
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  finally {
        closeResources(rs, ps, con);
        }

    }




    
     public void ejecutar_archivoPDF(int id, String nombrepaciente) {
    EnlaceBd cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con =null;
    try {
        String sql="SELECT Reportepdf FROM table_laboratorio WHERE codigopdf = ?";
        cn = new EnlaceBd();
        con= cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            InputStream bos = new ByteArrayInputStream(rs.getBytes(1));

            try (OutputStream out = new FileOutputStream("Análisis.pdf")) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bos.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    } catch (IOException | SQLException ex) {
        System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR INTENTA; CORREGIR EL ESTUDIO O GENERARLO DE NUEVO", "CONSULTAS", JOptionPane.ERROR_MESSAGE);
    }  finally {
        closeResources(rs, ps, con);
        }

}

    
   public void simularClic(final JButton boton) {
    boton.setBackground(Color.GREEN); // Cambia el color de fondo al hacer clic
    boton.setEnabled(false); // Deshabilita el botón para evitar clics adicionales

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000); // Espera durante 1 segundo (1000 ms)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    boton.setBackground(UIManager.getColor("Button.background")); // Restaura el color de fondo predeterminado
                    boton.setEnabled(true); // Habilita el botón nuevamente
                }
            });
        }
    });
    thread.start();
}
    
       public void correo_archivoPDF(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;

        try {
            ps = cn.getConnection().prepareStatement("SELECT Reportepdf FROM table_laboratorio WHERE codigopdf = ?;");
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
        } finally {
        closeResources(rs, ps, con);
        }

    }

    
    
   
    
    
    
    
       public void ejecutar_archivoHistoria(int id, String nombrepaciente) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con=null;
        byte[] b = null;

        try {
            String sql = "SELECT  Archivo_Historia FROM table_historias WHERE Id_historias=?";
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

          //  OutputStream out = new FileOutputStream("Reporte #"+id+"_"+nombrepaciente+".pdf");
          OutputStream out = new FileOutputStream("Reporte.pdf");
          out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();


        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Esta historia aún no cuenta con un reporte asociado; debe asignarle un reporte para visualizar","HISTORIAS", JOptionPane.ERROR_MESSAGE);
        } finally {
        closeResources(rs, ps, con);
        }

    }
    
       
       
       
       
       
       
       
       
       
   public void ejecutar_archivoHonorario(int id, String nombrepaciente) {
    EnlaceBd cn = new EnlaceBd();
    String query = "SELECT Ho_informe FROM honorarios WHERE id_horonario=?";
    
    try (Connection connection = cn.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {
        
        ps.setInt(1, id);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                byte[] datosPDF = rs.getBytes("Ho_informe");
                
                if (datosPDF != null) {
                    String filePath = "Reporte_Honorario.pdf";
                    try (OutputStream out = new FileOutputStream(filePath)) {
                        out.write(datosPDF);
                    }
                    JOptionPane.showMessageDialog(null,"Abriendo reporte...","Reporte",1);
                    Desktop.getDesktop().open(new File("Reporte_Honorario.pdf"));
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Esta honorario aún no cuenta con un reporte asociado; debe asignarle un reporte para visualizar", 
                        "HONORARIO", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error en la consulta SQL: " + ex.getMessage());
    } catch (IOException ex) {
        System.out.println("Error al abrir archivo PDF: " + ex.getMessage());
    } finally {
        closeResources(rs, ps, con);
        }

}
       
       
       
       
       
         public void correo_archHonorario(int id) {

        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con=null;
        byte[] b = null;

        try {
           String sql="SELECT Ho_informe FROM honorarios WHERE id_horonario = ?";
           con =cn.getConnection();
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

            OutputStream out = new FileOutputStream("Honorario.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();


        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        } finally {
        closeResources(rs, ps, con);
        }

    }
       
       
       
         
         public void show_bill(int id) {
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = null;
    byte[] b = null;

    try {
        String sql = "SELECT arch_fact FROM table_facturacion WHERE id_factura = ?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            b = rs.getBytes("arch_fact");
        }

        if (b == null || b.length == 0) {
            throw new IOException("No se encontró el archivo.");
        }

        // Detectar tipo de archivo por los primeros bytes
        boolean esPDF = b[0] == 0x25 && b[1] == 0x50; // %P para PDF
        boolean esPNG = (b[0] & 0xFF) == 0x89 && b[1] == 0x50; // ‰P para PNG

        String extension;
        if (esPDF) {
            extension = ".pdf";
        } else if (esPNG) {
            extension = ".png";
        } else {
            throw new IllegalArgumentException("El archivo no es ni PDF ni PNG válido.");
        }

        String nombreArchivo = "Reporte_" + id + extension;

        // Guardar archivo
        try (OutputStream out = new FileOutputStream(nombreArchivo)) {
            out.write(b);
        }
        
        JOptionPane.showMessageDialog(null,"Abriendo reporte...","Reporte",1);
        // Abrir automáticamente (opcional)
        Desktop.getDesktop().open(new File(nombreArchivo));

    } catch (IOException | SQLException ex) {
        System.out.println("Error al abrir archivo: " + ex.getMessage());
        JOptionPane.showMessageDialog(null,
            "No cuenta con un reporte asociado; debe asignarle un reporte para visualizar",
            "REPORTES", JOptionPane.ERROR_MESSAGE);
    } finally {
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
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
}
         
       
    
}
