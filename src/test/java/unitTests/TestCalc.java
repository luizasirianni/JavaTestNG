package unitTests;

import devcalc.Calc;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class TestCalc {
    @Test
    public void testarSomarDoisNumeros(){
        // configuração do teste
        int num1 = 5;
        int num2 = 10;
        int resultadoEsperado = 15;

        // executar o teste
        int resultadoObtido = Calc.somarDoisNumeros(num1, num2);

        // validar o teste
        assertEquals(resultadoObtido, resultadoEsperado);
    }

    @Test
    public void testarMain(){

    }
}
