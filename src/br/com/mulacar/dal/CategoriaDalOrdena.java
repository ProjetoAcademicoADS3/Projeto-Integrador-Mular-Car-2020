/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.bll.CategoriaBll;
import br.com.mulacar.model.Categoria;
import java.util.List;

/**
 *
 * @author roger
 */
public abstract class CategoriaDalOrdena {

    public abstract boolean ePrimeiro(Categoria cat1, Categoria cat2) throws Exception;

    public final void ordenaCategorias(List<Categoria> lista) throws Exception {

        for (int i = 0; i < lista.size(); i++) {
            for (int j = i; j < lista.size(); j++) {
                if (!ePrimeiro(lista.get(i), lista.get(j))) {
                    Categoria temp = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, temp);
                }
            }
        }

    }
}
