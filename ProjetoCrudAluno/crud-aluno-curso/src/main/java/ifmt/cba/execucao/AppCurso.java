package ifmt.cba.execucao;

import java.util.List;

import javax.swing.JOptionPane;

import ifmt.cba.negocio.CursoNegocio;
import ifmt.cba.negocio.NegocioException;
import ifmt.cba.vo.CursoVO;

public class AppCurso {
    
    private static CursoNegocio cursoNegocio;

    static {
        try {
            cursoNegocio = new CursoNegocio();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        int selecao;

        do {
            selecao = menu();
            switch(selecao) {

                case 1:
                    processarInclusao();
                    break;
                case 2:
                    processarAlteracao();
                    break;
                case 3:
                    processarExclusao();
                    break;
                case 4:
                    SelecaoPorCodigo();
                    break;
                case 5:
                    SelecaoPorNome();
                    break;
                case 6:
                    ListarTodos();
                    break;
            }
        } while (selecao != 7);

        try {
            cursoNegocio.desconectar();
        } catch(NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        System.exit(0);
    }

    private static int menu() {
        int selecao = 0;
        String opcoes = "1 - Incluir Curso\n" +
                    "2 - Alterar Curso\n" +
                    "3 - Excluir Curso\n" +
                    "4 - Pesquisar Curso por Codigo\n" +
                    "5 - Pesquisar Curso por Nome\n" +
                    "6 - Listar todos os Cursos\n" +
                    "7 - Sair";
        do {
            selecao = Integer.parseInt(JOptionPane.showInputDialog(opcoes));
            if(selecao < 1 || selecao > 7) {
                JOptionPane.showMessageDialog(null, "Opcao invalida, repita a escolha");
            }
        }while (selecao < 1 || selecao > 7);
        return selecao;
    }

    private static void processarInclusao() {
        try {
            String nome = JOptionPane.showInputDialog("Forneca o nome do curso");
            int cargahoraria = Integer.parseInt(JOptionPane.showInputDialog("Forneca a carga horaria do curso"));
            int  numsemestre = Integer.parseInt(JOptionPane.showInputDialog("Forneca o novo numero de semestres do curso"));
            CursoVO cursoVO = new CursoVO();
            cursoVO.setNome(nome);
            cursoVO.setCargahoraria(cargahoraria);
            cursoVO.setNumsemestre(numsemestre);
            cursoNegocio.incluir(cursoVO);
            JOptionPane.showMessageDialog(null, "Inclusao realizada com sucesso");
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void processarAlteracao() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Forneca o codigo do curso a ser Alterado"));
            CursoVO cursoVO = cursoNegocio.buscaPorCodigo(codigo);
            if (cursoVO != null) {
                String nome = cursoVO.getNome();
                nome = JOptionPane.showInputDialog("Forneca o novo nome do curso", nome);
                int cargahoraria = cursoVO.getCargahoraria();
                cargahoraria = Integer.parseInt(JOptionPane.showInputDialog("Forneca a nova carga horaria do curso", cargahoraria));
                int numsemestre = cursoVO.getNumsemestre();
                numsemestre = Integer.parseInt(JOptionPane.showInputDialog("Forneca o novo numero de semestres do cursso", numsemestre));
                cursoVO.setNome(nome);
                cursoVO.setCargahoraria(cargahoraria);
                cursoVO.setNumsemestre(numsemestre);
                cursoNegocio.alterar(cursoVO);
                JOptionPane.showMessageDialog(null, "Alteracao realizada com sucesso");
            }else {
                JOptionPane.showMessageDialog(null, "Curso nao localizado");
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void processarExclusao() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Forneca o codigo do curso a ser Excluido"));
            CursoVO cursoVO = cursoNegocio.buscaPorCodigo(codigo);
            if (cursoVO != null) {
                if (JOptionPane.showConfirmDialog(null, "Confirma a Exclusao do curso" + cursoVO.getNome(), "Exclusao", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    cursoNegocio.excluir(cursoVO.getCodigo());
                    JOptionPane.showMessageDialog(null, "Exclusao realizada com sucesso");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Curso nao localizado");
            }
        } catch ( NegocioException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void SelecaoPorCodigo() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Forneca o codigo do curso a ser localizado"));
            CursoVO cursoVO = cursoNegocio.buscaPorCodigo(codigo);
            if (cursoVO != null) {
                System.out.println("Codigo: " + cursoVO.getCodigo());
                System.out.println("Nome: " + cursoVO.getNome());
                System.out.println("Carga Horaria: " + cursoVO.getCargahoraria());
                System.out.println("Numero de Semestres: " + cursoVO.getNumsemestre());
                System.out.println("-------------------------------------------------------------------");
            } else {
                System.out.println("Nao localizado");
            }
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void SelecaoPorNome() {
        try {
            String nome = JOptionPane.showInputDialog("Forneca parte do nome do curso a ser localizado");
            List<CursoVO> listaCurso = cursoNegocio.buscaPorNome(nome);
            if (listaCurso.size() > 0) {
                for (CursoVO cursoVO : listaCurso) {
                    System.out.println("Codigo: " + cursoVO.getCodigo());
                    System.out.println("Nome: " + cursoVO.getNome());
                    System.out.println("Carga Horaria: " + cursoVO.getCargahoraria());
                    System.out.println("Numero de Semestres: " + cursoVO.getNumsemestre());
                    System.out.println("-------------------------------------------------------------------");
                }
            } else {
                System.out.println("Curso nao localizado");
            }
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void ListarTodos() {
        try {
            List<CursoVO> listaCurso = cursoNegocio.buscaTodos();
            if (listaCurso.size() > 0) {
                for(CursoVO cursoVO : listaCurso) {
                    System.out.println("Codigo: " + cursoVO.getCodigo());
                    System.out.println("Nome: " + cursoVO.getNome());
                    System.out.println("Carga Horaria: " + cursoVO.getCargahoraria());
                    System.out.println("Numero de Semestres: " + cursoVO.getNumsemestre());
                    System.out.println("-------------------------------------------------------------------");
                }
            } else {
                System.out.println("Nenhum curso cadastrado");
            }
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
