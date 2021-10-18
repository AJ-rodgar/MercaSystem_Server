package TEST;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

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
	
	flujo_cliente.println("Hola");
	System.out.println(flujo_servidor.readLine());
    }
}
