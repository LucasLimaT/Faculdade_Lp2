package ifmt.cba.persistencia;

public class PersistenciaException extends Exception{

    public PersistenciaException() {
        super("Erro na persistencia de dados");
    }

    public PersistenciaException(String msg) {
        super(msg);
    }
}
