package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Bebida;

public class BebidaRefrigerante extends BebidaDecorator {
  public BebidaRefrigerante(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
   return super.exibirDetalhes() + " com a bebida Refrigerante.";
  }

  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Bebida.REFRIGERANTE.getPreco();
  }
  
}
