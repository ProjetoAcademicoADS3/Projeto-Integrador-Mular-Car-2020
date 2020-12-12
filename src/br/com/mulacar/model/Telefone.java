/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumTipoTelefone;

/**
 *
 * @author roger
 */
public class Telefone {
     private EnumTipoTelefone tipoTelefone;
    private String numero;

    public Telefone() {
    }
    
    public Telefone(EnumTipoTelefone tipoTelefone, String numero) {
        this.tipoTelefone = tipoTelefone;
        this.numero = numero;
    }

    public EnumTipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" + "tipoTelefone=" + tipoTelefone + ", numero=" + numero + '}';
    }

    
}
