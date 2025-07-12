/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import Registros.*;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JCProcedimientos;
import Clases.JCProcedimientosDao;
import Clases.JCambiarState;
import Clases.JPacientesDao;
import Clases.LlenarCombobox;
import Clases.Temporal;
import Clases.Validar;
import Consultas.ConsultaLaboratorio;
import Menu.Mprincipal;
import com.itextpdf.text.BadElementException;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author FCGinebraI
 */
public class JHorariosDoctores extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JHorariosDoctores() {
        initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
        FechaOne1.setCalendar(Fecha);
        FechaOne.setCalendar(Fecha);
        FechaTwo.setCalendar(Fecha);

        
        
        
        
        llenarCombo();
        llenarDrs();
        jResumen.setSelected(true);
        jRadioDoctores.setSelected(true);
        hideItems();
        limpiarTabla(); 
        listarHorarios();
        textES();
        informacionpdf();
        BtnAg.setVisible(false);
        BtnModif.setVisible(false);
        BtnLimpiar1.setVisible(false);
        jRadioAm.setSelected(true);
        jTabbedPanel.setBorder(BorderFactory.createEmptyBorder());
        
             String Nivel=TM.getNivel();
             if(Nivel.equals("Administrador")){
             Activar.setEnabled(true); 
             Desactivar.setEnabled(true);
             BtnAg.setVisible(true);
             BtnModif.setVisible(true);
             BtnLimpiar1.setVisible(true);
             
         }
             
             
             
    
             
             
    }

  
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPopup = new javax.swing.JPopupMenu();
        Activar = new javax.swing.JMenuItem();
        Desactivar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        FechaAc3 = new javax.swing.JLabel();
        jRadioDoctores = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPanel = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTextEntrada = new javax.swing.JTextField();
        jTextSalida = new javax.swing.JTextField();
        JComboEspecialidad1 = new javax.swing.JComboBox<>();
        BtnAg = new javax.swing.JButton();
        BtnModif = new javax.swing.JButton();
        BtnLimpiar1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextObservacion = new javax.swing.JTextArea();
        jSeparator8 = new javax.swing.JSeparator();
        jCheckDomingo = new javax.swing.JCheckBox();
        jCheckLunes = new javax.swing.JCheckBox();
        jCheckMartes = new javax.swing.JCheckBox();
        jCheckMiercoles = new javax.swing.JCheckBox();
        jCheckJueves = new javax.swing.JCheckBox();
        jCheckViernes = new javax.swing.JCheckBox();
        jCheckSabado = new javax.swing.JCheckBox();
        JComboDoctor = new javax.swing.JComboBox<>();
        jRadioAll = new javax.swing.JRadioButton();
        jRadioAm = new javax.swing.JRadioButton();
        jRadioPm = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        FechaOne1 = new com.toedter.calendar.JDateChooser();
        BtnAg2 = new javax.swing.JButton();
        BtnModif2 = new javax.swing.JButton();
        BtnLimpiar3 = new javax.swing.JButton();
        jTextEntrada1 = new javax.swing.JTextField();
        jTextSalida1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTextObs = new javax.swing.JTextArea();
        jSeparator7 = new javax.swing.JSeparator();
        JComboEspecialidad3 = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        JTextretraso = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        TXTfindbyName = new javax.swing.JTextField();
        FechaTwo = new com.toedter.calendar.JDateChooser();
        FechaOne = new com.toedter.calendar.JDateChooser();
        jSearch = new javax.swing.JButton();
        jRadiaTab = new javax.swing.JRadioButton();
        jRadioEsta = new javax.swing.JRadioButton();
        jRadioEsta1 = new javax.swing.JRadioButton();
        jSeparator6 = new javax.swing.JSeparator();
        jReporte1 = new javax.swing.JButton();
        jResumen = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableHorario = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableHorario1 = new javax.swing.JTable();

        MenuPopup.setEnabled(false);

        Activar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editar.png"))); // NOI18N
        Activar.setText("Activar");
        Activar.setEnabled(false);
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Activar);

        Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/basura.png"))); // NOI18N
        Desactivar.setText("Desactivar");
        Desactivar.setEnabled(false);
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

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc3.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc3.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc3.setText("M Ó D U L O S");
        jPanel5.add(FechaAc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 110, -1));

        jRadioDoctores.setBackground(new java.awt.Color(255, 255, 255));
        jRadioDoctores.setText("AGREGAR DOCTORES");
        jRadioDoctores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDoctoresActionPerformed(evt);
            }
        });
        jPanel5.add(jRadioDoctores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 370, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPanel.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPanel.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hora entrada"));
        jTextEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextEntradaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextEntradaFocusLost(evt);
            }
        });
        jTextEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextEntradaKeyTyped(evt);
            }
        });
        jPanel6.add(jTextEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, 50));

        jTextSalida.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hora Salida"));
        jTextSalida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextSalidaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextSalidaFocusLost(evt);
            }
        });
        jTextSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextSalidaKeyTyped(evt);
            }
        });
        jPanel6.add(jTextSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 240, 50));

        JComboEspecialidad1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Especialidad"));
        JComboEspecialidad1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboEspecialidad1ItemStateChanged(evt);
            }
        });
        jPanel6.add(JComboEspecialidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 50));

        BtnAg.setText("Agregar");
        BtnAg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAg.setContentAreaFilled(false);
        BtnAg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgActionPerformed(evt);
            }
        });
        jPanel6.add(BtnAg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 80, 40));

        BtnModif.setText("Modificar");
        BtnModif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModif.setContentAreaFilled(false);
        BtnModif.setEnabled(false);
        BtnModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifActionPerformed(evt);
            }
        });
        jPanel6.add(BtnModif, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 80, 40));

        BtnLimpiar1.setText("Nuevo");
        BtnLimpiar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar1.setContentAreaFilled(false);
        BtnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiar1ActionPerformed(evt);
            }
        });
        jPanel6.add(BtnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 80, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/magnifier-1_icon-icons.com_56924.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 70, 50));

        jTextObservacion.setColumns(20);
        jTextObservacion.setLineWrap(true);
        jTextObservacion.setRows(5);
        jTextObservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Observación"));
        jScrollPane6.setViewportView(jTextObservacion);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 300, 90));
        jPanel6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 360, 20));

        jCheckDomingo.setBackground(new java.awt.Color(255, 255, 255));
        jCheckDomingo.setText("Domingo");
        jCheckDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckDomingoActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckDomingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        jCheckLunes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckLunes.setText("Lunes");
        jCheckLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckLunesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jCheckMartes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckMartes.setText("Martes");
        jCheckMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckMartesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jCheckMiercoles.setBackground(new java.awt.Color(255, 255, 255));
        jCheckMiercoles.setText("Miércoles");
        jCheckMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckMiercolesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, -1, -1));

        jCheckJueves.setBackground(new java.awt.Color(255, 255, 255));
        jCheckJueves.setText("Jueves");
        jCheckJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckJuevesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, -1, -1));

        jCheckViernes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckViernes.setText("Viernes");
        jCheckViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckViernesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jCheckSabado.setBackground(new java.awt.Color(255, 255, 255));
        jCheckSabado.setText("Sabado");
        jCheckSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSabadoActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        JComboDoctor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Especialista"));
        jPanel6.add(JComboDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 300, 50));

        jRadioAll.setBackground(new java.awt.Color(255, 255, 255));
        jRadioAll.setText("24H");
        jRadioAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAllActionPerformed(evt);
            }
        });
        jPanel6.add(jRadioAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        jRadioAm.setBackground(new java.awt.Color(255, 255, 255));
        jRadioAm.setText("AM");
        jRadioAm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAmActionPerformed(evt);
            }
        });
        jPanel6.add(jRadioAm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jRadioPm.setBackground(new java.awt.Color(255, 255, 255));
        jRadioPm.setText("PM");
        jRadioPm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioPmActionPerformed(evt);
            }
        });
        jPanel6.add(jRadioPm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, -1));

        jTabbedPanel.addTab("tab1", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaOne1.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne1.setToolTipText("");
        FechaOne1.setDateFormatString("yyyy-MM-dd");
        FechaOne1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel7.add(FechaOne1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, 30));

        BtnAg2.setText("Agregar");
        BtnAg2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAg2.setContentAreaFilled(false);
        BtnAg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAg2ActionPerformed(evt);
            }
        });
        jPanel7.add(BtnAg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 100, 40));

        BtnModif2.setText("Modificar");
        BtnModif2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModif2.setContentAreaFilled(false);
        BtnModif2.setEnabled(false);
        BtnModif2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModif2ActionPerformed(evt);
            }
        });
        jPanel7.add(BtnModif2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 100, 40));

        BtnLimpiar3.setText("Nuevo");
        BtnLimpiar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar3.setContentAreaFilled(false);
        BtnLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiar3ActionPerformed(evt);
            }
        });
        jPanel7.add(BtnLimpiar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 100, 40));

        jTextEntrada1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hora entrada"));
        jTextEntrada1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextEntrada1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextEntrada1FocusLost(evt);
            }
        });
        jTextEntrada1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextEntrada1KeyTyped(evt);
            }
        });
        jPanel7.add(jTextEntrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 140, 50));

        jTextSalida1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hora Salida"));
        jTextSalida1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextSalida1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextSalida1FocusLost(evt);
            }
        });
        jTextSalida1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextSalida1KeyTyped(evt);
            }
        });
        jPanel7.add(jTextSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 140, 50));

        JTextObs.setColumns(20);
        JTextObs.setLineWrap(true);
        JTextObs.setRows(5);
        JTextObs.setBorder(javax.swing.BorderFactory.createTitledBorder("Observación"));
        jScrollPane4.setViewportView(JTextObs);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 300, 90));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 360, 20));

        JComboEspecialidad3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doctor/a"));
        JComboEspecialidad3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboEspecialidad3ItemStateChanged(evt);
            }
        });
        jPanel7.add(JComboEspecialidad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 300, 50));

        JTextretraso.setColumns(20);
        JTextretraso.setLineWrap(true);
        JTextretraso.setRows(5);
        JTextretraso.setBorder(javax.swing.BorderFactory.createTitledBorder("Motivo retraso"));
        jScrollPane8.setViewportView(JTextretraso);

        jPanel7.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 300, 90));

        jTabbedPanel.addTab("tab2", jPanel7);

        jPanel2.add(jTabbedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 570));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 340, 540));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTfindbyName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Especialista"));
        TXTfindbyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyTyped(evt);
            }
        });
        jPanel4.add(TXTfindbyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 50));

        FechaTwo.setBackground(new java.awt.Color(255, 255, 255));
        FechaTwo.setToolTipText("");
        FechaTwo.setDateFormatString("yyyy-MM-dd");
        FechaTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.add(FechaTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 140, 40));

        FechaOne.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne.setToolTipText("");
        FechaOne.setDateFormatString("yyyy-MM-dd");
        FechaOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.add(FechaOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 150, 40));

        jSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/magnifier-1_icon-icons.com_56924.png"))); // NOI18N
        jSearch.setContentAreaFilled(false);
        jSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchActionPerformed(evt);
            }
        });
        jPanel4.add(jSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 60, 50));

        jRadiaTab.setBackground(new java.awt.Color(255, 255, 255));
        jRadiaTab.setText("Tabla");
        jRadiaTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadiaTabActionPerformed(evt);
            }
        });
        jPanel4.add(jRadiaTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 90, -1));

        jRadioEsta.setBackground(new java.awt.Color(255, 255, 255));
        jRadioEsta.setText("Entradas");
        jRadioEsta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioEstaActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioEsta, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 90, -1));

        jRadioEsta1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioEsta1.setText("Salidas");
        jRadioEsta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioEsta1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioEsta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 90, -1));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 20, 90));

        jReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jReporte1.setToolTipText("Exportar estadística de estudios realizados");
        jReporte1.setContentAreaFilled(false);
        jReporte1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReporte1ActionPerformed(evt);
            }
        });
        jPanel4.add(jReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 70, 50));

        jResumen.setBackground(new java.awt.Color(255, 255, 255));
        jResumen.setText("Resumen");
        jResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResumenActionPerformed(evt);
            }
        });
        jPanel4.add(jResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 90, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 920, 100));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTableHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ESPECIALIDAD", "DOCTOR", "ENTRADA", "SALIDA", "AM/PM", "DIAS", "ESTADO", "OBSERVACION"
            }
        ));
        JTableHorario.setComponentPopupMenu(MenuPopup);
        JTableHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableHorarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableHorario);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 920, 500));

        jTabbedPane1.addTab("tab1", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        JTableHorario1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DOCTOR", "ENTRADA", "DIF. ENTRADA", "SALIDA", "DIF. SALIDA", "MOTIVO RETRASO", "OBSERVACION"
            }
        ));
        JTableHorario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableHorario1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JTableHorario1MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(JTableHorario1);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 920, 490));

        jTabbedPane1.addTab("tab2", jPanel9);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 920, 540));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTfindbyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyReleased
     
    /*    if(jRadioButton1.isSelected()){
         Search(); 
        
        }
        
        else */if(jRadioDoctores.isSelected()){
        searchHorario();
        }
       
        
    }//GEN-LAST:event_TXTfindbyNameKeyReleased

    private void TXTfindbyNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyTyped
      
    }//GEN-LAST:event_TXTfindbyNameKeyTyped
  
    int idHorarios=0;
    String EstadoTexto = "", nombreprocedimiento, variableTiempo="";
    private void JTableHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorarioMouseClicked
 int fila = JTableHorario.getSelectedRow();
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
    }                                 
    }//GEN-LAST:event_JTableHorarioMouseClicked

    
    
    private void updateTimeRadioButtons(String timeValue) {
    boolean isAm = "AM".equals(timeValue);
    boolean isPm = "PM".equals(timeValue);
    boolean is24h = "24H".equals(timeValue);

    jRadioAm.setSelected(isAm);
    jRadioPm.setSelected(isPm);
    jRadioAll.setSelected(is24h);

    tiempoDay = isAm ? "AM" : isPm ? "PM" : is24h ? "24H" : "";
}
    
    
    
    private String getStringValueOrDefault(Object value) {
    return (value != null) ? value.toString() : "";
}
    
    
    
    
    
    
    
    private void BtnAgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgActionPerformed
      
        
      
        int row = JTableHorario.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = JTableHorario.getValueAt(i, 2);
    }
    Object value_to_find= JComboDoctor.getSelectedItem().toString();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
           if (jTextEntrada.getText().equals("")){
           JOptionPane.showMessageDialog(null, "EL CAMPO HORA DE ENTRADA ES OBLIGATORIO", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
           }
           
           else if (jTextSalida.getText().equals("")){
           JOptionPane.showMessageDialog(null, "EL CAMPO HORA DE SALIDA ES OBLIGATORIO DE QUEDAR VACIO COLOQUE 00:00", "LLENADO DE CAMPO S", JOptionPane.INFORMATION_MESSAGE);
           }
           
           else if (exist){
           JOptionPane.showMessageDialog(null, "ESTE DOCTOR YA SE HA AGREGADO", "DOCTORES", JOptionPane.ERROR_MESSAGE);
            }
           
           else if(!jCheckLunes.isSelected() && !jCheckMartes.isSelected() && !jCheckMiercoles.isSelected() && !jCheckJueves.isSelected()
                   && !jCheckViernes.isSelected() && !jCheckSabado.isSelected() && !jCheckDomingo.isSelected()){
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR LOS DIAS DE CONSULTA", "HORARIO", JOptionPane.ERROR_MESSAGE);
           }
         
        
         
           else{
              auditAgregarHorario();
              agregarHorario(); 
              limpiarTabla(); 
              listarHorarios(); 
              limpiarCampos(); 
            
           }
           
           
         
        
    }//GEN-LAST:event_BtnAgActionPerformed

    private void BtnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiar1ActionPerformed
            BtnModif.setEnabled(false);
            BtnAg.setEnabled(true);
            limpiarTabla(); 
            listarHorarios();
            limpiarCampos(); 
           
            
    }//GEN-LAST:event_BtnLimpiar1ActionPerformed

    private void BtnModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifActionPerformed

   
           if(!jCheckLunes.isSelected() && !jCheckMartes.isSelected() && !jCheckMiercoles.isSelected() && !jCheckJueves.isSelected()
                   && !jCheckViernes.isSelected() && !jCheckSabado.isSelected() && !jCheckDomingo.isSelected()){
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR LOS DIAS DE CONSULTA", "HORARIO", JOptionPane.ERROR_MESSAGE);
           }
    
         
           else{
               
              auditModificarHorario();
              actualizarHorarios();
              limpiarTabla(); 
              listarHorarios();
              limpiarCampos(); 
              BtnModif.setEnabled(false);
              BtnAg.setEnabled(true);
              textES();
             
     
           }
      
    }//GEN-LAST:event_BtnModifActionPerformed

    private void DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarActionPerformed
  if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente inactivar este procedimiento?",
            "INACTIVAR HORARIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableHorario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (EstadoTexto.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "EL HORARIO YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.activarHorario(101,      idHorarios )) {

           JOptionPane.showMessageDialog(null, "EL ESTADO SE HA ACTUALIZADO A : Inactivo", "Estado", 1);
           limpiarTabla();
           listarHorarios(); 
           acomodarceldas();
           auditDesactivar();
             
             
            }
        }

            
            
         }
    }//GEN-LAST:event_DesactivarActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
 if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Activar el procedimiento?",
            "ACTIVAR HORARIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableHorario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (EstadoTexto.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL HORARIO YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.activarHorario(100,     idHorarios  )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO SE HA ACTUALIZADO A : Activo", "Estado", 1);
           limpiarTabla();
          listarHorarios(); 
           acomodarceldas();
           auditActivar();
             
             
            }
        }

            
            
         }
    }//GEN-LAST:event_ActivarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      /*  SearchBetweenDates();
        if(Jtabla.getRowCount()>0){  acomodarceldas(); }

        conteoTabla();
        Jtabla.requestFocusInWindow();*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchActionPerformed
   /*     SearchBetweenDates();
        if(Jtabla.getRowCount()>0){  acomodarceldas(); }

        conteoTabla();
        Jtabla.requestFocusInWindow();*/
   
      limpiarDiferencia();
      listarDiferencia();
   
   
    }//GEN-LAST:event_jSearchActionPerformed

    private void jTextEntradaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEntradaFocusGained
         if (jTextEntrada.getText().equals("00:00")) {
            jTextEntrada.setText(""); // Borrar el placeholder al obtener el foco
         //   jTextField4.setForeground(Color.BLACK); // Restaurar el color del texto
        }
    }//GEN-LAST:event_jTextEntradaFocusGained

    private void jTextEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEntradaFocusLost
         if (jTextEntrada.getText().isEmpty()) {
          //  jTextField4.setForeground(Color.GRAY); // Restaurar el color gris si el campo está vacío
            jTextEntrada.setText("00:00"); // Restaurar el placeholder si el campo está vacío
        }
    }//GEN-LAST:event_jTextEntradaFocusLost

    private void BtnAg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAg2ActionPerformed
 
       
            int row = JTableHorario1.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = JTableHorario1.getValueAt(i, 1);
    }
    Object value_to_find= JComboEspecialidad3.getSelectedItem().toString();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
           if (jTextEntrada1.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "EL CAMPO HORA ENTRADA ES OBLIGATORIO", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
            
           }
           

           else if (exist){
           JOptionPane.showMessageDialog(null, "ESTE DOCTOR YA ESTA REGISTRADO POR HOY", "DOCTORES", JOptionPane.ERROR_MESSAGE);
            }
         
        
         
           else{
             auditAgregarDiferencia();
             calDif();
             agregarDif();
             limpiarDiferencia();
             listarDiferencia();
             limpiarCampos2();  
           }
           
           
       
       
    }//GEN-LAST:event_BtnAg2ActionPerformed

    private void BtnModif2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModif2ActionPerformed
             if (jTextEntrada1.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
 
         
    
         
           else{
               
             auditModificoDiferencia();
              calDif();
              actualizarDiferencia();
              limpiarDiferencia();
              listarDiferencia();
             
              limpiarCampos2(); 
              BtnModif2.setEnabled(false);
              BtnAg2.setEnabled(true);
           
     
           }
      
    }//GEN-LAST:event_BtnModif2ActionPerformed

    private void BtnLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiar3ActionPerformed
       limpiarDiferencia();
       listarDiferencia();
       limpiarCampos2();  
       BtnModif2.setEnabled(false);
       BtnAg2.setEnabled(true); 
    }//GEN-LAST:event_BtnLimpiar3ActionPerformed

    private void jTextEntrada1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEntrada1FocusGained
               if (jTextEntrada1.getText().equals("00:00")) {
            jTextEntrada1.setText(""); //Borrar el placeholder al obtener el foco
         //   jTextField4.setForeground(Color.BLACK); // 
        
    }   
    }//GEN-LAST:event_jTextEntrada1FocusGained

    private void jTextEntrada1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEntrada1FocusLost
            if (jTextEntrada1.getText().isEmpty()) {
          //  jTextField4.setForeground(Color.GRAY); // Restaurar el color gris si el campo está vacío
            jTextEntrada1 .setText("00:00"); // Restaurar el placeholder si el campo está vacío
        }

    }//GEN-LAST:event_jTextEntrada1FocusLost

    private void jRadioDoctoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDoctoresActionPerformed
       this.jRadioDoctores.setSelected(true);
      // jRadioButton1.setSelected(false);
       jTabbedPanel.setSelectedIndex(0);
       jTabbedPane1.setSelectedIndex(0);
       hideItems();
       limpiarTabla(); 
       listarHorarios();
    }//GEN-LAST:event_jRadioDoctoresActionPerformed

    
    int idDiferencia;
    private void JTableHorario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorario1MouseClicked
         int fila = JTableHorario1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            BtnModif2.setEnabled(true);
            BtnAg2.setEnabled(false);
            idDiferencia=(int) (JTableHorario1.getValueAt(fila, 0));
            JComboEspecialidad3.setSelectedItem(JTableHorario1.getValueAt(fila, 1).toString());
            jTextEntrada1.setText(JTableHorario1.getValueAt(fila, 2).toString());
            jTextSalida1.setText(JTableHorario1.getValueAt(fila, 4).toString());
            JTextretraso.setText(JTableHorario1.getValueAt(fila, 6).toString());
            JTextObs.setText(JTableHorario1.getValueAt(fila, 7).toString());
        //    jTextField1.setText(JTableProcedimientos.getValueAt(fila, 1).toString());
       
           
    BtnModif2.setEnabled(true);
    
    }  
    }//GEN-LAST:event_JTableHorario1MouseClicked

    private void jTextSalidaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSalidaFocusGained
             if (jTextSalida.getText().equals("00:00")) {
            jTextSalida.setText(""); // Borrar el placeholder al obtener el foco
         //   jTextField4.setForeground(Color.BLACK); // Restaurar el color del texto
        }
    }//GEN-LAST:event_jTextSalidaFocusGained

    private void jTextSalidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSalidaFocusLost
              if (jTextSalida.getText().isEmpty()) {
          //  jTextField4.setForeground(Color.GRAY); // Restaurar el color gris si el campo está vacío
            jTextSalida.setText("00:00"); // Restaurar el placeholder si el campo está vacío
        }
    }//GEN-LAST:event_jTextSalidaFocusLost

    private void JComboEspecialidad3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboEspecialidad3ItemStateChanged
      if(JComboEspecialidad3.getSelectedIndex()!=-1){
        knowCategory();
          }
    }//GEN-LAST:event_JComboEspecialidad3ItemStateChanged

    private void jTextEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextEntradaKeyTyped
      char car = evt.getKeyChar();
