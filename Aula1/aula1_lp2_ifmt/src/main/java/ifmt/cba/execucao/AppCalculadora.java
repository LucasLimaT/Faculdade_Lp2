package ifmt.cba.execucao;

import ifmt.cba.negocio.Calculadora;

public class AppCalculadora 
{
    public static void main( String[] args )
    {
        Calculadora calculadora = new Calculadora();
        System.out.println("______________________________");
        System.out.println("Soma de 10 + 30 = " + calculadora.somar(10, 30));
        System.out.println("Subtracao de 30 - 15 = " + calculadora.subtrair(30, 15));
        System.out.println("Multiplicacao de 20 * 30 = " + calculadora.multiplicar(20, 30));
        System.out.println("Divisao de 50 / 2 = " + calculadora.dividir(50, 2));
        System.out.println("______________________________");
    }
}
