package lpii.trabalhofinal.vo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends PessoaJuridica{
    
    @ManyToMany
    @JoinTable(
        name = "fornecedor_produto", 
        joinColumns = @JoinColumn(name = "fornecedor_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
