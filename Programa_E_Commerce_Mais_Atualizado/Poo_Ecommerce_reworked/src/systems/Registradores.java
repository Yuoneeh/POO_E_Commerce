package systems;

import java.util.Scanner;
import entities.Categorias;
import entities.Produto;
import entities.Pedidos;
import java.util.List;

public class Registradores { // Removido o 'extends Categoria'

    Scanner sc = new Scanner(System.in);

    // Produto Vars
    public String new_SKU;
    public String new_Nome;
    public Integer new_quant;
    public Double new_preco;
    public String new_categoria;
    public String new_Descricao;

    public void adicionar_produto() {
        System.out.println("Insira a quantidade de produtos DIFERENTES que deseja cadastrar no banco: ");
        int pdd_num = sc.nextInt();
        sc.nextLine(); // Consumir quebra de linha

        for (int i = 0; i < pdd_num; i++) {
            // VOLTOU: Perguntando o SKU primeiro
            System.out.println("Insira o SKU do produto (Ex: PROD001): ");
            new_SKU = sc.nextLine();

            System.out.println("Insira o Nome do produto: ");
            new_Nome = sc.nextLine();

            // Perguntando a descrição
            System.out.println("Insira uma breve Descrição do produto: ");
            String new_desc = sc.nextLine();

            System.out.println("Insira a Quantidade em estoque: ");
            new_quant = sc.nextInt();

            System.out.println("Insira o Preço do produto: ");
            new_preco = sc.nextDouble();
            sc.nextLine(); // Consumir quebra de linha após nextDouble()

            System.out.println("Insira a Categoria do produto: ");
            new_categoria = sc.nextLine();
            // Consumir quebra de linha após nextDouble()

            // Cria o objeto Produto com os dados corretos
            Produto prod = new Produto(new_SKU, new_quant, new_Nome, new_desc, "DISPONIVEL", new_preco, new_categoria);

            dao.ProdutoDAO dao = new dao.ProdutoDAO();
            dao.inserir(prod);

            System.out.println("Produto " + prod.getNome() + " SALVO no banco de dados com sucesso!\n");
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
                                " | Desc.: " + p.getDescricao() + // NOVO: Imprimindo a descrição
                                " | Qtd: " + p.getQuant() +
                                " | Preço: R$" + String.format("%.2f", p.getPreco())
                );
            }
        }
        System.out.println("===========================\n");
    }

    // O FIM DO CHURRASCAMENTO CEREBRAL ESTÁ AQUI
    public void adicionar_pedido() {
        dao.PedidoDAO pedidoDao = new dao.PedidoDAO();

        System.out.println("=== CRIANDO NOVO PEDIDO ===");
        System.out.println("Insira o ID do Pedido (Ex: PED001): ");
        String pdd_cod = sc.nextLine();

        System.out.println("Insira o CPF do cliente: ");
        String cli_cpf = sc.nextLine();

        System.out.println("Insira o valor total do pedido: ");
        Double pdd_valor = sc.nextDouble();
        sc.nextLine();

        System.out.println("Insira o status do pedido (Ex: AGUARDANDO, ENVIADO, ENTREGUE): ");
        String pdd_status = sc.nextLine();

        // Data capturada automaticamente
        java.time.LocalDate pdd_data = java.time.LocalDate.now();

        Pedidos ped = new Pedidos(pdd_cod, pdd_data, pdd_valor, pdd_status, cli_cpf);
        pedidoDao.inserirPedido(ped);

        System.out.println("Pedido " + ped.getPdd_cod() + " registrado com sucesso no banco. Vamos adicionar os itens!\n");

        System.out.println("Quantos produtos diferentes este pedido terá?");
        int pdd_num = sc.nextInt();
        sc.nextLine(); // Consome a quebra de linha após ler o número inteiro

        // O laço começa aqui para coletar cada item do pedido
        for (int i = 0; i < pdd_num; i++) {
            System.out.println("Insira o SKU do produto que deseja incluir neste pedido: ");
            String sku_comprado = sc.nextLine();

            // ====================================================================
            // GANHO DE LINHA 2: VINCULAR PRODUTO AO PEDIDO (Tabela Providenciar)
            // Adicione a linha abaixo aqui, DENTRO do laço for.
            // Esse método vai rodar repetidamente para cada SKU que for digitado,
            // criando várias linhas na tabela 'providenciar' ligadas ao mesmo ID de pedido.
            // ====================================================================
            pedidoDao.inserirProvidenciar(ped.getPdd_cod(), sku_comprado);

            System.out.println("Produto de SKU " + sku_comprado + " vinculado ao pedido " + ped.getPdd_cod() + " no banco!\n");
        }
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
                                " | Status: " + ped.getPdd_status() +
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



}



