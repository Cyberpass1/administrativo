/*
Made by Ing Juan Calderón
tupas49@gmail.com

 */


package Registros;

import Clases.ColorRenderer;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JPacientesDao;
import Clases.Temporal;
import Clases.Validar;
import Menu.Mprincipal;
import Procesos.JLabexamenes.JPerfilrutina;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
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
import java.util.stream.Stream;
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
public class JPacientes extends javax.swing.JInternalFrame implements Runnable{

  
    
    int idPaciente;
 
    
    
    public JPacientes() {
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();

        FechaOrden.setCalendar(Fecha);
        
        
        limpiarTabla();
        listarPacientes();
        
        limpiarTablaOrden();
        listarOrden();
    
        this.JRMasculino.setSelected(true);
        acomodarceldas();
        conteoTablatotal();
        conteoTablaOrden();
        
       this.jRpacientes.setSelected(true);
       this.jRllegada.setSelected(false);
       jTabpacientes.setSelectedIndex(0);
       if (jOrden.getRowCount() == 0) {
       idorden = 0;
       }
         
   
     String Nivel = TM.getNivel();
     String especialidad= TM.getEspecialidad();
     String nivelUsuario=Nivel+" "+especialidad;

     hojaTrabajo.setVisible(false);  
     
         
         if(Nivel.equals("Administrador") ){
           //|| nivelUsuario.equals("Lcdo. Bioanalista")
             Eliminar.setEnabled(true); 
          
    
         }
 
  
        
    }

 
    

    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AsignarOrden = new javax.swing.JPopupMenu();
        Orden = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jTableOrden = new javax.swing.JPopupMenu();
        jEstudios = new javax.swing.JMenu();
        jHematologia = new javax.swing.JMenuItem();
        jQuimica = new javax.swing.JMenuItem();
        Modf = new javax.swing.JMenuItem();
        deletedOrden = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        TXTPnombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTPdireccion = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        TXTPtelefono = new javax.swing.JFormattedTextField();
        jLabel111 = new javax.swing.JLabel();
        FechaNacimiento = new com.toedter.calendar.JDateChooser();
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
        jPanel9 = new javax.swing.JPanel();
        jRllegada = new javax.swing.JRadioButton();
        jRpacientes = new javax.swing.JRadioButton();
        hojaTrabajo = new javax.swing.JButton();
        jTabpacientes = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablePaciente = new javax.swing.JTable();
        TXTBcedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jOrden = new javax.swing.JTable();
        FechaOrden = new com.toedter.calendar.JDateChooser();
        TXTBcedula2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delegar_1.png"))); // NOI18N
        Orden.setText("Asignar Orden");
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

        jEstudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/asignacion.png"))); // NOI18N
        jEstudios.setText("Asignar Estudios");

        jHematologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/hematologia.png"))); // NOI18N
        jHematologia.setText("Hematologia");
        jHematologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHematologiaActionPerformed(evt);
            }
        });
        jEstudios.add(jHematologia);

        jQuimica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/muestra-de-sangre_1.png"))); // NOI18N
        jQuimica.setText("Quimica Sanguinea");
        jQuimica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuimicaActionPerformed(evt);
            }
        });
        jEstudios.add(jQuimica);

        jTableOrden.add(jEstudios);

        Modf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editar.png"))); // NOI18N
        Modf.setText("Modificar");
        Modf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModfActionPerformed(evt);
            }
        });
        jTableOrden.add(Modf);

        deletedOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        deletedOrden.setText("Quitar de la lista");
        deletedOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedOrdenActionPerformed(evt);
            }
        });
        jTableOrden.add(deletedOrden);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      P A C I E N T E S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 240, -1));

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

        jLabel111.setBackground(new java.awt.Color(0, 0, 0));
        jLabel111.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel111.setText("Fecha de nacimiento");
        jPanel2.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, 60));

        FechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        FechaNacimiento.setToolTipText("");
        FechaNacimiento.setDateFormatString("dd/MM/yyyy");
        FechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 260, 40));

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
        jPanel2.add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, 100, 30));

        BtnLimpiar.setText("Nuevo");
        BtnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar.setContentAreaFilled(false);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 100, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 500, 20));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 520, 10, 70));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 10, 70));

        TXTPcedula.setText("V-");
        TXTPcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cédula")));
        TXTPcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPcedulaKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, 50));

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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 450, 590));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRllegada.setBackground(new java.awt.Color(255, 255, 255));
        jRllegada.setText("Orden de llegada");
        jRllegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRllegadaActionPerformed(evt);
            }
        });
        jPanel9.add(jRllegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        jRpacientes.setBackground(new java.awt.Color(255, 255, 255));
        jRpacientes.setText("Pacientes");
        jRpacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRpacientesActionPerformed(evt);
            }
        });
        jPanel9.add(jRpacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        hojaTrabajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/hojaTrabajo.png"))); // NOI18N
        hojaTrabajo.setToolTipText("Ver hoja de trabajo");
        hojaTrabajo.setBorder(null);
        hojaTrabajo.setContentAreaFilled(false);
        hojaTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hojaTrabajoActionPerformed(evt);
            }
        });
        jPanel9.add(hojaTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 60, 50));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 790, -1));

        jTabpacientes.setEnabled(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTablePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "CEDULA", "TELEFONO", "CORREO", "DIRECCIÓN", "SEXO", "FEC_NACIMIENTO"
            }
        ));
        JTablePaciente.setComponentPopupMenu(AsignarOrden);
        JTablePaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablePacienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JTablePacienteMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(JTablePaciente);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 770, 470));

        TXTBcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar cédula | nombre"));
        TXTBcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyTyped(evt);
            }
        });
        jPanel6.add(TXTBcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 50));

        jLabel1.setText("Total de pacientes:");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        jLabel2.setText("0");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 40, -1));

        jTabpacientes.addTab("Pacientes", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orden", "Factura", "Paciente", "Cédula", "Id"
            }
        ));
        jOrden.setComponentPopupMenu(jTableOrden);
        jOrden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jOrdenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jOrdenMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(jOrden);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 770, 470));

        FechaOrden.setBackground(new java.awt.Color(255, 255, 255));
        FechaOrden.setToolTipText("");
        FechaOrden.setDateFormatString("dd/MM/yyyy");
        FechaOrden.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FechaOrden.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FechaOrdenPropertyChange(evt);
            }
        });
        jPanel7.add(FechaOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 190, 30));

        TXTBcedula2.setText("V-");
        TXTBcedula2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar cédula"));
        TXTBcedula2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBcedula2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTBcedula2KeyTyped(evt);
            }
        });
        jPanel7.add(TXTBcedula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 50));

        jLabel3.setText("Pacientes Asignados");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 54, 150, 20));

        jLabel4.setText("0");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 40, -1));

        jTabpacientes.addTab("Orden de llegada", jPanel7);

        jPanel1.add(jTabpacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 790, 590));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
       
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    EnlaceBd cn = new EnlaceBd();
try {
    String fechaN = new SimpleDateFormat("yyyy/MM/dd").format(FechaNacimiento.getDate());
    String[] dateParts1 = fechaN.split("/");
    String an1 = dateParts1[0];
    String mes1 = dateParts1[1];
    String dia1 = dateParts1[2];

    String an_actual = Validar.fecha_actual();
    String mes_actual = Validar.fecha_mes();
    String dia_actual = Validar.fecha_dia();

    int diferenciadia = Integer.parseInt(dia_actual) - Integer.parseInt(dia1);
    int diferenciames = Integer.parseInt(mes_actual) - Integer.parseInt(mes1);
    int diferencian = Integer.parseInt(an_actual) - Integer.parseInt(an1);

    if (diferenciames < 0 || (diferenciames == 0 && diferenciadia < 0)) {
        diferencian = diferencian - 1;
    }

    Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    String Correo = TXTPcorreo.getText();
    Matcher mather = pattern.matcher(Correo);

    if (Stream.of(TXTPapellido.getText(), TXTPnombre.getText(), TXTPcedula.getText(), TXTPcorreo.getText(), TXTPtelefono.getText(), TXTPdireccion.getText())
            .anyMatch(String::isEmpty)) {
        JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < JTablePaciente.getRowCount(); i++) {
            if (JTablePaciente.getValueAt(i, 1).equals(TXTPcedula.getText())) {
                JOptionPane.showMessageDialog(null, "EL PACIENTE YA SE ENCUENTRA REGISTRADO", "DATO DUPLICADO", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else if (!mather.find()) {
        JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
        TXTPcorreo.requestFocus();
    } else if (diferencian > 120 || diferencian < 0) {
        JOptionPane.showMessageDialog(null, "POR FAVOR, INTRODUCE UNA FECHA DE NACIMIENTO VALIDA, EJEMPLO:1999", "FECHA DE NACIMIENTO", JOptionPane.ERROR_MESSAGE);
    } else if (TXTPtelefono.getValue() == null) {
        JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NUMERO DE TELEFONO", "TELEFONO", JOptionPane.ERROR_MESSAGE);
    } else {
        String sql = "SELECT Idpaciente, Nombre, Apellido, Cedula, Telefono, Direccion FROM table_paciente WHERE Cedula=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, TXTPcedula.getText());
        rs = ps.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "ESTA CÉDULA YA SE ENCUENTRA REGISTRADA", "CAMPO CÉDULA", JOptionPane.ERROR_MESSAGE);
        } else {
            AgregarPaciente();
            conteoTablatotal();
        }
    }
} catch (Exception e) {
    System.out.println(e);
} finally {
    try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    } catch (SQLException ex) {
        System.out.println(ex);
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
    
   
    String fechaN = new SimpleDateFormat("yyyy/MM/dd").format(FechaNacimiento.getDate());
            String [] dateParts1= fechaN.split("/");
            String an1 = dateParts1[0];
            String mes1 = dateParts1[1];
            String dia1 = dateParts1[2];
            
            
            String an_actual = Validar.fecha_actual();
            String mes_actual = Validar.fecha_mes();
            String dia_actual = Validar.fecha_dia();
            

            int diferenciadia=Integer.parseInt(dia_actual)- Integer.parseInt(dia1);
            int diferenciames=Integer.parseInt(mes_actual)- Integer.parseInt(mes1);
            int diferencian=Integer.parseInt(an_actual)- Integer.parseInt(an1);;
        

   if (diferenciames < 0 || (diferenciames == 0 && diferenciadia < 0)) {
        diferencian = diferencian - 1;
    }
   
   
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = TXTPcorreo.getText();
        Matcher mather = pattern.matcher(Correo);

        if (TXTPapellido.getText().equals("") || TXTPnombre.getText().equals("") || TXTPcedula.getText().equals("") 
            || TXTPcorreo.getText().equals("") || TXTPtelefono.getText().equals("") || TXTPdireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        } 
        
         else if (diferencian>120 || diferencian <0){
        JOptionPane.showMessageDialog(null, "POR FAVOR, INTRODUCE UNA FECHA DE NACIMIENTO VALIDA, EJEMPLO:1999", "FECHA DE NACIMIENTO", JOptionPane.ERROR_MESSAGE);
        }
        
         
        else if (mather.find() == false) {
           
            
            JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
            this.TXTPcorreo.requestFocus();
            
            
        }

        
             else{    
 AuditoriaModificar();
 ActualizarPaciente(); 
 limpiarCampos();
 conteoTablatotal();
                }
       
                
    }//GEN-LAST:event_BtnModificarActionPerformed

    
  
    
         
            String nacimiento, sexoTable, cedulaPaciente, nombreAsignar;  
    private void JTablePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacienteMouseClicked
      int fila = JTablePaciente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   
   
               
           cedulaPaciente=(JTablePaciente.getValueAt(fila, 3).toString());;
           nombreAsignar=JTablePaciente.getValueAt(fila, 1).toString() + " "+ JTablePaciente.getValueAt(fila, 2).toString();
    
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
    
 
 
 
 
try (Connection con = EnlaceBd.getConnection();
     PreparedStatement ps = con.prepareStatement("SELECT date_format(FechNacimiento, '%d/%m/%Y') AS Fecha FROM table_paciente WHERE IdPaciente = ?");
      ) {
    
    // Establecer el valor del parámetro antes de ejecutar la consulta
    ps.setInt(1, idPaciente);
    
    // Ejecutar la consulta después de haber establecido los parámetros
    try (ResultSet rs = ps.executeQuery()) {
        // Formato de fecha
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        if (rs.next()) {
            // Obtener la fecha desde el resultado
            String nacimiento = rs.getString("Fecha");
            
            // Parsear la fecha y establecerla en el componente de interfaz gráfica
            FechaNacimiento.setDate(formato.parse(nacimiento));
        }
    }

} catch (Exception e) {
    System.out.println("Error: " + e);
}




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
 
    private Thread searchThread;
    private void TXTBcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedulaKeyReleased

   
     int DELAY = 600;
        
        
        
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
               Search(); 
            });
        });

           searchThread.start();
        
        
    
    }//GEN-LAST:event_TXTBcedulaKeyReleased

    private void TXTPnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPnombreKeyTyped
char car = evt.getKeyChar();

// Verifica si el carácter no es una barra '/' ni una eliminación de teclado
if (car != '/' && car != KeyEvent.VK_DELETE) {
    // Verifica si la longitud del texto es menor o igual a 30
    if (TXTPnombre.getText().length() >= 30) {
        evt.consume(); // Consumir el evento si se alcanza o excede la longitud máxima
    }
} else if (car == '/') {
    evt.consume(); // Consumir el evento si el carácter es una barra '/'
}
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
            //CONTABILIZA LOS CARACTERES 10292
            if(Caracteres.length()==14)
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
      

va.longitud(TXTBcedula.getText(), 50, evt);

        /* char car = evt.getKeyChar();
        //VERIFICA Y LIMITA COMPOSICION DE LOS DATOS
        if((car=='V' || car=='E'  || car=='-'|| car =='H' || car>='0' && car<='9' || car==(char)KeyEvent.VK_DELETE))
        {
            String Caracteres = TXTBcedula.getText();
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
        }*/
    }//GEN-LAST:event_TXTBcedulaKeyTyped

    private void TXTBcedula2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedula2KeyReleased
 searchOrder();
