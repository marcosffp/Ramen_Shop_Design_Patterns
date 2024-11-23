package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class BebidaKoCha extends BebidaDecorator {
  private static final double PRECO_KOCHA = 0.00;
  public BebidaKoCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com a bebida Ko-Cha.";
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_KOCHA;
  }

  @Override
  public int getQuantidadeItens() {
    return super.getQuantidadeItens() + 1;
  }
}
