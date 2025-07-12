/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;



public class JCFactura {
    
    
    int serv_id;
    String serv_code;
    String servicio;
    String serv_catg;
    String serv_estado;
    String serv_precio;

        
    int idCatgServ;
    String nameCatgServ;
    String stateCatServ;
    
    
    
    int idproducto;
    String Codeproducto;
    String producto;
    String CtgProducto;
    int existencia;
    int stockmin;
    int stockmax;
    String precioPrd;
    String statePrd;
    String code_barra;
    

    
    String cod_fact;
    int id_paciente;
    int id_usuario;
    int metodo_pago;
    int id_pago;
    String fecha_fact;
    byte[] arch_fact;
    String estado_factS;
    int estado_factI;
    

    int id_hon;
    int hon_especialista;
    int hon_especialidad;
    int hon_encargado;
    String hon_fecha1;
    String hon_fecha2;
    byte[] ho_reporte;
    String ho_observacion;
    int ho_estado;
    
    
    
    //tabla honorario
    
    String servicioH;
    int cantidadH;
    String brutoInitH;
    String brutoTotalH;
    String porcentajeH;
    String netoH;
    
    
    
   public JCFactura(){}

    public JCFactura(int serv_id, String serv_code, String servicio, String serv_catg, String serv_estado, String serv_precio, int idCatgServ, String nameCatgServ, String stateCatServ, int idproducto, String Codeproducto, String producto, String CtgProducto, int existencia, int stockmin, int stockmax, String precioPrd, String statePrd, String code_barra, String cod_fact, int id_paciente, int id_usuario, int metodo_pago, int id_pago, String fecha_fact, byte[] arch_fact, String estado_factS, int estado_factI, int id_hon, int hon_especialista, int hon_especialidad, int hon_encargado, String hon_fecha1, String hon_fecha2, byte[] ho_reporte, String ho_observacion, int ho_estado, String servicioH, int cantidadH, String brutoInitH, String brutoTotalH, String porcentajeH, String netoH) {
        this.serv_id = serv_id;
        this.serv_code = serv_code;
        this.servicio = servicio;
        this.serv_catg = serv_catg;
        this.serv_estado = serv_estado;
        this.serv_precio = serv_precio;
        this.idCatgServ = idCatgServ;
        this.nameCatgServ = nameCatgServ;
        this.stateCatServ = stateCatServ;
        this.idproducto = idproducto;
        this.Codeproducto = Codeproducto;
        this.producto = producto;
        this.CtgProducto = CtgProducto;
        this.existencia = existencia;
        this.stockmin = stockmin;
        this.stockmax = stockmax;
        this.precioPrd = precioPrd;
        this.statePrd = statePrd;
        this.code_barra = code_barra;
        this.cod_fact = cod_fact;
        this.id_paciente = id_paciente;
        this.id_usuario = id_usuario;
        this.metodo_pago = metodo_pago;
        this.id_pago = id_pago;
        this.fecha_fact = fecha_fact;
        this.arch_fact = arch_fact;
        this.estado_factS = estado_factS;
        this.estado_factI = estado_factI;
        this.id_hon = id_hon;
        this.hon_especialista = hon_especialista;
        this.hon_especialidad = hon_especialidad;
        this.hon_encargado = hon_encargado;
        this.hon_fecha1 = hon_fecha1;
        this.hon_fecha2 = hon_fecha2;
        this.ho_reporte = ho_reporte;
        this.ho_observacion = ho_observacion;
        this.ho_estado = ho_estado;
        this.servicioH = servicioH;
        this.cantidadH = cantidadH;
        this.brutoInitH = brutoInitH;
        this.brutoTotalH = brutoTotalH;
        this.porcentajeH = porcentajeH;
        this.netoH = netoH;
    }

    public int getServ_id() {
        return serv_id;
    }

    public void setServ_id(int serv_id) {
        this.serv_id = serv_id;
    }

    public String getServ_code() {
        return serv_code;
    }

