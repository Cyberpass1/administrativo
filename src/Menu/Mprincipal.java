
package Menu;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.RespaldoBd;
import Clases.RestauracionBd;
import Clases.Temporal;
import Consultas.ConsultaFacturas;
import Consultas.ConsultaPacientes;
import Consultas.ConsultaUsers;
import Procesos.JCompras;
import Procesos.JFacturacion;
import Procesos.jInventario;
import Registros.JAuditoria;
import Registros.JCategoriaProductos;
import Registros.JCategoriaServ;
import Registros.JConfigEmpresas;
import Registros.JGastos;
import Registros.JOnline;
import Registros.JPacientes;
import Registros.JPacientesPCP;
import Registros.JPermisos;
import Registros.JProductos;
import Registros.JProveedores;
import Registros.JSeguridad;
import Registros.JServicios;
import Registros.JUregistro;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.login.Login;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date; 
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel; 
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;



public class Mprincipal extends javax.swing.JFrame implements Runnable{
   
 
public static void main(String args[]) {
    // Crear una instancia de Mprincipal y hacerla visible
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            Mprincipal frame = new Mprincipal();
            frame.setVisible(true);
           // frame.setResizable(true);
          // Registrar un shutdown hook
          Runtime.getRuntime().addShutdownHook(new Thread() {
    
      
              
        public void run() {  
        try {
               updateLastLogin(idUsuarioOn);
               
        } catch (Exception e) { 
            System.err.println("Error en el shutdown hook: " + e.getMessage());
            e.printStackTrace(); 
        }
    }});
        }});
    
    

}





 public static int idUsuarioOn=0;
 Temporal TM = new Temporal();   
 public Mprincipal() {

 initComponents();
 setResizable(false);
 
 h1= new Thread(this);
 h1.start();
 informacion();
 
 
 String fecha = new SimpleDateFormat("dd/MM/yyyy").format(FechaAdmin.getDate());
 String fecha2 = new SimpleDateFormat("dd/MM/yyyy").format(FechaAdmin.getDate());
 String [] dateParts= fecha.split("/");
 String an = dateParts[0];
 String mes = dateParts[1];
 String dia = dateParts[2];
 fecha2 = (an+"/"+mes+"/"+dia);
 FechaAc.setText(fecha2);
 FechaAdmin.setVisible(false);
 IDUSER.setVisible(false);
 jMenuFactura.setVisible(false);
 IDEspecialidad.setVisible(false);
 
     java.net.URL url = ClassLoader.getSystemResource("C:\\Cyberia\\src\\imagenes\\Logo_System.png");
try{    
       setIconImage(ImageIO.read(new File("C:\\Cyberia\\src\\imagenes\\Logo_System.png")));   
   }
catch (Exception ex){
       System.out.println(ex);
   }

    
    }



 
 
    
  
