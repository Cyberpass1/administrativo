/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Clases.EnlaceBd;
import Clases.JCambiarState;
import Clases.PdfDAO1;
import Clases.PdfVO;
import Clases.SearchConsultasDao;
import Clases.Tabla_PdfVO;
import Clases.Temporal;
import Clases.Validar;
import Clases.imgTabla;
import Menu.Mprincipal;
import Procesos.JLabexamenes.JCmv;
import Procesos.JLabexamenes.JCompleto;
import Procesos.JLabexamenes.JCreatinina;
import Procesos.JLabexamenes.JElectrolitos;
import Procesos.JLabexamenes.JEnzimatico;
import Procesos.JLabexamenes.JEpstein;
import Procesos.JLabexamenes.JFemenino;
import Procesos.JLabexamenes.JFerrecinetico;
import Procesos.JLabexamenes.JGrupoSanguineo;
import Procesos.JLabexamenes.JHematologia;
import Procesos.JLabexamenes.JPcr;
import Procesos.JLabexamenes.JPerfil20;
import Procesos.JLabexamenes.JPerfilrutina;
import Procesos.JLabexamenes.JPreoperatorio;
import Procesos.JLabexamenes.JPsa;
import Procesos.JLabexamenes.JPtt;
import Procesos.JLabexamenes.JQuimicaSanguinea;
import Procesos.JLabexamenes.JReferidos;
import Procesos.JLabexamenes.JSangreOculta;
import Procesos.JLabexamenes.JSerologia;
import Procesos.JLabexamenes.JTiroidea;
import Procesos.JLabexamenes.JUroanalisis;
import Procesos.JLabexamenes.JVIH;
import Procesos.JLaboratorio;
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
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.KeyEvent;
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
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.UIManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * 
 * 
 */
public class ConsultaLaboratorio extends javax.swing.JInternalFrame implements Runnable {

    Tabla_PdfVO tpdf = new Tabla_PdfVO();
    String ruta_archivo = "";
    int id = -1;

    private Thread searchThread;
    private final int DELAY = 600;
    
    
    public ConsultaLaboratorio() {
    initComponents();

   
    
    
   ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

   
   informacionpdf();           
   JRpaciente.setSelected(true);
   String P="Paciente";
   JRpaciente.equals(P);
   //jTextField1.setText("V-");
   
   
         String Nivel=Tempo.getNivel();
         String especialidad= Tempo.getEspecialidad();
         String nivelUsuario=Nivel+" "+especialidad;
      
         if(Nivel.equals("Administrador") || especialidad.equals("Bioanalista")){
             Autorizar.setEnabled(true); 
             Cancelar.setEnabled(true);
             AutorizarTodo.setEnabled(true);
             Corregir.setEnabled(true);
             jOrientar.setEnabled(true);
         }
      
        else if(nivelUsuario.equals("Supervisor Consultor")){
            Jtabla.setEnabled(false);
         }
         
         
         
         Autorizar.setVisible(false);
         Completar.setVisible(false);
         
         Calendar Fecha = new GregorianCalendar();
         FechaOne.setCalendar(Fecha);
         FechaTwo.setCalendar(Fecha);
        
         iniciarActualizacionAutomatica();

         
         DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) Jtabla.getTableHeader().getDefaultRenderer();
         headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      
         Jtabla.requestFocusInWindow();
         
         
    }

 
    
      private int contador=0;
      private void limpiarYActualizarTabla() {
        // Aquí deberías llamar tus métodos para limpiar y listar pacientes
        limpiarTablaEstudios();
        visualizar_PdfVO(Jtabla); 
        if(Jtabla.getRowCount()>0){  acomodarceldas(); 
        Jtabla.getTableHeader().setReorderingAllowed(false);
           conteoTabla();
         }
        
     //   conteoTabla();
  
    }

    public void iniciarActualizacionAutomatica() {
        // Iniciar la actualización automática cada 5 minutos
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this, 0, 15, TimeUnit.MINUTES); // 10 minutos
    }

    @Override
    public void run() {
        // Actualizar la tabla en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { 
                
                String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
                String fechaNueva = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
                if (fechaActual.equals(fechaNueva) && jTextField1.getText().equals("") && JCestudios.getSelectedItem().equals("Todos") && jEstado.getSelectedItem().equals("Todos")) {
                limpiarYActualizarTabla();
           //    contador++;
           //     System.out.println("Se realizo un update"+ contador);

                
                }
 
            }
        });
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        State = new javax.swing.JPopupMenu();
        Autorizar = new javax.swing.JMenuItem();
        AutorizarTodo = new javax.swing.JMenuItem();
        jOrientar = new javax.swing.JMenuItem();
        Completar = new javax.swing.JMenuItem();
        CompletarTodo = new javax.swing.JMenuItem();
        Corregir = new javax.swing.JMenuItem();
        Cancelar = new javax.swing.JMenuItem();
        abrirPdfs = new javax.swing.JMenuItem();
        imprimirItems = new javax.swing.JMenuItem();
        Correo = new javax.swing.JMenuItem();
        Whatsapp = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jRcodigo = new javax.swing.JRadioButton();
        JRpaciente = new javax.swing.JRadioButton();
        jRusuario = new javax.swing.JRadioButton();
        FechaTwo = new com.toedter.calendar.JDateChooser();
        FechaOne = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        JCestudios = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtabla = new javax.swing.JTable();

        Autorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/autorizacion.png"))); // NOI18N
        Autorizar.setActionCommand("Autorizar");
        Autorizar.setEnabled(false);
        Autorizar.setLabel("Autorizar");
        Autorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutorizarActionPerformed(evt);
            }
        });
        State.add(Autorizar);

        AutorizarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/autorizacion.png"))); // NOI18N
        AutorizarTodo.setText("Autorizar");
        AutorizarTodo.setActionCommand("");
        AutorizarTodo.setEnabled(false);
        AutorizarTodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AutorizarTodoItemStateChanged(evt);
            }
        });
        AutorizarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutorizarTodoActionPerformed(evt);
            }
        });
        State.add(AutorizarTodo);

        jOrientar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/hablar.png"))); // NOI18N
        jOrientar.setText("Requiere Orientación");
        jOrientar.setActionCommand("Orientar Paciente");
        jOrientar.setEnabled(false);
        jOrientar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOrientarActionPerformed(evt);
            }
        });
        State.add(jOrientar);

        Completar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/comprobacion.png"))); // NOI18N
        Completar.setLabel("Completar");
        Completar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompletarActionPerformed(evt);
            }
        });
        State.add(Completar);

        CompletarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/comprobacion.png"))); // NOI18N
        CompletarTodo.setText("Completar");
        CompletarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompletarTodoActionPerformed(evt);
            }
        });
        State.add(CompletarTodo);

        Corregir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edicion.png"))); // NOI18N
        Corregir.setText("Corregir este estudio");
        Corregir.setEnabled(false);
        Corregir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorregirActionPerformed(evt);
            }
        });
        State.add(Corregir);

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setEnabled(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        State.add(Cancelar);

        abrirPdfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/pdfName.png"))); // NOI18N
        abrirPdfs.setText("Abrir reportes");
        abrirPdfs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirPdfsActionPerformed(evt);
            }
        });
        State.add(abrirPdfs);

        imprimirItems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/impresora.png"))); // NOI18N
        imprimirItems.setText("Imprimir reportes");
        imprimirItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirItemsActionPerformed(evt);
            }
        });
        State.add(imprimirItems);

        Correo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/correo-electronico.png"))); // NOI18N
        Correo.setText("Enviar por correo");
        Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoActionPerformed(evt);
            }
        });
        State.add(Correo);

        Whatsapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/_whatsapp.png"))); // NOI18N
        Whatsapp.setText("Enviar por WhatsApp");
        Whatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WhatsappActionPerformed(evt);
            }
        });
        State.add(Whatsapp);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setMinimumSize(new java.awt.Dimension(1285, 663));
        setPreferredSize(new java.awt.Dimension(1292, 683));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Refrescar");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 40, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total de estudios:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 610, 1310, 70));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Exportar Data:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("C O N S U L T A S          L A B O R A T O R I O");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buscar por:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 230, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/grafica.png"))); // NOI18N
        jButton1.setToolTipText("Ver grafica de estudios realizados");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, 70, 50));

        jRcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jRcodigo.setText("Código");
        jRcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRcodigoActionPerformed(evt);
            }
        });
        jPanel2.add(jRcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        JRpaciente.setBackground(new java.awt.Color(255, 255, 255));
        JRpaciente.setText("N° Cédula");
        JRpaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRpacienteActionPerformed(evt);
            }
        });
        jPanel2.add(JRpaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jRusuario.setBackground(new java.awt.Color(255, 255, 255));
        jRusuario.setText("C.I Historia");
        jRusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRusuarioActionPerformed(evt);
            }
        });
        jPanel2.add(jRusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        FechaTwo.setBackground(new java.awt.Color(255, 255, 255));
        FechaTwo.setToolTipText("");
        FechaTwo.setDateFormatString("yyyy-MM-dd");
        FechaTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 170, 50));

        FechaOne.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne.setToolTipText("");
        FechaOne.setDateFormatString("yyyy-MM-dd");
        FechaOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 170, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jButton2.setToolTipText("Exportar estadística de estudios realizados");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 50, 70, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/Pacientes.png"))); // NOI18N
        jButton3.setToolTipText("Exportar estadística de pacientes asignados");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 50, 70, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/magnifier-1_icon-icons.com_56924.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 70, 50));

        jLabel5.setText("Sortear por rango de fechas");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        JCestudios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Hematologia Completa", "Quimica Sanguinea", "Perfil 20", "Perfil Pre-Operatorio", "Pruebas Especiales", "Estudios Referidos", "Perfil de Rutina", "Uroanalisis", "Coproanalisis", "Uroanalisis & Coproanalisis", "PT, PPT Y VSG", "Serologia", "Test VIH", "Grupo Sanguineo", "CMV", "Perfil Tiroideo", "Perfil Ferrocinetico", "Perfil Hepatico", "Perfil Inmunologico", "Perfil Femenino", "Perfil Enzimatico", "PSA", "Factores de Complemento", "Electrolitos", "Estudio Personalizado", "Insulina Basal y Post Pandrial", "Helycobacter", "Helycobacter en Heces", "Toxoplasma gondii", "Relaciones Urinarias", "Marcadores Tumorales", "Sangre Oculta en Heces", "Depuracion de Creatinina" }));
        JCestudios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCestudiosItemStateChanged(evt);
            }
        });
        jPanel2.add(JCestudios, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 180, 50));

        jLabel7.setText("Filtrar por Estado");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, -1, -1));

        jEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "Por Orientar", "Autorizado", "Completado", "Cancelado" }));
        jEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jEstadoItemStateChanged(evt);
            }
        });
        jPanel2.add(jEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 110, 50));

        jLabel8.setText("Filtrar por Estudio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1290, 110));

        Jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Orden", "Examen", "Paciente", "C.I", "Usuario encargado", "Fecha", "archivopdf", "Estado", "Telefono", "Correo"
            }
        ));
        Jtabla.setComponentPopupMenu(State);
        Jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JtablaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(Jtabla);
        if (Jtabla.getColumnModel().getColumnCount() > 0) {
            Jtabla.getColumnModel().getColumn(0).setResizable(false);
            Jtabla.getColumnModel().getColumn(1).setResizable(false);
            Jtabla.getColumnModel().getColumn(3).setResizable(false);
            Jtabla.getColumnModel().getColumn(4).setResizable(false);
            Jtabla.getColumnModel().getColumn(5).setResizable(false);
            Jtabla.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1290, 450));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    int idtabla;
    String idestados, opcion, nombrepaciente, examen, estado, name, fechaEstudio, telefonoPac;

    JButton boton;
    Object value;
    private void JtablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtablaMouseClicked


    try {
        PdfDAO1 pd = new PdfDAO1();
        String nivel = Tempo.getNivel();
        int row = Jtabla.rowAtPoint(evt.getPoint());
        int column = Jtabla.columnAtPoint(evt.getPoint());
    

        if (row >= 0 && column >= 0) {
            int fila = Jtabla.getSelectedRow();
            idtabla = (int) Jtabla.getValueAt(fila, 0);
            examen = Jtabla.getValueAt(fila, 1).toString();
            name = Jtabla.getValueAt(fila, 2).toString();
            nombrepaciente = Jtabla.getValueAt(fila, 3).toString();
           
            String fechaStudy = Jtabla.getValueAt(fila, 5).toString();
            String[] partesFecha = fechaStudy.split("-");
            String mesFormateado = String.format("%02d", Integer.parseInt(partesFecha[1]));
            fechaEstudio = partesFecha[2] + "/" + mesFormateado + "/" + partesFecha[0];

      
            
            idestados = Jtabla.getValueAt(fila, 7).toString();
            telefonoPac = Jtabla.getValueAt(fila, 8).toString();
            correopaciente = Jtabla.getValueAt(fila, 9).toString();
      
            value = Jtabla.getValueAt(row, column);

            if (value instanceof JButton) {
                 boton = (JButton) value;

                if (boton.getText().equals("Vacio")) {
                    JOptionPane.showMessageDialog(null, "No hay archivo");
                } else if (NivelAuxLaboratorioYEstadoPendiente()) {
                    pd.simularClic(boton);
 
                    JOptionPane.showMessageDialog(null, "El archivo debe autorizarse para que lo puedas visualizar", "PERMISOS", 1);
                    
                } else if (NivelAuxOrientar()) {
                    pd.simularClic(boton);
                    JOptionPane.showMessageDialog(null, "El archivo debe estar autorizado para que lo puedas visualizar", "PERMISOS", 1);
                    
                }else if (NivelAuxLaboratorioYEstadoCancelado()) {
                    pd.simularClic(boton);
               
                    JOptionPane.showMessageDialog(null, "No puede visualizar reportes cancelados", "PERMISOS", 1);
                    
                } else {
                    pd.simularClic(boton);
                    JOptionPane.showMessageDialog(null, "Abriendo reporte...", "Reporte", 1);
                    abrirPDF();
    
                }
                

                
            }
        }
    } catch (Exception e) {
        System.out.println(e);
        Jtabla.requestFocusInWindow();
    }
        
        
        
    }//GEN-LAST:event_JtablaMouseClicked

    
 
    
    
    
    
    
    private boolean NivelAuxLaboratorioYEstadoPendiente() {
     String nivel = Tempo.getNivel();
     String especialidad= Tempo.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
        
        return nivelUsuario.equals("Aux. Laboratorio") && idestados.equals("Pendiente");
}

     private boolean NivelAuxOrientar() {
     String nivel = Tempo.getNivel();
     String especialidad= Tempo.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
        
        return nivelUsuario.equals("Aux. Laboratorio") && idestados.equals("Por Orientar");
}

    
private boolean NivelAuxLaboratorioYEstadoCancelado() {
     String nivel = Tempo.getNivel();
     String especialidad= Tempo.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
     
    return nivelUsuario.equals("Aux. Laboratorio") && idestados.equals("Cancelado");
}

