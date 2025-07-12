/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;
import Clases.AddPdfConsulta;
import Clases.AddPdfConsultaDao;
import Registros.*;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCAntecedentesFamiliares;
import Clases.JCAntecedentesFamiliaresDao;
import Clases.JCAsignar;
import Clases.JCMostrarAsignados;
import Clases.JCPacientes;
import Clases.JCProcedimientos;
import Clases.JCambiarState;
import Clases.PdfDAO1;
import Clases.PdfVO;
import Clases.Temporal;
import Clases.Validar;
import Clases.imgTabla;
import Clases.sql;
import Menu.Mprincipal;
import static com.itextpdf.kernel.pdf.PdfName.Footer;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
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
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import java.text.DecimalFormat;
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
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;
import org.apache.poi.ss.usermodel.Footer;

/**
 *
 * @author FCGinebraI
 */
public class JConsultasPsicologia extends javax.swing.JInternalFrame implements Runnable {


    
    Calendar Fecha;
    public JConsultasPsicologia() {
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Fecha = new GregorianCalendar();

        obtenerFecha();
        
        iniciarActualizacionAutomatica();

        Txtbusqueda.setText("V-");
   


        JTablePacientes.getTableHeader().setReorderingAllowed(false); 
        JTablePacientes.requestFocusInWindow();
    }

    
    
    
    
    
    
    
    
    public void activarDate(){
    Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        try {
          
            Thread.sleep(5000);
            

            FechaAsignar1.setEnabled(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
});

// Iniciar el Thread
t.start();
    
    }
    
    
    
    public void obtenerFecha(){
            
               // Obtener la fecha y hora del servidor
        try (Connection con = new EnlaceBd().getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT NOW() AS server_time");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                java.sql.Timestamp currentTime = rs.getTimestamp("server_time");
                Fecha.setTime(currentTime); 
                FechaAsignar1.setCalendar(Fecha); 
                activarDate();
            }

        } catch (Exception e) {
            System.out.println("Error al obtener la fecha del servidor: " + e);
            JOptionPane.showMessageDialog(this, "Error al obtener la fecha del servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    
    
      }
    
    

      private void limpiarYActualizarTabla() {
        // Aquí deberías llamar tus métodos para limpiar y listar pacientes
        limpiarTPacientes(); 
        ListarPacientes();
    }

    public void iniciarActualizacionAutomatica() {
        // Iniciar la actualización automática cada 5 minutos
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this, 0, 5, TimeUnit.MINUTES); // 5 minutos
    }

    @Override
    public void run() {
        // Actualizar la tabla en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(Fecha.getTime());
                String fechaNueva = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());
                if (fechaActual.equals(fechaNueva)) {
                limpiarYActualizarTabla();
            //  conteoActualizacion++;
            //  System.out.println("Actualización número: " + conteoActualizacion);
                }
 
            }
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupEstado = new javax.swing.JPopupMenu();
        Completar = new javax.swing.JMenuItem();
        Pendiente = new javax.swing.JMenuItem();
        PopupHistorias = new javax.swing.JPopupMenu();
        jUtilizarHisto = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Txtbusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        FechaAsignar1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabelPaciente = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTablePacientes = new javax.swing.JTable();
        tabbedAntecedentes = new javax.swing.JTabbedPane();
        JConsulta = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        BtnImprimir = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextMotivoConsulta = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextDiagnostico = new javax.swing.JTextArea();
        BtnGuardar = new javax.swing.JButton();
        BtnVisualizar = new javax.swing.JButton();
        JPanelAnteriores = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVisualizarC = new javax.swing.JTable();

        Completar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/autorizacion.png"))); // NOI18N
        Completar.setText("Completar");
        Completar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompletarActionPerformed(evt);
            }
        });
        PopupEstado.add(Completar);

        Pendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edicion.png"))); // NOI18N
        Pendiente.setText("Pendiente");
        Pendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendienteActionPerformed(evt);
            }
        });
        PopupEstado.add(Pendiente);

        jUtilizarHisto.setText("Utilizar los datos de esta historia clinica");
        jUtilizarHisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUtilizarHistoActionPerformed(evt);
            }
        });
        PopupHistorias.add(jUtilizarHisto);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 1290, 30));

        Txtbusqueda.setText("V-");
        Txtbusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por cedula:"));
        Txtbusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtbusquedaFocusLost(evt);
            }
        });
        Txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtbusquedaKeyReleased(evt);
            }
        });
        jPanel1.add(Txtbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 340, 50));

        jLabel3.setText("Total pacientes asignados:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel1.setText("numero");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        jButton1.setText("Refrescar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 80, 20));

        FechaAsignar1.setBackground(new java.awt.Color(255, 255, 255));
        FechaAsignar1.setToolTipText("");
        FechaAsignar1.setDateFormatString("yyyy-MM-dd");
        FechaAsignar1.setEnabled(false);
        FechaAsignar1.setFocusable(false);
        FechaAsignar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FechaAsignar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                FechaAsignar1FocusLost(evt);
            }
        });
        FechaAsignar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FechaAsignar1PropertyChange(evt);
            }
        });
        jPanel1.add(FechaAsignar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 140, 30));

        jLabel5.setText("Paciente:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 30));

        jLabelPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPaciente.setForeground(new java.awt.Color(0, 51, 255));
        jPanel1.add(jLabelPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 30));

        JTablePacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Nombre", "C.I", "Estado"
            }
        ));
        JTablePacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablePacientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JTablePacientesMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(JTablePacientes);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 340, 480));

        tabbedAntecedentes.setBackground(new java.awt.Color(255, 255, 255));
        tabbedAntecedentes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabbedAntecedentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedAntecedentesMouseClicked(evt);
            }
        });

        JConsulta.setBackground(new java.awt.Color(255, 255, 255));
        JConsulta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setText("Cancelar");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        JConsulta.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 110, 40));

        BtnImprimir.setText("Imprimir");
        BtnImprimir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnImprimir.setContentAreaFilled(false);
        BtnImprimir.setEnabled(false);
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });
        JConsulta.add(BtnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, 160, 40));

        jTextMotivoConsulta.setColumns(20);
        jTextMotivoConsulta.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextMotivoConsulta.setLineWrap(true);
        jTextMotivoConsulta.setRows(5);
        jTextMotivoConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Informe / Interpretación"));
        jTextMotivoConsulta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextMotivoConsultaFocusLost(evt);
            }
        });
        jTextMotivoConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextMotivoConsultaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextMotivoConsultaKeyTyped(evt);
            }
        });
        jScrollPane12.setViewportView(jTextMotivoConsulta);

        JConsulta.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 250));

        jTextDiagnostico.setColumns(20);
        jTextDiagnostico.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextDiagnostico.setLineWrap(true);
        jTextDiagnostico.setRows(5);
        jTextDiagnostico.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Diagnóstico & Recomendaciones"));
        jTextDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDiagnosticoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDiagnosticoKeyTyped(evt);
            }
        });
        jScrollPane14.setViewportView(jTextDiagnostico);

        JConsulta.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 880, 260));

        BtnGuardar.setText("Guardar");
        BtnGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnGuardar.setContentAreaFilled(false);
        BtnGuardar.setEnabled(false);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        JConsulta.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 550, 160, 40));

        BtnVisualizar.setText("Visualizar");
        BtnVisualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnVisualizar.setContentAreaFilled(false);
        BtnVisualizar.setEnabled(false);
        BtnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVisualizarActionPerformed(evt);
            }
        });
        JConsulta.add(BtnVisualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 140, 40));

        tabbedAntecedentes.addTab("Historia Clinica | ", JConsulta);

        JPanelAnteriores.setBackground(new java.awt.Color(255, 255, 255));
        JPanelAnteriores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableVisualizarC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Psicologo/a", "Reporte", "Fecha", "Estado"
            }
        ));
        jTableVisualizarC.setComponentPopupMenu(PopupHistorias);
        jTableVisualizarC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVisualizarCMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableVisualizarC);

        JPanelAnteriores.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 880, 550));

        tabbedAntecedentes.addTab("Evolución & citas anteriores |", JPanelAnteriores);

        jPanel1.add(tabbedAntecedentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 910, 630));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarTPacientes(); 
        ListarPacientes();
  

        JTablePacientes.requestFocus();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FechaAsignar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FechaAsignar1FocusLost
     
  
    }//GEN-LAST:event_FechaAsignar1FocusLost

  
    private void FechaAsignar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FechaAsignar1PropertyChange
 /*
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(Fecha.getTime());
String fechaNueva = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());
if (!fechaActual.equals(fechaNueva) || filaPaciente != -1) {
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
}*/

