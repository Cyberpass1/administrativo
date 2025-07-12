/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Registros.*;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCFactura;
import Clases.JCFacturaDao;
import Clases.LlenarCombobox;
import Clases.Temporal;
import Clases.Validar;
import Consultas.ConsultaHonorarios;
import Consultas.ConsultaLaboratorio;
import Menu.Mprincipal;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
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
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JHonorarios extends javax.swing.JInternalFrame {

  
    public boolean corregir=false;
    public JHonorarios() {
        initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
        llenarCombo();
      
       
      
        disableBox();
   
        acomodarTabla();
        informacionpdf();
    
   

        // Obtener la fecha del servidor
    java.sql.Date fechaServidor = getFechaDelServidor();
    
    // Establecer la fecha del servidor en el componente FechaAsignar
    if (fechaServidor != null) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaServidor);
        FechaOne.setCalendar(cal);
        FechaTwo.setCalendar(cal);
    }
        
    
    
          
            
            if(JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
            llenarBioanalisis();
           //jISRL.setVisible(false);
            jTabbedPane2.setSelectedIndex(0);
            jRadioConsulta.setEnabled(false);
            jRadioProc.setEnabled(false);
            jRadioQuirurgico.setEnabled(false);
            
            jRadioOtros.setSelected(true);
            } 
            
            else{
         //   jISRL.setVisible(true);    
     
            
            jRadioConsulta.setEnabled(true);
            jRadioOtros.setSelected(false);
            jRadioProc.setEnabled(true);
            jRadioQuirurgico.setEnabled(true);
            jTabbedPane2.setSelectedIndex(1);
            }
            
     
      knowCode(); 
      
      
      
       
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jQuitar = new javax.swing.JPopupMenu();
        jRemove = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServ = new javax.swing.JTable();
        jSeparator6 = new javax.swing.JSeparator();
        jTextTotal = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jTextCantidad = new javax.swing.JTextField();
        BtnVisualizar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        jTextDcc = new javax.swing.JTextField();
        BtnProcesar = new javax.swing.JButton();
        jTextSubtotal = new javax.swing.JTextField();
        jISRL = new javax.swing.JCheckBox();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTextDcFlat1 = new javax.swing.JTextField();
        jTextISRL1 = new javax.swing.JTextField();
        jTextDcIncine1 = new javax.swing.JTextField();
        jTextDcNomina = new javax.swing.JTextField();
        jTextDcAlmc1 = new javax.swing.JTextField();
        jTextBioseguridad = new javax.swing.JTextField();
        jTextOtherDc1 = new javax.swing.JTextField();
        jTextAnticipo1 = new javax.swing.JTextField();
        JTextPrtjFundacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextDcAlmc = new javax.swing.JTextField();
        jTextOtherDc = new javax.swing.JTextField();
        jTextAnticipo = new javax.swing.JTextField();
        jTextDcPersonal = new javax.swing.JTextField();
        jTextISRL = new javax.swing.JTextField();
        jTextDcFlat = new javax.swing.JTextField();
        jTextIsrlPrcj = new javax.swing.JTextField();
        jTextIsrlPrcj1 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextObservacion = new javax.swing.JTextArea();
        BtnGuardar = new javax.swing.JButton();
        jTextFacturar = new javax.swing.JTextField();
        jTextotalNeto = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        FechaAc5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        JComboEspecialidad = new javax.swing.JComboBox<>();
        JComboDoctor = new javax.swing.JComboBox<>();
        JComboServ = new javax.swing.JComboBox<>();
        BtnRemove = new javax.swing.JButton();
        jSpinnerCant = new javax.swing.JSpinner();
        jTextBruto = new javax.swing.JTextField();
        jTextPorcentaje = new javax.swing.JTextField();
        jTextNeto = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JButton();
        FechaTwo = new com.toedter.calendar.JDateChooser();
        FechaOne = new com.toedter.calendar.JDateChooser();
        jRadioOtros = new javax.swing.JRadioButton();
        jRadioConsulta = new javax.swing.JRadioButton();
        jRadioQuirurgico = new javax.swing.JRadioButton();
        jRadioProc = new javax.swing.JRadioButton();
        jTextsearch = new javax.swing.JTextField();
        jTextBrutoTotal = new javax.swing.JTextField();

        jRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        jRemove.setText("Quitar");
        jRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemoveActionPerformed(evt);
            }
        });
        jQuitar.add(jRemove);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1300, 30));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTableServ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SERVICIO", "CANTIDAD", "BRUTO UNIT", "BRUTO TOTAL", "%", "NETO"
            }
        ));
        jTableServ.setComponentPopupMenu(jQuitar);
        jTableServ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableServ);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 870, 310));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 910, 20));

        jTextTotal.setEditable(false);
        jTextTotal.setText("0");
        jTextTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL A PAGAR"));
        jTextTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextTotalFocusLost(evt);
            }
        });
        jTextTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextTotalKeyTyped(evt);
            }
        });
        jPanel9.add(jTextTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 310, 40));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 400, 430, 20));

        jTextCantidad.setEditable(false);
        jTextCantidad.setText("0");
        jTextCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CANT. SERVICIOS"));
        jTextCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextCantidadFocusLost(evt);
            }
        });
        jTextCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextCantidadKeyTyped(evt);
            }
        });
        jPanel9.add(jTextCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 120, 40));

        BtnVisualizar.setText("VISUALIZAR");
        BtnVisualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnVisualizar.setContentAreaFilled(false);
        BtnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVisualizarActionPerformed(evt);
            }
        });
        jPanel9.add(BtnVisualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 90, 40));

        BtnCancelar.setText("CANCELAR");
        BtnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnCancelar.setContentAreaFilled(false);
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });
        jPanel9.add(BtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, 90, 40));

        jTextDcc.setEditable(false);
        jTextDcc.setText("0");
        jTextDcc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL DEDUCCIONES"));
        jTextDcc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDccFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDccFocusLost(evt);
            }
        });
        jTextDcc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDccKeyTyped(evt);
            }
        });
        jPanel9.add(jTextDcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 190, -1));

        BtnProcesar.setText("PROCESAR");
        BtnProcesar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnProcesar.setContentAreaFilled(false);
        BtnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcesarActionPerformed(evt);
            }
        });
        jPanel9.add(BtnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, 110, 40));

        jTextSubtotal.setEditable(false);
        jTextSubtotal.setText("0");
        jTextSubtotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB TOTAL"));
        jTextSubtotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextSubtotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextSubtotalFocusLost(evt);
            }
        });
        jTextSubtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextSubtotalKeyTyped(evt);
            }
        });
        jPanel9.add(jTextSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 220, 40));

        jISRL.setBackground(new java.awt.Color(255, 255, 255));
        jISRL.setText("APLICAR ISLR");
        jISRL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jISRLActionPerformed(evt);
            }
        });
        jPanel9.add(jISRL, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 500, -1, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextDcFlat1.setText("0");
        jTextDcFlat1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIONES COMISION FLAT 15%"));
        jTextDcFlat1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcFlat1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcFlat1FocusLost(evt);
            }
        });
        jTextDcFlat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcFlat1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextDcFlat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 210, 40));

        jTextISRL1.setText("0");
        jTextISRL1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ret ISLR 3%"));
        jTextISRL1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextISRL1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextISRL1FocusLost(evt);
            }
        });
        jTextISRL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextISRL1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextISRL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 230, 40));

        jTextDcIncine1.setText("0");
        jTextDcIncine1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIÓN INCINERADORA"));
        jTextDcIncine1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcIncine1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcIncine1FocusLost(evt);
            }
        });
        jTextDcIncine1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDcIncine1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcIncine1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextDcIncine1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 40));

        jTextDcNomina.setText("0");
        jTextDcNomina.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIÓN NOMINA"));
        jTextDcNomina.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcNominaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcNominaFocusLost(evt);
            }
        });
        jTextDcNomina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDcNominaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcNominaKeyTyped(evt);
            }
        });
        jPanel4.add(jTextDcNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 230, 40));

        jTextDcAlmc1.setText("0");
        jTextDcAlmc1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIONES DE ALMACEN"));
        jTextDcAlmc1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcAlmc1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcAlmc1FocusLost(evt);
            }
        });
        jTextDcAlmc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDcAlmc1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcAlmc1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextDcAlmc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, 40));

        jTextBioseguridad.setText("0");
        jTextBioseguridad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIÓN BIOSEGURIDAD"));
        jTextBioseguridad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextBioseguridadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextBioseguridadFocusLost(evt);
            }
        });
        jTextBioseguridad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBioseguridadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextBioseguridadKeyTyped(evt);
            }
        });
        jPanel4.add(jTextBioseguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 230, 40));

        jTextOtherDc1.setText("0");
        jTextOtherDc1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OTRAS DEDUCCIONES"));
        jTextOtherDc1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextOtherDc1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextOtherDc1FocusLost(evt);
            }
        });
        jTextOtherDc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextOtherDc1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextOtherDc1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextOtherDc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 440, 40));

        jTextAnticipo1.setText("0");
        jTextAnticipo1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ANTICIPO"));
        jTextAnticipo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAnticipo1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAnticipo1FocusLost(evt);
            }
        });
        jTextAnticipo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAnticipo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAnticipo1KeyTyped(evt);
            }
        });
        jPanel4.add(jTextAnticipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 230, 40));

        JTextPrtjFundacion.setText("0");
        JTextPrtjFundacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "70% FUNDACIÓN"));
        JTextPrtjFundacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTextPrtjFundacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTextPrtjFundacionFocusLost(evt);
            }
        });
        JTextPrtjFundacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTextPrtjFundacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTextPrtjFundacionKeyTyped(evt);
            }
        });
        jPanel4.add(JTextPrtjFundacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, 40));

        jTabbedPane2.addTab("Lab", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextDcAlmc.setText("0");
        jTextDcAlmc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIONES DE CONSUMO ALMACEN"));
        jTextDcAlmc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcAlmcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcAlmcFocusLost(evt);
            }
        });
        jTextDcAlmc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDcAlmcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcAlmcKeyTyped(evt);
            }
        });
        jPanel2.add(jTextDcAlmc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 440, 40));

        jTextOtherDc.setText("0");
        jTextOtherDc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OTRAS DEDUCCIONES"));
        jTextOtherDc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextOtherDcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextOtherDcFocusLost(evt);
            }
        });
        jTextOtherDc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextOtherDcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextOtherDcKeyTyped(evt);
            }
        });
        jPanel2.add(jTextOtherDc, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 220, 40));

        jTextAnticipo.setText("0");
        jTextAnticipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ANTICIPO"));
        jTextAnticipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAnticipoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAnticipoFocusLost(evt);
            }
        });
        jTextAnticipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAnticipoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAnticipoKeyTyped(evt);
            }
        });
        jPanel2.add(jTextAnticipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, 40));

        jTextDcPersonal.setText("0");
        jTextDcPersonal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIONES PERSONAL ASIGNADO"));
        jTextDcPersonal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcPersonalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcPersonalFocusLost(evt);
            }
        });
        jTextDcPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDcPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcPersonalKeyTyped(evt);
            }
        });
        jPanel2.add(jTextDcPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 440, 40));

        jTextISRL.setText("0");
        jTextISRL.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ret ISLR"));
        jTextISRL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextISRLFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextISRLFocusLost(evt);
            }
        });
        jTextISRL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextISRLKeyTyped(evt);
            }
        });
        jPanel2.add(jTextISRL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 280, 40));

        jTextDcFlat.setText("0");
        jTextDcFlat.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DEDUCCIONES COMISION FLAT 15%"));
        jTextDcFlat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextDcFlatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDcFlatFocusLost(evt);
            }
        });
        jTextDcFlat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextDcFlatKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDcFlatKeyTyped(evt);
            }
        });
        jPanel2.add(jTextDcFlat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 440, 40));

        jTextIsrlPrcj.setText("3");
        jTextIsrlPrcj.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ajustar %"));
        jTextIsrlPrcj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextIsrlPrcjFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextIsrlPrcjFocusLost(evt);
            }
        });
        jTextIsrlPrcj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextIsrlPrcjKeyTyped(evt);
            }
        });
        jPanel2.add(jTextIsrlPrcj, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 80, 40));

        jTextIsrlPrcj1.setText("22.5");
        jTextIsrlPrcj1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Resta"));
        jTextIsrlPrcj1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextIsrlPrcj1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextIsrlPrcj1FocusLost(evt);
            }
        });
        jTextIsrlPrcj1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextIsrlPrcj1KeyTyped(evt);
            }
        });
        jPanel2.add(jTextIsrlPrcj1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 80, 40));

        jTabbedPane2.addTab("Others", jPanel2);

        jPanel9.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 450, 270));

        jTextObservacion.setColumns(20);
        jTextObservacion.setLineWrap(true);
        jTextObservacion.setRows(5);
        jTextObservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Observación"));
        jScrollPane7.setViewportView(jTextObservacion);

        jPanel9.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 440, 50));

        BtnGuardar.setText("GUARDAR");
        BtnGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnGuardar.setContentAreaFilled(false);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        jPanel9.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 90, 40));

        jTextFacturar.setEditable(false);
        jTextFacturar.setText("0");
        jTextFacturar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "FACTURAR"));
        jTextFacturar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFacturarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFacturarFocusLost(evt);
            }
        });
        jTextFacturar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFacturarKeyTyped(evt);
            }
        });
        jPanel9.add(jTextFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 410, 40));

        jTextotalNeto.setEditable(false);
        jTextotalNeto.setText("0");
        jTextotalNeto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL NETO"));
        jTextotalNeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextotalNetoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextotalNetoFocusLost(evt);
            }
        });
        jTextotalNeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextotalNetoKeyTyped(evt);
            }
        });
        jPanel9.add(jTextotalNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 290, 40));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel9.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, 10, 280));

        jTabbedPane1.addTab("tab1", jPanel9);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 900, 650));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc5.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc5.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc5.setText("H O N O R A R I O S");
        jPanel6.add(FechaAc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 190, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(340, 480));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JComboEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Especialidad"));
        JComboEspecialidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboEspecialidadItemStateChanged(evt);
            }
        });
        jPanel7.add(JComboEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 330, 50));

        JComboDoctor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Especialista"));
        JComboDoctor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboDoctorItemStateChanged(evt);
            }
        });
        jPanel7.add(JComboDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 330, 50));

        JComboServ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Servicios"));
        JComboServ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboServItemStateChanged(evt);
            }
        });
        jPanel7.add(JComboServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 330, 50));

        BtnRemove.setText("Quitar");
        BtnRemove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnRemove.setContentAreaFilled(false);
        BtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemoveActionPerformed(evt);
            }
        });
        jPanel7.add(BtnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 80, 40));

        jSpinnerCant.setModel(new javax.swing.SpinnerNumberModel(1, 1, 500, 1));
        jSpinnerCant.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cantidad"));
        jSpinnerCant.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerCantStateChanged(evt);
            }
        });
        jSpinnerCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSpinnerCantFocusLost(evt);
            }
        });
        jSpinnerCant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSpinnerCantMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSpinnerCantMouseExited(evt);
            }
        });
        jSpinnerCant.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerCantPropertyChange(evt);
            }
        });
        jPanel7.add(jSpinnerCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 100, 50));

        jTextBruto.setText("0");
        jTextBruto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bruto Unit"));
        jTextBruto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextBrutoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextBrutoFocusLost(evt);
            }
        });
        jTextBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBrutoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextBrutoKeyTyped(evt);
            }
        });
        jPanel7.add(jTextBruto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 110, 50));

        jTextPorcentaje.setText("0");
        jTextPorcentaje.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "%"));
        jTextPorcentaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextPorcentajeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPorcentajeFocusLost(evt);
            }
        });
        jTextPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPorcentajeKeyTyped(evt);
            }
        });
        jPanel7.add(jTextPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 100, 50));

        jTextNeto.setText("0");
        jTextNeto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "NETO"));
        jTextNeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextNetoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNetoFocusLost(evt);
            }
        });
        jTextNeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNetoKeyTyped(evt);
            }
        });
        jPanel7.add(jTextNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 230, 50));

        BtnAdd.setText("Añadir");
        BtnAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAdd.setContentAreaFilled(false);
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });
        jPanel7.add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 80, 40));

        FechaTwo.setBackground(new java.awt.Color(255, 255, 255));
        FechaTwo.setToolTipText("");
        FechaTwo.setDateFormatString("yyyy-MM-dd");
        FechaTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel7.add(FechaTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 160, 40));

        FechaOne.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne.setToolTipText("");
        FechaOne.setDateFormatString("yyyy-MM-dd");
        FechaOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel7.add(FechaOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 40));

        jRadioOtros.setBackground(new java.awt.Color(255, 255, 255));
        jRadioOtros.setText("Otros");
        jRadioOtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioOtrosActionPerformed(evt);
            }
        });
        jPanel7.add(jRadioOtros, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        jRadioConsulta.setBackground(new java.awt.Color(255, 255, 255));
        jRadioConsulta.setText("Consulta");
        jRadioConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioConsultaActionPerformed(evt);
            }
        });
        jPanel7.add(jRadioConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jRadioQuirurgico.setBackground(new java.awt.Color(255, 255, 255));
        jRadioQuirurgico.setText("Quirurgico");
        jRadioQuirurgico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioQuirurgicoActionPerformed(evt);
            }
        });
        jPanel7.add(jRadioQuirurgico, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, -1, -1));

        jRadioProc.setBackground(new java.awt.Color(255, 255, 255));
        jRadioProc.setText("Proc.");
        jRadioProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioProcActionPerformed(evt);
            }
        });
        jPanel7.add(jRadioProc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        jTextsearch.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Búsqueda rápida"));
        jTextsearch.setEnabled(false);
        jTextsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextsearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextsearchFocusLost(evt);
            }
        });
        jTextsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextsearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextsearchKeyTyped(evt);
            }
        });
        jPanel7.add(jTextsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 330, 50));

        jTextBrutoTotal.setText("0");
        jTextBrutoTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bruto Total"));
        jTextBrutoTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextBrutoTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextBrutoTotalFocusLost(evt);
            }
        });
        jTextBrutoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBrutoTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextBrutoTotalKeyTyped(evt);
            }
        });
        jPanel7.add(jTextBrutoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 120, 50));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 350, 580));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    
    
    
    private void JComboEspecialidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboEspecialidadItemStateChanged

   if (JComboEspecialidad.getSelectedIndex() != -1) {
    // Llenar los datos iniciales
    llenarDrs();

    // Obtener la especialidad seleccionada
    String especialidad = (String) JComboEspecialidad.getSelectedItem();

    // Caso para Bioanalista
    if ("Bioanalista".equals(especialidad)) {
        jTabbedPane2.setSelectedIndex(0);
        llenarBioanalisis();

        // Deshabilitar opciones específicas para Bioanalista
        jRadioConsulta.setEnabled(false);
        jRadioProc.setEnabled(false);
        jRadioQuirurgico.setEnabled(false);
        jRadioOtros.setEnabled(false);

        // Seleccionar la opción "Otros" y desmarcar las demás
        jRadioOtros.setSelected(true);
        jRadioConsulta.setSelected(false);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);

    } 
    
    
    else {
        // Llenar la consulta general para otros casos
        llenarConsulta();

        // Habilitar todas las opciones
        jRadioConsulta.setEnabled(true);
        jRadioProc.setEnabled(true);
        jRadioQuirurgico.setEnabled(true);
        jRadioOtros.setEnabled(true);

        // Seleccionar la opción por defecto (Consulta)
        jRadioConsulta.setSelected(true);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);
        jRadioOtros.setSelected(false);

        // Cambiar a la pestaña de opciones generales
        jTabbedPane2.setSelectedIndex(1);
    }


    jTextsearch.setEnabled(false);


    cancelarTodo();


}
    }//GEN-LAST:event_JComboEspecialidadItemStateChanged

    private void JComboServItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboServItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboServItemStateChanged

    private void BtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemoveActionPerformed
      int fila = jTableServ.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA SERVICIOS/PRODUCTOS", "Fila",1);
        } else {
   

        try {
            int FilaRemover;
            DefaultTableModel Mode = (DefaultTableModel) jTableServ.getModel();
            FilaRemover = this.jTableServ.getSelectedRow();
            Mode.removeRow(FilaRemover);
            calcularTotales();
            
            
            if(JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
    
           calcularFlatLab(); 
           double clcDeduccion = 0.15 * sumaLaboratorio;
           jTextDcFlat1.setText(String.format(Locale.US, "%.2f", clcDeduccion));
           }
    
    
          else{
          calcularSumaEco(); 
          double clcDeduccion = 0.15 * sumaEco;
          jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion));
          calDeducciones();
          }
            
            
            
            
            
            
            
          

        } catch (Exception e){ System.out.println("" + e);
        }}
     
    }//GEN-LAST:event_BtnRemoveActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
     
        if(corregir==true){
            
            backtoConsulta();
        }
        
        else if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea cancelar?",
            " Cancelar Honorarios", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
        cancelarTodo(); 
        corregir=false;
            } 
            
            
         
            
            

    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void jTextBrutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBrutoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBrutoFocusGained

    private void jTextBrutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBrutoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBrutoFocusLost

    private void jTextBrutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBrutoKeyTyped
           char car = evt.getKeyChar();
    String caracteres = jTextBruto.getText();

