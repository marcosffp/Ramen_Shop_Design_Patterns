package br.lpm.business.repository;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;

public interface PedidoRepository {
  public Pedido buscarPedidoPorNumero(PedidosSingleton pedidosSingleton,int numeroPedido);

  public Pedido buscarPedidoPorSenha(PedidosSingleton pedidosSingleton,String senha);
}
