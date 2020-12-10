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
import br.com.mulacar.enumeration.EnumTipoCliente;

public class Cliente {

    private int id;
    private String razaoSocial;
    private String nomeFantasia;
    private String nome;
    private EnumStatus status;
    private String cpfCnpj;
    private String rg;
    private String orgaoEmissor;
    private EnumTipoCliente tipoCliente;
    private Endereco endereco;
    private Contato contato;

    public Cliente() {
    }
    
    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(String razaoSocial, String nomeFantasia, String nome, EnumStatus status, String cpfCnpj, String rg, String orgaoEmissor, EnumTipoCliente tipoCliente, Endereco endereco, Contato contato) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.nome = nome;
        this.status = status;
        this.cpfCnpj = cpfCnpj;
        this.rg = rg;
        this.orgaoEmissor = orgaoEmissor;
        this.tipoCliente = tipoCliente;
        this.endereco = endereco;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public EnumTipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(EnumTipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", nome=" + nome + ", status=" + status + ", cpfCnpj=" + cpfCnpj + ", rg=" + rg + ", orgaoEmissor=" + orgaoEmissor + ", tipoCliente=" + tipoCliente + ", endereco=" + endereco + ", contato=" + contato + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
