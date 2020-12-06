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
import br.com.mulacar.enumeration.EnumTipoCliente;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDal {

    private Connection conexao;

    public ClienteDal() {
        conexao = Conexao.getConexao();
    }
    
    public Cliente addCliente(Cliente cliente) throws Exception {
        Cliente clienteBanco = new Cliente();
        
        String sql = "INSERT INTO cliente (cli_razao_social, cli_nome_fantasia, "
                + "cli_nome, cli_status, cli_cpf_cnpj, cli_rg, cli_rg_orgao_emissor, cli_tipo) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(4, cliente.getStatus().toString());
            preparedStatement.setString(8, cliente.getTipoCliente().toString());
            preparedStatement.setString(5, cliente.getCpfCnpj());
            
            if (cliente.getTipoCliente().equals(EnumTipoCliente.PESSOA_JURIDICA)) {
                preparedStatement.setString(1, cliente.getRazaoSocial().trim());
                preparedStatement.setString(2, cliente.getNomeFantasia());
                preparedStatement.setString(3, null);
                preparedStatement.setString(6, null);
                preparedStatement.setString(7, null);                
                
            } else {
                preparedStatement.setString(1, null);
                preparedStatement.setString(2, null);                
                preparedStatement.setString(3, cliente.getNome());
                preparedStatement.setString(6, cliente.getRg());
                preparedStatement.setString(7, cliente.getOrgaoEmissor());
            }
            
            int idCliente = preparedStatement.executeUpdate();
            
            conexao.commit();

            if (idCliente == 0) {
                throw new SQLException("Falha ao inserir usuario no banco, nenhum registro criado.");
            }
            
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clienteBanco.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao criar o cliente. Não obteve o id da inserção.");
                }
            }
            
            return clienteBanco;

        } catch (SQLException e) {
            conexao.rollback();
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", ex );
            throw ex;            
        }      
    }
    
    public void updateCliente(Cliente cliente) throws Exception {
        
        String sql = "UPDATE cliente "
                + "SET cli_razao_social = ?, "
                + "cli_nome_fantasia = ?, "
                + "cli_nome = ? "
                + "cli_status = ? "
                + "cli_cpf_cnpj = ? "
                + "cli_rg = ? "
                + "cli_rg_orgao_emissor = ? "
                + "cli_tipo = ? "
                + "WHERE cli_id = ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(4, cliente.getStatus().toString());
            preparedStatement.setString(8, cliente.getTipoCliente().toString());
            preparedStatement.setString(5, cliente.getCpfCnpj());
            
            if (cliente.getTipoCliente().equals(EnumTipoCliente.PESSOA_JURIDICA)) {
                preparedStatement.setString(1, cliente.getRazaoSocial().trim());
                preparedStatement.setString(2, cliente.getNomeFantasia());
                
            } else {
                preparedStatement.setString(3, cliente.getNome());
                preparedStatement.setString(6, cliente.getRg());
                preparedStatement.setString(7, cliente.getOrgaoEmissor());
            }
            
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }    

    public void deleteCliente(Cliente cliente) throws Exception {
        String sql = "DELETE FROM cliente WHERE cli_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getId());
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );            
            throw e;
        }
    }

    public List<Cliente> getAllClientes() throws Exception {
        List<Cliente> listaClientes = new ArrayList<>();
        
        String sql = "SELECT * FROM cliente";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente = preencherClienteRetornoBanco(rs);

                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );            
            throw new Exception("Erro ao listar clientes" + e.getMessage());
        }
        return listaClientes;
    }

    public Cliente getClienteById(Cliente cliente) throws Exception {
        Cliente clienteRetorno = null;
        
        String sql = "SELECT * FROM cliente WHERE cli_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, cliente.getId());
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                clienteRetorno = preencherClienteRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );
            throw e;
        } 
        return cliente;
    }

    public Cliente getClienteByCpfCnpj(Cliente cliente) throws Exception {
        Cliente clienteRetorno = null;
        
        String sql = "SELECT * FROM cliente WHERE cli_cpf_cnpj = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getCpfCnpj());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                clienteRetorno = preencherClienteRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );
            throw e;
        }
        return clienteRetorno;
    }
    
    public Cliente getClienteByRg(Cliente cliente) throws Exception {
        Cliente clienteRetorno = null;
        
        String sql = "SELECT * FROM cliente WHERE cli_rg = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getRg());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherClienteRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );
            throw e;
        }
        return clienteRetorno;
    }    

    public Cliente getClienteByNomeOuFantasia(Cliente cliente) throws Exception {
        Cliente clienteRetorno = null;

        String sql = "SELECT * FROM cliente where cli_nome like ? "
                + "or cli_nome_fantasia like ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, String.format("%%s%", cliente.getNome()));
            preparedStatement.setString(2, String.format("%%s%", cliente.getNomeFantasia()));
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                clienteRetorno = preencherClienteRetornoBanco(rs);
            }            

        } catch (Exception e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "ClienteDal - ", e );
            throw e;            
        }
        return clienteRetorno;
    }
    
    private Cliente preencherClienteRetornoBanco(ResultSet rs) throws SQLException {
        Cliente clienteRetorno = new Cliente();
        clienteRetorno.setId(rs.getInt("cli_id"));
        clienteRetorno.setNomeFantasia(rs.getString("cli_nome_fantasia"));
        clienteRetorno.setNome(rs.getString("cli_nome"));
        clienteRetorno.setCpfCnpj(rs.getString("cli_cpf_cnpj"));
        clienteRetorno.setRg(rs.getString("cli_rg"));
        clienteRetorno.setOrgaoEmissor(rs.getString("cli_rg_orgao_emissor"));
        clienteRetorno.setStatus(EnumStatus.valueOf(rs.getString("cli_status")));
        clienteRetorno.setTipoCliente(EnumTipoCliente.valueOf(rs.getString("cli_tipo")));
        
        return clienteRetorno;
    }    
    
}
