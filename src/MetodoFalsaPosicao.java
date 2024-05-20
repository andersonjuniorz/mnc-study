import java.math.BigDecimal;
import java.util.Scanner;

/*
 * Metodo da falsa posicao
 * Intervalo = [0.5 , 1]
 * Precisao de 0.01  ||   10^-2
 * Considere  F(x) = x^3-9x+5
 */
public class MetodoFalsaPosicao {

    /* F(x) = x^3 - 9x + 5 */
    public static double calculaF(double x){
        return Math.pow(x,3)-9*x+5;
    }

    public static double calculaX(double a, double b, double fa,  double fb){
        return (a * fb - b * fa) / (fb - fa);
    }

    /* Erro Absoluto */
    public static double calculaErroAbsoluto(double a, double b){
        return Math.abs(b - a);
    }

    /* Erro Relativo */
    public static double calculaErroRelativo(double a, double b){
        return (b != 0) ? Math.abs((b - a) / b) : 0;
    }

    /* Print dos valores */
    public static void exibirResultado(double a, double b, double precisao, double x, double fa, double fb, double fx, double  erroAbs, double  erroRel, int i, int qtdAposVirgula){
        System.out.println();
        System.out.println("************************* ");
        System.out.println("****** ITERACAO "+ i + " ******* ");
        System.out.println("************************* ");

        System.out.printf("A Atual: %." + qtdAposVirgula +"f \n", a);
        System.out.printf("B Atual: %." + qtdAposVirgula +"f \n", b);
        System.out.printf("precisão: %." + qtdAposVirgula +"f \n" , precisao);
        System.out.printf("x: %." + qtdAposVirgula +"f \n", x);
        System.out.printf("f(a): %." + qtdAposVirgula +"f \n", fa);
        System.out.printf("f(b): %." + qtdAposVirgula +"f \n", fb);
        System.out.printf("f(x): %." + qtdAposVirgula +"f \n", fx);
        System.out.printf("Erro Absoluto: %." + qtdAposVirgula +"f \n", erroAbs);
        System.out.printf("Erro Relativo: %." + qtdAposVirgula +"f \n", erroRel);

        System.out.println();
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int maxInteracao = 0;
        int qtdAposVirgula = 3; //default 3 casas apos a virgula

        double a = 0, b = 0;           //intervalo A, intervalo B
        double x = 0;                  //Estimativa atual da raiz e utilizada para atualizar os intervalos
        double fa = 0, fb = 0, fx = 0;
        double precisao = 0.001;          //Precisao default
        double erroAbs  = 0, erroRel = 0; //Erro Absoluto, Erro Relativo


        /* Entrada de dados */
        System.out.print("Intervalo inicial A (Ex.: 0.5): ");
        a = in.nextDouble(); //Intervalo A

        System.out.print("Intervalo final B (Ex.: 1): ");
        b = in.nextDouble(); //Intervalo B

        System.out.print("Informe a precisao [default 0.01]: ");
        precisao = in.nextDouble();

        System.out.print("Quantidade de casas apos a virgula: ");
        qtdAposVirgula = in.nextInt();

        System.out.print("Quantidade  maxima de iteracao: ");
        maxInteracao = in.nextInt();

        in.close(); //Fechando scanner de input

        int i = 0;
        while(i <= maxInteracao) {

            fa = calculaF(a);
            fb = calculaF(b);
            x = calculaX(a, b, fa, fb);
            fx = calculaF(x);

            erroAbs = calculaErroAbsoluto(a, b);
            erroRel = calculaErroRelativo(a, b);

            exibirResultado( a, b, precisao, x, fa, fb, fx, erroAbs, erroRel, i, qtdAposVirgula);

            if (fa * fx > 0) { a = x; } //Atualiza intervalos se fafx forem maior do que 0
            if (fb * fx > 0) { b = x; } //Atualiza intervalos se fbfx forem maior do que 0

            /* Critério de parada, Se (Erro Absoluto ou Modulo de Fx < precisao) */
            if (erroAbs < precisao || Math.abs(fx) < precisao) {
                System.out.println("Critério de parada atingido. \n");

                System.out.printf("Erro absoluto (%."+qtdAposVirgula+"f) ou f(x) (%."+qtdAposVirgula+"f) menor que a precisão (%."+qtdAposVirgula+"f).\n", erroAbs, fx, precisao);
                System.out.printf("Raiz aproximada encontrada: %."+qtdAposVirgula+"f \n", x);
                break;
            }
            i++;
        }
    }
}
