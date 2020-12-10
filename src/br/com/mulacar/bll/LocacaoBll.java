/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.bll;

import br.com.mulacar.dal.LocacaoDal;
import br.com.mulacar.model.Locacao;
import java.util.List;

/**
 *
 * @author roger
 */
public class LocacaoBll {
    
    private LocacaoDal locacaoDal;
    
    public LocacaoBll(){
        
        locacaoDal = new LocacaoDal();
        
    }
    
    public List<Locacao> getConsultaLocacoes()throws Exception{
        return locacaoDal.getAllLocations();
    }
    
    public Locacao getConsultaLocacaoPorId(Locacao locacao)throws Exception{
        return locacaoDal.getLocationById(locacao);
    }
    
}
