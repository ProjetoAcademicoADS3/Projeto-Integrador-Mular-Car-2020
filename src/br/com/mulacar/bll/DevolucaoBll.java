/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.bll;

import br.com.mulacar.dal.DevolucaoDal;
import br.com.mulacar.model.Devolucao;
import java.util.List;

/**
 *
 * @author roger
 */
public class DevolucaoBll {
    
    private static final long sderialVersionUID = 1L;

    private DevolucaoDal devolucaoDal;

    public DevolucaoBll() {
        devolucaoDal = new DevolucaoDal();
    }
    
    public void adicionarDevolucao (Devolucao devolucao)throws Exception{
        devolucaoDal.addDevolution(devolucao);
    }
    
    public void excluirDevolucao (Devolucao devolucao) throws Exception{
        devolucaoDal.deleteDevolution(devolucao);
    }
    
    public void alterarDevolucao (Devolucao devolucao) throws Exception{
        devolucaoDal.updateDevolution(devolucao);
    }
    
    public List<Devolucao> getConsultaDevolucoes() throws Exception{
        return devolucaoDal.getAllDevolution();
    }
    
    public Devolucao getConsultaDevolucoesPorId(Devolucao devolucao)throws Exception{
        return devolucaoDal.getDevolutionById(devolucao);
    }
    
    public void ordenarListaDeDevolucoes (List<Devolucao> lista) throws Exception{
        devolucaoDal.ordenaListaDevolution(lista);
    }
}
