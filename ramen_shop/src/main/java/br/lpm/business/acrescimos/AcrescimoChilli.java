package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

public class AcrescimoChilli extends AcrescimoDecorator {
  private static final double PRECO_CHILLI = 2.50;
  public AcrescimoChilli(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de Chilli.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_CHILLI;
  }
  
}
