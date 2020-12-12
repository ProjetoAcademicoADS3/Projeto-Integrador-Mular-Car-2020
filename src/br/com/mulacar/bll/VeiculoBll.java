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

    private VeiculoDal veiculoDal;
    private Calendar cal;

    public VeiculoBll() {
        veiculoDal = new VeiculoDal();
        cal = Calendar.getInstance();
    }

    public void adicionarVeiculo(Veiculo veiculo) throws Exception {
        validarVeiculo(veiculo);
        veiculoDal.addVeiculo(veiculo);
    }

    public void excluirVeiculo(int id) throws Exception {
        try {
            veiculoDal.deleteVeiculo(id);
        } catch (Exception erro) {
            String mensagem = erro.getMessage();
            if (mensagem.toLowerCase().contains("violates foreign")) {
                throw new Exception("Este registro não pode ser excluído"
                        + " porque existe outros registros vinculados a ele\n");
            }
        }
    }

    public void atualizarVeiculo(Veiculo veic) throws Exception {
        veiculoDal.updateVeiculo(veic);
    }

    public List<Veiculo> getConsultarVeiculos() throws Exception {
        return veiculoDal.getAllVeiculo();
    }

    public Veiculo getConsultarVeiculoByPlaca(String placa) throws Exception {
        return veiculoDal.getVeiculoByPlaca(placa);
    }

    public Veiculo getConsultarVeiculoPorId(Veiculo veiculo) throws Exception {
        return veiculoDal.getVeiculoById(veiculo);
    }

    public List<Veiculo> getConsultarVeiculoByCategoria(int categoria, String situacao) throws Exception {
        return veiculoDal.getVeiculoByCategoria(categoria, situacao);
    }

    public List<Veiculo> getConsultarVeiculoByCategoria(int categoria) throws Exception {
        return veiculoDal.getVeiculoByCategoria(categoria);
    }

    public List<Veiculo> getConsultarVeiculoBySituacao(String situacao) throws Exception {
        return veiculoDal.getVeiculoBySituacao(situacao);
    }

    public ArrayList sourceVeiculo(String dados) throws Exception {
        return veiculoDal.sourceVeiculo(dados);
    }

    public void validarVeiculo(Veiculo objeto) throws Exception {
        int placaPadrao = 7;
        int anoFabricPrimeiroVeiculo = 1886;
        int renavanPadrao = 11;
        String renavan = objeto.getRenavan().toLowerCase().trim();
        String placa = objeto.getPlaca().toLowerCase().trim();

        List<Veiculo> listaVeiculos = veiculoDal.getAllVeiculo();
        for (int pos = 0; pos < listaVeiculos.size(); pos++) {
            Veiculo veiculo = listaVeiculos.get(pos);
            if (renavan.equals(veiculo.getRenavan().trim())) {
                throw new Exception("O número de renavan informado já existe "
                        + "para outro veículo!\nVerifique.");
            }
            if (placa.equals(veiculo.getPlaca())) {
                throw new Exception("A placa informada já existe para "
                        + "outro veículo!\nVerifique.");
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
        if (objeto.getPrecoVenda().compareTo(BigDecimal.ZERO) == 0
                || objeto.getPrecoVenda().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("Valor de venda do veículo inválido!\n"
                    + "Verifique.");
        }
        if (objeto.getNumPassageiros() < 1) {
            throw new Exception("O número de passageiros não pode ser menor que um!\n");
        }
        if (objeto.getKm() <= 0 || objeto.getKm() > 999999) {
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

    public void ordenaListaModelos(List<Veiculo> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getModelo().getDescricao().compareToIgnoreCase(lista.get(j).getModelo().getDescricao()) >= 0) {
                    Veiculo temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
        // retorna o array ordenado por nome
    }

}
