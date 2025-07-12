/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCPacientes;
import Clases.JCSeguridad;
import Clases.JPacientesDao;
import Clases.JSeguridadDao;
import Clases.Temporal;
import Clases.Validar;
import Menu.Mprincipal;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JSeguridad extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JSeguridad() {
           initComponents();
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        Calendar Fecha = new GregorianCalendar();
limpiarTabla();
listarSeguridad();
acomodarceldas();

    }

    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Txtrespuesta3 = new javax.swing.JTextField();
        JCpregunta1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        JCpregunta2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Txtrespuesta1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JCpregunta3 = new javax.swing.JComboBox<>();
        Txtrespuesta2 = new javax.swing.JTextField();
        TxtClave = new javax.swing.JTextField();
        TxtUsuario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        BtnModificar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableSeguridad = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        TxtBusuario = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setBorder(null);
        setTitle("seguridad");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SEGURIDAD CONTRASEÑA"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Txtrespuesta3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Respuesta 3"));
        jPanel2.add(Txtrespuesta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 150, 50));

        JCpregunta1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿Cual es tu ciudad favorita?", "¿Cual es tu video juego favorito?", "¿Cual es tu sabor de helado favorito?" }));
        jPanel2.add(JCpregunta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 210, 40));

        jLabel1.setText("Pregunta 1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        JCpregunta2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿Genero musical preferido?", "¿Nombre de tu primer amor?", "¿Pasatiempo preferido?" }));
        jPanel2.add(JCpregunta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 210, 40));

        jLabel2.setText("Pregunta 2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        Txtrespuesta1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Respuesta 1"));
        jPanel2.add(Txtrespuesta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 150, 50));

        jLabel3.setText("Pregunta 3");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        JCpregunta3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿Idioma favorito?", "¿Color preferido?", "¿Nombre de un amigo de la infancia?" }));
        JCpregunta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCpregunta3ActionPerformed(evt);
            }
        });
        jPanel2.add(JCpregunta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 210, 40));

        Txtrespuesta2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Respuesta 2"));
        jPanel2.add(Txtrespuesta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 150, 50));

        TxtClave.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contraseña"));
        jPanel2.add(TxtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 370, 50));

        TxtUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuario"));
        jPanel2.add(TxtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 370, 50));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 410, 10));

        BtnModificar.setText("Modificar");
        BtnModificar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnModificar.setContentAreaFilled(false);
        BtnModificar.setEnabled(false);
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 90, 30));

        jButton5.setText("Limpiar");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 90, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 10, 60));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 10, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 580));

        JTableSeguridad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USUARIO", "CONTRASEÑA", "PREGUNTA1", "RESPUESTA1", "PREGUNTA2", "RESPUESTA2", "PREGUNTA3", "RESPUESTA3"
            }
        ));
        JTableSeguridad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableSeguridadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableSeguridad);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 820, 490));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar usuario"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtBusuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuario"));
        TxtBusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBusuarioKeyReleased(evt);
            }
        });
        jPanel4.add(TxtBusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 360, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 820, 80));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 1290, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
