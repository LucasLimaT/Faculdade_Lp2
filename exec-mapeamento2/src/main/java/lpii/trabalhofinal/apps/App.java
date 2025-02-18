package lpii.trabalhofinal.apps;

import java.time.LocalDate;
import java.util.Arrays;

import jakarta.persistence.EntityManager;
import lpii.trabalhofinal.util.EntityManagerUtil;
import lpii.trabalhofinal.vo.Cliente;
import lpii.trabalhofinal.vo.Fornecedor;
import lpii.trabalhofinal.vo.GrupoProduto;
import lpii.trabalhofinal.vo.ItemVenda;
import lpii.trabalhofinal.vo.Produto;
import lpii.trabalhofinal.vo.Venda;
import lpii.trabalhofinal.vo.Vendedor;

public class App {
    public static void main(String[] args) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();
            
            GrupoProduto grupo_produto_um = new GrupoProduto("Doces");
            GrupoProduto grupo_produto_dois = new GrupoProduto("Carnes");
            GrupoProduto grupo_produto_tres = new GrupoProduto("Frutas");
            
            entityManager.persist(grupo_produto_um);
            entityManager.persist(grupo_produto_dois);
            entityManager.persist(grupo_produto_tres);
            
            Produto produto_um = new Produto();
            produto_um.setNome("Doce de leite");
            produto_um.setGrupo_produto(grupo_produto_um);
            produto_um.setPrecoVenda(5.0f);
            Produto produto_dois = new Produto();
            produto_dois.setNome("Picanha");
            produto_dois.setGrupo_produto(grupo_produto_dois);
            produto_dois.setPrecoVenda(50.0f);
            Produto produto_tres = new Produto();
            produto_tres.setNome("Pera");
            produto_tres.setGrupo_produto(grupo_produto_tres);
            produto_tres.setPrecoVenda(2.0f);
            
            entityManager.persist(produto_um);
            entityManager.persist(produto_dois);
            entityManager.persist(produto_tres);     
            
            Fornecedor fornecedor_um = new Fornecedor();
            fornecedor_um.setNome("Mercado do bairro");
            fornecedor_um.setCnpj("00.000.000/0001-00");
            fornecedor_um.setNomeFantasia("Mercearia do joão");
            fornecedor_um.setProdutos(Arrays.asList(produto_um, produto_dois));
            fornecedor_um.setRazaoSocial("Mercadinho");
            Fornecedor fornecedor_dois = new Fornecedor();
            fornecedor_dois.setNome("Supermercado");
            fornecedor_dois.setCnpj("11.111.111/11111-00");
            fornecedor_dois.setNomeFantasia("Comper");
            fornecedor_dois.setProdutos(Arrays.asList(produto_dois, produto_tres));
            fornecedor_dois.setRazaoSocial("Mercadão");
            Fornecedor fornecedor_tres = new Fornecedor();
            fornecedor_tres.setNome("Hortifrute");
            fornecedor_tres.setCnpj("22.222.222/2221-22");
            fornecedor_tres.setNomeFantasia("Hortifrute");
            fornecedor_tres.setProdutos(Arrays.asList(produto_tres));
            fornecedor_tres.setRazaoSocial("Mercearia");
            
            entityManager.persist(fornecedor_um);
            entityManager.persist(fornecedor_dois);
            entityManager.persist(fornecedor_tres);
            
            Cliente cliente_um = new Cliente();
            cliente_um.setNome("João Silva");
            cliente_um.setCPF("111.111.111-11");
            cliente_um.setRG("12.345.678-9");
            cliente_um.setLimiteCredito(400f);
            Cliente cliente_dois = new Cliente();
            cliente_dois.setNome("Lucas Lima");
            cliente_dois.setCPF("222.222.222-22");
            cliente_dois.setRG("23.456.788-9");
            cliente_dois.setLimiteCredito(1000f);
            Cliente cliente_tres = new Cliente();
            cliente_tres.setNome("Neymar junior");
            cliente_tres.setCPF("333.333.333-33");
            cliente_tres.setRG("34.567.888-9");
            cliente_tres.setLimiteCredito(1000000f);
            
            entityManager.persist(cliente_um);
            entityManager.persist(cliente_dois);
            entityManager.persist(cliente_tres);
            
            Vendedor vendedor_um = new Vendedor();
            vendedor_um.setNome("Maria Souza");
            vendedor_um.setCPF("888.888.888-88");
            vendedor_um.setRG("98.765.432-1");
            vendedor_um.setPerComissao(5.0);
            Vendedor vendedor_dois = new Vendedor();
            vendedor_dois.setNome("Leticia Oliveira");
            vendedor_dois.setCPF("444.444.444-44");
            vendedor_dois.setRG("87.654.322-1");
            vendedor_dois.setPerComissao(5.0);
            Vendedor vendedor_tres = new Vendedor();
            vendedor_tres.setNome("Gustavo Lima");
            vendedor_tres.setCPF("777.777.777-77");
            vendedor_tres.setRG("55.555.555-1");
            vendedor_tres.setPerComissao(5.0);
            
