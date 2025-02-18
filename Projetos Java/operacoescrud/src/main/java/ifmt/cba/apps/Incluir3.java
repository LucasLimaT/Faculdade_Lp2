package ifmt.cba.apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Incluir3 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;
        String nome = JOptionPane.showInputDialog("Forneca o nome do grupo do produto");
        float promocao = Float.parseFloat(JOptionPane.showInputDialog("Forneca o nome do grupo de produtos"));
        float margem = Float.parseFloat(JOptionPane.showInputDialog("Fornecao percentual da margem de lucro do grupo de produtos"));
        try {
            conexao = FabricaConexao.obterConexao();
            String sql = "INSERT INTO grupoproduto ( nome, promocao, margemlucro ) VALUES (?, ?, ?)";
            comando.setString(1, nome);
            comando.setFloat(2, promocao);
            comando.setFloat(3, margem);
            comando.executeUpdate();
            ResultSet rs = comando.getGeneratedKeys();
            long chave = 0;
            if(rs.next()) {
                chave = rs.getLong("Codigo");
            }
            System.out.println("Inclusao realizada com sucesso [chave: " + chave + "]");
        } catch (Exception ex) {
            System.out.println("Erro ao incluir grupo de produto" + ex.toString());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }
        }
    }
}
