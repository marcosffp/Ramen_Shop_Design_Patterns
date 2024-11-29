package br.lpm.business.pedidos;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Proteina;
import br.lpm.business.model.enums.Tamanho;

public class PedidoFactory {

  public Pedido criarPedido(String tipoTamanho, String tipoProteina, String nomeCliente)
      throws RamenShopException {

    if (tipoTamanho == null || tipoTamanho.isEmpty()) {
      throw new RamenShopException("Tamanho do pedido não pode ser vazio");
    }

    if (tipoProteina == null || tipoProteina.isEmpty()) {
      throw new RamenShopException("Proteína do pedido não pode ser vazia");
    }

    if (nomeCliente == null || nomeCliente.isEmpty()) {
      throw new RamenShopException("Nome do cliente não pode ser vazio");
    }

    Tamanho tamanho = Tamanho.valueOf(tipoTamanho.toUpperCase());
    Proteina proteina = Proteina.valueOf(tipoProteina.toUpperCase());

    return switch (tamanho) {
      case PEQUENO -> new PedidoPequeno(nomeCliente, tamanho, proteina);
      case MEDIO -> new PedidoMedio(nomeCliente, tamanho, proteina);
      case GRANDE -> new PedidoGrande(nomeCliente, tamanho, proteina);
    };
  }
}
