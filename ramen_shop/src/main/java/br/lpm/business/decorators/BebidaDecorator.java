package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public abstract class BebidaDecorator implements Pedido {
  private final Pedido pedido;

  @Override
  public String getNomeCliente() {
    return pedido.getNomeCliente();
  }

  public BebidaDecorator(Pedido pedido) {
    this.pedido = pedido;
  }

  @Override
  public String exibirDetalhes() {
    return pedido.exibirDetalhes();
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

  @Override
  public int getQuantidadeItens() {
    return pedido.getQuantidadeItens();
  }
}