// Verifica si el carácter es válido
boolean esCaracterValido = car == '.'  ||
                           (car >= '0' && car <= '9') || car == KeyEvent.VK_DELETE;

if (esCaracterValido) {
    // Permite el ingreso solo si la longitud del texto es menor a 14
    if (caracteres.length() >= 14 && car != KeyEvent.VK_DELETE) {
        evt.consume();
    }
} else {
    // Evita el ingreso de caracteres no válidos
    evt.consume();
}
    }//GEN-LAST:event_jTextBrutoKeyTyped

    private void jTextPorcentajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPorcentajeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPorcentajeFocusGained

    private void jTextPorcentajeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPorcentajeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPorcentajeFocusLost

    private void jTextPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPorcentajeKeyTyped
              char car = evt.getKeyChar();
    String caracteres = jTextPorcentaje.getText();

// Verifica si el carácter es válido
boolean esCaracterValido =  (car >= '0' && car <= '9') || car == KeyEvent.VK_DELETE;

if (esCaracterValido) {
    // Permite el ingreso solo si la longitud del texto es menor a 14
    if (caracteres.length() >= 3 && car != KeyEvent.VK_DELETE) {
        evt.consume();
    }
} else {
    // Evita el ingreso de caracteres no válidos
    evt.consume();
}
    }//GEN-LAST:event_jTextPorcentajeKeyTyped

    private void jTextNetoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNetoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNetoFocusGained

    private void jTextNetoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNetoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNetoFocusLost

    private void jTextNetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNetoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNetoKeyTyped

    private void jTextAnticipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAnticipoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAnticipoFocusGained

    private void jTextAnticipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAnticipoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAnticipoFocusLost

    private void jTextAnticipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAnticipoKeyTyped
     validarEntrada(jTextAnticipo, evt);
    }//GEN-LAST:event_jTextAnticipoKeyTyped

    private void jTextDcFlatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcFlatFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcFlatFocusGained

    private void jTextDcFlatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcFlatFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcFlatFocusLost

    private void jTextDcFlatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcFlatKeyTyped
           validarEntrada(jTextDcFlat, evt);
    }//GEN-LAST:event_jTextDcFlatKeyTyped

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed

        
     
  int cantidad = (int) jSpinnerCant.getValue();  
  int rowCount = jTableServ.getRowCount();
  Object valueToFind = JComboServ.getSelectedItem().toString();
  boolean exists = false;

for (int i = 0; i < rowCount; i++) {
    Object cellValue = jTableServ.getValueAt(i,0);
    if (cellValue != null && cellValue.equals(valueToFind)) {
        exists = true;
        break;
    }
}

if (exists) {
    JOptionPane.showMessageDialog(null, "NO PUEDEN EXISTIR ELEMENTOS REPETIDOS EN LA TABLA", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
}



else{
    
    addHonorario(); calcularTotales();


    if(JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
    
    calcularFlatLab(); 
    double clcDeduccion = 0.15 * sumaLaboratorio;
    jTextDcFlat1.setText(String.format(Locale.US, "%.2f", clcDeduccion));
    calDeduccionesLab();
    }
    
    
    
  else if (JComboEspecialidad.getSelectedItem().equals("Odontologia") || 
         JComboEspecialidad.getSelectedItem().equals("Espirometria") || 
         JComboEspecialidad.getSelectedItem().equals("Tecnico RX")) {

    // Llamar al método para calcular la suma de los valores
    double suma = calcularFlatGeneral();

    // Calcular la deducción solo si la suma es válida
    if (suma > 0) {
        double clcDeduccion = 0.15 * suma; // 15% de la suma total
        jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion)); // Mostrar la deducción
        calDeducciones();
    } 
}
    
  
          
    
  else if(JComboEspecialidad.getSelectedItem().equals("Ecografia")){
    calcularSumaEco(); 
    double clcDeduccion = 0.15 * sumaEco;
    jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion));
    calDeducciones();}

    
    else{
          
    calcularSumaGeneral();
    double clcDeduccion = 0.15 * sumaEco;
    jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion));
    calDeducciones();
          }
    
    
    
    
    
    jSpinnerCant.setValue(1);
    jTextBruto.setText("0");
    jTextPorcentaje.setText("0");
    jTextBrutoTotal.setText("0");
    jTextNeto.setText("0");
    
    
    
    
    
}  
    }//GEN-LAST:event_BtnAddActionPerformed

    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    private void BtnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVisualizarActionPerformed

try{
    pdf();
    Desktop.getDesktop().open(file);
}catch(Exception e){System.out.println(e);}

      
    }//GEN-LAST:event_BtnVisualizarActionPerformed

    private void jTextISRLFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextISRLFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextISRLFocusGained

    private void jTextISRLFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextISRLFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextISRLFocusLost

    private void jTextISRLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextISRLKeyTyped
       validarEntrada(jTextISRL, evt);
    }//GEN-LAST:event_jTextISRLKeyTyped

    private void jTextDcAlmcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcAlmcFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcAlmcFocusGained

    private void jTextDcAlmcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcAlmcFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcAlmcFocusLost

    private void jTextDcAlmcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcAlmcKeyTyped
     validarEntrada(jTextDcAlmc, evt);
    }//GEN-LAST:event_jTextDcAlmcKeyTyped

    private void jTableServMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServMouseClicked
     /*   int fila = JTableHorario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
            BtnModif.setEnabled(true);
            BtnAg.setEnabled(false);

            // Retrieve and check each value for null before converting to string
            Object value = JTableHorario.getValueAt(fila, 0);
            idHorarios = (value != null) ? (int) value : 0; // Default or error value if needed

            JComboEspecialidad1.setSelectedItem(getStringValueOrDefault(JTableHorario.getValueAt(fila, 1)));
            JComboDoctor.setSelectedItem(getStringValueOrDefault(JTableHorario.getValueAt(fila, 2)));
            jTextEntrada.setText(getStringValueOrDefault(JTableHorario.getValueAt(fila, 3)));
            jTextSalida.setText(getStringValueOrDefault(JTableHorario.getValueAt(fila, 4)));
            EstadoTexto = getStringValueOrDefault(JTableHorario.getValueAt(fila, 7));
            jTextObservacion.setText(getStringValueOrDefault(JTableHorario.getValueAt(fila, 8)));
            variableTiempo=(JTableHorario.getValueAt(fila, 5).toString());

            // Handle the time of day for radio buttons
            updateTimeRadioButtons(getStringValueOrDefault(JTableHorario.getValueAt(fila, 5)));

            BtnModif2.setEnabled(true);
        }*/
    }//GEN-LAST:event_jTableServMouseClicked

    private void jTextTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTotalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTotalFocusGained

    private void jTextTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTotalFocusLost

    private void jTextTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTotalKeyTyped

    private void jTextDcPersonalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcPersonalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcPersonalFocusGained

    private void jTextDcPersonalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcPersonalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcPersonalFocusLost

    private void jTextDcPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcPersonalKeyTyped
    validarEntrada(jTextDcPersonal, evt);
    }//GEN-LAST:event_jTextDcPersonalKeyTyped

    private void jTextCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextCantidadFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCantidadFocusGained

    private void jTextCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextCantidadFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCantidadFocusLost

    private void jTextCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextCantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCantidadKeyTyped

    private void jTextDccFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDccFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDccFocusGained

    private void jTextDccFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDccFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDccFocusLost

    private void jTextDccKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDccKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDccKeyTyped

    private void BtnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcesarActionPerformed
    
    knowCode(); 
    knowIdSpecialty();
    pdf();
    String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    String rutaArchivo = "C:\\Fundaginebra\\Reportes\\ReporteHonorario.pdf";
   
    String sql2 = "SELECT `id_especialista`, `id_especialidad`, `Ho_estado`, `Ho_fechaOne`, `Ho_fechaTwo`\n" +
    "FROM `honorarios`\n" +
    "WHERE `id_especialista` = ?\n" +
    "AND `id_especialidad` = ?\n" +
    "AND (\n" +
    "  (`Ho_estado` = 108 AND `Ho_fechaOne` BETWEEN ? AND ?)\n" +
    "  OR\n" +
    "  (`Ho_estado` IN (108, 108) AND `Ho_fechaOne` BETWEEN ? AND ?))";

/*
    
    108 en todo para quitar la condicional
    
    lo de abajo agrega las condicionales
"  (`Ho_estado` = 106 AND `Ho_fechaOne` BETWEEN ? AND ?)\n" +
"  OR\n" +
"  (`Ho_estado` IN (103, 107) AND `Ho_fechaOne` BETWEEN ? AND ?))";
*/

