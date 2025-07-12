/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

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
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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


        
        
        
        
        llenarCombo();
        llenarDrs();

        limpiarTabla(); 
        listarHorarios();

        informacionpdf();
        BtnAg.setVisible(false);
        BtnModif.setVisible(false);
        BtnLimpiar1.setVisible(false);
       // jRadioAm.setSelected(true);
        jTabbedPanel.setBorder(BorderFactory.createEmptyBorder());   
         
             String Nivel=TM.getNivel();
             if(Nivel.equals("Administrador")){
             Activar.setEnabled(true); 
             Desactivar.setEnabled(true);
             DejarLibre.setEnabled(true);
             QuitarLibre.setEnabled(true);
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
        DejarLibre = new javax.swing.JMenuItem();
        QuitarLibre = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        FechaAc3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPanel = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
        TXTfindbyName = new javax.swing.JTextField();
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

        Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        Desactivar.setText("Desactivar");
        Desactivar.setEnabled(false);
        Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Desactivar);

        DejarLibre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delegar_1.png"))); // NOI18N
        DejarLibre.setText("Dejar Horario Libre");
        DejarLibre.setEnabled(false);
        DejarLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DejarLibreActionPerformed(evt);
            }
        });
        MenuPopup.add(DejarLibre);

        QuitarLibre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        QuitarLibre.setText("Quitar Horio Libre");
        QuitarLibre.setEnabled(false);
        QuitarLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarLibreActionPerformed(evt);
            }
        });
        MenuPopup.add(QuitarLibre);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/basura.png"))); // NOI18N
        Eliminar.setText("Eliminar Horario");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        MenuPopup.add(Eliminar);

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
        FechaAc3.setText("M Ó D U L O   H O R A R I O");
        jPanel5.add(FechaAc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPanel.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPanel.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 300, 90));
        jPanel6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 360, 20));

        jCheckDomingo.setBackground(new java.awt.Color(255, 255, 255));
        jCheckDomingo.setText("Domingo");
        jCheckDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckDomingoActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckDomingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        jCheckLunes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckLunes.setText("Lunes");
        jCheckLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckLunesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jCheckMartes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckMartes.setText("Martes");
        jCheckMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckMartesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        jCheckMiercoles.setBackground(new java.awt.Color(255, 255, 255));
        jCheckMiercoles.setText("Miércoles");
        jCheckMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckMiercolesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        jCheckJueves.setBackground(new java.awt.Color(255, 255, 255));
        jCheckJueves.setText("Jueves");
        jCheckJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckJuevesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        jCheckViernes.setBackground(new java.awt.Color(255, 255, 255));
        jCheckViernes.setText("Viernes");
        jCheckViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckViernesActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jCheckSabado.setBackground(new java.awt.Color(255, 255, 255));
        jCheckSabado.setText("Sabado");
        jCheckSabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSabadoActionPerformed(evt);
            }
        });
        jPanel6.add(jCheckSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        JComboDoctor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Especialista"));
        jPanel6.add(JComboDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 300, 50));

        jTabbedPanel.addTab("tab1", jPanel6);

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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 910, 100));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTableHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ESPECIALIDAD", "DOCTOR", "HORARIO", "DIAS", "ESTADO", "OBSERVACION"
            }
        ));
        JTableHorario.setComponentPopupMenu(MenuPopup);
        JTableHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableHorarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JTableHorarioMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(JTableHorario);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 500));

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

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 910, 540));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTfindbyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyReleased
     
   
        searchHorario();
      
       
        
    }//GEN-LAST:event_TXTfindbyNameKeyReleased

    private void TXTfindbyNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyTyped
      
    }//GEN-LAST:event_TXTfindbyNameKeyTyped
  
    int idHorarios=0;
    String EstadoTexto = "", nombreprocedimiento, variableTiempo="", observacion;
    String horas="", dias="";
    
    private void JTableHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorarioMouseClicked
 int fila = JTableHorario.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
    } else {
        BtnModif.setEnabled(true);
        BtnAg.setEnabled(false);
        
        
        Object value = JTableHorario.getValueAt(fila, 0);
        idHorarios = (value != null) ? (int) value : 0; 
        
        JComboEspecialidad1.setSelectedItem(getStringValueOrDefault(JTableHorario.getValueAt(fila, 1)));
        JComboDoctor.setSelectedItem(getStringValueOrDefault(JTableHorario.getValueAt(fila, 2)));
        
        horas=(getStringValueOrDefault(JTableHorario.getValueAt(fila, 3)));
        dias=(getStringValueOrDefault(JTableHorario.getValueAt(fila, 4)));
        
        EstadoTexto = getStringValueOrDefault(JTableHorario.getValueAt(fila, 5));
        jTextObservacion.setText(getStringValueOrDefault(JTableHorario.getValueAt(fila, 6)));
        variableTiempo=(JTableHorario.getValueAt(fila, 5).toString());
       
        observacion=(getStringValueOrDefault(JTableHorario.getValueAt(fila, 6)));
        
        
        
        
        diasSeleccionados = dias.split(",");
        horarioSelect =  horas.split(",");

        lunes = Arrays.asList(diasSeleccionados).contains("Lunes");
        martes = Arrays.asList(diasSeleccionados).contains("Martes");
        miercoles = Arrays.asList(diasSeleccionados).contains("Miércoles");
        jueves = Arrays.asList(diasSeleccionados).contains("Jueves");
        viernes = Arrays.asList(diasSeleccionados).contains("Viernes");
        sabado = Arrays.asList(diasSeleccionados).contains("Sábado");
        domingo = Arrays.asList(diasSeleccionados).contains("Domingo");
       

        checkDays();
        Arrays.asList(diasSeleccionados).contains(dias);
        Arrays.asList(horarioSelect).contains(horas);
       // obtenerDiasSeleccionados();
        
        
        System.out.println(Arrays.toString(diasSeleccionados));
        System.out.println(Arrays.toString(horarioSelect));
        
    }                                 
    }//GEN-LAST:event_JTableHorarioMouseClicked

    
   
     
 public void checkDays() {
    jCheckLunes.setSelected(lunes);
    jCheckMartes.setSelected(martes);
    jCheckMiercoles.setSelected(miercoles);
    jCheckJueves.setSelected(jueves);
    jCheckViernes.setSelected(viernes);
    jCheckSabado.setSelected(sabado);
    jCheckDomingo.setSelected(domingo);
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
        
         
           if (exist){
           JOptionPane.showMessageDialog(null, "ESTE DOCTOR YA SE HA AGREGADO", "DOCTORES", JOptionPane.ERROR_MESSAGE);
            }
           
           else if(!jCheckLunes.isSelected() && !jCheckMartes.isSelected() && !jCheckMiercoles.isSelected() && !jCheckJueves.isSelected()
                   && !jCheckViernes.isSelected() && !jCheckSabado.isSelected() && !jCheckDomingo.isSelected()){
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR LOS DIAS DE CONSULTA", "HORARIO", JOptionPane.ERROR_MESSAGE);
           }
         
        
         
           else{
            
              agregarHorario(); 
              limpiarTabla(); 
              listarHorarios(); 
              auditAgregarHorario();
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
   ;
             
     
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
          else if (setState.activarHorario(100,     idHorarios )) {

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

    
    int idDiferencia;
    private void JTableHorario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorario1MouseClicked
    
    }//GEN-LAST:event_JTableHorario1MouseClicked

    private void TXTfindbyNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyPressed
       
    }//GEN-LAST:event_TXTfindbyNameKeyPressed

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

    
    
    
 

  boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;

    JTextField entrada = new JTextField(10);
   JTextField salida = new JTextField(10);
    private void jCheckLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckLunesActionPerformed
          
          lunes= jCheckLunes.isSelected() ? true : false;
      /*  
  
    
    
    int fila = JTableHorario.getSelectedRow();
    if (fila >= 0) {
    jCheckLunes.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           return;
    }
        
          */ 
    
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckLunes.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
        
     
    }//GEN-LAST:event_jCheckLunesActionPerformed

    private void jCheckMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckMartesActionPerformed

        
    martes= jCheckMartes.isSelected() ? true : false;
      JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckMartes.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
      
      
    }//GEN-LAST:event_jCheckMartesActionPerformed

    private void jCheckMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckMiercolesActionPerformed
    miercoles= jCheckMiercoles.isSelected() ? true : false;
      JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckMiercoles.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
    }//GEN-LAST:event_jCheckMiercolesActionPerformed

    private void jCheckJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckJuevesActionPerformed
    jueves= jCheckJueves.isSelected() ? true : false;
       JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckJueves.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
    }//GEN-LAST:event_jCheckJuevesActionPerformed

    private void jCheckViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckViernesActionPerformed
    viernes= jCheckViernes.isSelected() ? true : false;
      JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckViernes.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
    }//GEN-LAST:event_jCheckViernesActionPerformed

    private void jCheckSabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSabadoActionPerformed
    sabado= jCheckSabado.isSelected() ? true : false;
      JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckSabado.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
    }//GEN-LAST:event_jCheckSabadoActionPerformed

    private void jCheckDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckDomingoActionPerformed
    domingo= jCheckDomingo.isSelected() ? true : false;
      JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Hora de entrada:"));
            panel.add(entrada);
            panel.add(new JLabel("Hora de salida:"));
            panel.add(salida);

            int result = JOptionPane.showConfirmDialog(null, panel, "Horario del doctor: " + JComboDoctor.getSelectedItem(), JOptionPane.OK_CANCEL_OPTION);
            
           if (result == JOptionPane.OK_OPTION) {
                entradaTexto = entrada.getText().trim();
                salidaTexto = salida.getText().trim();
               
                diasSeleccionados = obtenerDiasSeleccionados();
                arrayHorario();
    
           }else{
           jCheckDomingo.setSelected(false);
           diasSeleccionados = obtenerDiasSeleccionados();
           eliminarDiasNoSeleccionados();
           } 
    }//GEN-LAST:event_jCheckDomingoActionPerformed

    private void JComboEspecialidad1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboEspecialidad1ItemStateChanged
          if(JComboEspecialidad1.getSelectedIndex()!=-1){
          
          llenarDrs();
         
          }
    }//GEN-LAST:event_JComboEspecialidad1ItemStateChanged

    private void DejarLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DejarLibreActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea dejar libre el horario de este doctor?",
            "HABILITAR HORARIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableHorario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (observacion.equals("Habilitado Indefinido")){ JOptionPane.showMessageDialog(null, "ESTE DOCTOR YA SE ENCUENTRA HABILITADO", "Estado", JOptionPane.WARNING_MESSAGE);}
          else {
          
           habilitarIndefinido(100, "Habilitado Indefinido");
           JOptionPane.showMessageDialog(null, "SE HA HABILITADO ESTE DOCTOR DE MANERA INDEFINIDA", "Estado", 1);
           limpiarTabla();
           listarHorarios(); 
           acomodarceldas();
           String accion= "Coloco indefinido el horario del Dr: " +JComboDoctor.getSelectedItem().toString() +" " ;
           auditDejarLibre(accion);
             
             
            }
        }

            
            
         }
    }//GEN-LAST:event_DejarLibreActionPerformed

    
     private void habilitarIndefinido(int idState, String msg) {
       
        
        DefaultTableModel modelo = (DefaultTableModel) JTableHorario.getModel();
        int columnaId = 0;
        int columnaPaciente = 2;
      
        int[] filasSeleccionadas = JTableHorario.getSelectedRows();

        // Itera sobre las filas seleccionadas y actualiza el estado
        for (int fila : filasSeleccionadas) {
            int id = (int) modelo.getValueAt(fila, columnaId); // Obtiene el ID
            String nombreDoctor = modelo.getValueAt(fila, columnaPaciente).toString(); 
            // Realiza la actualización del estado en la base de datos utilizando el ID
            setState.activarHabilitar(idState, id, msg);
            String accion= "Coloco indefinido el horario del Dr: " +JComboDoctor.getSelectedItem().toString() +" " ;
            auditDejarLibre(accion);
     
        }
    }
    
    
    
    
    private void QuitarLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarLibreActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea establecer el horario anterior de este doctor?",
            "HABILITAR HORARIO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableHorario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      

           habilitarIndefinido(101, "");
           JOptionPane.showMessageDialog(null, "SE HA ACTUALIZADO EL HORARIO DEL DOCTOR A: DEFINIDO", "Estado", 1);
           limpiarTabla();
           listarHorarios(); 
           acomodarceldas();
           String accion= "Coloco definido el horario del Dr: " +JComboDoctor.getSelectedItem().toString() +" " ;
           auditDejarLibre(accion);
             
             
            
        }

            
            
         }
    }//GEN-LAST:event_QuitarLibreActionPerformed

    private void JTableHorarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableHorarioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JTableHorarioMouseEntered

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
      eliminarServicio();
    }//GEN-LAST:event_EliminarActionPerformed

    String tiempoDay="AM";
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
        

        eliminarDiasDesactivados(listaDias);
        
        // Convierte la lista a un array
        diasSeleccionados = listaDias.toArray(new String[0]);
      
        System.out.println(Arrays.toString(diasSeleccionados));
        // Retorna el array
        return diasSeleccionados;
        
    }
     
     
    
  
  
    
    
   
    
    
    
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
    
     
   public void auditEliminar(){     
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Elimino el horario del Dr/a: " +JComboDoctor.getSelectedItem().toString() +" " ;
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
      
    
    
       
        public void auditDejarLibre(String accion){
            
           
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
       
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
            ob[4] = lista.get(i).getDiasHorario();
            ob[5] = lista.get(i).getEstadoHora();
            ob[6] = lista.get(i).getObservacionHora();
            
            modelo.addRow(ob);

        }
       JTableHorario.setModel(modelo);
       JTableHorario.setDefaultEditor(Object.class, null);
       JTableHorario.getTableHeader().setReorderingAllowed(false); 
       DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) JTableHorario.getTableHeader().getDefaultRenderer();
       headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
       acomodarceldas();

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
        }  finally {
            closeResources(rs, ps, con);
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
            
         
         
         
       
         
      
         
         


     
   
        
        
        
        
        
        
        
        
     
    
    public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTableHorario.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHorario.setRowHeight(20);
        
        JTableHorario.getColumnModel().getColumn(0).setMaxWidth(0);
        JTableHorario.getColumnModel().getColumn(0).setMinWidth(0);
        JTableHorario.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
        JTableHorario.getColumnModel().getColumn(4).setMaxWidth(0);
        JTableHorario.getColumnModel().getColumn(4).setMinWidth(0);
        JTableHorario.getColumnModel().getColumn(4).setPreferredWidth(0);
     
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setPreferredWidth(40);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setPreferredWidth(80);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("HORARIO")).setPreferredWidth(220);
    //    JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DIAS")).setPreferredWidth(200);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(10);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("OBSERVACION")).setPreferredWidth(80);
        

        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DOCTOR")).setCellRenderer(Alinear);
        JTableHorario.getColumnModel().getColumn(Tabla.findColumn("HORARIO")).setCellRenderer(Alinear);
 //       JTableHorario.getColumnModel().getColumn(Tabla.findColumn("DIAS")).setCellRenderer(Alinear);
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
    
      
      

      
      
      
      
      
     public void agregarHorario(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

              
            String sql = "INSERT INTO `horario_doctores`( `id_especialidad`, `Doctor`, `Horario`, `Observaciones`, Dias_disponible) VALUES ((SELECT id_especialidad FROM table_especialidad WHERE especialidad=?),(SELECT IdPersonal FROM table_usuario WHERE usuario=?),?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            
            ps.setString(1, JComboEspecialidad1.getSelectedItem().toString());
            ps.setString(2, JComboDoctor.getSelectedItem().toString());
            String horarioStr = String.join(",", horarioSelect);
            ps.setString(3, horarioStr);
            ps.setString(4, jTextObservacion.getText());
            String diasSeleccionadosStr = String.join(",", diasSeleccionados);
            ps.setString(5, diasSeleccionadosStr);
           
               
            ps.execute();
  
            
         limpiarTabla();  listarHorarios();  limpiarCampos();
         JOptionPane.showMessageDialog(null, "HORARIO REGISTRADO","REGISTRO ", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        } finally {
            closeResources(rs, ps, con);
        }


      
          
      
    } 
    
 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
      public void actualizarHorarios() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "UPDATE `horario_doctores` SET `id_especialidad`=(SELECT `id_especialidad` FROM `table_especialidad` WHERE `especialidad`=?), `Doctor`=(SELECT IdPersonal  FROM table_usuario WHERE usuario=?), `Horario`=?, `Observaciones`=?, Dias_disponible=?  WHERE `id_horario`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, JComboEspecialidad1.getSelectedItem().toString());
            ps.setString(2, JComboDoctor.getSelectedItem().toString());
            String horarioStr = String.join(",", horarioSelect);
            ps.setString(3, horarioStr);
            ps.setString(4, jTextObservacion.getText());
            String diasSeleccionadosStr = String.join(",", diasSeleccionados);
            ps.setString(5, diasSeleccionadosStr);
       
            ps.setInt(6, idHorarios);
         
           
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "Horario Actualizado", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "PROCEDIMIENTOS"+ e, JOptionPane.ERROR_MESSAGE);
           } finally {
            closeResources(rs, ps, con);
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

String sql = "SELECT id_horario, Especialidad, s.Usuario, Horario, Estado, observaciones, Dias_disponible, s.Pestado " +
             "FROM horario_doctores u " +
             "INNER JOIN table_especialidad e ON u.id_especialidad = e.id_especialidad " +
             "INNER JOIN table_estado o ON u.EstadoHorario = o.IdEstado " +
             "INNER JOIN table_usuario s ON u.Doctor = s.IdPersonal " +
             "WHERE s.Usuario LIKE ?  " +
             "ORDER BY id_horario ASC";

//AND o.IdEstado = 100

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, Busqueda);
        rs = ps.executeQuery();
        limpiarTabla();

        while(rs.next()) {
            int col1 = rs.getInt("id_horario");
            String col2 = rs.getString("Especialidad");
            String col3 = rs.getString("s.Usuario");
            String col4 = rs.getString("Horario");
            String col7 = rs.getString("Dias_disponible");
            String col8 = rs.getString("Estado");
            String col9 = rs.getString("Observaciones");
    
         

            Vector vRow=new Vector();
            vRow.add(col1);
            vRow.add(col2);
            vRow.add(col3);
            vRow.add(col4);
            vRow.add(col7);
            vRow.add(col8);
            vRow.add(col9);
            
            Tabla.addRow(vRow);
        } 
        acomodarceldas();
    } catch(Exception e) {
        e.printStackTrace(); // Modificado para imprimir la traza completa del error
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
          //  ps.setString(3, jTextEntrada.getText());
          // ps.setString(4, jTextSalida.getText());
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
      
  


    
 
       
    private String[] horarioSelect = new String[0];
    String entradaTexto, salidaTexto;
    public void arrayHorario() {


        try {
          
                // Validar horas
                if (validarHora(entradaTexto) && validarHora(salidaTexto)) {
                    String entrada1 = entradaTexto;
                    String salida1 = salidaTexto;

                    List<String> diasHorario = new ArrayList<>();

                    if (lunes) diasHorario.add("Lunes: " + entrada1 + " - " + salida1);
                    if (martes) diasHorario.add("Martes: " + entrada1 + " - " + salida1);
                    if (miercoles) diasHorario.add("Miércoles: " + entrada1 + " - " + salida1);
                    if (jueves) diasHorario.add("Jueves: " + entrada1 + " - " + salida1);
                    if (viernes) diasHorario.add("Viernes: " + entrada1 + " - " + salida1);
                    if (sabado) diasHorario.add("Sábado: " + entrada1 + " - " + salida1);
                    if (domingo) diasHorario.add("Domingo: " + entrada1 + " - " + salida1);

                    // Convierte la lista a un array
                    horarioSelect = diasHorario.toArray(new String[0]);
                    System.out.println(Arrays.toString(horarioSelect));

                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese horas en el formato HH:mm.");
                  //  arrayHorario(); // Repetir el diálogo si hay un error
                }
          
           
          

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            System.out.println(e);
        } 
    }
   
    
    
    
    
    
    
    
    
    
    
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");
    private boolean validarHora(String hora) {
        return TIME_PATTERN.matcher(hora).matches();
    }

   
    
    

    
    
    
    
     public void limpiarCampos(){

 
     TXTfindbyName.setText("");
     jTextObservacion.setText("");
     jCheckLunes.setSelected(false);
     jCheckMartes.setSelected(false);
     jCheckMiercoles.setSelected(false);
     jCheckJueves.setSelected(false);
     jCheckViernes.setSelected(false);
     jCheckSabado.setSelected(false);
     jCheckDomingo.setSelected(false);

     lunes=false; 
     martes=false;
     miercoles=false; 
     jueves=false; 
     viernes=false; 
     sabado=false;
     domingo=false;
     //actualizarDiasSeleccionados();
 
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
  
  public void eliminarDiasNoSeleccionados() {
    // Crear una lista a partir del array horarioSelect
    List<String> horarioList = new ArrayList<>(Arrays.asList(horarioSelect));
    
    // Eliminar días que no están en true
    if (!lunes) horarioList.removeIf(entry -> entry.startsWith("Lunes: "));
    if (!martes) horarioList.removeIf(entry -> entry.startsWith("Martes: "));
    if (!miercoles) horarioList.removeIf(entry -> entry.startsWith("Miércoles: "));
    if (!jueves) horarioList.removeIf(entry -> entry.startsWith("Jueves: "));
    if (!viernes) horarioList.removeIf(entry -> entry.startsWith("Viernes: "));
    if (!sabado) horarioList.removeIf(entry -> entry.startsWith("Sábado: "));
    if (!domingo) horarioList.removeIf(entry -> entry.startsWith("Domingo: "));
    
    // Actualizar el array original
    horarioSelect = horarioList.toArray(new String[0]);
    System.out.println("Horario actualizado (eliminación): " + Arrays.toString(horarioSelect));
}
    
    public void eliminarDiasDesactivados(List<String> listaDias) {
    if (!lunes && listaDias.contains("Lunes")) listaDias.remove("Lunes");
    if (!martes && listaDias.contains("Martes")) listaDias.remove("Martes");
    if (!miercoles && listaDias.contains("Miércoles")) listaDias.remove("Miércoles");
    if (!jueves && listaDias.contains("Jueves")) listaDias.remove("Jueves");
    if (!viernes && listaDias.contains("Viernes")) listaDias.remove("Viernes");
    if (!sabado && listaDias.contains("Sábado")) listaDias.remove("Sábado");
    if (!domingo && listaDias.contains("Domingo")) listaDias.remove("Domingo");
}
    
    
    
  
    
       public void eliminarServicio() {
           
           
           
     int fila = JTableHorario.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        return;
    }       
           
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
     

      String mensaje = "¿Está seguro de que desea eliminar el horario del especialista: " + JComboDoctor.getSelectedItem().toString();
      mensaje += "\nNOTA: No se podran asignar pacientes a este doctor si se elimina el horario";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `horario_doctores` WHERE id_horario = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHorarios);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El horario ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla(); 
                listarHorarios(); 
                auditEliminar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el horario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
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
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAg;
    private javax.swing.JButton BtnLimpiar1;
    private javax.swing.JButton BtnModif;
    private javax.swing.JMenuItem DejarLibre;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JLabel FechaAc3;
    private javax.swing.JComboBox<String> JComboDoctor;
    private javax.swing.JComboBox<String> JComboEspecialidad1;
    private javax.swing.JTable JTableHorario;
    private javax.swing.JTable JTableHorario1;
    private javax.swing.JPopupMenu MenuPopup;
    private javax.swing.JMenuItem QuitarLibre;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPanel;
    private javax.swing.JTextArea jTextObservacion;
    // End of variables declaration//GEN-END:variables
}
