package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoCremeAlho extends AcrescimoDecorator {
  private static final double PRECO_CREME_ALHO = 1.50;

  public AcrescimoCremeAlho(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de Creme de Alho.";
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_CREME_ALHO;
  }

  @Override
  public int getQuantidadeItens() {
    return super.getQuantidadeItens() + 1;
  }
}
