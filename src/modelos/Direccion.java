package modelos;

public class Direccion {
    private String provincia;
    private String ciudad;
    private String calleYnumero;
    
    public Direccion() {
	setProvincia("Barcelona");
	setCiudad("Barcelona");
	setCalleYnumero("Av. de Josep Tarradellas, 5");
    }
    
    public Direccion(String provincia, String ciudad, String calleYnumero) {
    	setProvincia(provincia);
    	setCiudad(ciudad);
    	setCalleYnumero(calleYnumero);
    }
    
    public String getProvincia() {
    	return provincia;
    }
    
    public void setProvincia(String provincia) {
    	this.provincia = provincia;
    }
    
    public String getCiudad() {
    	return ciudad;
    }
    
    public void setCiudad(String ciudad) {
    	this.ciudad = ciudad;
    }
    
    public String getCalleYnumero() {
    	return calleYnumero;
    }
    
    public void setCalleYnumero(String calleYnumero) {
    	this.calleYnumero = calleYnumero;
    }
}
