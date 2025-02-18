package ifmt.cba.vo;

import ifmt.cba.util.DataUtil;

public class AlunoVO {

    private int matricula;
    private String nome;
    private DataUtil dataNascimento;
    private String nomeMae;
    private String nomePai;
    private EnumSexo sexo;
    private EnderecoVO endereco;
    private CursoVO curso;

    public AlunoVO() {
        this.endereco = new EnderecoVO();
        this.matricula = 0;
        this.nome = "";
        this.nomeMae = "";
        this.nomePai = "";
        this.sexo = EnumSexo.FEMININO;
        this.curso = new CursoVO();
    }

    public AlunoVO(int matricula, String nome, EnumSexo sexo, DataUtil dataNascimento, String nomeMae, String nomePai, CursoVO cursoVO) {
        this();
        this.matricula = matricula;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.curso = cursoVO;
    }

    public int getMatricula() {
        return matricula;
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    
    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public EnderecoVO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoVO endereco) {
        this.endereco = endereco;
    }

    public DataUtil getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataUtil dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return matricula + ", " + nome + ", " + sexo + ", residente em: " + endereco;
    }
}
