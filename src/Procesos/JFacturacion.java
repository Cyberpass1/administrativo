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
import Menu.Mprincipal;
import static Procesos.JHonorarios.corregirAcentos;

import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author FCGinebraI
 */
public class JFacturacion extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JFacturacion() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
  
  
   textPane.setEditable(false);
   showDivisa();
   TxtDivisa.setEditable(false);
  
   jTxtIvaGe.setEditable(false);
   jTxtFactura.setEditable(false);
   limpiarTabla();
   listarServicios();
   knowId();
   knowNote();
 jTxtIvaGe.setText("0");

   

   
   
   usuarioActivo =Tempo.getNombre();
   idUsuario=Tempo.getTexto();
   jTable2.getTableHeader().setReorderingAllowed(false);
   informacionpdf();

   
   
   
   jradioEntregaBsf.setSelected(true);
   jradioDls.setSelected(true);
   jradioEntrega.setSelected(true);
    }

   private  String usuarioActivo;
   private int idUsuario;


    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTxtFind = new javax.swing.JTextField();
        jTxtCedula = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTxtPagoBs = new javax.swing.JTextField();
        jCancelBs = new javax.swing.JButton();
        jFacturarBs = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtObsevBs = new javax.swing.JTextArea();
        jPVbs = new javax.swing.JCheckBox();
        jPagomovilBs = new javax.swing.JCheckBox();
        jEfectivoBs = new javax.swing.JCheckBox();
        jOtroBs = new javax.swing.JCheckBox();
        jTransferenciaBs = new javax.swing.JCheckBox();
        jradioEntregaBsf = new javax.swing.JRadioButton();
        jradioBsf = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jTxtDivisas = new javax.swing.JTextField();
        jCancelD = new javax.swing.JButton();
        jBtnFactD = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTxtObservD = new javax.swing.JTextArea();
        jPVdolar = new javax.swing.JCheckBox();
        jEfectvD = new javax.swing.JCheckBox();
        jOtroD = new javax.swing.JCheckBox();
        jZelleD = new javax.swing.JCheckBox();
        jTransfD = new javax.swing.JCheckBox();
        jradioDlrs = new javax.swing.JRadioButton();
        jradioDls = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jTxtPDMixto = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jTxtPagoMixto = new javax.swing.JButton();
        jTxtPBMixto = new javax.swing.JTextField();
        jEfectivoMix = new javax.swing.JCheckBox();
        jPtoVentaMix = new javax.swing.JCheckBox();
        jPgoMmix = new javax.swing.JCheckBox();
        jTransfMix = new javax.swing.JCheckBox();
        jOtroMix = new javax.swing.JCheckBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jZelleMix = new javax.swing.JCheckBox();
        jradioMixto = new javax.swing.JRadioButton();
        jradioEntrega = new javax.swing.JRadioButton();
        TxtDivisa = new javax.swing.JTextField();
        jTxtIvaD = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableServicio = new javax.swing.JTable();
        jTxtTbolivar = new javax.swing.JTextField();
        jTxtSubBS = new javax.swing.JTextField();
        jTxtIvaBsf = new javax.swing.JTextField();
        JTxtTDivisa = new javax.swing.JTextField();
        jTxtSubDolar = new javax.swing.JTextField();
        JTabbedForm = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTtxtPrecio = new javax.swing.JTextField();
        jTxtCode = new javax.swing.JTextField();
        jBtnAggPrd = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jTextServ = new javax.swing.JTextField();
        jSpinnerCant = new javax.swing.JSpinner();
        jTtxtPrecio1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTxtName = new javax.swing.JTextField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtCorreo = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        JRMasculino1 = new javax.swing.JRadioButton();
        JRfemenino1 = new javax.swing.JRadioButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTxtDireccion = new javax.swing.JTextArea();
        jTxtTelefono = new javax.swing.JFormattedTextField();
        jAgNuevo = new javax.swing.JButton();
        jAgregarCliente = new javax.swing.JButton();
        jTxtFactura = new javax.swing.JTextField();
        jTxtIvaGe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jNota = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 50));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "SERVICIO/PRODUCTO", "CANTIDAD", "PRECIO UNIT", "PRECIO TOTAL", "PRECIO BSF UNIT", "PRECIO BSF TOTAL"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 960, 130));

        jTxtFind.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar servicio/producto"));
        jTxtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtFindKeyReleased(evt);
            }
        });
        jPanel1.add(jTxtFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 440, 40));

        jTxtCedula.setText("V-");
        jTxtCedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "C.I CLIENTE"));
        jTxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCedulaActionPerformed(evt);
            }
        });
        jTxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyTyped(evt);
            }
        });
        jPanel1.add(jTxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 130, 40));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTxtPagoBs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOLIVARES"));
        jTxtPagoBs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPagoBsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtPagoBsKeyTyped(evt);
            }
        });
        jPanel2.add(jTxtPagoBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 40));

        jCancelBs.setText("CANCELAR");
        jCancelBs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCancelBs.setContentAreaFilled(false);
        jCancelBs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCancelBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelBsActionPerformed(evt);
            }
        });
        jPanel2.add(jCancelBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 120, 30));

        jFacturarBs.setText("PROCESAR");
        jFacturarBs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jFacturarBs.setContentAreaFilled(false);
        jFacturarBs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFacturarBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFacturarBsActionPerformed(evt);
            }
        });
        jPanel2.add(jFacturarBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 120, 30));

        jTxtObsevBs.setColumns(20);
        jTxtObsevBs.setLineWrap(true);
        jTxtObsevBs.setRows(5);
        jTxtObsevBs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observación"));
        jScrollPane1.setViewportView(jTxtObsevBs);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 5, 260, 150));

        jPVbs.setBackground(new java.awt.Color(255, 255, 255));
        jPVbs.setText("Punto de Venta");
        jPVbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPVbsActionPerformed(evt);
            }
        });
        jPanel2.add(jPVbs, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jPagomovilBs.setBackground(new java.awt.Color(255, 255, 255));
        jPagomovilBs.setText("Pago Movil");
        jPagomovilBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPagomovilBsActionPerformed(evt);
            }
        });
        jPanel2.add(jPagomovilBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jEfectivoBs.setBackground(new java.awt.Color(255, 255, 255));
        jEfectivoBs.setText("Efectivo");
        jEfectivoBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEfectivoBsActionPerformed(evt);
            }
        });
        jPanel2.add(jEfectivoBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jOtroBs.setBackground(new java.awt.Color(255, 255, 255));
        jOtroBs.setText("Otro");
        jOtroBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOtroBsActionPerformed(evt);
            }
        });
        jPanel2.add(jOtroBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jTransferenciaBs.setBackground(new java.awt.Color(255, 255, 255));
        jTransferenciaBs.setText("Transferencia");
        jTransferenciaBs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransferenciaBsActionPerformed(evt);
            }
        });
        jPanel2.add(jTransferenciaBs, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jradioEntregaBsf.setText("NOTA DE ENTREGA");
        jradioEntregaBsf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioEntregaBsfActionPerformed(evt);
            }
        });
        jPanel2.add(jradioEntregaBsf, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        jradioBsf.setText("FACTURA");
        jradioBsf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioBsfActionPerformed(evt);
            }
        });
        jPanel2.add(jradioBsf, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        jTabbedPane1.addTab("PAGO EN BSF", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTxtDivisas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DIVISAS"));
        jTxtDivisas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtDivisasKeyReleased(evt);
            }
        });
        jPanel4.add(jTxtDivisas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 40));

        jCancelD.setText("CANCELAR");
        jCancelD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCancelD.setContentAreaFilled(false);
        jCancelD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCancelD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelDActionPerformed(evt);
            }
        });
        jPanel4.add(jCancelD, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 120, 30));

        jBtnFactD.setText("PROCESAR");
        jBtnFactD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnFactD.setContentAreaFilled(false);
        jBtnFactD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnFactD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFactDActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnFactD, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 120, 30));

        jTxtObservD.setColumns(20);
        jTxtObservD.setLineWrap(true);
        jTxtObservD.setRows(5);
        jTxtObservD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observación"));
        jScrollPane4.setViewportView(jTxtObservD);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 5, 260, 150));

        jPVdolar.setBackground(new java.awt.Color(255, 255, 255));
        jPVdolar.setText("Punto de Venta");
        jPVdolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPVdolarActionPerformed(evt);
            }
        });
        jPanel4.add(jPVdolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jEfectvD.setBackground(new java.awt.Color(255, 255, 255));
        jEfectvD.setText("Efectivo");
        jEfectvD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEfectvDActionPerformed(evt);
            }
        });
        jPanel4.add(jEfectvD, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jOtroD.setBackground(new java.awt.Color(255, 255, 255));
        jOtroD.setText("Otro");
        jOtroD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOtroDActionPerformed(evt);
            }
        });
        jPanel4.add(jOtroD, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jZelleD.setBackground(new java.awt.Color(255, 255, 255));
        jZelleD.setText("Zelle");
        jZelleD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jZelleDActionPerformed(evt);
            }
        });
        jPanel4.add(jZelleD, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jTransfD.setBackground(new java.awt.Color(255, 255, 255));
        jTransfD.setText("Transferencia");
        jTransfD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransfDActionPerformed(evt);
            }
        });
        jPanel4.add(jTransfD, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jradioDlrs.setText("FACTURA");
        jradioDlrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioDlrsActionPerformed(evt);
            }
        });
        jPanel4.add(jradioDlrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        jradioDls.setText("NOTA DE ENTREGA");
        jradioDls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioDlsActionPerformed(evt);
            }
        });
        jPanel4.add(jradioDls, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        jTabbedPane1.addTab("PAGO EN DIVISA", jPanel4);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTxtPDMixto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DIVISAS"));
        jTxtPDMixto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPDMixtoKeyReleased(evt);
            }
        });
        jPanel9.add(jTxtPDMixto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 310, 40));

        jButton15.setText("CANCELAR");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton15.setContentAreaFilled(false);
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 120, 30));

        jTxtPagoMixto.setText("PROCESAR");
        jTxtPagoMixto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTxtPagoMixto.setContentAreaFilled(false);
        jTxtPagoMixto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTxtPagoMixto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPagoMixtoActionPerformed(evt);
            }
        });
        jPanel9.add(jTxtPagoMixto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 120, 30));

        jTxtPBMixto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOLIVARES"));
        jTxtPBMixto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPBMixtoKeyReleased(evt);
            }
        });
        jPanel9.add(jTxtPBMixto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 40));

        jEfectivoMix.setBackground(new java.awt.Color(255, 255, 255));
        jEfectivoMix.setText("Efectivo");
        jEfectivoMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEfectivoMixActionPerformed(evt);
            }
        });
        jPanel9.add(jEfectivoMix, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jPtoVentaMix.setBackground(new java.awt.Color(255, 255, 255));
        jPtoVentaMix.setText("Punto de Venta");
        jPtoVentaMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPtoVentaMixActionPerformed(evt);
            }
        });
        jPanel9.add(jPtoVentaMix, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jPgoMmix.setBackground(new java.awt.Color(255, 255, 255));
        jPgoMmix.setText("Pago Movil");
        jPgoMmix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPgoMmixActionPerformed(evt);
            }
        });
        jPanel9.add(jPgoMmix, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        jTransfMix.setBackground(new java.awt.Color(255, 255, 255));
        jTransfMix.setText("Transferencia");
        jTransfMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransfMixActionPerformed(evt);
            }
        });
        jPanel9.add(jTransfMix, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jOtroMix.setBackground(new java.awt.Color(255, 255, 255));
        jOtroMix.setText("Otro");
        jOtroMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOtroMixActionPerformed(evt);
            }
        });
        jPanel9.add(jOtroMix, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observación"));
        jScrollPane7.setViewportView(jTextArea4);

        jPanel9.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 5, 260, 150));

        jZelleMix.setBackground(new java.awt.Color(255, 255, 255));
        jZelleMix.setText("Zelle");
        jZelleMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jZelleMixActionPerformed(evt);
            }
        });
        jPanel9.add(jZelleMix, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jradioMixto.setText("FACTURA");
        jradioMixto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioMixtoActionPerformed(evt);
            }
        });
        jPanel9.add(jradioMixto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        jradioEntrega.setText("NOTA DE ENTREGA");
        jradioEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioEntregaActionPerformed(evt);
            }
        });
        jPanel9.add(jradioEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        jTabbedPane1.addTab("PAGO MIXTO", jPanel9);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 960, 200));

        TxtDivisa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TASA DIVISA"));
        jPanel1.add(TxtDivisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 110, 40));

        jTxtIvaD.setEditable(false);
        jTxtIvaD.setText("0");
        jTxtIvaD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "IVA DIVISA"));
        jPanel1.add(jTxtIvaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 460, 130, 40));

        jTableServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD", "SERVICIO/PRODUCTO", "PRECIO", "STOCK", "StockMin", "StockMax", "CATEGORIA"
            }
        ));
        jTableServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableServicioMouseEntered(evt);
            }
        });
        jScrollPane5.setViewportView(jTableServicio);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 440, 220));

        jTxtTbolivar.setEditable(false);
        jTxtTbolivar.setText("0");
        jTxtTbolivar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL BOLIVARES"));
        jPanel1.add(jTxtTbolivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 580, 290, 40));

        jTxtSubBS.setEditable(false);
        jTxtSubBS.setText("0");
        jTxtSubBS.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB TOTAL BOLIVARES"));
        jPanel1.add(jTxtSubBS, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 420, 140, 40));

        jTxtIvaBsf.setEditable(false);
        jTxtIvaBsf.setText("0");
        jTxtIvaBsf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "IVA BOLIVARES"));
        jPanel1.add(jTxtIvaBsf, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 460, 140, 40));

        JTxtTDivisa.setEditable(false);
        JTxtTDivisa.setText("0");
        JTxtTDivisa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL DIVISA"));
        jPanel1.add(JTxtTDivisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 530, 290, 40));

        jTxtSubDolar.setEditable(false);
        jTxtSubDolar.setText("0");
        jTxtSubDolar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB TOTAL DIVISA"));
        jPanel1.add(jTxtSubDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 420, 130, 40));

        JTabbedForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTtxtPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Precio"));
        jTtxtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTtxtPrecioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTtxtPrecioKeyReleased(evt);
            }
        });
        jPanel6.add(jTtxtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

        jTxtCode.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Codigo"));
        jTxtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCodeActionPerformed(evt);
            }
        });
        jTxtCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCodeKeyPressed(evt);
            }
        });
        jPanel6.add(jTxtCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 40));

        jBtnAggPrd.setText("AGREGAR");
        jBtnAggPrd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnAggPrd.setContentAreaFilled(false);
        jBtnAggPrd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnAggPrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAggPrdActionPerformed(evt);
            }
        });
        jPanel6.add(jBtnAggPrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 80, 40));

        jButton9.setText("QUITAR");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 80, 40));

        jButton17.setText("NUEVO");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton17.setContentAreaFilled(false);
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 80, 40));

        jTextServ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Servicio/Producto"));
        jTextServ.setEnabled(false);
        jTextServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextServActionPerformed(evt);
            }
        });
        jPanel6.add(jTextServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 290, 40));

        jSpinnerCant.setModel(new javax.swing.SpinnerNumberModel(1, 0, 40, 1));
        jPanel6.add(jSpinnerCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 290, 30));

        jTtxtPrecio1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Equivalente Bsf"));
        jPanel6.add(jTtxtPrecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 150, 40));

        JTabbedForm.addTab("Servicios", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setEnabled(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTxtName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nombre"));
        jTxtName.setEnabled(false);
        jPanel7.add(jTxtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        jTxtApellido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Apellido"));
        jTxtApellido.setEnabled(false);
        jPanel7.add(jTxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 170, 40));

        jTxtCorreo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Correo"));
        jTxtCorreo.setEnabled(false);
        jPanel7.add(jTxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 170, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sexo"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JRMasculino1.setBackground(new java.awt.Color(255, 255, 255));
        JRMasculino1.setText("Masculino");
        JRMasculino1.setEnabled(false);
        JRMasculino1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRMasculino1ActionPerformed(evt);
            }
        });
        jPanel8.add(JRMasculino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        JRfemenino1.setBackground(new java.awt.Color(255, 255, 255));
        JRfemenino1.setText("Femenino");
        JRfemenino1.setEnabled(false);
        JRfemenino1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRfemenino1ActionPerformed(evt);
            }
        });
        jPanel8.add(JRfemenino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 120, 80));

        jTxtDireccion.setColumns(20);
        jTxtDireccion.setRows(5);
        jTxtDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder("Dirección"));
        jTxtDireccion.setEnabled(false);
        jTxtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtDireccionKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(jTxtDireccion);

        jPanel7.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 350, 70));

        jTxtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Telefono"));
        try {
            jTxtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtTelefono.setEnabled(false);
        jPanel7.add(jTxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 170, 40));

        jAgNuevo.setText("CANCELAR");
        jAgNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAgNuevo.setContentAreaFilled(false);
        jAgNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jAgNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgNuevoActionPerformed(evt);
            }
        });
        jPanel7.add(jAgNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 120, 30));

        jAgregarCliente.setText("AGREGAR");
        jAgregarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAgregarCliente.setContentAreaFilled(false);
        jAgregarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jAgregarCliente.setEnabled(false);
        jAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgregarClienteActionPerformed(evt);
            }
        });
        jPanel7.add(jAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 120, 30));

        JTabbedForm.addTab("Datos", jPanel7);

        jPanel1.add(JTabbedForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 510, 220));

        jTxtFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "#CONTROL"));
        jPanel1.add(jTxtFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 120, 40));

        jTxtIvaGe.setText("0");
        jTxtIvaGe.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "IVA %"));
        jPanel1.add(jTxtIvaGe, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 110, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 240, 30));

        jLabel2.setText("FACTURA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, -1, 30));

        jScrollPane8.setViewportView(textPane);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 290, 360));

        jNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/reload.png"))); // NOI18N
        jNota.setBorder(null);
        jNota.setContentAreaFilled(false);
        jNota.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNotaActionPerformed(evt);
            }
        });
        jPanel1.add(jNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     String Sexo;
    private void jCancelBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelBsActionPerformed
         if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar esta factura?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {   
           cleanAll();
        }
     
    }//GEN-LAST:event_jCancelBsActionPerformed

    private void jFacturarBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFacturarBsActionPerformed
      try {
    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

    // Verificación de condiciones
    if (jTable2.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "PARA FACTURAR DEBE AGREGAR ALGUN ITEM A LA TABLA DE SERVICIOS/PRODUCTOS", "FACTURAR", JOptionPane.ERROR_MESSAGE);
        return;
    }

    else if (jTxtPagoBs.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "DEBE AGREGAR UN MONTO A PAGAR EN EL CAMPO BOLIVARES", "PAGO BOLIVARES", JOptionPane.ERROR_MESSAGE);
        return;
    }

    else if (!jPVbs.isSelected() && !jPagomovilBs.isSelected() &&
        !jTransferenciaBs.isSelected() && !jOtroBs.isSelected() &&
        !jEfectivoBs.isSelected()) {
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR POR LO MENOS UN METODO DE PAGO", "METODO PAGO", JOptionPane.ERROR_MESSAGE);
        return;
    }
    else{
        
      
    // Generación de la factura
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
    String rutaArchivo = "C:\\Cyberia\\Reportes\\factura.png";
    File ruta = new File(rutaArchivo);
   
  
    bill_print(); // calcular
 
    
    
    
    tipoPago="bsf";
    
    
    if(jradioBsf.isSelected()){
     
    //  ImprimirBill();
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Fact" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      factPdf(file);
      insertFact();
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "FACTURA GENERADA...", "FACTURA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowFact();
    
    }else if(jradioEntregaBsf.isSelected()){
      

      
      insertNota();
     
      
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Nota_Entrega" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      notaEntrega(file);
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "NOTA DE ENTREGA GENERADA...", "NOTA DE ENTREGA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowNote();
    }
       


    // Obtención de métodos de pago
    boolean ptoVenta = jPVbs.isSelected();
    boolean pagoMovil = jPagomovilBs.isSelected();
    boolean efectivo = jEfectivoBs.isSelected();
    boolean transferencia = jTransferenciaBs.isSelected();
    // boolean zelle = jPVbs.isSelected(); 
    boolean otro = jOtroBs.isSelected();

    
      
    restarProductos();
    agregarFormaPago(ptoVenta, pagoMovil, efectivo, transferencia, false, otro, jTxtObsevBs.getText(), 1);
    double pagoBsf=Double.parseDouble(jTxtPagoBs.getText());
    
    agregarMontos(pagoBsf,0); 
    agregarDetalles();
   
    
    
    cleanAll();
    limpiarTabla();
    listarServicios();
    
    
    
    }
 

} catch (Exception e) {
    System.out.println(e);
}
        
    }//GEN-LAST:event_jFacturarBsActionPerformed

    
    
    
    
    
    private void jBtnAggPrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAggPrdActionPerformed
  
  String code = jTxtCode.getText();
  int cantidad = (int) jSpinnerCant.getValue();  
  int rowCount = jTable2.getRowCount();
  Object valueToFind = jTextServ.getText();
  boolean exists = false;

