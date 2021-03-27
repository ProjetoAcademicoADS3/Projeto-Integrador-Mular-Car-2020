/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class ConexaoTest {
    
    //Criando uma referência da classe Connection
    Connection conexao;
    
    public ConexaoTest() {
    }

    @Test
    public void testConexao() throws SQLException {
        
        //instanciando uma conexão com o design patterns Singleton
        conexao = Conexao.getConexao();
        //Simples saída para conferência do valor booleano
        System.out.println(!conexao.isClosed());
        //Armazendo o valor booleano na variável expected
        boolean expected = !conexao.isClosed();
        //Utilizando o método assert(afirmação) para confirmar o valor booleano
        //esperado(expected)
        Assert.assertTrue(expected);
        
    }
    
}