public void hora(){ 

  Calendar calendario = new GregorianCalendar();
  Date horaactual= new Date(); 
  calendario.setTime(horaactual);
  hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
  minutos= calendario.get(Calendar.MINUTE)>9? ""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
  segundos= calendario.get(Calendar.SECOND)>9? ""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);

}

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JMenu = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        FechaAc = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        BtnMensaje = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        FechaAdmin = new com.toedter.calendar.JDateChooser();
        TxtUser = new javax.swing.JTextField();
        TxtRol = new javax.swing.JTextField();
        Txtentrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Txtinfo = new javax.swing.JTextArea();
        IDUSER = new javax.swing.JTextField();
        JLabelNombre = new javax.swing.JTextField();
        TxtRol1 = new javax.swing.JTextField();
        IDEspecialidad = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        JDesktopMenu = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenuRegistros = new javax.swing.JMenu();
        JMuser = new javax.swing.JMenuItem();
        JMenuCliente = new javax.swing.JMenuItem();
        jProveedores = new javax.swing.JMenuItem();
        jMenAdmin = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenCtgPrd = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuProcesos = new javax.swing.JMenu();
        jMinventario = new javax.swing.JMenuItem();
        jMenuFactura = new javax.swing.JMenuItem();
        jCompras = new javax.swing.JMenuItem();
        JMreportes = new javax.swing.JMenu();
        jCfacturas = new javax.swing.JMenuItem();
        jConsPac = new javax.swing.JMenuItem();
        JConsultPersonal = new javax.swing.JMenuItem();
        jMenuConfiguracion = new javax.swing.JMenu();
        JMPermisologia = new javax.swing.JMenuItem();
        jMenuDivisa = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        JEmpresa = new javax.swing.JMenuItem();
        jMenuSeguridad = new javax.swing.JMenu();
        jMenuPassword = new javax.swing.JMenuItem();
        jOnline = new javax.swing.JMenuItem();
        jMenuServicios = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuRestauracion = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Aboutme = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú - Pérez F.P");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1300, 695));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JMenu.setBackground(new java.awt.Color(255, 255, 255));
        JMenu.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        JMenu.setEnabled(false);
        JMenu.setMinimumSize(new java.awt.Dimension(1300, 695));
        JMenu.setPreferredSize(new java.awt.Dimension(1300, 695));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confeccion.jpg"))); // NOI18N
        panelImage2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        FechaAc.setForeground(new java.awt.Color(255, 255, 255));
        FechaAc.setText("Fecha");
        panelImage2.add(FechaAc, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, 50));

        Time.setBackground(new java.awt.Color(0, 0, 0));
        Time.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        Time.setForeground(new java.awt.Color(255, 255, 255));
        Time.setText("Hora");
        panelImage2.add(Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, 50));

        jLabel102.setBackground(new java.awt.Color(0, 0, 0));
        jLabel102.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("AM");
        panelImage2.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 60, 50));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel95.setBackground(new java.awt.Color(0, 0, 0));
        jLabel95.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel95.setText("Buenos dias");
        jLabel95.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel95MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 50));

        jLabel98.setBackground(new java.awt.Color(0, 0, 0));
        jLabel98.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel98.setText("Última Entrada");
        jPanel7.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 50));

        jLabel104.setBackground(new java.awt.Color(0, 0, 0));
        jLabel104.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel104.setText("Rol");
        jPanel7.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 100, 50));

        BtnMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/modificar.png"))); // NOI18N
        BtnMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnMensaje.setBorderPainted(false);
        BtnMensaje.setContentAreaFilled(false);
        BtnMensaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnMensaje.setEnabled(false);
        BtnMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMensajeActionPerformed(evt);
            }
        });
        jPanel7.add(BtnMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 120, 40));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setBackground(new java.awt.Color(0, 0, 0));
        jLabel108.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("D A T O S    D E    U S U A R I O");
        jPanel6.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -10, 210, 50));

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 30));

        jLabel106.setBackground(new java.awt.Color(0, 0, 0));
        jLabel106.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel106.setText("Usuario:");
        jPanel7.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, 50));

        FechaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        FechaAdmin.setToolTipText("");
        FechaAdmin.setEnabled(false);
        FechaAdmin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel7.add(FechaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 510, 110, 30));

        TxtUser.setEditable(false);
        TxtUser.setFocusable(false);
        TxtUser.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtUserCaretUpdate(evt);
            }
        });
        jPanel7.add(TxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 260, 30));

        TxtRol.setEditable(false);
        TxtRol.setFocusable(false);
        TxtRol.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtRolCaretUpdate(evt);
            }
        });
        jPanel7.add(TxtRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 130, 30));

        Txtentrada.setEditable(false);
        Txtentrada.setFocusable(false);
        jPanel7.add(Txtentrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 260, 30));

        Txtinfo.setEditable(false);
        Txtinfo.setColumns(20);
        Txtinfo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Txtinfo.setLineWrap(true);
        Txtinfo.setRows(5);
        Txtinfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Mnesaje Administrativo"));
        Txtinfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TxtinfoMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(Txtinfo);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 390, 140));

        IDUSER.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                IDUSERCaretUpdate(evt);
            }
        });
        jPanel7.add(IDUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 60, -1));

        JLabelNombre.setEditable(false);
        JLabelNombre.setFocusable(false);
        JLabelNombre.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                JLabelNombreCaretUpdate(evt);
            }
        });
        jPanel7.add(JLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 260, 30));

        TxtRol1.setEditable(false);
        TxtRol1.setFocusable(false);
        TxtRol1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TxtRol1CaretUpdate(evt);
            }
        });
        jPanel7.add(TxtRol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 120, 30));

        IDEspecialidad.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                IDEspecialidadCaretUpdate(evt);
            }
        });
        jPanel7.add(IDEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 60, -1));

        jLabel107.setBackground(new java.awt.Color(0, 0, 0));
        jLabel107.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 153));
        jLabel107.setText("Pérez F.P");
        jPanel7.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 170, 50));

        jLabel109.setBackground(new java.awt.Color(0, 0, 0));
        jLabel109.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 153));
        jLabel109.setText("Bordados y Confecciones ");
        jPanel7.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 370, 50));

        panelImage2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 410, 560));

        jPanel5.setBackground(new java.awt.Color(0, 0, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelImage2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 30));

        jPanel3.add(panelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 660));

        JMenu.addTab("", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JDesktopMenu.setMinimumSize(new java.awt.Dimension(1300, 695));

        javax.swing.GroupLayout JDesktopMenuLayout = new javax.swing.GroupLayout(JDesktopMenu);
        JDesktopMenu.setLayout(JDesktopMenuLayout);
        JDesktopMenuLayout.setHorizontalGroup(
            JDesktopMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        JDesktopMenuLayout.setVerticalGroup(
            JDesktopMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        jPanel4.add(JDesktopMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 660));

        JMenu.addTab("", jPanel4);

        jPanel1.add(JMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -28, 1300, 690));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, -1));

        jMenu11.setText("|  INICIO   |");
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenu11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu11ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu11);

        jMenuRegistros.setText("  REGISTROS  |");

        JMuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/mujer.png"))); // NOI18N
        JMuser.setText("Usuarios");
        JMuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMuserActionPerformed(evt);
            }
        });
        jMenuRegistros.add(JMuser);

        JMenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/doctor.png"))); // NOI18N
        JMenuCliente.setText("Clientes");
        JMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuClienteActionPerformed(evt);
            }
        });
        jMenuRegistros.add(JMenuCliente);

        jProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supplier (1).png"))); // NOI18N
        jProveedores.setText("Proveedores");
        jProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProveedoresActionPerformed(evt);
            }
        });
        jMenuRegistros.add(jProveedores);

        jMenAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/administrar.png"))); // NOI18N
        jMenAdmin.setText("Administrativo");
        jMenAdmin.setEnabled(false);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/public-service.png"))); // NOI18N
        jMenuItem9.setText("Servicios");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenAdmin.add(jMenuItem9);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregar-producto.png"))); // NOI18N
        jMenuItem14.setText("Productos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenAdmin.add(jMenuItem14);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/categorias.png"))); // NOI18N
        jMenuItem21.setText("Categ. Servicios");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenAdmin.add(jMenuItem21);

        jMenCtgPrd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/categorias (1).png"))); // NOI18N
        jMenCtgPrd.setText("Categ. Productos");
        jMenCtgPrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenCtgPrdActionPerformed(evt);
            }
        });
        jMenAdmin.add(jMenCtgPrd);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/spending.png"))); // NOI18N
        jMenuItem7.setText("Gastos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenAdmin.add(jMenuItem7);

        jMenuRegistros.add(jMenAdmin);

        jMenuBar1.add(jMenuRegistros);

        jMenuProcesos.setText("PROCESOS  | ");

        jMinventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/inventario.png"))); // NOI18N
        jMinventario.setText("Inventario");
        jMinventario.setEnabled(false);
        jMinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMinventarioActionPerformed(evt);
            }
        });
        jMenuProcesos.add(jMinventario);

        jMenuFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Facturar.png"))); // NOI18N
        jMenuFactura.setText("Facturación");
        jMenuFactura.setEnabled(false);
        jMenuFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFacturaActionPerformed(evt);
            }
        });
        jMenuProcesos.add(jMenuFactura);

        jCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/grocery-store.png"))); // NOI18N
        jCompras.setText("Compras & Gastos");
        jCompras.setEnabled(false);
        jCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComprasActionPerformed(evt);
            }
        });
        jMenuProcesos.add(jCompras);

        jMenuBar1.add(jMenuProcesos);

        JMreportes.setText("CONSULTAS & REPORTES  | ");

        jCfacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Facturar.png"))); // NOI18N
        jCfacturas.setText("Facturas");
        jCfacturas.setEnabled(false);
        jCfacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCfacturasActionPerformed(evt);
            }
        });
        JMreportes.add(jCfacturas);

        jConsPac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/paciente.png"))); // NOI18N
        jConsPac.setText("Clientes");
        jConsPac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsPacActionPerformed(evt);
            }
        });
        JMreportes.add(jConsPac);

        JConsultPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trabajo-en-equipo.png"))); // NOI18N
        JConsultPersonal.setText("Personal");
        JConsultPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JConsultPersonalActionPerformed(evt);
            }
        });
        JMreportes.add(JConsultPersonal);

        jMenuBar1.add(JMreportes);

        jMenuConfiguracion.setText("CONFIGURACIÓN  |");

        JMPermisologia.setText("Permisologia");
        JMPermisologia.setEnabled(false);
        JMPermisologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMPermisologiaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(JMPermisologia);

        jMenuDivisa.setText("Tasa & Porcentajes");
        jMenuDivisa.setEnabled(false);
        jMenuDivisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDivisaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(jMenuDivisa);

        jMenuItem16.setText("Reportes");
        jMenuItem16.setEnabled(false);
        jMenuConfiguracion.add(jMenuItem16);

        JEmpresa.setText("Empresas");
        JEmpresa.setEnabled(false);
        JEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEmpresaActionPerformed(evt);
            }
        });
        jMenuConfiguracion.add(JEmpresa);

        jMenuBar1.add(jMenuConfiguracion);

        jMenuSeguridad.setText("SEGURIDAD  |");
        jMenuSeguridad.setEnabled(false);

        jMenuPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bloquear.png"))); // NOI18N
        jMenuPassword.setText("Cambio Contraseña");
        jMenuPassword.setEnabled(false);
        jMenuPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPasswordActionPerformed(evt);
            }
        });
        jMenuSeguridad.add(jMenuPassword);

        jOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/ModuloOn.png"))); // NOI18N
        jOnline.setText("Usuarios Online");
        jOnline.setEnabled(false);
        jOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOnlineActionPerformed(evt);
            }
        });
        jMenuSeguridad.add(jOnline);

        jMenuBar1.add(jMenuSeguridad);

        jMenuServicios.setText("SERVICIOS  |");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/copia-de-respaldo.png"))); // NOI18N
        jMenuItem4.setText("Respaldo");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuServicios.add(jMenuItem4);

        jMenuRestauracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/respaldo.png"))); // NOI18N
        jMenuRestauracion.setText("Restauración");
        jMenuRestauracion.setEnabled(false);
        jMenuRestauracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRestauracionActionPerformed(evt);
            }
        });
        jMenuServicios.add(jMenuRestauracion);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/auditoria.png"))); // NOI18N
        jMenuItem6.setText("Auditoria");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuServicios.add(jMenuItem6);

        jMenuBar1.add(jMenuServicios);

        jMenu8.setText("HERRAMIENTAS  |");
        jMenu8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jMenu8ItemStateChanged(evt);
            }
        });

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calculadora.png"))); // NOI18N
        jMenuItem8.setText("Calculadora");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem8);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/nota-adhesiva.png"))); // NOI18N
        jMenuItem11.setText("Notas");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);

        jMenuBar1.add(jMenu8);

        jMenu9.setText("AYUDA  |");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/informacion.png"))); // NOI18N
        jMenuItem1.setText("Manual de Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem1);

        Aboutme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/informacion (1).png"))); // NOI18N
        Aboutme.setText("Acerda de...");
        Aboutme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutmeActionPerformed(evt);
            }
        });
        jMenu9.add(Aboutme);

        jMenuBar1.add(jMenu9);

        jMenu10.setText("CERRAR SESIÓN  |");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/flecha.png"))); // NOI18N
        jMenuItem2.setText("Cerrar sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/boton.png"))); // NOI18N
        jMenuItem3.setText("Cerrar sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem3);

        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       
     if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar la sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    String sql = "UPDATE table_usuario SET Fecha=?, Hora=?, online=? WHERE IdPersonal=?";
    
    // Usando try-with-resources para cerrar automáticamente los recursos
    try (Connection con = EnlaceBd.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        java.sql.Date fechaU = new java.sql.Date(FechaAdmin.getDate().getTime());
        
        ps.setDate(1, fechaU);
        ps.setString(2, Time.getText() + " " + jLabel102.getText());
        ps.setInt(3, 0);
        ps.setInt(4, Integer.parseInt(IDUSER.getText()));
        
        // Ejecuta la actualización
        ps.executeUpdate();
        
    } catch (Exception e) {
        System.out.println("Error al actualizar la sesión: " + e);
    }


        Login me = new Login();
        me.setLocationRelativeTo(null);
        me.setVisible(true);
        dispose();
        } 
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
    
    
    
    
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
 
   Connection con;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps;
   ResultSet rs;   
   java.sql.Date fechaU;
   Date dateU = FechaAdmin.getDate();
   long dU = dateU.getTime(); 
   fechaU = new java.sql.Date(dU);

        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?",
            "Cerrar Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        try {
            String sql = "update table_usuario set Fecha=?, Hora=?, online=? where IdPersonal=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechaU);
            ps.setString(2, Time.getText() +" "+jLabel102.getText());
            ps.setInt(3, 0);
            ps.setInt(4, Integer.parseInt(IDUSER.getText()));
            ps.executeUpdate();
          

      
        } catch (Exception e) { System.out.println(e);
        }

        System.exit(0);
        } else {}
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    
    
    private void JMuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMuserActionPerformed
        JMenu.setSelectedIndex(1);
        JUregistro Ir = new JUregistro();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_JMuserActionPerformed

    
    
    private void JMPermisologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMPermisologiaActionPerformed
        JMenu.setSelectedIndex(1);
        JPermisos Ir = new JPermisos();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
        Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_JMPermisologiaActionPerformed

    
    
    
    
    private void JMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuClienteActionPerformed

    
      String nivel = Tempo.getNivel();
      String especialidad = Tempo.getEspecialidad();
      String nivelUsuario = nivel + " " + especialidad;

      JMenu.setSelectedIndex(1);
      JDesktopMenu.setVisible(true);

      JInternalFrame ir; // Declaración de variable para la ventana interna
