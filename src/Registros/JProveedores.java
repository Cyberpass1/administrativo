/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JCambiarState;
import Clases.JPacientesDao;
import Clases.Temporal;
import Clases.Validar;
import Clases.jProveedores;
import Menu.Mprincipal;
import Procesos.JAsignarPaciente;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class JProveedores extends javax.swing.JInternalFrame {

  
    
    int idProveedor;
    
    
    
    
    public JProveedores() {
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();

       
        limpiarTabla();
        listarProveedores();
        
   
    
 
        conteoTablatotal();

        
 
         
         String Nivel=TM.getNivel();
         if(Nivel.equals("Administrador") || Nivel.equals("Inspector")){
             Eliminar.setEnabled(true); 
    
         }
 
         
    //   BtnLimpiar1.setVisible(false);
        
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
    } catch (SQLException ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
}
    
    
    
    
    
    
    
    
    
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AsignarOrden = new javax.swing.JPopupMenu();
        Desactivar = new javax.swing.JMenuItem();
        Activar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        TXTPnombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXTPdireccion = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        TXTPtelefono = new javax.swing.JFormattedTextField();
        BtnAgregar = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        TXTPcedula = new javax.swing.JTextField();
        TXTPcorreo = new javax.swing.JTextField();
        jCategoria = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        TXTBcedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableProveedor = new javax.swing.JTable();

        Desactivar.setText("Inactivar");
        Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarActionPerformed(evt);
            }
        });
        AsignarOrden.add(Desactivar);

        Activar.setText("Activar");
        Activar.setToolTipText("");
        Activar.setActionCommand("Asignar Orden");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        AsignarOrden.add(Activar);

        Eliminar.setText("Eliminar");
        Eliminar.setToolTipText("");
        Eliminar.setEnabled(false);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        AsignarOrden.add(Eliminar);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      P R O V E E D O R E S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 280, -1));

        TXTPnombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Razón Social")));
        TXTPnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPnombreKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 340, 50));

        TXTPdireccion.setColumns(20);
        TXTPdireccion.setLineWrap(true);
        TXTPdireccion.setRows(5);
        TXTPdireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dirección"));
        TXTPdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPdireccionKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(TXTPdireccion);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 340, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, -1));

        TXTPtelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Telefono"));
        try {
            TXTPtelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel2.add(TXTPtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 170, 50));

        BtnAgregar.setText("Agregar");
        BtnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgregar.setContentAreaFilled(false);
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 100, 30));

        BtnModificar.setText("Modificar");
        BtnModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModificar.setContentAreaFilled(false);
        BtnModificar.setEnabled(false);
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 100, 30));

        BtnLimpiar.setText("Nuevo");
        BtnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar.setContentAreaFilled(false);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 100, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 500, 20));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, 10, 70));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 10, 70));

        TXTPcedula.setText("J-");
        TXTPcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "RIF | CI"));
        TXTPcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPcedulaKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 340, 50));

        TXTPcorreo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Correo")));
        TXTPcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPcorreoKeyTyped(evt);
            }
        });
        jPanel2.add(TXTPcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 170, 50));

        jCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Gasto" }));
        jCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria"));
        jPanel2.add(jCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 340, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 430, 590));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTBcedula.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar cédula | nombre"));
        TXTBcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTBcedulaKeyTyped(evt);
            }
        });
        jPanel8.add(TXTBcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 50));

        jLabel1.setText("Total de pacientes:");
        jPanel8.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        jLabel2.setText("0");
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 40, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 820, 80));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1300, 30));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RAZÓN", "CATEGORIA", "RIF | CI", "TELEFONO", "CORREO", "DIRECCIÓN", "ESTADO"
            }
        ));
        JTableProveedor.setComponentPopupMenu(AsignarOrden);
        JTableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableProveedor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 820, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = TXTPcorreo.getText();
        Matcher mather = pattern.matcher(Correo);
    
  int row = JTableProveedor.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = JTableProveedor.getValueAt(i, 3);
    }
    Object value_to_find= TXTPcedula.getText();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
        if (exist){
        JOptionPane.showMessageDialog(null, "EL PROVEEDOR YA EXISTE", "PROVEEDORES", JOptionPane.ERROR_MESSAGE);
    }
    
    

       else if (TXTPnombre.getText().equals("")
            || TXTPcedula.getText().equals("")  || TXTPdireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "LOS CAMPOS: NOMBRE, RIF Y DIRECCIÓN SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
     
        
        
        else {

         
                    
                 AuditoriaAgregar();
                 AgregarPaciente(); 
                 conteoTablatotal();
                
                


            }
    
        
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
limpiarCampos();
conteoTablatotal();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
       
   // Connection con;
   //EnlaceBd cn = new EnlaceBd();
   // PreparedStatement ps;
   // ResultSet rs;
    
   
   
   
   
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        String Correo = TXTPcorreo.getText();
        Matcher mather = pattern.matcher(Correo);

        if ( TXTPnombre.getText().equals("") || TXTPcedula.getText().equals("") 
              || TXTPdireccion.getText().equals("")) {
            
            JOptionPane.showMessageDialog(null, "LOS CAMPOS RAZÓN, RIF Y DIRECCIÓN SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        } 
        
   
       /*  
        else if (mather.find() == false) {
           
            
            JOptionPane.showMessageDialog(null, "EMAIL INVALIDO, VERIFIQUE", "EMAIL INVALIDO", JOptionPane.ERROR_MESSAGE);
            this.TXTPcorreo.requestFocus();
            
            
        }*/

        
             else{    
 AuditoriaModificar();
 actualizarProveedor(); 
                }
       
                
    }//GEN-LAST:event_BtnModificarActionPerformed

    
  
    
         
     String estado;
    private void JTableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableProveedorMouseClicked
     int fila = JTableProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;  
            
               
          
 
    
            idProveedor=(int) (JTableProveedor.getValueAt(fila, 0));
            TXTPnombre.setText(JTableProveedor.getValueAt(fila, 1).toString());
            jCategoria.setSelectedItem(JTableProveedor.getValueAt(fila, 2).toString());

            TXTPcedula.setText(JTableProveedor.getValueAt(fila, 3).toString());
            TXTPtelefono.setText(JTableProveedor.getValueAt(fila, 4).toString());
            TXTPcorreo.setText(JTableProveedor.getValueAt(fila, 5).toString());
            TXTPdireccion.setText(JTableProveedor.getValueAt(fila, 6).toString());
            estado = (JTableProveedor.getValueAt(fila, 7).toString());

            
            
            
            BtnAgregar.setEnabled(false);
            BtnModificar.setEnabled(true);
    
 
 
 
 
     
    }//GEN-LAST:event_JTableProveedorMouseClicked
    }      
    
    
    public String  Sexo="Masculino";;
    private void TXTBcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedulaKeyReleased
Search(); acomodarceldas();
    }//GEN-LAST:event_TXTBcedulaKeyReleased

    private void TXTPnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPnombreKeyTyped
   va.longitud(TXTPnombre.getText(), 30, evt);
    }//GEN-LAST:event_TXTPnombreKeyTyped

    private void TXTPcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPcedulaKeyTyped
               char car = evt.getKeyChar();
        //VERIFICA Y LIMITA COMPOSICION DE LOS DATOS
        if((car=='V' || car=='E'  || car=='-'|| car =='H' || car>='0' && car<='9' || car==(char)KeyEvent.VK_DELETE))
        {
            String Caracteres = TXTPcedula.getText();
            //CONTABILIZA LOS CARACTERES
            if(Caracteres.length()==10)
            {
                evt.consume();
            }
        }
        //EVITA EL INGRESO DE OTROS CARACTERES
        else if((car!='V' && car!='E' && car != 'J' && car!='-' && car!='H' || car<'0' || car>'9' || car!=(char)KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_TXTPcedulaKeyTyped

    private void TXTPcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPcorreoKeyTyped
        va.longitud(TXTPcorreo.getText(), 50, evt);
    }//GEN-LAST:event_TXTPcorreoKeyTyped

    private void TXTPdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPdireccionKeyTyped
        va.longitud(TXTPcorreo.getText(), 100, evt);
    }//GEN-LAST:event_TXTPdireccionKeyTyped

    private void TXTBcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTBcedulaKeyTyped
          va.longitud(TXTBcedula.getText(), 30, evt);
    }//GEN-LAST:event_TXTBcedulaKeyTyped

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed

 
                  if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente activar este proveedor?",
            "ACTIVAR PROVEEDOR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = JTableProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (estado.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL PROVEEDOR YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarProveedor(100, idProveedor)) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL SERVICIO SE HA ACTUALIZADO HA : Activo", "Estado", 1);
                limpiarTabla();
                listarProveedores();
                audtiActivarServ("Activo el proveedor");
              //  audtDesactCatg();

            }
        }

        }
        
    }//GEN-LAST:event_ActivarActionPerformed

    
    public void eliminarServicio() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      int fila = JTableProveedor.getSelectedRow();

      String mensaje = "¿Está seguro de que desea eliminar " + JTableProveedor.getValueAt(fila, 1).toString() + " de la tabla de proveedores?";
      mensaje += "\nNOTA: Si existen compras o gastos con este proveedor asignado y lo elimina, no podran visualizar los mismos";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_proveedor` WHERE id_proveedor = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El proveedor ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                 limpiarTabla();
                listarProveedores();
                audtiActivarServ("Elimino el siguiente proveedor de la tabla: ");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
        }
}

    
     public void agregarOrden() {                                             
 
        
       Mprincipal MP = (Mprincipal) SwingUtilities.getWindowAncestor(this);
       MP.JMenu.setSelectedIndex(0);
       dispose();
 
       MP.JMenu.setSelectedIndex(1);
       JAsignarPaciente jAsig = new JAsignarPaciente();
       MP.JDesktopMenu.setVisible(true);
       MP.JDesktopMenu.add(jAsig);
       jAsig.setClosable(true);
       jAsig.setIconifiable(true);
       
        try {
            jAsig.setMaximum(true);
        } catch (Exception e) {
        }
        jAsig.toFront();
        jAsig.setVisible(true);
       
       
              
      
        // Configurar el valor en ConsultaLaboratorio
        jAsig.TXTPcedula.setText(TXTPcedula.getText());
    
        //jAsig.Callpacient();
     
       
        
        
        
        
}
    
    
    
    
    
    int idorden, orden;
    String nombreCompleto, factura, cedulaOrden;
    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
   int fila = JTableProveedor.getSelectedRow();
        if (fila == -1) {
        JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UNA FILA DE LA TABLA PROVEEDORES", "SELECCION", JOptionPane.ERROR_MESSAGE);
        } else {
   
        
        
       eliminarProveedor();
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarActionPerformed
                if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente inactivar este proveedor?",
            "INACTIVAR PROVEEDOR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = JTableProveedor.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (estado.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "EL PROVEEDOR YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarProveedor(101, idProveedor)) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL SERVICIO SE HA ACTUALIZADO HA : Inactivo", "Estado", 1);
                limpiarTabla();
                listarProveedores();
                audtiActivarServ("Inactivo el proveedor");
              //  audtDesactCatg();

            }
        }

        }
    }//GEN-LAST:event_DesactivarActionPerformed
  JCambiarState setState = new JCambiarState();  
        
 
      public void audtiActivarServ(String Accion){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= Accion+" "+TXTPnombre.getText() ;
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
      
      
   
   void limpiarTabla() {
       
  DefaultTableModel Tabla = (DefaultTableModel)JTableProveedor.getModel();  
  Tabla.setRowCount(0);
    }


   
    
  public void limpiarCampos() {
      TXTPnombre.setText("");

      TXTPcedula.setText("J-");
      TXTPcorreo.setText("");
      TXTPtelefono.setText("");
      TXTPdireccion.setText("");
     // TXTBnombre.setText("");


      BtnAgregar.setEnabled(true);
      BtnModificar.setEnabled(false);
    }
    
     public void listarProveedores() {

        List<jProveedores> lista = pacientesDao.listarProveedores();
        modelo = (DefaultTableModel) JTableProveedor.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdProveedor();
            ob[1] = lista.get(i).getProveedor();
            ob[2] = lista.get(i).getCategoria();
            ob[3] = lista.get(i).getRif();
            ob[4] = lista.get(i).getTelefono();
            ob[5] = lista.get(i).getCorreo();
            ob[6] = lista.get(i).getDireccion();
            ob[7] = lista.get(i).getEstado();
            modelo.addRow(ob);

        }
       JTableProveedor.setModel(modelo);
        
     acomodarceldas();
     JTableProveedor.setDefaultEditor(Object.class, null);
     DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) JTableProveedor.getTableHeader().getDefaultRenderer();
     headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    
    

    
      public void AgregarPaciente(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

        
        java.sql.Date fechaU;
        Date dateU = Menu.FechaAdmin.getDate();
        long dU = dateU.getTime();
        fechaU = new java.sql.Date(dU);

        
        
        
        
              
        
            String sql = "INSERT INTO table_proveedor (proveedor,id_categoria, rif, telefono, correo, direccion) " +
             "VALUES (?, (SELECT id_catprov FROM categorias_proveedores WHERE categoria = ?), ?, ?, ?, ?)";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
     
            ps.setString(1, TXTPnombre.getText());
            ps.setString(2, jCategoria.getSelectedItem().toString());
            ps.setString(3, TXTPcedula.getText());
            ps.setString(4, TXTPtelefono.getText());
            ps.setString(5, TXTPcorreo.getText());
            ps.setString(6, TXTPdireccion.getText());


            ps.execute();
  
            
            limpiarTabla();      listarProveedores(); limpiarCampos();
  JOptionPane.showMessageDialog(null, "EL PROVEEDOR HA SIDO REGISTRADO","REGISTRO DE PACIENTES", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        } finally {
     closeResources(rs, ps, con);
    }
          
    } 
      
      
      

      
      
      
     
   public void actualizarProveedor() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
            
        java.sql.Date fechaU;
        Date dateU = Menu.FechaAdmin.getDate();
        long dU = dateU.getTime();
        fechaU = new java.sql.Date(dU);

         
        
            String sql = "UPDATE table_proveedor SET " +
                     "proveedor = ?, " +
                     "id_categoria = (SELECT id_catprov FROM categorias_proveedores WHERE categoria = ?), " +
                     "rif = ?, " +
                     "telefono = ?, " +
                     "correo = ?, " +
                     "direccion = ? " +
                     "WHERE id_proveedor = ?";
            
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, TXTPnombre.getText());
            ps.setString(2, jCategoria.getSelectedItem().toString());
            ps.setString(3, TXTPcedula.getText());
            ps.setString(4, TXTPtelefono.getText());
            ps.setString(5, TXTPcorreo.getText());
            ps.setString(6, TXTPdireccion.getText());
            ps.setInt(7, idProveedor);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PROVEEDOR ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
                limpiarTabla();
                   listarProveedores();
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROVEEDOR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "NO PUEDE INGRESAR UNA CÉDULA DUPLICADA", "DATOS DUPLICADOS", JOptionPane.ERROR_MESSAGE);
           }finally {
     closeResources(rs, ps, con);
    }
                 
    }

      
   


   
   public void Search() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    EnlaceBd cn = new EnlaceBd();
    
    String busqueda = this.TXTBcedula.getText().trim(); // Obtener texto de búsqueda
    
    
    
 
    
    // Consulta SQL para buscar por cédula, nombre o apellido
String sql = "SELECT id_proveedor, proveedor, p.categoria, rif, telefono, correo, direccion, e.Estado " +
             "FROM table_proveedor u " +
             "INNER JOIN categorias_proveedores p ON u.id_categoria = p.id_catprov " +
             "INNER JOIN table_estado e ON u.estado = e.IdEstado " +
             "WHERE u.rif LIKE ? OR u.proveedor LIKE ?";

    
    // Declaración del modelo de la tabla
    DefaultTableModel tabla = (DefaultTableModel) JTableProveedor.getModel();
    tabla.setRowCount(0); // Limpiar la tabla antes de agregar datos
    
    try {
        // Establecer conexión y preparar consulta
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        
        // Configurar parámetros de búsqueda
        ps.setString(1, "%" + busqueda + "%"); // Cédula
        ps.setString(2, "%" + busqueda + "%"); // Nombre y Apellido combinados
        
        // Ejecutar consulta
        rs = ps.executeQuery();
        
        // Procesar resultados
        while (rs.next()) {
            // Obtener datos de la consulta
            int col1 = rs.getInt(1);        // Idpaciente
            String col2 = rs.getString(2);  // Nombre
            String col3 = rs.getString(3);  // Apellido
            String col4 = rs.getString(4);  // Cedula
            String col5 = rs.getString(5);  // Telefono
            String col6 = rs.getString(6);  // Correo
            String col7 = rs.getString(7);  // Direccion
            String col8 = rs.getString(8);  // Nombre

            
            // Agregar datos a la tabla
            Vector<Object> vRow = new Vector<>();
            vRow.add(col1);
            vRow.add(col2);
            vRow.add(col3);
            vRow.add(col4);
            vRow.add(col5);
            vRow.add(col6);
            vRow.add(col7);
            vRow.add(col8);
  
            
            tabla.addRow(vRow);
        }
        
        // Otras configuraciones de la tabla
        JTableProveedor.getTableHeader().setReorderingAllowed(false);
        acomodarceldas(); // Ajustar celdas si es necesario
        
    } catch (SQLException e) {
        System.out.println("Error en la consulta SQL: " + e.getMessage());
    } finally {
     closeResources(rs, ps, con);
    }
}
      
      
      
      
      
      
        
      
      
      
      
      
      
      
       
  public void EliminarAntc(){
  
  
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
 
            String sql = "DELETE FROM orden_lab WHERE lugar = ? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idorden);
            int res = ps.executeUpdate();

            if (res >= 1) {
           JOptionPane.showMessageDialog(null, "PROCESO REALIZADO", "ORDENES", 1);
             
            } else {
               JOptionPane.showMessageDialog(null, "NO SE PUDO QUITAR DE LA LISTA EL PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
          
            
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL QUITAR DEL PACIENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
           }finally {
            closeResources(rs, ps, con);
        }

  } 
      
      
      
      
      
      
      
        public void AuditoriaAgregar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
              String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" Agrego al proveedor: "+ TXTPnombre.getText() ;
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
      
      
        public void AuditoriaEliminar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Quito al paciente de la lista orden: "+ nombreCompleto +" " ;
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
      
        
        
           public void auditoriaEliminarPaciente(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
            String nombreCompleto = TXTPnombre.getText() ;
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
           
            String accion= "Elimino al paciente de la bd: "+ nombreCompleto +" " ;
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
        
        
        
        
        
        
        
        public void auditoriaAsignar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Asigno al paciente: "+ nombreCompleto +"a la lista de orden" ;
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
      
        
        
      
           public void auditoriaModifico(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modifico al paciente de la lista orden: "+ nombreCompleto +" " ;
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
        
        
        
        
      public void AuditoriaModificar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" Modifico al paciente: "+ TXTPnombre.getText();
            
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
    
      
      
      
      
      
      
   public void acomodarceldas() {
    DefaultTableModel tabla = (DefaultTableModel) JTableProveedor.getModel();
    DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    alinear.setHorizontalAlignment(SwingConstants.CENTER);

    // Nombres de las columnas y sus anchos respectivos
    String[] columnas = {
        "ID", "RAZÓN", "CATEGORIA", "RIF | CI",
        "TELEFONO", "CORREO", "DIRECCIÓN",
        "ESTADO"
    };
    
    int[] anchos = {10, 180, 80, 40, 40, 40, 80, 20};

    for (int i = 0; i < columnas.length; i++) {
        int col = tabla.findColumn(columnas[i]);
  
        /*if ("ID".equals(columnas[i])) {
    TableColumn colId = jTable1.getColumnModel().getColumn(col);
    colId.setMinWidth(0);
    colId.setMaxWidth(0);
    colId.setPreferredWidth(0);
    continue;
}*/
        
        if (col != -1) {
            JTableProveedor.getColumnModel().getColumn(col).setPreferredWidth(anchos[i]);
            JTableProveedor.getColumnModel().getColumn(col).setCellRenderer(alinear);
        } else {
            System.err.println("⚠️  Columna no encontrada: " + columnas[i]);
        }
    }
}
    
       
     
       
       
    public void eliminarProveedor() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs=null;
    try {
        String nombreCompleto = TXTPnombre.getText() ;

      String mensaje = "¿Está seguro de que desea eliminar a " + nombreCompleto + " de la tabla de proveedores?";
      mensaje += "\nNOTA: Si el proveedor cuenta con reportes ya generados, no se podrán visualizar en la tabla de consultas.";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_proveedor` WHERE id_proveedor = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El Proveedor ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                    listarProveedores();
                conteoTablatotal();
                auditoriaEliminarPaciente();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar al proveedor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
    closeResources(rs, ps, con);
    }
}
         

    
    
    
    