limpiarTPacientes();
ListarPacientes();


 JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_FechaAsignar1PropertyChange
  
    
    
    int idAsignar;
    String cedula, nombrepaciente, estado;
    private void tabbedAntecedentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedAntecedentesMouseClicked
    /*
        if (JOptionPane.showConfirmDialog(rootPane, "¿Deseas utilizar los datos previos registrados de este paciente?",
            "Historias", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {}
    
    else{cleanHistorias();}
     
        */
    }//GEN-LAST:event_tabbedAntecedentesMouseClicked

    private void TxtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtbusquedaKeyReleased
   Search(); acomodarceldas();
    }//GEN-LAST:event_TxtbusquedaKeyReleased

    private void CompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompletarActionPerformed
    

        int fila = JTablePacientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (estado.equals("Completado")){ JOptionPane.showMessageDialog(null, "ESTA CITA YA SE ENCUENTRA: Completado", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.CompletarPaciente(103,      idAsignar ) && setState.CompletarHistoria(103,      idAsignar )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CITA SE HA ACTUALIZADO A : Completado", "Estado", 1);
                 
                limpiarTPacientes();
                ListarPacientes();
                //AuditoriaCancelarCita();
                limpiarTVisualizar();
                visualizar_PdfVOHistorias(jTableVisualizarC);
             
            }
        }
  
    }//GEN-LAST:event_CompletarActionPerformed

    private void PendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendienteActionPerformed
              
        int fila = JTablePacientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (estado.equals("Espera")){ JOptionPane.showMessageDialog(null, "ESTA CITA YA SE ENCUENTRA EN: Espera", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.CompletarPaciente(102,     idAsignar ) && setState.CompletarHistoria(102,     idAsignar )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CITA SE HA ACTUALIZADO A : Espera", "Estado", 1);
                 
                limpiarTPacientes();
                ListarPacientes();
                //AuditoriaCancelarCita();
            }
        }
    }//GEN-LAST:event_PendienteActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    limpiarInformeNormal();
    }//GEN-LAST:event_jButton7ActionPerformed

    public void limpiarInformeNormal(){
    
    
      jTextDiagnostico.setText("");

      jTextMotivoConsulta.setText("");
      JTablePacientes.requestFocus(); 
    
    }
    
    
    
    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed

      if(jTextMotivoConsulta.getText().equals("")){
      JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }  
      else{
          
          
          
       try{
           
           
       pdf();
       guardarReporte();
       ActualizarHistoriaBd();
       setState.CompletarHistoria(103,      idAsignar );
       imprimirReporte();
       limpiarTPacientes(); 
       ListarPacientes();
      

       
       }catch(Exception e){System.out.println(e);
                           JOptionPane.showMessageDialog(this,"Error en metodo imprimir, intente de nuevo","ERROR",JOptionPane.ERROR_MESSAGE);
       }   
          
          
  
      }
    
    }//GEN-LAST:event_BtnImprimirActionPerformed

    
    int idtablaproceso;
    String nombreAntc1;