for (int i = 0; i < rowCount; i++) {
    Object cellValue = jTable2.getValueAt(i,1);
    if (cellValue != null && cellValue.equals(valueToFind)) {
        exists = true;
        break;
    }
}

if (exists) {
    JOptionPane.showMessageDialog(null, "NO PUEDEN EXISTIR ELEMENTOS REPETIDOS EN LA FACTURA", "FACTURA", JOptionPane.ERROR_MESSAGE);
} else if (jTextServ.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "EL CAMPO DE SERVICIO NO PUEDE ESTAR VACIO", "FACTURA", JOptionPane.ERROR_MESSAGE);
} 
else if (jLabel1.getText().equals("")){
  JOptionPane.showMessageDialog(null, "DEBE ASIGNAR UN CLIENTE A UNA FACTURA", "FACTURA", JOptionPane.ERROR_MESSAGE);
}
else if (code.startsWith("PDR-") && existenciaStock==0){
  JOptionPane.showMessageDialog(null, "ESTE PRODUCTO HA AGOTADO TODA SU EXISTENCIA, DEBE REPONER EN INVENTARIO", "FACTURA", JOptionPane.ERROR_MESSAGE);
}

else if (code.startsWith("PDR-") && cantidad > existenciaStock){
  JOptionPane.showMessageDialog(null, "NO CUENTA CON SUFICIENTE STOCK DEL PRODUCTO, STOCK DISPOBLE: "+ existenciaStock, "FACTURA", JOptionPane.ERROR_MESSAGE);
}

else {
   
    try{
    addexamen();
    calcularTotales();
    
 
    textPane.setText("");
    bill_print();
    
      cleanPrd();
    
    }catch(Exception e){System.out.println(e);}
  
   
    //BtnAprocedimiento.setEnabled(true);

}

    
    }//GEN-LAST:event_jBtnAggPrdActionPerformed

    
   
    
 
