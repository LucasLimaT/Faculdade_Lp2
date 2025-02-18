package ifmt.cba.apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Incluir4 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = FabricaConexao.obterConexao();
            Map<String, Integer> listaGrupos = obterGruposProdutos(conexao);
            String nome = JOptionPane.showInputDialog("Forneca o nome do produto");
            int estoque = Integer.parseInt(JOptionPane.showInputDialog("Forneca a quantidade em estoque de produto"));
            float valorCompra = Float.parseFloat(JOptionPane.showInputDialog("Forneca o valor de compra do produto"));
            float promocao = Float.parseFloat(JOptionPane.showInputDialog("Forneca o percentual de promocao de produto"));
            float margem = Float.parseFloat(JOptionPane.showInputDialog("Forneca o percentual da margem de lucro do produto"));
            String nomeGrupo = (String) JOptionPane.showInputDialog(null,
                "Escolha o grupo de produto",
                "Grupos de produto",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listaGrupos.keySet().toArray(),
                listaGrupos.keySet().toArray()[0]);
            comando = conexao.prepareStatement("INSERT INTO produto ( nome, estoque, valorcompra, promocao, margemlucro, grupo ) VALUES (?, ?, ?, ?, ?, ?)");
            comando.setString(1, nome);
            comando.setInt(2, estoque);
            comando.setFloat(3, valorCompra);
            comando.setFloat(4, promocao);
            comando.setFloat(5, margem);
            comando.setInt(6, listaGrupos.get(nomeGrupo));
            comando.executeUpdate();
            System.out.println("Inclusao do produto realizado com sucesso");
        } catch (Exception ex) {
            System.out.println("Erro ao incluir produto" + ex.toString());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }
        }
    }


    public static Map<String, Integer> obterGruposProdutos(Connection conexao) {
        Map<String, Integer> listaGrupos = new HashMap();
        PreparedStatement comando = null;
        try {
            comando = conexao.prepareStatement("SELECT * FROM grupoproduto ORDER BY nome");
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                listaGrupos.put(resultado.getString("nome"), resultado.getInt("codigo"));
            }
            resultado.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar os grupos de produtos" + ex.toString());
        } finally {
            try {
                comando.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }
        }

        return listaGrupos;
    }
}