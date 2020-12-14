/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.dal;

import br.com.mulacar.bll.MarcaBll;
import br.com.mulacar.enumeration.EnumCategoriaVeiculo;
import br.com.mulacar.enumeration.EnumStatus;
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

    public void addModelo(Modelo modelo) throws Exception {
        String sql = "INSERT INTO modelo (mod_nome, mod_status, mod_marca_id) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelo.getDescricao());
            preparedStatement.setString(2, modelo.getStatus().toString());
            preparedStatement.setInt(3, modelo.getMarca().getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteModelo(int id) throws Exception {
        String sql = "DELETE FROM modelo WHERE mod_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception erro) {
            throw erro;
        }
    }

    public void updateModelo(Modelo modelo) throws Exception {
        String sql = "UPDATE modelo SET mod_nome=?,"
                + "mod_status=?,"
                + "mod_marca_id=? WHERE mod_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelo.getDescricao());
            preparedStatement.setString(2, modelo.getStatus().toString());
            preparedStatement.setInt(3, modelo.getMarca().getId());
            preparedStatement.setInt(4, modelo.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public List<Modelo> getAllModelos() throws Exception {
        List<Modelo> listaModelos = new ArrayList<>();
        String sql = "SELECT * FROM modelo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Modelo mod = new Modelo();
                mod.setId(rs.getInt("mod_id"));
                mod.setDescricao(rs.getString("mod_nome"));
                mod.setStatus(EnumStatus.valueOf(rs.getString("mod_status")));
                MarcaBll marBll = new MarcaBll();
                mod.setMarca(marBll.getMarcaPorId(rs.getInt("mod_marca_id")));
                
                listaModelos.add(mod);
            }
        } catch (Exception erro) {
            throw erro;
        }
        return listaModelos;
    }

    public Modelo getModeloById(int id) throws Exception {
        Modelo mod = new Modelo();
        String sql = "SELECT * FROM modelo WHERE mod_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                mod.setId(rs.getInt("mod_id"));
                mod.setDescricao(rs.getString("mod_nome"));
                mod.setStatus(EnumStatus.valueOf(rs.getString("mod_status")));
                MarcaBll marBll = new MarcaBll();
                mod.setMarca(marBll.getMarcaPorId(rs.getInt("mod_marca_id")));
            }
        } catch (Exception erro) {
            throw erro;
        }
        return mod;
    }
    public Modelo getModeloByName(String nome) throws Exception {
        Modelo mod = new Modelo();
        String sql = "SELECT * FROM modelo WHERE upper (mod_nome) = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                mod.setId(rs.getInt("mod_id"));
                mod.setDescricao(rs.getString("mod_nome"));
                mod.setStatus(EnumStatus.valueOf(rs.getString("mod_status")));
                MarcaBll marBll = new MarcaBll();
                mod.setMarca(marBll.getMarcaPorId(rs.getInt("mod_marca_id")));
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

        String sql = "SELECT * FROM modelo where mod_nome like ?";
        PreparedStatement pst;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome + "%");
            rs = pst.executeQuery();

        } catch (Exception e) {
        }
        return rs;
    }
    
    public List<Modelo> listarModelosPorCategoria(EnumCategoriaVeiculo categoriaSelecionada)  throws Exception {
        
        List<Modelo> listaModelos = new ArrayList<>();
        
        String sql = "select * from modelo mod " 
                   + "inner join veiculo vei on vei.vei_modelo_id = mod.mod_id " 
                   + "inner join categoria cat on cat.cat_id = vei.vei_categoria_id " 
                   + "where cat.cat_nome = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            
            pst.setString(1, String.valueOf(categoriaSelecionada));
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Modelo mod = new Modelo();
                mod.setId(rs.getInt("mod_id"));
                mod.setDescricao(rs.getString("mod_nome"));
                mod.setStatus(EnumStatus.valueOf(rs.getString("mod_status")));
                MarcaBll marBll = new MarcaBll();
                mod.setMarca(marBll.getMarcaPorId(rs.getInt("mod_marca_id")));
                
                listaModelos.add(mod);
            }
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao buscar os veiculos disponiveis para locação.", e);
        }
        return listaModelos;        
    }

}
