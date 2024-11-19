package br.lpm.business.utils;

import br.lpm.business.model.Pedido;
import br.lpm.business.model.PedidoGrande;
import br.lpm.business.model.PedidoMedio;
import br.lpm.business.model.PedidoPequeno;

public class RamenFactory {

  public Pedido criarPedido(String tipoTamanho, String proteina) {
    if (tipoTamanho == null || proteina == null || tipoTamanho.isBlank() || proteina.isBlank()) {
      throw new IllegalArgumentException("Tamanho ou proteína não podem ser nulos ou vazios.");
    }

    return switch (tipoTamanho.toUpperCase()) {
      case "PEQUENO" -> new PedidoPequeno(proteina);
      case "MEDIO" -> new PedidoMedio(proteina);
      case "GRANDE" -> new PedidoGrande(proteina);
      default -> throw new IllegalArgumentException("Tipo de tamanho inválido: " + tipoTamanho);
    };
  }
}
