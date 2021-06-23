package mercado.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import mercado.framework.Menu;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Menu m = new Menu("../utilizacion/config");
		m.elegirOpcion();
	}

}
