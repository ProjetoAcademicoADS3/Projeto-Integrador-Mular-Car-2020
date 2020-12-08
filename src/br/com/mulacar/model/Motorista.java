/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumCategoriaCnh;
import java.util.Date;
import java.util.Objects;

public class Motorista {

    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String orgaoEmissor;
    private String numeroCnh;
    private Date dataValidadeCnh;
    private String pathImagemCnh;
    private EnumCategoriaCnh categoriaCnh;

    public Motorista() {
    }

    public Motorista(int id) {
        this.id = id;
    }

    public Motorista(String string) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public Date getDataValidadeCnh() {
        return dataValidadeCnh;
    }

    public void setDataValidadeCnh(Date dataValidadeCnh) {
        this.dataValidadeCnh = dataValidadeCnh;
    }

    public EnumCategoriaCnh getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setCategoriaCnh(EnumCategoriaCnh categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public String getPathImagemCnh() {
        return pathImagemCnh;
    }

    public void setPathImagemCnh(String pathImagemCnh) {
        this.pathImagemCnh = pathImagemCnh;
    }

    @Override
    public String toString() {
        return "Motorista{" + "id=" + id + ", razaoSocial=" + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", orgaoEmissor=" + orgaoEmissor + ", numeroCnh=" + numeroCnh + ", dataValidadeCnh=" + dataValidadeCnh + ", categoriaCnh=" + categoriaCnh + ", pathImagemCnh=" + pathImagemCnh + ", tipoCliente=";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Motorista other = (Motorista) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
