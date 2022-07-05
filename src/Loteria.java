import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Loteria extends Thread {
	
	private int[] valores;
	private static int apuesta;
	private static int num1;
	private static int num2;
	private static int num3;
	
	public Loteria() {
		valores = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 
							 8, 9, 10, 11, 12, 13, 14, 15,
							 16, 17, 18, 19, 20, 21, 22, 23, 
							 24, 25, 26, 27, 28, 29, 30, 31,
							 32, 33, 34, 35, 36, 37, 38, 39,
							 40, 41, 42, 43, 44, 45, 46, 47,
							 48, 49, 50, 51, 52, 53, 54, 55,
							 56, 57, 58, 59, 60};
	}
	
	
	public void run() {
		int contador1 = 1, contador2 = 0;
		mezclar(valores);
		
		for(int i = 0; i <= 15; i++) {
			if(valores[i] == num1 || valores[i] == num2 || valores[i] == num3) {
				for(int j = 1; j <= 1; j++) {
					System.out.println(valores[i] + " ¡Acierto " + contador1++ + "!");
					contador2++;
				}
			}
			else {
				System.out.println(valores[i]);
			}
			
			try {
				sleep(700);
			} catch(InterruptedException e) {
			}
		}
		
		switch(contador2) {
			case 0:
				apuesta = 0;
				System.out.println("Ha acertado " + contador2 + " números. Ganancia: $" + apuesta);
				break;
			case 1:
				apuesta = apuesta + (apuesta / 2);
				System.out.println("Ha acertado " + contador2 + " número. Ganancia: $" + apuesta);
				break;
			case 2:
				apuesta = apuesta * 2;
				System.out.println("Ha acertado " + contador2 + " números. Ganancia: $" + apuesta);
				break;
			case 3:
				apuesta = apuesta * 3 ;
				System.out.println("Ha acertado " + contador2 + " números. Ganancia: $" + apuesta);
				break;
		}
		System.out.println("Presione ctrl + Z para salir (ctrl + C en Unix)");
	}
	
	
	private void mezclar(int[] arr) {
		int indice;
		int s;
		Random r = new Random();
		int i = arr.length - 1;
		
		while(i > 0) {
			indice = r.nextInt(i + 1);
			s = arr[indice];
			arr[indice] = arr[i];
			arr[i] = s;
			i--;
		}
	}
	
	
	public static void main(String[] args) throws ExcepcionNumeroNegativo {
		Loteria l = new Loteria();
		int scan = 0;
		
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Bienvenido - escriba la cantidad a apostar (num. entero) y luego ENTER - ctrl + Z para salir (ctrl + C en Unix)");
			try {
				scan = in.nextInt();
				apuesta = scan;
				while(scan >= 0) {
					System.out.println("Escriba el primer número (del 0 al 60) a apostar y luego ENTER");
					scan = in.nextInt();
					num1 = scan;
					System.out.println("Escriba el segundo número (del 0 al 60) a apostar y luego ENTER");
					scan = in.nextInt();
					num2 = scan;
					System.out.println("Escriba el tercer número (del 0 al 60) a apostar y luego ENTER");
					scan = in.nextInt();
					num3 = scan;
					
					System.out.println("Sorteando números...");
					l.start();
					scan = in.nextInt();
				}
			} catch(InputMismatchException e) {
				e.printStackTrace();
			} catch(NoSuchElementException ex) {
			}
			
			// Para evitar el ingreso de cifras negativas durante la etapa de apuesta:
			if(scan < 0) {
				throw new ExcepcionNumeroNegativo("Ingreso inválido - no apueste cantidades negativas");
			}
		}
	}

}
