package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

public abstract class AcrescimoDecorator implements Pedido {
  private final Pedido pedido;

  public AcrescimoDecorator(Pedido pedido) {
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
}
