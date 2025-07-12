package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;


public class RespaldoBd{
    
    private static RespaldoBd instancia;
    
  
    public void crearBackup() throws IOException{ //puede llamarse crearRespaldo()
       
        try{
     
         DateTimeFormatter fth = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).ofPattern("dd-MM-yyyy--HH-mm");
         LocalDateTime fechaactual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        //Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump -u admin -p1234 fundaginebrabd"); //aqui pones tu ruta de Mysql , usuario y password
        Process proceso = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump -u admin -pelefante1999 fundaginebrabd"); //aqui pones tu ruta de Mysql , usuario y password
        InputStream entrada = proceso.getInputStream();
        FileOutputStream archivo = new FileOutputStream("C:\\Fundaginebra\\RespaldosBD\\RespaldoBD-Fundaginebra" + fth.format(fechaactual) + ".sql");;
     
        
        byte[] buffer = new byte[1000];
        int byteLeido = entrada.read(buffer);
        
        while(byteLeido > 0){
            archivo.write(buffer,0, byteLeido);
            byteLeido = entrada.read(buffer);
        }
        
        archivo.close();
         
         
         
         
        }catch(Exception e){System.out.println(e);}
    }
    
    
    public static RespaldoBd getInstance(){
        if(instancia == null){
            instancia = new RespaldoBd();
        }
        return instancia;
    }
}
