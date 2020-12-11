/*
 * Novembro/Dezembro 2020.
 * Senai Fatesg Faculdade de Tecnologia
 * ADS - Análise e Desenvolvimento de Sistemas
 * Projeto Integrador - ADS3
 * Projeto Mula Car - aluguel de Veículos
 * Alunos: Aires Ribeiro, Gabriel Cunha, Lucas França e Rogério Reis
 */

package br.com.mulacar.exception;

public class MulaCarException extends Exception{
    
    public MulaCarException() {
            super();
    }

    public MulaCarException(String message, Throwable cause) {
            super(message, cause);
    }

    public MulaCarException(String message) {
            super(message);
    }

    public MulaCarException(Throwable cause) {
            super(cause);
    }
    
}
