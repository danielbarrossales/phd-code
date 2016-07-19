package br.ufpe.cin.dados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface LedorUnidadeInfeccao {
	
	public void leUnidadeInfeccao(String linguagem, Map<Integer, UnidadeInfeccao> projetos) throws FileNotFoundException, IOException;
	
	public void leMembros(Map<Integer, UnidadeInfeccao> projetos) throws FileNotFoundException, IOException;

}