    public void setServ_code(String serv_code) {
        this.serv_code = serv_code;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getServ_catg() {
        return serv_catg;
    }

    public void setServ_catg(String serv_catg) {
        this.serv_catg = serv_catg;
    }

    public String getServ_estado() {
        return serv_estado;
    }

    public void setServ_estado(String serv_estado) {
        this.serv_estado = serv_estado;
    }

    public String getServ_precio() {
        return serv_precio;
    }

    public void setServ_precio(String serv_precio) {
        this.serv_precio = serv_precio;
    }

    public int getIdCatgServ() {
        return idCatgServ;
    }

    public void setIdCatgServ(int idCatgServ) {
        this.idCatgServ = idCatgServ;
    }

    public String getNameCatgServ() {
        return nameCatgServ;
    }

    public void setNameCatgServ(String nameCatgServ) {
        this.nameCatgServ = nameCatgServ;
    }

    public String getStateCatServ() {
        return stateCatServ;
    }

    public void setStateCatServ(String stateCatServ) {
        this.stateCatServ = stateCatServ;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getCodeproducto() {
        return Codeproducto;
    }

    public void setCodeproducto(String Codeproducto) {
        this.Codeproducto = Codeproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCtgProducto() {
        return CtgProducto;
    }

    public void setCtgProducto(String CtgProducto) {
        this.CtgProducto = CtgProducto;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getStockmin() {
        return stockmin;
    }

    public void setStockmin(int stockmin) {
        this.stockmin = stockmin;
    }

    public int getStockmax() {
        return stockmax;
    }

    public void setStockmax(int stockmax) {
        this.stockmax = stockmax;
    }

    public String getPrecioPrd() {
        return precioPrd;
    }

    public void setPrecioPrd(String precioPrd) {
        this.precioPrd = precioPrd;
    }

    public String getStatePrd() {
        return statePrd;
    }

    public void setStatePrd(String statePrd) {
        this.statePrd = statePrd;
    }

    public String getCode_barra() {
        return code_barra;
    }

    public void setCode_barra(String code_barra) {
        this.code_barra = code_barra;
    }

    public String getCod_fact() {
        return cod_fact;
    }

    public void setCod_fact(String cod_fact) {
        this.cod_fact = cod_fact;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(int metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getFecha_fact() {
        return fecha_fact;
    }

    public void setFecha_fact(String fecha_fact) {
        this.fecha_fact = fecha_fact;
    }

    public byte[] getArch_fact() {
        return arch_fact;
    }

    public void setArch_fact(byte[] arch_fact) {
        this.arch_fact = arch_fact;
    }

    public String getEstado_factS() {
        return estado_factS;
    }

    public void setEstado_factS(String estado_factS) {
        this.estado_factS = estado_factS;
    }

    public int getEstado_factI() {
        return estado_factI;
    }

    public void setEstado_factI(int estado_factI) {
        this.estado_factI = estado_factI;
    }

    public int getId_hon() {
        return id_hon;
    }

    public void setId_hon(int id_hon) {
        this.id_hon = id_hon;
    }

    public int getHon_especialista() {
        return hon_especialista;
    }

    public void setHon_especialista(int hon_especialista) {
        this.hon_especialista = hon_especialista;
    }

    public int getHon_especialidad() {
        return hon_especialidad;
    }

    public void setHon_especialidad(int hon_especialidad) {
        this.hon_especialidad = hon_especialidad;
    }

    public int getHon_encargado() {
        return hon_encargado;
    }

    public void setHon_encargado(int hon_encargado) {
        this.hon_encargado = hon_encargado;
    }

    public String getHon_fecha1() {
        return hon_fecha1;
    }

    public void setHon_fecha1(String hon_fecha1) {
        this.hon_fecha1 = hon_fecha1;
    }

    public String getHon_fecha2() {
        return hon_fecha2;
    }

    public void setHon_fecha2(String hon_fecha2) {
        this.hon_fecha2 = hon_fecha2;
    }

    public byte[] getHo_reporte() {
        return ho_reporte;
    }

    public void setHo_reporte(byte[] ho_reporte) {
        this.ho_reporte = ho_reporte;
    }

    public String getHo_observacion() {
        return ho_observacion;
    }

    public void setHo_observacion(String ho_observacion) {
        this.ho_observacion = ho_observacion;
    }

    public int getHo_estado() {
        return ho_estado;
    }

    public void setHo_estado(int ho_estado) {
        this.ho_estado = ho_estado;
    }

    public String getServicioH() {
        return servicioH;
    }

    public void setServicioH(String servicioH) {
        this.servicioH = servicioH;
    }

    public int getCantidadH() {
        return cantidadH;
    }

    public void setCantidadH(int cantidadH) {
        this.cantidadH = cantidadH;
    }

    public String getBrutoInitH() {
        return brutoInitH;
    }

    public void setBrutoInitH(String brutoInitH) {
        this.brutoInitH = brutoInitH;
    }

    public String getBrutoTotalH() {
        return brutoTotalH;
    }

    public void setBrutoTotalH(String brutoTotalH) {
        this.brutoTotalH = brutoTotalH;
    }

    public String getPorcentajeH() {
        return porcentajeH;
    }

    public void setPorcentajeH(String porcentajeH) {
        this.porcentajeH = porcentajeH;
    }

    public String getNetoH() {
        return netoH;
    }

    public void setNetoH(String netoH) {
        this.netoH = netoH;
    }

   
        
}
