package Clases;


import Menu.Mprincipal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class RestauracionBd{
    
    private static RestauracionBd instancia;
    
    //Creamos el método para generar la restauracion
public void crearRestauracion() throws IOException{ 
    Mprincipal me = new Mprincipal();
    try{
        JFileChooser filechooser = new JFileChooser("C:\\Fundaginebra\\RespaldosBD");
        int resultado = filechooser.showOpenDialog(me.jMenuRestauracion);
        File archivorespaldo = null;
        String dbnombre = null;
                
        if (resultado == JFileChooser.APPROVE_OPTION){
            archivorespaldo = filechooser.getSelectedFile();
            dbnombre = archivorespaldo.getName();
            // No es necesario renombrar el archivo de respaldo
            // archivorespaldo.renameTo(new File("C:\\Fundaginebra\\RespaldosBD\\"+dbnombre)); 

          //Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysql -u admin -p1234 fundaginebrabd");
            Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysql -u admin -pelefante1999 fundaginebrabd");
            OutputStream salida = proceso.getOutputStream();
            FileInputStream archivo = new FileInputStream(archivorespaldo);
        
            byte[] buffer = new byte[1000];
            int byteLeido = archivo.read(buffer);
       
            while(byteLeido > 0){
                salida.write(buffer,0, byteLeido);
                byteLeido = archivo.read(buffer);
            }
        
            salida.flush();
            salida.close();
            archivo.close();
            
            // Mostrar un mensaje de éxito solo si no hay excepciones
            JOptionPane.showMessageDialog(null,"LA RESTAURACIÓN HA SIDO REALIZADA CON ÉXITO", "RESTAURACIÓN", 1);
        }
    } catch(Exception e) {
        // Mostrar la excepción completa en el cuadro de diálogo
        JOptionPane.showMessageDialog(null, "ERROR DURANTE LA RESTAURACIÓN: " + e.getMessage(), "RESTAURACIÓN", JOptionPane.ERROR_MESSAGE); 
    }
}
    
    
    
    
    
    
    
    
    
    public static RestauracionBd getInstance(){
        if(instancia == null){
            instancia = new RestauracionBd();
        }
        return instancia;
    }
}
