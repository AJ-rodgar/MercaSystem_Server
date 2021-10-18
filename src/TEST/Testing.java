package TEST;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import modelos.*;

public class Testing {
    

    public static void main(String[] args) {
	
	Producto p = new Producto();
	Compra compra = new Compra();
	Consumible c = new Consumible();
	compra.getListaCompra().add(new Linea());
	
	HashMap<Consumible, Integer> map = new HashMap<>();
	map.put(new Consumible(), 0);
	
	List<Linea> l = new ArrayList<>();
	l.add(new Linea());
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String json = gson.toJson(compra);
	
	//System.out.println(json);
	//System.out.println(compra.getPrecioTotal());
	
	Ubicacion u = new Ubicacion(Localizacion.Almacen);
	System.out.println(u.getCantidadMaximaConsumible());
	
    }

}
