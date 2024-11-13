package br.lpm.business.bebidas;

import br.lpm.business.pedido.Pedido;

public class BebidaRefrigerante extends BebidaDecorator {
  private static final double PRECO_REFRIGERANTE = 5.90;
  public BebidaRefrigerante(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com a bebida Refrigerante.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_REFRIGERANTE;
  }
  
}
