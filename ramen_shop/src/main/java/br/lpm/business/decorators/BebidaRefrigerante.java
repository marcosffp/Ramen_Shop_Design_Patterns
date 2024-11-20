package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

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
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_REFRIGERANTE;
  }
  
}