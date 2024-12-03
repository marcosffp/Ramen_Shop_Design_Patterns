package br.lpm.main;

import br.lpm.business.balanco.Balanco;
import br.lpm.business.controller.RamenShopController;
import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.observer.Cozinha;
import br.lpm.business.observer.Subject;
import br.lpm.business.pedidos.ListaPedidos;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.util.GeradorIdPedido;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ListaPedidos listaPedidos = ListaPedidos.getInstance();
    private static Balanco balanco = Balanco.getInstance();
    private static Subject cozinha = new Cozinha();
    private static PedidoFactory ramenFactory = new PedidoFactory();
    private static RamenShopController ramenController = new RamenShopController(listaPedidos, balanco, ramenFactory,
            cozinha);

    public static void main(String[] args) {
        preparandoAmbiente();
        Main main = new Main();
        main.exibirMenuPrincipal();
    }

    public static void preparandoAmbiente() {
        ListaPedidos.getInstance().removerTodosPedidos();
        Balanco.getInstance().removerTodosPedidosConcluidos();
        GeradorIdPedido.reset();
    }

    public void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            try {
                System.out.println("\n--- Ramen Shop ---");
                System.out.println("1. Fazer Pedido");
                System.out.println("2. Processar Pedido");
                System.out.println("3. Ver Balanço");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> fazerPedido(scanner);
                    case 2 -> processarPedido(scanner);
                    case 3 -> exibirBalanco();
                    case 4 -> {
                        executando = false;
                        System.out.println("\nObrigado por usar o Ramen Shop!");
                    }
                    default -> System.out.println("\nOpção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Por favor, digite um número válido.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\nErro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public void fazerPedido(Scanner scanner) {
        try {
            System.out.print("\nNome do Cliente: ");
            String nomeCliente = scanner.nextLine();
            String tamanhoEscolhido = "";
            while (tamanhoEscolhido.isEmpty()) {
                System.out.println("\nEscolha o tamanho do Ramen:");
                System.out.println("1. Pequeno (R$ 9,90)");
                System.out.println("2. Médio (R$ 12,90)");
                System.out.println("3. Grande (R$ 15,90)");

                try {
                    int opcaoTamanho = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoTamanho) {
                        case 1 -> tamanhoEscolhido = "Pequeno";
                        case 2 -> tamanhoEscolhido = "Medio";
                        case 3 -> tamanhoEscolhido = "Grande";
                        default -> System.out.println("Opção de tamanho inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número correspondente ao tamanho.");
                    scanner.nextLine();
                }
            }

            String proteinaEscolhida = "";
            while (proteinaEscolhida.isEmpty()) {
                System.out.println("\nEscolha a proteína:");
                System.out.println("1. Vegano (+ R$ 3,90)");
                System.out.println("2. Boi (+ R$ 7,90)");
                System.out.println("3. Porco (+ R$ 5,90)");

                try {
                    int opcaoProteina = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoProteina) {
                        case 1 -> proteinaEscolhida = "Vegano";
                        case 2 -> proteinaEscolhida = "Boi";
                        case 3 -> proteinaEscolhida = "Porco";
                        default -> System.out.println("Opção de proteína inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número correspondente à proteína.");
                    scanner.nextLine();
                }
            }

            List<Integer> opcoesSelecionadas = new ArrayList<>();
            System.out.println("\nEscolha os acréscimos (Digite 7 para finalizar):");
            boolean continuarAcrescimos = true;
            while (continuarAcrescimos) {
                System.out.println("1. Proteína Extra (+ R$ 4,00)");
                System.out.println("2. Chilli (+ R$ 2,50)");
                System.out.println("3. Creme de Alho (+ R$ 1,50)");
                System.out.println("4. Croutons (+ R$ 2,00)");
                System.out.println("5. Shitake (+ R$ 6,90)");
                System.out.println("6. Tofu (+ R$ 2,70)");
                System.out.println("7. Finalizar Acréscimos");

                try {
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    if (opcao == 7) {
                        continuarAcrescimos = false;
                    } else if (opcao >= 1 && opcao <= 6) {
                        opcoesSelecionadas.add(opcao);
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número correspondente ao acréscimo.");
                    scanner.nextLine();
                }
            }

            System.out.println("\nEscolha as bebidas (Digite 4 para finalizar):");
            boolean continuarBebidas = true;
            while (continuarBebidas) {
                System.out.println("1. Refrigerante (R$ 5,90)");
                System.out.println("2. Chá Oolong (R$ 3,90)");
                System.out.println("3. Chá Preto (R$ 0,00)");
                System.out.println("4. Finalizar Bebidas");

                try {
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    if (opcao == 4) {
                        continuarBebidas = false;
                    } else if (opcao >= 1 && opcao <= 3) {
                        opcoesSelecionadas.add(opcao + 6);
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número correspondente à bebida.");
                    scanner.nextLine();
                }
            }

            Pedido pedido = ramenController.fazerPedido(nomeCliente, tamanhoEscolhido, proteinaEscolhida,
                    opcoesSelecionadas);

            System.out.println("\nPedido criado com sucesso! Detalhes:");
            System.out.println("---------------------------------------");
            System.out.println(ramenController.obterInformacoesPedido(pedido));
            System.out.println("---------------------------------------");

        } catch (RamenShopException e) {
            System.out.println("\nErro ao processar a operação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nErro inesperado: " + e.getMessage());
        }
    }

    public void processarPedido(Scanner scanner) {
        try {
            System.out.println("Processando pedido...");
            String mensagem = ramenController.processarPedido();
            System.out.println(mensagem);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: Entrada inválida. O número do pedido deve ser numérico.");
        } catch (RamenShopException e) {
            System.out.println("\nErro ao retirar o pedido: " + e.getMessage());
        }
    }

    public void exibirBalanco() {
        try {
            String balancoStr = ramenController.obterBalanco();
            System.out.println("\n--- Balanço Financeiro ---");
            System.out.println(balancoStr);
        } catch (RamenShopException e) {
            System.out.println("\nErro ao exibir o balanço: " + e.getMessage());
        }
    }
}