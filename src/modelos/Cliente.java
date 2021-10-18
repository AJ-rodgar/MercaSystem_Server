package modelos;

import java.sql.Date;

public class Cliente extends Usuario{
    private int idCliente;

    public Cliente() {
	super();
	idCliente = 0;
    }
    
    public Cliente(String nombre, String apellido1, String apellido2, 
	    	   Date fechaNacimiento, Direccion direccion, String telefono,
	    	   String email, String usuario, String contrasenya, int idCliente) {
    	super(nombre, apellido1, apellido2, fechaNacimiento, direccion, telefono, email,
    	      usuario, contrasenya);
    	this.idCliente = idCliente;
    }
    
    public int getIdCliente() {
    	return idCliente;
    }
}