private void abrirPDF() {
   
    PdfDAO1 pd = new PdfDAO1();
    pd.ejecutar_archivoPDF(idtabla, name);
   
 
    try {
        
        //JOptionPane.showMessageDialog(null, "Abriendo reporte...", "Reporte", 1);
        Desktop.getDesktop().open(new File("Análisis.pdf"));
        //Desktop.getDesktop().open(new File("Reporte #" + id + "_" + name + ".pdf"));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
    
    
    
    
    
    private void JRpacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRpacienteActionPerformed
        this.JRpaciente.setSelected(true);
        jRusuario.setSelected(false);
        jRcodigo.setSelected(false);
       // jTextField1.setText("V-");
        
    }//GEN-LAST:event_JRpacienteActionPerformed

    private void jRusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRusuarioActionPerformed
      
        this.jRusuario.setSelected(true);
        JRpaciente.setSelected(false);
        jRcodigo.setSelected(false);
        jTextField1.setText("");
        
    }//GEN-LAST:event_jRusuarioActionPerformed

    private void jRcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRcodigoActionPerformed
        this.jRcodigo.setSelected(true);
        JRpaciente.setSelected(false);
        jRusuario.setSelected(false);
        jTextField1.setText("");
    }//GEN-LAST:event_jRcodigoActionPerformed

    

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    
  JCestudios.setSelectedItem("Todos");

        if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }

        searchThread = new Thread(() -> {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                return; // La búsqueda fue interrumpida
            }

            SwingUtilities.invokeLater(() -> {
                realizarBusqueda();
            });
        });

        searchThread.start();
    
   
      
    }//GEN-LAST:event_jTextField1KeyReleased

    
    
    
    
    
    
    
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       SearchBetweenDates(); 
       if(Jtabla.getRowCount()>0){  acomodarceldas(); }
    
       conteoTabla();
       Jtabla.requestFocusInWindow();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try{      
       if(Jtabla.getRowCount()<=0){  JOptionPane.showMessageDialog(null, "La tabla se encuentra vacia", "TABLA VACIA", JOptionPane.ERROR_MESSAGE); }
       else{    grafico();         Jtabla.requestFocusInWindow(); }
             

        }  catch(Exception e){  JOptionPane.showMessageDialog(null,"Error", "Reporte Grafico", JOptionPane.ERROR_MESSAGE); System.out.println(e);}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        
       if(Jtabla.getRowCount()<=0){  JOptionPane.showMessageDialog(null, "La tabla se encuentra vacia", "TABLA VACIA", JOptionPane.ERROR_MESSAGE);  }
       else{ try {
           graficoPdf();
           pdf(); 
           AuditoriaReporte();         
           Jtabla.requestFocusInWindow();
           } catch (IOException ex) {
               Logger.getLogger(ConsultaLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
               Jtabla.requestFocusInWindow();
           }
}
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

       
        try{
           graficoPacientesAsig();
           pdfPacientesAig();
           auditoriaReporteAsignar(); 
            Jtabla.requestFocusInWindow();
        }catch(Exception e){System.out.println(e);}
       
        
        
        /*
            if(Jtabla.getRowCount()<=0){  JOptionPane.showMessageDialog(null, "La tabla se encuentra vacia", "TABLA VACIA", JOptionPane.ERROR_MESSAGE); }
       else{   reporte();    AuditoriaReporte();          Jtabla.requestFocusInWindow();}
        */
       
        
        
     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void AutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutorizarActionPerformed
          int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      

            
            if (idestados.equals("Autorizado")){ JOptionPane.showMessageDialog(null, "Este informe ya se encuentra : Autorizado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}
            
           else if (setState.Revisar(107,      idtabla ) ) {
                 
               
               
                  if(!jEstado.getSelectedItem().equals("Todos") && !JCestudios.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
                    limpiarTablaEstudios();
                    searchbyEstudioyEstado();
                    acomodarceldas();
                  
                    Jtabla.requestFocusInWindow();
                 }
                 
               
               
                else if(!jEstado.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
                    limpiarTablaEstudios();
                    searchbyEstudioyEstado();
                    acomodarceldas();
                  
                    Jtabla.requestFocusInWindow();
                 }
                 
                 
               
               else if(!JCestudios.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
                    limpiarTablaEstudios();
                    searchbyEstudio();
                    acomodarceldas();
                  
                    Jtabla.requestFocusInWindow();
                 }
                 
                 else if (JRpaciente.isSelected() &&  jTextField1.getText().length() >= 4){
                 
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
                    limpiarTablaEstudios();
                    Searchbyname(); 
                    acomodarceldas();
               
                    Jtabla.requestFocusInWindow();
                 
                 }
                 
                 
                 else{
                 JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
                 limpiarTablaEstudios();
                 visualizar_PdfVO(Jtabla);
                 acomodarceldas();
      
                 Jtabla.requestFocusInWindow();
                 }
            }

        }
        
        
    }//GEN-LAST:event_AutorizarActionPerformed

    private void CompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompletarActionPerformed
          int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (idestados.equals("Completado")){ JOptionPane.showMessageDialog(null, "Este informe ya se encuentra : Completado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (idestados.equals("Pendiente")){ JOptionPane.showMessageDialog(null, "El informe debe autorizarse antes de completarse", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (idestados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "No puede cambiar el estado de un examen cancelado", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (idestados.equals("Por Orientar")){ JOptionPane.showMessageDialog(null, "No tienes permiso para completar este reporte", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (setState.Revisar(103,      idtabla )) {

              
              
                if(!JCestudios.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Completado");
                    limpiarTablaEstudios();
                    searchbyEstudio();
                    acomodarceldas();
                    AuditoriaCompletar();
                    Jtabla.requestFocusInWindow();
                 }
              
                
                    else if (JRpaciente.isSelected() &&  jTextField1.getText().length() >= 4){
                 
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Completado");
                    limpiarTablaEstudios();
                    Searchbyname(); 
                    acomodarceldas();
                 //   AuditoriaAutorizar();
                    Jtabla.requestFocusInWindow();
                 
                 }
                
                
                
                else{
                JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Completado");
                 limpiarTablaEstudios();
                 visualizar_PdfVO(Jtabla);
                 acomodarceldas();
                 AuditoriaCompletar();
                 Jtabla.requestFocusInWindow();}
            }
        }
    }//GEN-LAST:event_CompletarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
     String Nivel=Tempo.getNivel();

        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cancelar el estudio del paciente c.i: "+ nombrepaciente + "?",
            "CANCELAR ESTUDIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (idestados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "Este informe ya se encuentra : Cancelado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}
      
          else if (Nivel.equals("Aux. Laboratorio") && idestados.equals("Cancelado")) {JOptionPane.showMessageDialog(null,"No puedes cancelar un estudio", "PERMISOS", 1);}
          else if (setState.Revisar(104,      idtabla )) {

                if(!JCestudios.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Cancelado");
                
                    AuditoriaCancelar();
                    limpiarTablaEstudios();
                    searchbyEstudio();
                    acomodarceldas();
                    Jtabla.requestFocusInWindow();
                 }
                
                
                    else if (JRpaciente.isSelected() &&  jTextField1.getText().length() >= 4){
                 
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Cancelado");
                  
                    AuditoriaCancelar();
                    limpiarTablaEstudios();
                    Searchbyname(); 
                    acomodarceldas();
                    Jtabla.requestFocusInWindow();
                 
                 }
                
              
                else{
                JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Cancelado");
             
                 AuditoriaCancelar();
                 limpiarTablaEstudios();
                 visualizar_PdfVO(Jtabla);
                 acomodarceldas();
                 Jtabla.requestFocusInWindow();
                }
            }
        }
            
            
            
         }
         
    }//GEN-LAST:event_CancelarActionPerformed

    private void CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoActionPerformed
       int fila = Jtabla.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} else {
    if (idestados.equals("Pendiente")) {
        JOptionPane.showMessageDialog(null, "El informe debe autorizarse antes de ser enviado por correo", "Estado", JOptionPane.ERROR_MESSAGE);
    } else if (idestados.equals("Cancelado")) {
        JOptionPane.showMessageDialog(null, "Este informe fue cancelado", "Estado", JOptionPane.ERROR_MESSAGE);
    } else if (idestados.equals("Por Orientar")) {
        JOptionPane.showMessageDialog(null, "No se pueden enviar informes con el estado: Por Orientar", "Estado", JOptionPane.ERROR_MESSAGE);
    }else if (setState.Revisar(103, idtabla)) {
        // Preparar mensaje de confirmación
        String message = "¿Realmente desea enviar por correo el estudio del paciente: " + nombrepaciente + " ?";
        int confirm = JOptionPane.showConfirmDialog(rootPane, message, "Enviar Correo", JOptionPane.YES_NO_OPTION);
        
        // Si el usuario confirma la acción
        if (confirm == JOptionPane.YES_OPTION) {
            // Enviar correo y realizar auditoría
            enviarCorreo();

            // Limpiar y actualizar tabla
            limpiarTablaEstudios();
            if (!JCestudios.getSelectedItem().equals("Todos")) {
                searchbyEstudio();
            } else if (JRpaciente.isSelected() && jTextField1.getText().length() >= 4) {
                Searchbyname();
            } else {
                visualizar_PdfVO(Jtabla);
            }
            acomodarceldas();
            Jtabla.requestFocusInWindow();
        }
    }
}
    }//GEN-LAST:event_CorreoActionPerformed

    private void enviarCorreo() {
    PdfDAO1 pd = new PdfDAO1();
    pd.correo_archivoPDF(idtabla);
    email2();
    AuditoriaCorreo();
}
    
    
    
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
      
 
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
         Jtabla.requestFocusInWindow();
    }//GEN-LAST:event_jTextField1FocusLost

    private void abrirPdfsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirPdfsActionPerformed
         
         
     //abrirVariosPdf()
      if (NivelAuxLaboratorioYEstadoPendiente()) {
                   // pd.simularClic(boton);
 
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" debe autorizarse para que lo puedas visualizar, NO PUEDE SELECCIONAR ARCHIVOS NO AUTORIZADOS", "PERMISOS", 1);
                    
                } else if (NivelAuxLaboratorioYEstadoCancelado()) {
               //     pd.simularClic(boton);
               
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" fue cancelado, NO PUEDE SELECCIONAR ARCHIVOS CANCELADOS", "PERMISOS", 1);
                    
                } else if (NivelAuxOrientar()) {
                    JOptionPane.showMessageDialog(null, "El archivo debe estar autorizado para que lo puedas visualizar", "PERMISOS", 1);
                    
                }else {
               //     pd.simularClic(boton);
                 
                    abrirVariosPdf();
    
                }  
        
        
    }//GEN-LAST:event_abrirPdfsActionPerformed

    
