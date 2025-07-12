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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;
import org.apache.poi.ss.usermodel.Footer;


public class JConsultasMedicas extends javax.swing.JInternalFrame implements Runnable{


    
      Calendar Fecha;
    public JConsultasMedicas() {
       
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Fecha = new GregorianCalendar();
        FechaAsignar1.setCalendar(Fecha); 
        obtenerFecha();

        informacionpdf();
        ActivarFirma();
      
        jRadioAF.setSelected(true);
        jRadioAPP.setSelected(false);
        iniciarActualizacionAutomatica();
        reOrderTable();

              
        
              
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
    
    
    
    public void reOrderTable(){
    
        JTablePacientes.getTableHeader().setReorderingAllowed(false);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jTableProcedimientos.getTableHeader().setReorderingAllowed(false);
        jTableAntecedentesF.getTableHeader().setReorderingAllowed(false);
        jTableAF2.getTableHeader().setReorderingAllowed(false);
        jTableAdic.getTableHeader().setReorderingAllowed(false);
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
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jRadioAF = new javax.swing.JRadioButton();
        jRadioAPP = new javax.swing.JRadioButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane34 = new javax.swing.JScrollPane();
        jTableAntecedentesF = new javax.swing.JTable();
        TxtbusquedaAntc = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        JtextAntc1 = new javax.swing.JTextArea();
        jBtnRetirarAntc = new javax.swing.JButton();
        jScrollPane35 = new javax.swing.JScrollPane();
        jTableAF2 = new javax.swing.JTable();
        jAButton30 = new javax.swing.JButton();
        BtnModifAnt = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        jTableAntcPers = new javax.swing.JTable();
        TxtBusAntcP = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextAntPersonales = new javax.swing.JTextArea();
        jButton26 = new javax.swing.JButton();
        jRetirarAntc2 = new javax.swing.JButton();
        jScrollPane33 = new javax.swing.JScrollPane();
        jTableAntpersonal = new javax.swing.JTable();
        BtnModifAnt2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckAngina = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckConstitucional = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckPielMucosa = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckORL = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckRespiratorio = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextRevision = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jComboBox16 = new javax.swing.JComboBox<>();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jComboBox19 = new javax.swing.JComboBox<>();
        jComboHabitos = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTConsumoDrugs = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboSexualidad = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jComboActividad = new javax.swing.JComboBox<>();
        jComboFrec = new javax.swing.JComboBox<>();
        jComboSueno = new javax.swing.JComboBox<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTConsumoAlcohol = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTConsumoTabaco = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerMenarquia = new javax.swing.JSpinner();
        jSmenospausia = new javax.swing.JSpinner();
        jSpartos = new javax.swing.JSpinner();
        jScesareas = new javax.swing.JSpinner();
        jSembarazo = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jComboMestruacion = new javax.swing.JComboBox<>();
        jScrollPane18 = new javax.swing.JScrollPane();
        Jtextwoman = new javax.swing.JTextArea();
        TxtCiclosMestru = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSabortos = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        FechaMestruacion = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        TXTsuperior = new javax.swing.JTextField();
        TXTinferior = new javax.swing.JTextField();
        TXTcolumna = new javax.swing.JTextField();
        TXTAbdomen2 = new javax.swing.JTextField();
        TXTcorazon = new javax.swing.JTextField();
        TXTcuello = new javax.swing.JTextField();
        TXTboca = new javax.swing.JTextField();
        TXTdientes = new javax.swing.JTextField();
        TXToidos = new javax.swing.JTextField();
        TXTojos = new javax.swing.JTextField();
        TXTpiel = new javax.swing.JTextField();
        TXTtemperatura = new javax.swing.JTextField();
        TXTtalla = new javax.swing.JTextField();
        TXTpeso = new javax.swing.JTextField();
        TXTpulso = new javax.swing.JTextField();
        TXTfr = new javax.swing.JTextField();
        TXTpesoIdeal = new javax.swing.JTextField();
        TXTpesoAdic = new javax.swing.JTextField();
        TXTrespiracion = new javax.swing.JTextField();
        TXTimc = new javax.swing.JTextField();
        TXTlinfaticos = new javax.swing.JTextField();
        TXTgenitales = new javax.swing.JTextField();
        TXTrectal = new javax.swing.JTextField();
        TXToftamoscopia = new javax.swing.JTextField();
        TXTnariz = new javax.swing.JTextField();
        TXTtorax = new javax.swing.JTextField();
        TXTmamas = new javax.swing.JTextField();
        TXTpulmones = new javax.swing.JTextField();
        TXThernias = new javax.swing.JTextField();
        TXTginecologo = new javax.swing.JTextField();
        TXTprostata = new javax.swing.JTextField();
        TXTneurologico = new javax.swing.JTextField();
        TXTvenas = new javax.swing.JTextField();
        TXTarterias = new javax.swing.JTextField();
        TXTtension = new javax.swing.JTextField();
        TXTaspecto = new javax.swing.JTextField();
        TXTabdominal = new javax.swing.JTextField();
        TXTcadera = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTableAdic = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTextObservAdic = new javax.swing.JTextArea();
        jButton21 = new javax.swing.JButton();
        jAdcDelete = new javax.swing.JButton();
        jTextExamenAdic = new javax.swing.JTextField();
        jAdcModif = new javax.swing.JButton();
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtbusquedaKeyTyped(evt);
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
        FechaAsignar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FechaAsignar1MouseEntered(evt);
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
        jLabelPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelPacienteMouseEntered(evt);
            }
        });
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
        jTextMotivoConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextMotivoConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextMotivoConsultaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextMotivoConsultaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextMotivoConsultaMouseReleased(evt);
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
        jTextArea15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea15MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea15MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextArea15MouseReleased(evt);
            }
        });
        jTextArea15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea15KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea15KeyTyped(evt);
            }
        });
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRadioAF.setBackground(new java.awt.Color(255, 255, 255));
        jRadioAF.setText("Antecedentes Familiares");
        jRadioAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAFActionPerformed(evt);
            }
        });
        jPanel14.add(jRadioAF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, -1));

        jRadioAPP.setBackground(new java.awt.Color(255, 255, 255));
        jRadioAPP.setText("Antecedentes Personales Patologicos");
        jRadioAPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAPPActionPerformed(evt);
            }
        });
        jPanel14.add(jRadioAPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 250, -1));

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 40));

        jTabbedPane3.setEnabled(false);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAntecedentesF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Enfermedad"
            }
        ));
        jTableAntecedentesF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAntecedentesFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAntecedentesFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTableAntecedentesFMouseExited(evt);
            }
        });
        jScrollPane34.setViewportView(jTableAntecedentesF);

        jPanel11.add(jScrollPane34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 550, 200));

        TxtbusquedaAntc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por nombre"));
        TxtbusquedaAntc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtbusquedaAntcFocusLost(evt);
            }
        });
        TxtbusquedaAntc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtbusquedaAntcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtbusquedaAntcKeyTyped(evt);
            }
        });
        jPanel11.add(TxtbusquedaAntc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 550, 40));

        jScrollPane21.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        JtextAntc1.setColumns(20);
        JtextAntc1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        JtextAntc1.setLineWrap(true);
        JtextAntc1.setRows(5);
        JtextAntc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JtextAntc1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JtextAntc1MouseReleased(evt);
            }
        });
        JtextAntc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JtextAntc1KeyTyped(evt);
            }
        });
        jScrollPane21.setViewportView(JtextAntc1);

        jPanel11.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 330, 210));

        jBtnRetirarAntc.setText("Retirar");
        jBtnRetirarAntc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnRetirarAntc.setContentAreaFilled(false);
        jBtnRetirarAntc.setEnabled(false);
        jBtnRetirarAntc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRetirarAntcActionPerformed(evt);
            }
        });
        jPanel11.add(jBtnRetirarAntc, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 80, 30));

        jTableAF2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Enfermedad", "Descripción"
            }
        ));
        jTableAF2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAF2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAF2MouseEntered(evt);
            }
        });
        jScrollPane35.setViewportView(jTableAF2);

        jPanel11.add(jScrollPane35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 890, 170));

        jAButton30.setText("Añadir");
        jAButton30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAButton30.setContentAreaFilled(false);
        jAButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAButton30ActionPerformed(evt);
            }
        });
        jPanel11.add(jAButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 80, 30));

        BtnModifAnt.setText("Modificar");
        BtnModifAnt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifAnt.setContentAreaFilled(false);
        BtnModifAnt.setEnabled(false);
        BtnModifAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifAntActionPerformed(evt);
            }
        });
        jPanel11.add(BtnModifAnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 80, 30));

        jTabbedPane3.addTab("Familiares", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAntcPers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Enfermedad"
            }
        ));
        jTableAntcPers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAntcPersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAntcPersMouseEntered(evt);
            }
        });
        jScrollPane32.setViewportView(jTableAntcPers);

        jPanel12.add(jScrollPane32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 550, 200));

        TxtBusAntcP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por nombre"));
        TxtBusAntcP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBusAntcPKeyReleased(evt);
            }
        });
        jPanel12.add(TxtBusAntcP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 550, 40));

        jScrollPane19.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        jTextAntPersonales.setColumns(20);
        jTextAntPersonales.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextAntPersonales.setLineWrap(true);
        jTextAntPersonales.setRows(5);
        jTextAntPersonales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAntPersonalesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAntPersonalesMouseReleased(evt);
            }
        });
        jTextAntPersonales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAntPersonalesKeyTyped(evt);
            }
        });
        jScrollPane19.setViewportView(jTextAntPersonales);

        jPanel12.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 330, 200));

        jButton26.setText("Añadir");
        jButton26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton26.setContentAreaFilled(false);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 80, 30));

        jRetirarAntc2.setText("Retirar");
        jRetirarAntc2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jRetirarAntc2.setContentAreaFilled(false);
        jRetirarAntc2.setEnabled(false);
        jRetirarAntc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRetirarAntc2ActionPerformed(evt);
            }
        });
        jPanel12.add(jRetirarAntc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 80, 30));

        jTableAntpersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Enfermedad", "Descripción"
            }
        ));
        jTableAntpersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAntpersonalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAntpersonalMouseEntered(evt);
            }
        });
        jScrollPane33.setViewportView(jTableAntpersonal);

        jPanel12.add(jScrollPane33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 890, 170));

        BtnModifAnt2.setText("Modificar");
        BtnModifAnt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifAnt2.setContentAreaFilled(false);
        BtnModifAnt2.setEnabled(false);
        BtnModifAnt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifAnt2ActionPerformed(evt);
            }
        });
        jPanel12.add(BtnModifAnt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 80, 30));

        jTabbedPane3.addTab("Patologico", jPanel12);

        jPanel6.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 920, 540));

        jTabbedPane2.addTab("Antecedentes |", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Simatología ");
        jPanel15.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 20));

        jLabel9.setText("CardioVascular");
        jPanel15.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jLabel10.setText("Digestivo");
        jPanel15.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 70, 20));

        jLabel11.setText("Músculo-Esqueletico");
        jPanel15.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 100, 20));

        jLabel12.setText("Neurológico");
        jPanel15.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 70, 20));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Cefalea");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        jCheckAngina.setBackground(new java.awt.Color(255, 255, 255));
        jCheckAngina.setText("Angina");
        jCheckAngina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckAnginaActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckAngina, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jCheckBox6.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox6.setText("Dolor Abdominal");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        jCheckConstitucional.setBackground(new java.awt.Color(255, 255, 255));
        jCheckConstitucional.setText("Constitucional");
        jCheckConstitucional.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckConstitucionalStateChanged(evt);
            }
        });
        jCheckConstitucional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckConstitucionalActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckConstitucional, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jCheckBox7.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox7.setText("Dolor Muscular");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jCheckPielMucosa.setBackground(new java.awt.Color(255, 255, 255));
        jCheckPielMucosa.setText("Piel y Mucosas");
        jCheckPielMucosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckPielMucosaActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckPielMucosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jCheckBox9.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox9.setText("Palpitaciones");
        jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox9ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        jCheckBox10.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox10.setText("Flatulencia");
        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jCheckBox11.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox11.setText("Dolor Articular");
        jCheckBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox11ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jCheckBox12.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox12.setText("Migraña");
        jCheckBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox12ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        jCheckORL.setBackground(new java.awt.Color(255, 255, 255));
        jCheckORL.setText("ORL/Sentidos");
        jCheckORL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckORLActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckORL, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jCheckBox14.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox14.setText("Debilidad");
        jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox14ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        jCheckBox15.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox15.setText("Inflamación");
        jCheckBox15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox15ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        jCheckBox16.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox16.setText("Síncope");
        jCheckBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox16ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jCheckBox17.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox17.setText("Estreñimiento");
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jCheckBox19.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox19.setText("Disestesia");
        jCheckBox19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox19ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, -1, -1));

        jCheckBox20.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox20.setText("Endema Msis");
        jCheckBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox20ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jCheckRespiratorio.setBackground(new java.awt.Color(255, 255, 255));
        jCheckRespiratorio.setText("Respiratorio/Disnea");
        jCheckRespiratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckRespiratorioActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckRespiratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jCheckBox27.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox27.setText("Parestesia");
        jCheckBox27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox27ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox27, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));

        jCheckBox18.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCheckBox18.setText("Genito-Urinario");
        jCheckBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox18ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jCheckBox22.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox22.setText("Anticonceptivos");
        jCheckBox22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox22ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jCheckBox23.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox23.setText("Angustia");
        jCheckBox23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox23ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        jCheckBox24.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox24.setText("Alergias");
        jCheckBox24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox24ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        jCheckBox28.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox28.setText("Otros");
        jCheckBox28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox28ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        jCheckBox25.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox25.setText("Endocrino");
        jCheckBox25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox25ActionPerformed(evt);
            }
        });
        jPanel15.add(jCheckBox25, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        jScrollPane17.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        jTextRevision.setColumns(20);
        jTextRevision.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextRevision.setLineWrap(true);
        jTextRevision.setRows(5);
        jTextRevision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextRevisionKeyTyped(evt);
            }
        });
        jScrollPane17.setViewportView(jTextRevision);

        jPanel15.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 240, 170));

        jPanel7.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 900, 190));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 880, 130));

        jLabel18.setText("Consumo de estupefacientes");
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 170, 20));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 880, 130));

        jLabel20.setText("Hábitos Sociales");
        jPanel20.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 20));

        jLabel21.setText("Hábitos Sociales");
        jPanel20.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 100, 20));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 100, 30));

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 100, 30));

        jLabel22.setText("Hábitos Sociales");
        jPanel20.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 20));

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 30));

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 100, 30));

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 100, 30));

        jLabel23.setText("Hábitos Sociales");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 100, 20));

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 100, 30));

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 100, 30));

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 880, 160));

        jComboHabitos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboHabitos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Practica deportes", "Lectura", "Montañismo", "Gimnasio", "Fiestas", "Baile", "Televisión", "Juegos de computadora", "Juegos de azar", "Ninguno de los anteriores" }));
        jPanel16.add(jComboHabitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 30));
        jPanel16.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 900, 10));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 20, 120));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 40, 120));

        jLabel31.setText("Sexualidad");
        jPanel16.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, 100, 20));

        jLabel32.setText("Consumo Alcohol");
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 100, 20));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 20, 120));

        jLabel33.setText("Consumo Tabaco");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 100, 20));

        jTConsumoDrugs.setColumns(20);
        jTConsumoDrugs.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoDrugs.setLineWrap(true);
        jTConsumoDrugs.setRows(5);
        jTConsumoDrugs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTConsumoDrugsKeyTyped(evt);
            }
        });
        jScrollPane9.setViewportView(jTConsumoDrugs);

        jPanel16.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 230, 90));

        jLabel34.setText("Tiempo libre");
        jPanel16.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 20));

        jLabel35.setText("Actividad Física");
        jPanel16.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 20));

        jComboSexualidad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboSexualidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Satisfactoria", "Insatisfactoria", "Dispaurenia/relación dolorosa", "Inapetencia/frigidez", "Urgencia sexual/ninfomania", "Abstinencia voluntaria", "Bisexualidad", "Homosexualidad", "Impotencia", "Eyaculación precoz" }));
        jPanel16.add(jComboSexualidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 190, 30));

        jLabel36.setText("Frecuencia semanal");
        jPanel16.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 160, 20));

        jLabel37.setText("Sueño");
        jPanel16.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 100, 20));

        jLabel38.setText("Hábitos Sociales");
        jPanel16.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        jComboActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Sedentario", "Aeróbicos", "Anaeróbicos  y aeróbicos", "Deportes", "Aeróbicos, anaeróbicos y deportes" }));
        jPanel16.add(jComboActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 190, 30));

        jComboFrec.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboFrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nunca", "Una vez", "Dos veces", "Tres veces", "Cuatro veces", "Cinco veces", "Seis veces", "Diariamente" }));
        jPanel16.add(jComboFrec, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 190, 30));

        jComboSueno.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboSueno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Reparador", "Interrumpido", "Insomnio", "Pesadillas" }));
        jPanel16.add(jComboSueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 190, 30));

        jTConsumoAlcohol.setColumns(20);
        jTConsumoAlcohol.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoAlcohol.setLineWrap(true);
        jTConsumoAlcohol.setRows(5);
        jTConsumoAlcohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTConsumoAlcoholKeyTyped(evt);
            }
        });
        jScrollPane15.setViewportView(jTConsumoAlcohol);

        jPanel16.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 230, 90));

        jTConsumoTabaco.setColumns(20);
        jTConsumoTabaco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoTabaco.setLineWrap(true);
        jTConsumoTabaco.setRows(5);
        jTConsumoTabaco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTConsumoTabacoKeyTyped(evt);
            }
        });
        jScrollPane16.setViewportView(jTConsumoTabaco);

        jPanel16.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 230, 90));

        jPanel7.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 900, 210));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel18.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 880, 130));

        jLabel13.setText("Menospausia edad");
        jPanel18.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(789, 0, 100, 20));

        jLabel15.setText("Embarazos");
        jPanel18.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 70, 20));

        jSpinnerMenarquia.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jPanel18.add(jSpinnerMenarquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 30));

        jSmenospausia.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jPanel18.add(jSmenospausia, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 50, 30));

        jSpartos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSpartos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpartosStateChanged(evt);
            }
        });
        jPanel18.add(jSpartos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 50, 30));

        jScesareas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jScesareas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jScesareasStateChanged(evt);
            }
        });
        jPanel18.add(jScesareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 50, 30));

        jSembarazo.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSembarazo.setEnabled(false);
        jPanel18.add(jSembarazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 50, 30));

        jLabel16.setText("Mernarquia edad");
        jPanel18.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        jComboMestruacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboMestruacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Eumenorreica", "Dismenorreica", "Oligomenorreica", "Hipermenorreica", "Polimenorreica", "Amenorreica", "Menospáusica" }));
        jPanel18.add(jComboMestruacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 100, 30));

        jScrollPane18.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        Jtextwoman.setColumns(20);
        Jtextwoman.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        Jtextwoman.setLineWrap(true);
        Jtextwoman.setRows(5);
        Jtextwoman.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JtextwomanKeyTyped(evt);
            }
        });
        jScrollPane18.setViewportView(Jtextwoman);

        jPanel18.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 870, 60));

        TxtCiclosMestru.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ciclos mestruación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        TxtCiclosMestru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtCiclosMestruKeyTyped(evt);
            }
        });
        jPanel18.add(TxtCiclosMestru, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 110, 40));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 60));

        jSabortos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSabortos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSabortosStateChanged(evt);
            }
        });
        jPanel18.add(jSabortos, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 50, 30));

        jLabel27.setText("Mestruación");
        jPanel18.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 110, 20));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 40, 60));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 10, 60));

        jLabel28.setText("Partos");
        jPanel18.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 50, 20));

        jLabel29.setText("Cesareas");
        jPanel18.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 50, 20));

        jLabel30.setText("Abortos");
        jPanel18.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 50, 20));

        FechaMestruacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Última mestruación"));
        try {
            FechaMestruacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel18.add(FechaMestruacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 120, 40));

        jPanel7.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 900, 130));

        jTabbedPane2.addTab("Revisión Funcional & hábitos |", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTsuperior.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTsuperior.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Extr. Superior"));
        jPanel8.add(TXTsuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 300, 40));

        TXTinferior.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTinferior.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Extr. Inferior"));
        jPanel8.add(TXTinferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 300, 40));

        TXTcolumna.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTcolumna.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Columna"));
        jPanel8.add(TXTcolumna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 300, 40));

        TXTAbdomen2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTAbdomen2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Abdomen"));
        jPanel8.add(TXTAbdomen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 300, 40));

        TXTcorazon.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTcorazon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Corazón"));
        jPanel8.add(TXTcorazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 300, 40));

        TXTcuello.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTcuello.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cuello"));
        jPanel8.add(TXTcuello, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 300, 40));

        TXTboca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTboca.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Boca"));
        jPanel8.add(TXTboca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 300, 40));

        TXTdientes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTdientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dientes"));
        jPanel8.add(TXTdientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 300, 40));

        TXToidos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXToidos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Oidos"));
        jPanel8.add(TXToidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 300, 40));

        TXTojos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTojos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ojos"));
        jPanel8.add(TXTojos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 300, 40));

        TXTpiel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTpiel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Piel"));
        jPanel8.add(TXTpiel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 300, 40));

        TXTtemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Temperatura"));
        TXTtemperatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTtemperaturaKeyTyped(evt);
            }
        });
        jPanel8.add(TXTtemperatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 150, 40));

        TXTtalla.setText("0");
        TXTtalla.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Estatura (cm)"));
        TXTtalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTtallaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTtallaKeyTyped(evt);
            }
        });
        jPanel8.add(TXTtalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        TXTpeso.setText("0");
        TXTpeso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Peso"));
        TXTpeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTpesoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTpesoKeyTyped(evt);
            }
        });
        jPanel8.add(TXTpeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 150, 40));

        TXTpulso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pulso"));
        TXTpulso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTpulsoKeyTyped(evt);
            }
        });
        jPanel8.add(TXTpulso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 150, 40));

        TXTfr.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "F.R"));
        TXTfr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTfrKeyTyped(evt);
            }
        });
        jPanel8.add(TXTfr, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 80, 40));

        TXTpesoIdeal.setEditable(false);
        TXTpesoIdeal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Peso ideal"));
        jPanel8.add(TXTpesoIdeal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 80, 40));

        TXTpesoAdic.setEditable(false);
        TXTpesoAdic.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dif. Peso"));
        TXTpesoAdic.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(TXTpesoAdic, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 110, 40));

        TXTrespiracion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo respiracion"));
        jPanel8.add(TXTrespiracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 220, 40));

        TXTimc.setEditable(false);
        TXTimc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "I.M.C"));
        jPanel8.add(TXTimc, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 110, 40));

        TXTlinfaticos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTlinfaticos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Linfáticos"));
        jPanel8.add(TXTlinfaticos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 300, 40));

        TXTgenitales.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTgenitales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Genitales"));
        jPanel8.add(TXTgenitales, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 300, 40));

        TXTrectal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTrectal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ano-Rectal"));
        jPanel8.add(TXTrectal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 300, 40));

        TXToftamoscopia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXToftamoscopia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Oftamoscopia"));
        jPanel8.add(TXToftamoscopia, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 300, 40));

        TXTnariz.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTnariz.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nariz"));
        jPanel8.add(TXTnariz, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 300, 40));

        TXTtorax.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTtorax.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Toráx"));
        jPanel8.add(TXTtorax, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 300, 40));

        TXTmamas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTmamas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Mamas"));
        jPanel8.add(TXTmamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 300, 40));

        TXTpulmones.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTpulmones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pulmones"));
        jPanel8.add(TXTpulmones, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 300, 40));

        TXThernias.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXThernias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hernias"));
        jPanel8.add(TXThernias, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 300, 40));

        TXTginecologo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTginecologo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ginecologo"));
        jPanel8.add(TXTginecologo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 300, 40));

        TXTprostata.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTprostata.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Prostata"));
        jPanel8.add(TXTprostata, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 300, 40));

        TXTneurologico.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTneurologico.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Neurológico"));
        jPanel8.add(TXTneurologico, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 300, 40));

        TXTvenas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTvenas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Venas"));
        jPanel8.add(TXTvenas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 300, 40));

        TXTarterias.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTarterias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Arterias"));
        jPanel8.add(TXTarterias, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 300, 40));

        TXTtension.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tension Arterial"));
        jPanel8.add(TXTtension, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 170, 40));

        TXTaspecto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Aspecto Contextura"));
        TXTaspecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTaspectoKeyTyped(evt);
            }
        });
        jPanel8.add(TXTaspecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 170, 40));

        TXTabdominal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Abdominal (cm)"));
        TXTabdominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTabdominalKeyTyped(evt);
            }
        });
        jPanel8.add(TXTabdominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 130, 40));

        TXTcadera.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cadera (cm)"));
        jPanel8.add(TXTcadera, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 130, 40));

        jTabbedPane2.addTab("Examen Físico |", jPanel8);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAdic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Examen", "Descripción"
            }
        ));
        jTableAdic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAdicMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableAdicMouseEntered(evt);
            }
        });
        jScrollPane24.setViewportView(jTableAdic);

        jPanel4.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 860, 310));

        jButton18.setText("Cancelar");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton18.setContentAreaFilled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 180, 70, 40));

        jScrollPane31.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        jTextObservAdic.setColumns(20);
        jTextObservAdic.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextObservAdic.setLineWrap(true);
        jTextObservAdic.setRows(5);
        jTextObservAdic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextObservAdicMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextObservAdicMouseReleased(evt);
            }
        });
        jTextObservAdic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextObservAdicKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextObservAdicKeyTyped(evt);
            }
        });
        jScrollPane31.setViewportView(jTextObservAdic);

        jPanel4.add(jScrollPane31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 760, 150));

        jButton21.setText("Agregar");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton21.setContentAreaFilled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 70, 40));

        jAdcDelete.setText("Retirar");
        jAdcDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAdcDelete.setContentAreaFilled(false);
        jAdcDelete.setEnabled(false);
        jAdcDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdcDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(jAdcDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 70, 40));

        jTextExamenAdic.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Examen"));
        jPanel4.add(jTextExamenAdic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 760, 40));

        jAdcModif.setText("Modificar");
        jAdcModif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAdcModif.setContentAreaFilled(false);
        jAdcModif.setEnabled(false);
        jAdcModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdcModifActionPerformed(evt);
            }
        });
        jPanel4.add(jAdcModif, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 70, 40));

        jTabbedPane2.addTab("Examen Complementario |", jPanel4);

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
        jTextRecomendacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextRecomendacionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextRecomendacionMouseReleased(evt);
            }
        });
        jTextRecomendacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextRecomendacionKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(jTextRecomendacion);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 850, 200));

        jTextConclusion.setColumns(20);
        jTextConclusion.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextConclusion.setLineWrap(true);
        jTextConclusion.setRows(5);
        jTextConclusion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Conclusión"));
        jTextConclusion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextConclusionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextConclusionMouseReleased(evt);
            }
        });
        jTextConclusion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextConclusionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextConclusionKeyTyped(evt);
            }
        });
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

        JPanelAnteriores.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 880, 560));

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


