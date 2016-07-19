package br.cin.ufpe.latex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import br.ufpe.cin.dados.Util;

public class LatexAppendixGenerator {
	
	static DecimalFormat df = new DecimalFormat("########.#####");
	
	public static void main(String[] args) throws IOException {
		//fazerTabela1();
		
		AppendixFitParameters afp = new AppendixFitParameters();
		afp.build();
		
		//tabela de goodness of fit para todas as linguagens
		//colunas Language, epsilon-statistic, p-value, Rejects H0?
		
		//tabela comparando valores do AIC e do R2
		//colunas: Languages, AIC (richards), AIC (gompertz), R2 (richards), R2 (gompertz), Best model
		
		// Tabelas com os testes de hipotese para todos os time slices pag. 54 da proposta
		
		//APENDICE
		//tabelas individuais para cada linguagem, cada linha um timeslice
		//semelhante as tabelas do começo do codigo, mas em vez de ter as linguagens nas linhas, sao os timeslices
	}

	public static void fazerTabela1() throws FileNotFoundException, IOException {
		String arquivoSaida = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/cin/ufpe/latex/fitparasmRichards.txt";
		
		//tabela de fit parameters para todas as linguagens (100% do dataset) pag 45 da proposta
		//colunas Language, K, r, tm, a
		String[] linguagensSourceforge = {"c", "cpp", "csharp", "java", "javascript", "matlab", "objectivec", "perl", "php", "python", "ruby"};
		String[] linguagensSourceforgePrettyPrinting = {"C", "C++", "C#", "Java", "Javascript", "Matlab", "Objective-C", "Perl", "PHP", "Python", "Ruby"};
		String prefixoArquivoFitParamsSourceforge = "fitparamsWhole_";
		String sufixofixoArquivoFitParamsSourceforge = "_.csv";
		String diretorioArquivosSourceforgeRichards = "/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/Socio-PLT/analise/pycharm/processar_lote_richards/";
		String cabecalhoRichards = "\\begin{table}[h]\n"+
				"\\centering\n"+
				"\\caption{Fit parameters found by the algorithm of nonlinear model fitting using the Richards model.}\n"+
				"\\label{table:fit_richards_params}\n"+
				"\\begin{tabular}{l | c | c | c | c}\n"+
				"\\hline\n"+
				"Language    & K      & r      & tm       & a                        \\\\ \\hline\n";
		String rodape = "\\hline\n"
				+ "\\end{tabular}\n"
				+ "\\end{table}\n";
		
		BufferedReader br = null;
		FileReader fr = null;
		String linha = null;
		String[] linhaQuebrada = null;

		Util.imprimir("Iniciando leitura do arquivo de fit params...");
		
		StringBuffer buf = new StringBuffer();
		buf.append(cabecalhoRichards);
		String linguagem = null;
		for(int i = 0; i < linguagensSourceforge.length; i++) {
			linguagem = linguagensSourceforge[i];
			fr = new FileReader(
					diretorioArquivosSourceforgeRichards + prefixoArquivoFitParamsSourceforge + linguagem + sufixofixoArquivoFitParamsSourceforge);
			br = new BufferedReader(fr);
			
			buf.append(linguagensSourceforgePrettyPrinting[i]);
			
			while ((linha = br.readLine()) != null) {
				linhaQuebrada = linha.trim().split(" -> ");
				buf.append(" & ");
				int pos;
				if ((pos = linhaQuebrada[1].indexOf('*')) != -1) {
					String parteDecimal = linhaQuebrada[1].substring(0, pos);
					String parteExpoente = linhaQuebrada[1].substring(pos + 2, linhaQuebrada[1].length());
					buf.append("$" + arredondar(parteDecimal));
					buf.append("\\times10^{" + parteExpoente + "}$");
				} else {
					buf.append(arredondar(linhaQuebrada[1]));
				}
			}
			buf.append("  \\\\ \n");
		}
		buf.append(rodape);
		buf.append("\n \n \n");
		
		FileWriter fw = new FileWriter(arquivoSaida);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(buf.toString());
		bw.flush();
		bw.close();
	}
	
	public static String arredondar(String valor) {
		return df.format(Double.parseDouble(valor)).replace(',', '.');
	}
	
}
