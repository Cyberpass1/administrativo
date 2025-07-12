/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.Temporal;
import Clases.Validar;
import Clases.labEstudiosBdDao;
import Menu.Mprincipal;
import Procesos.JLabexamenes.EstudioPersonalizado;
import Procesos.JLabexamenes.JCmv;
import Procesos.JLabexamenes.JCompleto;
import Procesos.JLabexamenes.JCreatinina;
import Procesos.JLabexamenes.JElectrolitos;
import Procesos.JLabexamenes.JEnzimatico;
import Procesos.JLabexamenes.JEpstein;
import Procesos.JLabexamenes.JFemenino;
import Procesos.JLabexamenes.JFerrecinetico;
import Procesos.JLabexamenes.JFrinogeno;
import Procesos.JLabexamenes.JFsh;
import Procesos.JLabexamenes.JFta;
import Procesos.JLabexamenes.JGrupoSanguineo;
import Procesos.JLabexamenes.JHelycobacter;
import Procesos.JLabexamenes.JHematologia;
import Procesos.JLabexamenes.JHemoglobinaGlicosiada;
import Procesos.JLabexamenes.JHepatitis;
import Procesos.JLabexamenes.JInmunologia;
import Procesos.JLabexamenes.JInsulinaBasal;
import Procesos.JLabexamenes.JMarcadores;
import Procesos.JLabexamenes.JPcr;
import Procesos.JLabexamenes.JPreoperatorio;
import Procesos.JLabexamenes.JPerfil20;
import Procesos.JLabexamenes.JPerfilrutina;
import Procesos.JLabexamenes.JPsa;
import Procesos.JLabexamenes.JPtt;
import Procesos.JLabexamenes.JQuimicaSanguinea;
import Procesos.JLabexamenes.JReferidos;
import Procesos.JLabexamenes.JRelacionesUrinarias;
import Procesos.JLabexamenes.JSangreOculta;
import Procesos.JLabexamenes.JSerologia;
import Procesos.JLabexamenes.JTiroidea;
import Procesos.JLabexamenes.JToxoplasma;
import Procesos.JLabexamenes.JUroanalisis;
import Procesos.JLabexamenes.JVIH;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;






import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


/**
 *
 * @author FCGinebraI
 */
public class JLaboratorio extends javax.swing.JInternalFrame {

    
    
    
    
    public JLaboratorio() {
       initComponents();
       informacionpdf();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
   
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(5);
     //  JLabelAviso.setHorizontalAlignment(SwingConstants.CENTER);
 
     
     
     String nivel = TM.getNivel();
     String especialidad= TM.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
     
     
     ActivarFirma.setVisible(false);
     DesactivarFirma.setVisible(false);
     VerificarFirma.setVisible(false);
     
     
     if(nivel.equals("Administrador") || especialidad.equals("Bioanalista")){
     ActivarFirma.setVisible(true);
     DesactivarFirma.setVisible(true);
     VerificarFirma.setVisible(true);
     }
      
      
   
      
     
 
     
    }  
    

    
  
    
    
