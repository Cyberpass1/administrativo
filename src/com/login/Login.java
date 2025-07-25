package com.login;

import Clases.Encriptar;
import Clases.EnlaceBd;
import Clases.Validar;
import Menu.Mprincipal;
import static Menu.Mprincipal.idUsuarioOn;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class Login extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    
    public Login() {
        initComponents();
        
         Calendar Fecha = new GregorianCalendar();
         FechaAdmin.setCalendar(Fecha);
        
  FechaAdmin.setVisible(false);
  
  
  java.net.URL url = ClassLoader.getSystemResource("C:\\Cyberia\\src\\imagenes\\Logo_System.png");
try{    
       setIconImage(ImageIO.read(new File("C:\\Cyberia\\src\\imagenes\\Logo_System.png")));   
   }
catch (Exception ex){
       System.out.println(ex);
   }
  
   try {
    // Establecer el LookAndFeel global
    UIManager.setLookAndFeel(new FlatLightLaf());
    
    // Aplicar un LookAndFeel diferente solo para JTabbedPane
    UIManager.put("TabbedPaneUI", "com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI"); 
    UIManager.put("TableUI", "javax.swing.plaf.basic.BasicTableUI");

     

    
        


} catch (Exception e) {
    e.printStackTrace();
}
  
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        header = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        exitTxt = new javax.swing.JLabel();
        FechaAdmin = new com.toedter.calendar.JDateChooser();
        title = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        passLabel = new javax.swing.JLabel();
        passTxt = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        loginBtn = new javax.swing.JPanel();
        loginBtnTxt = new javax.swing.JLabel();
        jRecovery = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/liveandwork.jpg"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 330, 500));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitTxt.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTxt.setText("X");
        exitTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitTxt.setPreferredSize(new java.awt.Dimension(40, 40));
        exitTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        header.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, -1, -1));

        FechaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        FechaAdmin.setToolTipText("");
        FechaAdmin.setEnabled(false);
        FechaAdmin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        header.add(FechaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 30));

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 40));

        title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        title.setText("INICIAR SESIÓN");
        bg.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        userLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel.setText("USUARIO");
        bg.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        userTxt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        userTxt.setBorder(null);
        userTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userTxtMousePressed(evt);
            }
        });
        userTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userTxtKeyTyped(evt);
            }
        });
        bg.add(userTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 410, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 410, 20));

        passLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel.setText("CONTRASEÑA");
        bg.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        passTxt.setForeground(new java.awt.Color(204, 204, 204));
        passTxt.setBorder(null);
        passTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passTxtMousePressed(evt);
            }
        });
        passTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passTxtKeyTyped(evt);
            }
        });
        bg.add(passTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 410, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 410, 20));

        loginBtn.setBackground(new java.awt.Color(0, 0, 51));

        loginBtnTxt.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        loginBtnTxt.setForeground(new java.awt.Color(255, 255, 255));
        loginBtnTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBtnTxt.setText("ENTRAR");
        loginBtnTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtnTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtnTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtnTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout loginBtnLayout = new javax.swing.GroupLayout(loginBtn);
        loginBtn.setLayout(loginBtnLayout);
        loginBtnLayout.setHorizontalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(loginBtnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        loginBtnLayout.setVerticalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(loginBtnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 130, 40));

        jRecovery.setBackground(new java.awt.Color(255, 255, 255));
        jRecovery.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jRecovery.setText("¿Olvidaste la contraseña?");
        jRecovery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRecovery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRecoveryMouseClicked(evt);
            }
        });
        bg.add(jRecovery, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void exitTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitTxtMouseClicked

    private void exitTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseEntered
        exitBtn.setBackground(Color.red);
        exitTxt.setForeground(Color.white);
    }//GEN-LAST:event_exitTxtMouseEntered

    private void exitTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseExited
        exitBtn.setBackground(Color.white);
        exitTxt.setForeground(Color.black);
    }//GEN-LAST:event_exitTxtMouseExited

    private void loginBtnTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnTxtMouseEntered
        loginBtn.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_loginBtnTxtMouseEntered

    private void loginBtnTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnTxtMouseExited
        loginBtn.setBackground(new Color(0,134,190));
    }//GEN-LAST:event_loginBtnTxtMouseExited

    private void userTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTxtMousePressed
 
    }//GEN-LAST:event_userTxtMousePressed

    private void passTxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passTxtMousePressed

    }//GEN-LAST:event_passTxtMousePressed

    private void loginBtnTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnTxtMouseClicked
   
    // Obtener la fecha actual
    LocalDate currentDate = LocalDate.now();
    
    // Obtener la fecha del servidor
    java.sql.Date fechaServidor = getFechaDelServidor();
    
    // Verificar si la fecha del servidor fue obtenida correctamente
    if (fechaServidor == null) {
        JOptionPane.showMessageDialog(null, "Error al obtener la fecha del servidor", "FECHA", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    
    
    // Convertir la fecha del servidor a LocalDate para compararla
    LocalDate serverDate = fechaServidor.toLocalDate();
   // LocalDate licenseExpirationDate = LocalDate.of(2030, 1, 10);
 
    // Comprobar si la fecha del servidor es anterior a la fecha actual
    if (serverDate.isBefore(currentDate)) {
        JOptionPane.showMessageDialog(null, "LA FECHA DE LA COMPUTADORA ESTÁ MAL CONFIGURADA", "FECHA", JOptionPane.ERROR_MESSAGE);
    }
    
    else {
    checkLicense(); 
   
    }
   
      
      
    }//GEN-LAST:event_loginBtnTxtMouseClicked

    
    private java.sql.Date getFechaDelServidor() {
    String sqlTime = "SELECT NOW() AS server_time";
    try (Connection con = new EnlaceBd().getConnection();
         PreparedStatement ps = con.prepareStatement(sqlTime);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getDate("server_time"); // Obtener la fecha del servidor
        }
    } catch (Exception e) {
        System.out.println("Error al obtener la fecha del servidor: " + e);
    }
    return null; // Retornar null si ocurre un error
}
    
    
    
    
    
    private void jRecoveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRecoveryMouseClicked

    LocalDate currentDate = LocalDate.now();
    
    // Obtener la fecha del servidor
    java.sql.Date fechaServidor = getFechaDelServidor();
    
    // Verificar si la fecha del servidor fue obtenida correctamente
    if (fechaServidor == null) {
        JOptionPane.showMessageDialog(null, "Error al obtener la fecha del servidor", "FECHA", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    
    
    // Convertir la fecha del servidor a LocalDate para compararla
    LocalDate serverDate = fechaServidor.toLocalDate();
    LocalDate licenseExpirationDate = LocalDate.of(2030, 1, 10);
 
    // Comprobar si la fecha del servidor es anterior a la fecha actual
    if (serverDate.isBefore(currentDate)) {
        JOptionPane.showMessageDialog(null, "LA FECHA DE LA COMPUTADORA ESTÁ MAL CONFIGURADA", "FECHA", JOptionPane.ERROR_MESSAGE);
    }
     else if (currentDate.isAfter(licenseExpirationDate)) {
        JOptionPane.showMessageDialog(null, "LA LICENCIA SE HA EXPIRADO, CONTACTE CON EL DESARROLLADOR AL CORREO: JPCRANGEL1999@GMAIL.COM", "LICENCIA", JOptionPane.ERROR_MESSAGE);
    }     
    else {
        recuperar me = new recuperar();
        me.setLocationRelativeTo(null);
        me.setVisible(true);
        dispose();
    }
   
    }//GEN-LAST:event_jRecoveryMouseClicked
   
    Validar va = new Validar();



    private void userTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTxtKeyTyped
    va.longitud(userTxt.getText(), 40, evt);
    }//GEN-LAST:event_userTxtKeyTyped

    private void passTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passTxtKeyTyped
  va.longitud(passTxt.getText(), 40, evt);
    }//GEN-LAST:event_passTxtKeyTyped

    private void passTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passTxtKeyReleased
    
    }//GEN-LAST:event_passTxtKeyReleased

    private void passTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passTxtKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
       
            
              // Obtener la fecha actual
    LocalDate currentDate = LocalDate.now();
    
    // Obtener la fecha del servidor
    java.sql.Date fechaServidor = getFechaDelServidor();
    
    // Verificar si la fecha del servidor fue obtenida correctamente
    if (fechaServidor == null) {
        JOptionPane.showMessageDialog(null, "Error al obtener la fecha del servidor", "FECHA", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    
    
    // Convertir la fecha del servidor a LocalDate para compararla
    LocalDate serverDate = fechaServidor.toLocalDate();
    LocalDate licenseExpirationDate = LocalDate.of(2030, 1, 10);
 
    // Comprobar si la fecha del servidor es anterior a la fecha actual
    if (serverDate.isBefore(currentDate)) {
        JOptionPane.showMessageDialog(null, "LA FECHA DE LA COMPUTADORA ESTÁ MAL CONFIGURADA", "FECHA", JOptionPane.ERROR_MESSAGE);
    } 
   else {
    checkLicense(); 
   
    }
   
            
  
     }
    }//GEN-LAST:event_passTxtKeyPressed

    private void userTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTxtKeyReleased
            String text = userTxt.getText();
    int maxLength = 40;
    if (text.length() > maxLength) {
        JOptionPane.showMessageDialog(this, "Se ha alcanzado la longitud máxima permitida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        // Trunca el texto si lo deseas
       userTxt.setText(text.substring(0, maxLength));
    }
    }//GEN-LAST:event_userTxtKeyReleased

    
    Encriptar encriptar = new Encriptar();
    public int idu, rolSesion;
    int id;    
    public String nombreSesion, usuarioSesion, nivelUsuario, idEspecialidad, categoria, idCategoria;
  
   
    


 
    


    public void validar() {
        String usuario = userTxt.getText();
        String claveEncriptada = encriptar.ecnode(passTxt.getText().trim());

        if (usuario.isEmpty() || passTxt.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "DEBE COMPLETAR LOS CAMPOS VACIOS", "CAMPOS VACIOS", JOptionPane.ERROR_MESSAGE);
            userTxt.requestFocus();
            return;
        }

        try (Connection con = EnlaceBd.getConnection()) {
         String query = "SELECT " +
               "    u.IdPersonal, " +
               "    u.Usuario, " +
               "    o.Nombre, " +
               "    o.Apellido, " +
               "    u.Clave, " +
               "    u.Nivel, " +
               "    DATE_FORMAT(u.Fecha, '%d/%m/%Y') AS myDate, " +
               "    u.Hora, " +
               "    l.Categoria AS nivelUsuario, " +
               "    u.Pestado " +
               "FROM " +
               "    table_usuario u " +
               "    INNER JOIN table_nivel n ON u.Nivel = n.Idnivel " +
               "    INNER JOIN table_personal o ON u.IdPersonal = o.IdPersonal " +
               "    LEFT JOIN categorias_administrativas l ON u.categoriaAdmin = l.id_Administrativo " +
               "WHERE " +
               "    u.Usuario = ? AND u.Clave = ?";

       
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, usuario);
                ps.setString(2, claveEncriptada);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("IdPersonal");
                        int nivel = rs.getInt("Nivel");
                        
                        String userFecha = rs.getString("myDate");
                        String hora = rs.getString("Hora");
                        nivelUsuario = rs.getString("nivelUsuario");
                        int estado = rs.getInt("Pestado");
                        String user = rs.getString("Usuario");
                        String nombre = rs.getString("Nombre");
                        String apellido = rs.getString("Apellido");

                        nombreSesion = nombre + " " + apellido;
                        rolSesion = nivel;
                        usuarioSesion = user;
            

                        if (estado == 101) {
                            JOptionPane.showMessageDialog(null, "SU USUARIO SE ENCUENTRA INACTIVO, COMUNIQUESE CON UN ADMINISTRADOR", "USUARIO INACTIVO", JOptionPane.ERROR_MESSAGE);
                        } else {
                            construirVentanaPrincipal(id, user, nivel, userFecha, hora, nivelUsuario, idEspecialidad);
                            updateOnline(con);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "USUARIO O CLAVE INCORRECTA", "DATOS ERRONEOS", JOptionPane.ERROR_MESSAGE);
                        userTxt.setText("");
                        passTxt.setText("");
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error en la conexión SQL: " + ex.getMessage());
        }
    }

    
    
    
    
