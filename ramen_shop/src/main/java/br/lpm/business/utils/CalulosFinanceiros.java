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
    Integer quantidadesItensTotal = pedidosSingleton.getPedidosConcluidos().stream()
        .mapToInt(Pedido::getQuantidadeItens).sum();
    return quantidadesItensTotal > 0 ? receitaTotal / quantidadesItensTotal : 0;
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
