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
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.model.Usuario;
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

public class LocacaoDal {

    private Connection conexao;

    public LocacaoDal() {
        conexao = Conexao.getConexao();
    }
    
    public Locacao addLocacao(Locacao locacao) throws Exception {
        Locacao locacaoBanco = new Locacao();
        
        String sql = "INSERT INTO locacao (loc_cliente_id, loc_motorista_id "
                    + "loc_veiculo_id, loc_usuario_cadastro_id, loc_valor_multa, "
                    + "loc_tanque_cheio, loc_data_retirada, loc_data_devolucao_prevista, "
                    + "loc_km_inicial, loc_observacoes, loc_valores_acessorios, "
                    + "loc_valor_locacao, loc_valor_caucao, loc_valor_seguro, "
                    + "loc_status, loc_reserva) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            conexao.setAutoCommit(false);
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, locacao.getCliente().getId());
            preparedStatement.setInt(2, locacao.getMotorista().getId());
            preparedStatement.setInt(3, locacao.getVeiculo().getId());
            preparedStatement.setInt(4, locacao.getUsuario().getId());
            preparedStatement.setBigDecimal(5, locacao.getValorMulta());
            preparedStatement.setBoolean(6, locacao.isTanqueCheio());
            preparedStatement.setDate(7, new java.sql.Date(locacao.getDataRetirada().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(locacao.getDataDevolucaoPrevista().getTime()));
            preparedStatement.setString(9, locacao.getKmInicial());
            preparedStatement.setString(10, locacao.getObservacoes());
            preparedStatement.setBigDecimal(11, locacao.getValorTotalAcessorios());
            preparedStatement.setBigDecimal(12, locacao.getValorLocacao());
            preparedStatement.setBigDecimal(13, locacao.getValorCaucao());
            preparedStatement.setBigDecimal(14, locacao.getValorSeguro());
            preparedStatement.setString(15, locacao.getStatus().name());
            preparedStatement.setBoolean(16, locacao.isReserva());

            int idLocacao = preparedStatement.executeUpdate();
            
            conexao.commit();

            if (idLocacao == 0) {
                throw new SQLException("Falha ao inserir a locacao no banco, nenhum registro criado.");
            }
            
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    locacaoBanco.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao criar o locacao. Não obteve o id da inserção.");
                }
            }
            
            return locacaoBanco;

        } catch (SQLException e) {
            conexao.rollback();
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", ex );
            throw ex;            
        }      
    }
    
    public void updateLocacao(Locacao locacao) throws Exception {
        
        String sql = "UPDATE locacao "
                + "SET loc_cliente_id = ?, "
                + "loc_motorista_id = ?, "
                + "loc_veiculo_id = ? "
                + "loc_usuario_cadastro_id = ? "
                + "loc_valor_multa = ? "
                + "loc_tanque_cheio = ? "
                + "loc_data_retirada = ? "
                + "loc_data_devolucao_prevista = ? "
                + "loc_km_inicial = ? "
                + "loc_observacoes = ? "
                + "loc_valores_acessorios = ? "
                + "loc_valor_locacao = ? "
                + "loc_valor_caucao = ? "
                + "loc_valor_seguro = ? "
                + "loc_status = ? "
                + "loc_reserva = ? "
                + "WHERE loc_id = ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, locacao.getCliente().getId());
            preparedStatement.setInt(2, locacao.getMotorista().getId());
            preparedStatement.setInt(3, locacao.getVeiculo().getId());
            preparedStatement.setInt(4, locacao.getUsuario().getId());
            preparedStatement.setBigDecimal(5, locacao.getValorMulta());
            preparedStatement.setBoolean(6, locacao.isTanqueCheio());
            preparedStatement.setDate(7, new java.sql.Date(locacao.getDataRetirada().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(locacao.getDataDevolucaoPrevista().getTime()));
            preparedStatement.setString(9, locacao.getKmInicial());
            preparedStatement.setString(10, locacao.getObservacoes());
            preparedStatement.setBigDecimal(11, locacao.getValorTotalAcessorios());
            preparedStatement.setBigDecimal(12, locacao.getValorLocacao());
            preparedStatement.setBigDecimal(13, locacao.getValorCaucao());
            preparedStatement.setBigDecimal(14, locacao.getValorSeguro());
            preparedStatement.setString(15, locacao.getStatus().name());
            preparedStatement.setBoolean(16, locacao.isReserva());
            
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }    

    public void deleteLocacao(Locacao locacao) throws Exception {
        String sql = "DELETE FROM locacao WHERE loc_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, locacao.getId());
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );            
            throw e;
        }
    }

    public List<Locacao> getAllLocacao() throws Exception {
        List<Locacao> listaLocacao = new ArrayList<>();
        
        String sql = "SELECT * FROM locacao";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
                Locacao locacao = new Locacao();
                
                locacao = preencherLocacaoRetornoBanco(rs);

                listaLocacao.add(locacao);
            }
        } catch (Exception e) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );            
            throw new Exception("Erro ao listar locacoes" + e.getMessage());
        }
        return listaLocacao;
    }

    public Locacao getLocacaoById(Locacao locacao) throws Exception {
        Locacao locacaoRetorno = null;
        
        String sql = "SELECT * FROM locacao WHERE loc_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, locacao.getId());
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                locacaoRetorno = preencherLocacaoRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );
            throw e;
        } 
        return locacaoRetorno;
    }

    public Locacao getLocacaoByCliente(Locacao locacao) throws Exception {
        Locacao locacaoRetorno = null;
        
        String sql = "SELECT * FROM locacao WHERE loc_cliente_id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, locacao.getCliente().getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                locacaoRetorno = preencherLocacaoRetornoBanco(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );
            throw e;
        }
        return locacaoRetorno;
    }
    
