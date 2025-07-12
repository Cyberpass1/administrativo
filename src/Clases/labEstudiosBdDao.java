/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class labEstudiosBdDao {
    
    
     public void ConsultarFirma() {
   
          
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EnlaceBd cn = new EnlaceBd();
        byte[] b = null;
          
        try {
           String sql= "SELECT img FROM tableinfo WHERE Idinfo =?";
           con=cn.getConnection();
           ps=con.prepareStatement(sql);
           ps.setInt(1, 1);
           rs = ps.executeQuery();
           while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("imagen.bin");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();

           // JOptionPane.showMessageDialog(null, "Estimado: "+  nameof + " su firma ha sido desactivada", "DESACTIVAR FIRMA", 1);
     
        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex);
        }
        
      finally {
            closeResources(rs, ps, con);
        }
      
      }
    
    
    
    
  public void AgregarCmv(int idHistoria, String igm, String igg){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO lab_cmv (id_historia, igm, igg) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, igm);
            ps.setString(3, igg);
 
            ps.execute();
   
      
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
         finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void ActualizarCmv(int idhistoria, String igm, String igg)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "update lab_cmv set igm=?,igg=? where id_historia=? " ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, igm);
            ps.setString(2, igg);
            ps.setInt(3, idhistoria);


            int res = ps.executeUpdate();

  
        } catch (Exception e) {
            System.out.println(e);
        }
        
      finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
  
  
  
   public void agregarHematologia(
           
           int idHistoria, 
           String leucocitos, 
           String Linfocitos, 
           String Neutrofilos, 
           String Hemoglobina, 
           String Hematocrito, 
           String VCM, 
           String CHCM, 
           String Plaquetas,
           String Eosinofilos,
           String Monocitos,
           String Basofilo,
           String Observacion
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs;

          try {
            String sql = "INSERT INTO `lab_hematologia`(`Id_historiaH`, `Leucocitos`, `Linfocitos`, `Neutrofilos`, `Hemoglobina`, `Hematocrito`, `VCM`, `CHCM`, `Plaquetas`, Eosinofilos, Monocitos, Basofilo, `Observacion` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, leucocitos);
            ps.setString(3, Linfocitos);
            ps.setString(4, Neutrofilos);
            ps.setString(5, Hemoglobina);
            ps.setString(6, Hematocrito);
            ps.setString(7, VCM);
            ps.setString(8, CHCM);
            ps.setString(9, Plaquetas);
            ps.setString(10, Eosinofilos);
            ps.setString(11, Monocitos);
            ps.setString(12, Basofilo);
            ps.setString(13, Observacion);
            ps.execute();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        }
           
       finally {
            closeResources(null, ps, con);
        }
          
    } 
  
  
    
    
          public void actualizarHematologia(
           
           int idHistoria, 
           String leucocitos, 
           String Linfocitos, 
           String Neutrofilos, 
           String Hemoglobina, 
           String Hematocrito, 
           String VCM, 
           String CHCM, 
           String Plaquetas,
           String Eosinofilos,
           String Monocitos,
           String Basofilo,
           String Observacion
   )   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
             String sql = "UPDATE `lab_hematologia` SET  `Leucocitos` = ?, " +
                     "`Linfocitos` = ?, `Neutrofilos` = ?, `Hemoglobina` = ?, `Hematocrito` = ?, " +
                     "`VCM` = ?, `CHCM` = ?, `Plaquetas` = ?, Eosinofilos=?, Monocitos=?, Basofilo=?, `Observacion` = ?   WHERE Id_historiaH=?";
             
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
          
            ps.setString(1, leucocitos);
            ps.setString(2, Linfocitos);
            ps.setString(3, Neutrofilos);
            ps.setString(4, Hemoglobina);
            ps.setString(5, Hematocrito);
            ps.setString(6, VCM);
            ps.setString(7, CHCM);
            ps.setString(8, Plaquetas);
            ps.setString(9, Eosinofilos);
            ps.setString(10, Monocitos);
            ps.setString(11, Basofilo);
            ps.setString(12, Observacion);
            ps.setInt(13, idHistoria);

            ps.executeUpdate();

   
        } catch (Exception e) {
            System.out.println(e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
          
          
          
          
          
          
          
           public void agregarElectrolitos(
           
           int idHistoriaE, 
           String Sodio, 
           String Potasio, 
           String Cloro, 
           String OSodio, 
           String OPotasio, 
           String OCloro 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_electrolitos`(`id_historiaE`, `Sodio`, `Potasio`, `Cloro`, `Osodio`, `OPotasio` ,`OCloro`) VALUES (?,?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaE);
            ps.setString(2, Sodio);
            ps.setString(3, Potasio);
            ps.setString(4, Cloro);
            ps.setString(5, OSodio);
            ps.setString(6, OPotasio);
            ps.setString(7, OCloro);
    
 
            ps.execute();
   
      
        } catch (Exception e) {
      
            System.out.println(e);
        }     
          
       finally {
            closeResources(rs, ps, con);
        }
          
          
    } 
  
  
    
    
        public void actualizarElectrolitos(
        int idHistoriaE,
        String Sodio,
        String Potasio,
        String Cloro,
        String OSodio,
        String OPotasio,
        String OCloro
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE `lab_electrolitos` SET `Sodio`=?,`Potasio`=?,`Cloro`=?,`Osodio`=?,`Opotasio`=?,`OCloro`=? WHERE `id_historiaE`=?";
             
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, Sodio);
        ps.setString(2, Potasio);
        ps.setString(3, Cloro);
        ps.setString(4, OSodio);
        ps.setString(5, OPotasio);
        ps.setString(6, OCloro);
        ps.setInt(7, idHistoriaE);

        ps.executeUpdate();

  
    } catch (SQLException e) {
        System.out.println("Error en actualizar: " + e);
    }
    
   finally {
            closeResources(null, ps, con);
        }
}
    
          
          
          
          
          
          
          
          
          
          
           
   public void agregarEnzimatico(
           
           int idHistoriaEn, 
           String LDH, 
           String GGT, 
           String AMILASA, 
           String LIPASA
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_enzimas`(`id_historiaEn`, `LDH`, `GGT`, `AMILASA`, `LIPASA`) VALUES (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaEn);
            ps.setString(2, LDH);
            ps.setString(3, GGT);
            ps.setString(4, AMILASA);
            ps.setString(5, LIPASA);

 
            ps.execute();
   
      
        } catch (Exception e) {
      
            System.out.println(e);
        }
        finally {
            closeResources(rs, ps, con);
        }
          
    } 
          
          
          
          
       public void actualizarEnzimatico(
        int idHistoriaEn,
        String LDH,
        String GGT,
        String AMILASA,
        String LIPASA
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE `lab_enzimas` SET `LDH`=?, `GGT`=?, `AMILASA`=?, `LIPASA`=? WHERE `id_historiaEn`=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, LDH);
        ps.setString(2, GGT);
        ps.setString(3, AMILASA);
        ps.setString(4, LIPASA);
        ps.setInt(5, idHistoriaEn);

        ps.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error en actualizar: " + e);
    } finally {
            closeResources(null, ps, con);
        }
}
          
          
          
       
       
         public void agregarEpstein(
        int idHistoriaEp,
        String Eigm,
        String Eigg
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "INSERT INTO `lab_epsteinbar`(`id_historiaEp`, `Eigm`, `Eigg`) VALUES (?, ?, ?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoriaEp);
        ps.setString(2, Eigm);
        ps.setString(3, Eigg);

        ps.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error en la inserción: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
       
       
       
       
       
       
       public void actualizarEpstein(
        int idHistoriaEp,
        String Eigm,
        String Eigg
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE `lab_epsteinbar` SET `Eigm`=?, `Eigg`=? WHERE `id_historiaEp`=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, Eigm);
        ps.setString(2, Eigg);
        ps.setInt(3, idHistoriaEp);

        ps.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error en la actualización: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
       
       
       
       
       
        public void agregarPFemenino(
        int idHistoriaFE,
        String FSH,
        String LH,
        String ESTROGENO,
        String PROGES,
        String ESTRADIOL,
        String PROLACTINA
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "INSERT INTO `lab_femenina`(`Id_historiaFE`, `FSH`, `LH`, `ESTROGENO`, `PROGES`, `ESTRADIOL`, `PROLACTINA`) VALUES (?,?,?,?,?,?,?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoriaFE);
        ps.setString(2, FSH);
        ps.setString(3, LH);
        ps.setString(4, ESTROGENO);
        ps.setString(5, PROGES);
        ps.setString(6, ESTRADIOL);
        ps.setString(7, PROLACTINA);

        ps.execute();

    } catch (SQLException e) {
        System.out.println("Error en la inserción: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
          
       
       public void actualizarPFemenino(
        int idHistoriaFE,
        String FSH,
        String LH,
        String ESTROGENO,
        String PROGES,
        String ESTRADIOL,
        String PROLACTINA
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE `lab_femenina`\n" +
"SET `FSH`=?, `LH`=?, `ESTROGENO`=?, `PROGES`=?, `ESTRADIOL`=?, `PROLACTINA`=?\n" +
"WHERE `Id_historiaFE`=?";
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

     
        ps.setString(1, FSH);
        ps.setString(2, LH);
        ps.setString(3, ESTROGENO);
        ps.setString(4, PROGES);
        ps.setString(5, ESTRADIOL);
        ps.setString(6, PROLACTINA);
        ps.setInt(7, idHistoriaFE);
        
        ps.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error en la inserción: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
          
       
                public void agregarFerrecinetico(
        int idHistoriaFR,
        String Ferritina,
        String LH
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "INSERT INTO `lab_ferrocinetico`(`id_historiaFR`, `Ferritina`, `LDH`) VALUES (?,?,?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoriaFR);
        ps.setString(2, Ferritina);
        ps.setString(3, LH);

        ps.execute();

    } catch (SQLException e) {
        System.out.println("Error en la inserción: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
       
       
       
       
       
       
       public void actualizarFerrecinetico(
        int idHistoriaFR,
        String Ferritina,
        String LH
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "UPDATE `lab_ferrocinetico` SET `Ferritina`=?,`LDH`=? WHERE id_historiaFR=?";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setString(1, Ferritina);
        ps.setString(2, LH);
        ps.setInt(3, idHistoriaFR);

        ps.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error en la actualización: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}
      
       
       

       
       
 public void agregarQuimica(
        int idHistoria,
        String GLICEMIA,
        String UREA,
        String CREATININA,
        String ACIDO_URICO,
        String COLESTEROL,
        String TRIGLICERIDOS,
        String HDL,
        String LDL,
        String VLDL,
        String BILITOTAL,
        String BILIDIRECTA,
        String BILIINDIRECTA,
        String PROTEINAS_TOTALES,
        String ALBUMINA,
        String GLOBULINAS,
        String RELACION,
        String TGO,
        String TGP,
        String GGT,
        String FOSFATASA,
        String LDH,
        String AMILASA,
        String LIPASA,
        String SODIO,
        String POTASIO,
        String CLORO,
        String CALCIO,
        String FOSFORO,
        String MAGNESIO,
        String HIERRO_SERICO,
        String CK,
        String CKMB,
        String GLICEMIAPANDRIAL,
        String OBSERVACION
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
        String sql = "INSERT INTO `lab_quimica`(`id_historiaQU`, `GLICEMIA`, `UREA`, `CREATININA`, `ACIDO_URICO`, `COLESTEROL`, `TRIGLICERIDOS`, `HDL`, `LDL`, `VLDL`, `BILITOTAL`, `BILIDIRECTA`, `BILIINDIRECTA`, `PROTEINAS_TOTALES`, `ALBUMINA`, `GLOBULINAS`, `RELACION`, `TGO`, `TGP`, `GGT`, `FOSFATASA`, `LDH`, `AMILASA`, `LIPASA`, `SODIO`, `POTASIO`, `CLORO`, `CALCIO`, `FOSFORO`, `MAGNESIO`, `HIERRO_SERICO`, `CK`, `CKMB`,`GLICEMIAPANDRIAL`,`Observacion` )\n" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoria);
        ps.setString(2, GLICEMIA);
        ps.setString(3, UREA);
        ps.setString(4, CREATININA);
        ps.setString(5, ACIDO_URICO);
        ps.setString(6, COLESTEROL);
        ps.setString(7, TRIGLICERIDOS);
        ps.setString(8, HDL);
        ps.setString(9, LDL);
        ps.setString(10, VLDL);
        ps.setString(11, BILITOTAL);
        ps.setString(12, BILIDIRECTA);
        ps.setString(13, BILIINDIRECTA);
        ps.setString(14, PROTEINAS_TOTALES);
        ps.setString(15, ALBUMINA);
        ps.setString(16, GLOBULINAS);
        ps.setString(17, RELACION);
        ps.setString(18, TGO);
        ps.setString(19, TGP);
        ps.setString(20, GGT);
        ps.setString(21, FOSFATASA);
        ps.setString(22, LDH);
        ps.setString(23, AMILASA);
        ps.setString(24, LIPASA);
        ps.setString(25, SODIO);
        ps.setString(26, POTASIO);
        ps.setString(27, CLORO);
        ps.setString(28, CALCIO);
        ps.setString(29, FOSFORO);
        ps.setString(30, MAGNESIO);
        ps.setString(31, HIERRO_SERICO);
        ps.setString(32, CK);
        ps.setString(33, CKMB);
        ps.setString(34, GLICEMIAPANDRIAL);
        ps.setString(35, OBSERVACION);

        ps.execute();
    } catch (SQLException e) {
        System.out.println("Error al agregar química: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}

       
       
       
            
   public void actualizarQuimica(
           
           int idHistoria, 
           String GLICEMIA, 
           String UREA, 
           String CREATININA, 
           String ACIDO_URICO, 
           String COLESTEROL, 
           String TRIGLICERIDOS, 
           String HDL, 
           String LDL,
           String VLDL, 
           String BILITOTAL, 
           String BILIDIRECTA, 
           String BILIINDIRECTA, 
           String PROTEINAS_TOTALES, 
           String ALBUMINA, 
           String GLOBULINAS, 
           String RELACION, 
           String TGO, 
           String TGP, 
           String GGT, 
           String FOSFATASA, 
           String LDH, 
           String AMILASA, 
           String LIPASA, 
           String SODIO,
           String POTASIO, 
           String CLORO, 
           String CALCIO, 
           String FOSFORO, 
           String MAGNESIO, 
           String HIERRO_SERICO, 
           String CK, 
           String CKMB,
           String GLICEMIAPANDRIAL,
           String OBSERVACION
     
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_quimica`\n" +
"SET `GLICEMIA`=?, `UREA`=?, `CREATININA`=?, `ACIDO_URICO`=?, `COLESTEROL`=?, `TRIGLICERIDOS`=?, `HDL`=?, `LDL`=?, `VLDL`=?, `BILITOTAL`=?, `BILIDIRECTA`=?, `BILIINDIRECTA`=?, `PROTEINAS_TOTALES`=?, `ALBUMINA`=?, `GLOBULINAS`=?, `RELACION`=?, `TGO`=?, `TGP`=?, `GGT`=?, `FOSFATASA`=?, `LDH`=?, `AMILASA`=?, `LIPASA`=?, `SODIO`=?, `POTASIO`=?, `CLORO`=?, `CALCIO`=?, `FOSFORO`=?, `MAGNESIO`=?, `HIERRO_SERICO`=?, `CK`=?, `CKMB`=?, `GLICEMIAPANDRIAL`=?, `Observacion`=?\n" +
"WHERE `id_historiaQU`=?;";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
          
            ps.setString(1, GLICEMIA);
            ps.setString(2, UREA);
            ps.setString(3, CREATININA);
            ps.setString(4, ACIDO_URICO);
            ps.setString(5, COLESTEROL);
            ps.setString(6, TRIGLICERIDOS);
            ps.setString(7, HDL);
            ps.setString(8, LDL);
            ps.setString(9, VLDL);
            ps.setString(10, BILITOTAL);
            ps.setString(11, BILIDIRECTA);
            ps.setString(12, BILIINDIRECTA);
            ps.setString(13, PROTEINAS_TOTALES);
            ps.setString(14, ALBUMINA);
            ps.setString(15, GLOBULINAS);
            ps.setString(16, RELACION);
            ps.setString(17, TGO);
            ps.setString(18, TGP);
            ps.setString(19, GGT);
            ps.setString(20, FOSFATASA);
            ps.setString(21, LDH);
            ps.setString(22, AMILASA);
            ps.setString(23, LIPASA);
            ps.setString(24, SODIO);
            ps.setString(25, POTASIO);
            ps.setString(26, CLORO);
            ps.setString(27, CALCIO);
            ps.setString(28, FOSFORO);
            ps.setString(29, MAGNESIO);
            ps.setString(30, HIERRO_SERICO);
            ps.setString(31, CK);
            ps.setString(32, CKMB);
            ps.setString(33, GLICEMIAPANDRIAL);
            ps.setString(34, OBSERVACION);
            ps.setInt(35, idHistoria);
            
            
            ps.execute();
   
      
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
           finally {
            closeResources(rs, ps, con);
        }
          
    } 
       
       
       
           
  public void AgregarVIH(int idHistoria, String EDGENS, String Observacion){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs;

          try {
            String sql = "INSERT INTO `lab_vih`(`id_historiaVIH`, `EDGENS`, `Observacion`) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, EDGENS);
            ps.setString(3, Observacion);
 
            ps.execute();
   
      
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
         finally {
            closeResources(null, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void ActualizarVIH(int idHistoria, String EDGENS, String Observacion)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_vih` SET  `EDGENS`=?,`Observacion`=? WHERE `id_historiaVIH`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
             
           
            ps.setString(1, EDGENS);
            ps.setString(2, Observacion);
            ps.setInt(3, idHistoria);

            int res = ps.executeUpdate();

           
        } catch (Exception e) {
            System.out.println(e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }

                  }
       
       
  
    
    
    
     public void agregarTiroidea(
           
           int idHistoriaTI, 
           String T3libre, 
           String T4libre, 
           String TSH, 
           String T3total, 
           String T4total
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_tiroideo`(`id_historiaTI`, `T3libre`, `T4libre`, `TSH`, `T3total`, `T4total`) VALUES (?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaTI);
            ps.setString(2, T3libre);
            ps.setString(3, T4libre);
            ps.setString(4, TSH);
            ps.setString(5, T3total);
            ps.setString(6, T4total);

 
            ps.execute();
   
     
        } catch (Exception e) {
      
            System.out.println(e);
        }
           finally {
            closeResources(rs, ps, con);
        }
          
          
    } 
  
  
    
       public void actualizarTiroidea(
           
           int idHistoriaTI, 
           String T3libre, 
           String T4libre, 
           String TSH, 
           String T3total, 
           String T4total
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs;

          try {
            String sql = "UPDATE `lab_tiroideo` SET `T3libre`=?,`T4libre`=?,`TSH`=?,`T3total`=?,`T4total`=? WHERE `id_historiaTI`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
            ps.setString(1, T3libre);
            ps.setString(2, T4libre);
            ps.setString(3, TSH);
            ps.setString(4, T3total);
            ps.setString(5, T4total);
            ps.setInt(6, idHistoriaTI);
 
            ps.executeUpdate();
   
    
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
         finally {
            closeResources(null, ps, con);
        }
          
    } 
    
    
    
    public void agregarPsa(int id_historiaPSA, String psalibre, String psatotal, String psalibreytotal){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;


          try {
            String sql = "INSERT INTO `lab_psa`(`id_historiaPSA`, `psalibre`, `psatotal`, `psalibreytotal`) VALUES (?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaPSA);
            ps.setString(2, psalibre);
            ps.setString(3, psatotal);
            ps.setString(4, psalibreytotal);
 
            ps.execute();
   
        
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
           finally {
            closeResources(null, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarPsa(int id_historiaPSA, String psalibre, String psatotal, String psalibreytotal)   {
                   
                   

    
    
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_psa` SET `psalibre`=?,`psatotal`=?,`psalibreytotal`=? WHERE  `id_historiaPSA`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
                   
          
            ps.setString(1, psalibre);
            ps.setString(2, psatotal);
            ps.setString(3, psalibreytotal);
            ps.setInt(4, id_historiaPSA);

            ps.executeUpdate();

      
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeResources(rs, ps, con);
        }
           
           
    
    
    
    
}

    
           
       
 public void agregarUro(
        int idHistoria,
        String COLOR,
        String OLOR,
        String DENSIDAD,
        String ASPECTO,
        String PH,
        String GLUCOSA,
        String PROTEINAS,
        String HEMOGLOBINA,
        String NITRITOS,
        String CETONICOS,
        String UROBILINOGENO,
        String PIGMENTOS,
        String BILIRRUBINA,
        String LEUCOCITOS,
        String BACTERIAS,
        String CELULASEP,
        String HEMATIES,
        String CELULASREDONDAS,
        String FILAMENTOS,
        String cilindros,
        String cristales,
        String fungicas

) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;

    try {
         String sql = "INSERT INTO lab_uroanalisis (id_historiaUR, Color, Olor, Densidad, Aspecto, PH, Glucosa, Proteinas, Hemoglobina, Nitritos, Cetonico, Urobilinogeno, Pgmentos, Bilirrubina, Leucocitos, Bacterias, CelulcasEP, Hematies, CelulasRedondas, Filamentos, cilindros, cristales, Fungicas) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoria);
        ps.setString(2, COLOR);
        ps.setString(3, OLOR);
        ps.setString(4, DENSIDAD);
        ps.setString(5, ASPECTO);
        ps.setString(6, PH);
        ps.setString(7, GLUCOSA);
        ps.setString(8, PROTEINAS);
        ps.setString(9, HEMOGLOBINA);
        ps.setString(10, NITRITOS);
        ps.setString(11, CETONICOS);
        ps.setString(12, UROBILINOGENO);
        ps.setString(13, PIGMENTOS);
        ps.setString(14, BILIRRUBINA);
        ps.setString(15, LEUCOCITOS);
        ps.setString(16, BACTERIAS);
        ps.setString(17, CELULASEP);
        ps.setString(18, HEMATIES);
        ps.setString(19, CELULASREDONDAS);
        ps.setString(20, FILAMENTOS);
        ps.setString(21, cilindros);
        ps.setString(22, cristales);
        ps.setString(23, fungicas);

        ps.execute();
    } catch (SQLException e) {
        System.out.println("Error al agregar química: " + e);
    }  finally {
            closeResources(null, ps, con);
        }
}

       
       
       
            
   public void actualizarUro(
           
        int idHistoria,
        String COLOR,
        String OLOR,
        String DENSIDAD,
        String ASPECTO,
        String PH,
        String GLUCOSA,
        String PROTEINAS,
        String HEMOGLOBINA,
        String NITRITOS,
        String CETONICOS,
        String UROBILINOGENO,
        String PIGMENTOS,
        String BILIRRUBINA,
        String LEUCOCITOS,
        String BACTERIAS,
        String CELULASEP,
        String HEMATIES,
        String CELULASREDONDAS,
        String FILAMENTOS,
        String cilindros,
        String cristales,
        String fungicas
       
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs = null;

          try {
         String sql = "UPDATE lab_uroanalisis SET Color = ?, Olor = ?, Densidad = ?, Aspecto = ?, PH = ?, " +
                 "Glucosa = ?, Proteinas = ?, Hemoglobina = ?, Nitritos = ?, Cetonico = ?, Urobilinogeno = ?, " +
                 "Pgmentos = ?, Bilirrubina = ?, Leucocitos = ?, Bacterias = ?, CelulcasEP = ?, Hematies = ?, " +
                 "CelulasRedondas = ?, Filamentos = ?, cilindros = ?, cristales = ?, Fungicas = ? WHERE id_historiaUR = ?";
    
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
          
       
        ps.setString(1, COLOR);
        ps.setString(2, OLOR);
        ps.setString(3, DENSIDAD);
        ps.setString(4, ASPECTO);
        ps.setString(5, PH);
        ps.setString(6, GLUCOSA);
        ps.setString(7, PROTEINAS);
        ps.setString(8, HEMOGLOBINA);
        ps.setString(9, NITRITOS);
        ps.setString(10, CETONICOS);
        ps.setString(11, UROBILINOGENO);
        ps.setString(12, PIGMENTOS);
        ps.setString(13, BILIRRUBINA);
        ps.setString(14, LEUCOCITOS);
        ps.setString(15, BACTERIAS);
        ps.setString(16, CELULASEP);
        ps.setString(17, HEMATIES);
        ps.setString(18, CELULASREDONDAS);
        ps.setString(19, FILAMENTOS);
        ps.setString(20, cilindros);
        ps.setString(21, cristales);
        ps.setString(22, fungicas);
        ps.setInt(23, idHistoria);    
            
        ps.execute();
   
            
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
    
    
    
    
    
     
    
     public void agregarCopro(
           
           int idHistoriaCO, 
           String Color, 
           String Aspecto, 
           String Consistencia, 
           String Reaccion, 
           String Moco,
           String Olor, 
           String Sangre, 
           String Alimentos, 
           String Observacion
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_coproanalisis`(`id_historiaCO`, `Color`, `Aspecto`, `Consistencia`, `Reaccion`, `Moco`, `Olor`, `Sangre`, `Alimentos`, `Observacion`) \n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaCO);
            ps.setString(2, Color);
            ps.setString(3, Aspecto);
            ps.setString(4, Consistencia);
            ps.setString(5, Reaccion);
            ps.setString(6, Moco);
            ps.setString(7, Olor);
            ps.setString(8, Sangre);
            ps.setString(9, Alimentos);
            ps.setString(10, Observacion);
 
            ps.execute();
   
        
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
        finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarCopro(
           
           int idHistoriaCO, 
           String Color, 
           String Aspecto, 
           String Consistencia, 
           String Reaccion, 
           String Moco,
           String Olor, 
           String Sangre, 
           String Alimentos, 
           String Observacion
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_coproanalisis` \n" +
"SET `Color` = ?, `Aspecto` = ?, `Consistencia` = ?, `Reaccion` = ?, `Moco` = ?, \n" +
"    `Olor` = ?, `Sangre` = ?, `Alimentos` = ?, `Observacion` = ? \n" +
"WHERE `id_historiaCO` = ?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
        
            ps.setString(1, Color);
            ps.setString(2, Aspecto);
            ps.setString(3, Consistencia);
            ps.setString(4, Reaccion);
            ps.setString(5, Moco);
            ps.setString(6, Olor);
            ps.setString(7, Sangre);
            ps.setString(8, Alimentos);
            ps.setString(9, Observacion);
            ps.setInt(10, idHistoriaCO);
            ps.executeUpdate();
   
          
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
         finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
    
    
    
       
       
       
       
       
       
        public void agregarSerologia(
           
           int idHistoriaSE, 
           String VDRL,
           String embarazo, 
           String reumatoideo, 
           String reactiva, 
           String asto, 
           String observaciones
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs = null;

          try {
            String sql = "INSERT INTO `lab_serologia`(`id_historiaSE`, `VDRL`,`embarazo`, `reumatoideo`, `reactiva`, `asto`, `observaciones`)\n" +
"VALUES (?, ?, ?, ?, ?, ?,?);";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaSE);
            ps.setString(2, VDRL);
            ps.setString(3, embarazo);
            ps.setString(4, reumatoideo);
            ps.setString(5, reactiva);
            ps.setString(6, asto);
            ps.setString(7, observaciones);

 
            ps.execute();
   
     
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
       finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarSerologia(
           
     int idHistoriaSE, 
           String VDRL,
           String embarazo, 
           String reumatoideo, 
           String reactiva, 
           String asto, 
           String observaciones
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_serologia`\n" +
"SET `VDRL`=?,`embarazo`=?, `reumatoideo`=?, `reactiva`=?, `asto`=?, `observaciones`=?\n" +
"WHERE `id_historiaSE`= ?;";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
            ps.setString(1, VDRL);
            ps.setString(2, embarazo);
            ps.setString(3, reumatoideo);
            ps.setString(4, reactiva);
            ps.setString(5, asto);
            ps.setString(6, observaciones);
            ps.setInt(7, idHistoriaSE);
 
            ps.executeUpdate();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
            finally {
            closeResources(rs, ps, con);
        }
          
    } 
       
       
       
       
       
       
       
       
       
       
       
          
       
 public void agregarQuimicaP(
        int idHistoria,
        String GLICEMIA,
        String UREA,
        String CREATININA,
        String ACIDO_URICO,
        String COLESTEROL,
        String TRIGLICERIDOS,
        String HDL,
        String LDL,
        String VLDL,
        String BILITOTAL,
        String BILIDIRECTA,
        String BILIINDIRECTA,
        String PROTEINAS_TOTALES,
        String ALBUMINA,
        String GLOBULINAS,
        String RELACION,
        String TGO,
        String TGP,
        String FOSFATASA,
        String CALCIO,
        String PANDRIAL,
        String FOSFORO,
        String MAGNESIO
      
) {
    Connection con = null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps = null;
    ResultSet rs=null;
    try {
     String sql = "INSERT INTO `lab_quimica`(`id_historiaQU`, `GLICEMIA`, `UREA`, `CREATININA`, `ACIDO_URICO`, `COLESTEROL`, `TRIGLICERIDOS`, `HDL`, `LDL`, `VLDL`, `BILITOTAL`, `BILIDIRECTA`, `BILIINDIRECTA`, `PROTEINAS_TOTALES`, `ALBUMINA`, `GLOBULINAS`, `RELACION`, `TGO`, `TGP`,`FOSFATASA`, `CALCIO`, `GLICEMIAPANDRIAL`, FOSFORO, MAGNESIO) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";


        con = cn.getConnection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, idHistoria);
        ps.setString(2, GLICEMIA);
        ps.setString(3, UREA);
        ps.setString(4, CREATININA);
        ps.setString(5, ACIDO_URICO);
        ps.setString(6, COLESTEROL);
        ps.setString(7, TRIGLICERIDOS);
        ps.setString(8, HDL);
        ps.setString(9, LDL);
        ps.setString(10, VLDL);
        ps.setString(11, BILITOTAL);
        ps.setString(12, BILIDIRECTA);
        ps.setString(13, BILIINDIRECTA);
        ps.setString(14, PROTEINAS_TOTALES);
        ps.setString(15, ALBUMINA);
        ps.setString(16, GLOBULINAS);
        ps.setString(17, RELACION);
        ps.setString(18, TGO);
        ps.setString(19, TGP);
        ps.setString(20, FOSFATASA);
        ps.setString(21, CALCIO);
        ps.setString(22, PANDRIAL);
        ps.setString(23, FOSFORO);
        ps.setString(24, MAGNESIO);
        
        ps.execute();
    } catch (SQLException e) {
        System.out.println("Error al agregar química: " + e);
    }  finally {
            closeResources(rs, ps, con);
        }
}

       
       
       
            
   public void actualizarQuimicaP(
           
        int idHistoria,
        String GLICEMIA,
        String UREA,
        String CREATININA,
        String ACIDO_URICO,
        String COLESTEROL,
        String TRIGLICERIDOS,
        String HDL,
        String LDL,
        String VLDL,
        String BILITOTAL,
        String BILIDIRECTA,
        String BILIINDIRECTA,
        String PROTEINAS_TOTALES,
        String ALBUMINA,
        String GLOBULINAS,
        String RELACION,
        String TGO,
        String TGP,
        String FOSFATASA,
        String CALCIO,
        String PANDRIAL,
        String FOSFORO,
        String MAGNESIO
     
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_quimica`\n" +
"SET `GLICEMIA`=?, `UREA`=?, `CREATININA`=?, `ACIDO_URICO`=?, `COLESTEROL`=?, `TRIGLICERIDOS`=?, `HDL`=?, `LDL`=?, `VLDL`=?, `BILITOTAL`=?, `BILIDIRECTA`=?, `BILIINDIRECTA`=?, `PROTEINAS_TOTALES`=?, `ALBUMINA`=?, `GLOBULINAS`=?, `RELACION`=?, `TGO`=?, `TGP`=?, `FOSFATASA`=?, `CALCIO`=? , `GLICEMIAPANDRIAL`=?, `FOSFORO`=?, `MAGNESIO`=?\n" +
"WHERE `id_historiaQU`=?;";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
          
            ps.setString(1, GLICEMIA);
            ps.setString(2, UREA);
            ps.setString(3, CREATININA);
            ps.setString(4, ACIDO_URICO);
            ps.setString(5, COLESTEROL);
            ps.setString(6, TRIGLICERIDOS);
            ps.setString(7, HDL);
            ps.setString(8, LDL);
            ps.setString(9, VLDL);
            ps.setString(10, BILITOTAL);
            ps.setString(11, BILIDIRECTA);
            ps.setString(12, BILIINDIRECTA);
            ps.setString(13, PROTEINAS_TOTALES);
            ps.setString(14, ALBUMINA);
            ps.setString(15, GLOBULINAS);
            ps.setString(16, RELACION);
            ps.setString(17, TGO);
            ps.setString(18, TGP);
            ps.setString(19, FOSFATASA);
            ps.setString(20, CALCIO);
            ps.setString(21, PANDRIAL);
            ps.setString(22, FOSFORO);
            ps.setString(23, MAGNESIO);
            ps.setInt(24, idHistoria);
            
            
            ps.execute();
   
           
        } catch (Exception e) {
      
            System.out.println(e);
        }
         finally {
            closeResources(rs, ps, con);
        }
          
    } 
       
       
       
        public void agregarPTT(int idHistoriaPTT, String TiempoPro, String TiempoParTro, String VSG, String ISI, String INR){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_ptt`(`id_historiaPTT`, `TiempoPro`, `TiempoParTro`, `VSG`,`ISI`,`INR` ) VALUES (?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaPTT);
            ps.setString(2, TiempoPro);
            ps.setString(3, TiempoParTro);
            ps.setString(4, VSG);
            ps.setString(5, ISI);
            ps.setString(6, INR);
            ps.execute();
   
          
        } catch (Exception e) {
      
            System.out.println(e);
        }
       finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarPTT(int idHistoriaPTT, String TiempoPro, String TiempoParTro, String VSG, String ISI, String INR)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_ptt` SET `TiempoPro`=?,`TiempoParTro`=?,`VSG`=?, `ISI`=?,`INR`=? WHERE `id_historiaPTT`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           
            
            ps.setString(1, TiempoPro);
            ps.setString(2, TiempoParTro);
            ps.setString(3, VSG);
            ps.setString(4, ISI);
            ps.setString(5, INR);
            ps.setInt(6, idHistoriaPTT);

            ps.executeUpdate();

         
        } catch (Exception e) {
            System.out.println(e);
        }
        
       finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
       
    
    
    
    public void AgregarVIH20(int idHistoria, String EDGENS, String VDRL){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_vih`(`id_historiaVIH`, `EDGENS`, `VDRL`) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, EDGENS);
            ps.setString(3, VDRL);
 
            ps.execute();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        }
       finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void ActualizarVIH20(int idHistoria, String EDGENS, String VDRL)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_vih` SET `EDGENS`=?, `VDRL`=? WHERE `id_historiaVIH`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
             
           
            ps.setString(1, EDGENS);
            ps.setString(2, VDRL);
            ps.setInt(3, idHistoria);
            ps.executeUpdate();

         
        } catch (Exception e) {
            System.out.println("error en actualizar" + e);
        }
        
       finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
    
    
    
    
     public void agregarPreoQuimica(int idHistoria, String GLICEMIA, String UREA, String CREATININA){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_quimica`(`id_historiaQU`, `GLICEMIA`, `UREA`, `CREATININA`) VALUES (?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, GLICEMIA);
            ps.setString(3, UREA);
            ps.setString(4, CREATININA);
 
            ps.execute();
   
       
        } catch (Exception e) {
      
            System.out.println(e);
        }
     finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarPreoQuimica(int idHistoria, String GLICEMIA, String UREA, String CREATININA)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_quimica` SET  `GLICEMIA`=?,`UREA`=?, `CREATININA`=? WHERE `id_historiaQU`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
             
           
            ps.setString(1, GLICEMIA);
            ps.setString(2, UREA);
            ps.setString(3, CREATININA);
            ps.setInt(4, idHistoria);

            int res = ps.executeUpdate();

         
        } catch (Exception e) {
            System.out.println(e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
    

    
    
       public void agregarPrePTT(int idHistoriaPTT, String TiempoPro, String TiempoParTro){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_ptt`(`id_historiaPTT`, `TiempoPro`, `TiempoParTro`) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaPTT);
            ps.setString(2, TiempoPro);
            ps.setString(3, TiempoParTro);
 
            ps.execute();
   
           
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
    finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarPrePTT(int idHistoriaPTT, String TiempoPro, String TiempoParTro)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_ptt` SET `TiempoPro`=?,`TiempoParTro`=? WHERE `id_historiaPTT`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           
            
            ps.setString(1, TiempoPro);
            ps.setString(2, TiempoParTro);
            ps.setInt(3, idHistoriaPTT);

            ps.executeUpdate();

          
        } catch (Exception e) {
            System.out.println(e);
        }
        
         finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
    
    
    
        public void agregarTiroideaEspecial(
           
           int idHistoriaTI, 
           String T3libre, 
           String T4libre, 
           String TSH, 
           String T3total, 
           String T4total,
           String TSH_BIOME, 
           String T3L_REACTIVA, 
           String T4L_REACTIVA, 
           String T3T_AUTOBIO, 
           String T4T_AUTOBIO
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_tiroideo`(`id_historiaTI`, `T3libre`, `T4libre`, `TSH`, `T3total`, `T4total`, `TSH_BIOME`, `T3L_REACTIVA`, `T4L_REACTIVA`, `T3T_AUTOBIO`, `T4T_AUTOBIO`)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoriaTI);
            ps.setString(2, T3libre);
            ps.setString(3, T4libre);
            ps.setString(4, TSH);
            ps.setString(5, T3total);
            ps.setString(6, T4total);
            ps.setString(7, TSH_BIOME);
            ps.setString(8, T3L_REACTIVA);
            ps.setString(9, T4L_REACTIVA);
            ps.setString(10, T3T_AUTOBIO);
            ps.setString(11, T4T_AUTOBIO);

 
            ps.execute();
   
       
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
         finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarTiroideaEspecial(
           
        int idHistoriaTI, 
           String T3libre, 
           String T4libre, 
           String TSH, 
           String T3total, 
           String T4total,
           String TSH_BIOME, 
           String T3L_REACTIVA, 
           String T4L_REACTIVA, 
           String T3T_AUTOBIO, 
           String T4T_AUTOBIO
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_tiroideo`\n" +
"SET\n" +
"  `T3libre` = ?,\n" +
"  `T4libre` = ?,\n" +
"  `TSH` = ?,\n" +
"  `T3total` = ?,\n" +
"  `T4total` = ?,\n" +
"  `TSH_BIOME` = ?,\n" +
"  `T3L_REACTIVA` = ?,\n" +
"  `T4L_REACTIVA` = ?,\n" +
"  `T3T_AUTOBIO` = ?,\n" +
"  `T4T_AUTOBIO` = ?\n" +
"WHERE\n" +
"  `id_historiaTI` = ?;";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
             
            ps.setString(1, T3libre);
            ps.setString(2, T4libre);
            ps.setString(3, TSH);
            ps.setString(4, T3total);
            ps.setString(5, T4total);
            ps.setString(6, TSH_BIOME);
            ps.setString(7, T3L_REACTIVA);
            ps.setString(8, T4L_REACTIVA);
            ps.setString(9, T3T_AUTOBIO);
            ps.setString(10, T4T_AUTOBIO);;
            ps.setInt(11, idHistoriaTI);
            
            
            ps.executeUpdate();
   
        } catch (Exception e) {
      
            System.out.println(e);
        }
           finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
    
    
    
    
    
          public void agregarInsulinaEspecial(
           
           int Id_historiaINSU, 
           String InsulinaBasal, 
           String InsulinaPandrial, 
           String insulina_Reactiva, 
           String insulina_PostReac, 
           String insulina_inmukit,
           String insulina_Postinmukit 

 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_insulina`(`Id_historiaINSU`, `InsulinaBasal`, `InsulinaPandrial`, `insulina_Reactiva`, `insulina_PostReac`, `insulina_inmukit`, `insulina_Postinmukit`)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?);";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, Id_historiaINSU);
            ps.setString(2, InsulinaBasal);
            ps.setString(3, InsulinaPandrial);
            ps.setString(4, insulina_Reactiva);
            ps.setString(5, insulina_PostReac);
            ps.setString(6, insulina_inmukit);
            ps.setString(7, insulina_Postinmukit);


 
            ps.execute();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        }
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarInsulinaEspecial(
           
           int Id_historiaINSU, 
           String InsulinaBasal, 
           String InsulinaPandrial, 
           String insulina_Reactiva, 
           String insulina_PostReac, 
           String insulina_inmukit,
           String insulina_Postinmukit 
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_insulina`\n" +
"SET\n" +
"  `InsulinaBasal` = ?,\n" +
"  `InsulinaPandrial` = ?,\n" +
"  `insulina_Reactiva` = ?,\n" +
"  `insulina_PostReac` = ?,\n" +
"  `insulina_inmukit` = ?,\n" +
"  `insulina_Postinmukit` = ?\n" +
"WHERE\n" +
"  `Id_historiaINSU` = ?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
             
            
            ps.setString(1, InsulinaBasal);
            ps.setString(2, InsulinaPandrial);
            ps.setString(3, insulina_Reactiva);
            ps.setString(4, insulina_PostReac);
            ps.setString(5, insulina_inmukit);
            ps.setString(6, insulina_Postinmukit);
            ps.setInt(7, Id_historiaINSU);
            
            ps.executeUpdate();
   
        
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
        finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
       
       

       
       
    public void agregarFerritina(int id_historiaFERR, String Ferritina){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_ferrocinetico`( `id_historiaFR`, `Ferritina`) VALUES (?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaFERR);
            ps.setString(2, Ferritina);
         
 
            ps.execute();
   
         
        } catch (Exception e) {
      
            System.out.println("error en agregarFerritina"+ e);
        }
          
        finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
       
    public void actualizarFerritina(int id_historiaFERR, String Ferritina){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_ferrocinetico` SET `Ferritina`=? WHERE `id_historiaFR`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
            ps.setString(1, Ferritina);
            ps.setInt(2, id_historiaFERR);
 
            ps.executeUpdate();
   
          
        } catch (Exception e) {
      
            System.out.println(e);
        } 
        finally {
            closeResources(rs, ps, con);
        }
          
    } 
     

    
    
    
    
    
        public void agregarToxoplasma(int id_historiaTO, String igm,  String igg ){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_toxo`(`id_historiaTO`, `IGM`, `IGG` ) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaTO);
            ps.setString(2, igm);
            ps.setString(3, igg);
 
            ps.execute();
   
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
       
    public void actualizarToxoplasma(int id_historiaTO, String igm, String igg){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_toxo` SET `IGM`=?, `IGG`=? WHERE `id_historiaTO`=?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
            ps.setString(1, igm);
            ps.setString(2, igg);
            ps.setInt(3, id_historiaTO);
 
            ps.executeUpdate();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        } 
          
    finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
    
    
    
    
     public void agregarPCR(int idHistoria, String pcr){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_pcr`(`id_historiaPCR`, `PCR`) VALUES (?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, pcr);
 
 
            ps.execute();
   

        } catch (Exception e) {
      
            System.out.println(e);
        }
          
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarPCR(int idhistoria, String pcr)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_pcr` SET `PCR`=? WHERE `id_historiaPCR`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pcr);
            ps.setInt(2, idhistoria);


            ps.executeUpdate();

           
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
    
    
    
    
    
      public void agregarHepatitis(
           
           int id_historiaHE, 
           String hepatitisA, 
           String hepatitisB_biome, 
           String hepatitisB, 
           String anticoreB, 
           String hepatitisC
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_hepatitis`(`id_historiaHE`, `hepatitisA`, `hepatitisB_biome`, `hepatitisB`, `anticoreB`, `hepatitisC`) VALUES (?,?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaHE);
            ps.setString(2, hepatitisA);
            ps.setString(3, hepatitisB_biome);
            ps.setString(4, hepatitisB);
            ps.setString(5, anticoreB);
            ps.setString(6, hepatitisC);


 
            ps.execute();
   

        } catch (Exception e) {
      
            System.out.println(e);
        }  
     finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarHepatitis(
           
        int id_historiaHE, 
           String hepatitisA, 
           String hepatitisB_biome, 
           String hepatitisB, 
           String anticoreB, 
           String hepatitisC

 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
              
           String sql = "UPDATE lab_hepatitis SET hepatitisA=?, hepatitisB_biome=?, hepatitisB=?, anticoreB=?, hepatitisC=? WHERE id_historiaHE=?";
         
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
             
              
            ps.setString(1, hepatitisA);
            ps.setString(2, hepatitisB_biome);
            ps.setString(3, hepatitisB);
            ps.setString(4, anticoreB);
            ps.setString(5, hepatitisC);
            ps.setInt(6, id_historiaHE);
            
            
            ps.executeUpdate();
   
  
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
    finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
    
    
    
     
    
     public void agregarSangre(int idHistoria, String sangre, String descripcion){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_sangrehe`(`id_historiaSa`, `Sangre`, `Descripcion`) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idHistoria);
            ps.setString(2, sangre);
            ps.setString(3, descripcion);
 
            ps.execute();
   

        } catch (Exception e) {
      
            System.out.println(e);
        }
          
     finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarSangre(int idhistoria, String sangre, String descripcion)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_sangrehe` SET `Sangre`=?,`Descripcion`=? WHERE `id_historiaSa`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sangre);
            ps.setString(2, descripcion);
            ps.setInt(3, idhistoria);


            ps.executeUpdate();

         
        } catch (Exception e) {
            System.out.println(e);
        }
     finally {
            closeResources(rs, ps, con);
        }
           
           
                  }
    
    
    
    
    
    public void agregarHelico(int id_historiaHE, String PyloriIgM, String PyloriIgG){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_helycobacter`(`id_historiaHE`, `PyloriIgM`, `PyloriIgG`) VALUES (?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaHE);
            ps.setString(2, PyloriIgM);
            ps.setString(3, PyloriIgG);

 
            ps.execute();
   
        
        } catch (Exception e) {
      
            System.out.println(e);
        } 
       finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarHelico(int id_historiaHE, String PyloriIgM, String PyloriIgG)   {
                   
                   

    
    
        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_helycobacter` SET `PyloriIgM`=?,`PyloriIgG`=? WHERE `id_historiaHE`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
                   
          

            ps.setString(1, PyloriIgM);
            ps.setString(2, PyloriIgG);
            ps.setInt(3, id_historiaHE);
            
            ps.executeUpdate();

     
        } catch (Exception e) {
            System.out.println(e);
        }
        
        finally {
            closeResources(rs, ps, con);
        }
           
           
    
    
    
    
}
    
    
         
    
     public void agregarGrupo(int idhistoria, String grupo, String factorRh, String observacion){                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_grupo`(`id_historia`, `grupo`, `factorRh`, `observacion`) VALUES (?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, idhistoria);
            ps.setString(2, grupo);
            ps.setString(3, factorRh);
            ps.setString(4, observacion);
            ps.execute();
   

        } catch (Exception e) {
      
            System.out.println(e);
        } 
     finally {
            closeResources(rs, ps, con);
        }
          
    } 
     
 
  
        
    
  
    public void actualizarGrupo(int idhistoria, String grupo, String factorRh, String observacion)   {
                   
                   

        Connection con=null;
        EnlaceBd cn = new EnlaceBd();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String sql = "UPDATE `lab_grupo` SET `grupo`=?,`factorRh`=?,`observacion`=? WHERE `id_historia`=?" ;
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setString(1, grupo);
            ps.setString(2, factorRh);
            ps.setString(3, observacion);
            ps.setInt(4, idhistoria);


            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        
       finally {
            closeResources(rs, ps, con);
        }
    
    
    
    
       
}


    
    
    
    
    
    
      public void agregarCreatinina(
           
           int id_historiaCRE, 
           String peso, 
           String talla, 
           String volumenOrina, 
           String superficieCorpo,
           String creatininaSangre,
           String creatininaOrina,
           String depuracionCreatinina,
           String valorRef,
           String observacion
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_creatinina` (`id_historia`, `peso`, `talla`, `volumenOrina`, `superficieCorpo`, `creatininaSangre`, `creatininaOrina`, `depuracionCreatinina`, `valorRef`, `observacion`)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, id_historiaCRE);
            ps.setString(2, peso);
            ps.setString(3, talla);
            ps.setString(4, volumenOrina);
            ps.setString(5, superficieCorpo);
            ps.setString(6, creatininaSangre);
            ps.setString(7, creatininaOrina);
            ps.setString(8, depuracionCreatinina);
            ps.setString(9, valorRef);
            ps.setString(10, observacion);
 
            ps.execute();
   
        } catch (Exception e) {
      
            System.out.println(e);
        }
          
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarCreatinina(
           
           int id_historiaCRE, 
           String peso, 
           String talla, 
           String volumenOrina, 
           String superficieCorpo,
           String creatininaSangre,
           String creatininaOrina,
           String depuracionCreatinina,
           String valorRef,
           String observacion

 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
              
           String sql = "UPDATE `lab_creatinina` \n" +
"SET `peso`=?, `talla`=?, `volumenOrina`=?, `superficieCorpo`=?, `creatininaSangre`=?, `creatininaOrina`=?, `depuracionCreatinina`=?, `valorRef`=?, `observacion`=? \n" +
"WHERE `id_historia`=?;";
         
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
             
              
           
            ps.setString(1, peso);
            ps.setString(2, talla);
            ps.setString(3, volumenOrina);
            ps.setString(4, superficieCorpo);
            ps.setString(5, creatininaSangre);
            ps.setString(6, creatininaOrina);
            ps.setString(7, depuracionCreatinina);
            ps.setString(8, valorRef);
            ps.setString(9, observacion);
            ps.setInt(10, id_historiaCRE);
            
            
            ps.executeUpdate();
   

        } catch (Exception e) {
      
            System.out.println(e);
        }
      finally {
            closeResources(rs, ps, con);
        }
          
    } 
    
    
    
    
    
    
    
          public void agregarEspecial(
           
           int Id_historia, 
           String Troponina, 
           String Hemoglobina, 
           String dengueigm, 
           String dengueigg 
      

 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "INSERT INTO `lab_especial`( `id_historia`, `Troponina`, `Hemoglobina`, `dengueigm`, `dengueigg`) VALUES (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
            ps.setInt(1, Id_historia);
            ps.setString(2, Troponina);
            ps.setString(3, Hemoglobina);
            ps.setString(4, dengueigm);
            ps.setString(5, dengueigg);



 
            ps.execute();
   
        
        } catch (Exception e) {
      
            System.out.println(e);
        } 
         finally {
            closeResources(rs, ps, con);
        }
          
    } 
  
  
    
       public void actualizarEspecial(
           
         int Id_historia, 
           String Troponina, 
           String Hemoglobina, 
           String dengueigm, 
           String dengueigg 
       
 
   )
   {                                             
    

    Connection con=null;
    EnlaceBd cn = new EnlaceBd();
    PreparedStatement ps=null;
    ResultSet rs=null;

          try {
            String sql = "UPDATE `lab_especial` SET `Troponina`=?,`Hemoglobina`=?,`dengueigm`=?,`dengueigg`=? WHERE `id_historia`=?";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
      
           
             
            
       
            ps.setString(1, Troponina);
            ps.setString(2, Hemoglobina);
            ps.setString(3, dengueigm);
            ps.setString(4, dengueigg);
            ps.setInt(5, Id_historia);
              
            ps.executeUpdate();
   
         
        } catch (Exception e) {
      
            System.out.println(e);
        }
        finally {
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
}