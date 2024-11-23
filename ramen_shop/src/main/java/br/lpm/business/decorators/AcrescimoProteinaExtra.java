package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoProteinaExtra extends AcrescimoDecorator {
  private static final double PRECO_PROTEINA_EXTRA = 4.00;
  public AcrescimoProteinaExtra(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de proteína extra.";
  }

  

  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_PROTEINA_EXTRA;
  }

  @Override
  public int getQuantidadeItens() {
    return super.getQuantidadeItens() + 1;
  }

}