   String  empresa, rif, ubicacion, telefonos, piepagina;
 public void informacionpdf() {
    String sql = "select * from tableinfopdfs";
    
    // Usamos try-with-resources para gestionar la conexión, PreparedStatement y ResultSet
    try (Connection con = new EnlaceBd().getConnection();  // Crear conexión
         PreparedStatement ps = con.prepareStatement(sql);  // Preparar la consulta
         ResultSet rs = ps.executeQuery()) {  // Ejecutar la consulta y obtener el ResultSet

        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }

    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
}
    
    
    
    
    
    
    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    public int Nlcdo;
    Temporal TM = new Temporal();
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        MEstudios = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ActivarFirma = new javax.swing.JButton();
        DesactivarFirma = new javax.swing.JButton();
        VerificarFirma = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        JTLaboratorio = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jPanel10 = new javax.swing.JPanel();
        JDExamenesLab = new javax.swing.JDesktopPane();
        jComboBox1 = new javax.swing.JComboBox<>();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1300, 30));

        MEstudios.setBackground(new java.awt.Color(255, 255, 255));
        MEstudios.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        MEstudios.setEnabled(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("LIMPIAR");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 90, 30));

        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("LIMPIAR");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 90, 30));

        jButton3.setText("PERFIL TIROIDEO");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 160, 30));

        jButton4.setText("PERFIL HEPÁTICO");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 160, 30));

        jButton8.setText("PERFIL 20");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, 30));

        jButton10.setText("PERFIL FERROCINÉTICO");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 160, 30));

        jButton12.setText("PERFIL INMUNULÓGICO");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton12.setContentAreaFilled(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 160, 30));

        jButton2.setText("PERFIL FEMENINO");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 160, 30));

        jButton5.setText("PERFIL ENZIMATICO");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 160, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        jButton18.setText("PERFIL PREOPERATORIO");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton18.setContentAreaFilled(false);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 160, 30));

        jButton19.setText("PERFIL DE RUTINA");
        jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton19.setContentAreaFilled(false);
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 160, 30));

        MEstudios.addTab("1", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setText("MARCADORES TUMORALES");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 180, 30));

        jButton14.setText("FACTORES DE COMPLEMENTO");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton14.setContentAreaFilled(false);
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 180, 30));

        jButton15.setText("HORMONALES");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 180, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        jButton11.setText("PRUEBAS ESPECIALES");
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton11.setContentAreaFilled(false);
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 180, 30));

        MEstudios.addTab("2", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton16.setText("CULTIVOS DE SECRECIONES");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, 30));

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton17.setText("UROANÁLISIS & COPROANÁLISIS");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton17.setContentAreaFilled(false);
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 180, 30));

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton21.setText("CULTIVO DE ESPUTO");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton21.setContentAreaFilled(false);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 180, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        MEstudios.addTab("3", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un estudio", "Quimica Sanguinea", "Hematología Completa", "PT, PTT Y VSG", "PCR", "Serologia", "VIH", "Grupo sanguineo", "Electrolitos", "Estudios Referidos", "Relaciones Urinarias", "Insulina Basal y Post Pandrial", "Toxoplasma", "PSA", "Helycobacter", "Hemoglobina Glicosilada", "FSH y LH", "CMV", "EPstein barr", "FTA", "Fibrinogeno", "Sangre Oculta en Heces", "Depuracion de Creatinina" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jPanel8.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        MEstudios.addTab("4", jPanel8);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        MEstudios.addTab("5", jPanel3);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ActivarFirma.setText("Activar Firma");
        ActivarFirma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ActivarFirma.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ActivarFirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarFirmaActionPerformed(evt);
            }
        });
        jPanel11.add(ActivarFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 30));

        DesactivarFirma.setText("Desactivar Firma");
        DesactivarFirma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DesactivarFirma.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DesactivarFirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarFirmaActionPerformed(evt);
            }
        });
        jPanel11.add(DesactivarFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, 30));

        VerificarFirma.setText("Verficar Firma");
        VerificarFirma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VerificarFirma.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        VerificarFirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerificarFirmaActionPerformed(evt);
            }
        });
        jPanel11.add(VerificarFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 160, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel11.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 130, 120));

        MEstudios.addTab("6", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton20.setText("PERFIL DE RUTINA");
        jButton20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton20.setContentAreaFilled(false);
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 30));

        jButton9.setText("SEROLOGÍA");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 160, 30));

        jButton22.setText("PRUEBAS ESPECIALES");
        jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton22.setContentAreaFilled(false);
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 30));

        jButton23.setText("PERFIL PREOPERATORIO");
        jButton23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton23.setContentAreaFilled(false);
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 160, 30));

        jButton25.setText("PERFIL 20");
        jButton25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton25.setContentAreaFilled(false);
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 160, 30));

        jButton26.setText("ELECTROLITOS");
        jButton26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton26.setContentAreaFilled(false);
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 160, 30));

        jButton27.setText("HEMATOLOGIA COMPLETA");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton27.setContentAreaFilled(false);
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 160, 30));

        jButton28.setText("QUIMICA SANGUINEA");
        jButton28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton28.setContentAreaFilled(false);
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 160, 30));

        jButton29.setText("ESTUDIOS REFERIDOS");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton29.setContentAreaFilled(false);
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 160, 30));

        jButton30.setText("PT, PTT Y VSG");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton30.setContentAreaFilled(false);
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 160, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoPag.png"))); // NOI18N
        jPanel12.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 130, 100));

        jButton31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton31.setText("UROANÁLISIS & COPROANÁLISIS");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton31.setContentAreaFilled(false);
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 160, 30));

        jButton33.setText("GRUPO SANGUINEO");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton33.setContentAreaFilled(false);
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 160, 30));

        MEstudios.addTab("7", jPanel12);

        jPanel2.add(MEstudios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 200, 640));

        JTLaboratorio.setBackground(new java.awt.Color(255, 255, 255));
        JTLaboratorio.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        JTLaboratorio.setEnabled(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setBackground(new java.awt.Color(255, 255, 255));
        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LabInicio.jpg"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 630));

        JTLaboratorio.addTab("INICIO", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JDExamenesLab.setMinimumSize(new java.awt.Dimension(1050, 600));
        JDExamenesLab.setName(""); // NOI18N
        jPanel10.add(JDExamenesLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 630));

        JTLaboratorio.addTab("tab2", jPanel10);

        jPanel2.add(JTLaboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 1060, 660));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Más utilizados", "Perfil", "Especial", "Bacteoriología", "Examen personalizado", "Otros" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JTiroidea Ir = new    JTiroidea();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 
        //  String restringir= Dnombre.getText();
        // Ir.Text.setText(restringir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JHepatitis Ir = new    JHepatitis();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 
        //  String restringir= Dnombre.getText();
        // Ir.Text.setText(restringir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);     
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 
        JTLaboratorio.setSelectedIndex(1);
        JPerfil20 Ir = new    JPerfil20();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 
        //  String restringir= Dnombre.getText();
        // Ir.Text.setText(restringir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);            
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()!=-1){
        Seleccion();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JFemenino Ir = new    JFemenino();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
       if(jComboBox2.getSelectedIndex()!=-1){
      
       JTLaboratorio.setSelectedIndex(1);    
       ComboboxOtros();
     
       }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       
        JTLaboratorio.setSelectedIndex(1);
        JUroanalisis Ir = new   JUroanalisis();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JFerrecinetico Ir = new    JFerrecinetico();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JMarcadores Ir = new   JMarcadores();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 
    
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JEnzimatico Ir = new JEnzimatico();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
 
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
         
          
       
              
        JTLaboratorio.setSelectedIndex(1);
        JCompleto Ir = new JCompleto();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
           
    
          
          
          
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
               JOptionPane.showMessageDialog(null, "FALTA INFORMACIÓN PARA COMPLETAR ESTE APARTADO", "MÓDULO INCOMPLETO", 1);   
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
             JOptionPane.showMessageDialog(null, "FALTA INFORMACIÓN PARA COMPLETAR ESTE APARTADO", "MÓDULO INCOMPLETO", 1);   
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
               JOptionPane.showMessageDialog(null, "FALTA INFORMACIÓN PARA COMPLETAR ESTE APARTADO", "MÓDULO INCOMPLETO", 1);   
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
          JOptionPane.showMessageDialog(null, "FALTA INFORMACIÓN PARA COMPLETAR ESTE APARTADO", "MÓDULO INCOMPLETO", 1);   
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
             JTLaboratorio.setSelectedIndex(1);
        JInmunologia Ir = new JInmunologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    
    
    private void DesactivarFirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarFirmaActionPerformed
    
         String Nivel=Tempo.getNivel();
         String especialidad= Tempo.getEspecialidad();
         String nivelUsuario=Nivel+" "+especialidad;
      

       if(Nivel.equals("Administrador") || especialidad.equals("Bioanalista")){
       DesactivarFirma();
       AuditoriDesactivarFirma();
       }
       else{JOptionPane.showMessageDialog(null, "Tu nivel de acceso no tiene permitido realizar esta accion", "PERMISOS", JOptionPane.ERROR_MESSAGE);}
   
       
       
       
    }//GEN-LAST:event_DesactivarFirmaActionPerformed

    private void ActivarFirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarFirmaActionPerformed
 String Nivel = Tempo.getNivel();
