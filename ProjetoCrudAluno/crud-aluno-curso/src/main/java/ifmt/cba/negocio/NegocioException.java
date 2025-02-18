package ifmt.cba.negocio;

public class NegocioException extends Exception{

    public NegocioException() {
        super("Erro na logica de negocio");
    }

    public NegocioException(String msg) {
        super(msg);
    }
}
