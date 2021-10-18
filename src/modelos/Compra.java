package modelos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    private int idCompra;
    private Cliente cliente;
    private List<Linea> listaCompra;
    private Date fechaCompra;
    
    public Compra() {
	idCompra = 0;
	cliente = new Cliente();
	listaCompra = new ArrayList<Linea>();
	fechaCompra = Date.valueOf(LocalDate.now());
    }
    
    public Compra(int idCompra, Cliente cliente, List<Linea> listaCompra, Date fechaCompra) {
    	this.idCompra = idCompra;
    	this.cliente = cliente;
    	this.listaCompra = listaCompra;
    	this.fechaCompra = fechaCompra;
    }

    public int getIdCompra() {
    	return idCompra;
    }
    
    public Cliente getCliente() {
    	return cliente;
    }
    
    public double getPrecioTotal() {
	double res = 0;
	for (Linea l : listaCompra) {
	    res += l.getPrecioVenta();
	}
    	return res;
    }
    
    public Date getFechaCompra() {
    	return fechaCompra;
    }

    public List<Linea> getListaCompra() {
	return listaCompra;
    }
    
    public void addLinea(Linea linea) {
	boolean existe = false;
	
	for (Linea l : listaCompra) {
	    if (l.getConsumible().equals(linea)) {
		l.setCantidad(linea.getCantidad());
		existe = true;
	    }
	}
	
	if (!existe) {
	    listaCompra.add(linea);
	}
    }
    
    public void removeLinea(Linea linea) {
	if (listaCompra.contains(linea)) {
	    listaCompra.remove(linea);
	}
    }
}
