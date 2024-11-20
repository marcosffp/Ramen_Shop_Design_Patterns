package br.lpm.business.repository;

import br.lpm.business.model.Pedido;

public interface PedidoRepository {
  public Pedido buscarPedidoPorNumero(int numeroPedido);

  public Pedido buscarPedidoPorSenha(String senha);
}
