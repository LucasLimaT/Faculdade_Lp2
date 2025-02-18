package lpii.trabalhofinal.consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.PessoaJuridica;

public class ConsultaPessoaJuridica {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT pj FROM PessoaJuridica pj ORDER BY pj.nomeFantasia";
            TypedQuery<PessoaJuridica> query = entityManager.createQuery(jpql, PessoaJuridica.class);
            List<PessoaJuridica> pessoasJuridicas = query.getResultList();
            for (PessoaJuridica pj : pessoasJuridicas) {
                System.out.println("Pessoa Jurídica:\n\tCódigo: " + pj.getCodigo() + "\n\tNome Fantasia: " + pj.getNomeFantasia() + "\n\tCNPJ: " + pj.getCnpj());
            }
        } finally {
            entityManager.close();
        }
    }
}