private void updateOnline(Connection con) {
    try {
        // Consulta SQL para actualizar el estado online
        String sql = "UPDATE table_usuario SET online = ?, Fecha_Entrada=?, Hora_Entrada=? WHERE IdPersonal = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            java.sql.Date fechaU = new java.sql.Date(System.currentTimeMillis());
            LocalTime hora = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String horaFormateada = hora.format(formatter);

            // Configuramos los parámetros de la consulta
            ps.setInt(1, 1); // Estado online = 1
            ps.setDate(2, fechaU); // Fecha actual
            ps.setString(3, horaFormateada); // Hora formateada
            ps.setInt(4, id); // ID de la persona a actualizar

            // Ejecutamos la actualización
            int res = ps.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("Error al actualizar el estado online: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "ERROR", "error en updateOnline", JOptionPane.ERROR_MESSAGE);
    }
}
    
 

    
    
    
    
    
    
    
    
    
    
  
public void checkLicense() {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try (Connection con = EnlaceBd.getConnection()) {  
        // 1. Obtener datos de la tabla de información
        String sql = "SELECT * FROM tableinfopdfs";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        java.util.Date fechaVencimiento = null;

        if (rs.next()) {
            String dateStr = rs.getString("vencimiento"); // Formato esperado: "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaVencimiento = sdf.parse(dateStr);
        }

        // 2. Obtener la hora actual del servidor
        String sqlTime = "SELECT NOW() AS server_time";
        ps = con.prepareStatement(sqlTime);
        rs = ps.executeQuery();

        if (rs.next()) {
            java.sql.Timestamp currentTime = rs.getTimestamp("server_time");
            java.util.Date fechaActual = new java.util.Date(currentTime.getTime());

            // Establecer la fecha en el componente gráfico
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(currentTime);
            FechaAdmin.setCalendar(calendar);

            // 3. Comparar fechas
            if (fechaVencimiento != null && fechaActual.after(fechaVencimiento)) {
                JOptionPane.showMessageDialog(null, "Estimado usuario la Licencia del sistema ha caducado, Favor de comunicarse con el desarrollador: cyberiabackup2024@mail.com", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
             validar();
            }
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    } 
}
    
    
    
    
    
    
    
    
    
    
    
    
    public String horaFormateada=null;
    private void construirVentanaPrincipal(int id, String user, int nivel, String userfecha, String hora, String especialidad, String idEspecialidad) {
    
    
    String horaFormateada = "PRIMERA VEZ";
    if (hora != null) {
    String horaString = hora.toString();
    String[] partesHora = horaString.split(":");
    if (partesHora.length >= 2) {
        horaFormateada = partesHora[0] + ":" + partesHora[1]; 
    }
    }

    String fechaFormateada = "";
    if (userfecha != null) {
    fechaFormateada = userfecha; 
    }
     
    
        
     Mprincipal ad = new Mprincipal();
     ad.IDUSER.setText(Integer.toString(id));
     ad.JLabelNombre.setText(nombreSesion);
     ad.TxtUser.setText(user);
     ad.Txtentrada.setText(fechaFormateada + " | " + horaFormateada);
     ad.jMenAdmin.setVisible(false);
     ad.idUsuarioOn=id;
     ad.jCfacturas.setVisible(false);
     ad.jMinventario.setVisible(false);

   
 
    switch (nivel) {
        case 1: // Administrador
            configurarVentanaAdministrador(ad);
            ad.setLocationRelativeTo(null);
            ad.setVisible(true);
            dispose();
            break;
            
            

           
         case 3: // Auxiliar 
          
              configAuxAdmins(ad);
              ad.setLocationRelativeTo(null);
              ad.setVisible(true);
              dispose();
             

            break;
            
            
      
            
   
            

            
            
            
            
               
      
          
        default:
            System.out.println(especialidad);
            JOptionPane.showMessageDialog(this,"CONTRASEÑA INCORRECTA O USUARIO INACTIVO", "INICIO DE SESIÓN", JOptionPane.ERROR_MESSAGE);
            break;
    }

   
}
    
    
    
    private void configurarVentanaAdministrador(Mprincipal ad) {
    ad.BtnMensaje.setEnabled(true);
    ad.Txtinfo.setEditable(true);
    ad.jMenuItem4.setEnabled(true);
    ad.jMenuRestauracion.setEnabled(true);
    ad.TxtRol.setText("Administrador");
    ad.TxtRol1.setText(nivelUsuario);
    ad.jCompras.setEnabled(true);
 
   // ad.jGastos.setEnabled(true);

    ad.jProveedores.setEnabled(true);
    ad.jMenAdmin.setVisible(true);
    ad.jMenAdmin.setEnabled(true);
    ad.JEmpresa.setEnabled(true);
    ad.jOnline.setEnabled(true);
    ad.jMenuPassword.setEnabled(true);
    ad.jMenuDivisa.setEnabled(true);
    
    ad.jMenuFactura.setVisible(true);
    ad.jMenuFactura.setEnabled(true);
    
    ad.jMenuSeguridad.setEnabled(true);
    ad.jCfacturas.setVisible(true);
    ad.jCfacturas.setEnabled(true);
    ad.jMinventario.setVisible(true);
    ad.jMinventario.setEnabled(true);
  
}

    


    

  
  

    

    
    
    



  
  
  private void configAuxAdmins(Mprincipal ad) {
 
    //PERMISOS
    ad.jMenuProcesos.setEnabled(true);
    ad.TxtRol.setText("Aux.");  
    ad.TxtRol1.setText(nivelUsuario);
    ad.IDEspecialidad.setText(idEspecialidad);

    ad.JMreportes.setEnabled(true);
    ad.JMenuCliente.setEnabled(true);
    ad.jMenuFactura.setVisible(true);
    ad.jCfacturas.setVisible(true);
    ad.jMenuFactura.setEnabled(true);
    ad.jCfacturas.setEnabled(true);
    
    //QUITAR PERMISOS
    
    ad.jProveedores.setEnabled(false);
    ad.TxtUser.setVisible(false);
    ad.JMuser.setEnabled(false);
    ad.jMenuConfiguracion.setEnabled(false);
    ad.jMenuSeguridad.setEnabled(false);
    ad.jMenuServicios.setEnabled(false);
    ad.TxtUser.setVisible(false);
    ad.jLabel106.setVisible(false);
    ad.jMenuRegistros.setEnabled(false);

    ad.jConsPac.setEnabled(false);
    ad.JConsultPersonal.setEnabled(false);
}      
   
    
    
    
   
  
   private void configSupervisorAdm(Mprincipal ad) {
 
       
    ad.TxtUser.setVisible(false);
    ad.JMuser.setEnabled(false);
    ad.jMenuConfiguracion.setEnabled(false);
    ad.JMreportes.setEnabled(false);
    ad.jMenuSeguridad.setEnabled(false);
    ad.jMenuServicios.setEnabled(false);
    ad.TxtUser.setVisible(false);
    ad.jLabel106.setVisible(false);
    
    ad.jMenuProcesos.setEnabled(true);
    ad.jMenuRegistros.setEnabled(false);

    
    ad.TxtRol.setText("Supervisor");  
    ad.TxtRol1.setText(nivelUsuario);
    ad.IDEspecialidad.setText(idEspecialidad);


    ad.JMreportes.setEnabled(true);
    

    ad.jConsPac.setEnabled(false);
    ad.JConsultPersonal.setEnabled(false);
    

       
       
       
}   
  
  
  
  
  
    
        
    Connection con = null;
    PreparedStatement ps=null;
    ResultSet rs = null;
 
    
    
    
    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JDateChooser FechaAdmin;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JLabel exitTxt;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jRecovery;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel loginBtn;
    private javax.swing.JLabel loginBtnTxt;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passTxt;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
