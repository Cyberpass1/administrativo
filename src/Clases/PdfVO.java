package Clases;

public class PdfVO {

    /*Todo los atributos*/
    int codigopdf;
    int idpaciente;
    int idusuario;
    int idexamen;
    String fecha;
    String nombrepdf;
    byte[] archivopdf;
    
   
    int numeropac;
    String cedulapaciente;
    String nombreApellido;
    String usuario;
    String examen;
    String state; 
    String Correo;
    
    
    
    //--HISTORIAS MEDICAS
    int codHistoria;
    String especialidadHistoria;
    String usuarioHistoria;
    String pacienteHistoria;
    String cedHistoria;
    byte[] archivoHistoria;
    String fechaHistoria;
    String estadoHistoria;
    String CorreoHistoria;
    String telefono;
    
    
    // VARIABLES HONORARIOS
    int id_hon;
    String hon_especialista;
    String hon_especialidad;
    String ho_cedula;
    String hon_encargado;
    byte[] ho_reporte;
    String hon_fecha1;
    String hon_fecha2;
    String ho_estado;
    String ho_telefono;
    String ho_correo;
    String netoPagar;
   
    //VARIABLES FACTURACION
    int id_factura;
    String fac_code;
    String fac_nombre;
    String fac_fecha;
    String fac_ced;
    String fac_correo;
    String fac_usuario;
    byte[] fac_reporte;
    String fac_estado;
    String moneda;
    
    
    
    
    
    

    public PdfVO() {
    }

    public PdfVO(int codigopdf, int idpaciente, int idusuario, int idexamen, String fecha, String nombrepdf, byte[] archivopdf, int numeropac, String cedulapaciente, String nombreApellido, String usuario, String examen, String state, String Correo, int codHistoria, String especialidadHistoria, String usuarioHistoria, String pacienteHistoria, String cedHistoria, byte[] archivoHistoria, String fechaHistoria, String estadoHistoria, String CorreoHistoria, String telefono, int id_hon, String hon_especialista, String hon_especialidad, String ho_cedula, String hon_encargado, byte[] ho_reporte, String hon_fecha1, String hon_fecha2, String ho_estado, String ho_telefono, String ho_correo, String netoPagar, int id_factura, String fac_code, String fac_nombre, String fac_fecha, String fac_ced, String fac_correo, String fac_usuario, byte[] fac_reporte, String fac_estado, String moneda) {
        this.codigopdf = codigopdf;
        this.idpaciente = idpaciente;
        this.idusuario = idusuario;
        this.idexamen = idexamen;
        this.fecha = fecha;
        this.nombrepdf = nombrepdf;
        this.archivopdf = archivopdf;
        this.numeropac = numeropac;
        this.cedulapaciente = cedulapaciente;
        this.nombreApellido = nombreApellido;
        this.usuario = usuario;
        this.examen = examen;
        this.state = state;
        this.Correo = Correo;
        this.codHistoria = codHistoria;
        this.especialidadHistoria = especialidadHistoria;
        this.usuarioHistoria = usuarioHistoria;
        this.pacienteHistoria = pacienteHistoria;
        this.cedHistoria = cedHistoria;
        this.archivoHistoria = archivoHistoria;
        this.fechaHistoria = fechaHistoria;
        this.estadoHistoria = estadoHistoria;
        this.CorreoHistoria = CorreoHistoria;
        this.telefono = telefono;
        this.id_hon = id_hon;
        this.hon_especialista = hon_especialista;
        this.hon_especialidad = hon_especialidad;
        this.ho_cedula = ho_cedula;
        this.hon_encargado = hon_encargado;
        this.ho_reporte = ho_reporte;
        this.hon_fecha1 = hon_fecha1;
        this.hon_fecha2 = hon_fecha2;
        this.ho_estado = ho_estado;
        this.ho_telefono = ho_telefono;
        this.ho_correo = ho_correo;
        this.netoPagar = netoPagar;
        this.id_factura = id_factura;
        this.fac_code = fac_code;
        this.fac_nombre = fac_nombre;
        this.fac_fecha = fac_fecha;
        this.fac_ced = fac_ced;
        this.fac_correo = fac_correo;
        this.fac_usuario = fac_usuario;
        this.fac_reporte = fac_reporte;
        this.fac_estado = fac_estado;
        this.moneda = moneda;
    }

    public int getCodigopdf() {
        return codigopdf;
    }

