import java.math.BigDecimal;
import java.util.Scanner;

/*
 * Metodo Secante
 * x0 inicial = 0.5
 * Precisao de 0.01 ||  10^-2
 * Considere  F(x) = x^2-3x-4
 */
public class MetodoSecante {

    public static double calculaProximoXAtual(double xAtual, double xAnterior, double fxAtual, double fxAnterior){
        return xAtual - ( (fxAtual * (xAtual - xAnterior) )/ (fxAtual - fxAnterior) );
    }

    //Formula aplicada -> F(x) = x^2-3x-4
    public static double calculaFx(double x){
        return Math.pow(x,2)-3*x-4;
    }

    //Erro Absoluto
    public static BigDecimal calculaErroAbsoluto(double xAtual, double xAnterior){
        return BigDecimal.valueOf(Math.abs(xAtual-xAnterior));
    }

    //Erro Relativo
    public static BigDecimal calculaErroRelativo(double xAtual, double xAnterior){
        if(xAtual != 0){
            return BigDecimal.valueOf(Math.abs((xAtual-xAnterior)/xAtual));
        }else{
            return BigDecimal.ZERO; //Evitar divisao por 0
        }
    }

    //Print dos valores
    public static void exibirResultado(double xAnterior, double xAtual, double precisao, double  fxAtual, double  fxAnterior, double proximoXAtual,BigDecimal erroAbs, BigDecimal  erroRel, int i, int qtdAposVirgula){
        System.out.println();
        System.out.println("************************* ");
        System.out.println("****** ITERACAO "+ i + " ******* ");
        System.out.println("************************* ");

        System.out.printf("X Anterior: %." + qtdAposVirgula +"f \n", xAnterior);
        System.out.printf("X Atual: %." + qtdAposVirgula +"f \n", xAtual);
        System.out.printf("precisão: %." + qtdAposVirgula +"f \n" , precisao);
        System.out.printf("f(X Anterior): %." + qtdAposVirgula +"f \n", fxAnterior);
        System.out.printf("f(X Atual): %." + qtdAposVirgula +"f \n", fxAtual);
        System.out.printf("Proximo X: %." + qtdAposVirgula +"f \n", proximoXAtual);

        //Escondendo erros apenas durante a primeira iteracao
        if(i > 0) {
            System.out.printf("Erro Absoluto: %." + qtdAposVirgula +"f \n", erroAbs);
            System.out.printf("Erro Relativo: %." + qtdAposVirgula +"f \n", erroRel);
        }

        System.out.println();
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int maxInteracao = 0;
        int qtdAposVirgula = 1;

        double xAnterior = 0, xAtual = 0;
        double fxAnterior = 0, fxAtual = 0;
        double proximoXAtual = 0;
        double precisao = 0.01;

        BigDecimal BD_erroAbs = BigDecimal.valueOf(0); //Erro Absoluto
        BigDecimal BD_erroRel = BigDecimal.valueOf(0); //Erro Relativo

        //Inputs
        System.out.print("Informe o primeiro intervalo: ");
        xAnterior = in.nextDouble();

        System.out.print("Informe o segundo intervalo: ");
        xAtual = in.nextDouble();

        System.out.print("Informe a precisao [default 0.01]: ");
        precisao = in.nextDouble();

        System.out.print("Quantidade de casas apos a virgula: ");
        qtdAposVirgula = in.nextInt();

        System.out.print("Informe uma quantidade  maxima de iteracao: ");
        maxInteracao = in.nextInt();


        //Calcs
        int i=0;
        while (i <= maxInteracao) {
            fxAtual = calculaFx(xAtual);
            fxAnterior = calculaFx(xAnterior);

            proximoXAtual = calculaProximoXAtual(xAtual, xAnterior, fxAtual, fxAnterior); //atualiza x atual

            exibirResultado(xAnterior, xAtual, precisao, fxAtual, fxAnterior,proximoXAtual, BD_erroAbs, BD_erroRel, i, qtdAposVirgula);

            xAnterior = xAtual; //atualiza x anterior
            xAtual = proximoXAtual;

            BD_erroAbs = calculaErroAbsoluto(xAtual, xAnterior);
            BD_erroRel = calculaErroRelativo(xAtual, xAnterior);

            // Critério de parada, (erro absoluto < precisao)
            if (BD_erroAbs.compareTo(BigDecimal.valueOf(precisao)) < 0) {
                System.out.println("Critério de parada atingido. \n");

                System.out.printf("Erro Absoluto(%."+qtdAposVirgula+"f) menor que a Precisao(%."+qtdAposVirgula+"f).\n",BD_erroAbs, precisao);
                System.out.printf("Raiz aproximada encontrada: %."+qtdAposVirgula+"f \n", xAtual);
                break;
            }
            i++;
        }
    }
}
