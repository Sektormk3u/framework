package mercado.utilizacion;

import java.io.File;

import mercado.framework.Accion;

public class AccionDos implements Accion{

	@Override
	public void ejectuar() {
		try {
			File file = new File("C:\\Users\\Public\\Documents\\prueba.txt");
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			System.out.println("Exito");
		} catch (Exception e) {
			System.out.println("Algo salio mal...");
		}
		
	}

	@Override
	public String nombreItemMenu() {
		// TODO Auto-generated method stub
		return "AccionDos";
	}

	@Override
	public String descripcionItemMenu() {
		// TODO Auto-generated method stub
		return "Genera un archivo vacio de texto en C:\\Users\\Public\\Documents con el nombre de 'prueba' si no existe";
	}

}
