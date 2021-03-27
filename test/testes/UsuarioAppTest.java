package testes;

import br.com.mulacar.dal.UsuarioDal;
import br.com.mulacar.enumeration.EnumPerfil;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Usuario;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class UsuarioAppTest {

    public UsuarioAppTest() {
    }

    @Test
    public void testInsertUser() throws Exception {

        //Criando um usuario 
        Usuario usu = new Usuario("Bruno", "3763250506", "bruno@gmail.com",
                "123456", EnumPerfil.ADMINISTRADOR, EnumStatus.ATIVO, new Date());

        //Criando a instância da dal para manipular o banco
        UsuarioDal dal = new UsuarioDal();
        //Inserindo um usuário no banco 
        dal.addUsuario(usu);

        //Criando a estrutura de lista para receber o get do banco
        List<Usuario> listUser;
        listUser = dal.getAllUsuarios();
        //Saída no console para simples conferência
        System.out.println(listUser.get(1));
        //Criando uma variável para receber o tamanho do cpf vindo do banco
        Integer cpfRetorno = listUser.get(1).getCpf().length();
        //Criado uma varíavel expressando qual a expectativa do retorno
        Integer cpfValido = 11;

        //Utilizando o método assert (afirmar) para fazer a comparação
        //da expectativa com o valor vindo do banco
        Assert.assertEquals(cpfValido, cpfRetorno);
    }
}
