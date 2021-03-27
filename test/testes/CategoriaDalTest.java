/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.mulacar.dal.CategoriaDal;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Categoria;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class CategoriaDalTest {

    public CategoriaDalTest() {
    }

    @Test
    public void testInserirEgetAll() throws Exception {

        //Criando o objeto cat da classe Categoria
        Categoria cat = new Categoria("automovel", EnumStatus.ATIVO, new BigDecimal(1.00));
        //Criando a instância da CategoriaDal para manipular o banco de dados
        CategoriaDal dao = new CategoriaDal();
        //Inserindo o objeto cat no banco de dados
        dao.addCategoria(cat);
        //Criando a estrutura de lista para receber do banco de dados
        List<Categoria> listCat;
        listCat = dao.getAllCategorias();
        //Pegando o tamanho da lista para usar como index
        int index = listCat.size();
        //Gerando uma saída para simples conferência
        System.out.println(listCat.get(index - 1));
        //Criando uma variável para armazenar o valor do id do último da lista
        Integer id = listCat.get(index - 1).getId();
        //Fazendo um get de um único registro
        cat = dao.getCategoriaById(new Categoria(id));
        //Criando uma variável booleana para armazenar o valor boolean
        //da comparação do valor vindo do banco com o valor 0
        boolean expected = cat.getValor().compareTo(BigDecimal.ZERO) == 1;
        //Utilizando o método Assert(afirmar) para  confirmar o valor booleano
        //esperado (expected)
        Assert.assertTrue(expected);

    }

}