String caracteres = jTextEntrada.getText();


if ((car == ':' || (car >= '0' && car <= '9') || car == (char)KeyEvent.VK_DELETE)) {

    if (caracteres.length() == 5) {
        evt.consume(); // Consumir el evento para evitar la inserción de más caracteres
    }
} else { 
    evt.consume(); 
}
    }//GEN-LAST:event_jTextEntradaKeyTyped

    private void jTextSalidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSalidaKeyTyped
        char car = evt.getKeyChar();
String caracteres = jTextSalida.getText();


if ((car == ':' || (car >= '0' && car <= '9') || car == (char)KeyEvent.VK_DELETE)) {

    if (caracteres.length() == 5) {
        evt.consume(); // Consumir el evento para evitar la inserción de más caracteres
    }
} else { 
    evt.consume(); 
}
    }//GEN-LAST:event_jTextSalidaKeyTyped

    private void jTextEntrada1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextEntrada1KeyTyped
          char car = evt.getKeyChar();
String caracteres = jTextEntrada1.getText();


if ((car == ':' || (car >= '0' && car <= '9') || car == (char)KeyEvent.VK_DELETE)) {

    if (caracteres.length() == 5) {
        evt.consume(); // Consumir el evento para evitar la inserción de más caracteres
    }
} else { 
    evt.consume(); 
}
    }//GEN-LAST:event_jTextEntrada1KeyTyped

    private void jTextSalida1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSalida1KeyTyped
     char car = evt.getKeyChar();
String caracteres = jTextSalida1.getText();


