package br.ufpe.cin.dados;

import java.util.Map;

public class ContabilizarIndividuos implements ContabilizadorIndividuos {

	@Override
	public int contabilizarIndividuos(UnidadeInfeccao unidade, Map<Integer, Integer> individuosContabilizados) {
		int quantidade = 0;
		if(individuosContabilizados.get(unidade.dono) == null){
			++quantidade;
			individuosContabilizados.put(unidade.dono, 0);
		}
		
		if(unidade.getMembros() != null) {
			for(int u = 0; u < unidade.getMembros().length; u++) {
				if(individuosContabilizados.get(unidade.obtemMembro(u)) == null){
					quantidade += 1;
					individuosContabilizados.put(unidade.obtemMembro(u), 0);
				}
			}
		}
		return quantidade;
	}

}