//    public Locacao getLocacaoByRg(Locacao locacao) throws Exception {
//        Locacao locacaoRetorno = null;
//        
//        String sql = "SELECT * FROM locacao WHERE loc_rg = ?";
//        
//        try {
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setString(1, locacao.getRg());
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                preencherLocacaoRetornoBanco(rs);
//            }
//        } catch (Exception e) {
//            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );
//            throw e;
//        }
//        return locacaoRetorno;
//    }    

//    public Locacao getLocacaoByNomeOuFantasia(Locacao locacao) throws Exception {
//        Locacao locacaoRetorno = null;
//
//        String sql = "SELECT * FROM locacao where loc_nome like ? "
//                + "or loc_nome_fantasia like ? ";
//        try {
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setString(1, String.format("%%s%", locacao.getNome()));
//            preparedStatement.setString(2, String.format("%%s%", locacao.getNomeFantasia()));
//            ResultSet rs = preparedStatement.executeQuery();
//            
//            if (rs.next()) {
//                locacaoRetorno = preencherLocacaoRetornoBanco(rs);
//            }            
//
//        } catch (Exception e) {
//            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, "LocacaoDal - ", e );
//            throw e;            
//        }
//        return locacaoRetorno;
//    }
    
    private Locacao preencherLocacaoRetornoBanco(ResultSet rs) throws SQLException {
        Locacao locacaoRetorno = new Locacao();
        locacaoRetorno.setId(rs.getInt("loc_id"));
        locacaoRetorno.setCliente(new Cliente(rs.getInt("loc_cliente_id")));
        locacaoRetorno.setUsuario(new Usuario(rs.getInt("loc_usuario_cadastro_id")));
        locacaoRetorno.setValorMulta(rs.getBigDecimal("loc_valor_multa"));
        locacaoRetorno.setTanqueCheio(rs.getBoolean("loc_tanque_cheio"));
        locacaoRetorno.setDataRetirada(rs.getDate("loc_data_retirada"));
        locacaoRetorno.setDataDevolucaoPrevista(rs.getDate("loc_data_devolucao_prevista"));
        locacaoRetorno.setKmInicial(rs.getString("loc_km_inicial"));
        locacaoRetorno.setObservacoes(rs.getString("loc_observacoes"));
        locacaoRetorno.setValorTotalAcessorios(rs.getBigDecimal("loc_valor_total_acessorios"));
        locacaoRetorno.setValorLocacao(rs.getBigDecimal("loc_valor_total_locacao"));
        locacaoRetorno.setValorCaucao(rs.getBigDecimal("loc_valor_caucao"));
        locacaoRetorno.setValorSeguro(rs.getBigDecimal("loc_valor_seguro"));
        locacaoRetorno.setStatus(EnumStatus.valueOf(rs.getString("loc_status")));
        locacaoRetorno.setReserva(rs.getBoolean("loc_reserva"));
        
        return locacaoRetorno;
    }    
    
}
