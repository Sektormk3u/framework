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

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

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
			System.out.println("Seleccione opcion, si quiere salir escribe 0");
			this.maxOpciones = arrayString.length;
			for (int j = 0; j < arrayString.length; j++) {
				Class a = Class.forName(arrayString[j].trim());
				Accion accion = (Accion) a.getDeclaredConstructor().newInstance();
				arrayMenu.add(accion);
				System.out.println((j+1)+" "+accion.descripcionItemMenu());
			}
			crearMenu();
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
		
		private void crearMenu() {
			// Setup terminal and screen layers
			DefaultTerminalFactory defaultTerminal;
			Terminal terminal;
			try {
				defaultTerminal = new DefaultTerminalFactory();
				defaultTerminal
						.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
				terminal = defaultTerminal.createTerminal();

				Screen screen = new TerminalScreen(terminal);
				screen.startScreen();

				MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
						new EmptySpace(TextColor.ANSI.BLUE));

				// Create panel to hold components
				Panel panel = new Panel();
				panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

				for (Accion accion : arrayMenu) {
					Button botonAccion = new Button(accion.nombreItemMenu(), new Runnable() {
						@Override
						public void run() {
							accion.ejectuar();
							MessageDialog.showMessageDialog(gui, "Ejecutando Accion", accion.descripcionItemMenu());
						}
					});
					panel.addComponent(botonAccion);
				}

				Button botonSalir = new Button("Salir", new Runnable() {
					@Override
					public void run() {
						try {
							terminal.exitPrivateMode();
						} catch (IOException e) {
							MessageDialog.showMessageDialog(gui, "Error", e.getMessage());
						}
					}
				});
				panel.addComponent(botonSalir);

				// Create window to hold the panel
				BasicWindow window = new BasicWindow("Menu con Lanterna");
				window.setComponent(panel);

				// Start gui
				gui.addWindowAndWait(window);
			} catch (IOException e) {
				throw new RuntimeException("Error al crear la gui", e);
			}
	}
}