public void addexamen() {
    // Formateador para limitar a dos decimales
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    // Obtención de valores
    String producto = jTextServ.getText();
    int cantidad = (int) jSpinnerCant.getValue();
    double precioDolar = Double.parseDouble(jTtxtPrecio.getText());
    double totalDolar = cantidad * precioDolar;
    
    double tasaBsf = Double.parseDouble(divisa);
    double precioBolivar = precioDolar * tasaBsf;
    double totalBolivar = precioBolivar * cantidad;

    // Aplicar el formato de dos decimales
    String precioDolarStr = decimalFormat.format(precioDolar);
    String totalDolarStr = decimalFormat.format(totalDolar);
    String precioBolivarStr = decimalFormat.format(precioBolivar);
    String totalBolivarStr = decimalFormat.format(totalBolivar);

    // Crear el modelo de la tabla
    modelo = (DefaultTableModel) jTable2.getModel();

    // Crear la lista con los valores formateados
    ArrayList<String> lista = new ArrayList<>();
    lista.add(jTxtCode.getText());
    lista.add(producto);
    lista.add(String.valueOf(cantidad));
    lista.add(precioDolarStr);
    lista.add(totalDolarStr);
    lista.add(precioBolivarStr);
    lista.add(totalBolivarStr);

    // Crear un arreglo de objetos para añadir a la tabla
    Object[] ob = new Object[8];
    ob[0] = lista.get(0);
    ob[1] = lista.get(1);
    ob[2] = lista.get(2);
    ob[3] = lista.get(3);
    ob[4] = lista.get(4);
    ob[5] = lista.get(5);
    ob[6] = lista.get(6);

    // Añadir la fila al modelo de la tabla
    modelo.addRow(ob);
    jTable2.setModel(modelo);
    jTable2.setDefaultEditor(Object.class, null);

    // Llamada a un método adicional (si es necesario)
    acomodarVentas();
}
    
    

    public void calcularTotales() {
        double sumaTotalD = 0;
        double sumaTotalB = 0;
        int rowCount = jTable2.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object totalDolar = jTable2.getValueAt(i, 4);
            Object TotalBolivar = jTable2.getValueAt(i, 6);

            try {
                sumaTotalD += parseToDouble(totalDolar);
                sumaTotalB += parseToDouble(TotalBolivar);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir valor: " + e.getMessage());
            }
        }

       // System.out.println("sumaTotalD: " + sumaTotalD);
        //System.out.println("sumaTotalB: " + sumaTotalB);
        
       // ----------------------------------------------------------------------IVA
        double ivaPrcje = 0;
        try {
            ivaPrcje = Double.parseDouble(jTxtIvaGe.getText());
        } catch (NumberFormatException e) {
            ivaPrcje = 0; // Manejo de error
            System.err.println("Error: Valor de IVA no válido.");
        }
        double ivaDecimal = ivaPrcje / 100;

        double calculoIvaD = sumaTotalD * ivaDecimal;
        double totalDolar = sumaTotalD + calculoIvaD;

        double calculoIvaB = sumaTotalB * ivaDecimal;
        double totalBolivar = sumaTotalB + calculoIvaB;

        
        sumaSubTtl=sumaTotalB;
        sumaIvaTtl=calculoIvaB;
        sumaTotal=totalBolivar;
        // ----------------------------------------------------------------------IVA
        

       //-----------------------------------------------------------------------IMPUESTO IGFT
       
     
       
        
        
        
        
        
        
        // Usar una clase interna para encapsular los valores
        class UIUpdater {
            private final double sumaTotalD;
            private final double calculoIvaD;
            private final double totalDolar;
            private final double sumaTotalB;
            private final double calculoIvaB;
            private final double totalBolivar;

            UIUpdater(double sumaTotalD, double calculoIvaD, double totalDolar,
                      double sumaTotalB, double calculoIvaB, double totalBolivar) {
                this.sumaTotalD = sumaTotalD;
                this.calculoIvaD = calculoIvaD;
                this.totalDolar = totalDolar;
                this.sumaTotalB = sumaTotalB;
                this.calculoIvaB = calculoIvaB;
                this.totalBolivar = totalBolivar;
            }

            void updateUI() {
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US); // Usa Locale.US para garantizar el punto como separador decimal
                symbols.setDecimalSeparator('.'); // Establece el punto como separador decimal
    
               // Configura DecimalFormat con los símbolos personalizados
                 DecimalFormat df = new DecimalFormat("#.##", symbols);
                 
                SwingUtilities.invokeLater(() -> {
                jTxtSubDolar.setText(df.format(sumaTotalD));
                jTxtIvaD.setText(df.format(calculoIvaD));
                JTxtTDivisa.setText(df.format(totalDolar));

                jTxtSubBS.setText(df.format(sumaTotalB));
                jTxtIvaBsf.setText(df.format(calculoIvaB));
                jTxtTbolivar.setText(df.format(totalBolivar));
                  
                
                /*
                double calculoIGFT= Double.parseDouble(JTxtTDivisa.getText()) *0.03 ;   
                jTxtIGFT.setText(df.format(calculoIGFT));
                
                double sumIGFT= Double.parseDouble(JTxtTDivisa.getText()) + calculoIGFT;
                JTxtTDivisa.setText(df.format(sumIGFT));
                */
                
                
                    
                });
            }
        }

        // Crear una instancia de la clase interna y actualizar la UI
        new UIUpdater(sumaTotalD, calculoIvaD, totalDolar, sumaTotalB, calculoIvaB, totalBolivar).updateUI();
    }

    
    private double sumaSubTtl=0;
    private double sumaIvaTtl=0;
    private double sumaTotal=0;
    
    
    private double parseToDouble(Object obj) throws NumberFormatException {
        if (obj instanceof String) {
            String text = (String) obj;
            // Convertir el texto a Double, manejando la posible configuración regional
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            decimalFormat.setParseBigDecimal(true);
            try {
                return decimalFormat.parse(text).doubleValue();
            } catch (ParseException e) {
                System.err.println("Error al analizar el número: " + e.getMessage());
                throw new NumberFormatException("Formato de número no válido.");
            }
        } else if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        throw new NumberFormatException("Formato de número no válido.");
    }
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
           int fila = jTable2.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA SERVICIOS/PRODUCTOS", "Fila",1);
        } else {
   

        try {
            int FilaRemover;
            DefaultTableModel Mode = (DefaultTableModel) jTable2.getModel();
            FilaRemover = this.jTable2.getSelectedRow();
            Mode.removeRow(FilaRemover);
            calcularTotales();
            textPane.setText("");
            bill_print();

        } catch (Exception e){ System.out.println("" + e);
        }  }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void JRMasculino1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRMasculino1ActionPerformed
        this.JRMasculino1.setSelected(true);
        JRfemenino1.setSelected(false);
        Sexo="Masculino";
    }//GEN-LAST:event_JRMasculino1ActionPerformed

    private void JRfemenino1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRfemenino1ActionPerformed
        Sexo="Femenino";
        this.JRMasculino1.setSelected(false);
        JRfemenino1.setSelected(true);
    }//GEN-LAST:event_JRfemenino1ActionPerformed

    private void jTxtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtDireccionKeyTyped

    private void jAgNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgNuevoActionPerformed
    
           if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {  
        
        cleanCliente(); }
    }//GEN-LAST:event_jAgNuevoActionPerformed

    
   
    public void cleanCliente(){
    
    jTxtCedula.setText("V-");
    jTxtName.setText("");
    jTxtApellido.setText("");
    jTxtCorreo.setText("");
    jTxtTelefono.setText("");
    JRMasculino1.setSelected(true);
    JRfemenino1.setSelected(false);
    Sexo="Masculino";
    JTabbedForm.setSelectedIndex(0);
    jLabel1.setText("");
    textPane.setText("");
    jTxtDireccion.setText("");
      showDivisa();
    }
    
    
    
    public void cleanPrd(){
    jTxtCode.setText("");
    jTextServ.setText("");
    jTtxtPrecio.setText("");
    jTtxtPrecio1.setText("");
    jSpinnerCant.setValue(1);
   
    }
    
    
    public void cleanAll(){
    
    cleanCliente();
    cleanPrd();
    limpiarTablaPrd();
    jTxtSubDolar.setText("");
    jTxtSubBS.setText("");
    jTxtIvaD.setText("");
    jTxtIvaBsf.setText("");
    JTxtTDivisa.setText("");
    jTxtTbolivar.setText("");
    jTxtObsevBs.setText("");
    jTxtFind.setText("");
    jTxtPagoBs.setText("");
    jTabbedPane1.setSelectedIndex(0);
    jPVbs.setSelected(false);
    jTransferenciaBs.setSelected(false);
    jPagomovilBs.setSelected(false);
    jOtroBs.setSelected(false);
    jEfectivoBs.setSelected(false);
    
    jEfectvD.setSelected(false);
    jPVdolar.setSelected(false);
    jZelleD.setSelected(false);
    jTransfD.setSelected(false);
    jOtroD.setSelected(false);
    
    jPtoVentaMix.setSelected(false);
    jPgoMmix.setSelected(false);
    jEfectivoMix.setSelected(false);
    jTransfMix.setSelected(false);
    jZelleMix.setSelected(false);
    jOtroMix.setSelected(false);
    
    JTxtTDivisa.setBackground(Color.WHITE);  
    jTxtTbolivar.setBackground(Color.WHITE); 
   
    sumaSubTtl=0;
    sumaIvaTtl=0;
    sumaTotal=0;
    
   jradioBsf.setSelected(false);
   jradioEntregaBsf.setSelected(true);
      
   jradioDlrs.setSelected(false);
   jradioDls.setSelected(true);
      
   jradioMixto.setSelected(false);
   jradioEntrega.setSelected(true);
   
  
    knowNote();
   knowId();
    showDivisa();
    }
    
    
    private void jAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgregarClienteActionPerformed
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = jTxtCorreo.getText();
        Matcher mather = pattern.matcher(Correo);
        Object Telefono = jTxtTelefono.getValue();
        
        


        if (jTxtName.getText().equals("") || jTxtApellido.getText().equals("")
            || jTxtTelefono.getText().equals("") || jTxtDireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);

        

        } /*else if (mather.find() == false) {
            JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
            this.TXTPcorreo.requestFocus();
        }*/

 
        /*  else if ( Telefono== null){
         JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NUMERO DE TELEFONO VALIDO", "NUMERO DE TELEFONO", JOptionPane.ERROR_MESSAGE);
        }
       */

        
        else {

            String sql="SELECT Idpaciente, Nombre, Apellido, Cedula, Telefono, Direccion FROM table_paciente  WHERE Cedula='"+ jTxtCedula.getText() +"'";

            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){JOptionPane.showMessageDialog(null, "EL PACIENTE YA SE ENCUENTRA", "DATO DUPLICADO",JOptionPane.ERROR_MESSAGE);}
               
                
                
                
                else{
                agregarPaciente(); 
                auditoriaAgregar();
                Callpacient();
                jAgregarCliente.setEnabled(false);
                JTabbedForm.setSelectedIndex(0);

                
               
                }
                
                
    
                
                

            }catch(Exception e){System.out.println(e);}
               finally {
            closeResources(rs, ps, con);
        }
        }
    }//GEN-LAST:event_jAgregarClienteActionPerformed

    
    
    
      
    
    public void agregarPaciente(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

              
                    

        
        

        
            String correo = jTxtCorreo.getText();
            if (correo.equals("")){ correo="Cyberia@gmail.com";}
        
            String telefono = jTxtTelefono.getText();
            if (jTxtTelefono.getValue()==null){telefono="0243-2362913";}
           
              
            String sql = "INSERT INTO table_paciente (Nombre, Apellido, Cedula,Telefono,Correo,Direccion, Sexo) VALUES (?,?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, jTxtName.getText());
            ps.setString(2, jTxtApellido.getText());
            ps.setString(3, jTxtCedula.getText());
            ps.setString(4, telefono);
            ps.setString(5, correo);
            ps.setString(6, jTxtDireccion.getText());
            ps.setString(7, Sexo);

            ps.execute();
  
            
            
  JOptionPane.showMessageDialog(null, "EL CLIENTE HA SIDO REGISTRADO","REGISTRO DE PACIENTES", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }   finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
    
    
    
public void Search() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String busqueda = this.jTxtFind.getText().trim().toLowerCase();
    String[] palabras = busqueda.split("\\s+"); // Separar por espacios

    // Construimos condiciones LIKE dinámicas
    StringBuilder condicionesProducto = new StringBuilder();
    StringBuilder condicionesServicio = new StringBuilder();

    for (int i = 0; i < palabras.length; i++) {
        condicionesProducto.append("LOWER(producto) LIKE ? AND ");
        condicionesServicio.append("LOWER(servicio) LIKE ? AND ");
    }

    // Eliminar el último " AND "
    if (condicionesProducto.length() > 0) {
        condicionesProducto.setLength(condicionesProducto.length() - 5);
        condicionesServicio.setLength(condicionesServicio.length() - 5);
    } else {
        // Si no hay palabras, busca todo
        condicionesProducto.append("1=1");
        condicionesServicio.append("1=1");
    }

    String sql = "SELECT \n" +
            "    id_producto AS idPrd, \n" +
            "    code_prd AS codigoPrd, \n" +
            "    producto AS Prd, \n" +
            "    precio AS precio, \n" +
            "    c.Catg_Prd AS categoriaPrd, \n" +
            "    existencia AS existencia, \n" +
            "    stockmin AS stockmin, \n" +
            "    stockmax AS stockmax, \n" +
            "    estadoPrd AS estado\n" +
            "FROM table_productos u\n" +
            "INNER JOIN categorias_productos c ON u.id_catgPrd = c.id_ctgPrd\n" +
            "WHERE " + condicionesProducto.toString() + "\n" +
            "UNION ALL\n" +
            "SELECT \n" +
            "    id_servicio AS idPrd, \n" +
            "    serv_codigo AS codigoPrd, \n" +
            "    servicio AS Prd, \n" +
            "    serv_precio AS precio, \n" +
            "    c.Catg_Servicio AS categoriaPrd, \n" +
            "    NULL AS existencia, \n" +
            "    NULL AS stockmin, \n" +
            "    NULL AS stockmax, \n" +
            "    serv_estado AS estado\n" +
            "FROM table_servicios s\n" +
            "INNER JOIN categorias_servicios c ON s.serv_categoria = c.Id_CatgServ\n" +
            "WHERE " + condicionesServicio.toString();

    DefaultTableModel Tabla = (DefaultTableModel) jTableServicio.getModel();
    jTableServicio.setDefaultEditor(Object.class, null);

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        // Asignar parámetros para productos y servicios
        int index = 1;
        for (String palabra : palabras) {
            ps.setString(index++, "%" + palabra + "%");
        }
        for (String palabra : palabras) {
            ps.setString(index++, "%" + palabra + "%");
        }

        rs = ps.executeQuery();
        limpiarTabla();

        while (rs.next()) {
            int col1 = rs.getInt("idPrd");
            String col2 = rs.getString("codigoPrd");
            String col3 = rs.getString("Prd");
            double col4 = rs.getDouble("precio");

            int col5 = rs.getObject("existencia") != null ? rs.getInt("existencia") : 0;
            int col6 = rs.getObject("stockmin") != null ? rs.getInt("stockmin") : 0;
            int col7 = rs.getObject("stockmax") != null ? rs.getInt("stockmax") : 0;
            String col8 = rs.getString("categoriaPrd");

            Vector<Object> vRow = new Vector<>();
            vRow.add(col1);
            vRow.add(col2);
            vRow.add(col3);
            vRow.add(col4);
            vRow.add(col5);
            vRow.add(col6);
            vRow.add(col7);
            vRow.add(col8);

            Tabla.addRow(vRow);
        }
    } catch (SQLException e) {
        System.out.println("Error en la consulta: " + e.getMessage());
    } finally {
        closeResources(rs, ps, con);
    }
}

    
    
    
    
    
    
    private void jCancelDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelDActionPerformed
    
        if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar esta factura?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {   
           cleanAll();
        }
     
    }//GEN-LAST:event_jCancelDActionPerformed

    private void jBtnFactDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFactDActionPerformed
  
      
      try {
    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

    // Verificación de condiciones
    if (jTable2.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "PARA FACTURAR DEBE AGREGAR ALGUN ITEM A LA TABLA DE SERVICIOS/PRODUCTOS", "FACTURAR", JOptionPane.ERROR_MESSAGE);
        return;
    }

    else if (jTxtDivisas.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "DEBE AGREGAR UN MONTO A PAGAR EN EL CAMPO BOLIVARES", "PAGO BOLIVARES", JOptionPane.ERROR_MESSAGE);
        return;
    }

    else  if (!jPVdolar.isSelected() && !jTransfD.isSelected() &&
        !jZelleD.isSelected() && !jOtroD.isSelected() &&
        !jEfectvD.isSelected()) {
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR POR LO MENOS UN METODO DE PAGO", "METODO PAGO", JOptionPane.ERROR_MESSAGE);
        return;
    }

    
    else{
    // Generación de la factura
   
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
    String rutaArchivo = "C:\\Cyberia\\Reportes\\factura.png";
    File ruta = new File(rutaArchivo);
   
   // knowCode();
    bill_print(); // calcular
    tipoPago="usd";
          
    if(jradioDlrs.isSelected()){
 //  ImprimirBill();
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Fact" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      factPdf(file);
      insertFact();
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "FACTURA GENERADA...", "FACTURA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowFact();
    
    }else if(jradioDls.isSelected()){
      insertNota();
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Nota_Entrega" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      notaEntrega(file);
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "NOTA DE ENTREGA GENERADA...", "NOTA DE ENTREGA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowNote();
    }
  

    // Obtención de métodos de pago
    boolean ptoVenta = jPVdolar.isSelected();
  //  boolean pagoMovil = jPagomovilBs.isSelected();
    boolean efectivo = jEfectvD.isSelected();
    boolean transferencia = jTransfD.isSelected();
    boolean zelle = jZelleD.isSelected(); 
    boolean otro = jOtroD.isSelected();

    
      
    restarProductos();
    agregarFormaPago(ptoVenta, false, efectivo, transferencia, zelle, otro, jTxtObservD.getText(), 2);
    double pagoDolar=Double.parseDouble(jTxtDivisas.getText());
    agregarMontos(0,pagoDolar); 
    agregarDetalles();
    
    
    
    cleanAll();
    limpiarTabla();
    listarServicios();
    
    }
    
    
 

} catch (Exception e) {
    System.out.println("ERROR EN FACTURAR DOLAR "+e);
}






 
    }//GEN-LAST:event_jBtnFactDActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar esta factura?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {   
           cleanAll();
        }
      
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTxtPagoMixtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPagoMixtoActionPerformed
   
  
        try{
           calcPagoMix();
           DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
      
            if(jTable2.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "PARA FACUTRAR DEBE AGREGAR ALGUN ITEM A LA TABLA DE SERVICIOS/PRODUCTOS","FACTURAR", JOptionPane.ERROR_MESSAGE);
            }
            
            
            
            else if(calculo!=pagoTDolar){
              JOptionPane.showMessageDialog(null, "EL PAGO NO COINCIDE CON EL MONTO A PAGAR","PAGO MIXTO", JOptionPane.ERROR_MESSAGE);
            }
            
            
              else if (!jPtoVentaMix.isSelected() && !jTransfMix.isSelected() &&
                    !jPgoMmix.isSelected() && !jZelleMix.isSelected() &&
                    !jEfectivoMix.isSelected() && !jOtroMix.isSelected()){
                    JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR POR LO MENOS UN METODO DE PAGO","METODO PAGO", JOptionPane.ERROR_MESSAGE);}
            
            
            
         else{
    // Generación de la factura
  
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
    String rutaArchivo = "C:\\Cyberia\\Reportes\\factura.png";
    File ruta = new File(rutaArchivo);
   
   // knowCode();
    bill_print(); // calcular
 
    
    
    
    
    if(jradioMixto.isSelected()){
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Fact" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      factPdf(file);
      insertFact();
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "FACTURA GENERADA...", "FACTURA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowFact();
     
    }else if(jradioEntrega.isSelected()){
      insertNota();
      tipoPago="mixto";
      DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy--HH-mm");
      String pdfName = "Nota_Entrega" + fth.format(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
      File file = new File("C:\\Cyberia\\Reportes\\" + pdfName + ".pdf");
      notaEntrega(file);
      guardarFactura(jTxtFactura.getText(), IdPaciente, idUsuario, idFacturacion, idFacturacion, fecha, file, 103);
      JOptionPane.showMessageDialog(null, "NOTA DE ENTREGA GENERADA...", "NOTA DE ENTREGA", JOptionPane.INFORMATION_MESSAGE);
      Desktop.getDesktop().open(file);
      knowNote();
    }

    // Obtención de métodos de pago
    boolean ptoVenta = jPtoVentaMix.isSelected();
    boolean pagoMovil = jPgoMmix.isSelected();
    boolean efectivo = jEfectivoMix.isSelected();
    boolean transferencia = jTransfMix.isSelected();
    boolean zelle = jZelleMix.isSelected(); 
    boolean otro = jOtroMix.isSelected();

    
      
    restarProductos();
    agregarFormaPago(ptoVenta, pagoMovil, efectivo, transferencia, zelle, otro, jTxtObservD.getText(), 2);
    
    double pagoBolivar=Double.parseDouble(jTxtPBMixto.getText());
    double pagoDolar=Double.parseDouble(jTxtPDMixto.getText());
    agregarMontos(pagoBolivar,pagoDolar); 
    agregarDetalles();
    
    
    cleanAll();
    limpiarTabla();
    listarServicios();
    
    }
          
        
        }catch(Exception e){System.out.println("ERROR EN FACTURAR PAGOMIXTO"+e);}
           
        
        
        
 
        
    
    }//GEN-LAST:event_jTxtPagoMixtoActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       cleanPrd();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextServActionPerformed

    
    
    int idService, existenciaStock, stockmin;
    private void jTableServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServicioMouseClicked
          int fila = jTableServicio.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            idService=(int) (jTableServicio.getValueAt(fila, 0));
           jTableServicio.getValueAt(fila, 7).toString();
          
           jTxtCode.setText(jTableServicio.getValueAt(fila, 1).toString());
           jTextServ.setText(jTableServicio.getValueAt(fila, 2).toString());
           jTtxtPrecio.setText(jTableServicio.getValueAt(fila, 3).toString());
          
           stockmin=(int) (jTableServicio.getValueAt(fila, 5));

        Object stock = jTableServicio.getValueAt(fila, 4);

        if (stock != null) {
       try {
        existenciaStock = Integer.parseInt(stock.toString()); // Convierte de String a int
        
           if(stockmin>=existenciaStock){
               String name = jTableServicio.getValueAt(fila, 2).toString() +" "+ jTableServicio.getValueAt(fila, 7).toString() ;
          //     JOptionPane.showMessageDialog(this,"EL PRODUCTO "+ name+" ESTA POR DEBAJO DEL STOCK MINIMO", "ALERTA DE STOCK", JOptionPane.WARNING_MESSAGE);
           }
           
    } catch (NumberFormatException e) {
        existenciaStock = 0; // En caso de que no sea un número válido
        System.out.println("Valor no válido para stock: " + stock);
    }  
} else {
    existenciaStock = 0; 
}
       //    System.out.println(existenciaStock);
       
         
         
           
           calcularMonto();
          
    }   
    }//GEN-LAST:event_jTableServicioMouseClicked

    private void jTxtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
     
        
        if(jTxtCedula.getText().equals("V-") || jTxtCedula.getText().equals("") )
        {
        JOptionPane.showMessageDialog(null, "CAMPO VACIO O CÉDULA INVALIDA", "CEDULA", JOptionPane.ERROR_MESSAGE);
        jTxtCedula.setText("V-");
        }
      
        else{
        textPane.setText("");
            try { 
                Callpacient();
                jTtxtPrecio.setEditable(true);
            } catch (PrinterException ex) {
                Logger.getLogger(JFacturacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JFacturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
     
         } 
           
        
        } 
    }//GEN-LAST:event_jTxtCedulaKeyPressed

    private void jTxtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyTyped
         char car = evt.getKeyChar();
        //VERIFICA Y LIMITA COMPOSICION DE LOS DATOS
        if((car=='V' || car=='E'  || car=='-'|| car=='J'|| car =='H' || car>='0' && car<='9' || car==(char)KeyEvent.VK_DELETE))
        {
            String Caracteres = jTxtCedula.getText();
            //CONTABILIZA LOS CARACTERES
            if(Caracteres.length()==14)
            {
                evt.consume();
            }
        }
        //EVITA EL INGRESO DE OTROS CARACTERES
        else if((car!='V' && car!='E' && car!='-' && car != 'J' && car!='H' || car<'0' || car>'9' || car!=(char)KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTxtCedulaKeyTyped

    private void jTtxtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTtxtPrecioKeyPressed
          if (!jTtxtPrecio.getText().isEmpty()) {
            calcularMonto();
       }
          
          else{jTtxtPrecio1.setText("");};
    }//GEN-LAST:event_jTtxtPrecioKeyPressed

    private void jTableServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServicioMouseEntered
      jTableServicio.requestFocusInWindow();
    }//GEN-LAST:event_jTableServicioMouseEntered

    
    
    
    

   
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
       int fila = jTable2.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

          //  cantTabla=(int) (jTable2.getValueAt(fila, 1));
           // precioUnitD=(double) (jTable2.getValueAt(fila, 2));
          //  precioTotD=(double) (jTable2.getValueAt(fila, 3));
             
         //   precioUnitB=(double) (jTable2.getValueAt(fila, 4));
          //  precioTotB=(double) (jTable2.getValueAt(fila, 5));
       

          
    }   
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTxtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFindKeyReleased
            Search(); acomodarListado();
    }//GEN-LAST:event_jTxtFindKeyReleased

    private void jTxtPagoBsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPagoBsKeyReleased
   
        
        
        
        
     JTxtTDivisa.setBackground(Color.WHITE);  
     jTxtTbolivar.setBackground(Color.WHITE); 
        
     
     if(jTxtPagoBs.getText().equals(jTxtTbolivar.getText())){
        
  jTxtTbolivar.setBackground(new Color(144, 238, 144)); // Light green
        }
       
        else if (jTxtPagoBs.getText().equals("")){
        
         JTxtTDivisa.setBackground(Color.WHITE);  
        jTxtTbolivar.setBackground(Color.WHITE); 
        }
        
        else{
         jTxtTbolivar.setBackground(new Color(255, 182, 182));
        }
        
        
     
     
        
    }//GEN-LAST:event_jTxtPagoBsKeyReleased

    private void jTxtDivisasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtDivisasKeyReleased
     

          
              if(jTxtDivisas.getText().equals(JTxtTDivisa.getText())){
        
        JTxtTDivisa.setBackground(new Color(144, 238, 144)); // Light green
        
        }
         
         
            else if (jTxtDivisas.getText().equals("")){
        
         JTxtTDivisa.setBackground(Color.WHITE);  
        jTxtTbolivar.setBackground(Color.WHITE); 
        }
        
            
            
            
        else{
         JTxtTDivisa.setBackground(new Color(255, 182, 182));
       
    
         
        }
         
    }//GEN-LAST:event_jTxtDivisasKeyReleased

    private void jTxtPBMixtoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPBMixtoKeyReleased
      calcPagoMix();
    }//GEN-LAST:event_jTxtPBMixtoKeyReleased

    private void jTxtPDMixtoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPDMixtoKeyReleased
       calcPagoMix();
    }//GEN-LAST:event_jTxtPDMixtoKeyReleased

    private void jTxtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCodeActionPerformed

    private void jTxtCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCodeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
     
        if (jLabel1.getText().equals("")){
  JOptionPane.showMessageDialog(null, "DEBE ASIGNAR UN CLIENTE A UNA FACTURA", "FACTURA", JOptionPane.ERROR_MESSAGE);
}
       else if(jTxtCode.getText().equals("") )
        {
        JOptionPane.showMessageDialog(null, "CAMPO VACIO INGRESE EL CODIGO DE UN PRODUCTO", "CAMPOS", JOptionPane.ERROR_MESSAGE);
    
        }
        
      
        else{
        
     addTableFromEvent();
        }
        } 
    }//GEN-LAST:event_jTxtCodeKeyPressed

    
    private Boolean PtoVentaBs=false;
    private Boolean PgoMovilBs=false;
    private Boolean EfectivoBs=false;
    private Boolean TransferenciaBs=false;
    private Boolean OtroBs=false;
    
    private Boolean PtoVentaDr=false;
    private Boolean EfectivoDr=false;
    private Boolean TransferenciaDr=false;
    private Boolean Zelle=false;
    private Boolean OtroDr=false;
    
    
    private Boolean PtoVentaMix=false;
    private Boolean PgoMovilMix=false;
    private Boolean EfectivoMix=false;
    private Boolean TransferenciaMix=false;
    private Boolean ZelleMix=false;
    private Boolean OtroMix=false;
    
   
    private void jPVbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPVbsActionPerformed
    PtoVentaBs = jPVbs.isSelected() ? true : false;
    idMetodoPago=1;
    }//GEN-LAST:event_jPVbsActionPerformed

    private void jPagomovilBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPagomovilBsActionPerformed
       PgoMovilBs = jPagomovilBs.isSelected() ? true : false;
           idMetodoPago=1;
    }//GEN-LAST:event_jPagomovilBsActionPerformed

    private void jEfectivoBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEfectivoBsActionPerformed
      EfectivoBs = jEfectivoBs.isSelected() ? true : false;
          idMetodoPago=1;
    }//GEN-LAST:event_jEfectivoBsActionPerformed

    private void jTransferenciaBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransferenciaBsActionPerformed
        TransferenciaBs = jTransferenciaBs.isSelected() ? true : false;
            idMetodoPago=1;
    }//GEN-LAST:event_jTransferenciaBsActionPerformed

    private void jOtroBsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOtroBsActionPerformed
         OtroBs = jOtroBs.isSelected() ? true : false;
             idMetodoPago=1;
    }//GEN-LAST:event_jOtroBsActionPerformed

    private void jPVdolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPVdolarActionPerformed
     PtoVentaDr = jPVdolar.isSelected() ? true : false;
         idMetodoPago=2;
    }//GEN-LAST:event_jPVdolarActionPerformed

    private void jZelleDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jZelleDActionPerformed
         Zelle = jZelleD.isSelected() ? true : false;
           idMetodoPago=2;
    }//GEN-LAST:event_jZelleDActionPerformed

    private void jEfectvDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEfectvDActionPerformed
         EfectivoDr = jEfectvD.isSelected() ? true : false;
           idMetodoPago=2;
    }//GEN-LAST:event_jEfectvDActionPerformed

    private void jTransfDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransfDActionPerformed
       TransferenciaDr = jTransfD.isSelected() ? true : false;
         idMetodoPago=2;
    }//GEN-LAST:event_jTransfDActionPerformed

    private void jOtroDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOtroDActionPerformed
    OtroDr = jTransfD.isSelected() ? true : false;
      idMetodoPago=2;
    }//GEN-LAST:event_jOtroDActionPerformed

    private void jPtoVentaMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPtoVentaMixActionPerformed
        OtroDr = jTransfD.isSelected() ? true : false;
     idMetodoPago=3;
    }//GEN-LAST:event_jPtoVentaMixActionPerformed

    private void jTxtPagoBsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPagoBsKeyTyped
    char car = evt.getKeyChar();
    String caracteres = jTxtPagoBs.getText();

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
    }//GEN-LAST:event_jTxtPagoBsKeyTyped

    private void jTxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCedulaActionPerformed

    private void jTransfMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransfMixActionPerformed
         idMetodoPago=3;
    }//GEN-LAST:event_jTransfMixActionPerformed

    private void jPgoMmixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPgoMmixActionPerformed
        idMetodoPago=3;
    }//GEN-LAST:event_jPgoMmixActionPerformed

    private void jZelleMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jZelleMixActionPerformed
      idMetodoPago=3;
    }//GEN-LAST:event_jZelleMixActionPerformed

    private void jEfectivoMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEfectivoMixActionPerformed
      idMetodoPago=3;
    }//GEN-LAST:event_jEfectivoMixActionPerformed

    private void jOtroMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOtroMixActionPerformed
       idMetodoPago=3;
    }//GEN-LAST:event_jOtroMixActionPerformed

    private void jNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNotaActionPerformed
        showDivisa();
    }//GEN-LAST:event_jNotaActionPerformed

    private void jradioBsfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioBsfActionPerformed
        jradioBsf.setSelected(true);
        jradioEntregaBsf.setSelected(false);
        knowFact();
        showDivisa();
    }//GEN-LAST:event_jradioBsfActionPerformed

    private void jradioEntregaBsfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioEntregaBsfActionPerformed
        jradioBsf.setSelected(false);
        jradioEntregaBsf.setSelected(true);
         knowNote();
         jTxtIvaGe.setText("0");
    }//GEN-LAST:event_jradioEntregaBsfActionPerformed

    private void jradioDlrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioDlrsActionPerformed
        jradioDlrs.setSelected(true);
        jradioDls.setSelected(false);
        knowFact();
        showDivisa();
    }//GEN-LAST:event_jradioDlrsActionPerformed

    private void jradioDlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioDlsActionPerformed
        jradioDlrs.setSelected(false);
        jradioDls.setSelected(true);
        knowNote();
         jTxtIvaGe.setText("0");
    }//GEN-LAST:event_jradioDlsActionPerformed

    private void jradioMixtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioMixtoActionPerformed
        jradioMixto.setSelected(true);
        jradioEntrega.setSelected(false);
        knowFact();
        showDivisa();
    }//GEN-LAST:event_jradioMixtoActionPerformed

    private void jradioEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioEntregaActionPerformed
        jradioMixto.setSelected(false);
        jradioEntrega.setSelected(true);
         jTxtIvaGe.setText("0");
         knowNote();
    }//GEN-LAST:event_jradioEntregaActionPerformed

    private void jTtxtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTtxtPrecioKeyReleased
     if (!jTtxtPrecio.getText().isEmpty()) {
            calcularMonto();
       }
          
          else{jTtxtPrecio1.setText("");};
    }//GEN-LAST:event_jTtxtPrecioKeyReleased

    
    
    
    
 
    
    double pagoTDolar, calculo;


  public void calcPagoMix() {
    try {
        // Obtén los textos de los JTextField
        String divisaText = divisa;  // Asegúrate de que 'divisa' esté definido y sea accesible
        String pagoBsfText = jTxtPBMixto.getText();
        String pagoDolarText = jTxtPDMixto.getText(); 
        String totalPagar = JTxtTDivisa.getText();
        
        
        
        // Verifica que los textos no estén vacíos
        if (divisaText.isEmpty() || pagoBsfText.isEmpty() || pagoDolarText.isEmpty()) {   
            JTxtTDivisa.setBackground(Color.WHITE);  
            jTxtTbolivar.setBackground(Color.WHITE);
            return;
        }
        
        
        
        
        // Convierte los textos a números
        double tasaBsf = Double.parseDouble(divisaText);
        double pagoBsf = Double.parseDouble(pagoBsfText);
        double pagoDolar = Double.parseDouble(pagoDolarText);
        pagoTDolar = Double.parseDouble(totalPagar);
        
        
        // Calcula la conversión y el cálculo final
        double conversion = pagoBsf / tasaBsf;
        calculo = conversion + pagoDolar;
        
        // Imprime valores para depuración
       // System.out.println("Tasa de BSF: " + tasaBsf);
       // System.out.println("Pago en BSF: " + pagoBsf);
      //  System.out.println("Pago en Dólares: " + pagoDolar);
     //   System.out.println("Conversión (pago en BSF / tasa): " + conversion);
    //    System.out.println("Cálculo final (conversión + pago en Dólares): " + calculo);
        
        // Compara el valor calculado con el texto en jTxtPDMixto (que probablemente no tiene sentido aquí)
        // Asegúrate de comparar con el valor correcto, por ejemplo, usando una comparación con el 'calculo' o con el texto esperado
        if (calculo==pagoTDolar) {
            JTxtTDivisa.setBackground(new Color(144, 238, 144)); // Verde claro
            jTxtTbolivar.setBackground(new Color(144, 238, 144)); // Verde claro
        } else {
            JTxtTDivisa.setBackground(new Color(255, 182, 182)); // Rojo claro
            jTxtTbolivar.setBackground(new Color(255, 182, 182));  // Verde claro
        }
    } catch (NumberFormatException e) {
        System.out.println("Error en calcPagoMix: " + e.getMessage());
        JTxtTDivisa.setBackground(new Color(255, 182, 182)); // Rojo claro
    } catch (Exception e) {
        System.out.println("Error en calcPagoMix: " + e.getMessage());
        JTxtTDivisa.setBackground(new Color(255, 182, 182)); // Rojo claro
    }
}
    
    
    
    
    
