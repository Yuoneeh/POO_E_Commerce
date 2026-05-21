package entities;

import java.util.*;
import application.ProgramaECommerce;
import entities.AdminUser;

public class UserManager {
    public static boolean acessoAutorizado;
    private static String admin_username;
    private static String admin_password;

    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void registrar() {
        System.out.print("Insira Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Insira Senha: ");
        String password = scanner.nextLine();
        System.out.print("Insira Email: ");
        String email = scanner.nextLine();
        System.out.print("Insira Pergunta Secreta ");
        String question = scanner.nextLine();
        System.out.print("Insira Resposta Secreta: ");
        String answer = scanner.nextLine();
        users.add(new User(username, password, email, question, answer));
        System.out.println("Registro Completo.");
    }
    public void login() {
        admin_username = "admin";
        admin_password = "1234";
        System.out.print("Insira Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Insira a Senha: ");
        String password = scanner.nextLine();

        if ((Objects.equals(username, admin_username)) && Objects.equals(password, admin_password)){
            System.out.println("Login bem sucedido como ADMIN.");
            ProgramaECommerce.menuPrincipal();
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("Login bem sucedido.");
                    ProgramaECommerce.menuPrincipal();
            }


        }
        System.out.println("Login falhou. Credenciais incorretas.");
    }
    public void esqueciSenha() {
        System.out.print("Insira seu usuario: ");
        String username = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Responda a pergunta secreta: " + user.getSecretQuestion());
                if (scanner.nextLine().equals(user.getSecretAnswer())) {
                    System.out.print("Insira a nova senha ");
                    user.setPassword(scanner.nextLine());
                    System.out.println("Senha Resetada com sucesso");
                } else {
                    System.out.println("Resposta Incorreta.");
                }
                return;
            }
        }
        System.out.println("Usuario não encontrado.");
    }
    public void start() {
        while (true) {
            System.out.println("\n1. Cadastrar\n2. Login\n3. Esqueci a Senha\n4. Sair");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> registrar();
                case 2 -> login();
                case 3 -> esqueciSenha();
                case 4 -> { return; }
                default -> System.out.println("Escolha Invalida.");
            }
        }
    }
}

