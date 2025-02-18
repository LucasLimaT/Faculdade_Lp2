package ifmt.cba.apps;

import java.util.List;
import ifmt.cba.util.EntityManagerUtil;
import ifmt.cba.vo.A1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@SuppressWarnings("unchecked")
public class AppA1c {

    public static void main(String args[]) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT a FROM A1 a");
            List<A1> listaA1 = query.getResultList();
            for (A1 a : listaA1) {
                System.out.println("Codigo A: " + a.getCodigo());
                System.out.println("Nome A: " + a.getNome());
                if (a.getB1() != null) {
                    System.out.println("Codigo B: " + a.getB1().getCodigo());
                    System.out.println("Nome B: " + a.getB1().getNome());
                }
                System.out.println("--------------------------------");
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
