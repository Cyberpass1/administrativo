package Clases;

import java.awt.Image;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tabla_PdfVO {

    PdfDAO1 dao = null;

    public void visualizar_PdfVO(JTable tabla) {
        
        
        try {
        
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
 
        
        dt.addColumn("Código");
        dt.addColumn("Examen");
        dt.addColumn("Paciente");
        dt.addColumn("Usuario encargado");
        dt.addColumn("Fecha");
        dt.addColumn("archivopdf");

        ImageIcon icono = null;
        if (get_Image("/Logos/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Logos/32pdf.png"));
        }

        dao = new PdfDAO1();
        PdfVO vo = new PdfVO();
        ArrayList<PdfVO> list = dao.Listar_PdfVO();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[10];
                vo = list.get(i);
                fila[0] = vo.getCodigopdf();
                fila[1] = vo.getExamen();
                fila[2] = vo.getCedulapaciente();
                fila[3] = vo.getUsuario();
                fila[4] = vo.getFecha();
                
                
                
                
                
                if (vo.getArchivopdf() != null) {
                    fila[5] = new JButton(icono);
                } else {
                    fila[5] = new JButton("Vacio");
                }

                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
        }
        
                 } catch (Exception e) { System.out.println(e);
        }
        
        
        
        
        
    }

    public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) { System.out.println(e);
        }
        return null;
    }
}
