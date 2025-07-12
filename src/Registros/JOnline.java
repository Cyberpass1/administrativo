/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.JCEspecialidades;
import Clases.JCUsers;
import Clases.JUserDao;
import Clases.Validar;
import Menu.Mprincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author FCGinebraI
 */
public class JOnline extends javax.swing.JInternalFrame {

    /**
     * Creates new form JUREGISTRO
     */
    public JOnline() {
           initComponents();
       jrOnline.setSelected(true);
       ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
       limpiarTabla();
       listarOnline();
       refreshButtom();
       

    }

    

    public String fechaOnline;
    public void listarOnline() {
         List<JCUsers> lista = new ArrayList<>();
        
    try{
      if (jrOnline.isSelected()) {
            lista = listarUsers.listarOnline(); 
        } else {
            lista = listarUsers.listarTodosOn(); 
        }
     
     
     
    modelo = (DefaultTableModel) jTable1.getModel();
    Object[] ob = new Object[8];


    
    for (int i = 0; i < lista.size(); i++) {
    JCUsers user = lista.get(i);
    ob[0] = user.getIdonline() != 0 ? user.getIdonline() : "";
    ob[1] = user.getOnline() != 0 ? user.getOnline() : "";
    ob[2] = user.getUsuario() != null ? user.getUsuario() : "";
    ob[3] = user.getNivel() != null ? user.getNivel() : "";
    ob[4] = user.getFechaEntrada() != null ? user.getFechaEntrada() : "";
    ob[5] = user.getHoraEntrada() != null ? user.getHoraEntrada() : "";
    ob[6] = user.getFechaOnline() != null ? user.getFechaOnline() : "";
    
    String horaString = user.getUltentrada() != null ? user.getUltentrada() : "00:00";
    String[] partesHora = horaString.split(":");
    String horaFormateada = partesHora.length >= 2 ? partesHora[0] + ":" + partesHora[1] : "00:00";
    ob[7] = horaFormateada;
    
    modelo.addRow(ob);
}
    
    
    
    
    
    jTable1.setModel(modelo);
    acomodarceldas();
    
 



    // Ajustar la alineación del encabezado de la tabla
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    // Deshabilitar la edición directa en la tabla
    jTable1.setDefaultEditor(Object.class, null);
        
        
        
    }catch(Exception e){System.out.println(e);}    
   
}

    
    
     
    public  void limpiarTabla() {
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
        int a = jTable1.getRowCount()-1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount()-1);
        }
    }

  
    
    public void conteoTabla(){
    
   int rowCount = jTable1.getRowCount();
    jLabel5.setText(String.valueOf(rowCount));
    }
    
      public void acomodarceldas()
    {
     
        DefaultTableModel Tabla = (DefaultTableModel)jTable1.getModel();
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setRowHeight(20);
        
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(0);
       
      //  jTable1.getColumnModel().getColumn(Tabla.findColumn("ONLINE")).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("USUARIO")).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ROL")).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("FECHA ENTRADA")).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("HORA ENTRADA")).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ULT. ENT. FECHA")).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ULT. ENT. HORA")).setPreferredWidth(70);
         
        
      //  jTable1.getColumnModel().getColumn(Tabla.findColumn("ONLINE")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("USUARIO")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ROL")).setCellRenderer(Alinear);
        jTable1.getColumnModel().getColumn(Tabla.findColumn("FECHA ENTRADA")).setCellRenderer(Alinear);;
        jTable1.getColumnModel().getColumn(Tabla.findColumn("HORA ENTRADA")).setCellRenderer(Alinear);;
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ULT. ENT. FECHA")).setCellRenderer(Alinear);;
        jTable1.getColumnModel().getColumn(Tabla.findColumn("ULT. ENT. HORA")).setCellRenderer(Alinear);;
    
    }
    
    
    
    
    
    
    
      
      private static void updateLastLogin(int userId) {
    Connection con = null;
    PreparedStatement ps = null;
    try {
        EnlaceBd cn = new EnlaceBd();
        con = cn.getConnection();
        
        // Obtener fecha y hora actuales
        java.sql.Date fechaU = new java.sql.Date(System.currentTimeMillis());
        LocalTime hora = LocalTime.now();
        
        // Actualizar base de datos
        String sql = "UPDATE table_usuario SET  online=? WHERE IdPersonal=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, 0); 
        ps.setInt(2, userId);
        ps.executeUpdate();
        
       // System.out.println("Último inicio de sesión actualizado para usuario con ID " + userId);
        
    } catch (SQLException e) {
        System.err.println("Error al actualizar último inicio de sesión: " + e.getMessage());
        e.printStackTrace();
    } finally {
        // Cerrar PreparedStatement
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Cerrar conexión
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

      
      
      
      
      
      
      
      
      
      
      
      
      
    
    
    Mprincipal Menu = new Mprincipal();
    Encriptar encriptar = new Encriptar();
    DefaultTableModel modelo = new DefaultTableModel();
    Validar va = new Validar();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        FechaAc2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jrTodos = new javax.swing.JRadioButton();
        jrOnline = new javax.swing.JRadioButton();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Refrescar");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1300, 60));

        FechaAc2.setBackground(new java.awt.Color(0, 0, 0));
        FechaAc2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        FechaAc2.setText("U S U A R I O S     E N      L I N E A ");
        jPanel1.add(FechaAc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 370, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ONLINE", "USUARIO", "ROL", "FECHA ENTRADA", "HORA ENTRADA", "ULT. ENT. FECHA", "ULT. ENT. HORA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 97, 1270, 490));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 50, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/equipo-medico.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 100, 80));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuarioOn.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 90, 80));

        jLabel4.setText("Total de usuarios en linea:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 70, -1, -1));

        jLabel5.setText("0");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 70, 50, -1));

        jrTodos.setBackground(new java.awt.Color(255, 255, 255));
        jrTodos.setText("Ver Todos");
        jrTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTodosActionPerformed(evt);
            }
        });
        jPanel1.add(jrTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jrOnline.setBackground(new java.awt.Color(255, 255, 255));
        jrOnline.setText("Ver Online");
        jrOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrOnlineActionPerformed(evt);
            }
        });
        jPanel1.add(jrOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

  
if(jrOnline.isSelected()){

refreshButtom2();
}


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jrOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrOnlineActionPerformed
          jrOnline.setSelected(true);
          jrTodos.setSelected(false);
          limpiarTabla();
          listarOnline();
          conteoTabla();

    }//GEN-LAST:event_jrOnlineActionPerformed

    private void jrTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTodosActionPerformed
         jrOnline.setSelected(false);
         jrTodos.setSelected(true);
         limpiarTabla();
         listarOnline();
    }//GEN-LAST:event_jrTodosActionPerformed

    


    public void refreshButtom(){
    
    if(jTable1.getRowCount()!=0){
  Calendar fechaActual = Calendar.getInstance(); // Obtener la fecha actual

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

for (int i = 0; i < jTable1.getRowCount(); i++) {
    int userId = (int) jTable1.getValueAt(i, 0); // Obtener el ID de usuario desde la tabla
    String fechaOnlineStr = jTable1.getValueAt(i, 4).toString(); // Obtener la fecha online como String desde la tabla
     String horanlineStr = jTable1.getValueAt(i, 5).toString(); // Obtiene la hora y minutos ejemplo: 8:15
    // Convertir fechaOnline de String a Calendar (solo fecha, sin hora)
    Calendar fechaOnlineCal = Calendar.getInstance();
    try {
        Date fechaOnlineDate = sdf.parse(fechaOnlineStr);
        fechaOnlineCal.setTime(fechaOnlineDate);
    } catch (ParseException e) {
        e.printStackTrace();
        // Manejo de error si la conversión falla
        continue; // Otra acción según tu lógica
    }
    
    
   
    
    // Comparar solo las fechas (ignorando la hora)
   if (fechaOnlineCal.get(Calendar.YEAR) != fechaActual.get(Calendar.YEAR) ||
            fechaOnlineCal.get(Calendar.MONTH) != fechaActual.get(Calendar.MONTH) ||
            fechaOnlineCal.get(Calendar.DAY_OF_MONTH) != fechaActual.get(Calendar.DAY_OF_MONTH)) {


        // Las fechas son iguales (misma fecha, sin considerar la hora)
        updateLastLogin(userId);
      //  System.out.println("entre condicional");
     }
}
}
      
limpiarTabla();
listarOnline();
conteoTabla();

    }
    
    
    
    
    
  public void refreshButtom2() {
    if (jTable1.getRowCount() != 0) {
        Calendar fechaActual = Calendar.getInstance(); // Obtener la fecha y hora actual

        SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd"); // Formato para la fecha
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm"); // Formato para la hora

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            int userId = (int) jTable1.getValueAt(i, 0); // Obtener el ID de usuario desde la tabla
            String fechaOnlineStr = jTable1.getValueAt(i, 4).toString(); // Obtener la fecha online como String desde la tabla
            String horaOnlineStr = jTable1.getValueAt(i, 5).toString(); // Obtener la hora y minutos ejemplo: 8:15

            // Convertir fechaOnline de String a Calendar
            Calendar fechaOnlineCal = Calendar.getInstance();
            try {
                Date fechaOnlineDate = sdfFecha.parse(fechaOnlineStr);
                fechaOnlineCal.setTime(fechaOnlineDate);
            } catch (ParseException e) {
                e.printStackTrace();
                // Manejo de error si la conversión falla
                continue; // Otra acción según tu lógica
            }

            // Convertir horaOnline de String a Calendar
            Calendar horaOnlineCal = Calendar.getInstance();
            try {
                Date horaOnlineDate = sdfHora.parse(horaOnlineStr);
                horaOnlineCal.setTime(horaOnlineDate);
            } catch (ParseException e) {
                e.printStackTrace();
                // Manejo de error si la conversión falla
                continue; // Otra acción según tu lógica
            }

            // Establecer la hora en fechaOnlineCal
            fechaOnlineCal.set(Calendar.HOUR_OF_DAY, horaOnlineCal.get(Calendar.HOUR_OF_DAY));
            fechaOnlineCal.set(Calendar.MINUTE, horaOnlineCal.get(Calendar.MINUTE));
            fechaOnlineCal.set(Calendar.SECOND, 0);
            fechaOnlineCal.set(Calendar.MILLISECOND, 0);

            // Obtener la fecha y hora actual
            Calendar fechaHoraActual = Calendar.getInstance();
            
            // Calcular la diferencia en milisegundos
            long diferenciaMillis = fechaHoraActual.getTimeInMillis() - fechaOnlineCal.getTimeInMillis();
            
            // Convertir la diferencia a horas
            long diferenciaHoras = diferenciaMillis / (1000 * 60 * 60);

            // Verificar si han pasado más de 5 horas o si la fecha registrada es diferente a la fecha actual
            boolean fechaDiferente = !sdfFecha.format(fechaOnlineCal.getTime()).equals(sdfFecha.format(fechaHoraActual.getTime()));
            if (diferenciaHoras >= 12 || fechaDiferente) {
           
                updateLastLogin(userId);
              //  System.out.println("entre condicional");
            }
        }
    }

    limpiarTabla();
    listarOnline();
    conteoTabla();
}
    
    
    
        JUserDao listarUsers = new JUserDao();
     
    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FechaAc2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton jrOnline;
    private javax.swing.JRadioButton jrTodos;
    // End of variables declaration//GEN-END:variables
}
