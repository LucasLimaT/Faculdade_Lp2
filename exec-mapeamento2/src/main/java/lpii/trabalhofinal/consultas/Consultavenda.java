package lpii.trabalhofinal.consultas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.ItemVenda;
import lpii.trabalhofinal.vo.Venda;

public class Consultavenda {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Venda v ORDER BY v.dataVenda";
            TypedQuery<Venda> query = entityManager.createQuery(jpql, Venda.class);
            List<Venda> vendas = query.getResultList();
            for (Venda v : vendas) {
                System.out.println("Venda:\n\tCódigo: " + v.getCodigo() + "\n\tData: " + v.getDataVenda());
                if (v.getItem_venda() != null) {
                    for (ItemVenda iv : v.getItem_venda()) {
                        System.out.println("\nItem:\n\tCódigo: " + iv.getCodigo() + "\n\tQuantidade: " + iv.getQuantidade() + "\n\tPreço: " + iv.getPrecoVenda() + "\n\tDesconto: " + iv.getPerDesconto());
                    }
                }
            }
        } finally {
            entityManager.close();
        }
    }
}
