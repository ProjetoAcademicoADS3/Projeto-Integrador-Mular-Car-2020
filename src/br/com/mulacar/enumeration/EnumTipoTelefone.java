/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.enumeration;

import java.util.Arrays;
import java.util.Vector;

public enum EnumTipoTelefone {
    
    CELULAR("Celular"), 
    FIXO("Fixo");

 private final String descricao;
    
    EnumTipoTelefone(final String descricao) {
        this.descricao = descricao;
    }
    
    public static EnumTipoTelefone fromDescricao(final String tipoDescricao) {
      for (final EnumTipoTelefone desc : EnumTipoTelefone.values()) {
        if (desc.descricao.equalsIgnoreCase(tipoDescricao)) {
          return desc;
        }
      }

      throw new IllegalArgumentException(tipoDescricao);
    }    
        
    public static Vector<EnumTipoTelefone> carregarTiposTelefones() {
        
        Vector<EnumTipoTelefone> tipos = new Vector();
        
        tipos.addAll(Arrays.asList(EnumTipoTelefone.values()));   
        
        return tipos;
    }     

    public String getTipo() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }    
    
}
