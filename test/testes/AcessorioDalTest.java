/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.mulacar.bll.AcessorioBll;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Acessorio;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class AcessorioDalTest {

    Acessorio acessorio;
    List<Acessorio> listAccessory;
    AcessorioBll aceBll;

    public AcessorioDalTest() {
        aceBll = new AcessorioBll();
    }

    @Test
    public void testInsertAccessory() throws Exception {

        //Instanciando um objeto acessorio
        acessorio = new Acessorio("banco de couro", EnumStatus.ATIVO, new BigDecimal(500.00));
        //Inserindo no banco de dados
        aceBll.adicionarAcessorio(acessorio);
        //Consultando a lista de objetos acessorio no banco de dados
        listAccessory = aceBll.getConsultaAcessorios();
        //Armazenando o último índice na variável indexLast
        int indexLast = listAccessory.size();
        //Saída no console para simples conferência do objeto isnerido
        System.out.println(listAccessory.get(indexLast - 1));
        //Armazenando um valor do tipo int para usá-lo no assert
        int tamanhoMinimoDoNomeDoAcessorio = 3;
        //Armazenando o tamanho de caracteres do nome do acessorio vindo do banco
        int nomeDoAcessorioInserido = listAccessory.get(indexLast - 1).getDescricao().length();
        //Inicializando uma variável booleana
        boolean nomeDoAcessorio = false;
        //Verificando qual é maior e atribuindo true caso a condição seja atendida
        if (nomeDoAcessorioInserido >= tamanhoMinimoDoNomeDoAcessorio) {
            nomeDoAcessorio = true;
        }
        //Utilizando o assert(afirmação) booleana para testar esta classe
        Assert.assertTrue(nomeDoAcessorio);

    }

}