private double equivalenciaBsf=0; 
public void calcularMonto(){

showDivisa();

double tasaBsf = Double.parseDouble(divisa);
double precioActual=Double.parseDouble(jTtxtPrecio.getText());


equivalenciaBsf=precioActual*tasaBsf;
jTtxtPrecio1.setText(String.format("%.2f", equivalenciaBsf)); 

}

    
    
    
   String divisa, fechDivisa, ivaPrcentaje;
   int idDivisa;   
   
 
   

 
   
   public void showDivisa() {

   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
   
        try {

            String sql = "SELECT valor_dia, iva, Fecha\n" +
            "FROM table_divisas\n" +
            "WHERE id_divisa = (SELECT MAX(id_divisa) FROM table_divisas);";

            con = cn.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
               // idDivisa= rs.getInt("id_divisa");
                divisa = rs.getString("valor_dia");
                fechDivisa = rs.getString("Fecha");
                ivaPrcentaje= rs.getString("iva");
                TxtDivisa.setText(divisa);
                
                if(!jradioEntrega.isSelected() || !jradioEntregaBsf.isSelected() || !jradioDls.isSelected()){
                  jTxtIvaGe.setText(ivaPrcentaje);
                }
              
            }

        } catch (Exception e) {System.out.println(e);
        }
        
          finally {
            closeResources(rs, ps, con);
        }
    }
         
    
    int stockMax=0, stockMin=0, existencia;
    
   public void addTableFromEvent() {


    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Definir la consulta SQL
      String sql = "SELECT \n" +
                     "    id_producto AS idPrd, \n" +
                     "    code_prd AS codigoPrd, \n" +
                     "    producto AS Prd, \n" +
                     "    precio AS precio, \n" +
                     "    c.Catg_Prd AS categoriaPrd, \n" +
                     "    existencia AS existencia, \n" +
                     "    stockmin AS stockmin, \n" +
                     "    stockmax AS stockmax, \n" +
                     "    estadoPrd AS estado\n" +
                     "FROM \n" +
                     "    table_productos u\n" +
                     "INNER JOIN \n" +
                     "    categorias_productos c \n" +
                     "    ON u.id_catgPrd = c.id_ctgPrd\n" +
                     "WHERE code_prd = ? \n" +  // Filtro aplicado aquí
                     "UNION ALL\n" +
                     "SELECT \n" +
                     "    id_servicio AS idPrd, \n" +
                     "    serv_codigo AS codigoPrd, \n" +
                     "    servicio AS Prd, \n" +
                     "    serv_precio AS precio, \n" +
                     "    c.Catg_Servicio AS categoriaPrd, \n" +
                     "    NULL AS existencia, \n" +
                     "    NULL AS stockmin, \n" +
                     "    NULL AS stockmax, \n" +
                     "    serv_estado AS estado\n" +
                     "FROM \n" +
                     "    table_servicios s\n" +
                     "INNER JOIN \n" +
                     "    categorias_servicios c \n" +
                     "    ON s.serv_categoria = c.Id_CatgServ\n" +
                     "WHERE serv_codigo = ?";  // Filtro aplicado aquí


        // Obtener la conexión
        con = cn.getConnection();

        // Preparar el statement
        ps = con.prepareStatement(sql);
        ps.setString(1, jTxtCode.getText());
        ps.setString(2, jTxtCode.getText());
        // Ejecutar la consulta
        rs = ps.executeQuery();

        // Procesar el resultado
        if (rs.next()) {
            jTxtCode.setText(rs.getString("codigoPrd"));
            jTextServ.setText(rs.getString("Prd"));
            jTtxtPrecio.setText(rs.getString("precio"));
            existencia=(rs.getInt("existencia"));
            stockMin=(rs.getInt("stockmin"));
            stockMax=(rs.getInt("stockmax"));
            
            
            
            
int rowCount = jTable2.getRowCount();
Object valueToFind = jTextServ.getText();
boolean exists = false;
String code = jTxtCode.getText();
int cantidad = (int) jSpinnerCant.getValue();
 
for (int i = 0; i < rowCount; i++) {
    Object cellValue = jTable2.getValueAt(i, 0);
    if (cellValue != null && cellValue.equals(valueToFind)) {
        exists = true;
        break;
    }
}

if (exists) {
    JOptionPane.showMessageDialog(null, "NO PUEDEN EXISTIR ELEMENTOS REPETIDOS EN LA FACTURA", "FACTURA", JOptionPane.ERROR_MESSAGE);
} else if (jTextServ.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "EL CAMPO DE SERVICIO NO PUEDE ESTAR VACIO", "FACTURA", JOptionPane.ERROR_MESSAGE);
} 
else if (jLabel1.getText().equals("")){
  JOptionPane.showMessageDialog(null, "DEBE ASIGNAR UN CLIENTE A UNA FACTURA", "FACTURA", JOptionPane.ERROR_MESSAGE);
}
else if (code.startsWith("PDR-") && existenciaStock==0){
  JOptionPane.showMessageDialog(null, "ESTE PRODUCTO HA AGOTADO TODA SU EXISTENCIA, DEBE REPONER EN INVENTARIO", "FACTURA", JOptionPane.ERROR_MESSAGE);
}

else if (code.startsWith("PDR-") && cantidad > existenciaStock){
  JOptionPane.showMessageDialog(null, "NO CUENTA CON SUFICIENTE STOCK DEL PRODUCTO, STOCK DISPOBLE: "+ existenciaStock, "FACTURA", JOptionPane.ERROR_MESSAGE);
}        
            
else{
            addexamen();
            calcularTotales();
            textPane.setText("");
            bill_print();
            calcularMonto();
}
            
            
        
            
            
            
            
            
            
            
            
        } else {
            JOptionPane.showMessageDialog(null, "EL CODIGO INGRESADO NO EXISTE", "CODIGO", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        System.out.println(e);
    }   finally {
            closeResources(rs, ps, con);
        }
}
   
   
   
  
    
   
    public  void listarServicios() {

        List<JCFactura> lista = factDao.listarServicios();
        modelo = (DefaultTableModel) jTableServicio.getModel();
        Object[] ob = new Object[8];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getServ_id();
            ob[1] = lista.get(i).getServ_code();
            ob[2] = lista.get(i).getServicio();
            ob[3] = lista.get(i).getServ_precio();
            ob[4] = lista.get(i).getExistencia();
            ob[5] = lista.get(i).getStockmin();
            ob[6] = lista.get(i).getStockmax();
            ob[7] = lista.get(i).getCtgProducto();

            modelo.addRow(ob);

        }
       jTableServicio.setModel(modelo);
     acomodarListado();
 
     jTableServicio.setDefaultEditor(Object.class, null);
     jTableServicio.getTableHeader().setReorderingAllowed(false);
    }
    
    
    
   
    
     private int IdPaciente;
    
    public void Callpacient() throws PrinterException, IOException {  
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EnlaceBd cn = new EnlaceBd();

        String Cedula = jTxtCedula.getText();
        
        if (Cedula.isEmpty() || Cedula.equals("V-")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO CLIENTE CÉDULA NO PUEDE ESTAR VACIO Y DEBE LLEVAR V-", "CAMPO VACIO", JOptionPane.ERROR_MESSAGE);
            return; // Salimos si el campo de cédula no es válido
        }

        try {
            String query = "SELECT Idpaciente FROM table_paciente WHERE Cedula=?";
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, Cedula);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                IdPaciente = rs.getInt("Idpaciente");

                // Consulta los detalles del paciente
                String sql2 = "SELECT Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo FROM table_paciente WHERE Idpaciente=?";
                ps = con.prepareStatement(sql2);
                ps.setInt(1, IdPaciente);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Obtener los datos
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    String cedula = rs.getString("Cedula");
                    String telefono = rs.getString("Telefono");
                    String correo = rs.getString("Correo");
                    String direccion = rs.getString("Direccion");
                    String sexo = rs.getString("Sexo");
       

                    // Asignar valores a la interfaz
                    jTxtName.setText(nombre);
                    jTxtApellido.setText(apellido);
                    jTxtCorreo.setText(correo);
                    jTxtTelefono.setText(telefono);
                    jTxtDireccion.setText(direccion);

                    // Configurar sexo
                    if (sexo.equals("Femenino")) {
                        JRfemenino1.setSelected(true);
                        JRMasculino1.setSelected(false);
                    } else if (sexo.equals("Masculino")) {
                        JRMasculino1.setSelected(true);
                        JRfemenino1.setSelected(false);
                    }

              
         
                    jLabel1.setText(nombre + " " + apellido);

                    // Llamar a las funciones adicionales
                    desactivarCampos();
                    bill_print();
                }
            } else {
           
                if (JOptionPane.showConfirmDialog(rootPane, "El cliente no se encuentra registrado. ¿Desea registrarlo?",
                        "Registro Cliente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    JTabbedForm.setSelectedIndex(1);
                    cleanRegister();
                    activarCampos();
                } else {
                    JTabbedForm.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

     
     
  public JFacturacion(JTextPane textPane, JTable jTable2, JTextField jTextField20, JTextField jTextField21, JTextField jTextField18) {
        this.textPane = textPane;
        this.jTable2 = jTable2;
        this.jTxtSubBS = jTextField20;
        this.jTxtIvaBsf = jTextField21;
        this.jTxtTbolivar = jTextField18;
    }


 
public void bill_print() throws PrinterException, IOException {
    try {
        // Configura el JTextPane para ajustar su tamaño
       // textPane.setSize(620, 800); // Ajusta el tamaño según tu necesidad
      //  textPane.setPreferredSize(textPane.getSize());

        // Crea un StyledDocument y configura su estilo
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet centerAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlign, StyleConstants.ALIGN_CENTER);
        
        SimpleAttributeSet normal = new SimpleAttributeSet();
        StyleConstants.setAlignment(normal, StyleConstants.ALIGN_LEFT);


        
        
        SimpleAttributeSet tableStyle = new SimpleAttributeSet();
        StyleConstants.setFontFamily(tableStyle, "Consolas");
        StyleConstants.setFontSize(tableStyle, 9);
        StyleConstants.setAlignment(tableStyle, StyleConstants.ALIGN_LEFT);
       // StyleConstants.setLineSpacing(tableStyle, -20.0f);
        
        
        
        

  // Define el ancho máximo de las columnas
int col1Width = 30;  // Ancho para el nombre del producto
int col2Width = 10;  // Ancho para la cantidad
int col3Width = 10;  // Ancho para el precio
int col4Width = 28;
int col5Width = 8;

int col1WidthTable = 38;  // Ancho para el nombre del producto
int col2WidthTable = 10;  // Ancho para la cantidad
int col3WidthTable = 15;  // Ancho para el precio
int col4WidthTable = 15;

// Limpia el documento
doc.remove(0, doc.getLength());

// Agrega contenido al documento
String[] lines = {
    centerText("SENIAT", 100), // Centrado
    centerText(empresa, 85), // Centrado
    centerText(""+rif+"", 93), // Centrado
    centerText("Maracay, Venezuela", 90), // Centrado
    centerText("TLF:0412-8968379", 92), // Centrado
    "",
    "---------------------------------------------------------------",
    centerText("F A C T U R A", 90), // Centrado
    "---------------------------------------------------------------",
    String.format("%-40s %-20s", "FECHA: " + new SimpleDateFormat("dd-MM-yyyy").format(MP.FechaAdmin.getDate()), 
                  "HORA: " + MP.Time.getText()),
    "#Factura: " + (idFacturacion == 0 ? 1 : idFacturacion),
    "C.I/RIF: " + jTxtCedula.getText(),
    "CLIENTE: " + jLabel1.getText(),
    "DIRECCIÓN: " + jTxtDireccion.getText(),
    "Vendedor/a: " + usuarioActivo,
    "",
    "----------------------------------------------------------------",
    String.format("%-" + col1WidthTable + "s %-" + col2WidthTable + "s %-" + col3WidthTable + "s", "Producto", "Cant.", "Precio"),
    "----------------------------------------------------------------"
};
// Inserta el contenido en el documento
for (String line : lines) {
    doc.insertString(doc.getLength(), line + "\n", line.contains("Serv./Prod.") ? normal : centerAlign);
}

// Agrega los ítems de la tabla
DefaultTableModel df = (DefaultTableModel) jTable2.getModel();
for (int i = 0; i < jTable2.getRowCount(); i++) {
    String name = df.getValueAt(i, 1).toString();
    String qt = df.getValueAt(i, 2).toString();
    String prc = df.getValueAt(i, 6).toString();

    // Divide el nombre del producto en varias líneas si excede el ancho de la columna
    List<String> linesForName = splitTextIntoLines(name, col4Width);
    
    // Por cada línea del nombre largo, agregar una nueva línea con el mismo formato
    for (int j = 0; j < linesForName.size(); j++) {
        String productName = linesForName.get(j);
        
        // Si no es la primera línea, el nombre de la cantidad y el precio se repite
        String formattedLine;
        if (j == 0) {
            formattedLine = String.format("%-" + col4Width + "s %-" + col5Width + "s %-" + col3Width + "s", productName, qt, "Bs " + prc);
        } else {
            formattedLine = String.format("%-" + col4Width + "s %-" + col5Width + "s %-" + col3Width + "s", productName, "", "");  // Línea continuación sin cantidad ni precio
        }
        doc.insertString(doc.getLength(), formattedLine + "\n", tableStyle);
    }
}

// Agrega más contenido
String formSumaSubTtl = String.format("%.2f", sumaSubTtl);
String formSumaIvaTtl = String.format("%.2f", sumaIvaTtl);
String formTotal = String.format("%.2f", sumaTotal);


String[] footerLines;  

if(jTxtIvaGe.getText().equals("0")){
    footerLines = new String[]{
        "----------------------------------------------------------------",
        String.format("SubTotal Bs:  %-" + (col1Width + col2Width) + "s %s", "", formSumaSubTtl),
        String.format("TOTAL Bs:      %-" + (col1Width + col2Width) + "s %s", "", formTotal),
        "==============================",
        "¡GRACIAS POR SU VISITA!"
    };
} else {
    footerLines = new String[]{
        "----------------------------------------------------------------",
        String.format("SubTotal Bs:  %-" + (col1Width + col2Width) + "s %s", "", formSumaSubTtl),
        String.format("IVA Bs:           %-" + (col1Width + col2Width) + "s %s", "", formSumaIvaTtl),
        String.format("TOTAL Bs:      %-" + (col1Width + col2Width) + "s %s", "", formTotal),
        "==============================",
        "¡GRACIAS POR SU VISITA!"
    };
}

// Después de asignar los valores correspondientes, recorremos el arreglo
for (String line : footerLines) {
    doc.insertString(doc.getLength(), line + "\n", centerAlign);
}   

        // Renderiza el JTextPane a una imagen  
        Dimension size = textPane.getPreferredSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        textPane.setSize(size);
        textPane.paint(g2d);
        g2d.dispose();

        // Guarda la imagen como archivo PNG
        File file = new File("C://Cyberia//Reportes//factura.png");
        ImageIO.write(image, "png", file);


    


    } catch (BadLocationException ex) {
        ex.printStackTrace(); 
    }
}

private String wrapText(String text, int maxWidth) {
    StringBuilder wrappedText = new StringBuilder();
    while (text.length() > maxWidth) {
        wrappedText.append(text.substring(0, maxWidth)).append("\n");
        text = text.substring(maxWidth);
    }
    wrappedText.append(text); // Agregar el resto del texto
    return wrappedText.toString();
}


// Método para dividir el nombre en varias líneas sin cortar palabras
private List<String> splitTextIntoLines(String text, int maxLength) {
    List<String> lines = new ArrayList<>();
    while (text.length() > maxLength) {
        // Divide el texto y lo agrega a la lista
        lines.add(text.substring(0, maxLength));
        text = text.substring(maxLength);
    }
    // Agrega la última parte que queda
    if (text.length() > 0) {
        lines.add(text);
    }
    return lines;
}


private boolean printing = false;
private void ImprimirBill() {
    if (!printing) {
        printing = true;

        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(textPane.getPrintable(null, null));
            
            // Mostrar el diálogo de impresión
            boolean userAccepted = job.printDialog();
            
            if (userAccepted) {
                job.print(); // Imprimir solo si el usuario aceptó
            }
        } catch (PrinterException l) {
            System.out.println("Error de impresión: " + l.getMessage());
        } finally {
            printing = false; // Permite que la impresión se maneje nuevamente
        }
    }
}














/*
private String centerText(String text, int width) {
    int padding = (width - text.length()) / 2;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < padding; i++) {
        sb.append(" ");
    }
    sb.append(text);
    while (sb.length() < width) {
        sb.append(" ");
    }
    return sb.toString();
}
 
 
    */


private String centerText(String text, int width) {
    if (text.length() >= width) {
        return text; // Si el texto es más largo que el ancho, no se centra.
    }
    int padding = (width - text.length()) / 2;
    // Usar String.format para simplificar la construcción de la cadena centrada
    return String.format("%" + (padding + text.length()) + "s", text);
}




String numFact;
int idNote;
public void knowNote() {
    String sql = "SELECT MAX(id_nota) AS max_nota, MAX(cod_nota) AS max_codigo FROM cod_nota";
   

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            idNote = rs.getInt("max_nota");
            String maxCodigo = rs.getString("max_codigo");

            if (idNote == 0) {
                idNote = 1;
            }

            if (maxCodigo != null && !maxCodigo.isEmpty()) {
                try {
                    String numeroParte = maxCodigo.substring(maxCodigo.lastIndexOf('-') + 1);
                    int numero = Integer.parseInt(numeroParte);
                    numero++; // Incrementar el número
                    idNote++; // Incrementar el id si es necesario

                    numFact = String.format("NOT-%04d", numero);
                } catch (NumberFormatException e) {
                    System.err.println("Error en el formato del código recuperado: " + maxCodigo);
                    numFact = "NOT-0001";
                }
            } else {
                numFact = "NOT-0001";
            }

            jTxtFactura.setText(numFact);
        }
    } catch (Exception e) {
        System.err.println("Error al obtener el código: " + e.getMessage());
    }
}

 int idFact;
 public void knowFact() {
    String sql = "SELECT MAX(Id_fact) AS max_factura, MAX(Fact) AS max_codigo FROM cod_factura";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            idFact = rs.getInt("max_factura");
            String maxCodigo = rs.getString("max_codigo");
            if (idFact == 0) {
                idFact = 1;
            }
    
            if (maxCodigo != null && !maxCodigo.isEmpty()) {
                
                
                try {
                    // Extraer la parte numérica y convertirla a número
                    String numeroParte = maxCodigo.substring(maxCodigo.lastIndexOf('-') + 1);
                    int numero = Integer.parseInt(numeroParte);
                    numero++; // Incrementar el número
                    idFact++; // Incrementar el idFacturacion si es necesario
                    // System.out.println(idFacturacion);
                    numFact = String.format("FACT-%04d", numero); // Formatear el nuevo código
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error en el formato del código recuperado: " + maxCodigo);
                    numFact = "FACT-0001"; // Valor por defecto en caso de error
                }       
                
                
            } else {
           
                numFact = "FACT-0001";
                
            }
            // Asumiendo que jTxtFactura es un componente de la interfaz gráfica
            jTxtFactura.setText(numFact);
        }
    } catch (Exception e) {
        System.err.println("Error al obtener el código: " + e.getMessage());
    }
}
 
 
 
 
 
 
 
 
 
 
int idFacturacion;