public void abrirVariosPdf() {
    DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
    int columnaId = 0; // Índice de la columna que contiene el ID en la tabla

    // Obtén las filas seleccionadas
    int[] filasSeleccionadas = Jtabla.getSelectedRows();

    if (filasSeleccionadas.length > 10) {
        JOptionPane.showMessageDialog(null, "La cantidad de filas seleccionadas no puede ser mayor a 10", "Cantidad", JOptionPane.ERROR_MESSAGE);
        return;
    }

    JOptionPane.showMessageDialog(null, "Abriendo reportes...", "Reportes", JOptionPane.INFORMATION_MESSAGE);

    for (int fila : filasSeleccionadas) {
        int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID

        PdfDAO1 pd = new PdfDAO1();
        pd.ejecutar_archivoPDF(id, "");

        String pdfFileName = "Análisis.pdf"; // Nombre del archivo PDF
        File pdfFile = new File(pdfFileName);

        try {
            // Verifica si el archivo PDF existe antes de abrirlo
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
                // Espera medio segundo antes de continuar con la siguiente iteración
                Thread.sleep(500);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo " + pdfFileName + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            // Manejo de interrupciones del Thread
            Thread.currentThread().interrupt();
            JOptionPane.showMessageDialog(null, "La operación fue interrumpida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



   
    

 public void imprimirReportes() {
        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla

        // Obtén las filas seleccionadas
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        if (filasSeleccionadas.length <= 5) {
            for (int fila : filasSeleccionadas) {
                int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID

                PdfDAO1 pd = new PdfDAO1();
                pd.ejecutar_archivoPDF(id, "");

                try {
                    String pdfFileName = "Análisis.pdf"; // Nombre del archivo PDF
                    File pdfFile = new File(pdfFileName);

                    // Verifica si el archivo PDF existe antes de abrirlo
                    if (pdfFile.exists()) {
                        
                        imprimirPDF(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "El archivo " + pdfFileName + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad de filas seleccionadas no puede ser mayor a 5", "Cantidad", JOptionPane.ERROR_MESSAGE);
        }
    }

 
public void imprimirPDF(File archivoPDF) throws IOException, PrinterException {
        // Cargar el documento PDF
        PDDocument document = PDDocument.load(archivoPDF);

        // Obtener una instancia de PrinterJob
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Obtener una instancia de PDPageable para el documento PDF
        PDPageable pageable = new PDPageable(document);

        // Asignar el documento PDF a PrinterJob
        printerJob.setPageable(pageable);

        // Obtener la impresora por defecto
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

        // Configurar la impresora en PrinterJob
        printerJob.setPrintService(defaultPrinter);

        // Lanzar el diálogo de impresión para que el usuario elija las opciones de impresión
        if (printerJob.printDialog()) {
            // Imprimir el documento PDF
            printerJob.print();
        }

        // Cerrar el documento PDF
        document.close();
    }


/*Codigo CMOLLCA
    
    
       int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      

            

            if (idestados.equals("Pendiente")){ JOptionPane.showMessageDialog(null, "El informe debe autorizarse antes de ser enviado por correo", "Estado", JOptionPane.ERROR_MESSAGE);}
            else if (idestados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "Este informe fue cancelado", "Estado", JOptionPane.ERROR_MESSAGE);}
            
            else if (setState.Revisar(103,      idtabla ) ) {

             
                
                
                    if(!JCestudios.getSelectedItem().equals("Todos")){
                    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Cancelado");
                    limpiarTablaEstudios();
                    searchbyEstudio();
                    acomodarceldas();
           
                    PdfDAO1 pd = new PdfDAO1();
                    pd.correo_archivoPDF(idtabla);
                    correopaciente="cmo.luislar.ca@gmail.com";
                    email2();
                    AuditoriaCorreo(); 
                    Jtabla.requestFocusInWindow(); 
                 }
              
                
                
                 else if (JRpaciente.isSelected() &&  jTextField1.getText().length() >= 4){
                 
                  
                 limpiarTablaEstudios();
                 Searchbyname(); 
                 acomodarceldas();
                    
                 PdfDAO1 pd = new PdfDAO1();
                 pd.correo_archivoPDF(idtabla);
                 correopaciente="cmo.luislar.ca@gmail.com";
                 email2();
                 AuditoriaCorreo(); 
                 Jtabla.requestFocusInWindow();
                 
                 }  
                    
                    
                
                 else{
                 limpiarTablaEstudios();
                 visualizar_PdfVO(Jtabla);
                 acomodarceldas();
                 
                 
                 PdfDAO1 pd = new PdfDAO1();
                 pd.correo_archivoPDF(idtabla);
                 correopaciente="cmo.luislar.ca@gmail.com";
                 email2();
                 AuditoriaCorreo(); 
                 Jtabla.requestFocusInWindow();
                    }
            }
          
        }      
    */
    
    
    private void CorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorregirActionPerformed

         String Nivel=Tempo.getNivel();
         String especialidad= Tempo.getEspecialidad();
      //   String nivelUsuario=Nivel+" "+especialidad;
         if (Nivel.equals("Administrador") || especialidad.equals("Bioanalista") ) {
            try{
              enviarDatos();
            }catch(Exception e){
            
            System.out.println(e);
            Jtabla.requestFocusInWindow();
            } 
           
    }
         
        //  else if (idestados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "No puede corregir un examen cancelado", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
        //  else if (idestados.equals("Completado")){ JOptionPane.showMessageDialog(null, "No puede corregir un examen completado", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
         
          else  if (Nivel.equals("Aux.") ) {
             
               JOptionPane.showMessageDialog(null,"Tu nivel de acceso no cuenta con esta opción", "MÓDULOS", JOptionPane.ERROR_MESSAGE);
               Jtabla.requestFocusInWindow();
    }
         else{
         
                  JOptionPane.showMessageDialog(null,"MÓDULO EN CONSTRUCCIÓN", "MÓDULOS", 1);
                  Jtabla.requestFocusInWindow();
         }
                

 
         

          
    }//GEN-LAST:event_CorregirActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          limpiarTablaEstudios();
          visualizar_PdfVO(Jtabla);
          acomodarceldas();
          conteoTabla();
          Jtabla.requestFocusInWindow();
          
    }//GEN-LAST:event_jButton5ActionPerformed

    private void JCestudiosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCestudiosItemStateChanged

 validarConjunto();
    }//GEN-LAST:event_JCestudiosItemStateChanged

    private void AutorizarTodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AutorizarTodoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_AutorizarTodoItemStateChanged

    private void AutorizarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutorizarTodoActionPerformed
  
        int fila = Jtabla.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 

else if (idestados.equals("Autorizado")){ JOptionPane.showMessageDialog(null, "El estudio o uno de los mismos ya se encuentra Autorizado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

else {
    autorizarFilasSeleccionadas();
    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
    limpiarTablaEstudios();
    
    if (!jEstado.getSelectedItem().equals("Todos") && !JCestudios.getSelectedItem().equals("Todos")) {
        searchbyEstudioyEstado();
    } else if (!jEstado.getSelectedItem().equals("Todos")) {
        searchbyEstado();
    } else if (!JCestudios.getSelectedItem().equals("Todos")) {
        searchbyEstudio();
    } else if (JRpaciente.isSelected() && jTextField1.getText().length() >= 4) {
        Searchbyname();
    } else {
        visualizar_PdfVO(Jtabla);
    }

    acomodarceldas();
    Jtabla.requestFocusInWindow();
}
        
       
    }//GEN-LAST:event_AutorizarTodoActionPerformed

    private void CompletarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompletarTodoActionPerformed
        
        int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (idestados.equals("Completado")){ JOptionPane.showMessageDialog(null, "Debe seleccionar solo estudios autorizados", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (idestados.equals("Pendiente")){ JOptionPane.showMessageDialog(null, "Debe seleccionar solo estudios autorizados", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
          else if (idestados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "No puede incluir estudios cancelados", "Estado", JOptionPane.ERROR_MESSAGE); Jtabla.requestFocusInWindow();}
          else  {


                if(!JCestudios.getSelectedItem().equals("Todos")){
                    completarEstudios();
                    JOptionPane.showMessageDialog(null, "Los estados se han actualizado a : Completado");
                    limpiarTablaEstudios();
                    searchbyEstudio();
                    acomodarceldas();
                 //   AuditoriaCompletar();
                    Jtabla.requestFocusInWindow();
                 }
                        
                
                    else if (JRpaciente.isSelected() &&  jTextField1.getText().length() >= 4){
                    completarEstudios();
                    JOptionPane.showMessageDialog(null, "Los estados se han actualizado a : Completado");
                    limpiarTablaEstudios();
                    Searchbyname(); 
                    acomodarceldas();
                 //   AuditoriaAutorizar();
                    Jtabla.requestFocusInWindow();
                 
                 }
                
                
                
                else{
                 completarEstudios();        
                 JOptionPane.showMessageDialog(null, "Los estados se han actualizado a : Completado");
                 limpiarTablaEstudios();
                 visualizar_PdfVO(Jtabla);
                 acomodarceldas();
              //   AuditoriaCompletar();
                 Jtabla.requestFocusInWindow();}
            }

        }
    }//GEN-LAST:event_CompletarTodoActionPerformed

    private void JtablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtablaMouseEntered
        Jtabla.requestFocusInWindow();
    }//GEN-LAST:event_JtablaMouseEntered

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
    
        
            if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
     
         JCestudios.setSelectedItem("Todos");      
     
     
     
     if(JRpaciente.isSelected()){   
      
         Searchbyname();  acomodarceldas(); 
     }
    
    
     
     else if(jRusuario.isSelected()){  
    
        // SearchbyUser();   acomodarceldas();
     }
    
     
     
     
     else if(jRcodigo.isSelected()){   
        
       
         SearchbyCod();   acomodarceldas();
         
     }
         
        
        
        }
  
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jEstadoItemStateChanged
    
        
   validarConjunto();
    }//GEN-LAST:event_jEstadoItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void imprimirItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirItemsActionPerformed
        //abrirVariosPdf()
      if (NivelAuxLaboratorioYEstadoPendiente()) {
                   // pd.simularClic(boton);
 
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" debe autorizarse para que lo puedas visualizar, NO PUEDE SELECCIONAR ARCHIVOS NO AUTORIZADOS", "PERMISOS", 1);
                    
                } else if (NivelAuxLaboratorioYEstadoCancelado()) {
               //     pd.simularClic(boton);
               
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" fue cancelado, NO PUEDE SELECCIONAR ARCHIVOS CANCELADOS", "PERMISOS", 1);
                    
                } else if (NivelAuxOrientar()) {
                    JOptionPane.showMessageDialog(null, "El archivo debe estar autorizado para que lo puedas visualizar", "PERMISOS", 1);
                    
                }else {
               //     pd.simularClic(boton);
                 
                  imprimirReportes();
    
                }  
        
    }//GEN-LAST:event_imprimirItemsActionPerformed

    private void WhatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WhatsappActionPerformed
             //abrirVariosPdf()
      if (NivelAuxLaboratorioYEstadoPendiente()) {
                   // pd.simularClic(boton);
 
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" debe autorizarse para que lo puedas visualizar, NO PUEDE SELECCIONAR ARCHIVOS NO AUTORIZADOS", "PERMISOS", 1);
                    
                } else if (NivelAuxLaboratorioYEstadoCancelado()) {
               //     pd.simularClic(boton);
               
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" fue cancelado, NO PUEDE SELECCIONAR ARCHIVOS CANCELADOS", "PERMISOS", 1);
                    
                }  else if (idestados.equals("Por Orientar")) {
        JOptionPane.showMessageDialog(null, "No se pueden enviar informes con el estado: Por Orientar", "Estado", JOptionPane.ERROR_MESSAGE);
    }else {
               //     pd.simularClic(boton);
                 
                      abrirEnlaceWhatsApp(telefonoPac);
    
                }  

    
         
    }//GEN-LAST:event_WhatsappActionPerformed

    private void jOrientarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOrientarActionPerformed
       
     int fila = Jtabla.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 

