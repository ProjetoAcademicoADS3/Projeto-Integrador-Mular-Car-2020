/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumCategoriaCnh;
import br.com.mulacar.model.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotoristaDal {

    private Connection conexao;

    public MotoristaDal() {
        conexao = Conexao.getConexao();
    }
    
    public void addMotorista(Motorista motorista) throws Exception {
        String sql = "INSERT INTO motorista (mot_nome, mot_cpf, mot_rg, mot_rg_orgao_emissor "
                + "mot_cnh_numero, mot_cnh_data_validade, mot_cnh_imagem, mot_cnh_categoria) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, motorista.getNome());
            preparedStatement.setString(2, motorista.getCpf());
            preparedStatement.setString(3, motorista.getRg());
            preparedStatement.setString(4, motorista.getOrgaoEmissor());
            preparedStatement.setString(5, motorista.getNumeroCnh());
            preparedStatement.setDate(6, new java.sql.Date(motorista.getDataValidadeCnh().getTime()));
            preparedStatement.setString(7, motorista.getPathImagemCnh());
            preparedStatement.setString(8, motorista.getCategoriaCnh().name());
            
            preparedStatement.executeUpdate();
            conexao.commit();

        } catch (SQLException e) {
            conexao.rollback();
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        }
    }
    
    public void updateMotorista(Motorista motorista) throws Exception {
        
        String sql = "UPDATE motorista "
                + "SET mot_nome = ?, "
                + "mot_cpf      = ?, "
                + "mot_rg       = ? "
                + "mot_rg_orgao_emissor = ? "
                + "mot_cnh_numero = ? "
                + "mot_cnh_data_validade = ? "
                + "mot_cnh_imagem = ? "
                + "mot_cnh_categoria = ? "
                + "WHERE mot_id = ? ";
          try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, motorista.getNome());
            preparedStatement.setString(2, motorista.getCpf());
            preparedStatement.setString(3, motorista.getRg());
            preparedStatement.setString(4, motorista.getOrgaoEmissor());
            preparedStatement.setString(5, motorista.getNumeroCnh());
            preparedStatement.setDate(6, new java.sql.Date(motorista.getDataValidadeCnh().getTime()));
            preparedStatement.setString(7, motorista.getPathImagemCnh());
            preparedStatement.setString(8, motorista.getCategoriaCnh().name());
            
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }    

    public void deleteMotorista(Motorista motorista) throws Exception {
        String sql = "DELETE FROM motorista WHERE mot_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, motorista.getId());
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );            
            throw e;
        }
    }

    public List<Motorista> getAllMotoristas() throws Exception {
        List<Motorista> listaMotoristas = new ArrayList<>();
        
        String sql = "SELECT * FROM motorista";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
                Motorista motorista = new Motorista();
                
                preencherMotoristaRetornoBanco(motorista, rs);

                listaMotoristas.add(motorista);
            }
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );            
            throw new Exception("Erro ao listar motoristas" + e.getMessage());
        }
        return listaMotoristas;
    }

    public Motorista getMotoristaById(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;

        String sql = "SELECT * FROM motorista WHERE mot_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, motorista.getId());
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherMotoristaRetornoBanco(motoristaRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        }
        return motorista;
    }

    public Motorista getMotoristaByCpf(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;
        
        String sql = "SELECT * FROM motorista WHERE mot_cpf = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, motorista.getCpf());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherMotoristaRetornoBanco(motoristaRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        }
        return motoristaRetorno;
    }
    
    public Motorista getMotoristaByRg(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;
        
        String sql = "SELECT * FROM motorista WHERE mot_rg = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, motorista.getRg());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherMotoristaRetornoBanco(motoristaRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        }
        return motoristaRetorno;
    }    

    public Motorista getMotoristaByNumeroCnh(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;

        String sql = "SELECT * FROM motorista where mot_cnh_numero like ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, String.format("%%s%", motorista.getNumeroCnh()));
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                preencherMotoristaRetornoBanco(motoristaRetorno, rs);
            }            

        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;            
        }
        return motoristaRetorno;
    }
 
   public Motorista getMotoristaByNome(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;

        String sql = "SELECT * FROM motorista where mot_nome like ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, String.format("%%s%", motorista.getNome()));
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                preencherMotoristaRetornoBanco(motoristaRetorno, rs);
            }            

        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;            
        }
        return motoristaRetorno;
    }    
    
    private void preencherMotoristaRetornoBanco(Motorista motoristaRetorno, ResultSet rs) throws SQLException {
        motoristaRetorno = new Motorista();
        motoristaRetorno.setId(rs.getInt("mot_id"));
        motoristaRetorno.setNome(rs.getString("mot_nome"));
        motoristaRetorno.setCpf(rs.getString("mot_cpf"));
        motoristaRetorno.setRg(rs.getString("mot_rg"));
        motoristaRetorno.setNumeroCnh(rs.getString("mot_cnh_numero"));
        motoristaRetorno.setDataValidadeCnh(new Date(rs.getString("mot_cnh_data_validade")));
        motoristaRetorno.setPathImagemCnh(rs.getString("mot_cnh_imagem"));
        motoristaRetorno.setCategoriaCnh(EnumCategoriaCnh.valueOf(rs.getString("mot_cnh_categoria")));
    }    
    
}