/*
      if (nivelUsuario.equals("Aux. Atencion al usuario") || TxtRol.getText().equals("PCP") || TxtRol.getText().equals("Inspector")) {
      ir = new JPacientesPCP();
      } else {
      ir = new JPacientes();
      }*/
      ir = new JPacientesPCP();
      JDesktopMenu.add(ir);
      ir.setClosable(true);
      ir.setIconifiable(true);
      try {
      ir.setMaximum(true);
      } catch (Exception e) {
      System.out.println(e);
      }
      ir.toFront();
      ir.setVisible(true);
      this.setLocationRelativeTo(null);

        
        
    }//GEN-LAST:event_JMenuClienteActionPerformed

    private void jMenuPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPasswordActionPerformed
       /* JMenu.setSelectedIndex(1);
        JSeguridad Ir = new  JSeguridad();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
        */
        
        JSeguridad Ir = new JSeguridad();
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        Ir.setVisible(true);
        JDesktopMenu.add(Ir);
        try {
        Ir.setMaximum(true);
        } catch (Exception e) {
        e.printStackTrace(); 
        }
        Ir.toFront();
        JMenu.setSelectedIndex(1);
        this.setLocationRelativeTo(null);

        
        
    }//GEN-LAST:event_jMenuPasswordActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JMenu.setSelectedIndex(1);
        JAuditoria Ir = new JAuditoria();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu11ActionPerformed
      JMenu.setSelectedIndex(0);
    }//GEN-LAST:event_jMenu11ActionPerformed

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked
          JMenu.setSelectedIndex(0);
    }//GEN-LAST:event_jMenu11MouseClicked

    private void AboutmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutmeActionPerformed
        AboutMe me = new  AboutMe();
        me.setLocationRelativeTo(null);
        me.setVisible(true); 
    }//GEN-LAST:event_AboutmeActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        
       try {
    ProcessBuilder processBuilder = new ProcessBuilder("calc");
    Process process = processBuilder.start();
} catch (IOException ioe) {
    ioe.printStackTrace();
} 
        
        
        
       /* 
        try
        {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
        }
        //EXCEPCION
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }*/
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    String variableRol = TxtRol.getText();
    String categoriaRol = TxtRol1.getText();
    String roLCompleto = variableRol+ " "+ categoriaRol;
    
    
    
    if (roLCompleto.equals("Lcdo. Bioanalista") || roLCompleto.equals("Aux. Laboratorio")) {

    try {
        File path = new File("C:\\Fundaginebra\\src\\Manual_Laboratorio.pdf");
        Desktop.getDesktop().open(path);

    } catch (IOException ex) {
        ex.printStackTrace();
    }

} else if (variableRol.equals("Doctor")) {

    try {
        File path = new File("C:\\Fundaginebra\\src\\Manual_Doctor.pdf");
        Desktop.getDesktop().open(path);

    } catch (IOException ex) {
        ex.printStackTrace();
    }
} 

