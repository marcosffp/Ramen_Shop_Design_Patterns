package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public abstract class BebidaDecorator implements Pedido {
  private final Pedido pedido;

  public BebidaDecorator(Pedido pedido) {
    this.pedido = pedido;
  }

  @Override
  public void exibirDetalhes() {
    pedido.exibirDetalhes();
  }

  @Override
  public double getPrecoTotal() {
    return pedido.getPrecoTotal();
  }

  @Override
  public double calcularTotal() {
    return pedido.calcularTotal();
  }

  @Override
  public int getNumeroPedido() {
    return pedido.getNumeroPedido();
  }
}
