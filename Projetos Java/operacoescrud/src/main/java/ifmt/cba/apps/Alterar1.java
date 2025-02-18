package ifmt.cba.apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ifmt.cba.util.FabricaConexao;

public class Alterar1 {
    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement comando = null;

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Fornece o codigo do grupo de produto a ser alterado"));
        float promocao = Integer.parseInt(JOptionPane.showInputDialog("Forneca o valor da promocao"));
        float margem = Integer.parseInt(JOptionPane.showInputDialog("Forneca o valor da margem de lucro"));
        try {
            conexao = FabricaConexao.obterConexao();
            comando = conexao.prepareStatement("UPDATE grupoproduto SET promocao=?, margemlucro=? WHERE codigo=?");
            comando.setFloat(1, promocao);
            comando.setFloat(2, margem);
            comando.setInt(3, codigo);
            int contRec = comando.executeUpdate();
            System.out.println("Alteracao realizada com sucess");
            System.out.println("Numero de registros alterados: " + contRec);
        } catch (Exception ex) {
            System.out.println("Erro ao alterar o grupo" + ex.toString());
        } finally {
            try {
                comando.close();
                conexao.close();
            } catch(SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }
        }
    }
}