acomodarOrden();
    }//GEN-LAST:event_TXTBcedula2KeyReleased

    private void TXTBcedula2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedula2KeyTyped
        

    }//GEN-LAST:event_TXTBcedula2KeyTyped

    private void jRpacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRpacientesActionPerformed
       this.jRpacientes.setSelected(true);
       this.jRllegada.setSelected(false); 
       hojaTrabajo.setVisible(false);  
 
       jTabpacientes.setSelectedIndex(0);
       
    }//GEN-LAST:event_jRpacientesActionPerformed

    private void jRllegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRllegadaActionPerformed
       this.jRpacientes.setSelected(false);
       this.jRllegada.setSelected(true);
       jTabpacientes.setSelectedIndex(1);
       BtnModificar.setEnabled(false);

       hojaTrabajo.setVisible(true);  
  
       
       conteoTablaOrden();
       limpiarTablaOrden();
       listarOrden();
       jOrden.requestFocusInWindow();
    }//GEN-LAST:event_jRllegadaActionPerformed

    
    
    int idorden, orden;
    String nombreCompleto, factura, cedulaOrden;
    private void jOrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOrdenMouseClicked

        
        try{
        int fila = jOrden.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {

            
            
          idorden=(int) (jOrden.getValueAt(fila, 0));
          factura = jOrden.getValueAt(fila, 1).toString();
          nombreCompleto = jOrden.getValueAt(fila, 2).toString();
          cedulaOrden= jOrden.getValueAt(fila, 3).toString();
          orden=(int) (jOrden.getValueAt(fila, 4));
 

}} catch(Exception e){ System.out.println(e);}

    }//GEN-LAST:event_jOrdenMouseClicked

    private void FechaOrdenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FechaOrdenPropertyChange
    limpiarTablaOrden();
    listarOrden();
    conteoTablaOrden();
    }//GEN-LAST:event_FechaOrdenPropertyChange

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int fila = JTablePaciente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA PACIENTES", "SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {

            eliminarPaciente();
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenActionPerformed
        int fila = JTablePaciente.getSelectedRow();
        try{

            if(TXTPcedula.getText().equals("")){
                JOptionPane.showMessageDialog(null, "POR FAVOR VUELVE A SELECCIONAR UN PACIENTE", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);

            }

            else if (fila == -1) {
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA", "SELECCION", JOptionPane.ERROR_MESSAGE);
            }
            
            
              else if ( idPaciente==0 ) {
                JOptionPane.showMessageDialog(this, "NO SE HA DETECTADO UN PACIENTE SELECCIONADO, VUELVA A SELECCIONAR", "SELECCION", JOptionPane.ERROR_MESSAGE);
            }


            else{

                agregarOrden();
            }

        }
        catch(Exception e ){
            System.out.println("primer try catch"+e);
            JTablePaciente.requestFocusInWindow();
        }
        conteoTablaOrden();
    }//GEN-LAST:event_OrdenActionPerformed

    private void deletedOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedOrdenActionPerformed

        int fila = jOrden.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA", "SELECCION", JOptionPane.ERROR_MESSAGE);
        }

        else{
            if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea quitar los pacientes seleccionados de la lista? ",
                "Quitar paciente/s", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

            eliminarOrden();
            limpiarTablaOrden();
            listarOrden();
            conteoTablaOrden();
            AuditoriaEliminar();
            idorden=-1;

            if (jOrden.getRowCount() == 0) {
                idorden = 0;
            }

        }

        }

    }//GEN-LAST:event_deletedOrdenActionPerformed

    private void ModfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModfActionPerformed

        int fila = jOrden.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA", "SELECCION", JOptionPane.ERROR_MESSAGE);
        }

        else{
            try {
                JTextField textoNumeroOrden = new JTextField(10);
                JTextField textoNumeroFactura = new JTextField(10);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 2));
                panel.add(new JLabel("Número de Orden:"));
                panel.add(textoNumeroOrden);
                panel.add(new JLabel("Número de Factura:"));
                panel.add(textoNumeroFactura);
                int result = JOptionPane.showConfirmDialog(null, panel, "MODIFICAR DATOS", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String numeroOrden = textoNumeroOrden.getText().trim();
                    String numeroFactura = textoNumeroFactura.getText().trim();

                    actualizarOrden(numeroOrden, numeroFactura );
                    auditoriaModifico();
                }
            }catch(Exception e){System.out.println(e);}

        }

    }//GEN-LAST:event_ModfActionPerformed

    private void jQuimicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuimicaActionPerformed
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String fechaOrden = new SimpleDateFormat("yyyy/MM/dd").format(FechaOrden.getDate());
        String sql = "SELECT `IdQuimica`, `id_ordenQU`, `lugar`, `Nombre`, `Apellido`, `Cedula`, `fecha`, `GLICEMIA`, `UREA`, `CREATININA`, `ACIDO_URICO`, `COLESTEROL`, `TRIGLICERIDOS`, `HDL`, `LDL`, `VLDL`, `BILITOTAL`, `BILIDIRECTA`, `BILIINDIRECTA`, `PROTEINAS_TOTALES`, `ALBUMINA`, `GLOBULINAS`, `RELACION`, `TGO`, `TGP`, `GGT`, `FOSFATASA`, `LDH`, `AMILASA`, `CALCIO`, `FOSFORO`, `MAGNESIO`" +
        " FROM `asig_quimica` u " +
        " INNER JOIN `orden_lab` a ON u.`Id_ordenQU` = a.`id_orden` " +
        " INNER JOIN `table_paciente` b ON a.`paciente` = b.`Idpaciente` " +
        " WHERE u.`Id_ordenQU` = ? AND `fecha` = ?";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orden);
            ps.setString(2, fechaOrden);
            rs = ps.executeQuery();

            if(rs.next()) {JOptionPane.showMessageDialog(null, "EL PACIENTE "+nombreCompleto+" YA HA SIDO ASIGNADO", "PACIENTES",JOptionPane.ERROR_MESSAGE);}

            else if(orden==0){JOptionPane.showMessageDialog(this, "ERROR AL SELECCIONAR, POR FAVOR SELECCIONA UN PACIENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE); jOrden.requestFocusInWindow();}
            else{


                asignarQuimica();
              //  pintarTabla();
            }

        }catch(Exception e){System.out.println(e);}

    }//GEN-LAST:event_jQuimicaActionPerformed

    private void jHematologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHematologiaActionPerformed

        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String fechaOrden = new SimpleDateFormat("yyyy/MM/dd").format(FechaOrden.getDate());
        String sql = "SELECT `id_hematologia`, `Id_ordenes`, `lugar`, `Nombre`, `Apellido`, `Cedula`, `fecha`, `Leucocitos`, `Linfocitos`, `Neutrofilos`, `Hemoglobina`, `Hematocrito`, `VCM`, `CHCM`, `Plaquetas` \n" +
        "            FROM `asig_hematologia` u \n" +
        "             INNER JOIN `orden_lab` a ON u.`Id_ordenes` = a.`id_orden` \n" +
        "             INNER JOIN `table_paciente` b ON a.`paciente` = b.`Idpaciente` \n" +
        "             WHERE Id_ordenes=? AND `fecha` = ?";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orden);
            ps.setString(2, fechaOrden);
            rs = ps.executeQuery();

            if(rs.next()) {JOptionPane.showMessageDialog(null, "EL PACIENTE "+nombreCompleto+" YA HA SIDO ASIGNADO", "PACIENTES",JOptionPane.ERROR_MESSAGE);}

            else if(orden==0){JOptionPane.showMessageDialog(this, "ERROR AL SELECCIONAR, POR FAVOR SELECCIONA UN PACIENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE); jOrden.requestFocusInWindow();}
            else{
                asignarHematologia();
             //   pintarTabla();
                jOrden.requestFocusInWindow();
            }

        }catch(Exception e){System.out.println(e);}

    }//GEN-LAST:event_jHematologiaActionPerformed

    private void JTablePacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacienteMouseEntered
          JTablePaciente.requestFocusInWindow();
    }//GEN-LAST:event_JTablePacienteMouseEntered

    private void jOrdenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jOrdenMouseEntered
     jOrden.requestFocusInWindow();
    }//GEN-LAST:event_jOrdenMouseEntered

    private void hojaTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hojaTrabajoActionPerformed
        pdf();
    }//GEN-LAST:event_hojaTrabajoActionPerformed

        
    
    
    

  public void pintarTabla(){
   
        int targetRow = jOrden.getSelectedRow();
        int targetColumn = 0;
        ColorRenderer  colorRenderer = new ColorRenderer(-1, -1); 
        jOrden.setDefaultRenderer(Object.class, colorRenderer);
        // Cambia el color de la celda objetivo a verde
        colorRenderer.setAlteredCellColor(targetRow, targetColumn, Color.GREEN);

      
      
      }
      
    
    
    
    
    
    
    
    
    
    
    
    
   String PdfNames, lugarh, hNombre, hApellido, hCedula  ;
   boolean Leucocitos, Neutrofilos, Hematocrito, CHCM, Linfocitos, Hemoglobina, VCM, PLAQ;
   int contadorHematologia=0;
   int contadorQuimica=0;
   PdfPTable Tabla0, Tabla2; 

   
