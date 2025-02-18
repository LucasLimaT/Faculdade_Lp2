package ifmt.cba.execucao;

import java.util.List;
import javax.swing.JOptionPane;
import ifmt.cba.negocio.AlunoNegocio;
import ifmt.cba.negocio.CursoNegocio;
import ifmt.cba.negocio.NegocioException;
import ifmt.cba.util.DataUtil;
import ifmt.cba.vo.AlunoVO;
import ifmt.cba.vo.CursoVO;
import ifmt.cba.vo.EnumSexo;
import ifmt.cba.vo.EnumUF;

public class AppAluno {

    private static AlunoNegocio alunoNegocio;
    private static CursoNegocio cursoNegocio;

    public static void main(String[] args) {
        try {
            alunoNegocio = new AlunoNegocio();
            cursoNegocio = new CursoNegocio();
        } catch (NegocioException ex) {
            System.out.println("Camada de negocio e persistencia nao iniciada - " + ex.getMessage());
        }

        if (alunoNegocio != null) {
            EnumMenu opcao = EnumMenu.IncluirAluno;
            do {
                try {
                    opcao = exibirMenu();
                    switch (opcao) {
                        case IncluirAluno:
                            incluirAluno();
                            break;
                        case AlterarAluno:
                            alterarAluno();
                            break;
                        case ExcluirAluno:
                            excluirAluno();
                            break;
                        case PesqMatricula:
                            pesquisarPorMatricula();
                            break;
                        case PesqNome:
                            pesquisarPorNome();
                            break;
                    }
                } catch (NegocioException ex) {
                    System.out.println("Operacao nao realizada corretamente - " + ex.getMessage());
                }
            } while (opcao != EnumMenu.Sair);
        }
        System.exit(0);
    }

    private static void incluirAluno() throws NegocioException {
        AlunoVO alunoTemp = lerDados();
        alunoNegocio.inserir(alunoTemp);
    }

