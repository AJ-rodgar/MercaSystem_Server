package TEST;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import modelos.CategoriaProducto;
import modelos.Empleado;
import modelos.Producto;
import modelos.Proveedor;
import modelos.Rol;

public class TEST_Cliente {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
	
	Socket socket = null;
	PrintWriter flujo_cliente = null;
	BufferedReader flujo_servidor = null;

	try {
	    socket = new Socket("localhost", 4444);
	    flujo_cliente = new PrintWriter(socket.getOutputStream(), true);
	    flujo_servidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (UnknownHostException e) {
	    System.err.println("Don't know about host: MercaSystem_Server.");
	    System.exit(1);
	} catch (IOException e) {
	    System.err.println("Couldn't get I/O for the connection to: MercaSystem_Server.");
	    System.exit(1);
	}
	
	Gson gson = new Gson();
	
	String contrasenya = Utiles.codePassword("admin");	
	flujo_cliente.println("C000-admin-" + contrasenya);
	String mensaje = flujo_servidor.readLine();
	System.out.println(mensaje);
	Empleado emp = gson.fromJson(mensaje.substring(5), Empleado.class);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	flujo_cliente.println("C016-%;%;%");
	mensaje = flujo_servidor.readLine();
	System.out.println(mensaje);
	java.lang.reflect.Type ProveedorListType = new TypeToken<ArrayList<Proveedor>>(){}.getType();  
	List<Proveedor> lista = gson.fromJson(mensaje.substring(5), ProveedorListType);
	System.out.println(lista);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	flujo_cliente.println("C003-" + gson.toJson(emp));
	System.out.println(flujo_servidor.readLine());
	flujo_cliente.println("EXIT");
	
	flujo_cliente.close();
        flujo_servidor.close();
        socket.close();
    }
}
