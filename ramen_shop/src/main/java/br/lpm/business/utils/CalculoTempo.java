package br.lpm.business.utils;

import java.time.Duration;
import java.time.LocalDateTime;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public final class CalculoTempo {

  private CalculoTempo() {
  }

  public static void duracaoProcessamentoPedido(Pedido pedido, PedidosSingleton pedidosSingleton) {
    LocalDateTime tempoInicio = pedidosSingleton.getTemposPreparo().get(pedido.getNumeroPedido());
    if (tempoInicio != null) {
      Duration duracao = Duration.between(tempoInicio, LocalDateTime.now());
      System.out.println("Pedido processado em " + duracao.getSeconds() + " segundos.");
    }
  }

  public static double calcularTempoMedioPreparo(PedidosSingleton pedidosSingleton) {
    return pedidosSingleton.getPedidosConcluidos().stream()
        .mapToLong(
            p -> Duration.between(pedidosSingleton.getTemposPreparo().get(p.getNumeroPedido()), LocalDateTime.now())
                .getSeconds())
        .average()
        .orElse(0);
  }
}
