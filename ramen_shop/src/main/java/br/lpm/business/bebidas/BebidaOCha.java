package br.lpm.business.bebidas;

import br.lpm.business.pedido.Pedido;

public class BebidaOCha extends BebidaDecorator {
  private static final double PRECO_KOCHA = 3.90;
  public BebidaOCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com a bebida O-Cha.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_KOCHA;
  }
  
}
