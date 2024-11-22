package br.lpm.business.repository;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public class ImplPedidoRepositorio implements PedidoRepository {

  @Override
  public Pedido buscarPedidoPorNumero(PedidosSingleton pedidosSingleton,int numeroPedido) {
    return pedidosSingleton.getListaPedidos().stream()
        .filter(pedido -> pedido.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElseGet(() -> pedidosSingleton.getPedidosConcluidos().stream()
            .filter(pedido -> pedido.getNumeroPedido() == numeroPedido)
            .findFirst()
            .orElse(null));
  }

  @Override
  public Pedido buscarPedidoPorSenha(PedidosSingleton pedidosSingleton,String senha) {
    return pedidosSingleton.getListaPedidos().stream()
        .filter(pedido -> pedido.getSenhaCliente().equals(senha))
        .findFirst()
        .orElseGet(() -> pedidosSingleton.getPedidosConcluidos().stream()
            .filter(pedido -> pedido.getSenhaCliente().equals(senha))
            .findFirst()
            .orElse(null));
  }



}