            entityManager.persist(vendedor_um);
            entityManager.persist(vendedor_dois);
            entityManager.persist(vendedor_tres);
            
            Venda venda_um = new Venda();
            venda_um.setDataVenda(LocalDate.now());
            venda_um.setValorTotal(200.0);
            venda_um.setCliente(cliente_um);
            venda_um.setVendedor(vendedor_um);
            
            ItemVenda item_vendido_um = new ItemVenda();
            item_vendido_um.setQuantidade(2);
            item_vendido_um.setPrecoVenda(200);
            item_vendido_um.setPerDesconto(5.0f);
            item_vendido_um.setProduto(produto_um);
            item_vendido_um.setVenda(venda_um);
            
            venda_um.setItem_venda(Arrays.asList(item_vendido_um));
            entityManager.persist(venda_um);
            
            Venda venda_dois = new Venda();
            venda_dois.setDataVenda(LocalDate.now().plusDays(1));
            venda_dois.setValorTotal(150.0);
            venda_dois.setCliente(cliente_dois);
            venda_dois.setVendedor(vendedor_dois);
            
            ItemVenda item_vendido_dois = new ItemVenda();
            item_vendido_dois.setQuantidade(3);
            item_vendido_dois.setPrecoVenda(150);
            item_vendido_dois.setPerDesconto(10.0f);
            item_vendido_dois.setProduto(produto_dois);
            item_vendido_dois.setVenda(venda_dois);
            
            venda_dois.setItem_venda(Arrays.asList(item_vendido_dois));
            entityManager.persist(venda_dois);
            
            Venda venda_tres = new Venda();
            venda_tres.setDataVenda(LocalDate.now().plusDays(2));
            venda_tres.setValorTotal(299.99);
            venda_tres.setCliente(cliente_tres);
            venda_tres.setVendedor(vendedor_tres);
            
            ItemVenda item_vendido_tres = new ItemVenda();
            item_vendido_tres.setQuantidade(1);
            item_vendido_tres.setPrecoVenda(300);
            item_vendido_tres.setPerDesconto(0.0f);
            item_vendido_tres.setProduto(produto_tres);
            item_vendido_tres.setVenda(venda_tres);
            
            venda_tres.setItem_venda(Arrays.asList(item_vendido_tres));
            entityManager.persist(venda_tres);
            
            Venda venda_quatro = new Venda();
            venda_quatro.setDataVenda(LocalDate.now().plusDays(3));
            venda_quatro.setValorTotal(650.0);
            venda_quatro.setCliente(cliente_um);
            venda_quatro.setVendedor(vendedor_dois);
            
            ItemVenda item_um_venda_quadro = new ItemVenda();
            item_um_venda_quadro.setQuantidade(2);
            item_um_venda_quadro.setPrecoVenda(250);
            item_um_venda_quadro.setPerDesconto(5.0f);
            item_um_venda_quadro.setProduto(produto_um);
            item_um_venda_quadro.setVenda(venda_quatro);
            
            ItemVenda item_dois_venda_quadro = new ItemVenda();
            item_dois_venda_quadro.setQuantidade(1);
            item_dois_venda_quadro.setPrecoVenda(400);
            item_dois_venda_quadro.setPerDesconto(0.0f);
            item_dois_venda_quadro.setProduto(produto_dois);
            item_dois_venda_quadro.setVenda(venda_quatro);
            
            venda_quatro.setItem_venda(Arrays.asList(item_um_venda_quadro, item_dois_venda_quadro));
            entityManager.persist(venda_quatro);
            
            Venda venda_cinco = new Venda();
            venda_cinco.setDataVenda(LocalDate.now().plusDays(4));
            venda_cinco.setValorTotal(100.0);
            venda_cinco.setCliente(cliente_dois);
            venda_cinco.setVendedor(vendedor_tres);
            
            ItemVenda item_venda_cinco = new ItemVenda();
            item_venda_cinco.setQuantidade(5);
            item_venda_cinco.setPrecoVenda(100);
            item_venda_cinco.setPerDesconto(2.0f);
            item_venda_cinco.setProduto(produto_tres);
            item_venda_cinco.setVenda(venda_cinco);
            
            venda_cinco.setItem_venda(Arrays.asList(item_venda_cinco));
            entityManager.persist(venda_cinco);
            
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
