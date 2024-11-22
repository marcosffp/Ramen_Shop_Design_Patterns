package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;

public class AcrescimoCroutons extends AcrescimoDecorator {
  private static final double PRECO_CROUTONS = 2.00;
  public AcrescimoCroutons(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de croutons.";
  }



  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + PRECO_CROUTONS;
  }
  
}
