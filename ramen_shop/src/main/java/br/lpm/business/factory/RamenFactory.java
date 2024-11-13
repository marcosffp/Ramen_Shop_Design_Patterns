package br.lpm.business.factory;

import br.lpm.business.pedido.Pedido;
import br.lpm.business.pedido.PedidoGrande;
import br.lpm.business.pedido.PedidoMedio;
import br.lpm.business.pedido.PedidoPequeno;

public class RamenFactory {

  public Pedido criarPedido(String tipoTamanho, String proteina) {
    if (tipoTamanho == null || proteina == null) {
      return null;
    }

    Pedido pedido = null;
    if (tipoTamanho.equalsIgnoreCase("PEQUENO")) {
      pedido = new PedidoPequeno(proteina);
    } else if (tipoTamanho.equalsIgnoreCase("MEDIO")) {
      pedido = new PedidoMedio(proteina);
    } else if (tipoTamanho.equalsIgnoreCase("GRANDE")) {
      pedido = new PedidoGrande(proteina);
    }
    return pedido;
  }
}
