package br.lpm.business.pedidos;

import java.util.LinkedList;
import java.util.Queue;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;
import br.lpm.business.model.enums.Status;

public class ListaPedidos {
  private static final ListaPedidos INSTANCE = new ListaPedidos();

  private final Queue<Pedido> pedidos = new LinkedList<>();

  private ListaPedidos() {
  }

  public static ListaPedidos getInstance() {
    return INSTANCE;
  }

  public void addPedido(Pedido pedido) throws RamenShopException {
    if (pedido == null) {
      throw new RamenShopException("O pedido não pode ser nulo.");
    }
    pedido.setStatusPedido(Status.EM_PREPARO);

    if (pedidos.stream().anyMatch(p -> p.getNumeroPedido() == pedido.getNumeroPedido())) {
      throw new RamenShopException("Já existe um pedido com o número " + pedido.getNumeroPedido() + ".");
    }

    pedidos.add(pedido);
  }

  public Pedido retirarPedido(int numeroPedido) throws RamenShopException {
    if (pedidos.isEmpty()) {
      throw new RamenShopException("A lista de pedidos está vazia.");
    }

    Pedido pedido = pedidos.stream()
        .filter(p -> p.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElse(null);

    if (pedido == null) {
      throw new RamenShopException("Pedido com número " + numeroPedido + " não encontrado.");
    }

    pedidos.removeIf(p -> p.getNumeroPedido() == numeroPedido);
    return pedido;
  }

  public Pedido getPedido(int numeroPedido) throws RamenShopException {
    if (pedidos.isEmpty()) {
      throw new RamenShopException("A lista de pedidos está vazia.");
    }

    Pedido pedido = pedidos.stream()
        .filter(p -> p.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElse(null);

    if (pedido == null) {
      throw new RamenShopException("Pedido com número " + numeroPedido + " não encontrado.");
    }

    return pedido;
  }

  public int getQuantidadePedidos() {
    return pedidos.size();
  }

  public void removerTodosPedidos() {
    pedidos.clear();
  }
}
