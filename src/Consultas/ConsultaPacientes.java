/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JPacientesDao;
import Clases.PdfDAO1;
import Clases.PdfVO;
import Clases.Temporal;
import Clases.Validar;
import Clases.imgTabla;
import Menu.Mprincipal;
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
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
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
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class ConsultaPacientes extends javax.swing.JInternalFrame {

 
    
    
    
    private Thread searchThread;
    private final int DELAY = 500;
    public ConsultaPacientes() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        listarPacientes();
        informacionpdf();
        if(JTablePaciente.getRowCount()>0){  acomodarceldas(); }
        this.JRCED.setSelected(true);


       
        
        
        
        conteoTabla();
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
        }   finally {
            closeResources(rs, ps, con);
        } 
    }
    
     
 
    
    
    
    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        TXTBUSCAR = new javax.swing.JTextField();
        JRCODIGO = new javax.swing.JRadioButton();
        JRNOMBRE = new javax.swing.JRadioButton();
        JRCED = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablePaciente = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setMinimumSize(new java.awt.Dimension(1285, 663));
        setPreferredSize(new java.awt.Dimension(1292, 683));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Exportar Tabla");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("C O N S U L T A S          C L I E N T E S");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar por:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTBUSCAR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TXTBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBUSCARKeyReleased(evt);
            }
        });
        jPanel2.add(TXTBUSCAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 230, 50));

        JRCODIGO.setBackground(new java.awt.Color(255, 255, 255));
        JRCODIGO.setText("Código");
        JRCODIGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRCODIGOActionPerformed(evt);
            }
        });
        jPanel2.add(JRCODIGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        JRNOMBRE.setBackground(new java.awt.Color(255, 255, 255));
        JRNOMBRE.setText("Nombre");
        JRNOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRNOMBREActionPerformed(evt);
            }
        });
        jPanel2.add(JRNOMBRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        JRCED.setBackground(new java.awt.Color(255, 255, 255));
        JRCED.setText("Cedula");
        JRCED.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRCEDActionPerformed(evt);
            }
        });
        jPanel2.add(JRCED, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, 70, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1290, 100));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 50));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTablePaciente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JTablePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Apellido", "Cédula", "Telefono", "Correo", "Dirección", "Sexo", "Fec_Nacimiento"
            }
        ));
        JTablePaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablePacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTablePaciente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1290, 460));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel3.setText("0");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel5.setText("Total pacientes:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     Temporal Tempo = new Temporal();
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed




         String Nivel=Tempo.getNivel();
         String especialidad= Tempo.getEspecialidad();
         String nivelUsuario=Nivel+" "+especialidad;
      
         if(Nivel.equals("Administrador") || especialidad.equals("Consultor")){
         pdf();
         AuditoriaReporte();
         }else{
            JOptionPane.showMessageDialog(null, "PERMISOS INSUFICIENTES", "PERMISOS", JOptionPane.ERROR_MESSAGE);
         }

  
  
  
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
   
          
          public void AuditoriaReporte(){
            
   int idusuario=TM.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Genero un reporte de la tabla pacientes " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
       finally {
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
     
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void JTablePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePacienteMouseClicked
      
    }//GEN-LAST:event_JTablePacienteMouseClicked

    private void JRCEDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRCEDActionPerformed
        this.JRCED.setSelected(true);
        JRNOMBRE.setSelected(false);
        JRCODIGO.setSelected(false);
        TXTBUSCAR.setText("");
    }//GEN-LAST:event_JRCEDActionPerformed

    private void JRNOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRNOMBREActionPerformed
        this.JRNOMBRE.setSelected(true);
        JRCED.setSelected(false);
        JRCODIGO.setSelected(false);
        TXTBUSCAR.setText("");
    
    }//GEN-LAST:event_JRNOMBREActionPerformed

    private void JRCODIGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRCODIGOActionPerformed
         this.JRCODIGO.setSelected(true);
        JRCED.setSelected(false);
        JRNOMBRE.setSelected(false);
         TXTBUSCAR.setText("");
    }//GEN-LAST:event_JRCODIGOActionPerformed

    private void TXTBUSCARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBUSCARKeyReleased

    if (searchThread != null && searchThread.isAlive()) {
    searchThread.interrupt();
}

