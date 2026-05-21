package dao;

import db.DB;
import entities.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    //Método 1: Insere apenas na tabela pedido
    public void inserirPedido(Pedidos pedido) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            // Correção 1: Deixamos apenas 4 pontos de interrogação no VALUES
            st = conn.prepareStatement(
                    "INSERT INTO pedido (pdd_id, pdd_data, pdd_valor, cli_cpf) VALUES (?, ?, ?, ?)"
            );
            st.setString(1, pedido.getPdd_cod());
            st.setDate(2, java.sql.Date.valueOf(pedido.getPdd_data()));
            st.setDouble(3, pedido.getPdd_valor());
            st.setString(4, pedido.getCli_cpf());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir o pedido: " + e.getMessage());
        } finally {
            if (st != null) try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
        }
    }

    // Método 2: Insere na tabela 'providenciar' amarrando o Pedido ao Produto
    // Método 2: Insere na tabela 'providenciar' amarrando o Pedido ao Produto com a Quantidade
    public void inserirProvidenciar(String pdd_id, String pdt_sku, int quantidade) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO providenciar (pdd_id, pdt_sku, prov_quant) VALUES (?, ?, ?)");

            st.setString(1, pdd_id);
            st.setString(2, pdt_sku);
            st.setInt(3, quantidade);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao vincular produto ao pedido: " + e.getMessage());
        } finally {
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    // Método 3: Busca todos os pedidos no banco e retorna uma Lista
    public List<Pedidos> listarTodos() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Pedidos> lista = new ArrayList<>();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM pedido");
            rs = st.executeQuery();

            while (rs.next()) {
                Pedidos ped = new Pedidos(
                        rs.getString("pdd_id"),
                        rs.getDate("pdd_data").toLocalDate(),
                        rs.getDouble("pdd_valor"),
                        rs.getString("cli_cpf")
                );
                lista.add(ped);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar pedidos: " + e.getMessage());
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

}