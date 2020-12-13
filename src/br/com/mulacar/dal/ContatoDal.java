/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.dal;

import br.com.mulacar.bll.ClienteBll;
import br.com.mulacar.bll.MotoristaBll;
import br.com.mulacar.enumeration.EnumTipoTelefone;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Contato;
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

public class ContatoDal {

    private Connection conexao;

    private ClienteBll clienteBll;

    private MotoristaBll motoristaBll;

    public ContatoDal() {
        conexao = Conexao.getConexao();
        clienteBll = new ClienteBll();

    }

    public void addContato(Contato contato) throws Exception {
        String sql = "INSERT INTO contato (con_tipo, con_telefone, con_email, "
                + "con_cliente_id, con_motorista_id) "
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, contato.getTipoTelefone().toString());
            preparedStatement.setString(2, contato.getNumero());
            preparedStatement.setString(3, contato.getEmail());

            if (!UtilObjetos.ehNuloOuVazio(contato.getCliente())) {
                preparedStatement.setInt(4, contato.getCliente().getId());
                preparedStatement.setNull(5, Types.INTEGER);
            }

            if (!UtilObjetos.ehNuloOuVazio(contato.getMotorista())) {
                preparedStatement.setNull(4, Types.INTEGER);
                preparedStatement.setInt(5, contato.getMotorista().getId());
            }

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
    }

    public void addContatos(List<Contato> contatos) throws Exception {
        try {

            conexao.setAutoCommit(false);

            String sql = "INSERT INTO contato (con_tipo, con_telefone, con_email, "
                    + "con_cliente_id, con_motorista_id) "
                    + "VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            for (Contato contato : contatos) {

                preparedStatement.setString(1, contato.getTipoTelefone().toString());
                preparedStatement.setString(2, contato.getNumero());
                preparedStatement.setString(3, contato.getEmail());

                if (!UtilObjetos.ehNuloOuVazio(contato.getCliente())) {
                    preparedStatement.setInt(4, contato.getCliente().getId());
                    preparedStatement.setNull(5, Types.INTEGER);
                }

                if (!UtilObjetos.ehNuloOuVazio(contato.getMotorista())) {
                    preparedStatement.setNull(4, Types.INTEGER);
                    preparedStatement.setInt(5, contato.getMotorista().getId());
                }

                preparedStatement.addBatch();
            }

            int[] count = preparedStatement.executeBatch();

            conexao.commit();

        } catch (SQLException e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e.getNextException());
            throw e;
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
    }

    public void updateContato(Contato contato) throws Exception {

        String sql = "UPDATE contato "
                + "SET con_tipo = ?, "
                + "con_telefone = ?, "
                + "con_email    = ? "
                + "con_cliente_id = ? "
                + "con_motorista_id = ? "
                + "WHERE con_id = ? ";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, contato.getTipoTelefone().toString());
            preparedStatement.setString(2, contato.getNumero());
            preparedStatement.setString(3, contato.getEmail());

            if (!UtilObjetos.ehNuloOuVazio(contato.getCliente())) {
                preparedStatement.setInt(4, contato.getCliente().getId());
            }

            if (!UtilObjetos.ehNuloOuVazio(contato.getMotorista())) {
                preparedStatement.setInt(5, contato.getMotorista().getId());
            }

            preparedStatement.executeUpdate();

        } catch (SQLException erro) {
            throw erro;
        }
    }

    public void deleteContato(Contato contato) throws Exception {
        String sql = "DELETE FROM contato WHERE con_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, contato.getId());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
    }

    public List<Contato> getAllContatos() throws Exception {
        List<Contato> listaContatos = new ArrayList<>();

        String sql = "SELECT * FROM contato";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Contato contato = new Contato();

                preencherContatoRetornoBanco(contato, rs);

                listaContatos.add(contato);
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw new Exception("Erro ao listar contatos" + e.getMessage());
        }
        return listaContatos;
    }

    public Contato getContatoById(Contato contato) throws Exception {
        Contato contatoRetorno = null;

        String sql = "SELECT * FROM contato WHERE con_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, contato.getId());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherContatoRetornoBanco(contatoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contato;
    }

    public Contato getContatoByCliente(Contato contato) throws Exception {
        Contato contatoRetorno = new Contato();

        String sql = "SELECT * FROM contato WHERE con_cliente_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, contato.getCliente().getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherContatoRetornoBanco(contatoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contatoRetorno;
    }

    public Contato getContatoByClienteId(Cliente cliente) throws Exception {
        Contato contato = new Contato();

        String sql = "SELECT * FROM contato WHERE con_cliente_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cliente.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                contato.setId(rs.getInt("con_id"));
                contato.setTipoTelefone(EnumTipoTelefone.valueOf(rs.getString("con_tipo")));
                contato.setNumero(rs.getString("con_telefone"));
                contato.setEmail(rs.getString("con_email"));
                contato.setCliente(new Cliente(rs.getInt("con_cliente_id")));
                contato.setMotorista(new Motorista(rs.getInt("con_motorista_id")));
                
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contato;
    }
    
    public Contato getContatoByMotoristaId(Motorista motorista) throws Exception {
        Contato contato = new Contato();

        String sql = "SELECT * FROM contato WHERE con_motorista_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, motorista.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                contato.setId(rs.getInt("con_id"));
                contato.setTipoTelefone(EnumTipoTelefone.valueOf(rs.getString("con_tipo")));
                contato.setNumero(rs.getString("con_telefone"));
                contato.setEmail(rs.getString("con_email"));
                contato.setCliente(new Cliente(rs.getInt("con_cliente_id")));
                contato.setMotorista(new Motorista(rs.getInt("con_motorista_id")));
                
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contato;
    }

    public Contato getContatoByMotorista(Contato contato) throws Exception {
        Contato contatoRetorno = null;

        String sql = "SELECT * FROM contato WHERE con_motorista_id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, contato.getMotorista().getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherContatoRetornoBanco(contatoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contatoRetorno;
    }

    public Contato getContatoByEmail(Contato contato) throws Exception {
        Contato contatoRetorno = null;

        String sql = "SELECT * FROM contato WHERE con_email like ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, contato.getEmail());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                preencherContatoRetornoBanco(contatoRetorno, rs);
            }
        } catch (Exception e) {
            Logger.getLogger(Contato.class.getName()).log(Level.SEVERE, "ContatoDal - ", e);
            throw e;
        }
        return contatoRetorno;
    }

    private void preencherContatoRetornoBanco(Contato contatoRetorno, ResultSet rs) throws Exception {
        contatoRetorno = new Contato();
        contatoRetorno.setId(rs.getInt("con_id"));
        contatoRetorno.setTipoTelefone(EnumTipoTelefone.valueOf(rs.getString("con_tipo")));
        contatoRetorno.setNumero(rs.getString("con_telefone"));
        contatoRetorno.setEmail(rs.getString("con_email"));

        int idCliente = rs.getInt("con_cliente_id");
        if (!UtilObjetos.ehNuloOuVazio(idCliente)) {
            Cliente cliente = clienteBll.getClientePorId(new Cliente(idCliente));
            contatoRetorno.setCliente(cliente);
        }

        int idMotorista = rs.getInt("con_motorista_id");
        if (!UtilObjetos.ehNuloOuVazio(idMotorista)) {
            Motorista motorista = motoristaBll.getMotoristaPorId(new Motorista(idMotorista));
            contatoRetorno.setMotorista(motorista);
        }

    }

}