/**
if (!fechaActual.equals(fechaNueva) || filaPaciente != -1) {
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
}
*/

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
     jTextArea15.setText("");

}


    }//GEN-LAST:event_BtnModifProcActionPerformed

    
    
    
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

                   
             if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar la informacion?",
            "Informacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
        limpiarTEAdd();
        
             }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void BtnImprimirHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirHistoriaActionPerformed
    
        
    if(jTextMotivoConsulta.getText().equals("")){
      JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }  
      else{
      
          
       try{
           
           
           
       if(estado.equals("Completado")){
       if (JOptionPane.showConfirmDialog(rootPane, "Ya completó esta historia, ¿desea sobre-escribirla?",
            "Reescribir historia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
           
       pdfHistorias();
       guardarHistoria();
       ActualizarHistoriaBd();
       imprimirReporte();

      }}  
           
       else{
       pdfHistorias();
       guardarHistoria();
       ActualizarHistoriaBd();
       imprimirReporte();
       
       }
  
  
       
       
       }catch(Exception e){ System.out.println(e);
       System.out.println(e); JOptionPane.showMessageDialog(this, "ERROR AL REALIZAR ESTE PROCESO, INTENTE DE NUEVO. DE CONTINUAR EL ERROR CONTACTE A SOPORTE", "ERROR", JOptionPane.ERROR_MESSAGE);}   
  

      }
    
    
    }//GEN-LAST:event_BtnImprimirHistoriaActionPerformed

    
    
    
    
    
    
    
    
    
    
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
int fila = jTableAntcPers.getSelectedRow();

if (fila == -1 || TextAntPersonal.equals("") || jTextAntPersonales.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ENFERMEDAD", "CAMPOS", JOptionPane.ERROR_MESSAGE);
} else {
    int row = jTableAntpersonal.getRowCount();
    Set<Object> contentSet = new HashSet<>();

    for (int i = 0; i < row; i++) {
        contentSet.add(jTableAntpersonal.getValueAt(i, 1));
    }

    Object value_to_find = TextAntPersonal;
    boolean exist = contentSet.contains(value_to_find);

    if (exist) {
        JOptionPane.showMessageDialog(null, "NO PUEDEN HABER ENFERMEDADES DUPLICADAS", "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
    } else if (JTablePacientes.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "NO TIENE PACIENTES ASIGNADOS", "PACIENTES", JOptionPane.ERROR_MESSAGE);
    } else {
        AgregarAntc2();
        
         if (usingData) {
            limpiarShowAntc2();
            ShowAntc2(idtablaHistorias);
        } else {
            limpiarShowAntc2();
            ShowAntc2(idAsignar);
        }
         
   
    }
}

    
    
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jRetirarAntc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRetirarAntc2ActionPerformed
        int fila = jTableAntpersonal.getSelectedRow();

       if (fila == -1) {
        
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ENFERMEDAD", "CAMPOS", JOptionPane.ERROR_MESSAGE);
        }
        
        else{
           
         
           
           
             if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea retirar este antecedente?",
            "Antecedentes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
             int idRetirarAntc;
       if (!usingData) {
        idRetirarAntc = idAsignar;
    } else {
       idRetirarAntc = idtablaHistorias;
    }    
              
           
                
   jRetirarAntc2.setEnabled(false);
   EliminarAntc2();     
   limpiarShowAntc2();
   ShowAntc2(idRetirarAntc);    
   jTextAntPersonales.setText("");
                }
               
               
           }   
           
           
           
                  

    }//GEN-LAST:event_jRetirarAntc2ActionPerformed

    private void jBtnRetirarAntcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRetirarAntcActionPerformed

        
        int fila = jTableAF2.getSelectedRow();

       if (fila == -1) {
        
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ENFERMEDAD", "CAMPOS", JOptionPane.ERROR_MESSAGE);
        }
        
        
       
       
       else{
           
           
               if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea retirar este antecedente?",
            "Antecedentes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
            int idRetirarAntc;
       if (!usingData) {
        idRetirarAntc = idAsignar;
    } else {
       idRetirarAntc = idtablaHistorias;
    }    
            
           
           
   jBtnRetirarAntc.setEnabled(false);
   EliminarAntc();     
   limpiarShowAntc1();
   ShowAntc1(idRetirarAntc);    
   JtextAntc1.setText("");
                }
               
               
           }   
          
 
    }//GEN-LAST:event_jBtnRetirarAntcActionPerformed

    
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

    private void jAButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAButton30ActionPerformed
    int fila = jTableAntecedentesF.getSelectedRow();

