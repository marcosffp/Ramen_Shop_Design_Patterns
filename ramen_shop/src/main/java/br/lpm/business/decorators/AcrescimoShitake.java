package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoShitake extends AcrescimoDecorator {
  private static final double PRECO_SHITAKE = 6.90;
  public AcrescimoShitake(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de shitake.";
  }



  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_SHITAKE;
  }
}
