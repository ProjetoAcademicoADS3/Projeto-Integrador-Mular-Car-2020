/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumSituacaoVeiculo;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumTipoCombustivel;
import br.com.mulacar.enumeration.EnumTipoVeiculo;
import java.math.BigDecimal;

/**
 *
 * @author roger
 */
public class Veiculo {

    private int id;
    private String placa;
    private int anoFabricacao;
    private int anoModelo;
    private EnumTipoCombustivel tipoCombustivel;
    private String renavan;
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private EnumTipoVeiculo tipo;
    private EnumStatus status;
    private int numPassageiros;
    private long km;
    private Categoria categoria;
    private Modelo modelo;
    private EnumSituacaoVeiculo situacao;

    public Veiculo() {
    }

    public Veiculo(int id) {
        this.id = id;
    }
    
    public Veiculo(int id, String placa, int anoFabricacao, int anoModelo,
            EnumTipoCombustivel tipoCombustivel, String renavan,
            BigDecimal precoCompra, BigDecimal precoVenda,
            EnumTipoVeiculo tipo, EnumStatus status, int numPassageiros,
            long km, Categoria categoria, Modelo modelo,
            EnumSituacaoVeiculo situacao) {
        this.id = id;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.tipoCombustivel = tipoCombustivel;
        this.renavan = renavan;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.tipo = tipo;
        this.status = status;
        this.numPassageiros = numPassageiros;
        this.km = km;
        this.categoria = categoria;
        this.modelo = modelo;
        this.situacao = situacao;
    }

    public Veiculo(String placa, int anoFabricacao, int anoModelo,
            EnumTipoCombustivel tipoCombustivel, String renavan,
            BigDecimal precoCompra, BigDecimal precoVenda, EnumTipoVeiculo tipo,
            EnumStatus status, int numPassageiros, long km, Categoria categoria,
            Modelo modelo, EnumSituacaoVeiculo situacao) {
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.tipoCombustivel = tipoCombustivel;
        this.renavan = renavan;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.tipo = tipo;
        this.status = status;
        this.numPassageiros = numPassageiros;
        this.km = km;
        this.categoria = categoria;
        this.modelo = modelo;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public EnumTipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(EnumTipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public EnumTipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public EnumSituacaoVeiculo getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacaoVeiculo situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return id + "\n"
                + placa + "\n"
                + anoFabricacao + "\n"
                + anoModelo + "\n"
                + tipoCombustivel + "\n"
                + renavan + "\n"
                + precoCompra + "\n"
                + precoVenda + "\n"
                + tipo + "\n"
                + status + "\n"
                + numPassageiros + "\n"
                + km + "\n"
                + categoria + "\n"
                + modelo + "\n"
                + situacao;
    }

}
