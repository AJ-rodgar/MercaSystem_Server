package modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Linea {

    private Consumible consumible;
    private int cantidad;
	
    public Linea() {
	setConsumible(new Consumible());
	setCantidad(6);
    }
    
    public Linea(Consumible consumible, int cantidad) {
	this.setConsumible(consumible);
	this.setCantidad(cantidad);
    }

    public Consumible getConsumible() {
	return consumible;
    }

    public void setConsumible(Consumible consumible) {
	this.consumible = consumible;
    }

    public int getCantidad() {
	return cantidad;
    }

    public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
    }
    
    public double getPrecioCompra() {
	return new BigDecimal(consumible.getPrecioCompra() * cantidad).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    public double getPrecioVenta() {
	return new BigDecimal(consumible.getPrecioVenta() * cantidad).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
}
