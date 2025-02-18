package lpii.trabalhofinal.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{
    
    @Column(length = 15, unique = true, nullable = false)
    private String RG;

    @Column(length = 15, unique = true, nullable = false)
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cpf) {
        CPF = cpf;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String rg) {
        RG = rg;
    }
}
