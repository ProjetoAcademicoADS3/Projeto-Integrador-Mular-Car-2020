/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumPerfil;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Usuario;
import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDal {

    private Connection conexao;

    public UsuarioDal() {
        conexao = Conexao.getConexao();
    }

    public void addUsuario(Usuario usu) throws Exception {

        String sql = "INSERT INTO usuario (usu_nome, usu_cpf, usu_email,"
                + "usu_senha,usu_status,usu_perfil, usu_data_cadastro) VALUES (?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, usu.getNome().trim());
            preparedStatement.setString(2, usu.getCpf());
            preparedStatement.setString(3, usu.getEmail().trim());
            preparedStatement.setString(4, usu.getSenha());
            preparedStatement.setString(5, usu.getStatus().toString());
            preparedStatement.setString(6, usu.getPerfil().toString());
            preparedStatement.setDate(7, new java.sql.Date(usu.getDataCadastro().getTime()));


            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteUsuario(int id) throws Exception {
     String sql = "DELETE FROM usuario WHERE usu_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void updateUsuario(Usuario usu) throws Exception {
       String sql = "UPDATE usuario SET usu_nome=?,"
                + "usu_cpf=?,"
                + "usu_email=?,"
                + "usu_senha=?,"
                + "usu_status=?"
                + "usu_perfil=? WHERE usu_id=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, usu.getNome().trim());
            preparedStatement.setString(2, usu.getCpf().trim());
            preparedStatement.setString(3, usu.getEmail().trim());
            preparedStatement.setString(4, usu.getSenha());
            preparedStatement.setString(5, usu.getStatus().toString());
            preparedStatement.setString(6, usu.getPerfil().toString());
            preparedStatement.setInt(7, usu.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw erro;
        }
    }

    public List<Usuario> getAllUsuarios() throws Exception {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("usu_id"));
                usu.setNome(rs.getString("usu_nome"));
                usu.setCpf(rs.getString("usu_cpf"));
                usu.setEmail(rs.getString("usu_email"));
                usu.setSenha(rs.getString("usu_senha"));
                usu.setStatus(EnumStatus.ATIVO);
                if (rs.getString("usu_perfil").equalsIgnoreCase("ADMINISTRADOR")) {
                    usu.setPerfil(EnumPerfil.ADMINISTRADOR);
                } else {
                    usu.setPerfil(EnumPerfil.CLIENTE);
                }
                listaUsuarios.add(usu);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de usuários\n"
                    + erro.getMessage());
        }
        return listaUsuarios;
    }

    public Usuario getUsuarioById(int id) throws Exception {
        Usuario usu = new Usuario();
        String sql = "SELECT * FROM usuario WHERE usu_id = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usu.setId(rs.getInt("usu_id"));
                usu.setNome(rs.getString("usu_nome"));
                usu.setCpf(rs.getString("usu_cpf"));
                usu.setEmail(rs.getString("usu_email"));
                usu.setSenha(rs.getString("usu_senha"));
                usu.setStatus(EnumStatus.ATIVO);
                if (rs.getString("usu_perfil").equalsIgnoreCase("ADMINISTRADOR")) {
                    usu.setPerfil(EnumPerfil.ADMINISTRADOR);
                } else {
                    usu.setPerfil(EnumPerfil.CLIENTE);
                }

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de Usuários\n"
                    + erro.getMessage());
        }
        return usu;
    }

    public ArrayList sourceUsuario(String dados) throws Exception {
        
         String textoDigitado = dados;
        
        ArrayList<Usuario> resultadoDaPesquisa = new ArrayList<>();
       
        boolean vdd = false;
        
        for (Usuario usu : getAllUsuarios()) {
            
            if (usu.getNome().toLowerCase().trim().contains(textoDigitado)
                    || usu.getCpf().toLowerCase().trim().contains(textoDigitado)
                    || (usu.getEmail().toLowerCase().trim().contains(textoDigitado))) {
                
                resultadoDaPesquisa.add(usu);
                
                vdd = true;
            }
        }
        if (!vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }
    
    public Usuario getUsuarioByEmail(String email) throws Exception {
        Usuario usu = null;
        
        String sql = "SELECT * FROM usuario WHERE usu_email LIKE ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usu = new Usuario();
                usu.setId(rs.getInt("usu_id"));
                usu.setNome(rs.getString("usu_nome"));
                usu.setCpf(rs.getString("usu_cpf"));
                usu.setEmail(rs.getString("usu_email"));
                usu.setSenha(rs.getString("usu_senha"));
                usu.setDataCadastro(rs.getDate("usu_data_cadastro"));
                
                EnumStatus status = EnumStatus.valueOf(rs.getString("usu_status"));
                usu.setStatus(status);
                
                EnumPerfil perfil = EnumPerfil.valueOf(rs.getString("usu_perfil"));
                usu.setPerfil(perfil);
                
//                if (rs.getString("usu_perfil").equalsIgnoreCase("ADMINISTRADOR")) {
//                    usu.setPerfil(EnumPerfil.ADMINISTRADOR);
//                } else {
//                    usu.setPerfil(EnumPerfil.CLIENTE);
//                }
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de Usuários\n"
                    + erro.getMessage());
        }
        return usu;
    }    
}
