/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.ModeloDal;
import br.com.mulacar.model.Modelo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ModeloBll {

    private static final long sderialVersionUID = 1L;
    private ModeloDal modDal;

    public ModeloBll() {
        super();
        modDal = new ModeloDal();
    }

    public void adicionarModelo(Modelo modelo) throws Exception {
        validarModelo(modelo);
        modDal.addModelo(modelo);
    }

    public void excluirModelo(Modelo modelo) throws Exception {
        try {
            modDal.deleteModelo(modelo.getId());
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception(" Este registro não pode ser excluído"
                        + " porque existe outros registros vinculados a ele\n");
            }
        }
    }

    public void alterarModelo(Modelo modelo) throws Exception {
        try {
            modDal.updateModelo(modelo);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("descricao_unica")) {
                throw new Exception("Este registro não pode ser duplicado!\n"
                        + " já existe uma modelo com esta descrição\n");
            }
        }
    }

    public List<Modelo> getConsultaModelos() throws Exception {
        return modDal.getAllModelos();
    }

    public Modelo getModeloPorId(int id) throws Exception {
        return modDal.getModeloById(id);
    }
    
    public Modelo getModeloPorNome(String nome) throws Exception {
        return modDal.getModeloByName(nome);
    }

    public ArrayList pesquisarModelo(String dados) throws Exception {
        return modDal.sourceModelo(dados);
    }

    public ResultSet pesquisaInteligente(String nome) {
        return modDal.sourceInteligente(nome);
    }

    public void validarModelo(Modelo objeto) throws Exception {
        String nome = objeto.getDescricao().trim().toLowerCase();
        String invalidos = "!@#$%¨&*()+={[}]?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição inválida para esta modelo!\n");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe a descrição da modelo\n");
        }
        if (nome.length() < 3) {
            throw new Exception("A descrição da modelo deve ter no mínimo 3 letras!\n");
        }

        List<Modelo> lista = modDal.getAllModelos();
        for (int pos = 0; pos < lista.size(); pos++) {
            Modelo mod = lista.get(pos);
            if (nome.equalsIgnoreCase(mod.getDescricao())) {
                throw new Exception("A descrição informada já existe no cadastro"
                        + "de modelos\n");
            }
        }
    }

    public void ordenaListaModelos(List<Modelo> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getDescricao().compareToIgnoreCase(lista.get(j).getDescricao()) >= 0) {
                    Modelo temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }
}
