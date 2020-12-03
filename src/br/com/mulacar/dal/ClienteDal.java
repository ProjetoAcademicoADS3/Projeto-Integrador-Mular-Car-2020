/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.model.Cliente;
import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author roger
 */
public class ClienteDal {
    
    private Connection conexao;
    
    public ClienteDal(){
        conexao = Conexao.getConexao();
    }
    
//  public void addCliente(Cliente cliente) throws Exception {
//
//        String sql = "INSERT INTO cliente("
//                + "cli_razao_social,"
//                + "cli_nome_fantasia,"
//                + "cli_nome,"
//                + "cli_status,"
//                + "cli_cpf_cnpj,"
//                + "cli_rg,"
//                + "cli_rg_orgao_emissor,"
//                + "cli_tipo) VALUES (?,?,?,?,?,?,?,?)";
//        try {
//
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//
//            preparedStatement.setString(1, cliente.getRazaoSocial().trim());
//            preparedStatement.setString(2, cliente.get);
//            preparedStatement.setString(3, cliente.getEmail().trim());
//            preparedStatement.setString(4, cliente.getSenha());
//            preparedStatement.setString(5, cliente.getStatus().toString());
//            preparedStatement.setString(6, cliente.getPerfil().toString());
//            preparedStatement.setDate(7, new java.sql.Date(cliente.getDataCadastro().getTime()));
//
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException erro) {
//            throw erro;
//        }
//    }  
    
    
    
}
