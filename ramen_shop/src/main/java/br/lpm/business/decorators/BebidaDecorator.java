package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public abstract class BebidaDecorator extends Pedido {
  private final Pedido pedido;

  public BebidaDecorator(Pedido pedido) {
    super(pedido.getNomeCliente(), pedido.getTamanhoPedido(), pedido.getProteinaPedido(),pedido.getNumeroPedido());
    this.pedido = pedido;
  }

  @Override
  public int getNumeroPedido() {
    return pedido.getNumeroPedido();
  }

  @Override
  public double getPrecoTotal() {
    return pedido.getPrecoTotal();
  }

  @Override
  public String exibirDetalhes() {
    return pedido.exibirDetalhes();
  }
}
