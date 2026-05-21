package dao;

import db.DB;
import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO responsável pelo gerenciamento dos dados da entidade Cliente
 * no banco de dados.
 */
public class ClienteDAO {

    /**
     * Insere um novo cliente no banco de dados.
     * * @param cliente Objeto Cliente contendo os dados (CPF, nome, e-mail, telefone e data).
     */
    public void inserir(Cliente cliente) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO cliente (cli_cpf, cli_nome, cli_email, cli_telefone, cli_dat_cad) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getTelefone());
            // Converte o LocalDate do Java para java.sql.Date antes de salvar no banco
            st.setDate(5, java.sql.Date.valueOf(cliente.getDataCadastro()));

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    /**
     * Recupera uma lista com todos os clientes cadastrados.
     * * @return Lista contendo os objetos Cliente registrados no banco.
     */
    public List<Cliente> listarTodos() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM cliente");
            rs = st.executeQuery();

            // Mapeamento objeto-relacional: Converte cada linha do ResultSet em um objeto Cliente
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("cli_cpf"),
                        rs.getString("cli_nome"),
                        rs.getString("cli_email"),
                        rs.getString("cli_telefone"),
                        rs.getDate("cli_dat_cad").toLocalDate() // Converte de java.sql.Date de volta para LocalDate
                );
                lista.add(cliente);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar clientes: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
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