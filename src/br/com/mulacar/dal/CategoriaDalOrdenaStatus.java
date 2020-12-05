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
public class CategoriaDalOrdenaStatus extends CategoriaDalOrdena {

    @Override
    public boolean ePrimeiro(Categoria cat1, Categoria cat2) throws Exception {
        if (cat1.getStatus().compareTo(cat2.getStatus()) <= 0) {
            return true;
        } else {
            return false;
        }
    }

}
