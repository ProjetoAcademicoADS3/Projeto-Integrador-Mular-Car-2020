/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.enumeration.EnumSituacaoVeiculo;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumTipoCombustivel;
import br.com.mulacar.enumeration.EnumTipoVeiculo;
import br.com.mulacar.model.Categoria;
import br.com.mulacar.model.Veiculo;
import java.sql.Connection;
import br.com.mulacar.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author roger
 */
public class VeiculoDal {

    private Connection conexao;

    public VeiculoDal() {
        conexao = Conexao.getConexao();
    }

    public void addVeiculo(Veiculo veic) throws Exception {

        String sql = "INSERT INTO veiculo ("
                + "vei_placa,"
                + "vei_ano_fabricacao,"
                + "vei_ano_modelo,"
                + "vei_tipo_combustivel,"
                + "vei_renavan,"
                + "vei_preco_compra,"
                + "vei_preco_venda,"
                + "vei_tipo,"
                + "vei_status,"
                + "vei_num_passageiro,"
                + "vei_km,"
                + "vei_categoria_id,"
                + "vei_modelo_id,"
                + "vei_situacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, veic.getPlaca().trim());
            preparedStatement.setInt(2, veic.getAnoFabricacao());
            preparedStatement.setInt(3, veic.getAnoModelo());
            preparedStatement.setString(4, veic.getTipoCombustivel().toString());
            preparedStatement.setString(5, veic.getRenavan());
            preparedStatement.setBigDecimal(6, veic.getPrecoCompra());
            preparedStatement.setBigDecimal(7, veic.getPrecoVenda());
            preparedStatement.setString(8, veic.getTipo().toString());
            preparedStatement.setString(9, veic.getStatus().toString());
            preparedStatement.setInt(10, veic.getNumPassageiros());
            preparedStatement.setLong(11, veic.getKm());
            preparedStatement.setInt(12, veic.getCategoria().getId());
            preparedStatement.setInt(13, veic.getModelo().getId());
            preparedStatement.setString(14, veic.getSituacao().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteVeiculo(int id) throws Exception {
        String sql = "DELETE FROM veiculo WHERE vei_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void updateVeiculo(Veiculo veic) throws Exception {
        String sql = "UPDATE veiculo SET "
                + "vei_placa=?,"
                + "vei_ano_fabricacao=?,"
                + "vei_ano_modelo=?,"
                + "vei_tipo_combustivel=?,"
                + "vei_renavan=?,"
                + "vei_preco_compra=?,"
                + "vei_preco_venda=?,"
                + "vei_tipo=?,"
                + "vei_status=?,"
                + "vei_num_passageiro=?,"
                + "vei_km=?,"
                + "vei_categoria_id=?,"
                + "vei_modelo_id=?,"
                + "vei_situacao=? WHERE vei_id=?";
        try {
            PreparedStatement preparedStatement
                    = conexao.prepareStatement(sql);

            preparedStatement.setString(1, veic.getPlaca().trim());
            preparedStatement.setInt(2, veic.getAnoFabricacao());
            preparedStatement.setInt(3, veic.getAnoModelo());
            preparedStatement.setString(4, veic.getTipoCombustivel().toString());
            preparedStatement.setString(5, veic.getRenavan());
            preparedStatement.setBigDecimal(6, veic.getPrecoCompra());
            preparedStatement.setBigDecimal(7, veic.getPrecoVenda());
            preparedStatement.setString(8, veic.getTipo().toString());
            preparedStatement.setString(9, veic.getStatus().toString());
            preparedStatement.setInt(10, veic.getNumPassageiros());
            preparedStatement.setLong(11, veic.getKm());
            preparedStatement.setInt(12, veic.getCategoria().getId());
            preparedStatement.setInt(13, veic.getModelo().getId());
            preparedStatement.setString(14, veic.getSituacao().toString());
            preparedStatement.setInt(15, veic.getId());
            preparedStatement.executeUpdate();

        } catch (Exception erro) {
            throw erro;
        }
    }

    public List<Veiculo> getAllVeiculo() throws Exception {
        List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        String sql = "SELECT * FROM veiculo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Veiculo veic = new Veiculo();
                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

                listaVeiculos.add(veic);
            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao consultar "
                    + "os registros de veiculos\n"
                    + erro.getMessage());
        }

        return listaVeiculos;
    }

