package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public abstract class AcrescimoDecorator implements Pedido {
  private final Pedido pedido;

  public AcrescimoDecorator(Pedido pedido) {
    this.pedido = pedido;
  }

  @Override
  public String getNomeCliente() {
    return pedido.getNomeCliente();
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
  public int getNumeroPedido() {
    return pedido.getNumeroPedido();
  }

  @Override
  public String getSenhaCliente() {
    return pedido.getSenhaCliente();
  }
}
