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
import Clases.LlenarCombobox;
import Clases.PdfDAO1;
import Clases.PdfVO;
import Clases.Temporal;
import Clases.Validar;
import Clases.imgTabla;
import Clases.sql;
import Menu.Mprincipal;
import Procesos.JLabexamenes.JCompleto;
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
import javax.swing.DefaultComboBoxModel;
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


public class JConsultasEcografia extends javax.swing.JInternalFrame implements Runnable{


    
    Calendar Fecha;
    public JConsultasEcografia() {
       initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Fecha = new GregorianCalendar();
 
 obtenerFecha();

        Txtbusqueda.setText("V-");
        // Aquí puedes mantener el resto de las inicializaciones
        informacionpdf();
        ActivarFirma();
        llenarResultado();
        iniciarActualizacionAutomatica();
        JTablePacientes.getTableHeader().setReorderingAllowed(false);
        JTablePacientes.requestFocusInWindow();
    
    }

    
     public void activarDate(){
    Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        try {
          
           Thread.sleep(5000);
            
            // Habilitar el componente FechaAsignar1 después de 1 minuto
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

  
    
    
    
    
    
/*try {
    Runtime rt = Runtime.getRuntime();
    Process p = rt.exec("C:\\Windows\\System32\\mspaint.exe C:\\Fundaginebra\\dist\\imagen.bin");            
} catch(Exception ex) {
    System.out.println(ex);
}*/
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
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTablePacientes = new javax.swing.JTable();
        tabbedAntecedentes = new javax.swing.JTabbedPane();
        JPanelHistoria = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        JMotivo = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextMotivoConsulta = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableProcedimientos = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        BtnModifProc1 = new javax.swing.JButton();
        BtnModifProc = new javax.swing.JButton();
        BtnProceso = new javax.swing.JButton();
        BtnRetirar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        BtnImprimirHistoria = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextRecomendacion = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextConclusion = new javax.swing.JTextArea();
        BtnGuardarHistoria = new javax.swing.JButton();
        BtnImprimirHistoria2 = new javax.swing.JButton();
        BtnVisualizar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
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

        Pendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/portapapeles.png"))); // NOI18N
        Pendiente.setText("Pendiente");
        Pendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendienteActionPerformed(evt);
            }
        });
        PopupEstado.add(Pendiente);

        jUtilizarHisto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/comprobacion.png"))); // NOI18N
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

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 30));

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

        JPanelHistoria.setBackground(new java.awt.Color(255, 255, 255));
        JPanelHistoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        JMotivo.setBackground(new java.awt.Color(255, 255, 255));
        JMotivo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane20.setViewportView(jTextMotivoConsulta);

        JMotivo.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 150));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Procedimiento", "Estado"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable4);

        JMotivo.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 420, 190));

        jTableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Procedimiento", "Descripción", "Estado"
            }
        ));
        jTableProcedimientos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableProcedimientosFocusLost(evt);
            }
        });
        jTableProcedimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProcedimientosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableProcedimientos);

        JMotivo.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 880, 190));

        jTextArea15.setColumns(20);
        jTextArea15.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextArea15.setLineWrap(true);
        jTextArea15.setRows(5);
        jTextArea15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Observaciones"));
        jScrollPane13.setViewportView(jTextArea15);

        JMotivo.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 370, 190));

        BtnModifProc1.setText("Nuevo");
        BtnModifProc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifProc1.setContentAreaFilled(false);
        BtnModifProc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifProc1ActionPerformed(evt);
            }
        });
        JMotivo.add(BtnModifProc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 70, 30));

        BtnModifProc.setText("Modificar");
        BtnModifProc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifProc.setContentAreaFilled(false);
        BtnModifProc.setEnabled(false);
        BtnModifProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifProcActionPerformed(evt);
            }
        });
        JMotivo.add(BtnModifProc, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 70, 30));

        BtnProceso.setText("Guardar");
        BtnProceso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnProceso.setContentAreaFilled(false);
        BtnProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcesoActionPerformed(evt);
            }
        });
        JMotivo.add(BtnProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 70, 30));

        BtnRetirar.setText("Retirar");
        BtnRetirar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnRetirar.setContentAreaFilled(false);
        BtnRetirar.setEnabled(false);
        BtnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRetirarActionPerformed(evt);
            }
        });
        JMotivo.add(BtnRetirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, 70, 30));

        jTabbedPane2.addTab(" Motivo & Procedimientos |", JMotivo);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnImprimirHistoria.setText("Imprimir");
        BtnImprimirHistoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnImprimirHistoria.setContentAreaFilled(false);
        BtnImprimirHistoria.setEnabled(false);
        BtnImprimirHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirHistoriaActionPerformed(evt);
            }
        });
        jPanel10.add(BtnImprimirHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, 150, 40));

        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Indicador de salud (resultado)")));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox2MouseEntered(evt);
            }
        });
        jPanel10.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 640, 50));

        jTextRecomendacion.setColumns(20);
        jTextRecomendacion.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextRecomendacion.setLineWrap(true);
        jTextRecomendacion.setRows(5);
        jTextRecomendacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Recomendaciones"));
        jScrollPane5.setViewportView(jTextRecomendacion);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 850, 200));

        jTextConclusion.setColumns(20);
        jTextConclusion.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextConclusion.setLineWrap(true);
        jTextConclusion.setRows(5);
        jTextConclusion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Conclusión"));
        jScrollPane8.setViewportView(jTextConclusion);

        jPanel10.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 850, 210));

        BtnGuardarHistoria.setText("Guardar");
        BtnGuardarHistoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnGuardarHistoria.setContentAreaFilled(false);
        BtnGuardarHistoria.setEnabled(false);
        BtnGuardarHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarHistoriaActionPerformed(evt);
            }
        });
        jPanel10.add(BtnGuardarHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 140, 40));

        BtnImprimirHistoria2.setText("Cancelar");
        BtnImprimirHistoria2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnImprimirHistoria2.setContentAreaFilled(false);
        BtnImprimirHistoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirHistoria2ActionPerformed(evt);
            }
        });
        jPanel10.add(BtnImprimirHistoria2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 110, 40));

        BtnVisualizar.setText("Visualizar");
        BtnVisualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnVisualizar.setContentAreaFilled(false);
        BtnVisualizar.setEnabled(false);
        BtnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVisualizarActionPerformed(evt);
            }
        });
        jPanel10.add(BtnVisualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, 140, 40));

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar en la lista"));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel10.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 200, 50));

        jTabbedPane2.addTab("Conclusión |", jPanel10);

        JPanelHistoria.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 920, 600));

        tabbedAntecedentes.addTab("Historia Clinica |", JPanelHistoria);

        JPanelAnteriores.setBackground(new java.awt.Color(255, 255, 255));
        JPanelAnteriores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableVisualizarC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Doctor", "Reporte", "Fecha", "Estado"
            }
        ));
        jTableVisualizarC.setComponentPopupMenu(PopupHistorias);
        jTableVisualizarC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVisualizarCMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableVisualizarC);

        JPanelAnteriores.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 880, 540));

        tabbedAntecedentes.addTab("Evolución & citas anteriores |", JPanelAnteriores);

        jPanel1.add(tabbedAntecedentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 910, 630));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarTPacientes(); 
        ListarPacientes();
   
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        
        JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FechaAsignar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FechaAsignar1FocusLost
     
  
    }//GEN-LAST:event_FechaAsignar1FocusLost

  
    private void FechaAsignar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FechaAsignar1PropertyChange
    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(Fecha.getTime());
    String fechaNueva = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());



