package lpii.trabalhofinal.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> item_venda;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public List<ItemVenda> getItem_venda() {
        return item_venda;
    }
    
    public void setItem_venda(List<ItemVenda> item_venda) {
        this.item_venda = item_venda;
    }
    
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
