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
    private BigDecimal valorTotalLocacao;
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

    public BigDecimal getValorTotalLocacao() {
        return valorTotalLocacao;
    }

    public void setValorTotalLocacao(BigDecimal valorTotalLocacao) {
        this.valorTotalLocacao = valorTotalLocacao;
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
         String contrato = "\t\t\t\tCONTRATO DE LOCAÇÃO DE VEÍCULO\n"
                + "\n\t1. Partes Contratantes\n\n"
                + "\t1.1 LOCADORA DE VEICULOS\n\n\tMULACAR, sediada na cidade de Goiânia,"
                + "estado de Goiás, inscrita no CNPJ sob o nº 62.566.006/0001-88, "
                + "empresa esta que opera sob licenciamento, devidamente identifi"
                + "cada no anverso deste Contrato de Locação de Veículos.\n\n"
                + "\t1.2 LOCATÁRIO\n\n\t" + getCliente().getNome().toUpperCase() + " portador do CPF/CNPJ nº " + getCliente().getCpfCnpj()
                + ", portador do RG " + getCliente().getRg() + ", orgão emissor: " + getCliente().getOrgaoEmissor().toUpperCase()
                + ", residente nesta cidade de " /*+ loc.getCliente().getEndereco().getCidade() + " estado de "  
                + loc.getCliente().getEndereco().getUf() + " à rua " + loc.getCliente().getEndereco().getRua() 
                + " nº " + loc.getCliente().getEndereco().getNumero() + " bairro " + loc.getCliente().getEndereco().getBairro() 
                + " complemento " + loc.getCliente().getEndereco().getComplemento() + " cep " + loc.getCliente().getEndereco().getCep()*/
                + ".\n\n\t2. Do Objeto do Contrato\n\n"
                + "\t2.1 Veiculo de placa " + getVeiculo().getPlaca().toUpperCase() + ", ano de fabricação " + getVeiculo().getAnoFabricacao()
                + ", ano modelo " + getVeiculo().getAnoModelo() + ", tipo de combustível " + getVeiculo().getTipoCombustivel()
                + ", número do renavam " + getVeiculo().getRenavan() + ", tipo do veículo " + getVeiculo().getTipo()
                + ", categoria " + getVeiculo().getCategoria().getDescricao() + ", modelo " + getVeiculo().getModelo().getDescricao().toUpperCase()
                + ", marca " + getVeiculo().getModelo().getMarca().getDescricao().toUpperCase() + ", km do veículo na data de retirada "
                + getVeiculo().getKm() + ",Observações: o veículo foi entregue ao cliente/motorista com o tanque cheio ? " + isTanqueCheio() +".\n\n"
                + "\t3. Do motorista\n\n"
                + "\t3.1 " + getMotorista().getNome().toUpperCase() + ", portador do CPF " + getMotorista().getCpf()
                + ", RG " + getMotorista().getRg() + ", orgão emissor " + getMotorista().getOrgaoEmissor().toUpperCase()
                + ", CNH de número " + getMotorista().getNumeroCnh() + ", com data de validade " + getMotorista().getDataValidadeCnh()
                + ", categoria " + getMotorista().getCategoriaCnh() + ".\n\n"
                + "\t4 Do valor da locação\n\n"
                + "\t4.1 O valor da locação é de R$ " + getValorTotalLocacao() + " a diária, tarifa pública da LOCADORA,"
                + " da qual o LOCATÁRIO é concordante, a ser pago somente na devolução do veículo\n"
                + "\t4.2 O valor do caução é de R$ " + getValorCaucao() + ", a ser pago ao adquirir este contrato.\n"
                + "\t4.3 O valor do seguro é de R$ " + getValorSeguro() + ", conforme regido no contrato, a ser pago junto com a locação.\n"
                + "\t4.4 O valor de acessórios é de R$ " + getValorTotalAcessorios() + ", conforme solicitado pelo locatário, a ser pago junto com a locação.\n"
                + "\t4.5 O valor da multa é de R$ " + getValorMulta() + ", conforme ocorrência da mesma, a ser paga conforme regido"
                + "neste contrato. Lembra-se que o valor da multa é de inteira responsabilidade do locatário/cliente e não do"
                + "motorista, porém os pontos na CNH, será na CNH do motorista.\n\n"
                + "\t5 Das observações\n\n"
                + "\t" + getObservacoes() + ".\n\n"
                + "\t6 O total a pagar pelo LOCATÁRIO corresponde aos serviços prestados pela LOCADORA, apurados nos termos"
                + " e condições ajustadas previamente.\n\n"
                + "\t6.1 Multas de trânsito, re-embolsos por danos causados ao veículo alugado, indenizações pôr danos "
                + "causados a terceiros e/ou seus bens, diferenças de cálculos, se pôr ventura ocorrerem, serão cobrados"
                + " posteriormente ao fechamento do Contrato de Aluguel, pôr impossibilidade de apuração imediata dos "
                + "seus valores.\n\n"
                + "\t7 Do Prazo de Rescisão do Contrato\n\n"
                + "\t7.1 O prazo do vencimento do aluguel é na data de " + convertDate(getDataDevolucaoPrevista()) + " devendo o LOCATÁRIO devolver o "
        + "veículo no dia, hora e local contratado. A prorrogação do vencimento dependerá de prévia autorização "
        + "escrita da LOCADORA.\n\n"
                + "\tDas Formas de Pagamento\n\n"
                + "\tO locatário reconhece o valor apurado neste instrumento como dívida líquida, certa e exigível, legitimando"
                + "a cobrança. o valor poderá ser pago em especie conforme moeda corrente deste país ou através de cartão de crédito.\n\n"
                
                + "\t\t\t\tGoiânia - GO, " + convertDate(getDataRetirada()) + "\n\n"
                + "\n"
                + "\n"
                + "		LOCADOR						LOCATÁRIO\n"
                + "\n"
                + "TESTEMUNHAS:\n"
                + "\n"
                + "\n"
                + "______________________________		_______________________________\n\n";
        return contrato;
    }
    
    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formato.format(dtConsulta);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
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
