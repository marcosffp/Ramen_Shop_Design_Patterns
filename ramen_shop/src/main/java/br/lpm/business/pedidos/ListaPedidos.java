package br.lpm.business.pedidos;

import br.lpm.business.exception.RamenShopException;
import br.lpm.business.model.Pedido;

import java.util.*;

public class ListaPedidos {
  private static final ListaPedidos INSTANCE = new ListaPedidos();

  private final Queue<Pedido> pedidos = new LinkedList<>();

  private ListaPedidos() {
  }

  public static ListaPedidos getInstance() {
    return INSTANCE;
  }

  public void addiPedido(Pedido pedido) {
    pedidos.add(pedido);
  }

  public Pedido removePedido() {
    return pedidos.poll();
  }

  public Pedido retirarPedido(int numeroPedido) throws RamenShopException {
    Pedido pedido = pedidos.stream()
        .filter(p -> p.getNumeroPedido() == numeroPedido)
        .findFirst()
        .orElse(null);

    if (pedido == null) {
      throw new RamenShopException("Pedido não encontrado");
    }
    pedidos.removeIf(nullPedido -> nullPedido.getNumeroPedido() == numeroPedido);
    return pedido;
  }
}
