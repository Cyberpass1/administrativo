/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCEspecialidades;
import Clases.JCFactura;
import Clases.JCFacturaDao;
import Clases.JCambiarState;
import Clases.LlenarCombobox;
import Clases.PdfVO;
import Clases.SearchConsultasDao;
import Clases.Temporal;
import Clases.Validar;
import Clases.imgTabla;
import Menu.Mprincipal;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author FCGinebraI
 */
public class JProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JProductos() {
       initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
       Calendar Fecha = new GregorianCalendar();
       limpiarTabla();
       listarProductos();
       jTxtPrecio1.setEditable(false);
       llenarCombo();
       findCategoria();
       jCategoria1.setVisible(false);
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jStates = new javax.swing.JPopupMenu();
        Inactivar = new javax.swing.JMenuItem();
        Activar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        FechaAc2 = new javax.swing.JLabel();
        jTxtPrd = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        BtnAgregar = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jTxtPrecio = new javax.swing.JTextField();
        jTxtPrecio1 = new javax.swing.JTextField();
        jCategoria = new javax.swing.JComboBox<>();
        jStockMin = new javax.swing.JSpinner();
        jStock = new javax.swing.JSpinner();
        jStockMax = new javax.swing.JSpinner();
        lbBarra = new javax.swing.JLabel();
        jTxtBarras = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTextProductos = new javax.swing.JTextField();
        jCategoria1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();

        Inactivar.setText("Inactivar");
        Inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactivarActionPerformed(evt);
            }
        });
        jStates.add(Inactivar);

        Activar.setText("Activar");
        Activar.setToolTipText("");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        jStates.add(Activar);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "PRODUCTOS", "PRECIO", "CATG.", "STOCK MIN", "EXISTENCIA", "STOCK MAX", "ESTADO", "BARRA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jStates);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 830, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 490, 20));

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      P R O D U C T O S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 260, -1));

        jTxtPrd.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Producto"));
        jTxtPrd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtPrdKeyTyped(evt);
            }
        });
        jPanel2.add(jTxtPrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 350, 50));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 490, 20));

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
        jPanel2.add(BtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 100, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 10, 70));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 10, 70));

        jTxtPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Precio en $"));
        jTxtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtPrecioKeyTyped(evt);
            }
        });
        jPanel2.add(jTxtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 170, 50));

        jTxtPrecio1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Equivalente en Bsf"));
        jPanel2.add(jTxtPrecio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 170, 50));

        jCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria"));
        jCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(jCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 350, 50));

        jStockMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        jStockMin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Stock Min")));
        jPanel2.add(jStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, 40));

        jStock.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        jStock.setBorder(javax.swing.BorderFactory.createTitledBorder("Existencia"));
        jPanel2.add(jStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 350, -1));

        jStockMax.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
        jStockMax.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Max"));
        jPanel2.add(jStockMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 170, 40));

        lbBarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(lbBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 350, 120));

        jTxtBarras.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Serial Código de Barras (Opcional)"));
        jTxtBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtBarrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtBarrasKeyTyped(evt);
            }
        });
        jPanel2.add(jTxtBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 350, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 400, 590));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar productos"));
        jTextProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextProductosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextProductosKeyReleased(evt);
            }
        });
        jPanel4.add(jTextProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 50));

        jCategoria1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Categoria"));
        jCategoria1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCategoria1ItemStateChanged(evt);
            }
        });
        jPanel4.add(jCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 240, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 830, 80));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
int stockMin = (Integer) jStockMin.getValue();
int stockMax = (Integer) jStockMax.getValue();
// int stockActual = (Integer) jStock.getValue();
int row = jTable1.getRowCount();
Object[][] content = new Object[row][2]; // Array bidimensional para almacenar columnas 2 (producto) y 4 (categoría)

// Llenando el array 'content' con los valores de las columnas 2 y 4
for (int i = 0; i < row; i++) {
    content[i][0] = jTable1.getValueAt(i, 2); // Columna 2 (producto)
    content[i][1] = jTable1.getValueAt(i, 4); // Columna 4 (categoría)
}

Object value_to_find = jTxtPrd.getText(); // Valor del producto a buscar
Object value_to_find2 = jCategoria.getSelectedItem().toString(); // Valor de la categoría a buscar
boolean exist = false;  // Variable para verificar si el producto con la categoría ya existe