try (
    Connection con = EnlaceBd.getConnection(); 
    PreparedStatement ps = con.prepareStatement(sql2); 
) {

    ps.setInt(1, idpersonal); 
    ps.setInt(2, idSpecialty); 
    ps.setString(3, fecha1); 
    ps.setString(4, fecha2); 
    ps.setString(5, fecha1); 
    ps.setString(6, fecha2); 


    try (ResultSet rs = ps.executeQuery()) { 
        if (rs.next()) {
            int estado = rs.getInt("Ho_estado");
         
            if (estado == 106) {
               JOptionPane.showMessageDialog(this, "EL ESPECIALISTA: "+JComboDoctor.getSelectedItem().toString()+" YA TIENE UN REPORTE PENDIENTE EN HONORARIOS", "HONORARIO", JOptionPane.ERROR_MESSAGE);

            } else if (estado == 103 || estado == 107) {
             
                 JOptionPane.showMessageDialog(this, "EL ESPECIALISTA: "+ JComboDoctor.getSelectedItem().toString()+" YA TIENE UN REPORTE EN ESTE RANGO DE FECHAS, SI DESEA GENERAR OTRO DEBE CANCELAR EL EXISTENTE", "HONORARIO", JOptionPane.ERROR_MESSAGE);
            }
            
        } 
         else if(jTextDcFlat1.getText().trim().isEmpty() || jTextISRL1.getText().trim().isEmpty() || jTextDcAlmc1.getText().trim().isEmpty() ||
                 jTextDcNomina.getText().trim().isEmpty() || jTextDcIncine1.getText().trim().isEmpty() || jTextAnticipo1.getText().trim().isEmpty() ||
                 jTextBioseguridad.getText().trim().isEmpty() || jTextOtherDc1.getText().trim().isEmpty() || JTextPrtjFundacion.getText().trim().isEmpty() ||
                 jTextDcFlat.getText().trim().isEmpty() || jTextAnticipo.getText().trim().isEmpty() || jTextOtherDc.getText().trim().isEmpty() ||
                 jTextDcAlmc.getText().trim().isEmpty() || jTextISRL.getText().trim().isEmpty() || jTextDcPersonal.getText().trim().isEmpty()|| 
                 jTextCantidad.getText().trim().isEmpty() || jTextSubtotal.getText().trim().isEmpty() || jTextDcc.getText().trim().isEmpty() || 
                 jTextTotal.getText().trim().isEmpty())
         {  JOptionPane.showMessageDialog(this, "TODOS LOS CAMPOS DEBEN TENER UN VALOR NUMERICO, NO PUEDEN QUEDAR VACIOS, EN SU LUGAR COLOQUE 0", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(JComboDoctor.getSelectedItem().equals("")){
         JOptionPane.showMessageDialog(this, "ESTA ESPECIALIDAD NO TIENE ESPECIALISTAS ASIGNADOS AUN", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(jTableServ.getRowCount()==0){
         JOptionPane.showMessageDialog(this, "DEBE AGREGAR AL MENOS UN REGISTRO A LA TABLA PARA PROCESAR", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(fecha1.equals(fecha2)){
         JOptionPane.showMessageDialog(this, "EL RANGO DE FECHAS NO PUEDE SER DEL MISMO DIA", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if (fecha1.compareTo(fecha2) > 0) {
         JOptionPane.showMessageDialog(this, "LA FECHA DE INICIO NO PUEDE SER POSTERIOR A LA FECHA DEL FIN.", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         return;} 
         
         else {
      
             
             
             
             
             
             
             
           //---------------AGREGAR---------------------------------------------- 
             
            if(corregir==false){
          
             guardarHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, rutaArchivo);

            // Agregar deducciones según la especialidad seleccionada
            if (JComboEspecialidad.getSelectedItem().equals("Bioanalista")) {
                agregarDeducciones(
                    jTextDcFlat1.getText(), 
                    jTextISRL1.getText(), 
                    null, 
                    jTextDcAlmc1.getText(), 
                    jTextDcNomina.getText(), 
                    jTextDcIncine1.getText(), 
                    jTextAnticipo1.getText(), 
                    jTextBioseguridad.getText(), 
                    JTextPrtjFundacion.getText(),
                    jTextOtherDc1.getText(),
                    jTextObservacion.getText()
                 
                ); 
            } else {
                  agregarDeducciones(
                    jTextDcFlat.getText(), 
                    jTextISRL.getText(), 
                    jTextDcPersonal.getText(), 
                    jTextDcAlmc.getText(), 
                    null, 
                    null, 
                    jTextAnticipo.getText(), 
                    null, 
                    null,
                    jTextOtherDc.getText(),
                    jTextObservacion.getText()
                          
                );
            }

            // Agregar resumen y servicios
            agregarResumen(jTextCantidad.getText(), jTextotalNeto.getText(), jTextDcc.getText(), jTextTotal.getText(), jTextFacturar.getText());
            agregarServicios();
    

            JOptionPane.showMessageDialog(null, "HONORARIO GENERADO...", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
            cancelarTodo();
            }
            
            
            //---------------------------CORREGIR---------------------------------------
            else if(corregir==true){
            
            
           //    if (confirmarSobrescribir()) {
            
                updateHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, rutaArchivo); //cambiar 107 a 106
                
                // Dependiendo de la especialidad, actualizar deducciones
                if (esBioanalista()) {
                    updateDeduccionesBioanalista();
                } else {
                    updateDeduccionesGenerales();
                }

                // Actualizar resumen y servicios
                updateResumen(jTextCantidad.getText(), jTextotalNeto.getText(), jTextDcc.getText(), jTextTotal.getText(),  jTextFacturar.getText());
                updateServicios();

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "HONORARIO CORREGIDO", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
                backtoConsulta();
           // }
            
            }
   
            
     
         
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}

        
        
        
    }//GEN-LAST:event_BtnProcesarActionPerformed

    
    
    /*
           
    public void btnProcesar() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    knowIdSpecialty();
    String rutaArchivo = "C:\\Fundaginebra\\Reportes\\ReporteHonorario.pdf";
    // Obtener fecha en formato adecuado
    String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    try {
        // Establecer conexión con la base de datos
        con = cn.getConnection();
        // Verificar si el honorario ya existe
        boolean existe = existeRegistro(con, "honorarios", "id_horonario", maxId);

        // Si el honorario ya existe, actualizarlo
        if (existe) {
            if (confirmarSobrescribir()) {
                // Actualizar honorario
               guardarHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, rutaArchivo);
                
                // Dependiendo de la especialidad, actualizar deducciones
                if (esBioanalista()) {
                    updateDeduccionesBioanalista();
                } else {
                    updateDeduccionesGenerales();
                }

                // Actualizar resumen y servicios
                updateResumen(jTextCantidad.getText(), jTextSubtotal.getText(), jTextDcc.getText(), jTextTotal.getText(),  jTextFacturar.getText());
                updateServicios();

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "HONORARIO ACTUALIZADO", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Si no existe, guardar un nuevo honorario
            guardarHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, null);
            
            // Dependiendo de la especialidad, agregar deducciones
            if (esBioanalista()) {
                agregarDeduccionesBioanalista();
            } else {
                agregarDeduccionesGenerales();
            }

            // Agregar resumen y servicios
            agregarResumen(jTextCantidad.getText(), jTextSubtotal.getText(), jTextDcc.getText(), jTextTotal.getText(),  jTextFacturar.getText());
            agregarServicios();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "HONORARIO GUARDADO", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception e) {
        System.out.println("ERROR EN BTN GUARDAR: " + e);
    }
}
    
    */
    
       public void backtoConsulta(){
   
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       ConsultaHonorarios jhonorario = new  ConsultaHonorarios();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jhonorario);
       jhonorario.setClosable(true);
       jhonorario.setIconifiable(true);
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       try{
         
       
       jhonorario.FechaOne.setDate(FechaOne.getDate());
       jhonorario.FechaTwo.setDate(FechaTwo.getDate());
       jhonorario.limpiarTabla();
       jhonorario.checkFromHonor();
       jhonorario.conteoTabla();
       
       }catch(Exception e){
               System.out.println(e);
               }
     
       
       
       
        try {
            jhonorario.setMaximum(true);
        } catch (Exception e) {
        }
       jhonorario.toFront();
       jhonorario.setVisible(true);

   
   }
    
    
    
    private void jTextOtherDcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextOtherDcFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOtherDcFocusGained

    private void jTextOtherDcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextOtherDcFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOtherDcFocusLost

    private void jTextOtherDcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextOtherDcKeyTyped
         validarEntrada(jTextOtherDc, evt);
    }//GEN-LAST:event_jTextOtherDcKeyTyped

    private void jRadioProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioProcActionPerformed
        jRadioConsulta.setSelected(false);
        jRadioProc.setSelected(true);
        jRadioQuirurgico.setSelected(false);
        jRadioOtros.setSelected(false);
        llenarProcedimientos();
        
        jTextsearch.setEnabled(true);
        
    }//GEN-LAST:event_jRadioProcActionPerformed

    private void jRadioConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioConsultaActionPerformed
        jRadioConsulta.setSelected(true);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);
        jRadioOtros.setSelected(false);
        llenarConsulta();  
        jTextsearch.setEnabled(false);
        jTextsearch.setText("");
    }//GEN-LAST:event_jRadioConsultaActionPerformed

    private void jRadioQuirurgicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioQuirurgicoActionPerformed
        jRadioConsulta.setSelected(false);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(true);
        jRadioOtros.setSelected(false);
        llenarQuirurgico();
        jTextsearch.setEnabled(false);
        jTextsearch.setText("");
    }//GEN-LAST:event_jRadioQuirurgicoActionPerformed

    private void jRadioOtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioOtrosActionPerformed
        jRadioConsulta.setSelected(false);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);
        jRadioOtros.setSelected(true);
        llenarGuardias();
        jTextsearch.setEnabled(false);
        jTextsearch.setText("");
        
    }//GEN-LAST:event_jRadioOtrosActionPerformed

    private void jTextBrutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBrutoKeyReleased
 
        
        if(!jTextBruto.getText().equals("") && !jTextPorcentaje.getText().equals("")){
          calcularMonto();
          calcularTotalBruto();
        }
        else {
        jTextNeto.setText("0");
        }
        
      
    }//GEN-LAST:event_jTextBrutoKeyReleased

    private void jTextPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPorcentajeKeyReleased
          
        if(!jTextBruto.getText().equals("") && !jTextPorcentaje.getText().equals("")){
          calcularMonto();
        }
        else {
        jTextNeto.setText("0");
        }
        
    }//GEN-LAST:event_jTextPorcentajeKeyReleased

    private void jSpinnerCantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinnerCantMouseClicked
   
    }//GEN-LAST:event_jSpinnerCantMouseClicked

    private void jTextSubtotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSubtotalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSubtotalFocusGained

    private void jTextSubtotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSubtotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSubtotalFocusLost

    private void jTextSubtotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSubtotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSubtotalKeyTyped

    private void jSpinnerCantPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerCantPropertyChange
       calcularMonto();
    }//GEN-LAST:event_jSpinnerCantPropertyChange

    private void jTextDcPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcPersonalKeyReleased

        calDeducciones();
    }//GEN-LAST:event_jTextDcPersonalKeyReleased

    private void jTextOtherDcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextOtherDcKeyReleased
           calDeducciones();
    }//GEN-LAST:event_jTextOtherDcKeyReleased

    private void jTextAnticipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAnticipoKeyReleased
        calDeducciones();
    }//GEN-LAST:event_jTextAnticipoKeyReleased

    private void jTextDcAlmcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcAlmcKeyReleased
   calDeducciones();
    }//GEN-LAST:event_jTextDcAlmcKeyReleased

    private void jISRLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jISRLActionPerformed

        
      double  clcIsrl= Double.parseDouble(jTextIsrlPrcj.getText())/100;
      double resta= Double.parseDouble(jTextIsrlPrcj1.getText());
    
      
       if(jISRL.isSelected() && !JComboEspecialidad.getSelectedItem().equals("Bioanalista")){

      double subTotal = 0;
      int prctaje = 0; 
   
      try {
            subTotal = Double.parseDouble(jTextTotal.getText());
       
        } catch (NumberFormatException e) {
         
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
     calculateIRL(subTotal, clcIsrl, resta);
     calDeducciones();  

       }
       
       
     //--------------------------------------------------------------------------------------------------------------------------  
       
      else if(jISRL.isSelected() && JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
     
        
      double subTotal = 0;
      int prctaje = 0; 
   
      try {
            subTotal = Double.parseDouble(jTextTotal.getText());
       
        } catch (NumberFormatException e) {
         
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
     calculateIRLLab(subTotal, clcIsrl);
     calDeduccionesLab();  
     
     
       
       }
       
       
       
       
       
       
       
       
       else{
       if(!jISRL.isSelected() && !JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
       jTextISRL.setText("0");
       calDeducciones(); 
         }
       
      else if(!jISRL.isSelected() && JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
          jTextISRL1.setText("0");
          calDeduccionesLab();
        }
       }
        
        
    }//GEN-LAST:event_jISRLActionPerformed

    private void jTextDcFlat1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcFlat1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcFlat1FocusGained

    private void jTextDcFlat1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcFlat1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcFlat1FocusLost

    private void jTextDcFlat1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcFlat1KeyTyped
         char car = evt.getKeyChar();
    String caracteres = jTextDcFlat1.getText();

// Verifica si el carácter es válido
boolean esCaracterValido = car == '.'  ||
                           (car >= '0' && car <= '9') || car == KeyEvent.VK_DELETE;

if (esCaracterValido) {
    // Permite el ingreso solo si la longitud del texto es menor a 14
    if (caracteres.length() >= 14 && car != KeyEvent.VK_DELETE) {
        evt.consume();
    }
} else {
    // Evita el ingreso de caracteres no válidos
    evt.consume();
}
    }//GEN-LAST:event_jTextDcFlat1KeyTyped

    private void jTextISRL1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextISRL1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextISRL1FocusGained

    private void jTextISRL1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextISRL1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextISRL1FocusLost

    private void jTextISRL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextISRL1KeyTyped
    char car = evt.getKeyChar();
    String caracteres = jTextISRL1.getText();

    // Verifica si el carácter es válido
    boolean esCaracterValido = car == '.'  ||
                           (car >= '0' && car <= '9') || car == KeyEvent.VK_DELETE;

    if (esCaracterValido) {
    // Permite el ingreso solo si la longitud del texto es menor a 14
    if (caracteres.length() >= 14 && car != KeyEvent.VK_DELETE) {
        evt.consume();
    }
    } else {
    // Evita el ingreso de caracteres no válidos
     evt.consume();
     }
    }//GEN-LAST:event_jTextISRL1KeyTyped

    private void jTextDcIncine1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcIncine1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcIncine1FocusGained

    private void jTextDcIncine1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcIncine1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcIncine1FocusLost

    private void jTextDcIncine1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcIncine1KeyReleased
      calDeduccionesLab();
    }//GEN-LAST:event_jTextDcIncine1KeyReleased

    private void jTextDcIncine1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcIncine1KeyTyped
     validarEntrada(jTextDcIncine1, evt); 
    }//GEN-LAST:event_jTextDcIncine1KeyTyped

    private void jTextDcNominaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcNominaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcNominaFocusGained

    private void jTextDcNominaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcNominaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcNominaFocusLost

    private void jTextDcNominaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcNominaKeyReleased
       calDeduccionesLab();
    }//GEN-LAST:event_jTextDcNominaKeyReleased

    private void jTextDcNominaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcNominaKeyTyped
      validarEntrada(jTextDcNomina, evt); 
    }//GEN-LAST:event_jTextDcNominaKeyTyped

    private void jTextDcAlmc1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcAlmc1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcAlmc1FocusGained

    private void jTextDcAlmc1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDcAlmc1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDcAlmc1FocusLost

    private void jTextDcAlmc1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcAlmc1KeyReleased
         calDeduccionesLab();
    }//GEN-LAST:event_jTextDcAlmc1KeyReleased

    private void jTextDcAlmc1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcAlmc1KeyTyped
    validarEntrada(jTextDcAlmc1, evt); 
    }//GEN-LAST:event_jTextDcAlmc1KeyTyped

    private void jTextBioseguridadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBioseguridadFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBioseguridadFocusGained

    private void jTextBioseguridadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBioseguridadFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBioseguridadFocusLost

    private void jTextBioseguridadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBioseguridadKeyReleased
       calDeduccionesLab();
    }//GEN-LAST:event_jTextBioseguridadKeyReleased

    private void jTextBioseguridadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBioseguridadKeyTyped
    validarEntrada(jTextBioseguridad, evt); 
    }//GEN-LAST:event_jTextBioseguridadKeyTyped

    private void jTextOtherDc1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextOtherDc1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOtherDc1FocusGained

    private void jTextOtherDc1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextOtherDc1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextOtherDc1FocusLost

    private void jTextOtherDc1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextOtherDc1KeyReleased
         calDeduccionesLab();
    }//GEN-LAST:event_jTextOtherDc1KeyReleased

    private void jTextOtherDc1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextOtherDc1KeyTyped
          validarEntrada(jTextOtherDc1, evt); 
    }//GEN-LAST:event_jTextOtherDc1KeyTyped

    private void jTextAnticipo1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAnticipo1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAnticipo1FocusGained

    private void jTextAnticipo1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAnticipo1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAnticipo1FocusLost

    private void jTextAnticipo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAnticipo1KeyReleased
        calDeduccionesLab();
    }//GEN-LAST:event_jTextAnticipo1KeyReleased

    private void jTextAnticipo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAnticipo1KeyTyped
       validarEntrada(jTextAnticipo1, evt); 
    }//GEN-LAST:event_jTextAnticipo1KeyTyped

    private void JTextPrtjFundacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTextPrtjFundacionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextPrtjFundacionFocusGained

    private void JTextPrtjFundacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTextPrtjFundacionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextPrtjFundacionFocusLost

    private void JTextPrtjFundacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextPrtjFundacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextPrtjFundacionKeyReleased

    private void JTextPrtjFundacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextPrtjFundacionKeyTyped
    validarEntrada(JTextPrtjFundacion, evt); 
    }//GEN-LAST:event_JTextPrtjFundacionKeyTyped

    private void jRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoveActionPerformed
        int fila = jTableServ.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA SERVICIOS/PRODUCTOS", "Fila",1);
        } else {
   

            
            
            
            
        try {
            int FilaRemover;
            DefaultTableModel Mode = (DefaultTableModel) jTableServ.getModel();
            FilaRemover = this.jTableServ.getSelectedRow();
            Mode.removeRow(FilaRemover);
            calcularTotales();
            
            
            
            if(Mode.getRowCount()<=0){
            cancelarTodo();
            return;
            }
            
          else  if(JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
    
           calcularFlatLab(); 
           double clcDeduccion = 0.15 * sumaLaboratorio;
           jTextDcFlat1.setText(String.format(Locale.US, "%.2f", clcDeduccion));
           }
    
    
            
            
          else if (JComboEspecialidad.getSelectedItem().equals("Odontologia") || 
         JComboEspecialidad.getSelectedItem().equals("Espirometria") || 
         JComboEspecialidad.getSelectedItem().equals("Tecnico RX")) {

         // Llamar al método para calcular la suma de los valores
         double suma = calcularFlatGeneral();

         // Calcular la deducción solo si la suma es válida
         if (suma > 0) {
         double clcDeduccion = 0.15 * suma; // 15% de la suma total
        jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion)); // Mostrar la deducción
        calDeducciones();
        } 
        } 
   
            
            
            
            
          else{
          calcularSumaEco(); 
          double clcDeduccion = 0.15 * sumaEco;
          jTextDcFlat.setText(String.format(Locale.US, "%.2f", clcDeduccion));
          calDeducciones();
          }
            
            
            
            
            
            
            
          

        } catch (Exception e){ System.out.println("" + e);
        }}
    }//GEN-LAST:event_jRemoveActionPerformed

    private void jSpinnerCantFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinnerCantFocusLost
         calcularMonto();
    }//GEN-LAST:event_jSpinnerCantFocusLost

    private void jTextsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextsearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextsearchFocusGained

    private void jTextsearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextsearchFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextsearchFocusLost

    private void jTextsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextsearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextsearchKeyTyped

    private void jTextsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextsearchKeyReleased
        if (JComboServ.getItemCount() == 0 || jTextsearch.getText().isEmpty()) {
             llenarProcedimientos();
        } else {
            filterComboBox();
        }
    }//GEN-LAST:event_jTextsearchKeyReleased

    private void jSpinnerCantMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinnerCantMouseExited

    }//GEN-LAST:event_jSpinnerCantMouseExited

    private void jSpinnerCantStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerCantStateChanged
         calcularMonto();
         actualizarValores();

    
    
         
    }//GEN-LAST:event_jSpinnerCantStateChanged

    private void JComboDoctorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboDoctorItemStateChanged
 if (JComboDoctor.getSelectedIndex() != -1) {
        knowIdSpecialist();
 }
    }//GEN-LAST:event_JComboDoctorItemStateChanged

    private void jTextBrutoTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBrutoTotalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBrutoTotalFocusGained

    private void jTextBrutoTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextBrutoTotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBrutoTotalFocusLost

    private void jTextBrutoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBrutoTotalKeyReleased
          if(!jTextBrutoTotal.getText().equals("") && !jTextPorcentaje.getText().equals("")){
          calcularMonto();
          calcularUnitBruto();
        }
        else {
        jTextNeto.setText("0");
        }
        
    }//GEN-LAST:event_jTextBrutoTotalKeyReleased

    private void jTextBrutoTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBrutoTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBrutoTotalKeyTyped

    private void jTextIsrlPrcjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIsrlPrcjFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcjFocusGained

    private void jTextIsrlPrcjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIsrlPrcjFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcjFocusLost

    private void jTextIsrlPrcjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextIsrlPrcjKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcjKeyTyped

    private void jTextIsrlPrcj1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIsrlPrcj1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcj1FocusGained

    private void jTextIsrlPrcj1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIsrlPrcj1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcj1FocusLost

    private void jTextIsrlPrcj1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextIsrlPrcj1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIsrlPrcj1KeyTyped

    

    
  
    
    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed

        
