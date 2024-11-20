package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class BebidaKoCha extends BebidaDecorator {
  private static final double PRECO_KOCHA = 0.00;
  public BebidaKoCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com a bebida Ko-Cha.");
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_KOCHA;
  }
}
