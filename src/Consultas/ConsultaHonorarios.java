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
import Clases.Temporal;
import Clases.imgTabla;
import Clases.vGraficaDc;
import Clases.vGraficasHono;
import static Consultas.ConsultaLaboratorio.abrirEnlaceWhatsApp;
import Menu.Mprincipal;
import Procesos.JHonorarios;
import Procesos.JLabexamenes.JCmv;
import Procesos.JLaboratorio;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author FCGinebraI
 */
public class ConsultaHonorarios extends javax.swing.JInternalFrame {

 
    
    
    private Thread searchThread;
    private final int DELAY = 500;
    private  String Nivel;
    public ConsultaHonorarios() {
        initComponents();
               ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
               
              java.sql.Date fechaServidor = getFechaDelServidor();
    
    // Establecer la fecha del servidor en el componente FechaAsignar
    if (fechaServidor != null) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaServidor);
        FechaOne.setCalendar(cal);
        FechaTwo.setCalendar(cal);
    }
  
             informacionPdf();
             limpiarTabla();
             visualizar_PdfVO(Jtabla);
             conteoTabla();
             
             
             jSearchCI1.setSelected(true);
             
             Nivel=Tempo.getNivel();
             String especialidad= Tempo.getEspecialidad();
             String nivelUsuario=Nivel+" "+especialidad;
         
             if(Nivel.equals("Administrador") || Nivel.equals("Supervisor") ||nivelUsuario.equals("Gerente Contable")){
             Cancelar.setEnabled(true);
             Autorizar.setEnabled(true);
             jCorregir.setEnabled(true);
     
            }
             else if(nivelUsuario.equals("Supervisor Consultor")){
             Cancelar.setEnabled(false);
             Autorizar.setEnabled(false);
             jCorregir.setEnabled(false);
             EnviarWs.setEnabled(false);
             EnviarCorreo.setEnabled(false);
             Completar.setEnabled(false);
            
             }
         
             
             
             
             
             
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptions = new javax.swing.JPopupMenu();
        Autorizar = new javax.swing.JMenuItem();
        jCorregir = new javax.swing.JMenuItem();
        Completar = new javax.swing.JMenuItem();
        Cancelar = new javax.swing.JMenuItem();
        EnviarCorreo = new javax.swing.JMenuItem();
        EnviarWs = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jSearchCI1 = new javax.swing.JRadioButton();
        jRcodigo = new javax.swing.JRadioButton();
        FechaOne = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        FechaTwo = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        JEstados = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jEstadistica = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        JCespecialidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtabla = new javax.swing.JTable();

        jOptions.setEnabled(false);

        Autorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/autorizacion.png"))); // NOI18N
        Autorizar.setText("Autorizar");
        Autorizar.setEnabled(false);
        Autorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutorizarActionPerformed(evt);
            }
        });
        jOptions.add(Autorizar);

        jCorregir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edicion.png"))); // NOI18N
        jCorregir.setText("Corregir este informe");
        jCorregir.setEnabled(false);
        jCorregir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCorregirActionPerformed(evt);
            }
        });
        jOptions.add(jCorregir);

        Completar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/comprobacion.png"))); // NOI18N
        Completar.setText("Completar");
        Completar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompletarActionPerformed(evt);
            }
        });
        jOptions.add(Completar);

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setEnabled(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jOptions.add(Cancelar);

        EnviarCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/correo-electronico.png"))); // NOI18N
        EnviarCorreo.setText("Enviar por correo");
        EnviarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarCorreoActionPerformed(evt);
            }
        });
        jOptions.add(EnviarCorreo);

        EnviarWs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/_whatsapp.png"))); // NOI18N
        EnviarWs.setText("Enviar por ws");
        EnviarWs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarWsActionPerformed(evt);
            }
        });
        jOptions.add(EnviarWs);

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

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total de reportes:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 613, 1310, 70));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Exportar Tabla:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("C O N S U L T A S         H O N O R A R I O S");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buscar por:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 30));

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
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 160, 50));

        jSearchCI1.setBackground(new java.awt.Color(255, 255, 255));
        jSearchCI1.setText("Especialista");
        jSearchCI1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchCI1ActionPerformed(evt);
            }
        });
        jPanel2.add(jSearchCI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jRcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jRcodigo.setText("Código");
        jRcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRcodigoActionPerformed(evt);
            }
        });
        jPanel2.add(jRcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        FechaOne.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne.setToolTipText("");
        FechaOne.setDateFormatString("yyyy-MM-dd");
        FechaOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 170, 50));

        jLabel5.setText("Sortear por rango de fechas");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        FechaTwo.setBackground(new java.awt.Color(255, 255, 255));
        FechaTwo.setToolTipText("");
        FechaTwo.setDateFormatString("yyyy-MM-dd");
        FechaTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 170, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/magnifier-1_icon-icons.com_56924.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 60, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jButton2.setToolTipText("Exportar estadistica por especialidad");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 70, 50));

        JEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "Autorizado", "Cancelado", "Completado" }));
        JEstados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JEstadosItemStateChanged(evt);
            }
        });
        jPanel2.add(JEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 110, 50));

        jLabel9.setText("Filtrar Estadística");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/grafica.png"))); // NOI18N
        jButton3.setToolTipText("Exportar estadistica por especialidad");
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 60, 60, 50));

        jEstadistica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Resumen Global", "Resumen Productividad", "Resumen Deducciones" }));
        jPanel2.add(jEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 190, 50));

        jLabel10.setText("Filtrar por Estado");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, -1, -1));

        JCespecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Anestesiologia", "Bioanalista", "Cardiologia", "Cirugia General", "Coloproctologia", "Dermatologia", "Ecografia", "Enfermeria", "Espirometria", "Electroencefalografista", "Emergencia", "Gastroenterologia", "Ginecologia", "Hematologia", "Medicina Interna", "Nefrologia", "Nefrologia Infantil", "Neumonologia", "Neurocirugia", "Nutricionista", "Odontologia", "Otorrinolaringologia", "Oftalmologia", "Psicologia", "Psiquiatria", "Medico Radiologo", "Traumatologia", "Terapia del Dolor", "Tecnico Rx", "Urologia", "Cirugia Pediatrica", "Ocupacional", "Oncologia", "Pediatria", "Medicina General" }));
        JCespecialidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCespecialidadItemStateChanged(evt);
            }
        });
        jPanel2.add(JCespecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 150, 50));

        jLabel8.setText("Filtrar por Especialidad");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1290, 120));

        Jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Especialidad", "Especialista", "Usuario Encargado", "Reporte", "Pago", "Fecha Inicio", "Fecha Cierre", "Telefono", "Correo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtabla.setComponentPopupMenu(jOptions);
        Jtabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JtablaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(Jtabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 150, 1290, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    @SuppressWarnings("unchecked")
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
    
    
    
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        Jtabla.requestFocusInWindow();
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    
    
    public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) Jtabla.getModel();
        int a = Jtabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }
    
    
    
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        /*
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {

            JCestudios.setSelectedItem("Todos");

            if(JRpaciente.isSelected()){

                Searchbyname();  acomodarceldas();
            }

            else if(jRusuario.isSelected()){

                SearchbyUser();   acomodarceldas();
            }

            else if(jRcodigo.isSelected()){

                SearchbyCod();   acomodarceldas();

            }

        }
        */
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
JCespecialidad.setSelectedItem("Todos");

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

    
    
     
     private void realizarBusqueda() {
        JCespecialidad.setSelectedItem("Todos");

        if (jSearchCI1.isSelected()) {
            if (jTextField1.getText().length() >= 4 || jTextField1.getText().isEmpty()) {
          searchBy(params -> search.listarNameHon(params[0], params[1], params[2]));
          
          
            }
        } 
        
        else if (jRcodigo.isSelected()) {
            
            if (jTextField1.getText().length() >= 1 || jTextField1.getText().isEmpty()) {
           searchBy(params -> search.listarIdHon(params[0], params[1], params[2]));
      
            }
        }
        
        else if (jTextField1.getText().equals("")){
         limpiarTablaEstudios();
        visualizar_PdfVO(Jtabla);
        }
        
          conteoTabla();
          acomodarceldas();
    }

    
    
    
    
     
     
    public void searchBy(Function<String[], ArrayList<PdfVO>> searchFunction) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = dateFormat.format(FechaOne.getDate());
    String fecha2 = dateFormat.format(FechaTwo.getDate());
    
    // Crear un array con los parámetros para pasar al Function
    String[] params = { jTextField1.getText(), fecha, fecha2 };

    try {
        // Usar la función pasada como parámetro para obtener la lista
        ArrayList<PdfVO> list = searchFunction.apply(params);
        
        // Configuración de la tabla y procesamiento de datos...
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        // Definir las columnas una sola vez
         String[] columnNames = {
            "Código", "Especialidad", "Especialista", "Usuario Encargado",
            "Reporte","Pago", "Fecha Inicio", "Fecha Cierre", "Telefono", "Correo", "Estado"
        };
         
        for (String column : columnNames) {
            dt.addColumn(column);
        }

        // Cargar el icono una sola vez
        ImageIcon icono = get_Image("/Logos/32pdf.png") != null ? 
                          new ImageIcon(get_Image("/Logos/32pdf.png")) : null;

        limpiarTablaEstudios(); // Limpiar tabla antes de mostrar resultados

        if (!list.isEmpty()) {
            for (PdfVO vo : list) {
                Object[] fila = {
                    vo.getId_hon(), 
                    vo.getHon_especialidad(),
                    vo.getHon_especialista(),
                    vo.getHon_encargado(),
                    new JButton(icono),
                    vo.getNetoPagar(),
                    vo.getHon_fecha1(),
                    vo.getHon_fecha2(),
                    vo.getHo_telefono(),
                    vo.getHo_correo(),
                    vo.getHo_estado()
                };
                dt.addRow(fila);
            }

            // Asignar el modelo a la tabla y ajustar la altura
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
         
        }

    } catch (Exception e) {
        System.err.println("Error en searchByName: " + e.getMessage());
    }
}
     
     
     
     
     
    
    
    
    
    
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jSearchCI1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchCI1ActionPerformed
        this.jSearchCI1.setSelected(true);
      //jRusuario.setSelected(false);
        jRcodigo.setSelected(false);
        // jTextField1.setText("V-");
    }//GEN-LAST:event_jSearchCI1ActionPerformed

    private void jRcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRcodigoActionPerformed
        this.jRcodigo.setSelected(true);
        jSearchCI1.setSelected(false);
   //   jRusuario.setSelected(false);
        jTextField1.setText("");
    }//GEN-LAST:event_jRcodigoActionPerformed

    private void JCespecialidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCespecialidadItemStateChanged
 validarConjunto();
    }//GEN-LAST:event_JCespecialidadItemStateChanged

    
    
    
    