else if (variableRol.equals("Inspector") ||variableRol.equals("PCP") || roLCompleto.equals("Aux. Atencion al paciente")) {

    try {
        File path = new File("C:\\Fundaginebra\\src\\Manual_Pcp.pdf");
        Desktop.getDesktop().open(path);
 
    } catch (IOException ex) {
        ex.printStackTrace();
    }
} 


else if (variableRol.equals("Administrador")) {

    try {
        File path = new File("C:\\Fundaginebra\\src\\Manual_Administrador.pdf");
        Desktop.getDesktop().open(path);

    } catch (IOException ex) {
        ex.printStackTrace();
    }
} 



else {

    JOptionPane.showMessageDialog(this, "Manual de usuario próximamente", "Manual de usuario", JOptionPane.INFORMATION_MESSAGE);

}

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         if (JOptionPane.showConfirmDialog(rootPane, "Esta apunto de hacer un respaldo de la BD ¿Desea realizarlo?",
            "Respaldo de BD", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {


        try {
            instanciaRespaldo.crearBackup();
            JOptionPane.showMessageDialog(null,"RESPALDO COMPLETADO CON EXITO", "RESPALDO", 1);
            AuditoriaRespaldo();
        } catch (IOException error) {
            System.out.println(error);
           JOptionPane.showMessageDialog(null,"ERROR EN LA CREACIÓN DEL RESPALDO - EL RESPALDO SOLO PUEDE SER HECHO DESDE EL SERVIDOR", "RESPALDO", JOptionPane.ERROR_MESSAGE);
        }

        }
       else{JOptionPane.showMessageDialog(null, "CREACIÓN DE RESPALDO CANCELADA","RESPALDO BD",1);}
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    

    
    private void jMenuRestauracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRestauracionActionPerformed

        
      if (JOptionPane.showConfirmDialog(rootPane, "Es recomendable consultar con el desarrollador del software antes de realizar una restauración, ¿Desea realizarla?",
            "Respaldo de BD", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  { 
   
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
   
   
    String query="SELECT IdPersonal, Usuario,  Clave, Nivel, date_format(Fecha, '%d/%m/%Y') AS myDate, Hora FROM table_usuario u\n" +
    "INNER JOIN table_nivel n ON u.Nivel=n.Idnivel\n" +
    "where Clave=?";
        
       
   JPasswordField contraseña = new JPasswordField();
   if(JOptionPane.showConfirmDialog(null, contraseña, "CONFIRMAR CONTRASEÑA", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
   String pass = new String(contraseña.getPassword());


   String clave = encriptar.ecnode(pass);
   
   try {        
       
       con = cn.getConnection();
       ps = con.prepareStatement(query);
       ps.setString(1, clave);
  
       rs=ps.executeQuery();

       if(rs.next())
       {      
        instanciaRestauracion.crearRestauracion();
        AuditoriaRestauracion();
        // ConsultaRestauracion();
       }else{JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA", "RESTAURACIÓN", JOptionPane.ERROR_MESSAGE);}
        } catch (Exception e) {
            System.out.println(e);
           JOptionPane.showMessageDialog(null,"ERROR EN LA RESTAURACIÓN - LA RESTAURACIÓN SOLO PUEDE SER HECHA DESDE EL SERVIDOR", "RESTAURACIÓN", JOptionPane.ERROR_MESSAGE);
        }

         finally{ try{ps.close();
        rs.close();
        con.close();
    }catch(Exception e){System.out.println(e);}    
    } }

else{
 JOptionPane.showMessageDialog(null,"RESTAURACIÓN CANCELADA", "RESTAURACIÓN", 1);}
}           
  
    }//GEN-LAST:event_jMenuRestauracionActionPerformed

    
    private void jConsPacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsPacActionPerformed
        JMenu.setSelectedIndex(1);
        ConsultaPacientes Ir = new ConsultaPacientes();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jConsPacActionPerformed

    private void JConsultPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JConsultPersonalActionPerformed
        JMenu.setSelectedIndex(1);
        ConsultaUsers Ir = new ConsultaUsers();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);

        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_JConsultPersonalActionPerformed

     
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
   updateLastLogin(idUsuarioOn);
    }//GEN-LAST:event_formWindowClosing

    private void jMenu8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jMenu8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu8ItemStateChanged

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        
       try {
    ProcessBuilder processBuilder = new ProcessBuilder("notepad");
    Process process = processBuilder.start();
} catch (IOException ioe) {
    ioe.printStackTrace();
} 
            
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void JEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEmpresaActionPerformed
 inEmpresas();      
    }//GEN-LAST:event_JEmpresaActionPerformed

    private void jOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOnlineActionPerformed
        JMenu.setSelectedIndex(1);
        JOnline Ir = new JOnline();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null);    
    }//GEN-LAST:event_jOnlineActionPerformed

    private void jMenuDivisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDivisaActionPerformed
 callDolar();

    }//GEN-LAST:event_jMenuDivisaActionPerformed

    private void jMenuFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFacturaActionPerformed
        JMenu.setSelectedIndex(1);
        JFacturacion Ir = new JFacturacion();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jMenuFacturaActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        JMenu.setSelectedIndex(1);
        JServicios Ir = new JServicios();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        JMenu.setSelectedIndex(1);
        JCategoriaServ Ir = new JCategoriaServ();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenCtgPrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenCtgPrdActionPerformed
        JMenu.setSelectedIndex(1);
        JCategoriaProductos Ir = new JCategoriaProductos();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
    }//GEN-LAST:event_jMenCtgPrdActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        JMenu.setSelectedIndex(1);
        JProductos Ir = new  JProductos();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jCfacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCfacturasActionPerformed
        JMenu.setSelectedIndex(1);
        ConsultaFacturas Ir = new  ConsultaFacturas();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jCfacturasActionPerformed

    private void jMinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMinventarioActionPerformed
        JMenu.setSelectedIndex(1);
        jInventario Ir = new  jInventario();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMinventarioActionPerformed

    private void TxtRol1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtRol1CaretUpdate
        Temporal tm = new Temporal();
        tm.setEspecialidad((TxtRol1.getText().trim()));
    }//GEN-LAST:event_TxtRol1CaretUpdate

    private void JLabelNombreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_JLabelNombreCaretUpdate
        Temporal tm = new Temporal();
        tm.setNombre((JLabelNombre.getText().trim()));
    }//GEN-LAST:event_JLabelNombreCaretUpdate

    private void TxtinfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtinfoMouseEntered

    }//GEN-LAST:event_TxtinfoMouseEntered

    private void TxtRolCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtRolCaretUpdate
        Temporal tm = new Temporal();
        tm.setNivel((TxtRol.getText().trim()));
    }//GEN-LAST:event_TxtRolCaretUpdate

    private void TxtUserCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TxtUserCaretUpdate
        Temporal tm = new Temporal();
        tm.setUser((TxtUser.getText().trim()));
    }//GEN-LAST:event_TxtUserCaretUpdate

    private void BtnMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMensajeActionPerformed
        String sql = "UPDATE tableinfo SET mensaje=?";

        try (Connection con = EnlaceBd.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el parámetro y ejecutar la actualización
            ps.setString(1, Txtinfo.getText());
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "Mensaje Actualizado", "Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Fallo al actualizar los datos....");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el mensaje: " + e.getMessage());
        }
    }//GEN-LAST:event_BtnMensajeActionPerformed

    private void jLabel95MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel95MouseClicked
        JMenu.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel95MouseClicked

    private void IDUSERCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_IDUSERCaretUpdate
        Temporal tm = new Temporal();
        tm.setTexto(Integer.parseInt(IDUSER.getText().trim()));
    }//GEN-LAST:event_IDUSERCaretUpdate

    private void IDEspecialidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_IDEspecialidadCaretUpdate
        Temporal tm = new Temporal();
        tm.setIdEspecialidad(Integer.parseInt(IDEspecialidad.getText().trim()));
    }//GEN-LAST:event_IDEspecialidadCaretUpdate

    private void jComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComprasActionPerformed

        
        
        JMenu.setSelectedIndex(1);


       JCompras ir = new JCompras();
       ir.setClosable(true);
       ir.setIconifiable(true);

       try {
       ir.setMaximum(true);  
       } catch (PropertyVetoException e) {
       e.printStackTrace();  
       }


       JDesktopMenu.setVisible(true);
       JDesktopMenu.add(ir);
       ir.setVisible(true);
       ir.toFront();
       this.setLocationRelativeTo(null);
        
        //    JOptionPane.showMessageDialog(null, "MÓDULO EN CONSTRUCCIÓN | PRÓXIMAMENTE", "MÓDULO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jComprasActionPerformed

    private void jProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProveedoresActionPerformed
     JMenu.setSelectedIndex(1);


       JProveedores ir = new JProveedores();
       ir.setClosable(true);
       ir.setIconifiable(true);

       try {
       ir.setMaximum(true);  
       } catch (PropertyVetoException e) {
       e.printStackTrace();  
       }


       JDesktopMenu.setVisible(true);
       JDesktopMenu.add(ir);
       ir.setVisible(true);
       ir.toFront();
       this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jProveedoresActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
  JMenu.setSelectedIndex(1);


       JGastos ir = new JGastos();
       ir.setClosable(true);
       ir.setIconifiable(true);

       try {
       ir.setMaximum(true);  
       } catch (PropertyVetoException e) {
       e.printStackTrace();  
       }


       JDesktopMenu.setVisible(true);
       JDesktopMenu.add(ir);
       ir.setVisible(true);
       ir.toFront();
       this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem7ActionPerformed
 

    
    
    
    
     public void inEmpresas() {
    // Crear un campo de texto para la contraseña
    JPasswordField campoContraseña = new JPasswordField(10);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 1));
    panel.add(new JLabel("Contraseña:"));
    panel.add(campoContraseña);

    // Mostrar el cuadro de diálogo para ingresar la contraseña
    int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar Contraseña:", JOptionPane.OK_CANCEL_OPTION);

    // Verificar si el usuario presionó el botón "OK"
    if (result == JOptionPane.OK_OPTION) {
        // Obtener la contraseña ingresada por el usuario
        char[] contraseñaIngresada = campoContraseña.getPassword();
        String contraseña = new String(contraseñaIngresada); // Convertir a String

        // Validar la contraseña (aquí puedes hacer la validación según tus requerimientos)
        if (validarContraseña(contraseña)) {
        JMenu.setSelectedIndex(1);
        JConfigEmpresas Ir = new JConfigEmpresas();
        JDesktopMenu.setVisible(true);
        JDesktopMenu.add(Ir);
        Ir.setClosable(true);
        Ir.setIconifiable(true);
        try {
            Ir.setMaximum(true);
        } catch (Exception e) {
        }
        Ir.toFront();
        Ir.setVisible(true);
        this.setLocationRelativeTo(null); 
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Acceso denegado.", "Acceso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private boolean validarContraseña(String contraseña) {
  
    return contraseña.equals("272010"); 
}
    
    
    
    
    
    
    
    JTextField campoDivisa = new  JTextField(10);
    JTextField campoIva = new  JTextField(10);
     public void callDolar() {
    // Crear un campo de texto para la contraseña
    
 
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1)); 
    panel.add(new JLabel("Divisa:"));
    panel.add(campoDivisa);
    panel.add(new JLabel("IVA %"));
    panel.add(campoIva);
    showDivisa();
    campoDivisa.setText(divisa);
    campoIva.setText(ivaPrcentaje);
    
    // Mostrar el cuadro de diálogo para ingresar la contraseña
    int result = JOptionPane.showConfirmDialog(null, panel, "DIVISA FECHA: "+ fechDivisa, JOptionPane.OK_CANCEL_OPTION);


    if (result == JOptionPane.OK_OPTION) {

        String divisa = campoDivisa.getText();
  
        
     String mensaje = "¿Está seguro de que desea actualizar la tasa a " + divisa + " y el porcentaje de IVA a " + ivaPrcentaje + "% en todo el sistema?";
mensaje += "\nNOTA: ESTA ACCIÓN AJUSTARÁ TODOS LOS PRECIOS EN BOLÍVARES ANCLADOS A ESTA NUEVA TASA Y A LA NUEVA TARIFA DE IVA. UNA VEZ HECHO EL CAMBIO, PUEDE VERIFICAR EN EL MÓDULO DE FACTURACIÓN.";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "CONFIRMAR ACTUALIZACION",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
           
         addDivisa();
         }
         
         
    }
}
     
     
     
    
 
    
    
   String divisa, fechDivisa, ivaPrcentaje;
   int idDivisa;
   public void showDivisa() {
 
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
   
   
        try {

            String sql = "SELECT valor_dia,iva, Fecha\n" +
"            FROM table_divisas\n" +
"            WHERE id_divisa = (SELECT MAX(id_divisa)  FROM table_divisas)";

            con = cn.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
               // idDivisa= rs.getInt("id_divisa");
                divisa = rs.getString("valor_dia");
                ivaPrcentaje= rs.getString("iva");
                fechDivisa = rs.getString("Fecha");
              
            }

        } catch (Exception e) {System.out.println(e);
        }
        
      finally {
            closeResources(rs, ps, con);
        }
    }
         
    
    
    
         
   
   public void addDivisa(){  
               
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;

            try {
             
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaAdmin.getDate());
           
            String sql = "INSERT INTO `table_divisas`(`valor_dia`,iva, `Fecha`) VALUES (?,?,?)";
  
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, campoDivisa.getText());
            ps.setString(2, campoIva.getText());
            ps.setString(3,Fecha);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "TASA ACTUALIZADA A: "+campoDivisa.getText() +" PORCENTAJE DE IVA: "+ campoIva.getText(),"ACTUALIZACION DE TASA", 1);          
        } catch (Exception e) {System.out.println(e);}
       
             finally {
            closeResources(null, ps, con);
        } }
    
   
   
  
