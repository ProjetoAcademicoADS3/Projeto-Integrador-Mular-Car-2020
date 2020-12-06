/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.exception;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
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
