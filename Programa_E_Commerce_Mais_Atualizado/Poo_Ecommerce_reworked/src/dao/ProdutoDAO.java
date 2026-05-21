package dao;

import db.DB;
import entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO de gerenciamento da entidade Produto. Visando manter a persistência dos
 * dados relacionados a Produto
 */
public class ProdutoDAO {

    /**
     * Registra um novo produto no catálogo (estoque).
     * * @param produto Objeto Produto com todos os seus atributos (SKU, preço, quantidades, etc).
     */
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

    /**
     * Busca todos os produtos do estoque.
     * * @return Lista contendo todos os Produtos disponíveis.
     */
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

    /**
     * Retorna os dados de um produto específico filtrando pelo seu código único.
     * * @param sku Código identificador único do Produto.
     * @return O objeto Produto correspondente, ou 'null' se não for encontrado.
     */
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

            // Como SKU é Primary Key, espera-se no máximo 1 resultado (por isso usa 'if' em vez de 'while')
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
    /**
     * Apaga um produto com base no seu SKU
     * * @param sku Código identificador único do Produto.
     * @return O objeto Produto correspondente, ou 'null' se não for encontrado.
     */
    public Produto deletar_produto(String sku) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "DELETE FROM produto WHERE pdt_sku = ?");


            st.setString(1, sku);
            st.executeUpdate();


            return null;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Produto atualizar_produto(String sku, Integer parameter, String new_name, String new_desc, Double new_preco, Integer new_quant) {

        Connection conn = null;
        PreparedStatement st = null;
        switch (parameter) {
            case 1:
                try {
                    conn = DB.getConnection();
                    st = conn.prepareStatement(
                            "UPDATE produto " +
                                    "SET pdt_nome = ? " +
                                    "WHERE pdt_sku = ?");

                    st.setString(1, new_name);
                    st.setString(2, sku);
                    st.executeUpdate();


                    return null;


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case 2:
                try {
                    conn = DB.getConnection();
                    st = conn.prepareStatement(
                            "UPDATE produto " +
                                    "SET pdt_desc = ? " +
                                    "WHERE pdt_sku = ?");

                    st.setString(1, new_desc);
                    st.setString(2, sku);
                    st.executeUpdate();


                    return null;


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case 3:
                try {
                conn = DB.getConnection();
                st = conn.prepareStatement(
                        "UPDATE produto " +
                                "SET pdt_preco = ? " +
                                "WHERE pdt_sku = ?");

                st.setDouble(1, new_preco);
                st.setString(2, sku);
                st.executeUpdate();


                return null;


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            case 4:
                try {
                    conn = DB.getConnection();
                    st = conn.prepareStatement(
                            "UPDATE produto " +
                                    "SET pdt_quant = ? " +
                                    "WHERE pdt_sku = ?");

                    st.setInt(1, new_quant);
                    st.setString(2, sku);
                    st.executeUpdate();


                    return null;


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            default:
                System.out.println("\nDeu pau ai ein");
        }
        return null;

    }

    /**
     * Valida a quantidade atual de um item no estoque.
     * * @param pdt_sku Código identificador do produto.
     * @return Número inteiro representando a quantidade disponível.
     */
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
                // Retorna diretamente a coluna de quantidade caso o produto seja localizado
                return rs.getInt("pdt_quant");
            }
            return 0; // Retorna 0 se o produto não existir
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar estoque: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            if (st != null) try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
        }
    }

    /**
     * Desconta uma certa quantidade do estoque de um produto no momento de uma compra.
     * * @param pdt_sku Código do produto vendido.
     * @param quantidadeComprada Quantidade a ser debitada do estoque atual.
     */
    public void baixarEstoque(String pdt_sku, int quantidadeComprada) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            // O UPDATE diminui matematicamente o estoque no próprio banco (pdt_quant - ?)
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