private void validarConjunto() {
    try {
        int indexEstudios = JCespecialidad.getSelectedIndex();
        int indexEstado = JEstados.getSelectedIndex();

        if (indexEstudios != -1 && indexEstado != -1) {
            if (indexEstudios != 0 && indexEstado != 0) {
     
           
              searchBetween(params -> search. listarHonoBoth(params[0], params[1], params[2], params[3]));
            } else if (indexEstudios != 0) {
   
               searchBetween(params ->        search.listarHonoSpeciallty(params[0], params[1], params[2], params[3]));
            } else if (indexEstado != 0) {

                  searchBetween(params -> search.listarHonoState(params[0], params[1], params[2], params[3]));
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
  
}

    
    
    
    
    
    
    
    
    
    
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    limpiarTabla();
    visualizar_PdfVO(Jtabla);
    conteoTabla(); 
    JEstados.setSelectedItem("Todos");
    JCespecialidad.setSelectedItem("Todos");
    JCespecialidad.setSelectedItem("Todos");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try{
              
           if(Jtabla.getRowCount()<=0){
            
          JOptionPane.showMessageDialog(null, "NO HAY ELEMENTOS EN LA BUSQUEDA, SELECCIONE UNA FECHA VALIDA Y PRESIONE EL ICONO DE LUPA", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }
            
       
          else if(jEstadistica.getSelectedItem().equals("Seleccionar")){   
         JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL TIPO DE ESTADÍSTICA A EXPORTAR", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          } 
      
          else{
               
      
               
          //GLOBAL--------------------------------------------------------          
       if(jEstadistica.getSelectedItem().equals("Resumen Global")){   
       graficarGlobal();
       pdfbyGlobal();
       String accion= "HORA: "+MP.Time.getText()+ " Genero el reporte estaditico global de consulta honorario global"; 
       auditoriaMethod(accion);
          }
          
          
          
          
          //PRODUCTIVIDAD--------------------------------------------------------
          else if(jEstadistica.getSelectedItem().equals("Resumen Productividad")){   
          if(JCespecialidad.getSelectedItem().equals("Todos")){
          JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECIALIDAD EN CONCRETO", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }else if(JCespecialidad.getSelectedItem().equals("Bioanalista") ||
          JCespecialidad.getSelectedItem().equals("Anesteciologia") ||
          JCespecialidad.getSelectedItem().equals("Radiologo") ||
          JCespecialidad.getSelectedItem().equals("Tecnico Rx")){   
          JOptionPane.showMessageDialog(null, "ESTA ESPECIALIDAD NO CUENTA CON ESTADÍSTICAS", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }else{
          graficoByServices();
          pdfbyDrs();
          String accion= "HORA: "+MP.Time.getText()+ " Genero el reporte estaditico productividad de consulta honorario"; 
          auditoriaMethod(accion);  }}
          
          
          
          
          
          //DEDUCCIONES-----------------------
          else if(jEstadistica.getSelectedItem().equals("Resumen Deducciones")){
          if(JCespecialidad.getSelectedItem().equals("Bioanalista")){   
       
          graficarDeduccion();    
          pdfbyDeduccionesLab();
          String accion= "HORA: "+MP.Time.getText()+ "Genero el reporte estaditico de deducciones de consulta honorario"; 
          auditoriaMethod(accion); 
          
          }else{
          graficarDeduccion();
          pdfbyDeducciones();
          String accion= "HORA: "+MP.Time.getText()+ "Genero el reporte estaditico de deducciones de consulta honorario"; 
          auditoriaMethod(accion); 
              
          }
          } 

  
               
               
         
         
         
         
         
               
       
            }
       
        }catch(Exception e){System.out.println(e);}
      
      
      
   
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
        private ChartFrame f;
        Font Letrasmall;
    
        public void pdfbyDrs() {
        try {
   
          
            DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy");
            LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          
                   
            String   PdfNames="ReporteHonorarios"+"_"+JCespecialidad.getSelectedItem().toString()+"_"+fth.format(fechaactual); 
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            File file = new File("C://Fundaginebra//Reportes//"+PdfNames+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            doc.setMargins(36, 36, 130, 130);          
            //doc.setMargins(36, 36, 125, 130);
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
          
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
            
            
            
            
            doc.open();
            
        
        
           //BODY 
            Letrasmall = new Font(BF, 8);
            Font letraMedium = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
            
            PdfPTable tablaResultado = new PdfPTable(5); 
            tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaResultado.setWidthPercentage(100);  
            float[] medidaCeldas3 = {4f, 12f, 2f, 3f, 3f};
            tablaResultado.setWidths(medidaCeldas3);
            tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            Paragraph tituloEspecialista = new Paragraph("Especialista");
            tituloEspecialista.getFont().setStyle(Font.BOLD);
            tituloEspecialista.setFont(Letrasmall);
            tituloEspecialista.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaEspecialista = new PdfPCell(tituloEspecialista);
            celdaEspecialista.setBorder(Rectangle.NO_BORDER);
            celdaEspecialista.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaResultado.addCell(celdaEspecialista);

            
            Paragraph tituloProc = new Paragraph("Items");
            tituloProc.getFont().setStyle(Font.BOLD);
            tituloProc.setFont(Letrasmall);
            tituloProc.setAlignment(Element.ALIGN_CENTER);
            PdfPCell ctituloProc = new PdfPCell(tituloProc);
            ctituloProc.setBorder(Rectangle.NO_BORDER);
            ctituloProc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(ctituloProc);
            
            
            Paragraph tituloCantidad = new Paragraph("Cant.");
            tituloCantidad.getFont().setStyle(Font.BOLD);
            tituloCantidad.setFont(Letrasmall);
            tituloCantidad.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaCantidad = new PdfPCell(tituloCantidad);
            celdaCantidad.setBorder(Rectangle.NO_BORDER);
            celdaCantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaResultado.addCell(celdaCantidad);
            
            Paragraph tituloBruto = new Paragraph("Bruto T.");
            tituloBruto.getFont().setStyle(Font.BOLD);
            tituloBruto.setFont(Letrasmall);
            tituloBruto.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaBruto = new PdfPCell(tituloBruto );
            celdaBruto.setBorder(Rectangle.NO_BORDER);
            celdaBruto.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaResultado.addCell(celdaBruto);
            
            Paragraph tituloNeto = new Paragraph("Neto T.");
            tituloNeto.getFont().setStyle(Font.BOLD);
            tituloNeto.setFont(Letrasmall);
            tituloNeto.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaNeto = new PdfPCell(tituloNeto);
            celdaNeto.setBorder(Rectangle.NO_BORDER);
            celdaNeto.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaResultado.addCell(celdaNeto);
            
       
           PdfPTable tablaSum = new PdfPTable(1); 
           tablaSum.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
           tablaSum.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
           tablaSum.setWidthPercentage(100); 
           float[] medidaSum = {5f};
           tablaSum.setWidths(medidaSum);
           tablaSum.setHorizontalAlignment(Element.ALIGN_CENTER);
           tablaSum.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);


    
            
            
            
            
            
   double sumEspecialidad=0;     
   Map<String, List<vGraficasHono>> cantidadesPorIdExamen = knowStats();
   int sumatoriaTotal = 0;
   double sumBruto=0;
   double sumNeto=0;
   double ganFundacion=0;
   double ganEspecialista=0;
   double totalDeducciones=0;
  
   
    // Itera sobre el Map y muestra las cantidades en la consola
    for (Map.Entry<String, List<vGraficasHono>> entry : cantidadesPorIdExamen.entrySet()) {
    String nombreCompleto = entry.getKey();
    List<vGraficasHono> statsList = entry.getValue();

    // Comprobación de nulidad
    if (statsList == null || statsList.isEmpty()) {
        System.out.println("No hay ExamenStats para el especialista: " + nombreCompleto);
        continue; 
    }
    sumBruto = 0;
    sumNeto = 0;
   
    // Bandera para controlar si el nombre ya se imprimió
    boolean esPrimeraFila = true;

    for (vGraficasHono stats : statsList) {
        String servicio = stats.getServicio();
        int cantidadRepeticiones = stats.getCantidad();
        double brutoTotal = stats.getBrutoTotal();
        double netoTotal = stats.getNetoTotal();
        totalDeducciones =stats.getRes_deducciones();
 
        
        // Comprobaciones de nulos
        if (nombreCompleto == null) {
            nombreCompleto = "N/A"; 
        }
        if (servicio == null) {
            servicio = "N/A"; 
        }

        // Sumar la cantidad de repeticiones
        if (cantidadRepeticiones < 0) {
            System.out.println("Cantidad negativa encontrada para " + nombreCompleto);
            continue; 
        }
        
        
        

        sumatoriaTotal += cantidadRepeticiones;
        sumBruto+= brutoTotal;
        sumNeto+= netoTotal;
       

        
     
     
        
        // Crear celdas para el PDF
        PdfPCell nombre;
        if (esPrimeraFila) {
            nombre = new PdfPCell(new Paragraph(nombreCompleto.toUpperCase(), Letrasmall));
            esPrimeraFila = false;  // Después de mostrar el nombre, no lo mostramos de nuevo
        } else {
            nombre = new PdfPCell(new Paragraph("", Letrasmall));  // Celda vacía en las filas siguientes
        }

        PdfPCell cellServ = new PdfPCell(new Paragraph(servicio.toUpperCase(), Letrasmall));
        PdfPCell cantidad = new PdfPCell(new Paragraph(String.valueOf(cantidadRepeticiones), Letrasmall));
        PdfPCell bruto = new PdfPCell(new Paragraph(String.valueOf(brutoTotal), Letrasmall));
        PdfPCell neto = new PdfPCell(new Paragraph(String.valueOf(netoTotal), Letrasmall));

        // Configuración de las celdas
        for (PdfPCell cell : new PdfPCell[]{nombre, cellServ, cantidad, bruto, neto}) {
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.enableBorderSide(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablaResultado.addCell(cell);
         
        }
    }
  
           ganFundacion=sumBruto-sumNeto;
           ganEspecialista=sumNeto-totalDeducciones;
   
           String totalBruto = String.format("%.2f", sumBruto);
           String totalNeto = String.format("%.2f", sumNeto);
           String deduccionEspecialista = String.format("%.2f", totalDeducciones);

           String gananciaFundacion = String.format("%.2f", ganFundacion);
           String gananciaEspecialista = String.format("%.2f", ganEspecialista);

           // Crear las celdas de texto
           PdfPCell cellTotal = new PdfPCell(new Paragraph(
           "\nTOTAL BRUTO: " + totalBruto + 
           "\nTOTAL NETO : " + totalNeto + 
           "\nDEDUCCIÓN ESPECIALISTA: " + deduccionEspecialista, 
           Letrasmall
            ));

           // Usar una fuente más pequeña para la segunda celda
           PdfPCell cellTotal2 = new PdfPCell(new Paragraph(
           "\nGANANCIA FUNDACIÓN: " + gananciaFundacion + 
           "\nGANANCIA ESPECIALISTA: " + gananciaEspecialista, 
           letraMedium
            ));


          cellTotal.setColspan(5); 
          cellTotal.setBorder(Rectangle.NO_BORDER);  
          cellTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);  
          cellTotal.setVerticalAlignment(Element.ALIGN_RIGHT); 
          cellTotal2.setColspan(5); 
          cellTotal2.setBorder(Rectangle.NO_BORDER);  
          cellTotal2.setHorizontalAlignment(Element.ALIGN_RIGHT);  
          cellTotal2.setVerticalAlignment(Element.ALIGN_RIGHT);  

// Agregar las celdas a la tabla
tablaResultado.addCell(cellTotal);
tablaResultado.addCell(cellTotal2);

// Establecer el ancho de la tabla al 100%
tablaResultado.setWidthPercentage(100);
  
    
    PdfPCell emptyCell = new PdfPCell(new Paragraph("")); 
    emptyCell.setColspan(5); 
    emptyCell.setBorder(Rectangle.NO_BORDER);
    emptyCell.enableBorderSide(Rectangle.BOTTOM);
    emptyCell.setFixedHeight(10); 
    tablaResultado.addCell(emptyCell);
    sumEspecialidad+=sumBruto;
    
}
    
    
  



            
            
PdfPTable tablaTotal = new PdfPTable(1); 
tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
tablaTotal.setWidthPercentage(100); 
float[] medidaResultado = {5f};
tablaTotal.setWidths(medidaResultado);
tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);  
tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

String fecha1 = new SimpleDateFormat("dd-MM-yyyy").format(FechaOne.getDate());
String fecha2 = new SimpleDateFormat("dd-MM-yyyy").format(FechaTwo.getDate());
Paragraph t41columna1 = new Paragraph("TOTAL DE SERVICIOS O ITEMS ENCONTRADOS: " + sumatoriaTotal +"\nSUMATORIA BRUTA TOTAL: " +String.format("%.2f", sumEspecialidad)+ "\nREALIZADOS ENTRE LAS FECHAS " + fecha1 + " | " + fecha2);
t41columna1.getFont().setStyle(Font.NORMAL);
t41columna1.getFont().setSize(12);        
t41columna1.setFont(Letrasmall);
t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
PdfPCell celdaTotal = new PdfPCell(t41columna1);
celdaTotal.setBorder(Rectangle.NO_BORDER);
tablaTotal.addCell(celdaTotal);

//doc.add(saltolinea);
doc.add(tablaResultado);
doc.add(saltolinea);

try {
    com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\grafica_Honorarios.png");
    
    float pageWidth = doc.getPageSize().getWidth();
    float imageWidth = imgGrafica.getScaledWidth();
    float xPos = (pageWidth - imageWidth) / 2;

    // Establece la posición X de la imagen en el documento
    imgGrafica.setAlignment(Element.ALIGN_CENTER);
    imgGrafica.scaleToFit(500, 500);
    
    // Agrega la imagen al documento
    doc.add(imgGrafica);
    doc.add(saltolinea);
    
    doc.add(tablaTotal);
    
} catch (IOException e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
   
         
   

         
            //FOOTER
       // addFooter(writer);
            
            
            doc.close();
            archivo.close();
            if (tablaResultado.getRows().size() <= 1) {
       //     JOptionPane.showMessageDialog(null, "NO HAY REPORTES QUE VISUALIZAR, SOLO SE MUESTRAN AUTORIZADOS O COMPLETADOS", "REPORTES", JOptionPane.ERROR_MESSAGE);
            } else {
             Desktop.getDesktop().open(file);}
        
            
         } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    
       






 public void graficoByServices() throws IOException {
    // Formatear las fechas con java.time para mayor flexibilidad
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
   String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    String rutaDestino = "C:\\Fundaginebra\\dist\\grafica_Honorarios.png";

 
    
    
   

   
   
   String sql = "SELECT " +
    "    a.id_especialista, " +
    "    CONCAT(p.Nombre, ' ', p.Apellido) AS nombreCompleto, " +
    "    es.especialidad, " +
    "    x.servicio, " +
    "    x.cantidad " + 
    "FROM " +
    "    honorario_servicios x " +
    "LEFT JOIN " +
    "    honorarios a ON a.id_horonario = x.id_honorario " +
    "LEFT JOIN " +
    "    table_personal p ON p.IdPersonal = a.id_especialista " +
    "INNER JOIN " +
    "    table_especialidad es ON es.id_especialidad = a.id_especialidad " +
    "LEFT JOIN " +
    "    table_servicios ts ON ts.servicio = x.servicio " +
    "LEFT JOIN " +
    "    table_procedimientos tp ON tp.Procedimiento = x.servicio " +
    "WHERE " +
    "    es.especialidad = ? " + 
    "    AND a.Ho_estado NOT IN (104, 106) " + 
    "    AND a.Ho_fechaOne BETWEEN ? AND ? " + 
    "    AND (ts.servicio IS NOT NULL OR tp.Procedimiento IS NOT NULL) " + 
    "GROUP BY " + 
    "    a.id_especialista, " +
    "    CONCAT(p.Nombre, ' ', p.Apellido), " +
    "    es.especialidad, " +
    "    x.servicio " + 
    "ORDER BY " +
    "    cantidad DESC;"; 
   
   
   
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Asignar parámetros a la consulta
        ps.setString(1, JCespecialidad.getSelectedItem().toString());
        ps.setString(2, fecha);
        ps.setString(3, fecha2);

        try (ResultSet rs = ps.executeQuery()) {
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "NO HAY DATOS PARA MOSTRAR, SOLO SE TOMA LOS INFORMES COMPLETADOS", "HONORARIOS", JOptionPane.WARNING_MESSAGE);
              
                return;
            }

            // Crear el dataset para la gráfica
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while (rs.next()) {
                String especialista = rs.getString("nombreCompleto");
                String servicio = rs.getString("servicio");
                int cantidad = rs.getInt("cantidad");

                // Agregar datos al dataset
                dataset.addValue(cantidad, especialista, servicio);
            }

      
            
            
             JFreeChart chart = create3DBarChart(dataset, "Resumen por Productividad", "Servicios", "Especialistas");
            

            // Guardar la gráfica como imagen
            ChartUtilities.saveChartAsPNG(new File(rutaDestino), chart, 1000, 600);

          
            f = new ChartFrame("GRAFICO SERVICIOS", chart);
            f.setSize(1000, 600);
      
        }
    } catch (SQLException e) {
        System.err.println("Error en la base de datos: " + e.getMessage());
        e.printStackTrace();
    }
}

        
        
   
   
   
   
   
   
   
   
   

private Color[] generateColorPalette(int numberOfColors) {
   
    Color[] predefinedColors = {
        Color.BLUE, Color.GREEN, Color.ORANGE, Color.CYAN,
        Color.MAGENTA, Color.YELLOW, Color.DARK_GRAY, Color.PINK,Color.RED,
        new Color(139, 69, 19),  // BROWN
        new Color(0, 255, 0),    // LIME (verde lima)
        new Color(128, 0, 128),  // PURPLE
        new Color(0, 128, 128),  // TEAL
        new Color(75, 0, 130),   // INDIGO
        new Color(238, 130, 238), // VIOLET
        new Color(192, 192, 192) // SILVER
    };

    // Si el número de colores solicitado es mayor que los colores predefinidos, se repiten
    Color[] result = new Color[numberOfColors];
    for (int i = 0; i < numberOfColors; i++) {
        result[i] = predefinedColors[i % predefinedColors.length];
    }
    return result;
}
 



/*     String sql="SELECT \n" +
"    x.id_servicio, \n" +
"    a.id_horonario, \n" +
"    a.id_especialista, \n" +
"    CONCAT(p.Nombre, ' ', p.Apellido) AS nombreCompleto, \n" +
"    es.especialidad,\n" +
"    x.servicio, \n" +
"    x.cantidad,\n" +
"    ROUND(SUM(x.bruto_total), 2) AS total_bruto,  \n" +
"    ROUND(SUM(x.neto), 2) AS total_neto,   \n" +
"    r.res_deducciones AS deduccion_total\n" +
"    FROM \n" +
"    honorario_servicios x\n" +
"    LEFT JOIN \n" +
"    honorarios a ON a.id_horonario = x.id_honorario\n" +
"    LEFT JOIN \n" +
"    table_personal p ON p.IdPersonal = a.id_especialista\n" +
"    LEFT JOIN \n" +
"    honorario_resumen r ON  x.id_honorario=r.id_honorario\n" +
"    INNER JOIN \n" +
"    table_especialidad es ON es.id_especialidad = a.id_especialidad\n" +
"    WHERE \n" +
"    es.especialidad = ? \n" +
"    AND a.Ho_estado <> 104 \n" +
"    AND a.Ho_estado <> 106  \n" +
"    AND a.Ho_fechaOne BETWEEN ? AND ? \n" +
"    GROUP BY \n" +
"    x.id_servicio, \n" +
"    a.id_horonario, \n" +
"    a.id_especialista, \n" +
"    p.Nombre, \n" +
"    p.Apellido, \n" +
"    es.especialidad,\n" +
"    x.servicio, \n" +
"    x.cantidad,\n" +
"    r.res_deducciones\n" +
"    ORDER BY \n" +
"    x.cantidad DESC;";*/




    public Map<String, List<vGraficasHono>> knowStats() {
    Map<String, List<vGraficasHono>> cantidadesPorIdExamen = new LinkedHashMap<>();
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

     String sql="SELECT \n" +
"    x.id_servicio, \n" +
"    a.id_horonario, \n" +
"    a.id_especialista, \n" +
"    CONCAT(p.Nombre, ' ', p.Apellido) AS nombreCompleto, \n" +
"    es.especialidad,\n" +
"    x.servicio, \n" +
"    SUM(x.cantidad) AS cantidad, \n" +
"    ROUND(SUM(x.bruto_total), 2) AS total_bruto,  \n" +
"    ROUND(SUM(x.neto), 2) AS total_neto,  \n" +
             
             
"    (SELECT  ROUND(SUM(x_sub.res_deducciones),2) \n" +
"    FROM honorario_resumen x_sub \n" +
"    LEFT JOIN honorarios a_sub ON a_sub.id_horonario = x_sub.id_honorario\n" +
"    INNER JOIN table_especialidad es_sub ON es_sub.id_especialidad = a_sub.id_especialidad\n" +
"    WHERE es_sub.especialidad = es.especialidad \n" +
"    AND a_sub.Ho_estado <> 104\n" +
"    AND a_sub.Ho_estado <> 106\n" +
"    AND a_sub.Ho_fechaOne BETWEEN ? AND ? \n" +
"    AND a_sub.id_especialista = a.id_especialista) AS total_deducciones\n" +
             
             
             
"FROM  honorario_servicios x\n" +
             
"LEFT JOIN \n" +
"honorarios a ON a.id_horonario = x.id_honorario\n" +
             
"LEFT JOIN \n" +
"table_personal p ON p.IdPersonal = a.id_especialista\n" +
             
"INNER JOIN \n" +
"table_especialidad es ON es.id_especialidad = a.id_especialidad\n" +
             
             
"WHERE \n" +
"es.especialidad = ? \n" +
"AND a.Ho_estado <> 104 \n" +
"AND a.Ho_estado <> 106  \n" +
"AND a.Ho_fechaOne BETWEEN ? AND ? \n" +
             
             
"GROUP BY \n" +
"    a.id_especialista, \n" +  
"    p.Nombre, \n" +
"    p.Apellido, \n" +
"    es.especialidad,\n" +
"    x.servicio \n" +
"ORDER BY cantidad DESC;";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        ps.setString(3, JCespecialidad.getSelectedItem().toString());
        ps.setString(4, fecha1);
        ps.setString(5, fecha2);

        rs = ps.executeQuery();

    while (rs.next()) {
    String especialista = rs.getString("nombreCompleto");
    String servicio = rs.getString("servicio"); 
    int cantidadRepeticiones = rs.getInt("cantidad"); 
    double brutoTotal = rs.getDouble("total_bruto"); 
    double neto = rs.getDouble("total_neto"); 
    double deducciones = rs.getDouble("total_deducciones"); 
 

    
    // Almacenar en el mapa
    cantidadesPorIdExamen.computeIfAbsent(especialista, k -> new ArrayList<>())
           .add(new vGraficasHono(servicio, cantidadRepeticiones, brutoTotal, neto, null, 0, 0,deducciones, 0));}


    } catch (Exception e) {
        System.out.println(e);
    } finally {
       cn.closeResources(rs, ps, con);
    }

     // Ordenar los especialistas por el total bruto
    List<Map.Entry<String, List<vGraficasHono>>> sortedList = new ArrayList<>(cantidadesPorIdExamen.entrySet());
    sortedList.sort((entry1, entry2) -> {
        double totalBruto1 = entry1.getValue().stream()
            .mapToDouble(v -> v.getNetoTotal())
            .sum(); // Sumar el total bruto de todos los servicios del especialista
        double totalBruto2 = entry2.getValue().stream()
            .mapToDouble(v -> v.getNetoTotal())
            .sum(); // Sumar el total bruto de todos los servicios del especialista
        return Double.compare(totalBruto2, totalBruto1); // Ordenar en orden descendente
    });

    // Volver a poner los elementos ordenados en el mapa
    Map<String, List<vGraficasHono>> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, List<vGraficasHono>> entry : sortedList) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }


    return sortedMap;
}

    
    
    
    
    
    







  
                 
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        try {

            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(MP.FechaAdmin.getDate());
            String Hora = MP.Time.getText() + " " + MP.jLabel102.getText();

            BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            cb.setFontAndSize(BF2, 12);

            // Ajustar el espacio entre el encabezado y el cuerpo (por ejemplo, margen superior de 50)
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
            imagen.setAbsolutePosition(50, 750);  // Ajusta la posición de la imagen
            imagen.scaleToFit(110, 110);  // Ajusta el tamaño de la imagen
            cb.addImage(imagen);

            cb.setFontAndSize(BF2, 12);
            cb.setTextMatrix(200, 800);
            cb.showText(empresa);
            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(260, 790);
            cb.showText(rif);
            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(460, 780);
            cb.showText("FECHA: " + Fecha);

            cb.setFontAndSize(BF2, 10);
            cb.setTextMatrix(460, 770);
            cb.showText("HORA: " + Hora);

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


  
  
  
  
  
  
  
  
  
  
  
    
    
    
    
    int idtabla;
    String especialidad, estados, nombreDoctor, correo, telefonoPac, fecha1, fecha2;
    
    
    
    private void JtablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtablaMouseClicked
   Jtabla.requestFocusInWindow();



   try{
        String Nivel=Tempo.getNivel();
        int column = Jtabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / Jtabla.getRowHeight();
       
        int fila = Jtabla.getSelectedRow();
        idtabla=(int) (Jtabla.getValueAt(fila, 0));
        especialidad= (Jtabla.getValueAt(fila, 1).toString());
        nombreDoctor= (Jtabla.getValueAt(fila, 2).toString());
        
        fecha1= (Jtabla.getValueAt(fila, 6).toString());
        fecha2= (Jtabla.getValueAt(fila, 7).toString());
        
        telefonoPac=(Jtabla.getValueAt(fila, 8).toString());
        correo= (Jtabla.getValueAt(fila, 9).toString());
        estados= (Jtabla.getValueAt(fila, 10).toString());
       
        
        
        
        



        
        if (row < Jtabla.getRowCount() && row >= 0 && column < Jtabla.getColumnCount() && column >= 0) {
            idtabla = (int) Jtabla.getValueAt(row, 0);
            Object value = Jtabla.getValueAt(row, column);
            
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

          
      
           
                  
                    try {
                               PdfDAO1 pd = new PdfDAO1();
                        
                    if (nivelAux() ) {
                    pd.simularClic(boton);
                    JOptionPane.showMessageDialog(null, "El archivo debe autorizarse para que lo puedas visualizar", "PERMISOS", 1);   
                   }
                    else if(nivelAuxC()){
                    JOptionPane.showMessageDialog(null, "No puedes visualizar archivos cancelados", "PERMISOS", 1);  
                    }
                    
                    
                    else{
                       PdfDAO1 click = new PdfDAO1();
                       click.simularClic(boton);
                       pd.ejecutar_archivoHonorario(idtabla, nombreDoctor);
                         
                       
                        
                        }
                        
                        
                        
                      
                        //Desktop.getDesktop().open(new File("Reporte #"+id+"_"+name+".pdf"));
                    } catch (Exception ex) {JOptionPane.showMessageDialog(null,"EL HONORARIO AUN NO CUENTA CON UN REPORTE ASOCIADO; GENERE EL REPORTE E INTENTE DE NUEVO", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
                    System.out.println("error abriendo pdf:" + ex);
                    
                    }
                    
                

            } 
            
 
        }
   }catch(Exception e){ 
   System.out.println(e);
   Jtabla.requestFocusInWindow();
   }     

    }//GEN-LAST:event_JtablaMouseClicked

    private void JtablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtablaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JtablaMouseEntered

    private void JEstadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JEstadosItemStateChanged
   validarConjunto();
    }//GEN-LAST:event_JEstadosItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //    limpiarTablaEstudios();
        //    visualizar_PdfVO(Jtabla);
        //    acomodarceldas();
        conteoTabla();
        limpiarTablaEstudios();
        visualizar_PdfVO(Jtabla);
     //   conteoTabla();
        Jtabla.requestFocusInWindow();
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    public void conteoTabla(){
       
       for(int x =0; x<=Jtabla.getRowCount(); x++ ){
       jLabel4.setText(""+x);
       
       }
       }
           
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
        try{
           
            
          if(Jtabla.getRowCount()<=0){
            
          JOptionPane.showMessageDialog(null, "NO HAY ELEMENTOS EN LA BUSQUEDA, SELECCIONE UNA FECHA VALIDA Y PRESIONE EL ICONO DE LUPA", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }
            
       
          else if(jEstadistica.getSelectedItem().equals("Seleccionar")){   
         JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL TIPO DE ESTADÍSTICA A EXPORTAR", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          } 
        
         
          else{
            
              
                      
           //GLOBAL--------------------------------------------------------          
          if(jEstadistica.getSelectedItem().equals("Resumen Global")){   
       
         graficarGlobal();
         RefineryUtilities.centerFrameOnScreen(f); 
         f.setVisible(true); 
          }
          
          
          
          
          //PRODUCTIVIDAD--------------------------------------------------------
          else if(jEstadistica.getSelectedItem().equals("Resumen Productividad")){   
          if(JCespecialidad.getSelectedItem().equals("Todos")){
          JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECIALIDAD EN CONCRETO", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }else if(JCespecialidad.getSelectedItem().equals("Bioanalista") ||
          JCespecialidad.getSelectedItem().equals("Anesteciologia") ||
          JCespecialidad.getSelectedItem().equals("Radiologo") ||
          JCespecialidad.getSelectedItem().equals("Tecnico Rx")){   
          JOptionPane.showMessageDialog(null, "ESTA ESPECIALIDAD NO CUENTA CON ESTADÍSTICAS", "HONORARIOS", JOptionPane.ERROR_MESSAGE);
          }else{
          graficoByServices();
          RefineryUtilities.centerFrameOnScreen(f); 
          f.setVisible(true);  
          String accion= "HORA: "+MP.Time.getText()+ "Exporto la grafica de: "+ jEstadistica.getSelectedItem().toString();  }
          
          
          
          
          
          //DEDUCCCIONES--------------------------------------------------------
          }else if(jEstadistica.getSelectedItem().equals("Resumen Deducciones")){
      
              
              
              graficarDeduccion();
              RefineryUtilities.centerFrameOnScreen(f); 
               f.setVisible(true); 
              
          } 
          
          
          
          
         
          
          }
       
        }catch(Exception e){System.out.println(e);}
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void AutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutorizarActionPerformed
        
        int fila = Jtabla.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 

else if (   estados.equals("Autorizado")){ JOptionPane.showMessageDialog(null, "El reporte o uno de los mismos ya se encuentra Autorizado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

else {
    autorizarFilasSeleccionadas();
    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Autorizado");
    limpiarTablaEstudios();
    
    if (!JEstados.getSelectedItem().equals("Todos") && !JCespecialidad.getSelectedItem().equals("Todos")) {
      searchBetween(params -> search. listarHonoBoth(params[0], params[1], params[2], params[3]));
    
    } else if (!JEstados.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search.listarHonoState(params[0], params[1], params[2], params[3]));
    } else if (!JCespecialidad.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoSpeciallty(params[0], params[1], params[2], params[3]));
    }  else {
        visualizar_PdfVO(Jtabla);
    }

    acomodarceldas();
    Jtabla.requestFocusInWindow();
}
       
    }//GEN-LAST:event_AutorizarActionPerformed

    private void CompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompletarActionPerformed
      int fila = Jtabla.getSelectedRow();
   
      if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 
      else if (estados.equals("Espera")){ JOptionPane.showMessageDialog(null, "Debe autorizar el estado del reporte antes de completar", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

      else if (estados.equals("Completado")){ JOptionPane.showMessageDialog(null, "El reporte o uno de los mismos ya se encuentra Completado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

else {
    completarFilasSeleccionadas();
    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Completado");
    limpiarTablaEstudios();
    
    if (!JEstados.getSelectedItem().equals("Todos") && !JCespecialidad.getSelectedItem().equals("Todos")) {
      searchBetween(params -> search. listarHonoBoth(params[0], params[1], params[2], params[3]));
    
    } else if (!JEstados.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoState(params[0], params[1], params[2], params[3]));
    } else if (!JCespecialidad.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoSpeciallty(params[0], params[1], params[2], params[3]));
    }  else {
        visualizar_PdfVO(Jtabla);
    }

    acomodarceldas();
    Jtabla.requestFocusInWindow();
}
    }//GEN-LAST:event_CompletarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
    int fila = Jtabla.getSelectedRow();
   
      if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 

      else if (estados.equals("Cancelado")){ JOptionPane.showMessageDialog(null, "El reporte o uno de los mismos ya se encuentra Cancelado", "Estado", JOptionPane.WARNING_MESSAGE); Jtabla.requestFocusInWindow();}

else {
          
       if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea cancelar estos reportes?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {     
          
    cancelarFilasSeleccionadas();
    JOptionPane.showMessageDialog(null, "El estado del estudio se ha actualizado a : Cancelado");
    limpiarTablaEstudios();
    
    if (!JEstados.getSelectedItem().equals("Todos") && !JCespecialidad.getSelectedItem().equals("Todos")) {
      searchBetween(params -> search. listarHonoBoth(params[0], params[1], params[2], params[3]));
    
    } else if (!JEstados.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoState(params[0], params[1], params[2], params[3]));
    } else if (!JCespecialidad.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoSpeciallty(params[0], params[1], params[2], params[3]));
    }  else {
        visualizar_PdfVO(Jtabla);
    }

    acomodarceldas();
    Jtabla.requestFocusInWindow();
    
   
    }
}
    }//GEN-LAST:event_CancelarActionPerformed

    private void EnviarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarCorreoActionPerformed
      int fila = Jtabla.getSelectedRow();
   
      if (fila == -1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar una Fila");
} 
      else  if(nivelAux()){
           JOptionPane.showMessageDialog(this, "El reporte debe autorizarse para ser enviado por correo", "Correo", JOptionPane.ERROR_MESSAGE);
        
        }
         else  if(nivelAuxC()){
           JOptionPane.showMessageDialog(this, "No puede enviar por correo un reporte cancelado", "Correo", JOptionPane.ERROR_MESSAGE);
        
        }
        
      else{
       
      PdfDAO1 pd = new PdfDAO1();
      pd.correo_archHonorario(idtabla);  
      email2();
     
 
      
      if (!JEstados.getSelectedItem().equals("Todos") && !JCespecialidad.getSelectedItem().equals("Todos")) {
      searchBetween(params -> search. listarHonoBoth(params[0], params[1], params[2], params[3]));
    
    } else if (!JEstados.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoState(params[0], params[1], params[2], params[3]));
    } else if (!JCespecialidad.getSelectedItem().equals("Todos")) {
           searchBetween(params -> search. listarHonoSpeciallty(params[0], params[1], params[2], params[3]));
    }  else {
        visualizar_PdfVO(Jtabla);
    }
   String accion= "HORA: "+MP.Time.getText()+ " Envio por correo el reporte honorario "+idtabla+" del especialista: "+ nombreDoctor;  
   auditoriaMethod(accion);
         }
      
     
          Jtabla.requestFocusInWindow();    
        
    }//GEN-LAST:event_EnviarCorreoActionPerformed

    private void jCorregirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCorregirActionPerformed
  
         String Nivel=Tempo.getNivel();
         String especialidad= Tempo.getEspecialidad();
         String nivelUsuario=Nivel+" "+especialidad;
         if (Nivel.equals("Administrador") || nivelUsuario.equals("Supervisor Administrativo") ) {
            try{
                
     
      int fila = Jtabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, Seleccione una fila e intente de nuevo", "Corregir", JOptionPane.ERROR_MESSAGE );
        } 

         else{
              enviarDatos();
              
              }  
              
            }catch(Exception e){
            
            System.out.println(e);
            Jtabla.requestFocusInWindow();
            } 
           
    }
         
   /*
          else  if (Nivel.equals("Aux. Laboratorio") ) {
             
               JOptionPane.showMessageDialog(null,"Tu nivel de acceso no cuenta con esta opción", "MÓDULOS", JOptionPane.ERROR_MESSAGE);
               Jtabla.requestFocusInWindow();
    }*/
         else{
         
                  JOptionPane.showMessageDialog(null,"MÓDULO EN CONSTRUCCIÓN", "MÓDULOS", 1);
                  Jtabla.requestFocusInWindow();
         }
                
   
    }//GEN-LAST:event_jCorregirActionPerformed

    
    
    
    
    public void enviarDatos(){
        
        
        
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JHonorarios jHon = new JHonorarios();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jHon);
       jHon.setClosable(true);
       jHon.setIconifiable(true);
       
       try {
        jHon.setMaximum(true);
        } catch (Exception e) {
        }
        jHon.toFront();
        jHon.setVisible(true);  
        jHon.BtnGuardar.setEnabled(false);

 
   
        
      
  
       jHon.llenarCombo();
        
       jHon.JComboEspecialidad.setSelectedItem(especialidad);
      
       if(especialidad.equals("Bioanalista")){
       jHon.llenarBioanalisis();
       }else{
       jHon.llenarConsulta();
       }
      
       
       jHon.JComboDoctor.setSelectedItem(nombreDoctor);
       jHon.idHonorario=idtabla;
       jHon.corregir= true;
       jHon.listarTablaC();
       jHon.calcularTotales();
       jHon.knowResume();
       jHon.knowDeduccion();
       jHon.jISRL.setSelected(true);
       
       

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       try{
       Date date1 = sdf.parse(fecha1);
       Date date2 = sdf.parse(fecha2);    
       
       jHon.FechaOne.setDate(date1);
       jHon.FechaTwo.setDate(date2);
       
       }catch(Exception e){
               System.out.println(e);
               }
     
       

    
    }
    
    
    
    
    private void EnviarWsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarWsActionPerformed
                //abrirVariosPdf()
      if (nivelAux()) {
                   // pd.simularClic(boton);
 
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" debe autorizarse para que lo puedas enviar, NO PUEDE SELECCIONAR ARCHIVOS NO AUTORIZADOS", "PERMISOS", 1);
                    
                } else if (nivelAuxC()) {
               //     pd.simularClic(boton);
               
                    JOptionPane.showMessageDialog(null, "El archivo n° "+idtabla+" fue cancelado, NO PUEDE SELECCIONAR ARCHIVOS CANCELADOS", "PERMISOS", 1);
                    
                } else {
               //     pd.simularClic(boton);
                 
                      abrirEnlaceWhatsApp(telefonoPac);
    
                }  

    }//GEN-LAST:event_EnviarWsActionPerformed
