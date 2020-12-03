/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.CategoriaDal;
import br.com.mulacar.model.Categoria;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class CategoriaBll {

    private static final long sderialVersionUID = 1L;
    private CategoriaDal catDal;

    public CategoriaBll() {
        super();
        catDal = new CategoriaDal();
    }

    public void adicionarCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);
        catDal.addCategoria(categoria);
    }

    public void excluirCategoria(Categoria categoria) throws Exception {
        try {
            catDal.deleteCategoria(categoria);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("Este registro não pode ser excluído"
                        + " porque existe outros registros vinculados a ele\n");
            }
        }
    }

    public void alterarCategoria(Categoria categoria) throws Exception {
        try {
            catDal.updateCategoria(categoria);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("descricao_unica")) {
                throw new Exception("Este registro não pode ser duplicado!\n"
                        + " já existe uma categoria com esta descrição\n");
            }
        }
    }

    public List<Categoria> getConsultaCategorias() throws Exception {
        return catDal.getAllCategorias();
    }

    public Categoria getCategoriaPorId(Categoria categoria) throws Exception {
        return catDal.getCategoriaById(categoria);
    }
    
    public Categoria getCategoriaPorNome(String nome) throws Exception {
        return catDal.getCategoriaByName(nome);
    }

    public ArrayList pesquisarCategoria(String dados) throws Exception {
        return catDal.sourceCategoria(dados);
    }

    public ResultSet pesquisaInteligente(String nome) {
        return catDal.sourceInteligente(nome);
    }

    public void validarCategoria(Categoria objeto) throws Exception {
        String nome = objeto.getDescricao().trim().toLowerCase();
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição inválida para esta categoria!\n");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe a descrição da categoria\n");
        }
        if (nome.length() < 3) {
            throw new Exception("A descrição da categoria deve ter no mínimo 3 letras!\n");
        }
        if (objeto.getValor().compareTo(BigDecimal.ZERO) == 0
                || objeto.getValor().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("O valor da categoria informado é inválido");
        }

        List<Categoria> lista = catDal.getAllCategorias();
        for (int pos = 0; pos < lista.size(); pos++) {
            Categoria cat = lista.get(pos);
            if (nome.equalsIgnoreCase(cat.getDescricao())) {
                throw new Exception("A descrição informada já existe no cadastro"
                        + "de categorias\n");
            }
        }
    }

    public void ordenaListaCategorias(List<Categoria> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getDescricao().compareToIgnoreCase(lista.get(j).getDescricao()) >= 0) {
                    Categoria temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }
}
