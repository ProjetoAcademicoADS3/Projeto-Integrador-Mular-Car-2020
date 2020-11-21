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
import br.com.mulacar.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;


public class CategoriaDal {

    private Connection conexao;

    public CategoriaDal() {
        conexao = Conexao.getConexao();
    }

    public void addCategoria(Categoria categoria) throws Exception {
        String sql = "INSERT INTO tb_categorias (cat_descricao, cat_valor, cat_status) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getDescricao().trim());
            preparedStatement.setBigDecimal(2, categoria.getValor());
            preparedStatement.setString(3, categoria.getStatus().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteCategoria(int id) throws Exception {
        String sql = "DELETE FROM tb_categorias WHERE cat_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public void updateCategoria(Categoria categoria) throws Exception {
        String sql = "UPDATE tb_categorias SET cat_descricao=?, cat_valor=?, cat_status=? WHERE cat_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getDescricao().trim());
            preparedStatement.setInt(2, categoria.getId());
            preparedStatement.setBigDecimal(3, categoria.getValor());
            preparedStatement.setString(4, categoria.getStatus().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public List<Categoria> getAllCategorias() throws Exception {
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM tb_categorias";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("cat_iden"));
                cat.setDescricao(rs.getString("cat_descricao"));
                cat.setValor(rs.getBigDecimal("cat_valor"));
                cat.setStatus(EnumStatus.ATIVO);

                listaCategorias.add(cat);
            }
        } catch (Exception erro) {
            throw erro;
        }
        return listaCategorias;
    }

    public Categoria getCategoriaById(int id) throws Exception {
        Categoria cat = new Categoria();
        String sql = "SELECT * FROM tb_categorias WHERE cat_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                cat.setId(rs.getInt("cat_iden"));
                cat.setDescricao(rs.getString("cat_descricao"));
                cat.setValor(rs.getBigDecimal("cat_valor"));
                cat.setStatus(EnumStatus.ATIVO);
            }
        } catch (Exception erro) {
            throw erro;
        }
        return cat;
    }

    public ArrayList sourceCategoria(String dados) throws Exception {
        String textoDigitado = dados.trim().toLowerCase();
        ArrayList<Categoria> resultado = new ArrayList<>();
        boolean existe = false;
        for (Categoria cat : getAllCategorias()) {
            if (cat.getDescricao().toLowerCase().trim().contains(textoDigitado)) {
                resultado.add(cat);
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

        String sql = "SELECT * FROM tb_categorias where cat_descricao like ?";
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
