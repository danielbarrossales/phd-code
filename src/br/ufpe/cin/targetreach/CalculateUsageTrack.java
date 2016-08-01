package br.ufpe.cin.targetreach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.ufpe.cin.data.Util;

public class CalculateUsageTrack {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] languagesStackOverflow = { "assembly", "c", "c++", "c#", "dart", "go", "java", "javascript", "julia",
				"objective-c", "php", "python", "r", "ruby", "rust", "shell", "swift" };
		String[] languagesGithub = { "Assembly", "C", "C++", "C#", "Dart", "Go", "Java", "JavaScript", "Julia",
				"Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		String[] languagesPrettyPrinting = { "Assembly", "C", "C++", "C\\#", "Dart", "Go", "Java", "Javascript",
				"Julia", "Objective-C", "PHP", "Python", "R", "Ruby", "Rust", "Shell", "Swift" };
		
		int[] kGitHub = {36743, 658454, 1093811, 1992480, 5659, 186634, 3704894, 3125306, 11780, 349495, 1174683, 2435384,
				140186, 690810, 10494, 508784, 96605};
		
		int[] kStackOverflow = {29561, 255544, 457044, 631818, 1836, 18714, 1220824, 1584183, 1643, 114080, 726038, 1739893, 
				472231, 139084, 1845, 68451, 59345};
		

		String directoryStackOverflow = "C:\\Users\\emano\\Dropbox\\UFPE\\Doutorado\\Dados\\stackoverflow_4\\individuos\\semana\\";
		String directoryGithub =  "C:\\Users\\emano\\Documents\\GitHub\\phd-code\\src\\br\\ufpe\\cin\\github\\individuos\\semana\\";
		
		String header = "\\begin{table}[h]\n"+
				"\\centering\n"+
				"\\caption{CAPTION.}\n"+
				"\\label{table:user_type_usage}\n"+
				"\\begin{tabular}{l | c | c | c | c}\n"+
				"\\hline\n"+
				"Language    & Innovators (2.5\\%) & Early Adopters (13.5\\%) & Early Majority (34\\%) & Late Majority (34\\%)  \\\\ \\hline\n";
		String footer = "\\hline\n"
				+ "\\end{tabular}\n"
				+ "\\end{table}\n";
		

		Util.print("started reading fit params file...");
		
		StringBuffer buf = new StringBuffer();
		buf.append(header);
		String language = null;
		
		String directory = directoryStackOverflow;
		String[] linguagens = languagesStackOverflow;
		int[] k = kStackOverflow;
		
		for(int i = 0; i < linguagens.length; i++) {
			language = linguagens[i];
			
			double innovators = k[i] * 0.025;
			double earlyAdopters = k[i] * 0.135 + innovators;
			double earlyMajority = k[i] * 0.34 + earlyAdopters;
			double lateMajority = k[i] * 0.34 + earlyMajority;
			
			buf.append(languagesPrettyPrinting[i] + " & ");
			
			String innovatorsTime = calculateHitTime(directory + "dados_" + language + "_semana.dat", innovators);
			if(innovatorsTime != null) {
				buf.append(innovatorsTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String earlyAdoptersTime = calculateHitTime(directory + "dados_" + language + "_semana.dat", earlyAdopters);
			if(earlyAdoptersTime != null) {
				buf.append(earlyAdoptersTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String earlyMajorityTime = calculateHitTime(directory + "dados_" + language + "_semana.dat", earlyMajority);
			if(earlyMajorityTime != null) {
				buf.append(earlyMajorityTime + " & ");
			} else {
				buf.append("N/R & ");
			}
			
			String lateMajorityTime = calculateHitTime(directory + "dados_" + language + "_semana.dat", lateMajority);
			if(lateMajorityTime != null) {
				buf.append(lateMajorityTime);
			} else {
				buf.append("N/R ");
			}
			
			buf.append("\\\\ \n ");
		}
		
		buf.append(footer);
		
		System.out.println(buf.toString());
	}
		
	private static String calculateHitTime(String path, double threshold) throws NumberFormatException, IOException {		
		FileReader fr = new FileReader(path);		
		BufferedReader br = new BufferedReader(fr);
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] splitLine = line.trim().split("\t");
			
			int value = Integer.valueOf(splitLine[1]);

			if (value > threshold) {
				br.close();
				return splitLine[0];
			}			
		}
		br.close();
		
		return null;
		
	}

}
