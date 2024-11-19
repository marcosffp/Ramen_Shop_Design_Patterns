package br.lpm.main;

import java.util.Scanner;
import br.lpm.business.utils.Observer;
import br.lpm.business.decorators.*;
import br.lpm.business.model.ClienteObserver;
import br.lpm.business.model.Pedido;
import br.lpm.business.services.NotificacaoService;
import br.lpm.business.utils.PedidosSingleton;
import br.lpm.business.utils.RamenFactory;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializa o singleton de pedidos e os serviços
        PedidosSingleton pedidosSingleton = PedidosSingleton.getInstance();
        RamenFactory ramenFactory = new RamenFactory();
        NotificacaoService notificacaoService = new NotificacaoService();

        System.out.println("Bem-vindo à Ramen Factory!");

        // Loop principal
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Fazer pedido");
            System.out.println("2. Retirar pedido da cozinha");
            System.out.println("3. Confirmar retirada de pedido");
            System.out.println("4. Exibir balanço de pedidos");
            System.out.println("5. Exibir progresso dos pedidos");
            System.out.println("6. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1 -> {
                    System.out.println("Informe o nome do cliente:");
                    String nomeCliente = scanner.nextLine();

                    System.out.println("Informe o tamanho do pedido (Pequeno, Medio, Grande):");
                    String tamanho = scanner.nextLine();

                    System.out.println("Informe a proteína (Vegano, Boi, Porco):");
                    String proteina = scanner.nextLine();

                    try {
                        Pedido pedido = ramenFactory.criarPedido(tamanho, proteina, nomeCliente);

                        // Adicionar acréscimos
                        String opcao;
                        do {
                            System.out.println("Deseja adicionar algum acréscimo?");
                            System.out.println("1. Proteína Extra (+ R$ 4,00)");
                            System.out.println("2. Chilli (+ R$ 2,50)");
                            System.out.println("3. Crème Alho (+ R$ 1,50)");
                            System.out.println("4. Croutons (+ R$ 2,00)");
                            System.out.println("5. Shitake (+ R$ 6,90)");
                            System.out.println("6. Tofu (+ R$ 2,70)");
                            System.out.println("0. Não adicionar mais acréscimos.");
                            System.out.print("Escolha uma opção: ");
                            opcao = scanner.nextLine();

                            switch (opcao) {
                                case "1" -> pedido = new AcrescimoProteinaExtra(pedido);
                                case "2" -> pedido = new AcrescimoChilli(pedido);
                                case "3" -> pedido = new AcrescimoCremeAlho(pedido);
                                case "4" -> pedido = new AcrescimoCroutons(pedido);
                                case "5" -> pedido = new AcrescimoShitake(pedido);
                                case "6" -> pedido = new AcrescimoTofu(pedido);
                                case "0" -> System.out.println("Nenhum acréscimo adicional será adicionado.");
                                default -> System.out.println("Opção inválida. Tente novamente.");
                            }
                        } while (!opcao.equals("0"));

                        // Adicionar bebida
                        String bebida;
                        do {
                            System.out.println("Deseja adicionar uma bebida?");
                            System.out.println("1. Refrigerante (R$ 5,90)");
                            System.out.println("2. O-Cha (Verde) (R$ 3,90)");
                            System.out.println("3. Ko-Cha (Preto) (R$ 0,00)");
                            System.out.println("0. Não adicionar bebida.");
                            System.out.print("Escolha uma opção: ");
                            bebida = scanner.nextLine();

                            switch (bebida) {
                                case "1" -> {
                                    pedido = new BebidaRefrigerante(pedido);
                                    System.out.println("Refrigerante adicionado.");
                                }
                                case "2" -> {
                                    pedido = new BebidaOCha(pedido);
                                    System.out.println("O-Cha (Verde) adicionado.");
                                }
                                case "3" -> {
                                    pedido = new BebidaKoCha(pedido);
                                    System.out.println("Ko-Cha (Preto) adicionado.");
                                }
                                case "0" -> System.out.println("Nenhuma bebida será adicionada.");
                                default -> System.out.println("Opção inválida. Tente novamente.");
                            }
                        } while (!bebida.equals("0"));
                        pedido.calcularTotal();
                        pedidosSingleton.addPedido(pedido);
                        System.out.println("Pedido registrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao criar pedido: " + e.getMessage());
                    }
                }

                case 2 -> {
                    pedidosSingleton.retirarPedido();

                    // Obter o pedido pronto e notificar o cliente
                    Pedido pedidoPronto = pedidosSingleton.getCozinha().getPedidoPronto();

                    if (pedidoPronto != null) {
                        Observer cliente = new ClienteObserver(pedidosSingleton.getCozinha(),
                                pedidoPronto.getNomeCliente());
                        notificacaoService.notificarCliente(pedidosSingleton.getCozinha(), cliente);
                    } else {
                        System.out.println("Nenhum pedido disponível para retirada.");
                    }
                }

                case 3 -> {
                    System.out.println("Informe o número do pedido para confirmar retirada:");
                    int numeroPedido = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    pedidosSingleton.confirmarRetirada(numeroPedido);
                }
                case 4 -> {
                    System.out.println("Exibindo balanço dos pedidos:");
                    pedidosSingleton.exibirBalanco();
                }
                case 5 -> {
                    System.out.println("Exibindo progresso dos pedidos:");
                    pedidosSingleton.exibirProgressoPedidos();
                }
                case 6 -> {
                    System.out.println("Encerrando sistema. Até a próxima!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