String especialidad = Tempo.getEspecialidad();
String nivelUsuario = Nivel + " " + especialidad;

if (Nivel.equals("Administrador") || especialidad.equals("Bioanalista")) {
    int idfirma = TM.getTexto();
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    ResultSet rs = null;
    byte[] b = null;

    try (Connection con = EnlaceBd.getConnection()){
        String sql = "SELECT Firma FROM table_personal WHERE IdPersonal = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idfirma);
        rs = ps.executeQuery();

        if (rs.next()) {
            b = rs.getBytes(1);

            if (b != null) {
                ActivarFirma();
                AuditoriActivarFirma();
            } else {
                JOptionPane.showMessageDialog(this, "AUN NO TE HAN ASIGNADO UNA FIRMA A TU USUARIO", "FIRMA", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (Exception e) {
        System.out.println("error en activar firma" + e);
    } finally {
        closeResources(rs, ps, null);
    }

} else {
    JOptionPane.showMessageDialog(null, "Tu nivel de acceso no tiene permitido realizar esta accion", "PERMISOS", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_ActivarFirmaActionPerformed

    private void VerificarFirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerificarFirmaActionPerformed

        labDao.ConsultarFirma(); 
        pdf();
    }//GEN-LAST:event_VerificarFirmaActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        
        
        JTLaboratorio.setSelectedIndex(1);
        JPreoperatorio Ir = new JPreoperatorio();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       // JOptionPane.showMessageDialog(this, "EN CONSTRUCCION", "PERFIL DE RUTINA", 1);
        
        JTLaboratorio.setSelectedIndex(1);
        JPerfilrutina Ir = new JPerfilrutina();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JPerfilrutina Ir = new JPerfilrutina();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      
        JTLaboratorio.setSelectedIndex(1);
        JSerologia Ir = new    JSerologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
               
        JTLaboratorio.setSelectedIndex(1);
        JCompleto Ir = new JCompleto();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
           
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JPreoperatorio Ir = new JPreoperatorio();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
   
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JPerfil20 Ir = new    JPerfil20();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 
        //  String restringir= Dnombre.getText();
        // Ir.Text.setText(restringir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);     
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JElectrolitos Ir = new    JElectrolitos();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true); 
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
     JTLaboratorio.setSelectedIndex(1);
        JHematologia Ir = new    JHematologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JQuimicaSanguinea Ir = new    JQuimicaSanguinea();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JReferidos Ir = new    JReferidos();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
      JTLaboratorio.setSelectedIndex(1);
        JPtt Ir = new    JPtt();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true); 
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        JTLaboratorio.setSelectedIndex(1);
        JUroanalisis Ir = new   JUroanalisis();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
           JTLaboratorio.setSelectedIndex(1);
        JGrupoSanguineo Ir = new    JGrupoSanguineo();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
    }//GEN-LAST:event_jButton33ActionPerformed
   
    
     public void pdf() {
      try {
       
          
          
          
                   
        /*-------------------------------------------------------------------------------------*/

   
          
          
          
          
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");

    
        
          
          
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
          

            FileOutputStream archivo;
            //String rut = System.getProperty("user.home");
            File file = new File("C://Fundaginebra//Reportes//testfirma.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.open();
       
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(Menu.FechaAdmin.getDate());
            String Hora = Menu.Time.getText()+" "+Menu.jLabel102.getText();
            //fecha.add( "  Fecha: " + Fecha + "\n" +"  Hora: "+ Hora ) ;

            
            
            PdfPTable Encabezado = new PdfPTable(1);
            Encabezado.setWidthPercentage(25);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{50f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
           // String razon =  "Fundación Convenio de Ginbra I";
           // String riff =   "J-8188418-8";
           // String tlf =    "0000-000000";
           // String dir =    "Centro";
            Encabezado.addCell(header);
            doc.add(Encabezado);
          
            
            
            
            

            PdfContentByte CB = writer.getDirectContent();  
            BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
  
            CB.beginText();
            CB.setFontAndSize(BF2, 12);
            CB.setTextMatrix(200, 800);
            CB.showText(empresa);
            CB.setFontAndSize(BF, 10);
            CB.setTextMatrix(260, 790);
            CB.showText(rif);
            CB.setFontAndSize(BF2, 10);
            CB.setTextMatrix(460, 780);
            CB.showText("FECHA: "+ Fecha);
            
            CB.setFontAndSize(BF2, 10);
            CB.setTextMatrix(460, 770);
            CB.showText("HORA: "+ Hora);
            
            CB.setFontAndSize(BF2, 8);
            CB.setTextMatrix(235, 740);
            CB.showText(ubicacion);
          
            CB.setFontAndSize(BF2, 8);
            CB.setTextMatrix(210, 730);
            CB.showText(telefonos); 
            CB.setTextMatrix(168, 725);
            CB.showText("__________________________________________________________________");
          
            CB.endText();

            
      
       //BODY 
       
       
       
            PdfPTable Tabla = new PdfPTable(3); 
            Tabla.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 1f };
            Tabla.setWidths(medidaCeldas);
            Tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("N° DE ORDEN: ");
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna1);
     
                
            Paragraph tcolumna2 = new Paragraph("ESTO ES UN FORMATO DE PRUEBA");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(12);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna2);

            Paragraph tcolumna3 = new Paragraph("N° DE PACIENTE: " );
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(8);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna3);
        

              
              
            PdfPTable Tabla0 = new PdfPTable(4); 
            // Tabla0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla0.setWidthPercentage(100);  
            float[] medidaCeldas0 = {3f, 2f, 1f,2f};
            Tabla0.setWidths(medidaCeldas0);
            Tabla0.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t0columna1 = new Paragraph("PACIENTE");
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(8);        
            t0columna1.setFont(Letra);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA");
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(8);        
            t0columna2.setFont(Letra);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);

            Paragraph t0columna3 = new Paragraph("EDAD");
            t0columna3.getFont().setStyle(Font.BOLD);
            t0columna3.getFont().setSize(8);        
            t0columna3.setFont(Letra);
            t0columna3.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna3);
        
            Paragraph t0columna4 = new Paragraph("SEXO");
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(8);        
            t0columna4.setFont(Letra);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
        
            
            Tabla0.addCell(new Paragraph("",FontFactory.getFont("Arial",8,Font.NORMAL))); 
            Tabla0.addCell(new Paragraph("",FontFactory.getFont("Arial",8,Font.NORMAL))); 
            Tabla0.addCell(new Paragraph("",FontFactory.getFont("Arial",8,Font.NORMAL))); 
            Tabla0.addCell(new Paragraph("",FontFactory.getFont("Arial",8,Font.NORMAL))); 

            
            PdfPTable Tabla1 = new PdfPTable(1); 
            Tabla1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla1.setWidthPercentage(100);  
            float[] medidaCeldas1 = {5f};
            Tabla1.setWidths(medidaCeldas1);
            Tabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t1columna1 = new Paragraph("----- FORMATO DE PRUEBA PARA VISUALZAR FIRMA---- ");
            t1columna1.getFont().setStyle(Font.BOLD);
            t1columna1.getFont().setSize(10);        
            t1columna1.setFont(Letra);
            t1columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla1.addCell(t1columna1);
     

            
            
            PdfPTable Tabla2 = new PdfPTable(4); 
            Tabla2.setWidthPercentage(100);  
            Tabla2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] medidaCeldas2 = {4f, 2f, 2f,4f};
            Tabla2.setWidths(medidaCeldas2);
            Tabla2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t2columna1 = new Paragraph("ANALISIS REALIZADO");
            t2columna1.getFont().setStyle(Font.BOLD);
            t2columna1.getFont().setSize(9);        
            t2columna1.setFont(Letra);
            t2columna1.setAlignment(Element.ALIGN_CENTER);
            Tabla2.addCell(t2columna1);
     
                
            Paragraph t2columna2 = new Paragraph("RESULTADOS");
            t2columna2.getFont().setStyle(Font.BOLD);
            t2columna2.getFont().setSize(9);        
            t2columna2.setFont(Letra);
            t2columna2.setAlignment(Element.ALIGN_CENTER);
            Tabla2.addCell(t2columna2);

            Paragraph t2columna3 = new Paragraph("UNIDAD");
            t2columna3.getFont().setStyle(Font.BOLD);
            t2columna3.getFont().setSize(10);        
            t2columna3.setFont(Letra);
            t2columna3.setAlignment(Element.ALIGN_CENTER);
            Tabla2.addCell(t2columna3);

            Paragraph t2columna4 = new Paragraph("REFERENCIA");
            t2columna4.getFont().setStyle(Font.BOLD);
            t2columna4.getFont().setSize(9);        
            t2columna4.setFont(Letra);
            t2columna4.setAlignment(Element.ALIGN_CENTER);
            Tabla2.addCell(t2columna4);
            
            
            
           Tabla2.addCell(new Paragraph("Hormonas Foliculo Estimulante (FSH)",FontFactory.getFont("Arial",9,Font.NORMAL))); 
           Tabla2.addCell(new Paragraph("0",FontFactory.getFont("Arial",9,Font.NORMAL)));
           Tabla2.addCell(new Paragraph("mUI/m",FontFactory.getFont("Arial",9,Font.NORMAL))); 
           Tabla2.addCell(new Paragraph("Fase Folicular: "+"0" +"\n Fase Lutea: "+ "0"+"\n Mitad de Ciclo: "+ "0"+"\n Postmenopausia: "+ "0"+"\n Hombres: "+ "0" +"\n \n \n",FontFactory.getFont("Arial",9,Font.NORMAL))); 
            
           Tabla2.addCell(new Paragraph("Hormona Luteínizante (LH)",FontFactory.getFont("Arial",9,Font.NORMAL))); 
           Tabla2.addCell(new Paragraph("0",FontFactory.getFont("Arial",9,Font.NORMAL)));
           Tabla2.addCell(new Paragraph("pg/ml",FontFactory.getFont("Arial",9,Font.NORMAL))); 
           Tabla2.addCell(new Paragraph("Fase Folicular: "+"0" +"\n Fase Lutea: "+ "0"+"\n Mitad de Ciclo: "+ "0"+"\n Postmenopausia: "+ "0" +"\n Hombres: "+ "0"+"\n \n \n",FontFactory.getFont("Arial",9,Font.NORMAL))); 
            

              
          
            PdfPTable Tablafirma = new PdfPTable(3); 
            Tablafirma.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Tablafirma.setWidthPercentage(100);  
            float[] medidaCeldasP = {3f, 1f, 3f};
            Tablafirma.setWidths(medidaCeldasP);
            Tablafirma.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tablafirma.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tPcolumna1 = new Paragraph("\n\n\n\n "+ piepagina);
            tPcolumna1.getFont().setStyle(Font.BOLD);
            tPcolumna1.getFont().setSize(8);        
            tPcolumna1.setFont(Letra);
            tPcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tablafirma.addCell(tPcolumna1);

                
            com.itextpdf.text.Image FirmaLcdo = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\imagen.bin");
            FirmaLcdo.setAlignment(Chunk.ALIGN_CENTER);
            Tablafirma.addCell(FirmaLcdo);

            Paragraph tPcolumna3 = new Paragraph("\n\n\n\n" + writer.getPageNumber()+ " Pág");
            tPcolumna3.getFont().setStyle(Font.BOLD);
            tPcolumna3.getFont().setSize(8);        
            tPcolumna3.setFont(Letra);
            tPcolumna3.setAlignment(Element.ALIGN_CENTER);
            Tablafirma.addCell(tPcolumna3);
           


           knowaboutlcdo(); 
           PdfPTable TablafirmaLcdo = new PdfPTable(1); 
           TablafirmaLcdo.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
           TablafirmaLcdo.setWidthPercentage(100);  
           float[] medidaCeldasFirma = {20f};
           TablafirmaLcdo.setWidths(medidaCeldasFirma);
           TablafirmaLcdo.setHorizontalAlignment(Element.ALIGN_CENTER);
           TablafirmaLcdo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
           Paragraph tPcolumnaFirma1 = new Paragraph("________________ \n "+NameOfLcdo);
           tPcolumnaFirma1.getFont().setStyle(Font.BOLD);
           tPcolumnaFirma1.getFont().setSize(8);        
           tPcolumnaFirma1.setFont(Letra);
           tPcolumnaFirma1.setAlignment(Element.ALIGN_CENTER);
           TablafirmaLcdo.addCell(tPcolumnaFirma1);

           
           
           
           
     
           
            saltolinea.add("\n");
            doc.add(saltolinea); 
            doc.add(Tabla);
            
            doc.add(Tabla0);
            doc.add(saltolinea);
           
        
            doc.add(Tabla1);
            doc.add(saltolinea);
            
            doc.add(Tabla2);
            
        
            doc.add(Tablafirma);
            doc.add(TablafirmaLcdo);
         
           //FOOTER
           //   addFooter(writer);
            
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
      
    

    String NameOfLcdo, Consultanlcdo;
     
     public void knowaboutlcdo(){
     
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs; 
         
         
         try{
         
       String query="Select FirmaLcdo from tableinfo where idinfo=? ";
       
       con = EnlaceBd.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, 1);
       rs=ps.executeQuery();
         
        if(rs.next()){
       Consultanlcdo= rs.getString("FirmaLcdo");
      
       if(!Consultanlcdo.equals("")){NameOfLcdo="Lcdo/a "+ Consultanlcdo;}
       else{NameOfLcdo="";}
       
       
         }

         }catch(Exception e){ System.out.println(e);}
     }

    

    
        public void Seleccion(){
            
                    if(jComboBox1.getSelectedItem().equals("Seleccione una opción") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(5);
           }
            
            else   if(jComboBox1.getSelectedItem().equals("Más utilizados") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(6);
           }           
            
        else   if(jComboBox1.getSelectedItem().equals("Perfil") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(0);
           }
           
           
     
         else     if(jComboBox1.getSelectedItem().equals("Especial") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(1);
           }
     
           else      if(jComboBox1.getSelectedItem().equals("Bacteoriología") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(2);
           }
     
           
        
             
         else        if(jComboBox1.getSelectedItem().equals("Otros") )
           {
     JTLaboratorio.setSelectedIndex(0);
     MEstudios.setSelectedIndex(3);
     jComboBox2.setSelectedItem("Selecciona un estudio");
           }
     
           
           
           
           else        if(jComboBox1.getSelectedItem().equals("Examen personalizado") )
           {
               
       JTLaboratorio.setSelectedIndex(1);
       MEstudios.setSelectedIndex(4);
       EstudioPersonalizado Ir = new   EstudioPersonalizado();
       JDExamenesLab.setVisible(true);
       JDExamenesLab.add(Ir);

 
 
  
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
           }
     
           
           
                 
                    
                  else        if(jComboBox1.getSelectedItem().equals("Hematología Completa") )
           {
               
                      
                    
        JTLaboratorio.setSelectedIndex(1);
        JHematologia Ir = new   JHematologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);

 
 

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);  
                    
           }    
                    
                    
                    
                    
     
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
           
        }
    
    
    
     public void Combobox() {
   
         
           if(jComboBox1.getSelectedItem().equals("Selecciona un estudio") )
           {
           JTLaboratorio.setSelectedIndex(0);
           
           
           
           
           }
     
           else if(jComboBox1.getSelectedItem().equals("Laboratorio") )
           { 
               
               
        JTLaboratorio.setSelectedIndex(1);


        JLaboratorio Ir = new JLaboratorio();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
    
   
               
               
               
      
           }
           
    }
        
     
     public void ComboboxOtros() {
   
         
           if(jComboBox2.getSelectedItem().equals("Selecciona un estudio") )
           {
           JTLaboratorio.setSelectedIndex(0);
           
           
           
           
           }
    
             
             
                else if(jComboBox2.getSelectedItem().equals("Quimica Sanguinea") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JQuimicaSanguinea Ir = new    JQuimicaSanguinea();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           else if(jComboBox2.getSelectedItem().equals("PT, PTT Y VSG") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JPtt Ir = new    JPtt();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
                else if(jComboBox2.getSelectedItem().equals("Serologia") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JSerologia Ir = new    JSerologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
              else if(jComboBox2.getSelectedItem().equals("VIH") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JVIH Ir = new    JVIH();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
          else if(jComboBox2.getSelectedItem().equals("Grupo sanguineo") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JGrupoSanguineo Ir = new    JGrupoSanguineo();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
           
        else if(jComboBox2.getSelectedItem().equals("Electrolitos") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JElectrolitos Ir = new    JElectrolitos();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
         else if(jComboBox2.getSelectedItem().equals("Insulina Basal y Post Pandrial") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JInsulinaBasal Ir = new    JInsulinaBasal();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
        else if(jComboBox2.getSelectedItem().equals("Toxoplasma") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JToxoplasma Ir = new    JToxoplasma();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
      else if(jComboBox2.getSelectedItem().equals("Helycobacter") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JHelycobacter Ir = new    JHelycobacter();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
               
               
           
       else if(jComboBox2.getSelectedItem().equals("PSA") )
           { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JPsa Ir = new     JPsa();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
          
           
      else if(jComboBox2.getSelectedItem().equals("Hemoglobina Glicosilada") ) { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JHemoglobinaGlicosiada Ir = new      JHemoglobinaGlicosiada();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
                         
                         
                         
       else if(jComboBox2.getSelectedItem().equals("FSH y LH") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JFsh Ir = new      JFsh();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
                        
         else if(jComboBox2.getSelectedItem().equals("CMV") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JCmv Ir = new      JCmv();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
                      
           else if(jComboBox2.getSelectedItem().equals("EPstein barr") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JEpstein Ir = new      JEpstein();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
           
                         
        else if(jComboBox2.getSelectedItem().equals("FTA") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JFta Ir = new      JFta();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
        else if(jComboBox2.getSelectedItem().equals("Relaciones Urinarias") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JRelacionesUrinarias Ir = new      JRelacionesUrinarias();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
           
         
       else if(jComboBox2.getSelectedItem().equals("Fibrinogeno") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JFrinogeno Ir = new    JFrinogeno();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
           
           
         else if(jComboBox2.getSelectedItem().equals("Hematología Completa") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JHematologia Ir = new    JHematologia();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           } 
           
           
        else if(jComboBox2.getSelectedItem().equals("PCR") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JPcr Ir = new    JPcr();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }
            
        
        else if(jComboBox2.getSelectedItem().equals("Sangre Oculta en Heces") ){ 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JSangreOculta Ir = new    JSangreOculta();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }  
           
           
        else if(jComboBox2.getSelectedItem().equals("Estudios Referidos") ) { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JReferidos Ir = new    JReferidos();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }  
           
           
              
        else if(jComboBox2.getSelectedItem().equals("Depuracion de Creatinina") ) { 
               
 
        JTLaboratorio.setSelectedIndex(1);
        JCreatinina Ir = new    JCreatinina();
        JDExamenesLab.setVisible(true);
        JDExamenesLab.add(Ir);


        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) { System.out.println(e);
        }
        Ir.toFront();
        Ir.setVisible(true);  
      
           }   
           
           
           
         
    }

     
    
     
     
     
     String cbalcdo, mppslcdo;
   public void ActivarFirma() {
    Connection con = null;
    String nameof = TM.getUser();
    int idfirma = TM.getTexto();
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    byte[] b = null;
    String cbalcdo = null, mppslcdo = null;

    try {
        // Primer bloque de conexión y lectura de la firma
        con = cn.getConnection();
        ps = con.prepareStatement("SELECT Firma, CMA, MPPS FROM table_personal WHERE IdPersonal = ?");
        ps.setInt(1, idfirma);
        rs = ps.executeQuery();

        while (rs.next()) {
            b = rs.getBytes(1);
            cbalcdo = rs.getString(2);
            mppslcdo = rs.getString(3);
        }

        // Escribir la firma en un archivo binario
        try (InputStream bos = new ByteArrayInputStream(b);
             OutputStream out = new FileOutputStream("imagen.bin")) {

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);
            out.write(datosPDF);
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo binario: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, "Estimado: " + nameof + " su firma ha sido activada", "ACTIVAR FIRMA", 1);

    } catch (SQLException ex) {
        System.out.println("Error al consultar la base de datos: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
    } finally {
        closeResources(rs, ps, con);
    }

    // Actualización de la firma en la base de datos
    String NombreFirma = TM.getNombre() + "\n C.B.A: " + cbalcdo + " M.P.P.S: " + mppslcdo;
    String rutaArchivo = "C:\\Fundaginebra\\dist\\imagen.bin";
    
    try (InputStream fotoFirma = new FileInputStream(new File(rutaArchivo))) {

        String sql = "UPDATE tableinfo SET FirmaLcdo=?, img=? WHERE Idinfo=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, NombreFirma);
        ps.setBlob(2, fotoFirma);
        ps.setInt(3, 1);
        ps.executeUpdate();

    } catch (SQLException | IOException e) {
        System.out.println("Error al actualizar la base de datos o leer el archivo: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos o leer el archivo: " + e.getMessage());
    } finally {
        closeResources(rs, ps, con);
    }
}
     
     
public void DesactivarFirma() {
    String nameof = TM.getUser();
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    byte[] b = null;

    try {
        // Primer bloque para obtener la imagen blanca
        String sql = "SELECT imgwhite FROM tableinfo WHERE Idinfo = ?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, 1);
        rs = ps.executeQuery();

        while (rs.next()) {
            b = rs.getBytes(1);
        }

        // Escribir la imagen blanca en un archivo binario
        try (InputStream bos = new ByteArrayInputStream(b);
             OutputStream out = new FileOutputStream("imagen.bin")) {

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);
            out.write(datosPDF);
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo binario: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, "Estimado: " + nameof + " su firma ha sido desactivada", "DESACTIVAR FIRMA", 1);

    } catch (SQLException ex) {
        System.out.println("Error al consultar la base de datos: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
    } finally {
        closeResources(rs, ps, con);
    }

    // Actualización para desactivar la firma
    String NombreFirma = "";
    String rutaArchivo = "C:\\Fundaginebra\\dist\\imagen.bin";

    try (InputStream fotoFirma = new FileInputStream(new File(rutaArchivo))) {
        String sql = "UPDATE tableinfo SET FirmaLcdo=?, img=? WHERE Idinfo=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, NombreFirma);
        ps.setBlob(2, fotoFirma);
        ps.setInt(3, 1);
        ps.executeUpdate();

    } catch (SQLException | IOException e) {
        System.out.println("Error al actualizar la base de datos o leer el archivo: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos o leer el archivo: " + e.getMessage());
    } finally {
        closeResources(rs, ps, con);
    }
}
     
     
     
     
     
      public void AuditoriActivarFirma(){
            
   int idfirma=TM.getTexto();       
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion=  "HORA: "+Menu.Time.getText()+" "+" Activo su firma en el modulo laboratorio" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idfirma);
            ps.setInt(2, idfirma);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
           closeResources(rs, ps, con);
           
        
     } 
    }
    
     
     
     
      
      public void AuditoriDesactivarFirma(){
            
   int idfirma=TM.getTexto();       
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion=  "HORA: "+Menu.Time.getText()+" "+" Desactivo su firma en el modulo laboratorio" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idfirma);
            ps.setInt(2, idfirma);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
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
       labEstudiosBdDao labDao = new labEstudiosBdDao();
       Temporal Tempo = new Temporal();
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActivarFirma;
    private javax.swing.JButton DesactivarFirma;
    public javax.swing.JDesktopPane JDExamenesLab;
    public javax.swing.JTabbedPane JTLaboratorio;
    private javax.swing.JTabbedPane MEstudios;
    private javax.swing.JButton VerificarFirma;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    // End of variables declaration//GEN-END:variables
}
