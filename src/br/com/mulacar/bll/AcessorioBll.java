/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */
//2020 12 07 0049
package br.com.mulacar.bll;

import br.com.mulacar.dal.AcessorioDal;
import br.com.mulacar.model.Acessorio;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AcessorioBll {
    
    private static final long serialVersionUID = 1L;
    private AcessorioDal  aceDal;
    
    public AcessorioBll(){
        super();
        aceDal = new AcessorioDal();
    }
    
    public void adicionarAcessorio(Acessorio acessorio)throws Exception{
        validarAcessorio(acessorio);
        aceDal.addAcessorio(acessorio);
    }
    
    public List<Acessorio> getConsultaAcessorios()throws Exception{
        return aceDal.getAllAcessorios();
    }
    
    public Acessorio getAcessorioPorId(int id)throws Exception{
        return aceDal.getAcessorioById(id);
    }

    public Acessorio getAcessorioPorNome(String nome)throws Exception{
        return aceDal.getAcessorioByName(nome);
    }
    
    public ArrayList pesquisarAcessorio(String dados)throws Exception{
        return aceDal.sourceAcessorio(dados);
    }
    
    public ResultSet pesquisaInteligente(String nome){
        return aceDal.sourceInteligente(nome);
    }
    
    //VALIDA SE EXISTE VINCULO COM OUTROS REGISTROS NA BASE
    public void excluirAcessorio(Acessorio acessorio)throws Exception{
        try {
            aceDal.deleteAcessorio(acessorio.getId());
        } catch (Exception e) {
            String mensagem = e.getMessage();
            if(mensagem.toLowerCase().contains("violates foreing")){
                throw new Exception("Este registro não pode ser excluido, existe vinculos com outros registros\n");
            }
        }
    }
    
    public void alterarAcessorio(Acessorio acessorio)throws Exception{
        try {
            aceDal.updateAcessorio(acessorio);
        } catch (Exception e) {
            String mensagem = e.getMessage();
            if(mensagem.toLowerCase().contains("acessorio_unico")){
                throw new Exception("Existe registro com esta descrição\n");
            }
        }
    }
    
    //VALIDAÇÃO DOS DADOS
    public void validarAcessorio(Acessorio obj)throws Exception{
        String nome = obj.getDescricao().trim().toLowerCase();

        String invalidos = "!@#$%¨&*()+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if(nome.contains("" + invalidos.charAt(i))){
                throw new Exception("Descrição contém carcteres inválidos\n");
            }
        }
        
        if(nome.equals("")){
            throw new Exception("Informe a descrição do Acessório!\n");
        }
        
        if(nome.length() < 3){
            throw new Exception("A descrição deve ter no minimo 3 caracter validos\n");
        }
        
        if(obj.getValor().compareTo(BigDecimal.ZERO) == 0 || obj.getValor().compareTo(BigDecimal.ZERO) == -1){
            throw new Exception("Valor informado Inválido\n");
        }
        
        List<Acessorio> lista = aceDal.getAllAcessorios();
        for (int pos = 0; pos < lista.size(); pos++) {
            Acessorio ace = lista.get(pos);
            if(nome.equalsIgnoreCase(ace.getDescricao())){
                throw new Exception("Descrição informada já existe\n");
            }
        }
    }
    
    //ORDENAR ARRAY POR NOME
    public void ordenaListaAcessorios(List<Acessorio> lista) throws Exception{
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(i).getDescricao().compareToIgnoreCase(lista.get(j).getDescricao())<=0) {
                    Acessorio temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }
    }
    
}