int  idtablaConsulta;
int  idtablaHistorias;
    private void jTableVisualizarCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisualizarCMouseClicked


        jTableVisualizarC.requestFocusInWindow();
      //  int fila = JTablePacientes.getSelectedRow();
        int column = jTableVisualizarC.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTableVisualizarC.getRowHeight();
        
    
        
               if (jLabelPaciente.getText().equals("")) {
                     JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN PACIENTE","Selección",1);
          }
                
                
                else{
        /*            
         int   filaHistoria = JTablePacientes.getSelectedRow();
         idtablaHistorias=(int) (JTablePacientes.getValueAt(filaHistoria, 0));   
   */
        if (row < jTableVisualizarC.getRowCount() && row >= 0 && column < jTableVisualizarC.getColumnCount() && column >= 0) {
            idtablaHistorias = (int) jTableVisualizarC.getValueAt(row, 0);
            Object value = jTableVisualizarC.getValueAt(row, column);
        
        
        if (value instanceof JButton) {
          ((JButton) value).doClick();
          JButton boton = (JButton) value;
         
          

                    try {
                         PdfDAO1 click = new PdfDAO1();
                         click.simularClic(boton);
                         AddPdfConsultaDao pd = new AddPdfConsultaDao();
                         pd.ejecutar_archivoPDFHistorias(idtablaHistorias);
                        JOptionPane.showMessageDialog(null,"Abriendo reporte...","Reporte",1);
                        Desktop.getDesktop().open(new File("ConsultaHistoria.pdf"));
                    } catch (Exception ex) {JOptionPane.showMessageDialog(null,"LA HISTORIA CLINICA AUN NO CUENTA CON UN REPORTE ASOCIADO; GENERE EL REPORTE E INTENTE DE NUEVO", "HISTORIAS", JOptionPane.ERROR_MESSAGE);}
                
        
        
        
        }}}
                
                
    }//GEN-LAST:event_jTableVisualizarCMouseClicked
    int filaPaciente;
       private String estadoPrevio="", nombrePrevio;
    private Integer idPacienteExento = null;
    private int idPacienteActual = -1;
    private boolean esPrimeraSeleccion = true;
    private void JTablePacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacientesMouseClicked

     
    filaPaciente = JTablePacientes.getSelectedRow();

    if (filaPaciente == -1) {
        // Manejar el caso donde no hay ninguna fila seleccionada
        return;
    }


     if (esPrimeraSeleccion) {  
     informacionpdf();
     ActivarFirma();
     esPrimeraSeleccion = false; 
 
    }

    
    
    
    idAsignar = (int) JTablePacientes.getValueAt(filaPaciente, 0);
    nombrepaciente = JTablePacientes.getValueAt(filaPaciente, 1).toString();
    cedula = JTablePacientes.getValueAt(filaPaciente, 2).toString();
    estado = JTablePacientes.getValueAt(filaPaciente, 3).toString();


    if (idPacienteExento == null) {
        idPacienteExento = idAsignar; 
        nombrePrevio =nombrepaciente;
        jLabelPaciente.setText(nombrepaciente);
    }

    // Verificar si es necesario dar inasistencia
    if (idAsignar != idPacienteExento && estado.equals("Espera") && "Espera".equals(estadoPrevio)) {
        int response = JOptionPane.showConfirmDialog(this, 
            "AÚN NO HA GUARDADO LA HISTORIA DE: " + nombrePrevio + " ¿DESEA PASAR A LA SIGUIENTE?", 
//  "DEBE COMPLETAR ESTA HISTORIA O DAR INASISTENCIA AL PACIENTE. ¿DESEA DAR INASISTENCIA a " + nombrePrevio + "?", 
            "Confirmación", 
            JOptionPane.YES_NO_OPTION);
        
   
        if (response == JOptionPane.YES_OPTION) {
           // setState.inasistenciaPac(108, idPacienteExento); 
           // limpiarYActualizarTabla();
            estadoPrevio = estado; 
           // return; 
        } else {
            
            return;
        }
    }

  
    jLabelPaciente.setText(nombrepaciente); 

    // Limpiar la historia si se selecciona un paciente diferente
    if (idAsignar != idPacienteActual) {
       limpiarInformeNormal();
        idPacienteActual = idAsignar;  
    }

    // Guardar el estado actual como previo para la próxima selección
    estadoPrevio = estado;

    // Al final, actualizamos idPacienteExento
    idPacienteExento = idAsignar; // Aquí guardamos el ID del paciente actualmente seleccionado
    nombrePrevio = nombrepaciente;
    
    selectedList();
    }//GEN-LAST:event_JTablePacientesMouseClicked

    
    
     
    public void selectedList(){
    

    BtnVisualizar.setEnabled(true);
    BtnGuardar.setEnabled(true);
    BtnImprimir.setEnabled(true);
    
    SearchIdPatient();
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);

    }
    
    
    
    
    
    
    
    String EnfermedadAF="";
    
    
    String TextAntPersonal="";

         String constitucional="", 
                piel="", 
                orl="",
                respiratorio="",
                Angina="",
                Palpitaciones="",
                Síncope="",
                EndemaMsis="",
                DolorAbdominal="",
                Flatulencia="",
                Estreñimiento="",
                DolorMuscular="",
                DolorArticular="",
                Inflamación="",
                Cefalea="",
                Migraña="",
                Debilidad="",
                Disestesia="",
                Parestesia="",
                Genito="",
                Endocrino="",
                Anticonceptivos="",
                Angustia="",
                Alergias="",
                Otros="";
                        
                
         
         
         
    
    
                                                                   

    
    int partos, cesareas, abortos, embarazos=0;  

    
    
    double altura=0, pesoactual=0, pesoideal=0, pesoextra=0, imc=0;
    
    
 
    
    
 
    
    
    
    
    
    

    
    
    
    
      int idprocedimientos;
    
    private void jUtilizarHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUtilizarHistoActionPerformed
    mostrarHistoria();
 
 
    }//GEN-LAST:event_jUtilizarHistoActionPerformed

    
    
    int antcid1;
    String ShowAntcObs="",ShowName1;
    private void jTextMotivoConsultaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaFocusLost

    }//GEN-LAST:event_jTextMotivoConsultaFocusLost

    
    
    
    
    
       int antcid2;
    String ShowAntcObs2="",ShowName2;
    
    
    
    
    int idEadc;
    String nomEadc, descripEadc;
    private void TxtbusquedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtbusquedaFocusLost
      JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_TxtbusquedaFocusLost

    private void JTablePacientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacientesMouseEntered
       JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_JTablePacientesMouseEntered

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
         if(jTextMotivoConsulta.getText().equals("")){
      JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }  
      else{
             
        try{
        pdf();
        guardarReporte();
        ActualizarHistoriaBd();
        setState.CompletarHistoria(103,      idAsignar );
        limpiarTPacientes(); 
        ListarPacientes();
                
                
       limpiarTVisualizar();
       visualizar_PdfVOHistorias(jTableVisualizarC);
            
       JOptionPane.showMessageDialog(this, "HISTORIA GUARDADA", "CAMPOS", 1);

        
        } catch(Exception e){ System.out.println(e);
          JOptionPane.showMessageDialog(this,"Error en metodo guardar, intente de nuevo","ERROR",JOptionPane.ERROR_MESSAGE);
        }   
    
      }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVisualizarActionPerformed

        if(jTextMotivoConsulta.getText().equals("")){
            JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);

        }
        else{

            try{

                 pdf();
                //    JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
                Desktop.getDesktop().open(file);

            }catch(Exception e){ System.out.println(e);}

        }
    }//GEN-LAST:event_BtnVisualizarActionPerformed

    private void jTextMotivoConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaKeyReleased
             String text = jTextMotivoConsulta.getText();
    int maxLength = 5000;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
        jTextMotivoConsulta.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextMotivoConsultaKeyReleased

    private void jTextMotivoConsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaKeyTyped
          va.longitud(jTextMotivoConsulta.getText(), 5000, evt);
    }//GEN-LAST:event_jTextMotivoConsultaKeyTyped

    private void jTextDiagnosticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDiagnosticoKeyTyped
         va.longitud(jTextDiagnostico.getText(), 5000, evt);
    }//GEN-LAST:event_jTextDiagnosticoKeyTyped

    private void jTextDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDiagnosticoKeyReleased
            String text = jTextDiagnostico.getText();
            int maxLength = 5000;
            if (text.length() > maxLength) {
            JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Trunca el texto si lo deseas
            jTextDiagnostico.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextDiagnosticoKeyReleased

    
    
 

    
    
    
    
    
    
    public void ListarPacientes(){
       List<JCMostrarAsignados> lista = LlenarLista();
        modelo = (DefaultTableModel) JTablePacientes.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdAsginado();
            ob[1] = lista.get(i).getPacAsignado();
            ob[2] = lista.get(i).getCedAsignado();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
       JTablePacientes.setModel(modelo);
       JTablePacientes.setDefaultEditor(Object.class, null);
       acomodarceldas();
       conteoTabla();
    
    
    }
    
    
    
          
  public ArrayList<JCMostrarAsignados> LlenarLista(){
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps =null;
    ResultSet rs = null;
    
    
    
ArrayList<JCMostrarAsignados> al=null;
al=new ArrayList<JCMostrarAsignados>();
String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());   
 String sql = "SELECT Id_historias, Id_usuarioh, Nombre, Apellido, Cedula, Usuario, date_format(Fecha_Historia, '%d/%m/%Y') AS Fecha, especialidad, Estado, Id_Estadoh, Fecha_Historia " +
             "FROM table_historias u " +
             "INNER JOIN table_paciente c ON u.Id_pacienteh = c.Idpaciente " +
             "INNER JOIN table_estado p ON u.Id_Estadoh = p.IdEstado " +
             "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
             "INNER JOIN table_especialidad l ON u.Id_Hespecialidad = l.id_especialidad " +
             "WHERE Id_usuarioh = ? " +
             "AND especialidad = ? " +
             "AND Fecha_Historia = ? " +
             "ORDER BY Id_historias";

        



 

        try {
            
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idusuario);
            ps.setString(2, especialidad);
            ps.setString(3, fecha);

            
            rs = ps.executeQuery();
            while (rs.next()) {
                int estado =rs.getInt("Id_Estadoh"); 
                if( estado!=104){
                JCMostrarAsignados prd = new JCMostrarAsignados();
                prd.setIdAsginado(rs.getInt(1));
                prd.setPacAsignado(rs.getString(3) + " " +rs.getString(4));
                prd.setCedAsignado(rs.getString(5));      
                prd.setEstado(rs.getString(9));

                al.add(prd);
                 }
            }
        } catch (Exception e) {System.err.println("Error al listar pacientes"+ e);
        }
        
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
        return al;
    }
    
    
    
    
    
    
    
    
  
  
  
  
  public ArrayList<JCProcedimientos> LlenarListaPro(){
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps =null;
    ResultSet rs = null;
    
    
    
ArrayList<JCProcedimientos> al=null;
al=new ArrayList<JCProcedimientos>();
String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());   
String sql = "SELECT Id_AProcedimientos,Id_Procedimiento, Procedimiento, Id_Paciente, Id_Usuario, Id_Especialidad, Descripcion_Procedimiento, Fecha_Procedimiento, Id_Estado, Estado\n" +
"\n" +
"FROM asignar_procedimiento u\n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.Id_Paciente= c.Idpaciente\n" +
"\n" +
"INNER JOIN table_estado p\n" +
"ON u.Id_Estado= p.IdEstado \n" +
"\n" +
"INNER JOIN table_procedimientos x\n" +
"ON u.Id_Procedimiento= x.idProcedimiento \n" +
        
 "where Id_Usuario LIKE '%" + idusuario + "%'     AND Id_Paciente LIKE "+  '"' + PacienteSelec + '"'  + "AND Fecha_Procedimiento LIKE"+  '"' + fecha + '"'
        
  +      "ORDER by  Id_AProcedimientos" ;

       
        

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int estado =rs.getInt("Id_Estado"); 
               if( estado==102){
                JCProcedimientos prd = new JCProcedimientos();
                prd.setIdprocedimiento(rs.getInt(1));
                prd.setProcedimiento(rs.getString(3));      
                prd.setObservacion(rs.getString(7));
                prd.setEstado(rs.getString(10));

                al.add(prd);
                  }
            }
        } catch (Exception e) {System.err.println("Error al listar"+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return al;
    }
    
  
  
  
  
  public ArrayList<JCProcedimientos> LlenarListaPro2(){
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps =null;
    ResultSet rs = null;
    
    
    
ArrayList<JCProcedimientos> al=null;
al=new ArrayList<JCProcedimientos>();
String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());   
String sql = "SELECT Id_AProcedimientos,Id_Procedimiento, Procedimiento, Id_Paciente, Id_Usuario, Id_Especialidad, Descripcion_Procedimiento, Fecha_Procedimiento, Id_Estado, Estado\n" +
"\n" +
"FROM asignar_procedimiento u\n" +
"\n" +
"INNER JOIN table_paciente c\n" +
"ON u.Id_Paciente= c.Idpaciente\n" +
"\n" +
"INNER JOIN table_estado p\n" +
"ON u.Id_Estado= p.IdEstado \n" +
"\n" +
"INNER JOIN table_procedimientos x\n" +
"ON u.Id_Procedimiento= x.idProcedimiento \n" +
        
 "where Id_Usuario LIKE '%" + idusuario + "%'     AND Id_Paciente LIKE "+  '"' + PacienteSelec + '"'  + "AND Fecha_Procedimiento LIKE"+  '"' + fecha + '"'
        
  +      "ORDER by  Id_AProcedimientos" ;

       
        

 

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int estado =rs.getInt("Id_Estado"); 
               if( estado==103){
                JCProcedimientos prd = new JCProcedimientos();
                prd.setIdprocedimiento(rs.getInt(1));
                prd.setProcedimiento(rs.getString(3));      
                prd.setObservacion(rs.getString(7));
                prd.setEstado(rs.getString(10));

                al.add(prd);
                  }
            }
        } catch (Exception e) {System.err.println("Error al listar"+ e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
        return al;
    }
  
  
  
  
  
      
      
      
      
      
      
      
      
      
      
      
      
    
  
  
  
  
  
  
  
  
    public void conteoTabla (){
      for(int x=0;x<=JTablePacientes.getRowCount();x++)
           {
           jLabel1.setText(""+x);
           }
             
    
    }
    
      public  void limpiarTPacientes() {
        DefaultTableModel tb = (DefaultTableModel) JTablePacientes.getModel();
        int a = JTablePacientes.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }



          
                 public  void limpiarTVisualizar() {
        DefaultTableModel tb = (DefaultTableModel) jTableVisualizarC.getModel();
        int a = jTableVisualizarC.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

      
      
    
      
      
        
            
      public void Search(){
              
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    String fechaDia = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());    
    String Busqueda = this.Txtbusqueda.getText();
    String sql = "SELECT \n" +
    "    Id_historias, \n" +
    "    Nombre, \n" +
    "    Apellido, \n" +
    "    Cedula, \n" +
    "    DATE_FORMAT(Fecha_Historia, '%d/%m/%Y') AS Fecha, \n" +
    "    usuario, \n" +
    "    Estado, \n" +
    "    Id_Estadoh, \n" +
    "    especialidad \n" +
    "FROM \n" +
    "    table_historias u\n" +
    "INNER JOIN \n" +
    "    table_paciente c ON u.Id_pacienteh = c.Idpaciente\n" +
    "INNER JOIN \n" +
    "    table_estado p ON u.Id_Estadoh = p.IdEstado\n" +
    "INNER JOIN \n" +
    "    table_usuario x ON u.Id_usuarioh = x.IdPersonal\n" +
    "INNER JOIN \n" +
    "    table_especialidad l ON u.Id_Hespecialidad = l.id_especialidad\n" +
    "WHERE \n" +
    "    Cedula LIKE '%" + Busqueda + "%' \n" +
    "    AND especialidad = '" + especialidad + "' \n" +
    "    AND Fecha_Historia = '" + fechaDia + "' \n" +
    "ORDER BY \n" +
    "    Id_historias ASC";

         
     
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTablePacientes.getModel();
        JTablePacientes.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTPacientes();

            while(rs.next()) 
            {
                //LLENADO DE TABLA
               int id = rs.getInt(1);
               String nombre = rs.getString(2) + " "+ rs.getString(3);
               String cedula = rs.getString(4);
               String estado = rs.getString(7);
         //      int estadonum = rs.getInt(8);
             
             //  if(estadonum==102){
               Vector vRow=new Vector();
               vRow.add(id);
               vRow.add(nombre);
               vRow.add(cedula);
               vRow.add(estado);
               Tabla.addRow(vRow);
               
             //  }
          
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }
        
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
        
      
      
      int PacienteSelec;
      String sexo="";
      Date fechanac;
      
      
   public void SearchIdPatient(){
     
          
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;

   try{
    
 //    int fila = JTablePacientes.getSelectedRow();
       
       String query="SELECT Idpaciente, Sexo, FechNacimiento from table_paciente  where  Cedula=?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setString(1, cedula);
      
       rs=ps.executeQuery();
   
       if (rs.next()){
        PacienteSelec= rs.getInt("Idpaciente");
        sexo= rs.getString("Sexo");
        fechanac= rs.getDate("FechNacimiento");
     //   System.out.println(PacienteSelec);
       }
       

          }
catch(Exception e){System.out.println("Error searchidpaciente"+e);} 
   finally {
            closeResources(rs, ps, con);
        }

      }
      
      
   
   
   
   
  
      

     int idexamen;
     String PdfNamesHistorias;
     File file ;
      //pdfHistorias
     public void pdf() {
      try {
       
          
         
        
            String fechaN = new SimpleDateFormat("yyyy/MM/dd").format(fechanac);
            String [] dateParts1= fechaN.split("/");
            String an1 = dateParts1[0];
            String mes1 = dateParts1[1];
            String dia1 = dateParts1[2];
            
            
            String an_actual = Validar.fecha_actual();
            String mes_actual = Validar.fecha_mes();
            String dia_actual = Validar.fecha_dia();
            
           /* System.out.println("Hoy es :"+dia_actual+" "+ mes_actual+" "+an_actual);*/
            
         
            
        
            
            int diferenciadia=Integer.parseInt(dia_actual)- Integer.parseInt(dia1);
            int diferenciames=Integer.parseInt(mes_actual)- Integer.parseInt(mes1);
            int diferencian=Integer.parseInt(an_actual)- Integer.parseInt(an1);;

            
   if (diferenciames < 0 || (diferenciames == 0 && diferenciadia < 0)) {
        diferencian = diferencian - 1;
    }
 
   
           PdfNamesHistorias="Historia_Paciente";
          
          
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF, 10); 
            Paragraph saltolinea = new Paragraph();
          

            FileOutputStream archivo;
            file = new File("C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.setMargins(36, 36, 36, 130);
            
            
            
            
            
            
             // Agregas el footer utilizando la clase PdfPageEventHelper
   
            writer.setPageEvent(eventHelper);
       
            
            
            

            
            doc.open();
       
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph fecha = new Paragraph();
    //      Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(Menu.FechaAdmin.getDate());
            String Hora = Menu.Time.getText()+" "+Menu.jLabel102.getText();


            
            
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

          
            CB.endText();
            
      
            
            
             //BODY ---------------------------------------------------------------------------------

            PdfPTable Tabla = new PdfPTable(3); 
            Tabla.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 1f };
            Tabla.setWidths(medidaCeldas);
            Tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("N° DE ORDEN: " + idAsignar);
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna1);
     
                
            Paragraph tcolumna2 = new Paragraph("INFORME MÉDICO");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(12);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna2);

            Paragraph tcolumna3 = new Paragraph("N° DE PACIENTE: " + PacienteSelec);
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
            
            Paragraph t0columna1 = new Paragraph("PACIENTE: "+ nombrepaciente);
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(8);        
            t0columna1.setFont(Letra);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+ cedula);
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(8);        
            t0columna2.setFont(Letra);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);

            Paragraph t0columna3 = new Paragraph("EDAD: "+ Integer.toString(diferencian));
            t0columna3.getFont().setStyle(Font.BOLD);
            t0columna3.getFont().setSize(8);        
            t0columna3.setFont(Letra);
            t0columna3.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna3);
        
            Paragraph t0columna4 = new Paragraph("SEXO: "+ sexo);
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(8);        
            t0columna4.setFont(Letra);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
        

            
            
            
            
                 
         
                

            
            //PARTE 1
            PdfPTable Tabla2 = new PdfPTable(1); 
            Tabla2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            float[] medidaCeldas3 = {5f};
            Tabla2.setWidthPercentage(100);  
            Tabla2.setWidths(medidaCeldas3);
            Tabla2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t2columna1 = new Paragraph("I N T E R P R E T A C I Ó N");
            t2columna1.getFont().setStyle(Font.BOLD);
            t2columna1.getFont().setSize(10);        
            t2columna1.setFont(Letra);
           
            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cell.setPadding(10);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPhrase (new Paragraph(jTextMotivoConsulta.getText(),FontFactory.getFont("Arial",10,Font.NORMAL)));
            
            t2columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            Tabla2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            Tabla2.addCell(t2columna1);
            Tabla2.addCell(cell);   
         

            
            
            //PARTE 2

            Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);
            
            PdfPTable TableProcedimiento = new PdfPTable(2);
            TableProcedimiento.setWidthPercentage(95);
            TableProcedimiento.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{30f, 60f};
            TableProcedimiento.setWidths(Columnapro);
            TableProcedimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pro1 = new PdfPCell(new Phrase("Procedimiento", font));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripción", font));
            pro1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pro2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pro1.setBorder(0);
            pro2.setBorder(0);

            pro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            pro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
  
            TableProcedimiento.addCell(pro1);
            TableProcedimiento.addCell(pro2);

       
         
            
            
            
            
            //PARTE 3
                     
            PdfPTable Tabla3 = new PdfPTable(1); 
            Tabla3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            Tabla3.setWidthPercentage(100);  
            Tabla3.setWidths(medidaCeldas3);
            Tabla3.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            Paragraph t3columna1 = new Paragraph("D I A G N Ó S T I C O   &   R E C O M E N D A C I O N E S");
            t3columna1.getFont().setStyle(Font.BOLD);
            t3columna1.getFont().setSize(10);        
            t3columna1.setFont(Letra);
           
            PdfPCell cell2 = new PdfPCell();
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cell2.setPadding(10);
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setPhrase (new Paragraph(jTextDiagnostico.getText(),FontFactory.getFont("Arial",10,Font.NORMAL)));
            
            t3columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            Tabla3.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            Tabla3.addCell(t3columna1);
            Tabla3.addCell(cell2);   
         
           
           

            
            
            
            PdfPTable Tablafirma = new PdfPTable(3); 
            Tablafirma.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Tablafirma.setWidthPercentage(100);  
            float[] medidaCeldasP = {3f, 1f, 3f};
            Tablafirma.setWidths(medidaCeldasP);
            Tablafirma.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tablafirma.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tPcolumna1 = new Paragraph("\n\n\n\n "+piepagina);
            tPcolumna1.getFont().setStyle(Font.BOLD);
            tPcolumna1.getFont().setSize(8);        
            tPcolumna1.setFont(Letra);
            tPcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tablafirma.addCell(tPcolumna1);

           File ImagenFirma = new File("C:\\Fundaginebra\\dist\\imagen.bin");
           if (ImagenFirma.exists()) {
            com.itextpdf.text.Image FirmaLcdo = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\imagen.bin");
            FirmaLcdo.setAlignment(Chunk.ALIGN_CENTER);
            Tablafirma.addCell(FirmaLcdo);
           } else {
        
            Tablafirma.addCell("");
           }
            
         

            Paragraph tPcolumna3 = new Paragraph("\n\n\n\n" + writer.getPageNumber()+ " Pág");
            tPcolumna3.getFont().setStyle(Font.BOLD);
            tPcolumna3.getFont().setSize(8);        
            tPcolumna3.setFont(Letra);
            tPcolumna3.setAlignment(Element.ALIGN_CENTER);
            Tablafirma.addCell(tPcolumna3);
           


         
           PdfPTable TablafirmaLcdo = new PdfPTable(1); 
           TablafirmaLcdo.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
           TablafirmaLcdo.setWidthPercentage(100);  
           float[] medidaCeldasFirma = {20f};
           TablafirmaLcdo.setWidths(medidaCeldasFirma);
           TablafirmaLcdo.setHorizontalAlignment(Element.ALIGN_CENTER);
           TablafirmaLcdo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
      
           Paragraph tPcolumnaFirma1 = new Paragraph("________________ \n "+"Dr/a " + nombredoctor + "\n C.M.A: "+CMA + "| MPPS: "+ MPPS  );
           tPcolumnaFirma1.getFont().setStyle(Font.BOLD);
           tPcolumnaFirma1.getFont().setSize(8);        
           tPcolumnaFirma1.setFont(Letra);
           tPcolumnaFirma1.setAlignment(Element.ALIGN_CENTER);
           TablafirmaLcdo.addCell(tPcolumnaFirma1);

           
           

           
            saltolinea.add("\n");
           // doc.add(saltolinea); 
            doc.add(Tabla); 
            doc.add(Tabla0);
            
            doc.add(saltolinea);
          
            
            
            
           // doc.add(saltolinea);
            doc.add(Tabla2);
            doc.add(saltolinea);
            
        
            
            doc.add(Tabla3);
            doc.add(saltolinea);
            
       
            
           // doc.add(Tablafirma);
           // doc.add(TablafirmaLcdo);
         
           //FOOTER
           //   addFooter(writer);
            
            
            doc.close();
            archivo.close();
           
     
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "NO SE CONSIGUE LA CARPETA FUNDAGINEBRA EN DISCO C", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
      
      

     
     
     
     
     
     
      

        PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
        public void onEndPage(PdfWriter writer, Document document) {
        
    
        try{
          com.itextpdf.text.Image FirmaDoctor = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\imagen.bin");
        
   
      
        
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
     
       
        
       Phrase phrase = new Phrase();
       FirmaDoctor.scaleToFit(100, 100);
       FirmaDoctor.setAlignment(Chunk.ALIGN_CENTER);
       phrase.add(new Chunk(FirmaDoctor, 0, 0));
       phrase.add(new Chunk("\n ______________ \n" + "Dr/a "+nombredoctor+ "\n C.M.A: "+CMA + "| MPPS: "+ MPPS , new Font(FontFactory.getFont("Arial",9,Font.NORMAL))));
         
       PdfPCell imgfirma = new PdfPCell(phrase);
       imgfirma.setBorder(Rectangle.NO_BORDER);
       // Alineas la imagen y el texto al centro horizontal y vertical de la celda
       imgfirma.setHorizontalAlignment(Element.ALIGN_CENTER);
       imgfirma.setVerticalAlignment(Element.ALIGN_MIDDLE);
       imgfirma.setFixedHeight(40);
       
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
       
     }catch(Exception e ){System.out.println("error en report pie"+ e);}
    
        
    }
        
    };

      
      
 
      


      
      
      
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

        } catch (Exception e) {System.out.println("ERROR AL LLAMAR INFORMACION PDF "+ e);
        }   finally {
            closeResources(rs, ps, con);
        }
    }
      
      
      
      
      
      
      
      
      
      
   
      
 
       public void guardarReporte()
    {

      
        ruta_archivo="C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf";
        int codigo =  idAsignar;
        File ruta = new File(ruta_archivo);
       
        
       
        
        if ( ruta_archivo.trim().length() != 0) {
           
            
            
            guardar_pdf(codigo, ruta);
  estadoPrevio="";

         ruta_archivo = "";

  
        } else {
            JOptionPane.showMessageDialog(null, "Rellenar todo los campos");
        }
    
    }
       
       
       
       
       
       
      
       public void guardar_pdf(int codigo, File ruta) {
           
        AddPdfConsulta pa = new AddPdfConsulta();
        AddPdfConsultaDao po = new  AddPdfConsultaDao();
        pa.setIdConsulta(codigo);
        
        try {
            byte[] pdf = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(pdf);
            pa.setArchivo(pdf);
        } catch (IOException ex) {
            pa.setArchivo(null);
            System.out.println("Error al agregar archivo pdf "+ex);
        }
        po.Agregar_PdfVO(pa);
    }


    
   
 
       
         
      
      
      
   
     
      
      
      
      
      
      public void visualizar_PdfVO(JTable tabla) {
        
        
        try {
        
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        
        dt.addColumn("Código");
        dt.addColumn("Psicologo/a");
        dt.addColumn("Fecha");
        dt.addColumn("Reporte");
        dt.addColumn("Estado");
        //dt.addColumn("Usuario encargado");
        //dt.addColumn("Fecha");
        //dt.addColumn("archivopdf");
        //dt.addColumn("Estado");
        //dt.addColumn("Correo");
        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

       
        AddPdfConsulta vo = new AddPdfConsulta();
        ArrayList<AddPdfConsulta> list = Listar_PdfVO();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getIdConsulta();
                fila[1] = vo.getNombredoctor();
                fila[2] = vo.getFechaAsignar();
           
                
                   
                if (vo.getArchivo() != null) {
                    fila[3] = new JButton(icono);
                } else {
                    fila[3] = new JButton("Vacio");
                }

                
                  fila[4] = vo.getEstado();
            
                
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
            acomodarConsultasN();
            
            
   
        
        }
        
                 } catch (Exception e) { System.out.println(e + "visualizarpdf") ;
        }
           
    }
    
      
      
      
      
      
      
        public ArrayList<AddPdfConsulta> Listar_PdfVO() {
           
 Connection con=null;
 EnlaceBd cn = new EnlaceBd();
 PreparedStatement ps=null;
 ResultSet rs=null;
        
//String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
// String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    
    
    
   ArrayList<AddPdfConsulta> list = new ArrayList<AddPdfConsulta>();
   String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());  
   
   String sql = "SELECT IdAsignar, IdPacientes, Usuario, Id_Especialidades, FechaAsignar, ReporteConsulta, EstadoA, Estado\n" +
