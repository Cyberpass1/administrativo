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
import com.itextpdf.text.BadElementException;
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
import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileTime;
import java.sql.Blob;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Footer;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageable;

public class JConsultasOdontologia extends javax.swing.JInternalFrame implements Runnable  {



    Calendar Fecha;
 
    
    public JConsultasOdontologia() {
        
      initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Fecha = new GregorianCalendar();

  obtenerFecha();

        Txtbusqueda.setText("V-");
        // Aquí puedes mantener el resto de las inicializaciones
        informacionpdf();
        ActivarFirma();
        searchOdontogram();
        llenarResultado();
        limpiarTAntecedentes();
        listarAntecedentes();
        jRadioAF.setSelected(true);
        jRadioAPP.setSelected(false);

        JTablePacientes.getTableHeader().setReorderingAllowed(false);
        JTablePacientes.requestFocusInWindow();
        Odontologia.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTableOdontograma.getTableHeader().setReorderingAllowed(false);
        iniciarActualizacionAutomatica();
    }

    

    
    
     public void activarDate(){
    Thread t = new Thread(new Runnable() {
    @Override
    public void run() {
        try {
          
            Thread.sleep(15000);
            
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
        Odontologia = new javax.swing.JTabbedPane();
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
        Anamnesis = new javax.swing.JPanel();
        jTransmision = new javax.swing.JCheckBox();
        TXT_SEXUAL = new javax.swing.JTextField();
        TXT_infecciosa = new javax.swing.JTextField();
        jInfecciosas = new javax.swing.JCheckBox();
        TXT_reumatica = new javax.swing.JTextField();
        jFiebre = new javax.swing.JCheckBox();
        TXT_Jaqueca = new javax.swing.JTextField();
        jMigrañas = new javax.swing.JCheckBox();
        TXT_Hipertension = new javax.swing.JTextField();
        jHipertension = new javax.swing.JCheckBox();
        TXT_diabetes = new javax.swing.JTextField();
        jDiabetes = new javax.swing.JCheckBox();
        TXT_Respiratorias = new javax.swing.JTextField();
        jRespiratorias = new javax.swing.JCheckBox();
        TXT_asma = new javax.swing.JTextField();
        jAsma = new javax.swing.JCheckBox();
        TXT_Hepaticas = new javax.swing.JTextField();
        jHepaticas = new javax.swing.JCheckBox();
        Txt_renales = new javax.swing.JTextField();
        jRenales = new javax.swing.JCheckBox();
        Txt_Anemia = new javax.swing.JTextField();
        jAnemia = new javax.swing.JCheckBox();
        Txt_Hemorragia = new javax.swing.JTextField();
        JHemorragias = new javax.swing.JCheckBox();
        JCardiovascular = new javax.swing.JCheckBox();
        Txt_Alteraciones = new javax.swing.JTextField();
        TXT_Zumbidos = new javax.swing.JTextField();
        jZumbidos = new javax.swing.JCheckBox();
        jArticulaciones = new javax.swing.JCheckBox();
        TXT_inflamacion = new javax.swing.JTextField();
        TXT_sinusitis = new javax.swing.JTextField();
        jSinusitis = new javax.swing.JCheckBox();
        jRespiradorBucal = new javax.swing.JCheckBox();
        TXT_respiradorbucal = new javax.swing.JTextField();
        TXT_Congenita = new javax.swing.JTextField();
        jCongenita = new javax.swing.JCheckBox();
        jHormonal = new javax.swing.JCheckBox();
        TXT_Hormonal = new javax.swing.JTextField();
        TXT_GraveEnfermo = new javax.swing.JTextField();
        jGrave = new javax.swing.JCheckBox();
        jGastro = new javax.swing.JCheckBox();
        TXT_Gastrointestinal = new javax.swing.JTextField();
        TXT_Alimento = new javax.swing.JTextField();
        jAlimento = new javax.swing.JCheckBox();
        TXT_Cansansio = new javax.swing.JTextField();
        jCansansio = new javax.swing.JCheckBox();
        TXT_Tratamiento = new javax.swing.JTextField();
        jMedicamento = new javax.swing.JCheckBox();
        TXT_Orina = new javax.swing.JTextField();
        jOrina = new javax.swing.JCheckBox();
        TXT_Aspirina = new javax.swing.JTextField();
        jAspirina = new javax.swing.JCheckBox();
        TXT_Agua = new javax.swing.JTextField();
        jVasosAgua = new javax.swing.JCheckBox();
        jQuirurgica = new javax.swing.JCheckBox();
        TXT_Intervenido = new javax.swing.JTextField();
        Habitoss = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
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
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTConsumoDrugs = new javax.swing.JTextArea();
        jComboSexualidad = new javax.swing.JComboBox<>();
        jComboActividad = new javax.swing.JComboBox<>();
        jComboFrec = new javax.swing.JComboBox<>();
        jComboSueno = new javax.swing.JComboBox<>();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTConsumoTabaco = new javax.swing.JTextArea();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTConsumoAlcohol = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerMenarquia = new javax.swing.JSpinner();
        jSmenospausia = new javax.swing.JSpinner();
        jSpartos = new javax.swing.JSpinner();
        jScesareas = new javax.swing.JSpinner();
        jSembarazo = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jComboMestruacion = new javax.swing.JComboBox<>();
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
        jEmbarazo = new javax.swing.JCheckBox();
        TXT_Embarazo = new javax.swing.JTextField();
        jTerapiaHormonal = new javax.swing.JCheckBox();
        TXT_TerapiaHormonal = new javax.swing.JTextField();
        jHalitosis = new javax.swing.JCheckBox();
        jReaccionAnestecia = new javax.swing.JCheckBox();
        TXT_ReaccionAnestecia = new javax.swing.JTextField();
        TXT_halitosis = new javax.swing.JTextField();
        jSatisfecho = new javax.swing.JCheckBox();
        TXT_Apariencia = new javax.swing.JTextField();
        TXT_Alimentos = new javax.swing.JTextField();
        jConsumoAcido = new javax.swing.JCheckBox();
        TXT_Sensibilidad = new javax.swing.JTextField();
        jSensibilidad = new javax.swing.JCheckBox();
        TXT_MovilidadDientes = new javax.swing.JTextField();
        jDientesM = new javax.swing.JCheckBox();
        TXT_Encias = new javax.swing.JTextField();
        jSangranEncias = new javax.swing.JCheckBox();
        jresequedad = new javax.swing.JCheckBox();
        TXTResequedad = new javax.swing.JTextField();
        jUlceraciones = new javax.swing.JCheckBox();
        TXT_Ulceraciones = new javax.swing.JTextField();
        jNingunaAnteriores = new javax.swing.JCheckBox();
        TXT_Mandibula = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        TXT_Observaciones = new javax.swing.JTextArea();
        jMandibula = new javax.swing.JCheckBox();
        TXT_Alergico = new javax.swing.JTextField();
        jAlergia = new javax.swing.JCheckBox();
        Habitos = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jRadioAF = new javax.swing.JRadioButton();
        jRadioAPP = new javax.swing.JRadioButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        Antecedentes = new javax.swing.JPanel();
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
        jPanel8 = new javax.swing.JPanel();
        TXTtemperatura = new javax.swing.JTextField();
        TXTtalla = new javax.swing.JTextField();
        TXTpeso = new javax.swing.JTextField();
        TXTpulso = new javax.swing.JTextField();
        TXTfr = new javax.swing.JTextField();
        TXTpesoIdeal = new javax.swing.JTextField();
        TXTpesoAdic = new javax.swing.JTextField();
        TXTrespiracion = new javax.swing.JTextField();
        TXTimc = new javax.swing.JTextField();
        TXTtension = new javax.swing.JTextField();
        TXTaspecto = new javax.swing.JTextField();
        TXTabdominal = new javax.swing.JTextField();
        TXTcadera = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        MuscularTxt = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        AspectoTxt = new javax.swing.JTextArea();
        jScrollPane25 = new javax.swing.JScrollPane();
        PaladarTxt = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        TemporomandibularTxt = new javax.swing.JTextArea();
        jScrollPane28 = new javax.swing.JScrollPane();
        AmigdalinaTxt = new javax.swing.JTextArea();
        jScrollPane29 = new javax.swing.JScrollPane();
        SalivaTxt = new javax.swing.JTextArea();
        jScrollPane30 = new javax.swing.JScrollPane();
        ComisuraTxt = new javax.swing.JTextArea();
        jScrollPane37 = new javax.swing.JScrollPane();
        LenguaTxt = new javax.swing.JTextArea();
        jScrollPane38 = new javax.swing.JScrollPane();
        GangliosTxt = new javax.swing.JTextArea();
        jScrollPane39 = new javax.swing.JScrollPane();
        TxtTiroidea = new javax.swing.JTextArea();
        jScrollPane40 = new javax.swing.JScrollPane();
        CarillosTxt = new javax.swing.JTextArea();
        jScrollPane41 = new javax.swing.JScrollPane();
        MaxilaresTxt = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextRadiologica = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jClatidos = new javax.swing.JCheckBox();
        jCpresente = new javax.swing.JCheckBox();
        jCespontaneo = new javax.swing.JCheckBox();
        jRsevero = new javax.swing.JRadioButton();
        jRleve = new javax.swing.JRadioButton();
        jRmoderado = new javax.swing.JRadioButton();
        jRlocalizado = new javax.swing.JRadioButton();
        jRdifuso = new javax.swing.JRadioButton();
        jRcontinuo = new javax.swing.JRadioButton();
        jRintermitente = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jCpalpitacion = new javax.swing.JCheckBox();
        jCextraoral = new javax.swing.JCheckBox();
        jCfractura = new javax.swing.JCheckBox();
        jCemento = new javax.swing.JCheckBox();
        jCfistula = new javax.swing.JCheckBox();
        jCorona = new javax.swing.JCheckBox();
        jCintraOral = new javax.swing.JCheckBox();
        jCincrustacion = new javax.swing.JCheckBox();
        jCabrasion = new javax.swing.JCheckBox();
        jCdienteOscuro = new javax.swing.JCheckBox();
        jCdienteMovil = new javax.swing.JCheckBox();
        jCrc = new javax.swing.JCheckBox();
        jCamalgama = new javax.swing.JCheckBox();
        jCadenopatia = new javax.swing.JCheckBox();
        jCarie = new javax.swing.JCheckBox();
        jTxtOtros = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        JtxtInforme = new javax.swing.JTextArea();
        jScrollPane36 = new javax.swing.JScrollPane();
        jTableOdontograma = new javax.swing.JTable();
        btnRetirarOdonto = new javax.swing.JButton();
        btnAgregarOdonto = new javax.swing.JButton();
        btnModificarOdonto = new javax.swing.JButton();
        jComboDiente = new javax.swing.JComboBox<>();
        btnCancerlarOdonto = new javax.swing.JButton();
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
        jimgOdontograma = new javax.swing.JCheckBox();
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

        Odontologia.setBackground(new java.awt.Color(255, 255, 255));
        Odontologia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Odontologia.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

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
        });
        jScrollPane20.setViewportView(jTextMotivoConsulta);

        JMotivo.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 880, 210));

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

        JMotivo.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 420, 190));

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

        JMotivo.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 880, 130));

        jTextArea15.setColumns(20);
        jTextArea15.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextArea15.setLineWrap(true);
        jTextArea15.setRows(5);
        jTextArea15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Observaciones"));
        jScrollPane13.setViewportView(jTextArea15);

        JMotivo.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 370, 190));

        BtnModifProc1.setText("Nuevo");
        BtnModifProc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifProc1.setContentAreaFilled(false);
        BtnModifProc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifProc1ActionPerformed(evt);
            }
        });
        JMotivo.add(BtnModifProc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, 70, 30));

        BtnModifProc.setText("Modificar");
        BtnModifProc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifProc.setContentAreaFilled(false);
        BtnModifProc.setEnabled(false);
        BtnModifProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifProcActionPerformed(evt);
            }
        });
        JMotivo.add(BtnModifProc, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, 70, 30));

        BtnProceso.setText("Guardar");
        BtnProceso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnProceso.setContentAreaFilled(false);
        BtnProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProcesoActionPerformed(evt);
            }
        });
        JMotivo.add(BtnProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 70, 30));

        BtnRetirar.setText("Retirar");
        BtnRetirar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnRetirar.setContentAreaFilled(false);
        BtnRetirar.setEnabled(false);
        BtnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRetirarActionPerformed(evt);
            }
        });
        JMotivo.add(BtnRetirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 70, 30));

        Odontologia.addTab(" Motivo & Procedimientos |", JMotivo);

        Anamnesis.setBackground(new java.awt.Color(255, 255, 255));
        Anamnesis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTransmision.setBackground(new java.awt.Color(255, 255, 255));
        jTransmision.setText("SI");
        jTransmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransmisionActionPerformed(evt);
            }
        });
        Anamnesis.add(jTransmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, 30));

        TXT_SEXUAL.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_SEXUAL.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades de transmisión sexual? "));
        TXT_SEXUAL.setEnabled(false);
        Anamnesis.add(TXT_SEXUAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 390, -1));

        TXT_infecciosa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_infecciosa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades infecciosas? "));
        TXT_infecciosa.setEnabled(false);
        Anamnesis.add(TXT_infecciosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 390, -1));

        jInfecciosas.setBackground(new java.awt.Color(255, 255, 255));
        jInfecciosas.setText("SI");
        jInfecciosas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInfecciosasActionPerformed(evt);
            }
        });
        Anamnesis.add(jInfecciosas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, 30));

        TXT_reumatica.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_reumatica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Fiebre reumática? "));
        TXT_reumatica.setEnabled(false);
        Anamnesis.add(TXT_reumatica, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 390, -1));

        jFiebre.setBackground(new java.awt.Color(255, 255, 255));
        jFiebre.setText("SI");
        jFiebre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFiebreActionPerformed(evt);
            }
        });
        Anamnesis.add(jFiebre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 30));

        TXT_Jaqueca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Jaqueca.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Dolores de cabeza frecuentes, migrañas, jaquecas? "));
        TXT_Jaqueca.setEnabled(false);
        Anamnesis.add(TXT_Jaqueca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 390, -1));

        jMigrañas.setBackground(new java.awt.Color(255, 255, 255));
        jMigrañas.setText("SI");
        jMigrañas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMigrañasActionPerformed(evt);
            }
        });
        Anamnesis.add(jMigrañas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 30));

        TXT_Hipertension.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Hipertension.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Hipertensión?"));
        TXT_Hipertension.setEnabled(false);
        Anamnesis.add(TXT_Hipertension, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 390, -1));

        jHipertension.setBackground(new java.awt.Color(255, 255, 255));
        jHipertension.setText("SI");
        jHipertension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHipertensionActionPerformed(evt);
            }
        });
        Anamnesis.add(jHipertension, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, 30));

        TXT_diabetes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_diabetes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Diabetes Mellitus? "));
        TXT_diabetes.setEnabled(false);
        Anamnesis.add(TXT_diabetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 390, -1));

        jDiabetes.setBackground(new java.awt.Color(255, 255, 255));
        jDiabetes.setText("SI");
        jDiabetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiabetesActionPerformed(evt);
            }
        });
        Anamnesis.add(jDiabetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 30));

        TXT_Respiratorias.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Respiratorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades respiratorias?"));
        TXT_Respiratorias.setEnabled(false);
        Anamnesis.add(TXT_Respiratorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 390, -1));

        jRespiratorias.setBackground(new java.awt.Color(255, 255, 255));
        jRespiratorias.setText("SI");
        jRespiratorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRespiratoriasActionPerformed(evt);
            }
        });
        Anamnesis.add(jRespiratorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 30));

        TXT_asma.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_asma.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Asma o alguna dificultad para respirar?"));
        TXT_asma.setEnabled(false);
        Anamnesis.add(TXT_asma, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 390, -1));

        jAsma.setBackground(new java.awt.Color(255, 255, 255));
        jAsma.setText("SI");
        jAsma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAsmaActionPerformed(evt);
            }
        });
        Anamnesis.add(jAsma, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        TXT_Hepaticas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Hepaticas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades hepáticas? "));
        TXT_Hepaticas.setEnabled(false);
        Anamnesis.add(TXT_Hepaticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 390, -1));

        jHepaticas.setBackground(new java.awt.Color(255, 255, 255));
        jHepaticas.setText("SI");
        jHepaticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHepaticasActionPerformed(evt);
            }
        });
        Anamnesis.add(jHepaticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 30));

        Txt_renales.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Txt_renales.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades renales? "));
        Txt_renales.setEnabled(false);
        Anamnesis.add(Txt_renales, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 390, -1));

        jRenales.setBackground(new java.awt.Color(255, 255, 255));
        jRenales.setText("SI");
        jRenales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRenalesActionPerformed(evt);
            }
        });
        Anamnesis.add(jRenales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 30));

        Txt_Anemia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Txt_Anemia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Anemia u otra alteración sanguínea? "));
        Txt_Anemia.setEnabled(false);
        Anamnesis.add(Txt_Anemia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 390, -1));

        jAnemia.setBackground(new java.awt.Color(255, 255, 255));
        jAnemia.setText("SI");
        jAnemia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAnemiaActionPerformed(evt);
            }
        });
        Anamnesis.add(jAnemia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        Txt_Hemorragia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Txt_Hemorragia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Hemorragias o sangrados frecuentes? "));
        Txt_Hemorragia.setEnabled(false);
        Anamnesis.add(Txt_Hemorragia, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 390, -1));

        JHemorragias.setBackground(new java.awt.Color(255, 255, 255));
        JHemorragias.setText("SI");
        JHemorragias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JHemorragiasActionPerformed(evt);
            }
        });
        Anamnesis.add(JHemorragias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        JCardiovascular.setBackground(new java.awt.Color(255, 255, 255));
        JCardiovascular.setText("SI");
        JCardiovascular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCardiovascularActionPerformed(evt);
            }
        });
        Anamnesis.add(JCardiovascular, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        Txt_Alteraciones.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        Txt_Alteraciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Alteraciones cardiovasculares?"));
        Txt_Alteraciones.setEnabled(false);
        Anamnesis.add(Txt_Alteraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 390, -1));

        TXT_Zumbidos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Zumbidos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Dolores de oído frecuentes o zumbidos? "));
        TXT_Zumbidos.setEnabled(false);
        Anamnesis.add(TXT_Zumbidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 390, -1));

        jZumbidos.setBackground(new java.awt.Color(255, 255, 255));
        jZumbidos.setText("SI");
        jZumbidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jZumbidosActionPerformed(evt);
            }
        });
        Anamnesis.add(jZumbidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, 30));

        jArticulaciones.setBackground(new java.awt.Color(255, 255, 255));
        jArticulaciones.setText("SI");
        jArticulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jArticulacionesActionPerformed(evt);
            }
        });
        Anamnesis.add(jArticulaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, 30));

        TXT_inflamacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_inflamacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Se le inflaman los pies o articulaciones frecuentemente? "));
        TXT_inflamacion.setEnabled(false);
        Anamnesis.add(TXT_inflamacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 390, -1));

        TXT_sinusitis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_sinusitis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Enfermedades de los senos paranasales (sinusitis)? "));
        TXT_sinusitis.setEnabled(false);
        Anamnesis.add(TXT_sinusitis, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 390, -1));

        jSinusitis.setBackground(new java.awt.Color(255, 255, 255));
        jSinusitis.setText("SI");
        jSinusitis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSinusitisActionPerformed(evt);
            }
        });
        Anamnesis.add(jSinusitis, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, 30));

        jRespiradorBucal.setBackground(new java.awt.Color(255, 255, 255));
        jRespiradorBucal.setText("SI");
        jRespiradorBucal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRespiradorBucalActionPerformed(evt);
            }
        });
        Anamnesis.add(jRespiradorBucal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, 30));

        TXT_respiradorbucal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_respiradorbucal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Respirador bucal? "));
        TXT_respiradorbucal.setEnabled(false);
        Anamnesis.add(TXT_respiradorbucal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 390, -1));

        TXT_Congenita.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Congenita.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Padece usted alguna enfermedad congénita?"));
        TXT_Congenita.setEnabled(false);
        Anamnesis.add(TXT_Congenita, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 390, -1));

        jCongenita.setBackground(new java.awt.Color(255, 255, 255));
        jCongenita.setText("SI");
        jCongenita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCongenitaActionPerformed(evt);
            }
        });
        Anamnesis.add(jCongenita, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, -1, 30));

        jHormonal.setBackground(new java.awt.Color(255, 255, 255));
        jHormonal.setText("SI");
        jHormonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHormonalActionPerformed(evt);
            }
        });
        Anamnesis.add(jHormonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, 30));

        TXT_Hormonal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Hormonal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Tiene algún tipo de desarreglo hormonal? "));
        TXT_Hormonal.setEnabled(false);
        Anamnesis.add(TXT_Hormonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 390, -1));

        TXT_GraveEnfermo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_GraveEnfermo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Alguna vez ha estado gravemente enfermo? "));
        TXT_GraveEnfermo.setEnabled(false);
        Anamnesis.add(TXT_GraveEnfermo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 390, -1));

        jGrave.setBackground(new java.awt.Color(255, 255, 255));
        jGrave.setText("SI");
        jGrave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGraveActionPerformed(evt);
            }
        });
        Anamnesis.add(jGrave, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, 30));

        jGastro.setBackground(new java.awt.Color(255, 255, 255));
        jGastro.setText("SI");
        jGastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGastroActionPerformed(evt);
            }
        });
        Anamnesis.add(jGastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, 30));

        TXT_Gastrointestinal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Gastrointestinal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Enfermedades gastrointestinales? "));
        TXT_Gastrointestinal.setEnabled(false);
        Anamnesis.add(TXT_Gastrointestinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 390, -1));

        TXT_Alimento.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Alimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Hay algún alimento que usted no pueda comer?"));
        TXT_Alimento.setEnabled(false);
        Anamnesis.add(TXT_Alimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 390, -1));

        jAlimento.setBackground(new java.awt.Color(255, 255, 255));
        jAlimento.setText("SI");
        jAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlimentoActionPerformed(evt);
            }
        });
        Anamnesis.add(jAlimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, -1, 30));

        TXT_Cansansio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Cansansio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Se cansa fácilmente al realizar algún esfuerzo físico? "));
        TXT_Cansansio.setEnabled(false);
        Anamnesis.add(TXT_Cansansio, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, 390, -1));

        jCansansio.setBackground(new java.awt.Color(255, 255, 255));
        jCansansio.setText("SI");
        jCansansio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCansansioActionPerformed(evt);
            }
        });
        Anamnesis.add(jCansansio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, -1, 30));

        TXT_Tratamiento.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Tratamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Está tomando algún tipo de medicamento o bajo algún tratamiento médico? "));
        TXT_Tratamiento.setEnabled(false);
        Anamnesis.add(TXT_Tratamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 390, -1));

        jMedicamento.setBackground(new java.awt.Color(255, 255, 255));
        jMedicamento.setText("SI");
        jMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMedicamentoActionPerformed(evt);
            }
        });
        Anamnesis.add(jMedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, -1, 30));

        TXT_Orina.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Orina.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Orina frecuentemente durante el día (más de 6 veces)?"));
        TXT_Orina.setEnabled(false);
        Anamnesis.add(TXT_Orina, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 390, -1));

        jOrina.setBackground(new java.awt.Color(255, 255, 255));
        jOrina.setText("SI");
        jOrina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOrinaActionPerformed(evt);
            }
        });
        Anamnesis.add(jOrina, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, -1, 30));

        TXT_Aspirina.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Aspirina.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Toma frecuentemente aspirina o similar?"));
        TXT_Aspirina.setEnabled(false);
        Anamnesis.add(TXT_Aspirina, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 390, -1));

        jAspirina.setBackground(new java.awt.Color(255, 255, 255));
        jAspirina.setText("SI");
        jAspirina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAspirinaActionPerformed(evt);
            }
        });
        Anamnesis.add(jAspirina, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, 30));

        TXT_Agua.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Agua.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Toma más de 8 vasos de agua al día? "));
        TXT_Agua.setEnabled(false);
        Anamnesis.add(TXT_Agua, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 390, -1));

        jVasosAgua.setBackground(new java.awt.Color(255, 255, 255));
        jVasosAgua.setText("SI");
        jVasosAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVasosAguaActionPerformed(evt);
            }
        });
        Anamnesis.add(jVasosAgua, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, -1, 30));

        jQuirurgica.setBackground(new java.awt.Color(255, 255, 255));
        jQuirurgica.setText("SI");
        jQuirurgica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuirurgicaActionPerformed(evt);
            }
        });
        Anamnesis.add(jQuirurgica, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, 30));

        TXT_Intervenido.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Intervenido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Ha sido intervenido quirúrgicamente? "));
        TXT_Intervenido.setEnabled(false);
        Anamnesis.add(TXT_Intervenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 390, -1));

        Odontologia.addTab("Anamnesis |", Anamnesis);

        Habitoss.setBackground(new java.awt.Color(255, 255, 255));
        Habitoss.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Habitos"));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 880, 130));

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
        jComboHabitos.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiempo Libre"));
        jPanel16.add(jComboHabitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 40));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 20, 110));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 110));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel16.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 20, 110));

        jTConsumoDrugs.setColumns(20);
        jTConsumoDrugs.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoDrugs.setLineWrap(true);
        jTConsumoDrugs.setRows(5);
        jTConsumoDrugs.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo de estupefacientes"));
        jScrollPane9.setViewportView(jTConsumoDrugs);

        jPanel16.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 230, 90));

        jComboSexualidad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboSexualidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Satisfactoria", "Insatisfactoria", "Dispaurenia/relación dolorosa", "Inapetencia/frigidez", "Urgencia sexual/ninfomania", "Abstinencia voluntaria", "Bisexualidad", "Homosexualidad", "Impotencia", "Eyaculación precoz" }));
        jComboSexualidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Sexualidad"));
        jPanel16.add(jComboSexualidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 230, 40));

        jComboActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Sedentario", "Aeróbicos", "Anaeróbicos  y aeróbicos", "Deportes", "Aeróbicos, anaeróbicos y deportes" }));
        jComboActividad.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividad Fisica"));
        jPanel16.add(jComboActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 160, 40));

        jComboFrec.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboFrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nunca", "Una vez", "Dos veces", "Tres veces", "Cuatro veces", "Cinco veces", "Seis veces", "Diariamente" }));
        jComboFrec.setBorder(javax.swing.BorderFactory.createTitledBorder("Frecuencia Semanal"));
        jPanel16.add(jComboFrec, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 220, 40));

        jComboSueno.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboSueno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Reparador", "Interrumpido", "Insomnio", "Pesadillas" }));
        jComboSueno.setBorder(javax.swing.BorderFactory.createTitledBorder("Sueño"));
        jPanel16.add(jComboSueno, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 240, 40));

        jTConsumoTabaco.setColumns(20);
        jTConsumoTabaco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoTabaco.setLineWrap(true);
        jTConsumoTabaco.setRows(5);
        jTConsumoTabaco.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo de Tabaco"));
        jScrollPane16.setViewportView(jTConsumoTabaco);

        jPanel16.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 230, 90));
        jPanel16.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 120, 930, 30));

        jTConsumoAlcohol.setColumns(20);
        jTConsumoAlcohol.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTConsumoAlcohol.setLineWrap(true);
        jTConsumoAlcohol.setRows(5);
        jTConsumoAlcohol.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo de Alcohol"));
        jScrollPane15.setViewportView(jTConsumoAlcohol);

        jPanel16.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 230, 90));

        Habitoss.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 900, 180));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("Menospausia edad");
        jPanel18.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(779, 0, 110, 20));

        jLabel15.setText("Embarazos");
        jPanel18.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 60, 20));

        jSpinnerMenarquia.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jPanel18.add(jSpinnerMenarquia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 30));

        jSmenospausia.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jPanel18.add(jSmenospausia, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 50, 30));

        jSpartos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSpartos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpartosStateChanged(evt);
            }
        });
        jPanel18.add(jSpartos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 50, 30));

        jScesareas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jScesareas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jScesareasStateChanged(evt);
            }
        });
        jPanel18.add(jScesareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 50, 30));

        jSembarazo.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSembarazo.setEnabled(false);
        jPanel18.add(jSembarazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 50, 30));

        jLabel16.setText("Mernarquia edad");
        jPanel18.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        jComboMestruacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboMestruacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Eumenorreica", "Dismenorreica", "Oligomenorreica", "Hipermenorreica", "Polimenorreica", "Amenorreica", "Menospáusica" }));
        jPanel18.add(jComboMestruacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 100, 30));

        TxtCiclosMestru.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ciclos mestruación"));
        jPanel18.add(TxtCiclosMestru, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 120, 40));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 60));

        jSabortos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jSabortos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSabortosStateChanged(evt);
            }
        });
        jPanel18.add(jSabortos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 50, 30));

        jLabel27.setText("Mestruación");
        jPanel18.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 110, 20));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 20, 60));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 10, 60));

        jLabel28.setText("Partos");
        jPanel18.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 50, 20));

        jLabel29.setText("Cesareas");
        jPanel18.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 50, 20));

        jLabel30.setText("Abortos");
        jPanel18.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 50, 20));

        FechaMestruacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Última mestruación"));
        try {
            FechaMestruacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel18.add(FechaMestruacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 130, 40));

        Habitoss.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 900, 60));

        jEmbarazo.setBackground(new java.awt.Color(255, 255, 255));
        jEmbarazo.setText("SI");
        jEmbarazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmbarazoActionPerformed(evt);
            }
        });
        Habitoss.add(jEmbarazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        TXT_Embarazo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Embarazo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Está usted embarazada? "));
        TXT_Embarazo.setEnabled(false);
        Habitoss.add(TXT_Embarazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 390, -1));

        jTerapiaHormonal.setBackground(new java.awt.Color(255, 255, 255));
        jTerapiaHormonal.setText("SI");
        jTerapiaHormonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTerapiaHormonalActionPerformed(evt);
            }
        });
        Habitoss.add(jTerapiaHormonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 30));

        TXT_TerapiaHormonal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_TerapiaHormonal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Toma algún tipo de terapia hormonal o anticonceptivos?"));
        TXT_TerapiaHormonal.setEnabled(false);
        Habitoss.add(TXT_TerapiaHormonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 390, -1));

        jHalitosis.setBackground(new java.awt.Color(255, 255, 255));
        jHalitosis.setText("SI");
        jHalitosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHalitosisActionPerformed(evt);
            }
        });
        Habitoss.add(jHalitosis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 30));

        jReaccionAnestecia.setBackground(new java.awt.Color(255, 255, 255));
        jReaccionAnestecia.setText("SI");
        jReaccionAnestecia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReaccionAnesteciaActionPerformed(evt);
            }
        });
        Habitoss.add(jReaccionAnestecia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 30));

        TXT_ReaccionAnestecia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_ReaccionAnestecia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Ha presentado alguna reacción al anestésico local? "));
        TXT_ReaccionAnestecia.setEnabled(false);
        Habitoss.add(TXT_ReaccionAnestecia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 390, -1));

        TXT_halitosis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_halitosis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Ha presentado mal olor o sabor de boca (halitosis)? "));
        TXT_halitosis.setEnabled(false);
        Habitoss.add(TXT_halitosis, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 390, -1));

        jSatisfecho.setBackground(new java.awt.Color(255, 255, 255));
        jSatisfecho.setText("SI");
        jSatisfecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSatisfechoActionPerformed(evt);
            }
        });
        Habitoss.add(jSatisfecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 30));

        TXT_Apariencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Apariencia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Está satisfecho con la apariencia de sus dientes? "));
        TXT_Apariencia.setEnabled(false);
        Habitoss.add(TXT_Apariencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 390, -1));

        TXT_Alimentos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Alimentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Consume muchos alimentos ácidos, calientes o fríos? "));
        TXT_Alimentos.setEnabled(false);
        Habitoss.add(TXT_Alimentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 390, -1));

        jConsumoAcido.setBackground(new java.awt.Color(255, 255, 255));
        jConsumoAcido.setText("SI");
        jConsumoAcido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsumoAcidoActionPerformed(evt);
            }
        });
        Habitoss.add(jConsumoAcido, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, 30));

        TXT_Sensibilidad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Sensibilidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Tiene sensibilidad o dolor en alguno de sus dientes?"));
        TXT_Sensibilidad.setEnabled(false);
        Habitoss.add(TXT_Sensibilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 390, -1));

        jSensibilidad.setBackground(new java.awt.Color(255, 255, 255));
        jSensibilidad.setText("SI");
        jSensibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSensibilidadActionPerformed(evt);
            }
        });
        Habitoss.add(jSensibilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, 30));

        TXT_MovilidadDientes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_MovilidadDientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Siente que sus dientes se mueven?"));
        TXT_MovilidadDientes.setEnabled(false);
        Habitoss.add(TXT_MovilidadDientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 390, -1));

        jDientesM.setBackground(new java.awt.Color(255, 255, 255));
        jDientesM.setText("SI");
        jDientesM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDientesMActionPerformed(evt);
            }
        });
        Habitoss.add(jDientesM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, 30));

        TXT_Encias.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Encias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Le sangran las encías frecuentemente? "));
        TXT_Encias.setEnabled(false);
        Habitoss.add(TXT_Encias, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 390, -1));

        jSangranEncias.setBackground(new java.awt.Color(255, 255, 255));
        jSangranEncias.setText("SI");
        jSangranEncias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSangranEnciasActionPerformed(evt);
            }
        });
        Habitoss.add(jSangranEncias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 30));

        jresequedad.setBackground(new java.awt.Color(255, 255, 255));
        jresequedad.setText("SI");
        jresequedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jresequedadActionPerformed(evt);
            }
        });
        Habitoss.add(jresequedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, 30));

        TXTResequedad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXTResequedad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Siente resequedad en la boca frecuentemente?"));
        TXTResequedad.setEnabled(false);
        Habitoss.add(TXTResequedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 390, -1));

        jUlceraciones.setBackground(new java.awt.Color(255, 255, 255));
        jUlceraciones.setText("SI");
        jUlceraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUlceracionesActionPerformed(evt);
            }
        });
        Habitoss.add(jUlceraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, 30));

        TXT_Ulceraciones.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Ulceraciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Siente molestias por ulceraciones en la boca?"));
        TXT_Ulceraciones.setEnabled(false);
        Habitoss.add(TXT_Ulceraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 390, -1));

        jNingunaAnteriores.setBackground(new java.awt.Color(255, 255, 255));
        jNingunaAnteriores.setText("NINGUNA DE LAS ANTERIORES");
        jNingunaAnteriores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNingunaAnterioresActionPerformed(evt);
            }
        });
        Habitoss.add(jNingunaAnteriores, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 430, 30));

        TXT_Mandibula.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Mandibula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Le molesta o le suena la mandíbula al masticar?"));
        TXT_Mandibula.setEnabled(false);
        Habitoss.add(TXT_Mandibula, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 390, -1));

        TXT_Observaciones.setColumns(20);
        TXT_Observaciones.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        TXT_Observaciones.setLineWrap(true);
        TXT_Observaciones.setRows(5);
        TXT_Observaciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));
        jScrollPane17.setViewportView(TXT_Observaciones);

        Habitoss.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 430, 80));

        jMandibula.setBackground(new java.awt.Color(255, 255, 255));
        jMandibula.setText("SI");
        jMandibula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMandibulaActionPerformed(evt);
            }
        });
        Habitoss.add(jMandibula, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, 30));

        TXT_Alergico.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TXT_Alergico.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "¿Es alérgico a alguna sustancia o medicamento? "));
        TXT_Alergico.setEnabled(false);
        Habitoss.add(TXT_Alergico, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 390, -1));

        jAlergia.setBackground(new java.awt.Color(255, 255, 255));
        jAlergia.setText("SI");
        jAlergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlergiaActionPerformed(evt);
            }
        });
        Habitoss.add(jAlergia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        Odontologia.addTab("Revisión & hábitos |", Habitoss);

        Habitos.setBackground(new java.awt.Color(255, 255, 255));
        Habitos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        Habitos.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 40));

        jTabbedPane3.setEnabled(false);

        Antecedentes.setBackground(new java.awt.Color(255, 255, 255));
        Antecedentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Antecedentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        });
        jScrollPane34.setViewportView(jTableAntecedentesF);

        Antecedentes.add(jScrollPane34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 550, 200));

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
        });
        Antecedentes.add(TxtbusquedaAntc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 550, 40));

        jScrollPane21.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informe / Interpretación"));

        JtextAntc1.setColumns(20);
        JtextAntc1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        JtextAntc1.setLineWrap(true);
        JtextAntc1.setRows(5);
        jScrollPane21.setViewportView(JtextAntc1);

        Antecedentes.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 330, 210));

        jBtnRetirarAntc.setText("Retirar");
        jBtnRetirarAntc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnRetirarAntc.setContentAreaFilled(false);
        jBtnRetirarAntc.setEnabled(false);
        jBtnRetirarAntc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRetirarAntcActionPerformed(evt);
            }
        });
        Antecedentes.add(jBtnRetirarAntc, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 80, 30));

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

        Antecedentes.add(jScrollPane35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 890, 170));

        jAButton30.setText("Añadir");
        jAButton30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jAButton30.setContentAreaFilled(false);
        jAButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAButton30ActionPerformed(evt);
            }
        });
        Antecedentes.add(jAButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 80, 30));

        BtnModifAnt.setText("Modificar");
        BtnModifAnt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModifAnt.setContentAreaFilled(false);
        BtnModifAnt.setEnabled(false);
        BtnModifAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifAntActionPerformed(evt);
            }
        });
        Antecedentes.add(BtnModifAnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 80, 30));

        jTabbedPane3.addTab("Familiares", Antecedentes);

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

        Habitos.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 920, 540));

        Odontologia.addTab("Antecedentes |", Habitos);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTtemperatura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Temperatura"));
        jPanel8.add(TXTtemperatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 40));

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
        jPanel8.add(TXTtalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        TXTpeso.setText("0");
        TXTpeso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Peso"));
        TXTpeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTpesoKeyReleased(evt);
            }
        });
        jPanel8.add(TXTpeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 150, 40));

        TXTpulso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Pulso"));
        jPanel8.add(TXTpulso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 150, 40));

        TXTfr.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "F.R"));
        jPanel8.add(TXTfr, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 80, 40));

        TXTpesoIdeal.setEditable(false);
        TXTpesoIdeal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Peso ideal"));
        jPanel8.add(TXTpesoIdeal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 80, 40));

        TXTpesoAdic.setEditable(false);
        TXTpesoAdic.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Peso adic"));
        TXTpesoAdic.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel8.add(TXTpesoAdic, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 110, 40));

        TXTrespiracion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo respiracion"));
        jPanel8.add(TXTrespiracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 220, 40));

        TXTimc.setEditable(false);
        TXTimc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "I.M.C"));
        jPanel8.add(TXTimc, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 110, 40));

        TXTtension.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tension Arterial"));
        jPanel8.add(TXTtension, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 170, 40));

        TXTaspecto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Aspecto Contextura"));
        jPanel8.add(TXTaspecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 170, 40));

        TXTabdominal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Abdominal (cm)"));
        jPanel8.add(TXTabdominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 130, 40));

        TXTcadera.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cadera (cm)"));
        jPanel8.add(TXTcadera, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 130, 40));

        MuscularTxt.setColumns(20);
        MuscularTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        MuscularTxt.setLineWrap(true);
        MuscularTxt.setRows(5);
        MuscularTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Palpitación Muscular"));
        jScrollPane14.setViewportView(MuscularTxt);

        jPanel8.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 300, 110));

        AspectoTxt.setColumns(20);
        AspectoTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        AspectoTxt.setLineWrap(true);
        AspectoTxt.setRows(5);
        AspectoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Aspecto del paciente"));
        jScrollPane18.setViewportView(AspectoTxt);

        jPanel8.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 300, 110));

        PaladarTxt.setColumns(20);
        PaladarTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        PaladarTxt.setLineWrap(true);
        PaladarTxt.setRows(5);
        PaladarTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Paladar duro y paladar blando"));
        jScrollPane25.setViewportView(PaladarTxt);

        jPanel8.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 300, 110));

        TemporomandibularTxt.setColumns(20);
        TemporomandibularTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        TemporomandibularTxt.setLineWrap(true);
        TemporomandibularTxt.setRows(5);
        TemporomandibularTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Articulación Temporomandibular"));
        jScrollPane27.setViewportView(TemporomandibularTxt);

        jPanel8.add(jScrollPane27, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 300, 110));

        AmigdalinaTxt.setColumns(20);
        AmigdalinaTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        AmigdalinaTxt.setLineWrap(true);
        AmigdalinaTxt.setRows(5);
        AmigdalinaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Región amigdalina"));
        jScrollPane28.setViewportView(AmigdalinaTxt);

        jPanel8.add(jScrollPane28, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 300, 110));

        SalivaTxt.setColumns(20);
        SalivaTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        SalivaTxt.setLineWrap(true);
        SalivaTxt.setRows(5);
        SalivaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Saliva"));
        jScrollPane29.setViewportView(SalivaTxt);

        jPanel8.add(jScrollPane29, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 300, 110));

        ComisuraTxt.setColumns(20);
        ComisuraTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        ComisuraTxt.setLineWrap(true);
        ComisuraTxt.setRows(5);
        ComisuraTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Labios y comisura labial"));
        jScrollPane30.setViewportView(ComisuraTxt);

        jPanel8.add(jScrollPane30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 300, 110));

        LenguaTxt.setColumns(20);
        LenguaTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        LenguaTxt.setLineWrap(true);
        LenguaTxt.setRows(5);
        LenguaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Lengua y piso de boca"));
        jScrollPane37.setViewportView(LenguaTxt);

        jPanel8.add(jScrollPane37, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 300, 110));

        GangliosTxt.setColumns(20);
        GangliosTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        GangliosTxt.setLineWrap(true);
        GangliosTxt.setRows(5);
        GangliosTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Ganglios Linfáticos"));
        jScrollPane38.setViewportView(GangliosTxt);

        jPanel8.add(jScrollPane38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 300, 110));

        TxtTiroidea.setColumns(20);
        TxtTiroidea.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        TxtTiroidea.setLineWrap(true);
        TxtTiroidea.setRows(5);
        TxtTiroidea.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Palpitación Tiroidea"));
        jScrollPane39.setViewportView(TxtTiroidea);

        jPanel8.add(jScrollPane39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 300, 110));

        CarillosTxt.setColumns(20);
        CarillosTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        CarillosTxt.setLineWrap(true);
        CarillosTxt.setRows(5);
        CarillosTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Carrillos"));
        jScrollPane40.setViewportView(CarillosTxt);

        jPanel8.add(jScrollPane40, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 300, 110));

        MaxilaresTxt.setColumns(20);
        MaxilaresTxt.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        MaxilaresTxt.setLineWrap(true);
        MaxilaresTxt.setRows(5);
        MaxilaresTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Maxilares"));
        jScrollPane41.setViewportView(MaxilaresTxt);

        jPanel8.add(jScrollPane41, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 300, 110));

        Odontologia.addTab("Examenes |", jPanel8);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Odontograma.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 340, 330));

        jTextRadiologica.setColumns(20);
        jTextRadiologica.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTextRadiologica.setLineWrap(true);
        jTextRadiologica.setRows(5);
        jTextRadiologica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Información radiológica"));
        jTextRadiologica.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextRadiologicaFocusLost(evt);
            }
        });
        jScrollPane22.setViewportView(jTextRadiologica);

        jPanel2.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 367, 900, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Características del dolor"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClatidos.setBackground(new java.awt.Color(255, 255, 255));
        jClatidos.setText("Con latidos");
        jClatidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClatidosActionPerformed(evt);
            }
        });
        jPanel5.add(jClatidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jCpresente.setBackground(new java.awt.Color(255, 255, 255));
        jCpresente.setText("Presente");
        jCpresente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCpresenteActionPerformed(evt);
            }
        });
        jPanel5.add(jCpresente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jCespontaneo.setBackground(new java.awt.Color(255, 255, 255));
        jCespontaneo.setText("Espontáneo");
        jCespontaneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCespontaneoActionPerformed(evt);
            }
        });
        jPanel5.add(jCespontaneo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jRsevero.setBackground(new java.awt.Color(255, 255, 255));
        jRsevero.setText("Severo");
        jRsevero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRseveroActionPerformed(evt);
            }
        });
        jPanel5.add(jRsevero, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jRleve.setBackground(new java.awt.Color(255, 255, 255));
        jRleve.setText("Leve");
        jRleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRleveActionPerformed(evt);
            }
        });
        jPanel5.add(jRleve, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jRmoderado.setBackground(new java.awt.Color(255, 255, 255));
        jRmoderado.setText("Moderado");
        jRmoderado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRmoderadoActionPerformed(evt);
            }
        });
        jPanel5.add(jRmoderado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jRlocalizado.setBackground(new java.awt.Color(255, 255, 255));
        jRlocalizado.setText("Localizado");
        jRlocalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRlocalizadoActionPerformed(evt);
            }
        });
        jPanel5.add(jRlocalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jRdifuso.setBackground(new java.awt.Color(255, 255, 255));
        jRdifuso.setText("Difuso");
        jRdifuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdifusoActionPerformed(evt);
            }
        });
        jPanel5.add(jRdifuso, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        jRcontinuo.setBackground(new java.awt.Color(255, 255, 255));
        jRcontinuo.setText("Continuo");
        jRcontinuo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRcontinuoActionPerformed(evt);
            }
        });
        jPanel5.add(jRcontinuo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jRintermitente.setBackground(new java.awt.Color(255, 255, 255));
        jRintermitente.setText("Intermitente");
        jRintermitente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRintermitenteActionPerformed(evt);
            }
        });
        jPanel5.add(jRintermitente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 540, 90));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Signos"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCpalpitacion.setBackground(new java.awt.Color(255, 255, 255));
        jCpalpitacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jCpalpitacion.setText("Sensible palpación sobre apical");
        jCpalpitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCpalpitacionActionPerformed(evt);
            }
        });
        jPanel9.add(jCpalpitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jCextraoral.setBackground(new java.awt.Color(255, 255, 255));
        jCextraoral.setText("Edema extra oral");
        jCextraoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCextraoralActionPerformed(evt);
            }
        });
        jPanel9.add(jCextraoral, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jCfractura.setBackground(new java.awt.Color(255, 255, 255));
        jCfractura.setText("Fractura");
        jCfractura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCfracturaActionPerformed(evt);
            }
        });
        jPanel9.add(jCfractura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jCemento.setBackground(new java.awt.Color(255, 255, 255));
        jCemento.setText("Cemento");
        jCemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCementoActionPerformed(evt);
            }
        });
        jPanel9.add(jCemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jCfistula.setBackground(new java.awt.Color(255, 255, 255));
        jCfistula.setText("Fístula");
        jCfistula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCfistulaActionPerformed(evt);
            }
        });
        jPanel9.add(jCfistula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        jCorona.setBackground(new java.awt.Color(255, 255, 255));
        jCorona.setText("Corona");
        jCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCoronaActionPerformed(evt);
            }
        });
        jPanel9.add(jCorona, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jCintraOral.setBackground(new java.awt.Color(255, 255, 255));
        jCintraOral.setText("Edema intra oral");
        jCintraOral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCintraOralActionPerformed(evt);
            }
        });
        jPanel9.add(jCintraOral, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        jCincrustacion.setBackground(new java.awt.Color(255, 255, 255));
        jCincrustacion.setText("Incrustación");
        jCincrustacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCincrustacionActionPerformed(evt);
            }
        });
        jPanel9.add(jCincrustacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jCabrasion.setBackground(new java.awt.Color(255, 255, 255));
        jCabrasion.setText("Abrasión");
        jCabrasion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCabrasionActionPerformed(evt);
            }
        });
        jPanel9.add(jCabrasion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, 20));

        jCdienteOscuro.setBackground(new java.awt.Color(255, 255, 255));
        jCdienteOscuro.setText("Diente oscuro");
        jCdienteOscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCdienteOscuroActionPerformed(evt);
            }
        });
        jPanel9.add(jCdienteOscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, 20));

        jCdienteMovil.setBackground(new java.awt.Color(255, 255, 255));
        jCdienteMovil.setText("Diente móvil");
        jCdienteMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCdienteMovilActionPerformed(evt);
            }
        });
        jPanel9.add(jCdienteMovil, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, 20));

        jCrc.setBackground(new java.awt.Color(255, 255, 255));
        jCrc.setText("R.C");
        jCrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCrcActionPerformed(evt);
            }
        });
        jPanel9.add(jCrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, 20));

        jCamalgama.setBackground(new java.awt.Color(255, 255, 255));
        jCamalgama.setText("Amalgama");
        jCamalgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCamalgamaActionPerformed(evt);
            }
        });
        jPanel9.add(jCamalgama, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, 20));

        jCadenopatia.setBackground(new java.awt.Color(255, 255, 255));
        jCadenopatia.setText("Adenopatía");
        jCadenopatia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCadenopatiaActionPerformed(evt);
            }
        });
        jPanel9.add(jCadenopatia, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, 20));

        jCarie.setBackground(new java.awt.Color(255, 255, 255));
        jCarie.setText("Carie profunda");
        jCarie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCarieActionPerformed(evt);
            }
        });
        jPanel9.add(jCarie, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, -1, 20));
        jPanel9.add(jTxtOtros, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 470, -1));

        jLabel8.setText("Otros:");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 110, 40, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 540, 140));

        JtxtInforme.setColumns(20);
        JtxtInforme.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        JtxtInforme.setLineWrap(true);
        JtxtInforme.setRows(5);
        JtxtInforme.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Informe / Interpretación"));
        JtxtInforme.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JtxtInformeFocusLost(evt);
            }
        });
        jScrollPane23.setViewportView(JtxtInforme);

        jPanel2.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 540, 80));

        jTableOdontograma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Diente", "Informe", "Dolor", "Signos", "Radiologia"
            }
        ));
        jTableOdontograma.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTableOdontogramaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTableOdontograma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOdontogramaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableOdontogramaMouseEntered(evt);
            }
        });
        jScrollPane36.setViewportView(jTableOdontograma);

        jPanel2.add(jScrollPane36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 800, 140));

        btnRetirarOdonto.setText("Retirar");
        btnRetirarOdonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRetirarOdonto.setContentAreaFilled(false);
        btnRetirarOdonto.setEnabled(false);
        btnRetirarOdonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarOdontoActionPerformed(evt);
            }
        });
        jPanel2.add(btnRetirarOdonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 80, 30));

        btnAgregarOdonto.setText("Agregar");
        btnAgregarOdonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAgregarOdonto.setContentAreaFilled(false);
        btnAgregarOdonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOdontoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarOdonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 80, 30));

        btnModificarOdonto.setText("Modificar");
        btnModificarOdonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnModificarOdonto.setContentAreaFilled(false);
        btnModificarOdonto.setEnabled(false);
        btnModificarOdonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarOdontoActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificarOdonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 80, 30));

        jComboDiente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar diente", "18", "17", "16", "15", "14", "13", "12", "11", "21", "22", "23", "24", "25", "26", "27", "28", "48", "47", "46", "45", "44", "43", "42", "41", "31", "32", "33", "34", "35", "36", "37", "38" }));
        jPanel2.add(jComboDiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 540, 30));

        btnCancerlarOdonto.setText("Cancelar");
        btnCancerlarOdonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCancerlarOdonto.setContentAreaFilled(false);
        btnCancerlarOdonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancerlarOdontoActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancerlarOdonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 80, 30));

        Odontologia.addTab("Odontograma |", jPanel2);

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

        Odontologia.addTab("Adicional |", jPanel4);

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

        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Indicador cualitativo de salud (resultado)")));
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

        jimgOdontograma.setBackground(new java.awt.Color(255, 255, 255));
        jimgOdontograma.setText("Agregar img odontograma");
        jPanel10.add(jimgOdontograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, -1, -1));

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

        Odontologia.addTab("Conclusión |", jPanel10);

        JPanelHistoria.add(Odontologia, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 920, 600));

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
     
     
        
       // actualizarPicture();
      

        
        JTablePacientes.requestFocusInWindow();
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
       actualizarHistoriaBd();
       imprimirReporte();

      }}  
           
           
           else{
                  
       pdfHistorias();
       guardarHistoria();
       actualizarHistoriaBd();
       imprimirReporte();
           
           }
           
           
           
           

  
       
       
       }catch(Exception e){ System.out.println(e);
       System.out.println(e); JOptionPane.showMessageDialog(this, "ERROR AL REALIZAR ESTE PROCESO, INTENTE DE NUEVO. DE CONTINUAR EL ERROR CONTACTE A SOPORTE", "ERROR", JOptionPane.ERROR_MESSAGE);}   
  

      }
     
        
   
     
    }//GEN-LAST:event_BtnImprimirHistoriaActionPerformed

       
        
        
        /*    
      if(jLabel7.getText().equals("")){
       JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN PACIENTE DE LA TABLA PACIENTES", "CAMPOS", 1);
      
      }
      else if(jTextMotivoConsulta.getText().equals("")){
      JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }  
      else{
      
          
        try{
       
       pdfHistorias();
       JOptionPane.showMessageDialog(null, "GENERANDO REPORTE...", "REPORTE CONSULTA", 1);
       Desktop.getDesktop().open(fileHistoria);

       guardarHistoria();
       //ActualizarHistoriaBd();
   
       cleanAll();
   

       BtnImprimirHistoria.setEnabled(false);
       BtnGuardarHistoria.setEnabled(false);
       usingData=false;
       
       
       }catch(Exception e){ System.out.println(e);}     
          
          

      }
    */
    
    
    
    
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
    //    int fila = JTablePacientes.getSelectedRow();
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
  
  
  
    private void JTablePacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacientesMouseClicked
    filaPaciente = JTablePacientes.getSelectedRow();


