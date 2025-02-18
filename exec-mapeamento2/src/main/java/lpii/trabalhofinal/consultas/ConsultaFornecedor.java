package lpii.trabalhofinal.consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Fornecedor;
import lpii.trabalhofinal.vo.Produto;

public class ConsultaFornecedor {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Fornecedor f ORDER BY f.nomeFantasia";
            TypedQuery<Fornecedor> query = entityManager.createQuery(jpql, Fornecedor.class);
            List<Fornecedor> fornecedores = query.getResultList();
            for (Fornecedor f : fornecedores) {
                System.out.println("Fornecedor: \n\tCódigo: " + f.getCodigo() + "\n\tNome: " + f.getNomeFantasia());
                if (f.getProdutos() != null) {
                    for (Produto p : f.getProdutos()) {
                        System.out.println("\tProduto: \n\tCódigo: " + p.getCodigo() + "\n\tNome: " + p.getNome());
                    }
                }
            }
        } finally {
            entityManager.close();
        }
    }
}