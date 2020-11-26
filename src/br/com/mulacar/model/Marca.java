/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumStatus;

public class Marca {

    private int id;
    private String descricao;
    private EnumStatus status;

    public Marca() {
    }

    public Marca(int id, String descricao, EnumStatus status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public Marca(String descricao, EnumStatus status) {
        this.descricao = descricao;
        this.status = status;
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

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + descricao + status;
    }

}
