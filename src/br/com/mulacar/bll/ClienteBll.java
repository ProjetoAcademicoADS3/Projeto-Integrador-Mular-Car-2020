/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.ClienteDal;   
import br.com.mulacar.enumeration.EnumTipoCliente;
import br.com.mulacar.exception.MulaCarException;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.util.UtilObjetos;
import java.util.List;


public class ClienteBll {

    private static final long sderialVersionUID = 1L;
    
    private ClienteDal clienteDal;

    public ClienteBll() {
        clienteDal = new ClienteDal();
    }

    public Cliente adicionarCliente(Cliente cliente) throws Exception {

        this.validarCliente(cliente);
        
        return clienteDal.addCliente(cliente);
        
    }

    public void excluirCliente(Cliente cliente) throws Exception {
        this.validarClienteNulo(cliente);
        
        this.validarIdNulo(cliente);
        
        this.validarClienteExistentePorId(cliente);
        
        clienteDal.deleteCliente(cliente);

    }

    public void alterarCliente(Cliente cliente) throws Exception {
        this.validarCliente(cliente);
        
        this.validarIdNulo(cliente);
        
        this.validarClienteExistentePorId(cliente);

        clienteDal.updateCliente(cliente);
    }

    private void validarClienteExistentePorId(Cliente cliente) throws Exception {
        Cliente clienteRetorno = clienteDal.getClienteById(cliente);
        
        if (UtilObjetos.ehNuloOuVazio(clienteRetorno)) {
            throw new MulaCarException("Cliente NÂO possui cadastro e não pode ser atualizado.");
        }
    }

    public List<Cliente> getConsultaClientes() throws Exception {
        return clienteDal.getAllClientes();
    }

    public Cliente getClientePorId(Cliente cliente) throws Exception {
        
        this.validarClienteNulo(cliente);
        
        this.validarIdNulo(cliente);
        
        return clienteDal.getClienteById(cliente);
    }

    public Cliente getClienteByNomeOuFantasia(Cliente cliente) throws Exception {
        this.validarCliente(cliente);
        
        if (UtilObjetos.ehNuloOuVazio(cliente.getNome()) && UtilObjetos.ehNuloOuVazio(cliente.getNomeFantasia())) {
            throw new MulaCarException("Digite nome ou nome Fantasia para pesquisa.");
        }
        
        return clienteDal.getClienteByNomeOuFantasia(cliente);
    }
    

    public void ordenaListaClientes(List<Cliente> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) >= 0) {
                    Cliente temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }    

    private void validarCliente(Cliente cliente) throws Exception {
        
        this.validarClienteNulo(cliente);
        
        this.validarCamposObrigatorios(cliente);
        
        this.validarClienteExistentePorCpfCnpjOuRg(cliente);
        
        this.validarCaracteresEspeciaisCpfCnpjOuRg(cliente);
        
        this.validarTamanhoMinimoNomeOuFantasia(cliente, 3);
        
    }
    
    private void validarIdNulo(Cliente cliente) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(cliente.getId())) {
            throw new MulaCarException("ID do Cliente não pode ser nulo ou vazio.");
        }
    }    

    private void validarCamposObrigatorios(Cliente cliente) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(cliente.getStatus())
                || UtilObjetos.ehNuloOuVazio(cliente.getCpfCnpj())
                || UtilObjetos.ehNuloOuVazio(cliente.getTipoCliente());
        
        if (temCamposNulos) {
            throw new MulaCarException("Campos obrigatórios não foram preenchidos!\n");
        }
    }

    private void validarTamanhoMinimoNomeOuFantasia(Cliente cliente, int tamanhoMinimo) throws Exception {
        if (cliente.getTipoCliente().equals(EnumTipoCliente.PESSOA_FISICA)) {
            if (cliente.getNome().length() < tamanhoMinimo) {
                throw new MulaCarException("O nome cliente deve ter no mínimo 3 letras!\n");
            }
        } else {
            if (cliente.getNomeFantasia().length() < tamanhoMinimo) {
                throw new MulaCarException("O nome fantasia do cliente deve ter no mínimo 3 letras!\n");
            }            
        }
    }

    private void validarCaracteresEspeciaisCpfCnpjOuRg(Cliente cliente) throws Exception {
        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        
        for (int i = 0; i < invalidos.length(); i++) {
            if (cliente.getCpfCnpj().contains("" + invalidos.charAt(i))) {
                throw new MulaCarException("Cpf/Cnpj inválido para o cliente!\n");
            }
            
            if (cliente.getTipoCliente().equals(EnumTipoCliente.PESSOA_FISICA)) {
                if (cliente.getCpfCnpj().contains("" + invalidos.charAt(i))) {
                    throw new MulaCarException("Cpf/Cnpj inválido para o cliente!\n");
                }                
            }
        }
    }

    private void validarClienteExistentePorCpfCnpjOuRg(Cliente cliente) throws Exception {
        this.validarClienteNulo(cliente);
        
        Cliente clienteBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(cliente.getCpfCnpj())) {
            clienteBanco = clienteDal.getClienteByCpfCnpj(cliente);

            if (!UtilObjetos.ehNuloOuVazio(clienteBanco)) {
                throw new MulaCarException("Cliente já possui cadastro.");
            }
        }
        
        if (cliente.getTipoCliente().equals(EnumTipoCliente.PESSOA_FISICA) 
                && !UtilObjetos.ehNuloOuVazio(cliente.getRg())) {
            
            clienteBanco = clienteDal.getClienteByRg(cliente);

            if (!UtilObjetos.ehNuloOuVazio(clienteBanco)) {
                throw new MulaCarException("Cliente já possui cadastro.");
            }        
        }
    }

    private void validarClienteNulo(Cliente cliente) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(cliente)) {
            throw new MulaCarException("Cliente não pode ser nulo ou vazio.");
        }
    }       
}
