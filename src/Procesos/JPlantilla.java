/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Registros.*;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.Validar;
import Menu.Mprincipal;

import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author FCGinebraI
 */
public class JPlantilla extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JPlantilla() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
      
   
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        JRpaciente = new javax.swing.JRadioButton();
        jRusuario = new javax.swing.JRadioButton();
        jRcodigo = new javax.swing.JRadioButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Cancelar");
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1290, 70));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 30));

        jLabel1.setText("paciente");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel2.setText("Control paciente: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel9.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 100));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/blur-hospital.jpg"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 460));

        jTabbedPane2.addTab("Inicio", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane2.addTab("Procesos", jPanel5);

        jPanel9.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 890, 490));

        jTabbedPane1.addTab("tab1", jPanel9);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 910, 610));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Nombre", "C.I", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 360, 490));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 360, 30));

        JRpaciente.setBackground(new java.awt.Color(255, 255, 255));
        JRpaciente.setText("Paciente");
        JRpaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRpacienteActionPerformed(evt);
            }
        });
        jPanel1.add(JRpaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jRusuario.setBackground(new java.awt.Color(255, 255, 255));
        jRusuario.setText("Usuario");
        jRusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRusuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jRusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jRcodigo.setBackground(new java.awt.Color(255, 255, 255));
        jRcodigo.setText("Código");
        jRcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRcodigoActionPerformed(evt);
            }
        });
        jPanel1.add(jRcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JRpacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRpacienteActionPerformed
        this.JRpaciente.setSelected(true);
        jRusuario.setSelected(false);
        jRcodigo.setSelected(false);
        jTextField1.setText("V-");

    }//GEN-LAST:event_JRpacienteActionPerformed

    private void jRusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRusuarioActionPerformed

        this.jRusuario.setSelected(true);
        JRpaciente.setSelected(false);
        jRcodigo.setSelected(false);
        jTextField1.setText("");

    }//GEN-LAST:event_jRusuarioActionPerformed

    private void jRcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRcodigoActionPerformed
        this.jRcodigo.setSelected(true);
        JRpaciente.setSelected(false);
        jRusuario.setSelected(false);
        jTextField1.setText("");
    }//GEN-LAST:event_jRcodigoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    Mprincipal MP = new Mprincipal();
    
          public void pdf() {
      try {
       
          
          
          
          
           BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
           Font Letra = new Font(BF); 
          
          
          
          
          
          
          
          

        
            FileOutputStream archivo;
            String rut = System.getProperty("user.home");
            File file = new File("C://Fundaginebra//Reportes//Reporte_ALMACEN.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.open();
           
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("F:\\SoftwareJC\\src\\Logos\\Fundacionlogo1.png");
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
            fecha.add(Chunk.NEWLINE);
            SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String Fecha = FormatoFecha.format(MP.FechaAdmin.getDate());
            fecha.add( "    Fecha: " + Fecha + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{50f,30f, 90f, 50f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);


            String riff =   "J-8188418-8";
            String razon =  "Fundación Convenio de Ginbra I";
            String tlf =    "0000-000000";
            String dir =    "Centro";
    
        
            Encabezado.addCell(header);
            Encabezado.addCell("");
            Encabezado.addCell("\n" + razon + "\nRIF: " + riff + "\nTelefono:" + tlf + "\nDireccion: " + dir );
            Encabezado.addCell(fecha);
            
            
            doc.add(Encabezado);
          

            Paragraph cli = new Paragraph();
            cli.add("____________________________________________________________________________");
      //      cli.add(Chunk.NEWLINE);
      //      cli.add(Chunk.NEWLINE);
      //      cli.add("Reporte de Almacen");
      //      cli.add(Chunk.NEWLINE);
      //      cli.add("Emitido Por: " + MP.JLabelNombre.getText()+ "\n");
      //      cli.add(Chunk.NEWLINE);
              doc.add(cli);
            
      
       
            PdfPTable Tabla = new PdfPTable(3); 
            Tabla.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 3f, 1f };
            Tabla.setWidths(medidaCeldas);
            Tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
            Tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("N° DE ORDEN" );
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(8);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna1);
     
                
            Paragraph tcolumna2 = new Paragraph("RESULTADOS DEL ANALISIS");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(12);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            Tabla.addCell(tcolumna2);

            Paragraph tcolumna3 = new Paragraph("N° DE PACIENTE");
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
            
            Paragraph t0columna1 = new Paragraph("PACIENTE");
            t0columna1.getFont().setStyle(Font.BOLD);
            t0columna1.getFont().setSize(8);        
            t0columna1.setFont(Letra);
            t0columna1.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna1);
     
                
            Paragraph t0columna2 = new Paragraph("CÉDULA");
            t0columna2.getFont().setStyle(Font.BOLD);
            t0columna2.getFont().setSize(8);        
            t0columna2.setFont(Letra);
            t0columna2.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna2);

            Paragraph t0columna3 = new Paragraph("EDAD");
            t0columna3.getFont().setStyle(Font.BOLD);
            t0columna3.getFont().setSize(8);        
            t0columna3.setFont(Letra);
            t0columna3.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna3);
        
            Paragraph t0columna4 = new Paragraph("SEXO");
            t0columna4.getFont().setStyle(Font.BOLD);
            t0columna4.getFont().setSize(8);        
            t0columna4.setFont(Letra);
            t0columna4.setAlignment(Element.ALIGN_LEFT);
            Tabla0.addCell(t0columna4);
        
        
            
            
        
            Tabla0.addCell("Juan Calderón") ;  
            Tabla0.addCell("V-27434836"); 
            Tabla0.addCell("18"+" años"); 
            Tabla0.addCell("Masculino");

        
            
            
            
            
            
            
            
            
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");
            doc.add(saltolinea); 
            doc.add(Tabla);
        
            doc.add(Tabla0);
            doc.add(saltolinea);
           

         
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

        footer.setWidths(new int[]{100});
        footer.setTotalWidth(527);
        footer.setLockedWidth(false);
        footer.getDefaultCell().setFixedHeight(40);
        footer.getDefaultCell().setBorder(Rectangle.TOP);
        footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // add current page count
        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        footer.addCell(new Phrase(String.format("Fundación Convenio de Ginebra I                        |    Redes Sociales: @Fundagienbra    |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

        // write page
        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        footer.writeSelectedRows(0, -1, 34, 50, canvas);
        canvas.endMarkedContentSequence();
    } catch(DocumentException de) {
        throw new ExceptionConverter(de);
   
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
    
    
        
     
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JRpaciente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRcodigo;
    private javax.swing.JRadioButton jRusuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
