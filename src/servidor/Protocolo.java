package servidor;

import java.util.List;

import com.google.gson.Gson;

import modelos.CategoriaProducto;
import modelos.Cliente;
import modelos.Consumible;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;
import modelos.Proveedor;
import modelos.Rol;
import servidor.Servidor.Server_Data;

public class Protocolo {
    
    private static final int DEFAULT = 0;
    private static final int LOGUEADO = 1;
    private static final int COMPRANDO = 2;
    
    private Server_Data datos;
    
    public int estado = DEFAULT;

    public Protocolo(Server_Data datos) {
	this.datos = datos;
    }
    
    public String processInput(String input) {
	String output = "";
	if (estado == DEFAULT) {
	    if (input.equals("EXIT")) {
		output = input;
	    } else if (input.startsWith("C000-")) {
		String mensaje = input.substring(5);
		String usuario = mensaje.split("-")[0];
		String contrasenya = mensaje.split("-")[1];
		
		if (datos.checkEmpleado(usuario)) {
		    
		    Empleado empleado = ConexionBD.loginEmpleado(usuario, contrasenya);
		    
		    if (empleado != null) {
			if (datos.addEmpleado(empleado, Thread.currentThread())) {
			    Gson gson = new Gson();
			    String json = gson.toJson(empleado);
			    estado = LOGUEADO;
			    output = "S000-" + json;
			} else {
			    output = "S001";
			}
		    } else {
			output = "S002";
		    }
		    
		} else {
		    output = "S001";
		}
		
	    } else if (input.startsWith("C001-")) {
		String mensaje = input.substring(5);
		String usuario = mensaje.split("-")[0];
		String contrasenya = mensaje.split("-")[1];
		
		if (datos.checkCliente(usuario)) {
		    
		    Cliente cliente = ConexionBD.loginCliente(usuario, contrasenya);
		    
		    if (cliente != null) {
			if (datos.addCliente(usuario)) {
			    Gson gson = new Gson();
			    String json = gson.toJson(cliente);
			    estado = LOGUEADO;
			    output = "S003-" + json;
			} else {
			    output = "S004";
			}
		    } else {
			output = "S005";
		    }
		    
		} else {
		    output = "S004";
		}
	    } else if (input.startsWith("C002-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(mensaje, Cliente.class);
		
		if (ConexionBD.updateCliente(cliente, true)) {		    
		    output = "S006";
		} else {		    
		    output = "S007";
		}		
	    }
	} else if(estado == LOGUEADO) {
	    if (input.startsWith("C003-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Empleado empleado = gson.fromJson(mensaje, Empleado.class);
		
		datos.logOutEmpleado(empleado);
		estado = DEFAULT;
		
		output = "S008";
	    } else if (input.startsWith("C004-")) {
		String mensaje = input.substring(5);
		
		datos.logOutCliente(mensaje);
		estado = DEFAULT;
		
		output = "S009";
	    } else if (input.startsWith("C005-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Cliente cliente = gson.fromJson(mensaje, Cliente.class);
		
		if (ConexionBD.updateCliente(cliente, false)) {		    
		    output = "S010";
		} else {		    
		    output = "S011";
		}
		
	    } else if (input.startsWith("C006-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Empleado empleado = gson.fromJson(mensaje, Empleado.class);
		
		if(ConexionBD.updateEmpleado(empleado, true)) {
		    output = "S012";
		} else {
		    output = "S013";
		}
	    } else if (input.startsWith("C007-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Empleado empleado = gson.fromJson(mensaje, Empleado.class);
		boolean correcto = ConexionBD.updateEmpleado(empleado, false);
		
		if(correcto) {
		    output = "S014";
		} else {
		    output = "S015";
		}
	    } else if (input.startsWith("C008-")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		List<Empleado> lista = ConexionBD.selectEmpleados(mensaje);	
		output = "S016-" + gson.toJson(lista);
	    } else if (input.startsWith("C009")) {
		List<Rol> lista = ConexionBD.selectRoles();
		Gson gson = new Gson();
		output = "S017-" +  gson.toJson(lista);
	    } else if (input.startsWith("C010")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Proveedor proveedor = gson.fromJson(mensaje, Proveedor.class);
		
		if(ConexionBD.updateProveedor(proveedor, true)) {
		    output = "S018";
		} else {
		    output = "S019";
		}
	    } else if (input.startsWith("C011")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Proveedor proveedor = gson.fromJson(mensaje, Proveedor.class);
		
		if(ConexionBD.updateProveedor(proveedor, false)) {
		    output = "S020";
		} else {
		    output = "S021";
		}
	    } else if (input.startsWith("C012")) {
		Gson gson = new Gson();
		List<CategoriaProducto> lista = ConexionBD.selectCategorias();	
		output = "S022-" + gson.toJson(lista);
	    } else if (input.startsWith("C013")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		List<Producto> lista = ConexionBD.selectProductos(mensaje);	
		output = "S023-" + gson.toJson(lista);
	    } else if (input.startsWith("C014")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Producto producto = gson.fromJson(mensaje, Producto.class);
		
		if(ConexionBD.updateProducto(producto, true)) {
		    output = "S024";
		} else {
		    output = "S025";
		}
	    } else if (input.startsWith("C015")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Producto producto = gson.fromJson(mensaje, Producto.class);
		
		if(ConexionBD.updateProducto(producto, true)) {
		    output = "S026";
		} else {
		    output = "S027";
		}
	    } else if (input.startsWith("C016")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		List<Proveedor> lista = ConexionBD.selectProveedores(mensaje);	
		output = "S028-" + gson.toJson(lista);
	    } else if (input.startsWith("C017")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		CategoriaProducto categoria = gson.fromJson(mensaje, CategoriaProducto.class);
		
		if(ConexionBD.updateCategoria(categoria, true)) {
		    output = "S029";
		} else {
		    output = "S030";
		}
	    } else if (input.startsWith("C018")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		CategoriaProducto categoria = gson.fromJson(mensaje, CategoriaProducto.class);
		
		if(ConexionBD.updateCategoria(categoria, false)) {
		    output = "S031";
		} else {
		    output = "S032";
		}
	    } else if (input.startsWith("C019")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Consumible consumible = gson.fromJson(mensaje, Consumible.class);
		
		if(ConexionBD.updateConsumible(consumible, true)) {
		    output = "S033";
		} else {
		    output = "S034";
		}
	    } else if (input.startsWith("C020")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		Consumible consumible = gson.fromJson(mensaje, Consumible.class);
		
		if(ConexionBD.updateConsumible(consumible, false)) {
		    output = "S035";
		} else {
		    output = "S036";
		}
	    } else if (input.startsWith("C021")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		List<Consumible> lista = ConexionBD.selectConsumibles(mensaje);	
		output = "S037-" + gson.toJson(lista);
	    } else if (input.startsWith("C022")) {
		String mensaje = input.substring(5);
		Gson gson = new Gson();
		List<Pedido> lista = ConexionBD.selectPedidos(mensaje);	
		output = "S038-" + gson.toJson(lista);
	    }
	} else if (estado == COMPRANDO) {
	    
	}
	
	return output;
    }

}
