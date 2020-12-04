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
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumTipoCliente;
import java.util.Date;
import java.util.Objects;

public class Motorista {

    private int id;
    private String razaoSocial;
    private String nome;
    private String cpf;
    private String rg;
    private String orgaoEmissor;
    private String numeroCnh;
    private Date dataValidadeCnh;
    private EnumCategoriaCnh categoriaCnh;
    private String pathImagem;
    private EnumTipoCliente tipoCliente;

    public Motorista(int id) {
        this.id = id;
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

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public EnumTipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(EnumTipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "Motorista{" + "id=" + id + ", razaoSocial=" + razaoSocial + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", orgaoEmissor=" + orgaoEmissor + ", numeroCnh=" + numeroCnh + ", dataValidadeCnh=" + dataValidadeCnh + ", categoriaCnh=" + categoriaCnh + ", pathImagem=" + pathImagem + ", tipoCliente=" + tipoCliente + '}';
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
