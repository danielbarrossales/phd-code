package br.ufpe.cin.github;

import java.io.IOException;

import br.ufpe.cin.data.SumData;
import br.ufpe.cin.data.SumDayData;
import br.ufpe.cin.data.SumMonthData;
import br.ufpe.cin.data.SumWeekData;
import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.CountIndividuals;
import br.ufpe.cin.data.CountProjects;
import br.ufpe.cin.data.InfectionUnitReader;

/**
 * Runs the count
 * @author emanoelbarreiros
 *
 */
public class RunCount {

	public static void main(String[] args) throws IOException {

		String languages[] = {"Java", "JavaScript", "Ruby", "Python", "PHP", "C", 
				"C++","C#", "Shell", "R", "Go", "Swift", "Dart", "Assembly", "Julia", "Rust","Objective-C"};

		IndividualsCounter counter = new CountIndividuals();
		String directory = "/Users/emanoel/OneDrive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/individuos/";
		InfectionUnitReader reader = new GithubReader();
		SumData sumDay = new SumDayData(counter, directory, reader);
		SumData sumWeek = new SumWeekData(counter, directory, reader);
		SumData sumMonth = new SumMonthData(counter, directory, reader);
		
		
		for(String language  : languages) {
			sumDay.analyze(language, true, true);
		}
		
		for(String linguagem : languages) {
			sumWeek.analyze(linguagem, true, true);
		}
		
		for(String linguagem : languages) {
			sumMonth.analyze(linguagem, true, true);
		}
		
		counter = new CountProjects();
		directory = "/Users/emanoel/OneDrive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/projetos/";
		sumDay = new SumDayData(counter, directory, reader);
		sumWeek = new SumWeekData(counter, directory, reader);
		sumMonth = new SumMonthData(counter, directory, reader);
		
		
		for(String language  : languages) {
			sumDay.analyze(language, true, true);
		}
		
		for(String language : languages) {
			sumWeek.analyze(language, true, true);
		}
		
		for(String language : languages) {
			sumMonth.analyze(language, true, true);
		}
		
	}

}
