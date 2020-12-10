/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumPerfil;
import br.com.mulacar.enumeration.EnumStatus;
import java.util.Date;

public class Usuario {

    private int         id;
    private String      nome;
    private String      cpf;
    private String      email;
    private String      senha;
    private EnumPerfil  perfil;
    private EnumStatus  status;
    private Date dataCadastro;

    public Usuario() {
    }

    public Usuario(int id) {
        this.id = id;
    }
    
    public Usuario(String nome, String cpf, String email, String senha, EnumStatus status, EnumPerfil perfil) {
        this.nome   = nome;
        this.cpf    = cpf;
        this.email  = email;
        this.senha  = senha;
        this.perfil = perfil;
        this.status = status;
    }

    public Usuario(int id, String nome, String cpf, String email, String senha, EnumPerfil perfil, EnumStatus status, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status;
        this.dataCadastro = dataCadastro;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(EnumPerfil perfil) {
        this.perfil = perfil;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha + ", perfil=" + perfil + ", status=" + status + ", dataCadastro=" + dataCadastro + '}';
    }
    
}
