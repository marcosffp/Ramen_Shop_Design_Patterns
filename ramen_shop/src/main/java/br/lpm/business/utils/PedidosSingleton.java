package br.lpm.business.utils;

import br.lpm.business.model.Pedido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PedidosSingleton {
  private static PedidosSingleton INSTANCE;
  private Queue<Pedido> listaPedidos = new LinkedList<>();
  private List<Pedido> pedidosConcluidos = new ArrayList<>();
  private Subject cozinha = new Subject();

  private PedidosSingleton() {
  }

  public static PedidosSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PedidosSingleton();
    }
    return INSTANCE;
  }

  public void addPedido(Pedido pedido) {
    if (pedido != null) {
      listaPedidos.add(pedido);
      System.out.println("Pedido de número " + pedido.getNumeroPedido() + " adicionado à fila.");
    } else {
      System.out.println("Erro: Não é possível adicionar um pedido nulo.");
    }
  }

  public void retirarPedido(String nomeCliente) {
    Pedido pedido = listaPedidos.poll();
    if (pedido != null) {
      pedidosConcluidos.add(pedido);
      cozinha.setPedidoPronto(pedido);
      System.out.println("Pedido " + pedido.getNumeroPedido() + " retirado para processamento.");
    } else {
      System.out.println("Nenhum pedido na fila para ser retirado.");
    }
  }

  public void exibirBalanço() {
    double receitaTotal = 0;

    System.out.println("Balanço do Restaurante:");
    System.out.println("Detalhes dos pedidos concluídos:");

    for (Pedido pedido : pedidosConcluidos) {
      receitaTotal += pedido.getPrecoTotal();
      System.out.println("- Número do Pedido: " + pedido.getNumeroPedido());
      System.out.println("  Descrição: ");
      pedido.exibirDetalhes();
      System.out.println("  Valor Total: R$ " + String.format("%.2f", pedido.getPrecoTotal()));
      System.out.println();
    }

    double ticketMedio = pedidosConcluidos.size() > 0 ? receitaTotal / pedidosConcluidos.size() : 0;

    System.out.println("Resumo do Balanço:");
    System.out.println("Número de pedidos concluídos: " + pedidosConcluidos.size());
    System.out.println("Receita total: R$ " + String.format("%.2f", receitaTotal));
    System.out.println("Ticket médio: R$ " + String.format("%.2f", ticketMedio));
  }

  public Subject getCozinha() {
    return cozinha;
  }
}
