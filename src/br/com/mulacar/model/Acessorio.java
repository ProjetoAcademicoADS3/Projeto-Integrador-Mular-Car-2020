/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
//06/12/2020 2058
package br.com.mulacar.model;
import br.com.mulacar.enumeration.EnumStatus;
import java.math.BigDecimal;

public class Acessorio{

    //ATRIBUTOS
    private int         id;
    private String      descricao;
    private EnumStatus  status;
    private BigDecimal  valor;
    
    //CONSTRUTORES

    public Acessorio() {
    }

    public Acessorio(String descricao) {
        this.descricao  = descricao;
    }

    public Acessorio(String descricao, EnumStatus status, BigDecimal valor) {
        this.descricao  = descricao;
        this.status     = status;
        this.valor      = valor;
    }

    public Acessorio(int id, String descricao, EnumStatus status, BigDecimal valor) {
        this.id         = id;
        this.descricao  = descricao;
        this.status     = status;
        this.valor      = valor;
    }

    @Override
    public String toString() {
        return "Acessorios{" + "id=" + id + ", descricao=" + descricao + ", status=" + status + ", valor=" + valor + '}';
    }
    
    public void splitCategoria(String combo)throws Exception{
        String[] str    = combo.split(" - ");
        this.id         = Integer.parseInt(str[0]);
        this.descricao  = str[1];
    }

    //GETTERs e SETTERs

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
