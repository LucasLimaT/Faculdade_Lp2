package lpii.trabalhofinal.consultas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.PessoaFisica;

public class ConsultaPessoaFisica {
    public static void main(String[] args) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            String jpql = "SELECT pf FROM PessoaFisica pf ORDER BY pf.nome";
            TypedQuery<PessoaFisica> query = entityManager.createQuery(jpql, PessoaFisica.class);
            List<PessoaFisica> pessoasFisicas = query.getResultList();
            for (PessoaFisica pf : pessoasFisicas) {
                System.out.println("Pessoa Física:\n\tCódigo: " + pf.getCodigo() + "\n\tNome: " + pf.getNome() + "\n\tRG: " + pf.getRG() + "\n\tCPF: " + pf.getCPF());
            }
        } finally {
            entityManager.close();
        }
    }
}
