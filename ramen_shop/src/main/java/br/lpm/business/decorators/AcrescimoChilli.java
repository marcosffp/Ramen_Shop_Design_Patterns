package br.lpm.business.decorators;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Acrescimo;

public class AcrescimoChilli extends AcrescimoDecorator {

    public AcrescimoChilli(Pedido pedido) {
        super(pedido);
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + " com acréscimo de Chilli.";
    }

    @Override
    public double getPrecoTotal() {
        return super.getPrecoTotal() + Acrescimo.CHILLI.getPreco();
    }
}