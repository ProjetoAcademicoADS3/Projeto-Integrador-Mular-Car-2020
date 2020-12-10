/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mulacar.app;

import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.model.Cliente;
import br.com.mulacar.model.Locacao;
import br.com.mulacar.model.Motorista;
import br.com.mulacar.model.Usuario;
import br.com.mulacar.model.Veiculo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author roger
 */
public class LocacaoTeste {

    public static void main(String[] args) {
        
        Date data = new Date();

        Locacao locacao = new Locacao(1, new Cliente("Rogerio"),
                new Motorista("Beltrano"), new Veiculo("ASD1234"),
                new Usuario("Cicrano"), new BigDecimal(120), true,
                createNewDate("09/12/2020"),createNewDate("09/12/2020"), "123456", "Nenhuma",
                new BigDecimal(200), new BigDecimal(120), new BigDecimal(120),
                new BigDecimal(120), EnumStatus.ATIVO, true);

        System.out.println(new Date().getTime());
        System.out.println(locacao);

    }
    private static Date createNewDate(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(data);
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }

}
