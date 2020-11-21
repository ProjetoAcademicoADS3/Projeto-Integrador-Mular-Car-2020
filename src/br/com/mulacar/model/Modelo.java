/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.model;


public class Modelo {

    private int id;
    private String descricao;

    public Modelo() {
    }

    public Modelo(String descricao) {
        this.descricao = descricao;
    }
    
    public Modelo(int id,String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public void splitModelo(String combo)throws Exception{
        String[] str = combo.split(" - ");
        this.id = Integer.parseInt(str[0]);
        this.descricao = str[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return id + descricao;
    }

}