public void informacion() {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try (Connection con = EnlaceBd.getConnection()) {  
        String sql = "SELECT * FROM tableinfo";
        
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            String info = rs.getString("mensaje");
            Txtinfo.setText(info);
        }

        // Obtener la hora actual del servidor
        String sqlTime = "SELECT NOW() AS server_time";
        ps = con.prepareStatement(sqlTime);
        rs = ps.executeQuery();

        if (rs.next()) {
            java.sql.Timestamp currentTime = rs.getTimestamp("server_time");
            
            // Establecer solo la fecha en el JDateChooser
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(currentTime); // Establecer la fecha y hora
            FechaAdmin.setCalendar(calendar); // Establecer el calendario en el JDateChooser
        }

    } catch (Exception e) {
        System.out.println(e);
    } 
}

   public void AuditoriaRespaldo() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) values (?, ?, ?, ?)";
    String accion = "Realizó un respaldo de la base de datos";
    
    try (Connection con = EnlaceBd.getConnection();  // Usamos try-with-resources para la conexión
         PreparedStatement ps = con.prepareStatement(sql)) {

        String Fecha = new SimpleDateFormat("dd/MM/yyyy").format(FechaAdmin.getDate());

        ps.setInt(1, Integer.parseInt(IDUSER.getText()));
        ps.setInt(2, Integer.parseInt(IDUSER.getText()));
        ps.setString(3, accion);
        ps.setString(4, Fecha);

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println(e);
    }
}