"FROM table_asignar u\n" +
"\n" +
"INNER JOIN table_estado l\n" +
"ON u.EstadoA=l.IdEstado \n"+
                
"INNER JOIN table_usuario x\n" +
"ON u.IdUsuario=x.IdPersonal \n"+            
//"WHERE IdPacientes = '" + PacienteSelec + "' ORDER BY IdAsignar";                
"where Id_Especialidades LIKE '%" + idespecialidad + "%'     AND IdPacientes = "+  '"' + PacienteSelec + '"'  +   "ORDER by IdAsignar";    

                

                
                
//"ON u.Id_personal=x.IdPersonal WHERE FechaReporte BETWEEN " + '"' + fecha + '"' + "AND"  + '"' + fecha2 + '"' +"ORDER BY  Codigopdf ASC";
            
     
  
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                AddPdfConsulta vo = new AddPdfConsulta();
                vo.setIdConsulta(rs.getInt(1));
                vo.setNombredoctor(rs.getString(3));
                vo.setFechaAsignar(rs.getString(5));
                vo.setArchivo(rs.getBytes(6));
                vo.setEstado(rs.getString(8));
   
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

      
      
      
        
      
     // LISTAR HISTORIAS
        public void visualizar_PdfVOHistorias(JTable tabla) {
        
        
        try {
        
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        
        dt.addColumn("Código");
        dt.addColumn("Psicologo/a");
        dt.addColumn("Fecha");
        dt.addColumn("Reporte");
        dt.addColumn("Estado");

        
        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

       
        AddPdfConsulta vo = new AddPdfConsulta();
        ArrayList<AddPdfConsulta> list = Listar_PdfVOHistorias();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getIdHistoria();
                fila[1] = vo.getNombredoctor();
                fila[2] = vo.getFecha_Historia();
                fila[3] = new JButton(icono);
                fila[4] = vo.getEstado();
        
               
            
                
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
            acomodarConsultasN();
           
        
        }

        
                 } catch (Exception e) { System.out.println(e + "visualizarpdf") ;
        }
           
    }
    
      
      
      
      
      
      
