package br.ufpe.cin.latex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTheBestAppendixYouHaveEverSeen {

	public static void main(String[] args) throws IOException {
		// for each data source create a chapter
			// for each time grouping create a section
				// for each language create a subsection
					// for each time slice create a subsubsection
						// create the plot page
						// create the table
						// for each iteration of correction print the fir params of the
		// correction, R2 and p-value

		// languages[0] -> GitHub, languages[1] -> Stack Overflow
		String[][] languages = {
				{ "Assembly", "C", "CPP", "CSharp", "Dart", "Go", "Java", "JavaScript", "Julia", "ObjectiveC", "PHP",
						"Python", "R", "Ruby", "Rust", "Shell", "Swift" },
				{ "assembly", "c", "c++", "c#", "dart", "go", "java", "javascript", "julia", "objective-c", "php",
						"python", "r", "ruby", "rust", "shell", "swift" } };

		String[] linguagesPrettyPrinting = { "Assembly", "C", "C++", "C\\#", "Dart", "Go", "Java", "Javascript",
				"Julia", "Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };

		// String stackOverflow = buildFitParamsSection("StackOverflow",
		// linguagensStackOverflow, linguagensPrettyPrinting,
		// "/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/stackoverflow_4/individuos/");
		String[]  paths = {"C:\\Users\\emano\\Dropbox\\UFPE\\Doutorado\\Dados\\github\\ghtorrent\\mathematica\\programadores\\", 
				"C:\\Users\\emano\\Dropbox\\UFPE\\Doutorado\\Dados\\stackoverflow_4\\individuos\\"};

		String[] sources = { "github", "stackoverflow" };
		String[] sourcesPrettyPrinting = { "GitHub", "Stack Overflow" };

		String[] timeGrouping = { "semana", "mes" };
		String[] timeGroupingEnglish = { "week", "month" };
		String[] timeGroupingPrettyPrinting = { "Week", "Month" };

		String chapter = "\\chapter{Data from %s}\n\n";// insert name of the data source
		String section = "\\section{%s}\n\n";// insert the language with
												// String.format
		String subsection = "\\subsection{%s}\n\n";// insert the time slices
													// with String.format
		String subsubsection = "\\subsubsection{%s}\n\n";// insert the time
															// slices with
															// String.format

		String[] timeSlices = { "100", "90", "80", "70", "60" };

		String outputPath = "C:\\Users\\emano\\Documents\\GitHub\\phd-code\\src\\br\\ufpe\\cin\\latex\\";

		for (int i = 0; i < sources.length; i++) {
			FileWriter fw = new FileWriter(outputPath + "appendix_" + sources[i] + ".tex");
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(String.format(chapter, sourcesPrettyPrinting[i]));
			bw.write("This appendix shows plots and tables regarding the fitting, residual-based correction and evaluation "
							+ "for the data from " + sourcesPrettyPrinting[i] + ". ");

			bw.write("In this you will find sections for each time grouping (week or month), sub-sections for each language, which are divided into inner sections for each time "
							+ "slice (100\\%, 90\\%, 80\\%, 70\\% and 60\\%).\n\n");
			
			bw.write("\\newpage\n\n\n");
			for (int j = 0; j < timeGrouping.length; j++) {
				bw.write(String.format(section, timeGroupingPrettyPrinting[j]));

				for (int k = 0; k < languages[i].length; k++) {
					bw.write(String.format(subsection, linguagesPrettyPrinting[k]));

					for (int l = 0; l < timeSlices.length; l++) {
						// do not start section if language is swift on time slices 80, 70, 60
						if (!((languages[i][k].equals("Swift") || languages[i][k].equals("swift"))
									&& (timeSlices[l].equals("80") || timeSlices[l].equals("70")
											|| timeSlices[l].equals("60")))) {
							bw.write(String.format(subsubsection, "Time slice " + timeSlices[l] + "\\%"));
							
							bw.write("\\begin{center} \n");
							bw.write(String.format("\\captionof{table}{Fit parameters, $R^2$ and p-value for the original model and corrections (language %s, grouped by %s, %s\\%% of the dataset)} \n",linguagesPrettyPrinting[k], timeGroupingEnglish[j], timeSlices[l]));
							bw.write("\\label{my-label} \n");
							bw.write("\\begin{tabular}{l|c|c|c|c|c|c} \n");
							bw.write("\\hline\n");
							bw.write(
									"\\multicolumn{1}{c|}{\\multirow{2}{*}{Model}} & \\multicolumn{4}{c|}{\\begin{tabular}[c]{@{}c@{}}Fit Parameters\\\\ (original/correction)\\end{tabular}} & \\multicolumn{1}{c|}{\\multirow{2}{*}{$R^2$}} & \\multicolumn{1}{c}{\\multirow{2}{*}{\\begin{tabular}[c]{@{}c@{}}SE\\\\ p-value\\end{tabular}}} \\\\ \\cline{2-5} \n");
							bw.write(
									"\\multicolumn{1}{c|}{} & K/$\\beta$ & \\multicolumn{1}{c|}{r/$\\omega$} & \\multicolumn{1}{c|}{$t_m$/$\\xi$} & \\multicolumn{1}{c|}{a/$\\delta$} & \\multicolumn{1}{c|}{} & \\multicolumn{1}{c}{} \\\\ \\hline \n");

							String pValue_1 = null;
							String pValue_2 = null;
							String pValue_3 = null;
							String pValue_4 = null;
							
							try {
								pValue_1 = getPvalue(true, getPValueFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], true), 0);
							} catch (FileNotFoundException e) {
								pValue_1 = getPvalue(true, getPValueAlternativeFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], true), 0);
							}
							
							try {
								pValue_2 = getPvalue(false, getPValueFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 5);
							} catch (FileNotFoundException e) {
								pValue_2 = getPvalue(false, getPValueAlternativeFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 5);
							}
							
							try {
								pValue_3 = getPvalue(false, getPValueFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 6);
							} catch (FileNotFoundException e) {
								pValue_3 = getPvalue(false, getPValueAlternativeFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 6);
							}
							
							
							try {
								pValue_4 = getPvalue(false, getPValueFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 7);
							} catch (FileNotFoundException e) {
								pValue_4 = getPvalue(false, getPValueAlternativeFilePath(paths[i], timeGrouping[j],
										languages[i][k], timeSlices[l], false), 7);
							}
							
							
							bw.write(String.format("Original & %s & %s & %s & %s & %s & %s \\\\\n",
									getFitParam("k",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], true),0),
									getFitParam("r",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], true),0),
									getFitParam("tm",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], true),0),
									getFitParam("a", getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], true), 0),
									getR2("fittedPart-dataFull",getR2FilePath(paths[i], timeGrouping[j], languages[i][k], timeSlices[l])), 
									pValue_1));
							bw.write(String.format("Correction 1 & %s & %s & %s & %s & %s & %s \\\\ \n",
									getFitParam("amp",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),1),
									getFitParam("freq",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),1),
									getFitParam("qual",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),1),
									getFitParam("shift", getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false), 1),
									getR2("gaussianCorrected1-dataFull",getR2FilePath(paths[i], timeGrouping[j], languages[i][k], timeSlices[l])), 
									pValue_2));
							bw.write(String.format("Correction 2 & %s & %s & %s & %s & %s & %s \\\\ \n",
									getFitParam("amp",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),2),
									getFitParam("freq",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),2),
									getFitParam("qual",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),2),
									getFitParam("shift", getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false), 2),
									getR2("gaussianCorrected2-dataFull",getR2FilePath(paths[i], timeGrouping[j], languages[i][k], timeSlices[l])), 
									pValue_3));
							bw.write(String.format("Correction 3 & %s & %s & %s & %s & %s & %s \\\\ \\hline \n",
									getFitParam("amp",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),3),
									getFitParam("freq",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),3),
									getFitParam("qual",getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false),3),
									getFitParam("shift", getFitParamFilePath(paths[i], timeGrouping[j], languages[i][k],timeSlices[l], false), 3),
									getR2("gaussianCorrected3-dataFull",getR2FilePath(paths[i], timeGrouping[j], languages[i][k], timeSlices[l])), 
									pValue_4));
							bw.write("\\end{tabular} \n");
							bw.write("\\end{center} \n\n");
							
							String language = null;
							if (sources[i].equals("stackoverflow") && languages[i][k].equals("c#")) {
								language = "csharp";
							} else {
								language = languages[i][k];
							}
							
							bw.write("\\begin{center}\n");
							bw.write(String.format("{\\includegraphics[width=\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_finalCorrectedGaussian.eps"));
							bw.write(String.format(
									"\\captionof{figure}{Result after thir iteration of residual-based correction, for language %s, grouped by %s, time slice %s\\%%.}\n",
									linguagesPrettyPrinting[k], timeGroupingEnglish[j], timeSlices[l]));
							bw.write("\\end{center}\n\n");
							
							bw.write("\\FloatBarrier\n\n");
							
							bw.write("\\begin{figure}[t]\n");
							bw.write("\\centering\n");
							bw.write("\\subcaptionbox{Original model}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fitPlot.eps"));
							bw.write("\\subcaptionbox{Original residual}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fittedGaussianResidual1.eps"));
							bw.write("\\subcaptionbox{Correction result after first iteration}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fittedGaussianPartialResult1.eps"));
							bw.write("\\subcaptionbox{Residual after the first correction}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fittedGaussianResidual2.eps"));
							bw.write("\\subcaptionbox{Correction result after second iteration}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fittedGaussianPartialResult2.eps"));
							bw.write("\\subcaptionbox{Residual after the second correction}\n");
							bw.write(String.format("{\\includegraphics[width=0.45\\textwidth]{figures/%s/%s}}\n",
									sources[i] + "-" + timeGroupingEnglish[j],
									language + timeSlices[l] + "_fittedGaussianResidual3.eps"));
							bw.write(String.format(
									"\\caption{Residual-based corretion for language %s, grouped by %s, time slice %s\\%%.}\n",
									linguagesPrettyPrinting[k], timeGroupingEnglish[j], timeSlices[l]));
							bw.write("\\end{figure}\n\n\n");			
							
							bw.write("\\FloatBarrier\n\n\n");
						}
					}
				}
			}			
			
			bw.close();
			fw.close();
		}
	}
	
	public static String getPValueFilePath(String path, String grouping, String language, String percentage, boolean clipped) {
		String retorno = path + grouping + "\\processado_tail\\" + language + percentage;
		
		if (clipped) {
			retorno += "_hypothesisTestClipped.txt";
		} else {
			retorno += "_hypothesisTest.txt";
		}
		
		return retorno;
	}
	
	public static String getPValueAlternativeFilePath(String path, String grouping, String language, String percentage, boolean clipped) {
		String retorno = path + grouping + "\\processado_tail\\" + language + "_" + percentage;
		
		if (clipped) {
			retorno += "_hypothesisTestClipped.txt";
		} else {
			retorno += "_hypothesisTest.txt";
		}
		
		return retorno;
	}
	
	public static String getR2FilePath(String path, String grouping, String language, String percentage){
		String retorno = path + grouping + "\\processado_tail\\" + language + percentage + "_rsquared.txt";
		return retorno;
	}
	
	public static String getFitParamFilePath(String path, String grouping, String language, String percentage, boolean original){
		String retorno = path + grouping + "\\processado_tail\\" + language + percentage;
		
		if (original) {
			retorno += "_fitParams.txt";
		} else {
			retorno += "_fitParamsGaussianResidualCorrection.txt";
		}
		
		return retorno;
	}
	
	public static String getFitParam(String param, String filePath, int iteration) throws IOException {
		String value = "";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = null;
	
		if (iteration == 0) {// original fit, the file is different
			while ((line = br.readLine()) != null) {
				if (line.startsWith(param)) {
					String[] splitLine = line.trim().split(" -> ");				
					int pos;
					if ((pos = splitLine[1].indexOf('*')) != -1) {
						String parteDecimal = splitLine[1].substring(0, pos);
						String parteExpoente = splitLine[1].substring(pos + 2, splitLine[1].length());
						value += "$" + LatexAppendixGenerator.arredondar(parteDecimal);
						value += "\\times10^{" + parteExpoente + "}$";
					} else {
						value += LatexAppendixGenerator.arredondar(splitLine[1]);
					}
					break;
				}
			}
		} else {// we will parse the params for the residual correction
			for(int i = 0; i < iteration; i++) { //skip the lines we do not want based on the iteration parameter
				line = br.readLine();
			}
			
			// removing unwanted char
			if(line == null) {
				System.out.println("PAU");
			}
			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replace(" ", "");
			
			String[] splitParams = line.trim().split(",");
			for (int i = 0; i < splitParams.length; i++) {
				String[] splitLine = splitParams[i].trim().split("->");
				int pos;
				if (splitLine[0].equals(param)) {
					if ((pos = splitLine[1].indexOf('*')) != -1) {
						String parteDecimal = splitLine[1].substring(0, pos);
						String parteExpoente = splitLine[1].substring(pos + 2, splitLine[1].length());
						value += "$" + LatexAppendixGenerator.arredondar(parteDecimal);
						value += "\\times10^{" + parteExpoente + "}$";
					} else {
						value += LatexAppendixGenerator.arredondar(splitLine[1]);
					}
					break;
				}				
			}
		}
		
		br.close();
		return value;
	}
	
	public static String getR2(String item, String filePath) throws IOException {
		String value = "";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		
		while ((line = br.readLine()) != null) {
			if (line.startsWith(item)) {
				String[] splitLine = line.trim().split("->");				
				int pos;
				if ((pos = splitLine[1].indexOf('*')) != -1) {
					String decimalPart = splitLine[1].substring(0, pos);
					String exponentPart = splitLine[1].substring(pos + 2, splitLine[1].length());
					value += "$" + LatexAppendixGenerator.arredondar(decimalPart);
					value += "\\times10^{" + exponentPart + "}$";
				} else {
					value += LatexAppendixGenerator.arredondar(splitLine[1]);
				}
				break;
			}
		}
		
		br.close();
		return value;
	}
	
	public static String getPvalue(boolean clippedFile, String filePath, int pValue) throws IOException {
		String value = "";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		
		if (clippedFile) {//clipped files have a different structure
			line = br.readLine();
			line = br.readLine();//we want the second line
			
			// removing unwanted char
			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replace(" ", "");
			
			String[] splitLine = line.split(",");
			if (splitLine[1].equals("0.") || splitLine[1].equals("1.")) {
				value = "$\\approx$ " + splitLine[1] + "0";
			} else {
				value = splitLine[1];
			}
		} else {
			int pValueCount = 0;
			while ((line = br.readLine()) != null) {
				line = line.replace("{", "");
				line = line.replace("}", "");
				line = line.replace(" ", "");
				line = line.replace("\"", "");
				
				String[] splitLine = line.split(",");
				if(splitLine[0].equals("PValue") && ++pValueCount == pValue) {
					if (splitLine[1].equals("0.") || splitLine[1].equals("1.")) {
						value = "$\\approx$ " + splitLine[1] + "0";
					} else {
						value = splitLine[1];
					}
					break;
				}
			}
		}
		
		return value;
	}

}