public void AuditoriaRestauracion() {
    String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion, FechaMov) values (?, ?, ?, ?)";
    String accion = "Realizó una restauración de la base de datos";

    try (Connection con = EnlaceBd.getConnection();  // Usamos try-with-resources para la conexión
         PreparedStatement ps = con.prepareStatement(sql)) {

        String Fecha = new SimpleDateFormat("dd/MM/yyyy").format(FechaAdmin.getDate());

        ps.setInt(1, Integer.parseInt(IDUSER.getText()));
        ps.setInt(2, Integer.parseInt(IDUSER.getText()));
        ps.setString(3, accion);
        ps.setString(4, Fecha);

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println(e);
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
  
    
    
  public void shorCuts(){
  JMenuItem jMenuItem9 = jMenuBar1.getMenu(2).getItem(0);  //asignar
  JMenuItem jMenuItem10 = jMenuBar1.getMenu(2).getItem(1); //laboratorio
  JMenuItem jMenuItem7 = jMenuBar1.getMenu(2).getItem(2);  //medicos
  JMenuItem JMenuCliente = jMenuBar1.getMenu(1).getItem(1);  //regirosPacientes
   
  JMenuItem jConsultaslab = jMenuBar1.getMenu(3).getItem(0);  //regirosPacientes
  
   // Define el KeyStroke para "Control + L"
   KeyStroke keyStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK);
   KeyStroke keyStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK);
   KeyStroke keyStroke3 = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
   KeyStroke keyStroke4 = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK);
   KeyStroke keyStroke5 = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK);
   // Asigna el atajo de teclado al JMenuItem
   jMenuItem9.setAccelerator(keyStroke1);
   jMenuItem10.setAccelerator(keyStroke2);
   jMenuItem7.setAccelerator(keyStroke3);
   JMenuCliente.setAccelerator(keyStroke4);
   jConsultaslab.setAccelerator(keyStroke5);
  }  
    
    

    public void removeFocus(){
    
     JLabelNombre.setFocusable(false);
     TxtUser.setFocusable(false);
     TxtRol.setFocusable(false);
     TxtRol1.setFocusable(false);
     Txtentrada.setFocusable(false);
    
    
    }
    
