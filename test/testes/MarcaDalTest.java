/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.mulacar.bll.MarcaBll;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Marca;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class MarcaDalTest {

    List<Marca> listMarca;
    MarcaBll marcaBll;
    Marca marca;

    public MarcaDalTest() {
        marcaBll = new MarcaBll();
    }

    @Test
    public void testInsertMarca() throws Exception {

        //Instanciando um objeto marca
        marca = new Marca("AUDI", EnumStatus.INATIVO);
        //Inserindo o objeto marca no banco de dados
        marcaBll.adicionarMarca(marca);
        //Consultando a lista de marcas no banco de dados
        listMarca = marcaBll.getConsultaMarcas();
        //Armazenando o tamanho da lista na variável indexLast
        int indexLast = listMarca.size();
        //Imprimindo uma saída para simples conferência
        System.out.println(listMarca.get(indexLast - 1));
        //Usando uma variável booleana para afirmar que o status é igual a inativo
        boolean status = listMarca.get(indexLast - 1).getStatus().toString().equals("INATIVO");
        //Saída do valor booleano para simples conferência
        System.out.println(status);
        //Verificando o valor da variável booleana
        if (!status) {
            fail("O status esperado não corresponde a expectativa");
        }
        //Utilizando o método assert para testar o insert
        Assert.assertTrue(status);
    }
}
