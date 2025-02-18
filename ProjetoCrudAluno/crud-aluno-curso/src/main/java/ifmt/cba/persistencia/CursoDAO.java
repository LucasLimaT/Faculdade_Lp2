package ifmt.cba.persistencia;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifmt.cba.vo.CursoVO;

public class CursoDAO extends DAO{

    private static PreparedStatement comandoInclusao;
    private static PreparedStatement comandoAlteracao;
    private static PreparedStatement comandoExclusao;
    private static PreparedStatement comandoSelecaoCodigo;
    private static PreparedStatement comandoSelecaoNome;

    public CursoDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);
        try {
            comandoInclusao = conexao.getConexao().prepareStatement("INSERT INTO curso ( nome, cargahoraria, numsemestre ) VALUES (?, ?, ?)");
            comandoAlteracao = conexao.getConexao().prepareStatement("UPDATE curso SET nome=?, cargahoraria=?, numsemestre=? WHERE codigo=?");
            comandoExclusao = conexao.getConexao().prepareStatement("DELETE FROM curso WHERE codigo=?");
            comandoSelecaoCodigo = conexao.getConexao().prepareStatement("SELECT * FROM curso WHERE codigo=?");
            comandoSelecaoNome = conexao.getConexao().prepareStatement("SELECT * FROM curso WHERE upper(nome) LIKE ?");
        } catch (Exception ex) {
            throw new PersistenciaException("Erro ao preparar a persistencia - " + ex.getMessage());
        }
    }

    public void incluir(CursoVO cursoVO) throws PersistenciaException {
        try {
            comandoInclusao.setString(1, cursoVO.getNome());
            comandoInclusao.setInt(2, cursoVO.getCargahoraria());
            comandoInclusao.setInt(3, cursoVO.getNumsemestre());
            comandoInclusao.executeUpdate();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao incluir Curso - " + sqle.getMessage());
        }
    }

    public void alterar(CursoVO cursoVO) throws PersistenciaException {
        try {
            comandoAlteracao.setString(1, cursoVO.getNome());
            comandoAlteracao.setInt(2, cursoVO.getCargahoraria());
            comandoAlteracao.setInt(3, cursoVO.getNumsemestre());
            comandoAlteracao.setInt(4, cursoVO.getCodigo());
            comandoAlteracao.executeUpdate();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao alterar Curso - " + sqle.getMessage());
        }
    }

    public void excluir(int codigo) throws PersistenciaException {
        try {
            comandoExclusao.setInt(1, codigo);
            comandoExclusao.executeUpdate();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao exlcuir Curso - " + sqle.getMessage());
        }
    }

    public CursoVO buscaPorCodigo(int codigo) throws PersistenciaException {
        CursoVO retorno = null;
        ResultSet resultado = null;
        try {
            comandoSelecaoCodigo.setInt(1, codigo);
            resultado = comandoSelecaoCodigo.executeQuery();
            if (resultado.next()) {
                retorno = this.montaVO(resultado);
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao selecionar Curso por codigo - " + sqle.getMessage());
        }
        return retorno;
    }

    public List<CursoVO> buscaPorNome(String nome) throws PersistenciaException {
        List<CursoVO> retorno =  new ArrayList<CursoVO>();
        ResultSet resultado = null;
        try {
            comandoSelecaoNome.setString(1, "%" + nome.toUpperCase() + "%");
            resultado = comandoSelecaoNome.executeQuery();
            while(resultado.next()) {
                retorno.add(this.montaVO(resultado));
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Errro ao selecionar Curso por nome - " + sqle.getMessage());
        }
        return retorno;
    }

    public List<CursoVO> buscaTodos() throws PersistenciaException {
        List<CursoVO> retorno = new ArrayList<CursoVO>();
        ResultSet resultado = null;
        try {
            Statement comandoBuscaTodos = this.conexao.getConexao().createStatement();
            resultado = comandoBuscaTodos.executeQuery("Select * FROM curso ORDER BY nome");
            while(resultado.next()) {
                retorno.add(this.montaVO(resultado));
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao selecionar Curso por nomme - " + sqle.getMessage());
        }
        return retorno;
    }

    private CursoVO montaVO (ResultSet resultado) throws PersistenciaException {
        CursoVO cursoVOTemp = new CursoVO();

        if (resultado != null) {
            try {
                cursoVOTemp.setCodigo(resultado.getInt("codigo"));
                cursoVOTemp.setNome(resultado.getString("nome"));
                cursoVOTemp.setCargahoraria(resultado.getInt("cargahoraria"));
                cursoVOTemp.setNumsemestre(resultado.getInt("numsemestre"));
            } catch (SQLException ex) {
                throw new PersistenciaException("Erro no MOR de Curso");
            }
        }
        return cursoVOTemp;
    }

    public void desconectar() throws PersistenciaException {
        try {
            comandoInclusao.close();
            comandoAlteracao.close();
            comandoExclusao.close();
            comandoSelecaoCodigo.close();
            comandoSelecaoNome.close();
            conexao.desconectar();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao desconectar com o banco de dados - " + sqle.getMessage());
        }
    }
}