/*
private PdfPCell crearCelda(String contenido, Font font) {
    PdfPCell celda = new PdfPCell(new Paragraph(contenido, font));
    celda.setBorder(PdfPCell.NO_BORDER);
    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return celda;
}*/


public void pdfbyGlobal() {
    
    try {
        DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        String pdfName = "ReporteHonorarios_Global_" + fth.format(fechaactual);
        String outputFilePath = "C://Fundaginebra//Reportes//" + pdfName + ".pdf";
        
        // Inicialización de la fuente
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
        Font letraSmall = new Font(BF, 8);
        Font letraMedium = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        Paragraph saltolinea = new Paragraph();
        saltolinea.add("\n");
        
        // Creación de archivo y documento
        FileOutputStream archivo = new FileOutputStream(new File(outputFilePath));
        Document doc = new Document();
        doc.setMargins(36, 36, 130, 130);
        PdfWriter writer = PdfWriter.getInstance(doc, archivo);
        Letrasmall = new Font(BF, 8);
       
       
        // Configuración del header y footer
        writer.setPageEvent(new HeaderFooterPageEvent());
        writer.setPageEvent(eventHelper);

            // Abrir el documento
            doc.open();

            PdfPTable tablaResultado = new PdfPTable(5); 
            tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            tablaResultado.setWidthPercentage(100);  
            float[] medidaCeldas3 = {6f, 3f, 6f, 6f, 6f};
            tablaResultado.setWidths(medidaCeldas3);
            tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            
            Paragraph tituloEspecialidad = new Paragraph("Especialista");
            tituloEspecialidad.getFont().setStyle(Font.BOLD);
            tituloEspecialidad.setFont(letraSmall);
            tituloEspecialidad.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaEspecialidad = new PdfPCell(tituloEspecialidad);
            celdaEspecialidad.setBorder(Rectangle.NO_BORDER);
            celdaEspecialidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(celdaEspecialidad);
            
         
            
            Paragraph tituloProc = new Paragraph("Cant.");
            tituloProc.getFont().setStyle(Font.BOLD);
            tituloProc.setFont(letraSmall);
            tituloProc.setAlignment(Element.ALIGN_CENTER);
            PdfPCell ctituloProc = new PdfPCell(tituloProc);
            ctituloProc.setBorder(Rectangle.NO_BORDER);
            ctituloProc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(ctituloProc);
            
            
            Paragraph tituloCantidad = new Paragraph("Bruto T.");
            tituloCantidad.getFont().setStyle(Font.BOLD);
            tituloCantidad.setFont(letraSmall);
            tituloCantidad.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaCantidad = new PdfPCell(tituloCantidad);
            celdaCantidad.setBorder(Rectangle.NO_BORDER);
            celdaCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(celdaCantidad);
            
            Paragraph tituloBruto = new Paragraph("Deducciones");
            tituloBruto.getFont().setStyle(Font.BOLD);
            tituloBruto.setFont(letraSmall);
            tituloBruto.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaBruto = new PdfPCell(tituloBruto );
            celdaBruto.setBorder(Rectangle.NO_BORDER);
            celdaBruto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(celdaBruto);
            
            Paragraph tituloNeto = new Paragraph("Neto Indv.");
            tituloNeto.getFont().setStyle(Font.BOLD);
            tituloNeto.setFont(letraSmall);
            tituloNeto.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaNeto = new PdfPCell(tituloNeto);
            celdaNeto.setBorder(Rectangle.NO_BORDER);
            celdaNeto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaResultado.addCell(celdaNeto);
       

        // Inicialización de variables para las sumas
        int sumatoriaTotal = 0;
        double sumBruto = 0;
        double sumNeto = 0;
        double sumDcc = 0;

        // Obtener los datos
        Map<String, List<vGraficasHono>> cantidadesPorIdExamen = knowGlobal();

        // Iterar sobre los datos
        for (Map.Entry<String, List<vGraficasHono>> entry : cantidadesPorIdExamen.entrySet()) {
            String especialidad = entry.getKey();
            List<vGraficasHono> statsList = entry.getValue();

            if (statsList == null || statsList.isEmpty()) {
                continue;
            }
            
         boolean esPrimeraFila = true;


            double sumBrutoEspecialidad = 0;
            double sumNetoEspecialidad = 0;
            double sumNetoDeducciones = 0;
            int sumServiciosEspecialidad = 0;
    
            // Iterar sobre las estadísticas de cada especialidad
            for (vGraficasHono stats : statsList) {
                String profesional = stats.getEspecialidad();
                int cantServicios = stats.getRes_cant();
                double brutoTotal = stats.getRes_subtotal();
                double deccTotal = stats.getRes_deducciones();
                double netoTotal = stats.getRes_totalneto();

          
              
            
                String brutoTotalFormateado = String.format("%.2f", brutoTotal);
                String deccTotalFormateado = String.format("%.2f", deccTotal);
                String netoTotalFormateado = String.format("%.2f", netoTotal);
                
                
                // Validar y sumar
                if (cantServicios < 0) continue;
        
                
                
                sumServiciosEspecialidad += cantServicios;
                sumBrutoEspecialidad += brutoTotal;
                sumNetoEspecialidad += netoTotal;
                sumNetoDeducciones+= deccTotal;
                
                
                
                sumatoriaTotal += cantServicios;
                sumBruto += brutoTotal;
                sumNeto += netoTotal;
                sumDcc+= deccTotal;
               
                
               
         
    
      
        PdfPCell nom = new PdfPCell(new Paragraph("\n"+ profesional.toUpperCase(), Letrasmall));
        PdfPCell cantidad = new PdfPCell(new Paragraph("\n"+ String.valueOf(cantServicios), letraSmall));
        PdfPCell bruto = new PdfPCell(new Paragraph("\n"+ brutoTotalFormateado, letraSmall));
        PdfPCell deducciones = new PdfPCell(new Paragraph("\n"+deccTotalFormateado, letraSmall));
        PdfPCell neto = new PdfPCell(new Paragraph("\n"+netoTotalFormateado, letraSmall));

        // Configuración de las celdas
        for (PdfPCell cell : new PdfPCell[]{nom,cantidad, bruto, deducciones, neto}) {
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.enableBorderSide(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablaResultado.addCell(cell);
         
        }
            }
   
            

         PdfPCell resumenEspecialidad = new PdfPCell(new Paragraph(
         "\n\n RESUMEN "  + especialidad.toUpperCase() +"\n"+
     //    "\n*********************************************** \n\n"+
        " | TOTAL SERVICIOS: " + sumServiciosEspecialidad + 
        " | TOTAL BRUTO: " + String.format("%.2f", sumBrutoEspecialidad) + 
        " | TOTAL DEDUCCIONES: " + String.format("%.2f", sumNetoDeducciones) + 
        " | TOTAL NETO: " + String.format("%.2f", sumNetoEspecialidad)+"\n",
        letraMedium
    ));
    resumenEspecialidad.setColspan(5);
    resumenEspecialidad.setBorder(Rectangle.NO_BORDER);
    resumenEspecialidad.setHorizontalAlignment(Element.ALIGN_LEFT);
    resumenEspecialidad.setVerticalAlignment(Element.ALIGN_LEFT);
    tablaResultado.addCell(resumenEspecialidad);

    // Resetear los acumuladores para la siguiente especialidad
    sumBrutoEspecialidad = 0;
    sumNetoEspecialidad = 0;
    sumServiciosEspecialidad = 0;
    
    
    
  
    
          PdfPCell emptyCell = new PdfPCell(new Paragraph("")); 
          emptyCell.setColspan(5); 
          emptyCell.setBorder(Rectangle.NO_BORDER);
          emptyCell.enableBorderSide(Rectangle.BOTTOM);
          emptyCell.setFixedHeight(10); 
          tablaResultado.addCell(emptyCell);
            
            
            
        }

    


                  PdfPTable tablaTotal = new PdfPTable(1); 
                  tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                  tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                  tablaTotal.setWidthPercentage(100); 
                  float[] medidaResultado = {5f};
                  tablaTotal.setWidths(medidaResultado);
                  tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                  String fecha1 = new SimpleDateFormat("dd-MM-yyyy").format(FechaOne.getDate());
                  String fecha2 = new SimpleDateFormat("dd-MM-yyyy").format(FechaTwo.getDate());
                  Paragraph t41columna1 = new Paragraph("\n TOTAL DE SERVICIOS ENCONTRADOS: " + sumatoriaTotal + 
                                                        "\n SUMATORIA BRUTO: " + String.format("%.2f",sumBruto) + 
                                                        "\n SUMATORIA DEDUCCIONES: " + String.format("%.2f",sumDcc) + 
                                                        "\n SUMATORIA PAGO ESPECIALISTAS: " + String.format("%.2f",sumNeto) + 
                                                        "\n ENCONTRADO EN EL RANGO DE FECHAS:  " + fecha1 + " | " + fecha2);
                  t41columna1.getFont().setStyle(Font.NORMAL);
                  t41columna1.getFont().setSize(11);        
                  t41columna1.setFont(Letrasmall);
                  t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
                  PdfPCell celdaTotal = new PdfPCell(t41columna1);
                  celdaTotal.setBorder(Rectangle.NO_BORDER);
                  tablaTotal.addCell(celdaTotal);






        doc.add(tablaResultado);
        doc.add(saltolinea);

  
  try {
    com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\grafica_Honorarios.png");
    
    float pageWidth = doc.getPageSize().getWidth();
    float imageWidth = imgGrafica.getScaledWidth();
    float xPos = (pageWidth - imageWidth) / 2;

    // Establece la posición X de la imagen en el documento
    imgGrafica.setAlignment(Element.ALIGN_CENTER);
    imgGrafica.scaleToFit(500, 500);
    
    // Agrega la imagen al documento
    if(JCespecialidad.getSelectedItem().equals("Todos")){
    doc.add(imgGrafica);
    doc.add(saltolinea);
    }
    
    
    doc.add(tablaTotal);
    
} catch (IOException e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
  
  
  
  

  
  
  
        // Cerrar documento
        doc.close();
        archivo.close();
        if (tablaResultado.getRows().size() <= 1) {
      //  JOptionPane.showMessageDialog(null, "NO HAY REPORTES QUE VISUALIZAR, SOLO SE MUESTRAN AUTORIZADOS O COMPLETADOS", "REPORTES", JOptionPane.ERROR_MESSAGE);
        } else {
      Desktop.getDesktop().open(new File(outputFilePath));
}

    } catch (DocumentException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}










    public Map<String, List<vGraficasHono>> knowGlobal() {
    Map<String, List<vGraficasHono>> cantidadesPorIdExamen = new LinkedHashMap<>();
    String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    

    String sql = "SELECT \n" +
"    CONCAT(p.Nombre, ' ', p.Apellido) AS especialista,\n" +
"    es.especialidad,\n" +
"    COUNT(x.id_resumen) AS total_registros,\n" +
"    SUM(x.res_cant) AS total_cant,\n" +
"    SUM(x.res_subtotal) AS total_subtotal,\n" +
"    SUM(x.res_deducciones) AS total_deducciones,\n" +
"    ROUND(SUM(x.res_subtotal) - SUM(x.res_deducciones), 2) AS total_totalneto\n" +
"FROM \n" +
"    honorario_resumen x\n" +
"LEFT JOIN honorarios a ON a.id_horonario = x.id_honorario\n" +
"LEFT JOIN table_personal p ON p.IdPersonal = a.id_especialista\n" +
"INNER JOIN table_especialidad es ON es.id_especialidad = a.id_especialidad\n" +
"WHERE \n" +
"    es.especialidad LIKE ?\n" +
"    AND a.Ho_estado <> 104\n" +
"    AND a.Ho_estado <> 106\n" +
"    AND a.Ho_fechaOne BETWEEN ? AND ?\n" +
"GROUP BY \n" +
"    especialista,\n" +
"    es.especialidad\n" +
"ORDER BY \n" +
"    total_subtotal DESC;";
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Obtener la especialidad seleccionada
        String especialidadSeleccionada = JCespecialidad.getSelectedItem().toString();
        
        // Verificar si se seleccionó "Todos", y usar un comodín '%'
        if ("Todos".equals(especialidadSeleccionada)) {
            ps.setString(1, "%");  // Comodín '%' para todas las especialidades
        } else {
            ps.setString(1, especialidadSeleccionada);  // Filtrar por especialidad seleccionada
        }
        
        // Establecer las fechas como parámetros en la consulta
        ps.setString(2, fecha1);  
        ps.setString(3, fecha2);  

        try (ResultSet rs = ps.executeQuery()) {
            // Recorrer los resultados de la consulta
            while (rs.next()) {
                String especialista = rs.getString("especialista");
                String especialidad = rs.getString("especialidad");
                int cantServicios = rs.getInt("total_cant"); 
                
                // Obtener los valores y verificar si son nulos
                double brutoTotal = rs.getDouble("total_subtotal");
                if (rs.wasNull()) {
                    brutoTotal = 0.00; 
                }

                double deccTotal = rs.getDouble("total_deducciones");
                if (rs.wasNull()) {
                    deccTotal = 0.00;  
                }

                double neto = rs.getDouble("total_totalneto");
                if (rs.wasNull()) {
                    neto = 0.00;  
                }

          
                cantidadesPorIdExamen.computeIfAbsent(especialidad, k -> new ArrayList<>())
                    .add(new vGraficasHono(null, 0, 0, 0, especialista, cantServicios, brutoTotal, deccTotal, neto));
            }
        
        
        
        
        }
        
        
        
           // Ahora ordenar las listas dentro del Map por el total_subtotal (de mayor a menor)
        for (Map.Entry<String, List<vGraficasHono>> entry : cantidadesPorIdExamen.entrySet()) {
            List<vGraficasHono> lista = entry.getValue();
            lista.sort((h1, h2) -> Double.compare(h2.getRes_subtotal(), h1.getRes_subtotal())); // Orden descendente por total_subtotal
        }

        // Finalmente ordenar el Map por el total_subtotal de las especialidades (de mayor a menor)
        cantidadesPorIdExamen = cantidadesPorIdExamen.entrySet()
            .stream()
            .sorted((entry1, entry2) -> Double.compare(
                entry2.getValue().stream().mapToDouble(vGraficasHono::getRes_subtotal).sum(),
                entry1.getValue().stream().mapToDouble(vGraficasHono::getRes_subtotal).sum()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                      (e1, e2) -> e1, LinkedHashMap::new));
        
        
    } catch (SQLException e) {
        // Manejo de excepciones con mensaje más detallado
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + e.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
    }

    return cantidadesPorIdExamen;
}



    
    
      public void graficarGlobal() {
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        String rutaDestino = "C:\\Fundaginebra\\dist\\grafica_Honorarios.png";
        

        String sql = "SELECT \n" +
                "    es.especialidad,\n" +
                "    COUNT(x.id_resumen) AS total_registros,  \n" +
                "    SUM(x.res_cant) AS total_cant,          \n" +
                "    SUM(x.res_subtotal) AS total_subtotal, \n" +
                "    SUM(x.res_deducciones) AS total_deducciones,\n" +
                "    SUM(x.res_totalneto) AS total_totalneto \n" +
                "FROM \n" +
                "    honorario_resumen x\n" +
                "    LEFT JOIN honorarios a ON a.id_horonario = x.id_honorario\n" +
                "    LEFT JOIN table_personal p ON p.IdPersonal = a.id_especialista\n" +
                "    INNER JOIN table_especialidad es ON es.id_especialidad = a.id_especialidad\n" +
                "WHERE \n" +
                "    ( ? = 'Todos' OR es.especialidad = ? ) \n" + 
                "    AND a.Ho_estado <> 104 \n" +
                "    AND a.Ho_estado <> 106  \n" +
                "    AND a.Ho_fechaOne BETWEEN ? AND ? \n" +
                "GROUP BY \n" +
                "    es.especialidad \n" +
                "ORDER BY \n" +
                "    total_subtotal DESC;";
        
        try (Connection con = new EnlaceBd().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            String especialidadSeleccionada = JCespecialidad.getSelectedItem().toString();
            if (especialidadSeleccionada.equals("Todos")) {
                ps.setString(1, "Todos");
                ps.setString(2, "");
            } else {
                ps.setString(1, especialidadSeleccionada);
                ps.setString(2, especialidadSeleccionada);
            }

            // Asignamos las fechas
            ps.setString(3, fecha);    // Fecha de inicio
            ps.setString(4, fecha2);   // Fecha de fin

            // Ejecutamos la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "NO HAY DATOS PARA MOSTRAR, SOLO SE TOMA LOS INFORMES COMPLETADOS", "HONORARIOS", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                while (rs.next()) {
                    String especialidad = rs.getString("especialidad");
                    double totalSubtotal = rs.getDouble("total_subtotal");
                    dataset.addValue(totalSubtotal, "Ingresos Brutos", especialidad);
                }

                // Crear gráfico de barras 3D
                JFreeChart chart = create3DBarChart(dataset, "Resumen por Especialidad", "", "Montos");

                // Guardar como imagen PNG
                ChartUtilities.saveChartAsPNG(new File(rutaDestino), chart, 1000, 600);

                // Mostrar el gráfico en un JFrame
                f = new ChartFrame("Gráfico Ingresos", chart);
                f.setSize(1000, 600);
              //  f.setVisible(true);
       
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // Método para crear un gráfico de barras 3D
 private JFreeChart create3DBarChart(DefaultCategoryDataset dataset, String Titulo, String ejex, String ejey) {
    // Crear gráfico de barras en 3D
    JFreeChart chart = ChartFactory.createBarChart3D(
            Titulo, // Título del gráfico
            ejex, // Eje X
            ejey, // Eje Y
            dataset, // Dataset
            PlotOrientation.VERTICAL, // Orientación
            true, // Incluir leyenda
            true, // Herramientas interactivas
            false // Desactivar URL
    );

    CategoryPlot plot = (CategoryPlot) chart.getPlot();

    // Configurar renderizador 3D
    BarRenderer3D renderer = new BarRenderer3D();
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    renderer.setBaseItemLabelsVisible(true);
    Color LightBlue = new Color(100, 150, 200);
    // Cambiar el color de las barras
    Color barraColor = LightBlue; // El color que quieras (en este caso, azul)
    renderer.setSeriesPaint(0, barraColor); 

    
    plot.setRenderer(renderer);

    // Configuración del eje X (categorías)
    plot.setDomainGridlinesVisible(true);
    plot.setRangeGridlinesVisible(true);
    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

    // Configuración del eje Y
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setAutoRangeIncludesZero(true);

    return chart;
}
    
    
    
    
    
    
    
    
    
 
 
 
    
    
    
    
    
    
    public void email2()
    {
    String remitente ="fundaginebralab@gmail.com";
    String clave= "sqjaqrvlxglnrtol";
    String destino=correo;
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
    mensaje.setSubject("Honorario Profesional | Fundación Convenio Ginebra I |");
  
    BodyPart parteTexto= new MimeBodyPart();
    parteTexto.setContent("Saludos cordiales, se adjunta el archivo pdf. <br> ¡Somos humanidad, paz y salud! <br><br> NOTA: Este es un mensaje automatizado, no debe ser respondido.", "text/html");
    
     
     File file = new File("C://Fundaginebra//dist//Honorario.pdf");
     BodyPart parteArchivo= new MimeBodyPart();
     parteArchivo.setDataHandler(new DataHandler(new FileDataSource(file)));
     parteArchivo.setFileName("Honorario.pdf");
   
    
    MimeMultipart todasLasPartes = new MimeMultipart();
    todasLasPartes.addBodyPart(parteTexto);
    todasLasPartes.addBodyPart(parteArchivo);
    mensaje.setContent(todasLasPartes);
    
    
    Transport transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", remitente, clave);
    transport.sendMessage(mensaje, mensaje.getAllRecipients());
    transport.close();
    JOptionPane.showMessageDialog(null,"Correo Enviado a: "+ correo );
    } 
    catch(Exception e)
    {System.out.println(e);
      JOptionPane.showMessageDialog(null,"ERROR AL ENVIAR EL CORREO", "ERROR EN CORREO", JOptionPane.ERROR_MESSAGE);}
    }
    
    
    
    
    
    
    
    
         
  public void auditoriaMethod(String accion){
            
   int idusuario=Tempo.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(MP.FechaAdmin.getDate());
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
          
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
 finally {
       cn.closeResources(rs, ps, con);
    }
     
    }
    
    
    
    
    
        
    // Método para autorizar las filas seleccionadas
    private void autorizarFilasSeleccionadas() {
       
        
        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0;
        int columnaPaciente = 2;
      
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            String nombreDoctor = modelo.getValueAt(fila, columnaPaciente).toString(); 
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.autorizarHonorario(107,    id );
            String accion= "HORA: "+MP.Time.getText()+ " Autorizo el reporte honorario "+id+" del especialista: "+ nombreDoctor;  
            auditoriaMethod(accion);
            //AuditoriaAutorizar(nombrepaciente, id);
            // Actualiza la fila en la tabla (por ejemplo, cambia el estado a "Autorizado")
          
        }
    }
    
    
    
    
        
      // Método para autorizar las filas seleccionadas
    private void completarFilasSeleccionadas() {
       
        
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
            setState.autorizarHonorario(103,    id );
            //AuditoriaAutorizar(nombrepaciente, id);
            // Actualiza la fila en la tabla (por ejemplo, cambia el estado a "Autorizado")
          
        }
    }
    
    
    
    
     private void cancelarFilasSeleccionadas() {

        DefaultTableModel modelo = (DefaultTableModel) Jtabla.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla
        int columnaPaciente = 2; //  
        // Obtén las filas seleccionadas
        int[] filasSeleccionadas = Jtabla.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            String nombreDoctor = modelo.getValueAt(fila, columnaPaciente).toString(); 
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.autorizarHonorario(104,    id );
            String accion= "HORA: "+MP.Time.getText()+ " Cancelo el reporte honorario "+id+" del especialista: "+ nombreDoctor;   
            auditoriaMethod(accion);
        }
    }
    
    
    
    
    
    
