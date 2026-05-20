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
            st = conn.prepareStatement("INSERT INTO pedido (pdd_id, pdd_nf) VALUES (?, ?)");

            st.setString(1, pedido.getPdd_cod());
            st.setString(2, pedido.getPdd_nf());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir o pedido: " + e.getMessage());
        } finally {
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    // Método 2: Insere na tabela 'providenciar' amarrando o Pedido ao Produto
    public void inserirProvidenciar(String pdd_id, String pdt_sku) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO providenciar (pdd_id, pdt_sku) VALUES (?, ?)");

            st.setString(1, pdd_id);
            st.setString(2, pdt_sku);

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
                // Cria um objeto Pedidos para cada linha que vier do banco
                Pedidos ped = new Pedidos(
                        rs.getString("pdd_id"),
                        rs.getString("pdd_nf")
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