// Crea un nuevo hilo de búsqueda
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

// Inicia el hilo de búsqueda
searchThread.start();

   
    }//GEN-LAST:event_TXTBUSCARKeyReleased


     private void realizarBusqueda() {
    if (JRCED.isSelected()) {
        if (TXTBUSCAR.getText().length() >= 4 ) {
            SearchbyCed();
            acomodarceldas();
        }
        else if (TXTBUSCAR.getText().isEmpty()){
            limpiarTabla();
            listarPacientes();
            conteoTabla();
        }
        
        
        
    } else if (JRNOMBRE.isSelected()) {
        SearchbyNombre();
    } else if (JRCODIGO.isSelected()) {
        SearchbyCodigo();
    }

    if (JTablePaciente.getRowCount() > 0) {
        acomodarceldas();
    }
}
    
      
       
      public void SearchbyCed(){
         
          
          
                      
       
          
          
          
          
          
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
 String Busqueda = this.TXTBUSCAR.getText();
 String sql = "select Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo, date_format(FechNacimiento, '%d/%m/%Y') AS Fecha  from table_paciente where Cedula LIKE '%" + Busqueda + "%' ";
        
        






//DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
        JTablePaciente.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTabla();

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
               String col9 = rs.getString(9);
               
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

               Tabla.addRow(vRow);
               conteoTabla();
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }finally {
            closeResources(rs, ps, con);
        }

  }
    
    
          
      public void SearchbyNombre(){
         
     Connection con=null;
     EnlaceBd cn = new EnlaceBd();
     PreparedStatement ps=null;
     ResultSet rs=null;
   
     String Busqueda = this.TXTBUSCAR.getText();
     String sql = "select Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo, date_format(FechNacimiento, '%d/%m/%Y') AS Fecha  from table_paciente where Nombre LIKE '%" + Busqueda + "%' ";
        
     //DECLARACIÓN DEL MODELO DE LA TABLA
     DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
     JTablePaciente.setDefaultEditor(Object.class, null);
     try
         
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTabla();

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
               String col9 = rs.getString(9);
               
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

               Tabla.addRow(vRow);
               conteoTabla();
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }finally {
            closeResources(rs, ps, con);
        }

  }
    
    
      
       
  
    
         
      public void SearchbyCodigo(){
         
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
    String Busqueda = this.TXTBUSCAR.getText();
    String sql = "select Idpaciente, Nombre, Apellido, Cedula, Telefono, Correo, Direccion, Sexo, date_format(FechNacimiento, '%d/%m/%Y') AS Fecha  from table_paciente where Idpaciente LIKE '%" + Busqueda + "%' ";
        
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
        JTablePaciente.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            limpiarTabla();

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
               String col9 = rs.getString(9);
               
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

               Tabla.addRow(vRow);
               conteoTabla();
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }finally {
            closeResources(rs, ps, con);
        }

  }
    
    

      
      
             public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) JTablePaciente.getModel();
        int a = JTablePaciente.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);

        }
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
    }
    
    
         
       public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTablePaciente.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTablePaciente.setRowHeight(20);
        
      
        
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Código")).setPreferredWidth(10);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Nombre")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Apellido")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Cédula")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Correo")).setPreferredWidth(110);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Dirección")).setPreferredWidth(200);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Sexo")).setPreferredWidth(30);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Fec_Nacimiento")).setPreferredWidth(30);
         
        
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Código")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Nombre")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Apellido")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Cédula")).setCellRenderer(Alinear);;
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Telefono")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Correo")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Dirección")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Sexo")).setCellRenderer(Alinear);
        JTablePaciente.getColumnModel().getColumn(Tabla.findColumn("Fec_Nacimiento")).setCellRenderer(Alinear);
    
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
            File file = new File("C:\\Cyberia\\Reportes\\" + PdfNames + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.open();
            doc.setMargins(36, 36, 110, 130);
                    
          
       


            Paragraph fecha = new Paragraph();
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(Menu.FechaAdmin.getDate());
            String Hora = Menu.Time.getText()+" "+Menu.jLabel102.getText();
    

            
            
       
           
       
                        // Cargar la imagen
com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Cyberia\\src\\imagenes\\Logo_System.png");

// Escalar a un tamaño adecuado sin deformar
header.scaleToFit(120, 80); // Puedes ajustar según el diseño

// Obtener el alto del documento
float pageHeight = doc.getPageSize().getHeight();

// Posicionar en la esquina superior izquierda con margen
float marginLeft = 30f; // distancia desde el borde izquierdo
float marginTop = 30f;  // distancia desde el borde superior

// Posicionar imagen desde la esquina izquierda, bajándola un poco para no pegarla al borde superior
header.setAbsolutePosition(marginLeft, pageHeight - header.getScaledHeight() - marginTop);

// Agregar la imagen
doc.add(header);
            
            
            

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
            CB.setTextMatrix(168, 725);
            CB.showText("__________________________________________________________________");
          
            CB.endText();

        
       //BODY 
       
       
        
            //productos
          
            
            
         
            
            
            PdfPTable  tablapro = new PdfPTable(5);
            
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            tablapro.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 3f,3f,4f};
            tablapro.setWidths(medidaCeldas);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("CÓDIGO");
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna1);
        
          
            Paragraph tcolumna2 = new Paragraph("NOMBRE");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(10);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna2);
        
            Paragraph tcolumna3 = new Paragraph("CEDULA");
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(10);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna3);
            
            Paragraph tcolumna4 = new Paragraph("TELEFONO");
            tcolumna4.getFont().setStyle(Font.BOLD);
            tcolumna4.getFont().setSize(10);        
            tcolumna4.setFont(Letra);
            tcolumna4.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna4);
            
            Paragraph tcolumna5 = new Paragraph("CORREO");
            tcolumna5.getFont().setStyle(Font.BOLD);
            tcolumna5.getFont().setSize(10);        
            tcolumna5.setFont(Letra);
            tcolumna5.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna5);
            

            

            for (int i = 0; i < JTablePaciente.getRowCount(); i++) {
               
                String Codigo = JTablePaciente.getValueAt(i, 0).toString();
                String Nombre = JTablePaciente.getValueAt(i, 1).toString() + " " + JTablePaciente.getValueAt(i, 2).toString() ;
                String Cedula = JTablePaciente.getValueAt(i, 3).toString();
                String Telefono = JTablePaciente.getValueAt(i, 4).toString(); 
                String Correo = JTablePaciente.getValueAt(i, 5).toString(); 
          
        
                tablapro.addCell(new Paragraph(Codigo,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Nombre,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Cedula,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Telefono,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Correo,FontFactory.getFont("Arial",8,Font.NORMAL))); 
    
       
            }

            doc.add(saltolinea);
            doc.add(saltolinea);
            doc.add(tablapro);
         
            
            
   
         
   

         
            //FOOTER
        addFooter(writer);
            
            
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

        
        String User=TM.getUser();
        
        
        footer.setWidths(new int[]{100});
        footer.setTotalWidth(527);
        footer.setLockedWidth(false);
        footer.getDefaultCell().setFixedHeight(40);
        footer.getDefaultCell().setBorder(Rectangle.TOP);
        footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // add current page count
        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        footer.addCell(new Phrase(String.format("Emitido por:"+User+"                                             |    www.fundaginebra.org  |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

        // write page
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footer.writeSelectedRows(0, -1, 34, 50, canvas);
        canvas.endMarkedContentSequence();
    } catch(DocumentException de) {
        throw new ExceptionConverter(de);
   
    }
}
     
     
     
       
       
       
       
       public void conteoTabla(){
       
       for(int x =0; x<=JTablePaciente.getRowCount(); x++ ){
       jLabel3.setText(""+x);
       
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
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JRCED;
    private javax.swing.JRadioButton JRCODIGO;
    private javax.swing.JRadioButton JRNOMBRE;
    private javax.swing.JTable JTablePaciente;
    private javax.swing.JTextField TXTBUSCAR;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
