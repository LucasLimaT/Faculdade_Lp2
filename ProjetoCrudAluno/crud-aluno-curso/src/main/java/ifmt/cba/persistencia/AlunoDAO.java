package ifmt.cba.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifmt.cba.util.DataUtil;
import ifmt.cba.vo.AlunoVO;
import ifmt.cba.vo.EnumSexo;
import ifmt.cba.vo.EnumUF;

public class AlunoDAO extends DAO {

    private static PreparedStatement comandoIncluir;
    private static PreparedStatement comandoAlterar;
    private static PreparedStatement comandoExcluir;
    private static PreparedStatement comandoBuscarPorMatricula;
    private CursoDAO cursoDAO;

    public AlunoDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);

        try {
            this.cursoDAO = new CursoDAO(conexao);
            comandoIncluir = conexao.getConexao().prepareStatement("INSERT INTO Aluno ( nome, datanasc, nomemae, nomepai, sexo, "
            + "logradouro, numero, bairro, cidade, uf, idcurso )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            comandoAlterar = conexao.getConexao().prepareStatement(
                "UPDATE Aluno SET nome=?, datanasc=?, nomemae=?, nomepai=?, sexo=?, "
                + "logradouro=?, numero=?, bairro=?, uf=?, idcurso=? WHERE matricula=?");
            comandoExcluir = conexao.getConexao().prepareStatement("DELETE FROM Aluno WHERE matricula = ?");
            comandoBuscarPorMatricula = conexao.getConexao().prepareStatement("SELECT * FROM Aluno WHERE matricula = ?");
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao incluir novo aluno - " + ex.getMessage());
        }
    }

    public void incluir(AlunoVO alunoVO) throws PersistenciaException {
        try {
            comandoIncluir.setString(1, alunoVO.getNome());
            comandoIncluir.setDate(2, alunoVO.getDataNascimento().getDataSQL());
            comandoIncluir.setString(3, alunoVO.getNomeMae());
            comandoIncluir.setString(4, alunoVO.getNomePai());
            comandoIncluir.setInt(5, alunoVO.getSexo().ordinal());
            comandoIncluir.setString(6, alunoVO.getEndereco().getLogradouro());
            comandoIncluir.setInt(7, alunoVO.getEndereco().getNumero());
            comandoIncluir.setString(8, alunoVO.getEndereco().getBairro());
            comandoIncluir.setString(9, alunoVO.getEndereco().getCidade());
            comandoIncluir.setString(10, alunoVO.getEndereco().getUf().name());
            comandoIncluir.setInt(11, alunoVO.getCurso().getCodigo());
            comandoIncluir.executeUpdate();
        } catch (SQLException ex){
            throw new PersistenciaException("Erro ao incluir aluno - " + ex.getMessage());
        }
    }

    public void alterar(AlunoVO alunoVO) throws PersistenciaException {
        try {
            comandoAlterar.setString(1, alunoVO.getNome());
            comandoAlterar.setDate(2, alunoVO.getDataNascimento().getDataSQL());
            comandoAlterar.setString(3, alunoVO.getNomeMae());
            comandoAlterar.setString(4, alunoVO.getNomePai());
            comandoAlterar.setInt(5, alunoVO.getSexo().ordinal());
            comandoAlterar.setString(6, alunoVO.getEndereco().getLogradouro());
            comandoAlterar.setInt(7, alunoVO.getEndereco().getNumero());
            comandoAlterar.setString(8, alunoVO.getEndereco().getBairro());
            comandoAlterar.setString(9, alunoVO.getEndereco().getCidade());
            comandoAlterar.setString(10, alunoVO.getEndereco().getUf().name());
            comandoAlterar.setInt(11, alunoVO.getCurso().getCodigo());
            comandoAlterar.setInt(12, alunoVO.getMatricula());
            comandoAlterar.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao alterar o aluno - " + ex.getMessage());
        }
    }

    public void excluir(int matricula) throws PersistenciaException {
        try {
            comandoExcluir.setInt(1, matricula);
            comandoExcluir.executeUpdate();
        } catch (SQLException ex){
            throw new PersistenciaException("Erro ao excluir o aluno - " + ex.getMessage());
        }
    }

    public AlunoVO buscarPorMatricula(int matricula) throws PersistenciaException {

        AlunoVO alu = null;

        try {
            comandoBuscarPorMatricula.setInt(1, matricula);
            ResultSet rs = comandoBuscarPorMatricula.executeQuery();
            if(rs.next()) {
                alu = this.montaAlunoVO(rs);
            }
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
        }
        return alu;
    }

    public List<AlunoVO> buscarPorNome(String nome) throws PersistenciaException {
        List<AlunoVO> listaAluno = new ArrayList<AlunoVO>();
        AlunoVO alu = null;

        String comandoSQL = "SELECT * FROM Aluno WHERE UPPER(nome) LIKE '" + nome.trim().toUpperCase() + "%' ORDER BY NOME LIMIT 10";

        try {
            PreparedStatement comando = conexao.getConexao().prepareStatement(comandoSQL);
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                alu = this.montaAlunoVO(rs);
                listaAluno.add(alu);
            }
            comando.close();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por nome - " + ex.getMessage());
        }
        return listaAluno;
    }

    private AlunoVO montaAlunoVO(ResultSet rs) throws PersistenciaException {
        AlunoVO alu = new AlunoVO();
        if(rs != null) {
            try {
                alu.setMatricula(rs.getInt("matricula"));
                alu.setNome(rs.getString("Nome").trim());
                alu.setDataNascimento(new DataUtil(rs.getDate("datanasc").toLocalDate()));
                alu.setNomeMae(rs.getString("nomemae"));
                alu.setNomePai(rs.getString("nomepai"));
                alu.setSexo(EnumSexo.values()[rs.getInt("sexo")]);
                alu.getEndereco().setLogradouro(rs.getString("logradouro"));
                alu.getEndereco().setNumero(rs.getInt("numero"));
                alu.getEndereco().setBairro(rs.getString("bairro"));
                alu.getEndereco().setCidade(rs.getString("cidade"));
                alu.getEndereco().setUf(EnumUF.valueOf(rs.getString("uf")));
                alu.setCurso(this.cursoDAO.buscaPorCodigo(rs.getInt("idcurso")));
            } catch (Exception ex) {
                throw new PersistenciaException("Erro ao acessar os dados do resulttado");
            }
        }
        return alu;
    }

    public void desconectar() throws PersistenciaException {
        try {
            comandoIncluir.close();
            comandoAlterar.close();
            comandoExcluir.close();
            comandoBuscarPorMatricula.close();
            this.conexao.desconectar();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Erro ao desconectar com o banco de dados - " + sqle.getMessage());
        }
    }
}