// Función para crear y personalizar campos de texto
private JCheckBox createStyledCheckBox(String text) {
    JCheckBox checkBox = new JCheckBox(text);
    checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
    checkBox.setBackground(Color.WHITE);
    checkBox.setForeground(Color.BLACK);
    // Puedes seguir personalizando el estilo del JCheckBox aquí si es necesario.
    return checkBox;
}

private JLabel createStyledLabel(String labelText) {
    JLabel label = new JLabel(labelText);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setHorizontalAlignment(JLabel.RIGHT); // Alinea el texto a la derecha
    label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Agrega un espacio alrededor de la etiqueta
    return label;
}
         
         
        
         
 
    



         
       
       
    public void conteoTablatotal(){
       
       for(int x =0; x<=JTableProveedor.getRowCount(); x++ ){
       jLabel2.setText(""+x);
       
       }
       }   
      
    
      
    
      
  DefaultTableModel modelo = new DefaultTableModel();
  Mprincipal Menu = new Mprincipal();
  Encriptar encriptar = new Encriptar();
  JCPacientes pacientes  = new JCPacientes();
  JPacientesDao pacientesDao  = new JPacientesDao();
    
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
  String niveluser=TM.getNivel();   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JPopupMenu AsignarOrden;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JTable JTableProveedor;
    private javax.swing.JTextField TXTBcedula;
    private javax.swing.JTextField TXTPcedula;
    private javax.swing.JTextField TXTPcorreo;
    private javax.swing.JTextArea TXTPdireccion;
    private javax.swing.JTextField TXTPnombre;
    private javax.swing.JFormattedTextField TXTPtelefono;
    private javax.swing.JComboBox<String> jCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
