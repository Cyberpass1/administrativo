/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCEspecialidadDao;
import Clases.JCEspecialidades;
import Clases.JCFactura;
import Clases.JCFacturaDao;
import Clases.JCPacientes;
import Clases.JCambiarState;
import Clases.Temporal;
import Clases.Validar;
import Menu.Mprincipal;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 *
 * @author FCGinebraI
 */
public class JCategoriaProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JCategoriaProductos() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
listarEspecialidades();
    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPopup = new javax.swing.JPopupMenu();
        Activar = new javax.swing.JMenuItem();
        Desactivar = new javax.swing.JMenuItem();
        Delete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        TxtEspecialidad = new javax.swing.JTextField();
        BtnAgg = new javax.swing.JButton();
        BtnLimpiar1 = new javax.swing.JButton();
        BtnModif = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        TXTfindbyName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableEspecialidad = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();

        Activar.setText("Activar");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Activar);

        Desactivar.setText("Desactivar");
        Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesactivarActionPerformed(evt);
            }
        });
        MenuPopup.add(Desactivar);

        Delete.setText("Eliminar");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        MenuPopup.add(Delete);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("C A T E G O R I A      P R O D U C T O S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 370, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 30));

        TxtEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ctg. Productos"));
        jPanel2.add(TxtEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 360, 50));

        BtnAgg.setText("Agregar");
        BtnAgg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgg.setContentAreaFilled(false);
        BtnAgg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAggActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAgg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 100, 30));

        BtnLimpiar1.setText("Nuevo");
        BtnLimpiar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnLimpiar1.setContentAreaFilled(false);
        BtnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiar1ActionPerformed(evt);
            }
        });
        jPanel2.add(BtnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, 100, 30));

        BtnModif.setText("Modificar");
        BtnModif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModif.setContentAreaFilled(false);
        BtnModif.setEnabled(false);
        BtnModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModifActionPerformed(evt);
            }
        });
        jPanel2.add(BtnModif, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 100, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 500, 20));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 10, 70));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 10, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 570));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTfindbyName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categoria"));
        TXTfindbyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyTyped(evt);
            }
        });
        jPanel4.add(TXTfindbyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 270, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 730, 100));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTableEspecialidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIAS", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableEspecialidad.setComponentPopupMenu(MenuPopup);
        JTableEspecialidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableEspecialidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableEspecialidad);
        if (JTableEspecialidad.getColumnModel().getColumnCount() > 0) {
            JTableEspecialidad.getColumnModel().getColumn(0).setResizable(false);
            JTableEspecialidad.getColumnModel().getColumn(1).setResizable(false);
            JTableEspecialidad.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 730, 470));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTfindbyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyReleased
     Search(); acomodarceldas();
    }//GEN-LAST:event_TXTfindbyNameKeyReleased

    
    
    
    
    
    
    private void TXTfindbyNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTfindbyNameKeyTyped
     
    }//GEN-LAST:event_TXTfindbyNameKeyTyped

    
    
    
    int idespecialidades =0;
    String EstadoTexto = "";
    
    private void JTableEspecialidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableEspecialidadMouseClicked

        
        
         int fila = JTableEspecialidad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            BtnModif.setEnabled(true);
            BtnAgg.setEnabled(false);
            idespecialidades=(int) (JTableEspecialidad.getValueAt(fila, 0));
            TxtEspecialidad.setText(JTableEspecialidad.getValueAt(fila, 1).toString());
            EstadoTexto =JTableEspecialidad.getValueAt(fila, 2).toString();
    
    
    }                                  
        
        
        
        
        
    }//GEN-LAST:event_JTableEspecialidadMouseClicked

    private void BtnAggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAggActionPerformed
   int row = JTableEspecialidad.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = JTableEspecialidad.getValueAt(i, 2);
    }
    Object value_to_find= TxtEspecialidad.getText();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
           if (TxtEspecialidad.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
         else if (exist){
        JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO YA EXISTE", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
    }
         
      
           else{
               
              agregarCategoria();
              AuditoriaAggEsp();
              limpiarTabla(); 
              listarEspecialidades();  
              limpiarCampos(); 
          
           }
    }//GEN-LAST:event_BtnAggActionPerformed

    private void BtnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiar1ActionPerformed
      limpiarCampos();
    }//GEN-LAST:event_BtnLimpiar1ActionPerformed

    private void BtnModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifActionPerformed
             if (TxtEspecialidad.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
       
      
           else{
              if (JOptionPane.showConfirmDialog(rootPane, "Modificar una categoria actualizara la informacion en el modulo facturacion, aún asi; ¿desea modificar esta categoria?",
            "Modificar especialidad", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

              actualizarCategoria();
              AuditoriaModfEsp();
              limpiarTabla(); 
              listarEspecialidades();  
              limpiarCampos(); 
             
           }
           }
    }//GEN-LAST:event_BtnModifActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Activar esta categoria?",
            "ACTIVAR CATEGORIA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = JTableEspecialidad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (EstadoTexto.equals("Activo")){ JOptionPane.showMessageDialog(null, "LA CATEGORIA YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarCtgPrd(100,       idespecialidades )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CATEGORIA SE HA ACTUALIZADO HA : Activo", "Estado", 1);
                limpiarTabla();
                listarEspecialidades();
             
              audtiActivarCatg();

            }
        }

        }
    }//GEN-LAST:event_ActivarActionPerformed

    private void DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente inactivar esta categoria?",
            "INACTIVAR CATEGORIA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = JTableEspecialidad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

            if (EstadoTexto.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "LA CATEGORIA YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
            else if (setState.inactivarCtgPrd(101,       idespecialidades )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CATEGORIA SE HA ACTUALIZADO HA : Inactivo", "Estado", 1);
                limpiarTabla();
                listarEspecialidades();
             
                audtDesactCatg("Se desactivo la categoria");

            }
        }

        }
    }//GEN-LAST:event_DesactivarActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
      if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente eliminar esta categoria?",
            "INACTIVAR CATEGORIA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {

        int fila = JTableEspecialidad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {

           
           
                eliminarServicio();
                JOptionPane.showMessageDialog(null, "SE HA ELIMINADO LA CATEGORIA", "ELIMINAR", 1);
                limpiarTabla();
                listarEspecialidades();
             
                audtDesactCatg("Se eliminó la categoria");

            
        }

        }
    }//GEN-LAST:event_DeleteActionPerformed

  
  
   
       public void eliminarServicio() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
     

            String sql = "DELETE FROM categorias_productos WHERE id_ctgPrd = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidades);
            ps.executeUpdate();

      
        
    } catch (Exception e) {
        System.out.println(e);
    }  finally {
            closeResources(rs, ps, con);
        }
}
  
   
  
   public void agregarCategoria(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

             
        
              
              
            String sql = "INSERT INTO categorias_productos (Catg_Prd) VALUES (?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, TxtEspecialidad.getText());
         

            ps.execute();
  
            
         limpiarTabla();  listarEspecialidades(); limpiarCampos();
  JOptionPane.showMessageDialog(null, "CATEGORIA REGISTRADA","REGISTRO", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }

       finally {
            closeResources(rs, ps, con);
        }
          
          
    } 
   
   
   
   
   
   
      public void actualizarCategoria() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
 
            String sql = "UPDATE `categorias_productos` SET `Catg_Prd`=? WHERE `id_ctgPrd`=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, TxtEspecialidad.getText());
            ps.setInt(2, idespecialidades);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "CATEGORIA ACTUALIZADA", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA CATEGORIA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LA CATEGORIA", "ERROR", JOptionPane.ERROR_MESSAGE);
           } finally {
            closeResources(rs, ps, con);
        }
                 
    }

     
     
   
   
   
   
    
  
  
  public void limpiarCampos(){
  TxtEspecialidad.setText("");
  TXTfindbyName.setText("");
  BtnModif.setEnabled(false);
  BtnAgg.setEnabled(true);
  
  }
  
  

     public void listarEspecialidades() {

        List<JCFactura> lista = facDao.listarCatgPrd();
        modelo = (DefaultTableModel) JTableEspecialidad.getModel();
        Object[] ob = new Object[5];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdCatgServ();
            ob[1] = lista.get(i).getNameCatgServ();
            ob[2] = lista.get(i).getStateCatServ();
            

            modelo.addRow(ob);

        }
       JTableEspecialidad.setModel(modelo);
        acomodarceldas();
 
     JTableEspecialidad.setDefaultEditor(Object.class, null);
    }
     
     
     
     
     
     
     
     
     
    
    
    public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTableEspecialidad.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTableEspecialidad.setRowHeight(20);
        

        
       
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(60);
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("CATEGORIAS")).setPreferredWidth(60);
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(60);
         
        
      
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("CATEGORIAS")).setCellRenderer(Alinear);
        JTableEspecialidad.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setCellRenderer(Alinear);
    
    }
  
  
  
  
  
      public void AuditoriaAggEsp(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Agrego el la categoria de producto: " +TxtEspecialidad.getText() +" " ;
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
      
      
      
      public void AuditoriaModfEsp(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modifico la categoria laboratorio: " +TxtEspecialidad.getText() +" " ;
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
      
  
  
       
      public void audtiActivarCatg(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Activo la Categoria " +TxtEspecialidad.getText() +" " ;
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
      
      
      
      
       
      public void audtDesactCatg(String delete){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= delete+" " +TxtEspecialidad.getText() +" " ;
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
      
      
      
      
      
      
  
    public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) JTableEspecialidad.getModel();
        int a = JTableEspecialidad.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

  
    
        
      public void Search(){
              
                Connection con;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps;
    ResultSet rs;
   
 String Busqueda = this.TXTfindbyName.getText();
String sql = "SELECT id_ctgPrd, Catg_Prd, x.estado " +
             "FROM categorias_productos u " +
             "INNER JOIN table_estado x ON u.Estado_Prd = x.IdEstado " +
             "WHERE Catg_Prd LIKE '%" + Busqueda + "%'";

        
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTableEspecialidad.getModel();
        JTableEspecialidad.setDefaultEditor(Object.class, null);
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
    

               
               Vector vRow=new Vector();
               vRow.add(col1);
               vRow.add(col2);
               vRow.add(col3);
   


               Tabla.addRow(vRow);
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }
  }
      
     
    
    
    
      DefaultTableModel modelo = new DefaultTableModel();
  
      JCFactura fac =  new JCFactura();
      JCFacturaDao facDao =  new JCFacturaDao();

  JCambiarState setState = new JCambiarState();
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
     
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAgg;
    private javax.swing.JButton BtnLimpiar1;
    private javax.swing.JButton BtnModif;
    private javax.swing.JMenuItem Delete;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JTable JTableEspecialidad;
    private javax.swing.JPopupMenu MenuPopup;
    private javax.swing.JTextField TXTfindbyName;
    private javax.swing.JTextField TxtEspecialidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    // End of variables declaration//GEN-END:variables
}
