
package com.login;

import com.login.Login;
import java.io.File;
import javax.imageio.ImageIO;

public class fsplash extends javax.swing.JFrame {

    public fsplash() {
        initComponents();
        
        try{    
       setIconImage(ImageIO.read(new File("C:\\Fundaginebra\\src\\imagenes\\LogoPag.png")));   
   }
catch (Exception ex){
       System.out.println(ex);
   }
 
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Percentage4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        proge1 = new org.edisoncor.gui.progressBar.ProgressBarRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Percentage4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Percentage4.setForeground(new java.awt.Color(255, 255, 255));
        Percentage4.setText("%");
        jPanel1.add(Percentage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fundacionlogo.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel105.setBackground(new java.awt.Color(0, 0, 0));
        jLabel105.setFont(new java.awt.Font("Freestyle Script", 1, 24)); // NOI18N
        jLabel105.setText("  ¡HUMANIDAD, PAZ Y SALUD!");
        jPanel2.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, -1));

        jLabel104.setBackground(new java.awt.Color(0, 0, 0));
        jLabel104.setFont(new java.awt.Font("Freestyle Script", 1, 24)); // NOI18N
        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gifs/cell2.gif"))); // NOI18N
        jPanel2.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 390, 410));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 650, 290));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 340));
        getContentPane().add(proge1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 650, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       fsplash me = new fsplash();
       me.setLocationRelativeTo(null);
       me.setVisible(true);
       
      //----
      
      
       for(int i=0;i<=100;i++){
           
        try{
       Thread.sleep (60);
       }
       catch(Exception e){}   
           
     //  System.out.println(i+"%");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.progressBar.ProgressBarRect proge1;
    // End of variables declaration//GEN-END:variables
}
