package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelos.DireccionPostal;
import modelos.Empleado;
import modelos.EstadoEmpleado;
import modelos.Rol;
import servidor.Servidor.Server_Data;

public class HiloServicio extends Thread{

    private Socket socket = null;
    private Server_Data datos;

    public HiloServicio(Socket socket, Server_Data datos) {
    	super("HiloServicio");
    	this.socket = socket;
    	this.datos = datos;
    }
    
    public void run() {
	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
		    new InputStreamReader(
			    	socket.getInputStream()));
	
	    String inputLine, outputLine;
	    Protocolo protocolo = new Protocolo(datos);
	
	    while ((inputLine = in.readLine()) != null) {
		
		outputLine = protocolo.processInput(inputLine);
	    	out.println(outputLine);
	    	
	    	if (outputLine.equals("EXIT")) {
	    		return;
	    	} 
	    	
	    }
	    out.close();
	    in.close();
	    socket.close();
	
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