String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
String rutaArchivo = "C:\\Fundaginebra\\Reportes\\ReporteHonorario.pdf";
   
String sql2 = "SELECT `id_especialista`, `id_especialidad`, `Ho_estado`, `Ho_fechaOne`, `Ho_fechaTwo`\n" +
"FROM `honorarios`\n" +
"WHERE `id_especialista` = ?\n" +
"AND `id_especialidad` = ?\n" +
"AND (\n" +
"  (`Ho_estado` = 108 AND `Ho_fechaOne` BETWEEN ? AND ?)\n" +
"  OR\n" +
"  (`Ho_estado` IN (108, 108) AND `Ho_fechaOne` BETWEEN ? AND ?))";

/*
"  (`Ho_estado` = 106 AND `Ho_fechaOne` BETWEEN ? AND ?)\n" +
"  OR\n" +
"  (`Ho_estado` IN (103, 107) AND `Ho_fechaOne` BETWEEN ? AND ?))";
*/

try (
    Connection con = EnlaceBd.getConnection(); 
    PreparedStatement ps = con.prepareStatement(sql2); 
) {

    ps.setInt(1, idpersonal); 
    ps.setInt(2, idSpecialty); 
    ps.setString(3, fecha1); 
    ps.setString(4, fecha2); 
    ps.setString(5, fecha1); 
    ps.setString(6, fecha2); 


    try (ResultSet rs = ps.executeQuery()) { 
        if (rs.next()) {
            int estado = rs.getInt("Ho_estado");
         
            if (estado == 106) {
               JOptionPane.showMessageDialog(this, "EL ESPECIALISTA: "+JComboDoctor.getSelectedItem().toString()+" YA TIENE UN REPORTE PENDIENTE EN HONORARIOS", "HONORARIO", JOptionPane.ERROR_MESSAGE);

            } else if (estado == 103 || estado == 107) {
             
                 JOptionPane.showMessageDialog(this, "EL ESPECIALISTA: "+ JComboDoctor.getSelectedItem().toString()+" YA TIENE UN REPORTE EN ESTE RANGO DE FECHAS, SI DESEA GENERAR OTRO DEBE CANCELAR EL EXISTENTE", "HONORARIO", JOptionPane.ERROR_MESSAGE);
            }
            
        } 
         else if(jTextDcFlat1.getText().trim().isEmpty() || jTextISRL1.getText().trim().isEmpty() || jTextDcAlmc1.getText().trim().isEmpty() ||
                 jTextDcNomina.getText().trim().isEmpty() || jTextDcIncine1.getText().trim().isEmpty() || jTextAnticipo1.getText().trim().isEmpty() ||
                 jTextBioseguridad.getText().trim().isEmpty() || jTextOtherDc1.getText().trim().isEmpty() || JTextPrtjFundacion.getText().trim().isEmpty() ||
                 jTextDcFlat.getText().trim().isEmpty() || jTextAnticipo.getText().trim().isEmpty() || jTextOtherDc.getText().trim().isEmpty() ||
                 jTextDcAlmc.getText().trim().isEmpty() || jTextISRL.getText().trim().isEmpty() || jTextDcPersonal.getText().trim().isEmpty()|| 
                 jTextCantidad.getText().trim().isEmpty() || jTextSubtotal.getText().trim().isEmpty() || jTextDcc.getText().trim().isEmpty() || 
                 jTextTotal.getText().trim().isEmpty())
         {  JOptionPane.showMessageDialog(this, "TODOS LOS CAMPOS DEBEN TENER UN VALOR NUMERICO, NO PUEDEN QUEDAR VACIOS, EN SU LUGAR COLOQUE 0", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(JComboDoctor.getSelectedItem().equals("")){
         JOptionPane.showMessageDialog(this, "ESTA ESPECIALIDAD NO TIENE ESPECIALISTAS ASIGNADOS AUN", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(jTableServ.getRowCount()==0){
         JOptionPane.showMessageDialog(this, "DEBE AGREGAR AL MENOS UN REGISTRO A LA TABLA PARA PROCESAR", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if(fecha1.equals(fecha2)){
         JOptionPane.showMessageDialog(this, "EL RANGO DE FECHAS NO PUEDE SER DEL MISMO DIA", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         }else if (fecha1.compareTo(fecha2) > 0) {
         JOptionPane.showMessageDialog(this, "LA FECHA DE INICIO NO PUEDE SER POSTERIOR A LA FECHA DEL FIN.", "HONORARIO", JOptionPane.ERROR_MESSAGE);
         return;}
    
         else{
                 
                   btnGuardar();
                 
                 }}
    
    
    
    
    
    }catch(Exception e){} 
        
        
        
      
   
        
    }//GEN-LAST:event_BtnGuardarActionPerformed

    
    
    
    
    
    
    
    
    public void insertarHonorario() {
    // Establecer la conexión a la base de datos usando un bloque try-with-resources
    String sql = "INSERT INTO `table_revision`(`Id_historiaR`) VALUES (?)";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Asignar el valor al parámetro
        ps.setInt(1, maxId);

        // Ejecutar la actualización
        ps.executeUpdate();

    } catch (Exception e) {
   
     System.out.println("ERROR EN INSERTAR Honorario: " + e.getMessage());
    } 
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void jTextFacturarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFacturarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFacturarFocusGained

    private void jTextFacturarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFacturarFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFacturarFocusLost

    private void jTextFacturarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFacturarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFacturarKeyTyped

    private void jTextotalNetoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextotalNetoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextotalNetoFocusGained

    private void jTextotalNetoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextotalNetoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextotalNetoFocusLost

    private void jTextotalNetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextotalNetoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextotalNetoKeyTyped

    private void jTextDcFlatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDcFlatKeyPressed
         calDeducciones();
    }//GEN-LAST:event_jTextDcFlatKeyPressed

    

    

        private void filterComboBox() {
        String filterText = jTextsearch.getText().toLowerCase();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (int i = 0; i < JComboServ.getItemCount(); i++) {
            String item = (String) JComboServ.getItemAt(i);
            if (item.toLowerCase().contains(filterText)) {
                model.addElement(item);
            }
        }
       JComboServ.setModel(model);
       JComboServ.setPopupVisible(model.getSize() > 0);
    }
     
    
    
    
    
    
     public static void validarEntrada(JTextField textField, KeyEvent evt) {
        char car = evt.getKeyChar();
        String caracteres = textField.getText();

        // Verifica si el carácter es válido
        boolean esCaracterValido = car == '.' ||
                                   (car >= '0' && car <= '9') || car == KeyEvent.VK_DELETE;

        if (esCaracterValido) {
            // Permite el ingreso solo si la longitud del texto es menor a 14
            if (caracteres.length() >= 14 && car != KeyEvent.VK_DELETE) {
                evt.consume();
            }
        } else {
            // Evita el ingreso de caracteres no válidos
            evt.consume();
        }
    }
    
    
    
    
    
 
    Mprincipal MP = new Mprincipal();
    

     LlenarCombobox lc = new LlenarCombobox();
     
      public void llenarCombo() {

    
           
        try{
   
        JComboEspecialidad.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3 = lc.llenarEspecialidad();
        for (int i = 0; i < lista3.size(); i++) {
            JComboEspecialidad.addItem(lista3.get(i));
        }
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
           
          }
    
      
      
      
  
       public void llenarDrs() {

       try{
      
        JComboDoctor.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarDoctoresAsignar(); 
        for (int i = 0; i < lista.size(); i++) {
            JComboDoctor.addItem(lista.get(i));

        }
  
           }catch(Exception e ){System.out.println(e);};

          }
    
    

 
     public void llenarProcedimientos(){
 
    
        try{
      
        JComboServ.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarProcess();
        for (int i = 0; i < lista.size(); i++) {
            JComboServ.addItem(lista.get(i).toUpperCase());

        }
  
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
     
     }
        
     
     
      
     public void llenarConsultas(){
 
    
        try{
      
        JComboServ.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarProcess();
        for (int i = 0; i < lista.size(); i++) {
            JComboServ.addItem(lista.get(i));

        }
  
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
     
     }
        
     
     
     
     
     
     
     
     
     
     
    public  ArrayList<String> llenarProcess(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
          
        ArrayList<String> lista = new ArrayList<String>();
        
        
    
        String sql = "SELECT procedimiento, especialidad, Id_Pestado FROM table_procedimientos u\n" +
                
"INNER JOIN table_especialidad c\n" +
"ON u.Id_Categoria= c.id_especialidad\n" +
"\n" +
"where Id_Pestado=100 AND  especialidad LIKE '%" + JComboEspecialidad.getSelectedItem().toString() + "%'  Order by Procedimiento ASC";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
          
            while(rs.next()){
                 
            String especialidad=JComboEspecialidad.getSelectedItem().toString();
            if (especialidad.equals("BIOANALISTA")) {
            JComboServ.addItem("N/A");    
            } else{
               lista.add(rs.getString("procedimiento"));}
             
             }
              
           
            
            
        } catch (Exception e) {System.out.println(e);}
        
        
        
        finally
    {  
    try{ps.close();
    rs.close();
    con.close();
    }catch(Exception e){System.out.println(e);}    
    }
       
        return lista;
    }
    
     
    
public ArrayList<String> llenarDoctoresAsignar() {
    ArrayList<String> lista = new ArrayList<>();
    String especialidad = JComboEspecialidad.getSelectedItem().toString();
    String sql = "SELECT u.IdPersonal, Usuario, CONCAT(s.nombre, ' ', s.apellido) AS nCompleto, s.cedula, c.especialidad, Pestado " +
                 "FROM table_usuario u " +
                 "INNER JOIN table_especialidad c ON u.idEspecialidad = c.id_especialidad " +
                 "INNER JOIN table_personal s ON u.IdPersonal = s.IdPersonal " +
                 "WHERE (Pestado = 100 OR Pestado = 108) AND c.especialidad = ? " +  
                 "ORDER BY Usuario ASC";

    // Usar try-with-resources para asegurar el cierre automático de los recursos
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setString(1, especialidad);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String usuario = rs.getString("nCompleto");
                lista.add(usuario);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error en la consulta: " + e.getMessage());
    }

    return lista;
}
    
    
   
   
  
    public void llenarServicio(){
     try{
      
        JComboServ.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = lc.llenarServicio();
        for (int i = 0; i < lista.size(); i++) {
           JComboServ.addItem(lista.get(i));

        } }catch(Exception e ){JOptionPane.showMessageDialog(null,e );}}
  
   
   
   
   private void llenarQuirurgico() {
   JComboServ.removeAllItems(); // Limpiar el JComboBox

    // Agregar nuevos ítems
   JComboServ.addItem("MEDICO PRINCIPAL");
   JComboServ.addItem("PRIMER AYUDANTE");
   JComboServ.addItem("SEGUNDO AYUDANTE");
   JComboServ.addItem("ANESTESIOLOGO");
   JComboServ.addItem("VISITA HOSPITALARIA");

   
   
}
   
   
   private void llenarGuardias() {
   JComboServ.removeAllItems();
 
    // Agregar nuevos ítems
   JComboServ.addItem("INGRESOS GENERALES");
   JComboServ.addItem("INFORME DE RAYOS X");
   JComboServ.addItem("INFORME CON MEDICION");
   JComboServ.addItem("GUARDIAS DIURNAS (1er CORTE)");
   JComboServ.addItem("GUARDIAS DIURNAS (2er CORTE)");
   JComboServ.addItem("GUARDIAS NOCTURNAS (1er CORTE)");
   JComboServ.addItem("GUARDIAS NOCTURNAS (2er CORTE)");
   JComboServ.addItem("GUARDIAS NOCTURNAS LUNES A VIERNES");
   JComboServ.addItem("GUARDAS NOCTURNAS SABADO Y DOMINGO");
   JComboServ.addItem("GUARDIAS 7AM A 1 PM LUNES A VIERNES");
   JComboServ.addItem("GUARDIAS 7AM A 1 PM SABADO Y DOMINGO");
   JComboServ.addItem("SALDO A FAVOR");
   JComboServ.addItem("SUELDOS Y SALARIOS");
   JComboServ.addItem("RECTIFICACION DE DEDUCCION");
   JComboServ.addItem("OTROS LABORAT.");
   
}
   
   
   
   
   
     
     
   public void llenarBioanalisis() {
   JComboServ.removeAllItems(); // Limpiar el JComboBox


   JComboServ.addItem("INGRESO BRUTO");
   JComboServ.addItem("RECTIFICACION DE DEDUCCION");
   JComboServ.addItem("SALDO A FAVOR");
   
   
}
   

   
public void llenarConsulta() {
    try {
        JComboServ.removeAllItems();
        
        // Obtén el texto seleccionado de JComboEspecialidad
        String especialidadSeleccionada = (String) JComboEspecialidad.getSelectedItem();
        
        // Llama a llenarConsultas con la especialidad seleccionada
        ArrayList<String> lista = lc.llenarConsultas(especialidadSeleccionada);
        
        for (String servicio : lista) {
            JComboServ.addItem(servicio);
        }
        
        
     String especialidad = JComboEspecialidad.getSelectedItem().toString();

    switch (especialidad) {
    case "Ecografia":
        JComboServ.addItem("Consulta de Nutricion y Dietetica");
        break;
    case "Anestesiologia":
        JComboServ.addItem("Consulta de Evaluacion Pre Anestesica");
        break;
        
       case "Emergencia":
        JComboServ.removeAllItems();
        JComboServ.addItem("Consulta Medicina General");
        break;
        
        
    default:
        break;
}
        
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
     
 /*    
public void addHonorario() {
    // Formateador para limitar a dos decimales
    DecimalFormat decimalFormat = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));

    // Obtención de valores
    String servicio = JComboServ.getSelectedItem().toString();
    int cantidad = (int) jSpinnerCant.getValue();
    double bruto = Double.parseDouble(jTextBruto.getText());
    int porcetj = Integer.parseInt(jTextPorcentaje.getText());
    double neto = Double.parseDouble(jTextNeto.getText());
    double brutoTotal = bruto * cantidad;

    // Aplicar el formato de dos decimales
    String brutoStr = decimalFormat.format(bruto);
    String brutoStrTotal = decimalFormat.format(brutoTotal);
    String netoStr = decimalFormat.format(neto);

    // Crear el modelo de la tabla
    modelo = (DefaultTableModel) jTableServ.getModel();

    // Crear la lista con los valores formateados
    ArrayList<String> lista = new ArrayList<>();
    lista.add(servicio);
    lista.add(String.valueOf(cantidad));
    lista.add(brutoStr);
    lista.add(brutoStrTotal);
    lista.add(String.valueOf(porcetj));
    lista.add(netoStr);

    // Crear un arreglo de objetos para añadir a la tabla
    Object[] ob = new Object[6];
    ob[0] = lista.get(0);
    ob[1] = lista.get(1);
    ob[2] = lista.get(2);
    ob[3] = lista.get(3);
    ob[4] = lista.get(4);
    ob[5] = lista.get(5);

    // Añadir la fila al modelo de la tabla
    modelo.addRow(ob);
    jTableServ.setModel(modelo);
    jTableServ.setDefaultEditor(Object.class, null);
}
     
    */


public void addHonorario() {
    // Formateador para limitar a dos decimales
    DecimalFormat decimalFormat = new DecimalFormat("#.00", DecimalFormatSymbols.getInstance(Locale.US));

    // Obtención de valores
    String servicio = JComboServ.getSelectedItem().toString();
    int cantidad = (int) jSpinnerCant.getValue();
    double bruto = Double.parseDouble(jTextBruto.getText());
    int porcetj = Integer.parseInt(jTextPorcentaje.getText());
    double neto = Double.parseDouble(jTextNeto.getText());
    double brutoTotal = bruto * cantidad;

    // Crear el modelo de la tabla
    DefaultTableModel modelo = (DefaultTableModel) jTableServ.getModel();

    // Crear un arreglo de objetos con los valores formateados
    Object[] fila = new Object[] {
        servicio,
        cantidad,
        decimalFormat.format(bruto),
        decimalFormat.format(brutoTotal),
        porcetj,
        decimalFormat.format(neto)
    };

    // Añadir la fila al modelo de la tabla
    modelo.addRow(fila);
    jTableServ.setModel(modelo);
    jTableServ.setDefaultEditor(Object.class, null);
}







private double netoPagar=0; 
public void calcularMonto() {
    // showDivisa();
    int cantidad = (int) jSpinnerCant.getValue();
    double bruto = 0;
    int prctaje = 0;

    try {
        bruto = Double.parseDouble(jTextBruto.getText());
        prctaje = Integer.parseInt(jTextPorcentaje.getText());
    } catch (NumberFormatException e) {
        // Manejo de error si la entrada no es un número

        return;
    }

    // Calculo del porcentaje como decimal
    double clcPcrtaje = (double) prctaje / 100;
    double clcBruto= cantidad * bruto;
    // Calculo del neto a pagar, restando el porcentaje
    netoPagar = clcBruto * (clcPcrtaje);

   jTextNeto.setText(String.format(Locale.US, "%.2f", netoPagar));
   
   
   
   
   
   
}





public void calcularUnitBruto(){
 int cantidad = (int) jSpinnerCant.getValue();
 double brutoTotal = 0;

 try {
        brutoTotal = Double.parseDouble(jTextBrutoTotal.getText());
        
    } catch (NumberFormatException e) {
        return;
    }

  double clcUnit= brutoTotal/cantidad;
  jTextBruto.setText(String.format(Locale.US, "%.4f", clcUnit));
}







public void calcularTotalBruto(){
 int cantidad = (int) jSpinnerCant.getValue();
 double brutoTotal = 0;

 try {
        brutoTotal = Double.parseDouble(jTextBruto.getText());
        
    } catch (NumberFormatException e) {
        return;
    }

  double clcUnit= brutoTotal*cantidad;
  jTextBrutoTotal.setText(String.format(Locale.US, "%.2f", clcUnit));
}





private void actualizarValores() {
    // Obtener el valor actual de la cantidad del spinner
    int cantidad = (int) jSpinnerCant.getValue();
    
    // Validar que los campos de texto contienen números válidos
    double brutoTotal = 0;
    double brutoUnitario = 0;
    
    try {
        // Intentamos obtener el valor de brutoTotal (si es un número válido)
        brutoTotal = Double.parseDouble(jTextBrutoTotal.getText());
    } catch (NumberFormatException e) {
        // Si ocurre un error, simplemente no actualizamos
    }
    
    try {
        // Intentamos obtener el valor de brutoUnitario (si es un número válido)
        brutoUnitario = Double.parseDouble(jTextBruto.getText());
    } catch (NumberFormatException e) {
        // Si ocurre un error, simplemente no actualizamos
    }
    
    // Si tenemos un valor de brutoTotal, calculamos el unitario
    if (brutoTotal > 0 && cantidad > 0) {
        double clcUnit = brutoTotal / cantidad;
        jTextBruto.setText(String.format(Locale.US, "%.4f", clcUnit));
    }
    
    // Si tenemos un valor de brutoUnitario, calculamos el total
    if (brutoUnitario > 0 && cantidad > 0) {
        double clcTotal = brutoUnitario * cantidad;
        jTextBrutoTotal.setText(String.format(Locale.US, "%.2f", clcTotal));
    }
}






  public void acomodarTabla()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)jTableServ.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        

  
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("SERVICIO")).setPreferredWidth(200);
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("CANTIDAD")).setPreferredWidth(20);
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("BRUTO UNIT")).setPreferredWidth(40);
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("BRUTO TOTAL")).setPreferredWidth(40);
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("%")).setPreferredWidth(20);
        jTableServ.getColumnModel().getColumn(Tabla.findColumn("NETO")).setPreferredWidth(40);
  
 
       
         
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("SERVICIO")).setCellRenderer(Alinear);
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("CANTIDAD")).setCellRenderer(Alinear);
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("BRUTO UNIT")).setCellRenderer(Alinear);
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("BRUTO TOTAL")).setCellRenderer(Alinear);
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("%")).setCellRenderer(Alinear);
       jTableServ.getColumnModel().getColumn(Tabla.findColumn("NETO")).setCellRenderer(Alinear);
       jTableServ.getTableHeader().setReorderingAllowed(false);
     ;

       
    
    }
   
  
  double totalTabla=0;
  public void calcularTotales() {
    double total = 0; // Variable para almacenar la suma
    int columnIndex = 5; // Cambia esto al índice de tu columna

    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        Object value = jTableServ.getValueAt(i, columnIndex); // Obtener el valor de la celda
        if (value != null) {
            try {
                // Reemplazar la coma por un punto
                String numericValue = value.toString().replace(",", ".");
                total += Double.parseDouble(numericValue);
            } catch (NumberFormatException e) {
                // Manejo de error si no se puede convertir a número
                System.out.println("Error al convertir valor en fila " + i + ": " + value);
            }
        }
    }

    countServices();
    // Establecer el total en un JTextField o mostrarlo
   
        totalTabla=total;
        jTextotalNeto.setText(String.format(Locale.US, "%.2f", total)); 
        jTextSubtotal.setText(String.format(Locale.US, "%.2f", total)); 
        jTextTotal.setText(String.format(Locale.US, "%.2f", total)); 
    
}
  
  
  
  
  
  
    private int cantServ;
    public void countServices() {
    cantServ = 0; // Reiniciar antes de contar
    int total = 0; 
    int columnIndex = 1; 

    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        Object value = jTableServ.getValueAt(i, columnIndex); // Obtener el valor de la celda
        if (value != null) { 
            try {
                String numericValue = value.toString();
                cantServ += Integer.parseInt(numericValue);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir valor en fila " + i + ": " + value);
            }
        }
    }     

    // Establecer el valor en el JTextField como cadena
    jTextCantidad.setText(String.valueOf(cantServ)); 
}
  

  
  
