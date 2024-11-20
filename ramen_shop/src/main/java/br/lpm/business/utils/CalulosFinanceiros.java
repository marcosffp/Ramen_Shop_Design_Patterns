package br.lpm.business.utils;

import java.util.HashMap;
import java.util.Map;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public class CalulosFinanceiros {
  public static double calcularReceitaTotal(PedidosSingleton pedidosSingleton) {
    return pedidosSingleton.getPedidosConcluidos().stream().mapToDouble(Pedido::getPrecoTotal).sum();
  }

  public static Map<String, Long> calularItensVendidos(PedidosSingleton pedidosSingleton) {
    Map<String, Long> itensVendidos = new HashMap<>();
    for (Pedido pedido : pedidosSingleton.getPedidosConcluidos()) {
      String descricao = pedido.getClass().getSimpleName();
      itensVendidos.put(descricao, itensVendidos.getOrDefault(descricao, 0L) + 1);
    }
    return itensVendidos;
  }

  public static double calcularTicketMedio(double receitaTotal, PedidosSingleton pedidosSingleton) {
    int totalPedidos = pedidosSingleton.getPedidosConcluidos().size();
    return totalPedidos > 0 ? receitaTotal / totalPedidos : 0;
  }
}
