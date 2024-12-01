package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Bebida;

public class BebidaKoCha extends BebidaDecorator {
  public BebidaKoCha(Pedido pedido) {
    super(pedido);
  }

  @Override
  public String exibirDetalhes() {
    return super.exibirDetalhes() + ", Bebida Ko-Cha";
  }


  @Override
  public double getPrecoTotal() {
    return super.getPrecoTotal() + Bebida.KO_CHA.getPreco();
  }

}
