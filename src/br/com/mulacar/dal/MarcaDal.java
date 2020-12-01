/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;

public class MarcaDal {

    private Connection conexao;

    public MarcaDal() {
        conexao = Conexao.getConexao();
    }

    public void addMarca(Marca marca) throws Exception {
        String sql = "INSERT INTO marca (mar_nome, mar_status) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricao());
            preparedStatement.setString(2, marca.getStatus().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteMarca(int id) throws Exception {
        String sql = "DELETE FROM marca WHERE mar_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public void updateMarca(Marca marca) throws Exception {
        String sql = "UPDATE marca SET mar_nome=?,"
                + "mar_status=? WHERE mar_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, marca.getDescricao());
            preparedStatement.setString(2, marca.getStatus().toString());
            preparedStatement.setInt(3, marca.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public List<Marca> getAllMarcas() throws Exception {
        List<Marca> listaMarcas = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Marca mar = new Marca();
                mar.setId(rs.getInt("mar_id"));
                mar.setDescricao(rs.getString("mar_nome"));
                mar.setStatus(EnumStatus.valueOf(rs.getString("mar_status")));

                listaMarcas.add(mar);
            }
        } catch (Exception erro) {
            throw erro;
        }
        return listaMarcas;
    }

    public Marca getMarcaById(int id) throws Exception {
        Marca mar = new Marca();
        String sql = "SELECT * FROM marca WHERE mar_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                mar.setId(rs.getInt("mar_id"));
                mar.setDescricao(rs.getString("mar_nome"));
                mar.setStatus(EnumStatus.valueOf(rs.getString("mar_status")));
            }
        } catch (Exception erro) {
            throw erro;
        }
        return mar;
    }
    
    public Marca getMarcaByNome(String nome) throws Exception {
        Marca marca = new Marca();
        String sql = "SELECT * FROM marca WHERE upper(mar_nome) = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                marca.setId(rs.getInt("mar_id"));
                marca.setDescricao(rs.getString("mar_nome"));
                marca.setStatus(EnumStatus.valueOf(rs.getString("mar_status")));
            }
        } catch (Exception erro) {
            throw erro;
        }
        return marca;
    }

    public ArrayList sourceMarca(String dados) throws Exception {
        String textoDigitado = dados.trim().toLowerCase();
        ArrayList<Marca> resultado = new ArrayList<>();
        boolean existe = false;
        for (Marca mar : getAllMarcas()) {
            if (mar.getDescricao().toLowerCase().trim().contains(textoDigitado)) {
                resultado.add(mar);
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

        String sql = "SELECT * FROM marca where mar_nome like ?";
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
