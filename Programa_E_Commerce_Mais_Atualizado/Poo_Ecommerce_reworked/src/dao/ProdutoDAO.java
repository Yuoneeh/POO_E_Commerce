package dao;

import db.DB;
import entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            // DEPOIS
            st = conn.prepareStatement(
                    "INSERT INTO produto (pdt_sku, pdt_quant, pdt_nome, pdt_desc, pdt_status, pdt_preco, pdt_categoria) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            st.setString(1, produto.getSku());
            st.setInt(2, produto.getQuant());
            st.setString(3, produto.getNome());
            st.setString(4, produto.getDescricao());
            st.setString(5, produto.getStatus());
            st.setDouble(6, produto.getPreco());
            st.setString(7, produto.getCategoria());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM produto");
            rs = st.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto(
                        rs.getString("pdt_sku"),
                        rs.getInt("pdt_quant"),
                        rs.getString("pdt_nome"),
                        rs.getString("pdt_desc"), // CORREÇÃO 2: Nome exato da coluna no banco
                        rs.getString("pdt_status"),
                        rs.getDouble("pdt_preco"),
                        rs.getString("pdt_categoria")
                );

                lista.add(prod);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar produtos: " + e.getMessage());
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    public Produto buscarPorSku(String sku) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "SELECT * FROM produto WHERE pdt_sku = ?"
            );

            st.setString(1, sku);

            rs = st.executeQuery();

            if (rs.next()) {

                return new Produto(
                        rs.getString("pdt_sku"),
                        rs.getInt("pdt_quant"),
                        rs.getString("pdt_nome"),
                        rs.getString("pdt_desc"),
                        rs.getString("pdt_status"),
                        rs.getDouble("pdt_preco"),
                        rs.getString("pdt_categoria")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para consultar a quantidade atual de um produto no banco
    public int verificarEstoque(String pdt_sku) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT pdt_quant FROM produto WHERE pdt_sku = ?");
            st.setString(1, pdt_sku);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt("pdt_quant");
            }
            return 0; // Retorna 0 se o produto não for encontrado
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar estoque: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            if (st != null) try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
        }
    }

    // Método para subtrair a quantidade comprada do estoque atual
    public void baixarEstoque(String pdt_sku, int quantidadeComprada) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            // Atualiza a tabela diminuindo a quantidade
            st = conn.prepareStatement("UPDATE produto SET pdt_quant = pdt_quant - ? WHERE pdt_sku = ?");
            st.setInt(1, quantidadeComprada);
            st.setString(2, pdt_sku);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao baixar estoque: " + e.getMessage());
        } finally {
            if (st != null) try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
        }
    }
}