public void knowId() {
    String sql = "SELECT MAX(id_factura) AS max_factura FROM table_facturacion";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int max = rs.getInt("max_factura");
            if (rs.wasNull()) {
                idFacturacion = 1; // No hay facturas aún
            } else {
                idFacturacion = max + 1; // Siguiente ID
            }
        } else {
            idFacturacion = 1; // Por si rs.next() devuelve false
        }

    } catch (Exception e) {
        System.err.println("Error al obtener el código: " + e.getMessage());
        idFacturacion = -1; // Error crítico, podría evitar que continúe el proceso
    }
}
 
 
 
 
 
 

public void insertNota(){
  String sql = "INSERT INTO `cod_nota`(`cod_nota`) VALUES (?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setString(1, jTxtFactura.getText());
        ps.executeUpdate();

       // JOptionPane.showMessageDialog(null, "ESPECIALIDAD REGISTRADA", "REGISTRO ESPECIALIDADES", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        // Manejar otros errores
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}




public void insertFact(){
String sql = "INSERT INTO `cod_factura`(`Fact`) VALUES (?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setString(1, jTxtFactura.getText());
        ps.executeUpdate();

     
        
        
 
       // JOptionPane.showMessageDialog(null, "ESPECIALIDAD REGISTRADA", "REGISTRO ESPECIALIDADES", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        // Manejar otros errores
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}







