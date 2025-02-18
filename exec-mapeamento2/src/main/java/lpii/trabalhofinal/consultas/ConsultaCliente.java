package lpii.trabalhofinal.consultas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Cliente;

public class ConsultaCliente {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT c, COUNT(v) FROM Cliente c LEFT JOIN Venda v ON v.cliente = c GROUP BY c ORDER BY c.nome";
            TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
            List<Object[]> resultados = query.getResultList();
            for (Object[] result : resultados) {
                Cliente c = (Cliente) result[0];
                Long qtdVendas = (Long) result[1];
                System.out.println("Cliente:\n\tCódigo: " + c.getCodigo() + "\n\tNome: " + c.getNome() + "\n\tVendas: " + qtdVendas);
            }
        } finally {
            entityManager.close();
        }
    }
}
