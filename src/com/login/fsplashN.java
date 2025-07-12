
package com.login;

import com.login.Login;
import java.io.File;
import javax.imageio.ImageIO;

public class fsplashN extends javax.swing.JFrame {

    public fsplashN() {
        initComponents();
        
        try{    
       setIconImage(ImageIO.read(new File("C:\\Cyberia\\src\\imagenes\\Logo_System.png")));   
   }
catch (Exception ex){
       System.out.println(ex);
   }
 
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel107 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Percentage4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel109 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        proge1 = new org.edisoncor.gui.progressBar.ProgressBarRect();

        jLabel107.setBackground(new java.awt.Color(0, 0, 0));
        jLabel107.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 153));
        jLabel107.setText("Pérez F.P");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Percentage4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Percentage4.setForeground(new java.awt.Color(255, 255, 255));
        Percentage4.setText("%");
        jPanel1.add(Percentage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confeccion2.jpg"))); // NOI18N
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel109.setBackground(new java.awt.Color(0, 0, 0));
        jLabel109.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 153));
        jLabel109.setText("Bordados y Confecciones ");
        panelImage1.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 370, 50));

        jLabel108.setBackground(new java.awt.Color(0, 0, 0));
        jLabel108.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 153));
        jLabel108.setText("Pérez F.P");
        panelImage1.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 170, 50));

        jPanel2.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 290));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 650, 290));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 340));
        getContentPane().add(proge1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 650, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       fsplashN me = new fsplashN();
       me.setLocationRelativeTo(null);
       me.setVisible(true);
       
      //----
      
      
       for(int i=0;i<=100;i++){
           
        try{
       Thread.sleep (60);
       }
       catch(Exception e){}   
           
      // System.out.println(i+"%");
       me.proge1.setValue(i);
       me.Percentage4.setText(Integer.toString(i)+"%");
       }
        
        
      me.dispose();
      Login de = new Login();
      de.setLocationRelativeTo(null);
      de.setVisible(true);
      
    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Percentage4;
    public javax.swing.JLabel jLabel107;
    public javax.swing.JLabel jLabel108;
    public javax.swing.JLabel jLabel109;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.progressBar.ProgressBarRect proge1;
    // End of variables declaration//GEN-END:variables
}
