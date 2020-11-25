/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
package br.com.mulacar.bll;

import br.com.mulacar.dal.VeiculoDal;
import br.com.mulacar.model.Veiculo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author roger
 */
public class VeiculoBll {

    private VeiculoDal veicDal;
    private Calendar cal;

    public VeiculoBll() {
        veicDal = new VeiculoDal();
        cal = Calendar.getInstance();
    }

    public void adicionarVeiculo(Veiculo veiculo) throws Exception {
        veicDal.addVeiculo(veiculo);
    }

    public void excluirVeiculo(int id) throws Exception {
        veicDal.deleteVeiculo(id);
    }

    public void atualizarVeiculo(Veiculo veic) throws Exception {
        veicDal.updateVeiculo(veic);
    }

    public List<Veiculo> getConsultarVeiculos() throws Exception {
        return veicDal.getAllVeiculo();
    }

    public Veiculo getConsultarVeiculoById(int id) throws Exception {
        return veicDal.getVeiculoById(id);
    }

    public ArrayList sourceVeiculo(String dados) throws Exception {
        return veicDal.sourceVeiculo(dados);
    }

    public void validarVeiculo(Veiculo objeto) throws Exception {
        int placaPadrao = 7;
        int anoFabricPrimeiroVeiculo = 1886;
        int renavanPadrao = 11;
        String renavan = objeto.getRenavan().toLowerCase().trim();

        String validos = "1234567890";
        for (int i = 0; i < validos.length(); i++) {
            if (!renavan.contains("" + validos.charAt(i))) {
                throw new Exception("Renavan inválido!\n"
                        + "O renavan informado deve ter apenas números.");
            }
        }
        if (objeto.getPlaca().length() != placaPadrao) {
            throw new Exception("Placa inválida!\n"
                    + "A placa informada deve ter 7 caracteres\n");
        }
        if (objeto.getAnoFabricacao() < anoFabricPrimeiroVeiculo) {
            throw new Exception("Ano de fabricação do veículo inválido!\n"
                    + "Ano de fabricação deve ser maior que 1886,"
                    + " ano em que foi fabricado o primeiro veículo da "
                    + "história automobilistica.");
        }
        if (objeto.getAnoFabricacao() > cal.get(Calendar.YEAR)) {
            throw new Exception("Ano de fabricação inválido!\n"
                    + "Ano de fabricação não pode ser maior que o ano corrente.");
        }
        if (objeto.getAnoModelo() < objeto.getAnoFabricacao()) {
            throw new Exception("Ano de modelo do veículo inválido!\n"
                    + "Ano de modelo deve ser maior ou igual ao "
                    + "ano de fabricação do veículo.");
        }
        if (objeto.getAnoModelo() > objeto.getAnoFabricacao() + 1) {
            throw new Exception("Ano de modelo inválido!\n"
                    + "Ano de modelo não pode ser maior que o ano corrente + 1.");
        }
        if (objeto.getRenavan().length() > renavanPadrao
                || objeto.getRenavan().length() < renavanPadrao) {
            throw new Exception("Número do renavan inválido!\n"
                    + "O número do renavan deve conter 11 digitos numéricos.");
        }
        if (objeto.getPrecoCompra().compareTo(BigDecimal.ZERO) == 0
                || objeto.getPrecoCompra().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("Valor de compra do veículo inválido!\n"
                    + "Verifique");
        }
        if (objeto.getPrecoVenda().compareTo(BigDecimal.ZERO) ==  0
                || objeto.getPrecoVenda().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("Valor de venda do veículo inválido!\n"
                    + "Verifique.");
        }
        if (objeto.getNumPassageiros() < 1) {
            throw new Exception("O número de passageiros não pode ser menor que um!\n");
        }
        if (objeto.getKm() <= 0) {
            throw new Exception("A quilometragem do veículo inválida\n"
                    + "Verifique!");
        }
        if (objeto.getCategoria().equals("")) {
            throw new Exception("Informe a categoria do veículo!\n");
        }
        if (objeto.getModelo().equals("")) {
            throw new Exception("Informe o modelo do veículo!\n");
        }

    }

}
