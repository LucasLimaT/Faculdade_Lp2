package ifmt.cba.vo;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabela_a1")
public class A1 implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;
    
    @Column(length = 40, nullable = false)
    private String nome;
    
    @OneToOne(fetch= FetchType.EAGER)
    private B1 b1;

    public A1() {
    }

    public A1(String nome) {
        this.nome = nome;
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

    
    public B1 getB1() {
        return b1;
    }

    public void setB1(B1 b1) {
        this.b1 = b1;
    }
}

