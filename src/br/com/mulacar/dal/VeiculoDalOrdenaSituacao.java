/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.model.Veiculo;

/**
 *
 * @author roger
 */
public class VeiculoDalOrdenaSituacao extends VeiculoDalOrdena {

    @Override
    public boolean ePrimeiro(Veiculo veiculo1, Veiculo veiculo2) throws Exception {
        if (veiculo1.getSituacao().toString().compareTo(veiculo2.getSituacao().toString()) <= 0) {
            return true;
        } else {
            return false;
        }
    }

}
