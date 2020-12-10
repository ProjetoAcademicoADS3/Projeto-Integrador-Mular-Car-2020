/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.model.Veiculo;
import br.com.mulacar.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author roger
 */
public class LocacaoDal {

    private Connection conexao;

    public LocacaoDal() {
        conexao = Conexao.getConexao();
    }

    public List<Locacao> getAllLocations() throws Exception {
        List<Locacao> listaLocacao = new ArrayList<Locacao>();
        String sql = "SELECT * FROM locacao";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Locacao locacaoRetorno = new Locacao();

                preencherLocacaoRetornoBanco(locacaoRetorno, rs);

                listaLocacao.add(locacaoRetorno);
            }

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de locações\n"
                    + erro.getMessage());
        }
        return listaLocacao;
    }

    public Locacao getLocationById(Locacao locacao) throws Exception {

        Locacao locacaoRetorno = new Locacao();

        String sql = "SELECT * FROM locacao WHERE loc_id = ?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, locacao.getId());

            ResultSet rs = preparedStatement.executeQuery();
          
            if (rs.next()) {

                preencherLocacaoRetornoBanco(locacaoRetorno, rs);

            }

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de locações\n"
                    + erro.getMessage());
        }
        return locacaoRetorno;
    }

    private void preencherLocacaoRetornoBanco(Locacao locacaoRetorno, ResultSet rs) throws Exception {
        locacaoRetorno.setId(rs.getInt("loc_id"));
        ClienteDal cliente = new ClienteDal();
        locacaoRetorno.setCliente(cliente.getClienteById(new Cliente(rs.getInt("loc_cliente_id"))));
        MotoristaDal motorista = new MotoristaDal();
        locacaoRetorno.setMotorista(motorista.getMotoristaById(new Motorista(rs.getInt("loc_motorista_id"))));
        VeiculoDal veiculo = new VeiculoDal();
        locacaoRetorno.setVeiculo(veiculo.getVeiculoById(new Veiculo(rs.getInt("loc_veiculo_id"))));
        UsuarioDal usuario = new UsuarioDal();
        locacaoRetorno.setUsuario(usuario.getUsuarioById(rs.getInt("loc_usuario_cadastro_id")));
        locacaoRetorno.setValorMulta(rs.getBigDecimal("loc_valor_multa"));
        locacaoRetorno.setTanqueCheio(rs.getBoolean("loc_tanque_cheio"));
        locacaoRetorno.setDataRetirada(rs.getDate("loc_data_retirada"));
        locacaoRetorno.setDataDevolucaoPrevista(rs.getDate("loc_data_devolucao_prevista"));
        locacaoRetorno.setKmInicial(rs.getString("loc_km_inicial"));
        locacaoRetorno.setObservacoes(rs.getString("loc_observacoes"));
        locacaoRetorno.setValorAcessorios(rs.getBigDecimal("loc_valores_acessorios"));
        locacaoRetorno.setValorLocacao(rs.getBigDecimal("loc_valor_locacao"));
        locacaoRetorno.setValorCaucao(rs.getBigDecimal("loc_valor_caucao"));
        locacaoRetorno.setValorSeguro(rs.getBigDecimal("loc_valor_seguro"));
        locacaoRetorno.setStatus(EnumStatus.valueOf(rs.getString("loc_status")));
        locacaoRetorno.setReserva(rs.getBoolean("loc_reserva"));
    }
    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formato.format(dtConsulta);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }
}
