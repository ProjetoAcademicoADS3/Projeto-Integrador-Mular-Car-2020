/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
// 2020 12 06 2320
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Acessorio;
import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcessorioDal {

    private Connection conexao;

    public AcessorioDal() {
        conexao = Conexao.getConexao();
    }

    public Acessorio addAcessorio(Acessorio acessorio) throws Exception {
        String sql = "INSERT INTO acessorio(ace_nome, ace_valor, ace_status) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, acessorio.getDescricao().trim());
            preparedStatement.setBigDecimal(2, acessorio.getValor());
            preparedStatement.setString(3, acessorio.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return acessorio;
    }

    public void deleteAcessorio(int id) throws Exception {
        String sql = "DELETE FROM acessorio WHERE ace_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateAcessorio(Acessorio acessorio) throws Exception {
        String sql = "UPDATE acessorio SET ace_nome=?,"
                + "ace_valor=?,"
                + "ace_status=? WHERE ace_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, acessorio.getDescricao().trim());
            preparedStatement.setBigDecimal(2, acessorio.getValor());
            preparedStatement.setString(3, acessorio.getStatus().toString());
            preparedStatement.setInt(4, acessorio.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Acessorio> getAllAcessorios() throws Exception {
        List<Acessorio> listaAcessorios = new ArrayList<>();
        String sql = "SELECT * FROM acessorio";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Acessorio ace = new Acessorio();
                ace.setId(rs.getInt("ace_id"));
                ace.setDescricao(rs.getString("ace_nome"));
                ace.setValor(rs.getBigDecimal("ace_valor"));
                ace.setStatus(EnumStatus.valueOf(rs.getString("ace_status")));
                listaAcessorios.add(ace);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar Acessorios" + e.getMessage());
        }
        return listaAcessorios;
    }

    public Acessorio getAcessorioById(int id) throws Exception {
        Acessorio ace = new Acessorio();
        String sql = "SELECT * FROM acessorio WHERE ace_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ace.setId(rs.getInt("ace_id"));
                ace.setDescricao(rs.getString("ace_nome"));
                ace.setValor(rs.getBigDecimal("ace_valor"));
                ace.setStatus(EnumStatus.valueOf(rs.getString("ace_status")));
            }
        } catch (Exception e) {
            throw e;
        }
        return ace;
    }

    public Acessorio getAcessorioByName(String nome) throws Exception {
        Acessorio ace = new Acessorio();
        String sql = "SELECT * FROM acessorio WHERE UPPER(ace_nome)=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ace.setId(rs.getInt("ace_id"));
                ace.setDescricao(rs.getString("ace_nome"));
                ace.setValor(rs.getBigDecimal("ace_valor"));
                ace.setStatus(EnumStatus.valueOf(rs.getString("ace_status")));
            }
        } catch (Exception e) {
            throw e;
        }
        return ace;
    }

    public ArrayList sourceAcessorio(String dados) throws Exception {
        String textoDigitado = dados.trim().toLowerCase();
        ArrayList<Acessorio> resultado = new ArrayList<>();
        boolean existe = false;
        for (Acessorio ace : getAllAcessorios()) {
            if (ace.getDescricao().toLowerCase().trim().contains(textoDigitado)) {
                resultado.add(ace);
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
        String sql = "SELECT * FROM acessorio WHERE ace_nome like ?";
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
