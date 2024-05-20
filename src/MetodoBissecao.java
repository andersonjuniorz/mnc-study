import java.math.BigDecimal;
import java.util.Scanner;

/*
 * Metodo da Bisseccao
 * A Intervalo Inicial = 0
 * B Intervalo Final = 1
 * Precisao de 0.1 ||  10^-1
 * Considere F(x) = x^3-9x+3
 */
public class MetodoBissecao {

    /*
     * Formula aplicada - F(?) = x^3-9x+3
     * Este metodo serve para descobrir o F(a), F(b) e o F(media), onde x sera o valor que eu quero descobrir
     */
    public static double calculaFx(double x){
        return Math.pow(x,3)-9*x+3;
    }
    public static double calculaMedia(double a,double b){
        return (a+b)/2;
    }

    /* Print */
    public static void exibirResultado(double a, double b, double media, double precisao ,double  fa, double  fb, double  fmedia, double  bMenosA, int i, int qtdAposVirgula){
        System.out.println();
        System.out.println("************************* ");
        System.out.println("****** ITERACAO "+ i + " ******* ");
        System.out.println("************************* ");

        System.out.printf("Intervalo A: %." + qtdAposVirgula +"f \n", a);
        System.out.printf("Intervalo B: %." + qtdAposVirgula +"f \n", b);
        System.out.printf("Media: %." + qtdAposVirgula +"f \n", media);
        System.out.printf("precisão: %." + qtdAposVirgula +"f \n" , precisao);
        System.out.printf("f(a): %." + qtdAposVirgula +"f \n", fa);
        System.out.printf("f(b): %." + qtdAposVirgula +"f \n", fb);
        System.out.printf("f(media): %." + qtdAposVirgula +"f \n", fmedia);
        System.out.printf("B-A: %." + qtdAposVirgula +"f \n", bMenosA);

        System.out.println();
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int maxInteracao = 0;
        int qtdAposVirgula = 1;

        double fa=0, fb=0, fmedia=0; //F(a), F(b), F(media)
        double a=0, b=0, media=0;   // intervalo 1, intervalo 2, media
        double bMenosA= 0;     //B-A
        double precisao = 0.1;

        /* Entrada de dados */
        System.out.print("Informe o primeiro intervalo: ");
        a = in.nextDouble();

        System.out.print("Informe o segundo intervalo: ");
        b = in.nextDouble();

        System.out.print("Informe a precisao [default 0.1]: ");
        precisao = in.nextDouble();

        System.out.print("Quantidade de casas apos a virgula: ");
        qtdAposVirgula = in.nextInt();

        System.out.print("Informe uma quantidade  maxima de iteracao: ");
        maxInteracao = in.nextInt();

        in.close(); //Fechando scanner de input

        /* Calculos */
        int i=0;
        while(i <= maxInteracao) {

            media = calculaMedia(a, b);
            fa = calculaFx(a);
            fb = calculaFx(b);
            fmedia = calculaFx(media);
            bMenosA = b - a;

            exibirResultado(a, b, media, precisao, fa, fb, fmedia, bMenosA, i, qtdAposVirgula);

            if (fa * fmedia > 0) { a = media; } //Atualiza o A apenas se o fa e fmedia forem maior do que 0
            if (fb * fmedia > 0) { b = media; } //Atualiza o B apenas se o fb e fmedia forem maior do que 0

            if (Math.abs(bMenosA) < precisao) {
                System.out.println("Precisão desejada atingida. Raiz aproximada encontrada: " + media);
                break;
            }
            i++;
        }
    }
}