if (!fechaActual.equals(fechaNueva) || filaPaciente != -1) {
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
}


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
                limpiarTVisualizar();
                visualizar_PdfVOHistorias(jTableVisualizarC);
            }
        }
    }//GEN-LAST:event_PendienteActionPerformed

    private void BtnModifProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifProcActionPerformed
if(jTextArea15.equals("")){

JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS", "CAMPOS", 1);
}

else{modificarprocedimiento();
     limpiarTProcedimientos2();
     ListarProcedimientos2();
     BtnProceso.setEnabled(true);

}


    }//GEN-LAST:event_BtnModifProcActionPerformed

    
    
    
    private void BtnImprimirHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirHistoriaActionPerformed
    
        
 if(jComboBox2.getSelectedItem().equals("")){
  JOptionPane.showMessageDialog(this, "LA LISTA RESULTADO EN CONCLUSIÓN NO PUEDE ESTAR VACIA", "CAMPOS", JOptionPane.ERROR_MESSAGE);
  }  
      else{
      
          
       try{
       
       pdfHistorias();
      //JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
      // Desktop.getDesktop().open(fileHistoria);

       guardarHistoria();
       ActualizarHistoriaBd();
       imprimirReporte();
  
       
       
       }catch(Exception e){ System.out.println(e);}   
  

      }
    
    
    }//GEN-LAST:event_BtnImprimirHistoriaActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    int idtablaproceso;
    String nombreAntc1;

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
               
               int fila = jTable4.getSelectedRow();
               idtablaproceso=(int) (jTable4.getValueAt(fila, 0));
               nombreAntc1=jTable4.getValueAt(fila, 1).toString();
   
    }//GEN-LAST:event_jTable4MouseClicked
