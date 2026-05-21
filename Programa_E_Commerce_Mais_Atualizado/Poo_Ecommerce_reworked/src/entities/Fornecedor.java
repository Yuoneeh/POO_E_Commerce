package entities;

public class Fornecedor {
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor(String cnpj, String nome, String telefone, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCnpj() { return cnpj; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
}
