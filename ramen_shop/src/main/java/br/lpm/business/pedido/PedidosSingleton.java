package br.lpm.business.pedido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PedidosSingleton {
  private static PedidosSingleton INSTANCE;
  private Queue<Pedido> listaPedidos = new LinkedList<>();
  private List<Pedido> pedidosConcluidos = new ArrayList<>();

  private PedidosSingleton() {
  }

  public static PedidosSingleton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PedidosSingleton();
    }
    return INSTANCE;
  }

  public void addPedido(Pedido pedido) {
    listaPedidos.add(pedido);
  }

  public void retirarPedido() {
    Pedido pedido = listaPedidos.poll();
    if (pedido != null) {
      pedidosConcluidos.add(pedido);
    }
  }

  public void exibirBalanço() {
    double receitaTotal = 0;
    for (Pedido pedido : pedidosConcluidos) {
      receitaTotal += pedido.getPrecoTotal();
    }
    double ticketMedio = pedidosConcluidos.size() > 0 ? receitaTotal / pedidosConcluidos.size() : 0;

    System.out.println("Balanço do Restaurante:");
    System.out.println("Número de pedidos: " + pedidosConcluidos.size());
    System.out.println("Receita total: R$ " + receitaTotal);
    System.out.println("Ticket médio: R$ " + ticketMedio);
  }
}