if (filaPaciente != -1) {
   //  cleanAll();
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

            
    limpiarShowAntc1();
    ShowAntc1(idAsignar);

    limpiarShowAntc2();
    ShowAntc2(idAsignar);

    limpiarEAdc();
    ShowExamAdic(idAsignar);
    
    limpiarTVisualizar();
    visualizar_PdfVOHistorias(jTableVisualizarC);
           
    limpiarOdontograma();    
    showOdontograma(idAsignar);
    
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

    
    
    double altura=0, pesoactual=0, pesoideal=0, pesoextra=0, imc=0;
    private void TXTtallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTtallaKeyReleased
       

       

       
        if (!TXTtalla.getText().isEmpty() && !sexo.equals("")) {
            calcularpeso();
       }
        
    }//GEN-LAST:event_TXTtallaKeyReleased

    private void TXTpesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTpesoKeyReleased
         
    
       
       

       
        if (!TXTpeso.getText().isEmpty() && !sexo.equals("")) {
        calcularpeso();
        
        
        }
    }//GEN-LAST:event_TXTpesoKeyReleased

    
    
 
    
    
    
    public void calcularpeso(){
        
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
            BtnProceso.setEnabled(false);
            BtnRetirar.setEnabled(true);
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

        
    if(jLabel7.getText().equals("")){
       JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN PACIENTE DE LA TABLA PACIENTES", "CAMPOS", 1);
      
      }
    else if(jTextMotivoConsulta.getText().equals("")){
      JOptionPane.showMessageDialog(this, "DEBE COMPLETAR EL CAMPO MOTIVO CONSULTA", "CAMPOS", 1);
      
      }         
    else{    
    try{
        
        
       if(estado.equals("Completado")){
    if (JOptionPane.showConfirmDialog(rootPane, "Ya completó esta historia, ¿desea sobre-escribirla?",
            "Reescribir historia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
  pdfHistorias(); 
  guardarHistoria();
  actualizarHistoriaBd();
  limpiarTVisualizar();
  visualizar_PdfVOHistorias(jTableVisualizarC);
  limpiarTPacientes(); 
  ListarPacientes();
  JOptionPane.showMessageDialog(this, "HISTORIA CLINICA GUARDADA", "HISTORIA CLINICA",1);
    
    } 
    }   
        
        
       else{
       
     pdfHistorias(); 
     guardarHistoria();
     actualizarHistoriaBd();
     limpiarTVisualizar();
     visualizar_PdfVOHistorias(jTableVisualizarC);
     limpiarTPacientes(); 
     ListarPacientes();
     JOptionPane.showMessageDialog(this, "HISTORIA CLINICA GUARDADA", "HISTORIA CLINICA",1);
       }  
    

}catch(Exception e){System.out.println(e);
System.out.println(e); JOptionPane.showMessageDialog(this, "ERROR AL REALIZAR ESTE PROCESO, INTENTE DE NUEVO. DE CONTINUAR EL ERROR CONTACTE A SOPORTE", "ERROR", JOptionPane.ERROR_MESSAGE);}
    }

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
        jimgOdontograma.setSelected(false);
        limpiarTPacientes(); 
        ListarPacientes();
        limpiarTProcedimientos();
        limpiarTProcedimientos2();
        ListarProcedimientos();
        ListarProcedimientos2();
        jLabel7.setText("");
        JTablePacientes.requestFocus();
        
        limpiarTVisualizar();
        searchOdontogram();
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

    private void jTextRadiologicaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextRadiologicaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextRadiologicaFocusLost

    private void JtxtInformeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JtxtInformeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_JtxtInformeFocusLost

    
    int idOdontograma1;
    String informeOdonto, informeRadiologico;
    private void jTableOdontogramaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOdontogramaMouseClicked
            int fila = jTableOdontograma.getSelectedRow();
        if (fila == -1) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar un campo de la tabla", "Campos", 1);

        }

        else{ idOdontograma1= (int) (jTableOdontograma.getValueAt(fila, 0));
            informeOdonto= (jTableOdontograma.getValueAt(fila, 2).toString());
            informeRadiologico= (jTableOdontograma.getValueAt(fila, 5).toString());
            btnModificarOdonto.setEnabled(true);
            btnRetirarOdonto.setEnabled(true);
            btnAgregarOdonto.setEnabled(false);
        
            JtxtInforme.setText(informeOdonto);
            jTextRadiologica.setText(informeRadiologico);
        System.out.println(idOdontograma1);
        }
        
    }//GEN-LAST:event_jTableOdontogramaMouseClicked

    private void btnRetirarOdontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarOdontoActionPerformed
       
         int fila = jTableOdontograma.getSelectedRow();

       if (fila == -1) {
        
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA OPCIÓN DE LA TABLA ", "CAMPOS", JOptionPane.ERROR_MESSAGE);
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
         
        
        
   btnRetirarOdonto.setEnabled(false);
   btnAgregarOdonto.setEnabled(true);
   eliminarOdontograma();   
   limpiarOdontograma();    
   showOdontograma(idValidarAdc);

  

   }  
                }
        
        
    }//GEN-LAST:event_btnRetirarOdontoActionPerformed

    private void btnAgregarOdontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOdontoActionPerformed
 int row = jTableOdontograma.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = jTableOdontograma.getValueAt(i, 1);
    }
    Object value_to_find= jComboDiente.getSelectedItem();
    boolean exist = Arrays.asList(content).contains(value_to_find);
    


    
    if (exist){
        JOptionPane.showMessageDialog(null, "NO SE PUEDE REPETIR EL MISMO NUMERO DE DIENTE", "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
    }
         
    
    
   else if (JtxtInforme.getText().equals("")){ JOptionPane.showMessageDialog(null, "DEBE COMPLETAR LOS CAMPOS", "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);}
    
      
   else if (JTablePacientes.getRowCount() == 0) {
   JOptionPane.showMessageDialog(this, "NO TIENE PACIENTES ASIGNADOS", "PACIENTES", JOptionPane.ERROR_MESSAGE);
   }

   else if (jComboDiente.getSelectedItem().equals("Seleccionar diente")){
      JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN DIENTE SEGUN LA IMAGEN", "CAMPOS", JOptionPane.ERROR_MESSAGE);
   }
      
 
   else 
   {
      if (usingData) {   
      agregarOdontograma();
      limpiarOdontograma();    
      showOdontograma(idtablaHistorias);
      } 
 
           
      else{   
  
      agregarOdontograma();
      limpiarOdontograma();   
      showOdontograma(idAsignar);
      }

       
  
       }
    }//GEN-LAST:event_btnAgregarOdontoActionPerformed

    private void btnModificarOdontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarOdontoActionPerformed
     
        
     int idAdicionalM;
   if (!usingData) {
        idAdicionalM = idAsignar;
    } else {
        idAdicionalM = idtablaHistorias;
    }    
        


      btnModificarOdonto.setEnabled(false);
      btnAgregarOdonto.setEnabled(true);
      modificarOdontograma();
      limpiarOdontograma();    
      showOdontograma(idAdicionalM);
        

        
        
    }//GEN-LAST:event_btnModificarOdontoActionPerformed

    
    
    
    
    
    
    String caracteristicasDolor="",
    presente = "",
    espontaneo = "",
    latidos = "",
    leve = "",
    moderado = "",
    severo = "",
    localizado = "",
    difuso = "",
    continuo = "",
    intermitente = "";
    
private void actualizarVariable(String nombreVariable, boolean seleccionado) {
    switch (nombreVariable) {
        case "presente":
            presente = seleccionado ? "Presente" : "";
            break;
        
        case "espontaneo":
            espontaneo = seleccionado ? "Espontaneo" : "";
            break;
            
        case "latidos":
            latidos = seleccionado ? "Con latidos" : "";
            break;  
            
        case "leve":
            leve = seleccionado ? "Leve" : "";
            moderado="";
            severo="";
            break; 
            
        case "moderado":
            moderado = seleccionado ? "Moderado" : "";
            leve="";
            severo="";
            break;   
            
        case "severo":
            severo = seleccionado ? "Severo" : "";
            moderado="";
            leve="";
            break;     
            
        case "localizado":
            localizado = seleccionado ? "Localizado" : "";
            difuso="";
            break;  
            
        case "difuso":
        difuso = seleccionado ? "Difuso" : "";
        localizado="";
        break;    
            
        case "continuo":
        continuo = seleccionado ? "Continuo" : "";
        intermitente="";
        break;        
        
        case "intermitente":
        intermitente = seleccionado ? "Intermitente" : "";
        continuo="";
        break; 
        
        
        default:
            // Manejo de caso no válido
            break;
    }
    
    // Lista de todas las variables
    String[] variables = {presente, espontaneo, latidos, leve, moderado, severo, localizado, difuso, continuo, intermitente};
    
    // Actualizar la variable caracteristicasDolor
    caracteristicasDolor = "";
    for (int i = 0; i < variables.length; i++) {
        if (!variables[i].isEmpty()) {
            caracteristicasDolor += variables[i];
            
            // Agregar coma si no es la última variable
            if (tieneSiguienteVariableNoVacia(variables, i)) {
                caracteristicasDolor += ", ";
             
            }
        }
    }
}
    



  String signosr="",
    endemaExtra = "",
    fractura = "",
    cemento = "",
    sensible = "",
    endemaIntra = "",
    incrustacion = "",
    corona = "",
    fistula = "",
    abrasion = "",
    dienteMovil = "",
    dienteOscuro = "",
    rc = "",
    adenopatia = "",
    amalgama = "",
    carieProfunda = "",
    otrosOdontograma;


private void actualizarSigno(String nombreVariable, boolean seleccionado) {
    switch (nombreVariable) {
        case "jCextraoral":
            endemaExtra = seleccionado ? "Endema extra oral" : "";
            break;
        
        case "jCfractura":
            fractura = seleccionado ? "Fractura" : "";
            break;
            
        case "jCemento":
            cemento = seleccionado ? "Con latidos" : "";
            break;  
            
        case "jCpalpitacion":
             sensible = seleccionado ? "Sensible palpitación sobre apical" : "";
            break;  
            
         case "jCintraOral":
            endemaIntra = seleccionado ? "Endema intra oral" : "";
            break;  
            
            
        case "jCincrustacion":
            incrustacion = seleccionado ? "Incrustacion" : "";
            break;  
            
            
        case "jCorona":
        corona = seleccionado ? "Corona" : "";
        break;  
        
        case "jCfistula":
        fistula = seleccionado ? "Fistula" : "";
        break;  
        
        case "jCabrasion":
        abrasion = seleccionado ? "Abrasión" : "";
        break;  
        
        case "jCdienteMovil":
        dienteMovil = seleccionado ? "Diente móvil" : "";
        break;  
        
        case "jCdienteOscuro":
        dienteOscuro = seleccionado ? "Diente oscuro" : "";
        break;  
        
        case "jCrc":
        rc = seleccionado ? "R.C" : "";
        break;  

        case "jCadenopatia":
        adenopatia = seleccionado ? "Adenopatía" : "";
        break;  
        
        case "jCamalgama":
        amalgama = seleccionado ? "Amalgama" : "";
        break; 
        
         case "jCarie":
        carieProfunda = seleccionado ? "Carie profunda" : "";
        break; 
        
        default:
            // Manejo de caso no válido
            break;
    }
    
    
    
    
    
    
    // Lista de todas las variables
    otrosOdontograma=jTxtOtros.getText();
    String[] variables = {endemaExtra, fractura, cemento, sensible, endemaIntra, incrustacion, corona, fistula, abrasion, dienteMovil, dienteOscuro, rc, adenopatia, amalgama,carieProfunda,otrosOdontograma };
    
    // Actualizar la variable caracteristicasDolor
    signosr = "";
    for (int i = 0; i < variables.length; i++) {
        if (!variables[i].isEmpty()) {
            signosr += variables[i];
            
            // Agregar coma si no es la última variable
            if (tieneSiguienteVariableNoVacia(variables, i)) {
                signosr += ", ";
             
            }
        }
    }
}






private boolean tieneSiguienteVariableNoVacia(String[] variables, int indiceActual) {
    for (int i = indiceActual + 1; i < variables.length; i++) {
        if (variables[i] != null && !variables[i].isEmpty()) {
            return true;
        }
    }
    return false;
}













    private void jCpresenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCpresenteActionPerformed
       
actualizarVariable("presente", jCpresente.isSelected());
        
    }//GEN-LAST:event_jCpresenteActionPerformed

    
    
    
    
    
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
 
       try {
        
   String imagePath = "C:\\Fundaginebra\\src\\imagenes\\odontograma.png";

   

    String command = "mspaint.exe \"" + imagePath + "\"";
    
    // Ejecutar el comando
    setupDirectoryWatcher(imagePath, jLabel4);
    Process process = Runtime.getRuntime().exec(command);
} catch (IOException ex) {
    Logger.getLogger(JConsultasOdontologia.class.getName()).log(Level.SEVERE, null, ex);
}
    
    }//GEN-LAST:event_jLabel4MouseClicked

 
   
    
    
    
    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
   
    }//GEN-LAST:event_jLabel4MouseEntered

    private void JCardiovascularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCardiovascularActionPerformed
     
        
       Txt_Alteraciones.setEnabled(JCardiovascular.isSelected() ? true : false);
        
    }//GEN-LAST:event_JCardiovascularActionPerformed

    private void JHemorragiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JHemorragiasActionPerformed
        Txt_Hemorragia.setEnabled(JHemorragias.isSelected() ? true : false);
    }//GEN-LAST:event_JHemorragiasActionPerformed

    private void jAnemiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAnemiaActionPerformed
   Txt_Anemia.setEnabled(jAnemia.isSelected() ? true : false);
    }//GEN-LAST:event_jAnemiaActionPerformed

    private void jRenalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRenalesActionPerformed
    Txt_renales.setEnabled(jRenales.isSelected() ? true : false);
    }//GEN-LAST:event_jRenalesActionPerformed

    private void jHepaticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHepaticasActionPerformed
    TXT_Hepaticas.setEnabled(jHepaticas.isSelected() ? true : false);
    }//GEN-LAST:event_jHepaticasActionPerformed

    private void jAsmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAsmaActionPerformed
    TXT_asma.setEnabled(jAsma.isSelected() ? true : false);
    }//GEN-LAST:event_jAsmaActionPerformed

    private void jRespiratoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRespiratoriasActionPerformed
    TXT_Respiratorias.setEnabled(jRespiratorias.isSelected() ? true : false);
    }//GEN-LAST:event_jRespiratoriasActionPerformed

    private void jDiabetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiabetesActionPerformed
    TXT_diabetes.setEnabled(jDiabetes.isSelected() ? true : false);
    }//GEN-LAST:event_jDiabetesActionPerformed

    private void jHipertensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHipertensionActionPerformed
    TXT_Hipertension.setEnabled(jHipertension.isSelected() ? true : false);
    }//GEN-LAST:event_jHipertensionActionPerformed

    private void jMigrañasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMigrañasActionPerformed
    TXT_Jaqueca.setEnabled(jMigrañas.isSelected() ? true : false);
    }//GEN-LAST:event_jMigrañasActionPerformed

    private void jFiebreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFiebreActionPerformed
    TXT_reumatica.setEnabled(jFiebre.isSelected() ? true : false);
    }//GEN-LAST:event_jFiebreActionPerformed

    private void jInfecciosasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInfecciosasActionPerformed
    TXT_infecciosa.setEnabled(jInfecciosas.isSelected() ? true : false);
    }//GEN-LAST:event_jInfecciosasActionPerformed

    private void jTransmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransmisionActionPerformed
     TXT_SEXUAL.setEnabled(jTransmision.isSelected() ? true : false);
    }//GEN-LAST:event_jTransmisionActionPerformed

    private void jGastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGastroActionPerformed
    TXT_Gastrointestinal.setEnabled(jGastro.isSelected() ? true : false);
    }//GEN-LAST:event_jGastroActionPerformed

    private void jZumbidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jZumbidosActionPerformed
     TXT_Zumbidos.setEnabled(jZumbidos.isSelected() ? true : false);
    }//GEN-LAST:event_jZumbidosActionPerformed

    private void jArticulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jArticulacionesActionPerformed
    TXT_inflamacion.setEnabled(jArticulaciones.isSelected() ? true : false);
    }//GEN-LAST:event_jArticulacionesActionPerformed

    private void jSinusitisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSinusitisActionPerformed
    TXT_sinusitis.setEnabled(jSinusitis.isSelected() ? true : false);
    }//GEN-LAST:event_jSinusitisActionPerformed

    private void jRespiradorBucalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRespiradorBucalActionPerformed
      TXT_respiradorbucal.setEnabled(jRespiradorBucal.isSelected() ? true : false);
    }//GEN-LAST:event_jRespiradorBucalActionPerformed

    private void jCongenitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCongenitaActionPerformed
    TXT_Congenita.setEnabled(jCongenita.isSelected() ? true : false);
    }//GEN-LAST:event_jCongenitaActionPerformed

    private void jHormonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHormonalActionPerformed
    TXT_Hormonal.setEnabled(jHormonal.isSelected() ? true : false);
    }//GEN-LAST:event_jHormonalActionPerformed

    private void jGraveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGraveActionPerformed
    TXT_GraveEnfermo.setEnabled(jGrave.isSelected() ? true : false);
    }//GEN-LAST:event_jGraveActionPerformed

    private void jQuirurgicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuirurgicaActionPerformed
    TXT_Intervenido.setEnabled(jQuirurgica.isSelected() ? true : false);
    }//GEN-LAST:event_jQuirurgicaActionPerformed

    private void jVasosAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVasosAguaActionPerformed
    TXT_Agua.setEnabled(jVasosAgua.isSelected() ? true : false);
    }//GEN-LAST:event_jVasosAguaActionPerformed

    private void jAspirinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAspirinaActionPerformed
    TXT_Aspirina.setEnabled(jAspirina.isSelected() ? true : false);
    }//GEN-LAST:event_jAspirinaActionPerformed

    private void jOrinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOrinaActionPerformed
    TXT_Orina.setEnabled(jOrina.isSelected() ? true : false);
    }//GEN-LAST:event_jOrinaActionPerformed

    private void jMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMedicamentoActionPerformed
    TXT_Tratamiento.setEnabled(jMedicamento.isSelected() ? true : false);
    }//GEN-LAST:event_jMedicamentoActionPerformed

    private void jCansansioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCansansioActionPerformed
    TXT_Cansansio.setEnabled(jCansansio.isSelected() ? true : false);
    }//GEN-LAST:event_jCansansioActionPerformed

    private void jAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlimentoActionPerformed
    TXT_Alimento.setEnabled(jAlimento.isSelected() ? true : false);
    }//GEN-LAST:event_jAlimentoActionPerformed

    private void jEmbarazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmbarazoActionPerformed
    TXT_Embarazo.setEnabled(jEmbarazo.isSelected() ? true : false);
    }//GEN-LAST:event_jEmbarazoActionPerformed

    private void jTerapiaHormonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTerapiaHormonalActionPerformed
    TXT_TerapiaHormonal.setEnabled(jTerapiaHormonal.isSelected() ? true : false);
    }//GEN-LAST:event_jTerapiaHormonalActionPerformed

    private void jReaccionAnesteciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReaccionAnesteciaActionPerformed
    TXT_ReaccionAnestecia.setEnabled(jReaccionAnestecia.isSelected() ? true : false);
    }//GEN-LAST:event_jReaccionAnesteciaActionPerformed

    private void jHalitosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHalitosisActionPerformed
    TXT_halitosis.setEnabled(jHalitosis.isSelected() ? true : false);
    }//GEN-LAST:event_jHalitosisActionPerformed

    private void jSatisfechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSatisfechoActionPerformed
    TXT_Apariencia.setEnabled(jSatisfecho.isSelected() ? true : false);
    }//GEN-LAST:event_jSatisfechoActionPerformed

    private void jSangranEnciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSangranEnciasActionPerformed
    TXT_Encias.setEnabled(jSangranEncias.isSelected() ? true : false);
    }//GEN-LAST:event_jSangranEnciasActionPerformed

    private void jDientesMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDientesMActionPerformed
   TXT_MovilidadDientes.setEnabled(jDientesM.isSelected() ? true : false);
    }//GEN-LAST:event_jDientesMActionPerformed

    private void jSensibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSensibilidadActionPerformed
    TXT_Sensibilidad.setEnabled(jSensibilidad.isSelected() ? true : false);
    }//GEN-LAST:event_jSensibilidadActionPerformed

    private void jConsumoAcidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsumoAcidoActionPerformed
    TXT_Alimentos.setEnabled(jConsumoAcido.isSelected() ? true : false);
    }//GEN-LAST:event_jConsumoAcidoActionPerformed

    private void jresequedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jresequedadActionPerformed
    TXTResequedad.setEnabled(jresequedad.isSelected() ? true : false);
    }//GEN-LAST:event_jresequedadActionPerformed

    private void jUlceracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUlceracionesActionPerformed
    TXT_Ulceraciones.setEnabled(jUlceraciones.isSelected() ? true : false);
    }//GEN-LAST:event_jUlceracionesActionPerformed

    private void jNingunaAnterioresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNingunaAnterioresActionPerformed

           if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea colocar todos los campos en `NO`?",
            "Cancelar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        
                cleanAnamnesis(); 
               
                   usingData=false;
           }
        
        
       
    }//GEN-LAST:event_jNingunaAnterioresActionPerformed

    private void jMandibulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMandibulaActionPerformed
        TXT_Mandibula.setEnabled(jMandibula.isSelected() ? true : false);
    }//GEN-LAST:event_jMandibulaActionPerformed

    private void jAlergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlergiaActionPerformed
        jAlergia.setEnabled(jAlergia.isSelected() ? true : false);
    }//GEN-LAST:event_jAlergiaActionPerformed

    private void jRleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRleveActionPerformed
     jRleve.setSelected(true);
     jRmoderado.setSelected(false);
     jRsevero.setSelected(false);
     actualizarVariable("leve", jRleve.isSelected());
     
     
    }//GEN-LAST:event_jRleveActionPerformed

    private void jRmoderadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRmoderadoActionPerformed
     jRleve.setSelected(false);
     jRmoderado.setSelected(true);
     jRsevero.setSelected(false);
     actualizarVariable("moderado", jRmoderado.isSelected());
    }//GEN-LAST:event_jRmoderadoActionPerformed

    private void jRseveroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRseveroActionPerformed
     jRleve.setSelected(false);
     jRmoderado.setSelected(false);
     jRsevero.setSelected(true);
     actualizarVariable("severo", jRsevero.isSelected());
    }//GEN-LAST:event_jRseveroActionPerformed

    private void jRlocalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRlocalizadoActionPerformed
     jRlocalizado.setSelected(true);
     jRdifuso.setSelected(false);
     actualizarVariable("localizado", jRlocalizado.isSelected());
    }//GEN-LAST:event_jRlocalizadoActionPerformed

    private void jRdifusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdifusoActionPerformed
     jRlocalizado.setSelected(false);
     jRdifuso.setSelected(true);
     actualizarVariable("difuso", jRdifuso.isSelected());
    }//GEN-LAST:event_jRdifusoActionPerformed

    private void jRcontinuoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRcontinuoActionPerformed
     jRcontinuo.setSelected(true);
     jRintermitente.setSelected(false);
     actualizarVariable("continuo", jRcontinuo.isSelected());
    }//GEN-LAST:event_jRcontinuoActionPerformed

    private void jRintermitenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRintermitenteActionPerformed
     jRcontinuo.setSelected(false);
     jRintermitente.setSelected(true);
     actualizarVariable("intermitente", jRintermitente.isSelected());
    }//GEN-LAST:event_jRintermitenteActionPerformed

    private void jCespontaneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCespontaneoActionPerformed
   actualizarVariable("espontaneo", jCespontaneo.isSelected());
    }//GEN-LAST:event_jCespontaneoActionPerformed

    private void jClatidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClatidosActionPerformed
      actualizarVariable("latidos", jClatidos.isSelected());
    }//GEN-LAST:event_jClatidosActionPerformed

    private void jTableOdontogramaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOdontogramaMouseEntered
      jTableOdontograma.requestFocusInWindow();
    }//GEN-LAST:event_jTableOdontogramaMouseEntered

    private void jCextraoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCextraoralActionPerformed
      actualizarSigno("jCextraoral", jCextraoral.isSelected());
    }//GEN-LAST:event_jCextraoralActionPerformed

    private void jCfracturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCfracturaActionPerformed
     actualizarSigno("jCfractura", jCfractura.isSelected());
    }//GEN-LAST:event_jCfracturaActionPerformed

    private void jCementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCementoActionPerformed
    actualizarSigno("jCemento", jCemento.isSelected());
    }//GEN-LAST:event_jCementoActionPerformed

    private void jCpalpitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCpalpitacionActionPerformed
         actualizarSigno("jCpalpitacion", jCpalpitacion.isSelected());
    }//GEN-LAST:event_jCpalpitacionActionPerformed

    private void jCintraOralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCintraOralActionPerformed
        actualizarSigno("jCintraOral", jCintraOral.isSelected());
    }//GEN-LAST:event_jCintraOralActionPerformed

    private void jCincrustacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCincrustacionActionPerformed
    actualizarSigno("jCincrustacion", jCincrustacion.isSelected());
    }//GEN-LAST:event_jCincrustacionActionPerformed

    private void jCoronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCoronaActionPerformed
       actualizarSigno("jCorona", jCorona.isSelected());
    }//GEN-LAST:event_jCoronaActionPerformed

    private void jCfistulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCfistulaActionPerformed
       actualizarSigno("jCfistula", jCfistula.isSelected());
    }//GEN-LAST:event_jCfistulaActionPerformed

    private void jCabrasionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCabrasionActionPerformed
       actualizarSigno("jCabrasion", jCabrasion.isSelected());
    }//GEN-LAST:event_jCabrasionActionPerformed

    private void jCdienteMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCdienteMovilActionPerformed
        actualizarSigno("jCdienteMovil", jCdienteMovil.isSelected());
    }//GEN-LAST:event_jCdienteMovilActionPerformed

    private void jCdienteOscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCdienteOscuroActionPerformed
         actualizarSigno("jCdienteOscuro", jCdienteOscuro.isSelected());
    }//GEN-LAST:event_jCdienteOscuroActionPerformed

    private void jCrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCrcActionPerformed
         actualizarSigno("jCrc", jCrc.isSelected());
    }//GEN-LAST:event_jCrcActionPerformed

    private void jCadenopatiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCadenopatiaActionPerformed
         actualizarSigno("jCadenopatia", jCadenopatia.isSelected());
    }//GEN-LAST:event_jCadenopatiaActionPerformed

    private void jCamalgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCamalgamaActionPerformed
       actualizarSigno("jCamalgama", jCamalgama.isSelected());
    }//GEN-LAST:event_jCamalgamaActionPerformed

    private void jCarieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCarieActionPerformed
        actualizarSigno("jCarie", jCarie.isSelected());
    }//GEN-LAST:event_jCarieActionPerformed

    private void jTableOdontogramaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTableOdontogramaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableOdontogramaAncestorAdded

    private void btnCancerlarOdontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancerlarOdontoActionPerformed

    
              
             if (JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cancelar la informacion?",
            "Informacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

           String imagePath = "C:\\Fundaginebra\\src\\imagenes\\odontograma.png";
           cancelarOdonto();
           searchOdontogram();
           loadImage(imagePath, jLabel4);
             }
    
    
    
    }//GEN-LAST:event_btnCancerlarOdontoActionPerformed

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

    private void jTableAntcPersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntcPersMouseEntered
   jTableAntcPers.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntcPersMouseEntered

    private void jTableAntpersonalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntpersonalMouseEntered
      jTableAntpersonal.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntpersonalMouseEntered

    private void jTableAntecedentesFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAntecedentesFMouseEntered
         jTableAntecedentesF.requestFocusInWindow();
    }//GEN-LAST:event_jTableAntecedentesFMouseEntered

    private void jTableAF2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAF2MouseEntered
      jTableAntecedentesF.requestFocusInWindow();
    }//GEN-LAST:event_jTableAF2MouseEntered

    private void jTableAdicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAdicMouseEntered
        jTableAdic.requestFocusInWindow();
    }//GEN-LAST:event_jTableAdicMouseEntered

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        if (jComboBox2.getItemCount() == 0 || jTextField1.getText().isEmpty()) {
            llenarResultado();
        } else {
            filterComboBox();
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextMotivoConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextMotivoConsultaMouseClicked
      
    }//GEN-LAST:event_jTextMotivoConsultaMouseClicked

    
    
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
    
    
    
    
    
   
    public void cancelarOdonto(){
       btnAgregarOdonto.setEnabled(true);
       btnModificarOdonto.setEnabled(false);
       btnRetirarOdonto.setEnabled(false);
       jTextRadiologica.setText("");
       JtxtInforme.setText("");
       jComboDiente.setSelectedItem("Seleccionar diente");
       jCpresente.setSelected(false);
       jCespontaneo.setSelected(false);
       jClatidos.setSelected(false);
       jRleve.setSelected(false);
       jRmoderado.setSelected(false);
       jRsevero.setSelected(false);
       jRlocalizado.setSelected(false);
       jRdifuso.setSelected(false);
       jRcontinuo.setSelected(false);
       jRintermitente.setSelected(false);
       jCextraoral.setSelected(false);
       jCfractura.setSelected(false);
       jCemento.setSelected(false);
       jCpalpitacion.setSelected(false);
       jCintraOral.setSelected(false);
       jCincrustacion.setSelected(false);
       jCorona.setSelected(false);
       jCfistula.setSelected(false);
       jCabrasion.setSelected(false);
       jCdienteMovil.setSelected(false);
       jCdienteOscuro.setSelected(false);
       jCrc.setSelected(false);
       jCadenopatia.setSelected(false);
       jCamalgama.setSelected(false);
       jCarie.setSelected(false);
       jTxtOtros.setText("");

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
       Font Letra, Letrasmall;
       public void pdfHistorias() {
      
       try {
 
         PdfNamesHistorias="Historia_Paciente";

         BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
         Letra = new Font(BF, 10); 
         Letrasmall = new Font(BF, 8);
         Paragraph saltolinea = new Paragraph();
          

         FileOutputStream archivo;
         fileHistoria = new File("C://Fundaginebra//Reportes_Consulta//"+PdfNamesHistorias+".pdf");
         archivo = new FileOutputStream(fileHistoria);
         Document doc = new Document();
         PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
         doc.setMargins(36, 36, 36, 130);
  
            
        // H E A D E R
        // HeaderFooterPageEvent headerEvent = new HeaderFooterPageEvent();
        // writer.setPageEvent(headerEvent);

        // F O O T E R
         writer.setPageEvent(eventHelper);
       
        
      
    
           
            doc.open();
           
            String fechaN = new SimpleDateFormat("yyyy/MM/dd").format(fechanac);
            String [] dateParts1= fechaN.split("/");
            String an1 = dateParts1[0];
            String mes1 = dateParts1[1];
            String dia1 = dateParts1[2];
            
            
            String an_actual = Validar.fecha_actual();
            String mes_actual = Validar.fecha_mes();
            String dia_actual = Validar.fecha_dia();
            

            
            PdfNamesHistorias="Historia_Paciente";
            
        
            
            int diferenciadia=Integer.parseInt(dia_actual)- Integer.parseInt(dia1);
            int diferenciames=Integer.parseInt(mes_actual)- Integer.parseInt(mes1);
            int diferencian=Integer.parseInt(an_actual)- Integer.parseInt(an1);;

            
            if (diferenciames < 0 || (diferenciames == 0 && diferenciadia < 0)) {
            diferencian = diferencian - 1;
            }
        
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
            
            
            
            
            
            
            //B O D Y
            
            
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
            Habitos.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Habitos.addElement(new Paragraph(jComboHabitos.getSelectedItem().toString(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            Habitos.setBorder(PdfPCell.NO_BORDER);
            
            
            PdfPCell Alcohol = new PdfPCell();
            Alcohol.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Alcohol.addElement(new Paragraph(jTConsumoAlcohol.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            Alcohol.setBorder(PdfPCell.NO_BORDER);
            
            PdfPCell Tabaco = new PdfPCell();
            Tabaco.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Tabaco.addElement(new Paragraph(jTConsumoTabaco.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            Tabaco.setBorder(PdfPCell.NO_BORDER);
            
            PdfPCell Esupefaciente = new PdfPCell();
            Esupefaciente.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Esupefaciente.addElement(new Paragraph(jTConsumoDrugs.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL, BaseColor.BLACK)));
            Esupefaciente.setBorder(PdfPCell.NO_BORDER);
          
          
           TablaFuncional5.addCell(Habitos); 
           TablaFuncional5.addCell(Alcohol); 
           TablaFuncional5.addCell(Tabaco); 
           TablaFuncional5.addCell(Esupefaciente); 
            
            
            
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
           // TablaFuncional2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] medidaCeldaFisico = {2f, 2f, 2f,2f,2f, 2f,2f};
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

            Paragraph t29columna3 = new Paragraph("Peso ideal");
            t29columna3.getFont().setStyle(Font.BOLD);
            t29columna3.getFont().setSize(9);        
            t29columna3.setFont(Letrasmall);
            t29columna3.setAlignment(Element.ALIGN_CENTER);
            TableFisico2.addCell(t29columna3);

            Paragraph t30columna4 = new Paragraph("Peso adicional");
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
            

            TableFisico2.addCell(new Paragraph(TXTtalla.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTpeso.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTpesoIdeal.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTpesoAdic.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTimc.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTtemperatura.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico2.addCell(new Paragraph(TXTpulso.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            
            
            
            
            
            
            
            
            PdfPTable TableFisico3 = new PdfPTable(6); 
            TableFisico3.setWidthPercentage(100);  
           // TablaFuncional2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] medidaCeldaFisico2 = {2f, 3f, 3f,2f,2f,2f};
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
            
           

            TableFisico3.addCell(new Paragraph(TXTfr.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico3.addCell(new Paragraph(TXTrespiracion.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico3.addCell(new Paragraph(TXTaspecto.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico3.addCell(new Paragraph(TXTtension.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico3.addCell(new Paragraph(TXTabdominal.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
            TableFisico3.addCell(new Paragraph(TXTcadera.getText(),FontFactory.getFont("Arial",8,Font.NORMAL))); 
          
            
            
           
            

    
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

            PdfPCell Thend1 = new PdfPCell(t68columna1);
            Thend1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Thend1.setBorder(Rectangle.NO_BORDER);
           // aspectoFisico.enableBorderSide(Rectangle.BOTTOM);
            TableConclusion2.addCell(Thend1);
            
            Paragraph t69columna1 = new Paragraph(jTextRecomendacion.getText());
            t69columna1.getFont().setStyle(Font.NORMAL);
            t69columna1.getFont().setSize(9);        
            t69columna1.setFont(Letrasmall);
            t69columna1.setAlignment(Element.ALIGN_JUSTIFIED);
            PdfPCell Thend2 = new PdfPCell(t69columna1);
            Thend2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            Thend2.setBorder(Rectangle.NO_BORDER);
      
            //aspectoFisico2.enableBorderSide(Rectangle.BOTTOM);
            TableConclusion2.addCell(Thend2);






 
            
              saltolinea.add("\n");
           // H E A D E R

            doc.add(Tabla); 
            doc.add(Tabla0);
            doc.add(saltolinea);
            
            
            //B O D Y
            
            //MOTIVO
          
            doc.add(Tabla2);
            doc.add(saltolinea);
            

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

            

    

           
            //mestruacion
           
                 
            if(sexo.equals("Femenino") && !TxtCiclosMestru.getText().equals("")){
           
            doc.add(TablaFuncional3);
            doc.add(saltolinea);
    
            }
            
            
            
            
          if(!jComboHabitos.getSelectedItem().equals("N/A")||
             !jTConsumoAlcohol.getText().equals("")   ||
             !jTConsumoTabaco.getText().equals("")   ||
             !jTConsumoDrugs.getText().equals("")   ||
             !jComboActividad.getSelectedItem().equals("N/A")||
             !jComboFrec.getSelectedItem().equals("N/A")||
             !jComboSueno.getSelectedItem().equals("N/A")||
             !jComboSexualidad.getSelectedItem().equals("N/A")){  
            
              doc.add(TablaFuncional5);
              doc.add(saltolinea);
              doc.add(TablaFuncional6);
              doc.add(saltolinea);
          }
          
          
          
           int ant1 = jTableAF2.getRowCount();  
           int ant2 =  jTableAntpersonal.getRowCount();  
            

  
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
      
           
           
           
          
          
          
          
          
          
          
          
            
            
            //Anamnesis
           
            
            Map<String, Boolean> Selections = new HashMap<>();
            Selections.put("JCardiovascular", JCardiovascular.isSelected());
            Selections.put("JHemorragias", JHemorragias.isSelected());
            Selections.put("jAnemia", jAnemia.isSelected());
            Selections.put("jRenales", jRenales.isSelected());
            Selections.put("jHepaticas", jHepaticas.isSelected());
            Selections.put("jAsma", jAsma.isSelected());
            Selections.put("jRespiratorias", jRespiratorias.isSelected());
            Selections.put("jDiabetes", jDiabetes.isSelected());
            Selections.put("jHipertension", jHipertension.isSelected());
            Selections.put("jMigrañas", jMigrañas.isSelected());
            Selections.put("jFiebre", jFiebre.isSelected());
            Selections.put("jInfecciosas", jInfecciosas.isSelected());
            Selections.put("jTransmision", jTransmision.isSelected());
            Selections.put("jGastro", jGastro.isSelected());
            Selections.put("jZumbidos", jZumbidos.isSelected());
            Selections.put("jArticulaciones", jArticulaciones.isSelected());
            Selections.put("jSinusitis", jSinusitis.isSelected());
            Selections.put("jRespiradorBucal", jRespiradorBucal.isSelected());
            Selections.put("jCongenita", jCongenita.isSelected());
            Selections.put("jHormonal", jArticulaciones.isSelected());
            Selections.put("jGrave", jGrave.isSelected());
            Selections.put("jQuirurgica", jQuirurgica.isSelected());
            Selections.put("jVasosAgua", jVasosAgua.isSelected());
            Selections.put("jAspirina", jAspirina.isSelected());
            Selections.put("jOrina", jOrina.isSelected());
            Selections.put("jMedicamento", jMedicamento.isSelected());
            Selections.put("jCansansio", jCansansio.isSelected());
            Selections.put("jAlimento", jAlimento.isSelected());  
            Selections.put("jAlergia", jAlergia.isSelected()); 
            Selections.put("jEmbarazo", jEmbarazo.isSelected());
            Selections.put("jTerapiaHormonal", jTerapiaHormonal.isSelected());
            Selections.put("jReaccionAnestecia", jReaccionAnestecia.isSelected());
            Selections.put("jHalitosis", jHalitosis.isSelected());  
            Selections.put("jSatisfecho", jSatisfecho.isSelected());
            Selections.put("jSangranEncias", jSangranEncias.isSelected());
            Selections.put("jDientesM", jDientesM.isSelected());
            Selections.put("jSensibilidad",jSensibilidad.isSelected()); 
            Selections.put("jConsumoAcido", jConsumoAcido.isSelected());
            Selections.put("jresequedad", jresequedad.isSelected());
            Selections.put("jUlceraciones", jUlceraciones.isSelected());
            Selections.put("jMandibula", jMandibula.isSelected());   
            Selections.put("jNingunaAnteriores", jNingunaAnteriores.isSelected());   
            
                  
           boolean existenElementos = false;
           for (boolean seleccion : Selections.values()) {
           if (seleccion) {
           existenElementos = true;
           break;
           }}
            
           if (existenElementos) {

           PdfPTable[] tableAnamnesis = crearTablaAnamnesisI();
           
           
           Paragraph tituloAnamnesis = new Paragraph("A N A M N E S I S", Letra);
           tituloAnamnesis.setAlignment(Element.ALIGN_CENTER);
           tituloAnamnesis.getFont().setStyle(Font.BOLD);
           tituloAnamnesis.getFont().setSize(10);
           
           
           doc.add(tituloAnamnesis);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[0]);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[1]);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[2]);
           doc.add(saltolinea);
           
           if(!TXT_Observaciones.getText().equals("")){
           doc.add(tableAnamnesis[3]);
           doc.add(saltolinea);
  
           }
           
           }
               
            
           
           
           
           //EXAMENES ODONTOLOGICOS
           
             if(!AspectoTxt.getText().equals("") ||
             !GangliosTxt.getText().equals("") ||
             !TxtTiroidea.getText().equals("") ||
             !MuscularTxt.getText().equals("") ||
             !TemporomandibularTxt.getText().equals("") ||
             !ComisuraTxt.getText().equals("") ||
             !CarillosTxt.getText().equals("") ||
             !PaladarTxt.getText().equals("") ||
             !AmigdalinaTxt.getText().equals("") ||
             !LenguaTxt.getText().equals("") ||
             !MaxilaresTxt.getText().equals("") ||
             !SalivaTxt.getText().equals("")){
     
             PdfPTable tableExamenes =  crearTablaExamenes();
             doc.add(tableExamenes);
             doc.add(saltolinea);
             }
           
           
        
          
          
       
           
      // THIS IS ABOUT ODONTOGRAMA\
      
      
      int rowtableOdontograma =  jTableOdontograma.getRowCount();  
     
    
      if ( rowtableOdontograma> 0) {
          
           Paragraph tituloOdontograma = new Paragraph("O D O N T O G R A M A", Letra);
           tituloOdontograma.setAlignment(Element.ALIGN_CENTER);
           tituloOdontograma.getFont().setStyle(Font.BOLD);
           tituloOdontograma.getFont().setSize(10);
           
           doc.add(tituloOdontograma);
           doc.add(saltolinea);
           
           PdfPTable tableOdontograma= crearTablaOdontograma() ;
           doc.add( tableOdontograma);
           doc.add(saltolinea);
           
      }
      
      
      
  if (jimgOdontograma.isSelected()) {
    com.itextpdf.text.Image imgOdontograma = null;
    try {
        imgOdontograma = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\odontograma.png");

        float pageWidth = doc.getPageSize().getWidth();
        float imageWidth = imgOdontograma.getScaledWidth();
        float xPos = (pageWidth - imageWidth) / 2;

        // Establece la posición X de la imagen en el documento
        imgOdontograma.setAlignment(Element.ALIGN_CENTER);
        // Establece el tamaño y la posición de la imagen en el documento
        imgOdontograma.scaleToFit(250, 250); // Ajusta el tamaño de la imagen

        // Agrega la imagen al documento
        doc.add(imgOdontograma);
        doc.add(saltolinea);
    } catch (IOException | BadElementException e) {
        e.printStackTrace();
        // Manejar la excepción de alguna manera apropiada
    }
}
      
  
           //PROCEDIMIENTOS
            int tProcedimientos = jTableProcedimientos.getRowCount();  
            if (tProcedimientos> 0 ) {
            doc.add(TableProcedimiento);
            doc.add(saltolinea);
              }
            
      
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
            
  
            
            
            
         
           
           
/*

           
            saltolinea.add("\n");
           // H E A D E R

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
            
            
            
            //Anamnesis
           
            
            Map<String, Boolean> Selections = new HashMap<>();
            Selections.put("JCardiovascular", JCardiovascular.isSelected());
            Selections.put("JHemorragias", JHemorragias.isSelected());
            Selections.put("jAnemia", jAnemia.isSelected());
            Selections.put("jRenales", jRenales.isSelected());
            Selections.put("jHepaticas", jHepaticas.isSelected());
            Selections.put("jAsma", jAsma.isSelected());
            Selections.put("jRespiratorias", jRespiratorias.isSelected());
            Selections.put("jDiabetes", jDiabetes.isSelected());
            Selections.put("jHipertension", jHipertension.isSelected());
            Selections.put("jMigrañas", jMigrañas.isSelected());
            Selections.put("jFiebre", jFiebre.isSelected());
            Selections.put("jInfecciosas", jInfecciosas.isSelected());
            Selections.put("jTransmision", jTransmision.isSelected());
            Selections.put("jGastro", jGastro.isSelected());
            Selections.put("jZumbidos", jZumbidos.isSelected());
            Selections.put("jArticulaciones", jArticulaciones.isSelected());
            Selections.put("jSinusitis", jSinusitis.isSelected());
            Selections.put("jRespiradorBucal", jRespiradorBucal.isSelected());
            Selections.put("jCongenita", jCongenita.isSelected());
            Selections.put("jHormonal", jArticulaciones.isSelected());
            Selections.put("jGrave", jGrave.isSelected());
            Selections.put("jQuirurgica", jQuirurgica.isSelected());
            Selections.put("jVasosAgua", jVasosAgua.isSelected());
            Selections.put("jAspirina", jAspirina.isSelected());
            Selections.put("jOrina", jOrina.isSelected());
            Selections.put("jMedicamento", jMedicamento.isSelected());
            Selections.put("jCansansio", jCansansio.isSelected());
            Selections.put("jAlimento", jAlimento.isSelected());  
            Selections.put("jAlergia", jAlergia.isSelected()); 
            Selections.put("jEmbarazo", jEmbarazo.isSelected());
            Selections.put("jTerapiaHormonal", jTerapiaHormonal.isSelected());
            Selections.put("jReaccionAnestecia", jReaccionAnestecia.isSelected());
            Selections.put("jHalitosis", jHalitosis.isSelected());  
            Selections.put("jSatisfecho", jSatisfecho.isSelected());
            Selections.put("jSangranEncias", jSangranEncias.isSelected());
            Selections.put("jDientesM", jDientesM.isSelected());
            Selections.put("jSensibilidad",jSensibilidad.isSelected()); 
            Selections.put("jConsumoAcido", jConsumoAcido.isSelected());
            Selections.put("jresequedad", jresequedad.isSelected());
            Selections.put("jUlceraciones", jUlceraciones.isSelected());
            Selections.put("jMandibula", jMandibula.isSelected());   
            Selections.put("jNingunaAnteriores", jNingunaAnteriores.isSelected());   
            
                  
           boolean existenElementos = false;
           for (boolean seleccion : Selections.values()) {
           if (seleccion) {
           existenElementos = true;
           break;
           }}
            
           if (existenElementos) {

           PdfPTable[] tableAnamnesis = crearTablaAnamnesisI();
           
           
           Paragraph tituloAnamnesis = new Paragraph("A N A M N E S I S", Letra);
           tituloAnamnesis.setAlignment(Element.ALIGN_CENTER);
           tituloAnamnesis.getFont().setStyle(Font.BOLD);
           tituloAnamnesis.getFont().setSize(10);
           
           
           doc.add(tituloAnamnesis);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[0]);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[1]);
           doc.add(saltolinea);
           doc.add(tableAnamnesis[2]);
           doc.add(saltolinea);
           
           if(!TXT_Observaciones.getText().equals("")){
           doc.add(tableAnamnesis[3]);
           doc.add(saltolinea);
  
           }
           
           }
               
            
           
           
           
           //EXAMENES 
           
             if(!AspectoTxt.getText().equals("") ||
             !GangliosTxt.getText().equals("") ||
             !TxtTiroidea.getText().equals("") ||
             !MuscularTxt.getText().equals("") ||
             !TemporomandibularTxt.getText().equals("") ||
             !ComisuraTxt.getText().equals("") ||
             !CarillosTxt.getText().equals("") ||
             !PaladarTxt.getText().equals("") ||
             !AmigdalinaTxt.getText().equals("") ||
             !LenguaTxt.getText().equals("") ||
             !MaxilaresTxt.getText().equals("") ||
             !SalivaTxt.getText().equals("")){
     
             PdfPTable tableExamenes =  crearTablaExamenes();
             doc.add(tableExamenes);
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
      
           
           
           

           
            //mestruacion
           
                 
            if(sexo.equals("Femenino") && !TxtCiclosMestru.getText().equals("")){
           
            doc.add(TablaFuncional3);
            doc.add(saltolinea);
    
            }
            
            
            
            
          if(!jComboHabitos.getSelectedItem().equals("N/A")||
             !jTConsumoAlcohol.getText().equals("")   ||
             !jTConsumoTabaco.getText().equals("")   ||
             !jTConsumoDrugs.getText().equals("")   ||
             !jComboActividad.getSelectedItem().equals("N/A")||
             !jComboFrec.getSelectedItem().equals("N/A")||
             !jComboSueno.getSelectedItem().equals("N/A")||
             !jComboSexualidad.getSelectedItem().equals("N/A")){  
            
              doc.add(TablaFuncional5);
              doc.add(saltolinea);
              doc.add(TablaFuncional6);
              doc.add(saltolinea);
          }
          
          
          
          
          
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

           
      // THIS IS ABOUT ODONTOGRAMA\
      
      
      int rowtableOdontograma =  jTableOdontograma.getRowCount();  
     
    
      if ( rowtableOdontograma> 0) {
          
           Paragraph tituloOdontograma = new Paragraph("O D O N T O G R A M A", Letra);
           tituloOdontograma.setAlignment(Element.ALIGN_CENTER);
           tituloOdontograma.getFont().setStyle(Font.BOLD);
           tituloOdontograma.getFont().setSize(10);
           
           doc.add(tituloOdontograma);
           doc.add(saltolinea);
           
           PdfPTable tableOdontograma= crearTablaOdontograma() ;
           doc.add( tableOdontograma);
           doc.add(saltolinea);
           
      }
      
      
      
  if (jimgOdontograma.isSelected()) {
    com.itextpdf.text.Image imgOdontograma = null;
    try {
        imgOdontograma = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\odontograma.png");

        float pageWidth = doc.getPageSize().getWidth();
        float imageWidth = imgOdontograma.getScaledWidth();
        float xPos = (pageWidth - imageWidth) / 2;

        // Establece la posición X de la imagen en el documento
        imgOdontograma.setAlignment(Element.ALIGN_CENTER);
        // Establece el tamaño y la posición de la imagen en el documento
        imgOdontograma.scaleToFit(250, 250); // Ajusta el tamaño de la imagen

        // Agrega la imagen al documento
        doc.add(imgOdontograma);
        doc.add(saltolinea);
    } catch (IOException | BadElementException e) {
        e.printStackTrace();
        // Manejar la excepción de alguna manera apropiada
    }
}
      
  
      
      
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
            
     
            
            
*/
            
       
            
    
            doc.close();
            archivo.close();
            

            
            
            
        } catch (DocumentException | IOException e) {
           System.out.println(e);
           JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);  
        }
    }

 
       
       
       
        private PdfPTable[] crearTablaAnamnesisI() {
        PdfPTable[] tablasAnamnesis = new PdfPTable[4];

            try{

            Map<String, Boolean> Selections = new HashMap<>();
            Selections.put("JCardiovascular", JCardiovascular.isSelected());
            Selections.put("JHemorragias", JHemorragias.isSelected());
            Selections.put("jAnemia", jAnemia.isSelected());
            Selections.put("jRenales", jRenales.isSelected());
            Selections.put("jHepaticas", jHepaticas.isSelected());
            Selections.put("jAsma", jAsma.isSelected());
            Selections.put("jRespiratorias", jRespiratorias.isSelected());
            Selections.put("jDiabetes", jDiabetes.isSelected());
            Selections.put("jHipertension", jHipertension.isSelected());
            Selections.put("jMigrañas", jMigrañas.isSelected());
            Selections.put("jFiebre", jFiebre.isSelected());
            Selections.put("jInfecciosas", jInfecciosas.isSelected());
            Selections.put("jTransmision", jTransmision.isSelected());
            Selections.put("jGastro", jGastro.isSelected());
            Selections.put("jZumbidos", jZumbidos.isSelected());
            Selections.put("jArticulaciones", jArticulaciones.isSelected());
            Selections.put("jSinusitis", jSinusitis.isSelected());
            Selections.put("jRespiradorBucal", jRespiradorBucal.isSelected());
            Selections.put("jCongenita", jCongenita.isSelected());
            Selections.put("jHormonal", jArticulaciones.isSelected());
            Selections.put("jGrave", jGrave.isSelected());
            Selections.put("jQuirurgica", jQuirurgica.isSelected());
            Selections.put("jVasosAgua", jVasosAgua.isSelected());
            Selections.put("jAspirina", jAspirina.isSelected());
            Selections.put("jOrina", jOrina.isSelected());
            Selections.put("jMedicamento", jMedicamento.isSelected());
            Selections.put("jCansansio", jCansansio.isSelected());
            Selections.put("jAlimento", jAlimento.isSelected());  
            Selections.put("jAlergia", jAlergia.isSelected()); 
            Selections.put("jEmbarazo", jEmbarazo.isSelected());
            Selections.put("jTerapiaHormonal", jTerapiaHormonal.isSelected());
            Selections.put("jReaccionAnestecia", jReaccionAnestecia.isSelected());
            Selections.put("jHalitosis", jHalitosis.isSelected());  
            Selections.put("jSatisfecho", jSatisfecho.isSelected());
            Selections.put("jSangranEncias", jSangranEncias.isSelected());
            Selections.put("jDientesM", jDientesM.isSelected());
            Selections.put("jSensibilidad",jSensibilidad.isSelected()); 
            Selections.put("jConsumoAcido", jConsumoAcido.isSelected());
            Selections.put("jresequedad", jresequedad.isSelected());
            Selections.put("jUlceraciones", jUlceraciones.isSelected());
            Selections.put("jMandibula", jMandibula.isSelected());   
            Selections.put("jNingunaAnteriores", jNingunaAnteriores.isSelected());   
           
            
            
        
        
                
            PdfPTable tableAnamnesis1 = new PdfPTable(4); 
            tableAnamnesis1 = new PdfPTable(4); 
            tableAnamnesis1.setWidthPercentage(100);  
            float[] medidaCeldaAnamn = {4f, 1f, 1f, 7f };
            tableAnamnesis1.setWidths(medidaCeldaAnamn);
            tableAnamnesis1.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableAnamnesis1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            Paragraph tAnam1 = new Paragraph("Padece usted o ha padecido de");
            tAnam1.getFont().setStyle(Font.BOLD);
            tAnam1.getFont().setSize(8);        
            tAnam1.setFont(Letrasmall);
            tAnam1.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis1.addCell(tAnam1);
     
                
            Paragraph tAnam2 = new Paragraph("SI");
            tAnam2.getFont().setStyle(Font.BOLD);
            tAnam2.getFont().setSize(8);        
            tAnam2.setFont(Letrasmall);
            tAnam2.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis1.addCell(tAnam2);

            Paragraph tAnam3 = new Paragraph("NO");
            tAnam3.getFont().setStyle(Font.BOLD);
            tAnam3.getFont().setSize(8);        
            tAnam3.setFont(Letrasmall);
            tAnam3.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis1.addCell(tAnam3);

            Paragraph tAnam4 = new Paragraph("Especifique");
            tAnam4.getFont().setStyle(Font.BOLD);
            tAnam4.getFont().setSize(8);        
            tAnam4.setFont(Letrasmall);
            tAnam4.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis1.addCell(tAnam4);
            
            

            
          tableAnamnesis1.addCell(new Paragraph("¿Alteraciones cardiovasculares?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("JCardiovascular")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(Txt_Alteraciones.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
            
            
           tableAnamnesis1.addCell(new Paragraph("¿Hemorragias o sangrados frecuentes?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("JHemorragias")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(Txt_Hemorragia.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Anemia u otra alteración sanguínea?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jAnemia")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(Txt_Anemia.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
          tableAnamnesis1.addCell(new Paragraph("¿Enfermedades renales?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jRenales")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(Txt_renales.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Enfermedades hepáticas?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jHepaticas")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Hepaticas.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Asma o alguna dificultad para respirar?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jAsma")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_asma.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Enfermedades respiratorias?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jRespiratorias")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Respiratorias.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
           
           
            tableAnamnesis1.addCell(new Paragraph("¿Diabetes Mellitus?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jDiabetes")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_diabetes.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           
            
           
           tableAnamnesis1.addCell(new Paragraph("¿Hipertensión?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jHipertension")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Hipertension.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
            
            
           tableAnamnesis1.addCell(new Paragraph("¿Migrañas o jaquecas frecuentes?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jMigrañas")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Jaqueca.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
            
            
            
           tableAnamnesis1.addCell(new Paragraph("¿Fiebre reumática?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jFiebre")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_reumatica.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            
            
            
           tableAnamnesis1.addCell(new Paragraph("¿Enfermedades infecciosas?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jInfecciosas")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_infecciosa.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            
             
           tableAnamnesis1.addCell(new Paragraph("¿Enfermedades de transmisión sexual?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jTransmision")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_SEXUAL.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Enfermedades gastrointestinales?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jGastro")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Gastrointestinal.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Dolores de oído frecuentes o zumbidos?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jZumbidos")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Zumbidos.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
           tableAnamnesis1.addCell(new Paragraph("¿Se le inflaman los pies o articulaciones frecuentemente?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jZumbidos")) {
           tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis1.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis1.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis1.addCell(new Paragraph(TXT_Zumbidos.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           

           
            PdfPTable tableAnamnesis2 = new PdfPTable(4); 
            tableAnamnesis2 = new PdfPTable(4); 
            tableAnamnesis2.setWidthPercentage(100);  
            tableAnamnesis2.setWidths(medidaCeldaAnamn);
            tableAnamnesis2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableAnamnesis2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            Paragraph tAnam5 = new Paragraph("HISTORIA GENERAL");
            tAnam5.getFont().setStyle(Font.BOLD);
            tAnam5.getFont().setSize(8);        
            tAnam5.setFont(Letrasmall);
            tAnam5.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis2.addCell(tAnam5);
     
                
            Paragraph tAnam6 = new Paragraph("SI");
            tAnam6.getFont().setStyle(Font.BOLD);
            tAnam6.getFont().setSize(8);        
            tAnam6.setFont(Letrasmall);
            tAnam6.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis2.addCell(tAnam6);

            Paragraph tAnam7 = new Paragraph("NO");
            tAnam7.getFont().setStyle(Font.BOLD);
            tAnam7.getFont().setSize(8);        
            tAnam7.setFont(Letrasmall);
            tAnam7.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis2.addCell(tAnam7);

            Paragraph tAnam8 = new Paragraph("Especifique");
            tAnam8.getFont().setStyle(Font.BOLD);
            tAnam8.getFont().setSize(8);        
            tAnam8.setFont(Letrasmall);
            tAnam8.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis2.addCell(tAnam8);
            
           
           
          tableAnamnesis2.addCell(new Paragraph("¿Alguna vez estuvo gravemente enfermo?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jGrave")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_GraveEnfermo.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis2.addCell(new Paragraph("¿Ha sido intervenido quirúrgicamente?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jQuirurgica")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Intervenido.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           tableAnamnesis2.addCell(new Paragraph("¿Toma más de 8 vasos de agua al día?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jVasosAgua")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Agua.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
                 
           tableAnamnesis2.addCell(new Paragraph("¿Toma frecuentemente aspirina o similar?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jAspirina")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Aspirina.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
                    
           tableAnamnesis2.addCell(new Paragraph("¿Orina frecuentemente durante el día (más de 6 veces)?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jOrina")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Orina.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
          tableAnamnesis2.addCell(new Paragraph("¿Está tomando algún tipo de medicamento o está bajo algún tratamiento médico?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jMedicamento")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Tratamiento.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
            tableAnamnesis2.addCell(new Paragraph("¿Se cansa fácilmente al realizar algún esfuerzo físico?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jCansansio")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Cansansio.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
                      
            tableAnamnesis2.addCell(new Paragraph("¿Hay algún alimento que usted no pueda comer?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jAlimento")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Alimento.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
                               
           tableAnamnesis2.addCell(new Paragraph("¿Es alérgico a alguna sustancia o medicamento?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jAlergia")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Alergico.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis2.addCell(new Paragraph("¿Está usted embarazada?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jEmbarazo")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_Embarazo.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           tableAnamnesis2.addCell(new Paragraph("¿Toma algún tipo de terapia hormonal o anticonceptivos?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           if (Selections.get("jTerapiaHormonal")) {
           tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           } else {
            tableAnamnesis2.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis2.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
           tableAnamnesis2.addCell(new Paragraph(TXT_TerapiaHormonal.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
           
            
           
           
            PdfPTable tableAnamnesis3 = new PdfPTable(4); 
            tableAnamnesis3 = new PdfPTable(4); 
            tableAnamnesis3.setWidthPercentage(100);  
            tableAnamnesis3.setWidths(medidaCeldaAnamn);
            tableAnamnesis3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableAnamnesis3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            Paragraph tAnam9 = new Paragraph("HISTORIA BUCAL");
            tAnam9.getFont().setStyle(Font.BOLD);
            tAnam9.getFont().setSize(8);        
            tAnam9.setFont(Letrasmall);
            tAnam9.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis3.addCell(tAnam9);
     
                
            Paragraph tAnam10 = new Paragraph("SI");
            tAnam10.getFont().setStyle(Font.BOLD);
            tAnam10.getFont().setSize(8);        
            tAnam10.setFont(Letrasmall);
            tAnam10.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis3.addCell(tAnam10);

            Paragraph tAnam11 = new Paragraph("NO");
            tAnam11.getFont().setStyle(Font.BOLD);
            tAnam11.getFont().setSize(8);        
            tAnam11.setFont(Letrasmall);
            tAnam11.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis3.addCell(tAnam11);

            Paragraph tAnam12 = new Paragraph("Especifique");
            tAnam12.getFont().setStyle(Font.BOLD);
            tAnam12.getFont().setSize(8);        
            tAnam12.setFont(Letrasmall);
            tAnam12.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis3.addCell(tAnam12);
            
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Ha presentado alguna reacción al anestésico local?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jReaccionAnestecia")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_ReaccionAnestecia.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
               
            tableAnamnesis3.addCell(new Paragraph("¿Ha presentado mal olor o sabor de boca (halitosis)?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jHalitosis")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_halitosis.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Está satisfecho con la apariencia de sus dientes?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jSatisfecho")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_Apariencia.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
            
            tableAnamnesis3.addCell(new Paragraph("¿Le sangran las encías frecuentemente?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jSangranEncias")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_Encias.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Siente que sus dientes se mueven?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jDientesM")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_MovilidadDientes.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
            
            
            tableAnamnesis3.addCell(new Paragraph("¿Tiene sensibilidad o dolor en alguno de sus dientes?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jSensibilidad")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_Sensibilidad.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Consume muchos alimentos ácidos, calientes o fríos?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jConsumoAcido")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_Alimentos.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Siente resequedad en la boca frecuentemente?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jresequedad")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXTResequedad.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Siente molestias por ulceraciones en la boca?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jUlceraciones")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }
            tableAnamnesis3.addCell(new Paragraph(TXT_Ulceraciones.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
           
            tableAnamnesis3.addCell(new Paragraph("¿Le molesta o le suena la mandíbula al masticar?", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            if (Selections.get("jMandibula")) {
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            }  else {
            tableAnamnesis3.addCell(new Paragraph("", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
            tableAnamnesis3.addCell(new Paragraph("X", FontFactory.getFont("Arial", 8, Font.NORMAL))); 
               }
            tableAnamnesis3.addCell(new Paragraph(TXT_Mandibula.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL))); 
           
 
            
            
               
          
            PdfPTable  tableAnamnesis4 = new PdfPTable(1);  
            tableAnamnesis4.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tableAnamnesis4.setWidthPercentage(100);  
            float[] medidaCeldaAnamn2 = {10f};
            tableAnamnesis4.setWidths(medidaCeldaAnamn2);
            tableAnamnesis4.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableAnamnesis4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            Paragraph tAnam13 = new Paragraph("Observaciones");
            tAnam13.getFont().setStyle(Font.BOLD);
            tAnam13.getFont().setSize(10);        
            tAnam13.setFont(Letrasmall);
            tAnam13.setAlignment(Element.ALIGN_CENTER);
            tableAnamnesis4.addCell(tAnam13);
     

            tableAnamnesis4.addCell(new Paragraph(TXT_Observaciones.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
            
     
            
            
            
            
            
            
            
            
            
            tablasAnamnesis[0] = tableAnamnesis1;
            tablasAnamnesis[1] = tableAnamnesis2;
            tablasAnamnesis[2] = tableAnamnesis3;
            tablasAnamnesis[3] = tableAnamnesis4;
           
            }catch(Exception  e){ System.out.println(e);}
             return tablasAnamnesis;
           
          }
              
        
        
        
        
        
        
         private PdfPTable crearTablaExamenes() {
         PdfPTable tableAnamnesis1 = null;
        
            try{
            
 
          tableAnamnesis1 = new PdfPTable(2);  
          tableAnamnesis1.setWidthPercentage(100);  
         // tableAnamnesis1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
          float[] medidaCeldaAnamn = {4f, 8f};
          tableAnamnesis1.setWidths(medidaCeldaAnamn);
          tableAnamnesis1.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableAnamnesis1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
          Paragraph tAnam1 = new Paragraph("Examen");
          tAnam1.getFont().setStyle(Font.BOLD);
          tAnam1.getFont().setSize(10);        
          tAnam1.setFont(Letrasmall);
          tAnam1.setAlignment(Element.ALIGN_CENTER);

          PdfPCell celda1 = new PdfPCell( tAnam1);
          celda1.setBorder(Rectangle.NO_BORDER);
          celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
          celda1 .enableBorderSide(Rectangle.TOP);
   //       celda1.enableBorderSide(Rectangle.BOTTOM);
          tableAnamnesis1.addCell(celda1);
           
          
          
          Paragraph tAnam2 = new Paragraph("Descripción");
          tAnam2.getFont().setStyle(Font.BOLD);
          tAnam2.getFont().setSize(10);        
          tAnam2.setFont(Letrasmall);
          tAnam2.setAlignment(Element.ALIGN_CENTER);

          PdfPCell celda2 = new PdfPCell( tAnam2);
          celda2.setBorder(Rectangle.NO_BORDER);
          celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
          celda2.enableBorderSide(Rectangle.TOP);
     //     celda2.enableBorderSide(Rectangle.BOTTOM);
          tableAnamnesis1.addCell(celda2);
            

          if(!AspectoTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Aspecto del paciente", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
          
          PdfPCell aspecto = new PdfPCell(new Paragraph(AspectoTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          aspecto.setPaddingTop(5); // Establece el margen superior 
          aspecto.setPaddingBottom(5); // Establece el margen inferior
          aspecto.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(aspecto);
          
          
          } 
          
          
          if(!GangliosTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Ganglios Linfáticos", FontFactory.getFont("Arial", 10, Font.NORMAL))); 

          PdfPCell ganglios = new PdfPCell(new Paragraph(GangliosTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          ganglios .setPaddingTop(5); // Establece el margen superior 
          ganglios .setPaddingBottom(5); // Establece el margen inferior
          ganglios .setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(ganglios);
          
          }
          
          
          
          if(!TxtTiroidea.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Palpitación Tiroidea", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
          
          PdfPCell tiroidea = new PdfPCell(new Paragraph(TxtTiroidea.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          tiroidea.setPaddingTop(5); // Establece el margen superior 
          tiroidea.setPaddingBottom(5); // Establece el margen inferior
          tiroidea.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(tiroidea);
          
          }
          
          if(!MuscularTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Palpitación Muscular", FontFactory.getFont("Arial", 10, Font.NORMAL)));
          
          PdfPCell muscular = new PdfPCell(new Paragraph(MuscularTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          muscular.setPaddingTop(5); // Establece el margen superior 
          muscular.setPaddingBottom(5); // Establece el margen inferior
          muscular.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(muscular);
          
          }
           
          if(!TemporomandibularTxt.getText().equals("")){ 
          tableAnamnesis1.addCell(new Paragraph("Articulación Temporomandibular", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
     
          
          PdfPCell mandibular= new PdfPCell(new Paragraph(TemporomandibularTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          mandibular.setPaddingTop(5); // Establece el margen superior 
          mandibular.setPaddingBottom(5); // Establece el margen inferior
          mandibular.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(mandibular);
          }
          
          if(!ComisuraTxt.getText().equals("")){ 
          tableAnamnesis1.addCell(new Paragraph("Labios y comisura labial", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
       
          
          PdfPCell comisura= new PdfPCell(new Paragraph(ComisuraTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          comisura.setPaddingTop(5); // Establece el margen superior 
          comisura.setPaddingBottom(5); // Establece el margen inferior
          comisura.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(comisura);
          
          }
          
          if(!CarillosTxt.getText().equals("")){ 
          tableAnamnesis1.addCell(new Paragraph("Carrillos", FontFactory.getFont("Arial", 10, Font.NORMAL))); 

          
          PdfPCell carillos= new PdfPCell(new Paragraph(CarillosTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          carillos.setPaddingTop(5); // Establece el margen superior 
          carillos.setPaddingBottom(5); // Establece el margen inferior
          carillos.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(carillos);
          
          }
          
          if(!PaladarTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Paladar duro y paladar blando", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
         
          
          PdfPCell paladar= new PdfPCell(new Paragraph(PaladarTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          paladar.setPaddingTop(5); // Establece el margen superior 
          paladar.setPaddingBottom(5); // Establece el margen inferior
          paladar.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(paladar);
          }
          
          if(!AmigdalinaTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Región amigdalina", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
         
          
          PdfPCell amigdala= new PdfPCell(new Paragraph(AmigdalinaTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          amigdala.setPaddingTop(5); // Establece el margen superior 
          amigdala.setPaddingBottom(5); // Establece el margen inferior
          amigdala.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(amigdala);
          
          }
          
          if(!LenguaTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Lengua y piso de boca", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
   
          
          PdfPCell lengua= new PdfPCell(new Paragraph(LenguaTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
           lengua.setPaddingTop(5); // Establece el margen superior 
           lengua.setPaddingBottom(5); // Establece el margen inferior
           lengua.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell( lengua);
           }
          
          if(!MaxilaresTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Maxilares(Tipo y forma)", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
          
          PdfPCell maxilares= new PdfPCell(new Paragraph(MaxilaresTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          maxilares.setPaddingTop(5); // Establece el margen superior 
          maxilares.setPaddingBottom(5); // Establece el margen inferior
          maxilares.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(maxilares);
          
           }
          
          if(!SalivaTxt.getText().equals("")){
          tableAnamnesis1.addCell(new Paragraph("Saliva. (Presencia, viscosidad, color)", FontFactory.getFont("Arial", 10, Font.NORMAL))); 
           
          PdfPCell saliva= new PdfPCell(new Paragraph(SalivaTxt.getText(), FontFactory.getFont("Arial", 8, Font.NORMAL)));
          saliva.setPaddingTop(5); // Establece el margen superior 
          saliva.setPaddingBottom(5); // Establece el margen inferior
          saliva.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
          tableAnamnesis1.addCell(saliva);
          
          
          }
          
            }catch(Exception  e){ System.out.println(e);}
             return tableAnamnesis1;
           
          }
                
        
        
        
        
        
     private PdfPTable crearTablaOdontograma() {
       PdfPTable tablaOdontograma=null;
             
             try{
             
             
             
tablaOdontograma = new PdfPTable(5); // Cambio de nombre aquí

tablaOdontograma.setHorizontalAlignment(Element.ALIGN_CENTER);
tablaOdontograma.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
tablaOdontograma.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

tablaOdontograma.setWidthPercentage(100);  
float[] medidaCeldas = {1f, 5f, 3f, 3f, 3f};
tablaOdontograma.setWidths(medidaCeldas);
tablaOdontograma.setHorizontalAlignment(Element.ALIGN_CENTER);
tablaOdontograma.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

Paragraph tcolumna1 = new Paragraph("Diente");
tcolumna1.getFont().setStyle(Font.BOLD);
tcolumna1.getFont().setSize(8);        
tcolumna1.setFont(Letra);
tcolumna1.setAlignment(Element.ALIGN_CENTER);
tablaOdontograma.addCell(tcolumna1);

Paragraph tcolumna2 = new Paragraph("Informe");
tcolumna2.getFont().setStyle(Font.BOLD);
tcolumna2.getFont().setSize(10);        
tcolumna2.setFont(Letra);
tcolumna2.setAlignment(Element.ALIGN_CENTER);
tablaOdontograma.addCell(tcolumna2);

Paragraph tcolumna3 = new Paragraph("Dolor");
tcolumna3.getFont().setStyle(Font.BOLD);
tcolumna3.getFont().setSize(10);        
tcolumna3.setFont(Letra);
tcolumna3.setAlignment(Element.ALIGN_CENTER);
tablaOdontograma.addCell(tcolumna3);

Paragraph tcolumna4 = new Paragraph("Signos");
tcolumna4.getFont().setStyle(Font.BOLD);
tcolumna4.getFont().setSize(10);        
tcolumna4.setFont(Letra);
tcolumna4.setAlignment(Element.ALIGN_CENTER);
tablaOdontograma.addCell(tcolumna4);

Paragraph tcolumna5 = new Paragraph("Radiologia");
tcolumna5.getFont().setStyle(Font.BOLD);
tcolumna5.getFont().setSize(10);        
tcolumna5.setFont(Letra);
tcolumna5.setAlignment(Element.ALIGN_CENTER);
tablaOdontograma.addCell(tcolumna5);

for (int i = 0; i < jTableOdontograma.getRowCount(); i++) {

    String diente = jTableOdontograma.getValueAt(i, 1).toString();
    String informe = jTableOdontograma.getValueAt(i, 2).toString()  ;
    String dolor = jTableOdontograma.getValueAt(i, 3).toString();
    String signo = jTableOdontograma.getValueAt(i, 4).toString(); 
    String radiologia = jTableOdontograma.getValueAt(i, 5).toString(); 

    tablaOdontograma.addCell(new Paragraph(diente, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    tablaOdontograma.addCell(new Paragraph(informe, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    tablaOdontograma.addCell(new Paragraph(dolor, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    tablaOdontograma.addCell(new Paragraph(signo, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
    tablaOdontograma.addCell(new Paragraph(radiologia, FontFactory.getFont("Arial", 8, Font.NORMAL))); 
}

             
             
             
             }catch(Exception  e){ System.out.println(e);}
             return tablaOdontograma;
           
          }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
          
public class HeaderFooterPageEvent extends PdfPageEventHelper {

    public void onStartPage(PdfWriter writer, Document document) {
      
        
        
        if (writer.getPageNumber() == 1) {
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
            cb.setTextMatrix(168, 725);
            cb.showText("__________________________________________________________________");

            cb.endText();

            
            
            
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
            
     
                
            Paragraph tcolumna2 = new Paragraph("HISTORIA CLINICA");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(12);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna2);

            Paragraph tcolumna3 = new Paragraph("N° DE PACIENTE: " +PacienteSelec+" ");
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
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA: "+cedula);
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(8);        
            t0columna2.setFont(Letra);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);

            Paragraph t0columna3 = new Paragraph("EDAD:" +Integer.toString(diferencian) + " años");
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
            
            
            

            
               float footerHeight = 580;
               float footerHeight0 = 563;
   
            
            
            Tabla.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() + footerHeight, writer.getDirectContent());
            Tabla0.writeSelectedRows(0, -1, document.leftMargin(), document.bottom() + footerHeight0, writer.getDirectContent());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
        
        
        
 
    }
}     
        
       
       
       
       
       

        PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
        public void onEndPage(PdfWriter writer, Document document) {
        
    

       try {
       com.itextpdf.text.Image FirmaDoctor = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\dist\\imagen.bin");
    // Continúa con el procesamiento de la imagen...

        
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
       
  
    } catch (Exception ex) {
    ex.printStackTrace();
}
        
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
    }  finally {
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
          
          
          
          
   
          
    } 
     
  
  
  boolean usingData=false;
  public void callHistoryinfo(){
  
      
    usingData = true; 
    
    mostrarFGeneral();
  //mostrarFisico();
    mostrarBucal();
    mostrarRevision();
    mostrarAmnesis();
    mostrarConclusion();
    
    limpiarShowAntc1();
    ShowAntc1(idtablaHistorias);
            
    limpiarShowAntc2();
    ShowAntc2(idtablaHistorias);
    
    limpiarEAdc();     
    ShowExamAdic(idtablaHistorias);
    
    limpiarOdontograma();    
    showOdontograma(idAsignar);
    
    
    BtnImprimirHistoria.setEnabled(true);
    BtnGuardarHistoria.setEnabled(true);
    
    JOptionPane.showMessageDialog(null, "LOS DATOS FUERON CARGADOS A HISTORIA CLINICA", "HISTORIA CLINICA", 1);
    
    tabbedAntecedentes.setSelectedIndex(0);
    Odontologia.setSelectedIndex(0);
    checkAmnemesis();
   
    
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
      
       
  
     
     
     
     
  /*
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
         
         */
         
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
           
           
           
           
     int vboolean;
     public void mostrarAmnesis(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
          
          
      // MOSTRAR EXAMEN FISICO NORMAL    
       try{
    
     //    int fila = JTablePacientes.getSelectedRow();
       
       String query="SELECT * FROM `consulta_anmenesis` WHERE id_historia=?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       Txt_Alteraciones.setText(rs.getString("alteracionesCardio"));
       Txt_Hemorragia.setText(rs.getString("hemorragia"));
       Txt_Anemia.setText(rs.getString("anemia"));
       Txt_renales.setText(rs.getString("enfermedadRenal"));
       TXT_Hepaticas.setText(rs.getString("enfermedadHepatica"));
       TXT_asma.setText(rs.getString("asma"));
       TXT_Respiratorias.setText(rs.getString("enfermedadRespiratoria"));
       TXT_diabetes.setText(rs.getString("diabetesMelitus"));
       TXT_Hipertension.setText(rs.getString("Hipertension"));
       TXT_Jaqueca.setText(rs.getString("doloresCabeza"));
       TXT_reumatica.setText(rs.getString("fiebreReumatica"));
       TXT_infecciosa.setText(rs.getString("enfermedadInfecciosa"));
       TXT_SEXUAL.setText(rs.getString("enfermedadSexual"));
       TXT_Gastrointestinal.setText(rs.getString("enfermedadGastro"));
       TXT_Zumbidos.setText(rs.getString("doloresOido"));
       TXT_inflamacion.setText(rs.getString("inflamanPies"));
       TXT_sinusitis.setText(rs.getString("sinusitis"));
       TXT_respiradorbucal.setText(rs.getString("respiradorBucal"));
       TXT_Congenita.setText(rs.getString("enfermdadCongenita"));
       TXT_Hormonal.setText(rs.getString("desarregloHormonal"));
       TXT_GraveEnfermo.setText(rs.getString("gravementeHerido"));
       TXT_Intervenido.setText(rs.getString("intervenidoQuirurgico"));
       TXT_Agua.setText(rs.getString("vasosAgua"));
       TXT_Aspirina.setText(rs.getString("aspirina"));
       TXT_Orina.setText(rs.getString("orinaFrecuente"));
       TXT_Tratamiento.setText(rs.getString("bajoTratamiento"));
       TXT_Cansansio.setText(rs.getString("cansansio"));
       TXT_Alimento.setText(rs.getString("alimentoProhibido"));
       TXT_Alergico.setText(rs.getString("alergico"));
       TXT_Embarazo.setText(rs.getString("embarazo"));
       TXT_TerapiaHormonal.setText(rs.getString("terapiaHormonal"));
       TXT_ReaccionAnestecia.setText(rs.getString("reaccionAnestecia"));
       TXT_halitosis.setText(rs.getString("halitosis"));
       TXT_Apariencia.setText(rs.getString("apariencia"));
       TXT_Encias.setText(rs.getString("sangradoEncias"));
       TXT_MovilidadDientes.setText(rs.getString("movDientes"));
       TXT_Sensibilidad.setText(rs.getString("sensibilidadDientes"));
       TXT_Alimentos.setText(rs.getString("consumoAlimentos"));
       TXTResequedad.setText(rs.getString("resequedadBoca"));
       TXT_Ulceraciones.setText(rs.getString("Ulceraciones"));
       TXT_Mandibula.setText(rs.getString("molestiaMandibula"));
       vboolean= rs.getInt("ningunaAnteriorres");
       jNingunaAnteriores.setSelected(vboolean == 1);
       TXT_Observaciones.setText(rs.getString("observaciones"));
       
       
       
       
       

   
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
      
       
  
     
     
     
     
     
     
    public void mostrarBucal(){
 
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
          
          
      
       try{
    

       String query="SELECT * FROM `consulta_bucal` WHERE id_historia=?";
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setInt(1, idtablaHistorias);
       //System.out.println(idtablaHistorias);
       rs=ps.executeQuery();
   
       if (rs.next()){
       AspectoTxt.setText(rs.getString("aspectoPaciente"));
       GangliosTxt.setText(rs.getString("gangliosLinfaticos"));
       TxtTiroidea.setText(rs.getString("palpitacionTiroidea"));
       MuscularTxt.setText(rs.getString("palpitacionMuscular"));
       TemporomandibularTxt.setText(rs.getString("articulacionTempo"));
       ComisuraTxt.setText(rs.getString("labiosComisura"));
       CarillosTxt.setText(rs.getString("carrillos"));
       PaladarTxt.setText(rs.getString("paladarDuro"));
       AmigdalinaTxt.setText(rs.getString("regionAmigdalina"));
       LenguaTxt.setText(rs.getString("lenguaPiso"));
       MaxilaresTxt.setText(rs.getString("maxilares"));
       SalivaTxt.setText(rs.getString("saliva"));
      
       knowOdontogram();
       Icon icono = new ImageIcon("C:\\Fundaginebra\\src\\imagenes\\odontograma.png");
       jLabel4.setIcon(icono);
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
      acomodarEadd();
            

    }
    
       
       
              public  void limpiarOdontograma() {
        DefaultTableModel tb = (DefaultTableModel)  jTableOdontograma.getModel();
        int a =  jTableOdontograma.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
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
    
    
    
    
public void actualizarHistoriaBd() {
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
        
        
        // Actualización de anmesis
        if (existeRegistro(con, "consulta_anmenesis", "id_historia", idAsignar)) {
         
            updateAnmenesis();
        } else {
            
          insertarAnmesis();
          updateAnmenesis();
        }


       // Actualización de examen bucal
        if (existeRegistro(con, "consulta_bucal", "id_historia", idAsignar)) {
         
        updateBucal();
        } else {
            
        insertarBucal();
        updateBucal();
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
         
           } finally {
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
         
           } finally {
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
           } finally {
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
           } finally {
            closeResources(rs, ps, con);
        }

 }


  
  
  

  public void insertarAnmesis(){
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;     
          try {
            String sql2 = "INSERT INTO `consulta_anmenesis`(`id_historia`) VALUES (?)";
          
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);

 
            ps.executeUpdate();
      
            
        } catch (Exception e) {
             System.out.println("ERROR EN REGISTRAR REVISION" + e);
         
           } finally {
            closeResources(rs, ps, con);
        }

  }
  
  
  

  
public void updateAnmenesis() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;



    try {
        
        String sql = "UPDATE `consulta_anmenesis` \n" +
"SET \n" +
"    `alteracionesCardio`=?,\n" +
"    `hemorragia`=?,\n" +
"    `anemia`=?,\n" +
"    `enfermedadRenal`=?,\n" +
"    `enfermedadHepatica`=?,\n" +
"    `asma`=?,\n" +
"    `enfermedadRespiratoria`=?,\n" +
"    `diabetesMelitus`=?,\n" +
"    `Hipertension`=?,\n" +
"    `doloresCabeza`=?,\n" +
"    `fiebreReumatica`=?,\n" +
"    `enfermedadInfecciosa`=?,\n" +
"    `enfermedadSexual`=?,\n" +
"    `enfermedadGastro`=?,\n" +
"    `doloresOido`=?,\n" +
"    `inflamanPies`=?,\n" +
"    `sinusitis`=?,\n" +
"    `respiradorBucal`=?,\n" +
"    `enfermdadCongenita`=?,\n" +
"    `desarregloHormonal`=?,\n" +
"    `gravementeHerido`=?,\n" +
"    `intervenidoQuirurgico`=?,\n" +
"    `vasosAgua`=?,\n" +
"    `aspirina`=?,\n" +
"    `orinaFrecuente`=?,\n" +
"    `bajoTratamiento`=?,\n" +
"    `cansansio`=?,\n" +
"    `alimentoProhibido`=?,\n" +
"    `alergico`=?,\n" +
"    `embarazo`=?,\n" +
"    `terapiaHormonal`=?,\n" +
"    `reaccionAnestecia`=?,\n" +
"    `halitosis`=?,\n" +
"    `apariencia`=?,\n" +
"    `sangradoEncias`=?,\n" +
"    `movDientes`=?,\n" +
"    `sensibilidadDientes`=?,\n" +
"    `consumoAlimentos`=?,\n" +
"    `Ulceraciones`=?,\n" +
"    `resequedadBoca`=?,\n" +
"    `molestiaMandibula`=?,\n" +
"    `ningunaAnteriorres`=?,\n" +
"    `observaciones`=?\n" +
"WHERE \n" +
"    `id_historia`=?";
        
        
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

   
        ps.setString(1, Txt_Alteraciones.getText());
        ps.setString(2, Txt_Hemorragia.getText());
        ps.setString(3, Txt_Anemia.getText());
        ps.setString(4, Txt_renales.getText());
        ps.setString(5, TXT_Hepaticas.getText());
        ps.setString(6, TXT_asma.getText());
        ps.setString(7, TXT_Respiratorias.getText());
        ps.setString(8, TXT_diabetes.getText());
        ps.setString(9, TXT_Hipertension.getText());
        ps.setString(10, TXT_Jaqueca.getText());
        ps.setString(11, TXT_reumatica.getText()); 
        ps.setString(12, TXT_infecciosa.getText());
        ps.setString(13, TXT_SEXUAL.getText());
        ps.setString(14, TXT_Gastrointestinal.getText());
        ps.setString(15, TXT_Zumbidos.getText());
        ps.setString(16, TXT_inflamacion.getText());
        ps.setString(17, TXT_sinusitis.getText());
        ps.setString(18, TXT_respiradorbucal.getText());
        ps.setString(19, TXT_Congenita.getText());
        ps.setString(20, TXT_Hormonal.getText());
        ps.setString(21, TXT_GraveEnfermo.getText());
        ps.setString(22, TXT_Intervenido.getText());
        ps.setString(23, TXT_Agua.getText());
        ps.setString(24, TXT_Aspirina.getText());
        ps.setString(25, TXT_Orina.getText());
        ps.setString(26, TXT_Tratamiento.getText());
        ps.setString(27, TXT_Cansansio.getText());
        ps.setString(28, TXT_Alimento.getText());
        ps.setString(29, TXT_Alergico.getText());
        ps.setString(30, TXT_Embarazo.getText());
        ps.setString(31, TXT_TerapiaHormonal.getText());
        ps.setString(32, TXT_ReaccionAnestecia.getText());
        ps.setString(33, TXT_halitosis.getText());
        ps.setString(34, TXT_Apariencia.getText());
        ps.setString(35, TXT_Encias.getText());
        ps.setString(36, TXT_MovilidadDientes.getText());
        ps.setString(37, TXT_Sensibilidad.getText());
        ps.setString(38, TXT_Alimentos.getText());
        ps.setString(40, TXT_Ulceraciones.getText());
        ps.setString(39, TXTResequedad.getText());
        ps.setString(41, TXT_Mandibula.getText()); 
        ps.setInt(42, jSatisfecho.isSelected() ? 1 : 0);
        ps.setString(43, TXT_Observaciones.getText());
        ps.setInt(44, idAsignar);

        ps.executeUpdate();

      
    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR AMNEMESIS: " + e);
    } finally {
            closeResources(null, ps, con);
        }
}
  
  
  
  


  public void insertarBucal(){
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;     
          try {
            String sql2 = "INSERT INTO `consulta_bucal`(`id_historia`) VALUES (?)";
          
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, idAsignar);

 
            ps.executeUpdate();
        
       
            
        } catch (Exception e) {
             System.out.println("ERROR EN REGISTRAR CONSULTA BUCAL" + e);
         
           } finally {
            closeResources(rs, ps, con);
        }

  }
  
  


       public void updateBucal(){


    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;



    try {
        
String sql = "UPDATE `consulta_bucal` SET " +
             "`aspectoPaciente`=?, " +
             "`gangliosLinfaticos`=?, " +
             "`palpitacionTiroidea`=?, " +
             "`palpitacionMuscular`=?, " +
             "`articulacionTempo`=?, " +
             "`labiosComisura`=?, " +
             "`carrillos`=?, " +
             "`paladarDuro`=?, " +
             "`regionAmigdalina`=?, " +
             "`lenguaPiso`=?, " +
             "`maxilares`=?, " +
             "`saliva`=? " +
             "WHERE `id_historia`=?";
        
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
   //     FileInputStream odontograma = new FileInputStream("C:\\Fundaginebra\\src\\imagenes\\odontograma.png");
        ps.setString(1, AspectoTxt.getText());
        ps.setString(2, GangliosTxt.getText());
        ps.setString(3, TxtTiroidea.getText());
        ps.setString(4, MuscularTxt.getText());
        ps.setString(5, TemporomandibularTxt.getText());
        ps.setString(6, ComisuraTxt.getText());
        ps.setString(7, CarillosTxt.getText());
        ps.setString(8, PaladarTxt.getText());
        ps.setString(9, AmigdalinaTxt.getText());
        ps.setString(10, LenguaTxt.getText());
        ps.setString(11, MaxilaresTxt.getText()); 
        ps.setString(12, SalivaTxt.getText());
        ps.setInt(13, idAsignar);


        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR EXAMEN BUCAL: " + e);
    } finally {
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
         
           } finally {
            closeResources(rs, ps, con);
        }

  }
  
  
  
public void updateRevision() {
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;


    int Menarquia = (int) jSpinnerMenarquia.getValue();
    int Partos = (int) jSpartos.getValue();
    int Cesareas = (int) jScesareas.getValue();
    int Abortos = (int) jSabortos.getValue();
    int Embarazo = (int) jSembarazo.getValue();
    int Menospausia = (int) jSmenospausia.getValue();

    try {
        String sql = "UPDATE `table_revision` SET  `Menarquia`=?, `Mestruacion`=?, `Ciclos_mestruacion`=?, `Ultima_mestruacion`=?, `Partos`=?, `Cesareas`=?, `Abortos`=?, `Embarazos`=?, `Menospausia`=?, `Motivo_Consulta`=?,`Tiempo_libre`=?, `Consumo_alcohol`=?, `Consumo_tabaco`=?, `Consumo_estupefacientes`=?, `Actividad_fisica`=?, `Frecuencia_semanal`=?, `Sueno`=?, `Sexualidad`=? WHERE `Id_historiaR`=?";
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
        ps.setString(10, jTextMotivoConsulta.getText());
        ps.setString(11, jComboHabitos.getSelectedItem().toString());
        ps.setString(12, jTConsumoAlcohol.getText());
        ps.setString(13, jTConsumoTabaco.getText());
        ps.setString(14, jTConsumoDrugs.getText());
        ps.setString(15, jComboActividad.getSelectedItem().toString());
        ps.setString(16, jComboFrec.getSelectedItem().toString());
        ps.setString(17, jComboSueno.getSelectedItem().toString());
        ps.setString(18, jComboSexualidad.getSelectedItem().toString());
        ps.setInt(19, idAsignar);

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("ERROR EN ACTUALIZAR REVISION: " + e);
    } finally {
            closeResources(null, ps, con);
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
         
           } finally {
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
    } finally {
            closeResources(null, ps, con);
        }
}
  



//---------- ODONTOLOGIA ---- //




private void setupDirectoryWatcher(String imagePath, JLabel label) {
    try {
        Path directory = Paths.get(imagePath).getParent();
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // Registrar el directorio para eventos de modificación
        directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        // Hilo para manejar eventos de modificación
        new Thread(() -> {
            while (true) {
                try {
                    // Esperar eventos
                    WatchKey key = watchService.take();

                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            Path fileName = (Path) event.context();
                            if (fileName.toString().equals("odontograma.png")) {
                                System.out.println("Se detectó un cambio en odontograma.png");
                                // Volver a cargar la imagen en la interfaz de usuario
                           
                                    loadImage(imagePath, label);
                            
                            }
                        }
                    }

                    // Reiniciar el key
                    boolean valid = key.reset();
                    if (!valid) {
                        break; // Salir del bucle si el key ya no es válido
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Cargar la imagen inicial
      //  loadImage(imagePath, label);

    } catch (IOException e) {
        e.printStackTrace();
    }
}















private void loadImage(String imagePath, JLabel label) {
    try {
        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);
        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    
    
public void searchOdontogram() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    byte[] b = null;
    InputStream in = null;
    FileOutputStream out = null;

    try {
        ps = cn.getConnection().prepareStatement("SELECT imgOdontograma FROM table_imagenes WHERE id_imagenes =?;");
        ps.setInt(1, 1);
        rs = ps.executeQuery();

        if (rs.next()) {
            b = rs.getBytes(1);
        }

        // Convertir bytes a imagen
        in = new ByteArrayInputStream(b);
        javax.imageio.ImageIO.setUseCache(false); // Desactivar la caché para evitar problemas al cargar la imagen
        java.awt.image.BufferedImage image = ImageIO.read(in);

        // Guardar la imagen en formato PNG
        out = new FileOutputStream("C:\\Fundaginebra\\src\\imagenes\\odontograma.png");
        ImageIO.write(image, "PNG", out);

        // Cerrar recursos
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
     
     

    } catch (IOException | NumberFormatException | SQLException ex) {
        System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        JOptionPane.showMessageDialog(null, ex);
    } finally {
            closeResources(rs, ps, con);
        }
}
    
    
    
    
    
    
    
        
    public void knowOdontogram() {
    
        
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = cn.getConnection();

     
        if (existeRegistro(con, "consulta_bucal", "id_historia", idAsignar)) {
            actualizarOdontograma();
       //     searchOdontogramPatient();
        } else {
            insertarOdontograma();
            actualizarOdontograma();
       //     searchOdontogramPatient();
        }

      

    } catch (Exception e) {
        System.out.println("Error al actualizar la historia: " + e);
    } finally {
            closeResources(rs, ps, con);
        }  
   
}
    
    
    
    
    
    
    
  public void actualizarOdontograma(){
  
  
     try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement("UPDATE consulta_bucal SET Odontograma=? WHERE id_historia=?");
         FileInputStream odontograma = new FileInputStream(new File("C:\\Fundaginebra\\src\\imagenes\\odontograma.png"))) {

        ps.setBlob(1, odontograma);
        ps.setInt(2, idAsignar);
        ps.executeUpdate();

    } catch (SQLException | IOException e) {
        System.out.println(e.getMessage());
    }


  
  }
    
        
  public void insertarOdontograma(){
  
  
     try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement("INSERT INTO consulta_bucal (id_historia) Values (?)");
          ) {

        ps.setInt(1, idAsignar);
        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }


  
  }
    

 public void searchOdontogramPatient() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    byte[] b = null;

    try {
        ps = cn.getConnection().prepareStatement("SELECT Odontograma FROM consulta_bucal WHERE id_historia =?;");
        ps.setInt(1, idtablaHistorias);
        rs = ps.executeQuery();

        while (rs.next()) {
            b = rs.getBytes(1);
            
        }

        // Convertir bytes a imagen
        InputStream in = new ByteArrayInputStream(b);
        javax.imageio.ImageIO.setUseCache(false); // Desactivar la caché para evitar problemas al cargar la imagen
        java.awt.image.BufferedImage image = ImageIO.read(in);

        // Guardar la imagen en formato PNG
        ImageIO.write(image, "PNG", new FileOutputStream("C:\\Fundaginebra\\src\\imagenes\\odontograma.png"));


        
        // Cerrar recursos
        in.close();
 

    } catch (IOException | NumberFormatException | SQLException ex) {
        System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        JOptionPane.showMessageDialog(null, ex);
    } finally {
            closeResources(rs, ps, con);
        }
}


    
    
    
  public static void eliminarArchivo(String rutaArchivo) {
  Path archivoPath = Paths.get(rutaArchivo);

try {
    Files.delete(archivoPath);
    System.out.println("Archivo eliminado con éxito.");
} catch (IOException e) {
    System.out.println("No se pudo eliminar el archivo.");
    e.printStackTrace();
}
    }

    
  

  
  

      public void agregarOdontograma(){
        int validarOdontograma;
     if (!usingData) {
        validarOdontograma = idAsignar;
    } else {
      validarOdontograma = idtablaHistorias;
    }
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
        try {
      
      String sql = "INSERT INTO `consulta_odontologia`( `id_oHistoria`, `Diente`, `Informe`, `Dolor`, `Signos`, `Radiologia`) VALUES (?,?,?,?,?,?)";

      
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, validarOdontograma);
            ps.setString(2, jComboDiente.getSelectedItem().toString());
            ps.setString(3, JtxtInforme.getText());
            ps.setString(4, caracteristicasDolor.isEmpty() ? "N/A" : caracteristicasDolor);
            ps.setString(5, signosr.isEmpty() ? "N/A" : signosr);
            ps.setString(6, jTextRadiologica.getText());
        //    ps.setBlob(7, odontograma);
            
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
    try {
        if (odontograma != null) {
            odontograma.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (IOException | SQLException e) {
        System.out.println("Error al cerrar recursos: " + e);
    }
}  
        

  }
  
      
    
  
       public void showOdontograma(int idOdontograma) {

       

        List<JCAntecedentesFamiliares> lista = AntcDao.showOdontograma(idOdontograma);
        modelo = (DefaultTableModel) jTableOdontograma.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdOdontologia();
            ob[1] = lista.get(i).getDientes();
            ob[2] = lista.get(i).getInformeDiente();
            ob[3] = lista.get(i).getDolorDientes();
            ob[4] = lista.get(i).getSignosDientes();
            ob[5] = lista.get(i).getRadiologiaDientes();
           // ob[6] = lista.get(i).getOdontograma();

            
            
            modelo.addRow(ob);

        }
       jTableOdontograma.setModel(modelo);
       acomodarOdontograma();
            

    }
    
  
  
  
  
  
  
  
  public void modificarOdontograma() {
    Connection con = null;
    PreparedStatement ps = null;

    try {
        EnlaceBd cn = new EnlaceBd();
        con = cn.getConnection();

        odontograma = new FileInputStream(new File("C:\\Fundaginebra\\src\\imagenes\\odontograma.png"));
        String sql = "UPDATE `consulta_odontologia` SET " +
                     "`Diente` = ?," +
                     "`Informe` = ?," +
                     "`Dolor` = ?," +
                     "`Signos` = ?," +
                     "`Radiologia` = ?," +
                 //    "`Odontograma` = ? " +
                     "WHERE " +
                     "`id_odontologia` = ?";

        ps = con.prepareStatement(sql);
        ps.setString(1, jComboDiente.getSelectedItem().toString());
        ps.setString(2, JtxtInforme.getText());
        ps.setString(3, caracteristicasDolor.isEmpty() ? "N/A" : caracteristicasDolor);
        ps.setString(4, signosr.isEmpty() ? "N/A" : signosr);
        ps.setString(5, jTextRadiologica.getText());
   //     ps.setBlob(6, odontograma);
        ps.setInt(6, idOdontograma1);

        int res = ps.executeUpdate();

        if (res >= 1) {
            JOptionPane.showMessageDialog(null, "PROCESO REALIZADO", "ACTUALIZACIÓN DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException | FileNotFoundException e) {
        System.out.println(e);
        // Puedes manejar el error de otra manera si es necesario
    } finally {
    try {
        if (odontograma != null) {
            odontograma.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (IOException | SQLException e) {
        System.out.println("Error al cerrar recursos: " + e);
    }
}
}

  
     
     
     
    
  public void eliminarOdontograma(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "DELETE FROM `consulta_odontologia` WHERE id_odontologia=? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idOdontograma1);
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
     
     
     
     
  
public void checkAmnemesis() {
  

if(!Txt_Alteraciones.getText().equals("")){
Txt_Alteraciones.setEnabled(true);
JCardiovascular.setSelected(true);
}

if(!Txt_Hemorragia.getText().equals("")){
Txt_Hemorragia.setEnabled(true);
JHemorragias.setSelected(true);
}

if(!Txt_Anemia.getText().equals("")){
Txt_Anemia.setEnabled(true);
jAnemia.setSelected(true);
}

if(!Txt_renales.getText().equals("")){
Txt_renales.setEnabled(true);
jRenales.setSelected(true);
}

if(!TXT_Hepaticas.getText().equals("")){
TXT_Hepaticas.setEnabled(true);
jHepaticas.setSelected(true);
}


if(!TXT_asma.getText().equals("")){
TXT_asma.setEnabled(true);
jAsma.setSelected(true);
}

if(!TXT_Respiratorias.getText().equals("")){
TXT_Respiratorias.setEnabled(true);
jRespiratorias.setSelected(true);
}


if(!TXT_diabetes.getText().equals("")){
TXT_diabetes.setEnabled(true);
jDiabetes.setSelected(true);
}

if(!TXT_Hipertension.getText().equals("")){
TXT_Hipertension.setEnabled(true);
jHipertension.setSelected(true);
}

if(!TXT_Jaqueca.getText().equals("")){
TXT_Jaqueca.setEnabled(true);
jMigrañas.setSelected(true);
}

if(!TXT_reumatica.getText().equals("")){
TXT_reumatica.setEnabled(true);
jFiebre.setSelected(true);
}

if(!TXT_infecciosa.getText().equals("")){
TXT_infecciosa.setEnabled(true);
jInfecciosas.setSelected(true);
}

if(!TXT_SEXUAL.getText().equals("")){
TXT_SEXUAL.setEnabled(true);
jTransmision.setSelected(true);
}

if(!TXT_Gastrointestinal.getText().equals("")){
TXT_Gastrointestinal.setEnabled(true);
jGastro.setSelected(true);
}

if(!TXT_Zumbidos.getText().equals("")){
TXT_Zumbidos.setEnabled(true);
jZumbidos.setSelected(true);
}

if(!TXT_inflamacion.getText().equals("")){
TXT_inflamacion.setEnabled(true);
jArticulaciones.setSelected(true);
}

if(!TXT_sinusitis.getText().equals("")){
TXT_sinusitis.setEnabled(true);
jSinusitis.setSelected(true);
}

if(!TXT_respiradorbucal.getText().equals("")){
TXT_respiradorbucal.setEnabled(true);
jRespiradorBucal.setSelected(true);
}


if(!TXT_Congenita.getText().equals("")){
TXT_Congenita.setEnabled(true);
jCongenita.setSelected(true);
}

if(!TXT_Hormonal.getText().equals("")){
TXT_Hormonal.setEnabled(true);
jHormonal.setSelected(true);
}

if(!TXT_GraveEnfermo.getText().equals("")){
TXT_GraveEnfermo.setEnabled(true);
jGrave.setSelected(true);
}

if(!TXT_sinusitis.getText().equals("")){
TXT_sinusitis.setEnabled(true);
jSinusitis.setSelected(true);
}

if(!TXT_respiradorbucal.getText().equals("")){
TXT_respiradorbucal.setEnabled(true);
jRespiradorBucal.setSelected(true);
}

if(!TXT_Congenita.getText().equals("")){
TXT_Congenita.setEnabled(true);
jCongenita.setSelected(true);
}

if(!TXT_Hormonal.getText().equals("")){
TXT_Hormonal.setEnabled(true);
jHormonal.setSelected(true);
}

if(!TXT_GraveEnfermo.getText().equals("")){
TXT_GraveEnfermo.setEnabled(true);
jGrave.setSelected(true);
}

if(!TXT_Intervenido.getText().equals("")){
TXT_Intervenido.setEnabled(true);
jQuirurgica.setSelected(true);
}

if(!TXT_Agua.getText().equals("")){
TXT_Agua.setEnabled(true);
jVasosAgua.setSelected(true);
}


if(!TXT_Aspirina.getText().equals("")){
TXT_Aspirina.setEnabled(true);
jAspirina.setSelected(true);
}

if(!TXT_Orina.getText().equals("")){
TXT_Orina.setEnabled(true);
jOrina.setSelected(true);
}

if(!TXT_Tratamiento.getText().equals("")){
TXT_Tratamiento.setEnabled(true);
jMedicamento.setSelected(true);
}

if(!TXT_Cansansio.getText().equals("")){
TXT_Cansansio.setEnabled(true);
jCansansio.setSelected(true);
}

if(!TXT_Alimento.getText().equals("")){
TXT_Alimento.setEnabled(true);
jAlimento.setSelected(true);
}

if(!TXT_Alergico.getText().equals("")){
TXT_Alergico.setEnabled(true);
jAlergia.setSelected(true);
}

if(!TXT_Embarazo.getText().equals("")){
TXT_Embarazo.setEnabled(true);
jEmbarazo.setSelected(true);
}

if(!TXT_TerapiaHormonal.getText().equals("")){
TXT_TerapiaHormonal.setEnabled(true);
jTerapiaHormonal.setSelected(true);
}

if(!TXT_ReaccionAnestecia.getText().equals("")){
TXT_ReaccionAnestecia.setEnabled(true);
jReaccionAnestecia.setSelected(true);
}

if(!TXT_halitosis.getText().equals("")){
TXT_halitosis.setEnabled(true);
jHalitosis.setSelected(true);
}

if(!TXT_Apariencia.getText().equals("")){
TXT_Apariencia.setEnabled(true);
jSatisfecho.setSelected(true);
}

if(!TXT_Encias.getText().equals("")){
TXT_Encias.setEnabled(true);
jSangranEncias.setSelected(true);
}

if(!TXT_MovilidadDientes.getText().equals("")){
TXT_MovilidadDientes.setEnabled(true);
jDientesM.setSelected(true);
}

if(!TXT_Sensibilidad.getText().equals("")){
TXT_Sensibilidad.setEnabled(true);
jSensibilidad.setSelected(true);
}



if(!TXT_Alimentos.getText().equals("")){
TXT_Alimentos.setEnabled(true);
jConsumoAcido.setSelected(true);
}

if(!TXTResequedad.getText().equals("")){
TXTResequedad.setEnabled(true);
jresequedad.setSelected(true);
}

if(!TXT_Ulceraciones.getText().equals("")){
TXT_Ulceraciones.setEnabled(true);
jUlceraciones.setSelected(true);
}

if(!TXT_Mandibula.getText().equals("")){
TXT_Mandibula.setEnabled(true);
jMandibula.setSelected(true);
}


if(vboolean==1){

jNingunaAnteriores.setSelected(true);
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
   
   
   public void acomodarOdontograma()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTableOdontograma.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTableOdontograma.setRowHeight(20);
        
  
        jTableOdontograma.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableOdontograma.getColumnModel().getColumn(0).setMinWidth(0);
        jTableOdontograma.getColumnModel().getColumn(0).setPreferredWidth(0);
       
     
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Diente")).setPreferredWidth(10);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Informe")).setPreferredWidth(120);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Dolor")).setPreferredWidth(70);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Signos")).setPreferredWidth(70);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Radiologia")).setPreferredWidth(100);
      
    
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Diente")).setCellRenderer(Alinear);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Informe")).setCellRenderer(Alinear);;
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Dolor")).setCellRenderer(Alinear);
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Signos")).setCellRenderer(Alinear);;
        jTableOdontograma.getColumnModel().getColumn(Tabla.findColumn("Radiologia")).setCellRenderer(Alinear);
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
     
      

      
      
      
      
      
     
 
     public void cleanHistorias(){
     
     jTextMotivoConsulta.setText("");
     limpiarTAntecedentesF3();
     limpiarTAntecedentesF2();
     cleanAnamnesis();
     JtextAntc1.setText("");
     jTextAntPersonales.setText("");
  

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
     AspectoTxt.setText("");
     GangliosTxt.setText("");
     TxtTiroidea.setText("");
     MuscularTxt.setText("");
     TemporomandibularTxt.setText("");
     ComisuraTxt.setText("");
     CarillosTxt.setText("");
     PaladarTxt.setText("");
     AmigdalinaTxt.setText("");
     LenguaTxt.setText("");
     MaxilaresTxt.setText("");
     SalivaTxt.setText("");
     
     
     
     
  
     jTextExamenAdic.setText("");
     jTextObservAdic.setText("");
     jTextConclusion.setText("");
     jTextRecomendacion.setText("");
     jComboBox2.setSelectedItem("Buenas condiciones de salud");
     jComboMestruacion.setSelectedItem("Eumenorreica");
     jComboHabitos.setSelectedItem("Practica deportes");
     jComboActividad.setSelectedItem("Sedentario");
     jComboFrec.setSelectedItem("Nunca");
     jComboSueno.setSelectedItem("Reparador");
     jComboSexualidad.setSelectedItem("Satisfactoria");
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
     
     
     public void cleanAnamnesis(){
     
     

Txt_Alteraciones.setText("");
JCardiovascular.setSelected(false);


Txt_Hemorragia.setText("");
JHemorragias.setSelected(false);


Txt_Anemia.setText("");
jAnemia.setSelected(false);


Txt_renales.setText("");
jRenales.setSelected(false);


TXT_Hepaticas.setText("");
jHepaticas.setSelected(false);


TXT_asma.setText("");
jAsma.setSelected(false);



TXT_Respiratorias.setText("");
jRespiratorias.setSelected(false);




TXT_diabetes.setText("");
jDiabetes.setSelected(false);



TXT_Hipertension.setText("");
jHipertension.setSelected(false);



TXT_Jaqueca.setText("");
jMigrañas.setSelected(false);



TXT_reumatica.setText("");
jFiebre.setSelected(false);



TXT_infecciosa.setText("");
jInfecciosas.setSelected(false);



TXT_SEXUAL.setText("");
jTransmision.setSelected(false);



TXT_Gastrointestinal.setText("");
jGastro.setSelected(false);



TXT_Zumbidos.setText("");
jZumbidos.setSelected(false);



TXT_inflamacion.setText("");
jArticulaciones.setSelected(false);



TXT_sinusitis.setText("");
jSinusitis.setSelected(false);



TXT_respiradorbucal.setText("");
jRespiradorBucal.setSelected(false);




TXT_Congenita.setText("");
jCongenita.setSelected(false);



TXT_Hormonal.setText("");
jHormonal.setSelected(false);



TXT_GraveEnfermo.setText("");
jGrave.setSelected(false);



TXT_sinusitis.setText("");
jSinusitis.setSelected(false);



TXT_respiradorbucal.setText("");
jRespiradorBucal.setSelected(false);



TXT_Congenita.setText("");
jCongenita.setSelected(false);



TXT_Hormonal.setText("");
jHormonal.setSelected(false);



TXT_GraveEnfermo.setText("");
jGrave.setSelected(false);



TXT_Intervenido.setText("");
jQuirurgica.setSelected(false);



TXT_Agua.setText("");
jVasosAgua.setSelected(false);


TXT_Aspirina.setText("");;
jAspirina.setSelected(false);


TXT_Orina.setText("");;
jOrina.setSelected(false);


TXT_Tratamiento.setText("");
jMedicamento.setSelected(false);


TXT_Cansansio.setText("");
jCansansio.setSelected(false);


TXT_Alimento.setText("");
jAlimento.setSelected(false);


TXT_Alergico.setText("");
jAlergia.setSelected(false);


TXT_Embarazo.setText("");
jEmbarazo.setSelected(false);


TXT_TerapiaHormonal.setText("");
jTerapiaHormonal.setSelected(false);


TXT_ReaccionAnestecia.setText("");
jReaccionAnestecia.setSelected(false);


TXT_halitosis.setText("");
jHalitosis.setSelected(false);


TXT_Apariencia.setText("");
jSatisfecho.setSelected(false);


TXT_Encias.setText("");
jSangranEncias.setSelected(false);


TXT_MovilidadDientes.setText("");
jDientesM.setSelected(false);


TXT_Sensibilidad.setText("");
jSensibilidad.setSelected(false);





TXT_Alimentos.setText("");
jConsumoAcido.setSelected(true);



TXTResequedad.setText("");
jresequedad.setSelected(false);



TXT_Ulceraciones.setText("");
jUlceraciones.setSelected(false);



TXT_Mandibula.setText("");
jMandibula.setSelected(false);


     
     
     
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
   FileInputStream odontograma;
   JCAntecedentesFamiliaresDao AntcDao = new JCAntecedentesFamiliaresDao ();
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AmigdalinaTxt;
    private javax.swing.JPanel Anamnesis;
    private javax.swing.JPanel Antecedentes;
    private javax.swing.JTextArea AspectoTxt;
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
    private javax.swing.JTextArea CarillosTxt;
    private javax.swing.JTextArea ComisuraTxt;
    private javax.swing.JMenuItem Completar;
    private com.toedter.calendar.JDateChooser FechaAsignar1;
    private javax.swing.JFormattedTextField FechaMestruacion;
    private javax.swing.JTextArea GangliosTxt;
    private javax.swing.JPanel Habitos;
    private javax.swing.JPanel Habitoss;
    private javax.swing.JCheckBox JCardiovascular;
    private javax.swing.JCheckBox JHemorragias;
    private javax.swing.JPanel JMotivo;
    private javax.swing.JPanel JPanelAnteriores;
    private javax.swing.JPanel JPanelHistoria;
    public javax.swing.JTable JTablePacientes;
    private javax.swing.JTextArea JtextAntc1;
    private javax.swing.JTextArea JtxtInforme;
    private javax.swing.JTextArea LenguaTxt;
    private javax.swing.JTextArea MaxilaresTxt;
    private javax.swing.JTextArea MuscularTxt;
    private javax.swing.JTabbedPane Odontologia;
    private javax.swing.JTextArea PaladarTxt;
    private javax.swing.JMenuItem Pendiente;
    private javax.swing.JPopupMenu PopupEstado;
    private javax.swing.JPopupMenu PopupHistorias;
    private javax.swing.JTextArea SalivaTxt;
    private javax.swing.JTextField TXTResequedad;
    private javax.swing.JTextField TXT_Agua;
    private javax.swing.JTextField TXT_Alergico;
    private javax.swing.JTextField TXT_Alimento;
    private javax.swing.JTextField TXT_Alimentos;
    private javax.swing.JTextField TXT_Apariencia;
    private javax.swing.JTextField TXT_Aspirina;
    private javax.swing.JTextField TXT_Cansansio;
    private javax.swing.JTextField TXT_Congenita;
    private javax.swing.JTextField TXT_Embarazo;
    private javax.swing.JTextField TXT_Encias;
    private javax.swing.JTextField TXT_Gastrointestinal;
    private javax.swing.JTextField TXT_GraveEnfermo;
    private javax.swing.JTextField TXT_Hepaticas;
    private javax.swing.JTextField TXT_Hipertension;
    private javax.swing.JTextField TXT_Hormonal;
    private javax.swing.JTextField TXT_Intervenido;
    private javax.swing.JTextField TXT_Jaqueca;
    private javax.swing.JTextField TXT_Mandibula;
    private javax.swing.JTextField TXT_MovilidadDientes;
    private javax.swing.JTextArea TXT_Observaciones;
    private javax.swing.JTextField TXT_Orina;
    private javax.swing.JTextField TXT_ReaccionAnestecia;
    private javax.swing.JTextField TXT_Respiratorias;
    private javax.swing.JTextField TXT_SEXUAL;
    private javax.swing.JTextField TXT_Sensibilidad;
    private javax.swing.JTextField TXT_TerapiaHormonal;
    private javax.swing.JTextField TXT_Tratamiento;
    private javax.swing.JTextField TXT_Ulceraciones;
    private javax.swing.JTextField TXT_Zumbidos;
    private javax.swing.JTextField TXT_asma;
    private javax.swing.JTextField TXT_diabetes;
    private javax.swing.JTextField TXT_halitosis;
    private javax.swing.JTextField TXT_infecciosa;
    private javax.swing.JTextField TXT_inflamacion;
    private javax.swing.JTextField TXT_respiradorbucal;
    private javax.swing.JTextField TXT_reumatica;
    private javax.swing.JTextField TXT_sinusitis;
    private javax.swing.JTextField TXTabdominal;
    private javax.swing.JTextField TXTaspecto;
    private javax.swing.JTextField TXTcadera;
    private javax.swing.JTextField TXTfr;
    private javax.swing.JTextField TXTimc;
    private javax.swing.JTextField TXTpeso;
    private javax.swing.JTextField TXTpesoAdic;
    private javax.swing.JTextField TXTpesoIdeal;
    private javax.swing.JTextField TXTpulso;
    private javax.swing.JTextField TXTrespiracion;
    private javax.swing.JTextField TXTtalla;
    private javax.swing.JTextField TXTtemperatura;
    private javax.swing.JTextField TXTtension;
    private javax.swing.JTextArea TemporomandibularTxt;
    private javax.swing.JTextField TxtBusAntcP;
    private javax.swing.JTextField TxtCiclosMestru;
    private javax.swing.JTextArea TxtTiroidea;
    private javax.swing.JTextField Txt_Alteraciones;
    private javax.swing.JTextField Txt_Anemia;
    private javax.swing.JTextField Txt_Hemorragia;
    private javax.swing.JTextField Txt_renales;
    private javax.swing.JTextField Txtbusqueda;
    private javax.swing.JTextField TxtbusquedaAntc;
    private javax.swing.JButton btnAgregarOdonto;
    private javax.swing.JButton btnCancerlarOdonto;
    private javax.swing.JButton btnModificarOdonto;
    private javax.swing.JButton btnRetirarOdonto;
    private javax.swing.JButton jAButton30;
    private javax.swing.JButton jAdcDelete;
    private javax.swing.JButton jAdcModif;
    private javax.swing.JCheckBox jAlergia;
    private javax.swing.JCheckBox jAlimento;
    private javax.swing.JCheckBox jAnemia;
    private javax.swing.JCheckBox jArticulaciones;
    private javax.swing.JCheckBox jAsma;
    private javax.swing.JCheckBox jAspirina;
    private javax.swing.JButton jBtnRetirarAntc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton26;
    private javax.swing.JCheckBox jCabrasion;
    private javax.swing.JCheckBox jCadenopatia;
    private javax.swing.JCheckBox jCamalgama;
    private javax.swing.JCheckBox jCansansio;
    private javax.swing.JCheckBox jCarie;
    private javax.swing.JCheckBox jCdienteMovil;
    private javax.swing.JCheckBox jCdienteOscuro;
    private javax.swing.JCheckBox jCemento;
    private javax.swing.JCheckBox jCespontaneo;
    private javax.swing.JCheckBox jCextraoral;
    private javax.swing.JCheckBox jCfistula;
    private javax.swing.JCheckBox jCfractura;
    private javax.swing.JCheckBox jCincrustacion;
    private javax.swing.JCheckBox jCintraOral;
    private javax.swing.JCheckBox jClatidos;
    private javax.swing.JComboBox<String> jComboActividad;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboDiente;
    private javax.swing.JComboBox<String> jComboFrec;
    private javax.swing.JComboBox<String> jComboHabitos;
    private javax.swing.JComboBox<String> jComboMestruacion;
    private javax.swing.JComboBox<String> jComboSexualidad;
    private javax.swing.JComboBox<String> jComboSueno;
    private javax.swing.JCheckBox jCongenita;
    private javax.swing.JCheckBox jConsumoAcido;
    private javax.swing.JCheckBox jCorona;
    private javax.swing.JCheckBox jCpalpitacion;
    private javax.swing.JCheckBox jCpresente;
    private javax.swing.JCheckBox jCrc;
    private javax.swing.JCheckBox jDiabetes;
    private javax.swing.JCheckBox jDientesM;
    private javax.swing.JCheckBox jEmbarazo;
    private javax.swing.JCheckBox jFiebre;
    private javax.swing.JCheckBox jGastro;
    private javax.swing.JCheckBox jGrave;
    private javax.swing.JCheckBox jHalitosis;
    private javax.swing.JCheckBox jHepaticas;
    private javax.swing.JCheckBox jHipertension;
    private javax.swing.JCheckBox jHormonal;
    private javax.swing.JCheckBox jInfecciosas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JCheckBox jMandibula;
    private javax.swing.JCheckBox jMedicamento;
    private javax.swing.JCheckBox jMigrañas;
    private javax.swing.JCheckBox jNingunaAnteriores;
    private javax.swing.JCheckBox jOrina;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox jQuirurgica;
    private javax.swing.JRadioButton jRadioAF;
    private javax.swing.JRadioButton jRadioAPP;
    private javax.swing.JRadioButton jRcontinuo;
    private javax.swing.JRadioButton jRdifuso;
    private javax.swing.JCheckBox jReaccionAnestecia;
    private javax.swing.JCheckBox jRenales;
    private javax.swing.JCheckBox jRespiradorBucal;
    private javax.swing.JCheckBox jRespiratorias;
    private javax.swing.JButton jRetirarAntc2;
    private javax.swing.JRadioButton jRintermitente;
    private javax.swing.JRadioButton jRleve;
    private javax.swing.JRadioButton jRlocalizado;
    private javax.swing.JRadioButton jRmoderado;
    private javax.swing.JRadioButton jRsevero;
    private javax.swing.JSpinner jSabortos;
    private javax.swing.JCheckBox jSangranEncias;
    private javax.swing.JCheckBox jSatisfecho;
    private javax.swing.JSpinner jScesareas;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSembarazo;
    private javax.swing.JCheckBox jSensibilidad;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JCheckBox jSinusitis;
    private javax.swing.JSpinner jSmenospausia;
    private javax.swing.JSpinner jSpartos;
    private javax.swing.JSpinner jSpinnerMenarquia;
    private javax.swing.JTextArea jTConsumoAlcohol;
    private javax.swing.JTextArea jTConsumoDrugs;
    private javax.swing.JTextArea jTConsumoTabaco;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableAF2;
    private javax.swing.JTable jTableAdic;
    private javax.swing.JTable jTableAntcPers;
    private javax.swing.JTable jTableAntecedentesF;
    private javax.swing.JTable jTableAntpersonal;
    private javax.swing.JTable jTableOdontograma;
    private javax.swing.JTable jTableProcedimientos;
    private javax.swing.JTable jTableVisualizarC;
    private javax.swing.JCheckBox jTerapiaHormonal;
    private javax.swing.JTextArea jTextAntPersonales;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextConclusion;
    private javax.swing.JTextField jTextExamenAdic;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextMotivoConsulta;
    private javax.swing.JTextArea jTextObservAdic;
    private javax.swing.JTextArea jTextRadiologica;
    private javax.swing.JTextArea jTextRecomendacion;
    private javax.swing.JCheckBox jTransmision;
    private javax.swing.JTextField jTxtOtros;
    private javax.swing.JCheckBox jUlceraciones;
    private javax.swing.JMenuItem jUtilizarHisto;
    private javax.swing.JCheckBox jVasosAgua;
    private javax.swing.JCheckBox jZumbidos;
    private javax.swing.JCheckBox jimgOdontograma;
    private javax.swing.JCheckBox jresequedad;
    private javax.swing.JTabbedPane tabbedAntecedentes;
    // End of variables declaration//GEN-END:variables



    

    
    

}


