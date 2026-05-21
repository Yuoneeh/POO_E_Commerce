package dao;

import db.DB;
import entities.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe DAO (Data Access Object) responsável pelas operações de banco de dados
 * relacionadas à entidade Categorias.
 */
public class CategoriaDAO {
    /**
     * Insere uma nova categoria no banco de dados.
     * * @param categoria Objeto contendo o nome e a descrição da categoria a ser cadastrada.
     */
    public void inserirCategoria(Categorias categoria) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO categoria (cat_nome, cat_desc) VALUES (?, ?)");

            // Prepara a instrução SQL de inserção, utilizando placeholders (?) para prevenir SQL Injection
            st.setString(1, categoria.getCat_nome());
            st.setString(2, categoria.getCat_desc());

            // Executa a alteração no banco de dados
            st.executeUpdate();

        } catch (SQLException e) {
            // Em caso de erro SQL, lança uma RuntimeException para interromper o fluxo e informar o erro
            throw new RuntimeException("Erro ao inserir a categoria: " + e.getMessage());
        } finally {
            // O bloco finally garante que os recursos do banco sejam fechados, independentemente de falhas
            if (st != null) {
                try { st.close(); } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
            }
        }
    }

    /**
     * Lista todas as categorias cadastradas no banco de dados.
     * * @return Uma lista (List) contendo todas as categorias em formato de objeto.
     */
    public List<Categorias> listarCategorias() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        // Lista que vai armazenar as categorias vindas do banco
        List<Categorias> lista = new ArrayList<>();

        try {

            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM categoria");

            // Armazena o resultado da busca na variável ResultSet
            rs = st.executeQuery();

            // Percorre linha por linha do ResultSet enquanto houver resultados
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

            // Fechamento seguro dos recursos ResultSet e PreparedStatement
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
