package lpii.trabalhofinal.consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Fornecedor;
import lpii.trabalhofinal.vo.Produto;

public class ConsultaProduto {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM Produto p ORDER BY p.nome";
            TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
            List<Produto> produtos = query.getResultList();
            for (Produto p : produtos) {
                System.out.println("Produto:\n\tCódigo: " + p.getCodigo() + "\n\tNome: " + p.getNome());
                if (p.getGrupo_produto() != null) {
                    System.out.println("\tGrupo: " + p.getGrupo_produto().getNome());
                }
                String jpqlFornec = "SELECT f FROM Fornecedor f JOIN f.produtos prod WHERE prod = :produto";
                TypedQuery<Fornecedor> queryFornec = entityManager.createQuery(jpqlFornec, Fornecedor.class);
                queryFornec.setParameter("produto", p);
                List<Fornecedor> fornecs = queryFornec.getResultList();
                for (Fornecedor f : fornecs) {
                    System.out.println("\tFornecedor:\n\tCódigo: " + f.getCodigo() + "\n\tNome: " + f.getNomeFantasia());
                }
            }
        } finally {
            entityManager.close();
        }
    }
}
