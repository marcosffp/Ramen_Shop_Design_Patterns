package br.lpm.business.services;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public abstract class PedidoRelatorios {
  private final PedidosSingleton pedidosSingleton;

  public PedidoRelatorios(PedidosSingleton pedidosSingleton) {
    this.pedidosSingleton = pedidosSingleton;
  }

  public abstract void exibirProgressoPedidos();

  public abstract String exibirPedidos(String titulo, Iterable<Pedido> pedidos);

  public abstract void exibirDetalhesBalanco();

  public abstract void exibirResumoBalanco();

  public abstract String obterDetalhesPedidosConcluidos();

  public abstract String obterResumoBalanco(double receitaTotal, double ticketMedio, double ticketUltimoPedido);
  
  public PedidosSingleton getPedidosSingleton() {
    return pedidosSingleton;
  }
}
