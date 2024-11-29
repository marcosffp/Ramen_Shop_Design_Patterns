package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoShitake extends AcrescimoDecorator {
  public AcrescimoShitake(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com acréscimo de shitake.";
  }



  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Acrescimo.SHITAKE.getPreco();
  }

}
