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
import Clases.Temporal;
import Clases.Validar;
import Clases.jCGastos;
import Clases.jCGastosDao;
import Menu.Mprincipal;
import java.awt.event.KeyEvent;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;






public class JGastos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JGastos() {
       initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
       Calendar Fecha = new GregorianCalendar();
       limpiarTabla();
       listarGastos();
knowNote();
       llenarCombo();
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
        Delete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        FechaAc2 = new javax.swing.JLabel();
        jTxtGasto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        BtnAgregar = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jCategoria = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        Inactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/archivo.png"))); // NOI18N
        Inactivar.setText("Inactivar");
        Inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactivarActionPerformed(evt);
            }
        });
        jStates.add(Inactivar);

        Activar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/autorizacion.png"))); // NOI18N
        Activar.setText("Activar");
        Activar.setToolTipText("");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        jStates.add(Activar);

        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/basura.png"))); // NOI18N
        Delete.setText("Eliminar");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jStates.add(Delete);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD", "GASTO", "PROVEEDOR", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
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
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 760, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 490, 20));

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      G A S T O S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 260, -1));

        jTxtGasto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Gasto"));
        jPanel2.add(jTxtGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 350, 50));
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
        jPanel2.add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 100, 30));

        BtnLimpiar.setText("Nuevo");
        BtnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar.setContentAreaFilled(false);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 100, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, 10, 70));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 10, 70));

        jCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));
        jCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(jCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 350, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 470, 590));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar gasto"));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 760, 80));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        
          int row = jTable1.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = jTable1.getValueAt(i, 1);
    }
    Object value_to_find= jTxtGasto.getText();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
        if (exist){
        JOptionPane.showMessageDialog(null, "EL GASTO YA EXISTE", "GASTOS", JOptionPane.ERROR_MESSAGE);
    }
        
        if(jTxtGasto.getText().equals("") || jCategoria.getSelectedItem().equals("")){
        
        JOptionPane.showMessageDialog(this, "DEBE COMPLETAR TODOS LOS CAMPOS", "CAMPOS", 1);
        }
        else if(jCategoria.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(this, "DEBE AGREGAR UN PROVEEDOR DESDE EL MÓDULO PROVEEDORES", "CAMPOS", 1);
        }
        else{
          agregarGasto();
          limpiarTabla();
          listarGastos();
          limpiarCampos();
          knowNote();
        }
        
      
     
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed

   
        if(jTxtGasto.getText().equals("") || jCategoria.getSelectedItem().equals("")){
        
        JOptionPane.showMessageDialog(this, "DEBE COMPLETAR TODOS LOS CAMPOS", "CAMPOS", 1);
        }
        
        else{
         
          actualizarGasto();
          limpiarTabla();
          listarGastos();
          limpiarCampos();
        }
        

    }//GEN-LAST:event_BtnModificarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
    limpiarCampos();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    
    
    int idServc;
    String servCodigo, servName, servPrecio, servCategoria, servEstado;
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
         int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            BtnModificar.setEnabled(true);
            BtnAgregar.setEnabled(false);
            idServc=(int) (jTable1.getValueAt(fila, 0));
          
            jTxtGasto.setText(jTable1.getValueAt(fila, 2).toString());
            jCategoria.setSelectedItem(jTable1.getValueAt(fila, 3).toString());
            servEstado=(jTable1.getValueAt(fila, 4).toString());
            
    }                                  
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
         Search(); acomodarceldas();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente activar este gasto?",
            "ACTIVAR GASTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (servEstado.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL GASTO YA SE ENCUENTA: activo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarGasto(100, idServc)) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL GASTO SE HA ACTUALIZADO HA : activo", "Estado", 1);
                limpiarTabla();
                listarGastos();
                audtiActivarServ("Activo el Gasto");
              //  audtDesactCatg();

            }
        }

        }
    }//GEN-LAST:event_ActivarActionPerformed

    private void InactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactivarActionPerformed
          if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente inactivar este gasto?",
            "INACTIVAR GASTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (servEstado.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "EL GASTO YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarGasto(101, idServc)) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL GASTO SE HA ACTUALIZADO HA : Inactivo", "Estado", 1);
                limpiarTabla();
                listarGastos();
                audtiActivarServ("Inactivo el Gasto");
              //  audtDesactCatg();

            }
        }

        }
    }//GEN-LAST:event_InactivarActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
      eliminarGasto();
    }//GEN-LAST:event_DeleteActionPerformed

    private void jCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCategoriaActionPerformed

  JCambiarState setState = new JCambiarState();   

    
    
    
