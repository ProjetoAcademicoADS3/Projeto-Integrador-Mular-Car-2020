/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.dal;

import br.com.mulacar.interfaces.Interface_ExibirImagem;
import br.com.mulacar.model.Motorista;
import javax.swing.ImageIcon;

/**
 *
 * @author roger
 */
public class Imagem_Adapter implements Interface_ExibirImagem {
    
    NovaDistribuidoraDeImagem novaDist = new NovaDistribuidoraDeImagem();

    @Override
    public ImageIcon exibirImagem(Motorista motorista) throws Exception {
        
        return novaDist.exibirImagemAdaptada(motorista.getPathImagemCnh());

    }

}