    public void setCodigopdf(int codigopdf) {
        this.codigopdf = codigopdf;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdexamen() {
        return idexamen;
    }

    public void setIdexamen(int idexamen) {
        this.idexamen = idexamen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombrepdf() {
        return nombrepdf;
    }

    public void setNombrepdf(String nombrepdf) {
        this.nombrepdf = nombrepdf;
    }

    public byte[] getArchivopdf() {
        return archivopdf;
    }

    public void setArchivopdf(byte[] archivopdf) {
        this.archivopdf = archivopdf;
    }

    public int getNumeropac() {
        return numeropac;
    }

    public void setNumeropac(int numeropac) {
        this.numeropac = numeropac;
    }

    public String getCedulapaciente() {
        return cedulapaciente;
    }

    public void setCedulapaciente(String cedulapaciente) {
        this.cedulapaciente = cedulapaciente;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getCodHistoria() {
        return codHistoria;
    }

    public void setCodHistoria(int codHistoria) {
        this.codHistoria = codHistoria;
    }

    public String getEspecialidadHistoria() {
        return especialidadHistoria;
    }

    public void setEspecialidadHistoria(String especialidadHistoria) {
        this.especialidadHistoria = especialidadHistoria;
    }

    public String getUsuarioHistoria() {
        return usuarioHistoria;
    }

    public void setUsuarioHistoria(String usuarioHistoria) {
        this.usuarioHistoria = usuarioHistoria;
    }

    public String getPacienteHistoria() {
        return pacienteHistoria;
    }

    public void setPacienteHistoria(String pacienteHistoria) {
        this.pacienteHistoria = pacienteHistoria;
    }

    public String getCedHistoria() {
        return cedHistoria;
    }

    public void setCedHistoria(String cedHistoria) {
        this.cedHistoria = cedHistoria;
    }

    public byte[] getArchivoHistoria() {
        return archivoHistoria;
    }

    public void setArchivoHistoria(byte[] archivoHistoria) {
        this.archivoHistoria = archivoHistoria;
    }

    public String getFechaHistoria() {
        return fechaHistoria;
    }

    public void setFechaHistoria(String fechaHistoria) {
        this.fechaHistoria = fechaHistoria;
    }

    public String getEstadoHistoria() {
        return estadoHistoria;
    }

    public void setEstadoHistoria(String estadoHistoria) {
        this.estadoHistoria = estadoHistoria;
    }

    public String getCorreoHistoria() {
        return CorreoHistoria;
    }

    public void setCorreoHistoria(String CorreoHistoria) {
        this.CorreoHistoria = CorreoHistoria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_hon() {
        return id_hon;
    }

    public void setId_hon(int id_hon) {
        this.id_hon = id_hon;
    }

    public String getHon_especialista() {
        return hon_especialista;
    }

    public void setHon_especialista(String hon_especialista) {
        this.hon_especialista = hon_especialista;
    }

    public String getHon_especialidad() {
        return hon_especialidad;
    }

    public void setHon_especialidad(String hon_especialidad) {
        this.hon_especialidad = hon_especialidad;
    }

    public String getHo_cedula() {
        return ho_cedula;
    }

    public void setHo_cedula(String ho_cedula) {
        this.ho_cedula = ho_cedula;
    }

    public String getHon_encargado() {
        return hon_encargado;
    }

    public void setHon_encargado(String hon_encargado) {
        this.hon_encargado = hon_encargado;
    }

    public byte[] getHo_reporte() {
        return ho_reporte;
    }

    public void setHo_reporte(byte[] ho_reporte) {
        this.ho_reporte = ho_reporte;
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

    public String getHo_estado() {
        return ho_estado;
    }

    public void setHo_estado(String ho_estado) {
        this.ho_estado = ho_estado;
    }

    public String getHo_telefono() {
        return ho_telefono;
    }

    public void setHo_telefono(String ho_telefono) {
        this.ho_telefono = ho_telefono;
    }

    public String getHo_correo() {
        return ho_correo;
    }

    public void setHo_correo(String ho_correo) {
        this.ho_correo = ho_correo;
    }

    public String getNetoPagar() {
        return netoPagar;
    }

    public void setNetoPagar(String netoPagar) {
        this.netoPagar = netoPagar;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getFac_code() {
        return fac_code;
    }

    public void setFac_code(String fac_code) {
        this.fac_code = fac_code;
    }

    public String getFac_nombre() {
        return fac_nombre;
    }

    public void setFac_nombre(String fac_nombre) {
        this.fac_nombre = fac_nombre;
    }

    public String getFac_fecha() {
        return fac_fecha;
    }

    public void setFac_fecha(String fac_fecha) {
        this.fac_fecha = fac_fecha;
    }

    public String getFac_ced() {
        return fac_ced;
    }

    public void setFac_ced(String fac_ced) {
        this.fac_ced = fac_ced;
    }

    public String getFac_correo() {
        return fac_correo;
    }

    public void setFac_correo(String fac_correo) {
        this.fac_correo = fac_correo;
    }

    public String getFac_usuario() {
        return fac_usuario;
    }

    public void setFac_usuario(String fac_usuario) {
        this.fac_usuario = fac_usuario;
    }

    public byte[] getFac_reporte() {
        return fac_reporte;
    }

    public void setFac_reporte(byte[] fac_reporte) {
        this.fac_reporte = fac_reporte;
    }

    public String getFac_estado() {
        return fac_estado;
    }

    public void setFac_estado(String fac_estado) {
        this.fac_estado = fac_estado;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

   
  

}
