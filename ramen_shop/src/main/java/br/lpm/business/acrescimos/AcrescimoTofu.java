package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

public class AcrescimoTofu extends AcrescimoDecorator {
  private static final double PRECO_TOFU = 2.70;
  public AcrescimoTofu(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de tofu.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_TOFU;
  }
  
}
