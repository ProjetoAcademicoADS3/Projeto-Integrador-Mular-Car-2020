/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumStatus;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class Locacao {

    private int id;
    private Cliente cliente;
    private Motorista motorista;
    private Veiculo veiculo;
    private Usuario usuario;
    private BigDecimal valorMulta;
    private boolean tanqueCheio;
    private Date dataRetirada;
    private Date dataDevolucaoPrevista;
    private String kmInicial;
    private String observacoes;
    private BigDecimal valorTotalAcessorios;
    private BigDecimal valorLocacao;
    private BigDecimal valorSeguro;
    private BigDecimal valorCaucao;
    private EnumStatus status;
    private boolean reserva;

    public Locacao() {
    }

    public Locacao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public boolean isTanqueCheio() {
        return tanqueCheio;
    }

    public void setTanqueCheio(boolean tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public String getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(String kmInicial) {
        this.kmInicial = kmInicial;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getValorTotalAcessorios() {
        return valorTotalAcessorios;
    }

    public void setValorTotalAcessorios(BigDecimal valorTotalAcessorios) {
        this.valorTotalAcessorios = valorTotalAcessorios;
    }

    public BigDecimal getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(BigDecimal valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public BigDecimal getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(BigDecimal valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public BigDecimal getValorCaucao() {
        return valorCaucao;
    }

    public void setValorCaucao(BigDecimal valorCaucao) {
        this.valorCaucao = valorCaucao;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Locacao{" + "id=" + id + ", cliente=" + cliente + ", motorista=" + motorista + ", veiculo=" + veiculo + ", usuario=" + usuario + ", valorMulta=" + valorMulta + ", tanqueCheio=" + tanqueCheio + ", dataRetirada=" + dataRetirada + ", dataDevolucaoPrevista=" + dataDevolucaoPrevista + ", kmInicial=" + kmInicial + ", observacoes=" + observacoes + ", valorTotalAcessorios=" + valorTotalAcessorios + ", valorLocacao=" + valorLocacao + ", valorSeguro=" + valorSeguro + ", valorCaucao=" + valorCaucao + ", status=" + status + ", reserva=" + reserva + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        final Locacao other = (Locacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
