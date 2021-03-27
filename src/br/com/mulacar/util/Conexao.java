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

public class Conexao {

    private static Connection conexao = null;

    private Conexao() {
    }

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/mulacar";
                String usuario = "postgres";
                String password = "131726";

                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usuario, password);
                String status = "Conetado";
                System.out.println("Status da conexão: " + status);

            } catch (ClassNotFoundException erro) {
                erro.printStackTrace();

            } catch (SQLException erro) {
                System.out.println("Não conectado");
                erro.printStackTrace();
            }
        }
        return conexao;
    }
}