    private static void alterarAluno() throws NegocioException {
        int matricula = 0;
        try {
            matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneca a matricula do Aluno", 
                "Leitura de Dados", JOptionPane.QUESTION_MESSAGE));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Digitacao inconsistente - " + ex.getMessage());
        }

        AlunoVO alunoVO = alunoNegocio.pesquisaMatricula(matricula);
        if (alunoVO != null) {
            AlunoVO alunoTemp = lerDados(alunoVO);
            alunoNegocio.alterar(alunoTemp);
        } else {
            JOptionPane.showMessageDialog(null, "Aluno nao localizado");
        }
    }

    private static void excluirAluno() throws NegocioException {
        int matricula = 0;
        try {
            matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneca a matricula do Aluno", 
                "Leitura de Dados", JOptionPane.QUESTION_MESSAGE));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Digitacao inconsistente - " + ex.getMessage());
        }

        AlunoVO alunoVO = alunoNegocio.pesquisaMatricula(matricula);
        if (alunoVO != null) {
            alunoNegocio.excluir(alunoVO.getMatricula());
        } else {
            JOptionPane.showMessageDialog(null, "Aluno nao localizado");
        }
    }

    private static void pesquisarPorMatricula() throws NegocioException {
        int matricula = 0;
        try {
            matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneca a matricula do Aluno", 
                "Leitura de Dados", JOptionPane.QUESTION_MESSAGE));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Digitacao inconsistente - " + ex.getMessage());
        }

        AlunoVO alunoVO = alunoNegocio.pesquisaMatricula(matricula);
        if (alunoVO != null) {
            mostrarDados(alunoVO);
        } else {
            JOptionPane.showMessageDialog(null, "Aluno nao localizado");
        }
    }

    private static void pesquisarPorNome() throws NegocioException {
        String nome = JOptionPane.showInputDialog(null, "Forneca o nome do Aluno", 
            "Leitura de Dados", JOptionPane.QUESTION_MESSAGE);
        if (nome != null) {
            List<AlunoVO> listaAlunoVO = alunoNegocio.pesquisaParteNome(nome);
            if (listaAlunoVO.size() > 0) {
                for (AlunoVO alunoVO : listaAlunoVO) {
                    mostrarDados(alunoVO);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Aluno nao localizado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome nao pode ser nulo");
        }
    }

    private static void mostrarDados(AlunoVO alunoVO) {
        if (alunoVO != null) {
            System.out.println("Matricula: " + alunoVO.getMatricula());
            System.out.println("Data de Nascimento: " + alunoVO.getDataNascimento());
            System.out.println("Nome: " + alunoVO.getNome());
            System.out.println("Nome da Mae: " + alunoVO.getNomeMae());
            System.out.println("Nome da Pai: " + alunoVO.getNomePai());
            System.out.println("Sexo: " + alunoVO.getSexo().name());
            System.out.println("Curso: " + alunoVO.getCurso());
            if (alunoVO.getEndereco() != null) {
                System.out.println("Logradouro: " + alunoVO.getEndereco().getLogradouro());
                System.out.println("Numero: " + alunoVO.getEndereco().getNumero());
                System.out.println("Bairro: " + alunoVO.getEndereco().getBairro());
                System.out.println("Cidade: " + alunoVO.getEndereco().getCidade());
                System.out.println("UF: " + alunoVO.getEndereco().getUf());
                System.out.println("---------------------------------------------------");
            }
        }
    }

    private static AlunoVO lerDados(AlunoVO alunoTemp) {
        String nome, nomeMae, nomePai, logradouro, bairro, cidade;
        int numero;
        EnumSexo sexo;
        EnumUF uf;
        DataUtil dataNascimento;
        CursoVO curso;

        try {
            nome = JOptionPane.showInputDialog("Forneca o nome do Aluno", alunoTemp.getNome().trim());
            alunoTemp.setNome(nome);

            dataNascimento = DataUtil.stringToDate(JOptionPane.showInputDialog("Forneca a data de nascimento do Aluno", 
                alunoTemp.getDataNascimento()));
            alunoTemp.setDataNascimento(dataNascimento);

            nomeMae = JOptionPane.showInputDialog("Forneca o nome da mae do Aluno", alunoTemp.getNomeMae().trim());
            alunoTemp.setNomeMae(nomeMae);

            nomePai = JOptionPane.showInputDialog("Forneca o nome do pai do Aluno", alunoTemp.getNomePai().trim());
            alunoTemp.setNomePai(nomePai);

            sexo = (EnumSexo) JOptionPane.showInputDialog(null, "Escolha uma Opcao", "Leitura de Dados",
                JOptionPane.QUESTION_MESSAGE, null, EnumSexo.values(), alunoTemp.getSexo());
            alunoTemp.setSexo(sexo);

            logradouro = JOptionPane.showInputDialog("Forneca o logradouro do endereco", 
                alunoTemp.getEndereco().getLogradouro().trim());
            alunoTemp.getEndereco().setLogradouro(logradouro);

            numero = Integer.parseInt(JOptionPane.showInputDialog("Forneca o numero no endereco", 
                alunoTemp.getEndereco().getNumero()));
            alunoTemp.getEndereco().setNumero(numero);

            bairro = JOptionPane.showInputDialog("Forneca o bairro no endereco", 
                alunoTemp.getEndereco().getBairro().trim());
            alunoTemp.getEndereco().setBairro(bairro);

            cidade = JOptionPane.showInputDialog("Forneca a cidade no endereco", 
                alunoTemp.getEndereco().getCidade().trim());
            alunoTemp.getEndereco().setCidade(cidade);

            uf = (EnumUF) JOptionPane.showInputDialog(null, "Escolha um Estado", "Unidade da Federacao",
                JOptionPane.QUESTION_MESSAGE, null, EnumUF.values(), alunoTemp.getEndereco().getUf());
            alunoTemp.getEndereco().setUf(uf);

            List<CursoVO> listaCursos = cursoNegocio.buscaTodos();
            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha um Curso", "Cursos Disponiveis",
                JOptionPane.QUESTION_MESSAGE, null, listaCursos.toArray(), alunoTemp.getCurso());
            alunoTemp.setCurso(curso);
        } catch (Exception ex) {
            System.out.println("Digitacao inconsistente - " + ex.getMessage());
        }
        return alunoTemp;
    }

    private static AlunoVO lerDados() {
        AlunoVO alunoTemp = new AlunoVO();
        return lerDados(alunoTemp);
    }

    private static EnumMenu exibirMenu() {
        EnumMenu opcao;
        opcao = (EnumMenu) JOptionPane.showInputDialog(null, "Escolha uma Opcao", "Menu",
            JOptionPane.QUESTION_MESSAGE, null, EnumMenu.values(), EnumMenu.values()[0]);
        if (opcao == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma Opcao Escolhida");
            opcao = EnumMenu.Sair;
        }
        return opcao;
    }
}