/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Registros.*;
import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCFactura;
import Clases.JCFacturaDao;
import Clases.LlenarCombobox;
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
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FCGinebraI
 */
public class jInventario extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public jInventario() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();

        limpiarTabla();
        listarProductos();
        informacionpdf();
        llenarCategoria();
        conteoTabla();
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        FechaAc2 = new javax.swing.JLabel();
        jTxtPrd = new javax.swing.JTextField();
        jStock = new javax.swing.JSpinner();
        BtnAgregar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        BtnAgregar2 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jExistenciaTotal = new javax.swing.JSpinner();
        jSpinnerRestar = new javax.swing.JSpinner();
        jSpinnerSum = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCategoria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1300, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 20));

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("I N V E N T A R I O");
        jPanel4.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 260, -1));

        jTxtPrd.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Producto"));
        jTxtPrd.setEnabled(false);
        jPanel4.add(jTxtPrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 310, 50));

        jStock.setBorder(javax.swing.BorderFactory.createTitledBorder("Existencia"));
        jStock.setEnabled(false);
        jPanel4.add(jStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 310, 50));

        BtnAgregar.setText("Nuevo");
        BtnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgregar.setContentAreaFilled(false);
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(BtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 100, 30));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 430, 20));

        BtnAgregar2.setText("Modificar");
        BtnAgregar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgregar2.setContentAreaFilled(false);
        BtnAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregar2ActionPerformed(evt);
            }
        });
        jPanel4.add(BtnAgregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 100, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 20, 90));

        jExistenciaTotal.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10000, 1));
        jExistenciaTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Total"));
        jExistenciaTotal.setEnabled(false);
        jPanel4.add(jExistenciaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 310, 50));

        jSpinnerRestar.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        jSpinnerRestar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Restar"));
        jSpinnerRestar.setEnabled(false);
        jSpinnerRestar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerRestarStateChanged(evt);
            }
        });
        jSpinnerRestar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerRestarPropertyChange(evt);
            }
        });
        jPanel4.add(jSpinnerRestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 150, 50));

        jSpinnerSum.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        jSpinnerSum.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Sumar")));
        jSpinnerSum.setEnabled(false);
        jSpinnerSum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerSumStateChanged(evt);
            }
        });
        jSpinnerSum.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerSumPropertyChange(evt);
            }
        });
        jPanel4.add(jSpinnerSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 150, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 370, 620));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar Productos"));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logos/adobe_pdf_document_14979.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 70, 80));

        jLabel1.setText("TOTAL REGISTROS");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 120, -1));

        jLabel2.setText("0");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 40, -1));

        jCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Categoria"));
        jCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCategoriaItemStateChanged(evt);
            }
        });
        jPanel5.add(jCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 240, 50));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 870, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "PRODUCTOS", "PRECIO", "CATEGORIA", "STOCK MIN", "EXISTENCIA", "STOCK MAX", "ESTADO", "BARRA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 870, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    
        private Thread searchThread;
    private final int DELAY = 500;
    
    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
               if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }

        searchThread = new Thread(() -> {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                return; // La búsqueda fue interrumpida
            }

            SwingUtilities.invokeLater(() -> {
            if(!jTextField3.getText().equals("")){
            searchBy(params -> new ArrayList<>(factDao.searchProductos(params[0])));
            }
            else{      
                limpiarTabla();
                listarProductos();}
                conteoTabla();

             
            });
        });

        searchThread.start();
    }//GEN-LAST:event_jTextField3KeyReleased

    
    
    public void searchByCat(Function<String[], ArrayList<JCFactura>> searchFunction) {
    String[] params = { jCategoria.getSelectedItem().toString() };

    try {
        // Obtener la lista de productos
        ArrayList<JCFactura> list = searchFunction.apply(params);
        
        // Si la lista no está vacía, configurar la tabla
        if (!list.isEmpty()) {
            setupTableModel(list);
        } else {
            limpiarTabla(); // Limpiar la tabla si no hay resultados
        }
    } catch (Exception e) {
        System.err.println("Error en searchBy: " + e.getMessage());
    }
}
    
    
      
  public void searchBy(Function<String[], ArrayList<JCFactura>> searchFunction) {
    String[] params = { jTextField3.getText() };

    try {
        // Obtener la lista de productos
        ArrayList<JCFactura> list = searchFunction.apply(params);
        
        // Si la lista no está vacía, configurar la tabla
        if (!list.isEmpty()) {
            setupTableModel(list);
        } else {
            limpiarTabla(); // Limpiar la tabla si no hay resultados
        }
    } catch (Exception e) {
        System.err.println("Error en searchBy: " + e.getMessage());
    }
}

