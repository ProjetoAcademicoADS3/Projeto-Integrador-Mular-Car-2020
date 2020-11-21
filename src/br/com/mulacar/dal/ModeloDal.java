/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.dal;

import br.com.mulacar.model.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;


public class ModeloDal {

    private Connection conexao;

    public ModeloDal() {
        conexao = Conexao.getConexao();
    }

    public void addModelo(Modelo modelos) throws Exception {
        String sql = "INSERT INTO tb_modelos (mod_descricao) VALUES (?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelos.getDescricao());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteModelo(int id) throws Exception {
        String sql = "DELETE FROM tb_modelos WHERE mod_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public void updateModelo(Modelo modelos) throws Exception {
        String sql = "UPDATE tb_modelos SET mod_descricao=? WHERE mod_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelos.getDescricao());
            preparedStatement.setInt(2, modelos.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public List<Modelo> getAllModelos() throws Exception {
        List<Modelo> listaModelos = new ArrayList<>();
        String sql = "SELECT * FROM tb_modelos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Modelo mod = new Modelo();
                mod.setId(rs.getInt("mod_iden"));
                mod.setDescricao(rs.getString("mod_descricao"));

                listaModelos.add(mod);
            }
        } catch (Exception erro) {
            throw erro;
        }
        return listaModelos;
    }

    public Modelo getModeloById(int id) throws Exception {
        Modelo mod = new Modelo();
        String sql = "SELECT * FROM tb_modelos WHERE mod_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                mod.setId(rs.getInt("mod_iden"));
                mod.setDescricao(rs.getString("mod_descricao"));
            }
        } catch (Exception erro) {
            throw erro;
        }
        return mod;
    }

    public ArrayList sourceModelo(String dados) throws Exception {
        String textoDigitado = dados.trim().toLowerCase();
        ArrayList<Modelo> resultado = new ArrayList<>();
        boolean existe = false;
        for (Modelo mod : getAllModelos()) {
            if (mod.getDescricao().toLowerCase().trim().contains(textoDigitado)) {
                resultado.add(mod);
                existe = true;
            }
        }
        if (!existe) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultado;
    }

    public ResultSet sourceInteligente(String nome) {
        ResultSet rs = null;

        String sql = "SELECT * FROM tb_modelos where mod_descricao like ?";
        PreparedStatement pst;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome + "%");
            rs = pst.executeQuery();

        } catch (Exception e) {
        }
        return rs;
    }

}
