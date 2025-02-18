package lpii.trabalhofinal.vo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica{
    
    @Column(nullable = false)
    private Float limiteCredito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas;
    
    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Float getLimiteCredito() {
        return limiteCredito;
    }    
    
    public void setLimiteCredito(Float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
