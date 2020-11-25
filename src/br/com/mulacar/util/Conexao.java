/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

//APLICADO O PADRÃO DE PROJETO SINGLETON

package br.com.mulacar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection conexao = null;

    private Conexao() {
    }

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://192.168.0.37:5432/mulacar";
                String usuario = "aires";
                String password = "aires";

                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usuario, password);
//                JOptionPane.showMessageDialog(null, "Conectado com Sucesso !");

            } catch (ClassNotFoundException erro) {
                erro.printStackTrace();

            } catch (SQLException erro) {
                erro.printStackTrace();
            }
        }
        return conexao;
    }
}
