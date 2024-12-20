package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoCroutons extends AcrescimoDecorator {
  public AcrescimoCroutons(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + ", Acréscimo de Croutons";
  }



  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Acrescimo.CROUTONS.getPreco();
  }
 
}