else if (idestados.equals("Por orientar")){ JOptionPane.showMessageDialog(null, "El estudio o uno de los mismos ya se encuentra en el estado: Por orientar", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

else {
   conversarFilasSeleccionadas();
    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Por Orientar");
    limpiarTablaEstudios();
    
    if (!jEstado.getSelectedItem().equals("Todos") && !JCestudios.getSelectedItem().equals("Todos")) {
        searchbyEstudioyEstado();
    } else if (!jEstado.getSelectedItem().equals("Todos")) {
        searchbyEstado();
    } else if (!JCestudios.getSelectedItem().equals("Todos")) {
        searchbyEstudio();
    } else if (JRpaciente.isSelected() && jTextField1.getText().length() >= 4) {
        Searchbyname();
    } else {
        visualizar_PdfVO(Jtabla);
    }

    acomodarceldas();
    Jtabla.requestFocusInWindow();
}
    }//GEN-LAST:event_jOrientarActionPerformed

    
    
     private static final Logger LOGGER = Logger.getLogger(ConsultaLaboratorio.class.getName());

 public static void abrirEnlaceWhatsApp(String numeroTelefono) {
        try {
            // Validar el número de teléfono
            if (numeroTelefono == null || numeroTelefono.isEmpty()) {
                throw new IllegalArgumentException("El número de teléfono no puede estar vacío.");
            }

            // Limpiar el número de teléfono de caracteres no válidos
            String telefonoPac = numeroTelefono.replace("-", "").substring(1);

            // Crear la URI base con el enlace de WhatsApp
            String uriBase = "https://wa.me/58" + telefonoPac;

            // Obtener la ruta del archivo
            String rutaArchivo = "C:/Fundaginebra/dist/Análisis.pdf";

            // Reemplazar \ por / en la ruta del archivo
            rutaArchivo = rutaArchivo.replace("\\", "/");

            // Crear la URI con el enlace de WhatsApp y el archivo adjunto + "?text=Adjunto%20el%20análisis&attachment=" + rutaArchivo
            URI uri = new URI(uriBase );

            // Verificar si el escritorio es compatible y si el enlace es válido
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                // Abrir el enlace en el navegador predeterminado
                Desktop.getDesktop().browse(uri);
            } else {
                LOGGER.log(Level.SEVERE, "El escritorio no es compatible con la acción de abrir el navegador.");
            }
        } catch (URISyntaxException | IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Error al abrir el enlace de WhatsApp:", e);
        }
    }

    
    
private void validarConjunto() {
    try {
        int indexEstudios = JCestudios.getSelectedIndex();
        int indexEstado = jEstado.getSelectedIndex();

        if (indexEstudios != -1 && indexEstado != -1) {
            if (indexEstudios != 0 && indexEstado != 0) {
     
                searchbyEstudioyEstado();
            } else if (indexEstudios != 0) {
          
                searchbyEstudio();
            } else if (indexEstado != 0) {

                searchbyEstado();
            } else {
                limpiarTablaEstudios();
                visualizar_PdfVO(Jtabla);
             
            }

            if (Jtabla.getRowCount() > 0) {
                acomodarceldas();
            }
            conteoTabla();
            Jtabla.requestFocusInWindow();
        }
    } catch (Exception e) {
        System.out.println(e);
    }
   // System.out.println("Índice JCestudios: " + JCestudios.getSelectedIndex());
   // System.out.println("Índice jEstado: " + jEstado.getSelectedIndex());
}


    
   
      public void visualizar_PdfVO(JTable tabla) {
        
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        try {
        
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");
        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

       
        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.Listar_PdfVO(fecha, fecha2);

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                
    
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
                
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
        }
        
   
                

        
        
                 } catch (Exception e) { System.out.println(e + "visualizarpdf") ;
        }
        
        
        
        
        
    }
    
    
    
    
    

    
    
    
    
     
      public void SearchBetweenDates(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
     
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list =ListarBetween_PdfVO();
limpiarTablaEstudios();
       if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                     
                
              
                fila[6] = new JButton(icono);
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();
                fila[9] = vo.getCorreo();
                
                /*else {
                    fila[6] = new JButton("Vacio");
                }*/

                
                        
               
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
    
    
    

      
      
      
      
      
      
      
      
 
      
    
    
       
   public void Searchbyname(){
              
   try {
        
 
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.ListarByCedPdfVO(jTextField1.getText(),fecha, fecha2 );
        limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();   
                fila[6] = new JButton(icono);        
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
                
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
      
      
      
      
      
      
    
    
       
      public void searchbyEstudio(){
              
      String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
      String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
      String estudio = JCestudios.getSelectedItem().toString();
      String estado = jEstado.getSelectedItem().toString();
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.listarbyEstudio_PdfVO(fecha, fecha2, estudio);
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);  
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
               /* 
                if (vo.getArchivopdf() != null) {
                    fila[6] = new JButton(icono);
                } else {
                    fila[6] = new JButton("Vacio");
                }*/

                
                      
                
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }

  }
    
      
      
      
        
   public void searchbyEstudioyEstado() {
   String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
      String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
      String estudio = JCestudios.getSelectedItem().toString();
      String estado = jEstado.getSelectedItem().toString();
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
     ArrayList<PdfVO> list = search.listarbyEstudioyEstado_PdfVO(fecha, fecha2, estudio, estado);
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);  
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
        
                
                      
                
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }
}
      
      
      
      
      
      
      
      
       
      public void searchbyEstado(){
              
      String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
      String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
      String estudio = jEstado.getSelectedItem().toString();
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.listarbyEstado_PdfVO(fecha, fecha2, estudio);
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);     
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
                
                dt.addRow(fila);
            
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }

  }
      
    
      
      public void SearchbyUser(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");
        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = ListarbyUser_PdfVO();
limpiarTablaEstudios();
      
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
                
              /*  
                if (vo.getArchivopdf() != null) {
                    fila[6] = new JButton(icono);
                } else {
                    fila[6] = new JButton("Vacio");
                }
*/
              
                
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
    
    
      
      
        
      public void SearchbyCod(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("N° Orden");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("C.I");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");
        dt.addColumn("Estado");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");

              
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }


        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = ListarbyCode_PdfVO();
limpiarTablaEstudios();
          if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = "N°"+ vo.getNumeropac()+" "+ vo.getNombreApellido();
                fila[3] = vo.getCedulapaciente();
                fila[4] = vo.getUsuario();
                fila[5] = vo.getFecha();
                fila[6] = new JButton(icono);
                fila[7] = vo.getState();
                fila[8] = vo.getTelefono();    
                fila[9] = vo.getCorreo();
                /*
                if (vo.getArchivopdf() != null) {
                    fila[6] = new JButton(icono);
                } else {
                    fila[6] = new JButton("Vacio");
                }
*/
                
                 
                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
      
      
      

      
      
      
      
      
    
            void limpiarTablaEstudios() {
      DefaultTableModel dt = new DefaultTableModel();
                Jtabla.setModel(dt);
                dt.setRowCount(0);
    }

    
    
    
   public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) { System.out.println(e);
        }
        return null;
    }
    

    
    


     
      
      
      
      
      
      
      
      
     
      
      
      
     
    public ArrayList<PdfVO> ListarbyUser_PdfVO() {
       
         Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
        
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    
    
        ArrayList<PdfVO> list = new ArrayList<PdfVO>();
   
     String sql = "SELECT Codigopdf,  Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono " +
             "FROM table_laboratorio u " +
             "INNER JOIN table_estudios n ON u.Id_examen=n.IdEstudio " +
             "INNER JOIN table_paciente c ON u.id_paciente=c.Idpaciente " +
             "INNER JOIN table_estado l ON u.Id_Estado=l.IdEstado " +
             "INNER JOIN table_usuario x ON u.Id_personal=x.IdPersonal " +
             "WHERE Cedula LIKE '%" + jTextField1.getText() + "%' " +
             "ORDER BY Codigopdf ASC";
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setNombreApellido(rs.getString(3)+ " "+ rs.getString(4));
                vo.setCedulapaciente(rs.getString(5));
                vo.setUsuario(rs.getString(6));
                vo.setFecha(rs.getString(7));
                //vo.setArchivopdf(rs.getBytes(8));
                vo.setState(rs.getString(8));
                vo.setCorreo(rs.getString(9));
                vo.setNumeropac(rs.getInt(10));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   finally {
            closeResources(rs, ps, con);
        }
        return list;
    }

    
    public ArrayList<PdfVO> ListarbyCode_PdfVO() {
       
         String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate()); 
        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
        
        ArrayList<PdfVO> list = new ArrayList<PdfVO>();
   
     String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo,Idpaciente, Telefono  \n" +
