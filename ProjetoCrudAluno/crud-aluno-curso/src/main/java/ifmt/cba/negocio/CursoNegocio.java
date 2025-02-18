package ifmt.cba.negocio;

import java.util.List;

import ifmt.cba.persistencia.ConexaoBD;
import ifmt.cba.persistencia.CursoDAO;
import ifmt.cba.persistencia.PersistenciaException;
import ifmt.cba.vo.CursoVO;

public class CursoNegocio {

    private CursoDAO cursoDAO;

    public CursoNegocio() throws NegocioException {
        try {
            this.cursoDAO = new CursoDAO(ConexaoBD.getInstancia());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Erro ao preparar a persistencia - " + ex.getMessage());
        }
    }

    public void incluir(CursoVO cursoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(cursoVO);
            if (validacao.isEmpty()) {
                this.cursoDAO.incluir(cursoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para a operacao: \n" + validacao);
            }
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao incluir Curso - " + sqle.getMessage());
        }
    }

    public void alterar(CursoVO cursoVO) throws NegocioException {
        try {
            String validacao = this.validarDados(cursoVO);
            if (validacao.isEmpty()) {
                this.cursoDAO.alterar(cursoVO);
            } else {
                throw new NegocioException("Dados inconsistentes para a operacao: \n" + validacao);
            }
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao alterar Curso - " + sqle.getMessage()); 
        }
    }

    public void excluir(int codigo) throws NegocioException {
        try {
            this.cursoDAO.excluir(codigo);
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao excluir Curso - " + sqle.getMessage());
        }
    }

    public CursoVO buscaPorCodigo(int codigo) throws NegocioException {
        CursoVO retorno = null;
        try {
            retorno = this.cursoDAO.buscaPorCodigo(codigo);
        } catch (PersistenciaException sqle) {
            throw new NegocioException(sqle.getMessage());
        }
        return retorno;
    }

    public List<CursoVO> buscaPorNome(String nome) throws NegocioException {
        List<CursoVO> retorno = null;
        try {
            retorno = this.cursoDAO.buscaPorNome(nome);
        } catch (PersistenciaException sqle) {
            throw new NegocioException(sqle.getMessage());
        }
        return retorno;
    }

    public List<CursoVO> buscaTodos() throws NegocioException {
        
        List<CursoVO> retorno = null;
        try {
            retorno = this.cursoDAO.buscaTodos();
        } catch (PersistenciaException sqle) {
            throw new NegocioException(sqle.getMessage());
        }
        return retorno;
    }

    public void desconectar() throws NegocioException {
        try {
            this.cursoDAO.desconectar();
        } catch (PersistenciaException sqle) {
            throw new NegocioException("Erro ao desconectar com o banco de dados - " + sqle.getMessage());
        }
    }

    private String validarDados(CursoVO cursoVO) {
        String retorno = "";

        if (cursoVO == null) {
            retorno += "Dados de curso nao podem ser nulos";
        } else {
            
            if (cursoVO.getNome() == null || cursoVO.getNome().isEmpty()) {
                retorno += "Nome de curso nao podem ser nulo";
            }
            
            if (cursoVO.getCargahoraria() <= 0) {
                retorno += "Carga horaria de curso deve ser maior que zero";
            }

            if (cursoVO.getNumsemestre() <= 0) {
                retorno += "Numero de semestre de curso deve ser maior que zero";
            }
        }
        return retorno;
    }
}