private double sumaEco = 0.0;

private double calcularSumaEco() {
    sumaEco = 0.0; // Reiniciar suma al inicio de la función

    // Iterar sobre las filas de la tabla
    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        // Obtener el nombre del estudio de la columna 0
        String estudio = jTableServ.getValueAt(i, 0).toString().toUpperCase();

        // Verificar si el estudio comienza con "ECO"
        if (estudio.startsWith("ECO")  || estudio.startsWith("DOPPLER") || estudio.startsWith("HOLTER") ) {
            // Obtener el valor de la columna 5 y sumarlo
            Object valorColumna5 = jTableServ.getValueAt(i, 5);
            if (valorColumna5 != null) { // Verificar que no sea nulo
                try {
                    // Reemplazar la coma por un punto para la conversión
                    String valorStr = valorColumna5.toString().replace(',', '.'); // Cambia la coma por punto
                    double valor = Double.parseDouble(valorStr); // Convertir a double
                    
                    sumaEco += valor; // Sumar el valor a sumaEco
                } catch (NumberFormatException e) {
                    // Manejar el error si no se puede convertir a número
                    System.out.println("Error al convertir valor en fila " + i + ": " + valorColumna5);
                }
            }
        }
    }

    return sumaEco; 
}
  



private double calcularSumaGeneral() {
    sumaEco = 0.0; // Reiniciar suma al inicio de la función

    // Iterar sobre las filas de la tabla
    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        // Obtener el nombre del estudio de la columna 0
        String estudio = jTableServ.getValueAt(i, 0).toString().toUpperCase();

        // Verificar si el estudio comienza con "ECO"
        if (estudio.startsWith("ECO")  || estudio.startsWith("DOPPLER") || estudio.startsWith("HOLTER")) {
            // Obtener el valor de la columna 5 y sumarlo
            Object valorColumna5 = jTableServ.getValueAt(i, 5);
            if (valorColumna5 != null) { // Verificar que no sea nulo
                try {
                    // Reemplazar la coma por un punto para la conversión
                    String valorStr = valorColumna5.toString().replace(',', '.'); // Cambia la coma por punto
                    double valor = Double.parseDouble(valorStr); // Convertir a double
                    
                    sumaEco += valor; // Sumar el valor a sumaEco
                } catch (NumberFormatException e) {
                    // Manejar el error si no se puede convertir a número
                    System.out.println("Error al convertir valor en fila " + i + ": " + valorColumna5);
                }
            }
        }
    }

    return sumaEco; 
}







  
private double sumaFlatGen = 0.0;
private double calcularFlatGeneral() {
    sumaFlatGen = 0.0; 

    // Iterar sobre las filas de la tabla
    for (int i = 0; i < jTableServ.getRowCount(); i++) {

        String servicio = jTableServ.getValueAt(i, 0).toString().toUpperCase();


        if (!servicio.contains("RECTIFICACION DE DEDUCCION") && !servicio.contains("SALDO A FAVOR")) {

            Object valorColumna5 = jTableServ.getValueAt(i, 5);
            if (valorColumna5 != null) { // Verificar que no sea nulo
                try {

                    String valorStr = valorColumna5.toString().replace(',', '.'); 
                    double valor = Double.parseDouble(valorStr); 
                    
                    sumaFlatGen += valor; 
                } catch (NumberFormatException e) {

                    System.out.println("Error al convertir valor en fila " + i + ": " + valorColumna5);
                }
            }
        }
    }

    return sumaFlatGen; 
}


  

private double sumaLaboratorio = 0.0;

