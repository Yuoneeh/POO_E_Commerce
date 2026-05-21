package entities;

public class Fornecedor {
    private String cnpj; // No banco: frn_cnpj
    private String nome; // No banco: frn_nome
    private String telefone; // No banco: frn_telefone
    private String email; // No banco: frn_email

    public Fornecedor(String cnpj, String nome, String telefone, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters
    public String getCnpj() { return cnpj; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    // Setters
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
}