// Verificando si el producto con la misma categoría ya existe en la tabla
for (int i = 0; i < row; i++) {
    if (content[i][0].equals(value_to_find) && content[i][1].equals(value_to_find2)) {
        exist = true;  // Si encuentra el producto con la misma categoría, marcar como existente
        break; // Salir del bucle si ya se encuentra el producto con la categoría
    }
}

// Validaciones antes de agregar un nuevo producto
if (jTxtPrd.getText().equals("") || jTxtPrecio.getText().equals("")) {
    JOptionPane.showMessageDialog(this, "DEBE COMPLETAR TODOS LOS CAMPOS", "CAMPOS", JOptionPane.WARNING_MESSAGE);
} else if (exist) {
    JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA EXISTE EN ESTA CATEGORÍA", "PRODUCTOS", JOptionPane.ERROR_MESSAGE);
} else if (stockMin == stockMax) {
    JOptionPane.showMessageDialog(this, "EL STOCK MINIMO NO PUEDE SER IGUAL AL STOCK MAXIMO", "CAMPOS", JOptionPane.WARNING_MESSAGE);
} else {
    agregarServicio();  
    auditoriAgregar();  
    limpiarTabla();      // Limpiar la tabla
    listarProductos();   // Listar los productos
    limpiarCampos();     // Limpiar los campos

}

      
     
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
    int stockMin= (Integer) jStockMin.getValue();
    int stockMax= (Integer) jStockMax.getValue();
   
          if(jTxtPrd.getText().equals("")|| jTxtPrecio.getText().equals("")){
        
        JOptionPane.showMessageDialog(this, "DEBE COMPLETAR TODOS LOS CAMPOS", "CAMPOS", 1);
        }
          else if (stockMin==stockMax){
         JOptionPane.showMessageDialog(this, "EL STOCK MINIMO NO PUEDE SER IGUAL AL STOCK MAXIMO", "CAMPOS", 1);
        
        }
        else{
         
          actualizarProducto() ;
         
        }
        

    }//GEN-LAST:event_BtnModificarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
    limpiarCampos();
      limpiarTabla();
       listarProductos();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void jTxtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPrecioKeyReleased
          if (!jTxtPrecio.getText().isEmpty()) {
            calcularMonto();
       }
          
          else{jTxtPrecio1.setText("");};
        
    }//GEN-LAST:event_jTxtPrecioKeyReleased

    private void jCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCategoriaActionPerformed

    
    
    int idServc;
    String servCodigo, servName, servPrecio, servCategoria, servEstado;
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int fila = jTable1.getSelectedRow();
if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
} else {
    // Habilitar y deshabilitar botones
    BtnModificar.setEnabled(true);
    BtnAgregar.setEnabled(false);

    // Obtener valores de la fila seleccionada
    idServc = (int) jTable1.getValueAt(fila, 0);
    String categoria = jTable1.getValueAt(fila, 4).toString();
    String producto = jTable1.getValueAt(fila, 2).toString();
    String precio = jTable1.getValueAt(fila, 3).toString();
    Object stockMin = jTable1.getValueAt(fila, 5);
    Object stock = jTable1.getValueAt(fila, 6);
    Object stockMax = jTable1.getValueAt(fila, 7);
    String estado = jTable1.getValueAt(fila, 8).toString();
    String codigoBarras = jTable1.getValueAt(fila, 9) != null ? jTable1.getValueAt(fila, 9).toString() : "";

    // Asignar los valores a los componentes correspondientes
    jCategoria.setSelectedItem(categoria);
    jTxtPrd.setText(producto);
    jTxtPrecio.setText(precio);
    jStockMin.setValue(stockMin);
    jStock.setValue(stock);
    jStockMax.setValue(stockMax);
    servEstado = estado;

    // Actualizar código de barras si es necesario
    if (!codigoBarras.isEmpty()) {
        jTxtBarras.setText(codigoBarras);
        actualizarCodigoDeBarras();
    } else {
        jTxtBarras.setText("");
        lbBarra.setIcon(null);
    }

  
    calcularMonto();
}

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextProductosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextProductosKeyPressed

    
    private Thread searchThread;
    private final int DELAY = 500;
    
    private void jTextProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextProductosKeyReleased
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
            if(!jTextProductos.getText().equals("")){
            searchBy(params -> new ArrayList<>(factDao.searchProductos(params[0])));
            }
            else{      
                limpiarTabla();
                listarProductos();}
    

             
            });
        });

        searchThread.start();
           
    }//GEN-LAST:event_jTextProductosKeyReleased

    private void InactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactivarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Inactivar este producto?",
            "Inactivar PRODUCTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (servEstado.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "EL PRODUCTO YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarProductos(101, idServc )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL PRODUCTO SE HA ACTUALIZADO HA : Inactivo", "Estado", 1);
                limpiarTabla();
                listarProductos();
             
                audtiPrd("Inactivo el producto");

            }
        }

        }
    }//GEN-LAST:event_InactivarActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
       if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Activar este producto?",
            "ACTIVAR PRODUCTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (servEstado.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL PRODUCTO YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarProductos(100, idServc )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL PRODUCTO SE HA ACTUALIZADO HA : Activo", "Estado", 1);
                limpiarTabla();
                listarProductos();
             
                audtiPrd("Activo el producto");

            }
        }

        }
    }//GEN-LAST:event_ActivarActionPerformed

    
    
    private String imagePath = ""; 
    private void jTxtBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtBarrasKeyReleased

        if(!jTxtBarras.getText().equals("")){
        actualizarCodigoDeBarras();
        }else{
        jTxtBarras.setText("");
        lbBarra.setIcon(null);
        }
       
        
        
    }//GEN-LAST:event_jTxtBarrasKeyReleased

    private void jTxtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPrecioKeyTyped
       va.longitud(jTxtPrecio.getText(), 40, evt);
    }//GEN-LAST:event_jTxtPrecioKeyTyped

    private void jTxtPrdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPrdKeyTyped
        va.longitud(jTxtPrd.getText(), 80, evt);
    }//GEN-LAST:event_jTxtPrdKeyTyped

    private void jTxtBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtBarrasKeyTyped
        va.longitud(jTxtBarras.getText(), 100, evt);
    }//GEN-LAST:event_jTxtBarrasKeyTyped

    private void jCategoria1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCategoria1ItemStateChanged

        if(jCategoria1.getSelectedIndex()!=-1){

            if(!jCategoria1.getSelectedItem().equals("Todos")){
                searchByCat(params -> new ArrayList<>(factDao.searchCategoria(params[0])));

              //  conteoTabla();

                jTable1.requestFocusInWindow();

            }

            else{

                limpiarTabla();
                listarProductos();
            //    conteoTabla();
                //  jEstado.setSelectedItem("Todos");
                jTable1.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_jCategoria1ItemStateChanged

    
      public void searchByCat(Function<String[], ArrayList<JCFactura>> searchFunction) {
    String[] params = { jCategoria1.getSelectedItem().toString() };

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
       public void findCategoria() {
    try {
        jCategoria1.removeAllItems(); // Limpia el combo por si acaso

        // Primero agrega "Todos"
        jCategoria1.addItem("Todos");

        // Luego llena con los datos reales
        ArrayList<String> lista3 = lc.llenarCatgPrd();
        for (int i = 0; i < lista3.size(); i++) {
            jCategoria1.addItem(lista3.get(i));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    
    private void actualizarCodigoDeBarras() {
    
        String texto = jTxtBarras.getText();

   
        generarCodigoDeBarras(texto, lbBarra);
    }
    

    public ImageIcon resizeImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }

        // Carga la imagen desde el archivo
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        
        // Redimensiona la imagen al tamaño del JLabel
        Image resizedImage = image.getScaledInstance(lbBarra.getWidth(), lbBarra.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    
    
private double equivalenciaBsf=0; 
public void calcularMonto(){
showDivisa();

double tasaBsf = Double.parseDouble(divisa);
double precioActual=Double.parseDouble(jTxtPrecio.getText());


equivalenciaBsf=precioActual*tasaBsf;
jTxtPrecio1.setText(String.format("%.2f", equivalenciaBsf)); 

}

    
    
    
String codeServ;
public void knowCode() {
    EnlaceBd cn = new EnlaceBd();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT MAX(code_prd) AS max_codigo FROM table_productos";

        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            String maxCodigo = rs.getString("max_codigo");

            if (maxCodigo != null && !maxCodigo.isEmpty()) {
                try {
                    // Verificar el formato esperado 'SERV-0001'
                    String numeroParte = maxCodigo.substring(5); // Extraer la parte numérica (asumiendo formato 'SERV-0001')
                    int numero = Integer.parseInt(numeroParte); // Convertir a número
                    numero++; // Incrementar el número
                    codeServ = String.format("PRD-%04d", numero); // Formatear el nuevo código
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato del código recuperado: " + maxCodigo);
                    codeServ = "PRD-0001"; // Valor por defecto en caso de error
                }
            } else {
                // Si no hay códigos existentes, iniciar con 'SERV-0001'
                codeServ = "PRD-0001";
            }
        }

    } catch (Exception e) {
        System.out.println("Error al obtener el código: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar recursos: " + ex.getMessage());
        }
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

 
 
 
 
  
  LlenarCombobox lc = new LlenarCombobox();
     
      public void llenarCombo() {

    
           
        try{
   
        jCategoria.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3 = lc.llenarCatgPrd();
        for (int i = 0; i < lista3.size(); i++) {
           jCategoria.addItem(lista3.get(i));
        }
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
           
          }
    
      
 

    
    
      
      
      
      
      
      
      
    
    //AGREGAR SERVICIOS
   public void agregarServicio(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    int stockMin= (Integer) jStockMin.getValue();
    int stockMax= (Integer) jStockMax.getValue();
    int stockActual= (Integer) jStock.getValue();
    
 
    try {

           knowCode();
           
           
            String sql = "INSERT INTO `table_productos`( `code_prd`, `producto`, `precio`, `id_catgPrd`, `existencia`, `stockmin`, `stockmax`, `serial_barra`) VALUES (?,?,?,(SELECT id_ctgPrd FROM categorias_productos WHERE Catg_Prd=?),?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, codeServ);
            ps.setString(2, jTxtPrd.getText());
            ps.setString(3, jTxtPrecio.getText());   
            ps.setString(4, jCategoria.getSelectedItem().toString());
            ps.setInt(5, stockActual);
            ps.setInt(6, stockMin);
            ps.setInt(7, stockMax);
            ps.setString(8, jTxtBarras.getText());
      
            ps.execute();
  
            

         JOptionPane.showMessageDialog(null, "SE HA REGISTRADO EL PRODUCTO","REGISTRO", 1);
        } catch (Exception e) {
            System.out.println(e);
        //    JOptionPane.showMessageDialog(null, e);
        }  finally {
            closeResources(rs, ps, con);
        }

  
          
    } 
   
   
   
   
      public void actualizarProducto() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    int stockMin= (Integer) jStockMin.getValue();
    int stockMax= (Integer) jStockMax.getValue();
    int stockActual= (Integer) jStock.getValue();
    
       //  boolean tieneFotoFirma = (imagePath != null && !imagePath.trim().isEmpty());
       // try (InputStream fotoFirma = tieneFotoFirma ? new FileInputStream(new File(imagePath)) : null)
        try {
           
            String sql = "UPDATE table_productos SET producto=?, precio=?, id_catgPrd=(SELECT id_ctgPrd FROM categorias_productos WHERE Catg_Prd=?), existencia=?, stockmin=?, stockmax=?, serial_barra=? WHERE id_producto=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
   
        
            ps.setString(1, jTxtPrd.getText());
            ps.setString(2, jTxtPrecio.getText());   
            ps.setString(3, jCategoria.getSelectedItem().toString());
            ps.setInt(4, stockActual);
            ps.setInt(5, stockMin);
            ps.setInt(6, stockMax);
            ps.setString(7, jTxtBarras.getText());
            ps.setInt(8 , idServc);
            
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PRODUCTO ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
                auditoriaModificar();
                limpiarTabla();
                listarProductos();
                limpiarCampos();
             
                
                
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

   
   
   
   
 
    
    
    
    
  public void searchBy(Function<String[], ArrayList<JCFactura>> searchFunction) {
    String[] params = { jTextProductos.getText() };

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
        "CATG.", "STOCK MIN", "EXISTENCIA", "STOCK MAX", 
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
    
     
     
  public void acomodarceldas() {
    DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
    DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
    alinear.setHorizontalAlignment(SwingConstants.CENTER);

    // Nombres de las columnas y sus anchos respectivos
    String[] columnas = {
        "ID", "CODIGO", "PRODUCTOS", "PRECIO",
        "CATG.", "STOCK MIN", "EXISTENCIA",
        "STOCK MAX", "ESTADO", "BARRA"
    };
    
    int[] anchos = {10, 40, 280, 30, 80, 20, 20, 20, 20, 30};

    for (int i = 0; i < columnas.length; i++) {
        int col = tabla.findColumn(columnas[i]);
        if ("ID".equals(columnas[i])) {
    TableColumn colId = jTable1.getColumnModel().getColumn(col);
    colId.setMinWidth(0);
    colId.setMaxWidth(0);
    colId.setPreferredWidth(0);
    continue;
}
        
        if (col != -1) {
            jTable1.getColumnModel().getColumn(col).setPreferredWidth(anchos[i]);
            jTable1.getColumnModel().getColumn(col).setCellRenderer(alinear);
        } else {
            System.err.println("⚠️  Columna no encontrada: " + columnas[i]);
        }
    }
}
   
   
   
   
   
   
    public void limpiarCampos(){
    
    jTxtPrd.setText("");
    jTxtPrecio.setText("");
    jTxtPrecio1.setText("");
    jTextProductos.setText("");
    jStockMax.setValue(0);
    jStockMin.setValue(0);
    jStock.setValue(0);
    imagePath="";
    lbBarra.setIcon(null);
        
    BtnModificar.setEnabled(false);
    BtnAgregar.setEnabled(true);
    }
        
     
    
   String divisa, fechDivisa;
   int idDivisa; 
   public void showDivisa() {

   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
   
        try {

            String sql = "SELECT valor_dia\n" +
            "FROM table_divisas\n" +
            "WHERE id_divisa = (SELECT MAX(id_divisa) FROM table_divisas);";

            con = cn.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
               // idDivisa= rs.getInt("id_divisa");
                divisa = rs.getString("valor_dia");
              //  fechDivisa = rs.getString("Fecha");
              
            }

        } catch (Exception e) {System.out.println(e);
        }
        
    finally {
            closeResources(rs, ps, con);
        }
    }
  
  

   
   
    
   
    public void auditoriAgregar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" REGISTRO UN NUEVO PRODUCTO: "+ jTxtPrd.getText();
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
   
   
    
   
    public void auditoriaModificar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "HORA: "+Menu.Time.getText()+" MODIFICO UN PRODUCTO: "+ jTxtPrd.getText();
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
   
   
   
       
      public void audtiPrd(String Accion){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= Accion+" "+jTxtPrd.getText() ;
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
   
   
   
   
  public void generarCodigoDeBarras(String texto, JLabel lbBarra) {
        try {
            
            
            // Crear el generador de código de barras (por ejemplo, código 39)
            Code39Bean barcodeBean = new Code39Bean();
            final int dpi = 150; // Resolución de la imagen
            barcodeBean.setModuleWidth(1.0); // Ancho del módulo
            barcodeBean.setWideFactor(3.0); // Factor de ancho
            barcodeBean.setHeight(50); // Altura del código de barras

            // Crear el Canvas para generar la imagen
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // Generar el código de barras
            barcodeBean.generateBarcode(canvas, texto);
            canvas.finish();

            // Convertir la imagen a BufferedImage para mostrarla
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            BufferedImage barcodeImage = ImageIO.read(bais);

            // Establecer la imagen del JLabel
            lbBarra.setIcon(new ImageIcon(barcodeImage));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
           System.out.println(e.getMessage());
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





   JCFacturaDao factDao=  new JCFacturaDao();
   JCFactura fac= new JCFactura();
   DefaultTableModel modelo = new DefaultTableModel();
      
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
  String niveluser=TM.getNivel();   
  JCambiarState setState = new JCambiarState();   

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JMenuItem Inactivar;
    private javax.swing.JComboBox<String> jCategoria;
    private javax.swing.JComboBox<String> jCategoria1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPopupMenu jStates;
    private javax.swing.JSpinner jStock;
    private javax.swing.JSpinner jStockMax;
    private javax.swing.JSpinner jStockMin;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextProductos;
    private javax.swing.JTextField jTxtBarras;
    private javax.swing.JTextField jTxtPrd;
    private javax.swing.JTextField jTxtPrecio;
    private javax.swing.JTextField jTxtPrecio1;
    private javax.swing.JLabel lbBarra;
    // End of variables declaration//GEN-END:variables
}
