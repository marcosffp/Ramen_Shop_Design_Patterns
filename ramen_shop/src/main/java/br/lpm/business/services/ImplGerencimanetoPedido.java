package br.lpm.business.services;

import java.time.LocalDateTime;

import br.lpm.business.model.Pedido;
import br.lpm.business.pedidos.PedidosSingleton;
import br.lpm.business.repository.PedidoRepository;
import br.lpm.business.utils.CalculoTempo;

public class ImplGerencimanetoPedido extends GerenciamentoPedido {

  public ImplGerencimanetoPedido(PedidosSingleton pedidosSingleton, PedidoRepository pedidoRepository) {
    super(pedidosSingleton, pedidoRepository);
  }

  @Override
  public void adicionarPedido(Pedido pedido) {
    super.getPedidosSingleton().getListaPedidos().add(pedido);
    super.getPedidosSingleton().getTemposPreparo().put(pedido.getNumeroPedido(), LocalDateTime.now());
    super.getPedidosSingleton().getCozinha().setPedidoPronto(pedido);
  }

  @Override
  public void retirarPedidoCozinha(int numeroPedido) {
    Pedido pedido = super.getPedidoRepository().buscarPedidoPorNumero(numeroPedido);
    CalculoTempo.duracaoProcessamentoPedido(pedido, super.getPedidosSingleton());
  }

  @Override
  public void retirarPedido(String senha) {
    Pedido pedidoEncontrado = super.getPedidoRepository().buscarPedidoPorSenha(senha);
    super.getPedidosSingleton().getCozinha().setPedidoPronto(pedidoEncontrado);
    super.getPedidosSingleton().getListaPedidos().remove(pedidoEncontrado);
    super.getPedidosSingleton().getPedidosConcluidos().add(pedidoEncontrado);
  }
}