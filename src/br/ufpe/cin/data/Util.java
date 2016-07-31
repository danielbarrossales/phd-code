package br.ufpe.cin.data;

public class Util {
	
	public static void imprimir(String texto) {
		System.out.println(texto);
	}

	public static void imprimir(int valor) {
		System.out.println(String.valueOf(valor));
	}

	public static void imprimirLinhas(String[] valores, int inicio, int quantos) {
		for (int i = 0; i < quantos; i++) {
			System.out.println(valores[inicio + i]);
		}
	}
	
	public static int[] converterArrayString(String[] vetor) {
		int[] saida = new int[vetor.length - 1];
		for (int i = 0; i < saida.length; i++) {
			saida[i] = Integer.parseInt(vetor[i + 1]);
		}

		return saida;
	}

}
