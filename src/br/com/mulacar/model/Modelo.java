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

public class Modelo {

    private int id;
    private String descricao;
    private EnumStatus status;
    private Marca marca;

    public Modelo() {
    }

    public Modelo(int id, String descricao, EnumStatus status, Marca marca) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.marca = marca;
    }

    public Modelo(String descricao, EnumStatus status, Marca marca) {
        this.descricao = descricao;
        this.status = status;
        this.marca = marca;
    }

    public Modelo(int id) {
        this.id = id;
    }

    public void splitModelo(String combo) {
        String[] vetor = combo.split(" - ");
        id = Integer.parseInt(vetor[0]);
        descricao = vetor[1];
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return id + descricao + status + marca;
    }

}
