package br.lpm.business.controller;

import java.text.DecimalFormat;
import java.util.Scanner;

import br.lpm.business.decorators.*;
import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidoFactory;
import br.lpm.business.pedidos.PedidoRelatorios;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.services.GerenciamentoPedido;


public class PedidoController {
  private final Scanner scanner;
  private final PedidosSingleton pedidosSingleton;
  private final GerenciamentoPedido gerenciamentoPedido;
  private final PedidoFactory ramenFactory;

  public PedidoController(Scanner scanner, PedidosSingleton pedidosSingleton, GerenciamentoPedido gerenciamentoPedido,
      PedidoFactory ramenFactory) {
    this.scanner = scanner;
    this.pedidosSingleton = pedidosSingleton;
    this.gerenciamentoPedido = gerenciamentoPedido;
    this.ramenFactory = ramenFactory;

  }

  public void exibirMenuPrincipal() {
    System.out.println("\nEscolha uma opção:");
    System.out.println("1. Fazer pedido");
    System.out.println("2. Retirar pedido da cozinha");
    System.out.println("3. Retirada de pedido");
    System.out.println("4. Exibir balanço de pedidos");
    System.out.println("5. Exibir progresso dos pedidos");
    System.out.println("6. Sair");
  }

  public void executarOpcao(int escolha) {
    switch (escolha) {
      case 1 -> fazerPedido();
      case 2 -> retirarPedidoCozinha();
      case 3 -> retirarPedido();
      case 4 -> exibirBalanco();
      case 5 -> exibirProgressoPedidos();
      case 6 -> System.out.println("Encerrando sistema. Até a próxima!");
      default -> System.out.println("Opção inválida. Tente novamente.");
    }
  }

  private void fazerPedido() {
    try {
      System.out.println("Informe o nome do cliente:");
      String nomeCliente = scanner.nextLine();
      System.out.println("Informe o tamanho do pedido (Pequeno, Medio, Grande):");
      String tamanho = scanner.nextLine();
      System.out.println("Informe a proteína (Vegano, Boi, Porco):");
      String proteina = scanner.nextLine();
      Pedido pedido = ramenFactory.criarPedido(tamanho, proteina, nomeCliente);
      pedido=adicionarAcrecimos(pedido);
      pedido =adicionarBebida(pedido);
      gerenciamentoPedido.adicionarPedido(pedido);
      System.out.println("Pedido registrado com sucesso!");
      System.out.println("Seu número de pedido é: " + pedido.getNumeroPedido());
      System.out.println("Sua senha para retirada é: " + pedido.getSenhaCliente());
      exibirInformacoesPedido(pedido);
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao criar pedido: " + e.getMessage());
    }

  }

  private Pedido adicionarAcrecimos(Pedido pedido) {
    String opcao;
    do {
      System.out.println("\nDeseja adicionar algum acréscimo?");
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
    return pedido;
  }

  private Pedido adicionarBebida(Pedido pedido) {
    String bebida;
    do {
      System.out.println("\nDeseja adicionar uma bebida?");
      System.out.println("1. Refrigerante (R$ 5,90)");
      System.out.println("2. O-Cha (Verde) (R$ 3,90)");
      System.out.println("3. Ko-Cha (Preto) (R$ 0,00)");
      System.out.println("0. Não adicionar bebida.");
      System.out.print("Escolha uma opção: ");
      bebida = scanner.nextLine();

      switch (bebida) {
        case "1" -> pedido = new BebidaRefrigerante(pedido);
        case "2" -> pedido = new BebidaOCha(pedido);
        case "3" -> pedido = new BebidaKoCha(pedido);
        case "0" -> System.out.println("Nenhuma bebida será adicionada.");
        default -> System.out.println("Opção inválida. Tente novamente.");
      }
    } while (!bebida.equals("0"));
    return pedido;
  }

  private void retirarPedidoCozinha() {
    System.out.println("Informe o número do pedido para confirmar retirada:");
    int numeroPedido = scanner.nextInt();
    scanner.nextLine(); 
    gerenciamentoPedido.retirarPedidoCozinha(numeroPedido);
  }

  private void retirarPedido() {
    System.out.println("Informe a senha para retirar o pedido:");
    String senha = scanner.nextLine();
    verificarPagamento();
    Pedido pedidoPronto = pedidosSingleton.getCozinha().getPedidoPronto();
    if (pedidoPronto == null || !senha.equals(pedidoPronto.getSenhaCliente())) {
      System.out.println("Nenhum pedido disponível para retirada.");
      return;
    }
  }

  private void exibirInformacoesPedido(Pedido pedidoPronto) {
    DecimalFormat dformat = new DecimalFormat("#.##");
    System.out.println("Pedido pronto para retirada: ");
    System.out.println("Cliente: " + pedidoPronto.getNomeCliente());
    System.out.println("Total do pedido: R$ " + dformat.format(pedidoPronto.getPrecoTotal()));
  }

  private void verificarPagamento() {
    System.out.println("O pagamento foi realizado? (sim/não)");
    String pagamento = scanner.nextLine();
    if (pagamento.equalsIgnoreCase("sim")) {
      System.out.println("Pagamento confirmado. Pode retirar seu pedido.");
    } else {
      System.out.println("Lembre-se de realizar o pagamento antes de retirar o pedido.");
    }
  }


  private void exibirBalanco() {
    System.out.println("\nExibindo balanço dos pedidos:");
    PedidoRelatorios relatorios = new PedidoRelatorios(pedidosSingleton);
    relatorios.exibirBalanco();
  }

  private void exibirProgressoPedidos() {
    System.out.println("\nExibindo progresso dos pedidos:");
    PedidoRelatorios relatorios = new PedidoRelatorios(pedidosSingleton);
    relatorios.exibirProgressoPedidos();
  }
}