    public Veiculo getVeiculoByPlaca(String placa) throws Exception {
        Veiculo veic = new Veiculo();
        String sql = "SELECT * from veiculo WHERE vei_placa=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, placa);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de contratos\n"
                    + erro.getMessage());
        }
        return veic;
    }
    
    public Veiculo getVeiculoById(Veiculo veiculo) throws Exception {
        Veiculo veic = new Veiculo();
        String sql = "SELECT * from veiculo WHERE vei_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, veiculo.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de contratos\n"
                    + erro.getMessage());
        }
        return veic;
    }

    
    public List<Veiculo> getVeiculoByCategoria(int categoria, String situacao) throws Exception {
        List<Veiculo> lista = new ArrayList<Veiculo>();
        String sql = "SELECT * from veiculo WHERE vei_categoria_id = ? AND vei_situacao = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, categoria);
            preparedStatement.setString(2, situacao);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();

                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

                lista.add(veic);

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de contratos\n"
                    + erro.getMessage());
        }
        return lista;
    }
    
    public List<Veiculo> getVeiculoByCategoria(int categoria) throws Exception {
        List<Veiculo> lista = new ArrayList<Veiculo>();
        String sql = "SELECT * from veiculo WHERE vei_categoria_id = ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, categoria);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();

                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

                lista.add(veic);

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de contratos\n"
                    + erro.getMessage());
        }
        return lista;
    }
    
    public List<Veiculo> getVeiculoBySituacao(String situacao) throws Exception {
        List<Veiculo> lista = new ArrayList<Veiculo>();
        String sql = "SELECT * from veiculo WHERE vei_situacao = ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, situacao);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Veiculo veic = new Veiculo();

                veic.setId(rs.getInt("vei_id"));
                veic.setPlaca(rs.getString("vei_placa"));
                veic.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veic.setAnoModelo(rs.getInt("vei_ano_modelo"));
                veic.setTipoCombustivel(EnumTipoCombustivel.valueOf(rs.getString("vei_tipo_combustivel")));
                veic.setRenavan(rs.getString("vei_renavan"));
                veic.setPrecoCompra(rs.getBigDecimal("vei_preco_compra"));
                veic.setPrecoVenda(rs.getBigDecimal("vei_preco_venda"));
                veic.setTipo(EnumTipoVeiculo.valueOf(rs.getString("vei_tipo")));
                veic.setStatus(EnumStatus.valueOf(rs.getString("vei_status")));
                veic.setNumPassageiros(rs.getInt("vei_num_passageiro"));
                veic.setKm(rs.getLong("vei_km"));
                CategoriaDal catDal = new CategoriaDal();
                veic.setCategoria(catDal.getCategoriaById(new Categoria(rs.getInt("vei_categoria_id"))));
                ModeloDal modDal = new ModeloDal();
                veic.setModelo(modDal.getModeloById(rs.getInt("vei_modelo_id")));
                veic.setSituacao(EnumSituacaoVeiculo.valueOf(rs.getString("vei_situacao")));

                lista.add(veic);

            }
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao buscar este registro de contratos\n"
                    + erro.getMessage());
        }
        return lista;
    }

    public ArrayList sourceVeiculo(String dados) throws Exception {
        String textoDigitado = dados;
        ArrayList<Veiculo> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = false;
        for (Veiculo veic : getAllVeiculo()) {

            if (veic.getPlaca().toLowerCase().trim().contains(textoDigitado)
                    || veic.getRenavan().trim().contains(textoDigitado)) {

                resultadoDaPesquisa.add(veic);

                vdd = true;
            }
        }
        if (!vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }

    public void ordenaListaVeiculos(List<Veiculo> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getCategoria().getDescricao().compareToIgnoreCase(lista.get(j).getCategoria().getDescricao()) >= 0) {
                    Veiculo temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }

    }

}
