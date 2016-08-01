package br.ufpe.cin.stackoverflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.CountIndividuals;
import br.ufpe.cin.data.CountProjects;

public class RunCount {

	public static void main(String[] args) throws IOException {

		String languages[] = {"java", "javascript", "ruby", "python", "php", "c", 
				"c++","c#", "shell", "r", "go", "swift", "dart", "assembly", "julia", "rust","objective-c"};
		
		Map<String,String[]> synonyms = new HashMap<String,String[]>();
		synonyms.put("java", new String[]{"java","jvm",".java","java.lang"});
		synonyms.put("javascript", new String[]{"javascript","js",".js",});
		synonyms.put("ruby", new String[]{"ruby","ruby-1.8","ruby-1.8.7","ruby-1.9","ruby-1.9.1","ruby-1.9.2","ruby-1.9.3","ruby-2.0","ruby-2.1","ruby-2.1.3",
				"ruby-2.1.4","ruby-2.1.5","ruby-2.2","ruby-2.3","rubyamf","ruby-block","ruby-box","rubycas","rubycas-client","rubycas-server","ruby-c-extension",
				"ruby-on-rails","ruby-on-rails-2","ruby-on-rails-3","ruby-on-rails-3.1","ruby-on-rails-3.2","ruby-on-rails-4","ruby-on-rails-4.1","ruby-on-rails-4.2",
				"ruby-on-rails-5","ruby-on-rails-plugins","ruby-openid","rubyosa","ruby-paranoia","ruby-parser","rubypress","ruby-prof","ruby-prolog",
				"rails-3.0.10","rails-3.1","rails-4-2-1"});
		synonyms.put("python", new String[]{"python","python.net","python-2.1","python-2.2","python-2.3","python-2.4","python-2.5","python-2.6",
				"python-2.7","python-2.x","python-3.1","python-3.2","python-3.3","python-3.4","python-3.5","python-3.6","python-3.x","pythonic"});
		synonyms.put("php", new String[]{"php","php4","php5","php-5.2","php-5.3","php-5.4","php-5.5","php-5.6","php-7"});
		synonyms.put("c", new String[]{"c"});
		synonyms.put("c++", new String[]{"c++","c++03","c++11","c++14","c++1z","c++98","cpp"});
		synonyms.put("c#", new String[]{"c#",".cs-file","c#.net","c#-1.2","c#-2.0","c#-3.0","c#-4.0","c#-5.0","c#-6.0","c#-7.0","c#-language","csharp",
				"c-sharp","visual-c#"});
		synonyms.put("shell", new String[]{"shell","shell32","shell32.dll","shellcode","shell-command","shell-commands","shell-exec",
				"shellexecute","shellexecuteex","shellscript","shell-script","shell-scripting"});
		synonyms.put("r", new String[]{"r","rstats","r-language"});
		synonyms.put("go", new String[]{"go","golang","go-language"});
		synonyms.put("swift", new String[]{"swift","swift1.2","swift2","swift2.1","swift2.2","swift3","swift-ios","swift-language"});
		synonyms.put("dart", new String[]{"dart","dart-sdk"});
		synonyms.put("assembly", new String[]{"assembly","asm","assembler","assembly-language"});
		synonyms.put("julia", new String[]{"julia","julia-lang"});
		synonyms.put("rust", new String[]{"rust","rust-0.11","rust-0.8","rust-0.9"});
		synonyms.put("objective-c", new String[]{"objective-c","objc","objective-c++","objective-c-2.0"});
		

		IndividualsCounter counter = new CountIndividuals();
		String directory = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/individuos/";
		StackOverflowReader reader = new StackOverflowReader();
		SumDataStackOverflow sumsDay = new SumDayDataStackOverflow(counter, directory, reader);
		SumDataStackOverflow sumsWeek = new SumWeekDataStackoverflow(counter, directory, reader);
		SumDataStackOverflow sumsMonth = new SumMonthDataStackoverflow(counter, directory, reader);
		
		
		for(String language  : languages) {
			sumsDay.analyze(synonyms.get(language), true, true);
		}
		
		for(String language : languages) {
			sumsWeek.analyze(synonyms.get(language), true, true);
		}
		
		for(String language : languages) {
			sumsMonth.analyze(synonyms.get(language), true, true);
		}
		
		counter = new CountProjects();
		directory = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/posts/";
		sumsDay = new SumDayDataStackOverflow(counter, directory, reader);
		sumsWeek = new SumWeekDataStackoverflow(counter, directory, reader);
		sumsMonth = new SumMonthDataStackoverflow(counter, directory, reader);
		
		
		for(String language  : languages) {
			sumsDay.analyze(synonyms.get(language), true, true);
		}
		
		for(String language : languages) {
			sumsWeek.analyze(synonyms.get(language), true, true);
		}
		
		for(String language : languages) {
			sumsMonth.analyze(synonyms.get(language), true, true);
		}
		
	}

	
}
