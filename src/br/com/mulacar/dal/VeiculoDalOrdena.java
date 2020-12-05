/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.model.Veiculo;
import java.util.List;

/**
 *
 * @author roger
 */
public abstract class VeiculoDalOrdena {

    public abstract boolean ePrimeiro(Veiculo veiculo1, Veiculo veiculo2) throws Exception;

    public final void ordenaVeiculos(List<Veiculo> lista) throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (!ePrimeiro(lista.get(i), lista.get(j))) {
                    Veiculo temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }

    }
}
