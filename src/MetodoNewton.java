import java.math.BigDecimal;
import java.util.Scanner;

/*
 * Metodo de Newton
 * x0 inicial = 0.5
 * Precisao de 0.001  ||   10^-3
 * Considere  F(x) = 2x^3+3x^2-2
 * Considere  F'(x) = 6x^2+6x
 */
public class MetodoNewton {

    /* Calcula o proximo valor de X */
    public static double calculaProximoXAtual(double xAtual, double fx, double f_x){
        return xAtual-(fx/f_x);
    }

    // F(x) = 2x^3+3x^2-2
    public static double calculaFx(double x){
        return 2*Math.pow(x,3) + 3*Math.pow(x,2) - 2;
    }

    //F'(x) = 6x^2+6x
    public static double calculaFxLinha(double x){
        return 6*Math.pow(x,2) + 6*x;
    }

    /* Erro Absoluto */
    public static double calculaErroAbsoluto(double a, double b){
        return Math.abs(b - a);
    }

    /* Erro Relativo */
    public static double calculaErroRelativo(double a, double b){
        return (b != 0) ? Math.abs((b - a) / b) : 0;
    }

    //Print dos valores
    public static void exibirResultado(double xAnterior, double xAtual, double precisao, double  fx, double  f_x, double  erroAbs, double  erroRel, int i, int qtdAposVirgula){
        System.out.println();
        System.out.println("************************* ");
        System.out.println("****** ITERACAO "+ i + " ******* ");
        System.out.println("************************* ");

        System.out.printf("x: %." + qtdAposVirgula +"f \n", xAnterior);
        System.out.printf("Proximo x: %." + qtdAposVirgula +"f \n", xAtual);
        System.out.printf("precisão: %." + qtdAposVirgula +"f \n" , precisao);
        System.out.printf("f(x): %." + qtdAposVirgula +"f \n", fx);
        System.out.printf("f'(x): %." + qtdAposVirgula +"f \n", f_x);
        System.out.printf("Erro Absoluto: %." + qtdAposVirgula +"f \n", erroAbs);
        System.out.printf("Erro Relativo: %." + qtdAposVirgula +"f \n", erroRel);

        System.out.println();
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int maxInteracao = 0;
        int qtdAposVirgula = 3; //default 3 casas apos a virgula

        double xAtual = 0, xAnterior=0;  //x atual, x anterior
        double fx=0, f_x=0;              //f(x), F'(x)
        double precisao = 0.001;         //Precisao default
        double erroAbs = 0, erroRel = 0; //Erro Absoluto

        //Entrada de dados
        System.out.print("Informe um chute inicial (Ex.: 0.5): ");
        xAtual = in.nextDouble();

        System.out.print("Informe a precisao [default 0.001]: ");
        precisao = in.nextDouble();

        System.out.print("Quantidade de casas apos a virgula: ");
        qtdAposVirgula = in.nextInt();

        System.out.print("Quantidade maxima de iteracao: ");
        maxInteracao = in.nextInt();

        in.close(); //Fechando scanner de input

        int i = 0;
        while (i <= maxInteracao) {

            fx = calculaFx(xAtual);
            f_x = calculaFxLinha(xAtual);

            xAnterior = xAtual; //Atualizando x anterior
            xAtual = calculaProximoXAtual(xAtual, fx,f_x);

            erroAbs = calculaErroAbsoluto(xAtual, xAnterior);
            erroRel = calculaErroRelativo(xAtual, xAnterior);

            exibirResultado( xAnterior, xAtual, precisao, fx, f_x, erroAbs, erroRel, i, qtdAposVirgula);

            // Criterio de parada, (erro absoluto < precisao)
            if (erroAbs  < precisao) {
                System.out.println("Critério de parada atingido. \n");

                System.out.printf("Erro Absoluto(%."+qtdAposVirgula+"f) menor que a Precisao(%."+qtdAposVirgula+"f).\n",erroAbs, precisao);
                System.out.printf("Raiz aproximada encontrada: %."+qtdAposVirgula+"f \n", xAtual);
                break;
            }
            i++;
        }
    }
}