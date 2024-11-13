package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

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
