package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoCremeAlho extends AcrescimoDecorator {
  private static final double PRECO_CREME_ALHO = 1.50;

  public AcrescimoCremeAlho(Pedido pedido) {
    super(pedido);
  }

  @Override
  public void exibirDetalhes() {
    super.exibirDetalhes();
    System.out.println("Com acréscimo de Creme de Alho.");
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_CREME_ALHO;
  }
}
