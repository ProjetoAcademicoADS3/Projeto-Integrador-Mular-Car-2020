/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.MotoristaDal;   
import br.com.mulacar.exception.MulaCarException;
import br.com.mulacar.interfaces.Interface_ExibirImagem;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.util.UtilObjetos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;


public class MotoristaBll {

    private static final long sderialVersionUID = 1L;
    
    private MotoristaDal motoristaDal;
    private Interface_ExibirImagem objetoMotoristaDal;

    public MotoristaBll() {
        motoristaDal = new MotoristaDal();
        objetoMotoristaDal = new MotoristaDal();
    }

    public Motorista adicionarMotorista(Motorista motorista) throws Exception {

        this.validarMotorista(motorista);
        
        return motoristaDal.addMotorista(motorista);
        
    }

    public void excluirMotorista(Motorista motorista) throws Exception {
        this.validarMotoristaNulo(motorista);
        
        this.validarIdNulo(motorista);
        
        this.validarMotoristaExistentePorId(motorista);
        
        motoristaDal.deleteMotorista(motorista);

    }

    public void alterarMotorista(Motorista motorista) throws Exception {
        this.validarMotorista(motorista);
        
        this.validarIdNulo(motorista);
        
        this.validarMotoristaExistentePorId(motorista);

        motoristaDal.updateMotorista(motorista);
    }

    private void validarMotoristaExistentePorId(Motorista motorista) throws Exception {
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
            throw new Exception("Digite nome ou nome Fantasia para pesquisa.");
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
        
        this.validarMotoristaExistentePorCpfRg(motorista);
        
        this.validarCaracteresEspeciaisCpfRg(motorista);
        
        this.validarTamanhoMinimoNome(motorista, 3);
        
    }
    
    private void validarIdNulo(Motorista motorista) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(motorista.getId())) {
            throw new Exception("ID do Motorista não pode ser nulo ou vazio.");
        }
    }    

    private void validarCamposObrigatorios(Motorista motorista) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(motorista.getStatus())
                || UtilObjetos.ehNuloOuVazio(motorista.getCpf());
        
        if (temCamposNulos) {
            throw new Exception("Campos obrigatórios não foram preenchidos!\n");
        }
    }

    private void validarTamanhoMinimoNome(Motorista motorista, int tamanhoMinimo) throws Exception {
            if (motorista.getNome().length() < tamanhoMinimo) {
                throw new Exception("O nome motorista deve ter no mínimo 3 letras!\n");
            }
    }

    private void validarCaracteresEspeciaisCpfRg(Motorista motorista) throws Exception {
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        
        for (int i = 0; i < invalidos.length(); i++) {
            if (motorista.getCpf().contains("" + invalidos.charAt(i))) {
                throw new Exception("Cpf inválido para o motorista!\n");
            }
        }
    }

    private void validarMotoristaExistentePorCpfRg(Motorista motorista) throws Exception {
        this.validarMotoristaNulo(motorista);
        
        Motorista motoristaBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(motorista.getCpf())) {
            motoristaBanco = motoristaDal.getMotoristaByCpf(motorista);

            if (!UtilObjetos.ehNuloOuVazio(motoristaBanco)) {
                throw new MulaCarException("Motorista já possui cadastro.");
            }
        }
        
        if (!UtilObjetos.ehNuloOuVazio(motorista.getRg())) {
            
            motoristaBanco = motoristaDal.getMotoristaByRg(motorista);

            if (!UtilObjetos.ehNuloOuVazio(motoristaBanco)) {
                throw new MulaCarException("Motorista já possui cadastro.");
            }        
        }
    }

    private void validarMotoristaNulo(Motorista motorista) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(motorista)) {
            throw new Exception("Motorista não pode ser nulo ou vazio.");
        }
    } 
    
    public ArrayList pesquisarMotorista(String dados) throws Exception{
        return motoristaDal.sourceMotorista(dados);
    }
    
    public ImageIcon exibirImagem(Motorista motorista) throws Exception{
        return objetoMotoristaDal.exibirImagem(motorista);
    }
}