"FROM table_laboratorio u\n" +
"\n" +
"INNER JOIN table_estudios n\n" +
"ON u.Id_examen=n.IdEstudio\n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.id_paciente=c.Idpaciente\n" +
"\n" +
"INNER JOIN table_estado l\n" +
"ON u.Id_Estado=l.IdEstado\n" +
"\n" +
                
"INNER JOIN table_usuario x\n" +
"ON u.Id_personal=x.IdPersonal\n" +
"WHERE Codigopdf LIKE '%" + jTextField1.getText() + "%' " +
"AND FechaReporte BETWEEN '" + fecha + "' AND '" + fecha2 + "' " +
"ORDER BY Codigopdf ASC";
   
        
        
        
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setNombreApellido(rs.getString(3)+ " "+ rs.getString(4));
                vo.setCedulapaciente(rs.getString(5));
                vo.setUsuario(rs.getString(6));
                vo.setFecha(rs.getString(7));
             //   vo.setArchivopdf(rs.getBytes(8));
                vo.setState(rs.getString(8));
                vo.setCorreo(rs.getString(9));
                vo.setNumeropac(rs.getInt(10));
                vo.setTelefono(rs.getString("Telefono"));
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
    
    

    
    
       
    public ArrayList<PdfVO> ListarBetween_PdfVO() {
       
        
        
        
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
        
        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
       
    
ArrayList<PdfVO> list = new ArrayList<PdfVO>();
String sql = "SELECT Codigopdf, Estudio, Nombre, Apellido, Cedula, Usuario, FechaReporte, Estado, Correo, Idpaciente, Telefono \n" +
"FROM table_laboratorio u\n" +
"\n" +
"INNER JOIN table_estudios n\n" +
"ON u.Id_examen=n.IdEstudio\n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.id_paciente=c.Idpaciente\n" +
"\n" +
"INNER JOIN table_estado l\n" +
"ON u.Id_Estado=l.IdEstado\n" +
"\n" +
"INNER JOIN table_usuario x\n" +
"ON u.Id_personal=x.IdPersonal WHERE FechaReporte BETWEEN " + '"' + fecha + '"' + "AND"  + '"' + fecha2 + '"' +"ORDER BY  Codigopdf ASC";
        
   
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                PdfVO vo = new PdfVO();
                vo.setCodigopdf(rs.getInt(1));
                vo.setExamen(rs.getString(2));
                vo.setNombreApellido(rs.getString(3)+ " "+ rs.getString(4));
                vo.setCedulapaciente(rs.getString(5));
                vo.setUsuario(rs.getString(6));
                vo.setFecha(rs.getString(7));
              //  vo.setArchivopdf(rs.getBytes(8));
                vo.setState(rs.getString(8));
                vo.setCorreo(rs.getString(9));
                vo.setNumeropac(rs.getInt(10));
                vo.setTelefono(rs.getString("Telefono"));
                list.add(vo);
            }
            
          
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   finally {
            closeResources(rs, ps, con);
        }
        return list;
    }
    
    
    public void grafico() {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String rutaDestino = "C:\\Fundaginebra\\dist\\grafica.png"; // Ruta de destino de la imagen

        // Crear el dataset de la gráfica circular
        DefaultPieDataset dataset = new DefaultPieDataset();
        double total = 0.0; // Para calcular el total

        // Obtener los datos de la base de datos y agregarlos al dataset
        
        
        /*Obtener los datos de la base de datos y agregarlos al dataset
        String sql = "SELECT Id_Estado, n.Estudio, COUNT(*) AS num\n" +
"FROM table_laboratorio AS a\n" +
"INNER JOIN table_estudios n ON a.Id_examen = n.IdEstudio\n" +
"WHERE a.Id_Estado <> 104\n" +
"AND FechaReporte BETWEEN ? AND ?\n" +
"GROUP BY Id_Estado, n.Estudio;";*/
        
        
        
        String sql = "SELECT Id_Estado, n.Estudio, COUNT(*) AS num\n" +
"FROM table_laboratorio AS a\n" +
"INNER JOIN table_estudios n\n" +
"ON a.Id_examen = n.IdEstudio\n" +
"WHERE a.Id_Estado <> 104\n" +
"AND FechaReporte BETWEEN ? AND ?\n" +
"GROUP BY n.Estudio";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();
        DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        while (rs.next()) {
            String estudio = rs.getString("Estudio");
            int num = rs.getInt("num");
            total += num; // Sumar al total
            dataset.setValue(estudio, num);
        }

        JFreeChart jf = ChartFactory.createPieChart3D("R E P O R T E     E S T U D I O S" + "\n\nEMITIDO EL " + fechaactual + " " + MP.jLabel102.getText(), dataset, true, true, false);

        // Obtener el plot (gráfico circular 3D)
        PiePlot3D plot = (PiePlot3D) jf.getPlot();

        // Configurar generador de etiquetas de sección para mostrar los porcentajes
        PieSectionLabelGenerator labelGenerator = new CustomPieSectionLabelGenerator(total);
        plot.setLabelGenerator(labelGenerator);
        plot.setDirection(Rotation.CLOCKWISE); // Cambiar la dirección de rotación del gráfico

        // Mostrar la gráfica en una ventana
        ChartFrame f = new ChartFrame("GRAFICO LABORATORIO", jf);
        f.setSize(1000, 600);
        RefineryUtilities.centerFrameOnScreen(f); // Centrar el gráfico en la pantalla
        f.setVisible(true);

        // Guardar la gráfica como imagen PNG (opcional)
        // ChartUtilities.saveChartAsPNG(new File(rutaDestino), jf, 800, 600);
    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        closeResources(rs, ps, con);  // Cerrar recursos en el bloque finally
    }
}
      
     




public void graficoPdf() throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String rutaDestino = "C:\\Fundaginebra\\dist\\grafica.png"; // Ruta de destino de la imagen

        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();
        double total = 0.0; // Para calcular el total

        // Obtener los datos de la base de datos y agregarlos al dataset
        String sql = "SELECT Id_Estado, n.Estudio, COUNT(*) AS num\n" +
"FROM table_laboratorio AS a\n" +
"INNER JOIN table_estudios n\n" +
"ON a.Id_examen = n.IdEstudio\n" +
"WHERE a.Id_Estado <> 104\n" +
"AND FechaReporte BETWEEN ? AND ?\n" +
"GROUP BY n.Estudio";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();
        DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        while (rs.next()) {
            String estudio = rs.getString("Estudio");
            int num = rs.getInt("num");
            total += num; // Sumar al total
            dataset.setValue(estudio, num);
        }

        JFreeChart jf = ChartFactory.createPieChart("R E P O R T E     E S T U D I O S", dataset, true, true, false);

        // Obtener el plot (gráfico circular)
        PiePlot plot = (PiePlot) jf.getPlot();

        // Configurar generador de etiquetas de sección para mostrar los porcentajes
        PieSectionLabelGenerator labelGenerator = new CustomPieSectionLabelGenerator(total);
        plot.setLabelGenerator(labelGenerator);
        plot.setDirection(Rotation.CLOCKWISE); // Cambiar la dirección de rotación del gráfico

        /* Mostrar la gráfica en una ventana
        ChartFrame f = new ChartFrame("GRAFICO LABORATORIO", jf);
        f.setSize(1000, 500);
        RefineryUtilities.centerFrameOnScreen(f); // Centrar el gráfico en la pantalla
        f.setVisible(true);
*/
       // Guardar la gráfica como imagen PNG
         ChartUtilities.saveChartAsPNG(new File(rutaDestino), jf, 900, 500);
    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        closeResources(rs, ps, con);  // Cerrar recursos en el bloque finally
    }
}




public class CustomPieSectionLabelGenerator implements PieSectionLabelGenerator {
    private double total;

    public CustomPieSectionLabelGenerator(double total) {
        this.total = total;
    }

    
    public String generateSectionLabel(PieDataset dataset, Comparable key) {
        double value = dataset.getValue(key).doubleValue();
        double percentage = (value / total) * 100.0;

        DecimalFormat format = new DecimalFormat("0.00%");
        return key.toString() + ": " + value + " (" + format.format(percentage / 100.0) + ")";
    }

    
    public String generateToolTip(PieDataset dataset, Comparable key) {
        return generateSectionLabel(dataset, key);
    }