private static void updateLastLogin(int userId) {
    // Usamos try-with-resources para manejar la conexión y el PreparedStatement
    String sql = "UPDATE table_usuario SET Fecha=?, Hora=?, online=? WHERE IdPersonal=?";
    
    try (Connection con = EnlaceBd.getConnection();  // La conexión se gestiona automáticamente
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Obtener fecha y hora actuales
        java.sql.Date fechaU = new java.sql.Date(System.currentTimeMillis());
        LocalTime hora = LocalTime.now();
        
        // Establecer los parámetros en el PreparedStatement
        ps.setDate(1, fechaU);
        ps.setString(2, hora.toString());
        ps.setInt(3, 0);  // Establecer 'online' en 0
        ps.setInt(4, userId);  // Establecer el IdPersonal del usuario
        
        // Ejecutar la actualización en la base de datos
        ps.executeUpdate();

    } catch (SQLException e) {
        System.err.println("Error al actualizar último inicio de sesión: " + e.getMessage());
        e.printStackTrace();
    }
}





 public void resizable() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Configura el tamaño inicial de la ventana (dos tercios del tamaño de la pantalla)
        setSize(screenWidth * 2 / 3, screenHeight * 2 / 3);

        // Verifica la resolución de la pantalla
        if (screenWidth <= 1280 && screenHeight <= 768) {
            // Si la resolución es menor o igual a 1280x720, la ventana será redimensionable
            setResizable(true);
        } else {
            // Si la resolución es mayor a 1280x720, la ventana no será redimensionable
            setResizable(false);
            
        }
        
    }

  
  
  
  

  Encriptar encriptar = new Encriptar();  
  public int  usuario;
  String hora, minutos, segundos, ampm;
  Thread h1;
  public String UserNombre;
  public int IdUsuario;

  RespaldoBd  instanciaRespaldo = RespaldoBd.getInstance(); 
  RestauracionBd instanciaRestauracion = RestauracionBd.getInstance(); 
  Temporal Tempo = new Temporal();
  
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Aboutme;
    public javax.swing.JButton BtnMensaje;
    public javax.swing.JLabel FechaAc;
    public com.toedter.calendar.JDateChooser FechaAdmin;
    public javax.swing.JTextField IDEspecialidad;
    public javax.swing.JTextField IDUSER;
    public javax.swing.JMenuItem JConsultPersonal;
    public javax.swing.JDesktopPane JDesktopMenu;
    public javax.swing.JMenuItem JEmpresa;
    public javax.swing.JTextField JLabelNombre;
    private javax.swing.JMenuItem JMPermisologia;
    public javax.swing.JTabbedPane JMenu;
    public javax.swing.JMenuItem JMenuCliente;
    public javax.swing.JMenu JMreportes;
    public javax.swing.JMenuItem JMuser;
    public javax.swing.JLabel Time;
    public javax.swing.JTextField TxtRol;
    public javax.swing.JTextField TxtRol1;
    public javax.swing.JTextField TxtUser;
    public javax.swing.JTextField Txtentrada;
    public javax.swing.JTextArea Txtinfo;
    public javax.swing.JMenuItem jCfacturas;
    public javax.swing.JMenuItem jCompras;
    public javax.swing.JMenuItem jConsPac;
    public javax.swing.JLabel jLabel102;
    public javax.swing.JLabel jLabel104;
    public javax.swing.JLabel jLabel106;
    public javax.swing.JLabel jLabel107;
    public javax.swing.JLabel jLabel108;
    public javax.swing.JLabel jLabel109;
    public javax.swing.JLabel jLabel95;
    public javax.swing.JLabel jLabel98;
    public javax.swing.JMenu jMenAdmin;
    public javax.swing.JMenuItem jMenCtgPrd;
    public javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    public javax.swing.JMenu jMenu8;
    public javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenu jMenuConfiguracion;
    public javax.swing.JMenuItem jMenuDivisa;
    public javax.swing.JMenuItem jMenuFactura;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    public javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JMenuItem jMenuPassword;
    public javax.swing.JMenu jMenuProcesos;
    public javax.swing.JMenu jMenuRegistros;
    public javax.swing.JMenuItem jMenuRestauracion;
    public javax.swing.JMenu jMenuSeguridad;
    public javax.swing.JMenu jMenuServicios;
    public javax.swing.JMenuItem jMinventario;
    public javax.swing.JMenuItem jOnline;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JMenuItem jProveedores;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    // End of variables declaration//GEN-END:variables

    
   public void run() {
    Thread current = Thread.currentThread();

    while (current == h1) {
        hora();
   int horaInt = Integer.parseInt(hora);
   int minutosInt = Integer.parseInt(minutos);
   int segundosInt = Integer.parseInt(segundos);

   String tiempo = String.format("%02d:%02d:%02d", horaInt, minutosInt, segundosInt);
        Time.setText(tiempo);
        
        int hour = Integer.parseInt(hora);

        if (hour < 12) {
            jLabel95.setText("Buenos días");
            jLabel102.setText("AM");
        } else if (hour >= 12 && hour < 19) {
            jLabel95.setText("Buenas tardes");
            jLabel102.setText("PM");
        } else {
            jLabel95.setText("Buenas noches");
            jLabel102.setText("PM");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Manejar la interrupción
            e.printStackTrace();
        }
    }
}
}
