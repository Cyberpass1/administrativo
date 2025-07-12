/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JCProcedimientos;
import Clases.JCProcedimientosDao;
import Clases.JCambiarState;
import Clases.JPacientesDao;
import Clases.LlenarCombobox;
import Clases.Temporal;
import Clases.Validar;
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

/**
 *
 * @author FCGinebraI
 */
public class JProcedimientos extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JProcedimientos() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
        limpiarTabla();
        listarProcedimientos();
        llenarCombo();
    }

  
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPopup = new javax.swing.JPopupMenu();
        Activar = new javax.swing.JMenuItem();
        Desactivar = new javax.swing.JMenuItem();
        delete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        FechaAc2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        BtnAg = new javax.swing.JButton();
        BtnLimpiar1 = new javax.swing.JButton();
        BtnModif = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        JComboEspecialidad = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        TXTfindbyName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableProcedimientos = new javax.swing.JTable();
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

        delete.setText("Eliminar");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        MenuPopup.add(delete);

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        FechaAc2.setText("M Ó D U L O      P R O C E D I M I E N T O S");
        jPanel2.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 370, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 30));

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Procedimiento"));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 360, 50));

        BtnAg.setText("Agregar");
        BtnAg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAg.setContentAreaFilled(false);
        BtnAg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 100, 30));

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

        JComboEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Especialidad"));
        jPanel2.add(JComboEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 360, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, 580));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar por"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXTfindbyName.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Procedimiento"));
        TXTfindbyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTfindbyNameKeyTyped(evt);
            }
        });
        jPanel4.add(TXTfindbyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 730, 100));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        JTableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PROCEDIMIENTO", "ESPECIALIDAD", "ESTADO"
            }
        ));
        JTableProcedimientos.setComponentPopupMenu(MenuPopup);
        JTableProcedimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableProcedimientosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableProcedimientos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 730, 480));

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
  
    int idprocedimientos =0;
    String EstadoTexto = "", nombreprocedimiento;
    private void JTableProcedimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableProcedimientosMouseClicked
 int fila = JTableProcedimientos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

            BtnModif.setEnabled(true);
            BtnAg.setEnabled(false);
            idprocedimientos=(int) (JTableProcedimientos.getValueAt(fila, 0));
            nombreprocedimiento=(JTableProcedimientos.getValueAt(fila, 1).toString());
            
            
            jTextField1.setText(JTableProcedimientos.getValueAt(fila, 1).toString());
            JComboEspecialidad.setSelectedItem(JTableProcedimientos.getValueAt(fila, 2).toString());
            EstadoTexto =JTableProcedimientos.getValueAt(fila, 3).toString();
    
    
    }                                  
    }//GEN-LAST:event_JTableProcedimientosMouseClicked

    private void BtnAgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgActionPerformed
      int row = JTableProcedimientos.getRowCount();
    Object[] content = new Object[row];
    for (int i = 0; i < row; i++) {
        content[i] = JTableProcedimientos.getValueAt(i, 2);
    }
    Object value_to_find= jTextField1.getText();
    boolean exist = Arrays.asList(content).contains(value_to_find);
        
           if (jTextField1.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
         else if (exist){
        JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO YA EXISTE", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
    }
         
                 else if (JComboEspecialidad.getSelectedItem().equals("Seleccionar")){
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECILIADAD VALIDA", "ESPECIALIDAD", JOptionPane.ERROR_MESSAGE);
    }
                        else if (JComboEspecialidad.getSelectedItem().equals("Administrativo")){
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECILIADAD VALIDA", "ESPECIALIDAD", JOptionPane.ERROR_MESSAGE);
    }
         
           else{
              AuditoriaCrearProc();
              AgregarProcedimiento(); 
              limpiarTabla(); 
              listarProcedimientos();  
              limpiarCampos(); 
            
           }
           
           
           
        
        
    }//GEN-LAST:event_BtnAgActionPerformed

    private void BtnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiar1ActionPerformed
            BtnModif.setEnabled(false);
            BtnAg.setEnabled(true);
             limpiarCampos();
    }//GEN-LAST:event_BtnLimpiar1ActionPerformed

    private void BtnModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModifActionPerformed

        
           if (jTextField1.getText().equals(""))  {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);
        }
         else if (JComboEspecialidad.getSelectedItem().equals("Seleccionar")){
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECILIADAD VALIDA", "ESPECIALIDAD", JOptionPane.ERROR_MESSAGE);
    }
         
               else if (JComboEspecialidad.getSelectedItem().equals("Administrativo")){
        JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA ESPECILIADAD VALIDA", "ESPECIALIDAD", JOptionPane.ERROR_MESSAGE);
    }
         
           else{
               AuditoriaModifProc();
              ActualizarProcedimiento();
              limpiarTabla(); 
              listarProcedimientos();  
              limpiarCampos(); 
             
     
           }
           
    }//GEN-LAST:event_BtnModifActionPerformed

    private void DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesactivarActionPerformed
  if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente inactivar este procedimiento?",
            "INACTIVAR PROCEDIMIENTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableProcedimientos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (EstadoTexto.equals("Inactivo")){ JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO YA SE ENCUENTA: Inactivo", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.InactivarProcedimiento(101,      idprocedimientos )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DEL PROCEDIMIENTO SE HA ACTUALIZADO HA : Inactivo", "Estado", 1);
           limpiarTabla();
           listarProcedimientos();
           acomodarceldas();
           //AuditoriaCancelarCita();
             
             
            }
        }

            
            
         }
    }//GEN-LAST:event_DesactivarActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
 if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente Activar el procedimiento?",
            "ACTIVAR PROCEDIMIENTO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {
         
            
            
            
            
        int fila = JTableProcedimientos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe selecionar una Fila");
        } else {
      
          if (EstadoTexto.equals("Activo")){ JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO YA SE ENCUENTA: Activo", "Estado", JOptionPane.WARNING_MESSAGE);}
          else if (setState.InactivarProcedimiento(100,      idprocedimientos )) {

                JOptionPane.showMessageDialog(null, "EL ESTADO DE LA CITA SE HA ACTUALIZADO HA : Activo", "Estado", 1);
           limpiarTabla();
           listarProcedimientos();
           acomodarceldas();
           //AuditoriaCancelarCita();
             
             
            }
        }

            
            
         }
    }//GEN-LAST:event_ActivarActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       eliminarPaciente();
    }//GEN-LAST:event_deleteActionPerformed

    
    
    
    
        
       
    public void eliminarPaciente() {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
     

      String mensaje = "¿Está seguro de que desea eliminar " + nombreprocedimiento + " de la tabla de procedimientos?";
      mensaje += "\nNOTA: Si existen pacientes con este procedimiento asignado y lo elimina, no podran visualizar los mismos";

   int confirmacion = JOptionPane.showConfirmDialog(
    rootPane,
    mensaje,
    "Confirmar eliminación",
    JOptionPane.YES_NO_OPTION
);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM `table_procedimientos` WHERE idProcedimiento = ?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprocedimientos);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "El procedimiento ha sido eliminado", "Actualización de Datos", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
                listarProcedimientos();
                AuditoriaBorrar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el procedimiento", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
    


      public void AuditoriaCrearProc(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Agrego un nuevo procedimiento: " +jTextField1.getText() +" " ;
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
    
    
    
      public void AuditoriaModifProc(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Modifico el procedimiento: " +jTextField1.getText() +" " ;
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
    
    
    
      public void AuditoriaBorrar(){
            
            
   Connection con=null;
   EnlaceBd cn = new EnlaceBd();
   PreparedStatement ps=null;
   ResultSet rs=null;
          

   try {
            
            String Fecha = new SimpleDateFormat("yyyy-MM-dd").format(Menu.FechaAdmin.getDate());
           
            String sql = "INSERT INTO table_auditoria (IdUsuario, IdPersonal, Accion,FechaMov) values (?,?,?,?)";
            String accion= "Elimino el procedimiento: " +jTextField1.getText() +" " ;
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
    
    
    
  
    
    
     public void listarProcedimientos() {

        List<JCProcedimientos> lista = ProcesosDao.listarProcedimientos();
        modelo = (DefaultTableModel) JTableProcedimientos.getModel();
        Object[] ob = new Object[5];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdprocedimiento();
            ob[1] = lista.get(i).getProcedimiento();
            ob[2] = lista.get(i).getCategoria();
            ob[3] = lista.get(i).getEstado();
            

            modelo.addRow(ob);

        }
       JTableProcedimientos.setModel(modelo);
        acomodarceldas();
 
     JTableProcedimientos.setDefaultEditor(Object.class, null);
    }
    
    
    public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)JTableProcedimientos.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        JTableProcedimientos.setRowHeight(20);
        

        
       
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ID")).setPreferredWidth(10);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("PROCEDIMIENTO")).setPreferredWidth(200);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setPreferredWidth(50);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setPreferredWidth(30);
         
        
      
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ID")).setCellRenderer(Alinear);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("PROCEDIMIENTO")).setCellRenderer(Alinear);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ESPECIALIDAD")).setCellRenderer(Alinear);
        JTableProcedimientos.getColumnModel().getColumn(Tabla.findColumn("ESTADO")).setCellRenderer(Alinear);
    
    }
            
    
    
  LlenarCombobox lc = new LlenarCombobox();
     
      public void llenarCombo() {

        
       try{
       
        
        JComboEspecialidad.removeAllItems();
        ArrayList<String> lista3 = new ArrayList<String>();
        lista3 = lc.llenarEspecialidad();
        for (int i = 0; i < lista3.size(); i++) {
            JComboEspecialidad.addItem(lista3.get(i));

        }
        
           }catch(Exception e ){JOptionPane.showMessageDialog(null,e );};
           
           
           
           
    
          }
    
     public void AgregarProcedimiento(){                                             
    
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    

          try {

              
 
        
        
 
        
        
              
              
            String sql = "INSERT INTO table_procedimientos (Procedimiento, Id_Categoria) VALUES (?,(SELECT id_especialidad FROM table_especialidad WHERE especialidad=?))";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, jTextField1.getText());
            ps.setString(2, JComboEspecialidad.getSelectedItem().toString());
         

            ps.execute();
  
            
         limpiarTabla();  listarProcedimientos(); limpiarCampos();
         JOptionPane.showMessageDialog(null, "EL PROCEDIMIENTO HA SIDO REGISTRADO","REGISTRO DE PROCEDIMIENTOS", 1);
        } catch (Exception e) {
      
            JOptionPane.showMessageDialog(null, e);
        }  finally {
            closeResources(rs, ps, con);
        }


      
          
          
    } 
    
    
      public void ActualizarProcedimiento() {
   
    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
          
            
            
            
            
            
 
            String sql = "update table_procedimientos set   Id_Categoria=(SELECT id_especialidad FROM table_especialidad WHERE especialidad=?),  Procedimiento=? where idProcedimiento=?";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, JComboEspecialidad.getSelectedItem().toString());
            ps.setString(2, jTextField1.getText());
            ps.setInt(3, idprocedimientos);
            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "PROCEDIMIENTO ACTUALIZADO", "ACTUALIZACIÓN DE DATOS", 1);
            
                
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR PROCEDIMIENTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL PROCEDIMIENTO", "PROCEDIMIENTOS", JOptionPane.ERROR_MESSAGE);
           }  finally {
            closeResources(rs, ps, con);
        }
                 
    }

     
     
     
     
     
     
          
      public void Search(){
              
                Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
 String Busqueda = this.TXTfindbyName.getText();
 String sql = "select idProcedimiento, Procedimiento, Especialidad, Estado   from table_procedimientos u\n" +
"INNER JOIN table_estado x\n" +
"ON u.Id_Pestado= x.IdEstado\n" +
"INNER JOIN table_especialidad c\n" +
" ON u.Id_Categoria= c.id_especialidad where Procedimiento LIKE '%" + Busqueda + "%' ";
        
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTableProcedimientos.getModel();
        JTableProcedimientos.setDefaultEditor(Object.class, null);
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

               
               Vector vRow=new Vector();
               vRow.add(col1);
               vRow.add(col2);
               vRow.add(col3);
               vRow.add(col4);


               Tabla.addRow(vRow);
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        }  finally {
            closeResources(rs, ps, con);
        }
  }
      
     
     
     
     
     
     
     
     
    
     public void limpiarCampos(){
     
     jTextField1.setText("");
     JComboEspecialidad.setSelectedItem("Seleccionar");
     TXTfindbyName.setText("");
     }
     
     
     
  public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) JTableProcedimientos.getModel();
        int a = JTableProcedimientos.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
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
  JCPacientes pacientes  = new JCPacientes();
  JCProcedimientosDao ProcesosDao  = new  JCProcedimientosDao();

  Mprincipal Menu = new Mprincipal();
  Encriptar encriptar = new Encriptar(); 
  JCambiarState setState = new JCambiarState();
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
    
     
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Activar;
    private javax.swing.JButton BtnAg;
    private javax.swing.JButton BtnLimpiar1;
    private javax.swing.JButton BtnModif;
    private javax.swing.JMenuItem Desactivar;
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JComboBox<String> JComboEspecialidad;
    private javax.swing.JTable JTableProcedimientos;
    private javax.swing.JPopupMenu MenuPopup;
    private javax.swing.JTextField TXTfindbyName;
    private javax.swing.JMenuItem delete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
