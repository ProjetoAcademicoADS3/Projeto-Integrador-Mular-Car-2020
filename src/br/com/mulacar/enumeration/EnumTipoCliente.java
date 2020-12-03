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
    
    private final String pessoa;
    
    
    /**
     * Construtor do enum
     *
     * @param pessoa    pessoa do tipo de cliente
     */    
    EnumTipoCliente(final String pessoa) {
      this.pessoa = pessoa;
    }  
    
    /**
     * Nome completo do tipo de cliente pessoa
     *
     * @return nome completo da UF
     */
    public String pessoa() {
      return this.pessoa;
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
        if (tipo.pessoa.equalsIgnoreCase(tipoPessoa)) {
          return tipo;
        }
      }

      throw new IllegalArgumentException(tipoPessoa);
    }    
    
}
