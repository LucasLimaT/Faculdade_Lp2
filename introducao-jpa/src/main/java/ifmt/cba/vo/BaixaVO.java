package ifmt.cba.vo;

import java.sql.Date;
public class BaixaVO {
    
    private int codigo;
    private TipoMotivoBaixa motivo;
    private Date data;
    private ProdutoVO produto;
    private int quantidade;

    public ProdutoVO getProduto() {
        return produto;
    }
    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }
    public TipoMotivoBaixa getMotivo() {
        return motivo;
    }
    public void setMotivo(TipoMotivoBaixa motivo) {
        this.motivo = motivo;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaixaVO other = (BaixaVO) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codigo;
        return hash;
    }

}
