package br.ufpe.cin.data;

import java.util.Map;

public class ContabilizarProjetos implements ContabilizadorIndividuos {

	@Override
	public int contabilizarIndividuos(UnidadeInfeccao unidade, Map<Integer, Integer> individuosContabilizados) {
		if(individuosContabilizados.get(unidade.id) == null){
			individuosContabilizados.put(unidade.id, 0);
			return 1;
		} else {
			return 0;
		}
	}

}
