/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCEspecialidadDao;
import Clases.JCEspecialidades;
import Clases.JCPacientes;
import Clases.JCProcedimientos;
import Clases.JCProcedimientosDao;
import Clases.JCambiarState;
import Clases.Temporal;
import Clases.Validar;
import Menu.Mprincipal;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JConfigEmpresas extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JConfigEmpresas() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
        
        informacion();

    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPopup = new javax.swing.JPopupMenu();
        Activar = new javax.swing.JMenuItem();
        Desactivar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        BtnAgg = new javax.swing.JButton();
        BtnLimpiar1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        txtRif = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtUbicacion = new javax.swing.JTextArea();
        txtTelefono = new javax.swing.JTextField();
        txtpiePagina = new javax.swing.JTextField();
        AUfoto = new javax.swing.JButton();
        lbfoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        Activar.setText("Activar");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Activar);

        Desactivar.setText("Desactivar");
        Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Desactivar);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/blur-hospital.jpg"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("C O N F I G U R A R    E M P R E S A S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 290, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 30));

        BtnAgg.setText("Actualizar");
        BtnAgg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgg.setContentAreaFilled(false);
        BtnAgg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAggActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAgg, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 100, 30));

        BtnLimpiar1.setText("Nuevo");
        BtnLimpiar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar1.setContentAreaFilled(false);
        BtnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiar1ActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 100, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 700, 20));

        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nombre empresa"));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 480, 50));

        txtRif.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "RIF"));
        jPanel2.add(txtRif, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 480, 50));

        txtUbicacion.setColumns(20);
        txtUbicacion.setLineWrap(true);
        txtUbicacion.setRows(5);
        txtUbicacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicacion"));
        jScrollPane2.setViewportView(txtUbicacion);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 480, 80));

        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Telefonos | Correos"));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 480, 50));

        txtpiePagina.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pie de pagina"));
        jPanel2.add(txtpiePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 480, 50));

        AUfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Img.png"))); // NOI18N
        AUfoto.setBorderPainted(false);
        AUfoto.setContentAreaFilled(false);
        AUfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AUfotoActionPerformed(evt);
            }
        });
        jPanel2.add(AUfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, 70, 30));

        lbfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(lbfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 200, 140));

        panelImage1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, 570));

        jPanel1.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 640));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    
    
    int idespecialidades =0;
    String EstadoTexto = "";
    
    private void BtnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiar1ActionPerformed
      limpiarCampos();
    }//GEN-LAST:event_BtnLimpiar1ActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Activar el procedimiento?",
            "ACTIVAR PROCEDIMIENTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

     

            if (EstadoTexto.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.InactivarEspecialidad(100,      idespecialidades )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CITA SE HA ACTUALIZADO HA : Activo", "Estado", 1);

                //AuditoriaCancelarCita();

            }
        

        }
    }//GEN-LAST:event_ActivarActionPerformed

    private void DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarActionPerformed
    
    }//GEN-LAST:event_DesactivarActionPerformed

    private void AUfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AUfotoActionPerformed

        try{
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("*.images", "JPEG", "jpg", "png");
            chooser.addChoosableFileFilter(fnef);
            int ans = chooser.showSaveDialog(null);

            //  if (ans == JFileChooser.APPROVE_OPTION && fila==-1) {
                //    File selectedPhoto = chooser.getSelectedFile();
                //  String path = selectedPhoto.getAbsolutePath();
                // lbfoto.setIcon(resetImageSize(path, null));
                //this.rutafoto = path;
                // AUregistrar.setEnabled(true);
                // }

            //else if (ans == JFileChooser.APPROVE_OPTION )
            //{
                File selectedPhoto = chooser.getSelectedFile();
                String path = selectedPhoto.getAbsolutePath();
                lbfoto.setIcon(resetImageSize(path, null));
                this.rutafoto = path;
                // }
            // else{}
            //System.out.println(rutafoto);

        }catch(Exception  e){System.out.println(e);}
    }//GEN-LAST:event_AUfotoActionPerformed

    private void BtnAggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAggActionPerformed
      
