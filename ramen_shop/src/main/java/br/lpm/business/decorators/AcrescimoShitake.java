package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoShitake extends AcrescimoDecorator {
  private static final double PRECO_SHITAKE = 6.90;
  public AcrescimoShitake(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de shitake.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_SHITAKE;
  }
}
