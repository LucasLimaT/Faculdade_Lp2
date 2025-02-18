package lpii.trabalhofinal.vo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    private Integer precoVenda;
    
    @Column(nullable = false)
    private Float perDesconto;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Float getPerDesconto() {
        return perDesconto;
    }

    public void setPerDesconto(Float perDesconto) {
        this.perDesconto = perDesconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Integer precoVenda) {
        this.precoVenda = precoVenda;
    }
}
