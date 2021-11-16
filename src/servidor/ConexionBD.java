package servidor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.*;

public class ConexionBD {

    private static String url="jdbc:mysql://localhost:3306/mercaSystem";
    private static String user="root";
    private static String password="";
    private static String driver= "com.mysql.jdbc.Driver";
    
    // Login 
    
    public static Empleado loginEmpleado(String usuario, String contrasenya) {
	Empleado res = null;
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement("SELECT * FROM Empleado JOIN Rol ON (Empleado.Rol = Rol.RolId) WHERE Usuario = ? AND Contrasenya = ?");
	    statement.setString(1, usuario);
	    statement.setString(2, contrasenya);
	    ResultSet result = statement.executeQuery();
			
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
		Rol rol = new Rol(result.getInt("RolId"), result.getString("NombreRol"));
		    
		res = new Empleado(id, name, surname1, surname2, date, new DireccionPostal(province, city, address), phone, email, user, pass, employeeState, rol);
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	return res;
    }

    public static Cliente loginCliente(String usuario, String contrasenya) {
	Cliente res = null;
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE Usuario = ? AND Contrasenya = ?");
	    statement.setString(1, usuario);
	    statement.setString(2, contrasenya);
	    ResultSet result = statement.executeQuery();
			
	    while(result.next()) {
		int id = result.getInt("ClienteId");
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
		
		res = new Cliente(id, name, surname1, surname2, date, new DireccionPostal(province, city, address), phone, email, user, pass);
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	return res;
    }
       
    // Altas y Updates
    
    public static boolean updateCliente(Cliente cliente, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	
	if (nuevo) {
	    consulta = "INSERT INTO Cliente VALUES (0,?,?,?,?,?,?,?,?,?,?,?)";
	} else {
	    consulta = "UPDATE Cliente SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, FechaNacimiento = ?, Provincia = ?, Ciudad = ?, Direccion = ?, Telefono = ?, Email = ?, Usuario = ?, Contrasenya = ? WHERE ClienteId = ?";
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, cliente.getNombre());
	    statement.setString(2, cliente.getApellido1());
	    statement.setString(3, cliente.getApellido2());
	    statement.setDate(4, cliente.getFechaNacimiento());
	    statement.setString(5, cliente.getDireccionPostal().getProvincia());
	    statement.setString(6, cliente.getDireccionPostal().getCiudad());
	    statement.setString(7, cliente.getDireccionPostal().getDireccion());
	    statement.setString(8, cliente.getTelefono());
	    statement.setString(9, cliente.getEmail());
	    statement.setString(10, cliente.getNombreUsuario());
	    statement.setString(11, cliente.getContrasenya());
	    if (!nuevo) {
		statement.setInt(12, cliente.getIdCliente());
	    }
	    int result = statement.executeUpdate();
	    
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }
    
    public static boolean updateEmpleado(Empleado empleado, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	
	if (nuevo) {
	    consulta = "INSERT INTO Empleado VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	} else {
	    consulta = "UPDATE Empleado SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, FechaNacimiento = ?, Provincia = ?, Ciudad = ?, Direccion = ?, Telefono = ?, Email = ?, Usuario = ?, Contrasenya = ?, Estado = ?, Rol = ? WHERE EmpleadoId = ?";
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, empleado.getNombre());
	    statement.setString(2, empleado.getApellido1());
	    statement.setString(3, empleado.getApellido2());
	    statement.setDate(4, empleado.getFechaNacimiento());
	    statement.setString(5, empleado.getDireccionPostal().getProvincia());
	    statement.setString(6, empleado.getDireccionPostal().getCiudad());
	    statement.setString(7, empleado.getDireccionPostal().getDireccion());
	    statement.setString(8, empleado.getTelefono());
	    statement.setString(9, empleado.getEmail());
	    statement.setString(10, empleado.getNombreUsuario());
	    statement.setString(11, empleado.getContrasenya());
	    statement.setString(12, empleado.getEstadoEmpleado().toString());
	    statement.setInt(13, empleado.getRol().getRolId());
	    if (!nuevo) {
		statement.setInt(14, empleado.getIdEmpleado());
	    }
	    
	    int result = statement.executeUpdate();
	    
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }

    public static boolean updateProveedor(Proveedor proveedor, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	
	if (nuevo) {
	    consulta = "INSERT INTO Proveedor VALUES (0,?,?,?,?,?,?)";
	} else {
	    consulta = "UPDATE Proveedor SET Nombre = ?, Email = ?, Telefono = ?, Provincia = ?, Ciudad = ?, Direccion = ? WHERE ProveedorId = ?";
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, proveedor.getNombreProveedor());
	    statement.setString(2, proveedor.getEmail());
	    statement.setString(3, proveedor.getTelefono());
	    statement.setString(4, proveedor.getDireccionPostal().getProvincia());
	    statement.setString(5, proveedor.getDireccionPostal().getCiudad());
	    statement.setString(6, proveedor.getDireccionPostal().getDireccion());
	    if (!nuevo) {
		statement.setInt(7, proveedor.getIdProveedor());
	    }
	    int result = statement.executeUpdate();
	    
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }

    public static boolean updateProducto(Producto producto, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	
	if (nuevo) {
	    consulta = "INSERT INTO Producto VALUES (0,?,?,?)";
	} else {
	    consulta = "UPDATE Producto SET CategoriaId = ?, ProveedorId = ?, Nombre = ? WHERE ProductoId = ?";
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setInt(1, producto.getCategoria().getIdCategoria());
	    statement.setInt(2, producto.getProveedor().getIdProveedor());
	    statement.setString(3, producto.getNombreProducto());
	    if (!nuevo) {
		statement.setInt(7, producto.getIdProducto());
	    }
	    int result = statement.executeUpdate();
	    
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }
    
    public static boolean updateConsumible(Consumible consumible, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	
	if (nuevo) {
	    consulta = "INSERT INTO Consumible VALUES (0,?,?,?,?,?,?,?,?)";
	} else {
	    consulta = "UPDATE Consumible SET ProductoId = ?, Peso = ?, Recipiente = ?, UnidadMedidaId = ?, Tamanyo = ?, PrecioCompra = ?, PrecioVenta = ?, Descatalogado = ? WHERE ConsumibleId = ?";
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setInt(1, consumible.getProducto().getIdProducto());
	    statement.setDouble(2, consumible.getPeso());
	    statement.setString(3, consumible.getRecipiente());
	    statement.setInt(4, consumible.getUnidadMedida().getIdUnidadMedida());
	    statement.setDouble(5, consumible.getTamanyo());
	    statement.setDouble(6, consumible.getPrecioCompra());
	    statement.setDouble(7, consumible.getPrecioVenta());
	    statement.setBoolean(8, consumible.isDescatalogado());
	    if (!nuevo) {
		statement.setInt(14, consumible.getIdConsumible());
	    }
	    int result = statement.executeUpdate();
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }
 
    public static boolean updateCategoria(CategoriaProducto categoria, boolean nuevo) {
	boolean res = false;
	String consulta = "";
	boolean esPadre = categoria.getCategoriaPadre() == null || categoria.getCategoriaPadre().getIdCategoria() == 0;
	
	if (esPadre) {
	    if (nuevo) {
		consulta = "INSERT INTO CategoriaProducto VALUES (0,null,?)";
	    } else {
		consulta = "UPDATE CategoriaProducto SET NombreCategoria = ? WHERE CategoriaId = ?";
	    }
	} else {
	    if (nuevo) {
		consulta = "INSERT INTO CategoriaProducto VALUES (0,?,?)";
	    } else {
		consulta = "UPDATE CategoriaProducto SET CategoriaPadreId = ?, NombreCategoria = ? WHERE CategoriaId = ?";
	    }
	}
	
	try {
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    
	    if (esPadre) {
		statement.setString(1, categoria.getNombreCategoria());
		if (!nuevo) {
		    statement.setInt(2, categoria.getIdCategoria());
		}
	    } else {
		statement.setInt(1, categoria.getCategoriaPadre().getIdCategoria());
		statement.setString(2, categoria.getNombreCategoria());
		if (!nuevo) {
		    statement.setInt(3, categoria.getIdCategoria());
		}
	    }
	    int result = statement.executeUpdate();
	    
	    if (result > 0) {
		res = true;
	    }
	    
	    statement.close();
	    connection.close();
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return res;
    }
    
    // Selects
    
    public static List<Empleado> selectEmpleados(String filtro){
	List<Empleado> lista = new ArrayList<>();
	String[] cadena = filtro.split(";");
	
	String nombre = cadena[0];
	String rol = cadena[1];
	String estado = cadena[2];
	
	String consulta = "SELECT * FROM Empleado JOIN Rol ON (Empleado.Rol = Rol.RolId) WHERE CONCAT(Nombre, ' ', Apellido1, ' ', Apellido2) LIKE ? AND Rol LIKE ? AND Estado LIKE ?" ;
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, "%" + nombre + "%");
	    statement.setString(2, rol);
	    statement.setString(3, estado);
	    ResultSet result = statement.executeQuery();
			
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
		Rol role = new Rol(result.getInt("RolId"), result.getString("NombreRol"));
		    
		lista.add(new Empleado(id, name, surname1, surname2, date, new DireccionPostal(province, city, address), phone, email, user, pass, employeeState, role));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return lista;
    }

    public static List<Rol> selectRoles() {
	List<Rol> lista = new ArrayList<>();
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    Statement statement = connection.createStatement();
	    ResultSet result = statement.executeQuery("SELECT * FROM Rol");
			
	    while(result.next()) {
		int id = result.getInt("RolId");
		String name = result.getString("NombreRol");
		
		lista.add(new Rol(id, name));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}	
	
	return lista;
    }
 
    public static List<CategoriaProducto> selectCategorias() {
	List<CategoriaProducto> lista = new ArrayList<>();
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    Statement statement = connection.createStatement();
	    ResultSet result = statement.executeQuery("SELECT c.CategoriaId as 'Id', cp.CategoriaId as 'PadreId', cp.NombreCategoria as 'PadreNombre', c.NombreCategoria as 'Nombre' FROM CategoriaProducto c LEFT JOIN CategoriaProducto cp ON (c.CategoriaPadreId = cp.CategoriaId)");
			
	    while(result.next()) {
		int id = result.getInt("Id");
		String name = result.getString("Nombre");
		int fatherId = result.getInt("PadreId");
		String fatherName = result.getString("PadreNombre");
		
		lista.add(new CategoriaProducto(id, new CategoriaProducto(fatherId, null, fatherName), name));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return lista;
    }
    
    public static List<Producto> selectProductos(String filtro) {
	List<Producto> lista = new ArrayList<>();
	String[] cadena = filtro.split(";");
	
	String nombre = cadena[0];
	String categoria = cadena[1];
	String proveedor = cadena[2];
	
	String consulta = "SELECT P.ProductoId , P.Nombre as Nombre, PR.ProveedorId, PR.Nombre as NombreProveedor, Email, Telefono, Provincia, Ciudad, Direccion, CP.CategoriaId,  CP.NombreCategoria, CPP.CategoriaId as IdPadre, CPP.NombreCategoria as NombrePadre "
		+ "FROM Producto P "
		+ "JOIN Proveedor PR ON (P.ProveedorId = PR.ProveedorId) "
		+ "JOIN CategoriaProducto CP ON (P.CategoriaId = CP.CategoriaId) "
		+ "LEFT JOIN categoriaproducto CPP ON (CP.CategoriaPadreId = CPP.CategoriaId) "
		+ "WHERE P.Nombre LIKE ? AND P.CategoriaId LIKE ? AND P.ProveedorId LIKE ?";
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, "%" + nombre + "%");
	    statement.setString(2, categoria);
	    statement.setString(3, proveedor);
	    ResultSet result = statement.executeQuery();
			
	    while(result.next()) {
		int id = result.getInt("ProductoId");
		String name = result.getString("Nombre");
		
		int supplierId = result.getInt("ProveedorId");
		String supplierName = result.getString("NombreProveedor");
		String phone = result.getString("Telefono");
		String email = result.getString("Email");
		String province = result.getString("Provincia");
		String city = result.getString("Ciudad");
		String address = result.getString("Direccion");
		
		int categoryId = result.getInt("CategoriaId");
		String categoryName = result.getString("NombreCategoria");
		
		int fatherId = result.getInt("IdPadre");
		String fatherName = result.getString("NombrePadre");
		
		CategoriaProducto father = new CategoriaProducto(fatherId, null, fatherName);
		CategoriaProducto category = new CategoriaProducto(categoryId, father, categoryName);
		Proveedor supplier = new Proveedor(supplierId, supplierName, phone, email, new DireccionPostal(province, city, address));
		
		lista.add(new Producto(id,category, supplier, name));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return lista;
    }
     
    public static List<Proveedor> selectProveedores(String filtro) {
	List<Proveedor> lista = new ArrayList<>();
	String[] cadena = filtro.split(";");
	
	String nombre = cadena[0];
	String categoria = cadena[1];
	String producto = cadena[2];
	
	String consulta = "SELECT PR.ProveedorId AS ProveedorId, PR.Nombre AS Nombre, Email, Telefono, Provincia, Ciudad, Direccion "
		+ "FROM Proveedor PR "
		+ "JOIN Producto P ON (P.ProveedorId = PR.ProveedorId) "
		+ "JOIN CategoriaProducto CP ON (P.CategoriaId = CP.CategoriaId) "
		+ "WHERE PR.Nombre LIKE ? AND P.CategoriaId LIKE ? AND P.ProductoId LIKE ?";
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, "%" + nombre + "%");
	    statement.setString(2, categoria);
	    statement.setString(3, producto);
	    ResultSet result = statement.executeQuery();
			
	    while(result.next()) {
		int supplierId = result.getInt("ProveedorId");
		String supplierName = result.getString("Nombre");
		String phone = result.getString("Telefono");
		String email = result.getString("Email");
		String province = result.getString("Provincia");
		String city = result.getString("Ciudad");
		String address = result.getString("Direccion");
		
		lista.add(new Proveedor(supplierId, supplierName, phone, email, new DireccionPostal(province, city, address)));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	return lista;
    }
    
    public static List<Consumible> selectConsumibles(String filtro) {
	List<Consumible> lista = new ArrayList<>();
	String[] cadena = filtro.split(";");
	
	String producto = cadena[0];
	String descatalogado = cadena[1];
	
	String consulta = "SELECT * "
		+ "FROM Consumible C "
		+ "JOIN Producto P ON (C.ProductoId = P.ProductoId) "
		+ "JOIN UnidadMedida UM ON (C.UnidadMedidaId = UM.UnidadMedidaId) "
		+ "WHERE P.ProductoId LIKE ? "
		+ "AND C.Descatalogado LIKE ?";
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, producto);
	    statement.setString(2, descatalogado);
	    ResultSet result = statement.executeQuery();
			
	    while(result.next()) {
		int productId = result.getInt("ProductoId");
		String productName = result.getString("Nombre");
		
		int unitId = result.getInt("UnidadMedidaId");
		String unitName = result.getString("Unidad");
		TipoMedida unitType = TipoMedida.valueOf(result.getString("TipoMedida"));
		
		int id = result.getInt("ProductoId");
		double weight = result.getDouble("Peso");
		String recipient = result.getString("Recipiente");
		double size = result.getDouble("Tamanyo");
		double buyPrice = result.getDouble("PrecioCompra");
		double sellPrice = result.getDouble("PrecioVenta");
		boolean discontinued = result.getBoolean("Descatalogado");
		
		Producto p = new Producto(productId, null, null, productName);
		UnidadMedida um = new UnidadMedida(unitId, unitName, unitType);
		
		lista.add(new Consumible(id, p, weight, recipient, um, size, buyPrice, sellPrice, discontinued));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	
	return lista;
    }
    
    public static List<Pedido> selectPedidos(String filtro) {
	List<Pedido> lista = new ArrayList<>();
	String[] cadena = filtro.split(";");
	
	String proveedor = cadena[0];
	String producto = cadena[1];
	String consumible = cadena[2];
	double minTotal = Double.parseDouble(cadena[3]);
	double maxTotal = Double.parseDouble(cadena[4]);
	Date fechaMin = Date.valueOf(cadena[5]);
	Date fechaMax = Date.valueOf(cadena[6]);
	
	String consulta = "SELECT P.PedidoId, PR.ProveedorId, PR.Nombre, P.Estado, P.FechaPedido, P.FechaEntrega, P.PrecioTotal "
		+ "FROM Pedido P "
		+ "JOIN Proveedor PR ON (P.ProveedorId = PR.ProveedorId) "
		+ "WHERE P.PedidoId IN ( "
		+ "    SELECT L.PedidoId "
		+ "    FROM LineaPedido L "
		+ "    JOIN Consumible C ON (L.ConsumibleId = C.ConsumibleId) "
		+ "    JOIN Producto LP ON (C.ProductoId = LP.ProductoId) "
		+ "    WHERE C.ConsumibleId LIKE ? "
		+ "    AND LP.ProductoId LIKE ?) "
		+ "AND PR.ProveedorId LIKE ? "
		+ "AND (PrecioTotal BETWEEN ? AND ?) "
		+ "AND (FechaPedido BETWEEN ? AND ?)";
	
	try {
		
	    Class.forName(driver);
		
	    Connection connection = DriverManager.getConnection(url,user,password);
	    PreparedStatement statement = connection.prepareStatement(consulta);
	    statement.setString(1, consumible);
	    statement.setString(2, producto);
	    statement.setString(3, proveedor);
	    statement.setDouble(4, minTotal);
	    statement.setDouble(5, maxTotal);
	    statement.setDate(6, fechaMin);
	    statement.setDate(7, fechaMax);
	    ResultSet result = statement.executeQuery();
			
	    while(result.next()) {
		int supplierId = result.getInt("ProveedorId");
		String supplierName = result.getString("Nombre");
		
		int id = result.getInt("PedidoId");
		EstadoPedido state = EstadoPedido.valueOf(result.getString("Estado"));
		Date requestDate = result.getDate("FechaPedido");
		Date deliveredDate = result.getDate("FechaEntrega");
		
		List<Linea> l = new ArrayList<Linea>();
		Proveedor p = new Proveedor(supplierId, supplierName, null, null, null);
		lista.add(new Pedido(id, state, p, l, requestDate, deliveredDate));
	    }
			
	    result.close();
	    statement.close();
	    connection.close();
			
	}catch(ClassNotFoundException cnfe){  
	    System.out.printf("Not found the jdbc driver %s\n", driver);
	}catch (SQLException sqle){
	    System.out.println("SQL Exception");
	}
	
	
	return lista;
    }

    
    
}