        @Override
        public AttributedString generateAttributedSectionLabel(PieDataset pd, Comparable cmprbl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    





//ESTADISTICA PACIENTES ASIGNADOS




     
     
     
       public void pdfPacientesAig() {
      try {
   
          
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          

         
 
    
        
                   
            String   PdfNames="ReportePacientesAsignados"+"_"+fth.format(fechaactual); 
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            File file = new File("C://Fundaginebra//Reportes//"+PdfNames+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            doc.setMargins(36, 36, 125, 130);
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
           
            
            
                 
            ConsultaLaboratorio.HeaderFooterPageEvent headerEvent = new  ConsultaLaboratorio.HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
            
            
            
            
            doc.open();
            
        
        
       //BODY 
            Font Letrasmall = new Font(BF, 8);
       
        
          
            PdfPTable tablaResultado = new PdfPTable(2); 
            tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaResultado.setWidthPercentage(100);  
            float[] medidaCeldas3 = {6f, 3f};
            tablaResultado.setWidths(medidaCeldas3);
            tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
         
            
            Map<String, Integer> cantidadesPorIdExamen = knowPatientsQuantity();
            int sumatoriaTotal = 0;
            // Itera sobre el Map y muestra las cantidades en la consola
            for (Map.Entry<String, Integer> entry : cantidadesPorIdExamen.entrySet()) {
            String nombreEstudio = entry.getKey();
            int cantidadRepeticiones = entry.getValue();
            sumatoriaTotal += cantidadRepeticiones;

     
            Paragraph t40columna1 = new Paragraph(nombreEstudio);
            t40columna1.getFont().setStyle(Font.NORMAL);
            t40columna1.getFont().setSize(9);        
            t40columna1.setFont(Letrasmall);
            t40columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico = new PdfPCell(t40columna1);
            aspectoFisico.setBorder(Rectangle.NO_BORDER);
            aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            aspectoFisico.setHorizontalAlignment(Element.ALIGN_CENTER);
            aspectoFisico.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            Paragraph t42columna1 = new Paragraph(String.valueOf(cantidadRepeticiones));
            t42columna1.getFont().setStyle(Font.NORMAL);
            t42columna1.getFont().setSize(9);        
            t42columna1.setFont(Letrasmall);
            t42columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico2 = new PdfPCell(t42columna1);
            aspectoFisico2.setBorder(Rectangle.NO_BORDER);
            aspectoFisico2.enableBorderSide(Rectangle.BOTTOM);
            aspectoFisico2.setHorizontalAlignment(Element.ALIGN_CENTER);
            aspectoFisico2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tablaResultado.addCell(aspectoFisico);
            tablaResultado.addCell(aspectoFisico2);
            }
            
            
            PdfPTable tablaTotal = new PdfPTable(1); 
            tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaTotal.setWidthPercentage(100); 
            float[] medidaResultado = {5f};
            tablaTotal.setWidths(medidaResultado);
            tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
            Paragraph t41columna1 = new Paragraph("TOTAL DE PACIENTES: "+ sumatoriaTotal + ", ENCONTRADOS ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(12);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell celdaTotal = new PdfPCell(t41columna1);
            celdaTotal.setBorder(Rectangle.NO_BORDER);
            //aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            
            tablaTotal.addCell(celdaTotal);
            
            
            
            
       
        
            
   
 

            doc.add(tablaResultado);
         
            doc.add(saltolinea);
            com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\graficaPacientes.png");
            
            
            float pageWidth = doc.getPageSize().getWidth();
            float imageWidth = imgGrafica.getScaledWidth();
            float xPos = (pageWidth - imageWidth) / 2;

            // Establece la posición X de la imagen en el documento
            imgGrafica.setAlignment(Element.ALIGN_CENTER);
            // Establece el tamaño y la posición de la imagen en el documento
            imgGrafica.scaleToFit(400, 400); // Ajusta el tamaño de la imagen
        
            
            // Agrega la imagen al documento
            doc.add(imgGrafica);
            doc.add(saltolinea);
            doc.add(tablaTotal);
   
         
   

         
            //FOOTER
       // addFooter(writer);
            
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     
     
     






public void graficoPacientesAsig() throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String rutaDestino = "C:\\Fundaginebra\\dist\\graficaPacientes.png"; // Ruta de destino de la imagen

        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();
        double total = 0.0; // Para calcular el total

        // Obtener los datos de la base de datos y agregarlos al dataset
       String sql = "SELECT fecha, paciente, COUNT(*) AS cantidadXdia\n" +
             "FROM orden_lab AS a\n" +
             "WHERE fecha BETWEEN ? AND ?\n" +
             "AND paciente <> 0\n" + 
             "GROUP BY fecha";



        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();
        DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        while (rs.next()) {
            Date fechaPacientes = rs.getDate("fecha");
            
            SimpleDateFormat formatoPersonalizado = new SimpleDateFormat("EEE, dd, MMMM");
            String fechaTraducida = formatoPersonalizado.format(fechaPacientes);
            int num = rs.getInt("cantidadXdia");
            total += num; // Sumar al total
            
            
            
            
            dataset.setValue(fechaTraducida, num);
        }

        JFreeChart jf = ChartFactory.createPieChart("R E P O R T E     P A C I E N T E S", dataset, true, true, false);

        // Obtener el plot (gráfico circular)
        PiePlot plot = (PiePlot) jf.getPlot();

        // Configurar generador de etiquetas de sección para mostrar los porcentajes
        PieSectionLabelGenerator labelGenerator = new CustomPieSectionLabelGenerator(total);
        plot.setLabelGenerator(labelGenerator);
        plot.setDirection(Rotation.CLOCKWISE); // Cambiar la dirección de rotación del gráfico

      /*   Mostrar la gráfica en una ventana
        ChartFrame f = new ChartFrame("GRAFICO LABORATORIO", jf);
        f.setSize(1000, 500);
        RefineryUtilities.centerFrameOnScreen(f); // Centrar el gráfico en la pantalla
        f.setVisible(true);
*/
       // Guardar la gráfica como imagen PNG
         ChartUtilities.saveChartAsPNG(new File(rutaDestino), jf, 900, 500);
    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        closeResources(rs, ps, con);  // Cerrar recursos en el bloque finally
    }
}




public Map<String, Integer> knowPatientsQuantity() {
    Map<String, Integer> cantidadesPorIdExamen = new LinkedHashMap<>();
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

        String sql = "SELECT fecha, COUNT(*) AS cantidad_por_dia\n" +
"FROM (\n" +
"    SELECT fecha, id_orden\n" +
"    FROM orden_lab\n" +
"    WHERE fecha BETWEEN ? AND ?\n" +
"    AND paciente <> 0\n" +
"    GROUP BY fecha, id_orden\n" +
") AS subconsulta\n" +
"GROUP BY fecha";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();

        while (rs.next()) {
            Date fechaEstudio = rs.getDate("fecha");
            int cantidadRepeticiones = rs.getInt("cantidad_por_dia");
            
            SimpleDateFormat formatoPersonalizado = new SimpleDateFormat("EEE, dd, MMMM");
            String fechaTraducida = formatoPersonalizado.format(fechaEstudio);
            

            // Almacenar en el mapa sin formatear la fecha
            cantidadesPorIdExamen.put(fechaTraducida, cantidadRepeticiones);
           // fechaEstudio.toString() puedes convertir un date colocando .toString que loco no?
        }
    } catch (Exception e) {
        e.printStackTrace(); // Imprime el seguimiento completo de la excepción
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Devolver el mapa después de procesar la consulta
    return cantidadesPorIdExamen;
}
     



















    
   String  empresa, rif, ubicacion, telefonos, piepagina;
public void informacionpdf() {
    String sql = "SELECT * FROM tableinfopdfs";
    
    // Uso de try-with-resources para cerrar automáticamente los recursos.
    try (Connection con = EnlaceBd.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        // Comprobamos si existe algún resultado y lo asignamos a las variables.
        if (rs.next()) {
            empresa = rs.getString("nombrempresa");
            rif = rs.getString("rif");
            ubicacion = rs.getString("ubicacion");
            telefonos = rs.getString("telefonos");
            piepagina = rs.getString("infopiepagina");
        }
    } catch (Exception e) {
        // Log de la excepción detallado para facilitar el diagnóstico.
        System.err.println("Error al obtener la información del PDF: " + e.getMessage());
        e.printStackTrace(); // Imprimir el stack trace completo para diagnóstico.
    }
}

    
    
    
  
     
     
     
     
      
     
     
     
       public void pdf() {
      try {
   
          
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          

         
 
    
        
                   
            String   PdfNames="ReporteLaboratorio"+"_"+fth.format(fechaactual); 
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            File file = new File("C://Fundaginebra//Reportes//"+PdfNames+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            doc.setMargins(36, 36, 125, 130);
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
           
            
            
                 
            ConsultaLaboratorio.HeaderFooterPageEvent headerEvent = new  ConsultaLaboratorio.HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
            
            
            
            
            doc.open();
            
        
        
       //BODY 
            Font Letrasmall = new Font(BF, 8);
       
        
          
            PdfPTable tablaResultado = new PdfPTable(2); 
            tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaResultado.setWidthPercentage(100);  
            float[] medidaCeldas3 = {6f, 3f};
            tablaResultado.setWidths(medidaCeldas3);
            tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
         
            
        Map<String, Integer> cantidadesPorIdExamen = knowNumbers();
        int sumatoriaTotal = 0;
        // Itera sobre el Map y muestra las cantidades en la consola
        for (Map.Entry<String, Integer> entry : cantidadesPorIdExamen.entrySet()) {
            String nombreEstudio = entry.getKey();
            int cantidadRepeticiones = entry.getValue();
            sumatoriaTotal += cantidadRepeticiones;

     
            Paragraph t40columna1 = new Paragraph(nombreEstudio);
            t40columna1.getFont().setStyle(Font.NORMAL);
            t40columna1.getFont().setSize(9);        
            t40columna1.setFont(Letrasmall);
            t40columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico = new PdfPCell(t40columna1);
            aspectoFisico.setBorder(Rectangle.NO_BORDER);
            aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            aspectoFisico.setHorizontalAlignment(Element.ALIGN_CENTER);
            aspectoFisico.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            Paragraph t42columna1 = new Paragraph(String.valueOf(cantidadRepeticiones));
            t42columna1.getFont().setStyle(Font.NORMAL);
            t42columna1.getFont().setSize(9);        
            t42columna1.setFont(Letrasmall);
            t42columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico2 = new PdfPCell(t42columna1);
            aspectoFisico2.setBorder(Rectangle.NO_BORDER);
            aspectoFisico2.enableBorderSide(Rectangle.BOTTOM);
            aspectoFisico2.setHorizontalAlignment(Element.ALIGN_CENTER);
            aspectoFisico2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tablaResultado.addCell(aspectoFisico);
            tablaResultado.addCell(aspectoFisico2);
            }
            
            
            PdfPTable tablaTotal = new PdfPTable(1); 
            tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaTotal.setWidthPercentage(100); 
            float[] medidaResultado = {5f};
            tablaTotal.setWidths(medidaResultado);
            tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
            Paragraph t41columna1 = new Paragraph("TOTAL DE ESTUDIOS: "+ sumatoriaTotal + ", REALIZADOS ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(12);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell celdaTotal = new PdfPCell(t41columna1);
            celdaTotal.setBorder(Rectangle.NO_BORDER);
            //aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            
            tablaTotal.addCell(celdaTotal);
            
            
            
            
       
        
            
   
 

            doc.add(tablaResultado);
         
            doc.add(saltolinea);
            com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\grafica.png");
            
            
            float pageWidth = doc.getPageSize().getWidth();
            float imageWidth = imgGrafica.getScaledWidth();
            float xPos = (pageWidth - imageWidth) / 2;

            // Establece la posición X de la imagen en el documento
            imgGrafica.setAlignment(Element.ALIGN_CENTER);
            // Establece el tamaño y la posición de la imagen en el documento
            imgGrafica.scaleToFit(400, 400); // Ajusta el tamaño de la imagen
        
            
            // Agrega la imagen al documento
            doc.add(imgGrafica);
            doc.add(saltolinea);
            doc.add(tablaTotal);
   
         
   

         
            //FOOTER
       // addFooter(writer);
            
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
           System.out.println(e);
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
        footer.addCell(new Phrase(String.format("Emitido por:"+User+"                                                  |    www.fundaginebra.org  |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

        // write page
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footer.writeSelectedRows(0, -1, 34, 50, canvas);
        canvas.endMarkedContentSequence();
    } catch(DocumentException de) {
        throw new ExceptionConverter(de);
   
    }
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
            
        
     
    public void acomodarceldas()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)Jtabla.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        

      
        
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("N° Orden")).setPreferredWidth(10);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Examen")).setPreferredWidth(100);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Paciente")).setPreferredWidth(120);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("C.I")).setPreferredWidth(30);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario encargado")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setPreferredWidth(30);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(20);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setPreferredWidth(40);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Correo")).setPreferredWidth(90);
         
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("N° Orden")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Examen")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Paciente")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("C.I")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario encargado")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Correo")).setCellRenderer(Alinear);
       
    
    }
            
            
            
       public void reporte() {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("REPORTE_LABORATORIO");

       
        try {
            
       
            InputStream is = new FileInputStream("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3);
           
          
            CellStyle tituloEstilo = book.createCellStyle();
          //  tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
          //  tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            org.apache.poi.ss.usermodel.Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 8);
            tituloEstilo.setFont(fuenteTitulo);
           
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(MP.FechaAdmin.getDate());
            
           
            Row filaTitulo3 = sheet.createRow(4);
            Cell celdaTitulo3 = filaTitulo3.createCell(0);
            celdaTitulo3.setCellStyle(tituloEstilo);
            celdaTitulo3.setCellValue("-REPORTE LABORATORIO-");
            
            
            Row filaTitulo = sheet.createRow(5);
            Cell celdaTitulo = filaTitulo.createCell(0);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Fecha: "+Fecha);
           
     
          sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            String[] cabecera = new String[]{"CÓDIGO", "EXAMEN","PACIENTE", "USUARIO ENCARGADO", "FECHA"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);


            org.apache.poi.ss.usermodel.Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 10);
            headerStyle.setFont(font);

            Row filaEncabezados = sheet.createRow(7);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

                   
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    Connection conn = cn.getConnection();

            int numFilaDatos = 8;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());


            ps = conn.prepareStatement("SELECT Codigopdf, Estudio, Cedula, Usuario, FechaReporte\n" +
            "FROM table_laboratorio u\n" +
            "INNER JOIN table_estudios n ON u.Id_examen = n.IdEstudio\n" +
            "INNER JOIN table_paciente c ON u.id_paciente = c.Idpaciente\n" +
            "INNER JOIN table_usuario x ON u.Id_personal = x.IdPersonal\n" +
            "WHERE FechaReporte BETWEEN ? AND ?\n" +
            "ORDER BY Codigopdf ASC");
            
            ps.setString(1, fecha);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }


                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

            
            
            
            
            sheet.setZoom(150);
            String fileName = "LABORATORIO";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "REPORTE GENERADO","REPORTE",1);

        } catch (FileNotFoundException ex) {
               System.out.println(ex); System.out.println(ex);
        } catch (IOException | SQLException ex) {
               System.out.println(ex);
        }

    }
            
            
             
          public void AuditoriaReporte(){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Genero un reporte de la tabla laboratorio " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
       finally {
            closeResources(rs, ps, con);
        }
     
    }
    
                
          public void auditoriaReporteAsignar(){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Genero un reporte de pacientes asignados " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
      finally {
            closeResources(rs, ps, con);
        }
     
    }
          
                   
          public void AuditoriaAutorizar(String nombrepaciente, int idtabla){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+MP.Time.getText()+ " Autorizo el reporte laboratorio "+idtabla+" del paciente: "+ nombrepaciente;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
       finally {
            closeResources(rs, ps, con);
        }
     
    }
          
          
          
          
                   
          public void AuditoriaCancelar(){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+MP.Time.getText()+ " Cancelo el reporte laboratorio "+idtabla+" del paciente: "+ nombrepaciente;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
       finally {
            closeResources(rs, ps, con);
        }
     
    }
          
          
          
          
          
           public void AuditoriaCorreo(){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
             String accion= "HORA: "+MP.Time.getText()+ " Envio el estudio al correo reporte "+idtabla+" del paciente: "+ nombrepaciente;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
       finally {
            closeResources(rs, ps, con);
        }
     
    }
          
          
          
            public void AuditoriaCompletar(){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+MP.Time.getText()+  " Completo el reporte del paciente: "+ nombrepaciente;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
      finally {
            closeResources(rs, ps, con);
        }
     
    }
        
   String fechaFormateada;
       public void enviarDatos(){
               
              
           
 
               
       if(examen.equals("CMV")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JCmv Ir = new    JCmv();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        MP.setLocationRelativeTo(null);              

    
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);     
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.estadoEstudio= idestados;
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.idCallestudios=idtabla;
        Ir.mostrarCmv();
        Ir.procesarPaciente();

       
           }        
               
               
               
       else if(examen.equals("Hematologia Completa")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JHematologia Ir = new    JHematologia();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);

        
        
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarHematologia();
       
           }       
               
               
           
       
       
       
       
       else if(examen.equals("Electrolitos")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JElectrolitos Ir = new    JElectrolitos();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        MP.setLocationRelativeTo(null);              
      
               

        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.procesarPaciente();
        Ir.mostrarElectrolitos();
           }
       
       
       
       
       else if(examen.equals("Perfil Enzimatico")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JEnzimatico Ir = new  JEnzimatico();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               

        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.procesarPaciente();
        Ir.mostrarEnzimas();
           }
       
       
       
       
       else if(examen.equals("EPstein Barr")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JEpstein Ir = new  JEpstein();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
    
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.procesarPaciente();
        Ir.mostrarEpstein();
           }
       
       
       
       
       else if(examen.equals("Perfil Femenino")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JFemenino Ir = new  JFemenino();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               

        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.procesarPaciente();
        Ir.mostrarPFemenino();
           }
       
       
       
       
       else if(examen.equals("Perfil Ferrocinetico")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JFerrecinetico Ir = new    JFerrecinetico();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
       
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarFerrecinetico();
       
           }       


       
       
       
       else if(examen.equals("Quimica Sanguinea")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JQuimicaSanguinea Ir = new    JQuimicaSanguinea();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarQuimica();
       
           }       

       
       
       
       
       else if(examen.equals("Test VIH")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JVIH Ir = new    JVIH();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               

        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.setfechaEstudio(fechaEstudio);
        Ir.procesarPaciente();
        Ir.mostrarVIH();
           }
       
       
       
       
       
       else if(examen.equals("Perfil Tiroideo")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JTiroidea Ir = new    JTiroidea();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarTiroidea();
       
           }       
       
       
       
       
       else if(examen.equals("PSA")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPsa Ir = new    JPsa();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      

        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarPsa();
       
           }       
       
       
       
       
       
       else if(examen.equals("Uroanalisis")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JUroanalisis Ir = new    JUroanalisis();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarUroanalisis();
        Ir.JRuroanalisis.setSelected(true);
        Ir.JRcoprocultivo.setSelected(false);
       
           }       

     
       
       
       
       else if(examen.equals("Coproanalisis")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JUroanalisis Ir = new    JUroanalisis();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarCopro();
        Ir.JRuroanalisis.setSelected(false);
        Ir.JRcoprocultivo.setSelected(true);
           }       

       
       
       
       else if(examen.equals("Uroanalisis & Coproanalisis")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JUroanalisis Ir = new    JUroanalisis();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarUroanalisis();
        Ir.mostrarCopro();
    
        Ir.JRuroanalisis.setSelected(true);
        Ir.JRcoprocultivo.setSelected(true);
           }       

        
        
       else if(examen.equals("Serologia")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JSerologia Ir = new    JSerologia();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarSerologia();
        Ir.mostrarVIH();
           }     
        
       
       
       
       else if(examen.equals("Perfil 20")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPerfil20 Ir = new    JPerfil20();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarHematologia();
        Ir.mostrarQuimica();
        Ir.mostrarUroanalisis();
        Ir.mostrarCopro();
        Ir.mostrarPTT();
        Ir.mostrarVIH();
        Ir.JradioUroanalisis.setEnabled(true);
        Ir.JradioPtt.setEnabled(true);
        Ir.comprobarCheck();
           }       
       
       
       
       
       
       else if(examen.equals("PT, PTT Y VSG")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPtt Ir = new    JPtt();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
        Ir.setfechaEstudio(fechaEstudio);
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarPTT();
           }
       
       
       
       
       
        else if(examen.equals("Perfil Pre-Operatorio")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPreoperatorio Ir = new    JPreoperatorio ();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
        Ir.setfechaEstudio(fechaEstudio);
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarHematologia();
        Ir.mostrarQuimica();
        Ir.mostrarPT();
        Ir.mostrarVIH();
        Ir.mostrarGrupo();
           }
       
        
        
           
       else if(examen.equals("Pruebas Especiales")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JCompleto Ir = new    JCompleto();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
        Ir.setfechaEstudio(fechaEstudio);
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarTiroideo();
        Ir.mostrarInsulina();
        Ir.mostrarPFemenino();
        Ir.mostrarPsa();
        Ir.mostrarFerritina();
        Ir.mostrarHepatitis();
        Ir.mostrarToxo();
        Ir.mostrarHelico();
        Ir.mostrarEspecial();
           }
       
        
        
        
        
       else if(examen.equals("PCR")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPcr Ir = new    JPcr();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarPCR();
 
           }
        
        
        
        
       else if(examen.equals("Perfil de Rutina")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JPerfilrutina Ir = new    JPerfilrutina();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
           
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarHematologia();
        Ir.mostrarQuimica();
        Ir.mostrarUroanalisis();
        Ir.mostrarCopro();
        Ir.mostrarPTT();
        Ir.mostrarVIH();
        Ir.mostrarEnzimas();
        Ir.mostrarPCR();
        Ir.mostrarSerologia();
        Ir.mostrarGrupo();
        Ir.JradioUroanalisis.setEnabled(true);
        Ir.JradioPtt.setEnabled(true);
        Ir.JradioSero.setEnabled(true);
        Ir.comprobarCheck();
           }       
       
        
        
        
        
           
        
       else if(examen.equals("Sangre Oculta en Heces")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JSangreOculta Ir = new   JSangreOculta();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);

        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarSangre();
 
           }
        
        
       
       
      else if(examen.equals("Grupo Sanguineo")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JGrupoSanguineo Ir = new   JGrupoSanguineo();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarGrupo();
 
           }
        
       
       
       
       
          
       
       else if(examen.equals("Depuracion de Creatinina")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JCreatinina Ir = new   JCreatinina();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
         MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
        Ir.mostrarCreatinina();
       
           }       
       
       
       
      else if(examen.equals("Estudios Referidos")){         
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JLaboratorio jlab = new JLaboratorio();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jlab);
       jlab.setClosable(true);
       jlab.setIconifiable(true);
       
        try {
            jlab.setMaximum(true);
        } catch (Exception e) {
        }
        jlab.toFront();
        jlab.setVisible(true);
       
        jlab.JTLaboratorio.setSelectedIndex(1);
        JReferidos Ir = new   JReferidos();
        jlab.JDExamenesLab.setVisible(true);
        jlab.JDExamenesLab.add(Ir);
        
        Ir.setClosable(true);
        Ir.setIconifiable(true);
         try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        MP.setLocationRelativeTo(null);              
      
               
       
      
        // Configurar el valor en ConsultaLaboratorio
        Ir.setValorA(idtabla);
        Ir.setValorB(examen);
        Ir.setValorC(nombrepaciente);
        Ir.setfechaEstudio(fechaEstudio);
        
        Ir.TXTPcedula.setText(nombrepaciente);
        Ir.estadoEstudio= idestados;
        Ir.idCallestudios=idtabla;
        Ir.procesarPaciente();
       // Ir.mostrarGrupo();
 
           }
        
       
       
        
        
       
       else{
            JOptionPane.showMessageDialog(this,"El estudio: "+ examen + " no cuenta con esta opción", "ESTUDIO",1);
       
       }
       
       
           } 
            
         public void conteoTabla(){
        
       for(int x =0; x<=Jtabla.getRowCount(); x++ ){
       jLabel4.setText(""+x);
       
       }
       }
           
          
          
    public void email2()
    {
    String remitente ="fundaginebralab@gmail.com";
    String clave= "sqjaqrvlxglnrtol";
    String destino=correopaciente;
    Properties props= new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");
    props.setProperty("mail.smtp.port", "587");
    props.put("mail.smtp.user", remitente);
    props.setProperty("mail.smtp.auth","true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
    
    
 //  String PdfNames=nombrepaciente+"_"+idtabla;
    
    Session session = Session.getDefaultInstance(props);
    MimeMessage mensaje = new MimeMessage(session);
    
    try{
    mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
    mensaje.setSubject("Resultado de análisis | Fundación Convenio Ginebra I |");
  
    BodyPart parteTexto= new MimeBodyPart();
    parteTexto.setContent("Saludos cordiales, se adjunta el archivo pdf con los resultados de su análisis. <br> ¡Somos humanidad, paz y salud! <br><br> NOTA: Este es un mensaje automatizado, no debe ser respondido.", "text/html");
    
     
     File file = new File("C://Fundaginebra//dist//correo.pdf");
     BodyPart parteArchivo= new MimeBodyPart();
     parteArchivo.setDataHandler(new DataHandler(new FileDataSource(file)));
     parteArchivo.setFileName("analisis.pdf");
   
    
    MimeMultipart todasLasPartes = new MimeMultipart();
    todasLasPartes.addBodyPart(parteTexto);
    todasLasPartes.addBodyPart(parteArchivo);
    mensaje.setContent(todasLasPartes);
    
    
    Transport transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", remitente, clave);
    transport.sendMessage(mensaje, mensaje.getAllRecipients());
    transport.close();
    JOptionPane.showMessageDialog(null,"Correo Enviado a: "+ correopaciente );
    } 
    catch(Exception e)
    {System.out.println(e);
      JOptionPane.showMessageDialog(null,"ERROR AL ENVIAR EL CORREO", "ERROR EN CORREO", JOptionPane.ERROR_MESSAGE);}
    }

          
          
    
      // Método para autorizar las filas seleccionadas
    private void autorizarFilasSeleccionadas() {
       
        
        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla
        int columnaPaciente = 2; //
        // Obtén las filas seleccionadas
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            String nombrepaciente = modelo.getValueAt(fila, columnaPaciente).toString(); 
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.Revisar(107,    id  );
            AuditoriaAutorizar(nombrepaciente, id);
            // Actualiza la fila en la tabla (por ejemplo, cambia el estado a "Autorizado")
          
        }
    }
    
    
    
    
    
    
        private void conversarFilasSeleccionadas() {
       
        
        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla
        int columnaPaciente = 2; //
        // Obtén las filas seleccionadas
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            String nombrepaciente = modelo.getValueAt(fila, columnaPaciente).toString(); 
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.Revisar(109,    id  );
            AuditoriaAutorizar(nombrepaciente, id);
            // Actualiza la fila en la tabla (por ejemplo, cambia el estado a "Autorizado")
          
        }
    }
    
     private void completarEstudios() {
       
        
        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla

        // Obtén las filas seleccionadas
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.Revisar(103,    id  );

            // Actualiza la fila en la tabla (por ejemplo, cambia el estado a "Autorizado")
          
        }
    }
   
    
     
      private void realizarBusqueda() {
   

        if (JRpaciente.isSelected()) {
            if (jTextField1.getText().length() >= 4 ) {
                Searchbyname();
              
            }
       
          else if(jTextField1.getText().isEmpty()){
          limpiarTablaEstudios();
          visualizar_PdfVO(Jtabla);

            }
        
        
        } 
        
        
        
        else if (jRusuario.isSelected()) {
             
            if (jTextField1.getText().length() >= 4 ) {
                SearchbyUser();
       
    
            }
            
          else if(jTextField1.getText().isEmpty()){
          limpiarTablaEstudios();
          visualizar_PdfVO(Jtabla);
    
   

            }
            
            
        } else if (jRcodigo.isSelected()) {
            
            if (jTextField1.getText().length() >= 1 ) {
                SearchbyCod();
            
            }
              
          else if(jTextField1.getText().isEmpty()){
          limpiarTablaEstudios();
          visualizar_PdfVO(Jtabla);
  
        

            }
            
        }
        
    
        if(Jtabla.getRowCount() > 0){   conteoTabla(); acomodarceldas();}
        else{jLabel4.setText("0");}
       
    }

      
      
  public Map<String, Integer> knowNumbers() {
  //      Map<String, Integer> cantidadesPorIdExamen = new HashMap<>();
        Map<String, Integer> cantidadesPorIdExamen = new LinkedHashMap<>();
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
      

        try {
            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
        String sql = "SELECT Id_Estado, Estudio, FechaReporte, id_examen, COUNT(*) as cantidad_repeticiones\n" +
                    "FROM table_laboratorio u\n" +
                    "INNER JOIN table_estudios e \n" +
                    "ON u.Id_examen=e.IdEstudio\n" +
                    "WHERE u.Id_Estado <> 104 "+
                    "AND FechaReporte BETWEEN ? AND ? "+
                    "GROUP BY id_examen\n" +
                    "ORDER BY cantidad_repeticiones DESC\n";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idExamen = rs.getInt("id_examen");
                String nombreExamen = rs.getString("Estudio");
                int cantidadRepeticiones = rs.getInt("cantidad_repeticiones");

                // Almacenar en el mapa
                cantidadesPorIdExamen.put(nombreExamen, cantidadRepeticiones);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
       
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Devolver el mapa después de procesar la consulta
        return cantidadesPorIdExamen;
    }

public void enviarWs() {
    WebDriver driver = null;
    try {
        // Configura la ubicación del driver de Selenium (en este caso, ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Fundaginebra\\src\\chromedriver.exe");
        String telefono = "58+" + telefonoPac;

        // Inicia una nueva instancia de ChromeDriver
        driver = new ChromeDriver();

        // Abre WhatsApp Web si no está ya abierta
        if (!driver.getCurrentUrl().startsWith("https://web.whatsapp.com/")) {
            driver.get("https://web.whatsapp.com/");
        }

        // Espera hasta que aparezca el campo de búsqueda
        WebDriverWait wait = new WebDriverWait(driver, 60); // Espera hasta 60 segundos
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'copyable-text')]")));

        // Agregar un pequeño retraso antes de escribir en el campo de búsqueda
        Thread.sleep(1000);

        // Limpiar el campo de búsqueda
        searchBox.clear();

        // Escribe el número de teléfono en el campo de búsqueda
        searchBox.sendKeys(telefono);

        // Espera a que aparezca el contacto o grupo y haz clic en él
        WebElement contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@title, '" + telefonoPac + "')]")));
        contact.click();

        // Adjunta el archivo
        WebElement attachButton = driver.findElement(By.xpath("//div[@title='Adjuntar']"));
        attachButton.click();

        // Seleciona 'Documento'
        WebElement documentOption = driver.findElement(By.xpath("//input[@accept='*']"));
        documentOption.sendKeys("‪C:\\Fundaginebra\\dist\\Análisis.pdf");

        // Espera a que el archivo se cargue antes de enviar
        // Aquí puedes agregar código para esperar a que el archivo se cargue
        // o simplemente agregar un retraso
        // Thread.sleep(5000); // Espera 5 segundos por ejemplo

        // Envía el archivo
        WebElement sendButton = driver.findElement(By.xpath("//span[@data-icon='send']"));
        sendButton.click();

    } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(this, "TIEMPO AGOTADO", "TIMEOUT", JOptionPane.ERROR_MESSAGE);
    }  finally {
        if (driver != null) {
            //driver.quit();
        }
    }
}


