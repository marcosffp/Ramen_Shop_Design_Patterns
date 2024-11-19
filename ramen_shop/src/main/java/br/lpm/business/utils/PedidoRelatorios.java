package br.lpm.business.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import br.lpm.business.model.Pedido;

public class PedidoRelatorios {
  public static void exibirProgressoPedidos(PedidosSingleton pedidosSingleton) {
    System.out.println("Progresso dos Pedidos:");
    System.out.println("Em Espera:");
    for (Pedido pedido : pedidosSingleton.getListaPedidos()) {
      System.out.println("- Pedido #" + pedido.getNumeroPedido());
    }
    System.out.println("\nConcluídos:");
    for (Pedido pedido : pedidosSingleton.getPedidosConcluidos()) {
      System.out.println("- Pedido #" + pedido.getNumeroPedido());
    }
  }

  public static void exibirBalanco(PedidosSingleton pedidosSingleton) {
    double receitaTotal = 0;
    Map<String, Long> itensVendidos = new HashMap<>();

    System.out.println("Balanço do Restaurante:");
    System.out.println("Detalhes dos pedidos concluídos:");

    for (Pedido pedido : pedidosSingleton.getPedidosConcluidos()) {
      receitaTotal += pedido.getPrecoTotal();
      String descricao = pedido.getClass().getSimpleName();
      itensVendidos.put(descricao, itensVendidos.getOrDefault(descricao, 0L) + 1);

      System.out.println("- Número do Pedido: " + pedido.getNumeroPedido());
      System.out.println("  Descrição: ");
      pedido.exibirDetalhes();
      System.out.println("  Valor Total: R$ " + String.format("%.2f", pedido.getPrecoTotal()));
      System.out.println();
    }

    double ticketMedio = pedidosSingleton.getPedidosConcluidos().size() > 0
        ? receitaTotal / pedidosSingleton.getPedidosConcluidos().size()
        : 0;
    double tempoMedioPreparo = pedidosSingleton.getPedidosConcluidos().stream()
        .mapToLong(p -> Duration.between(pedidosSingleton.getTemposPreparo().get(p.getNumeroPedido()), LocalDateTime.now())
            .getSeconds())
        .average()
        .orElse(0);

    System.out.println("Resumo do Balanço:");
    System.out.println("Número de pedidos concluídos: " + pedidosSingleton.getPedidosConcluidos().size());
    System.out.println("Receita total: R$ " + String.format("%.2f", receitaTotal));
    System.out.println("Ticket médio: R$ " + String.format("%.2f", ticketMedio));
    System.out.println("Tempo médio de preparo: " + String.format("%.2f", tempoMedioPreparo) + " segundos.");
    System.out.println("Itens mais vendidos:");
    itensVendidos.forEach((item, quantidade) -> System.out.println("- " + item + ": " + quantidade + " vendidos."));
  }
}
