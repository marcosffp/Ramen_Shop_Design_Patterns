package br.lpm.business.services;


import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.PedidoRepository;


public class ImplGerencimanetoPedido extends GerenciamentoPedido {

  public ImplGerencimanetoPedido(PedidosSingleton pedidosSingleton, PedidoRepository pedidoRepository) {
    super(pedidosSingleton, pedidoRepository);
  }

  @Override
  public void adicionarPedido(Pedido pedido) {
    super.getPedidosSingleton().getListaPedidos().add(pedido);
    super.getPedidosSingleton().getCozinha().setPedidoPronto(pedido);
  }

  @Override
  public void retirarPedidoCozinha(int numeroPedido) {
    Pedido pedido = super.getPedidoRepository().buscarPedidoPorNumero(super.getPedidosSingleton(),numeroPedido);
    super.getPedidosSingleton().getCozinha().setPedidoPronto(pedido);
  }

  @Override
  public void retirarPedido(String senha) {
    Pedido pedidoEncontrado = super.getPedidoRepository().buscarPedidoPorSenha(super.getPedidosSingleton(),senha);
    super.getPedidosSingleton().getListaPedidos().remove(pedidoEncontrado);
    super.getPedidosSingleton().getPedidosConcluidos().add(pedidoEncontrado);
  }
}