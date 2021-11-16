package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

import modelos.Empleado;

public class Servidor {
    
    static class Server_Data{
	
	List<String> empleadosLogueados = new ArrayList<>();
	List<String> clientesLogueados = new ArrayList<>();
	TreeMap<Integer, Thread> hilosReponedores = new TreeMap<>();
	Integer reponedorActual;
	
	public synchronized boolean checkEmpleado(String usuario) {
	    if (empleadosLogueados.contains(usuario)) {
		return false;
	    } else {
		return true;
	    }
	}
	
	public synchronized boolean addEmpleado(Empleado empleado, Thread hilo) {
	    if (checkEmpleado(empleado.getNombreUsuario())) {
		empleadosLogueados.add(empleado.getNombreUsuario());
		if (empleado.getRol().getNombre().equals("Reponedor")) {
		    hilosReponedores.put(empleado.getIdEmpleado(), hilo);
		    if (reponedorActual == null) {
			reponedorActual = empleado.getIdEmpleado();
		    }
		}
		return true;
	    } else {
		return false;
	    }
	}
	
	public synchronized void logOutEmpleado(Empleado empleado) {
	    if (hilosReponedores.containsKey(empleado.getIdEmpleado())) {
		hilosReponedores.remove(empleado.getIdEmpleado());
		if (reponedorActual == empleado.getIdEmpleado()) {
		    if (hilosReponedores.keySet().size() > 0) {
			setNextReponedor();
		    } else {
			reponedorActual = null;
		    }
		}
	    }
	    empleadosLogueados.remove(empleado.getNombreUsuario());
	}
	
	public synchronized boolean checkCliente(String usuario) {
	    if (clientesLogueados.contains(usuario)) {
		return false;
	    } else {
		return true;
	    }
	}
	
	public synchronized boolean addCliente(String usuario) {
	    if (checkEmpleado(usuario)) {
		clientesLogueados.add(usuario);
		return true;
	    } else {
		return false;
	    }
	}
	
	public synchronized void logOutCliente(String usuario) {
	    clientesLogueados.remove(usuario);
	}
	
	public synchronized void setNextReponedor() {
	    for (Integer i : hilosReponedores.keySet()) {
		if (i.compareTo(reponedorActual) > 0) {
		    reponedorActual = i;
		    break;
		} else if (i.equals(hilosReponedores.lastKey())) {
		    reponedorActual = hilosReponedores.firstKey();
		}
	    }
	}
    
	
    }
    

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }
        
        Server_Data data = new Server_Data();
        
        while (listening) {
        	new HiloServicio(serverSocket.accept(), data).start();
        }
	    
        serverSocket.close();
        
    }
}
