package lpii.trabalhofinal.consultas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Pessoa;

public class ConsultaPessoa {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Pessoa p ORDER BY p.nome";
            TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
            List<Pessoa> pessoas = query.getResultList();
            for (Pessoa p : pessoas) {
                System.out.println("Pessoa:\n\tCódigo: " + p.getCodigo() + "\n\tNome: " + p.getNome());
            }
        } finally {
            entityManager.close();
        }
    }
}