if (fila == -1 || EnfermedadAF.equals("") || JtextAntc1.getText().equals("")) {
    JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ENFERMEDAD", "CAMPOS", JOptionPane.ERROR_MESSAGE);
} else {
    int row = jTableAF2.getRowCount();
    Set<Object> contentSet = new HashSet<>();

    for (int i = 0; i < row; i++) {
        contentSet.add(jTableAF2.getValueAt(i, 1));
    }

    Object value_to_find = EnfermedadAF;
    boolean exist = contentSet.contains(value_to_find);

    if (exist) {
        JOptionPane.showMessageDialog(null, "NO PUEDEN HABER ENFERMEDADES DUPLICADAS", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
    } else if (JTablePacientes.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "NO TIENE PACIENTES ASIGNADOS", "PACIENTES", JOptionPane.ERROR_MESSAGE);
    } else {
           AgregarAntc1();
        if (usingData) {
            limpiarShowAntc1();
            ShowAntc1(idtablaHistorias);
        } else {
            limpiarShowAntc1();
            ShowAntc1(idAsignar);
        }
     
    }
}
    
    }//GEN-LAST:event_jAButton30ActionPerformed
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
     llenarResultado();    
     limpiarTAntecedentes();
     listarAntecedentes();
     esPrimeraSeleccion = false; 
    // System.out.println("entre una vez");
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
        cleanHistorias();
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
    
    BtnImprimirHistoria.setEnabled(true);
    BtnGuardarHistoria.setEnabled(true);
    BtnVisualizar.setEnabled(true);

    SearchIdPatient();

    

    limpiarTProcedimientos();
    limpiarTProcedimientos2();
    ListarProcedimientos();
    ListarProcedimientos2();

            
    limpiarShowAntc1();
    ShowAntc1(idAsignar);

    limpiarShowAntc2();
    ShowAntc2(idAsignar);

    limpiarEAdc();
    ShowExamAdic(idAsignar);
    
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
    jTabbedPane2.setSelectedIndex(0);
    
    
    }
    
    
    
    
    
    
    private void TxtbusquedaAntcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtbusquedaAntcKeyReleased
       SearchAntecedentesF();
    }//GEN-LAST:event_TxtbusquedaAntcKeyReleased

    private void jRadioAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAFActionPerformed
        this.jRadioAF.setSelected(true);
        jRadioAPP.setSelected(false);
     
        jTabbedPane3.setSelectedIndex(0);
        
        
        limpiarTAntecedentes();
        listarAntecedentes();
        jRadioAF.setSelected(true);
        jTableAntecedentesF.requestFocusInWindow();
    }//GEN-LAST:event_jRadioAFActionPerformed

    private void jRadioAPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAPPActionPerformed
        jRadioAF.setSelected(false);
        this.jRadioAPP.setSelected(true);
        limpiarTAntecedentesPerso();
        listarAntecedentespersonales();
        jTabbedPane3.setSelectedIndex(1);
        jTableAntcPers.requestFocusInWindow();
        
          
        
        
        
        
        
    }//GEN-LAST:event_jRadioAPPActionPerformed

    
    
    String EnfermedadAF="";
    private void jTableAntecedentesFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntecedentesFMouseClicked
       jTableAntcPers.requestFocusInWindow();   
       int fila = jTableAntecedentesF.getSelectedRow();
        if (fila == -1) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);

        }

        else{ EnfermedadAF=(jTableAntecedentesF.getValueAt(fila, 1).toString());}
    
    }//GEN-LAST:event_jTableAntecedentesFMouseClicked

    
    
    String TextAntPersonal="";
    private void jTableAntcPersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntcPersMouseClicked
               jTableAntcPers.requestFocusInWindow(); 
        
        int fila = jTableAntcPers.getSelectedRow();
        if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);
         
        }

        else{ TextAntPersonal=(jTableAntcPers.getValueAt(fila, 1).toString());}
    
    }//GEN-LAST:event_jTableAntcPersMouseClicked

    private void TxtBusAntcPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusAntcPKeyReleased
 SearchAntecedentesP();
    }//GEN-LAST:event_TxtBusAntcPKeyReleased

    private void jCheckConstitucionalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckConstitucionalStateChanged

    }//GEN-LAST:event_jCheckConstitucionalStateChanged


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
                        
                
         
         
         
    private void jCheckConstitucionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckConstitucionalActionPerformed
         if(jCheckConstitucional.isSelected()){
         
         constitucional="  Constitucional ";
         }
         else{constitucional="";}
   
    }//GEN-LAST:event_jCheckConstitucionalActionPerformed

    
    
    
    private void jCheckPielMucosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckPielMucosaActionPerformed

     if(jCheckPielMucosa.isSelected()){
         
         piel="  Piel y mucosa ";
         }
         else{piel="";}
    }//GEN-LAST:event_jCheckPielMucosaActionPerformed

    private void jCheckORLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckORLActionPerformed
  if(jCheckORL.isSelected()){
         
         orl="  ORL/sentidos ";
         }
         else{orl="";}
    }//GEN-LAST:event_jCheckORLActionPerformed

    private void jCheckRespiratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckRespiratorioActionPerformed
  if(jCheckRespiratorio.isSelected()){
         
         respiratorio="  Respiratorio/disnea ";
         }
         else{respiratorio="";}
    }//GEN-LAST:event_jCheckRespiratorioActionPerformed

    private void jCheckAnginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckAnginaActionPerformed
           if(jCheckAngina.isSelected()){
         
         Angina="  Angina ";
         }
         else{Angina="";}
    }//GEN-LAST:event_jCheckAnginaActionPerformed

    private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox9ActionPerformed
                 if(jCheckBox9.isSelected()){
         
         Palpitaciones="  Palpitaciones ";
         }
         else{Palpitaciones="";}
    }//GEN-LAST:event_jCheckBox9ActionPerformed

    private void jCheckBox16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox16ActionPerformed
                      if(jCheckBox16.isSelected()){
         
         Síncope="  Síncope ";
         }
         else{Síncope="";}
    }//GEN-LAST:event_jCheckBox16ActionPerformed

    private void jCheckBox20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox20ActionPerformed
                         if(jCheckBox20.isSelected()){
         
        EndemaMsis="  Endema Msis ";
         }
         else{EndemaMsis="";}
    }//GEN-LAST:event_jCheckBox20ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
            
        if(jCheckBox6.isSelected()){
         
       DolorAbdominal="  Dolor Abdominal ";
         }
         else{DolorAbdominal="";}
    }//GEN-LAST:event_jCheckBox6ActionPerformed
                                                               

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
              if(jCheckBox10.isSelected()){
         
       Flatulencia="  Flatulencia ";
         }
         else{Flatulencia="";}
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed
       if(jCheckBox17.isSelected()){
         
       Estreñimiento="  Estreñimiento ";
         }
         else{Estreñimiento="";}
    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
       if(jCheckBox7.isSelected()){
         
       DolorMuscular="  Dolor Muscular ";
         }
         else{DolorMuscular="";}
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox11ActionPerformed
       if(jCheckBox11.isSelected()){
         
       DolorArticular="  Dolor Muscular ";
         }
         else{DolorArticular="";}
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    private void jCheckBox15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox15ActionPerformed
       if(jCheckBox15.isSelected()){
         
       Inflamación="  Inflamación ";
         }
         else{Inflamación="";}
    }//GEN-LAST:event_jCheckBox15ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
       if(jCheckBox1.isSelected()){
         
       Cefalea="  Cefalea ";
         }
         else{Cefalea="";}
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox12ActionPerformed
       if(jCheckBox12.isSelected()){
         
       Migraña="  Migraña ";
         }
         else{Migraña="";}
    }//GEN-LAST:event_jCheckBox12ActionPerformed

    private void jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox14ActionPerformed
       if(jCheckBox14.isSelected()){
         
       Debilidad="  Debilidad ";
         }
         else{Debilidad="";}
    }//GEN-LAST:event_jCheckBox14ActionPerformed

    private void jCheckBox19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox19ActionPerformed
        if(jCheckBox19.isSelected()){
         
       Disestesia="  Disestesia ";
         }
         else{Disestesia="";}
    }//GEN-LAST:event_jCheckBox19ActionPerformed

    private void jCheckBox27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox27ActionPerformed
            if(jCheckBox27.isSelected()){
         
       Parestesia="  Parestesia ";
         }
         else{Parestesia="";}
    }//GEN-LAST:event_jCheckBox27ActionPerformed

    private void jCheckBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox18ActionPerformed
               if(jCheckBox18.isSelected()){
         
       Genito="  Genito-Urinario ";
         }
         else{Genito="";}
    }//GEN-LAST:event_jCheckBox18ActionPerformed

    private void jCheckBox25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox25ActionPerformed
        if(jCheckBox25.isSelected()){
         
       Endocrino="  Endocrino ";
         }
         else{Endocrino="";}
    }//GEN-LAST:event_jCheckBox25ActionPerformed

    private void jCheckBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox22ActionPerformed
            if(jCheckBox22.isSelected()){
         
       Anticonceptivos="  Anticonceptivos ";
         }
         else{Anticonceptivos="";}
    }//GEN-LAST:event_jCheckBox22ActionPerformed

    private void jCheckBox23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox23ActionPerformed
             if(jCheckBox23.isSelected()){
         
       Angustia="  Angustia ";
         }
         else{Angustia="";}
    }//GEN-LAST:event_jCheckBox23ActionPerformed

    private void jCheckBox24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox24ActionPerformed
             if(jCheckBox24.isSelected()){
         
       Alergias="  Alergias ";
         }
         else{Alergias="";}
    }//GEN-LAST:event_jCheckBox24ActionPerformed

    private void jCheckBox28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox28ActionPerformed
                  if(jCheckBox28.isSelected()){
         
       Otros="  Otros ";
         }
         else{Otros="";}
    }//GEN-LAST:event_jCheckBox28ActionPerformed

    
    int partos, cesareas, abortos, embarazos=0;
    private void jSpartosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpartosStateChanged
       
        
         partos= (Integer) this.jSpartos.getValue();
         cesareas= (Integer) this.jScesareas.getValue();
         abortos= (Integer) this.jSabortos.getValue();
         
         embarazos=partos+cesareas+abortos;
         jSembarazo.setValue(embarazos);
        
    }//GEN-LAST:event_jSpartosStateChanged
  

    private void jScesareasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jScesareasStateChanged
       partos= (Integer) this.jSpartos.getValue();
         cesareas= (Integer) this.jScesareas.getValue();
         abortos= (Integer) this.jSabortos.getValue();
         
         embarazos=partos+cesareas+abortos;
         jSembarazo.setValue(embarazos);
    }//GEN-LAST:event_jScesareasStateChanged

    private void jSabortosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSabortosStateChanged
          partos= (Integer) this.jSpartos.getValue();
         cesareas= (Integer) this.jScesareas.getValue();
         abortos= (Integer) this.jSabortos.getValue();
         
         embarazos=partos+cesareas+abortos;
         jSembarazo.setValue(embarazos);
    }//GEN-LAST:event_jSabortosStateChanged

    
    

    private void TXTtallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTtallaKeyReleased
       

       

       
        if (!TXTtalla.getText().isEmpty() && !sexo.equals("")) {
              calcularPeso();
       }
        
    }//GEN-LAST:event_TXTtallaKeyReleased

    private void TXTpesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTpesoKeyReleased
         
    
       
       

       
        if (!TXTpeso.getText().isEmpty() && !sexo.equals("")) {
        calcularPeso();
        
        
        }
    }//GEN-LAST:event_TXTpesoKeyReleased

    
    
 
    double altura=0, pesoactual=0, pesoideal=0, pesoextra=0, imc=0,pesoActual=0, pesoIdeal=0, pesoExtra=0;
    
     
    public void calcularPeso(){
        
       DecimalFormat formato = new DecimalFormat("##.##");
       altura=0;
       pesoactual=0;
       pesoideal=0;
       pesoextra=0;
       imc=0;
    
        altura = Double.parseDouble(TXTtalla.getText());
        pesoactual = Double.parseDouble(TXTpeso.getText());
        

 double alturaMetros = altura / 100;           
 imc = pesoactual / (alturaMetros * alturaMetros);           


 if(sexo.equals("Masculino")){ pesoideal = (altura - 100) - ((altura - 150) / 4);}
 else  if(sexo.equals("Femenino")){ pesoideal = (altura - 100) - ((altura - 150) / 2);}
 
 
 pesoextra = pesoactual - pesoideal;
        
        
        
         TXTimc.setText(String.valueOf(formato.format(imc)));
         TXTpesoIdeal.setText(String.valueOf(formato.format(pesoideal)));
         TXTpesoAdic.setText(String.valueOf(formato.format(pesoextra)));
    
    }
    
    
    
    
    
    
    
    private void TXTtallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTtallaKeyTyped
  char car = evt.getKeyChar();

// Permitir dígitos y punto decimal
if (Character.isDigit(car) || car == '.') {
    String caracteres = TXTtalla.getText();

    // Limitar a 4 caracteres
    if (caracteres.length() == 4 && !Character.isDigit(car)) {
        evt.consume(); // Consumir el evento si se intenta agregar más caracteres después del límite
    }
    // Permitir la entrada
    return;
}

// Permitir teclas de control (como retroceso)
if (Character.isISOControl(car)) {
    return;
}

// Consumir el evento si el carácter no es válido
evt.consume();
    }//GEN-LAST:event_TXTtallaKeyTyped

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

    int row = jTableAdic.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = jTableAdic.getValueAt(i, 1);
    }
    Object value_to_find= jTextExamenAdic.getText();
    boolean exist = Arrays.asList(content).contains(value_to_find);
    
    
    
    
    
    
    
    
    if (exist){
        JOptionPane.showMessageDialog(null, "NO PUEDEN HABER ENFERMEDADES DUPLICADAS", "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
    }
         
    
    
      else if (jTextExamenAdic.getText().equals("") || jTextObservAdic.getText().equals("") ){ JOptionPane.showMessageDialog(null, "DEBE COMPLETAR LOS CAMPOS", "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);}
    
      
     else if (JTablePacientes.getRowCount() == 0) {
      JOptionPane.showMessageDialog(this, "NO TIENE PACIENTES ASIGNADOS", "PACIENTES", JOptionPane.ERROR_MESSAGE);
      }  
      

      
       else 
       {
  
           
   
      if (usingData) {   
      AgregarExamenAdc();
      limpiarEAdc();     
      ShowExamAdic(idtablaHistorias);
      } 
      
           
           
           
      else{   
      // addexamenextra();
       AgregarExamenAdc();
       limpiarEAdc();     
       ShowExamAdic(idAsignar);
      }

       
  
       }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jAdcDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdcDeleteActionPerformed
  
        
        
         int fila = jTableAdic.getSelectedRow();

       if (fila == -1) {
        
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ENFERMEDAD", "CAMPOS", JOptionPane.ERROR_MESSAGE);
        }
        
        else{
                
    if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente desea retirar este estudio?",
            "Estudios", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {     
           
           
         
        
    int idValidarAdc;
       if (!usingData) {
        idValidarAdc = idAsignar;
    } else {
        idValidarAdc = idtablaHistorias;
    }    
         
        
        
   jAdcDelete.setEnabled(false);
   EliminarEadc();   
   limpiarEAdc();
   ShowExamAdic(idValidarAdc);

  
   jTextObservAdic.setText("");
   jTextExamenAdic.setText("");
   }  
                }
        
        
 


    }//GEN-LAST:event_jAdcDeleteActionPerformed

    

    
    
    
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
  
  else if(jTextMotivoConsulta.getText().equals("")){
  JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }   
  
  else{
  
  if(estado.equals("Completado")){
  if (JOptionPane.showConfirmDialog(rootPane, "Ya completó esta historia, ¿desea sobre-escribirla?",
            "Reescribir historia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
  pdfHistorias(); 
  guardarHistoria();
  ActualizarHistoriaBd();
  limpiarTVisualizar();
  visualizar_PdfVOHistorias(jTableVisualizarC);
  limpiarTPacientes(); 
  ListarPacientes();
  JOptionPane.showMessageDialog(this, "HISTORIA CLINICA GUARDADA", "HISTORIA CLINICA",1);
    
  }}  
    
    
  else{    
  pdfHistorias(); 
  guardarHistoria();
  ActualizarHistoriaBd();
  limpiarTVisualizar();
  visualizar_PdfVOHistorias(jTableVisualizarC);
  limpiarTPacientes(); 
  ListarPacientes();
  JOptionPane.showMessageDialog(this, "HISTORIA CLINICA GUARDADA", "HISTORIA CLINICA",1);


  }}
  
  
  
  
  }catch(Exception e){
  System.out.println(e); JOptionPane.showMessageDialog(this, "ERROR AL REALIZAR ESTE PROCESO, INTENTE DE NUEVO. DE CONTINUAR EL ERROR CONTACTE A SOPORTE", "ERROR", JOptionPane.ERROR_MESSAGE);}
  
  
  
  
  
  
    }//GEN-LAST:event_BtnGuardarHistoriaActionPerformed

    private void jUtilizarHistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUtilizarHistoActionPerformed

 callHistoryinfo();
 
 
    }//GEN-LAST:event_jUtilizarHistoActionPerformed

    
    
    int antcid1;
    String ShowAntcObs="",ShowName1;
    private void jTableAF2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAF2MouseClicked

                                            

        
        jTableAF2.requestFocusInWindow();   
        int fila = jTableAF2.getSelectedRow();
        if (fila == -1) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);

        }

        else{ antcid1= (int) (jTableAF2.getValueAt(fila, 0));
            ShowName1= (jTableAF2.getValueAt(fila, 1).toString());
            ShowAntcObs= (jTableAF2.getValueAt(fila, 2).toString());
             jBtnRetirarAntc.setEnabled(true);
             BtnModifAnt.setEnabled(true);
        
             
             JtextAntc1.setText(ShowAntcObs);
        
        }

    }//GEN-LAST:event_jTableAF2MouseClicked

    
    
    
    private void BtnImprimirHistoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirHistoria2ActionPerformed
           if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar la historia clinica?",
            "Cancelar historia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
               cleanAll();
               usingData=false;
           }
    }//GEN-LAST:event_BtnImprimirHistoria2ActionPerformed

    private void TxtbusquedaAntcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtbusquedaAntcFocusLost
        jTableAntcPers.requestFocusInWindow();  
    }//GEN-LAST:event_TxtbusquedaAntcFocusLost

    
    
    
    public void cleanAll(){
    
        cleanHistorias();
        BtnImprimirHistoria.setEnabled(false); 
        limpiarTPacientes(); 
        ListarPacientes();
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        jLabelPaciente.setText("");
        JTablePacientes.requestFocus();
       
        limpiarTVisualizar();
  
    
    }
    
    
    
    
    
    private void BtnModifAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifAntActionPerformed

        
        
   if (!usingData) {
        idValidado = idAsignar;
    } else {
        idValidado = idtablaHistorias;
    }    
        
        
        
   BtnModifAnt.setEnabled(false);
   modificarShowAntc1();
   limpiarShowAntc1();
   ShowAntc1(idValidado);  
     
    }//GEN-LAST:event_BtnModifAntActionPerformed

    private void BtnModifAnt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifAnt2ActionPerformed
   BtnModifAnt2.setEnabled(false);
   modificarShowAntc2();
   limpiarShowAntc2();
   ShowAntc2(idAsignar);
    }//GEN-LAST:event_BtnModifAnt2ActionPerformed

    
    
    int antcid2;
    String ShowAntcObs2="",ShowName2;
    
    
    private void jTableAntpersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntpersonalMouseClicked
      jTableAntpersonal.requestFocusInWindow();   
        int fila = jTableAntpersonal.getSelectedRow();
        if (fila == -1) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);

        }

        else{ antcid2= (int) (jTableAntpersonal.getValueAt(fila, 0));
            ShowName2= (jTableAntpersonal.getValueAt(fila, 1).toString());
            ShowAntcObs2= (jTableAntpersonal.getValueAt(fila, 2).toString());
            jRetirarAntc2.setEnabled(true);
            BtnModifAnt2.setEnabled(true);
        
             
             jTextAntPersonales.setText(ShowAntcObs2);
        
        }
    }//GEN-LAST:event_jTableAntpersonalMouseClicked

    private void jAdcModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdcModifActionPerformed

     int idAdicionalM;
   if (!usingData) {
        idAdicionalM = idAsignar;
    } else {
        idAdicionalM = idtablaHistorias;
    }    
        


        jAdcModif.setEnabled(false);
        modificarEadc();
        limpiarEAdc();     
        ShowExamAdic(idAdicionalM);
        
        jTextObservAdic.setText("");
       jTextExamenAdic.setText("");
    }//GEN-LAST:event_jAdcModifActionPerformed

    
    
    int idEadc;
    String nomEadc, descripEadc;
    private void jTableAdicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAdicMouseClicked
   
        
           jTableAdic.requestFocusInWindow();   
        int fila = jTableAdic.getSelectedRow();
        if (fila == -1) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);

        }

        else{ idEadc= (int) (jTableAdic.getValueAt(fila, 0));
            nomEadc= (jTableAdic.getValueAt(fila, 1).toString());
            descripEadc= (jTableAdic.getValueAt(fila, 2).toString());
            jAdcModif.setEnabled(true);
            jAdcDelete.setEnabled(true);
        
             jTextExamenAdic.setText(nomEadc);
             jTextObservAdic.setText(descripEadc);
        
        }
        
        
        
        
   
    }//GEN-LAST:event_jTableAdicMouseClicked

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

        if(jTextMotivoConsulta.getText().equals("")){
            JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);

        }
        else{

            try{

                pdfHistorias();
                //    JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
                Desktop.getDesktop().open(fileHistoria);

            }catch(Exception e){ System.out.println(e);}

        }
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

    private void jTextMotivoConsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaKeyTyped
       va.longitud(jTextMotivoConsulta.getText(), 3100, evt);
    }//GEN-LAST:event_jTextMotivoConsultaKeyTyped

    private void jTextMotivoConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaKeyReleased
         String text = jTextMotivoConsulta.getText();
    int maxLength = 3100;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
        jTextMotivoConsulta.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextMotivoConsultaKeyReleased

    private void jTableAntpersonalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntpersonalMouseEntered
       jTableAntpersonal.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntpersonalMouseEntered

    private void jTableAntcPersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntcPersMouseEntered
       jTableAntcPers.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntcPersMouseEntered

    private void jTableAntecedentesFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntecedentesFMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableAntecedentesFMouseExited

    private void jTableAntecedentesFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntecedentesFMouseEntered
         jTableAntecedentesF.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntecedentesFMouseEntered

    private void jTableAF2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAF2MouseEntered
         jTableAF2.requestFocusInWindow();
    }//GEN-LAST:event_jTableAF2MouseEntered

    private void jTableAdicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAdicMouseEntered
         jTableAdic.requestFocusInWindow();
    }//GEN-LAST:event_jTableAdicMouseEntered

    private void jTextMotivoConsultaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaMouseReleased
    checkPopup(evt, jTextMotivoConsulta);
    }//GEN-LAST:event_jTextMotivoConsultaMouseReleased

    private void jTextMotivoConsultaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaMousePressed
   checkPopup(evt, jTextMotivoConsulta);
    }//GEN-LAST:event_jTextMotivoConsultaMousePressed

    private void jTextMotivoConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaMouseClicked
     checkPopup(evt, jTextMotivoConsulta);
    }//GEN-LAST:event_jTextMotivoConsultaMouseClicked

    private void jTextArea15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea15MousePressed
        checkPopup(evt, jTextArea15);
    }//GEN-LAST:event_jTextArea15MousePressed

    private void jTextArea15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea15MouseReleased
checkPopup(evt, jTextArea15);
    }//GEN-LAST:event_jTextArea15MouseReleased

    private void JtextAntc1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtextAntc1MousePressed
  checkPopup(evt, JtextAntc1);
    }//GEN-LAST:event_JtextAntc1MousePressed

    private void JtextAntc1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtextAntc1MouseReleased
    checkPopup(evt, JtextAntc1);
    }//GEN-LAST:event_JtextAntc1MouseReleased

    private void jTextAntPersonalesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAntPersonalesMousePressed
   checkPopup(evt, jTextAntPersonales);
    }//GEN-LAST:event_jTextAntPersonalesMousePressed

    private void jTextAntPersonalesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAntPersonalesMouseReleased
       checkPopup(evt, jTextAntPersonales);
    }//GEN-LAST:event_jTextAntPersonalesMouseReleased

    private void jTextConclusionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextConclusionMousePressed
    checkPopup(evt, jTextConclusion);
    }//GEN-LAST:event_jTextConclusionMousePressed

    private void jTextConclusionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextConclusionMouseReleased
