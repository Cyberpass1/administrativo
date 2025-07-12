/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JPacientesDao;
import Clases.Temporal;
import Clases.Validar;
import Menu.Mprincipal;
import Procesos.JAsignarPaciente;
import Procesos.JLabexamenes.JPerfilrutina;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JPacientesPCP extends javax.swing.JInternalFrame {

  
    
    int idPaciente;
    
    
    
    
    public JPacientesPCP() {
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();

       
        limpiarTabla();
        listarPacientes();
        
   
        this.JRMasculino.setSelected(true);
        acomodarceldas();
        conteoTablatotal();

        
 
         
         String Nivel=TM.getNivel();
         if(Nivel.equals("Administrador") || Nivel.equals("Inspector")){
             Eliminar.setEnabled(true); 
    
         }

         
    //   BtnLimpiar1.setVisible(false);
        
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
    
    
    
    
    
    
    
    
    
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AsignarOrden = new javax.swing.JPopupMenu();
        Orden = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        TXTPnombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTPdireccion = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        TXTPtelefono = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        JRMasculino = new javax.swing.JRadioButton();
        JRfemenino = new javax.swing.JRadioButton();
        BtnAgregar = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        TXTPcedula = new javax.swing.JTextField();
        TXTPcorreo = new javax.swing.JTextField();
        TXTPapellido = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        TXTBcedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablePaciente = new javax.swing.JTable();

        Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delegar_1.png"))); // NOI18N
        Orden.setText("Asignar estudio");
        Orden.setActionCommand("Asignar Orden");
        Orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrdenActionPerformed(evt);
            }
        });
        AsignarOrden.add(Orden);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/basura.png"))); // NOI18N
        Eliminar.setText("Eliminar Paciente");
        Eliminar.setToolTipText("");
        Eliminar.setEnabled(false);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        AsignarOrden.add(Eliminar);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      C L I E N T E S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 260, -1));

        TXTPnombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nombre")));
        TXTPnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPnombreKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 50));

        TXTPdireccion.setColumns(20);
        TXTPdireccion.setLineWrap(true);
        TXTPdireccion.setRows(5);
        TXTPdireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dirección"));
        TXTPdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPdireccionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(TXTPdireccion);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, -1));

        TXTPtelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Telefono"));
        try {
            TXTPtelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(TXTPtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 200, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sexo"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JRMasculino.setBackground(new java.awt.Color(255, 255, 255));
        JRMasculino.setText("Masculino");
        JRMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRMasculinoActionPerformed(evt);
            }
        });
        jPanel5.add(JRMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        JRfemenino.setBackground(new java.awt.Color(255, 255, 255));
        JRfemenino.setText("Femenino");
        JRfemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRfemeninoActionPerformed(evt);
            }
        });
        jPanel5.add(JRfemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 200, 50));

        BtnAgregar.setText("Agregar");
        BtnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgregar.setContentAreaFilled(false);
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 100, 30));

        BtnModificar.setText("Modificar");
        BtnModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModificar.setContentAreaFilled(false);
        BtnModificar.setEnabled(false);
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 100, 30));

        BtnLimpiar.setText("Nuevo");
        BtnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar.setContentAreaFilled(false);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 100, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 500, 20));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 10, 70));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 10, 70));

        TXTPcedula.setText("V-");
        TXTPcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cédula")));
        TXTPcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPcedulaKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, 50));

        TXTPcorreo.setText("CyberiaBackup2024@gmail.com");
        TXTPcorreo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Correo")));
        TXTPcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPcorreoKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, 50));

        TXTPapellido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Apellido")));
        TXTPapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPapellidoKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 200, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 460, 590));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTBcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar cédula | nombre"));
        TXTBcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyTyped(evt);
            }
        });
        jPanel8.add(TXTBcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 50));

        jLabel1.setText("Total de pacientes:");
        jPanel8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jLabel2.setText("0");
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 40, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 780, 80));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 30));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTablePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "CEDULA", "TELEFONO", "CORREO", "DIRECCIÓN", "SEXO"
            }
        ));
        JTablePaciente.setComponentPopupMenu(AsignarOrden);
        JTablePaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablePacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTablePaciente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 780, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
       
  Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
 
          
    
    
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = TXTPcorreo.getText();
        Matcher mather = pattern.matcher(Correo);

        if (TXTPapellido.getText().equals("") || TXTPnombre.getText().equals("")
            || TXTPcedula.getText().equals("") || TXTPcorreo.getText().equals("") || TXTPtelefono.getText().equals("") || TXTPdireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);

            for (int i =0; i< JTablePaciente.getRowCount();i++)
            {
                if(JTablePaciente.getValueAt(i,1).equals(TXTPcedula.getText())){ JOptionPane.showMessageDialog(null, "EL PACIENTE YA SE ENCUENTRA REGISTRADO", "DATO DUPLICADO", JOptionPane.ERROR_MESSAGE);;}
            }

        } else if (mather.find() == false) {
            JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
            this.TXTPcorreo.requestFocus();
        }

    
        
        else {

            String sql="SELECT Idpaciente, Nombre, Apellido, Cedula, Telefono, Direccion FROM table_paciente  WHERE Cedula='"+ TXTPcedula.getText() +"'";

            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
    
                
                
                if(rs.next()) {JOptionPane.showMessageDialog(null, "ESTA CÉDULA YA SE ENCUENTRA REGISTRADA", "CAMPO CÉDULA",JOptionPane.ERROR_MESSAGE);}
               
                
                else{
                    
                 AuditoriaAgregar();
                 AgregarPaciente(); 
                 conteoTablatotal();
                }
                


            }catch(Exception e){System.out.println(e);}
            finally {
     closeResources(rs, ps, con);
    }
            
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
limpiarCampos();
conteoTablatotal();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
       
   // Connection con;
   //EnlaceBd cn = new EnlaceBd();
   // PreparedStatement ps;
   // ResultSet rs;
    
   

   
   
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = TXTPcorreo.getText();
        Matcher mather = pattern.matcher(Correo);

        if (TXTPapellido.getText().equals("") || TXTPnombre.getText().equals("") || TXTPcedula.getText().equals("") 
            || TXTPcorreo.getText().equals("") || TXTPtelefono.getText().equals("") || TXTPdireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        } 
        
     
        
         
        else if (mather.find() == false) {
           
            
            JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
            this.TXTPcorreo.requestFocus();
            
            
        }

        
             else{    
 AuditoriaModificar();
 ActualizarPaciente(); 
                }
       
                
    }//GEN-LAST:event_BtnModificarActionPerformed

    
  
    
         
            String nacimiento, sexoTable;  
    private void JTablePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacienteMouseClicked
     int fila = JTablePaciente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;  
            
               
          
 
    
            idPaciente=(int) (JTablePaciente.getValueAt(fila, 0));
            TXTPnombre.setText(JTablePaciente.getValueAt(fila, 1).toString());
            TXTPapellido.setText(JTablePaciente.getValueAt(fila, 2).toString());
            TXTPcedula.setText(JTablePaciente.getValueAt(fila, 3).toString());
            TXTPtelefono.setText(JTablePaciente.getValueAt(fila, 4).toString());
            TXTPcorreo.setText(JTablePaciente.getValueAt(fila, 5).toString());
            TXTPdireccion.setText(JTablePaciente.getValueAt(fila, 6).toString());
            sexoTable=(String) (JTablePaciente.getValueAt(fila, 7).toString());
   
                    Sexo=sexoTable;
          String F = "Femenino";
          String M = "Masculino";
    
          if(Sexo.equals(F)){ JRfemenino.setSelected(true); JRMasculino.setSelected(false);  }
          else if(Sexo.equals(M)){ JRMasculino.setSelected(true); JRfemenino.setSelected(false);  }
            
            
            
    
            
            
            
            BtnAgregar.setEnabled(false);
            BtnModificar.setEnabled(true);
    
 
 
 
 
     
     
    }//GEN-LAST:event_JTablePacienteMouseClicked
    }      
    
    
    public String  Sexo="Masculino";;
    private void JRMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRMasculinoActionPerformed
  
        this.JRMasculino.setSelected(true);
        JRfemenino.setSelected(false);
        Sexo="Masculino";
        
        
    }//GEN-LAST:event_JRMasculinoActionPerformed

    private void JRfemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRfemeninoActionPerformed
        Sexo="Femenino";
        this.JRMasculino.setSelected(false);
        JRfemenino.setSelected(true);
    }//GEN-LAST:event_JRfemeninoActionPerformed

    private void TXTBcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedulaKeyReleased
Search(); acomodarceldas();
    }//GEN-LAST:event_TXTBcedulaKeyReleased

    private void TXTPnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPnombreKeyTyped
   va.longitud(TXTPnombre.getText(), 30, evt);
    }//GEN-LAST:event_TXTPnombreKeyTyped

    private void TXTPapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPapellidoKeyTyped
       va.longitud(TXTPapellido.getText(), 30, evt);
    }//GEN-LAST:event_TXTPapellidoKeyTyped

    private void TXTPcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPcedulaKeyTyped
               char car = evt.getKeyChar();
        //VERIFICA Y LIMITA COMPOSICION DE LOS DATOS
        if((car=='V' || car=='E'  || car=='-'|| car =='H' || car>='0' && car<='9' || car==(char)KeyEvent.VK_DELETE))
        {
            String Caracteres = TXTPcedula.getText();
            //CONTABILIZA LOS CARACTERES
            if(Caracteres.length()==10)
            {
                evt.consume();
            }
        }
        //EVITA EL INGRESO DE OTROS CARACTERES
        else if((car!='V' && car!='E' && car != 'J' && car!='-' && car!='H' || car<'0' || car>'9' || car!=(char)KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_TXTPcedulaKeyTyped

    private void TXTPcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPcorreoKeyTyped
        va.longitud(TXTPcorreo.getText(), 50, evt);
    }//GEN-LAST:event_TXTPcorreoKeyTyped

    private void TXTPdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPdireccionKeyTyped
        va.longitud(TXTPcorreo.getText(), 100, evt);
    }//GEN-LAST:event_TXTPdireccionKeyTyped

    private void TXTBcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedulaKeyTyped
          va.longitud(TXTBcedula.getText(), 30, evt);
    }//GEN-LAST:event_TXTBcedulaKeyTyped

    private void OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenActionPerformed

         int fila = JTablePaciente.getSelectedRow();
        try{

            if(TXTPcedula.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR VUELVE A SELECCIONAR UN PACIENTE", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);

            }

            else if (fila == -1) {
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA", "SELECCION", JOptionPane.ERROR_MESSAGE);
            }

            else{

                agregarOrden();
            }

        }
        catch(Exception e ){
            System.out.println("primer try catch"+e);
            JTablePaciente.requestFocusInWindow();
        }
  
        
        
        
    }//GEN-LAST:event_OrdenActionPerformed

    
    
    
     public void agregarOrden() {                                             
 
        
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JAsignarPaciente jAsig = new JAsignarPaciente();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jAsig);
       jAsig.setClosable(true);
       jAsig.setIconifiable(true);
       
        try {
            jAsig.setMaximum(true);
        } catch (Exception e) {
        }
        jAsig.toFront();
        jAsig.setVisible(true);
       
       
              
      
        // Configurar el valor en ConsultaLaboratorio
        jAsig.TXTPcedula.setText(TXTPcedula.getText());
    
        //jAsig.Callpacient();
     
       
        
        
        
        
}
    
    
    
    
    
    int idorden, orden;
    String nombreCompleto, factura, cedulaOrden;
    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
   int fila = JTablePaciente.getSelectedRow();
        if (fila == -1) {
        JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA PACIENTES", "SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {
   
        
        
        eliminarPaciente();
        }
    }//GEN-LAST:event_EliminarActionPerformed

        

   
   void limpiarTabla() {
       
  DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();  
  Tabla.setRowCount(0);
    }


   
    
  public void limpiarCampos() {
      TXTPnombre.setText("");
      TXTPapellido.setText("");
      TXTPcedula.setText("V-");
      TXTPcorreo.setText("");
      TXTPtelefono.setText("");
      TXTPdireccion.setText("");
     // TXTBnombre.setText("");
      TXTBcedula.setText("V-");
 
      JRMasculino.setSelected(true);
      JRfemenino.setSelected(false);
      Sexo="Masculino";
      BtnAgregar.setEnabled(true);
      BtnModificar.setEnabled(false);
    }
    
     public void listarPacientes() {

        List<JCPacientes> lista = pacientesDao.listarPacientes();
        modelo = (DefaultTableModel) JTablePaciente.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdpacientes();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getApellido();
            ob[3] = lista.get(i).getCedula();
            ob[4] = lista.get(i).getTelefono();
            ob[5] = lista.get(i).getCorreo();
            ob[6] = lista.get(i).getDireccion();
            ob[7] = lista.get(i).getSexo();
            modelo.addRow(ob);

        }
       JTablePaciente.setModel(modelo);
        
            
     JTablePaciente.getColumnModel().getColumn(0).setMaxWidth(0);
     JTablePaciente.getColumnModel().getColumn(0).setMinWidth(0);
     JTablePaciente.getColumnModel().getColumn(0).setPreferredWidth(0);
     JTablePaciente.setDefaultEditor(Object.class, null);
     DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) JTablePaciente.getTableHeader().getDefaultRenderer();
     headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    
    

    
      public void AgregarPaciente(){                                             
    
  Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

              
                    

        
  
        
        
              
              
            String sql = "INSERT INTO table_paciente (Nombre, Apellido, Cedula,Telefono,Correo,Direccion, Sexo) VALUES (?,?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, TXTPnombre.getText());
            ps.setString(2, TXTPapellido.getText());
            ps.setString(3, TXTPcedula.getText());
            ps.setString(4, TXTPtelefono.getText());
            ps.setString(5, TXTPcorreo.getText());
            ps.setString(6, TXTPdireccion.getText());
            ps.setString(7, Sexo);
            ps.execute();
  
            
            limpiarTabla(); listarPacientes(); limpiarCampos();
  JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO","REGISTRO DE CLIENTE", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }  finally {
     closeResources(rs, ps, con);
    }
          
    } 
      
      
      

      
      
      
     
   public void ActualizarPaciente() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
            

        
            String sql = "update table_paciente set Nombre=?,	Apellido=?,	Cedula=?,	Telefono=?,	Correo=?,  Direccion=?,  Sexo=? where Idpaciente=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, TXTPnombre.getText());
            ps.setString(2, TXTPapellido.getText());
            ps.setString(3, TXTPcedula.getText());
            ps.setString(4, TXTPtelefono.getText());
            ps.setString(5, TXTPcorreo.getText());
            ps.setString(6, TXTPdireccion.getText());
            ps.setString(7, Sexo);
            ps.setInt(8, idPaciente);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PACIENTE ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
                limpiarTabla();
                listarPacientes();
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "NO PUEDE INGRESAR UNA CÉDULA DUPLICADA", "DATOS DUPLICADOS", JOptionPane.ERROR_MESSAGE);
           } finally {
     closeResources(rs, ps, con);
    }
                 
    }

      
   


   
   public void Search() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    EnlaceBd cn = new EnlaceBd();
    
    String busqueda = this.TXTBcedula.getText().trim(); // Obtener texto de búsqueda
    
    // Consulta SQL para buscar por cédula, nombre o apellido
    String sql = "SELECT Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo " +
                 "FROM table_paciente " +
                 "WHERE Cedula LIKE ? OR CONCAT(Nombre, ' ', Apellido) LIKE ?";
    
    // Declaración del modelo de la tabla
    DefaultTableModel tabla = (DefaultTableModel) JTablePaciente.getModel();
    tabla.setRowCount(0); // Limpiar la tabla antes de agregar datos
    
    try {
        // Establecer conexión y preparar consulta
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        
        // Configurar parámetros de búsqueda
        ps.setString(1, "%" + busqueda + "%"); // Cédula
        ps.setString(2, "%" + busqueda + "%"); // Nombre y Apellido combinados
        
        // Ejecutar consulta
        rs = ps.executeQuery();
        
        // Procesar resultados
        while (rs.next()) {
            // Obtener datos de la consulta
            int col1 = rs.getInt(1);        // Idpaciente
            String col2 = rs.getString(2);  // Nombre
            String col3 = rs.getString(3);  // Apellido
            String col4 = rs.getString(4);  // Cedula
            String col5 = rs.getString(5);  // Telefono
            String col6 = rs.getString(6);  // Correo
            String col7 = rs.getString(7);  // Direccion
            String col8 = rs.getString(8);  // Sexo
     
            
            // Agregar datos a la tabla
            Vector<Object> vRow = new Vector<>();
            vRow.add(col1);
            vRow.add(col2);
            vRow.add(col3);
            vRow.add(col4);
            vRow.add(col5);
            vRow.add(col6);
            vRow.add(col7);
            vRow.add(col8);
          
            
            tabla.addRow(vRow);
        }
        
        // Otras configuraciones de la tabla
        JTablePaciente.getTableHeader().setReorderingAllowed(false);
        acomodarceldas(); // Ajustar celdas si es necesario
        
    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.getMessage());
    } finally {
     closeResources(rs, ps, con);
    }
}
      
      
      
      
      
      
        
      
      
      
      
      
      
      
       
  public void EliminarAntc(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "DELETE FROM orden_lab WHERE lugar = ? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idorden);
            int res = ps.executeUpdate();

            if (res >= 1) {
           JOptionPane.showMessageDialog(null, "PROCESO REALIZADO", "ORDENES", 1);
             
            } else {
               JOptionPane.showMessageDialog(null, "NO SE PUDO QUITAR DE LA LISTA EL PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
          
            
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL QUITAR DEL PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
           }finally {
            closeResources(rs, ps, con);
        }

  } 
      
      
      
      
      
      
      
        public void AuditoriaAgregar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
              String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" Agrego al paciente: "+ TXTPnombre.getText()+" "+TXTPapellido.getText() +" " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
             closeResources(rs, ps, con);
           
    }
    }
      
      
        public void AuditoriaEliminar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Quito al paciente de la lista orden: "+ nombreCompleto +" " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
         closeResources(rs, ps, con);
           
    }
    }
      
        
        
           public void auditoriaEliminarPaciente(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
            String nombreCompleto = TXTPnombre.getText() + " " + TXTPapellido.getText();
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
           
            String accion= "Elimino al paciente de la bd: "+ nombreCompleto +" " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
   closeResources(rs, ps, con);
           
    }
    }
        
        
        
        
        
        
        
        public void auditoriaAsignar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Asigno al paciente: "+ nombreCompleto +"a la lista de orden" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
      closeResources(rs, ps, con);
           
    }
    }
      
        
        
      
           public void auditoriaModifico(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modifico al paciente de la lista orden: "+ nombreCompleto +" " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
          closeResources(rs, ps, con);
           
    }
    }
        
        
        
        
      public void AuditoriaModificar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" Modifico al paciente: "+ TXTPnombre.getText()+" "+TXTPapellido.getText() +" " ;
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
             finally {
           closeResources(rs, ps, con);
           
    }
    }
    
      
      
      
      
      
      
      
      
       public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTablePaciente.setRowHeight(20);
        
       JTablePaciente.getColumnModel().getColumn(0).setMaxWidth(0);
       JTablePaciente.getColumnModel().getColumn(0).setMinWidth(0);
       JTablePaciente.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("NOMBRE")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("APELLIDO")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CEDULA")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("TELEFONO")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CORREO")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("DIRECCIÓN")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("SEXO")).setPreferredWidth(60);

         
        
      
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("NOMBRE")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("APELLIDO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CEDULA")).setCellRenderer(Alinear);;
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("TELEFONO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CORREO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("DIRECCIÓN")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("SEXO")).setCellRenderer(Alinear);

    
    }
            
       
     
       
       
    public void eliminarPaciente() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs=null;
    try {
        String nombreCompleto = TXTPnombre.getText() + " " + TXTPapellido.getText();

      String mensaje = "¿Está seguro de que desea eliminar a " + nombreCompleto + " de la tabla de pacientes?";
      mensaje += "\nNOTA: Si el paciente cuenta con estudios ya generados, no se podrán visualizar en la tabla de consultas.";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_paciente` WHERE Idpaciente = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPaciente);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El paciente ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                listarPacientes();
                conteoTablatotal();
                auditoriaEliminarPaciente();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar al paciente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
    closeResources(rs, ps, con);
    }
}
         
         
    public void asignarHematologia() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Crear campos de texto
        JCheckBox gbCheckBox = createStyledCheckBox("GB");
        JCheckBox linfCheckBox = createStyledCheckBox("LINF");
        JCheckBox segCheckBox = createStyledCheckBox("SEG");
        JCheckBox hbCheckBox = createStyledCheckBox("HB");
        JCheckBox htoCheckBox = createStyledCheckBox("HTO");
        JCheckBox vcmCheckBox = createStyledCheckBox("VCM");
        JCheckBox chcmCheckBox = createStyledCheckBox("CHCM");
        JCheckBox plaqCheckBox = createStyledCheckBox("PLAQ");
        JCheckBox todos = createStyledCheckBox("Todos");

        // Crear etiquetas para los campos de texto

        // Crear el panel y establecer el diseño
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(400, 200));
        // Agregar etiquetas y campos de texto al panel

        panel.add(gbCheckBox);
        panel.add(linfCheckBox);
        panel.add(segCheckBox);
        panel.add(hbCheckBox);
        panel.add(htoCheckBox);
        panel.add(vcmCheckBox);
        panel.add(chcmCheckBox);
        panel.add(plaqCheckBox);
        panel.add(todos);

        // Agregar un ActionListener para el JCheckBox "Todos"
        todos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean seleccionado = todos.isSelected();

                // Iterar sobre los JCheckBox y establecer su estado
                gbCheckBox.setSelected(seleccionado);
                linfCheckBox.setSelected(seleccionado);
                segCheckBox.setSelected(seleccionado);
                hbCheckBox.setSelected(seleccionado);
                htoCheckBox.setSelected(seleccionado);
                vcmCheckBox.setSelected(seleccionado);
                chcmCheckBox.setSelected(seleccionado);
                plaqCheckBox.setSelected(seleccionado);
            }
        });

        // Mostrar el panel de entrada de datos
        int result = JOptionPane.showConfirmDialog(null, panel, "HEMATOLOGIA PACIENTE: " + nombreCompleto, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Map<String, Boolean> data = new HashMap<>();
            data.put("GB", gbCheckBox.isSelected() ? true : false);
            data.put("LINF", linfCheckBox.isSelected() ? true : false);
            data.put("SEG", segCheckBox.isSelected() ? true : false);
            data.put("HB", hbCheckBox.isSelected() ? true : false);
            data.put("HTO", htoCheckBox.isSelected() ? true : false);
            data.put("VCM", vcmCheckBox.isSelected() ? true : false);
            data.put("CHCM", chcmCheckBox.isSelected() ? true : false);
            data.put("PLAQ", plaqCheckBox.isSelected() ? true : false);

            try {
                String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());

                String sql = "INSERT INTO `asig_hematologia`( `Id_ordenes`, `Leucocitos`, `Linfocitos`, `Neutrofilos`, `Hemoglobina`, `Hematocrito`, `VCM`, `CHCM`, `Plaquetas`) VALUES (?,?,?,?,?,?,?,?,?)";

                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, orden);
                ps.setBoolean(2, data.get("GB"));
                ps.setBoolean(3, data.get("LINF"));
                ps.setBoolean(4, data.get("SEG"));
                ps.setBoolean(5, data.get("HB"));
                ps.setBoolean(6, data.get("HTO"));
                ps.setBoolean(7, data.get("VCM"));
                ps.setBoolean(8, data.get("CHCM"));
                ps.setBoolean(9, data.get("PLAQ"));
      
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Estudios", 1);
                
                
               
            } catch (Exception e) {
                System.out.println(e + "1");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR NÚMEROS ERROR: " + e);
        System.out.println(e);
    } finally {
       closeResources(rs, ps, con);
    }
}

 
    

    
    
    
public void asignarQuimica() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Crear campos de texto
        JCheckBox GLICEMIACheckBox = createStyledCheckBox("GLI");
        JCheckBox UREACheckBox = createStyledCheckBox("UREA");
        JCheckBox CREACheckBox = createStyledCheckBox("CREA");
        JCheckBox AUCheckBox = createStyledCheckBox("A.U");
        JCheckBox COLTCheckBox = createStyledCheckBox("COLEST");
        JCheckBox TRIGCheckBox = createStyledCheckBox("TRIG");
        JCheckBox HDLCCheckBox = createStyledCheckBox("HDL");
        JCheckBox LDLCCheckBox = createStyledCheckBox("LDL");
        JCheckBox VLDLCCheckBox = createStyledCheckBox("VLDL");
        
        JCheckBox BTCheckBox = createStyledCheckBox("BT");
        JCheckBox BDCheckBox = createStyledCheckBox("BD");
        JCheckBox BICheckBox = createStyledCheckBox("BI");
        JCheckBox PROTCheckBox = createStyledCheckBox("PT");
        JCheckBox ALBCheckBox = createStyledCheckBox("ALB");
        
        JCheckBox GLOBCheckBox = createStyledCheckBox("GLOB");
        JCheckBox RAGCheckBox = createStyledCheckBox("RA/G");
        JCheckBox TGOCheckBox = createStyledCheckBox("TGO");
        JCheckBox TGPCheckBox = createStyledCheckBox("TGP");
        JCheckBox CALCheckBox = createStyledCheckBox("CAL");
        
        JCheckBox FOSFCheckBox = createStyledCheckBox("FOSF");
        JCheckBox MAGCheckBox = createStyledCheckBox("MAG");
        JCheckBox GGTCheckBox = createStyledCheckBox("GGT");
        JCheckBox ALPCheckBox = createStyledCheckBox("ALP");
        JCheckBox LDHCheckBox = createStyledCheckBox("LDH");
        
        JCheckBox AMICheckBox = createStyledCheckBox("AMI");
        JCheckBox LIPCheckBox = createStyledCheckBox("LIP");
        JCheckBox PCRCheckBox = createStyledCheckBox("PCR");

        
        JCheckBox todos = createStyledCheckBox("Todos");

        // Crear etiquetas para los campos de texto

        // Crear el panel y establecer el diseño
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(550, 200));
        // Agregar etiquetas y campos de texto al panel

        panel.add(GLICEMIACheckBox);
        panel.add(UREACheckBox);
        panel.add(CREACheckBox);
        panel.add(AUCheckBox);
        panel.add(COLTCheckBox);
        panel.add(TRIGCheckBox);
        panel.add(HDLCCheckBox);
        panel.add(LDLCCheckBox);
        panel.add(VLDLCCheckBox);
        panel.add(BTCheckBox);
        panel.add(BDCheckBox);
        panel.add(BICheckBox);
        panel.add(PROTCheckBox);
        panel.add(ALBCheckBox);
        panel.add(GLOBCheckBox);
        panel.add(RAGCheckBox);
        panel.add(TGOCheckBox);
        panel.add(TGPCheckBox);
        panel.add(CALCheckBox);
        panel.add(FOSFCheckBox);
        panel.add(MAGCheckBox);
        panel.add(GGTCheckBox);
        panel.add(ALPCheckBox);
        panel.add(LDHCheckBox);        
        panel.add(AMICheckBox);
        panel.add(LIPCheckBox);
        panel.add(PCRCheckBox);
  
        
        
        
        panel.add(todos);

        // Agregar un ActionListener para el JCheckBox "Todos"
        todos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean seleccionado = todos.isSelected();

                // Iterar sobre los JCheckBox y establecer su estado
                GLICEMIACheckBox.setSelected(seleccionado);
                UREACheckBox.setSelected(seleccionado);
                CREACheckBox.setSelected(seleccionado);
                AUCheckBox.setSelected(seleccionado);
                COLTCheckBox.setSelected(seleccionado);
                TRIGCheckBox.setSelected(seleccionado);
                HDLCCheckBox.setSelected(seleccionado);
                LDLCCheckBox.setSelected(seleccionado);
                
                VLDLCCheckBox.setSelected(seleccionado);
                BTCheckBox.setSelected(seleccionado);
                BDCheckBox.setSelected(seleccionado);
                BICheckBox.setSelected(seleccionado);
                PROTCheckBox.setSelected(seleccionado);
                ALBCheckBox.setSelected(seleccionado);
                GLOBCheckBox.setSelected(seleccionado);
                RAGCheckBox.setSelected(seleccionado);
                
                TGOCheckBox.setSelected(seleccionado);
                TGPCheckBox.setSelected(seleccionado);
                CALCheckBox.setSelected(seleccionado);
                FOSFCheckBox.setSelected(seleccionado);
                MAGCheckBox.setSelected(seleccionado);
                GGTCheckBox.setSelected(seleccionado);
                ALPCheckBox.setSelected(seleccionado);
                LDHCheckBox.setSelected(seleccionado);
                
                AMICheckBox.setSelected(seleccionado);
                LIPCheckBox.setSelected(seleccionado);
                PCRCheckBox.setSelected(seleccionado);

                
            }
        });

        // Mostrar el panel de entrada de datos
        int result = JOptionPane.showConfirmDialog(null, panel, "QUIMICA PACIENTE: " + nombreCompleto, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Map<String, Boolean> data = new HashMap<>();
            data.put("GLI", GLICEMIACheckBox.isSelected() ? true : false);
            data.put("UREA", UREACheckBox.isSelected() ? true : false);
            data.put("CREA", CREACheckBox.isSelected() ? true : false);
            data.put("AU", AUCheckBox.isSelected() ? true : false);
            data.put("COLEST", COLTCheckBox.isSelected() ? true : false);
            data.put("TRIG", TRIGCheckBox.isSelected() ? true : false);
            data.put("HDL", HDLCCheckBox.isSelected() ? true : false);
            data.put("LDL", LDLCCheckBox.isSelected() ? true : false);
            data.put("VLDL", VLDLCCheckBox.isSelected() ? true : false);
            data.put("BT", BTCheckBox.isSelected() ? true : false);
            data.put("BD", BDCheckBox.isSelected() ? true : false);
            data.put("BI", BICheckBox.isSelected() ? true : false);
            data.put("PT", PROTCheckBox.isSelected() ? true : false);
            data.put("ALB", ALBCheckBox.isSelected() ? true : false);
            data.put("GLOB", GLOBCheckBox.isSelected() ? true : false);
            
            data.put("RAG", RAGCheckBox.isSelected() ? true : false);
            data.put("TGO", TGOCheckBox.isSelected() ? true : false);
            data.put("TGP", TGPCheckBox.isSelected() ? true : false);
            data.put("CAL", CALCheckBox.isSelected() ? true : false);
            
            data.put("FOSF", FOSFCheckBox.isSelected() ? true : false);
            data.put("MAG",  MAGCheckBox.isSelected() ? true : false);
            data.put("GGT", GGTCheckBox.isSelected() ? true : false);
            data.put("ALP", ALPCheckBox.isSelected() ? true : false);
            data.put("LDH", LDHCheckBox.isSelected() ? true : false);
            data.put("AMI", AMICheckBox.isSelected() ? true : false);
            data.put("LIP", LIPCheckBox.isSelected() ? true : false);
            data.put("PCR", PCRCheckBox.isSelected() ? true : false);
            

            try {
                String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());

String sql = "INSERT INTO `asig_quimica`(`id_ordenQU`, `GLICEMIA`, `UREA`, `CREATININA`, `ACIDO_URICO`, `COLESTEROL`, `TRIGLICERIDOS`, `HDL`, `LDL`, `VLDL`, `BILITOTAL`, `BILIDIRECTA`, `BILIINDIRECTA`, `PROTEINAS_TOTALES`, `ALBUMINA`, `GLOBULINAS`, `RELACION`, `TGO`, `TGP`, `GGT`, `FOSFATASA`, `LDH`, `AMILASA`, `LIPASA`, `CALCIO`, `FOSFORO`, `MAGNESIO`, `CK`) \n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, orden);
               
                ps.setBoolean(2, data.get("GLI"));
                ps.setBoolean(3, data.get("UREA"));
                ps.setBoolean(4, data.get("CREA"));
                ps.setBoolean(5, data.get("AU"));
                ps.setBoolean(6, data.get("COLEST"));
                ps.setBoolean(7, data.get("TRIG"));
                ps.setBoolean(8, data.get("HDL"));
                ps.setBoolean(9, data.get("LDL"));
                ps.setBoolean(10, data.get("VLDL"));
                
                ps.setBoolean(11, data.get("BT"));
                ps.setBoolean(12, data.get("BD"));
                ps.setBoolean(13, data.get("BI"));
                ps.setBoolean(14, data.get("PT"));
                ps.setBoolean(15, data.get("ALB"));
                ps.setBoolean(16, data.get("GLOB"));
                ps.setBoolean(17, data.get("RAG"));
                ps.setBoolean(18, data.get("TGO"));
                ps.setBoolean(19, data.get("TGP"));
                
                ps.setBoolean(20, data.get("GGT"));
                ps.setBoolean(21, data.get("ALP"));
                ps.setBoolean(22, data.get("LDH"));
                ps.setBoolean(23, data.get("AMI"));
                ps.setBoolean(24, data.get("LIP"));
                ps.setBoolean(25, data.get("CAL"));
                ps.setBoolean(26, data.get("FOSF"));
                ps.setBoolean(27, data.get("MAG"));
                ps.setBoolean(28, data.get("PCR"));
                
                            
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Estudios", 1);
                
                
               
            } catch (Exception e) {
                System.out.println(e + "1");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR NÚMEROS ERROR: " + e);
        System.out.println(e);
    } finally {
      closeResources(rs, ps, con);
    }
}
   
    
    
    
    
    
    
// Función para crear y personalizar campos de texto
private JCheckBox createStyledCheckBox(String text) {
    JCheckBox checkBox = new JCheckBox(text);
    checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
    checkBox.setBackground(Color.WHITE);
    checkBox.setForeground(Color.BLACK);
    // Puedes seguir personalizando el estilo del JCheckBox aquí si es necesario.
    return checkBox;
}

private JLabel createStyledLabel(String labelText) {
    JLabel label = new JLabel(labelText);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setHorizontalAlignment(JLabel.RIGHT); // Alinea el texto a la derecha
    label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Agrega un espacio alrededor de la etiqueta
    return label;
}
         
         
        
         
 
    



         
       
       
    public void conteoTablatotal(){
       
       for(int x =0; x<=JTablePaciente.getRowCount(); x++ ){
       jLabel2.setText(""+x);
       
       }
       }   
      
    
      
    
      
  DefaultTableModel modelo = new DefaultTableModel();
  Mprincipal Menu = new Mprincipal();
  Encriptar encriptar = new Encriptar();
  JCPacientes pacientes  = new JCPacientes();
  JPacientesDao pacientesDao  = new JPacientesDao();
    
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
  String niveluser=TM.getNivel();   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu AsignarOrden;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JRadioButton JRMasculino;
    private javax.swing.JRadioButton JRfemenino;
    private javax.swing.JTable JTablePaciente;
    private javax.swing.JMenuItem Orden;
    private javax.swing.JTextField TXTBcedula;
    private javax.swing.JTextField TXTPapellido;
    private javax.swing.JTextField TXTPcedula;
    private javax.swing.JTextField TXTPcorreo;
    private javax.swing.JTextArea TXTPdireccion;
    private javax.swing.JTextField TXTPnombre;
    private javax.swing.JFormattedTextField TXTPtelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