private void setupTableModel(ArrayList<JCFactura> list) {
    // Crear el modelo de la tabla
    DefaultTableModel dt = createTableModel();
    
    // Rellenar la tabla con los datos de los productos
    for (JCFactura vo : list) {
        Object[] row = {
            vo.getIdproducto(),
            vo.getCodeproducto(),
            vo.getProducto(),
            vo.getPrecioPrd(),
            vo.getCtgProducto(),
            vo.getStockmin(),
            vo.getExistencia(),
            vo.getStockmax(),
            vo.getStatePrd(),
            vo.getCode_barra()
        };
        dt.addRow(row);
    }

    // Asignar el modelo a la tabla y ajustar propiedades
    jTable1.setModel(dt);
    jTable1.setRowHeight(32);
    acomodarceldas(); // Método que ajusta el tamaño de las celdas si es necesario
}

private DefaultTableModel createTableModel() {
    // Crear el modelo de la tabla y especificar que no sea editable
    DefaultTableModel dt = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // No editable
        }
    };

    // Definir los nombres de las columnas
    String[] columnNames = {
        "ID", "CODIGO", "PRODUCTOS", "PRECIO", 
        "CATEGORIA", "STOCK MIN", "EXISTENCIA", "STOCK MAX", 
        "ESTADO", "BARRA"
    };

    // Añadir las columnas al modelo
    for (String column : columnNames) {
        dt.addColumn(column);
    }
    
    return dt;
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
    
     
    
    
    
    
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        
        limpiarTabla();
        listarProductos();
        disableItems();
    limpiarCampos();

    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregar2ActionPerformed
  actualizarProducto() ;
    }//GEN-LAST:event_BtnAgregar2ActionPerformed
 
    
    
    
      public void actualizarProducto() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

    int stockActual= (Integer) jExistenciaTotal.getValue();
    
       //  boolean tieneFotoFirma = (imagePath != null && !imagePath.trim().isEmpty());
       // try (InputStream fotoFirma = tieneFotoFirma ? new FileInputStream(new File(imagePath)) : null)
        try {
           
            String sql = "UPDATE table_productos SET  existencia=?  WHERE id_producto=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           
            ps.setInt(1, stockActual);
            ps.setInt(2 , idServc);
            
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
                limpiarTabla();
                listarProductos();
                limpiarCampos();
                auditoriaModificar();
                
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
           }  finally {
            closeResources(rs, ps, con);
        }
                 
    }
    
    
    
    public void limpiarCampos(){
    
       limpiarTabla();
        listarProductos();
        jTxtPrd.setText("");
        jStock.setValue(0);
        jSpinnerSum.setValue(0);
        jSpinnerRestar.setValue(0);
        jExistenciaTotal.setValue(0);
                
    }
    
    
    
    
     public void auditoriaModificar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" MODIFICO EL STOCK DEL PRODUCTO: "+ jTxtPrd.getText();
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
   
    
    
    
    
    
    int idServc;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int fila = jTable1.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
} else {  
    
    
        idServc = (int) jTable1.getValueAt(fila, 0);
        String producto = jTable1.getValueAt(fila, 2).toString();
        Object stock = jTable1.getValueAt(fila, 6);
        
        jTxtPrd.setText(producto);
        jStock.setValue(stock);  
        jExistenciaTotal.setValue(stock);  
        
        enableItems();
}                                 
   


    }//GEN-LAST:event_jTable1MouseClicked

    
    
    public void enableItems(){
   
  // jStock.setEnabled(true);
    jSpinnerSum.setEnabled(true);
    jSpinnerRestar.setEnabled(true);
    jExistenciaTotal.setEnabled(true);

    }
    
      public void disableItems(){

    jStock.setEnabled(false);
    jSpinnerSum.setEnabled(false);
    jSpinnerRestar.setEnabled(false);
    jExistenciaTotal.setEnabled(false);

    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       
            pdf();
            AuditoriaReporte();
      

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSpinnerSumPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerSumPropertyChange
    
 
        
        if (jStock != null && jSpinnerSum != null && jExistenciaTotal != null) {
        try {
            int stockActual = (Integer) jStock.getValue();  
            int stockAgregar = (Integer) jSpinnerSum.getValue();  
            int sumatoria = stockActual + stockAgregar;
            
            // Si deseas, puedes agregar validaciones de límites aquí
            // Por ejemplo:
            // if (sumatoria > MAX_STOCK) sumatoria = MAX_STOCK;
            
            jExistenciaTotal.setValue(sumatoria);
        } catch (Exception e) {
            // Manejo de excepciones si ocurre un error durante el casteo
            e.printStackTrace();  // Para depuración
        }
    }
                
    }//GEN-LAST:event_jSpinnerSumPropertyChange

    private void jSpinnerRestarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerRestarPropertyChange
        if (jStock != null && jSpinnerRestar != null && jExistenciaTotal != null) {
        try {
            int stockActual = (Integer) jStock.getValue();  
            int stockRestar = (Integer) jSpinnerRestar.getValue();  
            int resta = stockActual - stockRestar ;
            
        
            jExistenciaTotal.setValue(resta);
        } catch (Exception e) {
        
            e.printStackTrace();  // Para depuración
        }
    
    } 
                
    }//GEN-LAST:event_jSpinnerRestarPropertyChange

    private void jSpinnerSumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerSumStateChanged
      
        if (jStock != null && jSpinnerSum != null && jExistenciaTotal != null) {
        try {
            int stockActual = (Integer) jStock.getValue();  
            int stockAgregar = (Integer) jSpinnerSum.getValue();  
            int sumatoria = stockActual + stockAgregar;
            
   
            
            jExistenciaTotal.setValue(sumatoria);
        } catch (Exception e) {
    
            e.printStackTrace();  // Para depuración
        }
    }
    }//GEN-LAST:event_jSpinnerSumStateChanged

    private void jSpinnerRestarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerRestarStateChanged
             if (jStock != null && jSpinnerRestar != null && jExistenciaTotal != null) {
        try {
            int stockActual = (Integer) jStock.getValue();  
            int stockRestar = (Integer) jSpinnerRestar.getValue();  
            int resta = stockActual - stockRestar ;
            
        
            jExistenciaTotal.setValue(resta );
        } catch (Exception e) {
        
            e.printStackTrace();  // Para depuración
        }
    
    } 
    }//GEN-LAST:event_jSpinnerRestarStateChanged

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
          jTable1.requestFocusInWindow();
    }//GEN-LAST:event_jTable1MouseEntered

    private void jCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCategoriaItemStateChanged
        
        
        if(jCategoria.getSelectedIndex()!=-1){

        

        if(!jCategoria.getSelectedItem().equals("Todos")){
       searchByCat(params -> new ArrayList<>(factDao.searchCategoria(params[0])));
  
       conteoTabla();
       
        jTable1.requestFocusInWindow();
       

            }

            else{
          
          limpiarTabla();
          listarProductos();
          conteoTabla();
        //  jEstado.setSelectedItem("Todos");
          jTable1.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_jCategoriaItemStateChanged

    
    
        
    
   
  
    
    
    
     public void pdf() {
         

         
      try {
        
          
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
          

         
 
    
      
                   
            String   PdfNames="ReporteInventario"+"_"+fth.format(fechaactual); 
            BaseFont BF = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);    
            Font Letra = new Font(BF); 
            Paragraph saltolinea = new Paragraph();
            saltolinea.add("\n");

            FileOutputStream archivo;
            File file = new File("C:\\Cyberia\\Reportes\\" + PdfNames + ".pdf");

            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter writer=  PdfWriter.getInstance(doc, archivo);
            doc.setMargins(36, 36, 110, 130);
            doc.open();
            
                    
           

           


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

            
            
            
            
            
// Centrado de los textos con PdfContentByte
PdfContentByte CB = writer.getDirectContent();  
BaseFont BF2 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

// Configuración de las fuentes
CB.beginText();
CB.setFontAndSize(BF2, 12);

// Obtenemos las coordenadas dinámicamente para centrar el texto en la página
float pageWidth = doc.getPageSize().getWidth();
float empresaWidth = BF2.getWidthPoint(empresa, 12);
float empresaX = (pageWidth - empresaWidth) / 2; // Centramos el texto de la empresa

CB.setTextMatrix(empresaX, 800);
CB.showText(empresa);


CB.setFontAndSize(BF2, 10);
float fechaWidth = BF2.getWidthPoint("FECHA: " + Fecha, 10);
float fechaX = (pageWidth - fechaWidth) / 2;
CB.setTextMatrix(460, 780);
CB.showText("FECHA: " + Fecha);

// Centramos el texto del "rif"
CB.setFontAndSize(BF, 10);
float rifWidth = BF.getWidthPoint(rif, 10);
float rifX = (pageWidth - rifWidth) / 2;
CB.setTextMatrix(rifX, 790);
CB.showText(rif);

// Centramos la fecha


// Centramos la hora
CB.setFontAndSize(BF2, 10);
float horaWidth = BF2.getWidthPoint("HORA: " + Hora, 10);
float horaX = (pageWidth - horaWidth) / 2;
CB.setTextMatrix(460, 770);
CB.showText("HORA: " + Hora);

// Centramos la ubicación
CB.setFontAndSize(BF2, 8);
float ubicacionWidth = BF2.getWidthPoint(ubicacion, 8);
float ubicacionX = (pageWidth - ubicacionWidth) / 2;
CB.setTextMatrix(ubicacionX, 740);
CB.showText(ubicacion);

// Centramos los teléfonos
CB.setFontAndSize(BF2, 8);
float telefonosWidth = BF2.getWidthPoint(telefonos, 8);
float telefonosX = (pageWidth - telefonosWidth) / 2;
CB.setTextMatrix(telefonosX, 730);
CB.showText(telefonos);

// Línea horizontal (ajustada al centro)
CB.setTextMatrix((pageWidth - 400) / 2, 725); // Asumiendo que la línea tiene un ancho de 400
CB.showText("_________________________________________________________________________________________");

CB.endText();




        
       //BODY 
       
       
        
            //productos
          
            
            
         
            
            
            PdfPTable  tablapro = new PdfPTable(4);
            
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            tablapro.setWidthPercentage(100);  
            float[] medidaCeldas = {1f, 5f, 3f,2f};
            tablapro.setWidths(medidaCeldas);
            tablapro.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablapro.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tcolumna1 = new Paragraph("CÓDIGO");
            tcolumna1.getFont().setStyle(Font.BOLD);
            tcolumna1.getFont().setSize(10);        
            tcolumna1.setFont(Letra);
            tcolumna1.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna1);
        
          
            Paragraph tcolumna2 = new Paragraph("PRODUCTO");
            tcolumna2.getFont().setStyle(Font.BOLD);
            tcolumna2.getFont().setSize(10);        
            tcolumna2.setFont(Letra);
            tcolumna2.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna2);
        
            Paragraph tcolumna3 = new Paragraph("CATEGORIA");
            tcolumna3.getFont().setStyle(Font.BOLD);
            tcolumna3.getFont().setSize(10);        
            tcolumna3.setFont(Letra);
            tcolumna3.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna3);
            
            Paragraph tcolumna4 = new Paragraph("EXISTENCIA");
            tcolumna4.getFont().setStyle(Font.BOLD);
            tcolumna4.getFont().setSize(10);        
            tcolumna4.setFont(Letra);
            tcolumna4.setAlignment(Element.ALIGN_CENTER);
            tablapro.addCell(tcolumna4);
            
            int sumExistencia=0;
            
  for (int i = 0; i < jTable1.getRowCount(); i++) {
    // Get the data from each column
    String Codigo = jTable1.getValueAt(i, 0).toString();
    String Producto = jTable1.getValueAt(i, 2).toString();
    String Categoria = jTable1.getValueAt(i, 4).toString();
    
    // Ensure that Existencia is being properly parsed as an integer
    int Existencia = 0; // Default value if conversion fails
    try {
        Existencia = Integer.parseInt(jTable1.getValueAt(i, 6).toString());
    } catch (NumberFormatException e) {
        // Handle the case if the data in the table is not a valid integer
        System.err.println("Error parsing Existencia at row " + i);
    }

    // Create a cell with border at the bottom and spacing
    Font font = FontFactory.getFont("Arial", 10, Font.NORMAL);
    PdfPCell cell;

    // Add the values into the PDF table (assuming 'tablapro' is the table you are adding cells to)
    cell = new PdfPCell(new Paragraph(Codigo, font));
    cell.setBorderWidthBottom(1); // Adds a line at the bottom
    cell.setPaddingBottom(5); // Adds spacing below the text
    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // Centers the text horizontally
    tablapro.addCell(cell);

    cell = new PdfPCell(new Paragraph(Producto, font));
    cell.setBorderWidthBottom(1);
    cell.setPaddingBottom(5);
    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // Centers the text horizontally
    tablapro.addCell(cell);

    cell = new PdfPCell(new Paragraph(Categoria, font));
    cell.setBorderWidthBottom(1);
    cell.setPaddingBottom(5);
    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // Centers the text horizontally
    tablapro.addCell(cell);

    cell = new PdfPCell(new Paragraph(String.valueOf(Existencia), font));
    cell.setBorderWidthBottom(1);
    cell.setPaddingBottom(5);
    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); // Centers the text horizontally
    tablapro.addCell(cell);

    // Summing up the 'Existencia' values
    sumExistencia += Existencia;
}


            
            PdfPTable TableTotal = new PdfPTable(1); 
            TableTotal.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
           // Tabla9.SetBorderBottomLeftRadius(new BorderRadius(4f)); // No border is drawn
            float[] medidaObservacion = {5f};
            TableTotal.setWidthPercentage(100);  
            TableTotal.setWidths(medidaObservacion);
            TableTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            TableTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           
            
            
     double dineroBruto=sumExistencia * 10;
     double gananciaNeta=0;
            
            Paragraph t9columna1 = new Paragraph("");
            t9columna1.getFont().setStyle(Font.BOLD);
            t9columna1.getFont().setSize(9);        
            t9columna1.setFont(Letra);
            t9columna1.setAlignment(Element.ALIGN_LEFT);
            TableTotal.addCell(t9columna1);
            TableTotal.addCell(new Paragraph("EXISTENCIA DE INVENTARIO TOTAL: " +sumExistencia  ,FontFactory.getFont("Arial",12,Font.BOLD)));       
            
            //+ " TOTAL DINERO BRUTO: "+dineroBruto 
            
            
            doc.add(saltolinea);
            doc.add(saltolinea);
            doc.add(tablapro);
         
            doc.add(saltolinea);
            doc.add(saltolinea);
            doc.add(TableTotal);
            
            
   
         
   

         
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
        footer.addCell(new Phrase(String.format("Emitido por:"+User+"                                                  |   "+piepagina+" |                                            "+ writer.getPageNumber()+ " | Pág") , new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));

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
       
       for(int x =0; x<=jTable1.getRowCount(); x++ ){
      jLabel2.setText(""+x);
       
       }
       }
       
    
    


        public  void listarProductos() {

        List<JCFactura> lista = factDao.listarProductos();
        modelo = (DefaultTableModel) jTable1.getModel();
        Object[] ob = new Object[12];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdproducto();
            ob[1] = lista.get(i).getCodeproducto();
            ob[2] = lista.get(i).getProducto();
            ob[3] = lista.get(i).getPrecioPrd();
            ob[4] = lista.get(i).getCtgProducto();
            ob[5] = lista.get(i).getStockmin();
            ob[6] = lista.get(i).getExistencia();
            ob[7] = lista.get(i).getStockmax();
            ob[8] = lista.get(i).getStatePrd();
            ob[9] = lista.get(i).getCode_barra();
            
            modelo.addRow(ob);

        }
       jTable1.setModel(modelo);
       jTable1.setModel(modelo);
       jTable1.setRowHeight(32);
       acomodarceldas();
 
     jTable1.setDefaultEditor(Object.class, null);
    }



  
    public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
        int a = jTable1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

 
    
      JCFacturaDao factDao=  new JCFacturaDao();
   JCFactura fac= new JCFactura();
      DefaultTableModel modelo = new DefaultTableModel();
    
    
    
    
           
     public void acomodarceldas()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)jTable1.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(0);
        
       // jTable1.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("CODIGO")).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PRODUCTOS")).setPreferredWidth(280);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PRECIO")).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("CATEGORIA")).setPreferredWidth(80);
       // jTable1.getColumnModel().getColumn(Tabla.findColumn("STOCK MIN")).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("EXISTENCIA")).setPreferredWidth(40);
       // jTable1.getColumnModel().getColumn(Tabla.findColumn("STOCK MAX")).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("BARRA")).setPreferredWidth(30);
         
      //  jTable1.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("CODIGO")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PRODUCTOS")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PRECIO")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("CATEGORIA")).setCellRenderer(Alinear);
       // jTable1.getColumnModel().getColumn(Tabla.findColumn("STOCK MIN")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("EXISTENCIA")).setCellRenderer(Alinear);
       // jTable1.getColumnModel().getColumn(Tabla.findColumn("STOCK MAX")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("BARRA")).setCellRenderer(Alinear);

       
    
    }
   
   
     
    
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
    
            
          public void AuditoriaReporte(){
            
   int idusuario=TM.getTexto();        
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
             try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Genero un reporte de inventario " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  idusuario);
            ps.setInt(2,  idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e);}
      finally {
            closeResources(rs, ps, con);
        } 
     
    }
    
          
          
          
          LlenarCombobox lc = new LlenarCombobox();
        public void llenarCategoria() {
    try {
        jCategoria.removeAllItems(); // Limpia el combo por si acaso

        // Primero agrega "Todos"
        jCategoria.addItem("Todos");

        // Luego llena con los datos reales
        ArrayList<String> lista3 = lc.llenarCatgPrd();
        for (int i = 0; i < lista3.size(); i++) {
            jCategoria.addItem(lista3.get(i));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnAgregar2;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jCategoria;
    private javax.swing.JSpinner jExistenciaTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSpinner jSpinnerRestar;
    private javax.swing.JSpinner jSpinnerSum;
    private javax.swing.JSpinner jStock;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTxtPrd;
    // End of variables declaration//GEN-END:variables
}
