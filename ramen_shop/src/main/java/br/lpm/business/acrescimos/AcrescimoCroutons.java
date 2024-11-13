package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

public class AcrescimoCroutons extends AcrescimoDecorator {
  private static final double PRECO_CROUTONS = 2.00;
  public AcrescimoCroutons(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de croutons.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_CROUTONS;
  }
  
}
