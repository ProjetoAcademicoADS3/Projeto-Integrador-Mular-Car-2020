/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.LocacaoDal;   
import br.com.mulacar.exception.MulaCarException;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.util.UtilObjetos;
import java.util.List;


public class LocacaoBll {

    private static final long sderialVersionUID = 1L;
    
    private LocacaoDal locacaoDal;

    public LocacaoBll() {
        locacaoDal = new LocacaoDal();
    }

    public Locacao adicionarLocacao(Locacao locacao) throws Exception {

        this.validarLocacao(locacao);
        
        return locacaoDal.addLocacao(locacao);
        
    }

    public void excluirLocacao(Locacao locacao) throws Exception {
        this.validarLocacaoNulo(locacao);
        
        this.validarIdNulo(locacao);
        
        this.validarLocacaoExistentePorId(locacao);
        
        locacaoDal.deleteLocacao(locacao);

    }

    public void alterarLocacao(Locacao locacao) throws Exception {
        this.validarLocacao(locacao);
        
        this.validarIdNulo(locacao);
        
        this.validarLocacaoExistentePorId(locacao);

        locacaoDal.updateLocacao(locacao);
    }

    private void validarLocacaoExistentePorId(Locacao locacao) throws Exception {
        Locacao locacaoRetorno = locacaoDal.getLocacaoById(locacao);
        
        if (UtilObjetos.ehNuloOuVazio(locacaoRetorno)) {
            throw new Exception("Já existe locação com esse ID.");
        }
    }

    public List<Locacao> getConsultaLocacaos() throws Exception {
        return locacaoDal.getAllLocacao();
    }

    public Locacao getLocacaoPorId(Locacao locacao) throws Exception {
        
        this.validarLocacaoNulo(locacao);
        
        this.validarIdNulo(locacao);
        
        return locacaoDal.getLocacaoById(locacao);
    }

    public Locacao getLocacaoCliente(Locacao locacao) throws Exception {
        this.validarLocacao(locacao);
        
        if (UtilObjetos.ehNuloOuVazio(locacao.getCliente())) {
            throw new Exception("Digite nome ou nome Fantasia para pesquisa.");
        }
        
        return locacaoDal.getLocacaoByCliente(locacao);
    }
    

    public void ordenaListaLocacaos(List<Locacao> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                
                if (!UtilObjetos.ehNuloOuVazio(lista.get(i).getCliente()) 
                        && !UtilObjetos.ehNuloOuVazio(lista.get(i).getCliente().getNome()))
                    
                if (lista.get(i).getCliente().getNome().compareToIgnoreCase(lista.get(j).getCliente().getNome()) >= 0) {
                    Locacao temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
                
            }
        }
    }    

    private void validarLocacao(Locacao locacao) throws Exception {
        
        this.validarLocacaoNulo(locacao);
        
        this.validarCamposObrigatorios(locacao);
        
        this.validarLocacaoExistentePorCliente(locacao);
        
    }
    
    private void validarIdNulo(Locacao locacao) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(locacao.getId())) {
            throw new Exception("ID da Locacao não pode ser nulo ou vazio.");
        }
    }    

    private void validarCamposObrigatorios(Locacao locacao) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(locacao.getStatus())
                                || UtilObjetos.ehNuloOuVazio(locacao.getCliente())
                                || UtilObjetos.ehNuloOuVazio(locacao.getCliente().getId())
                                || UtilObjetos.ehNuloOuVazio(locacao.getMotorista())
                                || UtilObjetos.ehNuloOuVazio(locacao.getMotorista().getId())
                                || UtilObjetos.ehNuloOuVazio(locacao.getVeiculo())
                                || UtilObjetos.ehNuloOuVazio(locacao.getVeiculo().getId())
                                || UtilObjetos.ehNuloOuVazio(locacao.getUsuario())
                                || UtilObjetos.ehNuloOuVazio(locacao.getUsuario().getId())
                                || UtilObjetos.ehNuloOuVazio(locacao.getDataRetirada())
                                || UtilObjetos.ehNuloOuVazio(locacao.getValorTotalLocacao())
                                || UtilObjetos.ehNuloOuVazio(locacao.getValorSeguro())
                                || UtilObjetos.ehNuloOuVazio(locacao.getStatus())
                                || UtilObjetos.ehNuloOuVazio(locacao.isReserva());
        
        if (temCamposNulos) {
            throw new Exception("Campos obrigatórios não foram preenchidos!\n");
        }
    }


    private void validarLocacaoExistentePorCliente(Locacao locacao) throws Exception {
        this.validarLocacaoNulo(locacao);
        
        Locacao locacaoBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(locacao.getCliente()) 
                && !UtilObjetos.ehNuloOuVazio(locacao.getCliente().getId())) {
            
            locacaoBanco = locacaoDal.getLocacaoByCliente(locacao);

            if (!UtilObjetos.ehNuloOuVazio(locacaoBanco)) {
                throw new MulaCarException("Já existe locação/reserva para este cliente.");
            }
        }
    }

    private void validarLocacaoNulo(Locacao locacao) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(locacao)) {
            throw new Exception("Locacao não pode ser nulo ou vazio.");
        }
    }       
}
