package lpii.trabalhofinal.vo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    
    @Column(length = 255, nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private Float preco_venda;

    @ManyToOne
    @JoinColumn(name = "grupo_produto_id", nullable = false)
    private GrupoProduto grupo_produto;

    @ManyToMany(mappedBy = "produtos")
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "produto")
    private List<ItemVenda> item_venda;
    
    public List<ItemVenda> getItem_venda() {
        return item_venda;
    }

    public void setItem_venda(List<ItemVenda> item_venda) {
        this.item_venda = item_venda;
    }

    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public GrupoProduto getGrupo_produto() {
        return grupo_produto;
    }

    public void setGrupo_produto(GrupoProduto grupo_produto) {
        this.grupo_produto = grupo_produto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPrecoVenda() {
        return preco_venda;
    }

    public void setPrecoVenda(Float precoVenda) {
        this.preco_venda = precoVenda;
    }

    public List<Fornecedor> getFornecedor() {
        return fornecedores;
    }

    public void setFornecedor(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