if(     txtNombre.getText().equals("")||
        txtRif.getText().equals("")||
        txtUbicacion.getText().equals("")||
        txtTelefono.getText().equals("")||
        txtpiePagina.getText().equals("")
        
        ){
JOptionPane.showMessageDialog(null, "DEBE COMPLETAR TODOS LOS CAMPOS", "CAMPOS", JOptionPane.ERROR_MESSAGE);
}
else{
actualizarEmpresa();
informacion();
searchImg();
}
        
    }//GEN-LAST:event_BtnAggActionPerformed

    
    
    
  public void actualizarEmpresa() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    EnlaceBd cn = new EnlaceBd();

    try {
        String sql;
        if (rutafoto != null && !rutafoto.isEmpty()) {
            // Si se proporcionó una nueva imagen
            sql = "UPDATE `tableinfopdfs` SET `nombrempresa`=?,`rif`=?,`ubicacion`=?,`telefonos`=?,`infopiepagina`=?,`Imglogo`=? WHERE `Id_Info`=?";
            InputStream fotoFirma = new FileInputStream(new File(rutafoto));
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtRif.getText());
            ps.setString(3, txtUbicacion.getText());
            ps.setString(4, txtTelefono.getText());
            ps.setString(5, txtpiePagina.getText());
            ps.setBlob(6, fotoFirma);
            ps.setInt(7, 1);
        } else {
            // Si no se proporcionó una nueva imagen
            sql = "UPDATE `tableinfopdfs` SET `nombrempresa`=?,`rif`=?,`ubicacion`=?,`telefonos`=?,`infopiepagina`=? WHERE `Id_Info`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtRif.getText());
            ps.setString(3, txtUbicacion.getText());
            ps.setString(4, txtTelefono.getText());
            ps.setString(5, txtpiePagina.getText());
            ps.setInt(6, 1);
        }

        int res = ps.executeUpdate();

        if (res >= 1) {
            JOptionPane.showMessageDialog(null, "Se ha actualizado la información de la empresa", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Fallo al actualizar la información de la empresa", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (FileNotFoundException e) {
        System.out.println("Archivo no encontrado: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }   finally {
            closeResources(rs, ps, con);
        }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      String rutafoto = "";

    public ImageIcon resetImageSize(String rutafoto, byte[] photo) {
        ImageIcon Ufoto = null;
        if (rutafoto != null) {
            Ufoto = new ImageIcon(rutafoto);
        } else {
            Ufoto = new ImageIcon(photo);
        }
        Image img = Ufoto.getImage();
        Image img1 = img.getScaledInstance(lbfoto.getWidth(), lbfoto.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon ph = new ImageIcon(img1);
        return ph;
    }

    
    
    

   

  
  
  
  
   public void AgregarEspecialidad(){                                             
    
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    

          try {

              
 
        
        
 
        
        
              
              
            String sql = "INSERT INTO table_especialidad (especialidad) VALUES (?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, txtNombre.getText());
         

            ps.execute();
  
            
   limpiarCampos();
  JOptionPane.showMessageDialog(null, "ESPECIALIDAD REGISTRADA","REGISTRO ESPECIALIDADES", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }


      
          
          
    } 
   
   
   
   
   
   
      public void ActualizarEspecialidad() {
   
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

        try {
          
            
            
            
            
            
 
            String sql = "update table_especialidad set   Especialidad=? where id_especialidad=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setInt(2, idespecialidades);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "ESPECIALIDAD ACTUALIZADA", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA ESPECIALIDAD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA ESPECIALIDAD", "ESPECIALIDADES", JOptionPane.ERROR_MESSAGE);
           }
                 
    }

     
     
   
   
public void searchImg() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    byte[] b = null;
    InputStream in = null;
    FileOutputStream out = null;

    try {
        ps = cn.getConnection().prepareStatement("SELECT `Imglogo` FROM `tableinfopdfs` WHERE Id_Info=?");
        ps.setInt(1, 1);
        rs = ps.executeQuery();

        if (rs.next()) {
            b = rs.getBytes(1);
        }

        // Convertir bytes a imagen
        in = new ByteArrayInputStream(b);
        javax.imageio.ImageIO.setUseCache(false); // Desactivar la caché para evitar problemas al cargar la imagen
        java.awt.image.BufferedImage image = ImageIO.read(in);

        // Guardar la imagen en formato PNG
        out = new FileOutputStream("C:\\Cyberia\\src\\imagenes\\Logo_System.png");
        ImageIO.write(image, "PNG", out);

        // Cerrar recursos
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }

    } catch (IOException | NumberFormatException | SQLException ex) {
        System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        JOptionPane.showMessageDialog(null, ex);
    }  finally {
            closeResources(rs, ps, con);
        }
}
    





   
   
    
  
  
  public void limpiarCampos(){

      
txtNombre.setText("");
txtRif.setText("");
txtUbicacion.setText("");
txtTelefono.setText("");
txtpiePagina.setText("");
lbfoto.setIcon(null);
  
  }
  

     
     
     
     
     
     
     
    
 
  
  
    
      
        public void informacion() {
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from tableinfopdfs";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Configuración de los otros campos si es necesario
                txtNombre.setText(rs.getString("nombrempresa"));
                txtRif.setText(rs.getString("rif"));
                txtUbicacion.setText(rs.getString("ubicacion"));
                txtTelefono.setText(rs.getString("telefonos"));
                txtpiePagina.setText(rs.getString("infopiepagina"));
                
                // Recuperar la imagen
                byte[] imgBytes = rs.getBytes("Imglogo");
                if (imgBytes != null) {
              
                    ImageIcon imageIcon = new ImageIcon(imgBytes);
                
                    lbfoto.setIcon(imageIcon);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al recuperar imagen: " + e);
        }  finally {
            closeResources(rs, ps, con);
        }
    }

      
      
      
      
  
    
    
    
  DefaultTableModel modelo = new DefaultTableModel();
  JCPacientes pacientes  = new JCPacientes();
  JCEspecialidadDao EspecialidadDao  = new  JCEspecialidadDao();


  JCambiarState setState = new JCambiarState();
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
    
     
    
        
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
    
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton AUfoto;
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAgg;
    private javax.swing.JButton BtnLimpiar1;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JPopupMenu MenuPopup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JLabel lbfoto;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRif;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtUbicacion;
    private javax.swing.JTextField txtpiePagina;
    // End of variables declaration//GEN-END:variables
}
