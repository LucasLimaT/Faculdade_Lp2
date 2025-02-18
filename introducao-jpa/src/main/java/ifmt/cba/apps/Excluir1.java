package ifmt.cba.apps;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.swing.JOptionPane;

import ifmt.cba.vo.GrupoProdutoVO;

public class Excluir1 {

    public static void main(String args[]) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        GrupoProdutoVO grupoVO = null;

        try {
            emf = Persistence.createEntityManagerFactory("UnidadeProdutos");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            String pNome = JOptionPane.showInputDialog("Forneca o nome do grupo de produto a ser excluido");

            Query consulta = em.createQuery("SELECT gp FROM GrupoProdutoVO gp WHERE UPPER(gp.nome) = :pNome");
            consulta.setParameter("pNome", pNome.toUpperCase());
            List<GrupoProdutoVO> lista = consulta.getResultList();
            if (lista.size() > 0) {
                grupoVO = lista.get(0);
                em.remove(grupoVO);
                em.getTransaction().commit();
                System.out.println("Exclusao realizada com sucesso");
            } else {
                System.out.println("Grupo de Produto nao localizado");
            }
        } catch (Exception ex) {
            System.out.println("Exclusao nao realizada - " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
