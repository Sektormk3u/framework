package mercado.utilizacion;

import mercado.framework.Accion;

public class AccionTres implements Accion{

	@Override
	public String descripcionItemMenu() {
		// TODO Auto-generated method stub
		return "Accion 3";
	}

	@Override
	public void ejectuar() {
		System.out.println("lo ejecute");
		
	}

	@Override
	public String nombreItemMenu() {
		// TODO Auto-generated method stub
		return "AccionTres";
	}

}