private double calcularFlatLab() {
    sumaLaboratorio = 0.0; 

    // Iterar sobre las filas de la tabla
    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        // Obtener el nombre del estudio de la columna 0
           String servicio = jTableServ.getValueAt(i, 0).toString().toUpperCase();


      if (!servicio.contains("RECTIFICACION DE DEDUCCION") && !servicio.contains("SALDO A FAVOR")){
            // Obtener el valor de la columna 5 y sumarlo
            Object valorColumna5 = jTableServ.getValueAt(i, 5);
            if (valorColumna5 != null) { // Verificar que no sea nulo
                try {
                    // Reemplazar la coma por un punto para la conversión
                    String valorStr = valorColumna5.toString().replace(',', '.'); // Cambia la coma por punto
                    double valor = Double.parseDouble(valorStr); // Convertir a double
                    
                    sumaLaboratorio += valor;
                } catch (NumberFormatException e) {
                    // Manejar el error si no se puede convertir a número
                    System.out.println("Error al convertir valor en fila calcularFlatLab" + i + ": " + valorColumna5);
                }
            }
        }
    }

    return sumaLaboratorio; 
}
  
  
  











  File file ;
  private BaseFont BF;
  Font Letrasmall;
      public void pdf() {
      try {
   
      
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          

         
 
    
        
                   
            //String   PdfNames="ReporteHonorario"+"_"+fth.format(fechaactual); 
            String   PdfNames="ReporteHonorario"; 
            BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Letrasmall = new Font(BF, 8);
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            file = new File("C://Fundaginebra//Reportes//"+PdfNames+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            doc.setMargins(36, 36, 125, 130);
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
           
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha1 = FormatoFecha.format(FechaOne.getDate());
            String Fecha2 = FormatoFecha.format(FechaTwo.getDate());
            
            
                 
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
            
            
            
            
            doc.open();
            
        
        
           //BODY 
      
       
        
          
            PdfPTable Tabla = new PdfPTable(3); 
            Tabla.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 1f };
            Tabla.setWidths(medidaCeldas);
            Tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
          
            Paragraph tcolumna1 = new Paragraph("N° ORDEN: "+maxId);
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna1);
            
    
            Paragraph tcolumna2 = new Paragraph("CORTE " + Fecha1+ " HASTA " +Fecha2);
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(10);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna2);

            Paragraph tcolumna3 = new Paragraph("N° ESPECIALISTA: "+idpersonal );
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(8);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna3);
            
            
            
             PdfPTable Tabla0 = new PdfPTable(3); 
            // Tabla0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla0.setWidthPercentage(100);  
            float[] medidaCeldas0 = {4f, 3f,4f};
            Tabla0.setWidths(medidaCeldas0);
            Tabla0.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            Paragraph t0columna1 = new Paragraph("ESPECIALISTA: "+ JComboDoctor.getSelectedItem().toString());
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(8);        
            t0columna1.setFont(Letra);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+cedula);
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(8);        
            t0columna2.setFont(Letra);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);


            Paragraph t0columna4 = new Paragraph("ESPECIALIDAD: "+ corregirAcentos(JComboEspecialidad.getSelectedItem().toString()));
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(8);        
            t0columna4.setFont(Letra);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
            
            
            
            
                
          Paragraph tituloHonorario = new Paragraph("H O N O R A R I O S ", Letra);
          tituloHonorario.setAlignment(Element.ALIGN_CENTER);
          tituloHonorario.getFont().setStyle(Font.BOLD);
          tituloHonorario.getFont().setSize(10);
          
          
          
           
          
          
          
            PdfPTable  tablapro = new PdfPTable(6);
            
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            tablapro.setWidthPercentage(100);  
            float[] medidaCeldas2 = {7f, 3f, 3f,3f,1f, 3f};
            tablapro.setWidths(medidaCeldas2);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna5 = new Paragraph("SERVICIO");
            tcolumna5.getFont().setStyle(Font.BOLD);
            tcolumna5.getFont().setSize(10);        
            tcolumna5.setFont(Letra);
            tcolumna5.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna5);
        
          
            Paragraph tcolumna6 = new Paragraph("CANTIDAD");
            tcolumna6.getFont().setStyle(Font.BOLD);
            tcolumna6.getFont().setSize(10);        
            tcolumna6.setFont(Letra);
            tcolumna6.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna6);
        
            Paragraph tcolumna7 = new Paragraph("BRUTO UNIT");
            tcolumna7.getFont().setStyle(Font.BOLD);
            tcolumna7.getFont().setSize(10);        
            tcolumna7.setFont(Letra);
            tcolumna7.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna7);
            
            Paragraph tcolumna8 = new Paragraph("BRUTO TOTAL");
            tcolumna8.getFont().setStyle(Font.BOLD);
            tcolumna8.getFont().setSize(10);        
            tcolumna8.setFont(Letra);
            tcolumna8.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna8);
            
            Paragraph tcolumna9 = new Paragraph("%");
            tcolumna9.getFont().setStyle(Font.BOLD);
            tcolumna9.getFont().setSize(10);        
            tcolumna9.setFont(Letra);
            tcolumna9.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna9);
            
            
            Paragraph tcolumna10 = new Paragraph("NETO");
            tcolumna10.getFont().setStyle(Font.BOLD);
            tcolumna10.getFont().setSize(10);        
            tcolumna10.setFont(Letra);
            tcolumna10.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna10);
               
            
        

            for (int i = 0; i < jTableServ.getRowCount(); i++) {
               
    
               String servicio = jTableServ.getValueAt(i, 0).toString().toUpperCase(); // Obtener el servicio
               servicio = corregirAcentos(servicio);

                String cantidad = jTableServ.getValueAt(i, 1).toString();
                String brutoUnit = formatearDecimal(jTableServ.getValueAt(i, 2).toString());
                String brutoTot = formatearDecimal(jTableServ.getValueAt(i, 3).toString()); 
                String porcentaje = jTableServ.getValueAt(i, 4).toString(); 
                String netoPagar = formatearDecimal(jTableServ.getValueAt(i, 5).toString()) ; 
        
                tablapro.addCell(new Paragraph(servicio,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(cantidad,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(brutoUnit,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(brutoTot,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(porcentaje,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(netoPagar,FontFactory.getFont("Arial",8,Font.NORMAL))); 
       
            }
            
            
            
            
            
            
            
            
            
          
            PdfPTable  tablaServLab = new PdfPTable(3);
            
            tablaServLab.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaServLab.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaServLab.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            tablaServLab.setWidthPercentage(100);  
            float[] medidaCeldas3 = {7f, 7f, 7f};
            tablaServLab.setWidths(medidaCeldas3);
            tablaServLab.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaServLab.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumnalab= new Paragraph("HONORARIO");
            tcolumnalab.getFont().setStyle(Font.BOLD);
            tcolumnalab.getFont().setSize(10);        
            tcolumnalab.setFont(Letra);
            tcolumnalab.setAlignment(Element.ALIGN_CENTER);
            tablaServLab.addCell(tcolumnalab);
        
            Paragraph tcolumnalab1= new Paragraph("CANTIDAD");
            tcolumnalab1.getFont().setStyle(Font.BOLD);
            tcolumnalab1.getFont().setSize(10);        
            tcolumnalab1.setFont(Letra);
            tcolumnalab1.setAlignment(Element.ALIGN_CENTER);
            tablaServLab.addCell(tcolumnalab1);
          
 
            
            Paragraph tcolumnalab2= new Paragraph("BRUTO TOTAL");
            tcolumnalab2.getFont().setStyle(Font.BOLD);
            tcolumnalab2.getFont().setSize(10);        
            tcolumnalab2.setFont(Letra);
            tcolumnalab2.setAlignment(Element.ALIGN_CENTER);
            tablaServLab.addCell(tcolumnalab2); 
            

            for (int i = 0; i < jTableServ.getRowCount(); i++) {
               
                String servicioLab = jTableServ.getValueAt(i, 0).toString().toUpperCase();
                String cantidadlab = jTableServ.getValueAt(i, 1).toString();
                String brutoTotlab = formatearDecimal(jTableServ.getValueAt(i, 5).toString()); 
     
                tablaServLab.addCell(new Paragraph(servicioLab,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablaServLab.addCell(new Paragraph(cantidadlab,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablaServLab.addCell(new Paragraph(brutoTotlab,FontFactory.getFont("Arial",8,Font.NORMAL))); 
     
            }
            
            
            
            
            
            

            
            
            
            
            
            
          //-----------------------------------------------------------------
       
       
          Paragraph tituloDeducciones = new Paragraph("D E D U C C I O N E S", Letra);
          tituloDeducciones.setAlignment(Element.ALIGN_CENTER);
          tituloDeducciones.getFont().setStyle(Font.BOLD);
          tituloDeducciones.getFont().setSize(10);
            
        
          PdfPTable tablaDeduccion = new PdfPTable(2); 
          tablaDeduccion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
          tablaDeduccion.setHorizontalAlignment(Element.ALIGN_CENTER);
          tablaDeduccion.setWidthPercentage(100);  
          tablaDeduccion.setWidths(new float[]{6f, 3f});

          
          if (!jTextDcFlat.getText().isEmpty() && Double.parseDouble(jTextDcFlat.getText()) != 0) {
          tablaDeduccion.addCell(crearCelda("COMISIÓN FLAT 15%"));
          tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextDcFlat.getText())));
          }

        // if (!jTextISRL.getText().isEmpty() && Double.parseDouble(jTextISRL.getText()) != 0) {
        //  tablaDeduccion.addCell(crearCelda("RETENCIÓN ISRL 3%"));
        //  tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextISRL.getText())));
        //  }

          if (!jTextDcPersonal.getText().isEmpty() && Double.parseDouble(jTextDcPersonal.getText()) != 0) {
          tablaDeduccion.addCell(crearCelda("PERSONAL ASIGNADO"));
          tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextDcPersonal.getText())));
          }

          if (!jTextDcAlmc.getText().isEmpty() && Double.parseDouble(jTextDcAlmc.getText()) != 0) {
          tablaDeduccion.addCell(crearCelda("CONSUMO ALMACEN"));
          tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextDcAlmc.getText())));
          }

        //  if (!jTextAnticipo.getText().isEmpty() && Double.parseDouble(jTextAnticipo.getText()) != 0) {
        // tablaDeduccion.addCell(crearCelda("ANTICIPO"));
        //   tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextAnticipo.getText())));
        //  } 

          if (!jTextOtherDc.getText().isEmpty() && Double.parseDouble(jTextOtherDc.getText()) != 0) {
          tablaDeduccion.addCell(crearCelda("OTRAS DEDUCCIONES"));
          tablaDeduccion.addCell(crearCelda(formatearDecimal(jTextOtherDc.getText())));
          }
            
            
          






          PdfPTable tablaDeduccionLab = new PdfPTable(2); 
          tablaDeduccionLab.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
          tablaDeduccionLab.setHorizontalAlignment(Element.ALIGN_CENTER);
          tablaDeduccionLab.setWidthPercentage(100);  
          tablaDeduccionLab.setWidths(new float[]{6f, 3f});

          
          if (!jTextDcFlat1.getText().isEmpty() && Double.parseDouble(jTextDcFlat1.getText()) != 0) {
          tablaDeduccionLab .addCell(crearCelda("COMISIÓN FLAT 15%"));
          tablaDeduccionLab .addCell(crearCelda(formatearDecimal(jTextDcFlat1.getText())));
          }

         //if (!jTextISRL1.getText().isEmpty() && Double.parseDouble(jTextISRL1.getText()) != 0) {
         //tablaDeduccionLab.addCell(crearCelda("RETENCIÓN ISRL 3%"));
         //tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextISRL1.getText())));
         // }

         if (!jTextDcAlmc1.getText().isEmpty() && Double.parseDouble(jTextDcAlmc1.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("DEDUCCIÓN ALMACEN "));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextDcAlmc1.getText())));
          }

        if (!jTextDcNomina.getText().isEmpty() && Double.parseDouble(jTextDcNomina.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("DEDUCCIÓN NOMINA "));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextDcNomina.getText())));
          }

         if (!jTextDcIncine1.getText().isEmpty() && Double.parseDouble(jTextDcIncine1.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("DEDUCCIÓN INCINERADORA "));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextDcIncine1.getText())));
          }

    

         if (!jTextBioseguridad.getText().isEmpty() && Double.parseDouble(jTextBioseguridad.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("DEDUCCIÓN BIOSEGURIDAD %"));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextBioseguridad.getText())));
          }

         if (!jTextOtherDc1.getText().isEmpty() && Double.parseDouble(jTextOtherDc1.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("OTRAS DEDUCCIONES "));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(jTextOtherDc1.getText())));
          }

           if (!JTextPrtjFundacion.getText().isEmpty() && Double.parseDouble(JTextPrtjFundacion.getText()) != 0) {
         tablaDeduccionLab.addCell(crearCelda("FUNDACIÓN 70%"));
         tablaDeduccionLab.addCell(crearCelda(formatearDecimal(JTextPrtjFundacion.getText())));
          }






      
          
          
          
            
          //------------------------------------
          Paragraph tituloResumen = new Paragraph("R E S U M E N", Letra);
          tituloResumen.setAlignment(Element.ALIGN_CENTER);
          tituloResumen.getFont().setStyle(Font.BOLD);
          tituloResumen.getFont().setSize(10);
            
        
          PdfPTable tablaResultado = new PdfPTable(2); 
          tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
          tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
          tablaResultado.setWidthPercentage(100);  
          tablaResultado.setWidths(new float[]{6f, 3f});

          tablaResultado.addCell(crearCelda("CANTIDAD DE SERVICIOS"));
          tablaResultado.addCell(crearCelda(jTextCantidad.getText()));
          
      
   
        //  tablaResultado.addCell(crearCelda("TOTAL DEDUCCIONES "));c  
        //  tablaResultado.addCell(crearCelda(formatearDecimal(jTextDcc.getText())));
          
          tablaResultado.addCell(crearCelda("FACTURAR "));
          tablaResultado.addCell(crearCelda(formatearDecimal(jTextFacturar.getText())));
          
          
          boolean isrlAdded = false;
          if (!jTextISRL.getText().isEmpty() && Double.parseDouble(jTextISRL.getText()) != 0) {
          tablaResultado.addCell(crearCelda("RETENCIÓN ISRL " + jTextIsrlPrcj.getText() + "%"));
          tablaResultado.addCell(crearCelda(formatearDecimal(jTextISRL.getText())));
          isrlAdded = true; // Marcar que ya se ha agregado la retención ISRL
           }

         if (!jTextISRL1.getText().isEmpty() && Double.parseDouble(jTextISRL1.getText()) != 0 && !isrlAdded) {
         tablaResultado.addCell(crearCelda("RETENCIÓN ISRL 3%"));
         tablaResultado.addCell(crearCelda(formatearDecimal(jTextISRL1.getText())));
          }
          
          tablaResultado.addCell(crearCelda("SUB TOTAL "));
          tablaResultado.addCell(crearCelda(formatearDecimal(jTextSubtotal.getText())));
          
       boolean anticipoAdded = false;
       if (!jTextAnticipo1.getText().isEmpty() && Double.parseDouble(jTextAnticipo1.getText()) != 0 && !anticipoAdded) {
       tablaResultado.addCell(crearCelda("ANTICIPO"));
       tablaResultado.addCell(crearCelda(formatearDecimal(jTextAnticipo1.getText())));
       anticipoAdded = true; // Marcar que ya se ha agregado el anticipo
       }

       if (!jTextAnticipo.getText().isEmpty() && Double.parseDouble(jTextAnticipo.getText()) != 0 && !anticipoAdded) {
       tablaResultado.addCell(crearCelda("ANTICIPO"));
       tablaResultado.addCell(crearCelda(formatearDecimal(jTextAnticipo.getText())));
       }
          
          tablaResultado.addCell(crearCelda("TOTAL A PAGAR "));
          tablaResultado.addCell(crearCelda(formatearDecimal(jTextTotal.getText())));
         
 
       


       //Header
       
  

       doc.add(Tabla);
       doc.add(Tabla0);
       doc.add(saltolinea);
       
       //Body
       doc.add(tituloHonorario);
       doc.add(saltolinea);
     
         
      
       

          //deduccion
          if(JComboEspecialidad.getSelectedItem().equals("Bioanalista")){
          doc.add(tablaServLab);
          doc.add(saltolinea);   
              
          doc.add(tituloDeducciones); 
          doc.add(saltolinea);
          doc.add(tablaDeduccionLab);
          doc.add(saltolinea);
           
          }  
          
          
          
          
          
          //-----------------------------------------------------------------------------
          else{
          doc.add(tablapro);
          doc.add(saltolinea);
       
          doc.add(tituloDeducciones); 
          doc.add(saltolinea);
          doc.add(tablaDeduccion);
          doc.add(saltolinea);

   
          }
       
   
       
          
          
          //resumen
          doc.add(tituloResumen); 
          doc.add(saltolinea);
          doc.add(tablaResultado);
            
          if (!jTextObservacion.getText().equals("")) {
          doc.add(saltolinea);
          doc.add(createObservationTable(jTextObservacion)); 
          doc.add(saltolinea);
          }
           
          
          doc.close();
          archivo.close();
      
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     
     
private String formatearDecimal(String texto) {
    try {
        double valor = Double.parseDouble(texto);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("es", "ES"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(valor);
    } catch (NumberFormatException e) {
        return texto; // Retorna el texto original si hay un error
    }
}
      
  public static String corregirAcentos(String texto) {
        // Mapa de palabras clave con correcciones
      Map<String, String> acentos = new HashMap<>();
      acentos.put("ginecologia", "ginecología");
      acentos.put("ginecologica", "ginecológica");
      acentos.put("cardiologia", "cardiología");
      acentos.put("neurologia", "neurología");
      acentos.put("oftalmologia", "oftalmología");
      acentos.put("otorrinolaringologia", "otorrinolaringología");
      acentos.put("pediatria", "pediatría");
      acentos.put("psiquiatria", "psiquiatría");
      acentos.put("medicina interna", "medicina interna");
      acentos.put("dermatologia", "dermatología");
      acentos.put("radiologia", "radiología");
      acentos.put("anestesiologia", "anestesiología" );
      acentos.put("traumatologia", "traumatología");
      acentos.put("geriatria", "geriatría");
      acentos.put("odontologia", "odontología");
      acentos.put("salud publica", "salud pública");
      acentos.put("bioanalisis", "Bioánalisis");
        // Añade más palabras según sea necesario

        // Convertir el texto a minúsculas para la comparación
        String textoLower = texto.toLowerCase();
        
        // Reemplazar palabras clave con las versiones correctas
        for (Map.Entry<String, String> entry : acentos.entrySet()) {
            textoLower = textoLower.replace(entry.getKey(), entry.getValue());
           
        }
        textoLower = textoLower.toUpperCase();
        return textoLower; // Devuelve el texto corregido
    }
      


  
  
  
  
  
  
  
  
 public PdfPTable createObservationTable(JTextArea jTextObservacion) {
        PdfPTable tableObservacion = new PdfPTable(1);
        try {
            tableObservacion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] medidaObservacion = {5f};
            tableObservacion.setWidthPercentage(100);
            tableObservacion.setWidths(medidaObservacion);
            tableObservacion.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableObservacion.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            // Crear y agregar el encabezado
            Paragraph t9columna1 = new Paragraph("Observación");
            t9columna1.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD));
            t9columna1.setAlignment(Element.ALIGN_LEFT);
            tableObservacion.addCell(t9columna1);

            // Agregar el contenido del JTextArea
            String observacionText = jTextObservacion.getText();
            tableObservacion.addCell(new Paragraph(observacionText, FontFactory.getFont("Arial", 9, Font.NORMAL)));
                    
        } catch (Exception e) {
            // Manejar la excepción, por ejemplo, imprimirla en la consola
            e.printStackTrace();
            // También puedes lanzar una excepción personalizada si lo deseas
        }

        return tableObservacion;
    }


private Font letraPequeña;
private PdfPCell crearCelda(String texto) {
  
  
    letraPequeña = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    
    Paragraph parrafo = new Paragraph(texto, letraPequeña);
    parrafo.setAlignment(Element.ALIGN_JUSTIFIED);

    PdfPCell celda = new PdfPCell(parrafo);
    celda.setBorder(Rectangle.NO_BORDER);
    celda.enableBorderSide(Rectangle.BOTTOM);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
    
    return celda;
    
}



               
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        try {
     

            Paragraph fecha = new Paragraph();
    //      Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(MP.FechaAdmin.getDate());
            String Hora = MP.Time.getText()+" "+MP.jLabel102.getText();
            
            
            BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
          

            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            cb.setFontAndSize(BF2, 12);

          // Ajustar el espacio entre el encabezado y el cuerpo (por ejemplo, margen superior de 50)
           

          com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
          imagen.setAbsolutePosition(50, 750);  // Ajusta la posición de la imagen según tus necesidades
          imagen.scaleToFit(110, 110);  // Ajusta el tamaño de la imagen según tus necesidades
          cb.addImage(imagen);
          
          
            cb.setFontAndSize(BF2, 12);
            cb.setTextMatrix(200, 800);
            cb.showText(empresa);
            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(260, 790);
            cb.showText(rif);
            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(460, 780);
            cb.showText("FECHA: "+ Fecha);
            
            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(460, 770);
            cb.showText("HORA: "+ Hora);
            
          
            cb.setFontAndSize(BF2, 8);
            cb.setTextMatrix(235, 740);
            cb.showText(ubicacion);
          
            cb.setFontAndSize(BF2, 8);
            cb.setTextMatrix(210, 730);
            cb.showText(telefonos); 
            cb.setTextMatrix(168, 725);
            cb.showText("__________________________________________________________________");

            cb.endText();

            
            
            
            
   
               
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}     
        
           
           
           
           
        PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
        public void onEndPage(PdfWriter writer, Document document) {
        
    
 try{
 
     
           
        PdfPTable footerTable = new PdfPTable(3);
        footerTable.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
        footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        footerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        float footerHeight = -26;
        float x = (document.left() + document.right()) / 2;
        float y = document.bottom() - footerHeight;

        footerTable.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
    

 
       Paragraph tfooter1 = new Paragraph(piepagina, new Font(FontFactory.getFont("Arial",9,Font.NORMAL)));  
       PdfPCell piepag = new PdfPCell(tfooter1 );
       piepag.setBorder(Rectangle.NO_BORDER);
       // Alineas la imagen y el texto al centro horizontal y vertical de la celda
       piepag.setHorizontalAlignment(Element.ALIGN_CENTER);
       piepag.setVerticalAlignment(Element.ALIGN_MIDDLE);
       footerTable.addCell(piepag);
     
       String User=Tempo.getUser();
        
       Phrase phrase = new Phrase();
       phrase.add(new Chunk("Emitido por: " + User, new Font(FontFactory.getFont("Arial",9,Font.NORMAL))));
         
       PdfPCell imgfirma = new PdfPCell(phrase);
       imgfirma.setBorder(Rectangle.NO_BORDER);
       // Alineas la imagen y el texto al centro horizontal y vertical de la celda
       imgfirma.setHorizontalAlignment(Element.ALIGN_CENTER);
       imgfirma.setVerticalAlignment(Element.ALIGN_MIDDLE);
       footerTable.addCell(imgfirma);
            
        
        
        
       Paragraph tfooter2 = new Paragraph(writer.getPageNumber()+ " Pág", new Font(FontFactory.getFont("Arial",9,Font.NORMAL)));
           
       PdfPCell numpag = new PdfPCell(tfooter2);
       numpag.setBorder(Rectangle.NO_BORDER);
       
       
       // Alineas la imagen y el texto al centro horizontal y vertical de la celda
       numpag.setHorizontalAlignment(Element.ALIGN_CENTER);
       numpag.setVerticalAlignment(Element.ALIGN_MIDDLE);
       footerTable.addCell(numpag);
        
       //footerTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
       

        footerTable.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() + footerHeight, writer.getDirectContent());
           
 } catch(Exception e){ System.out.println(e);}


        
    }
    };   
            









    
   String  empresa, rif, ubicacion, telefonos, piepagina;
 public void informacionpdf() {
    String sql = "SELECT * FROM tableinfopdfs";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener información: " + e.getMessage());
    }
}
    

 
   private String cedula;
   private int idpersonal;
 
 
 public void knowIdSpecialist() {
    String sql = "SELECT IdPersonal, Cedula \n" +
"FROM table_personal \n" +
"WHERE CONCAT(Nombre, ' ', Apellido) = ?";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Set the parameter for the query before executing it
        ps.setString(1, JComboDoctor.getSelectedItem().toString());
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
               idpersonal = rs.getInt("IdPersonal");
               cedula = rs.getString("Cedula");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener especialidad: " + e.getMessage());
    }
}
 
 
 

 
 int idSpecialty;
