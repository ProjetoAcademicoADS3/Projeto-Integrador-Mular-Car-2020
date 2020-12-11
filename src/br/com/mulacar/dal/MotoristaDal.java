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
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.interfaces.Interface_ExibirImagem;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.mulacar.util.Conexao;
import java.awt.Image;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class MotoristaDal implements  Interface_ExibirImagem{

    private Connection conexao;

    public MotoristaDal() {
        conexao = Conexao.getConexao();
    }
    
    public Motorista addMotorista(Motorista motorista) throws Exception {
        Motorista motoristaBanco = new Motorista();
        
        String sql = "INSERT INTO motorista (mot_nome, mot_cpf, mot_rg, mot_rg_orgao_emissor, mot_status "
                + "mot_cnh_numero, mot_cnh_data_validade, mot_cnh_imagem, mot_cnh_categoria,) "
                + "VALUES (?,?,?,?,?,?,?,?,?,)";
        
        try {
            conexao.setAutoCommit(false);
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
                preparedStatement.setString(1, motorista.getNome());
                preparedStatement.setString(2, motorista.getCpf());
                preparedStatement.setString(3, motorista.getRg());
                preparedStatement.setString(4, motorista.getOrgaoEmissor());
                preparedStatement.setString(5, motorista.getStatus().toString());
                preparedStatement.setString(6, motorista.getNumeroCnh());
                preparedStatement.setDate(7, new java.sql.Date(motorista.getDataValidadeCnh().getTime()));
                preparedStatement.setString(8, motorista.getPathImagemCnh());
                preparedStatement.setString(9, motorista.getCategoriaCnh().name());
            
            int idMotorista = preparedStatement.executeUpdate();
            
            conexao.commit();

            if (idMotorista == 0) {
                throw new SQLException("Falha ao inserir usuario no banco, nenhum registro criado.");
            }
            
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    motoristaBanco.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao criar o motorista. Não obteve o id da inserção.");
                }
            }
            
            return motoristaBanco;

        } catch (SQLException e) {
            conexao.rollback();
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, "EnderecoDal - ", ex );
            throw ex;            
        }      
    }
    
    public void updateMotorista(Motorista motorista) throws Exception {
        
        String sql = "UPDATE motorista "
                + "SET mot_nome = ? "
                + "mot_cpf = ? "
                + "mot_rg = ? "
                + "mot_rg_orgao_emissor = ? "
                + "mot_status = ? "
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
                preparedStatement.setString(5, motorista.getStatus().toString());
                preparedStatement.setString(6, motorista.getNumeroCnh());
                preparedStatement.setDate(7, new java.sql.Date(motorista.getDataValidadeCnh().getTime()));
                preparedStatement.setString(8, motorista.getPathImagemCnh());
                preparedStatement.setString(9, motorista.getCategoriaCnh().name());
            
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
                
                motorista = preencherMotoristaRetornoBanco(rs);

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
                motoristaRetorno = preencherMotoristaRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;
        } 
        return motoristaRetorno;
    }

    public Motorista getMotoristaByCpf(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = null;
        
        String sql = "SELECT * FROM motorista WHERE mot_cpf = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, motorista.getCpf());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                motoristaRetorno = preencherMotoristaRetornoBanco(rs);
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
                preencherMotoristaRetornoBanco(rs);
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
                motoristaRetorno = preencherMotoristaRetornoBanco(rs);
            }            

        } catch (Exception e) {
            Logger.getLogger(Motorista.class.getName()).log(Level.SEVERE, "MotoristaDal - ", e );
            throw e;            
        }
        return motoristaRetorno;
    }
    
    private Motorista preencherMotoristaRetornoBanco(ResultSet rs) throws SQLException {
        Motorista motoristaRetorno = new Motorista();
        motoristaRetorno.setId(rs.getInt("mot_id"));
        motoristaRetorno.setNome(rs.getString("mot_nome"));
        motoristaRetorno.setCpf(rs.getString("mot_cpf"));
        motoristaRetorno.setRg(rs.getString("mot_rg"));
        motoristaRetorno.setOrgaoEmissor(rs.getString("mot_rg_orgao_emissor"));
        motoristaRetorno.setStatus(EnumStatus.valueOf(rs.getString("mot_status")));
        motoristaRetorno.setNumeroCnh(rs.getString("mot_cnh_numero"));
        motoristaRetorno.setDataValidadeCnh(rs.getDate("mot_cnh_data_validade"));
        motoristaRetorno.setPathImagemCnh(rs.getString("mot_cnh_imagem"));
        motoristaRetorno.setCategoriaCnh(EnumCategoriaCnh.valueOf(rs.getString("mot_cnh_categoria")));
        
        return motoristaRetorno;
    }  
    
    public ArrayList sourceMotorista(String dados) throws Exception {

        String textoDigitado = dados.trim().toLowerCase();
        ArrayList<Motorista> resultado = new ArrayList<>();
        boolean existe = false;
        for (Motorista mot : getAllMotoristas()) {
            if (mot.getNome().toLowerCase().trim().contains(textoDigitado)
                    || mot.getCpf().trim().contains(textoDigitado)
                    || mot.getRg().trim().contains(textoDigitado)
                    || mot.getNumeroCnh().trim().contains(textoDigitado)) {
                resultado.add(mot);
                existe = true;
            }
        }
        if (!existe) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultado;

    }

    @Override
    public ImageIcon exibirImagem(Motorista motorista) throws Exception {
        
        ImageIcon imageIcon = new ImageIcon(motorista.getPathImagemCnh());
        Image image = imageIcon.getImage();
        Image newing = image.getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH);
        
        ImageIcon icon = new ImageIcon(newing);
        
        return icon;
    }

}
