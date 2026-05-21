package systems;

import java.util.Scanner;

import application.ProgramaECommerce;
import entities.Categorias;
import entities.Produto;
import entities.Pedidos;
import entities.Cliente;
import java.util.List;

public class Registradores { // Removido o 'extends Categoria'

    Scanner sc = new Scanner(System.in);

    public String new_SKU;
    public String new_Nome;
    public Integer new_quant;
    public Double new_preco;
    public String new_categoria;
    public String new_Descricao;

    public void adicionar_produto() {

        // Instancia o DAO de categoria
        dao.CategoriaDAO categoriaDAO = new dao.CategoriaDAO();

        // Busca todas as categorias do banco
        List<Categorias> categorias = categoriaDAO.listarCategorias();

        // Verifica se existem categorias cadastradas
        if (categorias.isEmpty()) {
            System.out.println("\n====================================");
            System.out.println("ERRO: Nenhuma categoria cadastrada!");
            System.out.println("Cadastre uma categoria antes de adicionar produtos.");
            System.out.println("====================================\n");

            return; // Interrompe o método
        }

        System.out.println("Insira a quantidade de produtos DIFERENTES que deseja cadastrar no banco: ");
        int pdd_num = sc.nextInt();
        sc.nextLine(); // Consumir quebra de linha

        for (int i = 0; i < pdd_num; i++) {

            System.out.println("Insira o SKU do produto (Ex: PROD001): ");
            new_SKU = sc.nextLine();

            System.out.println("Insira o Nome do produto: ");
            new_Nome = sc.nextLine();

            System.out.println("Insira uma breve Descrição do produto: ");
            String new_desc = sc.nextLine();

            System.out.println("Insira a Quantidade em estoque: ");
            new_quant = sc.nextInt();

            System.out.println("Insira o Preço do produto: ");
            new_preco = sc.nextDouble();
            sc.nextLine();

            // Mostra categorias disponíveis
            System.out.println("\n=== Categorias Disponíveis ===");

            for (Categorias cat : categorias) {
                System.out.println("- " + cat.getCat_nome());
            }

            System.out.println("==============================");

            // Loop até o usuário digitar uma categoria válida
            boolean categoriaValida = false;

            while (!categoriaValida) {

                System.out.println("Escolha uma das categorias acima: ");
                new_categoria = sc.nextLine();

                // Verifica se a categoria existe
                for (Categorias cat : categorias) {

                    if (cat.getCat_nome().equalsIgnoreCase(new_categoria)) {
                        categoriaValida = true;

                        // Salva exatamente como está no banco
                        new_categoria = cat.getCat_nome();

                        break;
                    }
                }

                if (!categoriaValida) {
                    System.out.println("Categoria inválida! Escolha uma categoria existente.\n");
                }
            }

            // Cria objeto Produto
            Produto prod = new Produto(
                    new_SKU,
                    new_quant,
                    new_Nome,
                    new_desc,
                    "DISPONIVEL",
                    new_preco,
                    new_categoria
            );

            // Insere no banco
            dao.ProdutoDAO produtoDAO = new dao.ProdutoDAO();
            produtoDAO.inserir(prod);

            System.out.println("\nProduto " + prod.getNome() + " cadastrado com sucesso!");

        }
    }

