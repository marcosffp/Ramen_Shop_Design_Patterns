package br.lpm.business.services;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.PedidoRepository;

public abstract class GerenciamentoPedido {

  private final PedidosSingleton pedidosSingleton;
  private final PedidoRepository pedidoRepository;

  public GerenciamentoPedido(PedidosSingleton pedidosSingleton, PedidoRepository pedidoRepository) {
    this.pedidosSingleton = pedidosSingleton;
    this.pedidoRepository = pedidoRepository;
  }

  public PedidosSingleton getPedidosSingleton() {
    return pedidosSingleton;
  }

  public PedidoRepository getPedidoRepository() {
    return pedidoRepository;
  }

  public abstract void adicionarPedido(Pedido pedido);

  public abstract Pedido retirarPedidoCozinha(int numeroPedido);

  public abstract void retirarPedido(String senha);

}
