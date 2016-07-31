package br.ufpe.cin.targetreach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.ufpe.cin.data.Util;

public class CalculateUsageTrack {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] linguagensStackOverflow = { "assembly", "c", "c++", "c#", "dart", "go", "java", "javascript", "julia",
				"objective-c", "php", "python", "r", "ruby", "rust", "shell", "swift" };
		String[] linguagensGitHub = { "Assembly", "C", "C++", "C#", "Dart", "Go", "Java", "JavaScript", "Julia",
				"Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		String[] linguagensPrettyPrinting = { "Assembly", "C", "C++", "C\\#", "Dart", "Go", "Java", "Javascript",
				"Julia", "Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		
		int[] kGitHub = {36743, 658454, 1093811, 1992480, 5659, 186634, 3704894, 3125306, 11780, 349495, 1174683, 2435384,
				140186, 690810, 10494, 508784, 96605};
		
		int[] kStackOverflow = {29561, 255544, 457044, 631818, 1836, 18714, 1220824, 1584183, 1643, 114080, 726038, 1739893, 
				472231, 139084, 1845, 68451, 59345};
		
		//String stackOverflow = buildFitParamsSection("StackOverflow", linguagensStackOverflow, linguagensPrettyPrinting,
			//"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/stackoverflow_4/individuos/");
		String diretorioStackOverflow = "C:\\Users\\emano\\Dropbox\\UFPE\\Doutorado\\Dados\\stackoverflow_4\\individuos\\semana\\";
		//String github = buildFitParamsSection("GitHub", linguagensGitHub, linguagensPrettyPrinting,
			//"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/github/ghtorrent/mathematica/programadores/");
		String diretorioGithub =  "C:\\Users\\emano\\Documents\\GitHub\\phd-code\\src\\br\\ufpe\\cin\\github\\individuos\\semana\\";
		
		String cabecalho = "\\begin{table}[h]\n"+
				"\\centering\n"+
				"\\caption{CAPTION.}\n"+
				"\\label{table:user_type_usage}\n"+
				"\\begin{tabular}{l | c | c | c | c}\n"+
				"\\hline\n"+
				"Language    & Innovators (2.5\\%) & Early Adopters (13.5\\%) & Early Majority (34\\%) & Late Majority (34\\%)  \\\\ \\hline\n";
		String rodape = "\\hline\n"
				+ "\\end{tabular}\n"
				+ "\\end{table}\n";
		

		Util.imprimir("Iniciando leitura do arquivo de fit params...");
		
		StringBuffer buf = new StringBuffer();
		buf.append(cabecalho);
		String linguagem = null;
		
		String diretorio = diretorioStackOverflow;
		String[] linguagens = linguagensStackOverflow;
		int[] k = kStackOverflow;
		
		for(int i = 0; i < linguagens.length; i++) {
			linguagem = linguagens[i];
			
			double innovators = k[i] * 0.025;
			double earlyAdopters = k[i] * 0.135 + innovators;
			double earlyMajority = k[i] * 0.34 + earlyAdopters;
			double lateMajority = k[i] * 0.34 + earlyMajority;
			
			buf.append(linguagensPrettyPrinting[i] + " & ");
			
			String innovatorsTime = calculaInstanteAtingiu(diretorio + "dados_" + linguagem + "_semana.dat", innovators);
			if(innovatorsTime != null) {
				buf.append(innovatorsTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String earlyAdoptersTime = calculaInstanteAtingiu(diretorio + "dados_" + linguagem + "_semana.dat", earlyAdopters);
			if(earlyAdoptersTime != null) {
				buf.append(earlyAdoptersTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String earlyMajorityTime = calculaInstanteAtingiu(diretorio + "dados_" + linguagem + "_semana.dat", earlyMajority);
			if(earlyMajorityTime != null) {
				buf.append(earlyMajorityTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String lateMajorityTime = calculaInstanteAtingiu(diretorio + "dados_" + linguagem + "_semana.dat", lateMajority);
			if(lateMajorityTime != null) {
				buf.append(lateMajorityTime);
			} else {
				buf.append("N/R ");
			}
			
			buf.append("\\\\ \n ");
		}
		
		buf.append(rodape);
		
		System.out.println(buf.toString());
	}
		
	private static String calculaInstanteAtingiu(String path, double threshold) throws NumberFormatException, IOException {		
		FileReader fr = new FileReader(path);		
		BufferedReader br = new BufferedReader(fr);
		String linha = null;

		while ((linha = br.readLine()) != null) {
			String[] linhaQuebrada = linha.trim().split("\t");
			
			int valor = Integer.valueOf(linhaQuebrada[1]);

			if (valor > threshold) {
				br.close();
				return linhaQuebrada[0];
			}			
		}
		br.close();
		
		return null;
		
	}

}
