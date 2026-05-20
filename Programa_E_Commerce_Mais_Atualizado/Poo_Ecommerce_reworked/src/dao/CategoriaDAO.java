package dao;

import db.DB;
import entities.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public void inserirCategoria(Categorias categoria) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO categoria (cat_nome, cat_desc) VALUES (?, ?)");

            st.setString(1, categoria.getCat_nome());
            st.setString(2, categoria.getCat_desc());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a categoria: " + e.getMessage());
        } finally {
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    public List<Categorias> listarCategorias() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        // Lista que vai armazenar as categorias vindas do banco
        List<Categorias> lista = new ArrayList<>();

        try {

            conn = DB.getConnection();

            st = conn.prepareStatement("SELECT * FROM categoria");

            rs = st.executeQuery();

            // Enquanto existir próxima linha no resultado...
            while (rs.next()) {

                // Criamos um objeto Categoria com os dados da linha
                Categorias cat = new Categorias(
                        rs.getString("cat_nome"),
                        rs.getString("cat_desc")
                );

                // Adiciona na lista
                lista.add(cat);
            }

            return lista;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao consultar categorias: " + e.getMessage()
            );

        } finally {

            // Fecha ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            // Fecha PreparedStatement
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }
}
