package br.ufpe.cin.data;

public class Util {
	
	public static void print(String texto) {
		System.out.println(texto);
	}

	public static void print(int valor) {
		System.out.println(String.valueOf(valor));
	}

	public static void printLines(String[] valores, int inicio, int quantos) {
		for (int i = 0; i < quantos; i++) {
			System.out.println(valores[inicio + i]);
		}
	}
	
	public static int[] toIntArray(String[] array) {
		int[] output = new int[array.length - 1];
		for (int i = 0; i < output.length; i++) {
			output[i] = Integer.parseInt(array[i + 1]);
		}

		return output;
	}

}