public int idMetodoPago;

 public void guardarFactura(String codeFact, int idPaciente, int idUsuario, int mtdPago, int idPago, String fechaFact, File ruta, int stateFact ) {
        
        JCFactura pa = new JCFactura();
        JCFacturaDao po = new  JCFacturaDao();
        pa.setCod_fact(codeFact);
        pa.setId_paciente(idPaciente);
        pa.setId_usuario(idUsuario);
        pa.setMetodo_pago(idMetodoPago);
        pa.setId_pago(idPago);
        pa.setFecha_fact(fechaFact);
        pa.setEstado_factI(stateFact);
        
        
        
        try {
            byte[] pdf = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(pdf);
            pa.setArch_fact(pdf);
        } catch (IOException ex) {
            pa.setArch_fact(null);
            System.out.println("Error al agregar archivo pdf "+ex);
        }
        po.facturarOrden(pa);
    }



 
 
 


 public void agregarMontos(double pagoBolivar, double pagoDolar) {
    String sql = "INSERT INTO `pago_factura`( `id_Factura`, `ivaDolar`, `ivaBolivar`, `SubtotalD`, `SubtotalB`, `TotalD`, `TotalB`, Pago_Bsf, Pago_Divisa) VALUES (?,?,?,?,?,?,?,?,?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setInt(1, idFacturacion);
        ps.setString(2, jTxtIvaD.getText());
        ps.setString(3, jTxtIvaBsf.getText());
        ps.setString(4, jTxtSubDolar.getText());
        ps.setString(5, jTxtSubBS.getText());   
        ps.setString(6, JTxtTDivisa.getText());
        ps.setString(7, jTxtTbolivar.getText());
        
        ps.setDouble(8, pagoBolivar);
        ps.setDouble(9, pagoDolar);
        
        ps.executeUpdate();

     
        
        
 
       // JOptionPane.showMessageDialog(null, "ESPECIALIDAD REGISTRADA", "REGISTRO ESPECIALIDADES", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        // Manejar otros errores
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
 



 

    public void agregarFormaPago(boolean ptoVenta, boolean pagoMovil,boolean efectivo,boolean transferencia,boolean zelle, boolean otro, String observacion, int metdPago ) {
    String sql = "INSERT INTO `metodo_pgofact`(`id_factura`, `pntoVenta`, `pagomovil`, `efectivo`, `transferencia`, `zelle`, `otro`, `observacion`, `moneda_pago`) VALUES (?,?,?,?,?,?,?,?,?)";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer el valor del parámetro y ejecutar la sentencia
        ps.setInt(1, idFacturacion);
        ps.setBoolean(2, ptoVenta);
        ps.setBoolean(3, pagoMovil);
        ps.setBoolean(4, efectivo);
        ps.setBoolean(5, transferencia);
        ps.setBoolean(6, zelle);
        ps.setBoolean(7, otro);
        ps.setString(8, observacion);
        ps.setInt(9, metdPago);
        ps.executeUpdate();

     
        
        
 
       // JOptionPane.showMessageDialog(null, "ESPECIALIDAD REGISTRADA", "REGISTRO ESPECIALIDADES", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        // Manejar errores SQL
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        // Manejar otros errores
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
 
 
 
 

    
    public void agregarDetalles() {
    String sql = "INSERT INTO `table_factdetail`(`id_factura`, `producto`, `cantidad`, `Precio_Unit`, `Precio_Total`, Precio_UnitBsf, Precio_TotalBsf) VALUES (?,?,?,?,?,?,?)";

    for (int i = 0; i < jTable2.getRowCount(); i++) {
        try (Connection con = new EnlaceBd().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el valor del parámetro
         
            ps.setInt(1, idFacturacion);
            ps.setString(2, jTable2.getValueAt(i, 0).toString());
            ps.setString(3, jTable2.getValueAt(i, 2).toString());
            String valorColumna3 = jTable2.getValueAt(i, 3).toString().replace(",", ".");
            String valorColumna4 = jTable2.getValueAt(i, 4).toString().replace(",", ".");
            String valorColumna5 = jTable2.getValueAt(i, 5).toString().replace(",", ".");
            String valorColumna6 = jTable2.getValueAt(i, 6).toString().replace(",", ".");

            ps.setDouble(4, Double.parseDouble(valorColumna3));
            ps.setDouble(5, Double.parseDouble(valorColumna4));
            
            ps.setDouble(6, Double.parseDouble(valorColumna5));
            ps.setDouble(7, Double.parseDouble(valorColumna6));


            ps.executeUpdate();

        } catch (SQLException e) {
            // Manejar errores SQL
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

    
    
    
    
    
     
    public void activarCampos(){
    jTxtName.setEnabled(true);
    jTxtApellido.setEnabled(true);
    jTxtCorreo.setEnabled(true);
    jTxtTelefono.setEnabled(true);
    JRMasculino1.setEnabled(true);
    JRfemenino1.setEnabled(true);
    jTxtDireccion.setEnabled(true);
    jAgregarCliente.setEnabled(true);
    
    }
    
    
    public void desactivarCampos(){
    jTxtName.setEnabled(false);
    jTxtApellido.setEnabled(false);
    jTxtCorreo.setEnabled(false);
    jTxtTelefono.setEnabled(false);
    JRMasculino1.setEnabled(false);
    JRfemenino1.setEnabled(false);
    jTxtDireccion.setEnabled(false);
    jAgregarCliente.setEnabled(false);
    
    
    }
    
    
    public void cleanRegister(){
    
      jTxtName.setText("");
      jTxtApellido.setText("");
      jTxtCorreo.setText("");
      jTxtTelefono.setText("");
      jTxtDireccion.setText("");
      JRMasculino1.setSelected(true);
      JRfemenino1.setSelected(false);
      Sexo="Masculino";
  
    }
    
    
    
    
    
    
    
   
    public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) jTableServicio.getModel();
        int a = jTableServicio.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }
    
    

     public  void limpiarTablaPrd() {
        DefaultTableModel tb = (DefaultTableModel) jTable2.getModel();
        int a = jTable2.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

 
    
    
    
   
     public void acomodarListado()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)jTableServicio.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
            
       jTableServicio.getColumnModel().getColumn(0).setMaxWidth(0);
       jTableServicio.getColumnModel().getColumn(0).setMinWidth(0);
       jTableServicio.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
       jTableServicio.getColumnModel().getColumn(5).setMaxWidth(0);
       jTableServicio.getColumnModel().getColumn(5).setMinWidth(0);
       jTableServicio.getColumnModel().getColumn(5).setPreferredWidth(0);
       
       jTableServicio.getColumnModel().getColumn(6).setMaxWidth(0);
       jTableServicio.getColumnModel().getColumn(6).setMinWidth(0);
       jTableServicio.getColumnModel().getColumn(6).setPreferredWidth(0);
       
       jTableServicio.getColumnModel().getColumn(7).setMaxWidth(0);
       jTableServicio.getColumnModel().getColumn(7).setMinWidth(0);
       jTableServicio.getColumnModel().getColumn(7).setPreferredWidth(0);
       
       

        jTableServicio.getColumnModel().getColumn(Tabla.findColumn("COD")).setPreferredWidth(20);
        jTableServicio.getColumnModel().getColumn(Tabla.findColumn("SERVICIO/PRODUCTO")).setPreferredWidth(200);
        jTableServicio.getColumnModel().getColumn(Tabla.findColumn("PRECIO")).setPreferredWidth(10);
        jTableServicio.getColumnModel().getColumn(Tabla.findColumn("STOCK")).setPreferredWidth(10);
     //   jTableServicio.getColumnModel().getColumn(Tabla.findColumn("CATEGORIA")).setPreferredWidth(40);
       
 
    
         
       jTableServicio.getColumnModel().getColumn(Tabla.findColumn("COD")).setCellRenderer(Alinear);
       jTableServicio.getColumnModel().getColumn(Tabla.findColumn("SERVICIO/PRODUCTO")).setCellRenderer(Alinear);
       jTableServicio.getColumnModel().getColumn(Tabla.findColumn("PRECIO")).setCellRenderer(Alinear);
       jTableServicio.getColumnModel().getColumn(Tabla.findColumn("STOCK")).setCellRenderer(Alinear);
    //   jTableServicio.getColumnModel().getColumn(Tabla.findColumn("CATEGORIA")).setCellRenderer(Alinear);

       
    
    }

   
   
   
     public void acomodarVentas()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)jTable2.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        

  
        jTable2.getColumnModel().getColumn(Tabla.findColumn("CODIGO")).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("SERVICIO/PRODUCTO")).setPreferredWidth(80);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("CANTIDAD")).setPreferredWidth(10);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO UNIT")).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO TOTAL")).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO BSF UNIT")).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO BSF TOTAL")).setPreferredWidth(40);
       
 
    
         
       jTable2.getColumnModel().getColumn(Tabla.findColumn("CODIGO")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("SERVICIO/PRODUCTO")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("CANTIDAD")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO UNIT")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO TOTAL")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO BSF UNIT")).setCellRenderer(Alinear);
       jTable2.getColumnModel().getColumn(Tabla.findColumn("PRECIO BSF TOTAL")).setCellRenderer(Alinear);

       
    
    }
   
   
   
    
     public void auditoriaAgregar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+MP.Time.getText()+" Agrego al paciente: "+ jTxtName.getText()+" "+jTxtApellido.getText() +" CI: "+ jTxtCedula.getText() ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
        finally {

        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e);
        }
    }
    }
   
   
   
    String  empresa, rif, ubicacion, telefonos, piepagina;
   public void informacionpdf() {



   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
   
        try {

            String sql = "select * from tableinfopdfs";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                empresa = rs.getString("nombrempresa");
                rif = rs.getString("rif");
                ubicacion = rs.getString("ubicacion");
                telefonos = rs.getString("telefonos");
                piepagina = rs.getString("infopiepagina");
               
            }

        } catch (Exception e) {System.out.println(e);
        }
        
           finally {
            closeResources(rs, ps, con);
        }
    }
    
   
