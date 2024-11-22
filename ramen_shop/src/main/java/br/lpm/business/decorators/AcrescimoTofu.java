package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoTofu extends AcrescimoDecorator {
  private static final double PRECO_TOFU = 2.70;
  public AcrescimoTofu(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de tofu.";
  }


  
  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_TOFU;
  }
}
