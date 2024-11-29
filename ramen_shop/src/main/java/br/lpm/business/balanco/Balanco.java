package br.lpm.business.balanco;

import java.util.ArrayList;
import java.util.List;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Status;

public class Balanco {
  private static final Balanco INSTANCE = new Balanco();
  private final List<Pedido> pedidosConcluidos = new ArrayList<>();

  private Balanco() {
  }

  public static Balanco getInstance() {
    return INSTANCE;
  }

  public void addPedidoConcluidos(Pedido pedido) throws RamenShopException {
    if (pedido == null) {
      throw new RamenShopException("O pedido não pode ser nulo.");
    }

    if (pedido.getStatusPedido() != Status.RETIRADO) {
      throw new RamenShopException(
          "O pedido deve estar com status PENDENTE para ser concluído. Status atual: "
              + pedido.getStatusPedido());
    }
    pedidosConcluidos.add(pedido);
  }

  public void removePedidoConcluidos(Pedido pedido) throws RamenShopException {
    if (pedido == null) {
      throw new RamenShopException("O pedido não pode ser nulo.");
    }

    if (!pedidosConcluidos.contains(pedido)) {
      throw new RamenShopException("O pedido não está na lista de pedidos concluídos.");
    }

    pedidosConcluidos.remove(pedido);
  }

  public String exibirBalanco() throws RamenShopException {
    if (pedidosConcluidos.isEmpty()) {
      throw new RamenShopException("Não há pedidos concluídos para exibir o balanço.");
    }

    double receita = pedidosConcluidos.stream()
        .mapToDouble(p -> p.getPrecoTotal())
        .sum();

    double ticketMedio = receita / pedidosConcluidos.size();

    StringBuilder relatorio = new StringBuilder();
    relatorio.append("=== Balanço Final do Restaurante ===\n");
    relatorio.append("Lista de Pedidos Realizados:\n");

    for (Pedido pedido : pedidosConcluidos) {
      relatorio.append("- Pedido Nº ")
          .append(pedido.getNumeroPedido())
          .append(": Cliente: ")
          .append(pedido.getNomeCliente())
          .append(", Tamanho: ")
          .append(pedido.getTamanhoPedido())
          .append(", Proteína: ")
          .append(pedido.getProteinaPedido())
          .append(pedido.exibirDetalhes())
          .append(", Status: ")
          .append(pedido.getStatusPedido())
          .append("\n");
    }

    relatorio.append("\nQuantidade de Pedidos: ").append(pedidosConcluidos.size());
    relatorio.append("\nReceita Total: R$ ").append(String.format("%.2f", receita));
    relatorio.append("\nTicket Médio: R$ ").append(String.format("%.2f", ticketMedio));

    return relatorio.toString();
  }
}
