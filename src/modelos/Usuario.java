package modelos;

import java.sql.Date;

public abstract class Usuario {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private Direccion direccion;
    private String telefono;
    private String email;
    private String nombreUsuario;
    private String contrasenya;
	
    public Usuario() {
	setNombre("Merlí");
	setApellido1("Bergeron");
	setApellido2("García");
	setFechaNacimiento(Date.valueOf("1980-10-10"));
	setDireccion(new Direccion());
	setTelefono("000000000");
	setEmail("merlibergeron@gmail.com");
	setNombreUsuario("Peripatetico");
	setContrasenya("Plotino");
    }
    
    public Usuario(String nombre, String apellido1, String apellido2, Date fechaNacimiento, Direccion direccion,
    			   String telefono, String email, String usuario, String contrasenya) {
    	setNombre(nombre);
    	setApellido1(apellido1);
    	setApellido2(apellido2);
    	setFechaNacimiento(fechaNacimiento);
    	setDireccion(direccion);
    	setTelefono(telefono);
    	setEmail(email);
    	setNombreUsuario(usuario);
    	setContrasenya(contrasenya);
    }
    
    public String getNombre() {
	return nombre;
    }
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public String getApellido1() {
    	return apellido1;
    }
    
    public void setApellido1(String apellido1) {
    	this.apellido1 = apellido1;
    }
    
    public String getApellido2() {
    	return apellido2;
    }
    
    public void setApellido2(String apellido2) {
    	this.apellido2 = apellido2;
    }
    
    public Date getFechaNacimiento() {
    	return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
    	this.fechaNacimiento = fechaNacimiento;
    }
    
    public Direccion getDireccion() {
    	return direccion;
    }
    
    public void setDireccion(Direccion direccion) {
    	this.direccion = direccion;
    }
    
    public String getTelefono() {
    	return telefono;
    }
    
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getNombreUsuario() {
    	return nombreUsuario;
    }
    
    public void setNombreUsuario(String usuario) {
    	this.nombreUsuario = usuario;
    }
    
    public String getContrasenya() {
    	return contrasenya;
    }
    
    public void setContrasenya(String contrasenya) {
    	this.contrasenya = contrasenya;
    }
	
}