int  idtablaConsulta;
int  idtablaHistorias;
    private void jTableVisualizarCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisualizarCMouseClicked


        jTableVisualizarC.requestFocusInWindow();
       // int fila = JTablePacientes.getSelectedRow();
        int column = jTableVisualizarC.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTableVisualizarC.getRowHeight();
        
    
        
        if (jLabel7.getText().equals("")) {
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
  
  
  
    private void JTablePacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacientesMouseClicked
    filaPaciente = JTablePacientes.getSelectedRow();


if (filaPaciente != -1) {
 selectedList();
} else {
    

 //  selectedList();
    
// JTablePacientes.setRowSelectionInterval(0, 0);
}

   
    }//GEN-LAST:event_JTablePacientesMouseClicked

    
    
    
    public void selectedList(){
     cleanHistorias();
     
   
    

    idAsignar = (int) JTablePacientes.getValueAt(filaPaciente, 0);
    nombrepaciente = JTablePacientes.getValueAt(filaPaciente, 1).toString();
    cedula = JTablePacientes.getValueAt(filaPaciente, 2).toString();
    estado = JTablePacientes.getValueAt(filaPaciente, 3).toString();

    BtnImprimirHistoria.setEnabled(true);
    BtnGuardarHistoria.setEnabled(true);
    BtnVisualizar.setEnabled(true);
    jLabel7.setText(nombrepaciente);
    SearchIdPatient();

    

    limpiarTProcedimientos();
    limpiarTProcedimientos2();
    ListarProcedimientos();
    ListarProcedimientos2();


    
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
           
    
    
    /*
    int idValidado = usingData ? idtablaHistorias : idAsignar;

    limpiarShowAntc1();
    ShowAntc1(idValidado);

    limpiarShowAntc2();
    ShowAntc2(idValidado);

    limpiarEAdc();
    ShowExamAdic(idValidado);

    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
    */
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
    
    
 

    
    
    

    
    
    
    private void BtnProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProcesoActionPerformed

        int fila = jTable4.getSelectedRow();
        if (fila == -1) {
        
         JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA DE LA TABLA PROCESOS", "PROCESOS", 1);
        
        }
        
        else if (jTextArea15.getText().equals("")) {
         JOptionPane.showMessageDialog(null, "EL CAMPO OBSERVACIÓN NO PUEDE ESTAR VACIO", "PROCESOS", 1);
        }
        else{
            
        guardarprocedimiendo();
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        }
    }//GEN-LAST:event_BtnProcesoActionPerformed

    
      int idprocedimientos;
    
    private void jTableProcedimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProcedimientosMouseClicked
        int fila = jTableProcedimientos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            BtnModifProc.setEnabled(true);
            BtnRetirar.setEnabled(true);
            BtnProceso.setEnabled(false);
            idprocedimientos=(int) (jTableProcedimientos.getValueAt(fila, 0));
            String  procedimientos=(jTableProcedimientos.getValueAt(fila, 2).toString());
            
            
            jTextArea15.setText(procedimientos);
        //   jTextField1.setText(JTableProcedimientos.getValueAt(fila, 1).toString());
        //   JComboEspecialidad.setSelectedItem(JTableProcedimientos.getValueAt(fila, 2).toString());
        //   EstadoTexto =JTableProcedimientos.getValueAt(fila, 3).toString();
    
    
    }               
    }//GEN-LAST:event_jTableProcedimientosMouseClicked

    private void jTableProcedimientosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableProcedimientosFocusLost

        
    }//GEN-LAST:event_jTableProcedimientosFocusLost

    private void BtnModifProc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifProc1ActionPerformed
       jTextArea15.setText("");
       BtnModifProc.setEnabled(false);
       BtnProceso.setEnabled(true);
       BtnRetirar.setEnabled(false);
       limpiarTProcedimientos();
       limpiarTProcedimientos2();
       ListarProcedimientos();
       ListarProcedimientos2();
    }//GEN-LAST:event_BtnModifProc1ActionPerformed

    private void BtnGuardarHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarHistoriaActionPerformed

