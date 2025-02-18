package ifmt.cba.negocio;

public class Calculadora {

    public double somar(double valor1, double valor2) {
        return valor1 + valor2;
    }

    public double subtrair(double valor1, double valor2) {
        return valor1 - valor2;
    }

    public double multiplicar(double valor1, double valor2) {
        return valor1 * valor2;
    }

    public double dividir(double valor1, double valor2) throws ArithmeticException {
        if (valor1 != 0) {
            return valor1 / valor2;
        } else {
            throw new ArithmeticException("Divisão por 0");
        }
    }
}
