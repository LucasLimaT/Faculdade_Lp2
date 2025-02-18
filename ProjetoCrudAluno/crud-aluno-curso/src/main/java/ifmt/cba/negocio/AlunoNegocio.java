package ifmt.cba.negocio;

import java.util.List;

import ifmt.cba.persistencia.AlunoDAO;
import ifmt.cba.persistencia.ConexaoBD;
import ifmt.cba.persistencia.PersistenciaException;
import ifmt.cba.vo.AlunoVO;

public class AlunoNegocio {

    private AlunoDAO alunoDAO;

    public AlunoNegocio() throws NegocioException {
        try {
            this.alunoDAO = new AlunoDAO(ConexaoBD.getInstancia());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Errro ao iniciar a Persistencia - " + ex.getMessage());
        }
    }

    public void inserir(AlunoVO alunoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(alunoVO);
            if (validacao.isEmpty()) {
                this.alunoDAO.incluir(alunoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para a operacao: \n" + validacao);
            }
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao incluir Aluno - " + sqle.getMessage());
        }
    }

    public void alterar(AlunoVO alunoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(alunoVO);
            if (validacao.isEmpty()) {
                this.alunoDAO.alterar(alunoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para a operacao: \n" + validacao);
            }
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao alterar Aluno - " + sqle.getMessage());
        }
    }

    public void excluir(int codigo) throws NegocioException {
        try {
          alunoDAO.excluir(codigo);  
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao excluir o aluno - " + ex.getMessage());
        } 
    }

    public List<AlunoVO> pesquisaParteNome(String parteNome) throws NegocioException {
        List<AlunoVO> listaAluno = null;
        try {
            listaAluno = alunoDAO.buscarPorNome(parteNome);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao pesquisar aluno pelo nome - " + ex.getMessage());
        }
        return listaAluno;
    }

    public AlunoVO pesquisaMatricula(int matricula) throws NegocioException {
        try {
            return alunoDAO.buscarPorMatricula(matricula);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao pesquisar aluno pela matricula - " + ex.getMessage());
        }
    }

    public void desconectar() throws NegocioException {
        try {
            this.alunoDAO.desconectar();
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao desconectar com o banco de dados - " + sqle.getMessage());
        }
    }

    private String validarDados(AlunoVO alunoVO) {
        
        String mensagemErros = "";

        if (alunoVO.getNome() == null || alunoVO.getNome().length() == 0) {
            mensagemErros += "Nome do aluno nao pode ser vazio";
        }

        if (alunoVO.getDataNascimento() == null) {
            mensagemErros += "Data de nascimento do aluno nao pode ser vazio";
        }

        if (alunoVO.getNomeMae() == null || alunoVO.getNomeMae().length() == 0) {
            mensagemErros += "\nNome da mae nao pode ser vazio";
        }

        if (alunoVO.getNomePai() == null || alunoVO.getNomePai().length() == 0) {
            mensagemErros += "\nNome do pai nao pode ser vazio";
        }

        if (alunoVO.getSexo() == null) {
            mensagemErros += "\nSexo nao pode ser nulo";
        }

        if (alunoVO.getEndereco().getLogradouro() == null || alunoVO.getEndereco().getLogradouro().length() == 0) {
            mensagemErros += "\nLogradouro nao pode ser vazio";
        }

        if (alunoVO.getEndereco().getNumero() <= 0) {
            mensagemErros += "\nNumero deve ser maior que zero";
        }

        if (alunoVO.getEndereco().getBairro() == null || alunoVO.getEndereco().getBairro().length() == 0) {
            mensagemErros += "\nBairro nao pode ser vazio";
        }

        if (alunoVO.getEndereco().getCidade() == null || alunoVO.getEndereco().getCidade().length() == 0) {
            mensagemErros += "\nCidade nao pode ser vazio";
        }

        if (alunoVO.getEndereco().getUf() == null) {
            mensagemErros += "\nUF nao pode ser vazio";
        }

        if (alunoVO.getCurso() == null) {
            mensagemErros += "\nCurso nao pode ser vazio";
        }
        return mensagemErros;
    }
}
