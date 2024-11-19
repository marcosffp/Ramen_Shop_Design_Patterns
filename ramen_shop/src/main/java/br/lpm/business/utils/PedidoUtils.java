package br.lpm.business.utils;

import java.time.Duration;
import java.time.LocalDateTime;

import br.lpm.business.model.Pedido;

public class PedidoUtils {

  public static void addPedido(Pedido pedido, PedidosSingleton pedidosSingleton) {
    if (pedido != null) {
      pedidosSingleton.getListaPedidos().add(pedido);
      pedidosSingleton.getTemposPreparo().put(pedido.getNumeroPedido(), LocalDateTime.now());
      System.out.println("Pedido de número " + pedido.getNumeroPedido() + " adicionado à fila.");
    } else {
      System.out.println("Erro: Não é possível adicionar um pedido nulo.");
    }
  }

  public static void retirarPedido(PedidosSingleton pedidosSingleton) {
    Pedido pedido = pedidosSingleton.getListaPedidos().poll();
    if (pedido != null) {
      pedidosSingleton.getPedidosConcluidos().add(pedido);

      // Marca o pedido como pronto na cozinha
      pedidosSingleton.getCozinha().setPedidoPronto(pedido);

      LocalDateTime tempoInicio = pedidosSingleton.getTemposPreparo().get(pedido.getNumeroPedido());
      if (tempoInicio != null) {
        Duration duracao = Duration.between(tempoInicio, LocalDateTime.now());
        System.out.println("Pedido " + pedido.getNumeroPedido() + " processado em " +
            duracao.getSeconds() + " segundos.");
      }
    } else {
      System.out.println("Nenhum pedido na fila para ser retirado.");
    }
  }
  

  public static void confirmarRetirada(int numeroPedido, PedidosSingleton pedidosSingleton) {
    Pedido pedido = pedidosSingleton.getPedidosConcluidos().stream()
        .filter(p -> p.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElse(null);
    if (pedido != null) {
      System.out.println("Pedido número " + numeroPedido + " confirmado como retirado.");
    } else {
      System.out.println("Erro: Pedido não encontrado nos pedidos concluídos.");
    }
  }
  
}
