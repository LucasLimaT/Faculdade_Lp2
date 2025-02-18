package lpii.trabalhofinal.consultas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Venda;
import lpii.trabalhofinal.vo.Vendedor;

public class ConsultaVendedor {
        public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Vendedor v ORDER BY v.nome";
            TypedQuery<Vendedor> query = entityManager.createQuery(jpql, Vendedor.class);
            List<Vendedor> vendedores = query.getResultList();
            for (Vendedor v : vendedores) {
                System.out.println("Vendedor:\n\tCódigo: " + v.getCodigo() + "\n\tNome: " + v.getNome() + "\n\tComissão: " + v.getPerComissao() + "%");
                if (v.getVendas() != null) {
                    for (Venda venda : v.getVendas()) {
                        double comissaoVenda = venda.getValorTotal() * v.getPerComissao() / 100.0;
                        System.out.println("\nVenda:\n\tCódigo: " + venda.getCodigo() + "\n\tValor Total: " + venda.getValorTotal() + "\n\tComissão: " + comissaoVenda);
                    }
                }
            }
        } finally {
            entityManager.close();
        }
    }
}
