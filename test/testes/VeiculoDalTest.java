/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.mulacar.dal.VeiculoDal;
import br.com.mulacar.enumeration.EnumSituacaoVeiculo;
import br.com.mulacar.enumeration.EnumStatus;
import br.com.mulacar.enumeration.EnumTipoCombustivel;
import br.com.mulacar.enumeration.EnumTipoVeiculo;
import br.com.mulacar.model.Categoria;
import br.com.mulacar.model.Modelo;
import br.com.mulacar.model.Veiculo;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author roger
 */
public class VeiculoDalTest {

    Veiculo veic;
    VeiculoDal veicDal;
    List<Veiculo> listVehicle;

    public VeiculoDalTest() {
        veicDal = new VeiculoDal();
    }

    @Test
    public void testInsertVehicle() throws Exception {

        veic = new Veiculo("RTR4321", 2009, 2010, EnumTipoCombustivel.GASOLINA, "12345678978", new BigDecimal(18000), new BigDecimal(17000), EnumTipoVeiculo.SUV, EnumStatus.ATIVO, 2, 321654,
                new Categoria(29), new Modelo(25), EnumSituacaoVeiculo.BAIXADO);

        veicDal.addVeiculo(veic);
        listVehicle = veicDal.getAllVeiculo();
        int indexLast = listVehicle.size();
        System.out.println(listVehicle.get(indexLast - 1).getId()
                + "\n" + listVehicle.get(indexLast - 1).getPlaca());

        int tamanhoDaPlaca = listVehicle.get(indexLast - 1).getPlaca().length();
        final int expected;
        expected = 7;

        if (tamanhoDaPlaca != expected) {
            fail("O número de caracteres da placa é diferente da expectativa");
        }

        Assert.assertEquals(expected, tamanhoDaPlaca);

    }

}
