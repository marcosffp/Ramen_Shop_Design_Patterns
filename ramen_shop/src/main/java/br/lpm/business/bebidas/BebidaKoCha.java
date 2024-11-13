package br.lpm.business.bebidas;

import br.lpm.business.pedido.Pedido;

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
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_KOCHA;
  }
}
