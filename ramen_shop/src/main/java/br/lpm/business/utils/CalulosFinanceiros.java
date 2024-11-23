package br.lpm.business.utils;

import java.util.Map;
import java.util.stream.Collectors;

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

  public static Map<Integer, Double> calcularTicketMedioPorPedido(PedidosSingleton pedidosSingleton) {
    return pedidosSingleton.getPedidosConcluidos()
        .stream()
        .collect(Collectors.toMap(
            Pedido::getNumeroPedido,
            pedido -> (double) (pedido.getQuantidadeItens() > 0
                ? pedido.getPrecoTotal() / pedido.getQuantidadeItens()
                : 0.0)));
  }
  
  public static double calcularTicketUltimoPedido(PedidosSingleton pedidosSingleton) {
    if (pedidosSingleton.getPedidosConcluidos().isEmpty()) {
      return 0.0; 
    }

    Pedido ultimoPedido = pedidosSingleton.getPedidosConcluidos()
        .get(pedidosSingleton.getPedidosConcluidos().size() - 1);

    return ultimoPedido.getQuantidadeItens() > 0
        ? ultimoPedido.getPrecoTotal() / (double) ultimoPedido.getQuantidadeItens()
        : 0.0;
  }

}