public ArrayList<AddPdfConsulta> Listar_PdfVOHistorias() {
    EnlaceBd cn = new EnlaceBd();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    ArrayList<AddPdfConsulta> list = new ArrayList<>();
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());

  String sql = "SELECT Id_historias, Usuario, Id_pacienteh, Fecha_Historia, Archivo_Historia, Id_Estadoh, Estado, Id_Hespecialidad, especialidad, Cedula \n" +
            "FROM table_historias u \n" +
            
            "INNER JOIN table_estado l \n" +
            "ON u.Id_Estadoh=l.IdEstado \n" +
            
            "INNER JOIN table_usuario x \n" +
            "ON u.Id_usuarioh=x.IdPersonal \n" +
            
            "INNER JOIN table_paciente p \n" +
            "ON u.Id_pacienteh=p.Idpaciente \n" +
            
            "INNER JOIN table_especialidad d \n" +
            "ON u.Id_Hespecialidad =d.id_especialidad \n" +
            
            "WHERE Cedula LIKE ? AND especialidad LIKE ?\n" +
            "ORDER BY Id_historias";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        // Utilizar setString para evitar problemas de SQL injection
        ps.setString(1, "%" + cedula + "%");
        ps.setString(2, "%" + especialidad + "%");

        rs = ps.executeQuery();

        while (rs.next()) {
            
            int estado =rs.getInt("Id_Estadoh");
            if( estado==103){ 
            
            AddPdfConsulta vo = new AddPdfConsulta();
            vo.setIdHistoria(rs.getInt(1));
            vo.setNombredoctor(rs.getString(2));
            vo.setFecha_Historia(rs.getString(4));
            vo.setArchivoHistoria(rs.getBytes(5));
            vo.setEstado(rs.getString("Estado"));

            list.add(vo);
             }
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

      
      
      
      
      
      
          
   public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) { System.out.println(e);
        }
        return null;
    }
    
      
      
      
      
   
      
        
        
        
        
        
     
     
     
      String CMA, MPPS;
      public void ActivarFirma()  {
        

        
        Connection con=null;     
        int idfirma=TP.getTexto();
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;
          
        try {
  
            String sql ="SELECT  Firma, CMA, MPPS FROM table_personal WHERE IdPersonal = ?";
            con =cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idfirma);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
                CMA=rs.getString(2);
                MPPS=rs.getString(3);
            }
            
           // System.out.println(b);
            
            if(b!=null){
            InputStream bos = new ByteArrayInputStream(b);
            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);
            OutputStream out = new FileOutputStream("imagen.bin");
            out.write(datosPDF);
            out.close();
            bos.close();
            
            //System.out.println("Estoy entrando diferente a null"+b);
            }
            
            
            
            else if (b==null){
             DesactivarFirma();
            // System.out.println("Estoy entrando igual a null"+b);
            }


            //abrir archivo
     
   
            //JOptionPane.showMessageDialog(null, "Estimado: "+  nameof + " su firma ha sido activada", "ACTIVAR FIRMA", 1);
     
        } catch (Exception e) {
            System.out.println("Error en activar firma "+ e);
           
           
        }  finally {
            closeResources(rs, ps, con);
        }
        
         
        
    }
     
     
     
      
      
      
      
       public void DesactivarFirma() {
    
          
        String nameof=TP.getUser();
          
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;
          
        try {
            String sql="SELECT imgwhite FROM tableinfo WHERE Idinfo = ?";
            con =cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("imagen.bin");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
     
            //JOptionPane.showMessageDialog(null, "Estimado: "+  nameof + " su firma ha sido desactivada", "DESACTIVAR FIRMA", 1);
     
        } catch (Exception e) {
            System.out.println("Error en desactivar firma"+e);
           
        }  finally {
            closeResources(rs, ps, con);
        }
        

        
        
        
    }
      
      
   
       
       
       