checkPopup(evt, jTextConclusion);
    }//GEN-LAST:event_jTextConclusionMouseReleased

    private void jTextRecomendacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextRecomendacionMousePressed
     checkPopup(evt, jTextRecomendacion);
    }//GEN-LAST:event_jTextRecomendacionMousePressed

    private void jTextRecomendacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextRecomendacionMouseReleased
        checkPopup(evt, jTextRecomendacion);
    }//GEN-LAST:event_jTextRecomendacionMouseReleased

    private void jTextArea15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea15KeyTyped
       va.longitud(jTextArea15.getText(), 1500, evt);
    }//GEN-LAST:event_jTextArea15KeyTyped

    private void jTextArea15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea15KeyReleased
           String text = jTextArea15.getText();
    int maxLength = 1500;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
        jTextArea15.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextArea15KeyReleased

    private void jTextAntPersonalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAntPersonalesKeyTyped
       va.longitud(jTextAntPersonales.getText(), 1500, evt);
    }//GEN-LAST:event_jTextAntPersonalesKeyTyped

    private void JtextAntc1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextAntc1KeyTyped
      va.longitud(JtextAntc1.getText(), 1500, evt);
    }//GEN-LAST:event_JtextAntc1KeyTyped

    private void jTextRevisionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextRevisionKeyTyped
      va.longitud(jTextRevision.getText(), 1500, evt);
    }//GEN-LAST:event_jTextRevisionKeyTyped

    private void JtextwomanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextwomanKeyTyped
    va.longitud(Jtextwoman.getText(), 1500, evt);
    }//GEN-LAST:event_JtextwomanKeyTyped

    private void jTConsumoAlcoholKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTConsumoAlcoholKeyTyped
       va.longitud(jTConsumoAlcohol.getText(), 1500, evt);
    }//GEN-LAST:event_jTConsumoAlcoholKeyTyped

    private void jTConsumoTabacoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTConsumoTabacoKeyTyped
      va.longitud(jTConsumoTabaco.getText(), 1500, evt);
    }//GEN-LAST:event_jTConsumoTabacoKeyTyped

    private void jTConsumoDrugsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTConsumoDrugsKeyTyped
        va.longitud(jTConsumoDrugs.getText(), 1500, evt);
    }//GEN-LAST:event_jTConsumoDrugsKeyTyped

    private void jTextObservAdicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextObservAdicKeyTyped
        va.longitud(jTextObservAdic.getText(), 3100, evt);
    }//GEN-LAST:event_jTextObservAdicKeyTyped

    private void jTextObservAdicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextObservAdicKeyReleased
             String text = jTextObservAdic.getText();
    int maxLength = 3100;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
        jTextObservAdic.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextObservAdicKeyReleased

    private void jTextConclusionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConclusionKeyReleased
              String text = jTextConclusion.getText();
    int maxLength = 3100;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
        jTextConclusion.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_jTextConclusionKeyReleased

    private void jTextConclusionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConclusionKeyTyped
           va.longitud(jTextConclusion.getText(), 3100, evt);
    }//GEN-LAST:event_jTextConclusionKeyTyped

    private void jTextRecomendacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextRecomendacionKeyTyped
   va.longitud(jTextRecomendacion.getText(), 3100, evt);
    }//GEN-LAST:event_jTextRecomendacionKeyTyped

    private void TxtbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtbusquedaKeyTyped
          va.longitud(Txtbusqueda.getText(), 100, evt);
    }//GEN-LAST:event_TxtbusquedaKeyTyped

    private void TxtbusquedaAntcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtbusquedaAntcKeyTyped
        va.longitud(TxtbusquedaAntc.getText(), 150, evt);
    }//GEN-LAST:event_TxtbusquedaAntcKeyTyped

    private void TxtCiclosMestruKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCiclosMestruKeyTyped
            va.longitud(TxtCiclosMestru.getText(), 150, evt);
    }//GEN-LAST:event_TxtCiclosMestruKeyTyped

    private void TXTpesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTpesoKeyTyped
      char car = evt.getKeyChar();

// Permitir dígitos y punto decimal
if (Character.isDigit(car) || car == '.') {
    String caracteres = TXTpeso.getText();

    // Limitar a 4 caracteres
    if (caracteres.length() == 4 && !Character.isDigit(car)) {
        evt.consume(); // Consumir el evento si se intenta agregar más caracteres después del límite
    }
    // Permitir la entrada
    return;
}

// Permitir teclas de control (como retroceso)
if (Character.isISOControl(car)) {
    return;
}

// Consumir el evento si el carácter no es válido
evt.consume();
    }//GEN-LAST:event_TXTpesoKeyTyped

    private void TXTaspectoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTaspectoKeyTyped
         va.longitud(TXTaspecto.getText(), 150, evt);
    }//GEN-LAST:event_TXTaspectoKeyTyped

    private void TXTabdominalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTabdominalKeyTyped
        va.longitud(TXTabdominal.getText(), 150, evt);
    }//GEN-LAST:event_TXTabdominalKeyTyped

    private void TXTtemperaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTtemperaturaKeyTyped
         va.longitud(TXTtemperatura.getText(), 100, evt);
    }//GEN-LAST:event_TXTtemperaturaKeyTyped

    private void TXTpulsoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTpulsoKeyTyped
           va.longitud(TXTtemperatura.getText(), 100, evt);
    }//GEN-LAST:event_TXTpulsoKeyTyped

    private void TXTfrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfrKeyTyped
           va.longitud(TXTtemperatura.getText(), 80, evt);
    }//GEN-LAST:event_TXTfrKeyTyped

    private void jTextObservAdicMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextObservAdicMousePressed
    checkPopup(evt, jTextObservAdic);
    }//GEN-LAST:event_jTextObservAdicMousePressed

    private void jTextObservAdicMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextObservAdicMouseReleased
     checkPopup(evt, jTextObservAdic);
    }//GEN-LAST:event_jTextObservAdicMouseReleased

    private void jTextMotivoConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaMouseEntered
         jTextMotivoConsulta.requestFocusInWindow();
    }//GEN-LAST:event_jTextMotivoConsultaMouseEntered

    private void jLabelPacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPacienteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelPacienteMouseEntered

    private void jTextArea15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea15MouseEntered

    private void FechaAsignar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FechaAsignar1MouseEntered
    
    }//GEN-LAST:event_FechaAsignar1MouseEntered

    
    
