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
    public void testarSubtracao2Num(){
        int num1 = 10;
        int num2 = 2;
        int resultadoEsperado = 8;

        int resultadoObtido = Calc.subtrairDoisNumeros(num1, num2);

        assertEquals(resultadoObtido, resultadoEsperado);
    }
    @Test
    public void testarMultiplicacao2Num(){
        int num1 = 10;
        int num2 = 2;
        int resultadoEsperado = 20;

        int resultadoObtido = Calc.multiplicarDoisNumeros(num1, num2);

        assertEquals(resultadoObtido, resultadoEsperado);
    }
    @Test
    public void testarDivisao2Num(){
        int num1 = 10;
        int num2 = 2;
        int resultadoEsperado = 5;

        int resultadoObtido = Calc.dividirDoisNumeros(num1, num2);

        assertEquals(resultadoObtido, resultadoEsperado);
    }

}
