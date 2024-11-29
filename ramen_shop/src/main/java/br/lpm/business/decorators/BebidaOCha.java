package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Bebida;

public class BebidaOCha extends BebidaDecorator {
  public BebidaOCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + " com a bebida O-Cha.";
  }

  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Bebida.O_CHA.getPreco();
  }

  
}
