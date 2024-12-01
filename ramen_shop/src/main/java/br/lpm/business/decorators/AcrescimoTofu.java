package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoTofu extends AcrescimoDecorator {
  public AcrescimoTofu(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + ", Acréscimo de tofu";
  }


  
  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Acrescimo.TOFU.getPreco();
  }
}
