package br.ufpe.cin.stackoverflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.ufpe.cin.data.ContabilizadorIndividuos;
import br.ufpe.cin.data.ContabilizarIndividuos;
import br.ufpe.cin.data.ContabilizarProjetos;

public class ExecutaContagem {

	public static void main(String[] args) throws IOException {

		String linguagens[] = {"java", "javascript", "ruby", "python", "php", "c", 
				"c++","c#", "shell", "r", "go", "swift", "dart", "assembly", "julia", "rust","objective-c"};
		
		Map<String,String[]> sinonimos = new HashMap<String,String[]>();
		sinonimos.put("java", new String[]{"java","jvm",".java","java.lang"});
		sinonimos.put("javascript", new String[]{"javascript","js",".js",});
		sinonimos.put("ruby", new String[]{"ruby","ruby-1.8","ruby-1.8.7","ruby-1.9","ruby-1.9.1","ruby-1.9.2","ruby-1.9.3","ruby-2.0","ruby-2.1","ruby-2.1.3",
				"ruby-2.1.4","ruby-2.1.5","ruby-2.2","ruby-2.3","rubyamf","ruby-block","ruby-box","rubycas","rubycas-client","rubycas-server","ruby-c-extension",
				"ruby-on-rails","ruby-on-rails-2","ruby-on-rails-3","ruby-on-rails-3.1","ruby-on-rails-3.2","ruby-on-rails-4","ruby-on-rails-4.1","ruby-on-rails-4.2",
				"ruby-on-rails-5","ruby-on-rails-plugins","ruby-openid","rubyosa","ruby-paranoia","ruby-parser","rubypress","ruby-prof","ruby-prolog",
				"rails-3.0.10","rails-3.1","rails-4-2-1"});
		sinonimos.put("python", new String[]{"python","python.net","python-2.1","python-2.2","python-2.3","python-2.4","python-2.5","python-2.6",
				"python-2.7","python-2.x","python-3.1","python-3.2","python-3.3","python-3.4","python-3.5","python-3.6","python-3.x","pythonic"});
		sinonimos.put("php", new String[]{"php","php4","php5","php-5.2","php-5.3","php-5.4","php-5.5","php-5.6","php-7"});
		sinonimos.put("c", new String[]{"c"});
		sinonimos.put("c++", new String[]{"c++","c++03","c++11","c++14","c++1z","c++98","cpp"});
		sinonimos.put("c#", new String[]{"c#",".cs-file","c#.net","c#-1.2","c#-2.0","c#-3.0","c#-4.0","c#-5.0","c#-6.0","c#-7.0","c#-language","csharp",
				"c-sharp","visual-c#"});
		sinonimos.put("shell", new String[]{"shell","shell32","shell32.dll","shellcode","shell-command","shell-commands","shell-exec",
				"shellexecute","shellexecuteex","shellscript","shell-script","shell-scripting"});
		sinonimos.put("r", new String[]{"r","rstats","r-language"});
		sinonimos.put("go", new String[]{"go","golang","go-language"});
		sinonimos.put("swift", new String[]{"swift","swift1.2","swift2","swift2.1","swift2.2","swift3","swift-ios","swift-language"});
		sinonimos.put("dart", new String[]{"dart","dart-sdk"});
		sinonimos.put("assembly", new String[]{"assembly","asm","assembler","assembly-language"});
		sinonimos.put("julia", new String[]{"julia","julia-lang"});
		sinonimos.put("rust", new String[]{"rust","rust-0.11","rust-0.8","rust-0.9"});
		sinonimos.put("objective-c", new String[]{"objective-c","objc","objective-c++","objective-c-2.0"});
		

		ContabilizadorIndividuos contabilizador = new ContabilizarIndividuos();
		String diretorio = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/individuos/";
		LedorStackoverflow ledor = new LedorStackoverflow();
		AcumulaDadosStackoverflow acumulaDia = new AcumulaDadosDiaStackoverflow(contabilizador, diretorio, ledor);
		AcumulaDadosStackoverflow acumulaSemana = new AcumulaDadosSemanaStackoverflow(contabilizador, diretorio, ledor);
		AcumulaDadosStackoverflow acumulaMes = new AcumulaDadosMesStackoverflow(contabilizador, diretorio, ledor);
		
		
		for(String linguagem  : linguagens) {
			acumulaDia.analisa(sinonimos.get(linguagem), true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaSemana.analisa(sinonimos.get(linguagem), true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaMes.analisa(sinonimos.get(linguagem), true, true);
		}
		
		contabilizador = new ContabilizarProjetos();
		diretorio = "/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/posts/";
		acumulaDia = new AcumulaDadosDiaStackoverflow(contabilizador, diretorio, ledor);
		acumulaSemana = new AcumulaDadosSemanaStackoverflow(contabilizador, diretorio, ledor);
		acumulaMes = new AcumulaDadosMesStackoverflow(contabilizador, diretorio, ledor);
		
		
		for(String linguagem  : linguagens) {
			acumulaDia.analisa(sinonimos.get(linguagem), true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaSemana.analisa(sinonimos.get(linguagem), true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaMes.analisa(sinonimos.get(linguagem), true, true);
		}
		
	}

	
}
