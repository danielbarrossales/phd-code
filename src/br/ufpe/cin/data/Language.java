package br.ufpe.cin.data;

public class Language {
	
	public static int JAVA = 0;
	
	public static int JAVASCRIPT = 1;
	
	public static int PYTHON = 2;
	
	public static int PHP = 3;
	
	public static int RUBY = 4;
	
	public static int CPP = 5;
	
	public static int CSHARP = 6;
	
	public static int C = 7;
	
	public static int PERL = 8;
	
	public static int SHELL = 9;
	
	public static int OBJECTIVEC = 10;
	
	public static int SWIFT = 11;
	
	public static int GO = 12;
	
	public static int DART = 13;
	
	public static int ASSEMBLY = 14;
	
	public static int JULIA = 15;
	
	public static int RUST = 16;
	
	public static int getLanguageCode(String linguagem) {
		switch(linguagem) {
			case "Java": 
			case "java": return JAVA;
			case "JavaScript": 
			case "javascript": return JAVASCRIPT;
			case "Python": 
			case "python": return PYTHON;
			case "PHP": 
			case "php": return PHP;
			case "Ruby": 
			case "ruby": return RUBY;
			case "C++": 
			case "c++": return CPP;
			case "C#": 
			case "c#": return CSHARP;
			case "C": 
			case "c": return C;
			case "Perl": 
			case "perl": return PERL;
			case "Shell": 
			case "shell": return SHELL;
			case "Objective-C": 
			case "objective-c": return OBJECTIVEC;
			case "Swift": 
			case "swift": return SWIFT;
			case "Go": 
			case "go": return GO;
			case "Dart": 
			case "dart": return DART;
			case "Assembly": 
			case "assembly": return ASSEMBLY;
			case "Julia": 
			case "julia": return JULIA;
			case "Rust": 
			case "rust": return RUST;
			default: return -1;
		}
		
	}

}
