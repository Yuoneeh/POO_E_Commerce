package entities;

public class Categorias {
    private String cat_nome;
    private String cat_desc;

    public Categorias(String cat_nome, String cat_desc) {
        this.cat_nome = cat_nome;
        this.cat_desc = cat_desc;
    }

    public String getCat_nome() {
        return cat_nome;
    }

    public void setCat_nome(String Cat_nome) {
        this.cat_nome = cat_nome;
    }

    public String getCat_desc() {
        return cat_desc;
    }

    public void setCat_desc(String Cat_nome) {
        this.cat_nome = cat_nome;
    }
}