public void knowIdSpecialty() {
    String sql = "SELECT id_especialidad FROM table_especialidad WHERE especialidad=?";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Set the parameter for the query before executing it
        ps.setString(1, JComboEspecialidad.getSelectedItem().toString());
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idSpecialty = rs.getInt("id_especialidad");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener especialidad: " + e.getMessage());
    }
}
 
 
 

  public void disableBox(){
  
  jTextNeto.setEditable(false);
  jTextCantidad.setEditable(false);
  jTextSubtotal.setEditable(false);
  jTextDcc.setEditable(false);
  jTextTotal.setEditable(false);
 // jTextDcFlat.setEditable(false);
  jTextISRL.setEditable(false);
  jTextDcFlat1.setEditable(false);
  jTextISRL1.setEditable(false);
  JTextPrtjFundacion.setEditable(false);
  }
   

     
        DefaultTableModel modelo = new DefaultTableModel();
     
      private java.sql.Date getFechaDelServidor() {
    String sqlTime = "SELECT NOW() AS server_time";
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sqlTime);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getDate("server_time"); // Obtener la fecha del servidor
        }
    } catch (Exception e) {
        System.out.println("Error al obtener la fecha del servidor: " + e);
    }
    return null; // Retornar null si ocurre un error
}
    
    
        
     