private boolean nivelAux() {
     String nivel = Tempo.getNivel();
     String especialidad= Tempo.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
     
    return nivelUsuario.equals("Aux. Administrativo") && estados.equals("Espera");
}
    

private boolean nivelAuxC() {
     String nivel = Tempo.getNivel();
     String especialidad= Tempo.getEspecialidad();
     String nivelUsuario=nivel+" "+especialidad;
     
    return nivelUsuario.equals("Aux. Administrativo") && estados.equals("Cancelado");
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
        
 
        
        dt.addColumn("Código");
        dt.addColumn("Especialidad");
        dt.addColumn("Especialista");
        dt.addColumn("Usuario Encargado");
        dt.addColumn("Reporte");
        dt.addColumn("Pago");
        dt.addColumn("Fecha Inicio");
        dt.addColumn("Fecha Cierre");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");
        dt.addColumn("Estado");
        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null ) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

       
        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.Listar_Honorarios(fecha, fecha2);
        limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
   
    
                fila[0] = vo.getId_hon();
                fila[1] = vo.getHon_especialista();
                fila[2] = vo.getHon_especialidad();
                fila[3] = vo.getHon_encargado();
                fila[4] = new JButton(icono);
                fila[5] = vo.getNetoPagar();
                fila[6] = vo.getHon_fecha1();
                fila[7] = vo.getHon_fecha2();
                fila[8] = vo.getHo_telefono();   
                fila[9] = vo.getHo_correo();    
                fila[10] = vo.getHo_estado();
             
               
                    
                
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
            acomodarceldas();
        }
         } catch (Exception e) { System.out.println(e + "visualizarpdf") ;
        }
  
    }
    
    
    
  
    
       public void checkFromHonor() {
        
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Código");
        dt.addColumn("Especialidad");
        dt.addColumn("Especialista");
        dt.addColumn("Usuario Encargado");
        dt.addColumn("Reporte");
        dt.addColumn("Pago");
        dt.addColumn("Fecha Inicio");
        dt.addColumn("Fecha Cierre");
        dt.addColumn("Telefono");
        dt.addColumn("Correo");
        dt.addColumn("Estado");
        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null ) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

       
        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = search.Listar_Honorarios(fecha, fecha2);
        limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[12];
                vo = list.get(i);
   
    
                fila[0] = vo.getId_hon();
                fila[1] = vo.getHon_especialista();
                fila[2] = vo.getHon_especialidad();
                fila[3] = vo.getHon_encargado();
                fila[4] = new JButton(icono);
                fila[5] = vo.getNetoPagar();
                fila[6] = vo.getHon_fecha1();
                fila[7] = vo.getHon_fecha2();
                fila[8] = vo.getHo_telefono();   
                fila[9] = vo.getHo_correo();    
                fila[10] = vo.getHo_estado();
             
               
                    
                
                dt.addRow(fila);
            }
           Jtabla.setModel(dt);
           Jtabla.setRowHeight(32);
            acomodarceldas();
        }
         } catch (Exception e) { System.out.println(e + "visualizarpdf") ;
        }
  
    }
      
      
    
     
     
    public void searchBetween(Function<String[], ArrayList<PdfVO>> searchFunction) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = dateFormat.format(FechaOne.getDate());
    String fecha2 = dateFormat.format(FechaTwo.getDate());
    
    // Crear un array con los parámetros para pasar al Function
    String[] params = {fecha, fecha2,JCespecialidad.getSelectedItem().toString(),JEstados.getSelectedItem().toString() };

    try {
        // Usar la función pasada como parámetro para obtener la lista
        ArrayList<PdfVO> list = searchFunction.apply(params);
        
        // Configuración de la tabla y procesamiento de datos...
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        
        

        // Definir las columnas una sola vez
        String[] columnNames = {
            "Código", "Especialidad", "Especialista", "Usuario Encargado", 
            "Reporte", "Pago",  "Fecha Inicio", "Fecha Cierre", "Telefono", "Correo", "Estado"
        };
        for (String column : columnNames) {
            dt.addColumn(column);
        }

        // Cargar el icono una sola vez
        ImageIcon icono = get_Image("/Logos/32pdf.png") != null ? 
                          new ImageIcon(get_Image("/Logos/32pdf.png")) : null;

        limpiarTablaEstudios();


        
        if (!list.isEmpty()) {
            for (PdfVO vo : list) {
                Object[] fila = {
                    vo.getId_hon(), 
                    vo.getHon_especialidad(),
                    vo.getHon_especialista(),
                    vo.getHon_encargado(),
                    new JButton(icono),
                    vo.getNetoPagar(),
                    vo.getHon_fecha1(),
                    vo.getHon_fecha2(),
                    vo.getHo_telefono(),
                    vo.getHo_correo(),
                    vo.getHo_estado()
                };
                dt.addRow(fila);
            }

            // Asignar el modelo a la tabla y ajustar la altura
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
    
        }

    } catch (Exception e) {
        System.err.println("Error en searchByName: " + e.getMessage());
    }
}
     
     
     
      
      
      
      
      
      
      
      
      
    
          
    public void acomodarceldas()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)Jtabla.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        


        
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Código")).setPreferredWidth(10);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Especialidad")).setPreferredWidth(50);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Especialista")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario Encargado")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Reporte")).setPreferredWidth(50);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Pago")).setPreferredWidth(50);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha Inicio")).setPreferredWidth(30);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha Cierre")).setPreferredWidth(30);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Correo")).setPreferredWidth(120);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(30);
        
        
        
         
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Código")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Especialidad")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Especialista")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario Encargado")).setCellRenderer(Alinear);
     // Jtabla.getColumnModel().getColumn(Tabla.findColumn("Reporte")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Pago")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha Inicio")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha Cierre")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Correo")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);
       
    
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
    
    
               void limpiarTablaEstudios() {
        DefaultTableModel tb = (DefaultTableModel) Jtabla.getModel();
        int a = Jtabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);

        }
    }
    
    
    
    
      String  empresa, rif, ubicacion, telefonos, piepagina;
    public void informacionPdf() {
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

    } catch (SQLException ex) {

        System.out.println("Error en la consulta informacion: " + ex.getMessage());
    } catch (Exception ex) {
  
        System.out.println("Error inesperado en informacion: " + ex.getMessage());
    }
}
    
    
    
    
    
    
    
    
    
   public void pdfbyDeducciones() {
    
    try {
        DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        String pdfName = "ReporteHonorarios_Deduccion_" + fth.format(fechaactual);
        String outputFilePath = "C://Fundaginebra//Reportes//" + pdfName + ".pdf";
        
        // Inicialización de la fuente
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
        Font letraSmall = new Font(BF, 8);
        Font letraMedium = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        Paragraph saltolinea = new Paragraph();
        saltolinea.add("\n");
        
        // Creación de archivo y documento
        FileOutputStream archivo = new FileOutputStream(new File(outputFilePath));
        Document doc = new Document();
        doc.setMargins(36, 36, 130, 130);
        PdfWriter writer = PdfWriter.getInstance(doc, archivo);
        Letrasmall = new Font(BF, 8);
       
       
        // Configuración del header y footer
        writer.setPageEvent(new HeaderFooterPageEvent());
        writer.setPageEvent(eventHelper);

            // Abrir el documento
            doc.open();

            
            
        PdfPTable tablaResultado = crearTablaResultado();
        
        // Inicialización de variables para las sumas
        double sumatoriaTotal = 0;
    

        // Obtener los datos
        Map<String, List<vGraficaDc>> totalDeducciones =  knowDcc();

        // Iterar sobre los datos
        for (Map.Entry<String, List<vGraficaDc>> entry : totalDeducciones.entrySet()) {
            String especialidad = entry.getKey();
            List<vGraficaDc> statsList = entry.getValue();
  

            if (statsList == null || statsList.isEmpty()) {
                continue;
            }
            
            boolean esPrimeraFila = true;

            double sumFlat = 0;
            double sumIsrl=0;
            double sumPersonal=0;
            double sumAlmacen=0;
            double sumAnticipo=0;
            double sumOtras=0;
           
        
    
            
             
            
            // Iterar sobre las estadísticas de cada especialidad
            for (vGraficaDc stats : statsList) {
                String profesional = stats.getEspecialita();
                double Dec_flat = stats.getDec_flat();
                double Dec_ISRL = stats.getDec_ISRL();
                double Dec_personal = stats.getDec_personal();
                double Dec_almacen = stats.getDec_almacen();
                double Dec_nomina= stats.getDec_nomina();
                double Dec_incineradora = stats.getDec_incineradora();
                double Dec_anticipo = stats.getDec_anticipo();
                double Dec_Bioseguridad= stats.getDec_Bioseguridad();
                double Fundacion = stats.getFundacion();
                double Dec_otras = stats.getDec_otras();
            
                
                sumFlat += Dec_flat;
                sumIsrl+=Dec_ISRL;
                sumPersonal+=Dec_personal;
                sumAlmacen+=Dec_almacen;
                sumAnticipo+=Dec_anticipo;
                sumOtras+=Dec_otras;
                
     

                        
               String Dec_flatFormateado = String.format("%.2f", Dec_flat);
               String Dec_ISRLFormateado = String.format("%.2f", Dec_ISRL);
               String Dec_personalFormateado = String.format("%.2f", Dec_personal);
               String Dec_almacenFormateado = String.format("%.2f", Dec_almacen);
               String Dec_nominaFormateado = String.format("%.2f", Dec_nomina);
               String Dec_incineradoraFormateado = String.format("%.2f", Dec_incineradora);
               String Dec_anticipoFormateado = String.format("%.2f", Dec_anticipo);
               String Dec_BioseguridadFormateado = String.format("%.2f", Dec_Bioseguridad);
               String FundacionFormateado = String.format("%.2f", Fundacion);
               String Dec_otrasFormateado = String.format("%.2f", Dec_otras);
                

                
                

               
                
               
         
    
      
        PdfPCell nom = new PdfPCell(new Paragraph("\n"+ profesional.toUpperCase(), Letrasmall));
        PdfPCell flat = new PdfPCell(new Paragraph("\n"+ String.valueOf(Dec_flatFormateado), letraSmall));
        PdfPCell isrl = new PdfPCell(new Paragraph("\n"+ Dec_ISRLFormateado, letraSmall));
        PdfPCell personal = new PdfPCell(new Paragraph("\n"+Dec_personalFormateado, letraSmall));
        PdfPCell almacen = new PdfPCell(new Paragraph("\n"+Dec_almacenFormateado, letraSmall));
        PdfPCell anticipo = new PdfPCell(new Paragraph("\n"+Dec_anticipoFormateado, letraSmall));
        PdfPCell otras = new PdfPCell(new Paragraph("\n"+Dec_otrasFormateado, letraSmall));

        // Configuración de las celdas
        for (PdfPCell cell : new PdfPCell[]{nom,flat, isrl, personal, almacen, anticipo, otras}) {
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.enableBorderSide(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablaResultado.addCell(cell);
         
        }
            }
   
         

         PdfPCell resumenEspecialidad = new PdfPCell(new Paragraph(
         "\n\nRESUMEN "  + especialidad.toUpperCase() +"\n"+
     //    "\n*********************************************** \n\n"+
        "TOTAL FLAT: " +  String.format("%.2f", sumFlat) + 
        " | TOTAL ISRL: "+String.format("%.2f",sumIsrl)+
        " | TOTAL PERSONAL: "+String.format("%.2f",sumPersonal)+
                 
        " | TOTAL ALMACEN: "+String.format("%.2f",sumAlmacen)+
        " | TOTAL ANTICIPO: "+String.format("%.2f",sumAnticipo)+
        " | TOTAL OTRAS: "+String.format("%.2f",sumOtras)+
  
        " ______________________________________________________________________________________________________________",
                 
        letraMedium
    ));
    resumenEspecialidad.setColspan(7);
    resumenEspecialidad.setBorder(Rectangle.NO_BORDER);
    resumenEspecialidad.setHorizontalAlignment(Element.ALIGN_LEFT);
    resumenEspecialidad.setVerticalAlignment(Element.ALIGN_LEFT);
    
    tablaResultado.setWidthPercentage(100); 
    tablaResultado.addCell(resumenEspecialidad);

    // Resetear los acumuladores para la siguiente especialidad
   // sumBrutoEspecialidad = 0;
   // sumNetoEspecialidad = 0;
   // sumServiciosEspecialidad = 0;
    
    
    
  
    
PdfPCell emptyCell = new PdfPCell(new Paragraph("")); 
emptyCell.setColspan(7); // Asegúrate de que esta cantidad coincida con las columnas de la tabla
emptyCell.setBorder(Rectangle.NO_BORDER); 
emptyCell.setFixedHeight(5f); // Añadir altura para asegurarte de que se ve la línea
//emptyCell.enableBorderSide(Rectangle.BOTTOM);  // Habilitar solo el borde inferior
tablaResultado.addCell(emptyCell);
            
    sumatoriaTotal += sumFlat + sumIsrl + sumPersonal + sumAlmacen + sumAnticipo + sumOtras;         
            
        }

    


                  PdfPTable tablaTotal = new PdfPTable(1); 
                  tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                  tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                  tablaTotal.setWidthPercentage(100); 
                  float[] medidaResultado = {5f};
                  tablaTotal.setWidths(medidaResultado);
                  tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                  String fecha1 = new SimpleDateFormat("dd-MM-yyyy").format(FechaOne.getDate());
                  String fecha2 = new SimpleDateFormat("dd-MM-yyyy").format(FechaTwo.getDate());
                  Paragraph t41columna1 = new Paragraph("\n SUMATORIA TOTAL: " + String.format("%.2f",sumatoriaTotal) + 
                                                        "\n ENCONTRADO EN EL RANGO DE FECHAS:  " + fecha1 + " | " + fecha2);
                  t41columna1.getFont().setStyle(Font.NORMAL);
                  t41columna1.getFont().setSize(11);        
                  t41columna1.setFont(Letrasmall);
                  t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
                  PdfPCell celdaTotal = new PdfPCell(t41columna1);
                  celdaTotal.setBorder(Rectangle.NO_BORDER);
                  tablaTotal.addCell(celdaTotal);






        doc.add(tablaResultado);
        doc.add(saltolinea);
  

  
  try {
    com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\grafica_Honorarios.png");
    
    float pageWidth = doc.getPageSize().getWidth();
    float imageWidth = imgGrafica.getScaledWidth();
    float xPos = (pageWidth - imageWidth) / 2;

    // Establece la posición X de la imagen en el documento
    imgGrafica.setAlignment(Element.ALIGN_CENTER);
    imgGrafica.scaleToFit(500, 500);
    
    // Agrega la imagen al documento
   
    doc.add(imgGrafica);
    doc.add(tablaTotal);
    
    

    
} catch (IOException e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
  
  

  
  
        // Cerrar documento
        doc.close();
        archivo.close();
        if (tablaResultado.getRows().size() <= 1) {
      //  JOptionPane.showMessageDialog(null, "NO HAY REPORTES QUE VISUALIZAR, SOLO SE MUESTRAN AUTORIZADOS O COMPLETADOS", "REPORTES", JOptionPane.ERROR_MESSAGE);
        } else {
         Desktop.getDesktop().open(new File(outputFilePath));}


    } catch (DocumentException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
   
   
public PdfPTable crearTablaResultado() {
    // Crear la tabla con 7 columnas
    PdfPTable tablaResultado = new PdfPTable(7);
    tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
    tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
    tablaResultado.setWidthPercentage(100);


    float[] medidaCeldas3 = {6f, 3f, 3f, 3f, 3f, 3f, 3f}; 

    try {
        // Crear la fuente base
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
   
        // Asignar las medidas de las celdas
        tablaResultado.setWidths(medidaCeldas3);

        // Crear la fuente para la tabla con la base definida
        Font letraSmall = new Font(BF, 10);
        letraSmall.setStyle(Font.BOLD);
        // Establecer el alineamiento de la tabla y las celdas
        tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Agregar las celdas con los títulos (sin valores todavía)
        tablaResultado.addCell(crearCeldaTitulo("ESPECIALISTA", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("FLAT", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ISRL", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("PERSONAL", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ALMACEN", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ANTICIPO", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("OTRAS", letraSmall));

    } catch (DocumentException | IOException e) {
        e.printStackTrace();  
    }


    return tablaResultado;
}
   
   
private PdfPCell crearCeldaTitulo(String texto, Font letraSmall) {

    Paragraph titulo = new Paragraph(texto, letraSmall);  
    titulo.setAlignment(Element.ALIGN_CENTER);
    PdfPCell celda = new PdfPCell(titulo);
    celda.setBorder(Rectangle.NO_BORDER);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    return celda;
}

private PdfPCell crearCeldaValor(String valor) {
    Paragraph valorParrafo = new Paragraph(valor);
    valorParrafo.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8));
    valorParrafo.setAlignment(Element.ALIGN_CENTER);
    PdfPCell celda = new PdfPCell(valorParrafo);
    celda.setBorder(Rectangle.NO_BORDER);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    return celda;
}
   
   
   
   
   
   
   
    
    
  
  
   
   
   
   
    
    

    public Map<String, List<vGraficaDc>> knowDcc() {
    Map<String, List<vGraficaDc>> totalDeducciones = new LinkedHashMap<>();
    String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    

    String sql = "SELECT \n" +
"    a.id_especialista, \n" +
"    CONCAT(p.Nombre, ' ', p.Apellido) AS especialista, \n" +
"    es.especialidad,\n" +
"    ROUND(SUM(x.Dec_flat), 2) AS total_Dec_flat,\n" +
"    ROUND(SUM(x.Dec_ISRL), 2) AS total_Dec_ISRL,\n" +
"    ROUND(SUM(x.Dec_personal), 2) AS total_Dec_personal,\n" +
"    ROUND(SUM(x.Dec_almacen), 2) AS total_Dec_almacen,\n" +
"    ROUND(SUM(x.Dec_nomina), 2) AS total_Dec_nomina,\n" +
"    ROUND(SUM(x.Dec_incineradora), 2) AS total_Dec_incineradora,\n" +
"    ROUND(SUM(x.Dec_anticipo), 2) AS total_Dec_anticipo,\n" +
"    ROUND(SUM(x.Dec_Bioseguridad), 2) AS total_Dec_Bioseguridad,\n" +
"    ROUND(SUM(x.Fundacion), 2) AS total_Fundacion,\n" +
"    ROUND(SUM(x.Dec_otras), 2) AS total_Dec_otras\n" +
"\n" +
"FROM \n" +
"    honorario_deducciones x\n" +
"LEFT JOIN \n" +
"    honorarios a ON a.id_horonario = x.id_honorario\n" +
"LEFT JOIN \n" +
"    table_personal p ON p.IdPersonal = a.id_especialista\n" +
"INNER JOIN \n" +
"table_especialidad es ON es.id_especialidad = a.id_especialidad\n" +
"WHERE \n" +
"es.especialidad like ? \n" +
"AND a.Ho_estado NOT IN (104, 106)\n" +
"AND a.Ho_fechaOne BETWEEN ? AND ?  \n";

    
    
        // Si se seleccionó "Todos", excluir "Bioanalista"
    if ("Todos".equals(JCespecialidad.getSelectedItem().toString())) {
        sql += "    AND es.especialidad != 'Bioanalista' ";
    }

    sql += "GROUP BY \n" +
"a.id_especialista, \n" +
"CONCAT(p.Nombre, ' ', p.Apellido), \n" +
"es.especialidad\n" +
"ORDER BY \n" +
"    a.id_especialista";;
    
    
    
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
    
        String especialidadSeleccionada = JCespecialidad.getSelectedItem().toString();
        
      
        if ("Todos".equals(especialidadSeleccionada)) {
            ps.setString(1, "%");  
        } else {
            ps.setString(1, especialidadSeleccionada);  
        }
        

        ps.setString(2, fecha1);  
        ps.setString(3, fecha2);  

        

        
        
        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String especialidad = rs.getString("especialidad");
                String especialista = rs.getString("especialista");
                
                
                double totalFlat = rs.getDouble("total_Dec_flat");
                if (rs.wasNull()) {
                    totalFlat = 0.00; 
                }


                double totalIstl = rs.getDouble("total_Dec_ISRL");
                if (rs.wasNull()) {
                    totalIstl = 0.00; 
                }

                double totalPersonal = rs.getDouble("total_Dec_personal");
                if (rs.wasNull()) {
                    totalPersonal = 0.00;  
                }

                double totalAlmacen = rs.getDouble("total_Dec_almacen");
                if (rs.wasNull()) {
                    totalAlmacen = 0.00;  
                }

                double totalNomina = rs.getDouble("total_Dec_nomina");
                if (rs.wasNull()) {
                    totalNomina = 0.00;  
                }

                   double totalIncineradora = rs.getDouble("total_Dec_incineradora");
                if (rs.wasNull()) {
                    totalIncineradora = 0.00;  
                }

                   double totalAnticipo = rs.getDouble("total_Dec_anticipo");
                if (rs.wasNull()) {
                    totalAnticipo= 0.00;  
                }

                   double totalBioseguridad= rs.getDouble("total_Dec_Bioseguridad");
                if (rs.wasNull()) {
                    totalBioseguridad= 0.00;  
                }

                double totalFundacion= rs.getDouble("total_Fundacion");
                if (rs.wasNull()) {
                    totalFundacion= 0.00;  
                }

                double totalOtras= rs.getDouble("total_Dec_otras");
                if (rs.wasNull()) {
                     totalOtras= 0.00;  
                }
                
          
                totalDeducciones.computeIfAbsent(especialidad, k -> new ArrayList<>())
                    .add(new vGraficaDc(especialista, totalFlat, totalIstl, totalPersonal, totalAlmacen,totalNomina, totalIncineradora, totalAnticipo, totalBioseguridad, totalFundacion,totalOtras));
            }
        
        
        
        
        }
        
        
        /*
           // Ahora ordenar las listas dentro del Map por el total_subtotal (de mayor a menor)
        for (Map.Entry<String, List< vGraficaDc>> entry : cantidadesPorIdExamen.entrySet()) {
            List< vGraficaDc> lista = entry.getValue();
            lista.sort((h1, h2) -> Double.compare(h2.getRes_subtotal(), h1.getRes_subtotal())); // Orden descendente por total_subtotal
        }

        // Finalmente ordenar el Map por el total_subtotal de las especialidades (de mayor a menor)
        cantidadesPorIdExamen = cantidadesPorIdExamen.entrySet()
            .stream()
            .sorted((entry1, entry2) -> Double.compare(
                entry2.getValue().stream().mapToDouble(vGraficasHono::getRes_subtotal).sum(),
                entry1.getValue().stream().mapToDouble(vGraficasHono::getRes_subtotal).sum()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                      (e1, e2) -> e1, LinkedHashMap::new));
        */
        
    } catch (SQLException e) {
        // Manejo de excepciones con mensaje más detallado
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + e.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
    }

    return totalDeducciones;
}

    
    
  public void graficarDeduccion() {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    String rutaDestino = "C:\\Fundaginebra\\dist\\grafica_Honorarios.png";

    // Consulta adaptada para obtener las deducciones específicas
    String sql = "SELECT " +
                 "    es.especialidad, " +
                 "    ROUND(SUM(COALESCE(x.Dec_flat, 0)), 2) AS total_Dec_flat, " +
                 "    ROUND(SUM(COALESCE(x.Dec_ISRL, 0)), 2) AS total_Dec_ISRL, " +
                 "    ROUND(SUM(COALESCE(x.Dec_personal, 0)), 2) AS total_Dec_personal, " +
                 "    ROUND(SUM(COALESCE(x.Dec_almacen, 0)), 2) AS total_Dec_almacen, " +
                 "    ROUND(SUM(COALESCE(x.Dec_nomina, 0)), 2) AS total_Dec_nomina, " +
                 "    ROUND(SUM(COALESCE(x.Dec_incineradora, 0)), 2) AS total_Dec_incineradora, " +
                 "    ROUND(SUM(COALESCE(x.Dec_anticipo, 0)), 2) AS total_Dec_anticipo, " +
                 "    ROUND(SUM(COALESCE(x.Dec_Bioseguridad, 0)), 2) AS total_Dec_Bioseguridad, " +
                 "    ROUND(SUM(COALESCE(x.Fundacion, 0)), 2) AS total_Fundacion, " +
                 "    ROUND(SUM(COALESCE(x.Dec_otras, 0)), 2) AS total_Dec_otras, " +
                 "    ROUND( " +
                 "        SUM(COALESCE(x.Dec_flat, 0)) + " +
                 "        SUM(COALESCE(x.Dec_ISRL, 0)) + " +
                 "        SUM(COALESCE(x.Dec_personal, 0)) + " +
                 "        SUM(COALESCE(x.Dec_almacen, 0)) + " +
                 "        SUM(COALESCE(x.Dec_nomina, 0)) + " +
                 "        SUM(COALESCE(x.Dec_incineradora, 0)) + " +
                 "        SUM(COALESCE(x.Dec_anticipo, 0)) + " +
                 "        SUM(COALESCE(x.Dec_Bioseguridad, 0)) + " +
             //    "        SUM(COALESCE(x.Fundacion, 0)) + " +
                 "        SUM(COALESCE(x.Dec_otras, 0)), " +
                 "    2) AS TOTAL " +
                 "FROM " +
                 "    honorario_deducciones x " +
                 "LEFT JOIN " +
                 "    honorarios a ON a.id_horonario = x.id_honorario " +
                 "INNER JOIN " +
                 "    table_especialidad es ON es.id_especialidad = a.id_especialidad " +
                 "WHERE " +
                 " (es.especialidad LIKE ? OR ? = 'Todos') " +  
            
           //      "    AND es.especialidad != 'Bioanalista' " +
             
                 "    AND a.Ho_estado NOT IN (104, 106) " +
                 "    AND a.Ho_fechaOne BETWEEN ? AND ? ";
    
    // Si se seleccionó "Todos", excluir "Bioanalista"
    if ("Todos".equals(JCespecialidad.getSelectedItem().toString())) {
        sql += "    AND es.especialidad != 'Bioanalista' ";
    }

    sql += "GROUP BY " +
           "    es.especialidad " +
           "ORDER BY " +
           "    TOTAL DESC";
    

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        String especialidadSeleccionada = JCespecialidad.getSelectedItem().toString();
        
        if (especialidadSeleccionada.equals("Todos")) {
            ps.setString(1, "%");  // Esto permite que busque todas las especialidades
            ps.setString(2, "Todos");
        } else {
            ps.setString(1, "%" + especialidadSeleccionada + "%");  
            ps.setString(2, especialidadSeleccionada);  
        }

        ps.setString(3, fecha);    // Fecha de inicio
        ps.setString(4, fecha2);   // Fecha de fin

        // Ejecutamos la consulta
        try (ResultSet rs = ps.executeQuery()) {
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "NO HAY DATOS PARA MOSTRAR, SOLO SE TOMA LOS INFORMES COMPLETADOS", "HONORARIOS", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crear el dataset para el gráfico de deducciones
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String especialidad = rs.getString("especialidad");

                // Obtener los valores de las deducciones
                double totalDecFlat = rs.getDouble("total_Dec_flat");
                double totalDecIsrl = rs.getDouble("total_Dec_ISRL");
                double totalDecPersonal = rs.getDouble("total_Dec_personal");
                double totalDecAlmacen = rs.getDouble("total_Dec_almacen");
                double totalDecNomina = rs.getDouble("total_Dec_nomina");
                double totalDecIncineradora = rs.getDouble("total_Dec_incineradora");
                double totalDecAnticipo = rs.getDouble("total_Dec_anticipo");
                double totalDecBioseguridad = rs.getDouble("total_Dec_Bioseguridad");
              //  double totalFundacion = rs.getDouble("total_Fundacion");
                double totalDecOtras = rs.getDouble("total_Dec_otras");
                double total = rs.getDouble("TOTAL");

                // Si seleccionamos "Todos", solo agregamos el total por especialidad
                if (especialidadSeleccionada.equals("Todos")) {
                    dataset.addValue(total, "Total", especialidad);
                } else {
                    // Si se seleccionó una especialidad específica, agregamos cada tipo de deducción
                    dataset.addValue(totalDecFlat, "Ded. Flat", especialidad);
                    dataset.addValue(totalDecIsrl, "Ded. ISRL", especialidad);
                    dataset.addValue(totalDecPersonal, "Ded. Personal", especialidad);
                    dataset.addValue(totalDecAlmacen, "Ded. Almacen", especialidad);
                    dataset.addValue(totalDecNomina, "Ded. Nomina", especialidad);
                    dataset.addValue(totalDecIncineradora, "Ded. Incineradora", especialidad);
                    dataset.addValue(totalDecAnticipo, "Ded. Anticipo", especialidad);
                    dataset.addValue(totalDecBioseguridad, "Ded. Bioseguridad", especialidad);
               //     dataset.addValue(totalFundacion, "Ded. Fundación", especialidad);
                    dataset.addValue(totalDecOtras, "Ded. Otras", especialidad);
                }
            }
            boolean incluirLeyenda = !especialidadSeleccionada.equals("Todos");
            // Crear gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                "Resumen de Deducciones por Especialidad",   // Título
                "Especialidad",                             // Eje X
                "Monto",                                    // Eje Y
                dataset,                                    // Datos
                PlotOrientation.VERTICAL,                   // Orientación
                incluirLeyenda,                                       // Incluir leyenda
                true,                                       // Incluir tooltips
                false);                                     // Incluir URLs

            // Crear gráfico de barras 3D
              chart = create3DBarChart(dataset, "Resumen Deducciones", "", "Montos");

                // Guardar como imagen PNG
                ChartUtilities.saveChartAsPNG(new File(rutaDestino), chart, 1000, 600);

                // Mostrar el gráfico en un JFrame
                f = new ChartFrame("Gráfico Ingresos", chart);
                f.setSize(1000, 600);
              //  f.setVisible(true);
        }
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
}
    
    
    ///DEDUCCIONES LABORATORIO  
  
  
  
  
  public PdfPTable crearTablaResultadoLab() {
    // Crear la tabla con 7 columnas
    PdfPTable tablaResultado = new PdfPTable(9);
    tablaResultado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
    tablaResultado.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
    tablaResultado.setWidthPercentage(100);


    float[] medidaCeldas3 = {6f, 3f, 3f, 3f, 3f, 3f, 3f,3f, 3f}; 

    try {
        // Crear la fuente base
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
   
        // Asignar las medidas de las celdas
        tablaResultado.setWidths(medidaCeldas3);

        // Crear la fuente para la tabla con la base definida
        Font letraSmall = new Font(BF, 10);
        letraSmall.setStyle(Font.BOLD);
        // Establecer el alineamiento de la tabla y las celdas
        tablaResultado.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaResultado.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        // Agregar las celdas con los títulos (sin valores todavía)
        tablaResultado.addCell(crearCeldaTitulo("ESPECIALISTA", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("FLAT", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ISRL", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("NOMINA", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("INC.", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("BIOSEG.", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ALM.", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("ANTICIPO", letraSmall));
        tablaResultado.addCell(crearCeldaTitulo("OTRAS", letraSmall));

    } catch (DocumentException | IOException e) {
        e.printStackTrace();  
    }


    return tablaResultado;
}
   
  
    public void pdfbyDeduccionesLab() {
    
    try {
        DateTimeFormatter fth = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        String pdfName = "ReporteHonorarios_Deduccion_" + fth.format(fechaactual);
        String outputFilePath = "C://Fundaginebra//Reportes//" + pdfName + ".pdf";
        
        // Inicialización de la fuente
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
        Font letraSmall = new Font(BF, 8);
        Font letraMedium = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
        Paragraph saltolinea = new Paragraph();
        saltolinea.add("\n");
        
        // Creación de archivo y documento
        FileOutputStream archivo = new FileOutputStream(new File(outputFilePath));
        Document doc = new Document();
        doc.setMargins(36, 36, 130, 130);
        PdfWriter writer = PdfWriter.getInstance(doc, archivo);
        Letrasmall = new Font(BF, 8);
       
       
        // Configuración del header y footer
        writer.setPageEvent(new HeaderFooterPageEvent());
        writer.setPageEvent(eventHelper);

            // Abrir el documento
            doc.open();

            
            
        PdfPTable tablaResultado = crearTablaResultadoLab();
        
        // Inicialización de variables para las sumas
        double sumatoriaTotal = 0;
    

        // Obtener los datos
        Map<String, List<vGraficaDc>> totalDeducciones =  knowDcc();

        // Iterar sobre los datos
        for (Map.Entry<String, List<vGraficaDc>> entry : totalDeducciones.entrySet()) {
            String especialidad = entry.getKey();
            List<vGraficaDc> statsList = entry.getValue();
  

            if (statsList == null || statsList.isEmpty()) {
                continue;
            }
            
            boolean esPrimeraFila = true;

            double sumFlat = 0;
            double sumIsrl=0;
            double sumNomina=0;  
            double sumIncineradora=0; 
            double sumBioseguridad=0;
            double sumAlmacen=0;
            double sumAnticipo=0;
            double sumOtras=0;
            double sumFundacion=0;
           
        
    
            
             
            
            // Iterar sobre las estadísticas de cada especialidad
            for (vGraficaDc stats : statsList) {
                String profesional = stats.getEspecialita();
                double Dec_flat = stats.getDec_flat();
                double Dec_ISRL = stats.getDec_ISRL();
                double Dec_personal = stats.getDec_personal();
                double Dec_almacen = stats.getDec_almacen();
                double Dec_nomina= stats.getDec_nomina();
                double Dec_incineradora = stats.getDec_incineradora();
                double Dec_anticipo = stats.getDec_anticipo();
                double Dec_Bioseguridad= stats.getDec_Bioseguridad();
                double Fundacion = stats.getFundacion();
                double Dec_otras = stats.getDec_otras();
            
                
                sumFlat += Dec_flat;
                sumIsrl+=Dec_ISRL;
                sumNomina+=Dec_nomina;
                sumIncineradora+=Dec_incineradora;
                sumBioseguridad+=Dec_Bioseguridad;
                sumAlmacen+=Dec_almacen;
                sumAnticipo+=Dec_anticipo;
                sumFundacion+=Fundacion;
                sumOtras+=Dec_otras;
            
     

                        
               String Dec_flatFormateado = String.format("%.2f", Dec_flat);
               String Dec_ISRLFormateado = String.format("%.2f", Dec_ISRL);
               String Dec_personalFormateado = String.format("%.2f", Dec_personal);
               String Dec_almacenFormateado = String.format("%.2f", Dec_almacen);
               String Dec_nominaFormateado = String.format("%.2f", Dec_nomina);
               String Dec_incineradoraFormateado = String.format("%.2f", Dec_incineradora);
               String Dec_anticipoFormateado = String.format("%.2f", Dec_anticipo);
               String Dec_BioseguridadFormateado = String.format("%.2f", Dec_Bioseguridad);
               String FundacionFormateado = String.format("%.2f", Fundacion);
               String Dec_otrasFormateado = String.format("%.2f", Dec_otras);
                

                
                

               
                
               
         
    
      
        PdfPCell nom = new PdfPCell(new Paragraph("\n"+ profesional.toUpperCase(), Letrasmall));
        PdfPCell flat = new PdfPCell(new Paragraph("\n"+ String.valueOf(Dec_flatFormateado), letraSmall));
        PdfPCell isrl = new PdfPCell(new Paragraph("\n"+ Dec_ISRLFormateado, letraSmall));
        PdfPCell nomina = new PdfPCell(new Paragraph("\n"+Dec_nominaFormateado, letraSmall));
        PdfPCell incineradora = new PdfPCell(new Paragraph("\n"+Dec_incineradoraFormateado, letraSmall));
        PdfPCell bioseguridad = new PdfPCell(new Paragraph("\n"+Dec_BioseguridadFormateado, letraSmall));
        PdfPCell fundacion = new PdfPCell(new Paragraph("\n"+FundacionFormateado, letraSmall));  
        PdfPCell almacen = new PdfPCell(new Paragraph("\n"+Dec_almacenFormateado, letraSmall));
        PdfPCell anticipo = new PdfPCell(new Paragraph("\n"+Dec_anticipoFormateado, letraSmall));
        PdfPCell otras = new PdfPCell(new Paragraph("\n"+Dec_otrasFormateado, letraSmall));

        // Configuración de las celdas
        for (PdfPCell cell : new PdfPCell[]{nom,flat, isrl, nomina,incineradora,bioseguridad,almacen, anticipo, otras}) {
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.enableBorderSide(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tablaResultado.addCell(cell);
         
        }
            }
   
         

         PdfPCell resumenEspecialidad = new PdfPCell(new Paragraph(
         "\n\nRESUMEN "  + especialidad.toUpperCase() +"\n"+
     //    "\n*********************************************** \n\n"+
        "TOTAL FLAT: " +  String.format("%.2f", sumFlat) + 
        " | TOTAL ISRL: "+String.format("%.2f",sumIsrl)+
        " | TOTAL NOMINA: "+String.format("%.2f",sumNomina)+
        " | TOTAL INCINERADORA: "+String.format("%.2f",sumIncineradora)+    
        " | TOTAL BIOSEGURIDAD: "+String.format("%.2f",sumBioseguridad)+
        " | TOTAL ALMACEN: "+String.format("%.2f",sumAlmacen)+
        " | TOTAL ANTICIPO: "+String.format("%.2f",sumAnticipo)+
        " | TOTAL OTRAS: "+String.format("%.2f",sumOtras)+
  
        " ______________________________________________________________________________________________________________",
                 
        letraMedium
    ));
    resumenEspecialidad.setColspan(9);
    resumenEspecialidad.setBorder(Rectangle.NO_BORDER);
    resumenEspecialidad.setHorizontalAlignment(Element.ALIGN_LEFT);
    resumenEspecialidad.setVerticalAlignment(Element.ALIGN_LEFT);
    
    tablaResultado.setWidthPercentage(100); 
    tablaResultado.addCell(resumenEspecialidad);

    
  
    
PdfPCell emptyCell = new PdfPCell(new Paragraph("")); 
emptyCell.setColspan(9); // Asegúrate de que esta cantidad coincida con las columnas de la tabla
emptyCell.setBorder(Rectangle.NO_BORDER); 
emptyCell.setFixedHeight(5f); // Añadir altura para asegurarte de que se ve la línea
//emptyCell.enableBorderSide(Rectangle.BOTTOM);  // Habilitar solo el borde inferior
tablaResultado.addCell(emptyCell);
            
    sumatoriaTotal += sumFlat + sumIsrl + sumNomina + sumIncineradora+sumBioseguridad + sumAlmacen + sumAnticipo + sumOtras;         
            
        }

    


                  PdfPTable tablaTotal = new PdfPTable(1); 
                  tablaTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                  tablaTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                  tablaTotal.setWidthPercentage(100); 
                  float[] medidaResultado = {5f};
                  tablaTotal.setWidths(medidaResultado);
                  tablaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
                  tablaTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

                  String fecha1 = new SimpleDateFormat("dd-MM-yyyy").format(FechaOne.getDate());
                  String fecha2 = new SimpleDateFormat("dd-MM-yyyy").format(FechaTwo.getDate());
                  Paragraph t41columna1 = new Paragraph("\n SUMATORIA TOTAL: " + String.format("%.2f",sumatoriaTotal) + 
                                                        "\n ENCONTRADO EN EL RANGO DE FECHAS:  " + fecha1 + " | " + fecha2);
                  t41columna1.getFont().setStyle(Font.NORMAL);
                  t41columna1.getFont().setSize(11);        
                  t41columna1.setFont(Letrasmall);
                  t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
                  PdfPCell celdaTotal = new PdfPCell(t41columna1);
                  celdaTotal.setBorder(Rectangle.NO_BORDER);
                  tablaTotal.addCell(celdaTotal);






        doc.add(tablaResultado);
        doc.add(saltolinea);
  

  
  try {
    com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\grafica_Honorarios.png");
    
    float pageWidth = doc.getPageSize().getWidth();
    float imageWidth = imgGrafica.getScaledWidth();
    float xPos = (pageWidth - imageWidth) / 2;

    // Establece la posición X de la imagen en el documento
    imgGrafica.setAlignment(Element.ALIGN_CENTER);
    imgGrafica.scaleToFit(500, 500);
    
    // Agrega la imagen al documento
   
    doc.add(imgGrafica);
    doc.add(tablaTotal);
    
    

    
} catch (IOException e) {
    System.out.println("Error al cargar la imagen: " + e.getMessage());
}
  
  

  
  
        // Cerrar documento
        doc.close();
        archivo.close();
        if (tablaResultado.getRows().size() <= 1) {
      //  JOptionPane.showMessageDialog(null, "NO HAY REPORTES QUE VISUALIZAR, SOLO SE MUESTRAN AUTORIZADOS O COMPLETADOS", "REPORTES", JOptionPane.ERROR_MESSAGE);
        } else {
         Desktop.getDesktop().open(new File(outputFilePath));}


    } catch (DocumentException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    
      SearchConsultasDao search = new SearchConsultasDao();
      Temporal Tempo = new Temporal();
      Mprincipal MP = new Mprincipal();
      JCambiarState setState = new JCambiarState();
      vGraficasHono varH = new vGraficasHono();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Autorizar;
    private javax.swing.JMenuItem Cancelar;
    private javax.swing.JMenuItem Completar;
    private javax.swing.JMenuItem EnviarCorreo;
    private javax.swing.JMenuItem EnviarWs;
    public com.toedter.calendar.JDateChooser FechaOne;
    public com.toedter.calendar.JDateChooser FechaTwo;
    private javax.swing.JComboBox<String> JCespecialidad;
    private javax.swing.JComboBox<String> JEstados;
    public javax.swing.JTable Jtabla;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuItem jCorregir;
    private javax.swing.JComboBox<String> jEstadistica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRcodigo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jSearchCI1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
