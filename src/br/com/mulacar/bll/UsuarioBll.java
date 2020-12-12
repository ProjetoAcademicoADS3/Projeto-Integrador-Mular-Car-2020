/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.UsuarioDal;
import br.com.mulacar.model.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioBll {

    private UsuarioDal usuDal;

    public UsuarioBll() {
        usuDal = new UsuarioDal();
    }

    public void adicionarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        usuDal.addUsuario(usuario);
    }

    public void excluirUsuario(int id) throws Exception {
        try {
            usuDal.deleteUsuario(id);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("Este registro não pode ser excluído"
                        + " porque existe outros registros vinculados a ele\n");
            }
        }
    }

    public void alterarUsuario(Usuario usuario) throws Exception {
            usuDal.updateUsuario(usuario);
        
    }

    public Iterator getConsultaUsuarios() throws Exception {
        return usuDal.getAllUsuarios();
    }

    public Usuario getConsultaUsuarioPorId(int id) throws Exception {
        return usuDal.getUsuarioById(id);
    }

    public ArrayList pesquisarUsuario(String dados) throws Exception {
        return usuDal.sourceUsuario(dados);
    }
    
    public void validarUsuario(Usuario usuario) throws Exception {
        String nome = usuario.getNome().trim().toLowerCase();
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome do usuário inválido\nVerifique!\n");
            }
        }
        if (nome.equals("")) {
            throw new Exception("O nome do usuário deve ser informado\n");
        }
        if (nome.length() < 3) {
            throw new Exception("O nome do usuário deve ter no mínimo 3 letras!\n");
        }
        if (!validaCPF(usuario.getCpf())) {
            throw new Exception("CPF inválido!Verifique\n");
        }

        Iterator<Usuario> lista = usuDal.getAllUsuarios();
        for (int pos = 0; pos < lista.hashCode(); pos++) {
//            Usuario usu = lista.next();
            while (lista.hasNext()) {
                Usuario usu = lista.next();
                if (usuario.getCpf().equals(usu.getCpf()) && !usuario.getEmail().equals("usuario.admin@gmail.com")) {
                    throw new Exception("Já existe um usuário com este CPF\nVerifique\n");
                }
                if (usuario.getEmail().equalsIgnoreCase(usu.getEmail())) {
                    throw new Exception("O email informado já existe para outro"
                            + " usuário\nVerifique\n");
                }
            }
        }
    }
    
    public void ordenaListaUsuarioa(List<Usuario> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) >= 0) {
                    Usuario temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }

    public boolean validaCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {

            // Calculando o primeiro digito
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            //Calculando o segundo digito
            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            //Verificando se os dígitos calculados são iguais os fornecidos pelo usuário
            if ((dig10 == CPF.charAt(9)) && dig11 == CPF.charAt(10)) {
                return (true);
            } else {
                return (false);
            }
        } catch (Exception erro) {
            return (false);
        }
    }
    
    public Usuario getUsuarioByEmail(String email) throws Exception {
        return usuDal.getUsuarioByEmail(email);
    }    

}
