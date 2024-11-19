package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoProteinaExtra extends AcrescimoDecorator {
  private static final double PRECO_PROTEINA_EXTRA = 4.00;
  public AcrescimoProteinaExtra(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de proteína extra.");
  }

  @Override
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_PROTEINA_EXTRA;
  }
  

}
