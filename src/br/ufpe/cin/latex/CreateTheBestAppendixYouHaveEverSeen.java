package br.ufpe.cin.latex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTheBestAppendixYouHaveEverSeen {
	
	public static void main(String[] args) throws IOException {
		//for each data source create a chapter
			//for each time grouping create a section
				//for each language create a subsection
					//for each time slice create a subsubsection
						//create the plot page
						//create the table
							//for each iteration of correction print the fir params of the correction, R2 and p-value
		
		// languages[0] -> GitHub, languages[1] -> Stack Overflow 
		String[][] languages = {{ "Assembly", "C", "C++", "C#", "Dart", "Go", "Java", "JavaScript", "Julia",
			"Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" }, { "assembly", "c", "c++", "c#", "dart", "go", "java", "javascript", "julia",
				"objective-c", "php", "python", "r", "ruby", "rust", "shell", "swift" }};
		
		String[] linguagesPrettyPrinting = { "Assembly", "C", "C++", "C\\#", "Dart", "Go", "Java", "Javascript",
				"Julia", "Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		
		//String stackOverflow = buildFitParamsSection("StackOverflow", linguagensStackOverflow, linguagensPrettyPrinting,
			//"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/stackoverflow_4/individuos/");
		String diretorioStackOverflow = "C:\\Users\\emano\\Dropbox\\UFPE\\Doutorado\\Dados\\stackoverflow_4\\individuos\\semana\\";
		//String github = buildFitParamsSection("GitHub", linguagensGitHub, linguagensPrettyPrinting,
			//"/Users/emanoel/Dropbox/UFPE/Doutorado/Dados/github/ghtorrent/mathematica/programadores/");
		String diretorioGithub =  "C:\\Users\\emano\\Documents\\GitHub\\phd-code\\src\\br\\ufpe\\cin\\github\\individuos\\semana\\";
		
		String[] sources = {"github", "stackoverflow"};
		String[] sourcesPrettyPrinting = {"GitHub", "Stack Overflow"};
		
		String[] timeGrouping = {"semana", "mes"};
		String[] timeGroupingEnglish = {"week", "month"};
		String[] timeGroupingPrettyPrinting = {"Week", "Month"};
		
		String chapter = "\\chapter{%}\n\n";//insert name of the data source
		String section = "\\section{%s}\n\n";//insert the language with String.format
		String subsection = "\\subsection{%s}\n\n";//insert the time slices with String.format
		String subsubsection = "\\subsubsection{%s}\n\n";//insert the time slices with String.format
		
		String[] timeSlices = {"100", "90", "80", "70", "60"};
		
		String outputPath = "C:\\Users\\emano\\Documents\\GitHub\\phd-code\\src\\br\\cin\\ufpe\\latex\\";

		
		
		for(int i = 0; i < sources.length; i++) {
			FileWriter fw = new FileWriter(outputPath + "appendix_" + sources[i] + ".tex");
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(String.format(chapter, sourcesPrettyPrinting[i]));
			bw.write("This appendix shows plots and tables regarding the fitting, residual-based correction and evaluation "
					+ "for the data from " + sourcesPrettyPrinting[i] + ".\n\n");
			
			bw.write("In this you will find sections for each time grouping (week or month), sub-sections for each language, which are divided into inner sections for each time "
					+ "slice (100\\%, 90\\%, 80\\%, 70\\% and 60\\%).\n\n\n");
			
			for (int j = 0; j < timeGrouping.length; j++) {
				bw.write(String.format(section, timeGroupingPrettyPrinting[j]));
				bw.write("\n\n\n");
				
				for(int k = 0; k < languages[i][k].length(); k++) {
					bw.write(String.format(subsection, linguagesPrettyPrinting[k]));
					bw.write("\n\n\n");
					
					for(int l = 0; l < timeSlices.length; l++) {
						bw.write(String.format(subsubsection, "Time slice " + timeSlices[l] + "\\%."));
						bw.write("\n\n\n");
						
						bw.write("\\begin{figure}[!ht]\n");
						bw.write("\\centering\n");
						bw.write("\\subcaptionbox{Original model.}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n", sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fitPlot.eps"));
						bw.write("\\subcaptionbox{Original residual.}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fittedGaussianResidual1.eps"));
						bw.write("\\subcaptionbox{Correction result after first iteration.}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fittedGaussianPartialResult1.eps"));
						bw.write("\\subcaptionbox{Residual after the first correction}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fittedGaussianResidual2.eps"));
						bw.write("\\subcaptionbox{Correction result after second iteration.}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fittedGaussianPartialResult2.eps"));
						bw.write("\\subcaptionbox{Residual after the second correction}\n");
						bw.write(String.format("{\\includegraphics[width=0.4\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_fittedGaussianResidual3.eps"));
						bw.write("\\subcaptionbox{Final result after third iteration.}\n");
						bw.write(String.format("{\\includegraphics[width=0.6\\textwidth]{figures/%s/%s}}\n",sources[i] + "-" + timeGroupingEnglish[j], languages[i][k] + "_finalCorrectedGaussian.eps"));
						bw.write("\\caption{Residual-based corretion for language %s, grouped by %s, time slice %s}\n");
						bw.write("\\end{figure}\n\n\n");
					}
				}
				
			}
			
			
			
			bw.write(String.format(section, args));
			
			
			bw.close();
			fw.close();
		}
	}

}