    public void consultar_estoque() {
        System.out.println("\n=== CONSULTANDO ESTOQUE ===");

        // Instancia o DAO
        dao.ProdutoDAO dao = new dao.ProdutoDAO();

        // Pede para o DAO trazer a lista preenchida do banco
        List<Produto> produtos = dao.listarTodos();

        // Verifica se a lista veio vazia
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio no momento.");
        } else {
            // Laço For-Each: "Para cada 'Produto p' dentro da lista 'produtos'..."
            for (Produto p : produtos) {
                System.out.println(
                        "SKU: " + p.getSku() +
                                " | Nome: " + p.getNome() +
                                " | Desc.: " + p.getDescricao() +
                                " | Qtd: " + p.getQuant() +
                                " | Preço: R$" + String.format("%.2f", p.getPreco()) +
                                " | Categoria: " + String.format(p.getCategoria())
                );
            }
        }
        System.out.println("===========================\n");
    }

    public void atualizar_produto(){
        dao.ProdutoDAO produtoDao = new dao.ProdutoDAO();
        dao.ProdutoDAO dao = new dao.ProdutoDAO();
        System.out.println("Atualizando Produtos");
        List<Produto> produtos = dao.listarTodos();

        // Verifica se a lista veio vazia
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio no momento.");
        } else {
            // Laço For-Each: "Para cada 'Produto p' dentro da lista 'produtos'..."
            for (Produto p : produtos) {
                System.out.println(
                        "SKU: " + p.getSku() +
                                " | Nome: " + p.getNome() +
                                " | Desc.: " + p.getDescricao() +
                                " | Qtd: " + p.getQuant() +
                                " | Preço: R$" + String.format("%.2f", p.getPreco()) +
                                " | Categoria: " + String.format(p.getCategoria())
                );
            }
        }
        System.out.println("===========================\n");
        System.out.println("\nInsira o SKU do  a ser atualizado:");
        consultar_estoque();
        String sku_atualizar = sc.nextLine();
        System.out.println("===========================\n");
        System.out.println("\nInforme o parametro de PRODUTO que deseja atualizar:");
        System.out.println("\n1-Nome\n2- Descrição\n3- Preço\n4- Quantidade\n5- Cancelar\n");
        Integer opcao_menu = sc.nextInt();
        sc.nextLine();
        switch (opcao_menu){
            case 1:
                System.out.println("\nInsira o NOVO NOME do produto:");
                String sku_new_name = sc.nextLine();
                System.out.print(sku_new_name);
                produtoDao.atualizar_produto(sku_atualizar, 1, sku_new_name,"",0.0, 0 );
                break;
            case 2:
                System.out.println("\nInsira a NOVA DESCRIÇÃO do produto:");
                String sku_new_desc = sc.nextLine();
                dao.atualizar_produto(sku_atualizar, 2, "",sku_new_desc, 0.0, 0);
                break;
            case 3:
                System.out.println("\nInsira o NOVO PREÇO do produto:");
                Double sku_new_price = sc.nextDouble();
                dao.atualizar_produto(sku_atualizar, 3, "","", sku_new_price, 0);
                break;
            case 4:
                System.out.println("\nInsira a NOVA QUANTIDADE do produto:");
                Integer sku_new_quant = sc.nextInt();
                dao.atualizar_produto(sku_atualizar, 4, "","", 0.0, sku_new_quant);
                break;
            case 5:
                System.out.println("\nVoltando ao menu principal):");
                ProgramaECommerce.menuPrincipal();
                break;
        }

       // Produto produto = produtoDao.buscarPorSku(sku_atualizar);

    }

    public void apagar_produto(){
        dao.ProdutoDAO produtoDao = new dao.ProdutoDAO();
        dao.ProdutoDAO dao = new dao.ProdutoDAO();

        List<Produto> produtos = dao.listarTodos();

        // Verifica se a lista veio vazia
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio no momento.");
        } else {
            // Laço For-Each: "Para cada 'Produto p' dentro da lista 'produtos'..."
            for (Produto p : produtos) {
                System.out.println(
                        "SKU: " + p.getSku() +
                                " | Nome: " + p.getNome() +
                                " | Desc.: " + p.getDescricao() +
                                " | Qtd: " + p.getQuant() +
                                " | Preço: R$" + String.format("%.2f", p.getPreco()) +
                                " | Categoria: " + String.format(p.getCategoria())
                );
            }
        }
        System.out.println("===========================\n");
        System.out.println("\nInsira o SKU do produto a ser deletado:");
        String sku_delete = sc.nextLine();

        Produto produto = produtoDao.deletar_produto(sku_delete);



    }

    public void adicionar_pedido() {

        dao.PedidoDAO pedidoDao = new dao.PedidoDAO();
        dao.ProdutoDAO produtoDao = new dao.ProdutoDAO();

        System.out.println("=== CRIANDO NOVO PEDIDO ===");

        System.out.println("Insira o ID do Pedido (Ex: PED001): ");
        String pdd_cod = sc.nextLine();

        System.out.println("Insira o CPF do cliente: ");
        String cli_cpf = sc.nextLine();

        java.time.LocalDate pdd_data = java.time.LocalDate.now();

        System.out.println("Quantos produtos diferentes este pedido terá?");
        int pdd_num = sc.nextInt();
        sc.nextLine();

        double valorTotal = 0;

        List<String> skus = new java.util.ArrayList<>();
        List<Integer> quantidades = new java.util.ArrayList<>();

        for (int i = 0; i < pdd_num; i++) {

            System.out.println("\nInsira o SKU do produto:");
            consultar_estoque();
            String sku_comprado = sc.nextLine();

            Produto produto = produtoDao.buscarPorSku(sku_comprado);

            if (produto == null) {

                System.out.println("Produto não encontrado!");
                i--;
                continue;
            }

            System.out.println("Produto encontrado: " + produto.getNome());
            System.out.println("Preço unitário: R$" + String.format("%.2f", produto.getPreco()));

            System.out.println("Quantidade desejada:");
            int quantidadeComprada = sc.nextInt();
            sc.nextLine();

            int estoqueAtual = produtoDao.verificarEstoque(sku_comprado);

            if (quantidadeComprada > estoqueAtual) {

                System.out.println("Estoque insuficiente!");
                System.out.println("Disponível: " + estoqueAtual);

                i--;
                continue;
            }

            valorTotal += produto.getPreco() * quantidadeComprada;

            skus.add(sku_comprado);
            quantidades.add(quantidadeComprada);

            System.out.println("Produto adicionado ao pedido!");
        }

        Pedidos ped = new Pedidos(
                pdd_cod,
                pdd_data,
                valorTotal,
                cli_cpf
        );

        pedidoDao.inserirPedido(ped);

        for (int i = 0; i < skus.size(); i++) {

            String sku = skus.get(i);
            int quantidade = quantidades.get(i);

            pedidoDao.inserirProvidenciar(
                    ped.getPdd_cod(),
                    sku,
                    quantidade
            );

            produtoDao.baixarEstoque(
                    sku,
                    quantidade
            );
        }

        System.out.println("\n=================================");
        System.out.println("PEDIDO CRIADO COM SUCESSO!");
        System.out.println("Valor total: R$" + String.format("%.2f", valorTotal));
        System.out.println("=================================\n");
    }

    public void consultar_pedido() {
        System.out.println("\n=== CONSULTANDO PEDIDOS ===");

        dao.PedidoDAO pedidoDao = new dao.PedidoDAO();
        List<Pedidos> listaPedidos = pedidoDao.listarTodos();

        if (listaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado no banco de dados.");
        } else {
            for (Pedidos ped : listaPedidos) {
                System.out.println(
                        "ID: " + ped.getPdd_cod() +
                                " | Data: " + ped.getPdd_data() +
                                " | Valor: R$" + String.format("%.2f", ped.getPdd_valor()) +
                                " | CPF Cliente: " + ped.getCli_cpf()
                );
            }
        }
        System.out.println("===========================\n");
    }


    public void adicionar_cliente() {
        System.out.println("\n=== CADASTRO DE CLIENTE ===");
        System.out.println("Insira o CPF do cliente (Apenas números, ex: 12345678901): ");
        String cpf = sc.nextLine();

        System.out.println("Insira o Nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Insira o E-mail do cliente: ");
        String email = sc.nextLine();

        System.out.println("Insira o Telefone do cliente: ");
        String telefone = sc.nextLine();

        // Captura automaticamente a data atual do computador para o cadastro
        java.time.LocalDate dataAtual = java.time.LocalDate.now();

        // Instancia o objeto Cliente com a data gerada automaticamente
        entities.Cliente novoCliente = new entities.Cliente(cpf, nome, email, telefone, dataAtual);

        // Envia para o banco de dados através do DAO
        dao.ClienteDAO clienteDao = new dao.ClienteDAO();
        clienteDao.inserir(novoCliente);

        System.out.println("Cliente " + novoCliente.getNome() + " cadastrado com sucesso!\n");
    }

    public void consultar_clientes() {
        System.out.println("\n=== CONSULTANDO CLIENTES ===");

        dao.ClienteDAO clienteDao = new dao.ClienteDAO();
        List<entities.Cliente> clientes = clienteDao.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no sistema.");
        } else {
            for (entities.Cliente c : clientes) {
                System.out.println(
                        "CPF: " + c.getCpf() +
                                " | Nome: " + c.getNome() +
                                " | E-mail: " + c.getEmail() +
                                " | Tel: " + c.getTelefone() +
                                " | Cadastrado em: " + c.getDataCadastro()
                );
            }
        }
        System.out.println("============================\n");
    }

    public void adicionar_Categoria() {
        System.out.println("Insira a quantidade de categorias DIFERENTES que deseja cadastrar no banco: ");
        int cat_num = sc.nextInt();
        sc.nextLine(); // Consumir quebra de linha

        for (int i = 0; i < cat_num; i++) {
            System.out.println("Insira o Nome da Categoria: ");
            new_Nome = sc.nextLine();

            System.out.println("Insira a Descrição da Categoria: ");
            new_Descricao = sc.nextLine();

            // Cria o objeto Produto com os dados corretos
            Categorias cat = new Categorias(new_Nome, new_Descricao);

            dao.CategoriaDAO dao = new dao.CategoriaDAO();
            dao.inserirCategoria(cat);

            System.out.println("Categoria " + cat.getCat_nome() + " pronta para ser salva no banco!\n");
        }
    }

    public void adicionar_fornecedor() {
        System.out.println("\n=== CADASTRO DE FORNECEDOR ===");
        System.out.println("Insira o CNPJ do fornecedor (Apenas números, 14 dígitos): ");
        String cnpj = sc.nextLine();

        System.out.println("Insira o Nome da empresa: ");
        String nome = sc.nextLine();

        System.out.println("Insira o Telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Insira o E-mail: ");
        String email = sc.nextLine();

        entities.Fornecedor novoFornecedor = new entities.Fornecedor(cnpj, nome, telefone, email);

        dao.FornecedorDAO fornecedorDao = new dao.FornecedorDAO();
        fornecedorDao.inserir(novoFornecedor);

        System.out.println("Fornecedor " + novoFornecedor.getNome() + " cadastrado com sucesso no banco!\n");
    }

    public void consultar_categorias() {
        System.out.println("\n=== CONSULTANDO CATEGORIAS ===");

        // Instancia o DAO
        dao.CategoriaDAO dao = new dao.CategoriaDAO();

        // Pede para o DAO trazer a lista preenchida do banco
        List<Categorias> categorias = dao.listarCategorias();

        // Verifica se a lista veio vazia
        if (categorias.isEmpty()) {
            System.out.println("O estoque está vazio no momento.");
        } else {
            // Laço For-Each: "Para cada 'Produto p' dentro da lista 'produtos'..."
            for (Categorias cat : categorias) {
                System.out.println(
                        " | Nome: " + cat.getCat_nome() +
                                " | Descrição: " + cat.getCat_desc()
                );
            }
        }
        System.out.println("===========================\n");
    }

    public void consultar_fornecedores() {
        System.out.println("\n=== CONSULTANDO FORNECEDORES ===");
        dao.FornecedorDAO fornecedorDao = new dao.FornecedorDAO();
        List<entities.Fornecedor> fornecedores = fornecedorDao.listarTodos();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado no sistema.");
        } else {
            for (entities.Fornecedor f : fornecedores) {
                System.out.println(
                        "CNPJ: " + f.getCnpj() +
                                " | Nome: " + f.getNome() +
                                " | Telefone: " + f.getTelefone() +
                                " | E-mail: " + f.getEmail()
                );
            }
        }
        System.out.println("=================================\n");
    }

}