public void restarProductos() {
Connection con = null;
EnlaceBd cn = new EnlaceBd();
PreparedStatement psSelect = null;
PreparedStatement psUpdate = null;
ResultSet rs = null;

try {
    con = cn.getConnection();
    
    // Preparar las consultas fuera del bucle
    String sqlSelect = "SELECT existencia FROM table_productos WHERE code_prd = ?";
    psSelect = con.prepareStatement(sqlSelect);

    String sqlUpdate = "UPDATE table_productos SET existencia = ? WHERE code_prd = ?";
    psUpdate = con.prepareStatement(sqlUpdate);

    // Recorrer todas las filas de la tabla
    for (int fila = 0; fila < jTable2.getRowCount(); fila++) {
        String codigoProducto = jTable2.getValueAt(fila, 0).toString();  // Obtener el código del producto de la tabla

        // Validar que el código comienza con "PRD-"
        if (codigoProducto.startsWith("PRD-")) {
            Object cantidadObj = jTable2.getValueAt(fila, 2); // Obtener la cantidad desde la columna correspondiente

            // Verificar que la cantidad es un número válido
            if (cantidadObj != null) {
                try {
                    int cantidadARestar = Integer.parseInt(cantidadObj.toString());

                    // Obtener la existencia actual del producto
                    psSelect.setString(1, codigoProducto);
                    rs = psSelect.executeQuery();

                    if (rs.next()) {
                        int existenciaActual = rs.getInt("existencia");
                        int nuevaExistencia = existenciaActual - cantidadARestar;

                        // Verificar si hay unidades suficientes
                        if (nuevaExistencia < 0) {
                            JOptionPane.showMessageDialog(null, "NO HAY UNIDADES DISPONIBLES DE ESTE PRODUCTO: " + codigoProducto + ", FAVOR DE ABASTECER INVENTARIO", "INVENTARIO", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Actualizar la base de datos con la nueva existencia
                        psUpdate.setInt(1, nuevaExistencia);
                        psUpdate.setString(2, codigoProducto);
                        psUpdate.executeUpdate();

                        // Actualizar la tabla en la interfaz
                        jTable2.setValueAt(nuevaExistencia, fila, 1);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida en la fila " + fila, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cantidad inválida en la fila " + fila, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} catch (Exception e) {
    System.out.println(e);
    JOptionPane.showMessageDialog(null, "ERROR AL RESTAR PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
} finally {
    try {
        if (rs != null) rs.close();
        if (psSelect != null) psSelect.close();
        if (psUpdate != null) psUpdate.close();
        if (con != null) con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
    
    
    
    
    /*
public void llenarCombo() {
    try {
        jComboDto.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<>();
        
        // Asegura que siempre tenga un valor por defecto
        jComboDto.addItem("N/A");
        
        lista3 = lc.llenarEspecialidad();
        
        for (String especialidad : lista3) {
            jComboDto.addItem(especialidad);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
     
public void llenarVendedores() {
    try {
        jComboVendedor.removeAllItems();  // Limpiar el ComboBox
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarDoctoresAsignar();  // Obtener los datos de la base de datos
        
        // Si la lista está vacía, agrega "Sin coincidencias"
        if (lista.isEmpty()) {
            jComboVendedor.addItem("Sin coincidencias");
        } else {
            // Si hay elementos, los agrega al ComboBox
            for (int i = 0; i < lista.size(); i++) {
                jComboVendedor.addItem(lista.get(i));
            }
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}
    

     

public ArrayList<String> llenarDoctoresAsignar() {
    ArrayList<String> lista = new ArrayList<>();
    String especialidad = jComboDto.getSelectedItem().toString();
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

*/


public String tipoPago;
public void notaEntrega(File file) {
    try {
        // Formato de nombre y hora
       
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
        doc.setMargins(36, 36, 130, 130);
        doc.open();

        // Fuente principal
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font fontNormal = new Font(bf, 10);
        Font fontBold = new Font(bf, 10, Font.BOLD);

  
      /*  com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Cyberia\\src\\imagenes\\Logo_System.png");
        header.setAlignment(Image.ALIGN_CENTER);
        header.scaleToFit(100, 50);
        header.setAbsolutePosition(30, doc.getPageSize().getHeight() - 50);
        doc.add(header);*/
      
      
            // Cargar la imagen
com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Cyberia\\src\\imagenes\\Logo_System.png");
// Escalar a un tamaño adecuado sin deformar
header.scaleToFit(120, 80); // Puedes ajustar según el diseño
// Obtener el alto del documento
float pageHeight = doc.getPageSize().getHeight();
// Posicionar en la esquina superior izquierda con margen
float marginLeft = 30f; // distancia desde el borde izquierdo
float marginTop = 30f;  // distancia desde el borde superior

header.setAbsolutePosition(marginLeft, pageHeight - header.getScaledHeight() - marginTop);
doc.add(header);


        // Encabezado
        PdfContentByte cb = writer.getDirectContent();
        float pageWidth = doc.getPageSize().getWidth();
        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.setTextMatrix((pageWidth - bf.getWidthPoint(empresa, 12)) / 2, 800);
        cb.showText(empresa);

        cb.setFontAndSize(bf, 10);
        cb.setTextMatrix((pageWidth - bf.getWidthPoint(rif, 10)) / 2, 790);
        cb.showText(rif);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(MP.FechaAdmin.getDate());
        String hora = MP.Time.getText() + " " + MP.jLabel102.getText();

        cb.setTextMatrix(460, 780);
        cb.showText("FECHA: " + fecha);
        cb.setTextMatrix(460, 770);
        cb.showText("HORA: " + hora);

        cb.setFontAndSize(bf, 8);
        cb.setTextMatrix((pageWidth - bf.getWidthPoint(ubicacion, 8)) / 2, 740);
        cb.showText(ubicacion);
        cb.setTextMatrix((pageWidth - bf.getWidthPoint(telefonos, 8)) / 2, 730);
        cb.showText(telefonos);
        cb.setTextMatrix((pageWidth - 400) / 2, 725);
        cb.showText("_________________________________________________________________________________________");
        cb.endText();

     
     String num = jTxtFactura.getText(); // "FACT-00001"
     String soloNumeros = num.replaceAll("\\D+", ""); // "00001"
     int numero = Integer.parseInt(soloNumeros); // convierte a int
     String numNota = String.format("NOT-%04d", numero); // "NOT-0001"

           PdfPTable Tabla0 = new PdfPTable(3); 
            Tabla0.getDefaultCell().setBorder(PdfPCell.BOTTOM);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla0.setWidthPercentage(100);  
            float[] medidaCeldas0 = {4f, 3f,4f};
            Tabla0.setWidths(medidaCeldas0);
            Tabla0.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      
            
            Paragraph t0columna1 = new Paragraph("CLIENTE: "+ jTxtName.getText() + " " + jTxtApellido.getText());
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(10);        
            t0columna1.setFont(fontNormal);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+jTxtCedula.getText());
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(10);        
            t0columna2.setFont(fontNormal);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);


            Paragraph t0columna4 = new Paragraph(numNota);
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(10);        
            t0columna4.setFont(fontNormal);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
            
            

// Determinar headers y columnas según el tipo de pago
String[] headers;
int[] columnas;

switch (tipoPago) {
    case "bsf":
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "UNIT BSF", "TOTAL BSF"};
        columnas = new int[]{0, 1, 2, 5, 6}; // índices que corresponden a los datos en BsF
        break;
    case "usd":
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "USD UNIT", "USD TOTAL"};
        columnas = new int[]{0, 1, 2, 3, 4}; // datos en USD
        break;
    case "mixto":
    default:
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "USD UNIT", "UDS TOTAL", "UNIT BSF", "TOTAL BSF"};
        columnas = new int[]{0, 1, 2, 3, 4, 5,6}; // ambas monedas
        break;
}

   // Tabla de datos
        PdfPTable tabla = new PdfPTable(columnas.length);
        tabla.setWidthPercentage(100);
        switch (tipoPago) {
    case "bsf":
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f}); // 5 columnas
        break;
    case "usd":
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f}); // 5 columnas
        break;
    case "mixto":
    default:
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f, 3f, 3f}); // 7 columnas
        break;
         }  
        tabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

// Agregar cabeceras al PDF
for (String head : headers) {
    PdfPCell cell = new PdfPCell(new Paragraph(head, fontBold));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(PdfPCell.NO_BORDER);
    tabla.addCell(cell);
}

// Agregar filas de datos según columnas seleccionadas
for (int i = 0; i < jTable2.getRowCount(); i++) {
    for (int col : columnas) {
        Object value = jTable2.getValueAt(i, col);
        tabla.addCell(new Paragraph(value != null ? value.toString() : "", fontNormal));
    }
}



        // Rango de fechas
        PdfPTable rango = new PdfPTable(1);
        rango.setWidthPercentage(100);
        rango.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        rango.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

        String subTotal;
        String Total;
        
        
   switch (tipoPago) {
    case "bsf":
        boolean ptoVentaBs = jPVbs.isSelected();
        boolean pagoMovilBs = jPagomovilBs.isSelected();
        boolean efectivoBs = jEfectivoBs.isSelected();
        boolean transferenciaBs = jTransferenciaBs.isSelected();

        subTotal = "TOTAL: " + jTxtSubBS.getText() + " Bs";
        Total = "PAGADO: " + jTxtPagoBs.getText() + " Bs";

        StringBuilder metodosBs = new StringBuilder("MÉTODO(S) BSF: ");
        if (ptoVentaBs) metodosBs.append("Punto de Venta, ");
        if (pagoMovilBs) metodosBs.append("Pago Móvil, ");
        if (efectivoBs) metodosBs.append("Efectivo, ");
        if (transferenciaBs) metodosBs.append("Transferencia, ");
        if (metodosBs.toString().endsWith(", ")) {
            metodosBs.setLength(metodosBs.length() - 2);
        }

        rango.addCell(new Paragraph(" "));
        rango.addCell(new Paragraph(
            subTotal + "\n" + Total + "\n" + metodosBs,
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
        ));
        break;

    case "usd":
        boolean zelleUsd = jZelleD.isSelected();
        boolean efectivoUsd = jEfectvD.isSelected();
        boolean pntoVenta = jPVdolar.isSelected();

        subTotal = "TOTAL: " + jTxtSubDolar.getText();
        Total = "PAGADO: " + jTxtDivisas.getText();
        

        StringBuilder metodosUsd = new StringBuilder("MÉTODO(S) USD: ");
        if (zelleUsd) metodosUsd.append("Zelle, ");
        if (efectivoUsd) metodosUsd.append("Efectivo, ");
        if (pntoVenta) metodosUsd.append("Punto de Venta, ");
        if (metodosUsd.toString().endsWith(", ")) {
            metodosUsd.setLength(metodosUsd.length() - 2);
        }

        rango.addCell(new Paragraph(" "));
        rango.addCell(new Paragraph(
            subTotal + "\n" + Total + "\n" + metodosUsd,
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
        ));
        break;

    case "mixto":
    default:
        boolean ptoVentaMix = jPtoVentaMix.isSelected();
        boolean pagoMovilMix = jPgoMmix.isSelected();
        boolean efectivoMix = jEfectivoMix.isSelected();
        boolean transferenciaMix = jTransfMix.isSelected();
        boolean zelleMix = jZelleMix.isSelected();

        subTotal = "TOTAL BSF: " + jTxtSubBS.getText()  + " REF $: " + jTxtSubDolar.getText();
        Total = "PAGADO BSF: " + jTxtPBMixto.getText() + "\n" + "PAGADO $: " + jTxtPDMixto.getText();

        StringBuilder metodosMix = new StringBuilder("MÉTODO(S) USADOS: ");

        if (ptoVentaMix) metodosMix.append("Punto de Venta (Bs), ");
        if (pagoMovilMix) metodosMix.append("Pago Móvil (Bs), ");
        if (efectivoMix) metodosMix.append("Efectivo (Bs/USD), ");
        if (transferenciaMix) metodosMix.append("Transferencia (Bs), ");
        if (zelleMix) metodosMix.append("Zelle (USD), ");

        if (metodosMix.toString().endsWith(", ")) {
            metodosMix.setLength(metodosMix.length() - 2);
        }

        rango.addCell(new Paragraph(" "));
        rango.addCell(new Paragraph(
            subTotal + "\n" + Total + "\n" + metodosMix,
            new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)
        ));
        break;
}

        
     


       doc.add(Tabla0);
       doc.add(new Paragraph("\n"));
        // Agregar contenido
        doc.add(tabla);
        doc.add(new Paragraph("\n"));
        doc.add(rango);

        //addFooter(writer);
        doc.close();
     
      

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}




