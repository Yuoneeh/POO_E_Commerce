package dao;

import db.DB;
import entities.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO para realizar as operações de persistência relacionadas
 * aos Fornecedores do E-Commerce.
 */
public class FornecedorDAO {

    /**
     * Cadastra um novo fornecedor.
     * * @param fornecedor Objeto Fornecedor a ser inserido.
     */
    public void inserir(Fornecedor fornecedor) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            // Script DML para inserção dos dados principais do fornecedor
            st = conn.prepareStatement(
                    "INSERT INTO fornecedor (frn_cnpj, frn_nome, frn_telefone, frn_email) " +
                            "VALUES (?, ?, ?, ?)"
            );
            st.setString(1, fornecedor.getCnpj());
            st.setString(2, fornecedor.getNome());
            st.setString(3, fornecedor.getTelefone());
            st.setString(4, fornecedor.getEmail());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fornecedor: " + e.getMessage());
        } finally {
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    /**
     * Consulta todos os fornecedores no banco.
     * * @return Lista de objetos Fornecedor.
     */
    public List<Fornecedor> listarTodos() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Fornecedor> lista = new ArrayList<>();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM fornecedor");
            rs = st.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor(
                        rs.getString("frn_cnpj"),
                        rs.getString("frn_nome"),
                        rs.getString("frn_telefone"),
                        rs.getString("frn_email")
                );
                lista.add(f);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar fornecedores: " + e.getMessage());
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