String codeServ;
public void knowCode() {
    EnlaceBd cn = new EnlaceBd();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT MAX(serv_codigo) AS max_codigo FROM table_servicios";

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
                    codeServ = String.format("SERV-%04d", numero); // Formatear el nuevo código
                } catch (NumberFormatException e) {
                    System.out.println("Error en el formato del código recuperado: " + maxCodigo);
                    codeServ = "SERV-0001"; // Valor por defecto en caso de error
                }
            } else {
                // Si no hay códigos existentes, iniciar con 'SERV-0001'
                codeServ = "SERV-0001";
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



 
 
      public  void listarGastos() {

        List<jCGastos> lista = gastosDao.listarGastos();
        modelo = (DefaultTableModel) jTable1.getModel();
        Object[] ob = new Object[8];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdGasto();
            ob[1] = lista.get(i).getCodeGasto();
            ob[2] = lista.get(i).getGasto();
            ob[3] = lista.get(i).getProveedor();
            ob[4] = lista.get(i).getEstado();

            

            modelo.addRow(ob);

        }
       jTable1.setModel(modelo);
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
        lista3 = lc.llenarProveedores();
        for (int i = 0; i < lista3.size(); i++) {
           jCategoria.addItem(lista3.get(i));
        }
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
           
          }
    
      
 

    
    
    //AGREGAR SERVICIOS
   public void agregarGasto(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

            knowCode();
         
            String sql = "INSERT INTO `table_gastos`( code_gasto, `gasto`, `proveedor`) VALUES (?,?,(SELECT id_proveedor  FROM table_proveedor WHERE proveedor=?))";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, numFact);
            ps.setString(2, jTxtGasto.getText());
            ps.setString(3, jCategoria.getSelectedItem().toString());

            ps.execute();
  
            

         JOptionPane.showMessageDialog(null, "SE HA REGISTRADO EL GASTO","REGISTRO", 1);
        } catch (Exception e) {
            System.out.println(e);
        //    JOptionPane.showMessageDialog(null, e);
        }
          
        finally {
            closeResources(rs, ps, con);
        }

  
          
    } 
   
   
   
   
   
   
    public void Search(){
              
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
 String busqueda = this.jTextField2.getText();
String sql = "SELECT `id_gasto`, code_gasto ,`gasto`, p.proveedor , e.Estado, id_estado FROM table_gastos u\n" +
"INNER JOIN table_proveedor p\n" +
"ON u.proveedor = p.id_proveedor\n" +
"\n" +
"INNER JOIN table_estado e\n" +
"ON u.id_estado= e.IdEstado WHERE gasto LIKE ?";

        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)jTable1.getModel();
        jTable1.setDefaultEditor(Object.class, null);
        try
        {
            //SENTENCIA SQL Y VARIABLES PARA CONEXION Y CONSULTA
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + busqueda + "%");
            rs = ps.executeQuery();
            limpiarTabla();

            while(rs.next()) 
            {
                //LLENADO DE TABLA
               int col1 = rs.getInt("id_gasto");
               String col2 = rs.getString("code_gasto");
               String col3 = rs.getString("gasto");
               String col4 = rs.getString("p.proveedor");
               String col5 = rs.getString("e.Estado");


    

               
               Vector vRow=new Vector();
               vRow.add(col1);
               vRow.add(col2);
               vRow.add(col3);
               vRow.add(col4);
               vRow.add(col5);



               Tabla.addRow(vRow);
            }            
        }
        catch(Exception e)
        {
            System.out.println("Error en buscar: "+e);
        }
        
    
        finally {
            closeResources(rs, ps, con);
        }
  }
      
   
   
   
   
   
   
   
   
   
   
    public void actualizarGasto() {
    
    String sql = " UPDATE table_gastos SET gasto=?, proveedor=(SELECT id_proveedor  FROM table_proveedor WHERE proveedor=?) WHERE id_gasto=?";

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, jTxtGasto.getText());

        ps.setString(2, jCategoria.getSelectedItem().toString());
        ps.setInt(3, idServc);

        int res = ps.executeUpdate();
        if (res >= 1) {
            JOptionPane.showMessageDialog(null, "GASTO ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL GASTO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL SERVICIO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

     
     
     public void acomodarceldas()
    {
    
        
        DefaultTableModel Tabla = (DefaultTableModel)jTable1.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        

      
        
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("COD")).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("GASTO")).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PROVEEDOR")).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(30);
    
         
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("COD")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("GASTO")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("PROVEEDOR")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setCellRenderer(Alinear);
  

       
    
    }
   
   
   
   
   
   
    public void limpiarCampos(){
    
    jTxtGasto.setText("");

    jTextField2.setText("");
    
    
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
  
  

   
   
   
       
      public void audtiActivarServ(String Accion){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= Accion+" "+jTxtGasto.getText() ;
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
      
      

    
      
      
      
    public void eliminarGasto() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
     

      String mensaje = "¿Está seguro de que desea eliminar " + servName + " de la tabla de gastos?";
      mensaje += "\nNOTA: Si existen compras con este gastos asignado y lo elimina, no podran visualizar los mismos";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_gastos` WHERE id_gasto  = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idServc);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El gasto ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                listarGastos();
                audtiActivarServ("Elimino el siguiente gasto de la tabla: ");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el gasto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
        }
}
    
       
       
       
       String numFact;
       int idNote;
public void knowNote() {
    String sql = "SELECT MAX(id_gasto) AS max_id, MAX(code_gasto) AS max_codigo FROM table_gastos";
   

    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            idNote = rs.getInt("max_id");
            String maxCodigo = rs.getString("max_codigo");

            if (idNote == 0) {
                idNote = 1;
            }

            if (maxCodigo != null && !maxCodigo.isEmpty()) {
                try {
                    String numeroParte = maxCodigo.substring(maxCodigo.lastIndexOf('-') + 1);
                    int numero = Integer.parseInt(numeroParte);
                    numero++; // Incrementar el número
                    idNote++; // Incrementar el id si es necesario

                    numFact = String.format("GTO-%04d", numero);
                } catch (NumberFormatException e) {
                    System.err.println("Error en el formato del código recuperado: " + maxCodigo);
                    numFact = "GTO-0001";
                }
            } else {
                numFact = "GTO-0001";
            }

   
        }
    } catch (Exception e) {
        System.err.println("Error al obtener el código: " + e.getMessage());
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
    

   jCGastos gastos = new jCGastos();
   jCGastosDao gastosDao = new jCGastosDao();
             
   DefaultTableModel modelo = new DefaultTableModel();
   Temporal TM = new Temporal(); 
   int idusuario=TM.getTexto(); 
    
   
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JMenuItem Delete;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JMenuItem Inactivar;
    private javax.swing.JComboBox<String> jCategoria;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTxtGasto;
    // End of variables declaration//GEN-END:variables
}