public void ActualizarHistoriaBd() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = cn.getConnection();

        // Actualización de Examen Físico
        if (existeRegistro(con, "examen_psicologia", "id_psicologia", idAsignar)) {
             updatePsicologia();
        } else {
            insertarPsicologia();
            updatePsicologia();
        }

    
        
        
    } catch (Exception e) {
        System.out.println("Error al actualizar la historia: " + e);
    } finally {
        cerrarConexiones(ps, con);
    }
}


       
       
      private void cerrarConexiones(PreparedStatement ps, Connection con) {
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

       
       
       
  
    public void insertarPsicologia(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
              
          try {
 
            String sql2 = "INSERT INTO `examen_psicologia`(`id_historia`) VALUES (?)";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);
            ps.executeUpdate();
        
         
            
        } catch (Exception e) {
            System.out.println(e);
         
           }
       
      finally {
        cerrarConexiones(ps, con);
    }

  
  }     
       
       
       
       
       
       
public void updatePsicologia() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;




    try {
        String sql = "UPDATE examen_psicologia SET Interpretacion =?, Diagnostico =? WHERE  id_historia =?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);



        ps.setString(1, jTextMotivoConsulta.getText());
        ps.setString(2, jTextDiagnostico.getText());
        ps.setInt(3, idAsignar);

        ps.executeUpdate();


    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR REVISION: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}

       
       
       
       
       
       
       
       
  
  
  
  
  
  
  
  
  
  
  

  public void insertarResivion(){
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;     
          try {
            String sql2 = "INSERT INTO `table_revision`(`Id_historiaR`)  values(?) ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);

 
            ps.executeUpdate();
        
  
            
        } catch (Exception e) {
             System.out.println("ERROR EN REGISTRAR REVISION" + e);
         
           }  finally {
            closeResources(rs, ps, con);
        }

  }
  


  public void insertarConclusion(){
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;     
          try {
            String sql2 = "INSERT INTO table_conclusion (Id_conclusionh)  values(?) ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);
            ps.executeUpdate();
      
            
        } catch (Exception e) {
             System.out.println("ERROR EN REGISTRAR CONCLUSION" + e);
         
           }  finally {
            closeResources(rs, ps, con);
        }

  }

 



   public void mostrarHistoria(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
          
          
      
       try{
    

       String query="SELECT `Interpretacion`, `Diagnostico` FROM `examen_psicologia` WHERE id_historia= ?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       jTextMotivoConsulta.setText(rs.getString("Interpretacion"));
       jTextDiagnostico.setText(rs.getString("Diagnostico"));

   
       }
    
      JOptionPane.showMessageDialog(null, "LOS DATOS FUERON CARGADOS A HISTORIA CLINICA", "HISTORIA CLINICA", 1);
      tabbedAntecedentes.setSelectedIndex(0);


       
       
          }
      catch(Exception e){System.out.println("Error en cargar datos a historia "+e);}
       
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
  
  
     
   
   
   
   
   
 public void imprimirReporte() {

  

                try {
                    
                  String pdfFileName = "C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf";
                  //  String pdfFileName = "Análisis.pdf"; // Nombre del archivo PDF
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

 
 
    public void imprimirPDF(File archivoPDF) throws IOException, PrinterException {
        // Cargar el documento PDF
        PDDocument document = PDDocument.load(archivoPDF);

        // Obtener una instancia de PrinterJob
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Obtener una instancia de PDPageable para el documento PDF
        PDPageable pageable = new PDPageable(document);
        printerJob.setPageable(pageable);

        // Obtener la impresora por defecto
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

        // Configurar la impresora en PrinterJob
        printerJob.setPrintService(defaultPrinter);

        // Crear un conjunto de atributos de solicitud de impresión
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        // Mostrar el diálogo de impresión y manejar la impresión
        if (printerJob.printDialog(attributeSet)) {
            try {
                // Imprimir el documento PDF con los atributos de solicitud de impresión
                printerJob.print(attributeSet);
                
                /*
                cleanAll();
                limpiarTPacientes(); 
                ListarPacientes();
                BtnGuardar.setEnabled(false);
                BtnImprimir.setEnabled(false);
                usingData = false;
                */
                
     
            } catch (PrinterException ex) {
                // Manejar cualquier excepción relacionada con la impresión
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al imprimir el documento PDF", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Cerrar el documento PDF
        document.close();
    }
   
   
   
   
   public void cleanAll(){
   
       limpiarTVisualizar();
       visualizar_PdfVOHistorias(jTableVisualizarC);
       limpiarInformeNormal();
       limpiarTPacientes(); 
       ListarPacientes();
   
   }
   
     
     
     public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTablePacientes.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTablePacientes.setRowHeight(20);
        
  
        JTablePacientes.getColumnModel().getColumn(0).setMaxWidth(0);
        JTablePacientes.getColumnModel().getColumn(0).setMinWidth(0);
        JTablePacientes.getColumnModel().getColumn(0).setPreferredWidth(0);
       
       // JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Cod")).setPreferredWidth(60);
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Nombre")).setPreferredWidth(120);
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("C.I")).setPreferredWidth(60);
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(70);


      
        //JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Cod")).setCellRenderer(Alinear);
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Nombre")).setCellRenderer(Alinear);
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("C.I")).setCellRenderer(Alinear);;
        JTablePacientes.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);

    }
     
     
 
      
     
     
      
      
      
      
     
     
     
         
      public void acomodarConsultasN()
    {
     
       DefaultTableModel Tabla = (DefaultTableModel)jTableVisualizarC.getModel();
       DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
       Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
       jTableVisualizarC.getColumnModel().getColumn(0).setMaxWidth(0);
       jTableVisualizarC.getColumnModel().getColumn(0).setMinWidth(0);
       jTableVisualizarC.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Psicologo/a")).setPreferredWidth(100);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setPreferredWidth(80);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(80);
     
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Psicologo/a")).setCellRenderer(Alinear);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setCellRenderer(Alinear);;
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);
    
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
      
      
      
     
   JCambiarState setState = new JCambiarState();
   Mprincipal MP = new Mprincipal();
   DefaultTableModel modelo = new DefaultTableModel();   
   String ruta_archivo = "";
   Mprincipal Menu = new Mprincipal();
   Encriptar encriptar = new Encriptar();
   Temporal TP = new Temporal();
   int idusuario=TP.getTexto();
   String especialidad=TP.getEspecialidad();
   int idespecialidad=TP.getIdEspecialidad();
   String nombredoctor=TP.getNombre();
   Validar va = new Validar();
  JCAntecedentesFamiliaresDao AntcDao = new JCAntecedentesFamiliaresDao ();
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton BtnVisualizar;
    private javax.swing.JMenuItem Completar;
    private com.toedter.calendar.JDateChooser FechaAsignar1;
    private javax.swing.JPanel JConsulta;
    private javax.swing.JPanel JPanelAnteriores;
    public javax.swing.JTable JTablePacientes;
    private javax.swing.JMenuItem Pendiente;
    private javax.swing.JPopupMenu PopupEstado;
    private javax.swing.JPopupMenu PopupHistorias;
    private javax.swing.JTextField Txtbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableVisualizarC;
    private javax.swing.JTextArea jTextDiagnostico;
    private javax.swing.JTextArea jTextMotivoConsulta;
    private javax.swing.JMenuItem jUtilizarHisto;
    private javax.swing.JTabbedPane tabbedAntecedentes;
    // End of variables declaration//GEN-END:variables
}
