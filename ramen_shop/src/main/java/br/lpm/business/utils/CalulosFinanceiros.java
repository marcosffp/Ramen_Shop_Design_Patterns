package br.lpm.business.utils;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public final class CalulosFinanceiros {

  private CalulosFinanceiros() {
  }

  public static double calcularReceitaTotal(PedidosSingleton pedidosSingleton) {
    return pedidosSingleton.getPedidosConcluidos().stream().mapToDouble(Pedido::getPrecoTotal).sum();
  }

  public static double calcularTicketMedio(double receitaTotal, PedidosSingleton pedidosSingleton) {
    long totalPedidos = pedidosSingleton.getPedidosConcluidos()
        .stream()
        .count();
    return totalPedidos > 0 ? receitaTotal / totalPedidos : 0;
  }
}