if ((car == ':' || (car >= '0' && car <= '9') || car == (char)KeyEvent.VK_DELETE)) {

    if (caracteres.length() == 5) {
        evt.consume(); // Consumir el evento para evitar la inserción de más caracteres
    }
} else { 
    evt.consume(); 
}
    }//GEN-LAST:event_jTextSalida1KeyTyped

    private void jTextSalida1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSalida1FocusGained
           if(jTextSalida1.getText().equals("00:00")) {
            jTextSalida1.setText(""); //Borrar el placeholder al obtener el foco
         // jTextField4.setForeground(Color.BLACK); // 
        
    } 
    }//GEN-LAST:event_jTextSalida1FocusGained

    private void jTextSalida1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextSalida1FocusLost
            if (jTextSalida1.getText().isEmpty()) {
          //jTextField4.setForeground(Color.GRAY); // Restaurar el color gris si el campo está vacío
            jTextSalida1 .setText("00:00"); // Restaurar el placeholder si el campo está vacío
        }
    }//GEN-LAST:event_jTextSalida1FocusLost

    private void TXTfindbyNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyPressed
       
    }//GEN-LAST:event_TXTfindbyNameKeyPressed

    private void jRadioEstaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioEstaActionPerformed
         jRadioEsta.setSelected(true);
         jRadiaTab.setSelected(false);
         jRadioEsta1.setSelected(false);
         jResumen.setSelected(false);
    }//GEN-LAST:event_jRadioEstaActionPerformed

    private void jRadiaTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadiaTabActionPerformed
         jRadioEsta.setSelected(false);
         jRadiaTab.setSelected(true);
         jRadioEsta1.setSelected(false);
         jResumen.setSelected(false);
    }//GEN-LAST:event_jRadiaTabActionPerformed

    private void jRadioEsta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioEsta1ActionPerformed
         jRadioEsta.setSelected(false);
         jRadiaTab.setSelected(false);
         jRadioEsta1.setSelected(true);
         jResumen.setSelected(false);
    }//GEN-LAST:event_jRadioEsta1ActionPerformed

    private void jReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReporte1ActionPerformed
       
  try{         
   if(jResumen.isSelected() && hayDatos()){
    auditGeneroResumen();
    pdfResumen();
  
   
}
        
   
   else if(jRadioEsta.isSelected()){
    auditGeneroEntrada();
    pdfEstadistica();
}

   
else if(jRadioEsta1.isSelected()){
    auditGeneroSalida();
    pdfEstadisticaSalid(); 
    
}


else if(jRadiaTab.isSelected()){
    auditGeneroTabla();
    pdf();
}
       
  else {
    JOptionPane.showMessageDialog(null, "NO HAY DATOS EN EL RANGO DE FECHA SELECCIONADO O NO SE ENCUENTRAN RETRASOS", "DATOS", JOptionPane.ERROR_MESSAGE);
}     
       
  }catch(Exception e){
   e.printStackTrace();
   System.out.println(e);
  }  

    }//GEN-LAST:event_jReporte1ActionPerformed

    private void JTableHorario1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorario1MouseEntered
  int row = JTableHorario1.rowAtPoint(evt.getPoint());
    int column = JTableHorario1.columnAtPoint(evt.getPoint());

    // Verifica si la fila y la columna están dentro de los límites de la tabla
    if (row >= 0 && column >= 0) {
        // Verifica si la columna es la columna deseada (en este caso, la columna 6)
        if (column == 6) {
            // Obtiene el valor de la celda
            Object cellValue = JTableHorario1.getValueAt(row, column);

            // Establece el texto del tooltip para mostrar el contenido completo de la celda
            JTableHorario1.setToolTipText((String) cellValue);
        } 
        
        
        else if(column == 7){
            
                 // Obtiene el valor de la celda
            Object cellValue = JTableHorario1.getValueAt(row, column);

            // Establece el texto del tooltip para mostrar el contenido completo de la celda
            JTableHorario1.setToolTipText((String) cellValue);
        }
        
        
        else {
            // Si el cursor no está sobre la columna deseada, establece el tooltip como null
            JTableHorario1.setToolTipText(null);
        }
    }
    }//GEN-LAST:event_JTableHorario1MouseEntered

    private void jResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResumenActionPerformed
         jRadioEsta.setSelected(false);
         jRadiaTab.setSelected(false);
         jRadioEsta1.setSelected(false);
         jResumen.setSelected(true);
    }//GEN-LAST:event_jResumenActionPerformed

    
    
    
 

  boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;

 
    private void jCheckLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckLunesActionPerformed
    lunes= jCheckLunes.isSelected() ? true : false;
    
     actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckLunesActionPerformed

    private void jCheckMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckMartesActionPerformed
      martes= jCheckMartes.isSelected() ? true : false;
      actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckMartesActionPerformed

    private void jCheckMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckMiercolesActionPerformed
        miercoles= jCheckMiercoles.isSelected() ? true : false;
         actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckMiercolesActionPerformed

    private void jCheckJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckJuevesActionPerformed
        jueves= jCheckJueves.isSelected() ? true : false;
         actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckJuevesActionPerformed

    private void jCheckViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckViernesActionPerformed
       viernes= jCheckViernes.isSelected() ? true : false;
         actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckViernesActionPerformed

    private void jCheckSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSabadoActionPerformed
      sabado= jCheckSabado.isSelected() ? true : false;
      actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckSabadoActionPerformed

    private void jCheckDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckDomingoActionPerformed
       domingo= jCheckDomingo.isSelected() ? true : false;
          actualizarDiasSeleccionados();
    }//GEN-LAST:event_jCheckDomingoActionPerformed

    private void JComboEspecialidad1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboEspecialidad1ItemStateChanged
          if(JComboEspecialidad1.getSelectedIndex()!=-1){
          
          llenarDrs();
          }
    }//GEN-LAST:event_JComboEspecialidad1ItemStateChanged

    String tiempoDay="AM";
    private void jRadioAmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAmActionPerformed
      
       jRadioAm.setSelected(true);
       tiempoDay="AM";
       
       jRadioPm.setSelected(false);
       jRadioAll.setSelected(false);
        
    }//GEN-LAST:event_jRadioAmActionPerformed

    private void jRadioPmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioPmActionPerformed
       jRadioPm.setSelected(true);
       tiempoDay="PM";
       
       jRadioAm.setSelected(false);
       jRadioAll.setSelected(false);
    }//GEN-LAST:event_jRadioPmActionPerformed

    private void jRadioAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAllActionPerformed
       jRadioAll.setSelected(true);
       tiempoDay="24H";
      
       jRadioPm.setSelected(false);
       jRadioAm.setSelected(false);
     
    }//GEN-LAST:event_jRadioAllActionPerformed

    private String[] diasSeleccionados = new String[0];
  
    public String[] obtenerDiasSeleccionados() {
        List<String> listaDias = new ArrayList<>();
        
        if (lunes) listaDias.add("Lunes");
        if (martes) listaDias.add("Martes");
        if (miercoles) listaDias.add("Miércoles");
        if (jueves) listaDias.add("Jueves");
        if (viernes) listaDias.add("Viernes");
        if (sabado) listaDias.add("Sábado");
        if (domingo) listaDias.add("Domingo");
        
        // Convierte la lista a un array
        diasSeleccionados = listaDias.toArray(new String[0]);
        
        // Retorna el array
        return diasSeleccionados;
        
    }
     
     
   
     private void actualizarDiasSeleccionados() {

        diasSeleccionados = obtenerDiasSeleccionados();
        System.out.println(Arrays.toString(diasSeleccionados));
    }
    
    
    
    public void hideItems(){
    FechaOne.setVisible(false);
    FechaTwo.setVisible(false);
    jSearch.setVisible(false);
    jRadioEsta.setVisible(false);
    jRadioEsta1.setVisible(false);
    jRadiaTab.setVisible(false);
    jSeparator6.setVisible(false);
    jReporte1.setVisible(false);
    jResumen.setVisible(false);
    }
    
    
    
    public void showItems(){
    
    FechaOne.setVisible(true);
    FechaTwo.setVisible(true);
    jSearch.setVisible(true);
    jRadioEsta.setVisible(true);
    jRadioEsta1.setVisible(true);
    jRadiaTab.setVisible(true);
    jSeparator6.setVisible(true);
    jReporte1.setVisible(true);
     jResumen.setVisible(true);
    }    
       
    
    /*
    public void eliminarPaciente() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
     

      String mensaje = "¿Está seguro de que desea eliminar " + nombreprocedimiento + " de la tabla de procedimientos?";
      mensaje += "\nNOTA: Si existen pacientes con este procedimiento asignado y lo elimina, no podran visualizar los mismos";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_procedimientos` WHERE idProcedimiento = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprocedimientos);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El procedimiento ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                limpiarTabla();
               listarHorarios(); 
             //   AuditoriaBorrar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el procedimiento", "Error", JOptionPane.ERROR_MESSAGE);
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
    
*/
    
    
       public void auditActivar(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Activo el estado horario del Dr: " +JComboDoctor.getSelectedItem().toString() +" " ;
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
    
    
        public void auditDesactivar(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Desactivo el estado horario del Dr: " +JComboDoctor.getSelectedItem().toString() +" " ;
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
    
    
     public void auditAgregarDiferencia(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Agrego un nuevo horario al doctor: " +JComboEspecialidad3.getSelectedItem().toString() ;
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
    
     
       public void auditModificoDiferencia(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modificó el horario al doctor: " +JComboEspecialidad3.getSelectedItem().toString() ;
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
     
     
     
    
    
    
      public void auditAgregarHorario(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Agrego un nuevo horario al doctor: " +JComboDoctor.getSelectedItem().toString() +" " ;
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
    
    

    
       public void auditModificarHorario(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modifico el horario al doctor: " +JComboDoctor.getSelectedItem().toString() +" " ;
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
      
      
      
       
        public void auditGeneroEntrada(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Generó el reporte estadistico de entradas en el modulo horarios ";
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
       
       
       
      
      
      
       public void auditGeneroResumen(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Generó el reporte estadistico de resumen en el modulo horario";
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
      
      
      
         
       public void auditGeneroSalida(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Generó el reporte estadistico de salidas en el modulo horario ";
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
      
      
         
       public void auditGeneroTabla(){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Generó el reporte de toda la tabla de horarios en el modulo horario ";
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
      
    
    
     public void listarHorarios() {

        List<JCProcedimientos> lista = ProcesosDao.listarHorarios();
        modelo = (DefaultTableModel) JTableHorario.getModel();
        Object[] ob = new Object[15];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdhorario();
            ob[1] = lista.get(i).getEspecialidad();
            ob[2] = lista.get(i).getDoctor();
            ob[3] = lista.get(i).getEntrada();
            ob[4] = lista.get(i).getSalida();
            ob[5] = lista.get(i).getAm_pm();
            ob[6] = lista.get(i).getDiasHorario();
            ob[7] = lista.get(i).getEstadoHora();
            ob[8] = lista.get(i).getObservacionHora();
            
            modelo.addRow(ob);

        }
       JTableHorario.setModel(modelo);
       JTableHorario.setDefaultEditor(Object.class, null);
       JTableHorario.getTableHeader().setReorderingAllowed(false); 
       DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) JTableHorario.getTableHeader().getDefaultRenderer();
       headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
       acomodarceldas();

    }
    
     
     
    
     
     
     
     public void listarDiferencia() {

       String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
       String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());  
         
        List<JCProcedimientos> lista = ProcesosDao.listarDiferencia(fecha1, fecha2);
        modelo = (DefaultTableModel) JTableHorario1.getModel();
        Object[] ob = new Object[8];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdDiferencia();
            ob[1] = lista.get(i).getDoctor();
            ob[2] = lista.get(i).getEntradaReal();
            ob[3] = lista.get(i).getEntradaDif();
            ob[4] = lista.get(i).getSalidaReal();
            ob[5] = lista.get(i).getSalidaDif();
            ob[6] = lista.get(i).getMotivoReal();
            ob[7] = lista.get(i).getObservacionReal();
           
            modelo.addRow(ob);

        }
        
       JTableHorario1.setModel(modelo);
       JTableHorario1.setDefaultEditor(Object.class, null);
       JTableHorario1.getTableHeader().setReorderingAllowed(false); 
       acomodarceldas2();
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
    }
       
   
   
   
   
       String tiempoTotalFormateado, TTC;     
       public void pdfResumen() {
      try {
           
          
            DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
            LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          
      
            String   PdfNames="ReporteResumen"+"_"+fth.format(fechaactual); 
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
           
            
            
                 
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
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
            
         
            
            
            
            
            Map<String, String> cantidadesPorIdExamen = knowResume(); // asumiendo que los valores son cadenas de horas y minutos ("hh:mm")
            int sumatoriaTotal = 0;
            // Itera sobre el Map y muestra las cantidades en la consola
            for (Map.Entry<String, String> entry : cantidadesPorIdExamen.entrySet()) {
            String nombreEstudio = entry.getKey();
            String tiempo = entry.getValue(); // Obtiene la cadena de horas y minutos
            tiempo = corregirFormatoTiempo(tiempo);
         //   System.out.println("salida normal"+tiempo); // Salida: 01:02
          
            String[] partesTiempo = tiempo.split(":"); // Divide la cadena en partes separadas por ":"
            int horas = Integer.parseInt(partesTiempo[0]); // Obtiene las horas como entero
            int minutos = Integer.parseInt(partesTiempo[1]); // Obtiene los minutos como entero
            int tiempoEnMinutos = horas * 60 + minutos; // Convierte las horas y minutos a minutos totales
   
    
            sumatoriaTotal += tiempoEnMinutos; 
           int horasTotales = sumatoriaTotal / 60; // Obtiene las horas totales
           int minutosTotales = sumatoriaTotal % 60; // Obtiene los minutos totales

// Ajusta las horas totales si los minutos totales exceden 60
           horasTotales += minutosTotales / 60;
           minutosTotales %= 60;

// Formatea la salida en formato de hora:minuto
           tiempoTotalFormateado = String.format("%02d:%02d", horasTotales, minutosTotales);
            
            
           // System.out.println("Calculo"+tiempoTotalFormateado);
          
    
  // Formatea los minutos con dos dígitos
    String minutosFormateados = String.format("%02d", minutos);
    
    // Crea el texto para el Paragraph con las horas y los minutos formateados
    String textoTiempo = String.format("%d:%s", horas, minutosFormateados);

     
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
            
            Paragraph t42columna1 = new Paragraph(textoTiempo);
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
            Paragraph t41columna1 = new Paragraph("TIEMPO TOTAL : "+ tiempoTotalFormateado + ", OBTENIDO ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(12);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell celdaTotal = new PdfPCell(t41columna1);
            celdaTotal.setBorder(Rectangle.NO_BORDER);
            tablaTotal.addCell(celdaTotal);
            
            
            
            //--------------------------ESPECIALIDADES----------------------------------------
            
            
            
     
 
graficoEspecialidad();
  
com.itextpdf.text.Image imgGrafica = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaResumen.png");
doc.add(tablaResultado);
doc.add(saltolinea);
doc.add(imgGrafica);
doc.add(saltolinea);
doc.add(tablaTotal);


 



 //CARDIOLOGIA
 if(hayRegistros("Cardiologia")){

 doc.newPage();
 graficarEverything("Cardiologia");
 com.itextpdf.text.Image imgGrafica1 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaCardiologia.png");
 Paragraph titulo = crearTitulo("-C A R D I O L O G Í A-");
 doc.add(titulo);
 doc.add(saltolinea);
 PdfPTable[] tablas = generarTablaEspecialidad("Cardiologia", fecha1, fecha2);
 PdfPTable tablaCardiologia = tablas[0];
 PdfPTable tablaTCardiologia = tablas[1];
 doc.add(tablaCardiologia);
 doc.add(imgGrafica1); 
 doc.add(tablaTCardiologia);  
 
}
 
 
 
 //Cirugia Cardiovascular
  if(hayRegistros("Cirugia Cardiovacular")){
 doc.newPage();
 graficarEverything("Cirugia Cardiovacular");
 com.itextpdf.text.Image imgGraficaCC = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaCirugia Cardiovascular.png");
 Paragraph tituloCC = crearTitulo("-C I R U G Í A    C A R D I O V A S C U L A R-");
 doc.add(tituloCC);
 doc.add(saltolinea);
 PdfPTable[] tablasCC = generarTablaEspecialidad("Cirugia Cardiovacular", fecha1, fecha2);
 PdfPTable tablaCC = tablasCC[0];
 PdfPTable tablaTCC = tablasCC[1];
 doc.add(tablaCC); 
 doc.add(imgGraficaCC); 
 doc.add(tablaTCC); 
 }
 
 //Cirugia General
 if(hayRegistros("Cirugia General")){
 doc.newPage();
 graficarEverything("Cirugia General");
 com.itextpdf.text.Image imgGraficaCG = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaCirugia General.png");
 Paragraph tituloCG = crearTitulo("-C I R U G Í A    G E N E R A L-");
 doc.add(tituloCG);
 doc.add(saltolinea);
 PdfPTable[] tablasCG = generarTablaEspecialidad("Cirugia General", fecha1, fecha2);
 PdfPTable tablaCG = tablasCG[0];
 PdfPTable tablaTCG = tablasCG[1];
 doc.add(tablaCG); 
 doc.add(imgGraficaCG); 
 doc.add(tablaTCG); 
 }
            
   //Cirugia Pediatrica
  if(hayRegistros("Cirugia Pediatrica")){
 doc.newPage();
 graficarEverything("Cirugia Pediatrica");
 com.itextpdf.text.Image imgGraficaCP = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaCirugia Pediatrica.png");
 Paragraph tituloCP = crearTitulo("-C I R U G Í A    P E D I A T R I C A-");
 doc.add(tituloCP);
 doc.add(saltolinea);
 PdfPTable[] tablasCP = generarTablaEspecialidad("Cirugia Pediatrica", fecha1, fecha2);
 PdfPTable tablaCP = tablasCP[0];
 PdfPTable tablaTCP = tablasCP[1];
 doc.add(tablaCP); 
 doc.add(imgGraficaCP); 
 doc.add(tablaTCP); 
 }
 
  
 
  
  
 //Ecografia
 if(hayRegistros("Ecografia")){
 doc.newPage();
 graficarEverything("Ecografia");
 com.itextpdf.text.Image imgGrafica3 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaEcografia.png");
 Paragraph titulo3 = crearTitulo("-E C O G R A F Í A-");
 doc.add(titulo3);
 doc.add(saltolinea);
 PdfPTable[] tablas3 = generarTablaEspecialidad("Ecografia", fecha1, fecha2);
 PdfPTable tablaEconografia = tablas3[0];
 PdfPTable tablaTEcografia = tablas3[1];
 doc.add(tablaEconografia); 
 doc.add(imgGrafica3); 
 doc.add(tablaTEcografia); 
 }
 
 
 //Espirometria
 if(hayRegistros("Espirometria")){
 doc.newPage();
 graficarEverything("Espirometria");
 com.itextpdf.text.Image imgGraficaE = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaEspirometria.png");
 Paragraph titulo7 = crearTitulo("-E S P I R O M E T R Í A-");
 doc.add(titulo7);
 doc.add(saltolinea);
 PdfPTable[] tablas7 = generarTablaEspecialidad("Espirometria", fecha1, fecha2);
 PdfPTable tablaEspirometria = tablas7[0];
 PdfPTable tablaTEspirometria = tablas7[1];
 doc.add(tablaEspirometria); 
 doc.add(imgGraficaE); 
 doc.add(tablaTEspirometria); 
  }
 
 
//GINECOLOGIA
 if(hayRegistros("Ginecologia")){
 doc.newPage();
 graficarEverything("Ginecologia");
 com.itextpdf.text.Image imgGrafica2 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaGinecologia.png");
 Paragraph titulo2 = crearTitulo("-G I N E C O L O G Í A-");
 doc.add(titulo2);
 doc.add(saltolinea);
 PdfPTable[] tablas2 = generarTablaEspecialidad("Ginecologia", fecha1, fecha2);
 PdfPTable tablaGinecologia = tablas2[0];
 PdfPTable tablaTGinecologia = tablas2[1];
 doc.add(tablaGinecologia);
 doc.add(imgGrafica2); 
 doc.add(tablaTGinecologia); 
}
 
 //GINECOLOGIA
 if(hayRegistros("Gastroenteorologia")){
 doc.newPage();
 graficarEverything("Gastroenteorologia");
 com.itextpdf.text.Image imgGastro = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaGastroenteorologia.png");
 Paragraph tituloGastro = crearTitulo("-G A S T R O E N T E O R O L O G I A-");
 doc.add(tituloGastro);
 doc.add(saltolinea);
 PdfPTable[] tablasGastro = generarTablaEspecialidad("Gastroenteorologia", fecha1, fecha2);
 PdfPTable tablaGastro = tablasGastro[0];
 PdfPTable tablaTGastro = tablasGastro[1];
 doc.add(tablaGastro);
 doc.add(imgGastro); 
 doc.add(tablaTGastro); 
}
 
 
 
 
 
 //HEMATO
 if(hayRegistros("Hematologia")){
 doc.newPage();
 graficarEverything("Hematologia");
 com.itextpdf.text.Image imgGrafica6 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaHematologia.png");
 Paragraph titulo6 = crearTitulo("-H E M A T O L O G Í A-");
 doc.add(titulo6);
 doc.add(saltolinea);
 PdfPTable[] tablas6 = generarTablaEspecialidad("Hematologia", fecha1, fecha2);
 PdfPTable tablaHemato = tablas6[0];
 PdfPTable tablaTHemato = tablas6[1];
 doc.add(tablaHemato);
 doc.add(imgGrafica6); 
 doc.add(tablaTHemato); 
 }
 
 
 //MEDICINA INTERNA
 if(hayRegistros("Medicina Interna")){
 doc.newPage();
 graficarEverything("Medicina Interna");
 com.itextpdf.text.Image imgGrafica5 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaMedicina Interna.png");
 Paragraph titulo5 = crearTitulo("-M É D I C I N A   I N T E R N A-");
 doc.add(titulo5);
 doc.add(saltolinea);
 PdfPTable[] tablas5 = generarTablaEspecialidad("Medicina Interna", fecha1, fecha2);
 PdfPTable tablaInterna = tablas5[0];
 PdfPTable tablaTInterna = tablas5[1];
 doc.add(tablaInterna);
 doc.add(imgGrafica5); 
 doc.add(tablaTInterna); 
 }
 
 
 //NEFROLOGIA
 if(hayRegistros("Nefrologia")){
     
 doc.newPage();
 graficarEverything("Nefrologia");
 com.itextpdf.text.Image imgGraficaNefro = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNefrologia.png");
 Paragraph tituloNefro = crearTitulo("-N E F R O L O G Í A-");
 doc.add(tituloNefro);
 doc.add(saltolinea);
 PdfPTable[] tablasNefro= generarTablaEspecialidad("Nefrologia", fecha1, fecha2);
 PdfPTable tablaNefro = tablasNefro[0];
 PdfPTable tablaTNefro = tablasNefro[1];
 doc.add(tablaNefro);
 doc.add(imgGraficaNefro); 
 doc.add(tablaTNefro);
 
 }
 
 
 //NEUMONOLOGIA
 if(hayRegistros("Neumonologia")){
 doc.newPage();
 graficarEverything("Neumonologia");
 com.itextpdf.text.Image imgGraficaNeu = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNeumonologia.png");
 Paragraph tituloNeu = crearTitulo("-N E U M O N O L O G Í A-");
 doc.add(tituloNeu);
 doc.add(saltolinea);
 PdfPTable[] tablasNeu= generarTablaEspecialidad("Nefrologia", fecha1, fecha2);
 PdfPTable tablaNeu = tablasNeu[0];
 PdfPTable tablaTNeu = tablasNeu[1];
 doc.add(tablaNeu );
 doc.add(imgGraficaNeu); 
 doc.add(tablaTNeu);
 }
 
 
 //NEUROCIRUGIA
 if(hayRegistros("Neurocirugia")){
 doc.newPage();
 graficarEverything("Neurocirugia");
 com.itextpdf.text.Image imgGraficaNeuro = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNeurocirugia.png");
 Paragraph tituloNeuro = crearTitulo("-N E U R O C I R U G Í A-");
 doc.add(tituloNeuro);
 doc.add(saltolinea);
 PdfPTable[] tablasNeuro= generarTablaEspecialidad("Neurocirugia", fecha1, fecha2);
 PdfPTable tablaNeuro = tablasNeuro[0];
 PdfPTable tablaTNeuro = tablasNeuro[1];
 doc.add(tablaNeuro );
 doc.add(imgGraficaNeuro); 
 doc.add(tablaTNeuro);
 }
 

 //NUTRICIONISTA
  if(hayRegistros("Nutricionista")){
 doc.newPage();
 graficarEverything("Nutricionista");
 com.itextpdf.text.Image imgGraficaNutri = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNutricionista.png");
 Paragraph tituloNutri = crearTitulo("-N U T R I C I O N I S T A-");
 doc.add(tituloNutri);
 doc.add(saltolinea); 
 PdfPTable[] tablasNutri= generarTablaEspecialidad("Nutricionista", fecha1, fecha2);
 PdfPTable tablaNutri = tablasNutri[0];
 PdfPTable tablaTNutri = tablasNutri[1];
 doc.add(tablaNutri );
 doc.add(imgGraficaNutri); 
 doc.add(tablaTNutri);
 }
  
  
 
 //ODONTOLOGIA
 if(hayRegistros("Odontologia")){
 doc.newPage();
 graficarEverything("Odontologia");
 com.itextpdf.text.Image imgGraficaOdont = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNutricionista.png");
 Paragraph tituloOdont = crearTitulo("-O D O N T O L O G Í A-");
 doc.add(tituloOdont);
 doc.add(saltolinea); 
 PdfPTable[] tablasOdont= generarTablaEspecialidad("Odontologia", fecha1, fecha2);
 PdfPTable tablaOdont = tablasOdont[0];
 PdfPTable tablaTOdont = tablasOdont[1];
 doc.add(tablaOdont);
 doc.add(imgGraficaOdont); 
 doc.add(tablaTOdont);
 }
  
  
 
 //OFTAMOLOGIA
  if(hayRegistros("Oftamologia")){
 doc.newPage();
 graficarEverything("Oftamologia");
 com.itextpdf.text.Image imgGraficaOfta = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaNutricionista.png");
 Paragraph tituloOfta  = crearTitulo("-O F T A M O L O G Í A-");
 doc.add(tituloOfta);
 doc.add(saltolinea); 
 PdfPTable[] tablasOfta= generarTablaEspecialidad("Oftamologia", fecha1, fecha2);
 PdfPTable tablaOfta = tablasOfta[0];
 PdfPTable tablaTOfta = tablasOfta[1];
 doc.add(tablaOfta);
 doc.add(imgGraficaOfta); 
 doc.add(tablaTOfta);
 }
  
  
 //ONCOLOGIA
  if(hayRegistros("Oncologia")){
  doc.newPage();
 graficarEverything("Oncologia");
 com.itextpdf.text.Image imgGraficaOnco = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaOncologia.png");
 Paragraph tituloOnco  = crearTitulo("-O N C O L O G Í A-");
 doc.add(tituloOnco);
 doc.add(saltolinea); 
 PdfPTable[] tablasOnco= generarTablaEspecialidad("Oncologia", fecha1, fecha2);
 PdfPTable tablaOnco = tablasOnco[0];
 PdfPTable tablaTOnco = tablasOnco[1];
 doc.add(tablaOnco);
 doc.add(imgGraficaOnco); 
 doc.add(tablaTOnco);
 }
  
  
 //OTORRINO
  if(hayRegistros("Otorrinolaringologia")){
 doc.newPage();
 graficarEverything("Otorrinolaringologia");
 com.itextpdf.text.Image imgGraficaOto = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaOtorrinolaringologia.png");
 Paragraph tituloOto  = crearTitulo("-O T O R R I N O L A R I N G O L O G Í A-");
 doc.add(tituloOto);
 doc.add(saltolinea); 
 PdfPTable[] tablasOto= generarTablaEspecialidad("Otorrinolaringologia", fecha1, fecha2);
 PdfPTable tablaOto = tablasOto[0];
 PdfPTable tablaTOto = tablasOto[1];
 doc.add(tablaOto);
 doc.add(imgGraficaOto); 
 doc.add(tablaTOto);
 }
  
  
  
 //Pediatria
 if(hayRegistros("Pediatria")){
 doc.newPage();
 graficarEverything("Pediatria");
 com.itextpdf.text.Image imgGrafica4 = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaPediatria.png");
 Paragraph titulo4 = crearTitulo("-P E D I A T R I A-");
 doc.add(titulo4);
 doc.add(saltolinea);
 PdfPTable[] tablas4 = generarTablaEspecialidad("Pediatria", fecha1, fecha2);
 PdfPTable tablaPediatria = tablas4[0];
 PdfPTable tablaTPediatria = tablas4[1];
 doc.add(tablaPediatria); 
 doc.add(imgGrafica4); 
 doc.add(tablaTPediatria); 
 }
 
 //PSICOLOGIA
  if(hayRegistros("Psicologia")){
 doc.newPage();
 graficarEverything("Psicologia");
 com.itextpdf.text.Image imgGraficaPsico = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaPsicologia.png");
 Paragraph tituloPsico  = crearTitulo("-P S I C O L O G Í A-");
 doc.add(tituloPsico);
 doc.add(saltolinea); 
 PdfPTable[] tablasPsico= generarTablaEspecialidad("Psicologia", fecha1, fecha2);
 PdfPTable tablaPsico = tablasPsico[0];
 PdfPTable tablaTPsico = tablasPsico[1];
 doc.add(tablaPsico);
 doc.add(imgGraficaPsico); 
 doc.add(tablaTPsico);
 }
 
  //Psiquiatria
   if(hayRegistros("Psiquiatria")){
  doc.newPage();
 graficarEverything("Psiquiatria");
 com.itextpdf.text.Image imgGraficaPsiqui = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaPsiquiatria.png");
 Paragraph tituloPsiqui  = crearTitulo("-P S I Q U I A T R Í A-");
 doc.add(tituloPsiqui);
 doc.add(saltolinea); 
 PdfPTable[] tablasPsiqui= generarTablaEspecialidad("Psiquiatria", fecha1, fecha2);
 PdfPTable tablaPsiqui= tablasPsiqui[0];
 PdfPTable tablaTPsiqui = tablasPsiqui[1];
 doc.add(tablaPsiqui);
 doc.add(imgGraficaPsiqui); 
 doc.add(tablaTPsiqui);
 }
 
  
  
  
 //TRAUMATOLOGIA
   if(hayRegistros("Traumatologia")){
 doc.newPage();
 graficarEverything("Traumatologia");
 com.itextpdf.text.Image imgGraficaTrau = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaTraumatologia.png");
 Paragraph tituloTrau  = crearTitulo("-T R A U M A T O L O G Í A-");
 doc.add(tituloTrau);
 doc.add(saltolinea); 
 PdfPTable[] tablasTrau= generarTablaEspecialidad("Psiquiatria", fecha1, fecha2);
 PdfPTable tablaTrau= tablasTrau[0];
 PdfPTable tablaTTrau = tablasTrau[1];
 doc.add(tablaTrau);
 doc.add(imgGraficaTrau); 
 doc.add(tablaTTrau);
 }
 
 
 
 //UROLOGIA
   if(hayRegistros("Urologia")){
  doc.newPage();
 graficarEverything("Urologia");
 com.itextpdf.text.Image imgGraficaUro = obtenerImagen("C:\\Fundaginebra\\Graficas\\graficaTraumatologia.png");
 Paragraph tituloUro  = crearTitulo("-U R O L O G Í A-");
 doc.add(tituloUro);
 doc.add(saltolinea); 
 PdfPTable[] tablasUro= generarTablaEspecialidad("Psiquiatria", fecha1, fecha2);
 PdfPTable tablaUro= tablasUro[0];
 PdfPTable tablaTUro = tablasUro[1];
 doc.add(tablaUro);
 doc.add(imgGraficaUro); 
 doc.add(tablaTUro);
 }
 
 
 
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     
         
      
      
private String corregirFormatoTiempo(String tiempo) {
    // Verifica si la cadena de tiempo está vacía
    if (tiempo.isEmpty()) {
        // Aquí puedes manejar el error o lanzar una excepción
        return null;
    }

    // Divide la cadena en partes separadas por ":"
    String[] partesTiempo = tiempo.split(":");

    // Verifica si hay suficientes partes
    if (partesTiempo.length < 2) {
        // Aquí puedes manejar el error o lanzar una excepción
        return null;
    }

    // Asegúrate de que cada parte tenga al menos dos dígitos
    String hora = partesTiempo[0].length() == 1 ? "0" + partesTiempo[0] : partesTiempo[0];
    String minuto = partesTiempo[1];

    // Elimina cualquier carácter no numérico al final de la cadena de minutos
    if (minuto.length() > 1 && !Character.isDigit(minuto.charAt(minuto.length() - 1))) {
        minuto = minuto.substring(0, minuto.length() - 1);
    }

    // Si la longitud de los minutos es 1, simplemente agrega un cero al principio
    if (minuto.length() == 1) {
        minuto = "0" + minuto;
    }

    // Reconstruye el tiempo corregido
    return hora + ":" + minuto;
}
   
 public Paragraph crearTitulo(String texto) {
    BaseFont BF;  
        try {
            BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font Letrasmall = new Font(BF, 8);
    Paragraph titulo = new Paragraph(texto);
    titulo.getFont().setStyle(Font.NORMAL);
    titulo.getFont().setSize(12);        
    titulo.setFont(Letrasmall);
    titulo.setAlignment(Element.ALIGN_CENTER);
    return titulo;
        } catch (Exception e) {
            Logger.getLogger(JHorariosDoctores.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } 

}
 
 
 
   public PdfPTable[] generarTablaEspecialidad(String especialidad, String fecha1, String fecha2) {
    PdfPTable[] tablas = new PdfPTable[2];
    try {
        float[] medidaCeldas3 = {6f, 3f};
        BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);  
        Font Letrasmall = new Font(BF, 8);
        float[] medidaResultado = {5f};
        
        // Crear tabla principal
        PdfPTable tabla = new PdfPTable(2); // Crear una tabla con 2 columnas
        tabla.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tabla.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        tabla.setWidthPercentage(100);  
        tabla.setWidths(medidaCeldas3);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Map<String, String> datosEspecialidad = knowEverything(especialidad); // Obtener datos para la especialidad dada
        int sumatoriaTotal = 0;

        // Itera sobre el Map y muestra las cantidades en la consola
        for (Map.Entry<String, String> entry : datosEspecialidad.entrySet()) {
            String nombreEstudio = entry.getKey();
            String tiempo = entry.getValue(); // Obtiene la cadena de horas y minutos
            tiempo = corregirFormatoTiempo(tiempo);
           // System.out.println("salida normal"+tiempo); // Salida: 01:02
          
            String[] partesTiempo = tiempo.split(":"); // Divide la cadena en partes separadas por ":"
            int horas = Integer.parseInt(partesTiempo[0]); // Obtiene las horas como entero
            int minutos = Integer.parseInt(partesTiempo[1]); // Obtiene los minutos como entero
            int tiempoEnMinutos = horas * 60 + minutos; // Convierte las horas y minutos a minutos totales
            sumatoriaTotal += tiempoEnMinutos; 
          
           // System.out.print("Resultado horas: "+ horas +"resultado minutos: "+ minutos+"Tiempo en minutos:" +tiempoEnMinutos);
            
           
             // Formatea los minutos con dos dígitos
    String minutosFormateados = String.format("%02d", minutos);
    
    // Crea el texto para el Paragraph con las horas y los minutos formateados
    String textoTiempo = String.format("%d:%s", horas, minutosFormateados);
           
           
            Paragraph tIndiv = new Paragraph(nombreEstudio);
            tIndiv.getFont().setStyle(Font.NORMAL);
            tIndiv.getFont().setSize(9);        
            tIndiv.setFont(Letrasmall);
            tIndiv.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell cellind = new PdfPCell(tIndiv);
            cellind.setBorder(Rectangle.NO_BORDER);
            cellind.enableBorderSide(Rectangle.BOTTOM);
            cellind.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellind.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            Paragraph tIndiv2 = new Paragraph(textoTiempo);
            tIndiv2.getFont().setStyle(Font.NORMAL);
            tIndiv2.getFont().setSize(9);        
            tIndiv2.setFont(Letrasmall);
            tIndiv2.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell cellind2 = new PdfPCell(tIndiv2);
            cellind2.setBorder(Rectangle.NO_BORDER);
            cellind2.enableBorderSide(Rectangle.BOTTOM);
            cellind2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellind2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tabla.addCell(cellind);
            tabla.addCell(cellind2);
        }

        // Calcular tiempo total
        int horasTotales = sumatoriaTotal / 60;
        int minutosRestantes = sumatoriaTotal % 60;
        String tiempoTotal = String.format("%02d:%02d", horasTotales, minutosRestantes);

        // Crear tabla para mostrar el tiempo total
        PdfPTable tablaTiempoTotal = new PdfPTable(1); 
        tablaTiempoTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tablaTiempoTotal.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
        tablaTiempoTotal.setWidthPercentage(100); 
        tablaTiempoTotal.setWidths(medidaResultado);
        tablaTiempoTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaTiempoTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph parrafoTiempoTotal = new Paragraph("TIEMPO TOTAL : "+ tiempoTotal + ", OBTENIDO ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
        parrafoTiempoTotal.getFont().setStyle(Font.NORMAL);
        parrafoTiempoTotal.getFont().setSize(12);        
        parrafoTiempoTotal.setFont(Letrasmall);
        parrafoTiempoTotal.setAlignment(Element.ALIGN_JUSTIFIED);
        PdfPCell celdaTiempoTotal = new PdfPCell(parrafoTiempoTotal);
        celdaTiempoTotal.setBorder(Rectangle.NO_BORDER);
        tablaTiempoTotal.addCell(celdaTiempoTotal);
        
        // Agregar las tablas al array
        tablas[0] = tabla; // Tabla principal
        tablas[1] = tablaTiempoTotal; // Tabla de tiempo total

        // Retornar el array con ambas tablas
        return tablas;
    } catch (Exception e) {
        // Manejar la excepción aquí, por ejemplo, imprimir un mensaje de error
        e.printStackTrace();
        return null; // o retornar un array con valores nulos según sea necesario
    }
}


   
   

   public static com.itextpdf.text.Image obtenerImagen(String rutaImagen) throws IOException, BadElementException {
    com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(rutaImagen);
    imagen.setAlignment(Element.ALIGN_CENTER);
    imagen.scaleToFit(500, 500);
    return imagen;
}
   
   
   
   
   
     public void pdf() {
         

         
      try {

             DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
             LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          
         
            String   PdfNames="ReportePacientes"+"_"+fth.format(fechaactual); 
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
           
            
            
                 
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
            doc.open();
            
                    
      
            
            
            
        
       //BODY 
       
       

         
            
            
            PdfPTable  tablapro = new PdfPTable(7);
            
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            tablapro.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 2f,3f,2f, 3f,4f};
            tablapro.setWidths(medidaCeldas);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("CÓD");
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna1);
        
          
            Paragraph tcolumna2 = new Paragraph("DOCTOR");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(10);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna2);
        
            Paragraph tcolumna3 = new Paragraph("ENTRADA");
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(10);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna3);
            
            Paragraph tcolumna4 = new Paragraph("DIF. ENTRADA");
            tcolumna4.getFont().setStyle(Font.BOLD);
            tcolumna4.getFont().setSize(10);        
            tcolumna4.setFont(Letra);
            tcolumna4.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna4);
            
            Paragraph tcolumna5 = new Paragraph("SALIDA");
            tcolumna5.getFont().setStyle(Font.BOLD);
            tcolumna5.getFont().setSize(10);        
            tcolumna5.setFont(Letra);
            tcolumna5.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna5);
            
            Paragraph tcolumna6 = new Paragraph("DIF. SALIDA");
            tcolumna6.getFont().setStyle(Font.BOLD);
            tcolumna6.getFont().setSize(10);        
            tcolumna6.setFont(Letra);
            tcolumna6.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna6);
            
            Paragraph tcolumna7 = new Paragraph("MOTIVO");
            tcolumna7.getFont().setStyle(Font.BOLD);
            tcolumna7.getFont().setSize(10);        
            tcolumna7.setFont(Letra);
            tcolumna7.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna7);
            

            for (int i = 0; i < JTableHorario1.getRowCount(); i++) {
               
                String Codigo = JTableHorario1.getValueAt(i, 0).toString();
                String Doctor = JTableHorario1.getValueAt(i, 1).toString()  ;
                String Entrada = JTableHorario1.getValueAt(i, 2).toString();
                String DifEntrada = JTableHorario1.getValueAt(i, 3).toString(); 
                String Salida = JTableHorario1.getValueAt(i, 4).toString(); 
                String  DifSalida = JTableHorario1.getValueAt(i, 5).toString(); 
                String Motivo = JTableHorario1.getValueAt(i, 6).toString(); 
         
                tablapro.addCell(new Paragraph(Codigo,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Doctor,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Entrada,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(DifEntrada,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Salida,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(DifSalida,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Motivo,FontFactory.getFont("Arial",8,Font.NORMAL))); 
       
            }
       
            doc.add(saltolinea);
            doc.add(tablapro);
         

         
            //FOOTER
          
            
            
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
      
      
      
    }
      
         
     
     
     
      
         
         
         
         
         
         
             
       public void pdfEstadistica() {
      try {
   
          
            DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
            LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          
      
            String   PdfNames="ReporteEntradas"+"_"+fth.format(fechaactual); 
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
           
            
            
                 
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
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
            Paragraph t41columna1 = new Paragraph("TOTAL DE RETRASOS: "+ sumatoriaTotal + ", REALIZADOS ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(12);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell celdaTotal = new PdfPCell(t41columna1);
            celdaTotal.setBorder(Rectangle.NO_BORDER);
            //aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            
            tablaTotal.addCell(celdaTotal);
            
            
            
            
       
        
            
   
 
            graficoPdf();
            doc.add(tablaResultado);
         
            doc.add(saltolinea);
             
            com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\Graficas\\graficaEntrada.png");
            
            
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
     
         
         
         
         
       
               
             
       public void pdfEstadisticaSalid() {
      try {
   
          
            DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
            LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          
      
            String   PdfNames="ReporteSalidas"+"_"+fth.format(fechaactual); 
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
           
            
            
                 
            HeaderFooterPageEvent headerEvent = new  HeaderFooterPageEvent();
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
            
         
            
            Map<String, Integer> cantidadesPorIdExamen = knowOutNumbers();
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
            Paragraph t41columna1 = new Paragraph("TOTAL SALIDAS TEMPRANO: "+ sumatoriaTotal + ", REALIZADOS ENTRE LAS FECHAS "+ fecha1 +" | "+ fecha2);
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(12);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell celdaTotal = new PdfPCell(t41columna1);
            celdaTotal.setBorder(Rectangle.NO_BORDER);
            //aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            
            tablaTotal.addCell(celdaTotal);
            
            
            
            
       
        
            
   
 
 graficoSalidas();
            doc.add(tablaResultado);
         
            doc.add(saltolinea);
             
            com.itextpdf.text.Image imgGrafica = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\Graficas\\graficaSalidas.png");
            
            
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
     
         
       
       
       
       
       
         
               
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        try {
     

            Paragraph fecha = new Paragraph();
    //      Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(Menu.FechaAdmin.getDate());
            String Hora = Menu.Time.getText()+" "+Menu.jLabel102.getText();
            
            
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
     
        String User=TM.getUser();
        
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
        
String sql = "SELECT \n" +
"    e.Doctor, \n" +
"    COUNT(*) AS veces_tarde\n" +
"FROM \n" +
"    diferencia_horario u\n" +
"INNER JOIN \n" +
"    horario_doctores e ON u.id_horario = e.id_horario\n" +
"WHERE \n" +
"    Fecha BETWEEN ? AND ? AND\n" +
"    dif_entrada LIKE '%TARDE%' \n" +
"    AND TIME_TO_SEC(STR_TO_DATE(SUBSTRING_INDEX(dif_entrada, ' ', 1), '%H:%i')) > 0\n" +
"GROUP BY \n" +
"    e.Doctor;";


            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            while (rs.next()) {
          //      int idExamen = rs.getInt("id_examen");
                String nombreExamen = rs.getString("e.Doctor");
                int cantidadRepeticiones = rs.getInt("veces_tarde");

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
         
         
         
         
         
        public Map<String, Integer> knowOutNumbers() {
  //      Map<String, Integer> cantidadesPorIdExamen = new HashMap<>();
        Map<String, Integer> cantidadesPorIdExamen = new LinkedHashMap<>();
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
      

        try {
            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
String sql = "SELECT \n" +
"e.Doctor, \n" +
"COUNT(*) AS veces_tarde\n" +
"FROM \n" +
"diferencia_horario u\n" +
"INNER JOIN \n" +
"horario_doctores e ON u.id_horario = e.id_horario\n" +
"WHERE \n" +
"Fecha BETWEEN ? AND ? AND\n" +
"dif_salida LIKE '%TEMPRANO%' \n" +
"AND TIME_TO_SEC(STR_TO_DATE(SUBSTRING_INDEX(dif_salida, ' ', 1), '%H:%i')) > 0\n" +
"GROUP BY \n" +
"e.Doctor;";


            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            while (rs.next()) {
          //      int idExamen = rs.getInt("id_examen");
                String nombreExamen = rs.getString("e.Doctor");
                int cantidadRepeticiones = rs.getInt("veces_tarde");

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
         
         
         
         
             
        public Map<String, String> knowResume() {
    Map<String, String> cantidadesPorIdExamen = new LinkedHashMap<>();
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

        String sql = "SELECT h.especialidad, " +
                     "CONCAT(FLOOR(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i')) / 3600)), " +
                     "':', " +
                     "LPAD(MOD(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i')) / 60), 60), 2, '0')) AS tiempo " +
                     "FROM diferencia_horario u " +
                     "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario " +
                     "INNER JOIN table_especialidad h ON e.id_especialidad=h.id_especialidad " +
                     "WHERE Fecha BETWEEN ? AND ? AND dif_entrada LIKE '%TARDE%' " +
                     "GROUP BY h.especialidad LIMIT 0, 25;";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();

        while (rs.next()) {
            String nombreExamen = rs.getString("h.especialidad");
            String cantidadRepeticiones = rs.getString("tiempo");

            // Validar el formato de la cadena de tiempo antes de almacenarla
        
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

    return cantidadesPorIdExamen;
}


     
     
        public Map<String, String> knowEverything(String especialidades) {
  //    Map<String, Integer> cantidadesPorIdExamen = new HashMap<>();
        Map<String, String> cantidadesPorIdExamen = new LinkedHashMap<>();
        Connection con = null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
      

        try {
            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
String sql = "SELECT  \n" +
    "    e.doctor, \n" +
    "    CONCAT( \n" +
    "    FLOOR(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i'))) / 3600), \n" +
    "    ':', \n" +
    "    LPAD(MOD(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i'))) / 60, 60), 2, '0')\n" +
    "    ) AS tiempo \n" +
    "    FROM \n" +
    "    diferencia_horario u \n" +
    "    INNER JOIN \n" +
    "    horario_doctores e ON u.id_horario = e.id_horario \n" +
    "    INNER JOIN \n" +
    "    table_especialidad h ON e.id_especialidad = h.id_especialidad \n" +
    "    WHERE \n" +
    "    Fecha BETWEEN ? AND ? \n" +
    "    AND dif_entrada LIKE '%TARDE%' \n" +
    "    AND h.especialidad = '"+ especialidades + "'\n" +
    "    GROUP BY  \n" +
    "    e.doctor \n" +
    "    LIMIT 0, 25;";



            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            rs = ps.executeQuery();

            while (rs.next()) {
          //      int idExamen = rs.getInt("id_examen");
                String nombreExamen = rs.getString("e.doctor");
                String cantidadRepeticiones = rs.getString("tiempo");

           
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
     
     

        
        
        
        
        
        
        
        
     
    
    public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTableHorario.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHorario.setRowHeight(20);
        
        JTableHorario.getColumnModel().getColumn(0).setMaxWidth(0);
        JTableHorario.getColumnModel().getColumn(0).setMinWidth(0);
        JTableHorario.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
       /// JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(10);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setPreferredWidth(40);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setPreferredWidth(80);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ENTRADA")).setPreferredWidth(10);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("SALIDA")).setPreferredWidth(10);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("AM/PM")).setPreferredWidth(10);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DIAS")).setPreferredWidth(200);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(20);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("OBSERVACION")).setPreferredWidth(80);
        
      
    //    JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ENTRADA")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("SALIDA")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("AM/PM")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DIAS")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("OBSERVACION")).setCellRenderer(Alinear);

    
    }
            
    
    
    
    public void acomodarceldas2()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTableHorario1.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHorario1.setRowHeight(20);
        

        
       
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(5);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setPreferredWidth(50);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("ENTRADA")).setPreferredWidth(10);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DIF. ENTRADA")).setPreferredWidth(80);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("SALIDA")).setPreferredWidth(10);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DIF. SALIDA")).setPreferredWidth(80);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("MOTIVO RETRASO")).setPreferredWidth(100);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("OBSERVACION")).setPreferredWidth(100);
      
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("ENTRADA")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DIF. ENTRADA")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("SALIDA")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("DIF. SALIDA")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("MOTIVO RETRASO")).setCellRenderer(Alinear);
        JTableHorario1.getColumnModel().getColumn(Tabla.findColumn("OBSERVACION")).setCellRenderer(Alinear);
    
    }
            
    
    
    
    
    
    
    
  LlenarCombobox lc = new LlenarCombobox();
     
      public void llenarCombo() {

    
           
        try{
   
        JComboEspecialidad1.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3 = lc.llenarEspecialidad();
        for (int i = 0; i < lista3.size(); i++) {
            JComboEspecialidad1.addItem(lista3.get(i));
        }
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
           
          }
    
      
      
      
      
        public void llenarDoctores() {
        try{
        JComboEspecialidad3.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3 = lc.llenarHorarios();
        for (int i = 0; i < lista3.size(); i++) {
        JComboEspecialidad3.addItem(lista3.get(i));

        }
        
          }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
          }
      
      
      
      
      
      
      
      
      
      
     public void agregarHorario(){                                             
    
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    

          try {

              
            String sql = "INSERT INTO `horario_doctores`( `id_especialidad`, `Doctor`, `Entrada`, `Salida`, `Observaciones`, Dias_disponible, am_pm) VALUES ((SELECT id_especialidad FROM table_especialidad WHERE especialidad=?),(SELECT IdPersonal FROM table_usuario WHERE usuario=?),?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            
            ps.setString(1, JComboEspecialidad1.getSelectedItem().toString());
            ps.setString(2, JComboDoctor.getSelectedItem().toString());
            ps.setString(3, jTextEntrada.getText());
            ps.setString(4, jTextSalida.getText());
            ps.setString(5, jTextObservacion.getText());
            String diasSeleccionadosStr = String.join(",", diasSeleccionados);
            ps.setString(6, diasSeleccionadosStr);
            ps.setString(7,  tiempoDay);
               
            ps.execute();
  
            
         limpiarTabla();  listarHorarios();  limpiarCampos();
         JOptionPane.showMessageDialog(null, "HORARIO REGISTRADO","REGISTRO ", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }


      
          
      
    } 
    
     
     
      
      public void actualizarDiferencia() {
   
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

        try {
          
            
            
           // String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());   
            
            
 
            String sql = "UPDATE diferencia_horario SET id_horario=(SELECT id_horario FROM horario_doctores WHERE Doctor=?), entrada=?, dif_entrada=?, salida=?, dif_salida=?, motivo=?, observacion=? WHERE id_diferencia=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, JComboEspecialidad3.getSelectedItem().toString());
            ps.setString(2, jTextEntrada1.getText());
            ps.setString(3, diferenciaTiempo);
            ps.setString(4, jTextSalida1.getText());
            ps.setString(5, difTimeOut);
            ps.setString(6, JTextretraso.getText());
            ps.setString(7, JTextObs.getText());
            ps.setInt(8, idDiferencia);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "Horario Actualizado", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
              
    }

     
     
    
      public void actualizarHorarios() {
   
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

        try {
 
            String sql = "UPDATE `horario_doctores` SET `id_especialidad`=(SELECT `id_especialidad` FROM `table_especialidad` WHERE `especialidad`=?), `Doctor`=(SELECT IdPersonal  FROM table_usuario WHERE usuario=?), `Entrada`=?, `Salida`=?, `Observaciones`=?, Dias_disponible=? , am_pm=? WHERE `id_horario`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, JComboEspecialidad1.getSelectedItem().toString());
            ps.setString(2, JComboDoctor.getSelectedItem().toString());
            ps.setString(3, jTextEntrada.getText());
            ps.setString(4, jTextSalida.getText());
            ps.setString(5, jTextObservacion.getText());
            String diasSeleccionadosStr = String.join(",", diasSeleccionados);
            ps.setString(6, diasSeleccionadosStr);
            ps.setString(7,  tiempoDay);
            ps.setInt(8, idHorarios);
         
           
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "Horario Actualizado", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
              
    }

     
     
  int idHorario; 
  String especialidadDoc, EntradaI, SalidaI;
  public void knowCategory() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

    String sql = "SELECT id_horario, Doctor, e.especialidad, Entrada, Salida \n" +
                 "FROM horario_doctores u \n" +
                 "INNER JOIN table_especialidad e ON u.id_especialidad = e.id_especialidad \n" +
                 "WHERE Doctor = ?";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, JComboEspecialidad3.getSelectedItem().toString());
        rs = ps.executeQuery();

        if (rs.next()) {
            idHorario=rs.getInt("id_horario");
            EntradaI= rs.getString("Entrada");
            SalidaI= rs.getString("Salida");
            especialidadDoc = rs.getString("especialidad");
       
        }

    } catch (Exception e) {
        System.out.println(e);
    } finally {
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
        } catch (SQLException se) {
            System.out.println(se);
        }
    }
}

          
    public void Search(){
              
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
       //DECLARACIÓN DEL MODELO DE LA TABLA
    DefaultTableModel Tabla = (DefaultTableModel)JTableHorario1.getModel();
        JTableHorario1.setDefaultEditor(Object.class, null);
        
        
       String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
       String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());    
        
    String Busqueda = "%" + this.TXTfindbyName.getText() + "%";
        String sql = "SELECT id_diferencia, s.Usuario, u.entrada, dif_entrada, u.salida, dif_salida, motivo, observacion, Fecha " +
             "FROM diferencia_horario u " +
             "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario " +
             "INNER JOIN table_usuario s ON u.Doctor = s.IdPersonal"+  
             "WHERE Fecha BETWEEN ? AND ? AND e.Doctor LIKE ? ORDER BY id_diferencia ASC";

        
    
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            ps.setString(3, Busqueda);
            rs = ps.executeQuery();
            limpiarDiferencia();

            while(rs.next()) 
            {
                //LLENADO DE TABLA
               int col1 = rs.getInt(1);
               String col2 = rs.getString(2);
               String col3 = rs.getString(3);
               String col4 = rs.getString(4);
               String col5 = rs.getString(5);
               String col6 = rs.getString(6);
               String col7 = rs.getString(7);
               String col8 = rs.getString(8);
               
               Vector vRow=new Vector();
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
            acomodarceldas2();
        }
        
        catch(Exception e)
        {
            System.out.println(""+e);
        }
  }
      
     
    
    public void searchHorario() {
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel Tabla = (DefaultTableModel)JTableHorario.getModel();
    JTableHorario.setDefaultEditor(Object.class, null);

    String Busqueda = "%" + this.TXTfindbyName.getText() + "%"; 

    String sql = "SELECT id_horario, e.especialidad, Doctor, Entrada, Salida,am_pm, Observaciones, Dias_disponible,Estado\n" +
                 "FROM horario_doctores u \n" +
                 "INNER JOIN table_especialidad e ON u.id_especialidad = e.id_especialidad \n " +
                 "INNER JOIN table_estado o ON u.EstadoHorario = o.IdEstado  \n" +
                 "WHERE  Doctor LIKE ? ORDER BY id_horario ASC";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, Busqueda);
        rs = ps.executeQuery();
        limpiarTabla();

        while(rs.next()) {
            int col1 = rs.getInt(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            String col4 = rs.getString(4);
            String col5 = rs.getString(5);
            String col6 = rs.getString(5);
            String col7 = rs.getString("am_pm");
            String col8 = rs.getString("Observaciones");
            String col9 = rs.getString("Dias_disponible");
            String col10 = rs.getString("Estado");
         

            Vector vRow=new Vector();
            vRow.add(col1);
            vRow.add(col2);
            vRow.add(col3);
            vRow.add(col4);
            vRow.add(col5);
            vRow.add(col6);
            vRow.add(col7);
            vRow.add(col8);
            vRow.add(col9);
            vRow.add(col10);

            Tabla.addRow(vRow);
        } 
        acomodarceldas();
    } catch(Exception e) {
        e.printStackTrace(); // Modificado para imprimir la traza completa del error
    }
}

      
      
      
      
     public void textES(){
        jTextEntrada.setText("00:00");
        jTextEntrada.setForeground(Color.GRAY);
        jTextSalida.setText("00:00");
        jTextSalida.setForeground(Color.GRAY);
        
        
        jTextEntrada1.setText("00:00");
        jTextEntrada1.setForeground(Color.GRAY);
        jTextSalida1.setText("00:00");
        jTextSalida1.setForeground(Color.GRAY);
        
                
        
     }
     
     
     
 String diferenciaTiempo,  difTimeOut;

 
public void calDif(){



String entradaInicial =  EntradaI;
String entradaFinal =    jTextEntrada1.getText();

// Separar las partes de la entrada inicial y final
String[] partesInicial = entradaInicial.split(":");
String[] partesFinal = entradaFinal.split(":");

// Convertir las partes a enteros
int horasInicial = Integer.parseInt(partesInicial[0]);
int minutosInicial = Integer.parseInt(partesInicial[1]);
int horasFinal = Integer.parseInt(partesFinal[0]);
int minutosFinal = Integer.parseInt(partesFinal[1]);

// Calcular los minutos totales
int minutosTotalesInicial = horasInicial * 60 + minutosInicial;
int minutosTotalesFinal = horasFinal * 60 + minutosFinal;

// Calcular la diferencia de minutos
int diferenciaMinutos = minutosTotalesFinal - minutosTotalesInicial;

// Calcular la diferencia de horas
int diferenciaHoras = Math.abs(diferenciaMinutos / 60);  // Tomar el valor absoluto de la diferencia de horas

// Formatear la diferencia de tiempo

if (diferenciaMinutos > 0) {
    diferenciaTiempo = String.format("%02d:%02d  TARDE", diferenciaHoras, Math.abs(diferenciaMinutos) % 60);
} else if (diferenciaMinutos < 0) {
    diferenciaTiempo = String.format("%02d:%02d  TEMPRANO", diferenciaHoras, Math.abs(diferenciaMinutos) % 60);
} else {
    diferenciaTiempo = "00:00"; // La diferencia es exactamente cero minutos
}




//DIFERENCIA SALIDA



String salidaInicial =  SalidaI;
String salidaFinal =    jTextSalida1.getText();

// Separar las partes de la entrada inicial y final
String[] partesI = salidaInicial.split(":");
String[] partesF = salidaFinal.split(":");

// Convertir las partes a enteros
int horasI = Integer.parseInt(partesI[0]);
int minutosI = Integer.parseInt(partesI[1]);
int horasF = Integer.parseInt(partesF[0]);
int minutosF = Integer.parseInt(partesF[1]);

// Calcular los minutos totales
int minTotalesInicial = horasI * 60 + minutosI;
int minTotalesFinal = horasF * 60 + minutosF;

// Calcular la diferencia de minutos
int difMinutos = minTotalesFinal - minTotalesInicial;

// Calcular la diferencia de horas
int difHoras = Math.abs(difMinutos / 60);  // Tomar el valor absoluto de la diferencia de horas

// Formatear la diferencia de tiempo


if (difMinutos > 0) {
    difTimeOut = String.format("%02d:%02d  TARDE", difHoras, Math.abs(difMinutos) % 60);
} else if (difMinutos < 0) {
    difTimeOut = String.format("%02d:%02d  TEMPRANO", difHoras, Math.abs(difMinutos) % 60);
} else {
    difTimeOut = "00:00"; // La diferencia es exactamente cero minutos
}


     



     }
     
     
     
     
     public void agregarDif(){                                             
    
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
    

          try {

              
            String sql = "INSERT INTO `diferencia_horario`(`id_horario`, `entrada`, `dif_entrada`, `salida`, `dif_salida`, `motivo`, `observacion`, `Fecha`) VALUES (?,?,?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            String fechaH = new SimpleDateFormat("yyyy/MM/dd").format(FechaOne1.getDate());
          //  JComboEspecialidad1.getSelectedItem().toString()
            ps.setInt(1, idHorario);
            ps.setString(2, jTextEntrada1.getText());
            ps.setString(3, diferenciaTiempo );
            
            ps.setString(4, jTextSalida1.getText());
            ps.setString(5, difTimeOut );
            ps.setString(6, JTextretraso.getText());
            ps.setString(7,  JTextObs.getText());
            ps.setString(8, fechaH);
           
           
            
            ps.execute();
  
            
         limpiarTabla();  listarHorarios();  limpiarCampos();
         JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO DEL DR: " +JComboEspecialidad3.getSelectedItem().toString(),"REGISTRO", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }

    } 
    
     
     
     
      
public void graficoEspecialidad() throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        
        String rutaDestino = "C:\\Fundaginebra\\Graficas\\graficaResumen.png"; 
      

        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Obtener los datos de la base de datos y agregarlos al dataset
        String sql = "SELECT h.especialidad, " +
                     "SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i'))) AS total_segundos " +
                     "FROM diferencia_horario u " +
                     "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario " +
                     "INNER JOIN table_especialidad h ON e.id_especialidad=h.id_especialidad " +
                     "WHERE Fecha BETWEEN ? AND ? AND dif_entrada LIKE '%TARDE%' " +
                     "GROUP BY h.especialidad;";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();

        double totalSegundosTotal = 0;

    while (rs.next()) {
    String especialidad = rs.getString("especialidad");
    int totalSegundos = rs.getInt("total_segundos");
    int horas = totalSegundos / 3600;
    int minutos = (totalSegundos % 3600) / 60;
    String tiempo = horas + ":" + String.format("%02d", minutos); // Formatear los minutos con dos dígitos
    dataset.setValue(especialidad + " (" + tiempo + ")", horas + (minutos / 60.0)); // Agregar horas y minutos como valor y formato de etiqueta
    totalSegundosTotal += totalSegundos;
}

        // Calcular los porcentajes para cada especialidad
PiePlot plot = new PiePlot(dataset);
for (Object key : dataset.getKeys()) {
    double value = dataset.getValue((Comparable) key).doubleValue();
    double percentage = (value / (totalSegundosTotal / 3600.0)) * 100.0;
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
}

        JFreeChart jf = new JFreeChart("R E P O R T E     G E N E R A L", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        
        // Guardar la gráfica como imagen PNG
        ChartUtilities.saveChartAsPNG(new File(rutaDestino), jf, 900, 500);
    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.toString());
    } finally {
        // Cerrar conexiones y liberar recursos
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }
}

     
     
     
     
     
     
     
     
     

public void graficoPdf() throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        
        String rutaDestino = "C:\\Fundaginebra\\Graficas\\graficaEntrada.png"; 
        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();
        double total = 0.0; // Para calcular el total

        // Obtener los datos de la base de datos y agregarlos al dataset
String sql = "SELECT " +
    "e.Doctor, " +
    "COUNT(*) AS veces_tarde " +
    "FROM " +
    "diferencia_horario u " +
    "INNER JOIN " +
    "horario_doctores e ON u.id_horario = e.id_horario " +
    "WHERE " +
    "Fecha BETWEEN ? AND ? " + 
    "AND dif_entrada LIKE '%TARDE%' " + 
    "AND TIME_TO_SEC(STR_TO_DATE(SUBSTRING_INDEX(dif_entrada, ' ', 1), '%H:%i')) > 0 " +
    "GROUP BY " +
    "e.Doctor;";

con = cn.getConnection();
ps = con.prepareStatement(sql);
ps.setString(1, fecha);
ps.setString(2, fecha2);
rs = ps.executeQuery();

        while (rs.next()) {
            String estudio = rs.getString("e.Doctor");
            int num = rs.getInt("veces_tarde");
            total += num; // Sumar al total
            dataset.setValue(estudio, num);
        }

        JFreeChart jf = ChartFactory.createPieChart("R E P O R T E     E N T R A D A S", dataset, true, true, false);

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
        System.out.println("error en grafica" + e.toString());
    }
}






public void graficoSalidas() throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String rutaDestino = "C:\\Fundaginebra\\Graficas\\graficaSalidas.png"; 


        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();
        double total = 0.0; // Para calcular el total

        // Obtener los datos de la base de datos y agregarlos al dataset
    String sql = "SELECT " +
    "e.Doctor, " +
    "COUNT(*) AS veces_tarde " +
    "FROM " +
    "diferencia_horario u " +
    "INNER JOIN " +
    "horario_doctores e ON u.id_horario = e.id_horario " +
    "WHERE " +
    "Fecha BETWEEN ? AND ? " + 
    "AND dif_salida LIKE '%TEMPRANO%' " + 
    "AND TIME_TO_SEC(STR_TO_DATE(SUBSTRING_INDEX(dif_salida, ' ', 1), '%H:%i')) > 0 " +
    "GROUP BY " +
    "e.Doctor;";

con = cn.getConnection();
ps = con.prepareStatement(sql);
ps.setString(1, fecha);
ps.setString(2, fecha2);
rs = ps.executeQuery();

        while (rs.next()) {
            String estudio = rs.getString("e.Doctor");
            int num = rs.getInt("veces_tarde");
            total += num; // Sumar al total
            dataset.setValue(estudio, num);
        }

        JFreeChart jf = ChartFactory.createPieChart("R E P O R T E     S A L I D A S", dataset, true, true, false);

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
        System.out.println("error en grafica" + e.toString());
        JOptionPane.showMessageDialog(null, "NO SE ENCUENTRAN REGISTROS DE SALIDAS", "SALIDAS", 1);
    }
}

     


     
 

public void graficarEverything(String especialidades) throws IOException {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        
        String rutaDestino = "C:\\Fundaginebra\\Graficas\\grafica"+especialidades+".png"; 
      

        // Crear la gráfica y configurarla
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Obtener los datos de la base de datos y agregarlos al dataset
String sql = "SELECT  \n" +
    "    e.doctor, \n" +
    "    CONCAT( \n" +
    "    FLOOR(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i'))) / 3600), \n" +
    "    ':', \n" +
    "    LPAD(MOD(SUM(TIME_TO_SEC(STR_TO_DATE(REGEXP_REPLACE(dif_entrada, '[^0-9:]+', ''), '%H:%i'))) / 60, 60), 2, '0')\n" +
    "    ) AS tiempo \n" +
    "    FROM \n" +
    "    diferencia_horario u \n" +
    "    INNER JOIN \n" +
    "    horario_doctores e ON u.id_horario = e.id_horario \n" +
    "    INNER JOIN \n" +
    "    table_especialidad h ON e.id_especialidad = h.id_especialidad \n" +
    "    WHERE \n" +
    "    Fecha BETWEEN ? AND ? \n" +
    "    AND dif_entrada LIKE '%TARDE%' \n" +
    "    AND h.especialidad = '"+ especialidades + "'\n" +
    "    GROUP BY  \n" +
    "    e.doctor \n" +
    "    LIMIT 0, 25;";


        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();

        double totalSegundosTotal = 0;

  while (rs.next()) {
    String especialidad = rs.getString("e.doctor");
    String tiempoStr = rs.getString("tiempo");
    
    String[] tiempoParts = tiempoStr.split(":");
    int horas = Integer.parseInt(tiempoParts[0]);
    int minutos = corregirFormatoMinutos(tiempoParts[1]);
    String tiempo = horas + ":" + String.format("%02d", minutos);
    dataset.setValue(especialidad + " (" + tiempo + ")", horas + (minutos / 60.0));
    totalSegundosTotal += (horas * 3600) + (minutos * 60);
}
  
  
        // Calcular los porcentajes para cada especialidad
PiePlot plot = new PiePlot(dataset);
for (Object key : dataset.getKeys()) {
    double value = dataset.getValue((Comparable) key).doubleValue();
    double percentage = (value / (totalSegundosTotal / 3600.0)) * 100.0;
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
}

        JFreeChart jf = new JFreeChart("R E P O R T E     G E N E R A L", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        
        // Guardar la gráfica como imagen PNG
        ChartUtilities.saveChartAsPNG(new File(rutaDestino), jf, 900, 500);
    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.toString());
    } finally {
        // Cerrar conexiones y liberar recursos
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }
}


private int corregirFormatoMinutos(String minutosStr) {
    // Eliminar cualquier carácter no numérico al final de la cadena de minutos
    if (minutosStr.length() > 1 && !Character.isDigit(minutosStr.charAt(minutosStr.length() - 1))) {
        minutosStr = minutosStr.substring(0, minutosStr.length() - 1);
    }

    // Si la longitud de los minutos es 1, simplemente agregar un cero al principio
    if (minutosStr.length() == 1) {
        minutosStr = "0" + minutosStr;
    }

    // Convertir la cadena de minutos a entero
    return Integer.parseInt(minutosStr);
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
    

     
     
     
     
     
     
         
      public void actualizarDif() {
   
    Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;

        try {
          
 
           String sql = "UPDATE `horario_doctores` SET `id_especialidad`=(SELECT `id_especialidad` FROM `table_especialidad` WHERE `especialidad`=?), `Doctor`=?, `Entrada`=?, `Salida`=?, `Observaciones`=?  WHERE `id_horario`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, JComboEspecialidad1.getSelectedItem().toString());
            ps.setString(2, JComboDoctor.getSelectedItem().toString());
            ps.setString(3, jTextEntrada.getText());
            ps.setString(4, jTextSalida.getText());
            ps.setString(5, jTextObservacion.getText());
            ps.setInt(6, idHorarios);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "Horario Actualizado", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
              
    }

     
     
    // VALIDAR EXISTENCIAS 
      
      
      
      
    public boolean hayDatos() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    boolean hayDatos = false;

    try {
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

        String sql = "SELECT COUNT(*) AS total FROM diferencia_horario u " +
                     "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario " +
                     "INNER JOIN table_especialidad h ON e.id_especialidad = h.id_especialidad " +
                     "WHERE Fecha BETWEEN ? AND ? AND dif_entrada LIKE '%TARDE%'";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        rs = ps.executeQuery();

        if (rs.next()) {
  
            int total = rs.getInt("total");
    //System.out.println("Total de registros encontrados: " + total);
    hayDatos = (total > 0);
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

    return hayDatos;
}
     
     

    
    public boolean hayRegistros(String especialidades) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    boolean hayRegistros = false;

    try {
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());

        String sql = "SELECT COUNT(*) AS total FROM diferencia_horario u " +
                     "INNER JOIN horario_doctores e ON u.id_horario = e.id_horario " +
                     "INNER JOIN table_especialidad h ON e.id_especialidad = h.id_especialidad " +
                     "WHERE Fecha BETWEEN ? AND ? AND dif_entrada LIKE '%TARDE%' AND h.especialidad = ?";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fecha1);
        ps.setString(2, fecha2);
        ps.setString(3, especialidades);
        rs = ps.executeQuery();

        if (rs.next()) {
            int total = rs.getInt("total");
          //  System.out.println("Total de registros encontrados para " + especialidades + ": " + total);
            hayRegistros = (total > 0);
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

    return hayRegistros;
}
    
    
     
    
    
       public void llenarDrs() {

       try{
      
        JComboDoctor.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = llenarDoctoresAsignar(); 
        for (int i = 0; i < lista.size(); i++) {
            JComboDoctor.addItem(lista.get(i));

        }
  
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};

          }
    
     
    
      
     
   public ArrayList<String> llenarDoctoresAsignar() {
    ArrayList<String> lista = new ArrayList<>();
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String especialidad = JComboEspecialidad1.getSelectedItem().toString();
    String sql = "SELECT Usuario, especialidad, Pestado FROM table_usuario u\n" +
                 "INNER JOIN table_especialidad c\n" +
                 "ON u.idEspecialidad = c.id_especialidad\n" +
                 "WHERE Pestado = 100 AND especialidad = ? ORDER BY Usuario ASC";
    
    

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, especialidad);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            String usuario = rs.getString("Usuario");
            String especialidadDB = rs.getString("especialidad");
            int pestado = rs.getInt("Pestado");


            lista.add(usuario);
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    return lista;
}
      
    
       
       
       
       
       
    
    
     
     
    
     public void limpiarCampos(){

 
     TXTfindbyName.setText("");
     jTextEntrada.setText("");
     jTextSalida.setText("");
     jTextObservacion.setText("");
     jCheckLunes.setSelected(false);
     jCheckMartes.setSelected(false);
     jCheckMiercoles.setSelected(false);
     jCheckJueves.setSelected(false);
     jCheckViernes.setSelected(false);
     jCheckSabado.setSelected(false);
     jCheckDomingo.setSelected(false);
     jRadioPm.setSelected(false);
     jRadioAll.setSelected(false);
     jRadioAm.setSelected(true);
   
     lunes=false; 
     martes=false;
     miercoles=false; 
     jueves=false; 
     viernes=false; 
     sabado=false;
     domingo=false;
     actualizarDiasSeleccionados();
 
     }
     
     
     
      
     public void limpiarCampos2(){


     TXTfindbyName.setText("");
     jTextEntrada1.setText("");
     jTextSalida1.setText("");
     JTextretraso.setText("");
     JTextObs.setText("");
     }
     
     
  public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) JTableHorario.getModel();
        int a = JTableHorario.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

    
    public  void limpiarDiferencia() {
        DefaultTableModel tb = (DefaultTableModel) JTableHorario1.getModel();
        int a = JTableHorario1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
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
  JCPacientes pacientes  = new JCPacientes();
  JCProcedimientosDao ProcesosDao  = new  JCProcedimientosDao();

  Mprincipal Menu = new Mprincipal();
  Encriptar encriptar = new Encriptar(); 
  JCambiarState setState = new JCambiarState();
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
    
     
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAg;
    private javax.swing.JButton BtnAg2;
    private javax.swing.JButton BtnLimpiar1;
    private javax.swing.JButton BtnLimpiar3;
    private javax.swing.JButton BtnModif;
    private javax.swing.JButton BtnModif2;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JLabel FechaAc3;
    public com.toedter.calendar.JDateChooser FechaOne;
    public com.toedter.calendar.JDateChooser FechaOne1;
    public com.toedter.calendar.JDateChooser FechaTwo;
    private javax.swing.JComboBox<String> JComboDoctor;
    private javax.swing.JComboBox<String> JComboEspecialidad1;
    private javax.swing.JComboBox<String> JComboEspecialidad3;
    private javax.swing.JTable JTableHorario;
    private javax.swing.JTable JTableHorario1;
    private javax.swing.JTextArea JTextObs;
    private javax.swing.JTextArea JTextretraso;
    private javax.swing.JPopupMenu MenuPopup;
    private javax.swing.JTextField TXTfindbyName;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckDomingo;
    private javax.swing.JCheckBox jCheckJueves;
    private javax.swing.JCheckBox jCheckLunes;
    private javax.swing.JCheckBox jCheckMartes;
    private javax.swing.JCheckBox jCheckMiercoles;
    private javax.swing.JCheckBox jCheckSabado;
    private javax.swing.JCheckBox jCheckViernes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadiaTab;
    private javax.swing.JRadioButton jRadioAll;
    private javax.swing.JRadioButton jRadioAm;
    private javax.swing.JRadioButton jRadioDoctores;
    private javax.swing.JRadioButton jRadioEsta;
    private javax.swing.JRadioButton jRadioEsta1;
    private javax.swing.JRadioButton jRadioPm;
    private javax.swing.JButton jReporte1;
    private javax.swing.JRadioButton jResumen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton jSearch;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPanel;
    private javax.swing.JTextField jTextEntrada;
    private javax.swing.JTextField jTextEntrada1;
    private javax.swing.JTextArea jTextObservacion;
    private javax.swing.JTextField jTextSalida;
    private javax.swing.JTextField jTextSalida1;
    // End of variables declaration//GEN-END:variables
}