public void factPdf(File file) {
    try {
        // Formato de nombre y hora
       
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
        doc.setMargins(36, 36, 140, 36);
        doc.open();

        // Fuente principal
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font fontNormal = new Font(bf, 10);
        Font fontBold = new Font(bf, 10, Font.BOLD);


     String num = jTxtFactura.getText(); // "FACT-00001"
     String soloNumeros = num.replaceAll("\\D+", ""); // "00001"
     int numero = Integer.parseInt(soloNumeros); // convierte a int
   

            PdfPTable Tabla0 = new PdfPTable(4); 
            Tabla0.getDefaultCell().setBorder(PdfPCell.BOTTOM);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla0.setWidthPercentage(100);  
            float[] medidaCeldas0 = {3f, 4f,4f, 4f};
            Tabla0.setWidths(medidaCeldas0);
            Tabla0.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      
            
            Paragraph t0columna4 = new Paragraph("Factura: "+ numero);
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(10);        
            t0columna4.setFont(fontNormal);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
            
            
            Paragraph t0columna1 = new Paragraph("CLIENTE: "+ jTxtName.getText() + " " + jTxtApellido.getText());
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(10);        
            t0columna1.setFont(fontNormal);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+jTxtCedula.getText());
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(10);        
            t0columna2.setFont(fontNormal);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);
            
         
            Paragraph t0columna5 = new Paragraph("Télefono: "+jTxtTelefono.getText());
            t0columna5.getFont().setStyle(Font.BOLD);
            t0columna5.getFont().setSize(10);        
            t0columna5.setFont(fontNormal);
            t0columna5.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna5);


            PdfPTable Tabla1 = new PdfPTable(1); 
            Tabla1.getDefaultCell().setBorder(PdfPCell.BOTTOM);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla1.setWidthPercentage(100);  
            float[] medidaCeldas1 = {10f};
            Tabla1.setWidths(medidaCeldas1);
            Tabla1.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      
            
            Paragraph t0columna6 = new Paragraph("Dirección: "+ jTxtDireccion.getText());
            t0columna6.getFont().setStyle(Font.BOLD);
            t0columna6.getFont().setSize(10);        
            t0columna6.setFont(fontNormal);
            t0columna6.setAlignment(Element.ALIGN_LEFT);
            Tabla1.addCell(t0columna6);
            

            

// Determinar headers y columnas según el tipo de pago
String[] headers;
int[] columnas;

switch (tipoPago) {
    case "bsf":
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "UNIT BS", "TOTAL BS"};
        columnas = new int[]{0, 1, 2, 5, 6}; // índices que corresponden a los datos en BsF
        break;
    case "usd":
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "USD UNIT", "USD TOTAL"};
        columnas = new int[]{0, 1, 2, 3, 4}; // datos en USD
        break;
    case "mixto":
    default:
        headers = new String[]{"CÓD", "PRODUCTO", "CANT.", "USD UNIT", "UDS TOTAL", "UNIT BS", "TOTAL BS"};
        columnas = new int[]{0, 1, 2, 3, 4, 5,6}; // ambas monedas
        break;
}

   // Tabla de datos
        PdfPTable tabla = new PdfPTable(columnas.length);
        tabla.setWidthPercentage(100);
        switch (tipoPago) {
    case "bsf":
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f}); // 5 columnas
        break;
    case "usd":
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f}); // 5 columnas
        break;
    case "mixto":
    default:
        tabla.setWidths(new float[]{3f, 8f, 2f, 3f, 3f, 3f, 3f}); // 7 columnas
        break;
         }  
        tabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

// Agregar cabeceras al PDF
for (String head : headers) {
    PdfPCell cell = new PdfPCell(new Paragraph(head, fontBold));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(PdfPCell.NO_BORDER);
    tabla.addCell(cell);
}

// Agregar filas de datos según columnas seleccionadas
for (int i = 0; i < jTable2.getRowCount(); i++) {
    for (int col : columnas) {
        Object value = jTable2.getValueAt(i, col);
        tabla.addCell(new Paragraph(value != null ? value.toString() : "", fontNormal));
    }
}



        // Rango de fechas
        PdfPTable rango = new PdfPTable(1);
        rango.setWidthPercentage(100);
        rango.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        rango.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

        String subTotal;
        String iva;
        String Total;
        
        
switch (tipoPago) {
    case "bsf":
        boolean ptoVentaBs = jPVbs.isSelected();
        boolean pagoMovilBs = jPagomovilBs.isSelected();
        boolean efectivoBs = jEfectivoBs.isSelected();
        boolean transferenciaBs = jTransferenciaBs.isSelected();

        subTotal = "SUB TOTAL: " + jTxtSubBS.getText() + " Bs";
        iva = "IVA: " + jTxtIvaBsf.getText() + " Bs";
        Total = "TOTAL: " + jTxtTbolivar.getText() + " Bs";
        break;

    case "usd":
        boolean zelleUsd = jZelleD.isSelected();
        boolean efectivoUsd = jEfectvD.isSelected();
        boolean pntoVenta = jPVdolar.isSelected();

        subTotal = "SUB TOTAL: " + jTxtSubDolar.getText();
        iva = "IVA: " + jTxtIvaD.getText();
        Total = "TOTAL: " + JTxtTDivisa.getText();
        break;

    case "mixto":
    default:
        subTotal = "SUB TOTAL: " + jTxtSubBS.getText() + " Bs";
        iva = "IVA: " + jTxtIvaBsf.getText() + " Bs";
        Total = "TOTAL: " + jTxtTbolivar.getText() + " Bs";
        break;
}

// Agregar celda vacía para espacio
rango.addCell(new Paragraph(" "));

// Crear un párrafo compuesto centrado
Paragraph contenido = new Paragraph();
Paragraph contenido2 = new Paragraph();
contenido.setAlignment(Element.ALIGN_CENTER);
contenido2.setAlignment(Element.ALIGN_RIGHT);

Font fuente = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
contenido2.add(new Paragraph(subTotal+ "\n", fuente));
contenido2.add(new Paragraph(iva + "\n", fuente));
contenido2.add(new Paragraph(Total+ "\n", fuente));
contenido.add("\n");
contenido.add(new Paragraph("TRANSFERENCIA O DEPOSITO A NOMBRE DE BORDADOS COLOR'S C.A \n", fuente));
contenido.add(new Paragraph("BANESCO CTA CORRIENTE 0134-0153-55153101-2433 \n", fuente));
contenido.add(new Paragraph("¡Gracias por su compra!", fuente));

PdfPCell celdaResumen = new PdfPCell(contenido2);
celdaResumen.setColspan(2); // Cambia el número si tu tabla tiene más columnas
celdaResumen.setBorder(Rectangle.NO_BORDER); // Sin borde, para estética
celdaResumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
rango.addCell(celdaResumen);


// Crear celda que ocupa todas las columnas (ajusta el número según tu tabla)
PdfPCell celdaFinal = new PdfPCell(contenido);
celdaFinal.setColspan(2); // Cambia el número si tu tabla tiene más columnas
celdaFinal.setBorder(Rectangle.NO_BORDER); // Sin borde, para estética
celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);
rango.addCell(celdaFinal);

        
     


       doc.add(Tabla0);
       doc.add(Tabla1);
       doc.add(new Paragraph("\n"));
        // Agregar contenido
        doc.add(tabla);
        doc.add(new Paragraph("\n"));
        doc.add(rango);

        //addFooter(writer);
        doc.close();
     
      

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}





  private void addFooter(PdfWriter writer){
    PdfPTable footer = new PdfPTable(1);
    try {

        
        String User=Tempo.getUser();
        
        
        footer.setWidths(new int[]{100});
        footer.setTotalWidth(527);
        footer.setLockedWidth(false);
        footer.getDefaultCell().setFixedHeight(40);
        footer.getDefaultCell().setBorder(Rectangle.TOP);
        footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // add current page count
        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        footer.addCell(new Phrase(String.format("Emitido por:"+User+"                                                  |   "+piepagina+" |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

        // write page
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footer.writeSelectedRows(0, -1, 34, 50, canvas);
        canvas.endMarkedContentSequence();
    } catch(DocumentException de) {
        throw new ExceptionConverter(de);
   
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





    
   Mprincipal MP = new Mprincipal();
 
   JCFacturaDao factDao=  new JCFacturaDao();
   JCFactura fac= new JCFactura();
   DefaultTableModel modelo = new DefaultTableModel();
   Temporal Tempo = new Temporal();
   int idusuario=Tempo.getTexto(); 
   LlenarCombobox lc = new LlenarCombobox();
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JRMasculino1;
    private javax.swing.JRadioButton JRfemenino1;
    private javax.swing.JTabbedPane JTabbedForm;
    private javax.swing.JTextField JTxtTDivisa;
    private javax.swing.JTextField TxtDivisa;
    private javax.swing.JButton jAgNuevo;
    private javax.swing.JButton jAgregarCliente;
    private javax.swing.JButton jBtnAggPrd;
    private javax.swing.JButton jBtnFactD;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jCancelBs;
    private javax.swing.JButton jCancelD;
    private javax.swing.JCheckBox jEfectivoBs;
    private javax.swing.JCheckBox jEfectivoMix;
    private javax.swing.JCheckBox jEfectvD;
    private javax.swing.JButton jFacturarBs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jNota;
    private javax.swing.JCheckBox jOtroBs;
    private javax.swing.JCheckBox jOtroD;
    private javax.swing.JCheckBox jOtroMix;
    private javax.swing.JCheckBox jPVbs;
    private javax.swing.JCheckBox jPVdolar;
    private javax.swing.JCheckBox jPagomovilBs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox jPgoMmix;
    private javax.swing.JCheckBox jPtoVentaMix;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSpinnerCant;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableServicio;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextServ;
    private javax.swing.JCheckBox jTransfD;
    private javax.swing.JCheckBox jTransfMix;
    private javax.swing.JCheckBox jTransferenciaBs;
    private javax.swing.JTextField jTtxtPrecio;
    private javax.swing.JTextField jTtxtPrecio1;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtCode;
    private javax.swing.JTextField jTxtCorreo;
    private javax.swing.JTextArea jTxtDireccion;
    private javax.swing.JTextField jTxtDivisas;
    private javax.swing.JTextField jTxtFactura;
    private javax.swing.JTextField jTxtFind;
    private javax.swing.JTextField jTxtIvaBsf;
    private javax.swing.JTextField jTxtIvaD;
    private javax.swing.JTextField jTxtIvaGe;
    private javax.swing.JTextField jTxtName;
    private javax.swing.JTextArea jTxtObservD;
    private javax.swing.JTextArea jTxtObsevBs;
    private javax.swing.JTextField jTxtPBMixto;
    private javax.swing.JTextField jTxtPDMixto;
    private javax.swing.JTextField jTxtPagoBs;
    private javax.swing.JButton jTxtPagoMixto;
    private javax.swing.JTextField jTxtSubBS;
    private javax.swing.JTextField jTxtSubDolar;
    private javax.swing.JTextField jTxtTbolivar;
    private javax.swing.JFormattedTextField jTxtTelefono;
    private javax.swing.JCheckBox jZelleD;
    private javax.swing.JCheckBox jZelleMix;
    private javax.swing.JRadioButton jradioBsf;
    private javax.swing.JRadioButton jradioDlrs;
    private javax.swing.JRadioButton jradioDls;
    private javax.swing.JRadioButton jradioEntrega;
    private javax.swing.JRadioButton jradioEntregaBsf;
    private javax.swing.JRadioButton jradioMixto;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}
