package lpii.trabalhofinal.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa{

    @Column(length = 255, nullable = false)
    private String razaoSocial; 

    @Column(length = 25, nullable = false, unique = true)
    private String cnpj;
    
    
    @Column(length = 255, nullable = false)
    private String nomeFantasia;
    
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
}
