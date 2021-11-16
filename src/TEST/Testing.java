package TEST;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import modelos.*;

public class Testing {
    

    public static void main(String[] args) throws NoSuchAlgorithmException {
	
	/*String current = "Bale";
	String selected = "";
	
	TreeMap<String, Integer> mapa = new TreeMap<>();
	mapa.put("Vinicius Jr", 0);
	mapa.put("Benzema", 1);
	mapa.put("Rodrygo", 2);
	mapa.put("Hazard", 3);
	mapa.put("Jovic", 4);
	mapa.put("Bale", 5);
	
	System.out.println(mapa);
	System.out.println("----------------------------------");
	
	for (int i = 0; i < 8; i++) {
	    System.out.println("Ultimo reponedor: " + current);
		
	    for (String s : mapa.keySet()) {
		if (s.compareTo(current) > 0) {
		    selected = s;
		    break;
		}
	    }
	    
	    System.out.println("Siguiente Reponedor: " + selected);
	    System.out.println("----------------------------------");
	    current = selected;
	    if (selected == mapa.lastKey()) {
		selected = mapa.firstKey();
	    }
	    try {
		Thread.sleep(2000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}*/
	
	/*
	 * if (input.startsWith("C20-SelectEmpleado")) {
	    List<Empleado> lista = new ArrayList<>();
	    try {
		
		Class.forName(driver);
		
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Empleado");
			
		while(result.next()) {
		    int id = result.getInt("EmpleadoId");
		    String name = result.getString("Nombre");
		    String surname1 = result.getString("Apellido1");
		    String surname2 = result.getString("Apellido2");
		    Date date = result.getDate("FechaNacimiento");
		    String province = result.getString("Provincia");
		    String city = result.getString("Ciudad");
		    String address = result.getString("Direccion");
		    String phone = result.getString("Telefono");
		    String email = result.getString("Email");
		    String user = result.getString("Usuario");
		    String pass = result.getString("Contrasenya");
		    EstadoEmpleado employeeState = EstadoEmpleado.valueOf(result.getString("Estado"));
		    int role = result.getInt("Rol");
		    
		    lista.add(new Empleado(id, name, surname1, surname2, date, new Direccion(province, city, address), phone, email, user, pass, employeeState, new Rol()));
		    
		}
			
		result.close();
		statement.close();
		connection.close();
			
	    }catch(ClassNotFoundException cnfe){  
		System.out.printf("Not found the jdbc driver %s\n", driver);
	    }catch (SQLException sqle){
		System.out.println("SQL Exception");
	    }
	    Gson gson = new Gson();
	    String json = gson.toJson(lista);
	    output = json;
	    
	    
	    Type EmpleadoListType = new TypeToken<ArrayList<Empleado>>(){}.getType();  
	List<Empleado> lista = gson.fromJson(json, EmpleadoListType);
	
	for (Empleado e : lista) {
	    System.out.println(e.getRol().getNombre() + " " + e.getNombre());
	}
	}
	 * */
	
	/*String input = "C000-Admin-root";
	String mensaje = input.substring(5);
	String[] mensajes = mensaje.split("-");
	System.out.println(mensajes[0] + " " + mensajes[1]);*/
	
	int i = 0;
	CategoriaProducto c = null;
	String s = null;
	
	CategoriaProducto cp = new CategoriaProducto(i,c,s);
    }

}
