package devcalc;

import java.util.Scanner;

public class Calc {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        System.out.println("================");
        System.out.println("[ 1 ] Somar");
        System.out.println("[ 2 ] Subtrair");
        System.out.println("[ 3 ] Multiplicar");
        System.out.println("[ 4 ] Dividir");
        System.out.println("Escolha o calculo desejado: ");

        int opcao = scanner.nextInt();
        int num1 = 0;
        int num2 = 0;

        if (opcao > 0 && opcao <=4){
            System.out.println("Digite o primeiro numero: ");
            num1 = scanner.nextInt();
            System.out.println("Digite o segundo numero: ");
            num2 = scanner.nextInt();
        }

        switch (opcao){
            case 1:
                System.out.println("Resultado da soma: " + somarDoisNumeros(num1, num2));
                break;
            case 2:
                System.out.println("Resultado da subtração: " + subtrairDoisNumeros(num1, num2));
                break;
            case 3:
                System.out.println("Resultado da multiplicação: " + multiplicarDoisNumeros(num1,num2));
                break;
            case 4:
                System.out.println("Resultado da divisao: " + dividirDoisNumeros(num1, num2));
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public static int somarDoisNumeros(int num1, int num2){

        return num1 + num2;
    }
    public static int subtrairDoisNumeros(int num1, int num2){
        return num1 - num2;
    }
    public static int multiplicarDoisNumeros(int num1, int num2){
        return num1 * num2;
    }
    public static int dividirDoisNumeros(int num1, int num2){
        return num1 / num2;
    }
}
