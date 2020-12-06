/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.bll;

import br.com.mulacar.dal.ContatoDal;
import br.com.mulacar.model.Contato;
import br.com.mulacar.util.UtilObjetos;
import java.util.List;


public class ContatoBll {

    private static final long sderialVersionUID = 1L;
    
    private ContatoDal contatoDal;

    public ContatoBll() {
        contatoDal = new ContatoDal();
    }

    public void adicionarContato(Contato contato) throws Exception {

        this.validarContato(contato);
        
        contatoDal.addContato(contato);
    }
    
    public void adicionarContatos(List<Contato> contatos) throws Exception {

        this.validarContatos(contatos);
        
        contatoDal.addContatos(contatos);
    }    

    public void excluirContato(Contato contato) throws Exception {
        this.validarObjetoNulo(contato);
        
        this.validarIdNulo(contato);
        
        this.validarContatoExistentePorId(contato);
        
        contatoDal.deleteContato(contato);

    }

    public void alterarContato(Contato contato) throws Exception {
        this.validarContato(contato);
        
        this.validarIdNulo(contato);
        
        this.validarContatoExistentePorId(contato);

        contatoDal.updateContato(contato);
    }

    private void validarContatoExistentePorId(Contato contato) throws Exception {
        Contato contatoRetorno = contatoDal.getContatoById(contato);
        
        if (UtilObjetos.ehNuloOuVazio(contatoRetorno)) {
            throw new Exception("Contato NÂO possui cadastro para ser atualizado.");
        }
    }

    public List<Contato> getConsultaContatos() throws Exception {
        return contatoDal.getAllContatos();
    }

    public Contato getContatoPorId(Contato contato) throws Exception {
        
        this.validarObjetoNulo(contato);
        
        this.validarIdNulo(contato);
        
        return contatoDal.getContatoById(contato);
    }

    public Contato getContatoByEmail(Contato contato) throws Exception {
        this.validarContato(contato);
        
        if (UtilObjetos.ehNuloOuVazio(contato.getEmail())) {
            throw new Exception("Emial inválido para pesquisa.");
        }
        
        return contatoDal.getContatoByEmail(contato);
    }
    
    public void ordenaListaContatos(List<Contato> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (lista.get(i).getEmail().compareToIgnoreCase(lista.get(j).getEmail()) >= 0) {
                    Contato temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }    

    private void validarContato(Contato contato) throws Exception {
        
        this.validarObjetoNulo(contato);
        
        this.validarCamposObrigatorios(contato);
        
        if (!UtilObjetos.ehNuloOuVazio(contato.getCliente())) {
            this.validarContatoExistentePorEmail(contato);
        }
    }
    
    private void validarContatos(List<Contato> contatos) throws Exception {
        
        this.validarObjetoNulo(contatos);
        
        for (Contato contato : contatos) {
            
            this.validarCamposObrigatorios(contato);
            
            this.validarContatoExistentePorEmail(contato);
        }
        
    }    
    
    private void validarIdNulo(Contato contato) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(contato.getId())) {
            throw new Exception("ID do Contato não pode ser nulo ou vazio.");
        }
    }    

    private void validarCamposObrigatorios(Contato contato) throws Exception {
        boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(contato.getTipoTelefone())
                                || UtilObjetos.ehNuloOuVazio(contato.getNumero())
                                || UtilObjetos.ehNuloOuVazio(contato.getEmail());
        
        if (temCamposNulos) {
            throw new Exception("Campos obrigatórios devem ser preenchidos!\n");
        }
    }
    
//    private void validarListaCamposObrigatorios(List<Contato> contatos) throws Exception {
//        for (Contato contato : contatos) {
//            
//            boolean temCamposNulos = UtilObjetos.ehNuloOuVazio(contato.getTelefone())
//                    || UtilObjetos.ehNuloOuVazio(contato.getTelefone().getTipoTelefone())
//                    || UtilObjetos.ehNuloOuVazio(contato.getTelefone().getNumero())
//                    || UtilObjetos.ehNuloOuVazio(contato.getEmail());
//
//            if (temCamposNulos) {
//                throw new Exception("Campos obrigatórios devem ser preenchidos!\n");
//            }
//        }
//    }    

    private void validarContatoExistentePorEmail(Contato contato) throws Exception {
        this.validarObjetoNulo(contato);
        
        Contato contatoBanco = null;
        
        if (!UtilObjetos.ehNuloOuVazio(contato.getEmail())) {
            contatoBanco = contatoDal.getContatoByEmail(contato);

            if (!UtilObjetos.ehNuloOuVazio(contatoBanco)) {
                throw new Exception("Já existe contato com esse email cadatrado.");
            }
        }
    }
    
    private void validarObjetoNulo(Object objeto) throws Exception {
        if (UtilObjetos.ehNuloOuVazio(objeto)) {
            throw new Exception("Objeto não pode ser nulo ou vazio.");
        }
    }       
}