try{
    
    
  if(jComboBox2.getSelectedItem().equals("")){
  JOptionPane.showMessageDialog(this, "LA LISTA RESULTADO EN CONCLUSIÓN NO PUEDE ESTAR VACIA", "CAMPOS", JOptionPane.ERROR_MESSAGE);
  }
  
    
  
  else{
      
      
      try{
      
      pdfHistorias(); 
      guardarHistoria();
      ActualizarHistoriaBd();
      limpiarTVisualizar();
      visualizar_PdfVOHistorias(jTableVisualizarC);
      limpiarTPacientes(); 
      ListarPacientes();
      JOptionPane.showMessageDialog(this, "HISTORIA CLINICA GUARDADA", "HISTORIA CLINICA",1);
      }catch(Exception e)
      { System.out.println(e); JOptionPane.showMessageDialog(this, "ERROR AL REALIZAR ESTE PROCESO, INTENTE DE NUEVO. DE CONTINUAR EL ERROR CONTACTE A SOPORTE", "CAMPOS", JOptionPane.ERROR_MESSAGE);}
      
      

  }  
    



}catch(Exception e){System.out.println(e);}
    }//GEN-LAST:event_BtnGuardarHistoriaActionPerformed

    private void jUtilizarHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUtilizarHistoActionPerformed

 callHistoryinfo();
 
 
    }//GEN-LAST:event_jUtilizarHistoActionPerformed

    
    
    int antcid1;
    String ShowAntcObs="",ShowName1;
    
    
    
    private void BtnImprimirHistoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirHistoria2ActionPerformed
           if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar la historia clinica?",
            "Cancelar historia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
               cleanAll();
               usingData=false;
           }
    }//GEN-LAST:event_BtnImprimirHistoria2ActionPerformed

    
    
    
    public void cleanAll(){
    
        cleanHistorias();
        BtnImprimirHistoria.setEnabled(false); 
        limpiarTPacientes(); 
        ListarPacientes();
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        jLabel7.setText("");
        JTablePacientes.requestFocus();
        
        limpiarTVisualizar();
  
    
    }
    
    
    
    
    
    
    
    int antcid2;
    String ShowAntcObs2="",ShowName2;
    
    
    
    
    int idEadc;
    String nomEadc, descripEadc;
    private void TxtbusquedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtbusquedaFocusLost
      JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_TxtbusquedaFocusLost

    private void jTextMotivoConsultaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextMotivoConsultaFocusLost

    private void JTablePacientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacientesMouseEntered
     JTablePacientes.requestFocusInWindow();
    }//GEN-LAST:event_JTablePacientesMouseEntered

    private void BtnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVisualizarActionPerformed

     

            try{

                pdfHistorias();
                //    JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
                Desktop.getDesktop().open(fileHistoria);

            }catch(Exception e){ System.out.println(e);}

        
    }//GEN-LAST:event_BtnVisualizarActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
   
      if (jComboBox2.getItemCount() == 0 || jTextField1.getText().isEmpty()) {
    llenarResultado();
} else {
    filterComboBox();
}
    
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseEntered
      jComboBox2.requestFocusInWindow();
    }//GEN-LAST:event_jComboBox2MouseEntered

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

    private void BtnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRetirarActionPerformed

        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea retirar este procedimiento?",
            "Retirar procedimiento", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
        retirarProcedimiento();
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        BtnRetirar.setEnabled(false);
        BtnProceso.setEnabled(true);
        BtnModifProc.setEnabled(false);
        }

    }//GEN-LAST:event_BtnRetirarActionPerformed

         
      public void retirarProcedimiento(){
      
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
 
            String sql = "update asignar_procedimiento set   Descripcion_Procedimiento=?, Id_Estado=? where Id_AProcedimientos=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "");
            ps.setInt(2, 102);
            ps.setInt(3, idprocedimientos);
            int res = ps.executeUpdate();

            if (res >= 1) {
            JOptionPane.showMessageDialog(null, "PROCEDIMIENTO RETIRADO", "ACTUALIZACIÓN DE DATOS", 1);
            jTextArea15.setText("");
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
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
    
    
    
    public void llenarResultado(){
     try{
      
        jComboBox2.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = lc.llenarResultado();
        for (int i = 0; i < lista.size(); i++) {
            jComboBox2.addItem(lista.get(i));

        }
  

        
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );}
    
    
    }
    
    
 
    
    
 public void modificarprocedimiento(){
 
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
           
 
            String sql = "update asignar_procedimiento set    Descripcion_Procedimiento=? where Id_AProcedimientos=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, jTextArea15.getText());
            ps.setInt(2, idprocedimientos);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PROCEDIMIENTO ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            
            
        } catch (Exception e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
        
        
       finally {
            closeResources(rs, ps, con);
        }
                 
 
 
 }
   
    
    
    
    
    
    
    
    
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
    
    
    
    
  int idpacientes;
  public ArrayList<JCMostrarAsignados> LlenarLista(){
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps =null;
    ResultSet rs = null;
    
    
    
ArrayList<JCMostrarAsignados> al=null;
al=new ArrayList<JCMostrarAsignados>();
String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());   
 String sql = "SELECT Id_historias, Id_usuarioh, Nombre, Apellido, Cedula, Usuario, date_format(Fecha_Historia, '%d/%m/%Y') AS Fecha, especialidad, Estado, Id_Estadoh, Fecha_Historia, Idpaciente " +
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
                idpacientes=rs.getInt("Idpaciente"); 
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
    
    
    
    
    
    
  
  
  
  
   public void ListarProcedimientos(){
       List<JCProcedimientos> lista = LlenarListaPro();
        modelo = (DefaultTableModel) jTable4.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdprocedimiento();
            ob[1] = lista.get(i).getProcedimiento();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
       jTable4.setModel(modelo);
       jTable4.setDefaultEditor(Object.class, null);
       acomodarProcedimientos();
    }
  
  
   
      public void ListarProcedimientos2(){
       List<JCProcedimientos> lista = LlenarListaPro2();
        modelo = (DefaultTableModel) jTableProcedimientos.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdprocedimiento();
            ob[1] = lista.get(i).getProcedimiento();
            ob[2] = lista.get(i).getObservacion();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
       jTableProcedimientos.setModel(modelo);
       jTableProcedimientos.setDefaultEditor(Object.class, null);
       acomodarProcedimientos2();
    }
   
   
   
  
  
public ArrayList<JCProcedimientos> LlenarListaPro() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    ArrayList<JCProcedimientos> al = new ArrayList<>();

    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());
   String sql = "SELECT Id_AProcedimientos, Id_Procedimiento, Procedimiento, Id_Paciente, Id_Usuario, Especialidad, Descripcion_Procedimiento, Fecha_Procedimiento, Id_Estado, Estado " +
             "FROM asignar_procedimiento u " +
             "INNER JOIN table_paciente c ON u.Id_Paciente = c.Idpaciente " +
             "INNER JOIN table_estado p ON u.Id_Estado = p.IdEstado " +
             "INNER JOIN table_especialidad w ON u.Id_Especialidad = w.id_especialidad " + 
             "INNER JOIN table_procedimientos x ON u.Id_Procedimiento = x.idProcedimiento " + 
             "WHERE Id_Usuario LIKE ? AND Id_Paciente LIKE ? AND especialidad LIKE ?  AND Fecha_Procedimiento LIKE ? " +
             "ORDER BY Id_AProcedimientos";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + idusuario + "%");
        ps.setString(2, "%" + PacienteSelec + "%");
        ps.setString(3, "%" + especialidad + "%");
        ps.setString(4, "%" + fecha + "%");

        rs = ps.executeQuery();

        while (rs.next()) {
            int estado = rs.getInt("Id_Estado");
            if (estado == 102) {
                JCProcedimientos prd = new JCProcedimientos();
                prd.setIdprocedimiento(rs.getInt(1));
                prd.setProcedimiento(rs.getString(3));
                prd.setObservacion(rs.getString(7));
                prd.setEstado(rs.getString(10));

                al.add(prd);
            }
        }
    } catch (Exception e) {
        System.err.println("Error al listar" + e);
    } finally {
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

    
          public  void limpiarTProcedimientos() {
        DefaultTableModel tb = (DefaultTableModel) jTable4.getModel();
        int a = jTable4.getRowCount()-1;
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

      
      
         
          public  void limpiarTProcedimientos2() {
        DefaultTableModel tb = (DefaultTableModel) jTableProcedimientos.getModel();
        int a = jTableProcedimientos.getRowCount()-1;
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
      
      
   
   
   
   
  
      
     String PdfNames;
     int idexamen;
      

     
     
       File fileHistoria;
       String PdfNamesHistorias;
       public void pdfHistorias() {
      
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
            
            PdfNamesHistorias="Historia_Paciente";
            
        
            
            int diferenciadia=Integer.parseInt(dia_actual)- Integer.parseInt(dia1);
            int diferenciames=Integer.parseInt(mes_actual)- Integer.parseInt(mes1);
            int diferencian=Integer.parseInt(an_actual)- Integer.parseInt(an1);;

            
            if (diferenciames < 0 || (diferenciames == 0 && diferenciadia < 0)) {
            diferencian = diferencian - 1;
            }
 
   
        
          
          
         BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
         Font Letra = new Font(BF, 10); 
         Font Letrasmall = new Font(BF, 8);
         Paragraph saltolinea = new Paragraph();
          

         FileOutputStream archivo;
         fileHistoria = new File("C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf");
         archivo = new FileOutputStream(fileHistoria);
         Document doc = new Document();
         PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
           doc.setMargins(36, 36, 170, 130);
           
            
       // Agregas el footer utilizando la clase PdfPageEventHelper
             
            HeaderFooterPageEvent headerEvent = new HeaderFooterPageEvent();
            writer.setPageEvent(headerEvent);
            writer.setPageEvent(eventHelper);
       


            
         
            doc.open();
           
         
            
            
            
            
                 
         
                

            
            //PARTE 1
            PdfPTable Tabla2 = new PdfPTable(1); 
            Tabla2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            float[] medidaCeldas3 = {5f};
            Tabla2.setWidthPercentage(100);  
            Tabla2.setWidths(medidaCeldas3);
            Tabla2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t2columna1 = new Paragraph("I N F O R M E    C O N S U L T A");
            t2columna1.getFont().setStyle(Font.BOLD);
            t2columna1.getFont().setSize(10);        
            t2columna1.setFont(Letra);
           
            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cell.setPadding(0);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPhrase (new Paragraph(jTextMotivoConsulta.getText(),FontFactory.getFont("Arial",9,Font.NORMAL)));
            
            t2columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            Tabla2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            Tabla2.addCell(t2columna1);
            Tabla2.addCell(cell);   
         

     

            
            
            
            
            




   
            PdfPTable TableConclusion = new PdfPTable(1); 
            TableConclusion.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            TableConclusion.setWidthPercentage(100);  
            TableConclusion.setWidths(medidaCeldas3);
            TableConclusion.setHorizontalAlignment(Element.ALIGN_CENTER);
     
           
            
            Paragraph t67columna1 = new Paragraph("D I A G N Ó S T I C O   &   R E C O M E N D A C I O N E S \n\n"+ jComboBox2.getSelectedItem().toString());
            t67columna1.getFont().setStyle(Font.BOLD);
            t67columna1.getFont().setSize(10);        
            t67columna1.setFont(Letra);
            t67columna1.setAlignment(Element.ALIGN_CENTER);
            
            PdfPCell Conclusiones = new PdfPCell(t67columna1 );
            Conclusiones.setBorder(Rectangle.NO_BORDER);
            Conclusiones.enableBorderSide(Rectangle.BOTTOM);
            Conclusiones.setHorizontalAlignment(Element.ALIGN_CENTER);
            TableConclusion.addCell(Conclusiones);  

            
            
            

            PdfPTable TableConclusion2 = new PdfPTable(1); 
            TableConclusion2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            TableConclusion2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            TableConclusion2.setWidthPercentage(100);  
            TableConclusion2.setWidths(medidaCeldas3);
            TableConclusion2.setHorizontalAlignment(Element.ALIGN_CENTER);
            TableConclusion2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t68columna1 = new Paragraph(jTextConclusion.getText());
            t68columna1.getFont().setStyle(Font.NORMAL);
            t68columna1.getFont().setSize(9);        
            t68columna1.setFont(Letrasmall);
            t68columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell Thend1 = new PdfPCell(t68columna1);
            Thend1.setBorder(Rectangle.NO_BORDER);
            Thend1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
           // aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            TableConclusion2.addCell(Thend1);
            
            Paragraph t69columna1 = new Paragraph(jTextRecomendacion.getText());
            t69columna1.getFont().setStyle(Font.NORMAL);
            t69columna1.getFont().setSize(9);        
            t69columna1.setFont(Letrasmall);
            t69columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell Thend2 = new PdfPCell(t69columna1);
            Thend2.setBorder(Rectangle.NO_BORDER);
            Thend2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //aspectoFisico2.enableBorderSide(Rectangle.BOTTOM);
            TableConclusion2.addCell(Thend2);






 
            
            
            
  
            
            
            
         
           
           


           
            saltolinea.add("\n");
           
            // H E A  D E R
           // doc.add(Tabla);
         //   doc.add(Tabla0);
        //    doc.add(saltolinea);
          
            
            
            
            //B O D Y
           
            
            if(!jTextMotivoConsulta.getText().equals("") || !jTextConclusion.getText().equals("") || !jTextRecomendacion.getText().equals("")){
            if(!jTextMotivoConsulta.getText().equals("")  ){
            doc.add(Tabla2);
            doc.add(saltolinea);
             }
               
            if(!jTextConclusion.getText().equals("") || !jTextRecomendacion.getText().equals("") ){
            doc.add(TableConclusion);
            doc.add(saltolinea);
            doc.add(TableConclusion2);
            
            }
            
         
            }
            
            
            
           if(jTableProcedimientos.getModel().getRowCount() > 0){
                       doc.newPage();
//PROCEDIMIENTOS
int tProcedimientos = jTableProcedimientos.getRowCount();  
if (tProcedimientos > 0) {
    for (int i = 0; i < tProcedimientos; i++) {
        String Procedimiento = jTableProcedimientos.getValueAt(i, 1).toString();
        String Observacion = jTableProcedimientos.getValueAt(i, 2).toString();

        // Alinea el texto según sea necesario
        PdfPCell cellTitulo = new PdfPCell(new Paragraph(Procedimiento, FontFactory.getFont("Arial", 11, Font.BOLD)));
        cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTitulo.setBorder(PdfPCell.NO_BORDER);

        PdfPCell cellEspacio = new PdfPCell(new Paragraph(" "));
        cellEspacio.setBorder(PdfPCell.NO_BORDER);
       
        
        PdfPCell cellObservacion = new PdfPCell(new Paragraph(Observacion, FontFactory.getFont("Arial", 9, Font.NORMAL)));
        cellObservacion.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cellObservacion.setBorder(PdfPCell.NO_BORDER);

        // Agregamos el par de procedimiento y observación a la tabla
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.addCell(cellTitulo);
        table.addCell(cellEspacio);
        table.addCell(cellObservacion);

        // Agregamos la tabla al documento
        doc.add(table);

        // Si no es la última iteración, añadimos una nueva página
        if (i < tProcedimientos - 1) {
            doc.newPage();
        }
    }
}

  }

            //- C O N C L U S I O N -
            
        
     
            
            

            
       
            
    
            doc.close();
            archivo.close();
         
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
      
      
       
       
       
                  
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
        try {
            
    
            String fechaN = new SimpleDateFormat("yyyy/MM/dd").format(fechanac);
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
            diferencian = diferencian - 1;}
            
            
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            
      

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
        
          cb.setFontAndSize(BF, 10);
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

          cb.endText();

            
            
       
            
     
            // ... Continúa con el resto del contenido

            
            PdfPTable Tabla = new PdfPTable(3); 
            Tabla.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()); 
           
            float[] medidaCeldas = {1f, 3f, 1f };
            Tabla.setWidths(medidaCeldas);
            Tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            

            
          
            Paragraph tcolumna1 = new Paragraph("N° ORDEN: "+idAsignar);
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

            Paragraph tcolumna3 = new Paragraph("N° PACIENTE: " +PacienteSelec+" ");
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(8);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna3);
            
            
            PdfPTable Tabla0 = new PdfPTable(4); 
            Tabla0.getDefaultCell().setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            Tabla0.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()); 
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

            Paragraph t0columna3 = new Paragraph("EDAD: " + Integer.toString(diferencian));
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
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
               float footerHeight = 590;
               float footerHeight0 = 573;
   
            
            
            Tabla.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() + footerHeight, writer.getDirectContent());
            Tabla0.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() + footerHeight0, writer.getDirectContent());
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
      
      
      
      
      
      
      
      
      
      
   
      
 
       public void guardarReporte()
    {

      
        ruta_archivo="C://Fundaginebra//Reportes_Consulta//"+PdfNames+".pdf";
        int codigo =  idAsignar;
        File ruta = new File(ruta_archivo);
       
        
       
        
        if ( ruta_archivo.trim().length() != 0) {
           
            
            
            guardar_pdf(codigo, ruta);
 
         //   tpdf.visualizar_PdfVO(tabla);
         ruta_archivo = "";
         // activa_boton(false, false, false);
  
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


    
   
      
      
      
    public void guardarprocedimiendo(){
      
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
 
            String sql = "update asignar_procedimiento set   Descripcion_Procedimiento=?, Id_Estado=? where Id_AProcedimientos=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, jTextArea15.getText());
            ps.setInt(2, 103);
            ps.setInt(3, idtablaproceso);
            int res = ps.executeUpdate();

            if (res >= 1) {
            JOptionPane.showMessageDialog(null, "PROCEDIMIENTO COMPLETADO", "ACTUALIZACIÓN DE DATOS", 1);
            jTextArea15.setText("");
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
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
       
       
       
       
       
          
      
      
 
 String rutaHistoria;
 public void guardarHistoria()
    {

      
        rutaHistoria="C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf";
        int codigo =  idAsignar;
        File ruta = new File(rutaHistoria);
        int paciente=PacienteSelec;
        int estadohistoria=103;
        
       
        
        if ( rutaHistoria.trim().length() != 0) {
           
            
            
            guardarH_pdf(codigo, ruta, paciente, estadohistoria);
 
         //   tpdf.visualizar_PdfVO(tabla);
         rutaHistoria = "";
         // activa_boton(false, false, false);
  
        } else {
            JOptionPane.showMessageDialog(null, "Rellenar todo los campos");
        }
    
    }
       
       
       
       
       
       
      
       public void guardarH_pdf(int codigo, File ruta, int paciente, int estadohistoria) {
        
        
   
        
        AddPdfConsulta pa = new AddPdfConsulta();
        AddPdfConsultaDao po = new  AddPdfConsultaDao();
        pa.setIdHistoria(codigo);
        pa.setId_pacienteh(paciente);
        pa.setId_Estadoh(estadohistoria);
        
        
        
        try {
            byte[] pdf = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(pdf);
            pa.setArchivoHistoria(pdf);
        } catch (IOException ex) {
            pa.setArchivoHistoria(null);
            System.out.println("Error al agregar archivo pdf "+ex);
        }
        po.AgregarHistoriaPdfVO(pa);
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
        dt.addColumn("Doctor");
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
"WHERE IdPacientes = '" + PacienteSelec + "' ORDER BY IdAsignar";                
//"where Id_Especialidades LIKE '%" + idespecialidad + "%'     AND IdPacientes = "+  '"' + PacienteSelec + '"'  +   "ORDER by IdAsignar";    

                

                
                
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
        }    finally {

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
        dt.addColumn("Doctor");
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
    
      
        
        
          public void acomodarConsultasN()
    {
     
       DefaultTableModel Tabla = (DefaultTableModel)jTableVisualizarC.getModel();
       DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
       Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
       jTableVisualizarC.getColumnModel().getColumn(0).setMaxWidth(0);
       jTableVisualizarC.getColumnModel().getColumn(0).setMinWidth(0);
       jTableVisualizarC.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Doctor")).setPreferredWidth(100);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setPreferredWidth(80);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(80);
     
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Doctor")).setCellRenderer(Alinear);
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setCellRenderer(Alinear);;
       jTableVisualizarC.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);
    
    }
     
        
           
     public void mostrarRevision(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;

          
      // MOSTRAR EXAMEN FISICO NORMAL    
       try{
    
      //int fila = JTablePacientes.getSelectedRow();
     
       String query="SELECT * from table_revision where Id_historiaR =?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
  
       jTextMotivoConsulta.setText(rs.getString("Motivo_Consulta")); 

       

       }
       

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
            
            "WHERE Cedula LIKE ? AND especialidad NOT IN ('Ocupacional', 'Psicologia') \n" +
            "ORDER BY Id_historias";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        // Utilizar setString para evitar problemas de SQL injection
        ps.setString(1, "%" + cedula + "%");
      //  ps.setString(2, "%" + especialidad + "%");

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
    } finally {
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
    
      
      
    
      

        
   
     
     
     
     
     
      
        
    
        
        
        
        
        
        
     
     private String CMA, MPPS;
     public void ActivarFirma() {
        try (Connection con = new EnlaceBd().getConnection()) {
            int idfirma = TP.getTexto();
            String query = "SELECT Firma, CMA, MPPS FROM table_personal WHERE IdPersonal = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idfirma);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        byte[] firmaBytes = rs.getBytes("Firma");
                        CMA = rs.getString("CMA");
                        MPPS = rs.getString("MPPS");

                        if (firmaBytes != null) {
                            try (InputStream bos = new ByteArrayInputStream(firmaBytes);
                                 OutputStream out = new FileOutputStream("imagen.bin")) {
                                byte[] buffer = new byte[bos.available()];
                                bos.read(buffer);
                                out.write(buffer);
                            }
                        } else {
                            DesactivarFirma();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en activar firma " + e);
        }
    }
     
     
      
      
      
  public void DesactivarFirma() {
    String nombreUsuario = TP.getUser();

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT imgwhite FROM tableinfo WHERE Idinfo = ?")) {

        ps.setInt(1, 1);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                byte[] firmaBytes = rs.getBytes("imgwhite");

                try (InputStream bos = new ByteArrayInputStream(firmaBytes);
                     OutputStream out = new FileOutputStream("imagen.bin")) {

                    byte[] buffer = new byte[bos.available()];
                    bos.read(buffer);
                    out.write(buffer);
                }
            }
        }

        // Opcional: Puedes mostrar un mensaje al usuario
        // JOptionPane.showMessageDialog(null, "Estimado: " + nombreUsuario + " su firma ha sido desactivada", "DESACTIVAR FIRMA", 1);

    } catch (Exception e) {
        System.out.println("Error en desactivar firma: " + e);
    }
}
  
  
  boolean usingData=false;
  public void callHistoryinfo(){
  
      
    usingData = true; 
    mostrarRevision();
    mostrarConclusion();
    

    BtnImprimirHistoria.setEnabled(true);
    BtnGuardarHistoria.setEnabled(true);
    
    JOptionPane.showMessageDialog(null, "LOS DATOS FUERON CARGADOS A HISTORIA CLINICA", "HISTORIA CLINICA", 1);
    
    tabbedAntecedentes.setSelectedIndex(0);
    jTabbedPane2.setSelectedIndex(0);

    
    
  }
  
  
  
  
  
  
 
     
     
     
     
    
           
    public void mostrarConclusion(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
          
          
      
       try{
    

       String query="SELECT * from table_conclusion where Id_conclusionh=?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       jComboBox2.setSelectedItem(rs.getString("Resultado"));
       jTextConclusion.setText(rs.getString("Conclusion"));
       jTextRecomendacion.setText(rs.getString("Recomendacion"));


   
       }
       

       
       
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
      
           
           
   
    
    


  
  
  
  
 
  
  
  
          
          
          

    
    
public void ActualizarHistoriaBd() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = cn.getConnection();

  
        
        // Actualización de Revisión
        if (existeRegistro(con, "table_revision", "Id_historiaR", idAsignar)) {
            updateRevision();
        } else {
            insertarResivion();
            updateRevision();
        }
        
        // Actualización de Conclusión
        if (existeRegistro(con, "table_conclusion", "Id_conclusionh", idAsignar)) {
            updateConclusion();
        } else {
            insertarConclusion();
            updateConclusion();
        }

    } catch (Exception e) {
        System.out.println("Error al actualizar la historia: " + e);
    } finally {
        cerrarConexiones(ps, con);
    }
}






  
public void updateRevision() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;




    try {
        String sql = "UPDATE `table_revision` SET   `Motivo_Consulta`=?  WHERE `Id_historiaR`=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);



        ps.setString(1, jTextMotivoConsulta.getText());

        ps.setInt(2, idAsignar);

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR REVISION: " + e);
    }finally {
            closeResources(null, ps, con);
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
  
  
  
  public void insertarFisico(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
              
          try {
 
            String sql2 = "INSERT INTO examen_fgeneral (Id_Historia)  values(?) ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);
            ps.executeUpdate();
        
     
            
        } catch (Exception e) {
            System.out.println(e);
         
           }finally {
            closeResources(rs, ps, con);
        }


           try {
 
            String sql2 = "INSERT INTO examen_fisico (Id_Historia)  values(?) ";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);
            ps.executeUpdate();
            
          
            
        } catch (Exception e) {
            System.out.println(e);
         
           }finally {
            closeResources(rs, ps, con);
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
         
           }finally {
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
         
           }finally {
            closeResources(rs, ps, con);
        }

  }

 
public void updateConclusion() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;



    try {
        String sql = "UPDATE `table_conclusion` SET  `Resultado`=?, `Conclusion`=?, `Recomendacion`=? WHERE `Id_conclusionh`=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);



        ps.setString(1, jComboBox2.getSelectedItem().toString());
        ps.setString(2, jTextConclusion.getText());
        ps.setString(3, jTextRecomendacion.getText());
        ps.setInt(4, idAsignar);

        ps.executeUpdate();

    
    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR CONCLUSION: " + e);
    }finally {
            closeResources(null, ps, con);
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
                BtnImprimirHistoria.setEnabled(false);
                BtnGuardarHistoria.setEnabled(false);
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
     
     
          
     public void acomodarProcedimientos()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTable4.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTable4.setRowHeight(20);
        
  
        
        jTable4.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable4.getColumnModel().getColumn(0).setMinWidth(0);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(0);
                
   //   jTable4.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(10);
        jTable4.getColumnModel().getColumn(Tabla.findColumn("Procedimiento")).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(50);



      
        jTable4.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTable4.getColumnModel().getColumn(Tabla.findColumn("Procedimiento")).setCellRenderer(Alinear);
        jTable4.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);;
    

    }
     
     
 
      
     
      public void acomodarProcedimientos2()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTableProcedimientos.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTableProcedimientos.setRowHeight(20);
        
        jTableProcedimientos.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableProcedimientos.getColumnModel().getColumn(0).setMinWidth(0);
        jTableProcedimientos.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
       // jTable5.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(20);
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Procedimiento")).setPreferredWidth(50);
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setPreferredWidth(150);
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Estado")).setPreferredWidth(50);


      
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Procedimiento")).setCellRenderer(Alinear);
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setCellRenderer(Alinear);;
        jTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("Estado")).setCellRenderer(Alinear);;

    }
     
       
   
     
     
     
   
     
          
   
      
      
      
      
     
     
  

         private void filterComboBox() {
        String filterText = jTextField1.getText().toLowerCase();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (int i = 0; i < jComboBox2.getItemCount(); i++) {
            String item = (String) jComboBox2.getItemAt(i);
            if (item.toLowerCase().contains(filterText)) {
                model.addElement(item);
            }
        }
        jComboBox2.setModel(model);
        jComboBox2.setPopupVisible(model.getSize() > 0);
    }
      
      
      
      
     
 
     public void cleanHistorias(){
     
     jTextMotivoConsulta.setText("");
   
     jTextConclusion.setText("");
     jTextRecomendacion.setText("");
     jComboBox2.setSelectedItem("Buenas condiciones de salud");

    
     
     
     usingData=false;
     
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
     
     
     
     
   LlenarCombobox lc = new LlenarCombobox();
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
    private javax.swing.JButton BtnGuardarHistoria;
    private javax.swing.JButton BtnImprimirHistoria;
    private javax.swing.JButton BtnImprimirHistoria2;
    private javax.swing.JButton BtnModifProc;
    private javax.swing.JButton BtnModifProc1;
    private javax.swing.JButton BtnProceso;
    private javax.swing.JButton BtnRetirar;
    private javax.swing.JButton BtnVisualizar;
    private javax.swing.JMenuItem Completar;
    private com.toedter.calendar.JDateChooser FechaAsignar1;
    private javax.swing.JPanel JMotivo;
    private javax.swing.JPanel JPanelAnteriores;
    private javax.swing.JPanel JPanelHistoria;
    public javax.swing.JTable JTablePacientes;
    private javax.swing.JMenuItem Pendiente;
    private javax.swing.JPopupMenu PopupEstado;
    private javax.swing.JPopupMenu PopupHistorias;
    private javax.swing.JTextField Txtbusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableProcedimientos;
    private javax.swing.JTable jTableVisualizarC;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextConclusion;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextMotivoConsulta;
    private javax.swing.JTextArea jTextRecomendacion;
    private javax.swing.JMenuItem jUtilizarHisto;
    private javax.swing.JTabbedPane tabbedAntecedentes;
    // End of variables declaration//GEN-END:variables
}