public void pdf() {
    
        try {
            
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;

        DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");

        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        com.itextpdf.text.Font Letra = new com.itextpdf.text.Font(BF);
        Paragraph saltolinea = new Paragraph();

        FileOutputStream archivo;
        File file = new File("C://Fundaginebra//Reportes//reporte.pdf");
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, archivo);
      
        doc.open();
        doc.setMargins(36, 36, 36, 80);
        PdfContentByte CB = writer.getDirectContent();  
        BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Fecha = FormatoFecha.format(Menu.FechaAdmin.getDate());
        String Hora = Menu.Time.getText()+" "+Menu.jLabel102.getText();
            
        CB.beginText();
        CB.setFontAndSize(BF2, 10);

        float pageWidth = doc.getPageSize().getWidth();
        float pageHeight = doc.getPageSize().getHeight();


        float x = (pageWidth - BF2.getWidthPoint("" + Fecha + " " + Hora, 10)) / 2;
        float y = pageHeight - 20; // Ajusta este valor según sea necesario para la distancia desde la parte superior

        CB.setTextMatrix(x, y);
        CB.showText("" + Fecha + " " + Hora);
        CB.endText();




        try {
            
            String fechaOrden = new SimpleDateFormat("yyyy/MM/dd").format(FechaOrden.getDate());
            String sql = "SELECT `id_hematologia`, Id_ordenes, `lugar`, Nombre, Apellido, Cedula, fecha, `Leucocitos`, `Linfocitos`, `Neutrofilos`, `Hemoglobina`, `Hematocrito`, `VCM`, `CHCM`, `Plaquetas` FROM `asig_hematologia` AS u " +
                         "INNER JOIN orden_lab a ON u.Id_ordenes = a.id_orden " +
                         "INNER JOIN table_paciente b ON a.paciente = b.Idpaciente " +
                         "WHERE fecha = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaOrden);
            rs = ps.executeQuery();

            
            
        while (rs.next()) {
        
        lugarh=rs.getString("lugar");
        hNombre=rs.getString("Nombre")+" "+rs.getString("Apellido") ;
        hCedula=rs.getString("Cedula");
        Leucocitos = rs.getBoolean("Leucocitos");
        Neutrofilos = rs.getBoolean("Neutrofilos");
        Hematocrito = rs.getBoolean("Hematocrito");
        CHCM = rs.getBoolean("CHCM");
        Linfocitos = rs.getBoolean("Linfocitos");
        Hemoglobina = rs.getBoolean("Hemoglobina");
        VCM = rs.getBoolean("VCM");
        PLAQ = rs.getBoolean("Plaquetas");

                
        Tabla2 = new PdfPTable(8);
        Tabla2.setWidthPercentage(100);
        float[] medidaCeldas2 = {2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
        Tabla2.setWidths(medidaCeldas2);
        Tabla2.setHorizontalAlignment(Element.ALIGN_LEFT);
        Tabla2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

        // Supongamos que tienes una lista de valores de condiciones (por ejemplo, booleanos)
        List<Boolean> condiciones = Arrays.asList(Leucocitos, Neutrofilos, Hematocrito, CHCM, Linfocitos, Hemoglobina, VCM, PLAQ);
        String[] etiquetas = {"GB:", "SEG:", "HTO:", "CHCHM:", "LINF:", "HB:", "VCH:", "PLAQ:"};
        for (int i = 0; i < condiciones.size(); i++) {

        boolean condicion = condiciones.get(i);

        Paragraph t2columna = new Paragraph(etiquetas[i]);
        t2columna.getFont().setStyle(Font.BOLD);
        t2columna.getFont().setSize(9);
        t2columna.setFont(Letra);
        t2columna.setAlignment(Element.ALIGN_LEFT);

        PdfPCell cell = new PdfPCell(t2columna);
    
        if (!condicion) {
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY); 
        }
    
    
       Tabla2.addCell(cell);
       }
        
        
        Tabla0 = new PdfPTable(3);
        Tabla0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        Tabla0.setWidthPercentage(100);
        float[] medidaCeldas0 = {2f, 4f, 2f};
        Tabla0.setWidths(medidaCeldas0);
        Tabla0.setHorizontalAlignment(Element.ALIGN_CENTER);
        Tabla0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph t0columna1 = new Paragraph("N ORDEN "+ lugarh);
        t0columna1.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna1.getFont().setSize(8);
        t0columna1.setFont(Letra);
        t0columna1.setAlignment(Element.ALIGN_LEFT);
        Tabla0.addCell(t0columna1);

        Paragraph t0columna2 = new Paragraph("NOMBRE " +hNombre);
        t0columna2.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna2.getFont().setSize(8);
        t0columna2.setFont(Letra);
        t0columna2.setAlignment(Element.ALIGN_LEFT);
        Tabla0.addCell(t0columna2);

        Paragraph t0columna4 = new Paragraph("CÉDULA "+ hCedula);
        t0columna4.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna4.getFont().setSize(8);
        t0columna4.setFont(Letra);
        t0columna4.setAlignment(Element.ALIGN_LEFT);
        Tabla0.addCell(t0columna4);
                
        doc.add(Tabla0);
        doc.add(Tabla2);       
            }

             } catch (Exception e) {
            System.out.println(e);
        } 
            finally {
            closeResources(rs, ps, con);
        }
            
            
            
        
        //  QUIMICA SANGUINEA
        
        doc.newPage();
        
           try {
            
         
               
            String fechaOrden = new SimpleDateFormat("yyyy/MM/dd").format(FechaOrden.getDate());
            String sql = "SELECT `IdQuimica`, `id_ordenQU`, `lugar`, Nombre, Apellido, Cedula, fecha,`GLICEMIA`, `UREA`, `CREATININA`, `ACIDO_URICO`, `COLESTEROL`, `TRIGLICERIDOS`, `HDL`, `LDL`, `VLDL`, `BILITOTAL`, `BILIDIRECTA`, `BILIINDIRECTA`, `PROTEINAS_TOTALES`, `ALBUMINA`, `GLOBULINAS`, `RELACION`, `TGO`, `TGP`, `GGT`, `FOSFATASA`, `LDH`, `AMILASA`, `LIPASA`, `CALCIO`, `FOSFORO`, `MAGNESIO`, `CK` FROM `asig_quimica` u\n" +
"INNER JOIN orden_lab a ON u.id_ordenQU = a.id_orden \n" +
"INNER JOIN table_paciente b ON a.paciente = b.Idpaciente WHERE fecha = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaOrden);
            rs = ps.executeQuery();

            
            
        while (rs.next()) {
        
        lugarh=rs.getString("lugar");
        hNombre=rs.getString("Nombre")+" "+rs.getString("Apellido") ;
        hCedula=rs.getString("Cedula");
        Boolean glicemia = rs.getBoolean("GLICEMIA");
        Boolean urea = rs.getBoolean("UREA");
        Boolean creatinina = rs.getBoolean("CREATININA");
        Boolean au = rs.getBoolean("ACIDO_URICO");
        Boolean colesterol = rs.getBoolean("COLESTEROL");
        Boolean triglice = rs.getBoolean("TRIGLICERIDOS");
        Boolean hdl = rs.getBoolean("HDL");
        Boolean ldl = rs.getBoolean("LDL");
        Boolean vldl = rs.getBoolean("VLDL");
                
        
        Boolean BT = rs.getBoolean("BILITOTAL");
        Boolean BD = rs.getBoolean("BILIDIRECTA");
        Boolean BI = rs.getBoolean("BILIINDIRECTA");
        Boolean Prot = rs.getBoolean("PROTEINAS_TOTALES");
        Boolean Alb = rs.getBoolean("ALBUMINA");
        Boolean Glob = rs.getBoolean("GLOBULINAS");
        Boolean Rag = rs.getBoolean("RELACION");
        Boolean TGO = rs.getBoolean("TGO");
        Boolean TGP = rs.getBoolean("TGP");
        
        Boolean Cal = rs.getBoolean("CALCIO");
        Boolean Fosf = rs.getBoolean("FOSFORO");
        Boolean Mag = rs.getBoolean("MAGNESIO");
        Boolean GGT = rs.getBoolean("GGT");
        Boolean ALP = rs.getBoolean("FOSFATASA");
        Boolean LDH = rs.getBoolean("LDH");
        Boolean Ami = rs.getBoolean("AMILASA");
        Boolean Lip = rs.getBoolean("LIPASA");
        Boolean Pcr = rs.getBoolean("CK");
        
        
        
        
        PdfPTable Tablaquimica = new PdfPTable(9);
        Tablaquimica.setWidthPercentage(100);
        float[] medidaCeldas2 = {2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f,2f};
        Tablaquimica.setWidths(medidaCeldas2);
        Tablaquimica.setHorizontalAlignment(Element.ALIGN_LEFT);
        Tablaquimica.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        

        
        
        
        List<Boolean> condiciones = Arrays.asList(glicemia, urea, creatinina, au, colesterol, triglice,  hdl, ldl, vldl);
        List<Boolean> condiciones1 = Arrays.asList(BT, BD, BI, Prot, Alb, Glob, Rag, TGO, TGP);
        List<Boolean> condiciones2 = Arrays.asList(Cal, Fosf, Mag, GGT, ALP, LDH, Ami, Lip, Pcr);
        String[] etiquetas = {"Gli:", "Urea:", "Crea:", "A.U:", "Colt:", "Trig:", "HLDc:", "LDLc:", "VDLl:"};
        String[] etiquetas1 = {"BT:", "BD:", "BI:", "Prot:", "Alb:", "Glob:", "Ra/g:", "TGO:", "TGP:"};
        String[] etiquetas2 = {"Cal:", "Fosf:", "Mag:", "GGT:", "ALP:", "LDH:", "Ami:", "Lip:", "Pcr:"};
        
    for (int i = 0; i < condiciones.size(); i++) {
    boolean condicion = condiciones.get(i);
    Paragraph t2columna = new Paragraph(etiquetas[i]);
    t2columna.getFont().setStyle(Font.BOLD);
    t2columna.getFont().setSize(9);
    t2columna.setFont(Letra);
    t2columna.setAlignment(Element.ALIGN_LEFT);
    PdfPCell cell = new PdfPCell(t2columna);
    
    if (condicion) {
        cell.setBackgroundColor(BaseColor.YELLOW); 
    }
    if(!glicemia && !urea && !creatinina && !au && !colesterol && !triglice && !hdl && !ldl && !vldl ){ }
    else{Tablaquimica.addCell(cell);}
}

for (int i = 0; i < condiciones1.size(); i++) {
    boolean condicion1 = condiciones1.get(i);
    Paragraph t3columna = new Paragraph(etiquetas1[i]);
    t3columna.getFont().setStyle(Font.BOLD);
    t3columna.getFont().setSize(9);
    t3columna.setFont(Letra);
    t3columna.setAlignment(Element.ALIGN_LEFT);
    PdfPCell cell1 = new PdfPCell(t3columna);
    
    if (condicion1) {
        cell1.setBackgroundColor(BaseColor.YELLOW); 
    }
    
    if(!BT && !BD && !BI && !Prot && !Alb && !Glob && !Rag && !TGO && !TGP ){ }
    else{Tablaquimica.addCell(cell1);}
}

for (int i = 0; i < condiciones2.size(); i++) {
    boolean condicion2 = condiciones2.get(i);
    Paragraph t4columna = new Paragraph(etiquetas2[i]);
    t4columna.getFont().setStyle(Font.BOLD);
    t4columna.getFont().setSize(9);
    t4columna.setFont(Letra);
    t4columna.setAlignment(Element.ALIGN_LEFT);
    PdfPCell cell2 = new PdfPCell(t4columna);
    
    if (condicion2) {
        cell2.setBackgroundColor(BaseColor.YELLOW); 
    }
    
    
    if(!Cal && !Fosf && !Mag && !GGT && !ALP && !LDH && !Ami && !Lip && !Pcr ){ }
    else{Tablaquimica.addCell(cell2);}
   
}
        
        

        
        
        
        
        
        
        
        
        PdfPTable TablaquimicaP = new PdfPTable(3);
        TablaquimicaP.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        TablaquimicaP.setWidthPercentage(100);
        float[] medidaCeldas0 = {2f, 2f, 2f};
        TablaquimicaP.setWidths(medidaCeldas0);
        TablaquimicaP.setHorizontalAlignment(Element.ALIGN_CENTER);
        TablaquimicaP.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph t0columna1 = new Paragraph("N ORDEN "+ lugarh);
        t0columna1.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna1.getFont().setSize(8);
        t0columna1.setFont(Letra);
        t0columna1.setAlignment(Element.ALIGN_LEFT);
        TablaquimicaP.addCell(t0columna1);

        Paragraph t0columna2 = new Paragraph("NOMBRE " +hNombre);
        t0columna2.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna2.getFont().setSize(8);
        t0columna2.setFont(Letra);
        t0columna2.setAlignment(Element.ALIGN_LEFT);
        TablaquimicaP.addCell(t0columna2);

        Paragraph t0columna4 = new Paragraph("CÉDULA "+ hCedula);
        t0columna4.getFont().setStyle(com.itextpdf.text.Font.NORMAL);
        t0columna4.getFont().setSize(8);
        t0columna4.setFont(Letra);
        t0columna4.setAlignment(Element.ALIGN_LEFT);
        TablaquimicaP.addCell(t0columna4);
                
        doc.add(TablaquimicaP);
        doc.add(Tablaquimica);       
            }

             } catch (Exception e) {
            System.out.println(e);
        } 
              finally {
            closeResources(rs, ps, con);
        }
        
        
        
        


        doc.close();
        archivo.close();
        Desktop.getDesktop().open(file);
        
      
        
        
    } catch (DocumentException | IOException e) {
        System.out.println(e);
   //     JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}



   void limpiarTabla() {
       
  DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();  
  Tabla.setRowCount(0);
    }

   
     void limpiarTablaOrden() {
         DefaultTableModel model = (DefaultTableModel) jOrden.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
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
      FechaNacimiento.setCalendar(null);
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
            ob[8] = lista.get(i).getFechaN();
            modelo.addRow(ob);

        }
       JTablePaciente.setModel(modelo);
        
            
  
     JTablePaciente.setDefaultEditor(Object.class, null);
     JTablePaciente.getTableHeader().setReorderingAllowed(false);
     
     DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) JTablePaciente.getTableHeader().getDefaultRenderer();
     headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    
    

    
      public void AgregarPaciente(){                                             
    EnlaceBd cn = new EnlaceBd();
    try (Connection con = cn.getConnection();
     PreparedStatement ps = con.prepareStatement("INSERT INTO table_paciente (Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo, FechNacimiento, Edad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

    if (TXTPnombre.getText().isEmpty() || TXTPapellido.getText().isEmpty() || TXTPcedula.getText().isEmpty() ||
        TXTPtelefono.getText().isEmpty() || TXTPcorreo.getText().isEmpty() || TXTPdireccion.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    java.util.Date fechaN = FechaNacimiento.getDate();
    java.sql.Date fechaU = new java.sql.Date(fechaN.getTime());
    LocalDate fechaLocalN = fechaN.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate fechaLocalActual = LocalDate.now();
    int edad = Period.between(fechaLocalN, fechaLocalActual).getYears();

    ps.setString(1, TXTPnombre.getText());
    ps.setString(2, TXTPapellido.getText());
    ps.setString(3, TXTPcedula.getText());
    ps.setString(4, TXTPtelefono.getText());
    ps.setString(5, TXTPcorreo.getText());
    ps.setString(6, TXTPdireccion.getText());
    ps.setString(7, Sexo);
    ps.setDate(8, fechaU);
    ps.setInt(9, edad);

    ps.executeUpdate();
    AuditoriaAgregar();
    limpiarTabla();
    listarPacientes();
    limpiarCampos();

    JOptionPane.showMessageDialog(null, "EL PACIENTE HA SIDO REGISTRADO", "REGISTRO DE PACIENTES", JOptionPane.INFORMATION_MESSAGE);
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.getMessage(), "Error al registrar paciente", JOptionPane.ERROR_MESSAGE);
}

    } 
      
      
      

      
      
      
     
 public void ActualizarPaciente() {
    String sql = "UPDATE table_paciente SET Nombre=?, Apellido=?, Cedula=?, Telefono=?, Correo=?, Direccion=?, Sexo=?, FechNacimiento=?, Edad=? WHERE Idpaciente=?";
    
    // Usamos try-with-resources para gestionar la conexión y PreparedStatement automáticamente
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Obtener fecha de nacimiento y calcular la edad
        java.sql.Date fechaU = new java.sql.Date(FechaNacimiento.getDate().getTime());
        String an_actual = Validar.fecha_actual();
        String an_paciente = new SimpleDateFormat("yyyy").format(FechaNacimiento.getDate());
        int edad = Integer.parseInt(an_actual) - Integer.parseInt(an_paciente);
        
        // Establecer los parámetros de la consulta
        ps.setString(1, TXTPnombre.getText());
        ps.setString(2, TXTPapellido.getText());
        ps.setString(3, TXTPcedula.getText());
        ps.setString(4, TXTPtelefono.getText());
        ps.setString(5, TXTPcorreo.getText());
        ps.setString(6, TXTPdireccion.getText());
        ps.setString(7, Sexo);
        ps.setDate(8, fechaU);
        ps.setInt(9, edad);
        ps.setInt(10, idPaciente);

        // Ejecutar la actualización
        int res = ps.executeUpdate();
        
        // Verificar si se actualizó correctamente
        if (res >= 1) {
            JOptionPane.showMessageDialog(null, "PACIENTE ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", JOptionPane.INFORMATION_MESSAGE);
            limpiarTabla();
            listarPacientes();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        // Captura de excepciones generales (por ejemplo, fecha inválida)
        System.out.println("Error inesperado: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "NO PUEDE INGRESAR UNA CÉDULA DUPLICADA O FECHA INVÁLIDA", "DATOS DUPLICADOS", JOptionPane.ERROR_MESSAGE);
    }
}
      
      
   
         
  public void listarOrden() {
    List<JCPacientes> lista = listarOrdenp();
    modelo = (DefaultTableModel) jOrden.getModel();
    Object[] ob = new Object[5]; // Cambié 10 a 3 porque solo estás mostrando 3 columnas

    for (int i = 0; i < lista.size(); i++) {
        ob[0] = lista.get(i).getOrden();
        ob[1] = lista.get(i).getFactura();
        ob[2] = lista.get(i).getPacienteOrden();
        ob[3] = lista.get(i).getCedulaorden();
        ob[4] = lista.get(i).getIdOrden();
   //     ob[2] = lista.get(i).getFechaOrden();
        modelo.addRow(ob);
    }
    jOrden.setModel(modelo);
    jOrden.setDefaultEditor(Object.class, null);
    jOrden.getTableHeader().setReorderingAllowed(false);
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jOrden.getTableHeader().getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    
    acomodarOrden();
}

  
public List<JCPacientes> listarOrdenp() {
    // Usamos try-with-resources para asegurarnos de que los recursos se cierren correctamente
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOrden.getDate());
    String sql = "SELECT id_orden, lugar, cedula, nombre, apellido, fecha, Factura FROM `orden_lab` o " +
                 "INNER JOIN table_paciente n ON o.paciente = n.Idpaciente " +
                 "WHERE fecha=? ORDER BY lugar ASC";

    List<JCPacientes> lista = new ArrayList<>();
    
    try (Connection con = EnlaceBd.getConnection();  // Gestionado automáticamente por HikariCP
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecer parámetros
        ps.setString(1, fecha);
        
        try (ResultSet rs = ps.executeQuery()) {  // ResultSet también se cierra automáticamente
            while (rs.next()) {
                JCPacientes p = new JCPacientes();
                p.setIdOrden(rs.getInt("id_orden"));
                p.setOrden(rs.getInt("lugar"));
                p.setCedulaorden(rs.getString("cedula"));
                p.setPacienteOrden(rs.getString("nombre") + " " + rs.getString("apellido"));
                p.setFechaOrden(rs.getString("fecha"));
                p.setFactura(rs.getString("Factura"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el ResultSet: " + e.getMessage());
        }

    } catch (SQLException e) {
        System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
    }
    
    return lista;
}






    public void agregarOrden() {                                             
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        JTextField textoNumeroOrden = new JTextField(10);
        JTextField textoNumeroFactura = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Número de Orden:"));
        panel.add(textoNumeroOrden);
        panel.add(new JLabel("Número de Factura:"));
        panel.add(textoNumeroFactura);

        int result = JOptionPane.showConfirmDialog(null, panel, "Asignar al paciente: "+nombreAsignar, JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String numeroOrden = textoNumeroOrden.getText().trim();
            String numeroFactura = textoNumeroFactura.getText().trim();
            String fecha = new SimpleDateFormat("yyyy/MM/dd").format(FechaOrden.getDate());

            int row = jOrden.getRowCount();
            Object[] content = new Object[row];
    
           for (int i = 0; i < row; i++) {
           content[i] = jOrden.getValueAt(i, 0);
           //System.out.println(content[i]);
           }
    
           boolean exist = false;
    
           for (Object item : content) {
           if (item != null && item.toString().equals(numeroOrden.trim())) {
            exist = true;
            break; // Salir del bucle si encuentra una coincidencia
           }
          }
    
    
    
    
    
          int rowCed = jOrden.getRowCount();
          Object[] contentCed = new Object[rowCed];
          for (int i = 0; i < row; i++) {
          contentCed[i] = jOrden.getValueAt(i, 3);
         }
         Object value_to_findCed= cedulaPaciente;
         boolean existCed = Arrays.asList(contentCed).contains(value_to_findCed);
    

            
            
           

            if (exist) {
                JOptionPane.showMessageDialog(null, "ESTE NUMERO DE ORDEN YA HA SIDO ASIGNADO A OTRO PACIENTE", "ASIGNAR", JOptionPane.ERROR_MESSAGE);
            } else if (existCed) {
                JOptionPane.showMessageDialog(null, "NO PUEDE REGISTRAR EL PACIENTE DOS VECES", "ASIGNAR", JOptionPane.ERROR_MESSAGE);
            } else if (numeroOrden.length() == 0 || numeroFactura.length() == 0) {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR AMBOS NÚMEROS", "ASIGNAR", JOptionPane.ERROR_MESSAGE);
            } else if (numeroOrden.length() <= 12) {
                String sql = "INSERT INTO `orden_lab`(`lugar`, `paciente`, `fecha`, `Factura`) VALUES (?,?,?,?)";
                con = cn.getConnection();
                ps = con.prepareStatement(sql);

                ps.setString(1, numeroOrden);
                ps.setInt(2, idPaciente);
                ps.setString(3, fecha);
                ps.setString(4, numeroFactura);

                ps.execute();

                limpiarTablaOrden();
                listarOrden();
                JOptionPane.showMessageDialog(null, "PACIENTE ASIGNADO NÚMERO DE ORDEN: " + numeroOrden + " y NÚMERO DE FACTURA: " + numeroFactura, "ORDEN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "EL CAMPO DE NÚMERO DE ORDEN NO PUEDE EXCEDER 4 DIGITOS", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "SOLO PUEDE INGRESAR NUMEROS ERROR: "+e);
        System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
        }
}
 


public void knowAboutOrder() {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOrden.getDate());
    String sql = "SELECT MAX(lugar) AS max_lugar FROM orden_lab WHERE fecha=?";

    // Usamos try-with-resources para manejar los recursos automáticamente
    try (Connection con = EnlaceBd.getConnection();  // Gestión automática por HikariCP
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Establecemos el parámetro
        ps.setString(1, fecha);

        try (ResultSet rs = ps.executeQuery()) {  // ResultSet también se cierra automáticamente
            if (rs.next()) {
                idorden = rs.getInt("max_lugar");
                System.out.println("Último valor de lugar: " + idorden);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el ResultSet: " + e.getMessage());
        }

    } catch (SQLException e) {
        System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
    }
}



public void actualizarOrden(String lugar, String factura) {
    String sql = "UPDATE `orden_lab` SET `lugar`=?, `Factura`=? WHERE `id_orden`=?";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        

        ps.setString(1, lugar);
        ps.setString(2, factura);
        ps.setInt(3, orden); 
        

        int res = ps.executeUpdate();
        
        if (res >= 1) {
            JOptionPane.showMessageDialog(null, "PACIENTE ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", JOptionPane.INFORMATION_MESSAGE);
            limpiarTablaOrden();
            listarOrden();
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        System.out.println("Error al actualizar la orden: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA ORDEN", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

   
   
   
public void Search() {
    String busqueda = this.TXTBcedula.getText().trim(); // Obtener texto de búsqueda
    
    // Consulta SQL para buscar por cédula, nombre o apellido
    String sql = "SELECT Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo, DATE_FORMAT(FechNacimiento, '%d/%m/%Y') AS Fecha " +
                 "FROM table_paciente " +
                 "WHERE Cedula LIKE ? OR CONCAT(Nombre, ' ', Apellido) LIKE ?";
    
    // Declaración del modelo de la tabla
    DefaultTableModel tabla = (DefaultTableModel) JTablePaciente.getModel();
    tabla.setRowCount(0); // Limpiar la tabla antes de agregar datos
    
    // Usamos try-with-resources para manejar la conexión, PreparedStatement y ResultSet
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Configurar parámetros de búsqueda
        ps.setString(1, "%" + busqueda + "%"); // Cédula
        ps.setString(2, "%" + busqueda + "%"); // Nombre y Apellido combinados
        
        // Ejecutar consulta
        try (ResultSet rs = ps.executeQuery()) {
            // Procesar resultados
            while (rs.next()) {
                // Obtener datos de la consulta
                Object[] row = new Object[] {
                    rs.getInt("Idpaciente"), // Idpaciente
                    rs.getString("Nombre"),  // Nombre
                    rs.getString("Apellido"), // Apellido
                    rs.getString("Cedula"), // Cedula
                    rs.getString("Telefono"), // Telefono
                    rs.getString("Correo"), // Correo
                    rs.getString("Direccion"), // Direccion
                    rs.getString("Sexo"), // Sexo
                    rs.getString("Fecha") // Fecha de nacimiento
                };
                
                // Agregar datos a la tabla
                tabla.addRow(row);
            }
        }
        
        // Otras configuraciones de la tabla
        JTablePaciente.getTableHeader().setReorderingAllowed(false);
        acomodarceldas(); // Ajustar celdas si es necesario
        
    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.getMessage());
    }
}
      
      
      
      
      
      
        
    public void searchOrder() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        EnlaceBd cn = new EnlaceBd();
        con = cn.getConnection();
        
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOrden.getDate());
        String busqueda = this.TXTBcedula2.getText();
   //  System.out.println(fecha);
        // Evita la concatenación de cadenas en la consulta SQL para prevenir SQL Injection
        String sql = "SELECT id_orden, lugar, cedula, nombre, apellido, fecha, Factura " +
                     "FROM orden_lab o " +
                     "INNER JOIN table_paciente n " +
                     "ON o.paciente = n.Idpaciente " +
                     "WHERE Cedula LIKE ? AND fecha = ? Order by lugar asc";
        
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + busqueda + "%");
        ps.setString(2, fecha);
        
        rs = ps.executeQuery();

        DefaultTableModel tabla = (DefaultTableModel) jOrden.getModel();
        tabla.setRowCount(0); // Limpia la tabla antes de agregar datos

        while (rs.next()) {
       
            int lugar = rs.getInt("lugar");
            String cedula = rs.getString("cedula");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String factura = rs.getString("Factura");
            String nombreCompleto = nombre + " " + apellido;

            Object[] rowData = {lugar, factura ,nombreCompleto,  cedula};
            tabla.addRow(rowData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cierra todas las conexiones y recursos en un bloque finally
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
      
      
      
      
      
      
      
      
       
public void eliminarOrden() {
    String sql = "DELETE FROM orden_lab WHERE lugar = ?"; 

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Obtén las filas seleccionadas
        DefaultTableModel modelo = (DefaultTableModel) jOrden.getModel();
        int columnaId = 0; // Índice de la columna que contiene el ID en la tabla
        int[] filasSeleccionadas = jOrden.getSelectedRows();

        // Itera sobre las filas seleccionadas y elimina las órdenes correspondientes
        for (int fila : filasSeleccionadas) {
            int idorden = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID de la orden
            ps.setInt(1, idorden); // Establece el ID en la consulta
            ps.executeUpdate(); // Ejecuta la eliminación
        }

        JOptionPane.showMessageDialog(null, "PROCESO REALIZADO", "ORDENES", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        // Manejo de excepciones
        System.out.println("Error al eliminar las órdenes: " + e);
        JOptionPane.showMessageDialog(null, "ERROR AL QUITAR DEL PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}  
      
      
      
      
   public void AuditoriaAgregar() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) VALUES (?, ?, ?, ?)";
    String accion = "HORA: " + Menu.Time.getText() + " Agrego al paciente: " + TXTPnombre.getText() + " " + TXTPapellido.getText() + " CI " + TXTPcedula.getText();
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());

    // Usamos try-with-resources para manejar automáticamente el cierre de recursos
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, idusuario);
        ps.setInt(2, idusuario);  // Asegúrate de que este sea el valor correcto
        ps.setString(3, accion);
        ps.setString(4, fecha);
        
        ps.executeUpdate();
        
    } catch (Exception e) {
        System.out.println(e + " - Error al insertar auditoría");
    }
}
      
 public void AuditoriaEliminar() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) VALUES (?, ?, ?, ?)";
    String accion = "Quito al paciente de la lista orden: " + nombreCompleto + " C.I: " + cedulaOrden;
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());

    // Usamos try-with-resources para manejar automáticamente el cierre de recursos
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, idusuario);
        ps.setInt(2, idusuario);  // Asegúrate de que este sea el valor correcto
        ps.setString(3, accion);
        ps.setString(4, fecha);
        
        ps.executeUpdate();
        
    } catch (Exception e) {
        System.out.println(e + " - Error al insertar auditoría");
    }
}

        
        
   public void auditoriaEliminarPaciente() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) VALUES (?, ?, ?, ?)";
    String nombreCompleto = TXTPnombre.getText() + " " + TXTPapellido.getText();
    String accion = "HORA: " + Menu.Time.getText() + " Elimino al paciente de la bd: " + nombreCompleto;
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
    
    // Usamos try-with-resources para manejar automáticamente el cierre de recursos
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, idusuario);
        ps.setInt(2, idusuario);  // Asegúrate de que este sea el valor correcto
        ps.setString(3, accion);
        ps.setString(4, fecha);
        
        ps.executeUpdate();
        
    } catch (Exception e) {
        System.out.println(e + " - Error al insertar auditoría");
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
        
        
        
 public void AuditoriaModificar() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) VALUES (?, ?, ?, ?)";
    String accion = "HORA: " + Menu.Time.getText() + " Modifico al paciente: " + nombreAsignar + " CI: " + cedulaPaciente;
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
    
    // Usamos try-with-resources para manejar automáticamente el cierre de recursos
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, idusuario);
        ps.setInt(2, idusuario);  // Asegúrate de que este sea el valor correcto
        ps.setString(3, accion);
        ps.setString(4, fecha);
        
        ps.executeUpdate();
        
    } catch (Exception e) {
        System.out.println(e + " - Error al insertar auditoría");
    }
}
      

      
       public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTablePaciente.setRowHeight(20);
        
 
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("NOMBRE")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("APELLIDO")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CEDULA")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("TELEFONO")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CORREO")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("DIRECCIÓN")).setPreferredWidth(70);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("SEXO")).setPreferredWidth(60);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("FEC_NACIMIENTO")).setPreferredWidth(60);
         
        
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("NOMBRE")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("APELLIDO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CEDULA")).setCellRenderer(Alinear);;
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("TELEFONO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("CORREO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("DIRECCIÓN")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("SEXO")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("FEC_NACIMIENTO")).setCellRenderer(Alinear);
    
    }
            
       
         public void acomodarOrden()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jOrden.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jOrden.setRowHeight(20);
        


         
       jOrden.getColumnModel().getColumn(4).setMaxWidth(0);
       jOrden.getColumnModel().getColumn(4).setMinWidth(0);
       jOrden.getColumnModel().getColumn(4).setPreferredWidth(0);
        
       
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Orden")).setPreferredWidth(30);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Factura")).setPreferredWidth(80);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Paciente")).setPreferredWidth(400);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Cédula")).setPreferredWidth(80);

         
        
      
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Orden")).setCellRenderer(Alinear);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Factura")).setCellRenderer(Alinear);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Paciente")).setCellRenderer(Alinear);
        jOrden.getColumnModel().getColumn(Tabla.findColumn("Cédula")).setCellRenderer(Alinear);
   
       

    
    }
       
       
       
    public void eliminarPaciente() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

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
                JOptionPane.showMessageDialog(null, "EL PACIENTE HA SIDO ELIMINADO", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                listarPacientes();
                conteoTablatotal();
                auditoriaEliminarPaciente();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR PACIENTE", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
      
    
        public void conteoTablaOrden(){
       
       for(int x =0; x<=jOrden.getRowCount(); x++ ){
       jLabel4.setText(""+x);
       
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
    private com.toedter.calendar.JDateChooser FechaNacimiento;
    private com.toedter.calendar.JDateChooser FechaOrden;
    private javax.swing.JRadioButton JRMasculino;
    private javax.swing.JRadioButton JRfemenino;
    private javax.swing.JTable JTablePaciente;
    private javax.swing.JMenuItem Modf;
    private javax.swing.JMenuItem Orden;
    private javax.swing.JTextField TXTBcedula;
    private javax.swing.JTextField TXTBcedula2;
    private javax.swing.JTextField TXTPapellido;
    private javax.swing.JTextField TXTPcedula;
    private javax.swing.JTextField TXTPcorreo;
    private javax.swing.JTextArea TXTPdireccion;
    private javax.swing.JTextField TXTPnombre;
    private javax.swing.JFormattedTextField TXTPtelefono;
    private javax.swing.JMenuItem deletedOrden;
    private javax.swing.JButton hojaTrabajo;
    private javax.swing.JMenu jEstudios;
    private javax.swing.JMenuItem jHematologia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTable jOrden;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JMenuItem jQuimica;
    private javax.swing.JRadioButton jRllegada;
    private javax.swing.JRadioButton jRpacientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPopupMenu jTableOrden;
    private javax.swing.JTabbedPane jTabpacientes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
