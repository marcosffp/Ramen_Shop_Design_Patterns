package br.lpm.business.acrescimos;

import br.lpm.business.pedido.Pedido;

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
  public double calcularTotal() {
    return super.calcularTotal() + PRECO_CREME_ALHO;
  }
}
