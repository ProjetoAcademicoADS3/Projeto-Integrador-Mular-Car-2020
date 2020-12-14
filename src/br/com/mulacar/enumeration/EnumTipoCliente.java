/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.enumeration;

public enum EnumTipoCliente {
    
    PESSOA_FISICA("Pessoa Física"), 
    PESSOA_JURIDICA("Pessoa Jurídica");
    
    private final String descricao;
    
    
    /**
     * Construtor do enum
     *
     * @param descricao    pessoa do tipo de cliente
     */    
    EnumTipoCliente(final String descricao) {
      this.descricao = descricao;
    }  
    
    /**
     * Converte a partir do tipo de pessoa para o respectivo enum.
     *
     * @param tipoPessoa o tipo da pessoa se física ou jurídica.Exemplo: "Pessoa Física"
     * @return o enum do tipo da pessoa
     * @throws IllegalArgumentException caso não o tipo de pessoa
     */
    public static EnumTipoCliente fromPessoa(final String tipoPessoa) {
      for (final EnumTipoCliente tipo : EnumTipoCliente.values()) {
        if (tipo.descricao.equalsIgnoreCase(tipoPessoa)) {
          return tipo;
        }
      }

      throw new IllegalArgumentException(tipoPessoa);
    }    

    public static EnumTipoCliente getPESSOA_FISICA() {
        return PESSOA_FISICA;
    }

    public static EnumTipoCliente getPESSOA_JURIDICA() {
        return PESSOA_JURIDICA;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
}
