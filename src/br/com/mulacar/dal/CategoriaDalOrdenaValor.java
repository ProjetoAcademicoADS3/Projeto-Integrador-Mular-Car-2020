/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.model.Categoria;

/**
 *
 * @author roger
 */
public class CategoriaDalOrdenaValor extends CategoriaDalOrdena{

    @Override
    public boolean ePrimeiro(Categoria cat1, Categoria cat2) throws Exception {
         if (cat1.getValor().compareTo(cat2.getValor()) <= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
}
