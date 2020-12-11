/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.bll;

import br.com.mulacar.dal.MotoristaDalOld;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.util.UtilObjetos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roger
 */
public class MotoristaBllOld {

    private static final long sderialVersionUID = 1L;

    private MotoristaDalOld motoristaDal;

    public MotoristaBllOld() {
        motoristaDal = new MotoristaDalOld();
    }

    public void adicionarMotorista(Motorista motorista) throws Exception {

        this.validarMotorista(motorista);

        motoristaDal.addMotorista(motorista);
    }

    public void excluirMotorista(Motorista motorista) throws Exception {
        this.validarMotoristaNulo(motorista);

        this.validarIdNulo(motorista);

        this.verificarMotoristaExistentePorId(motorista);

        motoristaDal.deleteMotorista(motorista);

    }

    public void alterarMotorista(Motorista motorista) throws Exception {
        this.validarMotorista(motorista);

        this.validarIdNulo(motorista);

        this.verificarMotoristaExistentePorId(motorista);

        motoristaDal.updateMotorista(motorista);
    }

    private void verificarMotoristaExistentePorId(Motorista motorista) throws Exception {
        Motorista motoristaRetorno = motoristaDal.getMotoristaById(motorista);

        if (UtilObjetos.ehNuloOuVazio(motoristaRetorno)) {
            throw new Exception("Motorista NÂO possui cadastro e não pode ser atualizado.");
        }
    }

    public List<Motorista> getConsultaMotoristas() throws Exception {
        return motoristaDal.getAllMotoristas();
    }

    public Motorista getMotoristaPorId(Motorista motorista) throws Exception {

        this.validarMotoristaNulo(motorista);

        this.validarIdNulo(motorista);

        return motoristaDal.getMotoristaById(motorista);
    }

    public Motorista getMotoristaByNome(Motorista motorista) throws Exception {
        this.validarMotorista(motorista);

        if (UtilObjetos.ehNuloOuVazio(motorista.getNome())) {
            throw new Exception("Digite nome para pesquisa.");
        }

        return motoristaDal.getMotoristaByNome(motorista);
    }

    public void ordenaListaMotoristas(List<Motorista> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) >= 0) {
                    Motorista temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }

    private void validarMotorista(Motorista motorista) throws Exception {

        this.validarMotoristaNulo(motorista);

        this.validarCamposObrigatorios(motorista);

        this.validarIdNulo(motorista);

        this.validarMotoristaExistente(motorista);

        this.validarTamanhoMinimoNome(motorista, 3);

    }

    private void validarIdNulo(Motorista motorista) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(motorista.getId())) {
            throw new Exception("ID do Motorista não pode ser nulo ou vazio.");
        }
    }

    private void validarCamposObrigatorios(Motorista motorista) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(motorista.getNome())
                || UtilObjetos.ehNuloOuVazio(motorista.getCpf())
                || UtilObjetos.ehNuloOuVazio(motorista.getRg())
                || UtilObjetos.ehNuloOuVazio(motorista.getOrgaoEmissor())
                || UtilObjetos.ehNuloOuVazio(motorista.getNumeroCnh())
                || UtilObjetos.ehNuloOuVazio(motorista.getDataValidadeCnh())
                || UtilObjetos.ehNuloOuVazio(motorista.getCategoriaCnh())
                || UtilObjetos.ehNuloOuVazio(motorista.getPathImagemCnh());

        if (temCamposNulos) {
            throw new Exception("Campos obrigatórios não foram preenchidos!\n");
        }
    }

    private void validarTamanhoMinimoNome(Motorista motorista, int tamanhoMinimo) throws Exception {
        if (motorista.getNome().length() < tamanhoMinimo || motorista.getNome().length() < tamanhoMinimo) {
            throw new Exception(String.format("O noem do motorista deve ter no mínimo %s caracteres!\n", tamanhoMinimo));
        }
    }

    private void validarCaracteresEspeciaisCpf(Motorista motorista) throws Exception {
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";

        for (int i = 0; i < invalidos.length(); i++) {
            if (motorista.getCpf().contains("" + invalidos.charAt(i))) {
                throw new Exception("Só é permitido numeros para o CPF.!\n");
            }
        }
    }

    private void validarMotoristaExistente(Motorista motorista) throws Exception {
        this.validarMotoristaNulo(motorista);

        Motorista motoristaBanco = null;

        if (!UtilObjetos.ehNuloOuVazio(motorista.getCpf())) {
            motoristaBanco = motoristaDal.getMotoristaByCpf(motorista);
        }

        if (!UtilObjetos.ehNuloOuVazio(motorista.getNumeroCnh())) {
            motoristaBanco = motoristaDal.getMotoristaByNumeroCnh(motorista);
        }

        if (!UtilObjetos.ehNuloOuVazio(motorista.getRg())) {
            motoristaBanco = motoristaDal.getMotoristaByRg(motorista);
        }

        if (!UtilObjetos.ehNuloOuVazio(motoristaBanco)) {
            throw new Exception("Motorista já possui cadastro.");
        }
    }

    private void validarMotoristaNulo(Motorista motorista) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(motorista)) {
            throw new Exception("Motorista não pode ser nulo ou vazio.");
        }
    }

    public ArrayList pesquisarMotorista(String string) throws Exception {
        return motoristaDal.sourceMotorista(string);
    }

}
