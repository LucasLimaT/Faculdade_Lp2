package lpii.trabalhofinal.vo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor extends PessoaFisica{
    
    @Column(nullable = false)
    private Double perComissao;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas;

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Double getPerComissao() {
        return perComissao;
    }

    public void setPerComissao(Double perComissao) {
        this.perComissao = perComissao;
    }
}
