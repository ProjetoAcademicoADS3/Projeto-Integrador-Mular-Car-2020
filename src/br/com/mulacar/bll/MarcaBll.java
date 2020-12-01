/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.bll;

import br.com.mulacar.dal.MarcaDal;
import br.com.mulacar.model.Marca;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MarcaBll {

    private static final long sderialVersionUID = 1L;
    private MarcaDal marDal;

    public MarcaBll() {
        super();
        marDal = new MarcaDal();
    }

    public void adicionarMarca(Marca marca) throws Exception {
        validarMarca(marca);
        marDal.addMarca(marca);
    }

    public void excluirMarca(Marca marca) throws Exception {
        try {
            marDal.deleteMarca(marca.getId());
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception(" Este registro não pode ser excluído"
                        + " porque existe outros registros vinculados a ele\n");
            }
        }
    }

    public void alterarMarca(Marca marca) throws Exception {
        try {
            marDal.updateMarca(marca);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("descricao_unica")) {
                throw new Exception("Este registro não pode ser duplicado!\n"
                        + " já existe uma marca com esta descrição\n");
            }
        }
    }

    public List<Marca> getConsultaMarcas() throws Exception {
        return marDal.getAllMarcas();
    }

    public Marca getMarcaPorId(int id) throws Exception {
        return marDal.getMarcaById(id);
    }
    
    public Marca getMarcaPorNome(String nome) throws Exception {
        return marDal.getMarcaByNome(nome);
    }

    public ArrayList pesquisarMarca(String dados) throws Exception {
        return marDal.sourceMarca(dados);
    }

    public ResultSet pesquisaInteligente(String nome) {
        return marDal.sourceInteligente(nome);
    }

    public void validarMarca(Marca objeto) throws Exception {
        String nome = objeto.getDescricao().trim().toLowerCase();
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição inválida para esta marca!\n");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe a descrição da marca\n");
        }
        if (nome.length() < 3) {
            throw new Exception("A descrição da marca deve ter no mínimo 3 letras!\n");
        }

        List<Marca> lista = marDal.getAllMarcas();
        for (int pos = 0; pos < lista.size(); pos++) {
            Marca mar = lista.get(pos);
            if (nome.equalsIgnoreCase(mar.getDescricao())) {
                throw new Exception("A descrição informada já existe no cadastro"
                        + "de marcas\n");
            }
        }
    }

    public void ordenaListaMarcas(List<Marca> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getDescricao().compareToIgnoreCase(lista.get(j).getDescricao()) >= 0) {
                    Marca temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }
}
