package br.lpm.business.services;


import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.PedidoRepository;


public class ImplGerenciamentoPedido extends GerenciamentoPedido {

  public ImplGerenciamentoPedido(PedidosSingleton pedidosSingleton, PedidoRepository pedidoRepository) {
    super(pedidosSingleton, pedidoRepository);
  }

  @Override
  public void adicionarPedido(Pedido pedido) {
    super.getPedidosSingleton().getListaPedidos().add(pedido);
  }

  @Override
  public Pedido retirarPedidoCozinha(int numeroPedido) {
    return super.getPedidoRepository().buscarPedidoPorNumero(super.getPedidosSingleton(), numeroPedido);
  }

  @Override
  public void retirarPedido(String senha) {
    Pedido pedidoEncontrado = super.getPedidoRepository().buscarPedidoPorSenha(super.getPedidosSingleton(),senha);
    super.getPedidosSingleton().getListaPedidos().remove(pedidoEncontrado);
    super.getPedidosSingleton().getPedidosConcluidos().add(pedidoEncontrado);
  }
}