private void checkPopup(MouseEvent evt, JTextArea textArea) {
    if (evt.isPopupTrigger() || (evt.getButton() == MouseEvent.BUTTON3)) {
        JPopupMenu menu = new JPopupMenu();

      
        ImageIcon iconCopy = new ImageIcon(getClass().getResource("/icons/copiar.png"));
        ImageIcon iconPaste = new ImageIcon(getClass().getResource("/icons/pegar.png"));

        JMenuItem itemCopy = new JMenuItem("Copiar", iconCopy);
        JMenuItem itemPaste = new JMenuItem("Pegar", iconPaste);
        
        itemCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        itemPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        menu.add(itemCopy);
        menu.add(itemPaste);

        menu.show(evt.getComponent(), evt.getX(), evt.getY());
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
  

        
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,"ERROR AL LLENAR LISTA DE DIAGNOSTICO", "ERR", JOptionPane.ERROR_MESSAGE );}
    
    
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
public ArrayList<JCMostrarAsignados> LlenarLista() {
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAsignar1.getDate());
    String sql = "SELECT Id_historias, Id_usuarioh, Nombre, Apellido, Cedula, Usuario, " +
                 "date_format(Fecha_Historia, '%d/%m/%Y') AS Fecha, especialidad, Estado, Id_Estadoh, " +
                 "Fecha_Historia, Idpaciente " +
                 "FROM table_historias u " +
                 "INNER JOIN table_paciente c ON u.Id_pacienteh = c.Idpaciente " +
                 "INNER JOIN table_estado p ON u.Id_Estadoh = p.IdEstado " +
                 "INNER JOIN table_usuario x ON u.Id_usuarioh = x.IdPersonal " +
                 "INNER JOIN table_especialidad l ON u.Id_Hespecialidad = l.id_especialidad " +
                 "WHERE Id_usuarioh = ? " +
                 "AND especialidad = ? " +
                 "AND Fecha_Historia = ? " +
                 "ORDER BY Id_historias";

    ArrayList<JCMostrarAsignados> al = new ArrayList<>();

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idusuario);
        ps.setString(2, especialidad);
        ps.setString(3, fecha);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int estado = rs.getInt("Id_Estadoh");
                if (estado != 104) {
                    JCMostrarAsignados prd = new JCMostrarAsignados();
                    prd.setIdAsginado(rs.getInt(1));
                    prd.setPacAsignado(rs.getString(3) + " " + rs.getString(4));
                    prd.setCedAsignado(rs.getString(5));
                    prd.setEstado(rs.getString(9));
                    idpacientes = rs.getInt("Idpaciente");
                    al.add(prd);
                }
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al listar pacientes: " + e);
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
  
  
  
  
  
  
  
  

  
  
  
  
    public void listarAntecedentes() {

        List<JCAntecedentesFamiliares> lista = AntcDao.listarAntecedentesFamiliares();
        modelo = (DefaultTableModel) jTableAntecedentesF.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdAntecentes1();
            ob[1] = lista.get(i).getEnfermedad1();
            ob[2] = lista.get(i).getEstado1();
   
            modelo.addRow(ob);

        }
       jTableAntecedentesF.setModel(modelo);
        
            
     jTableAntecedentesF.getColumnModel().getColumn(0).setMaxWidth(0);
     jTableAntecedentesF.getColumnModel().getColumn(0).setMinWidth(0);
     jTableAntecedentesF.getColumnModel().getColumn(0).setPreferredWidth(0);
     jTableAntecedentesF.setDefaultEditor(Object.class, null);
     jTableAntecedentesF.setRowHeight(20);
    }
    
    
  
  
  
  
  
  
  
    public void listarAntecedentespersonales() {

        List<JCAntecedentesFamiliares> lista = AntcDao.listarAntecedentesPatologicos();
        modelo = (DefaultTableModel) jTableAntcPers.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdAntecentes2();
            ob[1] = lista.get(i).getEnfermedad2();
            ob[2] = lista.get(i).getEstado2();
   
            modelo.addRow(ob);

        }
       jTableAntcPers.setModel(modelo);
        
            
     jTableAntcPers.getColumnModel().getColumn(0).setMaxWidth(0);
     jTableAntcPers.getColumnModel().getColumn(0).setMinWidth(0);
     jTableAntcPers.getColumnModel().getColumn(0).setPreferredWidth(0);
     jTableAntcPers.setDefaultEditor(Object.class, null);
     jTableAntcPers.setRowHeight(20);
    }
    
  
  
  
      public void ShowAntc1(int idantc1) {

       

        List<JCAntecedentesFamiliares> lista = AntcDao.ShowAntc1(idantc1);
        modelo = (DefaultTableModel) jTableAF2.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getShowAntcid1();
            ob[1] = lista.get(i).getShowNameantc1();
            ob[2] = lista.get(i).getShowDescription1();
   
            modelo.addRow(ob);

        }
       jTableAF2.setModel(modelo);
       jTableAF2.setDefaultEditor(Object.class, null);
       acomodarAntec1();
            

    }
    
        public  void limpiarShowAntc1() {
        DefaultTableModel tb = (DefaultTableModel) jTableAF2.getModel();
        int a = jTableAF2.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }


    
        
        
        public void ShowAntc2(int idantc2) {
             
        List<JCAntecedentesFamiliares> lista = AntcDao.ShowAntc2(idantc2);
        modelo = (DefaultTableModel) jTableAntpersonal.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getShowAntcid2();
            ob[1] = lista.get(i).getShowNameantc2();
            ob[2] = lista.get(i).getShowDescription2();
   
            modelo.addRow(ob);

        }
       jTableAntpersonal.setModel(modelo);
       acomodarAntec2();

    }
        
        
        public  void limpiarShowAntc2() {
        DefaultTableModel tb = (DefaultTableModel) jTableAntpersonal.getModel();
        int a = jTableAntpersonal.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

      
      
      
      
      
      
      
      
      
      
      
      
      
      
    
    
    
  
  
  
    public void listarCallAntc1() {

        List<JCAntecedentesFamiliares> lista = AntcDao.listarCallAntc1(idtablaConsulta);
        modelo = (DefaultTableModel) jTableAF2.getModel();
        Object[] ob = new Object[10];
         

   
   
        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getCallAtcd1();
            ob[1] = lista.get(i).getCallE1();
            ob[2] = lista.get(i).getCallD1();
   
            modelo.addRow(ob);

        }
       jTableAF2.setModel(modelo);
       acomodarAntec1();

    }
  
  
  
  
  
  
  
  
  
  
  
    public void conteoTabla (){
      for(int x=0;x<=JTablePacientes.getRowCount();x++)
           {
           jLabel1.setText(""+x);
           }
             
    
    }
    
        public  void limpiarTEAdd() {
        DefaultTableModel tb = (DefaultTableModel) jTableAdic.getModel();
        int a = jTableAdic.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }
    

      public  void limpiarTAntecedentes() {
        DefaultTableModel tb = (DefaultTableModel) jTableAntecedentesF.getModel();
        int a = jTableAntecedentesF.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }
    
      
            public  void limpiarTAntecedentesPerso() {
        DefaultTableModel tb = (DefaultTableModel) jTableAntcPers.getModel();
        int a = jTableAntcPers.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
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
      
      
          
              public  void limpiarTAntecedentesF2() {
        DefaultTableModel tb = (DefaultTableModel) jTableAF2.getModel();
        int a = jTableAF2.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

                    
              public  void limpiarTAntecedentesF3() {
        DefaultTableModel tb = (DefaultTableModel) jTableAntpersonal.getModel();
        int a = jTableAntpersonal.getRowCount()-1;
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
            closeResources(rs, ps, con);
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
         doc.setMargins(36, 36, 36, 145);
           
            
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
     
                
            Paragraph tcolumna2 = new Paragraph("HISTORIA CLINICA ");
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
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+ cedula );
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
        
            
           // Tabla0.addCell(new Paragraph(nombrepaciente ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
           // Tabla0.addCell(new Paragraph(cedula,FontFactory.getFont("Arial",8,Font.NORMAL))); 
           // Tabla0.addCell(new Paragraph(Integer.toString(diferencian),FontFactory.getFont("Arial",8,Font.NORMAL))); 
           // Tabla0.addCell(new Paragraph(sexo,FontFactory.getFont("Arial",8,Font.NORMAL))); 

         
            
            
            
            
                 
         
                

            
            //PARTE 1
            PdfPTable Tabla2 = new PdfPTable(1); 
            Tabla2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            float[] medidaCeldas3 = {5f};
            Tabla2.setWidthPercentage(100);  
            Tabla2.setWidths(medidaCeldas3);
            Tabla2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t2columna1 = new Paragraph("M O T I V O   C O N S U L T A");
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
         

        
            // P R O C E D I M I E N T O S 
            
            
            Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);
            
            PdfPTable TableProcedimiento = new PdfPTable(2);
            TableProcedimiento.setWidthPercentage(100);
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

       
            
            for (int i = 0; i < jTableProcedimientos.getRowCount(); i++) {
               
                String Procedimiento = jTableProcedimientos.getValueAt(i, 1).toString();
                String Observacion = jTableProcedimientos.getValueAt(i, 2).toString();
      
        
                TableProcedimiento.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                
                TableProcedimiento.addCell(new Paragraph(Procedimiento,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                TableProcedimiento.addCell(new Paragraph(Observacion,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                
            }
            
        


            
            
            
            
            
            //PARTE  ANTECEDENTES 
          
            PdfPTable TableAntc1 = new PdfPTable(2);
            TableAntc1.setWidthPercentage(100);
     
            TableAntc1.setWidths(Columnapro);
            TableAntc1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell antc = new PdfPCell(new Phrase("Ant. Familiares", font));
            PdfPCell antcOb = new PdfPCell(new Phrase("Observación", font));
            antc.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            antcOb.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            antc.setBorder(0);
            antcOb.setBorder(0);

            antc.setBackgroundColor(BaseColor.LIGHT_GRAY);
            antcOb.setBackgroundColor(BaseColor.LIGHT_GRAY);
  
            TableAntc1.addCell(antc);
            TableAntc1.addCell(antcOb);
      
            for (int i = 0; i < jTableAF2.getRowCount(); i++) {
               
                String Procedimiento = jTableAF2.getValueAt(i, 1).toString();
                String Observacion = jTableAF2.getValueAt(i, 2).toString();
      
                TableAntc1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                
               
                TableAntc1.addCell(new Paragraph(Procedimiento,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                TableAntc1.addCell(new Paragraph(Observacion,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                
            }
            
        
           
            
            
            
            
            PdfPTable TableAntc2 = new PdfPTable(2);
            TableAntc2.setWidthPercentage(100);
            //TableProcedimiento.getDefaultCell().setBorder(0);

            TableAntc2.setWidths(Columnapro);
            TableAntc2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell Antc1 = new PdfPCell(new Phrase("Ant. Personales Patológicos", font));
            PdfPCell Antc2 = new PdfPCell(new Phrase("Observación", font));
            Antc1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            Antc2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            Antc1.setBorder(0);
            Antc2.setBorder(0);

            Antc1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            Antc2.setBackgroundColor(BaseColor.LIGHT_GRAY);
  
            TableAntc2.addCell(Antc1);
            TableAntc2.addCell(Antc2);
      
            for (int i = 0; i < jTableAntpersonal.getRowCount(); i++) {
               
                String Procedimiento = jTableAntpersonal.getValueAt(i, 1).toString();
                String Observacion = jTableAntpersonal.getValueAt(i, 2).toString();
      
                TableAntc2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                
               
                TableAntc2.addCell(new Paragraph(Procedimiento,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                TableAntc2.addCell(new Paragraph(Observacion,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                
            }
            
            
            
            
            
            
            // PARTE 3 REVISION FUNCIONAL
            
            PdfPTable TablaFuncional1 = new PdfPTable(1); 
            TablaFuncional1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            float[] medidaCeldasF = {5f};
            TablaFuncional1.setWidthPercentage(100);  
            TablaFuncional1.setWidths(medidaCeldas3);
            TablaFuncional1.setHorizontalAlignment(Element.ALIGN_CENTER);
            TablaFuncional1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t3columna1 = new Paragraph("Revisión Funcional & hábitos Psicobiologicos");
            t3columna1.getFont().setStyle(Font.BOLD);
          //t3columna1.getFont().setSize(10);        
            t3columna1.setFont(Letra);
           
            PdfPCell cellFuncional1 = new PdfPCell();
            cellFuncional1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
          //cellFuncional1.setPadding(10);
            cellFuncional1.setBorder(PdfPCell.NO_BORDER);
            cellFuncional1.setPhrase (new Paragraph(jTextRevision.getText(),FontFactory.getFont("Arial",9,Font.NORMAL)));
            
            t3columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            TablaFuncional1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            TablaFuncional1.addCell(t3columna1);
            TablaFuncional1.addCell(cellFuncional1);   
            
            
            
            
           
                 
            PdfPTable TablaFuncional2 = new PdfPTable(5); 
            TablaFuncional2.setWidthPercentage(100);  
           // TablaFuncional2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] medidaCeldas2 = {4f, 4f, 4f,4f,4f};
            TablaFuncional2.setWidths(medidaCeldas2);
            TablaFuncional2.setHorizontalAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t4columna1 = new Paragraph("Simatología");
            t4columna1.getFont().setStyle(Font.BOLD);
            t4columna1.getFont().setSize(9);        
            t4columna1.setFont(Letrasmall);
            t4columna1.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.addCell(t4columna1);
     
                
            Paragraph t5columna2 = new Paragraph("CardioVascular");
            t5columna2.getFont().setStyle(Font.BOLD);
            t5columna2.getFont().setSize(9);        
            t5columna2.setFont(Letrasmall);
            t5columna2.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.addCell(t5columna2);

            Paragraph t6columna3 = new Paragraph("Digestivo");
            t6columna3.getFont().setStyle(Font.BOLD);
            t6columna3.getFont().setSize(10);        
            t6columna3.setFont(Letrasmall);
            t6columna3.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.addCell(t6columna3);

            Paragraph t7columna4 = new Paragraph("Músculo-Esqueletico");
            t7columna4.getFont().setStyle(Font.BOLD);
            t7columna4.getFont().setSize(9);        
            t7columna4.setFont(Letrasmall);
            t7columna4.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.addCell(t7columna4);
            
            
            Paragraph t8columna4 = new Paragraph("Neurológico");
            t8columna4.getFont().setStyle(Font.BOLD);
            t8columna4.getFont().setSize(9);        
            t8columna4.setFont(Letrasmall);
            t8columna4.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional2.addCell(t8columna4);
            String fila1, fila2, fila3, fila4, fila5;
            
            if(constitucional.equals("") && piel.equals("") && orl.equals("") && respiratorio.equals("")){
             fila1="N/A";
            }
            else{fila1=constitucional + piel + orl + respiratorio;}
            
           
            if(Angina.equals("") && Palpitaciones.equals("") && Síncope.equals("") && EndemaMsis.equals("")){
             fila2="N/A";
            }
            else{fila2=Angina + Palpitaciones + Síncope + EndemaMsis;}
            
            
             if(DolorAbdominal.equals("") && Flatulencia.equals("") && Estreñimiento.equals("")){
             fila3="N/A";
            }
            else{fila3=DolorAbdominal + Flatulencia + Estreñimiento;}
            

            if(DolorMuscular.equals("") && DolorArticular.equals("") && Inflamación.equals("")){
             fila4="N/A";
            }
            else{fila4=DolorMuscular + DolorArticular + Inflamación ;}
            
            if(Cefalea.equals("") && Migraña.equals("") && Debilidad.equals("") && Disestesia.equals("") && Parestesia.equals("")
               && Genito.equals("") && Endocrino.equals("") && Anticonceptivos.equals("") && Angustia.equals("")
               && Alergias.equals("") && Otros.equals("") ){
             fila5="N/A";
            }
            else{fila5=Cefalea + Migraña + Debilidad + Disestesia + Parestesia + Genito + Endocrino + Anticonceptivos + Angustia + Alergias + Otros ;}
            
            
             
            TablaFuncional2.addCell(new Paragraph(fila1,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional2.addCell(new Paragraph(fila2,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional2.addCell(new Paragraph(fila3,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional2.addCell(new Paragraph(fila4,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional2.addCell(new Paragraph(fila5 ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            
           
            
          
          
            
            
            
           
           //MENSTRUACION
           
           
            PdfPTable TablaFuncional3 = new PdfPTable(9); 
            // Tabla0.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            // Tabla0.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            TablaFuncional3.setWidthPercentage(100);  
            float[] medidaCeldasF3 = {2f, 4f, 3f, 3f, 2f, 2f, 2f, 2f, 2f};
            TablaFuncional3.setWidths(medidaCeldasF3);
            TablaFuncional3.setHorizontalAlignment(Element.ALIGN_CENTER);
            TablaFuncional3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t9columna1 = new Paragraph("Mernarquia");
            t9columna1.getFont().setStyle(Font.BOLD);
            t9columna1.getFont().setSize(8);        
            t9columna1.setFont(Letra);
            t9columna1.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t9columna1);
     
                
            Paragraph t10columna2 = new Paragraph("Menstruación");
            t10columna2.getFont().setStyle(Font.BOLD);
            t10columna2.getFont().setSize(8);        
            t10columna2.setFont(Letra);
            t10columna2.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t10columna2);

            Paragraph t11columna3 = new Paragraph("Ciclos menstruales");
            t11columna3.getFont().setStyle(Font.BOLD);
            t11columna3.getFont().setSize(8);        
            t11columna3.setFont(Letra);
            t11columna3.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t11columna3);
        
            Paragraph t12columna4 = new Paragraph("Ultima Menstruación");
            t12columna4.getFont().setStyle(Font.BOLD);
            t12columna4.getFont().setSize(8);        
            t12columna4.setFont(Letra);
            t12columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t12columna4);
            
            Paragraph t13columna4 = new Paragraph("Partos");
            t13columna4.getFont().setStyle(Font.BOLD);
            t13columna4.getFont().setSize(8);        
            t13columna4.setFont(Letra);
            t13columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t13columna4);
            
            
            Paragraph t14columna4 = new Paragraph("Cesareas");
            t14columna4.getFont().setStyle(Font.BOLD);
            t14columna4.getFont().setSize(8);        
            t14columna4.setFont(Letra);
            t14columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t14columna4);
            
            
            Paragraph t15columna4 = new Paragraph("Abortos");
            t15columna4.getFont().setStyle(Font.BOLD);
            t15columna4.getFont().setSize(8);        
            t15columna4.setFont(Letra);
            t15columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t15columna4);
           
            
            Paragraph t16columna4 = new Paragraph("Embarazos");
            t16columna4.getFont().setStyle(Font.BOLD);
            t16columna4.getFont().setSize(8);        
            t16columna4.setFont(Letra);
            t16columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t16columna4);
            
            
            
            Paragraph t17columna4 = new Paragraph("Menospaucia");
            t17columna4.getFont().setStyle(Font.BOLD);
            t17columna4.getFont().setSize(8);        
            t17columna4.setFont(Letra);
            t17columna4.setAlignment(Element.ALIGN_LEFT);
            TablaFuncional3.addCell(t17columna4);
            

       
    
            
          
            
            int mernarquia = (Integer) this.jSpinnerMenarquia.getValue();
            if(mernarquia>0){TablaFuncional3.addCell(new Paragraph(String.valueOf(jSpinnerMenarquia.getValue() + " años") ,FontFactory.getFont("Arial",8,Font.NORMAL))); }
            else{TablaFuncional3.addCell(new Paragraph("N/A" ,FontFactory.getFont("Arial",8,Font.NORMAL))); }
            
            TablaFuncional3.addCell(new Paragraph(jComboMestruacion.getSelectedItem().toString(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(TxtCiclosMestru.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(FechaMestruacion.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(String.valueOf(jSpartos.getValue()) ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(String.valueOf(jScesareas.getValue()) ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(String.valueOf(jSabortos.getValue()) ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional3.addCell(new Paragraph(String.valueOf(jSembarazo.getValue()) ,FontFactory.getFont("Arial",8,Font.NORMAL))); 
           
            int menospaucia = (Integer) this.jSmenospausia.getValue();
            if(menospaucia>0){TablaFuncional3.addCell(new Paragraph(String.valueOf(jSmenospausia.getValue() +" años") ,FontFactory.getFont("Arial",8,Font.NORMAL))); }
            else{TablaFuncional3.addCell(new Paragraph("N/A" ,FontFactory.getFont("Arial",8,Font.NORMAL))); }
            
          
    

       
            
            PdfPTable TablaFuncional4 = new PdfPTable(1); 
            TablaFuncional4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
     
            TablaFuncional4.setWidthPercentage(100);  
            TablaFuncional4.setWidths(medidaCeldas3);
            TablaFuncional4.setHorizontalAlignment(Element.ALIGN_CENTER);
            TablaFuncional4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
        
           
            PdfPCell cellFuncional2 = new PdfPCell();
            cellFuncional2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
         //   cellFuncional2.setPadding(10);
            cellFuncional2.setBorder(PdfPCell.NO_BORDER);
            cellFuncional2.setPhrase (new Paragraph(Jtextwoman.getText(),FontFactory.getFont("Arial",9,Font.NORMAL)));
            

            TablaFuncional4.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            TablaFuncional4.addCell(cellFuncional2);  
         
              
          
            //CONSUMO Y CREACION
            
            
            
PdfPTable TablaFuncional5 = new PdfPTable(4); 
TablaFuncional5.setWidthPercentage(100);  
TablaFuncional5.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
float[] medidaCeldas5 = {2f, 4f, 4f,4f};
TablaFuncional5.setWidths(medidaCeldas5);
TablaFuncional5.setHorizontalAlignment(Element.ALIGN_CENTER);
TablaFuncional5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

Paragraph t18columna1 = new Paragraph("Hábitos");
t18columna1.getFont().setStyle(Font.BOLD);
t18columna1.getFont().setSize(9);        
t18columna1.setFont(Letrasmall);
t18columna1.setAlignment(Element.ALIGN_CENTER);
TablaFuncional5.addCell(t18columna1);


Paragraph t19columna2 = new Paragraph("Consumo Alcohol");
t19columna2.getFont().setStyle(Font.BOLD);
t19columna2.getFont().setSize(9);        
t19columna2.setFont(Letrasmall);
t19columna2.setAlignment(Element.ALIGN_CENTER);
TablaFuncional5.addCell(t19columna2);

Paragraph t20columna3 = new Paragraph("Consumo Tabaco");
t20columna3.getFont().setStyle(Font.BOLD);
t20columna3.getFont().setSize(10);        
t20columna3.setFont(Letrasmall);
t20columna3.setAlignment(Element.ALIGN_CENTER);
TablaFuncional5.addCell(t20columna3);

Paragraph t21columna4 = new Paragraph("Consumo de estupefacientes");
t21columna4.getFont().setStyle(Font.BOLD);
t21columna4.getFont().setSize(9);        
t21columna4.setFont(Letrasmall);
t21columna4.setAlignment(Element.ALIGN_CENTER);
TablaFuncional5.addCell(t21columna4);


PdfPCell Habitos = new PdfPCell();
Habitos.setHorizontalAlignment(Element.ALIGN_CENTER);
String habitosText = jComboHabitos.getSelectedItem().toString().isEmpty() ? "N/A" : jComboHabitos.getSelectedItem().toString();
Habitos.addElement(new Paragraph(habitosText, FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
//Habitos.setBorder(PdfPCell.NO_BORDER);


PdfPCell Alcohol = new PdfPCell();
Alcohol.setHorizontalAlignment(Element.ALIGN_CENTER);
String alcoholText = jTConsumoAlcohol.getText().isEmpty() ? "N/A" : jTConsumoAlcohol.getText();
Alcohol.addElement(new Paragraph(alcoholText, FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
//Alcohol.setBorder(PdfPCell.NO_BORDER);

PdfPCell Tabaco = new PdfPCell();
Tabaco.setHorizontalAlignment(Element.ALIGN_CENTER);
String tabacoText = jTConsumoTabaco.getText().isEmpty() ? "N/A" : jTConsumoTabaco.getText();
Tabaco.addElement(new Paragraph(tabacoText, FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
//Tabaco.setBorder(PdfPCell.NO_BORDER);

PdfPCell Esupefaciente = new PdfPCell();
Esupefaciente.setHorizontalAlignment(Element.ALIGN_CENTER);
String drugsText = jTConsumoDrugs.getText().isEmpty() ? "N/A" : jTConsumoDrugs.getText();
Esupefaciente.addElement(new Paragraph(drugsText, FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
//Esupefaciente.setBorder(PdfPCell.NO_BORDER);

// Verificar si todos los campos están vacíos
if (!jComboHabitos.getSelectedItem().toString().equals("N/A") || !alcoholText.isEmpty() || !tabacoText.isEmpty() || !drugsText.isEmpty()) {
    TablaFuncional5.addCell(Habitos); 
    TablaFuncional5.addCell(Alcohol); 
    TablaFuncional5.addCell(Tabaco); 
    TablaFuncional5.addCell(Esupefaciente);
}
            
            
           
           
           
           
            PdfPTable TablaFuncional6 = new PdfPTable(4); 
            TablaFuncional6.setWidthPercentage(100);  
            TablaFuncional6.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            TablaFuncional6.setWidths(medidaCeldas5);
            TablaFuncional6.setHorizontalAlignment(Element.ALIGN_CENTER);
            TablaFuncional6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph t22columna1 = new Paragraph("Actividad Física");
            t22columna1.getFont().setStyle(Font.BOLD);
            t22columna1.getFont().setSize(9);        
            t22columna1.setFont(Letrasmall);
            t22columna1.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional6.addCell(t22columna1);
     
                
            Paragraph t23columna2 = new Paragraph("Frecuencia semanal");
            t23columna2.getFont().setStyle(Font.BOLD);
            t23columna2.getFont().setSize(9);        
            t23columna2.setFont(Letrasmall);
            t23columna2.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional6.addCell(t23columna2);

            Paragraph t24columna3 = new Paragraph("Sueño");
            t24columna3.getFont().setStyle(Font.BOLD);
            t24columna3.getFont().setSize(9);        
            t24columna3.setFont(Letrasmall);
            t24columna3.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional6.addCell(t24columna3);

            Paragraph t25columna4 = new Paragraph("Sexualidad");
            t25columna4.getFont().setStyle(Font.BOLD);
            t25columna4.getFont().setSize(9);        
            t25columna4.setFont(Letrasmall);
            t25columna4.setAlignment(Element.ALIGN_CENTER);
            TablaFuncional6.addCell(t25columna4);
            
            TablaFuncional6.addCell(new Paragraph(jComboActividad.getSelectedItem().toString(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional6.addCell(new Paragraph(jComboFrec.getSelectedItem().toString(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional6.addCell(new Paragraph(jComboSueno.getSelectedItem().toString(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TablaFuncional6.addCell(new Paragraph(jComboSexualidad.getSelectedItem().toString(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            
            
            
            
            
            
            
            
            // EXAMEN FISICO

          Paragraph tituloFisico = new Paragraph("E X A M E N   F Í S I C O", Letra);
          tituloFisico.setAlignment(Element.ALIGN_CENTER);
          tituloFisico.getFont().setStyle(Font.BOLD);
          tituloFisico.getFont().setSize(10);
            
            


          PdfPTable TableFisico2 = new PdfPTable(7); 
          TableFisico2.setWidthPercentage(100);  
          float[] medidaCeldaFisico = {2f, 2f, 2f, 2f, 2f, 2f, 2f};
          TableFisico2.setWidths(medidaCeldaFisico);
          TableFisico2.setHorizontalAlignment(Element.ALIGN_CENTER);
          TableFisico2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

          Paragraph t27columna1 = new Paragraph("Estatura (CM)");
          t27columna1.getFont().setStyle(Font.BOLD);
          t27columna1.getFont().setSize(9);        
          t27columna1.setFont(Letrasmall);
          t27columna1.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t27columna1);


          Paragraph t28columna2 = new Paragraph("Peso");
          t28columna2.getFont().setStyle(Font.BOLD);
          t28columna2.getFont().setSize(9);        
          t28columna2.setFont(Letrasmall);
          t28columna2.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t28columna2);

          Paragraph t29columna3 = new Paragraph("Peso Ideal");
          t29columna3.getFont().setStyle(Font.BOLD);
          t29columna3.getFont().setSize(9);        
          t29columna3.setFont(Letrasmall);
          t29columna3.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t29columna3);

          Paragraph t30columna4 = new Paragraph("Dif. Peso");
          t30columna4.getFont().setStyle(Font.BOLD);
          t30columna4.getFont().setSize(9);        
          t30columna4.setFont(Letrasmall);
          t30columna4.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t30columna4);

          Paragraph t31columna4 = new Paragraph("I.M.C");
          t31columna4.getFont().setStyle(Font.BOLD);
          t31columna4.getFont().setSize(9);        
          t31columna4.setFont(Letrasmall);
          t31columna4.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t31columna4);

          Paragraph t32columna4 = new Paragraph("Temperatura");
          t32columna4.getFont().setStyle(Font.BOLD);
          t32columna4.getFont().setSize(9);        
          t32columna4.setFont(Letrasmall);
          t32columna4.setAlignment(Element.ALIGN_CENTER);
          TableFisico2.addCell(t32columna4);

Paragraph t33columna4 = new Paragraph("Pulso");
t33columna4.getFont().setStyle(Font.BOLD);
t33columna4.getFont().setSize(9);        
t33columna4.setFont(Letrasmall);
t33columna4.setAlignment(Element.ALIGN_CENTER);
TableFisico2.addCell(t33columna4);

// Obtener el texto de los campos y verificar si están vacíos
String tallaText = TXTtalla.getText().isEmpty() ? "N/A" : TXTtalla.getText();
String pesoText = TXTpeso.getText().isEmpty() ? "N/A" : TXTpeso.getText();
String pesoIdealText = TXTpesoIdeal.getText().isEmpty() ? "N/A" : TXTpesoIdeal.getText();
String pesoAdicText = TXTpesoAdic.getText().isEmpty() ? "N/A" : TXTpesoAdic.getText();
String imcText = TXTimc.getText().isEmpty() ? "N/A" : TXTimc.getText();
String temperaturaText = TXTtemperatura.getText().isEmpty() ? "N/A" : TXTtemperatura.getText();
String pulsoText = TXTpulso.getText().isEmpty() ? "N/A" : TXTpulso.getText();

if(tallaText.equals("0") || pesoText.equals("0")) {
    pesoIdealText = "N/A";
    pesoAdicText = "N/A";
    imcText= "N/A";
}



// Verificar si todos los campos están vacíos
if (!tallaText.equals("0") || !pesoText.equals("0") || !pesoIdealText.equals("N/A") || !pesoAdicText.equals("N/A") || !imcText.equals("N/A") || !temperaturaText.equals("N/A") || !pulsoText.equals("N/A")) {
    TableFisico2.addCell(new Paragraph(tallaText, FontFactory.getFont("Arial", 8, Font.NORMAL)));
    TableFisico2.addCell(new Paragraph(pesoText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico2.addCell(new Paragraph(pesoIdealText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico2.addCell(new Paragraph(pesoAdicText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico2.addCell(new Paragraph(imcText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico2.addCell(new Paragraph(temperaturaText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico2.addCell(new Paragraph(pulsoText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
}
            
            
            
            
            
            
            
PdfPTable TableFisico3 = new PdfPTable(6); 
TableFisico3.setWidthPercentage(100);  
float[] medidaCeldaFisico2 = {2f, 3f, 3f, 2f, 2f, 2f};
TableFisico3.setWidths(medidaCeldaFisico2);
TableFisico3.setHorizontalAlignment(Element.ALIGN_CENTER);
TableFisico3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

Paragraph t34columna1 = new Paragraph("F.R");
t34columna1.getFont().setStyle(Font.BOLD);
t34columna1.getFont().setSize(9);        
t34columna1.setFont(Letrasmall);
t34columna1.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t34columna1);


Paragraph t35columna2 = new Paragraph("Tipo respiración");
t35columna2.getFont().setStyle(Font.BOLD);
t35columna2.getFont().setSize(9);        
t35columna2.setFont(Letrasmall);
t35columna2.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t35columna2);

Paragraph t36columna3 = new Paragraph("Aspecto ctxtura");
t36columna3.getFont().setStyle(Font.BOLD);
t36columna3.getFont().setSize(9);        
t36columna3.setFont(Letrasmall);
t36columna3.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t36columna3);

Paragraph t37columna4 = new Paragraph("Tensión art");
t37columna4.getFont().setStyle(Font.BOLD);
t37columna4.getFont().setSize(9);        
t37columna4.setFont(Letrasmall);
t37columna4.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t37columna4);

Paragraph t38columna4 = new Paragraph("Abdominal (CM)");
t38columna4.getFont().setStyle(Font.BOLD);
t38columna4.getFont().setSize(9);        
t38columna4.setFont(Letrasmall);
t38columna4.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t38columna4);

Paragraph t39columna4 = new Paragraph("Cadera (CM)");
t39columna4.getFont().setStyle(Font.BOLD);
t39columna4.getFont().setSize(9);        
t39columna4.setFont(Letrasmall);
t39columna4.setAlignment(Element.ALIGN_CENTER);
TableFisico3.addCell(t39columna4);

// Obtener el texto de los campos y verificar si están vacíos
String frText = TXTfr.getText().isEmpty() ? "N/A" : TXTfr.getText();
String respiracionText = TXTrespiracion.getText().isEmpty() ? "N/A" : TXTrespiracion.getText();
String aspectoText = TXTaspecto.getText().isEmpty() ? "N/A" : TXTaspecto.getText();
String tensionText = TXTtension.getText().isEmpty() ? "N/A" : TXTtension.getText();
String abdominalText = TXTabdominal.getText().isEmpty() ? "N/A" : TXTabdominal.getText();
String caderaText = TXTcadera.getText().isEmpty() ? "N/A" : TXTcadera.getText();

// Verificar si todos los campos están vacíos
if (!frText.equals("N/A") || !respiracionText.equals("N/A") || !aspectoText.equals("N/A") || !tensionText.equals("N/A") || !abdominalText.equals("N/A") || !caderaText.equals("N/A")) {
    TableFisico3.addCell(new Paragraph(frText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico3.addCell(new Paragraph(respiracionText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico3.addCell(new Paragraph(aspectoText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico3.addCell(new Paragraph(tensionText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico3.addCell(new Paragraph(abdominalText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    TableFisico3.addCell(new Paragraph(caderaText, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
}
            
            
            
            
            
            
            PdfPTable TableFisico4 = new PdfPTable(1); 
            TableFisico4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            TableFisico4.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            TableFisico4.setWidthPercentage(100);  
            TableFisico4.setWidths(medidaCeldas3);
            TableFisico4.setHorizontalAlignment(Element.ALIGN_CENTER);
            TableFisico4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            Paragraph t40columna1 = new Paragraph("Piel: "+ TXTpiel.getText());
            t40columna1.getFont().setStyle(Font.NORMAL);
            t40columna1.getFont().setSize(9);        
            t40columna1.setFont(Letrasmall);
            t40columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico = new PdfPCell(t40columna1);
            aspectoFisico.setBorder(Rectangle.NO_BORDER);
            aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t41columna1 = new Paragraph("Ojos: "+ TXTojos.getText());
            t41columna1.getFont().setStyle(Font.NORMAL);
            t41columna1.getFont().setSize(9);        
            t41columna1.setFont(Letrasmall);
            t41columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico2 = new PdfPCell(t41columna1);
            aspectoFisico2.setBorder(Rectangle.NO_BORDER);
            aspectoFisico2.enableBorderSide(Rectangle.BOTTOM);

            
             
            Paragraph t42columna1 = new Paragraph("Oídos: "+ TXToidos.getText());
            t42columna1.getFont().setStyle(Font.NORMAL);
            t42columna1.getFont().setSize(9);        
            t42columna1.setFont(Letrasmall);
            t42columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico3 = new PdfPCell(t42columna1);
            aspectoFisico3.setBorder(Rectangle.NO_BORDER);
            aspectoFisico3.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t43columna1 = new Paragraph("Dientes: "+ TXTdientes.getText());
            t43columna1.getFont().setStyle(Font.NORMAL);
            t43columna1.getFont().setSize(9);        
            t43columna1.setFont(Letrasmall);
            t43columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico4 = new PdfPCell(t43columna1);
            aspectoFisico4.setBorder(Rectangle.NO_BORDER);
            aspectoFisico4.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t44columna1 = new Paragraph("Boca: "+ TXTboca.getText());
            t44columna1.getFont().setStyle(Font.NORMAL);
            t44columna1.getFont().setSize(9);        
            t44columna1.setFont(Letrasmall);
            t44columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico5 = new PdfPCell(t44columna1);
            aspectoFisico5.setBorder(Rectangle.NO_BORDER);
            aspectoFisico5.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t45columna1 = new Paragraph("Cuello: "+ TXTcuello.getText());
            t45columna1.getFont().setStyle(Font.NORMAL);
            t45columna1.getFont().setSize(9);        
            t45columna1.setFont(Letrasmall);
            t45columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico6 = new PdfPCell(t45columna1);
            aspectoFisico6.setBorder(Rectangle.NO_BORDER);
            aspectoFisico6.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t46columna1 = new Paragraph("Corazón: "+ TXTcorazon.getText());
            t46columna1.getFont().setStyle(Font.NORMAL);
            t46columna1.getFont().setSize(9);        
            t46columna1.setFont(Letrasmall);
            t46columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico7 = new PdfPCell(t46columna1);
            aspectoFisico7.setBorder(Rectangle.NO_BORDER);
            aspectoFisico7.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t47columna1 = new Paragraph("Abdomen: "+ TXTAbdomen2.getText());
            t47columna1.getFont().setStyle(Font.NORMAL);
            t47columna1.getFont().setSize(9);        
            t47columna1.setFont(Letrasmall);
            t47columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico8 = new PdfPCell(t47columna1);
            aspectoFisico8.setBorder(Rectangle.NO_BORDER);
            aspectoFisico8.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t48columna1 = new Paragraph("Columna: "+ TXTcolumna.getText());
            t48columna1.getFont().setStyle(Font.NORMAL);
            t48columna1.getFont().setSize(9);        
            t48columna1.setFont(Letrasmall);
            t48columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico9 = new PdfPCell(t48columna1);
            aspectoFisico9.setBorder(Rectangle.NO_BORDER);
            aspectoFisico9.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t49columna1 = new Paragraph("Ext. Inferior: "+ TXTinferior.getText());
            t49columna1.getFont().setStyle(Font.NORMAL);
            t49columna1.getFont().setSize(9);        
            t49columna1.setFont(Letrasmall);
            t49columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico10 = new PdfPCell(t49columna1);
            aspectoFisico10.setBorder(Rectangle.NO_BORDER);
            aspectoFisico10.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t50columna1 = new Paragraph("Linfáticos: "+ TXTlinfaticos.getText());
            t50columna1.getFont().setStyle(Font.NORMAL);
            t50columna1.getFont().setSize(9);        
            t50columna1.setFont(Letrasmall);
            t50columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico11 = new PdfPCell(t50columna1);
            aspectoFisico11.setBorder(Rectangle.NO_BORDER);
            aspectoFisico11.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t51columna1 = new Paragraph("Genitales: "+ TXTgenitales.getText());
            t51columna1.getFont().setStyle(Font.NORMAL);
            t51columna1.getFont().setSize(9);        
            t51columna1.setFont(Letrasmall);
            t51columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico12 = new PdfPCell(t51columna1);
            aspectoFisico12.setBorder(Rectangle.NO_BORDER);
            aspectoFisico12.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t52columna1 = new Paragraph("Ano-rectal: "+ TXTrectal.getText());
            t52columna1.getFont().setStyle(Font.NORMAL);
            t52columna1.getFont().setSize(9);        
            t52columna1.setFont(Letrasmall);
            t52columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico13 = new PdfPCell(t52columna1);
            aspectoFisico13.setBorder(Rectangle.NO_BORDER);
            aspectoFisico13.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t53columna1 = new Paragraph("Oftamoscopia: "+ TXToftamoscopia.getText());
            t53columna1.getFont().setStyle(Font.NORMAL);
            t53columna1.getFont().setSize(9);        
            t53columna1.setFont(Letrasmall);
            t53columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico14 = new PdfPCell(t53columna1);
            aspectoFisico14.setBorder(Rectangle.NO_BORDER);
            aspectoFisico14.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t54columna1 = new Paragraph("Nariz: "+ TXTnariz.getText());
            t54columna1.getFont().setStyle(Font.NORMAL);
            t54columna1.getFont().setSize(9);        
            t54columna1.setFont(Letrasmall);
            t54columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico15 = new PdfPCell(t54columna1);
            aspectoFisico15.setBorder(Rectangle.NO_BORDER);
            aspectoFisico15.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t55columna1 = new Paragraph("Toráx: "+ TXTtorax.getText());
            t55columna1.getFont().setStyle(Font.NORMAL);
            t55columna1.getFont().setSize(9);        
            t55columna1.setFont(Letrasmall);
            t55columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico16 = new PdfPCell(t55columna1);
            aspectoFisico16.setBorder(Rectangle.NO_BORDER);
            aspectoFisico16.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t56columna1 = new Paragraph("Mamas: "+ TXTmamas.getText());
            t56columna1.getFont().setStyle(Font.NORMAL);
            t56columna1.getFont().setSize(9);        
            t56columna1.setFont(Letrasmall);
            t56columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico17 = new PdfPCell(t56columna1);
            aspectoFisico17.setBorder(Rectangle.NO_BORDER);
            aspectoFisico17.enableBorderSide(Rectangle.BOTTOM);
            
            
                        
            Paragraph t57columna1 = new Paragraph("Pulmones: "+ TXTpulmones.getText());
            t57columna1.getFont().setStyle(Font.NORMAL);
            t57columna1.getFont().setSize(9);        
            t57columna1.setFont(Letrasmall);
            t57columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico18 = new PdfPCell(t57columna1);
            aspectoFisico18.setBorder(Rectangle.NO_BORDER);
            aspectoFisico18.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t58columna1 = new Paragraph("Hernias: "+ TXThernias.getText());
            t58columna1.getFont().setStyle(Font.NORMAL);
            t58columna1.getFont().setSize(9);        
            t58columna1.setFont(Letrasmall);
            t58columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico19 = new PdfPCell(t58columna1);
            aspectoFisico19.setBorder(Rectangle.NO_BORDER);
            aspectoFisico19.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t59columna1 = new Paragraph("Ext. Superior: "+ TXTsuperior.getText());
            t59columna1.getFont().setStyle(Font.NORMAL);
            t59columna1.getFont().setSize(9);        
            t59columna1.setFont(Letrasmall);
            t59columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico20 = new PdfPCell(t59columna1);
            aspectoFisico20.setBorder(Rectangle.NO_BORDER);
            aspectoFisico20.enableBorderSide(Rectangle.BOTTOM);
            


            Paragraph t62columna1 = new Paragraph("Arterias: "+ TXTarterias.getText());
            t62columna1.getFont().setStyle(Font.NORMAL);
            t62columna1.getFont().setSize(9);        
            t62columna1.setFont(Letrasmall);
            t62columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico23 = new PdfPCell(t62columna1);
            aspectoFisico23.setBorder(Rectangle.NO_BORDER);
            aspectoFisico23.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t63columna1 = new Paragraph("Venas: "+ TXTvenas.getText());
            t63columna1.getFont().setStyle(Font.NORMAL);
            t63columna1.getFont().setSize(9);        
            t63columna1.setFont(Letrasmall);
            t63columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico24 = new PdfPCell(t63columna1);
            aspectoFisico24.setBorder(Rectangle.NO_BORDER);
            aspectoFisico24.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t64columna1 = new Paragraph("Neurológico: "+ TXTneurologico.getText());
            t64columna1.getFont().setStyle(Font.NORMAL);
            t64columna1.getFont().setSize(9);        
            t64columna1.setFont(Letrasmall);
            t64columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico25 = new PdfPCell(t64columna1);
            aspectoFisico25.setBorder(Rectangle.NO_BORDER);
            aspectoFisico25.enableBorderSide(Rectangle.BOTTOM);
            
            Paragraph t65columna1 = new Paragraph("Prostata: "+ TXTprostata.getText());
            t65columna1.getFont().setStyle(Font.NORMAL);
            t65columna1.getFont().setSize(9);        
            t65columna1.setFont(Letrasmall);
            t65columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico26 = new PdfPCell(t65columna1);
            aspectoFisico26.setBorder(Rectangle.NO_BORDER);
            aspectoFisico26.enableBorderSide(Rectangle.BOTTOM);
            
            
            Paragraph t66columna1 = new Paragraph("Ginecologo: "+ TXTginecologo.getText());
            t66columna1.getFont().setStyle(Font.NORMAL);
            t66columna1.getFont().setSize(9);        
            t66columna1.setFont(Letrasmall);
            t66columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell aspectoFisico27 = new PdfPCell(t66columna1);
            aspectoFisico27.setBorder(Rectangle.NO_BORDER);
            aspectoFisico27.enableBorderSide(Rectangle.BOTTOM);
            
            

            if(!TXTpiel.getText().equals("")){TableFisico4.addCell(aspectoFisico);}
            if(!TXTojos.getText().equals("")){TableFisico4.addCell(aspectoFisico2);}
            if(!TXToidos.getText().equals("")){TableFisico4.addCell(aspectoFisico3);}
            if(!TXTdientes.getText().equals("")){TableFisico4.addCell(aspectoFisico4);}
            if(!TXTboca.getText().equals("")){TableFisico4.addCell(aspectoFisico5);}
            if(!TXTcuello.getText().equals("")){TableFisico4.addCell(aspectoFisico6);}
            if(!TXTcorazon.getText().equals("")){TableFisico4.addCell(aspectoFisico7);}
            if(!TXTAbdomen2.getText().equals("")){TableFisico4.addCell(aspectoFisico8);}
            if(!TXTcolumna.getText().equals("")){TableFisico4.addCell(aspectoFisico9);}
            if(!TXTinferior.getText().equals("")){TableFisico4.addCell(aspectoFisico10);}
            if(!TXTlinfaticos.getText().equals("")){TableFisico4.addCell(aspectoFisico11);}
            if(!TXTgenitales.getText().equals("")){TableFisico4.addCell(aspectoFisico12);}
            if(!TXTrectal.getText().equals("")){TableFisico4.addCell(aspectoFisico13);}
            if(!TXToftamoscopia.getText().equals("")){TableFisico4.addCell(aspectoFisico14);}
            if(!TXTnariz.getText().equals("")){TableFisico4.addCell(aspectoFisico15);}
            if(!TXTtorax.getText().equals("")){TableFisico4.addCell(aspectoFisico16);}
            if(!TXTmamas.getText().equals("")){TableFisico4.addCell(aspectoFisico17);}
            if(!TXTpulmones.getText().equals("")){TableFisico4.addCell(aspectoFisico18);}
            if(!TXThernias.getText().equals("")){TableFisico4.addCell(aspectoFisico19);}
            if(!TXTsuperior.getText().equals("")){TableFisico4.addCell(aspectoFisico20);}

     
            if(!TXTarterias.getText().equals("")){TableFisico4.addCell(aspectoFisico23);}
            if(!TXTvenas.getText().equals("")){TableFisico4.addCell(aspectoFisico24);}
            if(!TXTneurologico.getText().equals("")){TableFisico4.addCell(aspectoFisico25);}
            if(!TXTprostata.getText().equals("")){TableFisico4.addCell(aspectoFisico26);}
            if(!TXTginecologo.getText().equals("")){TableFisico4.addCell(aspectoFisico27);}
         
            


    
            PdfPTable TableEadd = new PdfPTable(2);
            TableEadd.setWidthPercentage(100);
            //TableProcedimiento.getDefaultCell().setBorder(0);
            float[] Columnapro2 = new float[]{30f, 60f};
            TableEadd.setWidths(Columnapro2);
            TableEadd.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell Tableadd1 = new PdfPCell(new Phrase("Examen Complementario", font));
            PdfPCell Tableadd2 = new PdfPCell(new Phrase("Observación", font));
            Tableadd1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            Tableadd2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            Tableadd1.setBorder(0);
            Tableadd2.setBorder(0);

            Tableadd1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            Tableadd2.setBackgroundColor(BaseColor.LIGHT_GRAY);
  
            TableEadd.addCell(Tableadd1);
            TableEadd.addCell(Tableadd2);
      
            for (int i = 0; i < jTableAdic.getRowCount(); i++) {
               
                String Procedimiento = jTableAdic.getValueAt(i, 1).toString();
                String Observacion = jTableAdic.getValueAt(i, 2).toString();
      
                TableEadd.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                
               
                TableEadd.addCell(new Paragraph(Procedimiento,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                TableEadd.addCell(new Paragraph(Observacion,FontFactory.getFont("Arial",9,Font.NORMAL))); 
                
            }
            






   
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
            doc.add(Tabla);
            doc.add(Tabla0);
            doc.add(saltolinea);
          
            
            
            
            //B O D Y
           
          
            doc.add(Tabla2);
            doc.add(saltolinea);
            
            
            
           //PROCEDIMIENTOS
            int tProcedimientos = jTableProcedimientos.getRowCount();  
            if (tProcedimientos> 0 ) {
            doc.add(TableProcedimiento);
            doc.add(saltolinea);
              }
            
            
          
           //Antecedentes
           int ant1 = jTableAF2.getRowCount();  
           int ant2 =  jTableAntpersonal.getRowCount();  
            

           // Verificar si el modelo tiene o no filas
           if (ant1 > 0 && ant2>0) {
            doc.add(TableAntc1);
            doc.add(TableAntc2);
            doc.add(saltolinea);
           }
           else if(ant1>0){
           doc.add(TableAntc1);
           doc.add(saltolinea);
           }
           else if(ant2>0){
           doc.add(TableAntc2);
           doc.add(saltolinea);
           }
      
           
           
           
           //Revision funcional
            

           
           
            if(jCheckConstitucional.isSelected() || jCheckPielMucosa.isSelected() || jCheckORL.isSelected() || jCheckRespiratorio.isSelected() ||
               jCheckAngina.isSelected()    || jCheckBox9.isSelected()  || jCheckBox16.isSelected() || jCheckBox20.isSelected() || jCheckBox6.isSelected()
               || jCheckBox6.isSelected()   || jCheckBox10.isSelected() || jCheckBox17.isSelected() || jCheckBox7.isSelected()  || jCheckBox11.isSelected()
               || jCheckBox15.isSelected()  || jCheckBox1.isSelected()  || jCheckBox12.isSelected() || jCheckBox14.isSelected() || jCheckBox19.isSelected()
               || jCheckBox27.isSelected()  || jCheckBox18.isSelected() || jCheckBox25.isSelected() || jCheckBox22.isSelected() || jCheckBox23.isSelected()
               || jCheckBox24.isSelected()  || jCheckBox28.isSelected()  || !jTextRevision.getText().equals("")      
                    ){
          
            doc.add(TablaFuncional1);
            doc.add(saltolinea);
            doc.add(TablaFuncional2); 
            doc.add(saltolinea);
            }

             
            //mestruacion
           
                 
            if(sexo.equals("Femenino") && !TxtCiclosMestru.getText().equals("")){
            

            doc.add(TablaFuncional3);
            doc.add(TablaFuncional4);
            doc.add(saltolinea);

            }
            
            
            
            
            
            
        //------------------------------------------------------------------------ 
            
          if(!jComboHabitos.getSelectedItem().equals("N/A")||
             !jTConsumoAlcohol.getText().equals("")   ||
             !jTConsumoTabaco.getText().equals("")   ||
             !jTConsumoDrugs.getText().equals("")   
           ){  
              doc.add(TablaFuncional5);
              doc.add(saltolinea);
          }
          
          boolean todosNAN = jComboActividad.getSelectedItem().toString().equals("N/A") &&
                   jComboFrec.getSelectedItem().toString().equals("N/A") &&
                   jComboSueno.getSelectedItem().toString().equals("N/A") &&
                   jComboSexualidad.getSelectedItem().toString().equals("N/A");
          
          if (!todosNAN) {
              doc.add(TablaFuncional6);
              doc.add(saltolinea);
          }
          
          
         //------------------------------------------------------------------------ 
         
          //EXAMEN FISICO
          if(!TXTpesoIdeal.getText().equals("") ||
             !TXTpesoAdic.getText().equals("") ||
             !TXTimc.getText().equals("") ||
             !TXTaspecto.getText().equals("") ||
             !TXTabdominal.getText().equals("") ||
                  
             !TXTtemperatura.getText().equals("") ||
             !TXTpulso.getText().equals("") ||
             !TXTfr.getText().equals("") ||
             !TXTrespiracion.getText().equals("") ||
             !TXTtension.getText().equals("") ||
             !TXTcadera.getText().equals("")  
                  ){
          doc.add(tituloFisico);
          doc.add(saltolinea);
          }
         
         
          
          
          
           if(!TXTpesoIdeal.getText().equals("") ||
           !TXTpesoAdic.getText().equals("") ||
           !TXTimc.getText().equals("") ||
           !TXTaspecto.getText().equals("") ||
           !TXTabdominal.getText().equals("") ){
           doc.add(TableFisico2);
           doc.add(saltolinea);  
           }
           
           if(!TXTtemperatura.getText().equals("") ||
           !TXTpulso.getText().equals("") ||
           !TXTfr.getText().equals("") ||
           !TXTrespiracion.getText().equals("") ||
           !TXTtension.getText().equals("") ||
           !TXTcadera.getText().equals("")  
                  ){
             doc.add(TableFisico3);
             doc.add(saltolinea);  
              }
          
      
          doc.add(TableFisico4);
          doc.add(saltolinea);
      
          
          
          //EXAMEN COMPLEMENTARIO
          int rowtableadd =  jTableAdic.getRowCount();  
          
          if ( rowtableadd > 0) {
          doc.add(TableEadd);
          doc.add(saltolinea);
          }
          
          
          
          
            //- C O N C L U S I O N -
            if(!jTextConclusion.getText().equals("") || !jTextRecomendacion.getText().equals("") ){
            doc.add(TableConclusion);
            doc.add(saltolinea);
            doc.add(TableConclusion2);
            
            }
            
    
    
            doc.close();
            archivo.close();
         
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
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
    String sql = "SELECT nombrempresa, rif, ubicacion, telefonos, infopiepagina FROM tableinfopdfs";
    
    // Usar try-with-resources para manejar automáticamente el cierre de recursos
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
        // Proporciona detalles adicionales sobre el error y su origen
        System.out.println("ERROR AL OBTENER LA INFORMACION DEL PDF: " + e.getMessage());
        e.printStackTrace();  // Opcional, para imprimir el traza del stack si es necesario
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
            closeResources(rs, ps, con);
        }  
        
        
      }
       
       
       
       
       
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
            closeResources(rs, ps, con);
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
  estadoPrevio="";
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
    
      
      
      
      
      
    public void SearchAntecedentesF(){
              
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

    String Busqueda = this.TxtbusquedaAntc.getText();
    String sql = "select * from table_afamiliares  WHERE Enfermedad LIKE '%" + Busqueda + "%'  ORDER by Id_AFamiliares ASC ";
    
         
     
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)jTableAntecedentesF.getModel();
        jTableAntecedentesF.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTAntecedentes();

            while(rs.next()) 
            {
                //LLENADO DE TABLA
               int id = rs.getInt(1);
               String enfermedad = rs.getString(2);
         //      int estadonum = rs.getInt(8);
             
             //  if(estadonum==102){
               Vector vRow=new Vector();
               vRow.add(id);
               vRow.add(enfermedad);
 
               Tabla.addRow(vRow);
               
             //  }
          
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }
        
        
       finally {
            closeResources(rs, ps, con);
        }
        
        
  }
      
      

        
        
         public void SearchAntecedentesP(){
              
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

    String Busqueda = this.TxtBusAntcP.getText();
    String sql = "select * from table_afamiliarespers  WHERE EnfermedadPer LIKE '%" + Busqueda + "%'  ORDER BY EnfermedadPer ASC; ";
    
         
     
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)jTableAntcPers.getModel();
        jTableAntcPers.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTAntecedentesPerso();

            while(rs.next()) 
            {
                //LLENADO DE TABLA
               int id = rs.getInt(1);
               String enfermedad = rs.getString(2);
         //      int estadonum = rs.getInt(8);
             
             //  if(estadonum==102){
               Vector vRow=new Vector();
               vRow.add(id);
               vRow.add(enfermedad);
 
               Tabla.addRow(vRow);
               
             //  }
          
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
  }
        
        

        
        
        
      int cantidad=0;
     public void addAntec1() {
//controlT.AsignarPaciente(JTableLaboratorio, (String) JComboEstudio.getSelectedItem(), 0, modelo);
            


        cantidad=cantidad+1;
        modelo = (DefaultTableModel) jTableAF2.getModel();
    //    Procedimiento = JComboProcedimientos.getSelectedItem().toString();
    
      
            ArrayList lista = new ArrayList();
     
            lista.add(cantidad);
            lista.add(EnfermedadAF);
            lista.add(JtextAntc1.getText());
            Object[] ob = new Object[8];
            ob[0] = lista.get(0);
            ob[1] = lista.get(1);
            ob[2] = lista.get(2);
            modelo.addRow(ob);
            jTableAF2.setModel(modelo);
            jTableAF2.setDefaultEditor(Object.class, null);
    }
      
      
     
     
     
     
     
        int  cantidad2 =0;            
        public void addAntec2() {
//controlT.AsignarPaciente(JTableLaboratorio, (String) JComboEstudio.getSelectedItem(), 0, modelo);
            


        cantidad2=cantidad2+1;
        modelo = (DefaultTableModel) jTableAntpersonal.getModel();
    //    Procedimiento = JComboProcedimientos.getSelectedItem().toString();
    
      
            ArrayList lista = new ArrayList();
     
            lista.add(cantidad2);
            lista.add(TextAntPersonal);
            lista.add(jTextAntPersonales.getText());
            Object[] ob = new Object[8];
            ob[0] = lista.get(0);
            ob[1] = lista.get(1);
            ob[2] = lista.get(2);
            modelo.addRow(ob);
            jTableAntpersonal.setModel(modelo);
            jTableAntpersonal.setDefaultEditor(Object.class, null);
            acomodarAntec2();
    }
      
      
      
        
        
        
        int  cantidadextr1 =0;            
        public void addexamenextra() {
//controlT.AsignarPaciente(JTableLaboratorio, (String) JComboEstudio.getSelectedItem(), 0, modelo);
            


        cantidadextr1=cantidadextr1+1;
        modelo = (DefaultTableModel) jTableAdic.getModel();
    //    Procedimiento = JComboProcedimientos.getSelectedItem().toString();
    
      
            ArrayList lista = new ArrayList();
     
            lista.add(cantidadextr1);
            lista.add(jTextExamenAdic.getText());
            lista.add(jTextObservAdic.getText());
            Object[] ob = new Object[8];
            ob[0] = lista.get(0);
            ob[1] = lista.get(1);
            ob[2] = lista.get(2);
            modelo.addRow(ob);
            jTableAdic.setModel(modelo);
            jTableAdic.setDefaultEditor(Object.class, null);
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
  
       
       
       
  public void updateFGeneral(){                                             
    
      
   // SearchMaxIdConsulta();
      
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {
              
            String sql = "UPDATE examen_fgeneral SET Talla=?, Peso=?, Peso_ideal=?, Peso_Adic=?, IMC=?, Aspecto=?, Abdominal=?, Temperatura=?, Pulso=?, FR=?, Tipo_respiracion=?, Cadera=?, Tension=? WHERE Id_Historia=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
            ps.setString(1,  TXTtalla.getText());
            ps.setString(2,  TXTpeso.getText());
            ps.setString(3,  TXTpesoIdeal.getText());
            ps.setString(4,  TXTpesoAdic.getText());
            ps.setString(5,  TXTimc.getText());
            ps.setString(6,  TXTaspecto.getText());
            ps.setString(7,  TXTabdominal.getText());
            ps.setString(8,  TXTtemperatura.getText());
            ps.setString(9, TXTpulso.getText());
            ps.setString(10, TXTfr.getText());
            ps.setString(11, TXTrespiracion.getText());
            ps.setString(12, TXTcadera.getText());
            ps.setString(13, TXTtension.getText());
            ps.setInt(14, idAsignar);
 
            
            ps.executeUpdate();

            
 
        } catch (Exception e) {
            System.out.println("ERROR EN ACTUALIZAR EXAMEN FGENERAL");
            System.out.println(e);
          //  JOptionPane.showMessageDialog(null, e);
        }
          
           finally {
            closeResources(rs, ps, con);
        }  
          
          
          
          
          
          
            try {
              
            String sql = "UPDATE examen_fisico SET Piel=?, Ojos=?, Oidos=?, Dientes=?, Boca=?, Cuello=?, Corazon=?, Abdomen=?, Columna=?, ExtrInferior=?, Linfaticos=?, Genitales=?, Ano=?, Oftamoscopia=?, Nariz=?, Torax=?, Mamas=?, Pulmones=?, Hernias=?, ExtSuperior=?, Arterias=?, Venas=?, Neurologico=?, Prostata=?, Ginecologo=? WHERE Id_Historia=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      

            ps.setString(1,  TXTpiel.getText());
            ps.setString(2,  TXTojos.getText());
            ps.setString(3,  TXToidos.getText());
            ps.setString(4,  TXTdientes.getText());
            ps.setString(5,  TXTboca.getText());
            ps.setString(6,  TXTcuello.getText());
            ps.setString(7,  TXTcorazon.getText());
            ps.setString(8,  TXTAbdomen2.getText());
            ps.setString(9, TXTcolumna.getText());
            ps.setString(10, TXTinferior.getText());
            ps.setString(11, TXTlinfaticos.getText());
            ps.setString(12, TXTgenitales.getText());
            ps.setString(13, TXTrectal.getText());
            ps.setString(14, TXToftamoscopia.getText());
            ps.setString(15, TXTnariz.getText());
            ps.setString(16, TXTtorax.getText());
            ps.setString(17, TXTmamas.getText());
            ps.setString(18, TXTpulmones.getText());
            ps.setString(19, TXThernias.getText());
            ps.setString(20, TXTsuperior.getText());
            ps.setString(21, TXTarterias.getText());
            ps.setString(22, TXTvenas.getText());
            ps.setString(23, TXTneurologico.getText());
            ps.setString(24, TXTprostata.getText());
            ps.setString(25, TXTginecologo.getText());
            ps.setInt(26, idAsignar);

            
            
            ps.executeUpdate();
  

 
        } catch (Exception e) {
      
            System.out.println("ERROR EN ACTUALIZAR EXAMEN FISICO");
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, e);
        }
          
          
          finally {
            closeResources(rs, ps, con);
        }    

          
          
    } 
     
  
  
  boolean usingData=false;
  public void callHistoryinfo(){
  
      
    usingData = true; 
    
    mostrarFGeneral();
    mostrarFisico();
    mostrarRevision();
    mostrarConclusion();
    
    limpiarShowAntc1();
    ShowAntc1(idtablaHistorias);
            
    limpiarShowAntc2();
    ShowAntc2(idtablaHistorias);
    
    limpiarEAdc();     
    ShowExamAdic(idtablaHistorias);
    
    BtnImprimirHistoria.setEnabled(true);
    BtnGuardarHistoria.setEnabled(true);
    
    JOptionPane.showMessageDialog(null, "LOS DATOS FUERON CARGADOS A HISTORIA CLINICA", "HISTORIA CLINICA", 1);
    
    tabbedAntecedentes.setSelectedIndex(0);
    jTabbedPane2.setSelectedIndex(0);

    
    
  }
  
  
  
  
  
  
     
     public void mostrarFGeneral(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
          
          
      // MOSTRAR EXAMEN FISICO NORMAL    
       try{
    
     //    int fila = JTablePacientes.getSelectedRow();
       
       String query="SELECT * from examen_fgeneral where Id_Historia =?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       TXTtalla.setText(rs.getString("Talla"));
       TXTpeso.setText(rs.getString("Peso"));
       TXTpesoIdeal.setText(rs.getString("Peso_ideal"));
       TXTpesoAdic.setText(rs.getString("Peso_Adic"));
       TXTimc.setText(rs.getString("IMC"));
       TXTaspecto.setText(rs.getString("Aspecto"));
       TXTabdominal.setText(rs.getString("Abdominal"));
       TXTtemperatura.setText(rs.getString("Temperatura"));
       TXTpulso.setText(rs.getString("Pulso"));
       TXTfr.setText(rs.getString("Fr"));
       TXTrespiracion.setText(rs.getString("Tipo_respiracion"));
       TXTcadera.setText(rs.getString("Cadera"));
       TXTtension.setText(rs.getString("Tension"));
       

   
       }
       

       
     
          }
      catch(Exception e){System.out.println("Error en cargar datos a historia "+e);}
         finally {
            closeResources(rs, ps, con);
        }
      }
      
       
  
     
     
     
     
  
   public void mostrarFisico(){
     
   
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          
          
           
 // ________________________________MOSTRAR EXAMEN FISICO COMPLEJO___________________________________
       
       
             try{
    
 //    int fila = JTablePacientes.getSelectedRow();
       
       String query="SELECT * from examen_fisico where Id_Historia=?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
     //  System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       TXTpiel.setText(rs.getString("Piel"));
       TXTojos.setText(rs.getString("Ojos"));
       TXToidos.setText(rs.getString("Oidos"));
       TXTdientes.setText(rs.getString("Dientes"));
       TXTboca.setText(rs.getString("Boca"));
       TXTcuello.setText(rs.getString("Cuello"));
       TXTcorazon.setText(rs.getString("Corazon"));
       TXTAbdomen2.setText(rs.getString("Abdomen"));
       TXTcolumna.setText(rs.getString("Columna"));
       TXTinferior.setText(rs.getString("ExtrInferior"));
       TXTlinfaticos.setText(rs.getString("Linfaticos"));
       TXTgenitales.setText(rs.getString("Genitales"));
       TXTrectal.setText(rs.getString("Ano"));
       TXToftamoscopia.setText(rs.getString("Oftamoscopia"));
       TXTnariz.setText(rs.getString("Nariz"));
       TXTtorax.setText(rs.getString("Torax"));
       TXTmamas.setText(rs.getString("Mamas"));
       TXTpulmones.setText(rs.getString("Pulmones"));
       TXThernias.setText(rs.getString("Hernias"));
       TXTsuperior.setText(rs.getString("ExtSuperior"));
       TXTarterias.setText(rs.getString("Arterias"));
       TXTvenas.setText(rs.getString("Venas"));
       TXTneurologico.setText(rs.getString("Neurologico"));
       TXTprostata.setText(rs.getString("Prostata"));
       TXTginecologo.setText(rs.getString("Ginecologo"));

       }
       
       
      }
             
             
             
             
      catch(Exception e){System.out.println("Error en fisicoc complejo "+e);}
             
        finally {
            closeResources(rs, ps, con);
        }     
       

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
       jTextRevision.setText(rs.getString("Informe_simatologia"));
      
       int menarquiaValue = rs.getInt("Menarquia");
       jSpinnerMenarquia.setValue(menarquiaValue);
       
       jComboMestruacion.setSelectedItem(rs.getString("Mestruacion"));
       TxtCiclosMestru.setText(rs.getString("Ciclos_mestruacion"));
       
       String ultimamest= rs.getString("Ultima_mestruacion");
       FechaMestruacion.setText(ultimamest);

       
       
       
       int partosValue = rs.getInt("Partos");
       jSpartos.setValue(partosValue);
               
       int cesareasValue = rs.getInt("Cesareas");
       jScesareas.setValue(cesareasValue);
               
       int pesoAbortos = rs.getInt("Abortos");
       jSabortos.setValue(pesoAbortos);
                            
       int pesoEmbarazos = rs.getInt("Embarazos");
       jSembarazo.setValue(pesoEmbarazos);
       
       int pesoMenospausia = rs.getInt("Menospausia");
       jSmenospausia.setValue(pesoMenospausia);
       Jtextwoman.setText(rs.getString("Informe_mestruacion"));
       jTextMotivoConsulta.setText(rs.getString("Motivo_Consulta")); 
       jComboHabitos.setSelectedItem(rs.getString("Tiempo_libre"));
       jTConsumoAlcohol.setText(rs.getString("Consumo_alcohol"));
       jTConsumoTabaco.setText(rs.getString("Consumo_tabaco"));
       jTConsumoDrugs.setText(rs.getString("Consumo_estupefacientes"));
       jComboActividad.setSelectedItem(rs.getString("Actividad_fisica"));
       jComboFrec.setSelectedItem(rs.getString("Frecuencia_semanal"));
       jComboSueno.setSelectedItem(rs.getString("Sueno"));
       jComboSexualidad.setSelectedItem(rs.getString("Sexualidad"));
       

       }
       

          }
      catch(Exception e){System.out.println("Error en cargar datos a historia "+e);}
       
        finally {
            closeResources(rs, ps, con);
        }
       

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
            closeResources(rs, ps, con);
        }
      }
      
           
           
   
    
    
 int idValidado;

public void AgregarAntc1() {
   int idValidado = usingData ? idtablaHistorias : idAsignar;

try (Connection con = new EnlaceBd().getConnection();
     PreparedStatement ps = con.prepareStatement("INSERT INTO table_antc1 (Id_Hist, Antc1_Enfer, Antc1_Descrip) VALUES (?, ?, ?)")) {

    ps.setInt(1, idValidado);
    ps.setString(2, EnfermedadAF);
    ps.setString(3, JtextAntc1.getText());

    int res = ps.executeUpdate();

    if (res >= 1) {
        jTextArea15.setText("");
    } 
    // No necesitas manejar un mensaje de error específico para el usuario aquí, 
    // ya que el error será capturado y manejado en el bloque catch
} catch (SQLException e) {
    System.out.println("Error al agregar los antecedentes: " + e);
    JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR LOS ANTECEDENTES", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
}
    
}
    
  
  
  
  public void AgregarAntc2(){
  
 int idValidado = usingData ? idtablaHistorias : idAsignar;

try (Connection con = new EnlaceBd().getConnection();
     PreparedStatement ps = con.prepareStatement("INSERT INTO table_antc2 (Id_Hist, Antc1_Enfer, Antc1_Descrip) VALUES (?, ?, ?)")) {

    ps.setInt(1, idValidado);
    ps.setString(2, TextAntPersonal);
    ps.setString(3, jTextAntPersonales.getText());

    int res = ps.executeUpdate();

    if (res >= 1) {
        jTextAntPersonales.setText("");
    } 
    // No necesitas manejar un mensaje de error específico para el usuario aquí, 
    // ya que el error será capturado y manejado en el bloque catch
} catch (SQLException e) {
    System.out.println("Error al agregar los antecedentes: " + e);
    JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR LOS ANTECEDENTES", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
}
 
        

  }
  
  
  
  
  
  
  
  
  
  public void EliminarAntc(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs;

        try {
 
            String sql = "DELETE FROM table_antc1 WHERE Id_antc = ? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, antcid1);
            int res = ps.executeUpdate();

            if (res >= 1) {
           JOptionPane.showMessageDialog(null, "ANTECEDENTE RETIRADO", "ANTECEDENTE", 1);
             
            } else {
               // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
       
            
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR LOS ANTECEDENTES", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
        
       finally {
            closeResources(null, ps, con);
        }

  } 
           
           
    public void EliminarAntc2(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "DELETE FROM table_antc2 WHERE Id_antc = ? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, antcid2);
            int res = ps.executeUpdate();

            if (res >= 1) {
           JOptionPane.showMessageDialog(null, "ANTECEDENTE RETIRADO", "ANTECEDENTE", 1);
             
            } else {
               // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            
      
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR LOS ANTECEDENTES", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
        
         finally {
            closeResources(rs, ps, con);
        }

  } 
  
  
  
  
  
  
      int validarEadicional;
      public void AgregarExamenAdc(){
  
     if (!usingData) {
        validarEadicional = idAsignar;
    } else {
       validarEadicional = idtablaHistorias;
    }
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
      String sql = "INSERT INTO `table_examenadic`(`id_historiadc`, `Nombre_adc`, `Descripcion_adc`) VALUES (?,?,?)";

      
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, validarEadicional);
            ps.setString(2, jTextExamenAdic.getText());
            ps.setString(3, jTextObservAdic.getText());
        int res = ps.executeUpdate();

        if (res >= 1) {
            // JOptionPane.showMessageDialog(null, "PROCEDIMIENTO COMPLETADO", "ACTUALIZACIÓN DE DATOS", 1);
           jTextObservAdic.setText("");
           jTextExamenAdic.setText("");
        } else {
            // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            
            
            

            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR EL EXAMEN", "EXAMENES", JOptionPane.ERROR_MESSAGE);
           }
        
         finally {
            closeResources(rs, ps, con);
        }
        

  }
  
    
    
       public void ShowExamAdic(int idadc) {

       

        List<JCAntecedentesFamiliares> lista = AntcDao.showEAdc(idadc);
        modelo = (DefaultTableModel) jTableAdic.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getShowEadc();
            ob[1] = lista.get(i).getShowNameAdc();
            ob[2] = lista.get(i).getShowDescriptionAdc();
   
            modelo.addRow(ob);

        }
       jTableAdic.setModel(modelo);
       jTableAdic.setDefaultEditor(Object.class, null);
       acomodarEadd();
            

    }
    
    
          public  void limpiarEAdc() {
        DefaultTableModel tb = (DefaultTableModel)  jTableAdic.getModel();
        int a =  jTableAdic.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

    
    
     public void modificarEadc(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
           
 
            String sql = "update table_examenadic set   Nombre_adc=?, Descripcion_adc=? where  id_adcional=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, jTextExamenAdic.getText());
            ps.setString(2, jTextObservAdic.getText());
            ps.setInt(3, idEadc);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "EXAMEN ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EXAMEN", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }
        
         finally {
            closeResources(rs, ps, con);
        }   
                 
 
 
 }

          
          
          

    
  public void EliminarEadc(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "DELETE FROM table_examenadic WHERE id_adcional = ? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEadc);
            int res = ps.executeUpdate();

            if (res >= 1) {
           JOptionPane.showMessageDialog(null, "EXAMEN RETIRADO", "EXAMEN", 1);
             
            } else {
               // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
           
            
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR EL EXAMEN", "EXAMEN", JOptionPane.ERROR_MESSAGE);
           }
        
        
     finally {
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
        if (existeRegistro(con, "examen_fgeneral", "Id_Historia", idAsignar)) {
            updateFGeneral();
        } else {
            insertarFisico();
            updateFGeneral();
        }

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
    }finally {
            closeResources(rs, ps, con);
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
         
           }   finally {
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
         
           }   finally {
            closeResources(rs, ps, con);
        }
  }
  
  

  
   
  
         
 public void modificarShowAntc1(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
           
 
            String sql = "update table_antc1 set    Antc1_Descrip=? where Antc1_Enfer=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, JtextAntc1.getText());
            ps.setString(2, ShowName1);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "ANTECEDENTE ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
           
            
        } catch (Exception e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }   finally {
            closeResources(rs, ps, con);
        }
                 
 
 
 }

           
  
  public void modificarShowAntc2(){
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
            
            String sql = "update table_antc2 set    Antc1_Descrip=? where Antc1_Enfer=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, jTextAntPersonales.getText());
            ps.setString(2, ShowName2);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "ANTECEDENTE ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
           
            
        } catch (Exception e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }   finally {
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
         
           }   finally {
            closeResources(rs, ps, con);
        }

  }
  
  
  
public void updateRevision() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null; 

    int Menarquia = (int) jSpinnerMenarquia.getValue();
    int Partos = (int) jSpartos.getValue();
    int Cesareas = (int) jScesareas.getValue();
    int Abortos = (int) jSabortos.getValue();
    int Embarazo = (int) jSembarazo.getValue();
    int Menospausia = (int) jSmenospausia.getValue();

    try {
        String sql = "UPDATE `table_revision` SET  `Menarquia`=?, `Mestruacion`=?, `Ciclos_mestruacion`=?, `Ultima_mestruacion`=?, `Partos`=?, `Cesareas`=?, `Abortos`=?, `Embarazos`=?, `Menospausia`=?, `Informe_mestruacion`=?, `Informe_simatologia`=?, `Motivo_Consulta`=?,`Tiempo_libre`=?, `Consumo_alcohol`=?, `Consumo_tabaco`=?, `Consumo_estupefacientes`=?, `Actividad_fisica`=?, `Frecuencia_semanal`=?, `Sueno`=?, `Sexualidad`=? WHERE `Id_historiaR`=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, Menarquia);
        ps.setString(2, jComboMestruacion.getSelectedItem().toString());
        ps.setString(3, TxtCiclosMestru.getText());
        ps.setString(4, FechaMestruacion.getText());
        ps.setInt(5, Partos);
        ps.setInt(6, Cesareas);
        ps.setInt(7, Abortos);
        ps.setInt(8, Embarazo);
        ps.setInt(9, Menospausia);
        ps.setString(10, Jtextwoman.getText());
        ps.setString(11, jTextRevision.getText());
        ps.setString(12, jTextMotivoConsulta.getText());
        ps.setString(13, jComboHabitos.getSelectedItem().toString());
        ps.setString(14, jTConsumoAlcohol.getText());
        ps.setString(15, jTConsumoTabaco.getText());
        ps.setString(16, jTConsumoDrugs.getText());
        ps.setString(17, jComboActividad.getSelectedItem().toString());
        ps.setString(18, jComboFrec.getSelectedItem().toString());
        ps.setString(19, jComboSueno.getSelectedItem().toString());
        ps.setString(20, jComboSexualidad.getSelectedItem().toString());
        ps.setInt(21, idAsignar);

        ps.executeUpdate();


    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR REVISION: " + e);
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
         
           }   finally {
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
    }   finally {
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
                    
                    System.out.println(ex);
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
     
       
   
     
     
     
     
     
          public void acomodarAntec1()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTableAF2.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTableAF2.setRowHeight(20);
        
        jTableAF2.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableAF2.getColumnModel().getColumn(0).setMinWidth(0);
        jTableAF2.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
         //jTableAF2.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(5);
         jTableAF2.getColumnModel().getColumn(Tabla.findColumn("Enfermedad")).setPreferredWidth(100);
         jTableAF2.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setPreferredWidth(250);



      
       // jTableAF2.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTableAF2.getColumnModel().getColumn(Tabla.findColumn("Enfermedad")).setCellRenderer(Alinear);
        jTableAF2.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setCellRenderer(Alinear);;
      

    }
     
     
          
          
    
     
          public void acomodarAntec2()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTableAntpersonal.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTableAntpersonal.setRowHeight(20);
        
  
        jTableAntpersonal.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableAntpersonal.getColumnModel().getColumn(0).setMinWidth(0);
        jTableAntpersonal.getColumnModel().getColumn(0).setPreferredWidth(0);
       
         //jTableAF2.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(5);
        jTableAntpersonal.getColumnModel().getColumn(Tabla.findColumn("Enfermedad")).setPreferredWidth(100);
        jTableAntpersonal.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setPreferredWidth(250);

        //jTableAF2.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTableAntpersonal.getColumnModel().getColumn(Tabla.findColumn("Enfermedad")).setCellRenderer(Alinear);
        jTableAntpersonal.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setCellRenderer(Alinear);;
      

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
     
     
 
     
        
         
      public void acomodarEadd()
    {
     
       DefaultTableModel Tabla = (DefaultTableModel)jTableAdic.getModel();
       DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
       Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
       
       jTableAdic.getColumnModel().getColumn(0).setMaxWidth(0);
       jTableAdic.getColumnModel().getColumn(0).setMinWidth(0);
       jTableAdic.getColumnModel().getColumn(0).setPreferredWidth(0);
       
   
       jTableAdic.getColumnModel().getColumn(Tabla.findColumn("Examen")).setPreferredWidth(150);
       jTableAdic.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setPreferredWidth(300);

   
       jTableAdic.getColumnModel().getColumn(Tabla.findColumn("Examen")).setCellRenderer(Alinear);;
       jTableAdic.getColumnModel().getColumn(Tabla.findColumn("Descripción")).setCellRenderer(Alinear);
    
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
     limpiarTAntecedentesF3();
     limpiarTAntecedentesF2();
     JtextAntc1.setText("");
     jTextAntPersonales.setText("");
     jCheckConstitucional.setSelected(false);
     jCheckPielMucosa.setSelected(false);
     jCheckORL.setSelected(false);
     jCheckRespiratorio.setSelected(false);
     jCheckAngina.setSelected(false);
     jCheckBox9.setSelected(false);
     jCheckBox16.setSelected(false);
     jCheckBox20.setSelected(false);
     jCheckBox6.setSelected(false);
     jCheckBox10.setSelected(false);
     jCheckBox17.setSelected(false);
     jCheckBox7.setSelected(false);
     jCheckBox11.setSelected(false);
     jCheckBox15.setSelected(false);
     jCheckBox1.setSelected(false);
     jCheckBox12.setSelected(false);
     jCheckBox14.setSelected(false);
     jCheckBox27.setSelected(false);
     jCheckBox18.setSelected(false);
     jCheckBox19.setSelected(false);
     jCheckBox25.setSelected(false);
     jCheckBox22.setSelected(false);
     jCheckBox23.setSelected(false);
     jCheckBox24.setSelected(false);
     jCheckBox28.setSelected(false);
     jTextRevision.setText("");
     Jtextwoman.setText("");
     jTConsumoTabaco.setText("");
     jTConsumoDrugs.setText("");
     TXTtalla.setText(String.valueOf(0));
     TXTpeso.setText(String.valueOf(0));
     TXTtemperatura.setText("");
     TXTpulso.setText("");
     TXTpesoIdeal.setText("");
     TXTpesoAdic.setText("");
     TXTimc.setText("");
     TXTfr.setText("");
     TXTrespiracion.setText("");
     TXTaspecto.setText("");
     TXTtension.setText("");
     TXTabdominal.setText("");
     TXTcadera.setText("");
     TXTpiel.setText("");
     TXTojos.setText("");
     TXToidos.setText("");
     TXTdientes.setText("");
     TXTboca.setText("");
     TXTcuello.setText("");
     TXTcorazon.setText("");
     TXTAbdomen2.setText("");
     TXTcolumna.setText("");
     TXTinferior.setText("");
     TXTlinfaticos.setText("");
     TXTgenitales.setText("");
     TXTrectal.setText("");
     TXToftamoscopia.setText("");
     TXTnariz.setText("");
     TXTtorax.setText("");
     TXTmamas.setText("");
     TXTpulmones.setText("");
     TXThernias.setText("");
     TXTsuperior.setText("");
     TXTarterias.setText("");
     TXTvenas.setText("");
     TXTneurologico.setText("");
     TXTprostata.setText("");
     TXTginecologo.setText("");
     jTextExamenAdic.setText("");
     jTextObservAdic.setText("");
     jTextConclusion.setText("");
     jTextRecomendacion.setText("");
     jComboBox2.setSelectedItem("Buenas condiciones de salud");
     jComboMestruacion.setSelectedItem("N/A");
     jComboHabitos.setSelectedItem("N/A");
     jComboActividad.setSelectedItem("N/A");
     jComboFrec.setSelectedItem("N/A");
     jComboSueno.setSelectedItem("N/A");
     jComboSexualidad.setSelectedItem("N/A");
     jTConsumoAlcohol.setText("");
     TxtCiclosMestru.setText("");
     limpiarTEAdd();   

     jSpinnerMenarquia.setValue(0);
     jSpartos.setValue(0);
     jScesareas.setValue(0);
     jSabortos.setValue(0);
     jSembarazo.setValue(0);
     jSmenospausia.setValue(0);
     
     
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
    private javax.swing.JButton BtnModifAnt;
    private javax.swing.JButton BtnModifAnt2;
    private javax.swing.JButton BtnModifProc;
    private javax.swing.JButton BtnModifProc1;
    private javax.swing.JButton BtnProceso;
    private javax.swing.JButton BtnRetirar;
    private javax.swing.JButton BtnVisualizar;
    private javax.swing.JMenuItem Completar;
    private com.toedter.calendar.JDateChooser FechaAsignar1;
    private javax.swing.JFormattedTextField FechaMestruacion;
    private javax.swing.JPanel JMotivo;
    private javax.swing.JPanel JPanelAnteriores;
    private javax.swing.JPanel JPanelHistoria;
    public javax.swing.JTable JTablePacientes;
    private javax.swing.JTextArea JtextAntc1;
    private javax.swing.JTextArea Jtextwoman;
    private javax.swing.JMenuItem Pendiente;
    private javax.swing.JPopupMenu PopupEstado;
    private javax.swing.JPopupMenu PopupHistorias;
    private javax.swing.JTextField TXTAbdomen2;
    private javax.swing.JTextField TXTabdominal;
    private javax.swing.JTextField TXTarterias;
    private javax.swing.JTextField TXTaspecto;
    private javax.swing.JTextField TXTboca;
    private javax.swing.JTextField TXTcadera;
    private javax.swing.JTextField TXTcolumna;
    private javax.swing.JTextField TXTcorazon;
    private javax.swing.JTextField TXTcuello;
    private javax.swing.JTextField TXTdientes;
    private javax.swing.JTextField TXTfr;
    private javax.swing.JTextField TXTgenitales;
    private javax.swing.JTextField TXTginecologo;
    private javax.swing.JTextField TXThernias;
    private javax.swing.JTextField TXTimc;
    private javax.swing.JTextField TXTinferior;
    private javax.swing.JTextField TXTlinfaticos;
    private javax.swing.JTextField TXTmamas;
    private javax.swing.JTextField TXTnariz;
    private javax.swing.JTextField TXTneurologico;
    private javax.swing.JTextField TXToftamoscopia;
    private javax.swing.JTextField TXToidos;
    private javax.swing.JTextField TXTojos;
    private javax.swing.JTextField TXTpeso;
    private javax.swing.JTextField TXTpesoAdic;
    private javax.swing.JTextField TXTpesoIdeal;
    private javax.swing.JTextField TXTpiel;
    private javax.swing.JTextField TXTprostata;
    private javax.swing.JTextField TXTpulmones;
    private javax.swing.JTextField TXTpulso;
    private javax.swing.JTextField TXTrectal;
    private javax.swing.JTextField TXTrespiracion;
    private javax.swing.JTextField TXTsuperior;
    private javax.swing.JTextField TXTtalla;
    private javax.swing.JTextField TXTtemperatura;
    private javax.swing.JTextField TXTtension;
    private javax.swing.JTextField TXTtorax;
    private javax.swing.JTextField TXTvenas;
    private javax.swing.JTextField TxtBusAntcP;
    private javax.swing.JTextField TxtCiclosMestru;
    private javax.swing.JTextField Txtbusqueda;
    private javax.swing.JTextField TxtbusquedaAntc;
    private javax.swing.JButton jAButton30;
    private javax.swing.JButton jAdcDelete;
    private javax.swing.JButton jAdcModif;
    private javax.swing.JButton jBtnRetirarAntc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton26;
    private javax.swing.JCheckBox jCheckAngina;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBox jCheckConstitucional;
    private javax.swing.JCheckBox jCheckORL;
    private javax.swing.JCheckBox jCheckPielMucosa;
    private javax.swing.JCheckBox jCheckRespiratorio;
    private javax.swing.JComboBox<String> jComboActividad;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboFrec;
    private javax.swing.JComboBox<String> jComboHabitos;
    private javax.swing.JComboBox<String> jComboMestruacion;
    private javax.swing.JComboBox<String> jComboSexualidad;
    private javax.swing.JComboBox<String> jComboSueno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPaciente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioAF;
    private javax.swing.JRadioButton jRadioAPP;
    private javax.swing.JButton jRetirarAntc2;
    private javax.swing.JSpinner jSabortos;
    private javax.swing.JSpinner jScesareas;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSembarazo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSpinner jSmenospausia;
    private javax.swing.JSpinner jSpartos;
    private javax.swing.JSpinner jSpinnerMenarquia;
    private javax.swing.JTextArea jTConsumoAlcohol;
    private javax.swing.JTextArea jTConsumoDrugs;
    private javax.swing.JTextArea jTConsumoTabaco;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableAF2;
    private javax.swing.JTable jTableAdic;
    private javax.swing.JTable jTableAntcPers;
    private javax.swing.JTable jTableAntecedentesF;
    private javax.swing.JTable jTableAntpersonal;
    private javax.swing.JTable jTableProcedimientos;
    private javax.swing.JTable jTableVisualizarC;
    private javax.swing.JTextArea jTextAntPersonales;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextConclusion;
    private javax.swing.JTextField jTextExamenAdic;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextMotivoConsulta;
    private javax.swing.JTextArea jTextObservAdic;
    private javax.swing.JTextArea jTextRecomendacion;
    private javax.swing.JTextArea jTextRevision;
    private javax.swing.JMenuItem jUtilizarHisto;
    private javax.swing.JTabbedPane tabbedAntecedentes;
    // End of variables declaration//GEN-END:variables
}
