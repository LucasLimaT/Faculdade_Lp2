package ifmt.cba.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory entityManagerFactory = null;

    private EntityManagerUtil(){} // para impedir instanciamento
    
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("BDRelacoes");
    }

    public static EntityManager getEntityManager() throws Exception {
        if (entityManagerFactory == null) {
            throw new Exception("Unidade de Persistencia nao iniciada");
        }
        return entityManagerFactory.createEntityManager();
    }
}