public static boolean isAnyWindowOpen(WebDriver driver) {
    return !driver.getWindowHandles().isEmpty();
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
            Temporal Tempo = new Temporal();
            JCambiarState setState = new JCambiarState();
            String correopaciente="";
            String Pnombrecompleto="";
            Validar va = new Validar();
            SearchConsultasDao search = new SearchConsultasDao();
            
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Autorizar;
    public javax.swing.JMenuItem AutorizarTodo;
    private javax.swing.JMenuItem Cancelar;
    private javax.swing.JMenuItem Completar;
    private javax.swing.JMenuItem CompletarTodo;
    private javax.swing.JMenuItem Corregir;
    private javax.swing.JMenuItem Correo;
    public com.toedter.calendar.JDateChooser FechaOne;
    public com.toedter.calendar.JDateChooser FechaTwo;
    private javax.swing.JComboBox<String> JCestudios;
    private javax.swing.JRadioButton JRpaciente;
    private javax.swing.JTable Jtabla;
    private javax.swing.JPopupMenu State;
    private javax.swing.JMenuItem Whatsapp;
    private javax.swing.JMenuItem abrirPdfs;
    private javax.swing.JMenuItem imprimirItems;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jOrientar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRcodigo;
    private javax.swing.JRadioButton jRusuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
