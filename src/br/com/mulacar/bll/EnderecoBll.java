/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.EnderecoDal;   
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Endereco;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.util.UtilObjetos;
import java.util.List;


public class EnderecoBll {

    private static final long sderialVersionUID = 1L;
    
    private EnderecoDal enderecoDal;

    public EnderecoBll() {
        enderecoDal = new EnderecoDal();
    }

    public void adicionarEndereco(Endereco endereco) throws Exception {

        this.validarEndereco(endereco);
        
        enderecoDal.addEndereco(endereco);
    }
    
    public void adicionarEnderecos(List<Endereco> enderecos) throws Exception {

        for (Endereco end : enderecos) {
            this.validarEndereco(end);
        }
        
        enderecoDal.addEnderecos(enderecos);
    }    

    public void excluirEndereco(Endereco endereco) throws Exception {
        this.validarObjetoNulo(endereco);
        
        this.validarIdNulo(endereco);
        
        this.validarEnderecoExistentePorId(endereco);
        
        enderecoDal.deleteEndereco(endereco);

    }

    public void alterarEndereco(Endereco endereco) throws Exception {
        this.validarEndereco(endereco);
        
        this.validarIdNulo(endereco);
        
        this.validarEnderecoExistentePorId(endereco);

        enderecoDal.updateEndereco(endereco);
    }

    private void validarEnderecoExistentePorId(Endereco endereco) throws Exception {
        Endereco enderecoRetorno = enderecoDal.getEnderecoById(endereco);
        
        if (UtilObjetos.ehNuloOuVazio(enderecoRetorno)) {
            throw new Exception("Endereco NÂO possui cadastro e não pode ser atualizado.");
        }
    }

    public List<Endereco> getConsultaEnderecos() throws Exception {
        return enderecoDal.getAllEnderecos();
    }

    public Endereco getEnderecoPorId(Endereco endereco) throws Exception {
        
        this.validarObjetoNulo(endereco);
        
        this.validarIdNulo(endereco);
        
        return enderecoDal.getEnderecoById(endereco);
    }

    public Endereco getEnderecoByCliente(Cliente cliente) throws Exception {
        this.validarObjetoNulo(cliente);
        
        if (UtilObjetos.ehNuloOuVazio(cliente.getId())) {
            throw new Exception("Digite o id do cliente para pesquisa.");
        }
        
        return enderecoDal.getEnderecoByCliente(cliente);
    }
    
    public void ordenaListaEnderecos(List<Endereco> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getUf().compareTo(lista.get(j).getUf()) >= 0) {
                    Endereco temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }    

    private void validarEndereco(Endereco endereco) throws Exception {
        
        this.validarObjetoNulo(endereco);
        
        this.validarCamposObrigatorios(endereco);
  
        if (!UtilObjetos.ehNuloOuVazio(endereco.getCliente())) {
            this.validarEnderecoExistentePorCliente(endereco.getCliente());
        }
        
        if (!UtilObjetos.ehNuloOuVazio(endereco.getMotorista())) {
            this.validarEnderecoExistentePorMotorista(endereco.getMotorista());
        }
        
        this.validarCaracteresEspeciaisCep(endereco);
        
        this.validarTamanhoMinimoCep(endereco, 3);
        
    }
    
    private void validarIdNulo(Endereco endereco) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(endereco.getId())) {
            throw new Exception("ID do Endereco não pode ser nulo ou vazio.");
        }
    }    

    private void validarCamposObrigatorios(Endereco endereco) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(endereco.getTipoEndereco())
                || UtilObjetos.ehNuloOuVazio(endereco.getCep())
                || UtilObjetos.ehNuloOuVazio(endereco.getRua())
                || UtilObjetos.ehNuloOuVazio(endereco.getNumero())
                || UtilObjetos.ehNuloOuVazio(endereco.getBairro())
                || UtilObjetos.ehNuloOuVazio(endereco.getCidade())
                || UtilObjetos.ehNuloOuVazio(endereco.getUf());
        
        if (temCamposNulos) {
            throw new Exception("Campos obrigatórios não foram preenchidos!\n");
        }
    }

    private void validarTamanhoMinimoCep(Endereco endereco, int tamanhoMinimo) throws Exception {
        if (endereco.getCep().length() < tamanhoMinimo) {
            throw new Exception(String.format("O CEP deve ter no mínimo %s caracteres!\n", tamanhoMinimo));
        }
    }

    private void validarCaracteresEspeciaisCep(Endereco endereco) throws Exception {
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        
        for (int i = 0; i < invalidos.length(); i++) {
            if (endereco.getCep().contains("" + invalidos.charAt(i))) {
                throw new Exception("CEP possui caracteres especiais não permitidos!\n");
            }
        }
    }

    private void validarEnderecoExistentePorCliente(Cliente cliente) throws Exception {
        this.validarObjetoNulo(cliente);
        
        Endereco enderecoBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(cliente.getId())) {
           
            enderecoBanco = enderecoDal.getEnderecoByCliente(cliente);

            if (!UtilObjetos.ehNuloOuVazio(enderecoBanco)) {
                throw new Exception("Cliente ja possui endereco cadastrado.");
            }
        }
    }
    
    private void validarEnderecoExistentePorMotorista(Motorista motorista) throws Exception {
        this.validarObjetoNulo(motorista);
        
        Endereco enderecoBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(motorista.getId())) {
            
            enderecoBanco = enderecoDal.getEnderecoByMotorista(motorista);

            if (!UtilObjetos.ehNuloOuVazio(enderecoBanco)) {
                throw new Exception("Motorista ja possui endereco cadastrado.");
            }
        }
    }    

    private void validarObjetoNulo(Object objeto) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(objeto)) {
            throw new Exception("Objeto não pode ser nulo ou vazio.");
        }
    }       
}
