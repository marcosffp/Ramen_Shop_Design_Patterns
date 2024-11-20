package br.lpm.business.repository;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public class ImplPedidoRepositorio implements PedidoRepository {

  private final PedidosSingleton pedidosSingleton;

  public ImplPedidoRepositorio(PedidosSingleton pedidosSingleton) {
    this.pedidosSingleton = pedidosSingleton;
  }

  @Override
  public Pedido buscarPedidoPorNumero(int numeroPedido) {
    return pedidosSingleton.getListaPedidos().stream()
        .filter(pedido -> pedido.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElse(null);
  }

  @Override
  public Pedido buscarPedidoPorSenha(String senha) {
    return pedidosSingleton.getListaPedidos().stream()
        .filter(pedido -> pedido.getSenhaCliente().equals(senha))
        .findFirst()
        .orElse(null);
  }



}
