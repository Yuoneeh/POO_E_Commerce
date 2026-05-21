package ui;

public class menu {
    public static void menuPrincipalUI(){
        System.out.println(" \n=== Menu Principal ===");
        System.out.println("1. Produtos");
        System.out.println("2. Pedidos");
        System.out.println("3. Clientes");
        System.out.println("4. Categorias");
        System.out.println("5. Fornecedores");
        System.out.println("6. Criadores do Projeto");
        System.out.println("7. Instruções básicas de uso do Projeto");
        System.out.println("8. Sair");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuProdutosUI(){
        System.out.println("====================================");
        System.out.println("         SEÇÃO PRODUTOS");
        System.out.println("====================================");
        System.out.println("Qual o proximo passo: ");
        System.out.println("1. Consultar Produtos");
        System.out.println("2. Adicionar Produto ao Estoque");
        System.out.println("3. Voltar para menu principal");
        System.out.println("4. Sair");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuPedidosUI(){
        System.out.println("====================================");
        System.out.println("         SEÇÃO PEDIDOS");
        System.out.println("====================================");
        System.out.println("1. Consultar Pedidos");
        System.out.println("2. Criar Pedido");
        System.out.println("3. Voltar para menu principal");
        System.out.println("4. Sair");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuClientesUI(){
        System.out.println("====================================");
        System.out.println("         SEÇÃO CLIENTES");
        System.out.println("====================================");
        System.out.println("Qual o proximo passo: ");
        System.out.println("1. Consultar Clientes");
        System.out.println("2. Cadastrar Novo Cliente");
        System.out.println("3. Voltar para menu principal");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuCategoriasUI(){
        System.out.println("====================================");
        System.out.println("         SEÇÃO CATEGORIAS");
        System.out.println("====================================");

        System.out.println("1. Consultar Categorias");
        System.out.println("2. Criar Categoria");
        System.out.println("3. Voltar para menu principal");
        System.out.println("4. Sair");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuFornecedoresUI(){
        System.out.println("====================================");
        System.out.println("         SEÇÃO FORNECEDORES");
        System.out.println("====================================");
        System.out.println("Qual o proximo passo: ");
        System.out.println("1. Consultar Fornecedores");
        System.out.println("2. Cadastrar Novo Fornecedor");
        System.out.println("3. Voltar para menu principal");
        System.out.println("4. Sair");

        System.out.print("INSIRA A OPÇÃO DESEJADA: ");
    }
    public static void menuMembrosUI(){
        System.out.println("============================================");
        System.out.println("              MEMBROS DO GRUPO");
        System.out.println("============================================");
        System.out.println("<<Eduardo Felipe Braga Silva>>\n" +
                "<<Gabriel Schmidt >>\n" +
                "<<Gabriel José Couto Pereira >>\n" +
                "<<Igor De Campos Alcantara Rocha>>\n" +
                "<<Pedro da Costa Aguiar>>\n" +
                "<<Renan Oliveira Guirra>>\n" +
                "<<Johann De Carvalho dos Santos>>\n");

    }
    public static void menuinstrucoesUI(){
        System.out.println("============================================");
        System.out.println("Instruções de Uso: \n" +
                "1- Crie um cliente\n" +
                "2 - Crie uma Categoria\n" +
                "3 - Monte seu estoque\n" +
                "4 - Monte quantos pedidos preferir\n");
        System.out.println("============================================");
    }
}
