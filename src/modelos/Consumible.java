package modelos;

import java.util.ArrayList;
import java.util.List;

public class Consumible {
	
    private int idConsumible;
    private Producto producto;
    private double peso;
    private String recipiente;
    private String tamanyo;
    private double precioCompra;
    private double precioVenta;
    private List<Ubicacion> ubicaciones;
    private boolean descatalogado;
    
    public Consumible() {
	idConsumible = 0;
	producto = new Producto();
	peso = 0.375;
	setRecipiente("Lata");
	setTamanyo("330 ml");
	setPrecioCompra(0.30);
	setPrecioVenta(0.58);
	ubicaciones = new ArrayList<>();
	setDescatalogado(false);
    }
    
    public Consumible(int idConsumible, Producto producto, double peso, 
	    	      String recipiente, String tamanyo, double precioCompra, 
	    	      double precioVenta, List<Ubicacion> ubicaciones, 
	    	      boolean descatalogado) {
	this.idConsumible = idConsumible;
	this.producto = producto;
	this.peso = peso;
    	setRecipiente(recipiente);
    	setTamanyo(tamanyo);
    	setPrecioCompra(precioCompra);
    	setPrecioVenta(precioVenta);
    	this.ubicaciones = ubicaciones;
    	setDescatalogado(descatalogado);
    }
    
    public int getIdConsumible() {
    	return idConsumible;
    }
    
    public Producto getProducto() {
    	return producto;
    }
    
    public double getPeso() {
	return peso;
    }

    public String getRecipiente() {
    	return recipiente;
    }
    
    public void setRecipiente(String recipiente) {
    	this.recipiente = recipiente;
    }
    
    public String getTamanyo() {
    	return tamanyo;
    }
    
    public void setTamanyo(String tamanyo) {
    	this.tamanyo = tamanyo;
    }
    
    public double getPrecioCompra() {
    	return precioCompra;
    }
    
    public void setPrecioCompra(double precioCompra) {
    	this.precioCompra = precioCompra;
    }
    
    public double getPrecioVenta() {
    	return precioVenta;
    }
    
    public void setPrecioVenta(double precioVenta) {
    	this.precioVenta = precioVenta;
    }
    
    public List<Ubicacion> getUbicaciones() {
    	return ubicaciones;
    }
    
    public void addUbicacion(Ubicacion ubicacion) {
	ubicaciones.add(ubicacion);
    }
    
    public void removeUbicacion(Ubicacion ubicacion) {
	if (ubicaciones.contains(ubicacion)) {
	    ubicaciones.remove(ubicacion);
	}
    }
    
    public boolean isDescatalogado() {
    	return descatalogado;
    }
    
    public void setDescatalogado(boolean descatalogado) {
    	this.descatalogado = descatalogado;
    }
}
