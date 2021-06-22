package mercado.framework;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;

public class Menu {

	private ArrayList<Accion> arrayMenu;
	private int maxOpciones;
	
	public Menu(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.arrayMenu = new ArrayList<Accion>();
		Properties p = new Properties();
		InputStream is = getClass().getResourceAsStream(path);
		try {
			p.load(is);
			String s = p.getProperty("acciones");
			String[] arrayString = s.split(";");
			System.out.println("Seleccione opcion");
			this.maxOpciones = arrayString.length;
			for (int j = 0; j < arrayString.length; j++) {
				Class a = Class.forName(arrayString[j].trim());
				Accion accion = (Accion) a.getDeclaredConstructor().newInstance();
				arrayMenu.add(accion);
				System.out.println((j+1)+" "+accion.descripcionItemMenu());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void elegirOpcion () {
		System.out.println("Seleccione opcion, si quiere salir escriba 0");
		Scanner leer = new Scanner (System.in);
		int num = leer.nextInt();
		while (num != 0) {
			if (num <= arrayMenu.size()) {
				arrayMenu.get(num-1).ejectuar();;
			}
			System.out.println("Seleccione opcion, si quiere salir escribe 0");
			num = leer.nextInt();
		}
		
	}
}
