package br.ufpe.cin.dados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface LedorUnidadeInfeccaoSinonimos {
	
	public void leUnidadeInfeccao(String[] sinonimos, Map<Integer, UnidadeInfeccao> projetos) throws FileNotFoundException, IOException;
	
	public void leMembros(Map<Integer, UnidadeInfeccao> posts) throws FileNotFoundException, IOException;

}