double restarIrl;
private void calculateIRL(double subTotal, double prctaje, double resta) {
    // Verifica si la tabla está vacía
    if (jTableServ.getRowCount() > 0) {
        double clcDeduccion;

        // Aplica la lógica de la fórmula
        if (subTotal <= 800) {
            clcDeduccion = 0; // Si el subtotal es menor o igual a 800, la deducción es 0
        } else {
            clcDeduccion = (prctaje * subTotal) - resta; // Aplica el cálculo de deducción
        }

        // Establece el resultado en el JTextField
        jTextISRL.setText(String.format(Locale.US, "%.2f", clcDeduccion));

        // Calcular el nuevo total a pagar
        double irl = parseDoubleSafe(jTextISRL.getText());
        double totalPagar = parseDoubleSafe(jTextTotal.getText());
        restarIrl = totalPagar - irl; 
        
      
        

        jTextTotal.setText(String.format(Locale.US, "%.2f", restarIrl));
        double totalDedcc = parseDoubleSafe(jTextDcc.getText());
       // sumIrl=totalDedcc+restarIrl;
        
        
    } else {
        // Si la tabla está vacía, puedes agregar un mensaje informativo
        JOptionPane.showMessageDialog(null, "No hay datos en la tabla para calcular.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}







double restarIrlLab;
private void calculateIRLLab(double subTotal, double prctaje) {
    // Verifica si la tabla está vacía
    if (jTableServ.getRowCount() > 0) {
        double clcDeduccion;

        // Aplica la lógica de la fórmula
        if (subTotal <= 800) {
            clcDeduccion = 0; // Si el subtotal es menor o igual a 800, la deducción es 0
        } else {
            clcDeduccion = (0.03 * subTotal) - 22.5; // Aplica el cálculo de deducción
        }

        // Establece el resultado en el JTextField
        jTextISRL1.setText(String.format(Locale.US, "%.2f", clcDeduccion));

        // Calcular el nuevo total a pagar
        double irl = parseDoubleSafe(jTextISRL1.getText());
        double totalPagar = parseDoubleSafe(jTextTotal.getText());
        restarIrlLab = totalPagar - irl; 
        
      
        

        jTextTotal.setText(String.format(Locale.US, "%.2f", restarIrl));
        double totalDedcc = parseDoubleSafe(jTextDcc.getText());
       // sumIrl=totalDedcc+restarIrl;
        
        
    } else {
     
        JOptionPane.showMessageDialog(null, "No hay datos en la tabla para calcular.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}






public void guardarHonorario(int hon_especialista, int hon_especialidad, int idEncargado, String hon_fecha1, String hon_fecha2, int ho_estado, String ruta) {
    JCFactura pa = new JCFactura();
    JCFacturaDao po = new JCFacturaDao();
    
    pa.setHon_especialista(hon_especialista);
    pa.setHon_especialidad(hon_especialidad);
    pa.setHon_encargado(idEncargado);
    pa.setHon_fecha1(hon_fecha1);
    pa.setHon_fecha2(hon_fecha2);
    pa.setHo_estado(ho_estado);
    
    
    if (ruta == null || ruta.isEmpty()) {
    pa.setHo_reporte(null);
    } else {
        try {
            File file = new File(ruta);
            if (file.exists() && file.isFile()) {
                byte[] pdf = new byte[(int) file.length()];
                
                try (InputStream input = new FileInputStream(file)) {
                    input.read(pdf);
                    pa.setHo_reporte(pdf);
                }
            } else {
                // Si el archivo no existe, setear el BLOB como null
                pa.setHo_reporte(null);
            }
        } catch (IOException ex) {
            pa.setHo_reporte(null); // Si ocurre un error al leer el archivo, lo setea como null
            System.out.println("Error al agregar archivo pdf " + ex);
        }
    }
    
    
    
    
    
  /*  try {
        File file = new File(ruta);
        byte[] pdf = new byte[(int) file.length()];
        
        try (InputStream input = new FileInputStream(file)) {
            input.read(pdf);
            pa.setHo_reporte(pdf);
        }
    } catch (IOException ex) {
        pa.setHo_reporte(null);
        System.out.println("Error al agregar archivo pdf " + ex);
    }*/
    
    po.guardarHonorario(pa);
}




public void updateHonorario(int hon_especialista, int hon_especialidad, int idEncargado, String hon_fecha1, String hon_fecha2, int ho_estado, String ruta) {
    JCFactura pa = new JCFactura();
    JCFacturaDao po = new JCFacturaDao();
    
    pa.setHon_especialista(hon_especialista);
    pa.setHon_especialidad(hon_especialidad);
    pa.setHon_encargado(idEncargado);
    pa.setHon_fecha1(hon_fecha1);
    pa.setHon_fecha2(hon_fecha2);
    pa.setHo_estado(ho_estado);
 
    if (ruta == null || ruta.isEmpty()) {
    pa.setHo_reporte(null);
    } else {
        try {
            File file = new File(ruta);
            if (file.exists() && file.isFile()) {
                byte[] pdf = new byte[(int) file.length()];
                
                try (InputStream input = new FileInputStream(file)) {
                    input.read(pdf);
                    pa.setHo_reporte(pdf);
                }
            } else {
                // Si el archivo no existe, setear el BLOB como null
                pa.setHo_reporte(null);
            }
        } catch (IOException ex) {
            pa.setHo_reporte(null); // Si ocurre un error al leer el archivo, lo setea como null
            System.out.println("Error al agregar archivo pdf " + ex);
        }
    }
    
   
    
    if(corregir==false){    po.updateHonorario(pa, maxId);}
    else{ po.updateHonorario(pa, idHonorario);

    }

    
    
}









public void cancelarTodo(){

DefaultTableModel model = (DefaultTableModel) jTableServ.getModel();
model.setRowCount(0);

jTextBruto.setText("0");
jTextPorcentaje.setText("0");
jTextNeto.setText("0");

jSpinnerCant.setValue(1);

jTextDcFlat.setText("0");
jTextISRL.setText("0");
jTextDcPersonal.setText("0");
jTextOtherDc.setText("0");
jTextAnticipo.setText("0");
jTextDcAlmc.setText("0");
jTextObservacion.setText("");
jTextCantidad.setText("0");
jTextSubtotal.setText("0");
jTextDcc.setText("0");
jTextTotal.setText("0");
jTextFacturar.setText("0");


jISRL.setSelected(false);



jTextDcFlat1.setText("0");
jTextISRL1.setText("0");
jTextDcAlmc1.setText("0");
jTextDcNomina.setText("0");
jTextDcIncine1.setText("0");
jTextAnticipo1.setText("0");
jTextBioseguridad.setText("0");
jTextOtherDc1.setText("0");
JTextPrtjFundacion.setText("0");
jTextotalNeto.setText("0");
jTextObservacion.setText("");
jTextsearch.setText("");

jTextIsrlPrcj.setText("3");
jTextIsrlPrcj1.setText("22.5"); 

    String especialidad = (String) JComboEspecialidad.getSelectedItem();
    if ("Bioanalista".equals(especialidad)) {
        jTabbedPane2.setSelectedIndex(0);
        llenarBioanalisis();

        // Deshabilitar opciones específicas para Bioanalista
        jRadioConsulta.setEnabled(false);
        jRadioProc.setEnabled(false);
        jRadioQuirurgico.setEnabled(false);
        jRadioOtros.setEnabled(false);

        // Seleccionar la opción "Otros" y desmarcar las demás
        jRadioOtros.setSelected(true);
        jRadioConsulta.setSelected(false);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);

    } else {
        // Llenar la consulta general para otros casos
        llenarConsulta();

        // Habilitar todas las opciones
        jRadioConsulta.setEnabled(true);
        jRadioProc.setEnabled(true);
        jRadioQuirurgico.setEnabled(true);
        jRadioOtros.setEnabled(true);

        // Seleccionar la opción por defecto (Consulta)
        jRadioConsulta.setSelected(true);
        jRadioProc.setSelected(false);
        jRadioQuirurgico.setSelected(false);
        jRadioOtros.setSelected(false);

        // Cambiar a la pestaña de opciones generales
        jTabbedPane2.setSelectedIndex(1);
    }


}










public void calDeducciones() {
    double totalDedccion = 0;
    double flat = 0;
    double irl = 0;
    double personal = 0;
    double otras = 0;
    double anticipo = 0; 
    double almacen = 0;
    double subTotal = 0;
    double facturar=0;
    double Total=0;
    
 
    try {
        
        Total = totalTabla;
        flat = parseDoubleSafe(jTextDcFlat.getText());
        personal = parseDoubleSafe(jTextDcPersonal.getText());
        otras = parseDoubleSafe(jTextOtherDc.getText());
        anticipo = parseDoubleSafe(jTextAnticipo.getText());
        almacen = parseDoubleSafe(jTextDcAlmc.getText());
       // subTotal = parseDoubleSafe(jTextSubtotal.getText());
        irl = parseDoubleSafe(jTextISRL.getText());
        
        
    } catch (NumberFormatException e) {
       
        return;
    }

    totalDedccion = flat  + personal + otras + almacen;
    double netoPagar = Total - totalDedccion;
    facturar = netoPagar;
    subTotal= netoPagar;
    actualizarResultados(totalDedccion, netoPagar, facturar,subTotal);
    
    totalDedccion += irl;
    netoPagar = Total - totalDedccion ;
    subTotal= netoPagar;
    actualizarResultados(totalDedccion, netoPagar, facturar, subTotal);
    
       
    totalDedccion += anticipo;
    netoPagar = Total - totalDedccion ;
    actualizarResultados(totalDedccion, netoPagar, facturar, subTotal);

  
    

}

private double parseDoubleSafe(String text) {
    return text.trim().isEmpty() ? 0 : Double.parseDouble(text);
}








public void calDeduccionesLab() {
    double Total = 0;
    double subTotal=0;
    double totalDeduccion = 0;
    double facturar=0;
    
    try {
        Total = totalTabla;

        
        
        // Deducciones
        double flat = parseDoubleSafe(jTextDcFlat1.getText());
        double irl = parseDoubleSafe(jTextISRL1.getText());
        double incineradora = parseDoubleSafe(jTextDcIncine1.getText());
        double nomina = parseDoubleSafe(jTextDcNomina.getText());
        double anticipo = parseDoubleSafe(jTextAnticipo1.getText());
        double almacen = parseDoubleSafe(jTextDcAlmc1.getText());
        double bioSeguridad = parseDoubleSafe(jTextBioseguridad.getText());
        double otras = parseDoubleSafe(jTextOtherDc1.getText());

        
        
    
        
        // Cálculo de total de deducciones                               +Otras
        totalDeduccion = flat + incineradora + nomina  + almacen + bioSeguridad ;
 
        // Cálculo del neto a pagar
        double netoPagar = Total - totalDeduccion;
        facturar= netoPagar;
        subTotal= netoPagar;
        // Mostrar totales
        actualizarResultados(totalDeduccion, netoPagar, facturar, subTotal);

        
        
        
        
        
        // Cálculo del pago de la fundación
        double pagoFundacion = netoPagar * 0.70;
        JTextPrtjFundacion.setText(String.format(Locale.US, "%.2f", pagoFundacion));

        // Actualiza las deducciones con el pago a la fundación
        totalDeduccion += pagoFundacion;
        netoPagar = Total - totalDeduccion;
        facturar= netoPagar;
        subTotal= netoPagar;
        actualizarResultados(totalDeduccion, netoPagar, facturar, subTotal);
 
      
        
        totalDeduccion +=  otras;
        netoPagar = Total - totalDeduccion;
        facturar= netoPagar;
        subTotal= netoPagar;
        actualizarResultados(totalDeduccion, netoPagar, facturar, subTotal);
        
        
        totalDeduccion += irl;
        netoPagar = Total - totalDeduccion;
         subTotal= netoPagar;
        actualizarResultados(totalDeduccion, netoPagar, facturar, subTotal);
        
        
        totalDeduccion += anticipo;
        netoPagar = Total - totalDeduccion;
        actualizarResultados(totalDeduccion, netoPagar, facturar, subTotal);

        
    

    } catch (NumberFormatException e) {
        // Manejo de errores en la conversión de números
        System.err.println("Error en la conversión de datos: " + e.getMessage());
    }
}








private void actualizarResultados(double totalDeduccion, double netoPagar, double facturar, double subTotal) {
    jTextDcc.setText(String.format(Locale.US, "%.2f", totalDeduccion));
    jTextFacturar.setText(String.format(Locale.US, "%.2f", facturar));
    
    jTextSubtotal.setText(String.format(Locale.US, "%.2f", subTotal)); 
    jTextTotal.setText(String.format(Locale.US, "%.2f", netoPagar));
    
} 






    public void agregarDeducciones(String decFlat, String isrl, String personalAsig ,String almacen, String nomina, String incineradora, String anticipo, String bioseguridad, String fundacion ,String otras, String observaciones ) {
    String sql = "INSERT INTO `honorario_deducciones`(`id_honorario`, `Dec_flat`, `Dec_ISRL`, `Dec_personal`, `Dec_almacen`, `Dec_nomina`, `Dec_incineradora`, `Dec_anticipo`, `Dec_Bioseguridad`, `Fundacion`, `Dec_otras`, observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setInt(1, maxId);
        ps.setString(2, decFlat);
        ps.setString(3, isrl);
        ps.setString(4, personalAsig);
        ps.setString(5, almacen);
        ps.setString(6, nomina);
        ps.setString(7, incineradora);
        ps.setString(8, anticipo);
        ps.setString(9, bioseguridad);
        ps.setString(10, fundacion);
        ps.setString(11, otras);
        ps.setString(12,  observaciones);
       
        ps.executeUpdate();

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
}
 

    
    
    
    
    
    
    public void agregarResumen(String cantidad, String subtotal, String deducciones ,String totalPagar, String facturar ) {
    String sql = "INSERT INTO `honorario_resumen`(`id_honorario`, `res_cant`, `res_subtotal`, `res_deducciones`, `res_totalneto`, `res_facturar`) VALUES (?,?,?,?,?,?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setInt(1, maxId);
        ps.setString(2, cantidad);
        ps.setString(3, subtotal);
        ps.setString(4, deducciones);
        ps.setString(5, totalPagar);
        ps.setString(6, facturar);
        
        ps.executeUpdate();

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
}
    
    
    
    
    
    
   public void agregarServicios() {
    String sql = "INSERT INTO `honorario_servicios`(`id_honorario`, `servicio`, `cantidad`, `bruto_unit`, `bruto_total`, `porcentaje`, `neto`) VALUES (?,?,?,?,?,?,?)";

    for (int i = 0; i < jTableServ.getRowCount(); i++) {
        try (Connection con = new EnlaceBd().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el valor del parámetro
            ps.setInt(1, maxId);
            ps.setString(2, jTableServ.getValueAt(i, 0).toString());
            ps.setString(3, jTableServ.getValueAt(i, 1).toString());
            ps.setString(4, jTableServ.getValueAt(i, 2).toString()); 
            ps.setString(5, jTableServ.getValueAt(i, 3).toString()); 
            ps.setString(6, jTableServ.getValueAt(i, 4).toString()); 
            ps.setString(7, jTableServ.getValueAt(i, 5).toString()); 

            ps.executeUpdate();

        } catch (SQLException e) {
            // Manejar errores SQL
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

    
   //-------------------------------------------------------
   
   
   
      public void updateDeducciones(String decFlat, String isrl, String personalAsig ,String almacen, String nomina, String incineradora, String anticipo, String bioseguridad, String fundacion ,String otras, String Observacion ) {
  
          
     int id = corregir ? idHonorario : maxId;
          
 String sql = "UPDATE `honorario_deducciones` \n" +
"SET \n" +
"  `Dec_flat` = ?, \n" +
"  `Dec_ISRL` = ?, \n" +
"  `Dec_personal` = ?, \n" +
"  `Dec_almacen` = ?, \n" +
"  `Dec_nomina` = ?, \n" +
"  `Dec_incineradora` = ?, \n" +
"  `Dec_anticipo` = ?, \n" +
"  `Dec_Bioseguridad` = ?, \n" +
"  `Fundacion` = ?, \n" +
"  `Dec_otras` = ?, \n" +
"  `observaciones` = ? \n" +
"WHERE \n" +
"  `id_honorario` = ?;";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia

        ps.setString(1, decFlat);
        ps.setString(2, isrl);
        ps.setString(3, personalAsig);
        ps.setString(4, almacen);
        ps.setString(5, nomina);
        ps.setString(6, incineradora);
        ps.setString(7, anticipo);
        ps.setString(8, bioseguridad);
        ps.setString(9, fundacion);
        ps.setString(10, otras);
        ps.setString(11, Observacion);
        ps.setInt(12,  id);
        ps.executeUpdate();

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
}
 

    
    
    
    
    
    
    public void updateResumen(String cantidad, String subtotal, String deducciones ,String totalPagar, String Facturar ) {
        
          int id = corregir ? idHonorario : maxId;
    String sql = "UPDATE `honorario_resumen` \n" +
"SET \n" +
"  `res_cant` = ?, \n" +
"  `res_subtotal` = ?, \n" +
"  `res_deducciones` = ?, \n" +
"  `res_totalneto` = ?, \n" +
"  `res_facturar` = ? \n" +
            
"WHERE \n" +
"  `id_honorario` = ?;";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
   
        ps.setString(1, cantidad);
        ps.setString(2, subtotal);
        ps.setString(3, deducciones);
        ps.setString(4, totalPagar);
        ps.setString(5, totalPagar);
        ps.setInt(6, id);

        ps.executeUpdate();

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
}
    
    
    
    
    
  public void updateServicios() {
    int id = corregir ? idHonorario : maxId;
    
    // Consulta para verificar si existen filas con el mismo id_honorario
    String checkExistenceSQL = "SELECT COUNT(*) FROM `honorario_servicios` WHERE `id_honorario` = ?";
    
    // Consulta para eliminar las filas existentes
    String deleteSQL = "DELETE FROM `honorario_servicios` WHERE `id_honorario` = ?";
    
    // Consulta para insertar los nuevos registros
    String insertSQL = "INSERT INTO `honorario_servicios`(`id_honorario`, `servicio`, `cantidad`, `bruto_unit`, `bruto_total`, `porcentaje`, `neto`) VALUES (?,?,?,?,?,?,?)";
    
    try (Connection con = new EnlaceBd().getConnection()) {
        
        // Comprobar si existen registros con el id_honorario
        try (PreparedStatement psCheck = con.prepareStatement(checkExistenceSQL)) {
            psCheck.setInt(1, id);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Si existen filas con el mismo id_honorario, las eliminamos
                try (PreparedStatement psDelete = con.prepareStatement(deleteSQL)) {
                    psDelete.setInt(1, id);
                    psDelete.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar registros existentes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    return; 
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar existencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return; // Terminar ejecución si ocurre un error al verificar existencia
        }
        
        // Insertar los nuevos registros
        try (PreparedStatement psInsert = con.prepareStatement(insertSQL)) {
            for (int i = 0; i < jTableServ.getRowCount(); i++) {
                psInsert.setInt(1, id);
                psInsert.setString(2, jTableServ.getValueAt(i, 0).toString());
                psInsert.setString(3, jTableServ.getValueAt(i, 1).toString());
                psInsert.setString(4, jTableServ.getValueAt(i, 2).toString()); 
                psInsert.setString(5, jTableServ.getValueAt(i, 3).toString()); 
                psInsert.setString(6, jTableServ.getValueAt(i, 4).toString()); 
                psInsert.setString(7, jTableServ.getValueAt(i, 5).toString()); 
                
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar los registros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    } catch (SQLException e) {
        // Error al obtener la conexión
        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
   
   
   
   
   
   
   
   
   
   
   
   
    
    

   private boolean existeRegistro(Connection con, String tabla, String campoId, int id) {
    String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE " + campoId + "=?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() && rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar existencia de registro: " + e);
        return false;
    }
}
   
   
   
    


int maxId = 0;




public void knowCode() {
    String sql = "SELECT MAX(id_horonario) AS max_id FROM honorarios";
  

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            maxId = rs.getInt("max_id") + 1;
        } else {
            maxId = 1;
        }

    } catch (SQLException e) {
        System.err.println("Error retrieving the code: " + e.getMessage());
        // Consider throwing a custom exception or logging the error
    }
}




public void btnGuardar() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    knowIdSpecialty();
    // Obtener fecha en formato adecuado
    String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    try {
        // Establecer conexión con la base de datos
        con = cn.getConnection();
        // Verificar si el honorario ya existe
        boolean existe = existeRegistro(con, "honorarios", "id_horonario", maxId);

        // Si el honorario ya existe, actualizarlo
        if (existe) {
            if (confirmarSobrescribir()) {
                // Actualizar honorario
                updateHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, null);
                
                // Dependiendo de la especialidad, actualizar deducciones
                if (esBioanalista()) {
                    updateDeduccionesBioanalista();
                } else {
                    updateDeduccionesGenerales();
                }

                // Actualizar resumen y servicios
                updateResumen(jTextCantidad.getText(), jTextSubtotal.getText(), jTextDcc.getText(), jTextTotal.getText(),  jTextFacturar.getText());
                updateServicios();

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "HONORARIO ACTUALIZADO", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Si no existe, guardar un nuevo honorario
            guardarHonorario(idpersonal, idSpecialty, idusuario, fecha1, fecha2, 106, null);
            
            // Dependiendo de la especialidad, agregar deducciones
            if (esBioanalista()) {
                agregarDeduccionesBioanalista();
            } else {
                agregarDeduccionesGenerales();
            }

            // Agregar resumen y servicios
            agregarResumen(jTextCantidad.getText(), jTextSubtotal.getText(), jTextDcc.getText(), jTextTotal.getText(),  jTextFacturar.getText());
            agregarServicios();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "HONORARIO GUARDADO", "HONORARIO", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception e) {
        System.out.println("ERROR EN BTN GUARDAR: " + e);
    }
}




// Método para confirmar si se desea sobrescribir el reporte
private boolean confirmarSobrescribir() {
    return JOptionPane.showConfirmDialog(rootPane, "¿Está seguro de que desea sobrescribir el reporte guardado previamente?", 
        "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
}

// Método para verificar si la especialidad es "Bioanalista"
private boolean esBioanalista() {
    return JComboEspecialidad.getSelectedItem().equals("Bioanalista");
}





// Método para actualizar deducciones específicas de Bioanalista
private void updateDeduccionesBioanalista() {
    updateDeducciones(
        jTextDcFlat1.getText(),
        jTextISRL1.getText(),
        null,
        jTextDcAlmc1.getText(),
        jTextDcNomina.getText(),
        jTextDcIncine1.getText(),
        jTextAnticipo1.getText(),
        jTextBioseguridad.getText(),
        JTextPrtjFundacion.getText(),
        jTextOtherDc1.getText(),
        jTextObservacion.getText()
    );
}

// Método para actualizar deducciones generales
private void updateDeduccionesGenerales() {
    updateDeducciones(
        jTextDcFlat.getText(),
        jTextISRL.getText(),
        jTextDcPersonal.getText(),
        jTextDcAlmc.getText(),
        null,
        null,
        jTextAnticipo.getText(),
        null,
        null,
        jTextOtherDc.getText(),
        jTextObservacion.getText()    
    );
}

// Método para agregar deducciones específicas de Bioanalista
private void agregarDeduccionesBioanalista() {
    agregarDeducciones(
        jTextDcFlat1.getText(),
        jTextISRL1.getText(),
        null,
        jTextDcAlmc1.getText(),
        jTextDcNomina.getText(),
        jTextDcIncine1.getText(),
        jTextAnticipo1.getText(),
        jTextBioseguridad.getText(),
        JTextPrtjFundacion.getText(),
        jTextOtherDc1.getText(),
        jTextObservacion.getText()  

    );
}

// Método para agregar deducciones generales
private void agregarDeduccionesGenerales() {
    agregarDeducciones(
        jTextDcFlat.getText(),
        jTextISRL.getText(),
        jTextDcPersonal.getText(),
        jTextDcAlmc.getText(),
        null,
        null,
        jTextAnticipo.getText(),
        null,
        null,
        jTextOtherDc.getText(),
        jTextObservacion.getText()  
    );
}





 public void callResumen() {
    String sql = "SELECT * FROM tableinfopdfs";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener información: " + e.getMessage());
    }
}
    


 public void callDeducciones() {
    String sql = "SELECT * FROM tableinfopdfs";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener información: " + e.getMessage());
    }
}
    


 public void callServicios() {
    String sql = "SELECT * FROM tableinfopdfs";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener información: " + e.getMessage());
    }
}
    



     
    public  void listarTablaC() {

        List<JCFactura> lista = factDao.listarHonorarioC(idHonorario);
        modelo = (DefaultTableModel) jTableServ.getModel();
        Object[] ob = new Object[8];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getServicioH();
            ob[1] = lista.get(i).getCantidadH();
            ob[2] = lista.get(i).getBrutoInitH();
            ob[3] = lista.get(i).getBrutoTotalH();
            ob[4] = lista.get(i).getPorcentajeH();
            ob[5] = lista.get(i).getNetoH();

            modelo.addRow(ob);

        }
       jTableServ.setModel(modelo);
  
 
     jTableServ.setDefaultEditor(Object.class, null);
     jTableServ.getTableHeader().setReorderingAllowed(false);
     
     
     acomodarTabla();
    }
    
    
 
public void knowResume() { 
    String sql = "SELECT res_cant, res_subtotal, res_deducciones, res_totalneto, res_facturar FROM `honorario_resumen` WHERE id_honorario = ?";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHonorario);  // Asegúrate de que idHonorario esté correctamente asignado
        ResultSet rs = ps.executeQuery();

        // Comprobar si se encuentran resultados
        if (rs.next()) {
            // Aquí podrías trabajar con los datos que recuperas
            int resCant = rs.getInt("res_cant");
            double resSubtotal = rs.getDouble("res_subtotal");
            double resDeducciones = rs.getDouble("res_deducciones");
            double resTotalNeto = rs.getDouble("res_totalneto");
            double resFacturar = rs.getDouble("res_facturar");
            
            jTextCantidad.setText(String.valueOf(resCant));
            jTextFacturar.setText(String.valueOf(resFacturar));
            jTextDcc.setText(String.valueOf(resDeducciones));
            jTextSubtotal.setText(String.valueOf(resSubtotal));
            jTextTotal.setText(String.valueOf(resTotalNeto));

      
        } else {
            System.out.println("No se encontraron resultados para el idHonorario: " + idHonorario);
        }

    } catch (SQLException e) {
        System.err.println("Error al recuperar los datos: " + e.getMessage());
        // Considerar lanzar una excepción personalizada o hacer un manejo de errores adecuado
    }
}

 
 
  
public void knowDeduccion() {
    String sql = "SELECT `Dec_flat`, `Dec_ISRL`, `Dec_personal`, `Dec_almacen`, `Dec_nomina`, `Dec_incineradora`, `Dec_anticipo`, `Dec_Bioseguridad`, `Fundacion`, `Dec_otras`, `observaciones` "
               + "FROM `honorario_deducciones` WHERE id_honorario = ?";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHonorario);  // Asegúrate de que idHonorario esté correctamente asignado
        ResultSet rs = ps.executeQuery();

        // Comprobar si se encuentran resultados
        if (rs.next()) {
            // Recupera los datos de la consulta y asignarlos a los JTextFields

            double decFlat = rs.getDouble("Dec_flat");
            double decISRL = rs.getDouble("Dec_ISRL");
            double decPersonal = rs.getDouble("Dec_personal");
            double decAlmacen = rs.getDouble("Dec_almacen");
            double decNomina = rs.getDouble("Dec_nomina");
            double decIncineradora = rs.getDouble("Dec_incineradora");
            double decAnticipo = rs.getDouble("Dec_anticipo");
            double decBioseguridad = rs.getDouble("Dec_Bioseguridad");
            double fundacion = rs.getDouble("Fundacion");
            double decOtras = rs.getDouble("Dec_otras");
            String observaciones = rs.getString("observaciones");

            // Asigna los valores recuperados a los JTextFields
            jTextDcFlat.setText(String.valueOf(decFlat));
            jTextISRL.setText(String.valueOf(decISRL));
            jTextDcPersonal.setText(String.valueOf(decPersonal));
            jTextDcAlmc.setText(String.valueOf(decAlmacen));
            jTextAnticipo.setText(String.valueOf(decAnticipo));
            
            jTextDcFlat1.setText(String.valueOf(decFlat));
            jTextISRL1.setText(String.valueOf(decISRL));
            jTextDcAlmc1.setText(String.valueOf(decAlmacen));
            jTextDcNomina.setText(String.valueOf(decNomina));
            jTextDcIncine1.setText(String.valueOf(decIncineradora));
            jTextAnticipo1.setText(String.valueOf(decAnticipo));
            jTextBioseguridad.setText(String.valueOf(decBioseguridad));
            JTextPrtjFundacion.setText(String.valueOf(fundacion));
            jTextOtherDc1.setText(String.valueOf(decOtras));
            jTextObservacion.setText(observaciones); 
            

        } else {
            System.out.println("No se encontraron resultados para el idHonorario: " + idHonorario);
        }

    } catch (SQLException e) {
        System.err.println("Error al recuperar los datos: " + e.getMessage());
        // Considerar lanzar una excepción personalizada o hacer un manejo de errores adecuado
    }
}
 
 
    
    
    
    
    
 

  JCFacturaDao factDao=  new JCFacturaDao();
  JCFactura fac= new JCFactura();
  Temporal Tempo = new Temporal();
  int idusuario=Tempo.getTexto();
  public int idHonorario=0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnCancelar;
    public javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnProcesar;
    private javax.swing.JButton BtnRemove;
    private javax.swing.JButton BtnVisualizar;
    private javax.swing.JLabel FechaAc5;
    public com.toedter.calendar.JDateChooser FechaOne;
    public com.toedter.calendar.JDateChooser FechaTwo;
    public javax.swing.JComboBox<String> JComboDoctor;
    public javax.swing.JComboBox<String> JComboEspecialidad;
    private javax.swing.JComboBox<String> JComboServ;
    private javax.swing.JTextField JTextPrtjFundacion;
    public javax.swing.JCheckBox jISRL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jQuitar;
    private javax.swing.JRadioButton jRadioConsulta;
    private javax.swing.JRadioButton jRadioOtros;
    private javax.swing.JRadioButton jRadioProc;
    private javax.swing.JRadioButton jRadioQuirurgico;
    private javax.swing.JMenuItem jRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSpinner jSpinnerCant;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableServ;
    private javax.swing.JTextField jTextAnticipo;
    private javax.swing.JTextField jTextAnticipo1;
    private javax.swing.JTextField jTextBioseguridad;
    private javax.swing.JTextField jTextBruto;
    private javax.swing.JTextField jTextBrutoTotal;
    private javax.swing.JTextField jTextCantidad;
    private javax.swing.JTextField jTextDcAlmc;
    private javax.swing.JTextField jTextDcAlmc1;
    private javax.swing.JTextField jTextDcFlat;
    private javax.swing.JTextField jTextDcFlat1;
    private javax.swing.JTextField jTextDcIncine1;
    private javax.swing.JTextField jTextDcNomina;
    private javax.swing.JTextField jTextDcPersonal;
    private javax.swing.JTextField jTextDcc;
    private javax.swing.JTextField jTextFacturar;
    private javax.swing.JTextField jTextISRL;
    private javax.swing.JTextField jTextISRL1;
    private javax.swing.JTextField jTextIsrlPrcj;
    private javax.swing.JTextField jTextIsrlPrcj1;
    private javax.swing.JTextField jTextNeto;
    private javax.swing.JTextArea jTextObservacion;
    private javax.swing.JTextField jTextOtherDc;
    private javax.swing.JTextField jTextOtherDc1;
    private javax.swing.JTextField jTextPorcentaje;
    private javax.swing.JTextField jTextSubtotal;
    private javax.swing.JTextField jTextTotal;
    private javax.swing.JTextField jTextotalNeto;
    private javax.swing.JTextField jTextsearch;
    // End of variables declaration//GEN-END:variables
}
