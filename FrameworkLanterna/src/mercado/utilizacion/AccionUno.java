package mercado.utilizacion;

import java.sql.Connection;
import java.sql.DriverManager;

import mercado.framework.Accion;

public class AccionUno implements Accion{

	@Override
	public void ejectuar() {
		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = "root";
			Connection dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			System.out.println("No se pudo conectar con la BD");
		}
		
	}

	@Override
	public String nombreItemMenu() {
		// TODO Auto-generated method stub
		return "AccionUno";
	}

	@Override
	public String descripcionItemMenu() {
		// TODO Auto-generated method stub
		return "Comprueba la conexion con la base de datos con usuario 'root' y contraseña 'root' en proyecto 'test'";
	}

}
