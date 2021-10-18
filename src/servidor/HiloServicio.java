package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServicio extends Thread{

    private Socket socket = null;

    public HiloServicio(Socket socket) {
    	super("HiloServicio");
    	this.socket = socket;
    }
    
    public void run() {
    
    		try {
    		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    		    BufferedReader in = new BufferedReader(
    					    new InputStreamReader(
    					    socket.getInputStream()));
    	
    		    String inputLine, outputLine;
    	
    		    while ((inputLine = in.readLine()) != null) {
    		    	
    		    	outputLine = "Conectado";
    		    	out.println(outputLine);
    		    	
    		    	if (outputLine.equals("P0-Exit")) {
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
