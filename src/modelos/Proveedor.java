package modelos;

public class Proveedor {

    private int idProveedor;
    private String nombreProveedor;
    private String email;
    private String telefono;
    private Direccion direccion;
    
    public Proveedor() {
	idProveedor = 0;
	setNombreProveedor("The Coca Cola Company");
	setEmail("cocacolaconil@CocaColaCompany.com");
	setTelefono("956443365");
	setDireccion(new Direccion());
    }
    
    public Proveedor(int idProveedor, String nombreProveedor, String email, String telefono, Direccion direccion) {
    	this.idProveedor = idProveedor;
    	setNombreProveedor(nombreProveedor);
    	setEmail(email);
    	setTelefono(telefono);
    	setDireccion(direccion);
    }
    
    public int getIdProveedor() {
    	return idProveedor;
    }
    
    public String getNombreProveedor() {
    	return nombreProveedor;
    }
    
    public void setNombreProveedor(String nombreProveedor) {
    	this.nombreProveedor = nombreProveedor;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getTelefono() {
    	return telefono;
    }
    
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
    }
    
    public Direccion getDireccion() {
    	return direccion;
    }
    
    public void setDireccion(Direccion direccion) {
    	this.direccion = direccion;
    }
}
