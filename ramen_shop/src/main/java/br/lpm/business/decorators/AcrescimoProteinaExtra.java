package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoProteinaExtra extends AcrescimoDecorator {
  public AcrescimoProteinaExtra(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + ", Acréscimo de proteína extra";
  }

  

  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Acrescimo.PROTEINA_EXTRA.getPreco();
  }
}
