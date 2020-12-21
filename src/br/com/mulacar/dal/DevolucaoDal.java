/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.model.Devolucao;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class DevolucaoDal {

    private Connection conexao;

    public DevolucaoDal() {
        conexao = Conexao.getConexao();
    }

    public void addDevolution(Devolucao devolucao) throws Exception {

        String sql = "INSERT INTO devolucao ("
                + "dev_data_devolucao_realizada,"
                + "dev_tanque_cheio,"
                + "dev_km_final,"
                + "dev_observacao,"
                + "dev_valor_multa,"
                + "dev_valor_combustivel,"
                + "dev_valor_pago,"
                + "dev_locacao_id,"
                + "dev_usuario_cadastro_id) VALUES (?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(devolucao.getDataDevolucaoRealizada().getTime()));
            preparedStatement.setBoolean(2, devolucao.isTanqueCheio());
            preparedStatement.setInt(3, devolucao.getKmFinal());
            preparedStatement.setString(4, devolucao.getObservacao());
            preparedStatement.setBigDecimal(5, devolucao.getValorMulta());
            preparedStatement.setBigDecimal(6, devolucao.getValorCombustivel());
            preparedStatement.setBigDecimal(7, devolucao.getValorPago());
            preparedStatement.setInt(8, devolucao.getLocacao().getId());
            preparedStatement.setInt(9, devolucao.getUsuario().getId());

            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw erro;
        }
    }

    public void deleteDevolution(Devolucao devolucao) throws Exception {
        String sql = "DELETE FROM devolucao WHERE dev_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, devolucao.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void updateDevolution(Devolucao devolucao) throws Exception {
        String sql = "UPDATE devolucao SET "
                + "dev_id=?,"
                + "dev_data_devolucao_realizada=?,"
                + "dev_tanque_cheio=?,"
                + "dev_km_final=?,"
                + "dev_observacao=?,"
                + "dev_valor_multa=?,"
                + "dev_valor_combustivel=?,"
                + "dev_valor_pago=?,"
                + "dev_locacao_id=?,"
                + "dev_usuario_cadastro_id=? WHERE dev_id=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(devolucao.getDataDevolucaoRealizada().getTime()));
            preparedStatement.setBoolean(2, devolucao.isTanqueCheio());
            preparedStatement.setInt(3, devolucao.getKmFinal());
            preparedStatement.setString(4, devolucao.getObservacao());
            preparedStatement.setBigDecimal(5, devolucao.getValorMulta());
            preparedStatement.setBigDecimal(6, devolucao.getValorCombustivel());
            preparedStatement.setBigDecimal(7, devolucao.getValorPago());
            preparedStatement.setInt(8, devolucao.getLocacao().getId());
            preparedStatement.setInt(9, devolucao.getUsuario().getId());
            preparedStatement.setInt(10, devolucao.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw erro;
        }

    }

    public List<Devolucao> getAllDevolution() throws Exception {
        List<Devolucao> listaDevolucoes = new ArrayList<Devolucao>();
        String sql = "SELECT * FROM devolucao";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Devolucao devolucao = new Devolucao();
                devolucao.setId(rs.getInt("dev_id"));
                devolucao.setDataDevolucaoRealizada(rs.getDate("dev_data_devolucao_realizada"));
                devolucao.setTanqueCheio(rs.getBoolean("dev_tanque_cheio"));
                devolucao.setKmFinal(rs.getInt("dev_km_final"));
                devolucao.setObservacao(rs.getString("dev_observacao"));
                devolucao.setValorMulta(rs.getBigDecimal("dev_valor_multa"));
                devolucao.setValorCombustivel(rs.getBigDecimal("dev_valor_combustivel"));
                devolucao.setValorPago(rs.getBigDecimal("dev_valor_pago"));
                LocacaoDal locacaoDal = new LocacaoDal();
                devolucao.setLocacao(locacaoDal.getLocacaoById(new Locacao(rs.getInt("dev_locacao_id"))));
                UsuarioDal usuarioDal = new UsuarioDal();
                devolucao.setUsuario(usuarioDal.getUsuarioById(rs.getInt("dev_usuario_cadastro_id")));

                listaDevolucoes.add(devolucao);
            }

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de devoluções\n"
                    + erro.getMessage());
        }
        return listaDevolucoes;
    }

    public Devolucao getDevolutionById(Devolucao devol) throws Exception {
        Devolucao devolucao = new Devolucao();
        String sql = "SELECT * FROM devolucao WHERE dev_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, devol.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                devolucao.setId(rs.getInt("dev_id"));
                devolucao.setDataDevolucaoRealizada(rs.getDate("dev_data_devolucao_realizada"));
                devolucao.setTanqueCheio(rs.getBoolean("dev_tanque_cheio"));
                devolucao.setKmFinal(rs.getInt("dev_km_final"));
                devolucao.setObservacao(rs.getString("dev_observacao"));
                devolucao.setValorMulta(rs.getBigDecimal("dev_valor_multa"));
                devolucao.setValorCombustivel(rs.getBigDecimal("dev_valor_combustivel"));
                devolucao.setValorPago(rs.getBigDecimal("dev_valor_pago"));
                LocacaoDal locacaoDal = new LocacaoDal();
                devolucao.setLocacao(locacaoDal.getLocacaoById(new Locacao(rs.getInt("dev_locacao_id"))));
                UsuarioDal usuarioDal = new UsuarioDal();
                devolucao.setUsuario(usuarioDal.getUsuarioById(rs.getInt("dev_usuario_cadastro_id")));

            }

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de devoluções\n"
                    + erro.getMessage());
        }
        return devolucao;
    }

    public void ordenaListaDevolution(List<Devolucao> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getLocacao().getCliente().getNome()
                        .compareToIgnoreCase(lista.get(j).getLocacao().getCliente().getNome()) >= 0) {
                    Devolucao temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }

}
