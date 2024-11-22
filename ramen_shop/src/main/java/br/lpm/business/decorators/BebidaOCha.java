package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class BebidaOCha extends BebidaDecorator {
  private static final double PRECO_KOCHA = 3.90;
  public BebidaOCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com a bebida O-Cha.";
  }

  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_KOCHA;
  }
  
}