limpiarSeguridad();
BtnModificar.setEnabled(false);

    }//GEN-LAST:event_jButton5ActionPerformed

    
   
    
    
    
    
    
    
    
    
    
    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
   
        
        
        
           if (TxtUsuario.getText().equals("") || TxtClave.getText().equals("")
            || Txtrespuesta1.getText().equals("") || Txtrespuesta2.getText().equals("") || Txtrespuesta3.getText().equals("") ) {
            
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS", "LLENADO DE CAMPOS", JOptionPane.INFORMATION_MESSAGE);

        } 
           
           
           
           
           
           
           
           
           else {
        AuditoriaModificar();
        modificarSeguridad();
        limpiarTabla();
        listarSeguridad();       
               
      }
    }//GEN-LAST:event_BtnModificarActionPerformed

    
    
    
    
    public void modificarSeguridad(){
       Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    
           if (JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea modificar los datos de usuario?",
            "Cerrar Sesión", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)  {        
    
    
    
    
    try {
           

            
            String sql = "update table_usuario set Usuario=?, Clave=?, Pregunta1=?, Respuesta1=?, Pregunta2=?, Respuesta2=?, Pregunta3=?, Respuesta3=?  where IdPersonal=? ";

            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
             ps.setString(1, TxtUsuario.getText());;
            ps.setString(2, encriptar.ecnode(String.valueOf(TxtClave.getText())));
            ps.setString(3, JCpregunta1.getSelectedItem().toString());
            ps.setString(4, encriptar.ecnode(String.valueOf(Txtrespuesta1.getText())));
            ps.setString(5, JCpregunta2.getSelectedItem().toString());
            ps.setString(6, encriptar.ecnode(String.valueOf(Txtrespuesta2.getText())));
            ps.setString(7, JCpregunta3.getSelectedItem().toString());
            ps.setString(8, encriptar.ecnode(String.valueOf(Txtrespuesta3.getText())));
            
            
            ps.setInt(9, idSeguridad);

            int res = ps.executeUpdate();

            if (res >= 1) {
                JOptionPane.showMessageDialog(null, "DATOS DE USUARIO MODIFICADOS "   , "ACTUALIZAR DATOS", 1);
      
        
        
            } else {
                JOptionPane.showMessageDialog(null, "Fallo al actualizar  ....");
            }
            
            
            
            
        } catch (Exception e) {
           {
               JOptionPane.showMessageDialog(null, "NO PUEDES ACTUALIZAR UN USUARIO CON LA DATA DE OTRO PREVIAMENTE REGISTRADO", "ACTUALIZAR DATOS", 1);
               
               System.out.println(e); }
        } finally {
            closeResources(rs, ps, con);
        }
   }

               
               
      }
    
    
    
    
    
    
    
    
    
    
    
   public void limpiarSeguridad(){
                TxtUsuario.setText("");
                TxtClave.setText("");
                Txtrespuesta1.setText("");
                Txtrespuesta2.setText("");
                Txtrespuesta3.setText("");
            }
    
    
    int idSeguridad;
    private void JTableSeguridadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableSeguridadMouseClicked
   int fila = JTableSeguridad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
        } else {
   

          
            idSeguridad=(int) (JTableSeguridad.getValueAt(fila, 0));
            TxtUsuario.setText(JTableSeguridad.getValueAt(fila, 1).toString());
            TxtClave.setText(encriptar.deecnode(JTableSeguridad.getValueAt(fila, 2).toString()));
           
            JCpregunta1.setSelectedItem(JTableSeguridad.getValueAt(fila, 3).toString());
            Txtrespuesta1.setText(encriptar.deecnode(JTableSeguridad.getValueAt(fila, 4).toString()));
            
            JCpregunta2.setSelectedItem(JTableSeguridad.getValueAt(fila, 5).toString());
            Txtrespuesta2.setText(encriptar.deecnode(JTableSeguridad.getValueAt(fila, 6).toString()));
           
            JCpregunta3.setSelectedItem(JTableSeguridad.getValueAt(fila, 7).toString());
            Txtrespuesta3.setText(encriptar.deecnode(JTableSeguridad.getValueAt(fila, 8).toString()));
          
            BtnModificar.setEnabled(true);
    }                                           
    }//GEN-LAST:event_JTableSeguridadMouseClicked

    
    
    
    

    
    private void TxtBusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusuarioKeyReleased
        searchbyUser();
        acomodarceldas();
       

    }//GEN-LAST:event_TxtBusuarioKeyReleased

    private void JCpregunta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCpregunta3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCpregunta3ActionPerformed

    

   
    String usuario;
    String clave;
    
    String pregunta1;
    String pregunta2;
    String pregunta3;
    
    String respuesta1;
    String respuesta2;
    String respuesta3;
    
    
    
   public void listarSeguridad() {

        List<JCSeguridad> lista = SeguridadDao.listarSeguridad();
        modelo = (DefaultTableModel) JTableSeguridad.getModel();
        Object[] ob = new Object[10];

        for (int i = 0; i < lista.size(); i++) {

            ob[0] = lista.get(i).getIdUser();
            ob[1] = lista.get(i).getUsuario();
            ob[2] = lista.get(i).getClave();
            ob[3] = lista.get(i).getPregunta1();
            ob[4] = lista.get(i).getRespuesta1();
            ob[5] = lista.get(i).getPregunta2();
            ob[6] = lista.get(i).getRespuesta2();
            ob[7] = lista.get(i).getPregunta3();
            ob[8] = lista.get(i).getRespuesta3();
            modelo.addRow(ob);

        }
      JTableSeguridad.setModel(modelo);
        
            
     JTableSeguridad.getColumnModel().getColumn(0).setMaxWidth(0);
     JTableSeguridad.getColumnModel().getColumn(0).setMinWidth(0);
     JTableSeguridad.getColumnModel().getColumn(0).setPreferredWidth(0);
     JTableSeguridad.setDefaultEditor(Object.class, null);
    }
    
        
   void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
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
            String accion= "Modificó los datos de usuario de: "+ TxtUsuario.getText() ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ps.setInt(2, idusuario);
            ps.setString(3,accion);
            ps.setString(4,Fecha);
   

             ps.executeUpdate();

            
        } catch (Exception e) {System.out.println(e +"1"); }
 
             
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
    
   
   
   
    
       public void acomodarceldas()
    {
    
        JTableSeguridad.setRowHeight(32);
        DefaultTableModel Tabla = (DefaultTableModel)JTableSeguridad.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
       JTableSeguridad.getColumnModel().getColumn(0).setMaxWidth(0);
       JTableSeguridad.getColumnModel().getColumn(0).setMinWidth(0);
       JTableSeguridad.getColumnModel().getColumn(0).setPreferredWidth(0);
        
       
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("USUARIO")).setPreferredWidth(60);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("CONTRASEÑA")).setPreferredWidth(60);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA1")).setPreferredWidth(60);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA1")).setPreferredWidth(70);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA2")).setPreferredWidth(70);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA2")).setPreferredWidth(70);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA3")).setPreferredWidth(60);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA3")).setPreferredWidth(60);
         
        
      
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("USUARIO")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("CONTRASEÑA")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA1")).setCellRenderer(Alinear);;
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA1")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA2")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA2")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("PREGUNTA3")).setCellRenderer(Alinear);
        JTableSeguridad.getColumnModel().getColumn(Tabla.findColumn("RESPUESTA3")).setCellRenderer(Alinear);
    
    }
   
   
   public void searchbyUser() {
           
            Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;
   
 String Busqueda = this.TxtBusuario.getText();
 String sql = "SELECT IdPersonal, Usuario , Clave,  Pregunta1, Respuesta1,Pregunta2, Respuesta2,Pregunta3, Respuesta3 from table_usuario where Usuario LIKE '%" + Busqueda + "%' ";
        
        //DECLARACIÓN DEL MODELO DE LA TABLA
        DefaultTableModel Tabla = (DefaultTableModel)JTableSeguridad.getModel();
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
            }            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
        } finally {
            closeResources(rs, ps, con);
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

  JSeguridadDao SeguridadDao = new  JSeguridadDao();

  
  Temporal TM = new Temporal(); 
  int idusuario=TM.getTexto(); 
    
    
    DefaultTableModel modelo = new DefaultTableModel() {

    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
};

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnModificar;
    private javax.swing.JComboBox<String> JCpregunta1;
    private javax.swing.JComboBox<String> JCpregunta2;
    private javax.swing.JComboBox<String> JCpregunta3;
    private javax.swing.JTable JTableSeguridad;
    private javax.swing.JTextField TxtBusuario;
    private javax.swing.JTextField TxtClave;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JTextField Txtrespuesta1;
    private javax.swing.JTextField Txtrespuesta2;
    private javax.swing.JTextField Txtrespuesta3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
