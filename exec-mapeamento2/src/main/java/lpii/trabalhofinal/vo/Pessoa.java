package lpii.trabalhofinal.vo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    
    @Column(length = 255, nullable = false)
    private String nome;
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
