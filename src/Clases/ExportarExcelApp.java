package Clases;
//IMPORTACIONES DE LAS LIBRERIAS PARA EXPORTAR
import java.io.*;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

public class ExportarExcelApp {
    //DECLARACION DE VARIABLES PARA EXPORTACION
    private File file;//
    private JTable table; 
    private String nombreTab; 

    //CONSTRUCTOR QUE INICIALIZA LAS VARIABLES
    public ExportarExcelApp(JTable table,File file,String nombreTab)
    {  
        this.file=file;
        this.table=table;
        this.nombreTab=nombreTab;
    }
    //METODO PARA EXPORTAR
    public boolean export(){
    try
    {
        //SE HACE REFERENCIA AL ARCHIVO
        DataOutputStream out=new DataOutputStream(new FileOutputStream(file));
        //LOCALIZACION DE DATOS
        WritableWorkbook w = Workbook.createWorkbook(out); 
        //NOMBRE DE ARCHIVO Y HOJA
        WritableSheet s = w.createSheet(nombreTab, 0);
        //RECORRIDO DE TABLLA Y LLENADO         
        for(int i=0;i< table.getRowCount();i++)
        {
            for(int j=0;j<table.getColumnCount();j++)
            {
                Object objeto=table.getValueAt(i,j);
                s.addCell(new Label(j, i, String.valueOf(objeto)));                
            }
        }   
        //SE ESCRIBE EN EL ARCHIVO EXCEL
        w.write();
        //MENSAJE DE AVISO
        JOptionPane.showMessageDialog(null,"SE HAN EXPORTADO TODOS LOS REGISTROS CORRECTAMENTE.", "DATOS EXPORTADOS CORRECTAMENTE",JOptionPane.INFORMATION_MESSAGE);                           
        w.close();
        out.close();
        //RETORNO        
        return true;
    }
    //EN CASO DE EXCEPCIONES
    catch(IOException ex)
    {
        ex.printStackTrace();
    }
    //EN CASO DE EXCEPCIONES
    catch(WriteException ex)
    {
        ex.printStackTrace();
    }
    //MENSAJE DE ERROR
    JOptionPane.showMessageDialog(null,"NO SE HAN EXPORTADO LOS REGISTROS CORRECTAMENTE.", "FALLO AL EXPORTAR DATOS",JOptionPane.ERROR_MESSAGE);                           
    return false;
    }
}
