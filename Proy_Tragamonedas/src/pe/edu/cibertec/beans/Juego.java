package pe.edu.cibertec.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "game")
@SessionScoped
public class Juego {
	private int numero1 = aleatorio(1, 3);
	private int numero2 = aleatorio(1, 3);
	private int numero3 = aleatorio(1, 3);

	private int intentos = 10;

	public int getNumero1() {
		return numero1;
	}

	public void setNumero1(int numero1) {
		this.numero1 = numero1;
	}

	public int getNumero2() {
		return numero2;
	}

	public void setNumero2(int numero2) {
		this.numero2 = numero2;
	}

	public int getNumero3() {
		return numero3;
	}

	public void setNumero3(int numero3) {
		this.numero3 = numero3;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public String aJugar() {
		String resultado = "";
		if (numero1 == numero2 && numero2 == numero3) {
			intentos = 10;
			reiniciarNumeros();
			resultado = "resultado";
		} else {
			reiniciarNumeros();
			intentos = intentos - 1;
			resultado = "game";
			if(intentos == 0){
				resultado = "noIntentos";
			}
		}
		return resultado;
	}
	
	public void reiniciarNumeros(){
		setNumero1(aleatorio(1, 3));
		setNumero2(aleatorio(1, 3));
		setNumero3(aleatorio(1, 3));
	}

	public String regresar() {
		return "game";
	}

	public static int aleatorio(int min, int max) {
		return (int) ((max - min + 1) * Math.random()) + min;
	}

}
