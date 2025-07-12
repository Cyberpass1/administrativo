/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Auditoria;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCAuditoria;
import Clases.JCAuditoriaDao;
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
import java.awt.event.KeyEvent;
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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JAuditoria extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JAuditoria() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();

        FechaOne.setCalendar(Fecha);
        FechaTwo.setCalendar(Fecha);
        limpiarTabla();
        listarAuditoria();
        acomodarceldas();
        this.JRUSUARIO.setSelected(true);
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        j13 = new javax.swing.JLabel();
        FechaTwo = new com.toedter.calendar.JDateChooser();
        FechaOne = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        JRPERSONAL = new javax.swing.JRadioButton();
        JRUSUARIO = new javax.swing.JRadioButton();
        j14 = new javax.swing.JLabel();
        JRPERSONAL1 = new javax.swing.JRadioButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        Jtabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        j13.setBackground(new java.awt.Color(0, 0, 0));
        j13.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        j13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j13.setText("EXPORTAR TABLA");
        j13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        j13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j13MouseClicked(evt);
            }
        });
        jPanel2.add(j13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, -1, 30));

        FechaTwo.setBackground(new java.awt.Color(255, 255, 255));
        FechaTwo.setToolTipText("");
        FechaTwo.setDateFormatString("yyyy-MM-dd");
        FechaTwo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 170, 50));

        FechaOne.setBackground(new java.awt.Color(255, 255, 255));
        FechaOne.setToolTipText("");
        FechaOne.setDateFormatString("yyyy-MM-dd");
        FechaOne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.add(FechaOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 170, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgBotones/magnifier-1_icon-icons.com_56924.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 70, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 70, 50));

        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 270, 50));

        JRPERSONAL.setBackground(new java.awt.Color(255, 255, 255));
        JRPERSONAL.setText("Personal");
        JRPERSONAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRPERSONALActionPerformed(evt);
            }
        });
        jPanel2.add(JRPERSONAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        JRUSUARIO.setBackground(new java.awt.Color(255, 255, 255));
        JRUSUARIO.setText("Usuario");
        JRUSUARIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRUSUARIOActionPerformed(evt);
            }
        });
        jPanel2.add(JRUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        j14.setBackground(new java.awt.Color(0, 0, 0));
        j14.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        j14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j14.setText("SORTEAR POR FECHAS");
        j14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        j14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                j14MouseClicked(evt);
            }
        });
        jPanel2.add(j14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, 30));

        JRPERSONAL1.setBackground(new java.awt.Color(255, 255, 255));
        JRPERSONAL1.setText("Acción");
        JRPERSONAL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRPERSONAL1ActionPerformed(evt);
            }
        });
        jPanel2.add(JRPERSONAL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1280, 110));

        Jtabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Usuario", "Personal", "Fecha", "Accion"
            }
        ));
        jScrollPane8.setViewportView(Jtabla);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1280, 480));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void j13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_j13MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SearchBetweenDates(); acomodarceldas();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pdf();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        if(JRUSUARIO.isSelected()){    Searchbyuser();  acomodarceldas(); }
        else if(JRPERSONAL.isSelected()){    Searchbyname();   acomodarceldas();}
        else if(JRPERSONAL1.isSelected()){     SearchbyAccion();   acomodarceldas();}

    }//GEN-LAST:event_jTextField1KeyReleased

    private void JRPERSONALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRPERSONALActionPerformed

        this.JRPERSONAL.setSelected(true);
        JRUSUARIO.setSelected(false);
        JRPERSONAL1.setSelected(false);
        jTextField1.setText("V-");
       
    }//GEN-LAST:event_JRPERSONALActionPerformed

    private void JRUSUARIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRUSUARIOActionPerformed
        this.JRUSUARIO.setSelected(true);
        JRPERSONAL.setSelected(false);
        JRPERSONAL1.setSelected(false);
        jTextField1.setText("");

    }//GEN-LAST:event_JRUSUARIOActionPerformed

    private void j14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_j14MouseClicked

    private void JRPERSONAL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRPERSONAL1ActionPerformed
        this.JRPERSONAL1.setSelected(true);
        JRPERSONAL.setSelected(false);
        JRUSUARIO.setSelected(false);
        jTextField1.setText("");
    }//GEN-LAST:event_JRPERSONAL1ActionPerformed

    

 
      public void SearchBetweenDates(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Codigo");
        dt.addColumn("Usuario");
        dt.addColumn("Personal");
        dt.addColumn("Fecha");
        dt.addColumn("Accion");
    
        Auditoria vo = new Auditoria();
        ArrayList<Auditoria> list =ListarBetween_PdfVO();
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getUsuario();
                fila[2] = vo.getPersonal();
                fila[3] = vo.getFecha();
                fila[4] = vo.getAccion();
                
      

                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
    
         
     
     
       
    public ArrayList<Auditoria> ListarBetween_PdfVO() {
       
        
        
        
 String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
 String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
        
        
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
        
        ArrayList<Auditoria> list = new ArrayList<Auditoria>();
   
        String sql = "SELECT  Idauditoria, Usuario, Cedula, FechaMov, Accion \n" +
"\n" +
"FROM table_auditoria u  \n" +
"\n" +
"INNER JOIN table_personal w\n" +
"ON u.IdPersonal= w.IdPersonal\n" +
"\n" +
"INNER JOIN table_usuario p\n" +
"ON u.IdUsuario= p.IdPersonal WHERE FechaMov BETWEEN " + '"' + fecha + '"' + "AND"  + '"' + fecha2 + '"';
   
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                Auditoria vo = new Auditoria();
                vo.setCodigopdf(rs.getInt(1));
                vo.setUsuario(rs.getString(2));
                vo.setPersonal(rs.getString(3));
                vo.setFecha(rs.getString(4));
                vo.setAccion(rs.getString(5));
  
                list.add(vo);
            }
            
          
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    finally {
            closeResources(rs, ps, con);
        }
             //   ArrayList<PdfVO> list = new ArrayList<PdfVO>();
        return list;
    }
    
    
     
    public void acomodarceldas()
    {
    
        Jtabla.setRowHeight(32);
        DefaultTableModel Tabla = (DefaultTableModel)Jtabla.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Codigo")).setPreferredWidth(20);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Personal")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setPreferredWidth(60);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Accion")).setPreferredWidth(550);
   

         
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Codigo")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Usuario")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Personal")).setCellRenderer(Alinear);;
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Fecha")).setCellRenderer(Alinear);
        Jtabla.getColumnModel().getColumn(Tabla.findColumn("Accion")).setCellRenderer(Alinear);
     

    
    
    }
            
            
            
        
            void limpiarTablaEstudios() {
        DefaultTableModel tb = (DefaultTableModel) Jtabla.getModel();
        int a = Jtabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);

        }
    }

    
    
    
    
     public void listarAuditoria()
{

   
    
    
    
    
 List<JCAuditoria> lista = listarAudit();
        modelo = (DefaultTableModel) Jtabla.getModel();
        Object[] ob = new Object[7];

        for (int i = 0; i < lista.size(); i++) {


            ob[0] = lista.get(i).getIdAudit();
            ob[1] = lista.get(i).getIdUser();
            ob[2] = lista.get(i).getIdPersonal();
            ob[3] = lista.get(i).getFecha();
            ob[4] = lista.get(i).getAccion();
            modelo.addRow(ob);

        }

     Jtabla.setModel(modelo);
     Jtabla.setDefaultEditor(Object.class, null);  
     DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) Jtabla.getTableHeader().getDefaultRenderer();
     headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
}
    
    
    DefaultTableModel modelo = new DefaultTableModel();
    JCAuditoriaDao Audit = new JCAuditoriaDao();
    
    
        
     
            
   void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
   
    
   
   
   
    
   
    public List listarAudit() {
                
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
                
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;    
        
        List<JCAuditoria> lista = new ArrayList<>();
        String sql = "SELECT  Idauditoria, Usuario, Cedula, FechaMov, Accion \n" +
"\n" +
"FROM table_auditoria u  \n" +
"\n" +
"INNER JOIN table_personal w\n" +
"ON u.IdPersonal= w.IdPersonal\n" +
"\n" +
"INNER JOIN table_usuario p\n" +
"ON u.IdUsuario= p.IdPersonal WHERE FechaMov BETWEEN " + '"' + fecha + '"' + "AND"  + '"' + fecha2 + '"';
        
    
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
               JCAuditoria p = new JCAuditoria();
                p.setIdAudit(rs.getInt(1));
                p.setIdUser(rs.getString(2));
                p.setIdPersonal(rs.getString(3));
                p.setFecha(rs.getString(4));
                p.setAccion(rs.getString(5));

          
             
     
                lista.add(p);
            }
        } catch (Exception e) {System.err.println("Error al conectar "+ e);
        }
        
          finally {
            closeResources(rs, ps, con);
        }
        return lista;
    }
    
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
     
     public void pdf() {
      try {
       
          
          
          
                   
        /*-------------------------------------------------------------------------------------*/
         
           /* System.out.println("Hoy es :"+dia_actual+" "+ mes_actual+" "+an_actual);*/
            
            

        
      /*      
                
            System.out.println("Fecha de nacimiento :"+dia1+" "+ mes1+" "+an1);
           
            
            
          System.out.println("Dia" +dia_actual+"- " +dia1 +" ="+diferenciadia);
          System.out.println("Mes" +mes_actual+"- " +mes1 +"= "+diferenciames);  
          System.out.println("Año" +an_actual+"- " +an1 +"= "+diferencian);   
            
            
          System.out.println("Tu cumpleaños es :"+diferenciadia+" "+ diferenciames+" "+diferencian); */
 
          
          
          
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          

         
 
    
        
                   
            String   PdfNames="ReporteAuditoria"+"_"+fth.format(fechaactual); 
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            File file = new File("C://Fundaginebra//Reportes//"+PdfNames+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("C:\\Fundaginebra\\src\\imagenes\\Fundacionlogo1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(MP.FechaAdmin.getDate());
            String Hora = MP.Time.getText()+" "+MP.jLabel102.getText();
            //fecha.add( "  Fecha: " + Fecha + "\n" +"  Hora: "+ Hora ) ;

            
            
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
           
          
            
            
            
            

            PdfContentByte CB = writer.getDirectContent();  
            BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
  
            CB.beginText();
        

            CB.setFontAndSize(BF2, 12);
            CB.setTextMatrix(200, 800);
            CB.showText("FUNDACIÓN CONVENIO DE GINEBRA I");
            CB.setFontAndSize(BF, 10);
            CB.setTextMatrix(260, 790);
            CB.showText("  RIF J-31258512-9  ");
            
            
            CB.setFontAndSize(BF2, 10);
            CB.setTextMatrix(460, 780);
            CB.showText("FECHA: "+ Fecha);
            
            CB.setFontAndSize(BF2, 10);
            CB.setTextMatrix(460, 770);
            CB.showText("HORA: "+ Hora);
            
            CB.setFontAndSize(BF2, 8);
            CB.setTextMatrix(250, 740);
            CB.showText("N° 9, Calle Mariño Sur, Maracay 2103, Aragua");
          
            CB.setFontAndSize(BF2, 8);
            CB.setTextMatrix(180, 730);
            CB.showText("Teléfonos: 0426-2407263 | 0243-2468726 | Correo: info@fundaginebra.com "); 
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
            float[] medidaCeldas = {1f, 3f, 2f,2f,7f};
            tablapro.setWidths(medidaCeldas);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
              Paragraph tcolumna1 = new Paragraph("CÓD");
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna1);
        
          
            Paragraph tcolumna2 = new Paragraph("USUARIO");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(10);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna2);
        
            Paragraph tcolumna3 = new Paragraph("PERSONAL");
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(10);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna3);
            
            Paragraph tcolumna4 = new Paragraph("FECHA");
            tcolumna4.getFont().setStyle(Font.BOLD);
            tcolumna4.getFont().setSize(10);        
            tcolumna4.setFont(Letra);
            tcolumna4.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna4);
            
            Paragraph tcolumna5 = new Paragraph("ACCIÓN");
            tcolumna5.getFont().setStyle(Font.BOLD);
            tcolumna5.getFont().setSize(10);        
            tcolumna5.setFont(Letra);
            tcolumna5.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna5);
            
            

            for (int i = 0; i < Jtabla.getRowCount(); i++) {
               
                String Codigo = Jtabla.getValueAt(i, 0).toString();
                String Examen = Jtabla.getValueAt(i, 1).toString();
                String Paciente = Jtabla.getValueAt(i, 2).toString();
                String Usuario = Jtabla.getValueAt(i, 3).toString(); 
                String FechaR = Jtabla.getValueAt(i, 4).toString(); 
          
        
                tablapro.addCell(new Paragraph(Codigo,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Examen,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Paciente,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(Usuario,FontFactory.getFont("Arial",8,Font.NORMAL))); 
                tablapro.addCell(new Paragraph(FechaR,FontFactory.getFont("Arial",8,Font.NORMAL))); 
    
       
            }
            doc.add(Encabezado);
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
  String User=Tempo.getUser();
        footer.setWidths(new int[]{100});
        footer.setTotalWidth(527);
        footer.setLockedWidth(false);
        footer.getDefaultCell().setFixedHeight(40);
        footer.getDefaultCell().setBorder(Rectangle.TOP);
        footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // add current page count
        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        footer.addCell(new Phrase(String.format("Emitido por:"+ User+"                                          |    www.fundaginebra.org    |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

        // write page
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footer.writeSelectedRows(0, -1, 34, 50, canvas);
        canvas.endMarkedContentSequence();
    } catch(DocumentException de) {
        throw new ExceptionConverter(de);
   
    }
}
     
     
     
     
     
    
       
      public void Searchbyuser(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Codigo");
        dt.addColumn("Usuario");
        dt.addColumn("Personal");
        dt.addColumn("Fecha");
        dt.addColumn("Accion");


       

        Auditoria vo = new Auditoria();
        ArrayList<Auditoria> list = ListarbyUser_PdfVO();
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getUsuario();
                fila[2] = vo.getPersonal();
                fila[3] = vo.getFecha();
                fila[4] = vo.getAccion();
  

                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
     
       
      public void Searchbyname(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Codigo");
        dt.addColumn("Usuario");
        dt.addColumn("Personal");
        dt.addColumn("Fecha");
        dt.addColumn("Accion");


       

        Auditoria vo = new Auditoria();
        ArrayList<Auditoria> list = Listarbyname_PdfVO();
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getUsuario();
                fila[2] = vo.getPersonal();
                fila[3] = vo.getFecha();
                fila[4] = vo.getAccion();
  

                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
     
     
     
     
     
    
     
    public ArrayList<Auditoria> Listarbyname_PdfVO() {
       
         Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
        
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    
        ArrayList<Auditoria> list = new ArrayList<Auditoria>();
   
 String sql = "SELECT Idauditoria, Usuario, Cedula, FechaMov, Accion " +
             "FROM table_auditoria u " +
             "INNER JOIN table_personal w " +
             "ON u.IdPersonal = w.IdPersonal " +
             "INNER JOIN table_usuario p " +
             "ON u.IdUsuario = p.IdPersonal " +
             "WHERE Cedula LIKE '%" + jTextField1.getText() + "%' " +
             "AND FechaMov BETWEEN '" + fecha + "' AND '" + fecha2 + "' " +
             "ORDER BY Idauditoria ASC";
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
               Auditoria vo = new Auditoria();
                vo.setCodigopdf(rs.getInt(1));
                vo.setUsuario(rs.getString(2));
                vo.setPersonal(rs.getString(3));
                vo.setFecha(rs.getString(4));
                vo.setAccion(rs.getString(5));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    finally {
            closeResources(rs, ps, con);
        }
        return list;
    }
     
     
     
    public ArrayList<Auditoria> ListarbyUser_PdfVO() {
       
         Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
        
    
    
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
        
        ArrayList<Auditoria> list = new ArrayList<Auditoria>();
   
String sql = "SELECT Idauditoria, Usuario, Cedula, FechaMov, Accion " +
             "FROM table_auditoria u " +
             "INNER JOIN table_personal w " +
             "ON u.IdPersonal = w.IdPersonal " +
             "INNER JOIN table_usuario p " +
             "ON u.IdUsuario = p.IdPersonal " +
             "WHERE Usuario LIKE '%" + jTextField1.getText() + "%' " +
             "AND FechaMov BETWEEN '" + fecha + "' AND '" + fecha2 + "' " +
             "ORDER BY Idauditoria ASC";
try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
               Auditoria vo = new Auditoria();
                vo.setCodigopdf(rs.getInt(1));
                vo.setUsuario(rs.getString(2));
                vo.setPersonal(rs.getString(3));
                vo.setFecha(rs.getString(4));
                vo.setAccion(rs.getString(5));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    finally {
            closeResources(rs, ps, con);
        }
        return list;
    }
     
     
    
    
    
     public void SearchbyAccion(){
              
   try {
        
        
        Jtabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Codigo");
        dt.addColumn("Usuario");
        dt.addColumn("Personal");
        dt.addColumn("Fecha");
        dt.addColumn("Accion");


       

        Auditoria vo = new Auditoria();
        ArrayList<Auditoria> list = ListarbyAccion();
limpiarTablaEstudios();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getUsuario();
                fila[2] = vo.getPersonal();
                fila[3] = vo.getFecha();
                fila[4] = vo.getAccion();
  

                dt.addRow(fila);
            }
            Jtabla.setModel(dt);
            Jtabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }


  }
     
     
     
     
     
    
     
    public ArrayList<Auditoria> ListarbyAccion() {
       
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(FechaOne.getDate());
    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(FechaTwo.getDate());
    
        ArrayList<Auditoria> list = new ArrayList<Auditoria>();
   
String sql = "SELECT Idauditoria, Usuario, Cedula, FechaMov, Accion " +
             "FROM table_auditoria u " +
             "INNER JOIN table_personal w " +
             "ON u.IdPersonal = w.IdPersonal " +
             "INNER JOIN table_usuario p " +
             "ON u.IdUsuario = p.IdPersonal " +
             "WHERE Accion LIKE '%" + jTextField1.getText() + "%' " +
             "AND FechaMov BETWEEN '" + fecha + "' AND '" + fecha2 + "' " +
             "ORDER BY Idauditoria ASC";
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
               Auditoria vo = new Auditoria();
                vo.setCodigopdf(rs.getInt(1));
                vo.setUsuario(rs.getString(2));
                vo.setPersonal(rs.getString(3));
                vo.setFecha(rs.getString(4));
                vo.setAccion(rs.getString(5));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    finally {
            closeResources(rs, ps, con);
        }
        return list;
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
  
    
    
    
    
    
     
     Mprincipal MP = new Mprincipal();
     Temporal Tempo = new Temporal();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JDateChooser FechaOne;
    public com.toedter.calendar.JDateChooser FechaTwo;
    private javax.swing.JRadioButton JRPERSONAL;
    private javax.swing.JRadioButton JRPERSONAL1;
    private javax.swing.JRadioButton JRUSUARIO;
    private javax.swing.JTable Jtabla;
    public javax.swing.JLabel j13;
    public javax.swing.JLabel j14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
