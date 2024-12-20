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

  public synchronized void addPedido(Pedido pedido) throws RamenShopException {
    if (pedido == null) {
      throw new RamenShopException("O pedido não pode ser nulo.");
    }
    pedido.setStatusPedido(Status.EM_PREPARO);

    if (pedidos.stream().anyMatch(p -> p.getNumeroPedido() == pedido.getNumeroPedido())) {
      throw new RamenShopException("Já existe um pedido com o número " + pedido.getNumeroPedido() + ".");
    }
    pedidos.add(pedido);
  }

  public synchronized Pedido proximoPedido() throws RamenShopException {
    if (pedidos.isEmpty()) {
      throw new RamenShopException("A lista de pedidos está vazia.");
    }

    Pedido pedido = pedidos.poll();
    if (pedido == null) {
      throw new RamenShopException("Erro ao retirar o próximo pedido.");
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
