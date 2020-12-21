/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author roger
 */
public class Devolucao {

    private int id;
    private Date dataDevolucaoRealizada;
    private boolean tanqueCheio;
    private int kmFinal;
    private String observacao;
    private BigDecimal valorMulta;
    private BigDecimal valorCombustivel;
    private BigDecimal valorPago;
    private Locacao locacao;
    private Usuario usuario;

    public Devolucao() {
    }

    public Devolucao(int id) {
        this.id = id;
    }

    public Devolucao(Date dataDevolucao, boolean tanqueCheio, int kmFinal, String observacao, BigDecimal valorMulta, BigDecimal valorCombustivel, BigDecimal valorPago, Locacao locacao, Usuario usuario) {
        this.dataDevolucaoRealizada = dataDevolucao;
        this.tanqueCheio = tanqueCheio;
        this.kmFinal = kmFinal;
        this.observacao = observacao;
        this.valorMulta = valorMulta;
        this.valorCombustivel = valorCombustivel;
        this.valorPago = valorPago;
        this.locacao = locacao;
        this.usuario = usuario;
    }

    public Devolucao(int id, Date dataDevolucao, boolean tanqueCheio, int kmFinal, String observacao, BigDecimal valorMulta, BigDecimal valorCombustivel, BigDecimal valorPago, Locacao locacao, Usuario usuario) {
        this.id = id;
        this.dataDevolucaoRealizada = dataDevolucao;
        this.tanqueCheio = tanqueCheio;
        this.kmFinal = kmFinal;
        this.observacao = observacao;
        this.valorMulta = valorMulta;
        this.valorCombustivel = valorCombustivel;
        this.valorPago = valorPago;
        this.locacao = locacao;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

    public void setDataDevolucaoRealizada(Date dataDevolucaoRealizada) {
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
    }

    public boolean isTanqueCheio() {
        return tanqueCheio;
    }

    public void setTanqueCheio(boolean tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
    }

    public int getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(int kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorCombustivel() {
        return valorCombustivel;
    }

    public void setValorCombustivel(BigDecimal valorCombustivel) {
        this.valorCombustivel = valorCombustivel;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Devolucao{" + "id=" + id + ", dataDevolucao=" + dataDevolucaoRealizada + ", tanqueCheio=" + tanqueCheio + ", kmFinal=" + kmFinal + ", observacao=" + observacao + ", valorMulta=" + valorMulta + ", valorCombustivel=" + valorCombustivel + ", valorPago=" + valorPago + ", locacao=" + locacao + ", usuario=" + usuario + '}';
    }
    

}
