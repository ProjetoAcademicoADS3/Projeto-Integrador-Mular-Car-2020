/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumTipoEndereco;
import br.com.mulacar.enumeration.EnumUF;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Endereco;
import br.com.mulacar.model.Motorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;
import br.com.mulacar.util.UtilObjetos;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDal {

    private Connection conexao;

    public EnderecoDal() {
        conexao = Conexao.getConexao();
    }
    
    public void addEndereco(Endereco endereco) throws Exception {
        String sql = "INSERT INTO endereco (end_tipo, end_cep, end_rua, end_numero "
                + "end_complemento, end_bairro, end_cidade, end_uf, end_endereco_id, end_motorista_id) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, endereco.getTipoEndereco().toString());
            preparedStatement.setString(2, endereco.getCep());
            preparedStatement.setString(3, endereco.getRua());
            preparedStatement.setString(4, endereco.getNumero());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setString(6, endereco.getBairro());
            preparedStatement.setString(7, endereco.getCidade());
            preparedStatement.setString(8, endereco.getUf().sigla());
            
            if (!UtilObjetos.ehNuloOuVazio(endereco.getCliente())) {
                preparedStatement.setInt(9, endereco.getCliente().getId());
                preparedStatement.setNull(10, Types.INTEGER);
            } 

            if (!UtilObjetos.ehNuloOuVazio(endereco.getMotorista())) {
                preparedStatement.setNull(9, Types.INTEGER);
                preparedStatement.setInt(10, endereco.getMotorista().getId());
            } 
            
            preparedStatement.executeUpdate();
          
            conexao.commit();

        } catch (SQLException e) {
            conexao.rollback();            
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", ex );
            throw ex;            
        }
    }
    
    public void addEnderecos(List<Endereco> enderecos) throws Exception {
        
        conexao.setAutoCommit(false);
        
        String sql = "INSERT INTO endereco (end_tipo, end_cep, end_rua, end_numero, "
                + "end_complemento, end_bairro, end_cidade, end_uf, end_cliente_id, end_motorista_id) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            for (Endereco endereco : enderecos) {
                preparedStatement.setString(1, endereco.getTipoEndereco().toString());
                preparedStatement.setString(2, endereco.getCep());
                preparedStatement.setString(3, endereco.getRua());
                preparedStatement.setString(4, endereco.getNumero());
                preparedStatement.setString(5, endereco.getComplemento());
                preparedStatement.setString(6, endereco.getBairro());
                preparedStatement.setString(7, endereco.getCidade());
                preparedStatement.setString(8, endereco.getUf().sigla().toString());

                if (!UtilObjetos.ehNuloOuVazio(endereco.getCliente())) {
                    preparedStatement.setInt(9, endereco.getCliente().getId());
                    preparedStatement.setNull(10, Types.INTEGER);
                } 

                if (!UtilObjetos.ehNuloOuVazio(endereco.getMotorista())) {
                    preparedStatement.setNull(9, Types.INTEGER);
                    preparedStatement.setInt(10, endereco.getMotorista().getId());
                } 

                preparedStatement.addBatch();
            }
            
            int[] count = preparedStatement.executeBatch();

            conexao.commit();

        } catch (SQLException e) {
            conexao.rollback();   
            
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e.getNextException());
            
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", ex );
            throw ex;            
        }
    }    
    
    public void updateEndereco(Endereco endereco) throws Exception {
        
        String sql = "UPDATE endereco "
                    + "SET end_razao_social = ?, "
                    + "end_nome_fantasia = ?, "
                    + "end_nome = ? "
                    + "end_status = ? "
                    + "end_cpf_cnpj = ? "
                    + "end_rg = ? "
                    + "end_rg_orgao_emissor = ? "
                    + "end_tipo = ? "
                    + "WHERE end_id = ? ";
          try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, endereco.getTipoEndereco().toString());
            preparedStatement.setString(2, endereco.getCep());
            preparedStatement.setString(3, endereco.getRua());
            preparedStatement.setString(4, endereco.getNumero());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setString(6, endereco.getBairro());
            preparedStatement.setString(7, endereco.getCidade());
            preparedStatement.setString(8, endereco.getUf().sigla());
            
            if (!UtilObjetos.ehNuloOuVazio(endereco.getCliente())) {
                preparedStatement.setInt(9, endereco.getCliente().getId());
            } 

            if (!UtilObjetos.ehNuloOuVazio(endereco.getMotorista())) {
                preparedStatement.setInt(10, endereco.getMotorista().getId());
            } 
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException erro) {
            throw erro;
        }
    }    

    public void deleteEndereco(Endereco endereco) throws Exception {
        String sql = "DELETE FROM endereco WHERE end_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, endereco.getId());
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );            
            throw e;
        }
    }

    public List<Endereco> getAllEnderecos() throws Exception {
        List<Endereco> listaEnderecos = new ArrayList<>();
        
        String sql = "SELECT * FROM endereco";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
                Endereco endereco = new Endereco();
                
                preencherEnderecoRetornoBanco(endereco, rs);

                listaEnderecos.add(endereco);
            }
        } catch (Exception e) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );            
            throw new Exception("Erro ao listar enderecos" + e.getMessage());
        }
        return listaEnderecos;
    }

    public Endereco getEnderecoById(Endereco endereco) throws Exception {
        Endereco enderecoRetorno = null;

        String sql = "SELECT * FROM endereco WHERE end_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, endereco.getId());
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherEnderecoRetornoBanco(enderecoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );
            throw e;
        }
        return endereco;
    }

    public Endereco getEnderecoByCliente(Cliente cliente) throws Exception {
        Endereco enderecoRetorno = null;
        
        String sql = "SELECT * FROM endereco WHERE end_cliente_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherEnderecoRetornoBanco(enderecoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );
            throw e;
        }
        return enderecoRetorno;
    }
    
    public Endereco getEnderecoByMotorista(Motorista motorista) throws Exception {
        Endereco enderecoRetorno = null;
        
        String sql = "SELECT * FROM endereco WHERE end_motorista_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, motorista.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherEnderecoRetornoBanco(enderecoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", e );
            throw e;
        }
        return enderecoRetorno;
    }    
    
    private void preencherEnderecoRetornoBanco(Endereco enderecoRetorno, ResultSet rs) throws SQLException {
        enderecoRetorno.setId(rs.getInt("end_id"));
        enderecoRetorno.setTipoEndereco(EnumTipoEndereco.valueOf(rs.getString("end_tipo")));
        enderecoRetorno.setCep(rs.getString("end_cep"));
        enderecoRetorno.setRua(rs.getString("end_rua"));
        enderecoRetorno.setComplemento(rs.getString("end_complemento"));
        enderecoRetorno.setBairro(rs.getString("end_bairro"));
        enderecoRetorno.setCidade(rs.getString("end_cidade"));
        enderecoRetorno.setUf(EnumUF.fromSigla(rs.getString("end_uf")));
        enderecoRetorno.setCliente(new Cliente(rs.getInt("end_cliente_id")));
        enderecoRetorno.setMotorista(new Motorista(rs.getInt("end_motorista_id")));
    }    
    
}
