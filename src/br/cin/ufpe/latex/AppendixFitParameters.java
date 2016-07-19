package br.cin.ufpe.latex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppendixFitParameters implements IAppendix {

	private final String APPENDIX_PREAMBLE = "In this appendix the fit parameters for the Richards "
			+ "model are presented. In the Sections below, the tables are grouped by data source "
			+ "(Sourceforge, StackOverflow and GitHub). All time slices discussed in Chapter~\ref{chapter:results}. ";

	public void build() throws IOException {
		String arquivoSaida = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/cin/ufpe/latex/fitparams2.txt";

		FileWriter fw = new FileWriter(arquivoSaida);
		BufferedWriter bw = new BufferedWriter(fw);
		
		String[] linguagensStackOverflow = { "assembly", "c", "c++", "c#", "dart", "go", "java", "javascript", "julia",
				"objective-c", "php", "python", "r", "ruby", "rust", "shell", "swift" };
		String[] linguagensGitHub = { "Assembly", "C", "cPP", "CSharp", "Dart", "Go", "Java", "JavaScript", "Julia",
				"ObjectiveC", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		String[] linguagensPrettyPrinting = { "Assembly", "C", "C++", "C\\#", "Dart", "Go", "Java", "Javascript",
				"Julia", "Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };

		String stackOverflow = buildFitParamsSection("StackOverflow", linguagensStackOverflow, linguagensPrettyPrinting,
				"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/stackoverflow_4/individuos/");
		String github = buildFitParamsSection("GitHub", linguagensGitHub, linguagensPrettyPrinting,
				"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/github/ghtorrent/mathematica/programadores/");

		bw.write(stackOverflow);
		bw.write("\n\n\n");
		bw.write(github);
		bw.flush();
		bw.close();
	}

	private String buildFitParamsSection(String fonteDados, String[] linguagens, String[] linguagensPrettyPrinting,
			String diretorioArquivos) throws IOException {

		// tabela de fit parameters para todas as linguagens (100% do dataset)
		// pag 45 da proposta
		// colunas Language, K, r, tm, a
		String[] porcentagens = { "50", "60", "70", "80", "90", "100" };
		String[] agrupamento = { "mes", "semana" };
		String[] agrupamentoPrettyPrinting = { "month", "week" };
		String[] agrupamentoPrettyPrintingCapital = { "Month", "Week" };
		String sufixofixoArquivoFitParamsSourceforge = "_fitParams.txt";

		StringBuffer buf = new StringBuffer();

		buf.append("\\section{Fit Parameters for ");
		buf.append(fonteDados);
		buf.append("}\n\n");
		buf.append("In this section the fit parameters found by the fitting algorithm are shown. Since there were two "
				+ "different time groups (month and week), fit parameters for the different time groupings are detailed in separate Sections.\n\n");

		BufferedReader br = null;
		FileReader fr = null;
		String linha = null;
		String[] linhaQuebrada = null;

		String rodape = "\\hline\n" + "\\end{tabular}\n" + "\\end{table}\n";

		for (int i = 0; i < agrupamento.length; i++) {
			buf.append("\\subsection{");
			buf.append(fonteDados);
			buf.append(", Grouped by ");
			buf.append(agrupamentoPrettyPrintingCapital[i]);
			buf.append("}\n\n");
			for (int j = 0; j < linguagens.length; j++) {

				buf.append(obtemCabecalho(linguagensPrettyPrinting[j], agrupamentoPrettyPrinting[i]));

				for (int k = 0; k < porcentagens.length; k++) {
					fr = new FileReader(diretorioArquivos + agrupamento[i] + "/processado_tail/" + linguagens[j]
							+ porcentagens[k] + sufixofixoArquivoFitParamsSourceforge);
					br = new BufferedReader(fr);

					buf.append(porcentagens[k]);
					buf.append("\\%");

					while ((linha = br.readLine()) != null) {
						linhaQuebrada = linha.trim().split(" -> ");
						buf.append(" & ");
						int pos;
						if ((pos = linhaQuebrada[1].indexOf('*')) != -1) {
							String parteDecimal = linhaQuebrada[1].substring(0, pos);
							String parteExpoente = linhaQuebrada[1].substring(pos + 2, linhaQuebrada[1].length());
							buf.append("$" + LatexAppendixGenerator.arredondar(parteDecimal));
							buf.append("\\times10^{" + parteExpoente + "}$");
						} else {
							buf.append(LatexAppendixGenerator.arredondar(linhaQuebrada[1]));
						}
					}
					buf.append("  \\\\ \n");
				}
				buf.append(rodape);
				buf.append("\n \n \n");
			}
			buf.append("\\FloatBarrier");
		}

		return buf.toString();
	}

	private String obtemCabecalho(String linguagem, String agrupamento) {
		String cabecalhoRichards = "\\begin{table}[h]\n" + "\\centering\n"
				+ "\\caption{Fit parameters for Richards, language " + linguagem + ", grouped by " + agrupamento
				+ ".}\n" + "\\label{table:fit_richards_params}\n" + "\\begin{tabular}{c | c | c | c | c}\n"
				+ "\\hline\n" + "Time slice    & K      & r      & tm       & a                        \\\\ \\hline\n";

		return cabecalhoRichards;
	}

}
