package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoCremeAlho extends AcrescimoDecorator {
  public AcrescimoCremeAlho(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de Creme de Alho.";
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Acrescimo.CREME_ALHO.getPreco();
  }
}
