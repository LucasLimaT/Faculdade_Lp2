package ifmt.cba.vo;

public class CursoVO {

    private int codigo;
    private String nome;
    private int cargahoraria;
    private int numsemestre;

    public CursoVO() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(int cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public int getNumsemestre() {
        return numsemestre;
    }

    public void setNumsemestre(int numsemestre) {
        this.numsemestre = numsemestre;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CursoVO other = (CursoVO) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }
}
