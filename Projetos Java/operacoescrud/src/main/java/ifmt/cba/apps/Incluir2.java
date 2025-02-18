package ifmt.cba.apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Incluir2 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;
        String nome = JOptionPane.showInputDialog("Digite o nome do grupo de produtos");
        Float promocao = Float.parseFloat(JOptionPane.showInputDialog("Digite qual a porcentagem da promocao"));
        Float margem = Float.parseFloat(JOptionPane.showInputDialog("Digite a margem de lucro do grupo de produtos"));
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("INSERT INTO grupoproduto (nome, promocao, margem) VALUES (?, ?, ?)");
            comando.setString(1, nome);
            comando.setFloat(2, promocao);
            comando.setFloat(3, margem);
            comando.executeUpdate();
            System.out.println("Inclusão realizada com sucesso");
        } catch (Exception ex) {
           System.out.println("Erro ao incluir grupo de produtos - " + ex.toString());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch (SQLException ex){
                System.out.println("Erro ao desconectar - " + ex.toString());
            }
        }
    }
}
